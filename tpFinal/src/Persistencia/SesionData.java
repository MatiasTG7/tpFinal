
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
  public void insertarSesion(Sesion sesion) {
        String sql = "INSERT INTO Sesion (codSesion, fechaInicio, fechaFin, codTratamiento, codMasajista, codPack,"
                + " estadoInstalacion) " + "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try{
     PreparedStatement ps= con.prepareStatement(sql);
        ps.setInt(1, sesion.getCodSesion());
        ps.setDate(2, Date.valueOf(sesion.getFechaInicio()));
        ps.setDate(3, Date.valueOf(sesion.getFechaFin()));
        ps.setInt(4, sesion.getCodTratamiento());
        ps.setInt(5, sesion.getCodMasajista());
        ps.setInt(6, sesion.getCodPack());
        ps.setBoolean(7, sesion.isEstadoInstalacion());
         int registro = ps.executeUpdate();
            System.out.println();
            if (registro > 0) {
                System.out.println("sesion registrada correctamente" + registro);
                return ;
            }
                
            }catch(SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error " + ex.getMessage());
           
}}
  public Sesion consultarSesion(int codSesion){
  Sesion sesion= null;
        String sql = "SELECT * FROM sesion WHERE codSesion = ?";
        try {
          PreparedStatement ps = con.prepareStatement(sql);
          ps.setInt(1,codSesion);
          ResultSet resultado = ps.executeQuery();
          if(resultado.next()){
          sesion = new Sesion();
          sesion.setCodSesion(resultado.getInt("codSesion"));
          sesion.setFechaInicio(resultado.getDate("fechaInicio").toLocalDate());
          sesion.setFechaFin(resultado.getDate("fechaFin").toLocalDate());
          sesion.setCodTratamiento(resultado.getInt("codTratamiento"));
          sesion.setCodMasajista(resultado.getInt("codMasajista"));
          sesion.setCodPack(resultado.getInt("codPack"));
          sesion.setEstadoInstalacion(resultado.getBoolean("estadoInstalacion"));
          
          }
      } catch (Exception e){ 
            System.out.println("error con este codigo");
      }
        return sesion;
        
        
  }
}