
package Vistass;

import Modeloo.Conexion;
import Modeloo.DiaSpa;
import Modeloo.Masaje;
import Modeloo.Masajista;
import Modeloo.Sesion;
import Persistencia.DiaSpaData;
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

    private DefaultTableModel modeloTabla;
    private DiaSpaData diaSpaData;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    
   
   public ReporteDiaSpa() {
        initComponents();
        this.modeloTabla = new DefaultTableModel();
        this.diaSpaData = new DiaSpaData((org.mariadb.jdbc.Connection) (Connection) Conexion.getConexion());
        armarCabeceraTabla();
        cargarDatosTabla();
     
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jtReporteDias = new javax.swing.JTable();
        jdiasMas = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setClosable(true);

        jtReporteDias.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Codigo", "Cliente", "Fecha y hora", "Preferencias", "Monto", "Estado"
            }
        ));
        jtReporteDias.setToolTipText("");
        jScrollPane1.setViewportView(jtReporteDias);

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
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 606, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jdiasMas)
                .addGap(194, 194, 194))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(241, 241, 241))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jdiasMas)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 366, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel jdiasMas;
    private javax.swing.JTable jtReporteDias;
    // End of variables declaration//GEN-END:variables

    private void armarCabeceraTabla() {
        modeloTabla.addColumn("Codigo");
        modeloTabla.addColumn("Cliente");
        modeloTabla.addColumn("Fecha y Hora");
        modeloTabla.addColumn("Preferencias");
        modeloTabla.addColumn("Monto");
        modeloTabla.addColumn("Estado");
        jtReporteDias.setModel(modeloTabla); 
    }
    
    private void cargarDatosTabla() {
        modeloTabla.setRowCount(0);

        List<DiaSpa> diasActivos = diaSpaData.listarDiasSpaActivos();
        
        for (DiaSpa ds : diasActivos) {
            
            String nombreCliente = "Cliente no encontrado";
            if (ds.getCodCli() != null) {
                nombreCliente = ds.getCodCli().getNombreCliente();
            }
            
            modeloTabla.addRow(new Object[]{
                ds.getCodPack(),
                nombreCliente,
                ds.getFechaYHora().format(formatter),
                ds.getPreferencias(),
                ds.getMonto(),
                ds.isEstadoDia() ? "Activo" : "Inactivo"
            });
        }
    }
}