/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fproject.arduinosketch.CodeEditer.Blocks;

import fproject.arduinosketch.CodeEditer.BlockContainer;
import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author Jack
 */
public class Input extends Block{
    protected static int startingCircleDiam = 20;
    protected static int startingRectWidth = 30;
    protected int circleDiam = 20;
    protected int rectWidth = 30;
    protected int maxValueWidth = 7;
    private Block owner;
    public Variable inner;
    protected String value;
    public boolean verticalMode = false;
    
    /**
     * Constructor: 
     * @param parent if the input has a owner
     * @param verticalMode if the inputs will be ordered vertically
     */
    public Input(Block parent, boolean verticalMode){
        super(Color.WHITE, 0, 0, startingCircleDiam+startingRectWidth, startingCircleDiam, false);
        blockType = "input";
        this.owner = parent;
        this.verticalMode = verticalMode;
        this.value = "";
        isClicked = false;
        //update();
    }
    /**
     * Constructor: 
     * @param parent if the input has a owner
     * @param verticalMode if the inputs will be ordered vertically
     * @param circleDiam the diameter of the two ovals painted on each side to make a pill shape
     * @param rectWidth width of the block
     */
    public Input(Block parent, boolean verticalMode, int circleDiam, int rectWidth){
        super(Color.WHITE, 0, 0, circleDiam+rectWidth, circleDiam, false);
        this.circleDiam = circleDiam;
        this.rectWidth = rectWidth;
        blockType = "input";
        this.owner = parent;
        this.verticalMode = verticalMode;
        this.value = "";
        isClicked = false;
        //update();
    }
    /**
     * Constructor: 
     * @param parent if the input has a owner
     * @param value The name of the input
     * @param c color
     * @param verticalMode if the inputs will be ordered vertically
     * @param circleDiam the diameter of the two ovals painted on each side to make a pill shape
     * @param rectWidth width of the block
     */
    public Input(Block parent, String value, Color c, boolean verticalMode, int circleDiam, int rectWidth){
        super(Color.WHITE, 0, 0, circleDiam+rectWidth, circleDiam, false);
        this.circleDiam = circleDiam;
        this.rectWidth = rectWidth;
        this.owner = parent;
        this.verticalMode = verticalMode;
        this.value = value;
        isClicked = false;
        isDraggable = false;
        //update();
        
    }
    /**
     * Constructor: 
     * @param parent if the input has a owner
     * @param value The name of the input
     * @param c color
     * @param verticalMode if the inputs will be ordered vertically
     */
    public Input(Block parent, String value, Color c, boolean verticalMode){
        super(Color.WHITE, 0, 0, startingCircleDiam+startingRectWidth, startingCircleDiam, false);
        this.owner = parent;
        this.verticalMode = verticalMode;
        this.value = value;
        isClicked = false;
        isDraggable = false;
        //update();
    }
    /**
     * Constructor: 
     * @param x x pos
     * @param y y pos
     * @param value The name of the input
     * @param c color
     * @param verticalMode if the inputs will be ordered vertically
     */
    public Input(int x, int y, String value, Color c, boolean verticalMode){
        super(c, x, y, startingCircleDiam+startingRectWidth, startingCircleDiam, false);
        this.owner = null;
        this.value = value;
        this.isClicked = false;
        isDraggable = false;
    }
    /**
     * Constructor: 
     * @param x x pos
     * @param y y pos
     * @param value The name of the input
     * @param c color
     * @param verticalMode if the inputs will be ordered vertically
     * @param circleDiam the diameter of the two ovals painted on each side to make a pill shape
     * @param rectWidth width of the block
     */
    public Input(int x, int y, String value, Color c, boolean verticalMode, int circleDiam, int rectWidth){
        super(c, x, y, circleDiam+rectWidth, circleDiam, false);
        this.circleDiam = circleDiam;
        this.rectWidth = rectWidth;
        this.owner = null;
        this.value = value;
        this.isClicked = false;
        isDraggable = false;
    }
            
    /**
     * Allows characters to be added to the inputs value or name.
     * @param c character typed
     */
    @Override
    public void handleKeyTyped(char c){
        if(c == '\b'){
            if(value.length() > 1){
                value = value.substring(0, value.length() - 1);
            }
            else{
                value = "";
            } 
            return;
        }
        value += c;
    }
    
    /**
     * Updates input with owner position.
     * @return 
     */
    @Override
    public int update(){
        if(owner == null){
            setPos(x, y);
        }
        else{
           owner.update();     
        }
        return w;
    }
    
