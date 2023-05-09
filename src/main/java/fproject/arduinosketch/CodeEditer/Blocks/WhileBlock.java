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
public class WhileBlock extends IfBlock{
    /**
     * Constructor:
     * @param x x pos
     * @param y y pos
     * @param isToolbarBlock if is the hard coded toolbar blocks.
     */
    public WhileBlock(int x, int y, boolean isToolbarBlock){
        super("while", x, y, isToolbarBlock, Color.MAGENTA, 1);
    }
    /**
     * Executes the runnable code, and calls the inner's runnable code.
     */
    @Override
    public void run(){
        try{
            if(inputs[0].inner != null){
                while(inputs[0].inner.getBool()){
                    //Error: This seems not to loop but fix set block error first to be sure
                    child.run();
                }
            }else{
                while(inputs[0].getBool()){
                    child.run();
                }
            }

            super.run();
        }catch(IncorrectOperandException e){
            
        }
        
    }
    /**
     * Duplicates the toolbar block into the editor panel
     * @param container The list to add the copy to.
     * @return The duplicate.
     */
    @Override
    public Block spawn(BlockContainer container){
        Block temp1 = new WhileBlock(x, y, false);
        container.blocks.add(temp1);
        return temp1;
    }
    /**
     * Paints the three rectangles needed to create the while block
     * @param g Java Graphics instance from the JPanel.
     */
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g); 
        g.setColor(Color.MAGENTA);
        g.fillRect(x, y, h, currentHeight);
        g.fillRect(x, y + currentHeight, w, h);
        g.setColor(Color.WHITE);
        g.drawString(text, x + 2, y + h/2 + 4);
        
    }
}
