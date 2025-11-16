
package Persistencia;

import Modeloo.DiaSpa;
import Modeloo.Cliente;
import Modeloo.Conexion;
import java.time.LocalDateTime;
import org.mariadb.jdbc.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class DiaSpaData {
    
    private Connection con = null;
    private ClienteData clienteData;

    public DiaSpaData(Connection con) {
        this.con = con;
        this.clienteData = new ClienteData(con);
    }

    
    public void insertarDia(DiaSpa diaSpa){
        
        Cliente cliente = diaSpa.getCodCli();
        if (cliente == null) {
            JOptionPane.showMessageDialog(null, "Debe seleccionar un cliente.");
            return;
        }

        if (!cliente.isEstadoCliente()) {
            JOptionPane.showMessageDialog(null, "El cliente está inactivo. No se puede crear un Día de Spa.");
            return;
        }
        
        String sql = "INSERT INTO diaspa (fechaYHora, preferencias, codCli, monto, estadoDia) VALUES (?,?,?,?,?)";

        try {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            ps.setTimestamp(1, Timestamp.valueOf(diaSpa.getFechaYHora()));
            ps.setString(2, diaSpa.getPreferencias());
            ps.setInt(3, diaSpa.getCodCli().getCodCli());
            ps.setDouble(4, diaSpa.getMonto());
            ps.setBoolean(5, diaSpa.isEstadoDia());

            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                diaSpa.setCodPack(rs.getInt(1));
            }

            ps.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al guardar el dia de spa: " + ex.getMessage());
        }
    }
    
    public DiaSpa buscarDiaSpa(int codPack) {
        DiaSpa diaSpa = null;
        String sql = "SELECT * FROM diaspa WHERE codPack = ?";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, codPack);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                diaSpa = new DiaSpa();
                diaSpa.setCodPack(rs.getInt("codPack"));
                diaSpa.setFechaYHora(rs.getTimestamp("fechaYHora").toLocalDateTime());
                diaSpa.setPreferencias(rs.getString("preferencias"));

                Cliente cliente = clienteData.buscarCliente(rs.getInt("codCli"));
                diaSpa.setCodCli(cliente);

                diaSpa.setMonto(rs.getDouble("monto"));
                diaSpa.setEstadoDia(rs.getBoolean("estadoDia"));
            }

            ps.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al buscar el dia de spa: " + ex.getMessage());
        }

        return diaSpa;
    }

    public boolean actualizarDiaSpa(DiaSpa diaSpa) {
    
        String sql = "UPDATE diaspa SET fechaYHora=?, preferencias=?, codCli=?, monto=?, estadoDia=? WHERE codPack=?";
        
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setTimestamp(1, Timestamp.valueOf(diaSpa.getFechaYHora()));
            ps.setString(2, diaSpa.getPreferencias());
            ps.setInt(3, diaSpa.getCodCli().getCodCli());
            ps.setDouble(4, diaSpa.getMonto());
            ps.setBoolean(5, diaSpa.isEstadoDia());
            ps.setInt(6, diaSpa.getCodPack());
            
            int filas = ps.executeUpdate();
            return filas > 0;
        
        } catch (SQLException ex) {
                 JOptionPane.showMessageDialog(null, "Error al actualizar: " + ex.getMessage());
        return false;
       }
    }


    public void cambiarEstadoDia(int codPack, boolean estado) {
        String sql = "UPDATE diaspa SET estadoDia=? WHERE codPack=?";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setBoolean(1, estado);
            ps.setInt(2, codPack);

            int filas = ps.executeUpdate();
            if (filas > 0) {

            } else {
                JOptionPane.showMessageDialog(null, "No se encontro un dia de spa con este codigo.");
            }

            ps.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al cambiar el estado del dia de spa: " + ex.getMessage());
        }
    }

    public void eliminarDiaSpaPorCliente(int codCli) {
    String sql = "DELETE FROM diaspa WHERE codCli=?";
    try (PreparedStatement ps = con.prepareStatement(sql)) {
        ps.setInt(1, codCli);
        int filas = ps.executeUpdate();

        if (filas > 0) {
            JOptionPane.showMessageDialog(null, "El dia de spa fue eliminado correctamente.");
        } else {
            JOptionPane.showMessageDialog(null, "No se encontro ningun dia de spa para ese cliente.");
        }

    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(null, "Error al eliminar el dia de spa: " + ex.getMessage());
    }
    }

    public DiaSpa buscarDiaSpaPorCliente(int codCli) {
    String sql = "SELECT * FROM diaspa WHERE codCli = ?";
    DiaSpa diaSpa = null;

    try (PreparedStatement ps = con.prepareStatement(sql)) {
        ps.setInt(1, codCli);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            diaSpa = new DiaSpa();
            diaSpa.setCodPack(rs.getInt("codPack"));
            diaSpa.setFechaYHora(rs.getTimestamp("fechaYHora").toLocalDateTime());
            diaSpa.setPreferencias(rs.getString("preferencias"));
            diaSpa.setMonto(rs.getDouble("monto"));
            diaSpa.setEstadoDia(rs.getBoolean("estadoDia"));
            
            Cliente cliente = new Cliente();
            cliente.setCodCli(rs.getInt("codCli"));
            diaSpa.setCodCli(cliente);
        }

        rs.close();

    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(null, "Error al buscar el dia de spa: " + ex.getMessage());
    }
    return diaSpa;
    }
    
    public List<DiaSpa> listarDiasSpaActivos() {
        List<DiaSpa> diasSpa = new ArrayList<>();
        String sql = "SELECT * FROM diaspa WHERE estadoDia = 1 ORDER BY fechaYHora ASC"; 

        try (PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                DiaSpa dia = new DiaSpa();
                dia.setCodPack(rs.getInt("codPack"));
                dia.setFechaYHora(rs.getTimestamp("fechaYHora").toLocalDateTime());
                dia.setPreferencias(rs.getString("preferencias"));
                dia.setMonto(rs.getDouble("monto"));
                dia.setEstadoDia(rs.getBoolean("estadoDia"));
                
                if (this.clienteData != null) { 
                    Cliente cliente = clienteData.buscarCliente(rs.getInt("codCli"));
                    dia.setCodCli(cliente);
                } else {
                    System.err.println("Error: ClienteData no está inicializado en DiaSpaData.");
                }
                
                diasSpa.add(dia);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al mostrar dias de spa: " + ex.getMessage());
        }
        return diasSpa;
    }
}