    /**
     * Updates with owner position
     * @param ox offset x pos
     * @param oy offset y pos
     */
    public void update(int ox, int oy){
        int halfOwner = 0;
        
        if(inner == null){
            this.setCircleDiam(Input.startingCircleDiam);
            this.setRectWidth(Input.startingRectWidth);
        }
        else{
            inner.move(x,y);
            this.setCircleDiam(inner.circleDiam);
            this.setRectWidth(inner.rectWidth);
        }
        if(owner != null){
           halfOwner = (owner.h/2) - (h/2);
        }
        if(verticalMode){
            this.x = ox + 5;
            this.y = oy + halfOwner;
        }
        else{
            this.x = ox + 12;//owner.getInputsWidth(place);
            this.y = oy + halfOwner;
        }
    }
    /**
     * Updates input with mouse position.
     * @param mx mouse x pos
     * @param my mouse y pos
     */
    @Override
    public void move(int mx, int my){
        
    }
    /**
     * snap call back function
     * @param block the block being snapped to
     */
    @Override
    public void snap(Block block){
        
    }
    /**
     * Joins the variable with an input, clears previous connections.
     * @param v the variable being snapped to an input.
     * @return true if the variable snapped, return false if not.
     */
    public boolean snap(Variable v){
        if(v.getX() < x + w && v.getY() < y + h && v.getY() > y && v.getX() > x ){
            v.disconnect();
            this.inner = v;
            v.owner = this;
            update(); 
            return true;
        }
        return false;
    }
    /**
     * Disconnects inner and removes it if it exists.
     */
    @Override
    public void remove(BlockContainer container){
        if(inner != null){
            inner.remove(container);
        }
    }
    /**
     * Draws itself and the text inside if it exists.
     * @param g Java Graphics instance from the JPanel.
     */
    @Override
    public void paintComponent(Graphics g){
        g.setColor(this.color);
     
        g.fillOval(x, y, circleDiam, circleDiam);
        g.fillOval(x + rectWidth, y, circleDiam, circleDiam);
        g.fillRect(x + circleDiam/2, y, rectWidth, circleDiam);
        g.setColor(Color.BLACK);
        
        if(isClicked){
            if(value.length() > maxValueWidth){
                g.drawString(value.substring(value.length() - maxValueWidth, value.length()), x, y+circleDiam/2 + 5);
            }else{
                g.drawString(value, x, y+circleDiam/2 + 5);
            }
        }else{
            if(value.length() > maxValueWidth){
                g.drawString(value.substring(0, maxValueWidth), x, y+circleDiam/2 + 5);
            }else{
                g.drawString(value, x, y+circleDiam/2 + 5);
            }
        }
        
    }
    /**
     * Setter:
     * @param nx new x pos
     * @param ny new y pos
     */
    @Override
    public void setPos(int nx, int ny){
        x = nx;
        y = ny;
        if(inner != null){
            inner.move(x,y);
        }
    }
    /**
     * Setter:
     * @param cd circle diameter
     */
    public void setCircleDiam(int cd){
        circleDiam = cd;
        this.w = circleDiam + rectWidth;
        this.h = circleDiam;
    }
    /**
     * Setter:
     * @param rw rectangle width
     */
    public void setRectWidth(int rw){
        rectWidth = rw;
        this.w = circleDiam + rectWidth;
    }
    /**
     * Setter:
     * @param value string typed
     */
    public void setValue(String value) {
        this.value = value;
    }
    
    /**
     * Getter:
     * @return value.
     */
    public String getValue(){
        return value;
    }
    
    /**
     * Getter:
     * @return true or false depending on the value.
     * @throws fproject.arduinosketch.CodeEditer.Blocks.IncorrectOperandException
     */
    public boolean getBool() throws IncorrectOperandException{
        if(inner != null){
            return inner.getBool();
        }
        return Boolean.valueOf(value);
    }
    /**
     * Getter:
     * @return an integer depending on the value
     * @throws fproject.arduinosketch.CodeEditer.Blocks.IncorrectOperandException
     */
    public int getInt() throws IncorrectOperandException{
        if(inner != null){
            return inner.getInt();
        }
        return Integer.valueOf(value);
    }
    public float getFloat() throws IncorrectOperandException{
        if(inner != null){
            return inner.getFloat();
        }
        return Float.valueOf(value);
    }
    /**
     * Getter:
     * @return height
     */
    public int getH() {
        return h;
    }
    
}
