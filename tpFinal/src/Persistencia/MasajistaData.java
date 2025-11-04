
package Persistencia;

import Modeloo.EspecialidadMasajista;
import Modeloo.Masajista;
import Modeloo.TipoMasaje;
import java.security.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.mariadb.jdbc.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class MasajistaData {

    private Connection con = null;
    
    public MasajistaData(Connection con) {
        this.con = con;
    }

    public MasajistaData() {
    }

    public List<Masajista> listarDisponibles(LocalDateTime inicio, LocalDateTime fin, EspecialidadMasajista especialidad) {
    List<Masajista> masajistasDisponibles = new ArrayList<>();
    
    String sql = "SELECT * FROM masajista "
               + "WHERE estadoMasajista = 1 " 
               + "  AND especialidad = ? "    
               + "  AND matricula NOT IN ("
               + "    SELECT codMasajista FROM sesion "
               + "    WHERE fechaInicio < ? AND fechaFin > ? " 
               + "  )";
               
    try (PreparedStatement ps = con.prepareStatement(sql)) { 
        
        ps.setString(1, especialidad.toString()); 
        ps.setTimestamp(2, java.sql.Timestamp.valueOf(fin));
        ps.setTimestamp(3, java.sql.Timestamp.valueOf(inicio));
        ResultSet rs = ps.executeQuery();
        
        while (rs.next()) {
            Masajista m = new Masajista();
            masajistasDisponibles.add(m);
        }
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(null, "Error al buscar disponibilidad de masajistas. " + ex.getMessage());
    }
    return masajistasDisponibles;
    }
    //----------------------------------------------
    //NO VAYAN A BORRARLO o les pego :/ ali estas de testigo que dijiste que esta bien, a las 00:50hrs
    //----------------------------------------------
    public Masajista mapeoResultset(ResultSet rs) throws SQLException{
    Masajista m = new Masajista();
    m.setCodMasajista(rs.getInt("codMasajista"));
          m.setMatricula(rs.getString("matricula"));
          m.setNombreMasajista(rs.getString("nombreMasajista"));
          m.setTelefonoMasajista(rs.getString("telefonoMasajista"));
          String especialidadMasajista = rs.getString("especialidad").toUpperCase();
          m.setEspecialidad(EspecialidadMasajista.valueOf(especialidadMasajista));
      return m;
     //----------------------------------------------
    //NO VAYAN A BORRARLO o les pego :/
    //----------------------------------------------
    }
    public Masajista buscarMasajistaPorMatricula(String matricula) {
    Masajista masajista = null;
    String sql = "SELECT * FROM masajista WHERE matricula = ?";
    
    try (PreparedStatement ps = con.prepareStatement(sql)) {
        ps.setString(1, matricula);
        
        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                masajista = new Masajista();
                masajista.setCodMasajista(rs.getInt("codMasajista"));
                masajista.setMatricula(rs.getString("matricula"));
                masajista.setNombreMasajista(rs.getString("nombreMasajista"));
                masajista.setTelefonoMasajista(rs.getString("telefonoMasajista"));
                
                String especialidadDB = rs.getString("especialidad");
                masajista.setEspecialidad(EspecialidadMasajista.valueOf(especialidadDB.toUpperCase()));
                
                masajista.setEstadoMasajista(rs.getBoolean("estadoMasajista"));
            }
        }
        
    } catch (SQLException e) {
        System.err.println("Error al buscar masajista por matrícula: " + e.getMessage());
    } catch (IllegalArgumentException ie) {
        System.err.println("Error de ENUM: " + ie.getMessage());
    }
    return masajista;
}

 /// no se que mierda hiciste pero faltan metodos asi q los voy a agregar 
}
