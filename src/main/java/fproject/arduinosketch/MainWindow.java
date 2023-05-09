/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package fproject.arduinosketch;

import com.formdev.flatlaf.FlatLightLaf;
import fproject.arduinosketch.circuit.Arduino;
import java.awt.CardLayout;
import javax.swing.event.ChangeListener;

/**
 *
 * @author Jack
 */
public class MainWindow extends javax.swing.JFrame {
    private static MainWindow instance;
    public static MainWindow getInstance(){
        if(instance == null){
            instance = new MainWindow();
            instance.setVisible(true);
        }
        return instance;
    }
    /**
     * Creates new form MainWindow
     */
    public MainWindow() {
        initComponents();
        lblPotSlider.setVisible(false);
        sldrPot.setVisible(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jbutrun = new javax.swing.JButton();
        CardHolder = new javax.swing.JPanel();
        blockPanel1 = new fproject.arduinosketch.CodeEditer.BlockPanel();
        circuitPanel1 = new fproject.arduinosketch.circuit.CircuitPanel();
        circuitEditorPanel2 = new fproject.arduinosketch.circuit.CircuitEditorPanel();
        jButton1 = new javax.swing.JButton();
        sldrPot = new javax.swing.JSlider();
        lblPotSlider = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                formKeyTyped(evt);
            }
        });

        jbutrun.setText("RUN");
        jbutrun.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbutrunActionPerformed(evt);
            }
        });

        CardHolder.setLayout(new java.awt.CardLayout());

        blockPanel1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                blockPanel1KeyTyped(evt);
            }
        });

        javax.swing.GroupLayout blockPanel1Layout = new javax.swing.GroupLayout(blockPanel1);
        blockPanel1.setLayout(blockPanel1Layout);
        blockPanel1Layout.setHorizontalGroup(
            blockPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1409, Short.MAX_VALUE)
        );
        blockPanel1Layout.setVerticalGroup(
            blockPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 706, Short.MAX_VALUE)
        );

        CardHolder.add(blockPanel1, "code_card");

        javax.swing.GroupLayout circuitPanel1Layout = new javax.swing.GroupLayout(circuitPanel1);
        circuitPanel1.setLayout(circuitPanel1Layout);
        circuitPanel1Layout.setHorizontalGroup(
            circuitPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1409, Short.MAX_VALUE)
        );
        circuitPanel1Layout.setVerticalGroup(
            circuitPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 706, Short.MAX_VALUE)
        );

        CardHolder.add(circuitPanel1, "circuit_card");
        CardHolder.add(circuitEditorPanel2, "CircuitEditorCard");

        jButton1.setText("STOP");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        sldrPot.setValue(0);
        sldrPot.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                sldrPotStateChanged(evt);
            }
        });

        lblPotSlider.setText("Pot Value:");

        jMenu1.setText("File");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        jMenu3.setText("Window");

        jMenuItem1.setText("Code");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem1);

        jMenuItem2.setText("Circuit");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem2);

        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jbutrun)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblPotSlider)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(sldrPot, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(CardHolder, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbutrun)
                    .addComponent(jButton1)
                    .addComponent(sldrPot, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPotSlider))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(CardHolder, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    /**
     * Callback function for when the "Run" button is clicked.
     * Calls the run function in the BlockPanel class.
     * @param evt The button clicked event.
     */
    public void paintCircuit(){
        circuitEditorPanel2.repaint();
    }
    private void jbutrunActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbutrunActionPerformed
        blockPanel1.run();
        repaint();
    }//GEN-LAST:event_jbutrunActionPerformed

    private void blockPanel1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_blockPanel1KeyTyped
        

    }//GEN-LAST:event_blockPanel1KeyTyped

    private void formKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyTyped
   
    }//GEN-LAST:event_formKeyTyped

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        CardLayout cl = (CardLayout)CardHolder.getLayout();
        cl.show(CardHolder, "code_card");
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        CardLayout cl = (CardLayout)CardHolder.getLayout();
        cl.show(CardHolder, "CircuitEditorCard");
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       Arduino.code.setShouldStop(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void sldrPotStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_sldrPotStateChanged
     
    }//GEN-LAST:event_sldrPotStateChanged
    public void setPotSliderValue(int v){
        sldrPot.setValue(v);
    }
    public void addPotListener(ChangeListener cl){
        sldrPot.addChangeListener(cl);
        //sldrPot.
    }
    public void removePotListener(ChangeListener cl){
        sldrPot.removeChangeListener(cl);
    }
    public void showPotControls(boolean show){
        lblPotSlider.setVisible(show);
        sldrPot.setVisible(show);
    }
    public int getPotSliderValue(){
        return sldrPot.getValue();
    }
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
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                FlatLightLaf.setup();
                instance = new MainWindow();
                instance.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel CardHolder;
    private fproject.arduinosketch.CodeEditer.BlockPanel blockPanel1;
    private fproject.arduinosketch.circuit.CircuitEditorPanel circuitEditorPanel2;
    private fproject.arduinosketch.circuit.CircuitPanel circuitPanel1;
    private javax.swing.JButton jButton1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JButton jbutrun;
    private javax.swing.JLabel lblPotSlider;
    private javax.swing.JSlider sldrPot;
    // End of variables declaration//GEN-END:variables
}