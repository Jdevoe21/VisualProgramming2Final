/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fproject.arduinosketch.CodeEditer.Blocks;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author Jack
 */
public class SetUpBlock extends Block{
    public SetUpBlock(int x, int y){
        super(Color.ORANGE, x, y, 135, 28, false);
        this.text = "setup ";
    }
    @Override
    public boolean wasClicked(int mx, int my){
        return false;
    }
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.setColor(Color.WHITE);
        g.drawString(text, x + 2, y + h/2 + 4);
    }
}
