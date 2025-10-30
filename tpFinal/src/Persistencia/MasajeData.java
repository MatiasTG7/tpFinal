
package Persistencia;

import Modeloo.Cliente;
import Modeloo.Masaje;
import Modeloo.TipoMasaje;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import org.mariadb.jdbc.Connection;

public class MasajeData {

    private Connection con = null;
    
    public MasajeData(Connection con){
        this.con = con;
    }

    public MasajeData() {
    }
    
    private void guardarMasaje(){
        
    }
    
    private void modificarMasaje(){
        
    }
    
    private void listarPorTipo (TipoMasaje tipo){
        
    }
    
    private void listarMasPedidos (){
        
    }
}
