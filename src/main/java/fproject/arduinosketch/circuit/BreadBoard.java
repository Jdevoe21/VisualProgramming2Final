/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fproject.arduinosketch.circuit;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

/**
 *
 * @author Jack
 */
public class BreadBoard extends Sprite{
    private static BreadBoard instance;
    public static BreadBoard getInstance(){
        if(instance == null){
            instance = new BreadBoard();
        }
        return instance;
    }
    
    public ArrayList<ArrayList<Pin>> matrix;
    
    /**
     * Constructor:
     */
    private BreadBoard() {
        super(300, 100, 300, 300, Color.LIGHT_GRAY);
        matrix = new ArrayList<>();
        for(int i = 0; i < 38; i++){
            matrix.add(new ArrayList<>());
            for(int j = 0; j < 26; j++){
                matrix.get(i).add(new Pin(x + (Pin.startWidth * j), y + (Pin.startHeight * i), j));
            }
        }
    }
    public void resetClick(){
        for(ArrayList<Pin> row:matrix){
            for(Pin p: row){
                p.resetMouse();
            }
        }
    }
    public void setColVoltage(int col, float voltage){
        if(col < 0 || col > 26){
            return;
        }
        for(int row = 0; row < matrix.size(); row++){
            matrix.get(row).get(col).setVoltage(voltage); 
        }
    }
    /**
     * Getter:
     * @param x x pos
     * @param y y pos
     * @Return
     */
    public Pin getClicked(int x, int y){
        for(ArrayList<Pin> row:matrix){
            for(Pin p: row){
                if(p.checkMouse(x, y)){
                    p.activate();
                    return p;
                }
            }
        }
        return null;
    }
    /**
     * Paints each input for the breadboard
     * @param g Java Graphics instance from the JPanel.
     */
    @Override
    public void paint(Graphics g){
        ArrayList<Pin.Wire> wires = new ArrayList<>();
        for(ArrayList<Pin> row:matrix){
            for(Pin p: row){
                p.paint(g, wires);
            }
        }
        for(Pin.Wire wire:wires){
            wire.paint(g);
        }
    }
    public void paint(Graphics g, ArrayList<Pin.Wire> wires){
        for(ArrayList<Pin> row:matrix){
            for(Pin p: row){
                p.paint(g, wires);
            }
        }
    }
    public void snap(Component comp){
        int i = 0, j;
        Pin relativePin;
        for(ArrayList<Pin> row:matrix){
            j = 0;
            for(Pin p: row){
                if(p.isColliding(comp.getPinX(), comp.getPinY() + 20)){
                    for(MalePin mp : comp.getPins()){
                        relativePin = matrix.get(i + mp.getRelativeY()).get(j + mp.getRelativeX());
                        if(i + mp.getRelativeY() < matrix.size() && j + mp.getRelativeX() < matrix.get(i).size()){
                            if(mp.isInput()){
                                relativePin.connect(mp, Color.GREEN);
                            }else{
                                mp.connect(relativePin, Color.ORANGE);
                            }
                        }
                    }
                    return;
                }   
                j++;
            }
            i++;
        }
}
    /**
     * Makes the Breadboards pins turn blue(Hovering test)
     * @param x x pos
     * @param y y pos
     * @param comp 
     */

    public void isGlowing(Component comp){
        for(ArrayList<Pin> row:matrix){
            for(Pin p: row){
                p.deActivate();
            }
        }
        int i = 0, j;
        for(ArrayList<Pin> row:matrix){
            j = 0;
            for(Pin p: row){
                if(p.isColliding(comp.getPinX(), comp.getPinY() + 20)){
                    for(MalePin mp : comp.getPins()){
                        if(i + mp.getRelativeY() < matrix.size() && j + mp.getRelativeX() < matrix.get(i).size()){
                            matrix.get(i + mp.getRelativeY()).get(j + mp.getRelativeX()).activate();
                        }
                    }
                    return;
                }   
                j++;
            }
            i++;
        }
    }
}
