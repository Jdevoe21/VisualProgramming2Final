/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fproject.arduinosketch.circuit;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author Jack
 */
public class MalePin extends Pin{
    private boolean isInput;
    private Runnable fx;
    
    public MalePin(int x, int y, boolean isOut){
        super(x, y);
        this.isInput = isOut;
    }
    public MalePin(int x, int y, int rx, int ry, int gridSize, boolean isOut){
        super(x + (rx * gridSize), y + (ry * gridSize), rx, ry);
        this.isInput = isOut;
    }
    public MalePin(int x, int y, boolean isOut, Component comp){
        super(x, y);
        this.isInput = isOut;
        this.comp = comp;
    }
    public MalePin(int x, int y, int rx, int ry, int gridSize, boolean isOut, Component comp){
        super(x + (rx * gridSize), y + (ry * gridSize), rx, ry);
        this.isInput = isOut;
        this.comp = comp;
    }
    @Override
    public void paint(Graphics g){
        g.setColor(Color.BLACK);
        g.fillRect(x+3, y, w-6, h-6);
        g.setColor(Color.WHITE);
        g.fillRect(x+5, y, w-10, h-3);
    }
    @Override
    public void setVoltage(float volt){
        super.setVoltage(volt);
        if(comp != null){
            comp.update();
        }
    }
    public void move(int x, int y, int gridSize){
        setPos(x + (relativeX * gridSize), y + (relativeY * gridSize));
    }
    public boolean isInput() {
        return isInput;
    }
}
