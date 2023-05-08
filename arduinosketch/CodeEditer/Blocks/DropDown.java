/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fproject.arduinosketch.CodeEditer.Blocks;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

/**
 *
 * @author Jack
 */
public class DropDown extends InputButton{
    private boolean isShowing;
    private ArrayList<InputButton> options; 
    
    /**
     * Constructor:
     * @param x x pos
     * @param y y pos
     * @param value beginning name
     */
    public DropDown(int x, int y, String value){
        super(x, y, value, Color.WHITE, true);
        this.setFx(() -> {
            isShowing = !isShowing;
            
        });
        options = new ArrayList();
        createlist();
        isShowing = false;
    }
    /**
     * Checks the point (mx, my). Tests if it is inside the x, y, w, h bounds.
     * @param mx mouse x pos
     * @param my mouse y pos
     * @return if it is inside
     */
    @Override
    public boolean wasClicked(int mx, int my){
        if(isShowing){
            for(InputButton temp : options){
                if(temp.wasClicked(mx, my)){
                    return super.wasClicked(mx, my);
                }
            }
        }
        return super.wasClicked(mx, my);
    }
    
    /**
     * Creates the list of options for the drop down.
     */
    public void createlist(){
        options.add(new InputButton(x, y + this.h, "&&", Color.WHITE, true, () -> {
            value = "&&";
            isShowing = false;
        }));
        options.add(new InputButton(x, y + (this.h*2), "||", Color.WHITE, true, () -> {
            value = "||";
            isShowing = false;
        }));
        options.add(new InputButton(x, y + (this.h*3), "==", Color.WHITE, true, () -> {
            value = "==";
            isShowing = false;
        }));
        options.add(new InputButton(x, y + (this.h*4), "<", Color.WHITE, true, () -> {
            value = "<";
            isShowing = false;
        }));
        options.add(new InputButton(x, y + (this.h*5), ">", Color.WHITE, true, () -> {
            value = ">";
            isShowing = false;
        }));
        options.add(new InputButton(x, y + (this.h*6), "<=", Color.WHITE, true, () -> {
            value = "<=";
            isShowing = false;
        }));
        options.add(new InputButton(x, y + (this.h*7), ">=", Color.WHITE, true, () -> {
            value = ">=";
            isShowing = false;
        }));
        options.add(new InputButton(x, y + (this.h*8), "+", Color.WHITE, true, () -> {
            value = "+";
            isShowing = false;
        }));
        options.add(new InputButton(x, y + (this.h*9), "-", Color.WHITE, true, () -> {
            value = "-";
            isShowing = false;
        }));
        options.add(new InputButton(x, y + (this.h*10), "*", Color.WHITE, true, () -> {
            value = "*";
            isShowing = false;
        }));
        options.add(new InputButton(x, y + (this.h*11), "/", Color.WHITE, true, () -> {
            value = "/";
            isShowing = false;
        }));
        options.add(new InputButton(x, y + (this.h*12), "%", Color.WHITE, true, () -> {
            value = "%";
            isShowing = false;
        }));
        options.add(new InputButton(x, y + (this.h*13), "=", Color.WHITE, true, () -> {
            value = "=";
            isShowing = false;
        }));
    }
    /**
     * Paints a rectangle and circles using Java.Graphics.
     * @param g Java Graphics instance from the JPanel.
     */
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        if(isShowing){
            g.setColor(Color.CYAN);
            g.fillRect(x, y+h, w + 10, h*4);
            for(InputButton temp : options){
                temp.paintComponent(g);
            }
        }
        
    }
    /**
     * Setter: x, y
     * @param nx new x pos
     * @param ny new y pos
     */
    @Override 
    public void setPos(int nx, int ny){
        super.setPos(nx, ny);
        int i = 1;
        for(InputButton temp : options){
            temp.setPos(nx, ny + (this.h*i));
            i++;
        }
    }

    
}
