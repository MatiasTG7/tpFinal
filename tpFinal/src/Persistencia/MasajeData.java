
package Persistencia;

import Modeloo.Cliente;
import Modeloo.Conexion;
import Modeloo.Masaje;
import Modeloo.TipoMasaje;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JOptionPane;
import org.mariadb.jdbc.Connection;
import org.mariadb.jdbc.Statement;

public class MasajeData {

    private Connection con = null;
    
    public MasajeData(Connection con){
        this.con = con;
    }

    public MasajeData(){
    }
    
   public void insertarTratamiento(Masaje masaje) {
    
       Connection con = (Connection) Conexion.getConexion();
       try {
        
        String sql = "INSERT INTO masaje (nombreTratamiento, tipo, detalleTratamiento, duracionTratamiento, costoTratamiento, activo)"
                + "VALUES (?, ?, ?, ?, ?, ?)";
        
            PreparedStatement ps = con.prepareStatement(sql);
            
            ps.setString(1, masaje.getNombreTratamiento());          
            ps.setString(2, masaje.getTipo().name().toLowerCase());
            ps.setString(3, masaje.getDetalleTratamiento());
            ps.setInt(4, masaje.getDuracionTratamiento());
            ps.setDouble(5, masaje.getCostoTratamiento());
            ps.setBoolean(6, masaje.isActivo());
            
            int registro = ps.executeUpdate();
            
            if (registro > 0) {
                JOptionPane.showMessageDialog(null, "Masaje registrado correctamente.");
            } else {
                 JOptionPane.showMessageDialog(null, "Error: La sentencia no afecto ninguna fila.");
            }
            ps.close(); 
        } catch(SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla masaje. " + ex.getMessage());
  }
    }


    private void modificarMasaje(Masaje masaje){
        String sql = "UPDATE masaje SET nombreTratamiento = ?, tipo = ?, detalleTratamiento = ?. costoTratamiento = ?, activo = ? "
                + "WHERE codTratamiento = ?";
        try{
        PreparedStatement ps = con.prepareStatement(sql);
         ps.setInt(1, masaje.getCodTratamiento());
         ps.setString(2, masaje.getTipo().name().toLowerCase());
         ps.setString(3, masaje.getDetalleTratamiento());
         ps.setInt(4, masaje.getDuracionTratamiento());
         ps.setDouble(5, masaje.getCostoTratamiento());
         ps.setBoolean(6, masaje.isActivo());
         int err = ps.executeUpdate();
         
         if(err == 0){
             System.out.println("No se encontro un masaje con el codigo " + masaje.getCodTratamiento() + "para actualizar");
     
         }else{System.out.println("El masaje fue actualizado correctamente.");}
        
        }catch(SQLException ex){
            System.out.println("Error al intentar modificar el masaje " + ex.getMessage());
        }
    }
    
    public List<Masaje> obtenerMasajesPorTipo(Masaje tipo) {
        
        List<Masaje> tratamientos = new LinkedList<>();
        String sql = "SELECT * FROM masaje WHERE tipo = ?";
        try{
        PreparedStatement ps = con.prepareStatement(sql);
        String tipoParaDB = tipo.toString().toLowerCase();
        ps.setString(1, tipoParaDB);
       try(ResultSet rs = ps.executeQuery()){
           while(rs.next()){
               
               Masaje t = new Masaje();
               t.setCodTratamiento(rs.getInt("codTratamiento"));
               t.setNombreTratamiento(rs.getString("nombreTratamiento"));
               String tipoString = rs.getString("tipo".toUpperCase());
               t.setTipo(TipoMasaje.valueOf(tipoString));
               t.setDetalleTratamiento(rs.getString("detalleTratamiento"));
               t.setCostoTratamiento(rs.getDouble("costoTratamiento"));
               t.setActivo(rs.getBoolean("activo"));
               tratamientos.add(t);
            }
       }
        }catch(SQLException ex){
            System.out.println("Error al obtener los masajes por tipo " + ex.getMessage());
        }return tratamientos;
        
    }
    
    private List<Masaje> obtenerMasajeMasPedido (Masaje masaje){
        List<Masaje> masajeOrd = new LinkedList<>();
        String sql = "SELECT masaje.*, COUNT(s.codTratamiento) AS frecuencia" + "FROM masaje masaje" + "JOIN Sesion s ON masaje.codTratamiento = s.codTratamiento +"
                + "GROUP BY masaje.codTratamiento, masaje.nombreTratamiento, masaje.tipo, masaje.DetalleTratamiento, masaje.duracionTratamiento, masaje.costoTratamiento, masaje.activo"
                + "ORDER BY frecuencia DESC";
        try{
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()){
            Masaje m = new Masaje();
            m.setCodTratamiento(rs.getInt("codTratamiento"));
            m.setNombreTratamiento(rs.getString("nombreTratamiento"));
            String tipoString = rs.getString("wtipo").toLowerCase();
            m.setTipo(TipoMasaje.valueOf(tipoString));
            m.setDetalleTratamiento(rs.getString("detalleTratamiento"));
            m.setCostoTratamiento(rs.getDouble("costoTratamiento"));
            m.setActivo(rs.getBoolean("activo"));
            masajeOrd.add(m);
        
        }
        
        }catch(SQLException ex){
            System.out.println("Error al obtener masaje mas solicitado " + ex.getMessage());}
    return masajeOrd;
    }
}
