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
public class ForBlock extends IfBlock{
    /**
     * Constructor:
     * @param x x pos
     * @param y y pos
     * @param isToolbarBlock if is the hard coded toolbar blocks.
     */
    public ForBlock(int x, int y, boolean isToolbarBlock){
        super("for", x, y, isToolbarBlock, Color.RED, 3);
    }
    /**
     * Executes the runnable code, and calls the inner's runnable code.
     */
    @Override
    public void run(){
        try{
           if(inputs[0].inner != null && inputs[1].inner != null && inputs[2].inner != null){
                if(null != inputs[1].inner.operatorSelector.value)switch (inputs[1].inner.operatorSelector.value) {
                   case "<=" -> {
                       for(inputs[0].inner.a.inner.literal.value = inputs[0].inner.getInt(); inputs[1].inner.a.inner.getInt() <= inputs[1].inner.b.inner.getInt(); inputs[2].inner.a.inner.literal.value = inputs[2].inner.getInt()){
                           child.run();
                       }
                    }
                   case ">=" -> {
                       for(inputs[0].inner.a.inner.literal.value = inputs[0].inner.getInt(); inputs[1].inner.a.inner.getInt() >= inputs[1].inner.b.inner.getInt(); inputs[2].inner.a.inner.literal.value = inputs[2].inner.getInt()){
                           child.run();
                       }
                    }
                   case ">" -> {
                       for(inputs[0].inner.a.inner.literal.value = inputs[0].inner.getInt(); inputs[1].inner.a.inner.getInt() > inputs[1].inner.b.inner.getInt(); inputs[2].inner.a.inner.literal.value = inputs[2].inner.getInt()){
                           child.run();
                       }
                    }
                   case "<" -> {
                       for(inputs[0].inner.a.inner.literal.value = inputs[0].inner.getFloat(); inputs[1].inner.a.inner.getFloat() < inputs[1].inner.b.inner.getFloat(); inputs[2].inner.a.inner.literal.value = inputs[2].inner.getFloat()){
                           child.run();
                       }
                    }
                   default -> {
                    }
               }
            }else{
                if(inputs[0].getBool()){
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
        Block temp1 = new ForBlock(x, y, false);
        container.blocks.add(temp1);
        return temp1;
    }
    /**
     * Paints the three rectangles needed to create the for block
     * @param g Java Graphics instance from the JPanel.
     */
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g); 
        g.setColor(Color.RED);
        g.fillRect(x, y, h, currentHeight);
        g.fillRect(x, y + currentHeight, w, h);
        g.setColor(Color.WHITE);
        g.drawString(text, x + 2, y + h/2 + 4);
        
    }
    
}
