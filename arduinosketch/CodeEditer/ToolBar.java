/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fproject.arduinosketch.CodeEditer;

import fproject.arduinosketch.CodeEditer.Blocks.AnalogWriteBlock;
import fproject.arduinosketch.CodeEditer.Blocks.TabMenu;
import fproject.arduinosketch.CodeEditer.Blocks.Variable;
import fproject.arduinosketch.CodeEditer.Blocks.CreateBlock;
import fproject.arduinosketch.CodeEditer.Blocks.Block;
import fproject.arduinosketch.CodeEditer.Blocks.DelayBlock;
import fproject.arduinosketch.CodeEditer.Blocks.DigitalReadBlock;
import fproject.arduinosketch.CodeEditer.Blocks.DigitalWriteBlock;
import fproject.arduinosketch.CodeEditer.Blocks.Expression;
import fproject.arduinosketch.CodeEditer.Blocks.ForBlock;
import fproject.arduinosketch.CodeEditer.Blocks.GPSReadBlock;
import fproject.arduinosketch.CodeEditer.Blocks.PrintBlock;
import fproject.arduinosketch.CodeEditer.Blocks.SetBlock;
import fproject.arduinosketch.CodeEditer.Blocks.IfBlock;
import fproject.arduinosketch.CodeEditer.Blocks.IfElseBlock;
import fproject.arduinosketch.CodeEditer.Blocks.PinModeBlock;
import fproject.arduinosketch.CodeEditer.Blocks.WhileBlock;
import java.awt.Graphics;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jack
 */
public class ToolBar {
    private String selectedKey;
    public HashMap<String, ArrayList<Block>> blockTable;
    public HashMap<String, ArrayList<Variable>> varTable;
    private TabMenu menu;
    
