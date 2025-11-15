package Persistencia;

import Modeloo.EspecialidadMasajista;
import Modeloo.Masajista;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;


public class MasajistaData {

    private Connection con;

    public MasajistaData(Connection con) {
        this.con = con;
    }

    public void insertarMasajista(Masajista masajista) {
        String sql = "INSERT INTO masajista (matricula, nombreMasajista, telefonoMasajista, especialidad, estadoMasajista) "
                   + "VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, masajista.getMatricula());
            ps.setString(2, masajista.getNombreMasajista());
            ps.setString(3, masajista.getTelefonoMasajista());
           
            ps.setString(4, masajista.getEspecialidad().toString().toLowerCase()); 
            ps.setBoolean(5, masajista.isEstadoMasajista()); 

            int exito = ps.executeUpdate();

            if (exito > 0) {
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        masajista.setCodMasajista(rs.getInt(1));
                        JOptionPane.showMessageDialog(null, "Masajista con ID " + masajista.getCodMasajista() + " guardado con éxito.");
                    }
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla Masajista (Guardar): " + ex.getMessage(), "Error SQL", JOptionPane.ERROR_MESSAGE);
        }
    }

   
    public Masajista buscarMasajistaPorMatricula(String matricula) {
        Masajista masajista = null;
        String sql = "SELECT codMasajista, matricula, nombreMasajista, telefonoMasajista, especialidad, estadoMasajista "
                   + "FROM masajista WHERE matricula = ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, matricula);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    masajista = new Masajista();
                    masajista.setCodMasajista(rs.getInt("codMasajista"));
                    masajista.setMatricula(rs.getString("matricula"));
                    masajista.setNombreMasajista(rs.getString("nombreMasajista"));
                    masajista.setTelefonoMasajista(rs.getString("telefonoMasajista"));

                    String especialidadStr = rs.getString("especialidad");
                    masajista.setEspecialidad(EspecialidadMasajista.valueOf(especialidadStr.toUpperCase())); 
                    
                    masajista.setEstadoMasajista(rs.getBoolean("estadoMasajista"));
                }
            }
        } catch (SQLException | IllegalArgumentException ex) {
             JOptionPane.showMessageDialog(null, "Error al buscar Masajista por Matrícula: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        return masajista;
    }

    public List<Masajista> listarMasajistasActivos() {
    List<Masajista> masajistas = new ArrayList<>();
    String sql = "SELECT * FROM masajista WHERE estadoMasajista = 1"; 
    
    try (PreparedStatement ps = con.prepareStatement(sql)) {
        ResultSet rs = ps.executeQuery();
        
        while (rs.next()) {
            Masajista masajista = new Masajista();
            
            masajista.setCodMasajista(rs.getInt("codMasajista"));
            masajista.setMatricula(rs.getString("matricula"));
            masajista.setNombreMasajista(rs.getString("nombreMasajista"));
            masajista.setTelefonoMasajista(rs.getString("telefonoMasajista"));
            masajista.setEstadoMasajista(rs.getBoolean("estadoMasajista"));
            
            String especialidadDB = rs.getString("especialidad");
            masajista.setEspecialidad(EspecialidadMasajista.valueOf(especialidadDB.toUpperCase()));
            
            masajistas.add(masajista);
        }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al listar masajistas activos: " + ex.getMessage());
            ex.printStackTrace();
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, "Error de ENUM en Masajistas: " + e.getMessage());
        }
        return masajistas;
    }
   
    public void actualizarMasajista(Masajista masajista) {
        String sql = "UPDATE masajista SET matricula = ?, nombreMasajista = ?, telefonoMasajista = ?, especialidad = ?, estadoMasajista = ? "
                   + "WHERE codMasajista = ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, masajista.getMatricula());
            ps.setString(2, masajista.getNombreMasajista());
            ps.setString(3, masajista.getTelefonoMasajista());
            ps.setString(4, masajista.getEspecialidad().toString().toLowerCase());
            ps.setBoolean(5, masajista.isEstadoMasajista());
            ps.setInt(6, masajista.getCodMasajista());

            int exito = ps.executeUpdate();
            if (exito > 0) {
                JOptionPane.showMessageDialog(null, "Masajista actualizado con éxito.");
            } else {
                 JOptionPane.showMessageDialog(null, "No se encontró el Masajista para actualizar.");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla Masajista (Actualizar): " + ex.getMessage(), "Error SQL", JOptionPane.ERROR_MESSAGE);
        }
    }

   
    public void borrarMasajista(int id) {
        String sql = "DELETE FROM masajista WHERE codMasajista = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            int exito = ps.executeUpdate();
            if (exito > 0) {
                JOptionPane.showMessageDialog(null, "Masajista eliminado con éxito (Baja Física).");
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró el Masajista para eliminar.");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla Masajista (Eliminar): " + ex.getMessage(), "Error SQL", JOptionPane.ERROR_MESSAGE);
        }
    }

    
    public void cambiarEstadoLogico(int id, boolean nuevoEstado) {
        String sql = "UPDATE masajista SET estadoMasajista = ? WHERE codMasajista = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setBoolean(1, nuevoEstado);
            ps.setInt(2, id);

            int exito = ps.executeUpdate();
            if (exito > 0) {
                String estado = nuevoEstado ? "Activado" : "Desactivado";
                JOptionPane.showMessageDialog(null, "Masajista " + estado + " (Cambio de estado lógico).");
            } else {
                 JOptionPane.showMessageDialog(null, "No se encontró el Masajista para cambiar el estado.");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla Masajista (Cambio de Estado): " + ex.getMessage(), "Error SQL", JOptionPane.ERROR_MESSAGE);
        }
    }
    
  
    public List<Masajista> obtenerTodosLosMasajistas() {
        List<Masajista> lista = new ArrayList<>();
        String sql = "SELECT codMasajista, matricula, nombreMasajista, telefonoMasajista, especialidad, estadoMasajista FROM masajista";
        
        try (PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Masajista m = new Masajista();
                m.setCodMasajista(rs.getInt("codMasajista"));
                m.setMatricula(rs.getString("matricula"));
                m.setNombreMasajista(rs.getString("nombreMasajista"));
                m.setTelefonoMasajista(rs.getString("telefonoMasajista"));
                m.setEspecialidad(EspecialidadMasajista.valueOf(rs.getString("especialidad").toUpperCase()));
                m.setEstadoMasajista(rs.getBoolean("estadoMasajista"));
                lista.add(m);
            }

        } catch (SQLException | IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(null, "Error al obtener la lista de Masajistas: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        return lista;
    }
}