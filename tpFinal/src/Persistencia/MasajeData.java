
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

public class MasajeData {

    private Connection con = null;
    
    public MasajeData(Connection con){
        this.con = con;
    }

    public MasajeData() {
        this.con = con;
    }
    
   public void insertarTratamiento(Masaje t) {
        
        String sql = "INSERT INTO tratamiento (nombreTratamiento, tipo, detalleTratamiento, duracionTratamiento, costoTratamiento, activo) VALUES (?, ?, ?, ?, ?, ?)";
        
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            
            ps.setString(1, t.getNombreTratamiento());
            
            String tipoParaDB  = t.getTipo().toString().toLowerCase();
            
            ps.setString(2, tipoParaDB); 
            
            ps.setString(3, t.getDetalleTratamiento());
            ps.setInt(4, t.getDuracionTratamiento());
            ps.setDouble(5, t.getCostoTratamiento());
            ps.setBoolean(6, t.isActivo());
            
            int registro = ps.executeUpdate();
            
            if (registro > 0) {
                JOptionPane.showMessageDialog(null, "Tratamiento registrado correctamente.");
            } else {
                 JOptionPane.showMessageDialog(null, "Error: La sentencia no afectó ninguna fila.");
            }
            ps.close(); 
        } catch(SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla 'tratamiento'. Detalle: " + ex.getMessage());
  }
    }


    private void modificarMasaje(Masaje t){
        String sql = "UPDATE tratamiento SET nombreTratamiento=?, tipo=?, detalleTratamiento=?. costoTratamiento=?, activo=? WHERE codTratamiento=?";
        try{
        PreparedStatement ps = con.prepareStatement(sql);
         ps.setInt(1, t.getCodTratamiento());
         String tipoParaDB  = t.getTipo().toString().toLowerCase();
         ps.setString(2, tipoParaDB );
         ps.setString(3, t.getDetalleTratamiento());
         ps.setInt(4, t.getDuracionTratamiento());
         ps.setDouble(5, t.getCostoTratamiento());
         ps.setBoolean(6, t.isActivo());
         int err = ps.executeUpdate();
         if(err == 0){
             System.out.println("no se encontro un masaje con codigo" + t.getCodTratamiento() + "para actualizar");
     }else{System.out.println("tratamiento actualizado correctamente");}
        }catch(SQLException ex){
            System.out.println(" error al querer modificar el masaje" + ex.getMessage());
        }
    }
    
    public List<Masaje> obtenerTratamientosPorTipo(Masaje tipo) {
        
        List<Masaje> tratamientos = new LinkedList<>();
        String sql = "SELECT * FROM tratamiento WHERE tipo = ?";
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
            System.out.println("error al listar tratamiento por tipo" + ex.getMessage());
        }return tratamientos;
        
    }
    
    private List<Masaje> obtenerMasajeMasPedido (Masaje masaje){
        List<Masaje> masajeOrd = new LinkedList<>();
        String sql = "SELECT t.*, COUNT(s.codTratamiento) AS frecuencia" + "FROM tratamiento t" + "JOIN Sesion s ON t.codTratamiento = s.codTratamiento " + "GROUP BY t.codTratamiento, t.nombreTratamiento, t.tipo, t.DetalleTratamiento, t.duracionTratamiento, t.costoTratamiento, t.activo"
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
            System.out.println("error al obtener masaje mas solicitado" + ex.getMessage());}
    return masajeOrd;
    }
}
