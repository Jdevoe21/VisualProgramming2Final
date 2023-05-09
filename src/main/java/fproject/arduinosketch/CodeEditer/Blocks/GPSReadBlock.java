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
public class GPSReadBlock extends FunctionBlock{
    
    /**
     * Constructor:
     * @param x x pos
     * @param y y pos
     * @param isToolbarBlock if is the hard coded toolbar blocks.
     */
    public GPSReadBlock(int x, int y, boolean isToolbarBlock){
        super("GPS.read ", Color.BLUE, x, y, isToolbarBlock);
        inputs = new Input[]{new Input(this, false),};
        super.update();
    }
    /**
     * Prints the value inside the input or the inner's text.
     */
    @Override
    public void run(){
        if(inputs[0].inner != null){
            //System.out.println(inputs[0].inner.getLiteral().getValue());
            //System.out.println(inputs[0].inner.getObject());
            Arduino.getSerialMonitor().println(Arduino.getInstance().readGPS(String.valueOf(inputs[0].inner.getObject())));
        }
        else{
            Arduino.getSerialMonitor().println(Arduino.getInstance().readGPS(inputs[0].value));
        }
        MainWindow.getInstance().paintCircuit();
        super.run();
        
    }
    /**
     * Duplicates itself in the list. Deep copy.
     * @param container The list to add the copy to.
     * @return the new copy.
     */
    @Override
    public Block spawn(BlockContainer container){
        Block temp1 = new GPSReadBlock(x, y, false);
        container.blocks.add(temp1);
        return temp1;
    }
}
