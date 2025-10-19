
package Persistencia;

import Modeloo.Entidad;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
                ps.setString(3, cliente.getApellido());
                ps.setInt(4, cliente.getTelefono());
                ps.setInt(5, cliente.getEdad()); 
                ps.setBoolean(6, cliente.getAfecciones());
                ps.setBoolean(7,cliente.isEstado());
                
                int registros = ps.executeUpdate();
                System.out.println("Cliente cargado correctamente. Registros insertados: " + registros);
                
            }catch(SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error de conexión: " + ex.getMessage());
        }
    }
    public Entidad buscarCliente(int dni){
        Entidad cliente= null;
        String sql = "SELECT * FROM cliente WHERE dni = ?";
        
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, dni);
            
            ResultSet resultado = ps.executeQuery();
            
            if (resultado.next()){
                cliente = new Entidad();
                cliente.setDni(resultado.getInt("dni"));
                cliente.setNombre(resultado.getString("nombreCliente"));
                cliente.setApellido(resultado.getString("apellidoCliente"));
                cliente.setTelefono(resultado.getInt("telefonoCliente"));
                cliente.setEdad(resultado.getInt("edad"));
                cliente.setAfecciones(resultado.getBoolean("afecciones"));
                cliente.setEstado(resultado.getBoolean("estadoCliente"));
            }else {
                System.out.println("No hay ningun cliente con este dni.");
            }
            resultado.close();
        }catch (SQLException ex){
            JOptionPane.showMessageDialog(null, "Error de conexion: " + ex.getMessage());
        }
        return cliente;
    }
    
}
