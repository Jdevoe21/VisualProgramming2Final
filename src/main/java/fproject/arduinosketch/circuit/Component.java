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
public class Component extends Sprite{
    protected int gridSize;
    protected float voltage;
    public boolean isTool;
    protected int homeX, homeY;
    public ArrayList<MalePin> pins;
    
    public Component(int x, int y, int w, int h, int numPinCols, Color c, boolean isTool){
        super(x, y, w, h, c);
        this.homeX = x;
        this.homeY = y;
        gridSize = w / numPinCols;
        pins = new ArrayList<>();
        this.isTool = isTool;
    }
    
    /**
     * Checks the point (mx, my). Tests if it is inside the x, y, w, h bounds.
     * @param mx mouse x pos
     * @param my mouse y pos
     * @return If it is inside.
     */
    public boolean checkMouse(int mx, int my){
        isClicked = this.isColliding(mx, my);
        return isClicked;
    }
    public void reset(){
        for(Pin p : pins){
            p.deActivate();
        }
    }
    public Pin getClickedPin(int mx, int my){
        for(Pin p : pins){
            //System.out.println(p.getX() + ", " + p.getY());
            if(p.checkMouse(mx, my)){
                p.activate();
                return p;
            }
        }
        return null;
    }
    
    public void update(){

    }
    /**
     * Moves this light to the new x and y.
     * @param nx new x position.
     * @param ny new y position.
     */
    public void move(int nx, int ny){
        this.setPos(nx, ny);
        for(MalePin mp : pins){
            mp.move(nx, ny + h, gridSize);
        }
//        pins.get(0).setPos(nx, ny + h);
//        pins.get(1).setPos(nx + (w/2), ny + h);
    }
    
    /**
     * Paints each LED Light
     * @param g Java Graphics instance from the JPanel.
     */
    @Override
    public void paint(Graphics g){
        super.paint(g);
        ArrayList<Pin.Wire> wires = new ArrayList<>();
        for(Pin p: pins){
            p.paint(g, wires);
        }
        for(Pin.Wire wire:wires){
            wire.paint(g);
        }
    }
    /**
     * Setter:
     * @param x x pos
     * @param y y pos
     */
    public void setPos(int x,int y){
           this.x = x;
           this.y = y;    
    }
    
    public ArrayList<MalePin> getPins(){
        return pins;
    }
    public String readData(){
        return "Component Pass";
    }
    public int getPinX(){
        return pins.get(0).getX();
    }
    public int getPinY(){
        return pins.get(0).getY();
    }
    public Component spawn(ArrayList<Component> comps){
        return this;
    }
    public void goHome(){
        move(homeX, homeY);
    }
}
