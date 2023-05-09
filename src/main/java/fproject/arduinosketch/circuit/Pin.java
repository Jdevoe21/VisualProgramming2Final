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
public class Pin extends Sprite{
    public static class Wire{
        private int x1, y1, x2, y2;
        private Color color;
        
        public Wire(int x1, int y1, int x2, int y2, Color color){
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
            this.color = color;
        }
        public void paint(Graphics g){
            g.setColor(color);
            g.drawLine(x1, y1, x2, y2);
        }
    }
    public static int startWidth = 15, startHeight = 15;
    private boolean isBreadBoard;
    private int row, col;
    public Pin in, out;
    public float voltage;
    int relativeX, relativeY;
    protected Component comp;
    String name;
    String side;
    Color wireColor;
    /**
     * Constructor
     * @param x x pos
     * @param y y pos
     */
    public Pin(int x, int y){
        super(x, y, startWidth, startHeight, Color.GRAY);
        isClicked = false;
        this.name = "";
        this.isBreadBoard = false;
        this.voltage = 0.0f;
    }

    public Pin(int x, int y, int col){
        this(x, y);
        this.col = col;
        this.isBreadBoard = true;
    }
    public Pin(int x, int y, int relativeX, int relativeY){
        this(x, y);
        this.relativeX = relativeX;
        this.relativeY = relativeY;
    }

    public Pin(int x, int y, String name, String side){
        super(x, y, startWidth, startHeight, Color.GRAY);
        isClicked = false;
        this.name = name;
        this.side = side;
        this.isBreadBoard = false;
        this.voltage = 0.0f;
    }
    public void setVoltage(float volt){
        this.voltage = volt;
        activate();
        
        if(this.out != null){
            if(this.out.isBreadBoard){
                BreadBoard.getInstance().setColVoltage(this.out.col, volt);
            }
            else{
                this.out.setVoltage(volt);
            }    
        }
        
        
    }
    /**
     * Resets all pins to not be selected when the user clicks.
     */
    public void resetMouse(){
        isClicked = false;
        this.deActivate();
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
    /**
     * Changes color of pin to blue.
     */
    public void activate(){
        this.setColor(Color.BLUE);
    }
    /**
     * Changes color of pin to gray.
     */
    public void deActivate(){
        this.setColor(Color.GRAY);
    }
    /**
     * Paints each pin
     * @param g Java Graphics instance from the JPanel.
     */
    @Override
    public void paint(Graphics g){
        g.setColor(Color.BLACK);
        g.fillRect(x-1, y-1, w+2, h+2);
        super.paint(g);
        g.setColor(Color.BLACK);
        g.fillOval(x+5, y+5, w-10, h-10);
        if(null != side)switch (side) {
            case "left" -> g.drawString(name, x - 15, y + (h/2) + (h/4));
            case "right" -> g.drawString(name, x + 15, y + (h/2) + (h/4));
            case "up" -> g.drawString(name, x, y-5);
            case "down" -> g.drawString(name, x, y + h*2);
            default -> {
            }
        }
        
    }
    public void paint(Graphics g, ArrayList<Wire> existingWires){
        paint(g);
        if(out != null){
            existingWires.add(new Wire(this.x + (this.w/2), this.y + (this.h/2), out.x + (out.w/2), out.y + (out.h/2), wireColor));
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

    public int getRelativeX() {
        return relativeX;
    }

    public int getRelativeY() {
        return relativeY;
    }
    
    public void disconnect(){
        if(this.out != null){
            this.out.out = null;
            this.out.in = null;
            this.out = null;
        }
        if(this.in != null){
            this.in.out = null;
            this.in.in = null;
            this.in = null;
        }
    }
    
    public void connect(Pin p, Color c){
        this.wireColor = c;
        p.disconnect();
        this.out = p;
        p.in = this;
    }

    public float getVoltage() {
        return voltage;
    }
    public String readData(){
        if(in != null){
            if(in.comp != null){
                return in.comp.readData();
            }
            return "Pin No In";
        }
        return "Pin Pass";
    }
}
