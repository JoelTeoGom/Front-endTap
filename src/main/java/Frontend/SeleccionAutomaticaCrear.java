/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Frontend;

import Estructura.Actor;
import Estructura.ActorContext;
import HelloWorld.HelloWorldActor;
import Insult.InsultActor;
import RingActor.*;
import java.util.ArrayList;

/**
 *
 * @author joel
 */
public class SeleccionAutomaticaCrear extends javax.swing.JFrame {
    
    private int n;

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    /**
     * Creates new form SeleccionAutomaticaCrear
     */
    public SeleccionAutomaticaCrear(int n) {
        initComponents();
        this.n = n;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        insultButton = new javax.swing.JButton();
        helloWorldActor = new javax.swing.JButton();
        RingActor = new javax.swing.JButton();
        ActorNormal = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        returnBut = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        insultButton.setText("InsultActor");
        insultButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                insultButtonActionPerformed(evt);
            }
        });

        helloWorldActor.setText("HelloWorldActor");
        helloWorldActor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                helloWorldActorActionPerformed(evt);
            }
        });

        RingActor.setText("RingActor");
        RingActor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RingActorActionPerformed(evt);
            }
        });

        ActorNormal.setText("ActorNormal");
        ActorNormal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ActorNormalActionPerformed(evt);
            }
        });

        jLabel1.setText("Selecció de Actors");

        returnBut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                returnButActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(RingActor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(insultButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(ActorNormal, javax.swing.GroupLayout.DEFAULT_SIZE, 254, Short.MAX_VALUE))
                    .addComponent(helloWorldActor, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(returnBut, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(9, Short.MAX_VALUE)
                .addComponent(returnBut, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(insultButton, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(helloWorldActor, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(RingActor, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(ActorNormal, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void insultButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_insultButtonActionPerformed
        int size = ActorContext.getInstance().getActorLibrary().size();
        if(n != 0 ){
            for(int i = 1; i<=n; i++){
                int offset = size + i;
                ActorContext.getInstance().spawnActor("insult"+offset, new InsultActor());
            }
        }
    }//GEN-LAST:event_insultButtonActionPerformed

    private void helloWorldActorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_helloWorldActorActionPerformed
        int size = ActorContext.getInstance().getActorLibrary().size();
        if(n != 0 ){
            for(int i = 1; i<=n; i++){
                int offset = size + i;
                ActorContext.getInstance().spawnActor("helloWorld"+offset, new HelloWorldActor());
            }
        }
    }//GEN-LAST:event_helloWorldActorActionPerformed

    private void RingActorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RingActorActionPerformed
        ArrayList<RingActor> ring = new ArrayList<>();
        ring.add(0,new RingActor());
        int i = 1;
        while (i < n){
            RingActor anterior = ring.get(i-1);
            RingActor actual = new RingActor();
            anterior.setNext(ActorContext.getInstance().spawnActor("Ring"+i, actual));
            ring.add(i, actual);
            if(i == n-1)
                actual.setNext(ActorContext.getInstance().spawnActor("Primero",ring.get(0)));
            i++;
        }
    }//GEN-LAST:event_RingActorActionPerformed

    private void ActorNormalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ActorNormalActionPerformed
        int size = ActorContext.getInstance().getActorLibrary().size();
        if(n != 0 ){
            for(int i = 1; i<=n; i++){
                int offset = size + i;
                ActorContext.getInstance().spawnActor("normal"+offset, new Actor());
            }
        }
    }//GEN-LAST:event_ActorNormalActionPerformed

    private void returnButActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_returnButActionPerformed
        dispose();
        CreaActors llista = new CreaActors();
        llista.setVisible(true);
    }//GEN-LAST:event_returnButActionPerformed
    
    
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
            java.util.logging.Logger.getLogger(SeleccionAutomaticaCrear.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SeleccionAutomaticaCrear.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SeleccionAutomaticaCrear.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SeleccionAutomaticaCrear.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SeleccionAutomaticaCrear(10).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ActorNormal;
    private javax.swing.JButton RingActor;
    private javax.swing.JButton helloWorldActor;
    private javax.swing.JButton insultButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JButton returnBut;
    // End of variables declaration//GEN-END:variables
}
