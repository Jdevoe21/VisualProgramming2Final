/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fproject.arduinosketch.CodeEditer.Blocks;

import fproject.arduinosketch.CodeEditer.BlockContainer;
import java.awt.Color;
import java.awt.Graphics;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jack
 */
public class Expression extends Variable{
    private Input selectedInput;
    private String value = "  \\/";
   
    
    /**
     * Constructor:
     * @param op blank name spacer
     * @param x x pos
     * @param y y pos
     */
    public Expression(String op, int x, int y){
        super("", x, y, Color.CYAN, 33, 140);
        a = new Input(x + 10, y + h/4, "", Color.WHITE, false);
        operatorSelector = new DropDown(x + 10 + a.getW(), y + h/4, value);
        b = new Input(x + 10 + a.getW() + operatorSelector.getW(), y + h/4, "", Color.WHITE, false);
    }
    
    /**
     * Constructor:
     * @param op blank name spacer
     * @param x x pos
     * @param y y pos
     * @param literal value of expression
     */
    public Expression(String op, int x, int y, Literal literal){
        super("", x, y, Color.CYAN, literal, 33, 140);
        a = new Input(x + 10, y + h/4, "", Color.WHITE, false);
        operatorSelector = new DropDown(x + 10 + a.getW(), y + h/4, value);
        b = new Input(x + 10 + a.getW() + operatorSelector.getW(), y + h/4, "", Color.WHITE, false);
    }
    
    /**
     * Checks the point (mx, my). Tests if it is inside the x, y, w, h bounds.
     * @param mx mouse x pos
     * @param my mouse y pos
     * @return if it is inside
     */
    @Override 
    public boolean wasClicked(int mx, int my){
        clicked = this;
        
        //Input clicking problem is cornered here
        selectedInput = (a.wasClicked(mx, my))? a : (b.wasClicked(mx, my))? b : (!isToolbar && operatorSelector.wasClicked(mx, my))? operatorSelector : null;
        if(selectedInput != null){  
            if(selectedInput.inner != null){
                clicked = selectedInput.inner;
            }
            else{
                clicked = selectedInput;
            }
            return true;
        }
        return mx < x + w && my < y + h && my > y && mx > x;
        
    }
    
    /**
     * Duplicates the toolbar block into the editor panel
     * @param container
     * @return The duplicate.
     */
    @Override
    public Block spawn(BlockContainer container){
        Expression temp = new Expression("", x, y, literal);
        container.expressions.add(temp);
        return temp;
    }
    
    /**
     * Moves this block to the new x and y, recursively moves it's next, with an updated y position.
     * @param nx new x pos
     * @param ny new y pos
     */
    @Override
    public void move(int nx, int ny){
        super.move(nx, ny);
        a.setPos(nx + 10, ny + h/4);
//        b.setPos(nx+w-b.getW()-10, ny + h/4);
        
        operatorSelector.setPos(x + 10 + a.getW(), y + h/4);
        b.setPos(x + 10 + a.getW() + operatorSelector.getW(), y + h/4);
    }
    
    /**
     * Adds a variable to an input.
     * @param var The variable input to snap to
     * @return If the variable snapped to this.
     */
    @Override
    public boolean snapVariable(Variable var){
        return a.snap(var) || b.snap(var);
    }
    
    /**
     * Disconnects this block and deletes itself from a list, recursively deletes children and next blocks.
     * @param container 
     */
    @Override
    public void remove(BlockContainer container){
        System.out.println("Expression-Remove");
        this.disconnect();
        container.expressions.remove(this);
        a.remove(container);
        b.remove(container);
        operatorSelector.remove(container);
    }
    
    /**
     * Paints a rectangle using Java.Graphics.
     * @param g Java Graphics instance from the JPanel.
     */
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        a.paintComponent(g);
        b.paintComponent(g);
        operatorSelector.paintComponent(g);
    }
    
    /**
     * Getter:
     * @return value of literal, false if there is no literal
     * @throws fproject.arduinosketch.CodeEditer.Blocks.IncorrectOperandException
     */
    @Override
    public boolean getBool() throws IncorrectOperandException{
        if(null == operatorSelector.value){
            return false;
        }else return switch (operatorSelector.value) {
            case "&&" -> a.getBool() && b.getBool();
            case "||" -> a.getBool() || b.getBool();
            case "==" -> a.getBool() == b.getBool();
            case "<" -> a.getInt() < b.getInt();
            case ">" -> a.getInt() > b.getInt();
            case "<=" -> a.getInt() <= b.getInt();
            case ">=" -> a.getInt() >= b.getInt();
            default -> false;
        }; //Error: these 4 need to work for integers as well
         
    }
    /**
     * Getter:
     * @return value of literal, 0 if there is no literal
     * @throws fproject.arduinosketch.CodeEditer.Blocks.IncorrectOperandException
     */
    @Override
    public int getInt() throws IncorrectOperandException{
        if(null == operatorSelector.value){
            return 0;
        }else switch (operatorSelector.value) {
            case "+" -> {
                //Error: this needs to work for Strings as well
                return a.getInt() + b.getInt();
            }
            case "-" -> {
                return a.getInt() - b.getInt();
            }
            case "*" -> {
                return a.getInt() * b.getInt();
            }
            case "/" -> {
                return a.getInt() / b.getInt();
            }
            case "%" -> {
                return a.getInt() % b.getInt();
            }
            case "=" -> {
                if(a.inner != null){
                    a.inner.literal.value = b.getInt();
                    return a.inner.getInt();
                }else{
                    throw new IncorrectOperandException("ERROR");
                }
            }
            default -> {
                throw new IncorrectOperandException("ERROR");
            }
        }
    }
    @Override
    public float getFloat() throws IncorrectOperandException{
        if(null == operatorSelector.value){
            return 0.0f;
        }else switch (operatorSelector.value) {
            case "+" -> {
                //Error: this needs to work for Strings as well
                return a.getFloat() + b.getFloat();
            }
            case "-" -> {
                return a.getFloat() - b.getFloat();
            }
            case "*" -> {
                return a.getFloat() * b.getFloat();
            }
            case "/" -> {
                return a.getFloat() / b.getFloat();
            }
            case "%" -> {
                return a.getFloat() % b.getFloat();
            }
            case "=" -> {
                if(a.inner != null){
                    a.inner.literal.value = b.getFloat();
                    return a.inner.getFloat();
                }else{
                    throw new IncorrectOperandException("ERROR");
                }
            }
            default -> {
                throw new IncorrectOperandException("ERROR");
            }
        }
    }
    /**
     * Getter:
     * @return value of literal
     */
    @Override
    public Object getObject(){
        if("+".equals(operatorSelector.value) || "-".equals(operatorSelector.value) || "*".equals(operatorSelector.value) || "/".equals(operatorSelector.value) || "%".equals(operatorSelector.value) || "=".equals(operatorSelector.value)){
            try {
                return getInt();
            } catch (IncorrectOperandException ex) {
                Logger.getLogger(Expression.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            try {
                return getBool();
            } catch (IncorrectOperandException ex) {
                Logger.getLogger(Expression.class.getName()).log(Level.SEVERE, null, ex);
            }
        }  
        return null;
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
