/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package fproject.arduinosketch.CodeEditer.Blocks;

import fproject.arduinosketch.CodeEditer.BlockContainer;
import java.awt.Color;
import java.awt.Graphics;

//Expression errors: 3, getInt(), getBool(), getClicked()
//IfElseBlock errors: 5, run(), shouldsnap(), getAllHeight(), move(), update() 

/**
 *
 * @author Jack
 */
public class Block implements Runnable{
    
    protected String text;
    public String blockType = "block"; 
    protected Runnable fx;
    protected int x, y, w, h;
    protected int ogX, ogY;
    protected Color color;
    protected boolean isClicked, isDraggable;
    protected Block next;
    protected Block prev;
    protected Block child;
    protected Block parent;
    protected Block temp;
    protected Block clicked;
    protected int currentHeight;
    protected boolean isToolbar;
    protected boolean inputSelected;
    
    
    /**
     * Constructor:
     * @param c Color
     * @param x x pos
     * @param y y pos
     * @param w width
     * @param h height
     * @param isToolbarBlock if is the hard coded toolbar blocks.
     */
    public Block(Color c, int x, int y, int w, int h, boolean isToolbarBlock){
        this.color = c;
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.ogX = x;
        this.ogY = y;
        this.isToolbar = isToolbarBlock;
        text = "";
        fx = null;
        isClicked = false;
        isDraggable = true;
    }
    /**
     * Checks the point (mx, my). Tests if it is inside the x, y, w, h bounds.
     * @param mx mouse x pos
     * @param my mouse y pos
     * @return If it is inside.
     */
    public boolean wasClicked(int mx, int my){
        this.isClicked = false;
        if(mx < x + w && my < y + h && my > y && mx > x){
            isClicked = true;
            return true;
        }
        else{
            isClicked = false;
            return false;
        }
    }
    /**
     * Executes the runnable code, and calls the next block recursively.
     */
    @Override
    public void run(){
        if(next != null){
            next.run();
        }
    };
    /**
     * Key pressed callback function.
     * @param key the key pressed.
     */
    public void handleKeyTyped(char key){
        
    }
    /**
     * Returns the block to its original position.
     */
    public void goHome(){
        move(ogX, ogY);
    }
    /**
     * Duplicates the toolbar block into the editor panel
     * @param container
     * @return The duplicate.
     */
    public Block spawn(BlockContainer container){
        return this;
    }
    
    /**
     * Updates the size and shape of a block.
     */
    public int update(){
        return 0;
    }
    /**
     * Moves this block to the new x and y, recursively moves it's next, with an updated y position.
     * @param nx new x position.
     * @param ny new y position.
     */
    public void move(int nx, int ny){
        this.setPos(nx, ny);
        if(this.next != null){
            this.next.move(nx, ny + h);
        }
    }
    
    /**
     * Checks if the selected block can snap with @param block
     * @param block is the object being snapped to
     * @return true if it is in range return false if it is not
     */
    public boolean shouldSnap(Block block){
        if(block.x > this.x - 10 && block.x < this.x + this.w){
            if(block.y > this.y + this.h - 10 && block.y < this.y + this.h + 10){
                //System.out.println("Block- Should Snapped");
                //block.disconnect();
                this.snap(block);
                return true;
            }
        }
        return false;
    }
    /**
     * Joins the block next with this.next, clears previous connections.
     * @param next The block to snap to.
     */
    public void snap(Block next){
        if(this.next != null){
            this.next.prev = null;
        }
        this.next = next;
        if (this.next.prev != null){
            this.next.prev.next = null;
        }
        this.next.prev = this;
        this.next.move(this.x, this.y + this.h);
        if(this.parent != null){
            this.parent.update();
        }
    }
    /**
     * Adds a variable to an input.
     * @param var The variable input to snap to
     * @return If the variable snapped to this.
     */
    public boolean snapVariable(Variable var){
        return false;
    }
    
    /**
     * Relieves the selected block of its previous or parent
     * Clears connections.
     */
    public void disconnect(){
        if(this.prev != null){
            this.prev.next = null;
            this.prev = null;
        }
        if(this.parent != null){
            this.parent.child = null;
            this.parent.update();
            this.parent = null;
        }
    }
    
    /**
     * Disconnects this block and deletes itself from a list, recursively deletes children and next blocks.
     * @param container
     */
    public void remove(BlockContainer container){
        System.out.println("Block - Remove");
        this.disconnect();
        container.blocks.remove(this);
        if(child != null){
            child.remove(container);
        }
        if(next != null){
            next.remove(container);
        }
    }
    /**
     * Reset click call back function.
     */
    public void resetClick(){
        
    }
    
    
    /**
     * Paints a rectangle using Java.Graphics.
     * @param g Java Graphics instance from the JPanel.
     */
    public void paintComponent(Graphics g){
        g.setColor(color);
        g.fillRect(x, y, w, h);
    }
    
    /**
     * Prints all pointers for this block.
     */
    public void debug(){
        System.out.print(prev);
        System.out.print(" : ");
        System.out.print(next);
        System.out.print(" | ");
        System.out.print(parent);
        System.out.print(" : ");
        System.out.println(child);
        System.out.println();
    }
    
    
    /**
     * Setter: isToolbar
     * @param isToolbarBlock new value
     */
    public void setIsToolbar(boolean isToolbarBlock) {
        this.isToolbar = isToolbarBlock;
    }
    /**
     * Setter: isClicked
     * @param isClicked 
     */
    public void setIsClicked(boolean isClicked) {
        this.isClicked = isClicked;
    }
    /**
     * Setter: x, y
     * @param x
     * @param y 
     */
    public void setPos(int x,int y){
           this.x = x;
           this.y = y;    
    }
    /**
     * Setter: fx
     * @param fx Lambda to run on the run() method.
     */
    public void setFx(Runnable fx){
        this.fx = fx;
    }
    /**
     * Setter: text
     * @param text 
     */
    public void setText(String text) {
        this.text = text;
    }
    
    /**
     * Getter: x
     * @return x position
     */
    public int getX() {
        return x;
    }
    /**
     * Getter: y
     * @return y position
     */
    public int getY() {
        return y;
    }
    
    /**
     * Getter: w
     * @return Width
     */
    public int getW() {
        return w;
    }
    
    /**
     * Getter: h
     * @return the height, h.
     */
    public int getHeight(){
        return h;
    }
    /**
     * Get the height of yourself and all next blocks recursively.
     * @param total recursive parameter for total height collect.
     * @param nested If the block is a child of another block, not next.
     * @return The total height of this and all next blocks.
     */
    public int getAllHeight(int total, boolean nested){
        total += h;
        if(next == null){
            return total;
        }
        else{
            return next.getAllHeight(total, false);
        }
    }
    /**
     * Getter: Width of input call back function.
     * @param place the number of input.
     * @return 0
     */
    public int getInputsWidth(int place){
        return 0;
    }
    /**
     * Gets the clicked block, input, or variable.
     * @return the clicked block.
     */
    public Block getClicked(){
        if(clicked != null){
            return clicked;
        }
        return this;
    }
    
    /**
     * Getter: text
     * @return String text
     */
    public String getText() {
        return text;
    }
    
    /**
     * Getter: isToolbar
     * @return true if it is a toolbar block.
     */
    public boolean isToolbar() {
        return isToolbar;
    }
    
    
}
