
package Persistencia;

import Modeloo.EspecialidadMasajista;
import Modeloo.Masajista;
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
    
    // Dentro de MasajistaData.java (o SesionData para verificar disponibilidad)

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
}
