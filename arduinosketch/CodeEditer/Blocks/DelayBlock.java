/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fproject.arduinosketch.CodeEditer.Blocks;

import fproject.arduinosketch.CodeEditer.BlockContainer;
import fproject.arduinosketch.circuit.Arduino;
import java.awt.Color;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jack
 */
public class DelayBlock extends FunctionBlock{
    public DelayBlock(int x, int y, boolean isToolbarBlock){
        super("delay ", Color.BLUE, x, y, isToolbarBlock);
        inputs = new Input[]{new Input(this, false)};
        super.update();
    }
    /**
     * 
     */
    @Override
    public void run(){
        try{
            Thread.sleep(inputs[0].getInt());
        }
        catch(IncorrectOperandException e){
          
        } catch (InterruptedException ex) {
            Logger.getLogger(DelayBlock.class.getName()).log(Level.SEVERE, null, ex);
        }
        super.run();
    }
    /**
     * Duplicates itself in the list. Deep copy.
     * @param container The list to add the copy to.
     * @return the new copy.
     */
    @Override
    public Block spawn(BlockContainer container){
        Block temp1 = new DelayBlock(x, y, false);
        container.blocks.add(temp1);
        return temp1;
    }
    
}
