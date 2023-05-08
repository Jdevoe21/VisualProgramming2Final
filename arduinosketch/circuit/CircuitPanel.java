/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package fproject.arduinosketch.circuit;

import fproject.arduinosketch.MainWindow;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

/**
 *
 * @author Jack
 */
public class CircuitPanel extends javax.swing.JPanel {
    
    /**
     * Creates new form CircuitPanel
     */
    private Component selected;
    private ComponentCollection comps;
    public ColorChooser wireCC;
    private Pin temp1, temp2;

    public CircuitPanel() {
        comps = new ComponentCollection(700, 0);
        wireCC = new ColorChooser(Color.RED, new Color[]{Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW, Color.CYAN, Color.ORANGE, Color.BLACK}, 7);
        initComponents();
       
        
    }
    /**
     * Paints an instance of a breadboard and a led light
     * @param g Java Graphics instance from the JPanel.
     */
    @Override
    public void paint(Graphics g){
        ArrayList<Pin.Wire> wires = new ArrayList<>();
        Arduino.getInstance().paint(g, wires);
        Arduino.getSerialMonitor().paint(g);
        BreadBoard.getInstance().paint(g, wires);
        wireCC.paint(g);
        comps.paint(g);
       
        for(Pin.Wire w: wires){
            w.paint(g);
        }

    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                formMouseDragged(evt);
            }
        });
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                formMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                formMouseReleased(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents
    /**
     * Callback function for when the mouse is pressed.
     * Checks each circuit part for collision with the mouse position.
     * @param evt The mouse event containing the mouse position.
     */
    private void formMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMousePressed
        Arduino.getInstance().resetClick();
        BreadBoard.getInstance().resetClick();
        comps.resetClick();
        wireCC.handleClick(evt.getX(), evt.getY());
        selected = comps.getClicked(evt.getX(), evt.getY());
        if(temp1 == null){
            if((temp1 = comps.getClickedPin(evt.getX(), evt.getY())) == null){
                if((temp1 = BreadBoard.getInstance().getClicked(evt.getX(), evt.getY())) == null){
                    temp1 = Arduino.getInstance().getClicked(evt.getX(), evt.getY());
                }
            }
        }
        else{
            if((temp2 = comps.getClickedPin(evt.getX(), evt.getY())) == null){
                //selected = comps.getClicked(evt.getX(), evt.getY());
                if((temp2 = BreadBoard.getInstance().getClicked(evt.getX(), evt.getY())) == null){
                    temp2 = Arduino.getInstance().getClicked(evt.getX(), evt.getY());
                }
                //temp2 = BreadBoard.getInstance().getClicked(evt.getX(), evt.getY());
            }
            if(temp1 == temp2){
                if(temp1.out != null || temp1.in != null){
                    temp1.disconnect();
                }
                temp1.deActivate();
                temp1 = null;
                temp2 = null;
            }
        }
        
        if(temp1 != null && temp2 != null){
            temp1.connect(temp2, wireCC.getCurrent());
            if(temp1.out != null){
                temp1.out = null;
            }
            if(temp1.in != null){
                temp1.in = null;
            }
            if(temp2.out != null){
                temp2.out = null;
            }
            if(temp2.in != null){
                temp2.in = null;
            }
            temp1.out = temp2;
            temp2.in = temp1;
            temp1.deActivate();
            temp2.deActivate();
            temp1 = null;
            temp2 = null;
        }
        getParent().repaint();
        //System.out.println(getParent() == MainWindow.getInstance().paintCircuit());
    }//GEN-LAST:event_formMousePressed
    /**
     * Callback function for when the mouse is released.
     * @param evt The mouse event containing the mouse position.
     */
    private void formMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseReleased
        if(selected != null){
            if(comps.isColliding(selected)){
                if(selected.isTool){
                    selected.goHome();   
                }else{
                    comps.comps.remove(selected);
                }
            }
            else{
                if(selected.isTool){
                    selected = selected.spawn(comps.comps);                 
                }
                BreadBoard.getInstance().snap(selected);
            }
        }
            
        selected = null;
        getParent().repaint();
    }//GEN-LAST:event_formMouseReleased
    /**
     * Callback function for when the mouse is dragged.
     * Moves selected parts recursively.
     * @param evt The mouse event containing the mouse position.
     */
    private void formMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseDragged
        if(selected != null){
            selected.move(evt.getX(), evt.getY());
            BreadBoard.getInstance().isGlowing(selected);
        }
        
        getParent().repaint();
    }//GEN-LAST:event_formMouseDragged

    public void debug(){
        if(temp1 != null){
            System.out.print(temp1.name);
            System.out.print(": ");
            System.out.println(temp1.voltage);
//            System.out.print(temp1.out);
//            System.out.print(", ");
//            System.out.println(temp1.in);
        }
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}