    /**
     * Constructor.
     */
    public ToolBar(){
        blockTable = new HashMap();
        varTable = new HashMap();
        
     
//        addBlock("Blocks", new PrintBlock(0, 20, true));
//        addBlock("Blocks", new IfBlock(0, 60, true));
//        addBlock("Vars", new SetBlock(0, 60, true));
//        addBlock("Vars", new CreateBlock(0, 20, true));

        addBlock("Blocks", PrintBlock.class);
        addBlock("Blocks", IfBlock.class);
        addBlock("Blocks", IfElseBlock.class);
        addBlock("Vars", CreateBlock.class);
        addBlock("Vars", SetBlock.class);
        addBlock("Blocks", ForBlock.class);
        addBlock("Blocks", WhileBlock.class);
        addBlock("Arduino", PinModeBlock.class);
        addBlock("Arduino", DigitalReadBlock.class);
        addBlock("Arduino", DigitalWriteBlock.class);
        addBlock("Arduino", DelayBlock.class);
        addBlock("Arduino", GPSReadBlock.class);
        addBlock("Arduino", AnalogWriteBlock.class);
        addVar("Vars", Expression.class, "");
        
        
        menu = new TabMenu(165, 0, this);
        selectedKey = "Blocks";
    }
    
    
    /**
     * Adds a variable to the varTable HashMap
     * @param key The hashed var.
     * @param type class the constructor is being grabbed from.
     * @param name name of variable
     */
    public void addVar(String key, Class type, String name){
        try {
            Constructor constructor = type.getConstructor(new Class[]{String.class, Integer.TYPE, Integer.TYPE});
            Object[] params = new Object[]{
                name,
                0,
                getHeight(key)
            };
          
            Variable var = (Variable) constructor.newInstance(params);
           
            if(!varTable.containsKey(key)){
                addVariableList(key, new ArrayList());
            }
            varTable.get(key).add(var);
            
        } catch (NoSuchMethodException ex) {
            Logger.getLogger(ToolBar.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            Logger.getLogger(ToolBar.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(ToolBar.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(ToolBar.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(ToolBar.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvocationTargetException ex) {
            Logger.getLogger(ToolBar.class.getName()).log(Level.SEVERE, null, ex);
        }
        //blockTable.get(key).add();
    }
    /**
     * Adds a variable to the blockTable HashMap
     * @param key The hashed block.
     * @param type class the constructor is being grabbed from.
     */
    public void addBlock(String key, Class type){
        try {
            Constructor constructor = type.getConstructor(new Class[]{Integer.TYPE, Integer.TYPE, Boolean.TYPE});
            Object[] params = new Object[]{
                0,
                getHeight(key),
                true
            };
          
            Block block = (Block) constructor.newInstance(params);
           
            if(!blockTable.containsKey(key)){
                addBlockList(key, new ArrayList());
            }
            blockTable.get(key).add(block);
            
        } catch (NoSuchMethodException ex) {
            Logger.getLogger(ToolBar.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            Logger.getLogger(ToolBar.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(ToolBar.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(ToolBar.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(ToolBar.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvocationTargetException ex) {
            Logger.getLogger(ToolBar.class.getName()).log(Level.SEVERE, null, ex);
        }
        //blockTable.get(key).add();
    }
    /**
     * Checks if the key exists, adds the arraylist to the blocktable at the key.
     * @param key The hashed block to store the list.
     * @param blocks The list to store.
     * @return True if it was Added of false if it was not.
     */
    public boolean addBlockList(String key, ArrayList<Block> blocks){
        if(!blockTable.containsKey(key)){
            blockTable.put(key, blocks);
            return true;
        }
        return false;
    }
    /**
     * Checks if the key exists, adds the Array List to the variable at the key.
     * @param key The hashed var to store the list.
     * @param vars The list to store.
     * @return True if it was Added of false if it was not.
     */
    public boolean addVariableList(String key, ArrayList<Variable> vars){
        if(!varTable.containsKey(key)){
            varTable.put(key, vars);
            return true;
        }
        return false;
    }
    /**
     * Draws all of the blocks and variables inside each HashMap.
     * @param g Java Graphics instance from the JPanel.
     */
    public void paintComponent(Graphics g){
        menu.paintComponent(g);
        if(blockTable.containsKey(selectedKey)){
            for(Block block : blockTable.get(selectedKey)){
                block.paintComponent(g);
            }
        }
        if(varTable.containsKey(selectedKey)){
            for(Variable var : varTable.get(selectedKey)){
                var.paintComponent(g);
            }
        }
        
//        for(String key : selectedVarKeys){
//            for(Variable var: varTable.get(key)){
//                var.paintComponent(g);
//            }
//        }
    }
    /**
     * Setter:
     * @param key key.
     */
    public void setSelectedKey(String key){
        selectedKey = key;
    }
    /**
     * Getter: 
     * @param key The hashed var or hashed block.
     * @return the height of everything already in the toolbar
     */
    public int getHeight(String key){
        int height = 0;
        if(blockTable.containsKey(key)){
            for(Block block : blockTable.get(key)){
                height += block.getAllHeight(0, false);
                height += 10;
            }
        }
        if(varTable.containsKey(key)){
                for(Variable var : varTable.get(key)){
                height += var.getH();
                height += 10;
            }
        }
        return height;
    }
    /**
     * Getter:
     * @param mx mouse x pos
     * @param my mouse y pos
     * @return If it is inside.
     */
    public Block getClickedBlock(int mx, int my){
        if(this.menu.wasClicked(mx, my)){
            return null;
        }
        if(blockTable.containsKey(selectedKey)){
            for(Block block : blockTable.get(selectedKey)){
                if(block.wasClicked(mx, my)){
                    return block.getClicked();
                }
            }
        }
        return null;
    }
    /**
     * Getter:
     * @param mx mouse x pos
     * @param my mouse y pos
     * @return If it is inside.
     */
    public Variable getClickedVar(int mx, int my){
        if(this.menu.wasClicked(mx, my)){
            return null;
        }
        if(varTable.containsKey(selectedKey)){
            for(Variable var : varTable.get(selectedKey)){
                if(var.wasClicked(mx, my)){
                    return var;
                }
            }
        }
        return null;
    }
}
