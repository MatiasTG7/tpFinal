
package Persistencia;

import Modeloo.Cliente;
import Modeloo.Conexion;
import Modeloo.Masajista;
import Modeloo.Sesion;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.sql.Timestamp;
import org.mariadb.jdbc.Connection;

public class SesionData {
    
    private Connection con = null;
    private MasajistaData masajistaData;
    
    public SesionData(Connection con){
        this.con = con;
        this.masajistaData= new MasajistaData(con);
    }

    public SesionData(){
    }
    
    public void insertarSesion(Sesion sesion) {
        String sql = "INSERT INTO sesion (fechaInicio, fechaFin, codTratamiento, codMasajista, codPack, codInstal, estadoInstalacion) "
                   + "VALUES (?, ?, ?, ?, ?, ?, ?)";
        
        try (PreparedStatement ps = con.prepareStatement(sql, java.sql.Statement.RETURN_GENERATED_KEYS)) { 
        ps.setTimestamp(1, Timestamp.valueOf(sesion.getFechaInicio()));
        ps.setTimestamp(2, Timestamp.valueOf(sesion.getFechaFin()));
        ps.setInt(3, sesion.getCodTratamiento());
        ps.setInt(4, sesion.getCodMasajista());
        ps.setInt(5, sesion.getCodPack());
        ps.setInt(6, sesion.getCodInstal());
        ps.setBoolean(7, sesion.isEstadoInstalacion());
        
        ps.executeUpdate(); // Solo ejecuta
        
        // Obten el ID de forma segura
        try (ResultSet rs = ps.getGeneratedKeys()) {
            if (rs.next()) {
                sesion.setCodSesion(rs.getInt(1));
            }
        }
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(null, "Error al agregar la sesion: " + ex.getMessage());
    }
}
    
    public Sesion buscarSesion(int codSesion){
        
        Sesion sesion = null;
        String sql = "SELECT * FROM sesion WHERE codSesion = ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, codSesion);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                sesion = new Sesion();
                sesion.setCodSesion(rs.getInt("codSesion"));
                sesion.setFechaInicio(rs.getTimestamp("fechaInicio").toLocalDateTime());
                sesion.setFechaFin(rs.getTimestamp("fechaFin").toLocalDateTime());
                sesion.setCodTratamiento(rs.getInt("codTratamiento"));
                sesion.setCodMasajista(rs.getInt("codMasajista"));
                sesion.setCodPack(rs.getInt("codPack"));
                sesion.setCodInstal(rs.getInt("codInstal"));
                sesion.setEstadoInstalacion(rs.getBoolean("estadoInstalacion"));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al buscar la sesion: " + ex.getMessage());
        }
        return sesion;
    }
    
    public boolean actualizarSesion(Sesion sesion){
    
    String sql = "UPDATE sesion SET fechaInicio = ?, fechaFin = ?, codTratamiento = ?, codMasajista = ?, codPack = ?, codInstal = ?, estadoInstalacion = ? "
                   + "WHERE codSesion = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setTimestamp(1, Timestamp.valueOf(sesion.getFechaInicio()));
            ps.setTimestamp(2, Timestamp.valueOf(sesion.getFechaFin()));
            ps.setInt(3, sesion.getCodTratamiento());
            ps.setInt(4, sesion.getCodMasajista());
            ps.setInt(5, sesion.getCodPack());
            ps.setInt(6, sesion.getCodInstal());
            ps.setBoolean(7, sesion.isEstadoInstalacion());
            ps.setInt(8, sesion.getCodSesion());
            
            int filas = ps.executeUpdate();
            
            return filas > 0; 
        
        } catch (SQLException ex) {
        JOptionPane.showMessageDialog(null, "Error al actualizar la sesion: " + ex.getMessage());
        return false;
        }
    }
    
   public boolean eliminarSesion(int codSesion){
       String sql = "DELETE FROM sesion WHERE codSesion = ?";
       
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, codSesion);
            int filas = ps.executeUpdate();
            
            return filas > 0; 
        
        }catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al eliminar la sesion: " + ex.getMessage());
            return false; // <-- Devuelve false en error
        }
    }
   
   public boolean BajaAltaLogicaSesion (int codSesion, boolean estadoInstalacion){
        String sql = "UPDATE sesion SET estadoInstalacion = ? WHERE codSesion = ?";
        
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setBoolean(1, estadoInstalacion);
            ps.setInt(2, codSesion);
            int filas = ps.executeUpdate();
            
            return filas > 0; 
        
        }catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al cambiar el estado: " + ex.getMessage());
            return false; // <-- Devuelve false en error
        }
    }
   
   public boolean modificarMasajista(int codSesion, String matricula) {
       
        Masajista masajista = masajistaData.buscarMasajistaPorMatricula(matricula);
        if (masajista == null) {
            JOptionPane.showMessageDialog(null, "No se encontró un masajista con matrícula " + matricula + ".");
            return false;
        }

        String sql = "UPDATE sesion SET codMasajista = ? WHERE codSesion = ?";
        
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, masajista.getCodMasajista());
            ps.setInt(2, codSesion);
            int filas = ps.executeUpdate();
            
            if (filas > 0) {
                JOptionPane.showMessageDialog(null, "El masajista fue actualizado correctamente.");
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "No se encontro la sesion con id: " + codSesion + ".");
                return false;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar el masajista: " + e.getMessage());
            return false;
        }
    }
} 
