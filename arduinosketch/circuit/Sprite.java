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
public class Sprite {
    protected int x, y, w ,h;
    protected Color color;
    public boolean isClicked;
    
    
    /**
     * Constructor:
     * @param x x pos
     * @param y y pos
     * @param w width
     * @param h height
     * @param color color
     */
    public Sprite(int x, int y, int w, int h, Color color){
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.color = color;
    }
    /**
     * Checks the point (tx, ty). Tests if it is inside the x, y, w, h bounds.
     * @param tx x pos
     * @param ty y pos
     * @return If it is inside.
     */
    public boolean isColliding(int tx, int ty){
        return tx > this.x && tx < this.x + this.w && ty > this.y && ty < this.y + this.h;
    }
    /**
     * Checks the sprite. Tests if it is inside the x, y, w, h bounds.
     * @param s sprite
     * @return If it is inside.
     */
    public boolean isColliding(Sprite s){
        return s.x > this.x && s.x < this.x + this.w && s.y > this.y && s.y < this.y + this.h;
    }
    /**
     * Paints all sprites.
     * @param g Java Graphics instance from the JPanel.
     */
    public void paint(Graphics g){
        g.setColor(color);
        g.fillRect(x, y, w, h);
    }
    /**
     * Getter:
     * @return x
     */
    public int getX() {
        return x;
    }
    /**
     * Setter:
     * @param x x
     */
    public void setX(int x) {
        this.x = x;
    }
    /**
     * Getter:
     * @return y
     */
    public int getY() {
        return y;
    }
    /**
     * Setter:
     * @param y y
     */
    public void setY(int y) {
        this.y = y;
    }
    /**
     * Getter:
     * @return width
     */
    public int getW() {
        return w;
    }
    /**
     * Setter:
     * @param w width
     */
    public void setW(int w) {
        this.w = w;
    }
    /**
     * Getter:
     * @return height
     */
    public int getH() {
        return h;
    }
    /**
     * Setter:
     * @param h height
     */
    public void setH(int h) {
        this.h = h;
    }
    /**
     * Getter:
     * @return color
     */
    public Color getColor() {
        return color;
    }
    /**
     * Setter:
     * @param color color
     */
    public void setColor(Color color) {
        this.color = color;
    }
    /**
     * Makes the Breadboards pins turn blue(Hovering test) call back function.
     * @param x
     * @param y 
     */
    public void isGlowing(int x, int y){
    }
    
}
