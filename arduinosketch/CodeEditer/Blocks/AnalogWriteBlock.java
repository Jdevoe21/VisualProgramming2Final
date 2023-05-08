/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fproject.arduinosketch.CodeEditer.Blocks;

import fproject.arduinosketch.CodeEditer.BlockContainer;
import fproject.arduinosketch.MainWindow;
import fproject.arduinosketch.circuit.Arduino;
import java.awt.Color;

/**
 *
 * @author Jack
 */
public class AnalogWriteBlock extends FunctionBlock{
       
    public AnalogWriteBlock(int x, int y, boolean isToolbarBlock){
        super("analogWrite", Color.BLUE, x, y, isToolbarBlock);
        inputs = new Input[]{new Input(this, false), new Input(this, false)};
        super.update();
    }
    /**
     * 
     */
    @Override
    public void run(){
        float f;
        String pin;
        try{
            if(inputs[0].inner != null){
                pin = String.valueOf(inputs[0].inner.getObject());                
            }
            else{
                pin = inputs[0].getValue();
            }
            if(inputs[1].inner != null){
                f = inputs[1].inner.getFloat();          
            }
            else{
                f = inputs[1].getFloat();
            }
            Arduino.getInstance().digitalWrite(pin, f);
        }
        catch(IncorrectOperandException e){
          
        }
        MainWindow.getInstance().repaint();
        super.run();
        
    }
    /**
     * Duplicates itself in the list. Deep copy.
     * @param container The list to add the copy to.
     * @return the new copy.
     */
    @Override
    public Block spawn(BlockContainer container){
        Block temp1 = new AnalogWriteBlock(x, y, false);
        container.blocks.add(temp1);
        return temp1;
    }
    
}
