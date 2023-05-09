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

public class ColorChooser extends Sprite{
    private Color current;
    private Node[] colors;
    private int colSize;
    public class Node extends Sprite{
        public Node(int x, int y, Color c){
            super(x, y, 24, 24, c);
        }
        @Override 
        public void paint(Graphics g){
            g.setColor(Color.WHITE);
            g.fillRect(x, y, w, h);
            g.setColor(color);
            g.fillRect(x + 2, y + 2, 20, 20);
        }
    }
    public ColorChooser(Color starting, Color[] possibleColors, int colSize){
        super(30, 550, 100, 50, Color.WHITE);
        this.current = starting; 
        this.colors = new Node[possibleColors.length];
        
        this.colSize = colSize;
        int row = -1, col = 0, tx, ty;
        for(int i = 0; i < possibleColors.length; i++){
            if(col % colSize == 0){
                row++;
                col = 0;
            }
            colors[i] = new Node(x + (col * 24), y + (row * 24), possibleColors[i]);
            col++;
        }
    }
//    public void update(){
//        int row = -1, col = 0, tx, ty;
//        for(Color c : colors){
//            if(col % colSize == 0){
//                row++;
//                col = 0;
//            }
//            tx = x + (col * 24);
//            ty = y + (row * 24);
////            g.setColor(Color.WHITE);
////            g.fillRect(tx, ty, 24, 24);
////            g.setColor(c);
////            g.fillRect(tx + 2, ty + 2, 20, 20);
//            col++;
//        }
//    }
    public void handleClick(int mx, int my){
        for(Node n : colors){
            if(n.isColliding(mx, my)){
                current = n.color;
                return;
            }
        }
    }
    
    @Override public void paint(Graphics g){
        for(Node n : colors){
            n.paint(g);
        }
    }
    
    public Color getCurrent(){
        return current;
    }
}
