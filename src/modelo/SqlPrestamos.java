package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class SqlPrestamos extends Conexion {

    

    private boolean prestarMaterial(String sql, Object... parametros) {

        PreparedStatement ps = null;
        Connection con = getConexion();

        try {
            ps = con.prepareStatement(sql);

            // Configura los parámetros de la consulta preparada
            for (int i = 0; i < parametros.length; i++) {
                ps.setObject(i + 1, parametros[i]);
            }

            // Ejecuta la consulta preparada
            ps.execute();

            JOptionPane.showMessageDialog(null, "Material prestado correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);

            return true;
        } catch (SQLException ex) {
            Logger.getLogger(SqlMateriales.class.getName()).log(Level.SEVERE, null, ex);

            JOptionPane.showMessageDialog(null, "Error al prestar material", "Error", JOptionPane.ERROR_MESSAGE);

            return false;
        }
    }

    private boolean LimitePrestaciones(int idUsuario) {

        String sql = "SELECT doc_prestados, limite FROM usuarios WHERE id = ?";
        try {
            Connection con = getConexion();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idUsuario);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int limit = rs.getInt("limite");

                int prestados = rs.getInt("doc_prestados");

                if (prestados == limit) {
                    JOptionPane.showMessageDialog(null, "Ha llegado al limite de documentos que se pueden prestar", "Error", JOptionPane.ERROR_MESSAGE);
                    return false;
                }
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(SqlMateriales.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Ya no se pueden prestar mas documentos", "Error", JOptionPane.ERROR_MESSAGE);

        }
        return false;
    }

    private boolean verificarCantidadMaximaPrestamo(String tipoMaterial, String codigoIdentificacion, int idUsuario) {
        if (!LimitePrestaciones(idUsuario)) {
            return false;
        }
        String sql = "SELECT UnidadesDisponibles FROM " + tipoMaterial + "  WHERE CodigoIdentificacion = ? ";
        try {
            Connection con = getConexion();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, codigoIdentificacion);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int unidadesdisponibles = rs.getInt("UnidadesDisponibles");

                if (unidadesdisponibles > 0) {
                    return true;
                } else {
                    JOptionPane.showMessageDialog(null, "No hay suficientes unidades disponibles para el préstamo", "Error", JOptionPane.ERROR_MESSAGE);
                    return false;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(SqlMateriales.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Error al verificar la cantidad máxima de préstamo", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }

    public boolean prestarCD(String codigoIdentificacion, int idUsuario) {
        if (!verificarCantidadMaximaPrestamo("cds", codigoIdentificacion, idUsuario)) {
            return false;
        }

        String sql = "UPDATE cds SET UnidadesPrestados = UnidadesPrestados + 1, UnidadesDisponibles = UnidadesDisponibles - 1 WHERE CodigoIdentificacion = ?";
        if (prestarMaterial(sql, codigoIdentificacion)) {
            // Actualiza la tabla de usuarios
            SqlUsuarios sqlUsuarios = new SqlUsuarios();
            sqlUsuarios.actualizarDocumentosPrestados(idUsuario, obtenerCantidadDocumentosPrestados(idUsuario) + 1);

            return true;
        } else {
            return false;
        }

    }

    public boolean prestarLibro(String codigoIdentificacion, int idUsuario) {
        if (!verificarCantidadMaximaPrestamo("libros", codigoIdentificacion, idUsuario)) {
            return false;
        }

        String sql = "UPDATE libros SET UnidadesPrestados = UnidadesPrestados + 1, UnidadesDisponibles = UnidadesDisponibles - 1 WHERE CodigoIdentificacion = ?";
        if (prestarMaterial(sql, codigoIdentificacion)) {
            // Actualiza la tabla de usuarios
            SqlUsuarios sqlUsuarios = new SqlUsuarios();
            sqlUsuarios.actualizarDocumentosPrestados(idUsuario, obtenerCantidadDocumentosPrestados(idUsuario) + 1);

            return true;
        } else {
            return false;
        }
    }

    public boolean prestarObra(String codigoIdentificacion, int idUsuario) {
        if (!verificarCantidadMaximaPrestamo("obras", codigoIdentificacion, idUsuario)) {
            return false;
        }

        String sql = "UPDATE obras SET UnidadesPrestados = UnidadesPrestados + 1, UnidadesDisponibles = UnidadesDisponibles - 1 WHERE CodigoIdentificacion = ?";
        if (prestarMaterial(sql, codigoIdentificacion)) {
            // Actualiza la tabla de usuarios
            SqlUsuarios sqlUsuarios = new SqlUsuarios();
            sqlUsuarios.actualizarDocumentosPrestados(idUsuario, obtenerCantidadDocumentosPrestados(idUsuario) + 1);

            return true;
        } else {
            return false;
        }
    }

    public boolean prestarRevista(String codigoIdentificacion, int idUsuario) {
        if (!verificarCantidadMaximaPrestamo("revistas", codigoIdentificacion, idUsuario)) {
            return false;
        }

        String sql = "UPDATE revistas SET UnidadesPrestados = UnidadesPrestados + 1, UnidadesDisponibles = UnidadesDisponibles - 1 WHERE CodigoIdentificacion = ?";
        if (prestarMaterial(sql, codigoIdentificacion)) {
            // Actualiza la tabla de usuarios
            SqlUsuarios sqlUsuarios = new SqlUsuarios();
            sqlUsuarios.actualizarDocumentosPrestados(idUsuario, obtenerCantidadDocumentosPrestados(idUsuario) + 1);

            return true;
        } else {
            return false;
        }
    }

    public boolean prestarTesis(String codigoIdentificacion, int idUsuario) {
        if (!verificarCantidadMaximaPrestamo("tesis", codigoIdentificacion, idUsuario)) {
            return false;
        }

        String sql = "UPDATE tesis SET UnidadesPrestados = UnidadesPrestados + 1, UnidadesDisponibles = UnidadesDisponibles - 1 WHERE CodigoIdentificacion = ?";
        if (prestarMaterial(sql, codigoIdentificacion)) {
            // Actualiza la tabla de usuarios
            SqlUsuarios sqlUsuarios = new SqlUsuarios();
            sqlUsuarios.actualizarDocumentosPrestados(idUsuario, obtenerCantidadDocumentosPrestados(idUsuario) + 1);

            return true;
        } else {
            return false;
        }
    }

    // Agrega métodos similares para libros, obras, revistas y tesis
    private int obtenerCantidadDocumentosPrestados(int idUsuario) {
        // Implementa la lógica para obtener la cantidad de documentos prestados por el usuario con el ID proporcionado
        // Puedes realizar una consulta SQL o usar métodos de otras clases según tu diseño.
        // Devuelve la cantidad de documentos prestados por el usuario.

        return 0;

    }

    public boolean ponerMora(int numeroMora) {
        Connection con = getConexion();
        PreparedStatement ps = null;

        String sql = "UPDATE tipo_usuario SET mora = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, numeroMora);

            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Modificacion realizada con exito");
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(SqlPrestamos.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "La modificacion no se pudo realizar");

            return false;
        }

    }

    public class ResultadoRegistro {

        private boolean exitoso;
        private int idGenerado;

        public ResultadoRegistro(boolean exitoso, int idGenerado) {
            this.exitoso = exitoso;
            this.idGenerado = idGenerado;
        }

        public boolean isExitoso() {
            return exitoso;
        }

        public int getIdGenerado() {
            return idGenerado;
        }
    }

    public ResultadoRegistro registrarPrestamo(String codigoidentificacion, String nombre, String fechaInicio) {
        Connection con = getConexion();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql1 = "SELECT mora FROM usuarios WHERE usuario = ?";

        try {
            ps = con.prepareStatement(sql1);
            ps.setString(1, nombre);
            ResultSet rs1 = ps.executeQuery();
            if (rs1.next()) {
                int debe = rs1.getInt("mora");
                if (debe != 0) {

                    JOptionPane.showMessageDialog(null, "El usuario tiene una mora pendiente y no puede realizar préstamos.", "Error", JOptionPane.ERROR_MESSAGE);
                    return new ResultadoRegistro(false, -1);
                }
            }

            String sql = "INSERT INTO prestamos (CodigoIdentificacion, usuario, IniciaPrestamo) VALUES (?, ?, ?)";
            ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, codigoidentificacion);
            ps.setString(2, nombre);
            ps.setString(3, fechaInicio);

            int affectedRows = ps.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("La inserción falló, no se generó ningún ID.");
            }

            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int idGenerado = generatedKeys.getInt(1);
                    JOptionPane.showMessageDialog(null, "ID generado: " + idGenerado);
                    return new ResultadoRegistro(true, idGenerado);
                } else {
                    throw new SQLException("La inserción falló, no se generó ningún ID.");
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(SqlPrestamos.class.getName()).log(Level.SEVERE, null, ex);
            return new ResultadoRegistro(false, -1); // Puedes ajustar el valor predeterminado según tus necesidades.
        } finally {
            // Cerrar recursos (ps, rs, con) en un bloque finally si es necesario.
            // Esto asegura que los recursos se cierren correctamente, incluso si ocurre una excepción.
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean devolverMaterial(String id, String textCodigo, String finaliza, int dato, String material, int datotipo) {
        Connection con = getConexion();

        PreparedStatement ps = null;

        String sql1 = "UPDATE prestamos SET FinalizaPrestamo = ? WHERE ID = ?";
        String sql2 = "UPDATE usuarios SET doc_prestados = doc_prestados - 1 WHERE id = ?";
        String sql3 = "UPDATE " + material + " SET UnidadesDisponibles = UnidadesDisponibles + 1, UnidadesPrestados = UnidadesPrestados - 1 WHERE CodigoIdentificacion = ?";
        String sql4 = "SELECT DATEDIFF(FinalizaPrestamo,IniciaPrestamo) AS DiffDate FROM prestamos WHERE ID = ?";
        String sql5 = "SELECT mora FROM tipo_usuario WHERE id = 2";
        String sql6 = "SELECT mora FROM tipo_usuario WHERE id = 3";
        String sql7 = "UPDATE usuarios SET mora = ? WHERE ID = ?";
        try {
            ps = con.prepareStatement(sql1);
            ps.setString(1, finaliza);
            ps.setString(2, id);
            ps.executeUpdate();

            ps = con.prepareStatement(sql2);
            ps.setInt(1, dato);
            ps.executeUpdate();

            ps = con.prepareStatement(sql3);
            ps.setString(1, textCodigo);
            ps.executeUpdate();

            ps = con.prepareStatement(sql4);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int diffDate = rs.getInt("DiffDate");
                if (datotipo == 2) {
                    if (diffDate > 6) {
                        int diasDeDiferencia = diffDate - 6;
                        ps = con.prepareStatement(sql5);
                        ResultSet rs1 = ps.executeQuery();

                        if (rs1.next()) {
                            int moral = rs1.getInt("mora");
                            int morafinal = diasDeDiferencia * moral;

                            ps = con.prepareStatement(sql7);
                            ps.setInt(1, morafinal);
                            ps.setInt(2, dato);
                            ps.executeUpdate();

                            JOptionPane.showMessageDialog(null, "Se modifico la mora del profesor");
                            return true;
                        } else {
                            JOptionPane.showMessageDialog(null, "NO SE MODIFICO PROFESOR");
                            return false;
                        }
                    }
                } else if (datotipo == 3) {
                    if (diffDate > 4) {
                        int diasDeDiferencia = diffDate - 4;
                        ps = con.prepareStatement(sql6);
                        ResultSet rs2 = ps.executeQuery();

                        if (rs2.next()) {
                            int moral = rs2.getInt("mora");
                            int morafinal = diasDeDiferencia * moral;

                            ps = con.prepareStatement(sql7);
                            ps.setInt(1, morafinal);
                            ps.setInt(2, dato);
                            ps.executeUpdate();

                            JOptionPane.showMessageDialog(null, "Se modifico la mora del alumno");
                            return true;
                        } else {
                            JOptionPane.showMessageDialog(null, "NO SE MODIFICO ALUMNO");
                            return false;
                        }
                    }
                }

            }

            JOptionPane.showMessageDialog(null, "El material ha sido devuelto");
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(SqlPrestamos.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Hubo un error al devolver el material");

            return false;
        }
    }
}
