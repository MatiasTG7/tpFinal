
package Vistass;

import Modeloo.Conexion;
import Persistencia.MasajeData;
import java.util.Map;
import javax.swing.table.DefaultTableModel;
import org.mariadb.jdbc.Connection;

public class ReporteSolicitados extends javax.swing.JInternalFrame {


    private DefaultTableModel modeloTabla;
    private MasajeData masajeData;
    
    public ReporteSolicitados() {
        initComponents();
        this.modeloTabla = new DefaultTableModel();
        this.masajeData = new MasajeData((Connection) Conexion.getConexion());
        armarCabeceraTabla();
        cargarDatosTabla();
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jtSolicitados = new javax.swing.JTable();
        jdiasMas = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setClosable(true);

        jtSolicitados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Masaje", "Veces Solicitado"
            }
        ));
        jScrollPane1.setViewportView(jtSolicitados);

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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(123, 123, 123)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jdiasMas, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(47, 47, 47)))))
                .addContainerGap(25, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(jdiasMas)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 318, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel jdiasMas;
    private javax.swing.JTable jtSolicitados;
    // End of variables declaration//GEN-END:variables

    private void armarCabeceraTabla() {
        modeloTabla.addColumn("Masaje");
        modeloTabla.addColumn("Veces Solicitado");
        jtSolicitados.setModel(modeloTabla);
    }

    private void cargarDatosTabla() {

        modeloTabla.setRowCount(0);
        
        Map<String, Integer> reporte = masajeData.reporteMasajes();
        
        for (Map.Entry<String, Integer> entry : reporte.entrySet()) {
            String nombreMasaje = entry.getKey();
            Integer frecuencia = entry.getValue();
            
            modeloTabla.addRow(new Object[]{
                nombreMasaje,
                frecuencia
            });
        }
    }
}
