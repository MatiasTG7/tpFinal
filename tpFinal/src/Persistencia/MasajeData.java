
package Persistencia;

import Modeloo.Cliente;
import Modeloo.Masaje;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import org.mariadb.jdbc.Connection;

public class MasajeData {
    public MasajeData(){

}
   private Connection con = null;
   
   public MasajeData(Connection con){
   this.con = con;
   }
   public void guardarmasaje(Masaje masaje){
        String sql= "INSERT INTO masaje (codSesion, fechaInicio, fechaFin, codTratamiento, codMasajista, codInstal, estadoInstalacion) VALUE (?;?;?,?;?;?;?;?)" ;
        
               
            try{
                PreparedStatement ps= con.prepareStatement(sql);
                ps.setInt(1, masaje.getCodSesion());
                ps.setDate(2, Date.valueOf(masaje.getFechaInicio()));
                ps.setDate(3, Date.valueOf(masaje.getFechaFin()));
                ps.setInt(4, masaje.getCodTratamiento()); 
                ps.setInt(5, masaje.getCodMasajista());
                ps.setInt(6, masaje.getCodPack());
                ps.setInt(7, masaje.getCodInstal());
                ps.setBoolean(8, masaje.isEstadoInstalacion());
                
                int registros = ps.executeUpdate();
                System.out.println("masaje/sesion cargado correctamente. masaje insertados: " + registros);
                
            }catch(SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error de conexión: " + ex.getMessage());
        }
    }
   public  Masaje buscarmasaje(int codSesion){
     Masaje masaje= null;
        String sql = "SELECT * FROM masaje WHERE Codmasaje = ?";
        
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, codSesion);
            
            ResultSet resultado = ps.executeQuery();
            
            if (resultado.next()){
               masaje = new Masaje();
               masaje.setCodSesion(codSesion);
                masaje.setFechaInicio(resultado.getDate("fechaInicio").toLocalDate());
                 masaje.setFechaFin(resultado.getDate("fechaFin").toLocalDate());
                 masaje.setCodTratamiento(resultado.getInt("codTratamiento"));
                 masaje.setCodMasajista(resultado.getInt("codMasajista"));
                 masaje.setCodPack(resultado.getInt("codPack"));
                 masaje.setCodInstal(resultado.getInt("codInstal"));
                 masaje.setEstadoInstalacion(resultado.getBoolean("estadoInstalacion"));
            }else {
                System.out.println("no hay ninguna clase con ester codigo.");
            }
            resultado.close();
        }catch (SQLException ex){
            JOptionPane.showMessageDialog(null, "Error de conexion: " + ex.getMessage());
        }
        return masaje; 
    }
     private void BajaAltaLogicaCliente (int codSesion, boolean estado){
        String sql = "UPDATE Masaje SET estadoInstalacion = ? WHERE codSesion = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setBoolean(1, estado);
            ps.setInt(2, codSesion);
            int filas = ps.executeUpdate();
            if (filas > 0) {
                String mensaje = estado ? "sesion dada de alta correctamente." : "sesion dada de baja correctamente.";
                JOptionPane.showMessageDialog(null, mensaje);
            } else {
                JOptionPane.showMessageDialog(null, "no se encontro sesion");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al cambiar la sesion: " + ex.getMessage());
        }
   }
    public void actualizarCliente(Masaje masaje) {
        String sql = "UPDATE masaje SET fechaInicio=?, fechaFin=?, codTratamiento=?, codMasajista=?, codPack=?, codInstal=?, estadoInstalacion=? WHERE codSesion= ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setDate(1, Date.valueOf(masaje.getFechaInicio()));
            ps.setDate(2, Date.valueOf(masaje.getFechaFin()));
            ps.setInt(3, masaje.getCodTratamiento());
            ps.setInt(4,masaje.getCodMasajista());
            ps.setInt(5, masaje.getCodPack());
            ps.setInt(6,masaje.getCodInstal());
            ps.setBoolean(7, masaje.isEstadoInstalacion());
            ps.setInt(8, masaje.getCodSesion());

            int filas = ps.executeUpdate();
            if (filas > 0) {
                JOptionPane.showMessageDialog(null, "la sesion fue actualizada correctamente.");
            } else {
                JOptionPane.showMessageDialog(null, "no se encontro esta sesion.");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al actuaalizar sesion. " + ex.getMessage());
        }
    }
}
