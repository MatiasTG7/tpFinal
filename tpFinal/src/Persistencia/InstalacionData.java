
package Persistencia;

import Modeloo.Instalacion;
import Modeloo.TipoMasaje;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import org.mariadb.jdbc.Connection;


public class InstalacionData {
    
    private Connection con = null;

    public InstalacionData(Connection con) {
        this.con = con;
    }

    public InstalacionData() {
    }
    
    public int guardarInstalacion(Instalacion instalacion) {
        String sql = "INSERT INTO instalacion (nombreInstalacion, detalleDeUso, precio30m, estadoInstalacion) VALUES (?, ?, ?, ?)";
        
        try (PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            ps.setString(1, instalacion.getNombreInstalacion());
            ps.setString(2, instalacion.getDetalleDeUso());
            ps.setDouble(3, instalacion.getPrecio30m());
            
            ps.setBoolean(4, instalacion.isEstadoInstalacion());
            
            ps.executeUpdate();
            
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    instalacion.setCodInstal(rs.getInt(1));
                    System.out.println("Instalación guardada con ID: " + instalacion.getCodInstal());
                    return instalacion.getCodInstal();
                }
            }
            
        } catch (SQLException e) {
            System.err.println("Error al guardar instalación: " + e.getMessage());
        }
        return 0;
    }
    
    public boolean modificarInstalacionPorNombre(Instalacion instalacion) {
        String sql = "UPDATE instalacion SET detalleDeUso = ?, precio30m = ?, estadoInstalacion = ? "
                   + "WHERE nombreInstalacion = ?";
    
        try (PreparedStatement ps = con.prepareStatement(sql)) {
        
            ps.setString(1, instalacion.getDetalleDeUso());
            ps.setDouble(2, instalacion.getPrecio30m());
            ps.setBoolean(3, instalacion.isEstadoInstalacion());
            ps.setString(4, instalacion.getNombreInstalacion());
        
            int filasAfectadas = ps.executeUpdate();
        
            if (filasAfectadas > 0) {
                System.out.println("la instalacion fue actualizada correctamente: " + instalacion.getNombreInstalacion());
                return true;
            } else {
                System.out.println("No se encontro ninguna instalacion con ese nombre.");
                return false;
            }
        
        } catch (SQLException e) {
            System.err.println("Error al actualizar la instalacion: " + e.getMessage());
            return false;
        }
}
    public Instalacion buscarInstalacionPorNombre(String nombre) {
        Instalacion inst = null;
        String sql = "SELECT * FROM instalacion WHERE nombreInstalacion = ?";
        
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, nombre);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                inst = new Instalacion();

                inst.setCodInstal(rs.getInt("codInstal"));
                inst.setNombreInstalacion(rs.getString("nombreInstalacion"));
                inst.setDetalleDeUso(rs.getString("detalleDeUso"));
                inst.setPrecio30m(rs.getDouble("precio30m"));
                inst.setEstadoInstalacion(rs.getBoolean("estadoInstalacion"));
            }
            rs.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al buscar la instalacion: " + ex.getMessage());
        }
        return inst;
    }
    
    public boolean cambiarEstadoInstalacion(String nombre) {
        String sql = "UPDATE instalacion SET estadoInstalacion = NOT estadoInstalacion WHERE nombreInstalacion = ?";
        
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, nombre);
            int filas = ps.executeUpdate();
            
            return filas > 0; 
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al cambiar estado: " + ex.getMessage());
            return false;
        }
    }
    
    public List<Instalacion> listarPorTipo(TipoMasaje tipo) {
        List<Instalacion> instalaciones = new ArrayList<>();
        
        String sql = "SELECT DISTINCT i.* FROM instalacion i " +
                     "JOIN sesion s ON i.codInstal = s.codInstal " +
                     "JOIN masaje m ON s.codTratamiento = m.codTratamiento " +
                     "WHERE m.tipo = ?";
        
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setString(1, tipo.name()); 
            
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Instalacion inst = new Instalacion();
                    inst.setCodInstal(rs.getInt("codInstal"));
                    inst.setNombreInstalacion(rs.getString("nombreInstalacion"));
                    inst.setDetalleDeUso(rs.getString("detalleDeUso"));
                    inst.setPrecio30m(rs.getDouble("precio30m"));
                    
                    inst.setEstadoInstalacion(rs.getBoolean("estadoInstalacion"));
                    
                    instalaciones.add(inst);
                }
            }
            
        } catch (SQLException e) {
            System.err.println("Error al listar instalaciones por tipo: " + e.getMessage());
        }
        return instalaciones;
    }
    public List<Instalacion> listarInstalaciones() {
        List<Instalacion> instalaciones = new ArrayList<>();
        String sql = "SELECT * FROM instalacion"; 
    
    try (PreparedStatement ps = con.prepareStatement(sql)) {
        ResultSet rs = ps.executeQuery();
        
        while (rs.next()) {
            Instalacion inst = new Instalacion();
            
            inst.setCodInstal(rs.getInt("codInstal"));
            inst.setNombreInstalacion(rs.getString("nombreInstalacion"));
            inst.setDetalleDeUso(rs.getString("detalleDeUso"));
            inst.setPrecio30m(rs.getDouble("precio30m"));
            inst.setEstadoInstalacion(rs.getBoolean("estadoInstalacion"));
            
            instalaciones.add(inst);
            }
        } catch (SQLException ex) {
        JOptionPane.showMessageDialog(null, "Error al listar instalaciones: " + ex.getMessage());
        }
    return instalaciones;
    }
    public boolean eliminarInstalacionPorNombre(String nombre) {
        String sql = "DELETE FROM instalacion WHERE nombreInstalacion = ?";
        
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setString(1, nombre);
            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;
            
        } catch (SQLException e) {
            System.err.println("Error al eliminar instalación: " + e.getMessage());
            return false;
        }
    }
}
    
