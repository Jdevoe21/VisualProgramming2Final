/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fproject.arduinosketch.CodeEditer.Blocks;

import java.awt.Color;

/**
 *
 * @author Jack
 */
public class InputButton extends Input{
    /**
     * Constructor:
     * @param name The name of the input
     * @param parent if the input has a owner
     * @param verticalMode if the inputs will be ordered vertically
     * @param fx lambda function
     */
    public InputButton(String name, Block parent, boolean verticalMode, Runnable fx){
        super(parent, name, Color.CYAN, verticalMode);
        inner = null;
        this.fx = fx;
    }
    /**
     * 
     * @param x x pos
     * @param y y pos
     * @param name The name of the input
     * @param verticalMode if the inputs will be ordered vertically
     * @param fx lambda function
     */
    public InputButton(int x, int y, String name, boolean verticalMode, Runnable fx){
        super(x, y, name, Color.CYAN, verticalMode);
        inner = null;
        this.fx = fx;
    }
    /**
     * 
     * @param name The name of the input
     * @param c Color
     * @param parent if the input has a owner
     * @param verticalMode if the inputs will be ordered vertically
     * @param fx lambda function
     */
    public InputButton(String name, Color c, Block parent, boolean verticalMode, Runnable fx){
        super(parent, name, c, verticalMode);
        inner = null;
        this.fx = fx;
    }
    /**
     * 
     * @param x x pos
     * @param y y pos
     * @param name The name of the input
     * @param c Color
     * @param verticalMode if the inputs will be ordered vertically
     * @param fx lambda function
     */
    public InputButton(int x, int y, String name, Color c, boolean verticalMode, Runnable fx){
        super(x, y, name, c, verticalMode);
        inner = null;
        this.fx = fx;
    }
    /**
     * 
     * @param x x pos
     * @param y y pos
     * @param name The name of the input
     * @param c Color
     * @param verticalMode if the inputs will be ordered vertically
     */
    public InputButton(int x, int y, String name, Color c, boolean verticalMode){
        super(x, y, name, c, verticalMode);
        inner = null;
    }
    
    /**
     * Checks the point (mx, my). Tests if it is inside the x, y, w, h bounds.
     * @param mx mouse x pos
     * @param my mouse y pos
     * @return If it is inside.
     */
    @Override
    public boolean wasClicked(int mx, int my){
        if(super.wasClicked(mx, my)){
            this.fx.run();
            return true;
        }
        return false;
    }
    /**
     * Input buttons should not be typed in.
     * @param c character typed
     */
    @Override
    public void handleKeyTyped(char c){
        
    }
    /**
     * Input buttons should not snap.
     * @param v variable to snap to
     * @return false
     */
    @Override
    public boolean snap(Variable v){
        return false;
    }
    
    
    
}
