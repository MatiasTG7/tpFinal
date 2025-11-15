
package Vistass;

import Modeloo.Conexion;
import Modeloo.Masaje;
import Modeloo.Masajista;
import Modeloo.Sesion;
import Persistencia.MasajeData;
import Persistencia.MasajistaData;
import Persistencia.SesionData;
import java.sql.Connection;
import java.time.Duration;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ReporteDiaSpa extends javax.swing.JInternalFrame {
private MasajistaData masajistaData;
    private DefaultTableModel modeloTabla;
    private SesionData sesionData;
    private MasajeData masajeData;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    private Connection con;
   
   public ReporteDiaSpa() {
        initComponents();
        modeloTabla = new DefaultTableModel();
        con = Conexion.getConexion();
        
       
        try {
            con = (Connection) Conexion.getConexion();
            sesionData = new SesionData((org.mariadb.jdbc.Connection) con);
            
            masajistaData = new MasajistaData((org.mariadb.jdbc.Connection) con);
            masajeData = new MasajeData((org.mariadb.jdbc.Connection) con);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error de conexión/inicialización: " + e.getMessage());
            sesionData = null; 
        }
       
        armarCabeceraTabla();
        cargarDatosTabla();
        System.out.println("" );
     
    }
   
 private void armarCabeceraTabla() {
        modeloTabla.addColumn("Fecha");
        modeloTabla.addColumn("Tiempo de Masaje");
        modeloTabla.addColumn("Masajista");
        modeloTabla.addColumn("Masaje");
        jTreporte.setModel(modeloTabla);
       // limpiarTabla();
    }

    private void limpiarTabla() {
        int a = modeloTabla.getRowCount() - 1;
        for (int i = a; i >= 0; i--) {
            modeloTabla.removeRow(i);
        }
    }

    private Map<Integer, Masajista> obtenerMapaMasajistas() {
        Map<Integer, Masajista> mapaMasajistas = new HashMap<>();
        List<Masajista> lista = masajistaData.obtenerTodosLosMasajistas(); 
        for (Masajista m : lista) {
            mapaMasajistas.put(m.getCodMasajista(), m);
        }
        return mapaMasajistas;
    }
    
    private Map<Integer, Masaje> obtenerMapaMasajes() {
        Map<Integer, Masaje> mapaMasajes = new HashMap<>();
        List<Masaje> lista = masajeData.listarMasajes();
        for (Masaje m : lista) {
            mapaMasajes.put(m.getCodTratamiento(), m);
        }
        return mapaMasajes;
    }

    private void cargarDatosTabla() {
        limpiarTabla();
        
        List<Sesion> sesiones = sesionData.listarSesiones();
        Map<Integer, Masajista> mapaMasajistas = obtenerMapaMasajistas();
        Map<Integer, Masaje> mapaMasajes = obtenerMapaMasajes();

        for (Sesion s : sesiones) {
            if (s.getCodPack() > 0) { 
                
                Masajista masajista = mapaMasajistas.get(s.getCodMasajista());
                Masaje masaje = mapaMasajes.get(s.getCodTratamiento());
                
                String nombreMasajista = (masajista != null) ? masajista.getNombreMasajista() : "N/D";
                String nombreMasaje = (masaje != null) ? masaje.getNombreTratamiento() : "N/D";
                
                String tiempoDeMasaje = "N/D";
                if (s.getFechaInicio() != null && s.getFechaFin() != null) {
                    Duration duracion = Duration.between(s.getFechaInicio(), s.getFechaFin());
                    long minutos = duracion.toMinutes();
                    tiempoDeMasaje = minutos + " min";
                }
                
                String fecha = (s.getFechaInicio() != null) ? s.getFechaInicio().format(formatter) : "N/D";
                
                modeloTabla.addRow(new Object[]{
                    fecha,
                    tiempoDeMasaje,
                    nombreMasajista,
                    nombreMasaje
                });
            }
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTreporte = new javax.swing.JTable();
        jdiasMas = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setClosable(true);

        jTreporte.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "fecha", "masajista", "masaje"
            }
        ));
        jTreporte.setToolTipText("");
        jScrollPane1.setViewportView(jTreporte);

        jdiasMas.setFont(new java.awt.Font("Cambria", 0, 36)); // NOI18N
        jdiasMas.setForeground(new java.awt.Color(69, 97, 11));
        jdiasMas.setText("Spa Entre Dedos");

        jLabel1.setFont(new java.awt.Font("Mongolian Baiti", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(69, 97, 11));
        jLabel1.setText("Lista de Reportes del Dia");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(211, 211, 211))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(186, 186, 186)
                        .addComponent(jdiasMas))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 575, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(26, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(jdiasMas)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 377, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTreporte;
    private javax.swing.JLabel jdiasMas;
    // End of variables declaration//GEN-END:variables

}