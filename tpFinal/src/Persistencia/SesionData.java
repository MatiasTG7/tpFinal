
package Persistencia;

import Modeloo.Cliente;
import Modeloo.Conexion;
import Modeloo.Sesion;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import org.mariadb.jdbc.Connection;

public class SesionData {
    
    private Connection con = null;
    
    public SesionData(Connection con){
        this.con = con;
    }

    public SesionData() {
    }
    
  public void insertarSesion(Sesion sesion) {
        String sql = "INSERT INTO Sesion (codSesion, fechaInicio, fechaFin, codTratamiento, codMasajista, codPack,"
                + " estadoInstalacion) " + "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try{
     PreparedStatement ps= con.prepareStatement(sql);
        ps.setInt(1, sesion.getCodSesion());

        ps.setInt(4, sesion.getCodTratamiento());
        ps.setInt(5, sesion.getCodMasajista());
        ps.setInt(6, sesion.getCodPack());
        ps.setBoolean(7, sesion.isEstadoInstalacion());
         int registro = ps.executeUpdate();
            System.out.println();
            if (registro > 0) {
                System.out.println("sesion registrada correctamente" + registro);
                return;
            }
                
            }catch(SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error " + ex.getMessage());
           
}}
 
//  public Sesion buscarSesion(int codSesion){
//  Sesion sesion= null;
//        String sql = "SELECT * FROM sesion WHERE codSesion = ?";
//        try {
//          PreparedStatement ps = con.prepareStatement(sql);
//          ps.setInt(1,codSesion);
//          ResultSet resultado = ps.executeQuery();
//          if(resultado.next()){
//          sesion = new Sesion();
//          sesion.setCodSesion(resultado.getInt("codSesion"));
//          sesion.setFechaInicio(resultado.getDate("fechaInicio").toLocalDate());
//          sesion.setFechaFin(resultado.getDate("fechaFin").toLocalDate());
//          sesion.setCodTratamiento(resultado.getInt("codTratamiento"));
//          sesion.setCodMasajista(resultado.getInt("codMasajista"));
//          sesion.setCodPack(resultado.getInt("codPack"));
//          sesion.setEstadoInstalacion(resultado.getBoolean("estadoInstalacion"));
//          
//          }
//      } catch (Exception e){ 
//            System.out.println("error con este codigo");
//      }
//        return sesion;
//
//  }
//  public Sesion buscarSesionXCod(int codSesion){
//    Sesion sesion = null;
//    String sql = "SELECT fechaInicio, fechaFin, codTratamiento, codMasajista, codPack, codInstal, estadoInstalacion FROM sesion WHERE codSesion = ? ";
//    try{
//        PreparedStatement ps = con.prepareStatement(sql);
//        ps.setInt(1, codSesion);
//        ResultSet resultado = ps.executeQuery();
//        if (resultado.next()) {
//            sesion = new Sesion();
//            sesion.setCodSesion(codSesion);
//            sesion.setFechaInicio(resultado.getDate("fechaInicio").toLocalDate());
//            sesion.setFechaFin(resultado.getDate("fechaFin").toLocalDate());
//            sesion.setCodTratamiento(resultado.getInt("codTratamiento"));
//            sesion.setCodMasajista(resultado.getInt("CodMasajista"));
//            sesion.setCodPack(resultado.getInt("codPack"));
//            sesion.setEstadoInstalacion(resultado.getBoolean("estadoInstalacion"));
//        }
//
//        } catch (SQLException ex){
//            System.out.println("Error de conexion" + ex.getMessage());
//    } 
//    return sesion;    
//}  
//   public boolean actualizarSesion(Sesion sesion){
//    
//    String sql = "UPDATE Sesion SET fechaInicio = ?, fechaFin = ?, codTratamiento = ?, codMasajista = ?, codPack = ?, estadoInstalacion = ? WHERE codSesion = ? ";
//    try (PreparedStatement ps = con.prepareStatement(sql)){
//        ps.setDate(1, Date.valueOf(sesion.getFechaInicio()));
//        ps.setDate(2, Date.valueOf(sesion.getFechaFin()));
//        ps.setInt(3, sesion.getCodTratamiento());
//        ps.setInt(4, sesion.getCodMasajista());
//        ps.setInt(5, sesion.getCodPack());
//        ps.setBoolean(6, sesion.isEstadoInstalacion());
//        ps.setInt(7, sesion.getCodSesion());
//        return ps.executeUpdate() > 0;
//        
//  }catch (Exception ex) {
//        System.out.println("Error de conexion"+ ex);
//        return false;
//  }
//  }
   public void eliminarSesion(int codSesion){
       String sql = "DELETE FROM sesion WHERE codSesion";
       
      try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, codSesion);
            int filas = ps.executeUpdate();
            if (filas > 0) {
                JOptionPane.showMessageDialog(null, "La sesion fue eliminada correctamente.");
            } else {
                JOptionPane.showMessageDialog(null, "No se encontro esta sesion.");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al eliminar la sesion. " + ex.getMessage());
        }
   }
   private void BajaAltaLogicaSesion (int codSesion, boolean estadoInstalacion){
        String sql = "UPDATE sesion SET estadoInstalacion = ? WHERE codSesion = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setBoolean(1, estadoInstalacion);
            ps.setInt(2, codSesion);
            int filas = ps.executeUpdate();
            if (filas > 0) {
                String mensaje = estadoInstalacion ? "sesion bien tomada/anotada." : "sesion no tomada o no subida correctamente";
                JOptionPane.showMessageDialog(null, mensaje);
            } else {
                JOptionPane.showMessageDialog(null, "no se encontro la sesion.");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "error al cambiar la sesion. " + ex.getMessage());
        }
   }
   
   private void modificarMasajista(int codSesion, String matricula){
       
   }
} 
