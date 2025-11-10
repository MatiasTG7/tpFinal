
package Vistass;

import Modeloo.Conexion;
import Modeloo.Masajista;
import Modeloo.EspecialidadMasajista;
import Persistencia.MasajistaData;
import javax.swing.JOptionPane;
import org.mariadb.jdbc.Connection;

public class AgregarMasajista extends javax.swing.JInternalFrame {

    private Connection con;
    private MasajistaData masajistaData;
    private int codMasajistaActual = -1; 
    
    
    public AgregarMasajista() {
        initComponents();
        con= (Connection) Conexion.getConexion();
        masajistaData = new MasajistaData(con);
        llenarComboEspecialidades(); 
    }
    
   
    private void llenarComboEspecialidades() {
        jcbEspecialidadMasajista.removeAllItems();
        for (EspecialidadMasajista esp : EspecialidadMasajista.values()) {
            // Formatea el Enum a Capitalizado (Ej: FACIAL -> Facial) para la vista
            String nombre = esp.name().toLowerCase();
            String nombreCapitalizado = nombre.substring(0, 1).toUpperCase() + nombre.substring(1);
            jcbEspecialidadMasajista.addItem(nombreCapitalizado);
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jtfNombreMasajista = new javax.swing.JTextField();
        jcbEspecialidadMasajista = new javax.swing.JComboBox<>();
        jcbEstadoMasajista = new javax.swing.JComboBox<>();
        jbBuscarMasajista = new javax.swing.JButton();
        jtfTelefonoMasajista = new javax.swing.JTextField();
        jlTitulo = new javax.swing.JLabel();
        jbGuardarMasajista = new javax.swing.JButton();
        jlMatriculaMasajista = new javax.swing.JLabel();
        jbActualizarMasajista = new javax.swing.JButton();
        jlNombreMasajista = new javax.swing.JLabel();
        jbEliminarMasajista = new javax.swing.JButton();
        jlGestionMasajista = new javax.swing.JLabel();
        jbCambiarEstadoMasajista = new javax.swing.JButton();
        jlTelefonoMasajista = new javax.swing.JLabel();
        jlEspecialidadMasajista = new javax.swing.JLabel();
        jtfMatriculaMasajista = new javax.swing.JTextField();
        jlEstadoMasajista = new javax.swing.JLabel();

        jtfNombreMasajista.setEditable(false);
        jtfNombreMasajista.setForeground(new java.awt.Color(69, 54, 14));
        jtfNombreMasajista.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(138, 186, 40)));

        jcbEspecialidadMasajista.setBackground(new java.awt.Color(242, 242, 242));
        jcbEspecialidadMasajista.setFont(new java.awt.Font("Mongolian Baiti", 0, 12)); // NOI18N
        jcbEspecialidadMasajista.setForeground(new java.awt.Color(69, 97, 11));
        jcbEspecialidadMasajista.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Facial", "Corporal", "Relajacion", "Estetico" }));
        jcbEspecialidadMasajista.setBorder(null);

        jcbEstadoMasajista.setBackground(new java.awt.Color(242, 242, 242));
        jcbEstadoMasajista.setFont(new java.awt.Font("Mongolian Baiti", 0, 12)); // NOI18N
        jcbEstadoMasajista.setForeground(new java.awt.Color(69, 97, 11));
        jcbEstadoMasajista.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Activo", "Inactivo" }));
        jcbEstadoMasajista.setBorder(null);

