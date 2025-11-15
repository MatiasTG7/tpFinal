
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
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ReporteDiaSpa extends javax.swing.JInternalFrame {

   private MasajistaData masajistaData;
   private DefaultTableModel modeloTabla;
   private SesionData sesionData;
   private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
   private Connection con;
  
   
   public ReporteDiaSpa() {
        initComponents();
        con = Conexion.getConexion();
        
       
        try {
            con = (Connection) Conexion.getConexion();
            sesionData = new SesionData((org.mariadb.jdbc.Connection) con); 
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error de conexión/inicialización: " + e.getMessage());
            sesionData = null; 
        }
        
        armarCabeceraTabla();
        cargarTabla();
    }
   
    private void armarCabeceraTabla(){
        modeloTabla.setColumnCount(0);
        
        modeloTabla.addColumn("Fecha");
        modeloTabla.addColumn("Masajista");
        modeloTabla.addColumn("Masaje (Tratamiento)");
        
       
        jTreporte.setModel(modeloTabla); 
    }
    
    private void limpiarTabla() {
        if (modeloTabla != null && modeloTabla.getRowCount() > 0) {
            modeloTabla.setRowCount(0);
        }
    }

   
    private void cargarTabla() {
        limpiarTabla();
        
        if (sesionData == null) {
            return; 
        }
        
        try {
          
            List<Sesion> sesiones = sesionData.listarSesiones(); 
            
            if (sesiones != null) {
                for (Sesion s : sesiones) {
                    
                   
                    String fecha = s.getFechaInicio().format(formatter);
                    
                   
                    Duration duration = Duration.between(s.getFechaInicio(), s.getFechaFin());
                    long minutos = duration.toMinutes();
                    String tiempoMasaje = minutos + " min";
                    
                   
                    int codigoMasajista = s.getCodMasajista(); 
                    
                    
                    modeloTabla.addRow(new Object[]{
                        fecha,               
                        tiempoMasaje,      
                        codigoMasajista
                    });
                }
            }
        } catch (Exception ex) {
             JOptionPane.showMessageDialog(this, "Error al cargar las sesiones: " + ex.getMessage() + ". Revisa la implementación de listarSesiones() y la clase Sesion.", "Error de Carga", JOptionPane.ERROR_MESSAGE);
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