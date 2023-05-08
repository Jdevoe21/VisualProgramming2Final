package fproject.arduinosketch.CodeEditer.Blocks;

import fproject.arduinosketch.CodeEditer.BlockContainer;
import java.awt.Color;

/**
 *
 * @author Jack
 */
public class SetBlock extends FunctionBlock{
    /**
     * Constructor:
     * @param x x pos
     * @param y y pos
     * @param isToolbarBlock if is the hard coded toolbar blocks.
     */
    public SetBlock(int x, int y, boolean isToolbarBlock){
        super("set", Color.BLACK, x, y, isToolbarBlock);
        inputs = new Input[]{new Input(this, false), new Input(this, false)};
        super.update();
    }
    /**
     * Executes the runnable code for the inner and sets its value to the type selected.
     */
    @Override
    public void run(){
        if(inputs[1].inner != null){
            if(inputs[0].inner != null){
                    try{
                        inputs[1].inner.literal.value = inputs[0].inner.getInt();
                    }
                    catch(Exception e){
                        try{
                            if(inputs[0].inner.getBool() == true || inputs[0].inner.getBool() == false){
                                inputs[1].inner.literal.value = inputs[0].inner.getBool();  
                            }
                        }
                        catch(Exception e1){
                            inputs[1].inner.literal.value = inputs[0].inner.literal.value;
                        }
                    }
                    
            }else{
                String temp = inputs[0].getValue();
                try{
                    inputs[1].inner.literal.value = Integer.valueOf(temp);
                    //System.out.println("int");
                }
                catch(Exception e){
                    try{
                        inputs[1].inner.literal.value = Float.valueOf(temp);
                        System.out.println("float");
                    }
                    catch(Exception e1){
                         if(temp.equals("true") || temp.equals("false")){
                             System.out.println("bool");
                             inputs[1].inner.literal.value = Boolean.valueOf(temp);
                         }
                         else{
                             System.out.println("string");
                             inputs[1].inner.literal.value = temp;
                         }
                    }
                }
            }   
        }
        super.run();
    }
    /**
     * Duplicates the toolbar block into the editor panel
     * @param container The list to add the copy to.
     * @return the new copy
     */
    @Override
    public Block spawn(BlockContainer container){
        Block temp1 = new SetBlock(x, y, false);
        container.blocks.add(temp1);
        return temp1;
    }
}
