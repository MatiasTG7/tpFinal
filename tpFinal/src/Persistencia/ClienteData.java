
package Persistencia;

import Modeloo.Cliente;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import org.mariadb.jdbc.Connection;

public class ClienteData {
    
    private Connection con= null;
    
    public ClienteData() {
    }
    
    public ClienteData(Connection con){
        this.con= con;
    }
    
    public boolean guardarCliente(Cliente cliente){
        String sql= "INSERT INTO cliente (dni, nombreCliente, telefonoCliente, edad, afecciones, estadoCliente)" 
                + "VALUES (?,?,?,?,?,?)";
            try{
                PreparedStatement ps= con.prepareStatement(sql);
                ps.setString(1, cliente.getDni());
                ps.setString(2, cliente.getNombreCliente());
                ps.setString(3, cliente.getTelefonoCliente());
                ps.setInt(4, cliente.getEdad()); 
                ps.setBoolean(5, cliente.isAfecciones());
                ps.setBoolean(6,cliente.isEstadoCliente());
                
                int registros = ps.executeUpdate();
                System.out.println("Cliente cargado correctamente. Registros insertados: " + registros);
                
                return registros >0;
                
            }catch(SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error de conexión: " + ex.getMessage());
        }
            return false;
    }
    public Cliente buscarClientePorDni(String dni){
        Cliente cliente= null;
        String sql = "SELECT * FROM cliente WHERE dni = ?";
        
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, dni);
            
            ResultSet resultado = ps.executeQuery();
            
            if (resultado.next()){
                cliente = new Cliente();
                cliente.setCodCli(resultado.getInt("codCli"));
                cliente.setDni(resultado.getString("dni"));
                cliente.setNombreCliente(resultado.getString("nombreCliente"));
                cliente.setTelefonoCliente(resultado.getString("telefonoCliente"));
                cliente.setEdad(resultado.getInt("edad"));
                cliente.setAfecciones(resultado.getBoolean("afecciones"));
                cliente.setEstadoCliente(resultado.getBoolean("estadoCliente"));
            }else {
                System.out.println("No hay ningun cliente con este dni.");
            }
            resultado.close();
        }catch (SQLException ex){
            JOptionPane.showMessageDialog(null, "Error de conexion: " + ex.getMessage());
        }
        return cliente; 
    }
    public Cliente buscarCliente (int codCli){
        Cliente cliente= null;
        String sql = "SELECT * FROM cliente WHERE codCli = ?";

        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, codCli);
            ResultSet resultado = ps.executeQuery();

            if (resultado.next()){
                cliente = new Cliente();
                // Ahora "codCli" SÍ existe en el resultado
                cliente.setCodCli(resultado.getInt("codCli")); 
                cliente.setDni(resultado.getString("dni"));
                cliente.setNombreCliente(resultado.getString("nombreCliente"));
                // Agrego esta línea que también faltaba en tu SELECT original
                cliente.setTelefonoCliente(resultado.getString("telefonoCliente")); 
                cliente.setEdad(resultado.getInt("edad"));
                cliente.setAfecciones(resultado.getBoolean("afecciones"));
                cliente.setEstadoCliente(resultado.getBoolean("estadoCliente"));
            }else {
                JOptionPane.showMessageDialog(null,"No hay ningun alumno con este id");
            }
            resultado.close();
        }catch (SQLException ex){
            JOptionPane.showMessageDialog(null, "Error de conexion: " + ex.getMessage());
        }
        return cliente;
    }
    public boolean eliminarCliente(int codCli) {
        String sql = "DELETE FROM cliente WHERE codCli = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, codCli);
            int filas = ps.executeUpdate();
            
            return filas>0;
            
        } catch (SQLException ex) {
//            JOptionPane.showMessageDialog(null, "Error al eliminar el cliente. " + ex.getMessage());
            System.out.println("----ERROR DE SQL AL ELIMINAR----");
            ex.printStackTrace();
            System.err.println("----------------------------------");
            return false;
        }
    }
    public boolean actualizarCliente(Cliente cliente) {
    String sql = "UPDATE cliente SET dni = ?, nombreCliente = ?, telefonoCliente = ?, edad = ?, afecciones = ?, estadoCliente = ? WHERE codCli = ?";

    try (PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setString(1, cliente.getDni());
        ps.setString(2, cliente.getNombreCliente());
        ps.setString(3, cliente.getTelefonoCliente());
        ps.setInt(4, cliente.getEdad());
        ps.setBoolean(5, cliente.isAfecciones());
        ps.setBoolean(6, cliente.isEstadoCliente());
        ps.setInt(7, cliente.getCodCli());

        int filasAfectadas = ps.executeUpdate();

        return filasAfectadas > 0; 

    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error al actualizar en DB: " + e.getMessage());
        return false;
    }
    }
        public List<Cliente> listarClientes() {
        List<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT * FROM cliente";
        try (PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Cliente c = new Cliente();
                c.setCodCli(rs.getInt("codCli"));
                c.setDni(rs.getString("dni"));
                c.setNombreCliente(rs.getString("nombreCliente"));
                c.setTelefonoCliente(rs.getString("telefonoCliente"));
                c.setEdad(rs.getInt("edad"));
                c.setAfecciones(rs.getBoolean("afecciones"));
                c.setEstadoCliente(rs.getBoolean("estadoCliente"));
                
                clientes.add(c);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al mostrar los clientes. " + ex.getMessage());
        }
        return clientes;
    }
//    public boolean cambiarEstadoCliente (int codCli, boolean nuevoEstado){
//        String sql = "UPDATE cliente SET estadoCliente = ? WHERE codCli = ?";
//        try (PreparedStatement ps = con.prepareStatement(sql)) {
//            ps.setBoolean(1, nuevoEstado);
//            ps.setInt(2, codCli);
//            
//            int filas = ps.executeUpdate();
//            if (filas > 0) {
//                String mensaje = nuevoEstado ? "Cliente dado de alta correctamente." : "Cliente dado de baja correctamente.";
//                JOptionPane.showMessageDialog(null, mensaje);
//            } else {
//                JOptionPane.showMessageDialog(null, "No se encontro este cliente.");
//            }
//        } catch (SQLException ex) {
//            JOptionPane.showMessageDialog(null, "Error al cambiar estado del cliente: " + ex.getMessage());
//        }
//        return false;
//    }
        public boolean cambiarEstadoCliente(int codCli, boolean nuevoEstado) {
        String sql = "UPDATE cliente SET estadoCliente = ? WHERE codCli = ?";
    
        try (PreparedStatement ps = con.prepareStatement(sql)) {
        
        ps.setBoolean(1, nuevoEstado);
        ps.setInt(2, codCli);
        
        int filasAfectadas = ps.executeUpdate();
        return filasAfectadas > 0;
        
        }catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al cambiar estado: " + ex.getMessage());
            return false;
    }
  }
}
