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
public class Variable extends Input{
    protected Input a;
    protected Input b;
    protected DropDown operatorSelector;
    public Input owner;
    public Literal literal;
    
    /**
     * Constructor:
     * @param name name of variable
     * @param x x pos
     * @param y y pos
     */
    public Variable(String name, int x, int y){
        super(x, y, name, Color.ORANGE, false);
        this.isToolbar = true;
        this.literal = new Literal();
        this.ogX = x;
        this.ogY = y;
        blockType = "var";
    }
    /**
     * Constructor:
     * @param name name of variable
     * @param x x pos
     * @param y y pos
     * @param c Color
     */
    public Variable(String name, int x, int y, Color c){
        super(x, y, name, c, false);
        this.isToolbar = true;
        this.literal = new Literal();
        this.ogX = x;
        this.ogY = y;
        blockType = "var";
    }
    /**
     * 
     * @param name name of variable
     * @param x x pos
     * @param y y pos
     * @param c Color
     * @param circleDiam the diameter of the two ovals painted on each side to make a pill shape
     * @param rectWidth width of the block
     */
    public Variable(String name, int x, int y, Color c, int circleDiam, int rectWidth){
        super(x, y, name, c, false, circleDiam, rectWidth);
        this.isToolbar = true;
        this.literal = new Literal();
        this.ogX = x;
        this.ogY = y;
        blockType = "var";
    }
    /**
     * Constructor:
     * @param name name of variable
     * @param x x pos
     * @param y y pos
     * @param c Color
     * @param literal value of variable
     */
    public Variable(String name, int x, int y, Color c, Literal literal){
        super(x, y, name, c, false);
        this.isToolbar = false;
        this.literal = literal;
        blockType = "var";
    }
    /**
     * 
     * @param name name of variable
     * @param x x pos
     * @param y y pos
     * @param c Color
     * @param literal value of variable
     * @param circleDiam the diameter of the two ovals painted on each side to make a pill shape
     * @param rectWidth width of the block
     */
    public Variable(String name, int x, int y, Color c, Literal literal, int circleDiam, int rectWidth){
        super(x, y, name, c, false, circleDiam, rectWidth);
        this.isToolbar = false;
        this.literal = literal;
        blockType = "var";
    }
    /**
     * Returns the variable to its original position.
     */
    @Override
    public void goHome(){
        move(ogX, ogY);
    }
    /**
     * Duplicates the toolbar variable into the editor panel
     * @param vars The list to add the copy to.
     * @return the variable being copied.
     */
    @Override
    public Block spawn(BlockContainer container){
        Variable temp = new Variable(value, x, y, color, literal);
        container.vars.add(temp);
        goHome();
        return temp;
    }
    /**
     * Updates input with mouse position.
     * @param nx mouse x pos
     * @param ny mouse y pos
     */
    @Override
    public void move(int nx, int ny){
        this.x = nx;
        this.y = ny;
    }
    /**
     * Disconnects variable from its owner input
     */
    @Override
    public void disconnect(){
        if(owner != null){
            owner.inner = null;
            owner = null;
        }
    }
    /**
     * Clears itself and removes it from the list
     * @param vars the variable being removed.
     */
    @Override
    public void remove(BlockContainer container){
        System.out.println("Variable-Remove");
        this.disconnect();
        container.vars.remove(this);
    }
    
    /**
     * Getter:
     * @return x
     */
    @Override
    public int getX() {
        return x;
    }
    
    
    /**
     * Getter:
     * @return y
     */
    @Override
    public int getY() {
        return y;
    }
    /**
     * Getter:
     * @return isToolBar
     */
    @Override
    public boolean isToolbar(){
        return isToolbar;
    }
    
    
    /**
     * Getter:
     * @return value of literal, false if there is no literal
     * @throws fproject.arduinosketch.CodeEditer.Blocks.IncorrectOperandException
     */
    @Override
    public boolean getBool() throws IncorrectOperandException{
        try{
            return (boolean)literal.value;
        }
        catch(Exception e){
            return false;
        }
    }
    /**
     * Getter:
     * @return value of literal, 0 if there is no literal
     * @throws fproject.arduinosketch.CodeEditer.Blocks.IncorrectOperandException
     */
    @Override
    public int getInt() throws IncorrectOperandException{
        try{
            return (int)literal.value;
        }
        catch(Exception e){
            return 0;
        }
    }
    @Override
    public float getFloat() throws IncorrectOperandException{
        try{
            return (Float)literal.value;
        }
        catch(Exception e){
            return 0.0f;
        }
    }
    /**
     * Getter:
     * @return value of literal
     */
    public Object getObject(){
        return literal.value;
    }
}
