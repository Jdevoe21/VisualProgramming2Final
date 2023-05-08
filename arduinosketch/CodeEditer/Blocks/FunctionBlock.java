/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fproject.arduinosketch.CodeEditer.Blocks;

import fproject.arduinosketch.CodeEditer.BlockContainer;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

/**
 *
 * @author Jack
 */
public class FunctionBlock extends Block{
    protected Input[] inputs;
    public Input selectedInput;
    
    /**
     * Constructor:
     * @param text name
     * @param c Color
     * @param x x pos
     * @param y y pos
     * @param isToolbarBlock if is the hard coded toolbar blocks.
     */
    public FunctionBlock(String text, Color c, int x, int y, boolean isToolbarBlock){
        super(c, x, y, 135, 28, isToolbarBlock);
        this.text = text;
    }
    
    /**
     * Checks the point (mx, my). Tests if it is inside the x, y, w, h bounds.
     * @param mx mouse x pos
     * @param my mouse y pos
     * @return If it is inside.
     */
    @Override
    public boolean wasClicked(int mx, int my){
        selectedInput = null;
        clicked = this;
        for(Input input: inputs){
            selectedInput = null;
            if(input.wasClicked(mx, my)){
                if(input.inner != null){
                    clicked = input.inner;
                }
                else{
                    clicked = input;
                }
                selectedInput = input;
                
                return true;
            }
        }
        return super.wasClicked(mx, my);
    }
    @Override
    public int update(){
        int ox = text.length() * 5 + x, oy = y;
        for(Input input: inputs){
            input.update(ox,oy);
            if(input.verticalMode){
                oy += input.getH();
            }
            else{
                ox += input.getW();
            }
        }
        int inputsWidth = 0;
        for(Input input: inputs){
            inputsWidth += input.getW();
        }
        this.w = inputsWidth+text.length() * 5 + 20;
        return super.update();
    }
    /**
     * Moves this block to the new x and y along with all its inputs to their updated position.
     * @param nx new x position.
     * @param ny new y position.
     */
    @Override
    public void move(int nx, int ny){
        if(selectedInput == null){
            super.move(nx, ny);
            update();
        }
    }
   
    /**
     * Calls snap for all Inputs
     * @param var the object being snapped
     * @return if an input snapped
     */
    @Override
    public boolean snapVariable(Variable var){
        for(Input input : inputs){
            if(input.snap(var)){
                return true;
            }
        }
        return false;
    }
    
    /**
     * Clears all inputs and removes this from the list
     * @param blocks is the list of blocks
     */
    @Override
    public void remove(BlockContainer container){
        for(Input input : inputs){
            input.remove(container);
        }
        super.remove(container);
    }
    
    /**
     * Draws itself as a block, each input, and its text.
     * @param g Java Graphics instance from the JPanel.
     */
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        for(Input input: inputs){
            input.paintComponent(g);
        }
        g.setColor(Color.WHITE);
        g.drawString(text, x + 2, y + h/2 + 4);
    }
    /**
     * Getter: Width of inputs.
     * @param place the number of input.
     * @return total width
     */
    @Override
    public int getInputsWidth(int place){
        int total = 0;
        for(int i = place-1; i >= 0; i--){
            total += inputs[i].getW();
        }
        return total;
    }
    /**
     * Resets all inputs to not be selected when the user clicks.
     */
    @Override
    public void resetClick(){
        selectedInput = null;
        clicked = this;
    }
}
