/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fproject.arduinosketch.CodeEditer.Blocks;

import fproject.arduinosketch.CodeEditer.BlockContainer;
import java.awt.Color;

/**
 *
 * @author Jack
 */
public class DigitalReadBlock extends FunctionBlock{
    
     public DigitalReadBlock(int x, int y, boolean isToolbarBlock){
        super("digitalRead", Color.BLUE, x, y, isToolbarBlock);
        inputs = new Input[]{new Input(this, false), new Input(this, false)};
        super.update();
    }
    /**
     * 
     */
    @Override
    public void run(){
      
    }
    /**
     * Duplicates itself in the list. Deep copy.
     * @param container The list to add the copy to.
     * @return the new copy.
     */
    @Override
    public Block spawn(BlockContainer container){
        Block temp1 = new DigitalReadBlock(x, y, false);
        container.blocks.add(temp1);
        return temp1;
    }
}
