
package Persistencia;

import Modeloo.DiaSpa;
import java.time.LocalDateTime;
import org.mariadb.jdbc.Connection;

public class DiaSpaData {
    
    public DiaSpaData() {
    }
    
    private Connection con= null;
    
    public DiaSpaData(Connection con){
        this.con= con;
    }
    
    private void insertarDia(DiaSpa dia){
        
    }
    private void obtenerDias (int id){
        
    }
    private void obtenerDiaPorCliente(int codCli, LocalDateTime FechaYHora){
        
    }
    private void obtenerMonto(int codPack, double nuevoMonto){
        
    }
}