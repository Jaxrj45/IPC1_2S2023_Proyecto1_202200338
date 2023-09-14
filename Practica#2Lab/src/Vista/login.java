/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Vista;

import Funciones.funcionesAlumno;
import static Funciones.funcionesAlumno.buscarAlumno;
import static Funciones.funcionesAlumno.devolverPromedioAlumno;
import static Funciones.funcionesAlumno.devolverPromedioAlumnoBajo;
import Funciones.funcionesUsuario;
import javax.swing.JOptionPane;
import vistasAlumno.moduloAlumno;
import vistasProfesor.moduloProfesor;

/**
 *
 * @author lopez
 */
public class login extends javax.swing.JFrame {

    public login() {
        initComponents();

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        codigoIngresar = new javax.swing.JTextField();
        passwordIngresar = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        iniciarSesion = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(51, 51, 51));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        codigoIngresar.setForeground(new java.awt.Color(102, 102, 102));
        codigoIngresar.setText("Ingresar Codigo");
        codigoIngresar.setName("codigoIngresar"); // NOI18N
        codigoIngresar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                codigoIngresarMouseClicked(evt);
            }
        });
        codigoIngresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                codigoIngresarActionPerformed(evt);
            }
        });

        passwordIngresar.setForeground(new java.awt.Color(102, 102, 102));
        passwordIngresar.setText("Ingresar Password");
        passwordIngresar.setName("passwordIngresar"); // NOI18N
        passwordIngresar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                passwordIngresarMouseClicked(evt);
            }
        });

        jLabel1.setText("Codigo");

        jLabel2.setText("DTT");

        jLabel3.setText("Password");

        iniciarSesion.setText("Iniciar Sesión");
        iniciarSesion.setName("iniciarSesion"); // NOI18N
        iniciarSesion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                iniciarSesionMouseClicked(evt);
            }
        });
        iniciarSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                iniciarSesionActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(61, 61, 61)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(passwordIngresar, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 56, Short.MAX_VALUE)
                        .addComponent(codigoIngresar, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(74, 74, 74))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(iniciarSesion)
                .addGap(124, 124, 124))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(263, Short.MAX_VALUE)
                    .addComponent(jLabel2)
                    .addGap(155, 155, 155)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(102, 102, 102)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(codigoIngresar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(43, 43, 43)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(passwordIngresar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(29, 29, 29)
                .addComponent(iniciarSesion)
                .addContainerGap(59, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(42, 42, 42)
                    .addComponent(jLabel2)
                    .addContainerGap(242, Short.MAX_VALUE)))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void codigoIngresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_codigoIngresarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_codigoIngresarActionPerformed

    private void codigoIngresarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_codigoIngresarMouseClicked

        if (codigoIngresar.getText().equals("Ingresar Codigo")) {
            codigoIngresar.setText(null);
        }

    }//GEN-LAST:event_codigoIngresarMouseClicked

    private void passwordIngresarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_passwordIngresarMouseClicked

        if (passwordIngresar.getText().equals("Ingresar Password")) {
            passwordIngresar.setText(null);
        }
    }//GEN-LAST:event_passwordIngresarMouseClicked

    private void iniciarSesionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_iniciarSesionMouseClicked


    }//GEN-LAST:event_iniciarSesionMouseClicked

    private void iniciarSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_iniciarSesionActionPerformed

        String codigoAdmin = "admin";
        String passwordAdmin = "admin";
        String usuarioIngresado = codigoIngresar.getText();
        String passwordIngresado = passwordIngresar.getText();
        boolean vistaP = funcionesUsuario.mostrarVentanaProfesor(usuarioIngresado, passwordIngresado);
        boolean vistaU = funcionesAlumno.mostrarVentanaAlumno(usuarioIngresado, passwordIngresado);
        
        devolverPromedioAlumnoBajo(1);
        
        if (codigoAdmin.equals(usuarioIngresado) && passwordAdmin.equals(passwordIngresado)) {

            modulo_Administracion modeloA = new modulo_Administracion();
            modeloA.setVisible(true);
            this.dispose();

        } else if (vistaU) {
            moduloAlumno.alumnoLogeado = buscarAlumno(Integer.parseInt(usuarioIngresado));
            moduloAlumno ventanaA = new moduloAlumno();
            ventanaA.setVisible(true);

        } else if (vistaP) {
            moduloProfesor.usuarioLogeado = funcionesUsuario.buscarUsuario(Integer.parseInt(usuarioIngresado));
            moduloProfesor ventanaP = new moduloProfesor();
            ventanaP.setVisible(true);
            this.dispose();

        } else {
            JOptionPane.showMessageDialog(null, "Datos Incorrectos");
        }


    }//GEN-LAST:event_iniciarSesionActionPerformed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        // TODO add your handling code here:
    }//GEN-LAST:event_formWindowActivated

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened

    }//GEN-LAST:event_formWindowOpened

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField codigoIngresar;
    private javax.swing.JButton iniciarSesion;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JTextField passwordIngresar;
    // End of variables declaration//GEN-END:variables
}
