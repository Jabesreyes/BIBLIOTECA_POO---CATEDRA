package modelo;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class SqlMateriales extends Conexion {

    public boolean consultarCds(DefaultTableModel model) {
        try {
            Connection con = getConexion();
            String sql = "SELECT * FROM cds";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            // Llena el modelo de la tabla con los datos de los CDs
            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getInt("ID"),
                    rs.getString("CodigoIdentificacion"),
                    rs.getString("Titulo"),
                    rs.getString("Artista"),
                    rs.getString("Genero"),
                    rs.getString("Duracion"),
                    rs.getInt("NumeroCanciones"),
                    rs.getString("Ubicacion"),
                    rs.getInt("Unidades"),
                    rs.getInt("UnidadesPrestados"),
                    rs.getInt("UnidadesDisponibles")
                });
            }
            return true;            
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "No se pudo consultar");
            return false;
        }
    }
    public boolean consultarLibros(DefaultTableModel model) {
        try {
            Connection con = getConexion();
            String sql = "SELECT * FROM libros";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            // Llena el modelo de la tabla con los datos de los CDs
            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getInt("ID"),
                    rs.getString("CodigoIdentificacion"),
                    rs.getString("Titulo"),
                    rs.getString("Autor"),
                    rs.getInt("Paginas"),
                    rs.getString("Editorial"),
                    rs.getString("ISBN"),
                    rs.getString("FechaPublicacion"),

                    rs.getString("Ubicacion"),
                    rs.getInt("Unidades"),
                    rs.getInt("UnidadesPrestados"),
                    rs.getInt("UnidadesDisponibles")
                });
            }
            return true;            
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "No se pudo consultar los libros ");
            return false;
        }
    }
    public boolean consultarObras(DefaultTableModel model) {
        try {
            Connection con = getConexion();
            String sql = "SELECT * FROM obras";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            // Llena el modelo de la tabla con los datos de los CDs
            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getInt("ID"),
                    rs.getString("CodigoIdentificacion"),
                    rs.getString("Titulo"),
                    rs.getString("Autor"),
                    rs.getInt("Paginas"),
                    rs.getString("Genero"),
                    rs.getString("FechaPublicacion"),
                    rs.getString("Ubicacion"),
                    rs.getInt("Unidades"),
                    rs.getInt("UnidadesPrestados"),
                    rs.getInt("UnidadesDisponibles")
                });
            }
            return true;            
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "No se pudo consultar las obras");
            return false;
        }
    }
    public boolean consultarRevistas(DefaultTableModel model) {
        try {
            Connection con = getConexion();
            String sql = "SELECT * FROM revistas";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            // Llena el modelo de la tabla con los datos de los CDs
            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getInt("ID"),
                    rs.getString("CodigoIdentificacion"),
                    rs.getString("Titulo"),
                    rs.getString("Editorial"),
                    rs.getString("Periodicidad"),

                    rs.getString("FechaPublicacion"),
                    rs.getString("Ubicacion"),
                    rs.getInt("Unidades"),
                    rs.getInt("UnidadesPrestados"),
                    rs.getInt("UnidadesDisponibles")
                });
            }
            return true;            
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "No se pudo consultar las revistas");
            return false;
        }
    }
    public boolean consultarTesis(DefaultTableModel model) {
        try {
            Connection con = getConexion();
            String sql = "SELECT * FROM tesis";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            // Llena el modelo de la tabla con los datos de los CDs
            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getInt("ID"),
                    rs.getString("CodigoIdentificacion"),
                    rs.getString("Titulo"),
                    rs.getString("Autor"),
                    rs.getString("Facultad"),
                    rs.getString("FechaPublicacion"),
                    rs.getString("Ubicacion"),
                    rs.getInt("Unidades"),
                    rs.getInt("UnidadesPrestados"),
                    rs.getInt("UnidadesDisponibles")
                });
            }
            return true;            
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "No se pudo consultar las tesis");
            return false;
        }
    }

    public boolean agregarCD(String titulo, String artista, String genero, String duracion, int numeroCanciones, String ubicacion, int unidades) {
        String sql = "INSERT INTO cds (Titulo,Artista,Genero,Duracion,NumeroCanciones,Ubicacion,Unidades,UnidadesDisponibles) VALUES (?, ?, ?, ?, ?, ?, ?, ?-1)";
        return agregarMaterial(sql, titulo, artista, genero, duracion, numeroCanciones, ubicacion, unidades, unidades);
    }

    public boolean agregarLibro(String titulo, String autor, int paginas, String editorial, String isbn, String fechapublicacion, String ubicacion, int unidades) {
        String sql = "INSERT INTO libros (Titulo, Autor, Paginas, Editorial, ISBN, FechaPublicacion, Ubicacion, Unidades,UnidadesDisponibles) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?-1)";
        return agregarMaterial(sql, titulo, autor, paginas, editorial, isbn, fechapublicacion, ubicacion, unidades, unidades);
    }

    public boolean agregarObra(String titulo, String autor, int paginas, String genero, String fechapublicacion, String ubicacion, int unidades) {
        String sql = "INSERT INTO obras (Titulo, Autor, Paginas, Genero,FechaPublicacion, Ubicacion, Unidades, UnidadesDisponibles) VALUES (?, ?, ?, ?, ?, ?, ?, ?-1)";
        return agregarMaterial(sql, titulo, autor, paginas, genero, fechapublicacion, ubicacion, unidades, unidades);
    }

    public boolean agregarRevista(String titulo, String editorial, String periodicidad, String fechapublicacion, String ubicacion, int unidades) {
        String sql = "INSERT INTO revistas (Titulo, Editorial, Periodicidad, FechaPublicacion, Ubicacion, Unidades, UnidadesDisponibles) VALUES (?, ?, ?, ?, ?, ?, ?-1)";
        return agregarMaterial(sql, titulo, editorial, periodicidad, fechapublicacion, ubicacion, unidades, unidades);
    }

    public boolean agregarTesis(String titulo, String autor, String facultad, String fechapublicacion, String ubicacion, int unidades) {
        String sql = "INSERT INTO tesis (Titulo, Autor, Facultad, FechaPublicacion, Ubicacion, Unidades, UnidadesDisponibles) VALUES (?, ?, ?, ?, ?, ?, ?-1)";
        return agregarMaterial(sql, titulo, autor, facultad, fechapublicacion, ubicacion, unidades, unidades);
    }

    private boolean agregarMaterial(String sql, Object... parametros) {
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

            JOptionPane.showMessageDialog(null, "Material agregado correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);

            return true;
        } catch (SQLException ex) {
            Logger.getLogger(SqlMateriales.class.getName()).log(Level.SEVERE, null, ex);

            JOptionPane.showMessageDialog(null, "Error al agregar Material", "Error", JOptionPane.ERROR_MESSAGE);

            return false;
        }
    }

    public boolean configAlumnos(String idtipo, String limit) {
        String sql = "UPDATE usuarios SET limite = ? WHERE id_tipo = ?";
        try {
            Connection con = getConexion();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, limit);
            ps.setString(2, idtipo);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Se modifico el limite");
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(SqlMateriales.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Error al modificar limite");

        }
        return false;
    }

    public boolean configurarCD(String unidadesdisponibles, String codigoidentificacion) {
        String sql = "UPDATE cds SET UnidadesDisponibles = ? WHERE CodigoIdentificacion = ? ";
        try {
            Connection con = getConexion();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, unidadesdisponibles);
            ps.setString(2, codigoidentificacion);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Se realizo con exito la modificacion");
            return true;

        } catch (SQLException ex) {
            Logger.getLogger(SqlMateriales.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Error al modificar", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return false;

    }

    public boolean configurarLibro(String unidadesdisponibles, String codigoidentificacion) {
        String sql = "UPDATE libros SET UnidadesDisponibles = ? WHERE CodigoIdentificacion = ? ";
        try {
            Connection con = getConexion();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, unidadesdisponibles);
            ps.setString(2, codigoidentificacion);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Se realizo con exito la modificacion");
            return true;

        } catch (SQLException ex) {
            Logger.getLogger(SqlMateriales.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Error al modificar", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return false;

    }

    public boolean configurarObra(String unidadesdisponibles, String codigoidentificacion) {
        String sql = "UPDATE obras SET UnidadesDisponibles = ? WHERE CodigoIdentificacion = ? ";
        try {
            Connection con = getConexion();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, unidadesdisponibles);
            ps.setString(2, codigoidentificacion);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Se realizo con exito la modificacion");
            return true;

        } catch (SQLException ex) {
            Logger.getLogger(SqlMateriales.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Error al modificar", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return false;

    }

    public boolean configurarRevista(String unidadesdisponibles, String codigoidentificacion) {
        String sql = "UPDATE revistas SET UnidadesDisponibles = ? WHERE CodigoIdentificacion = ? ";
        try {
            Connection con = getConexion();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, unidadesdisponibles);
            ps.setString(2, codigoidentificacion);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Se realizo con exito la modificacion");
            return true;

        } catch (SQLException ex) {
            Logger.getLogger(SqlMateriales.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Error al modificar", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return false;

    }

    public boolean configurarTesis(String unidadesdisponibles, String codigoidentificacion) {
        String sql = "UPDATE tesis SET UnidadesDisponibles = ? WHERE CodigoIdentificacion = ? ";
        try {
            Connection con = getConexion();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, unidadesdisponibles);
            ps.setString(2, codigoidentificacion);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Se realizo con exito la modificacion");
            return true;

        } catch (SQLException ex) {
            Logger.getLogger(SqlMateriales.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Error al modificar", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return false;

    }

}
