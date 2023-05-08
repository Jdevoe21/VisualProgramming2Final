/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fproject.arduinosketch.CodeEditer.Blocks;

import fproject.arduinosketch.CodeEditer.BlockContainer;
import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author Jack
 */
public class IfElseBlock extends IfBlock{
    /**
     * Constructor:
     * @param x x pos
     * @param y y pos
     * @param isToolbarBlock if is the hard coded toolbar blocks.
     */
    public IfElseBlock(int x, int y, boolean isToolbarBlock){
        super(x, y, isToolbarBlock);
        totalHeight = h*5;
    }
    /**
     * Checks the point (mx, my). Tests if it is inside the x, y, w, h bounds.
     * @param mx mouse x pos
     * @param my mouse y pos
     * @return if it is inside.
     */
    @Override
    public boolean wasClicked(int mx, int my){
        if(super.wasClicked(mx, my)){
            return true;
        }else if(mx < x + h && my < y + (currentHeight) && my > y && mx > x){
            return true;
        }
        else if (mx < x + w && my < y + (currentHeight) + h  && my > y + (currentHeight) && mx > x){
            return true;
        }
        return false;
    }
    /**
     * Duplicates the toolbar block into the editor panel
     * @param container The list to add the copy to.
     * @return The duplicate.
     */
    @Override
    public Block spawn(BlockContainer container){
        Block temp1 = new IfElseBlock(x, y, false);
        container.blocks.add(temp1);
        return temp1;
    }
    
    /**
     * Paints the three rectangles needed to create the if block
     * @param g Java Graphics instance from the JPanel.
     */
    @Override
    public void paintComponent(Graphics g){
        g.setColor(Color.BLACK);
        g.fillRect(x, y + currentHeight, h, currentHeight);
        g.fillRect(x, y + currentHeight*2, w, h);
        super.paintComponent(g);
        g.setColor(Color.WHITE);
        g.drawString("else", x + 2, y + currentHeight + h/2 + 4);
        
    }
    
}
