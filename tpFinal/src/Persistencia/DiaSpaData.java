
package Persistencia;

import Modeloo.DiaSpa;
import Modeloo.Cliente;
import java.time.LocalDateTime;
import org.mariadb.jdbc.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JOptionPane;

public class DiaSpaData {
    
    public DiaSpaData() {
    }
    
    private Connection con= null;
    private ClienteData clienteData;
    
    public DiaSpaData(Connection con){
        this.con= con;
    }
    
    public int insertarDia(DiaSpa dia){
        String sql = "INSERT INTO dia_de_spa (FechayHora, preferencias, codCli, monto, estadoDia) VALUES (?,?,?,?,?)";
        try (PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
            ps.setTimestamp(1, java.sql.Timestamp.valueOf(dia.getFechaYHora()));
            ps.setBoolean(2,Boolean.parseBoolean(dia.getPreferencias()));
            ps.setInt(3, dia.getCodCliente().getCodCli());
            ps.setDouble(4, dia.getMonto());
            ps.setBoolean(5, dia.isEstadoDia());
            
            ps.executeUpdate();
            
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    dia.setCodPack(rs.getInt(1)); 
                    System.out.println("Día de Spa insertado con ID: " + dia.getCodPack());
                    return dia.getCodPack();
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al insertar día de spa: " + e.getMessage());
        }
        return 0;
    }
    public DiaSpa obtenerDia(int id) {
        DiaSpa dia = null;
        String sql = "SELECT * FROM dia_de_spa WHERE codPack = ?";
        
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    dia = new DiaSpa();
                    dia.setCodPack(rs.getInt("codPack"));
                    dia.setFechaYHora(rs.getTimestamp("FechayHora").toLocalDateTime());

                    dia.setPreferencias(String.valueOf(rs.getBoolean("preferencias")));

                    int codCli = rs.getInt("CodCli");
                    Cliente cliente = clienteData.buscarCliente(codCli); // Usamos ClienteData
                    dia.setCodCliente(cliente);
                    
                    dia.setMonto(rs.getDouble("monto"));
                    dia.setEstadoDia(rs.getBoolean("estadoDia"));
                }
            }
            
        } catch (SQLException e) {
            System.err.println("Error al obtener día de spa: " + e.getMessage());
        }
        return dia;
    }
    public DiaSpa obtenerDiaPorCliente(int codCli, LocalDateTime FechaYHora) {
        DiaSpa dia = null;
        String sql = "SELECT * FROM dia_de_spa WHERE CodCli = ? AND FechayHora = ?";
        
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, codCli);
            ps.setTimestamp(2, Timestamp.valueOf(FechaYHora));
            
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    dia = new DiaSpa();
                    dia.setCodPack(rs.getInt("codPack"));
                    dia.setFechaYHora(rs.getTimestamp("FechayHora").toLocalDateTime());

                    dia.setPreferencias( String.valueOf( rs.getBoolean("preferencias") ) ); // Asumiendo que setPreferencias(boolean) existe

                    Cliente cliente = clienteData.buscarCliente(codCli);
                    dia.setCodCliente(cliente);
                    
                    dia.setMonto(rs.getDouble("monto"));
                    dia.setEstadoDia(rs.getBoolean("estadoDia"));
                }
            }
            
        } catch (SQLException e) {
            System.err.println("Error al obtener día por cliente: " + e.getMessage());
        }
        return dia;
    }
    public boolean actualizarMonto(int codPack, double nuevoMonto) {
        String sql = "UPDATE dia_de_spa SET monto = ? WHERE codPack = ?";
        
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setDouble(1, nuevoMonto);
            ps.setInt(2, codPack);
            
            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0; 
            
        } catch (SQLException e) {
            System.err.println("Error al actualizar el monto: " + e.getMessage());
            return false;
        }
    }
}