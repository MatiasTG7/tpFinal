
package Persistencia;

import Modeloo.Entidad;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import org.mariadb.jdbc.Connection;

public class EntidadData {
    
    public EntidadData() {
    }
    
    private Connection con= null;
    
    public EntidadData(Connection con){
        this.con= con;
    }
    
    public void guardarCliente(Entidad cliente){
        String sql= "INSERT INTO cliente (dni, nombreCliente, apellidoCliente, telefonoCliente,edad, afecciones, estadoCliente)" 
                + "VALUES (?,?,?,?,?,?,?)";
            try{
                PreparedStatement ps= con.prepareStatement(sql);
                ps.setInt(1, cliente.getDni());
                ps.setString(2, cliente.getNombre());
                ps.setInt(3, cliente.getTelefono());
                ps.setInt(4, cliente.getEdad()); 
                ps.setBoolean(5, cliente.getAfecciones());
                ps.setBoolean(6,cliente.isEstado());
                
                int registros = ps.executeUpdate();
                System.out.println("Alumno cargado correctamente. Registros insertados: " + registros);
                
            }catch(SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error de conexión: " + ex.getMessage());
        }
    }
}
