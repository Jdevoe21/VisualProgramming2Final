/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fproject.arduinosketch.CodeEditer.Blocks;


import fproject.arduinosketch.CodeEditer.BlockContainer;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Jack
 */
public class IfBlock extends FunctionBlock{
    protected int totalHeight;
    protected int minHeight;
    
    /**
     * Constructor:
     * @param x x pos
     * @param y y pos
     * @param isToolbarBlock if is the hard coded toolbar blocks.
     */
    public IfBlock(int x, int y, boolean isToolbarBlock){
        super("if", Color.BLACK, x, y, isToolbarBlock);
        inputs = new Input[]{new Input(this, false)};
        super.update();
        totalHeight = h*3;
        minHeight = h*2;
        currentHeight = minHeight;
    }
    public IfBlock(String name, int x, int y, boolean isToolbarBlock, Color c, int numInputs){
        super(name, c, x, y, isToolbarBlock);
        if(numInputs == 3){
            inputs = new Input[]{new Input(this, false), new Input(this, false), new Input(this, false)};
        }else if(numInputs == 1){
            inputs = new Input[]{new Input(this, false)};
        }
        super.update();
        totalHeight = h*3;
        minHeight = h*2;
        currentHeight = minHeight;
    }
    /**
     * Checks the point (mx, my). Tests if it is inside the x, y, w, h bounds.
     * @param mx mouse x pos
     * @param my mouse y pos
     * @return if it is inside.
     */
    @Override
    public boolean wasClicked(int mx, int my){
        if(super.wasClicked(mx, my)){
            return true;
        }
        else if(mx < x + h && my < y + currentHeight && my > y && mx > x){
            return true;
        }
        else if (mx < x + w && my < y + currentHeight + h  && my > y + currentHeight && mx > x){
            return true;
        }
        return false;
    }
    /**
     * Executes the runnable code, and calls the inner's runnable code.
     */
    @Override
    public void run(){
        if(inputs[0].inner != null){
            try {
                if(inputs[0].inner.getBool()){
                    child.run();
                }
            } catch (IncorrectOperandException ex) {
                Logger.getLogger(IfBlock.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            try {
                if(inputs[0].getBool()){
                    child.run();
                }
            } catch (IncorrectOperandException ex) {
                Logger.getLogger(IfBlock.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        super.run();
        
    }
    /**
     * Duplicates the toolbar block into the editor panel
     * @param container The list to add the copy to.
     * @return The duplicate.
     */
    @Override
    public Block spawn(BlockContainer container){
        Block temp1 = new IfBlock(x, y, false);
        container.blocks.add(temp1);
        return temp1;
    }
    /**
     * Updates the size and shape of the block, its next, and its children recursively.
     * @return 
     */
    @Override
    public int update(){
        if(child == null){
            currentHeight = minHeight;
        }
        else{
            currentHeight = child.getAllHeight(h, false);
        }
        if(parent != null){
            this.parent.update();
        }
        if(next != null){
            this.next.move(x, y + (this.currentHeight + h));
        }
        return super.update();
    }
    /**
     * Moves this block to the new x and y, recursively moves it's next, children, and input, with an updated y position.
     * @param nx new x position.
     * @param ny new y position.
     */
    @Override
    public void move(int nx, int ny){
        if(selectedInput == null){
            this.setPos(nx, ny);
            if(this.next != null){
                this.next.move(nx, ny + (this.currentHeight + h));
            }
            if(this.child != null){
                this.child.move(nx + h, ny + h);
            }
            super.update();
        }
    }
    /**
     * Checks if the selected block can snap as a inner or as a next with @param block
     * @param block is the object being snapped to
     * @return true if it is in range return false if it is not
     */
    @Override
    public boolean shouldSnap(Block block){
        if(block.x > this.x - 10 + this.h && block.x < this.x + this.w){
            if(block.y > this.y + this.h - 10 && block.y < this.y + this.h + 10){
                System.out.println("IfBlock-Should Snap");
                block.disconnect(); // Source of inner random disconnect.
                if(this.child != null){
                    this.child.parent = null;
                }
                this.child = block;
                this.child.parent = this;
                block.move(this.x + h, this.y + h);
                update();
                return true;
            }
        }
        else{
            if(block.x > this.x - 10 && block.x < this.x + this.w){
                if(block.y > this.y + (this.currentHeight + h) - 10 && block.y < this.y + (this.currentHeight + h) + 10){
                    System.out.println("IfBlock-Should Snap2");

                    block.disconnect();
                    this.snap(block);
                    block.move(this.x, this.y + (this.currentHeight + h));
                    return true;
                }
            }
        }
        return false;
    }
    /**
     * Paints the three rectangles needed to create the if block
     * @param g Java Graphics instance from the JPanel.
     */
    @Override
    public void paintComponent(Graphics g){
        g.setColor(Color.BLACK);
        g.fillRect(x, y, h, currentHeight);
        g.fillRect(x, y + currentHeight, w, h);
        super.paintComponent(g);
        
    }
    
    /**
     * Get the height of yourself and all next and children blocks recursively.
     * @param total recursive parameter for total height collect.
     * @param nested If the block is a inner of another block, not next.
     * @return The total height of this and all next blocks.
     */
    @Override
    public int getAllHeight(int total, boolean nested){
        if(child == null){
            total += totalHeight;
        }
        else{
            total += child.getAllHeight(minHeight, true);
        }  
        if(next == null){
            return total;
        }
        else{
            return next.getAllHeight(total, false);
        }
    }
    
    
    
}