        jbBuscarMasajista.setFont(new java.awt.Font("Mongolian Baiti", 0, 12)); // NOI18N
        jbBuscarMasajista.setForeground(new java.awt.Color(69, 97, 11));
        jbBuscarMasajista.setText("Buscar");
        jbBuscarMasajista.setBorder(null);
        jbBuscarMasajista.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbBuscarMasajistaActionPerformed(evt);
            }
        });

        jtfTelefonoMasajista.setEditable(false);
        jtfTelefonoMasajista.setForeground(new java.awt.Color(69, 54, 14));
        jtfTelefonoMasajista.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(138, 186, 40)));
        jtfTelefonoMasajista.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfTelefonoMasajistaActionPerformed(evt);
            }
        });

        jlTitulo.setFont(new java.awt.Font("Cambria", 0, 36)); // NOI18N
        jlTitulo.setForeground(new java.awt.Color(69, 97, 11));
        jlTitulo.setText("Spa Entre Dedos");

        jbGuardarMasajista.setFont(new java.awt.Font("Mongolian Baiti", 0, 12)); // NOI18N
        jbGuardarMasajista.setForeground(new java.awt.Color(69, 97, 11));
        jbGuardarMasajista.setText("Guardar");
        jbGuardarMasajista.setBorder(null);
        jbGuardarMasajista.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbGuardarMasajistaActionPerformed(evt);
            }
        });

        jlMatriculaMasajista.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        jlMatriculaMasajista.setForeground(new java.awt.Color(69, 54, 14));
        jlMatriculaMasajista.setText("Matricula:");

        jbActualizarMasajista.setFont(new java.awt.Font("Mongolian Baiti", 0, 12)); // NOI18N
        jbActualizarMasajista.setForeground(new java.awt.Color(69, 97, 11));
        jbActualizarMasajista.setText("Actualizar");
        jbActualizarMasajista.setBorder(null);
        jbActualizarMasajista.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbActualizarMasajistaActionPerformed(evt);
            }
        });

        jlNombreMasajista.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        jlNombreMasajista.setForeground(new java.awt.Color(69, 54, 14));
        jlNombreMasajista.setText("Nombre Completo:");

        jbEliminarMasajista.setFont(new java.awt.Font("Mongolian Baiti", 0, 12)); // NOI18N
        jbEliminarMasajista.setForeground(new java.awt.Color(69, 97, 11));
        jbEliminarMasajista.setText("Eliminar");
        jbEliminarMasajista.setBorder(null);
        jbEliminarMasajista.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbEliminarMasajistaActionPerformed(evt);
            }
        });

        jlGestionMasajista.setFont(new java.awt.Font("Mongolian Baiti", 0, 14)); // NOI18N
        jlGestionMasajista.setForeground(new java.awt.Color(69, 97, 11));
        jlGestionMasajista.setText("Gestion Masajistas");

        jbCambiarEstadoMasajista.setFont(new java.awt.Font("Mongolian Baiti", 0, 12)); // NOI18N
        jbCambiarEstadoMasajista.setForeground(new java.awt.Color(69, 97, 11));
        jbCambiarEstadoMasajista.setText("Cambiar Estado");
        jbCambiarEstadoMasajista.setBorder(null);
        jbCambiarEstadoMasajista.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbCambiarEstadoMasajistaActionPerformed(evt);
            }
        });

        jlTelefonoMasajista.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        jlTelefonoMasajista.setForeground(new java.awt.Color(69, 54, 14));
        jlTelefonoMasajista.setText("Telefono:");

        jlEspecialidadMasajista.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        jlEspecialidadMasajista.setForeground(new java.awt.Color(69, 54, 14));
        jlEspecialidadMasajista.setText("Especialidad:");

        jtfMatriculaMasajista.setEditable(false);
        jtfMatriculaMasajista.setForeground(new java.awt.Color(69, 54, 14));
        jtfMatriculaMasajista.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(138, 186, 40)));

        jlEstadoMasajista.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        jlEstadoMasajista.setForeground(new java.awt.Color(69, 54, 14));
        jlEstadoMasajista.setText("Estado:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(115, Short.MAX_VALUE)
                .addComponent(jlTitulo)
                .addGap(98, 98, 98))
            .addGroup(layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jlNombreMasajista, javax.swing.GroupLayout.DEFAULT_SIZE, 182, Short.MAX_VALUE)
                            .addComponent(jtfNombreMasajista)
                            .addComponent(jtfMatriculaMasajista)
                            .addComponent(jcbEspecialidadMasajista, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jlMatriculaMasajista)
                            .addComponent(jbActualizarMasajista, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jbGuardarMasajista, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jlEspecialidadMasajista, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(52, 52, 52)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jbBuscarMasajista, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jcbEstadoMasajista, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jtfTelefonoMasajista)
                            .addComponent(jlTelefonoMasajista, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jbEliminarMasajista, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jbCambiarEstadoMasajista, javax.swing.GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE)
                            .addComponent(jlEstadoMasajista, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(138, 138, 138)
                        .addComponent(jlGestionMasajista)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jlTitulo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlGestionMasajista)
                .addGap(37, 37, 37)
                .addComponent(jlMatriculaMasajista)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jbBuscarMasajista, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jtfMatriculaMasajista, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE))
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jlNombreMasajista)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtfNombreMasajista, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jlTelefonoMasajista)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtfTelefonoMasajista, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jlEspecialidadMasajista)
                    .addComponent(jlEstadoMasajista))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jcbEstadoMasajista, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jcbEspecialidadMasajista, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(66, 66, 66)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jbGuardarMasajista, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbEliminarMasajista, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbActualizarMasajista, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbCambiarEstadoMasajista, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(43, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
 private void llenarFormulario(Masajista m) {
        jtfMatriculaMasajista.setText(m.getMatricula());
        jtfNombreMasajista.setText(m.getNombreMasajista());
        jtfTelefonoMasajista.setText(m.getTelefonoMasajista());
        
      
        String especialidadStr = m.getEspecialidad().toString();
        String especialidadCapitalizada = especialidadStr.substring(0, 1).toUpperCase() + especialidadStr.substring(1).toLowerCase();
        jcbEspecialidadMasajista.setSelectedItem(especialidadCapitalizada);
        
      
        jcbEstadoMasajista.setSelectedItem(m.isEstadoMasajista() ? "Activo" : "Inactivo");
        
       
        this.codMasajistaActual = m.getCodMasajista();
    }
    

    private Masajista obtenerDatosDelFormulario() {
        String matricula = jtfMatriculaMasajista.getText().trim();
        String nombre = jtfNombreMasajista.getText().trim();
        String telefono = jtfTelefonoMasajista.getText().trim();
        String especialidadStr = (String) jcbEspecialidadMasajista.getSelectedItem();
        boolean estado = jcbEstadoMasajista.getSelectedItem().equals("Activo");
        
        if (matricula.isEmpty() || nombre.isEmpty() || especialidadStr == null) {
             JOptionPane.showMessageDialog(this, "Matrícula, Nombre y Especialidad son obligatorios.", "Validación", JOptionPane.WARNING_MESSAGE);
             return null;
        }

        try {
           
            EspecialidadMasajista especialidad = EspecialidadMasajista.valueOf(especialidadStr.toUpperCase());
            
          
            if (this.codMasajistaActual != -1) {
               
                return new Masajista(this.codMasajistaActual, matricula, nombre, telefono,  estado,especialidad);
            } else {
               
                return new Masajista(matricula, nombre, telefono, estado,especialidad);
            }
            
        } catch (IllegalArgumentException e) {
             JOptionPane.showMessageDialog(this, "Especialidad seleccionada no es válida.", "Error", JOptionPane.ERROR_MESSAGE);
             return null;
        }
    }
   
    
    private void limpiarFormulario() {
        jtfMatriculaMasajista.setText("");
        jtfNombreMasajista.setText("");
        jtfTelefonoMasajista.setText("");
        jcbEstadoMasajista.setSelectedIndex(0);
        this.codMasajistaActual = -1; // Resetear el ID
    }
 
   
    private void jtfTelefonoMasajistaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfTelefonoMasajistaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtfTelefonoMasajistaActionPerformed

    private void jbGuardarMasajistaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbGuardarMasajistaActionPerformed
   if (this.codMasajistaActual != -1) {
            JOptionPane.showMessageDialog(this, "El Masajista ya existe (ID: " + this.codMasajistaActual + "). Use 'Actualizar'.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        Masajista nuevo = obtenerDatosDelFormulario(); 
        
        if (nuevo != null) {
            masajistaData.insertarMasajista(nuevo); 
            limpiarFormulario();
        }
    }//GEN-LAST:event_jbGuardarMasajistaActionPerformed

    private void jbBuscarMasajistaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbBuscarMasajistaActionPerformed
     String matricula = jtfMatriculaMasajista.getText().trim();
        if (matricula.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe ingresar una matrícula para buscar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Masajista encontrado = masajistaData.buscarMasajistaPorMatricula(matricula);
        
        if (encontrado != null) {
            llenarFormulario(encontrado);
            JOptionPane.showMessageDialog(this, "Masajista encontrado y datos cargados.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } else {
            limpiarFormulario();
            jtfMatriculaMasajista.setText(matricula); 
            JOptionPane.showMessageDialog(this, "Masajista con matrícula " + matricula + " no encontrado. Listo para Guardar.", "Búsqueda", JOptionPane.INFORMATION_MESSAGE);
        }
    
    }//GEN-LAST:event_jbBuscarMasajistaActionPerformed

    private void jbActualizarMasajistaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbActualizarMasajistaActionPerformed
      if (this.codMasajistaActual == -1) { 
             JOptionPane.showMessageDialog(this, "Primero debe buscar y cargar un Masajista para actualizar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
             return;
        }
        
        Masajista modificado = obtenerDatosDelFormulario(); 
        
        if (modificado != null) {
            masajistaData.actualizarMasajista(modificado); 
            limpiarFormulario();
        }
    }//GEN-LAST:event_jbActualizarMasajistaActionPerformed

    private void jbEliminarMasajistaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbEliminarMasajistaActionPerformed
    if (this.codMasajistaActual == -1) { 
             JOptionPane.showMessageDialog(this, "Primero debe buscar y cargar un Masajista para eliminar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
             return;
        }
        
        int confirmacion = JOptionPane.showConfirmDialog(this, "¿Está seguro de eliminar PERMANENTEMENTE el Masajista con ID " + this.codMasajistaActual + "?", "Confirmar Eliminación", JOptionPane.YES_NO_OPTION);
        
        if (confirmacion == JOptionPane.YES_OPTION) {
            masajistaData.borrarMasajista(this.codMasajistaActual);
            limpiarFormulario();
        }
    }//GEN-LAST:event_jbEliminarMasajistaActionPerformed

    private void jbCambiarEstadoMasajistaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbCambiarEstadoMasajistaActionPerformed
      if (this.codMasajistaActual == -1) { 
             JOptionPane.showMessageDialog(this, "Primero debe buscar y cargar un Masajista para cambiar el estado.", "Advertencia", JOptionPane.WARNING_MESSAGE);
             return;
        }
        
        // Obtiene el estado actual de la interfaz y lo invierte
        boolean estadoActual = jcbEstadoMasajista.getSelectedItem().equals("Activo");
        boolean nuevoEstado = !estadoActual; 

        masajistaData.cambiarEstadoLogico(this.codMasajistaActual, nuevoEstado);
        
        // Actualiza el formulario para reflejar el cambio
        jcbEstadoMasajista.setSelectedItem(nuevoEstado ? "Activo" : "Inactivo");
    }//GEN-LAST:event_jbCambiarEstadoMasajistaActionPerformed

    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jbActualizarMasajista;
    private javax.swing.JButton jbBuscarMasajista;
    private javax.swing.JButton jbCambiarEstadoMasajista;
    private javax.swing.JButton jbEliminarMasajista;
    private javax.swing.JButton jbGuardarMasajista;
    private javax.swing.JComboBox<String> jcbEspecialidadMasajista;
    private javax.swing.JComboBox<String> jcbEstadoMasajista;
    private javax.swing.JLabel jlEspecialidadMasajista;
    private javax.swing.JLabel jlEstadoMasajista;
    private javax.swing.JLabel jlGestionMasajista;
    private javax.swing.JLabel jlMatriculaMasajista;
    private javax.swing.JLabel jlNombreMasajista;
    private javax.swing.JLabel jlTelefonoMasajista;
    private javax.swing.JLabel jlTitulo;
    private javax.swing.JTextField jtfMatriculaMasajista;
    private javax.swing.JTextField jtfNombreMasajista;
    private javax.swing.JTextField jtfTelefonoMasajista;
    // End of variables declaration//GEN-END:variables
}
