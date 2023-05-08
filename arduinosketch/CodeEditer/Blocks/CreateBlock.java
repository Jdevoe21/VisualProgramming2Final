/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fproject.arduinosketch.CodeEditer.Blocks;

import fproject.arduinosketch.CodeEditer.BlockPanel;
import java.awt.Color;

/**
 *
 * @author Jack
 */
public class CreateBlock extends FunctionBlock{
    
    /**
     * Constructor:
     * @param x x pos
     * @param y y pos
     * @param isToolbar if is the hard coded toolbar blocks.
     */
    public CreateBlock(int x, int y, boolean isToolbar){
        super("Create", Color.GREEN, x, y, isToolbar);
        inputs = new Input[]{new Input(this, false), new InputButton("Go", this, false, () -> {
            BlockPanel.getToolbar().addVar("Vars", Variable.class, inputs[0].getValue());
        })};
        this.w = 150;
        move(x, y);
        super.update();
    }
    /**
     * Moves all inputs in the list
     * @param nx new x pos
     * @param ny new y pos
     */
    @Override
    public void move(int nx, int ny){
        for(Input input: inputs){
            input.update();
        }
    }
}
