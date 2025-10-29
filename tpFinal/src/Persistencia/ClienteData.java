
package Persistencia;

import Modeloo.Cliente;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import org.mariadb.jdbc.Connection;

public class ClienteData {
    
    public ClienteData() {
    }
    
    private Connection con= null;
    
    public ClienteData(Connection con){
        this.con= con;
    }
    
    public void guardarCliente(Cliente cliente){
        String sql= "INSERT INTO cliente (dni, nombreCliente, telefonoCliente, edad, afecciones, estadoCliente)" 
                + "VALUES (?,?,?,?,?,?,?)";
            try{
                PreparedStatement ps= con.prepareStatement(sql);
                ps.setInt(1, cliente.getDni());
                ps.setString(2, cliente.getNombreCliente());
                ps.setString(4, cliente.getTelefonoCliente());
                ps.setInt(5, cliente.getEdad()); 
                ps.setBoolean(6, cliente.isAfecciones());
                ps.setBoolean(7,cliente.isEstadoCliente());
                
                int registros = ps.executeUpdate();
                System.out.println("Cliente cargado correctamente. Registros insertados: " + registros);
                
            }catch(SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error de conexión: " + ex.getMessage());
        }
    }
    public Cliente buscarClientePorDni(int dni){
        Cliente cliente= null;
        String sql = "SELECT * FROM cliente WHERE dni = ?";
        
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, dni);
            
            ResultSet resultado = ps.executeQuery();
            
            if (resultado.next()){
                cliente = new Cliente();
                cliente.setDni(resultado.getInt("dni"));
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
        String sql = "SELECT dni, nombreCliente, telefonoCliente, edad, afecciones, estadoCliente FROM cliente WHERE codCli = ?";

        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, codCli);
            ResultSet resultado = ps.executeQuery();

            if (resultado.next()){
                cliente = new Cliente();
                cliente.setCodCli(resultado.getInt("codCli"));
                cliente.setDni(resultado.getInt("dni"));
                cliente.setNombreCliente(resultado.getString("nombreCliente"));
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
    public void eliminarCliente(int codCli) {
        String sql = "DELETE FROM cliente WHERE codCli = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, codCli);
            int filas = ps.executeUpdate();
            if (filas > 0) {
                JOptionPane.showMessageDialog(null, "El cliente fue eliminado correctamente.");
            } else {
                JOptionPane.showMessageDialog(null, "No se encontro este cliente.");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al eliminar el cliente. " + ex.getMessage());
        }
    }
    public void actualizarCliente(Cliente cliente) {
        String sql = "UPDATE cliente SET dni = ?, nombreCliente = ?, telefonoCliente = ?, edad = ?, afecciones = ?, estadoCliente = ? WHERE codCli = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, cliente.getDni());
            ps.setString(2, cliente.getNombreCliente());
            ps.setString(3, cliente.getTelefonoCliente());
            ps.setInt(4, cliente.getEdad());
            ps.setBoolean(5, cliente.isAfecciones());
            ps.setBoolean(6, cliente.isEstadoCliente());

            int filas = ps.executeUpdate();
            if (filas > 0) {
                JOptionPane.showMessageDialog(null, "El cliente fue actualizado correctamente.");
            } else {
                JOptionPane.showMessageDialog(null, "No se encontro este cliente.");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al actualizar el cliente. " + ex.getMessage());
        }
    }
    private void BajaAltaLogicaCliente (int codCli, boolean nuevoEstado){
        String sql = "UPDATE cliente SET estadoCliente = ? WHERE codCli = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setBoolean(1, nuevoEstado);
            ps.setInt(2, codCli);
            int filas = ps.executeUpdate();
            if (filas > 0) {
                String mensaje = nuevoEstado ? "Cliente dado de alta correctamente." : "Cliente dado de baja correctamente.";
                JOptionPane.showMessageDialog(null, mensaje);
            } else {
                JOptionPane.showMessageDialog(null, "No se encontro este cliente.");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al cambiar estado del cliente: " + ex.getMessage());
        }
    }
}
