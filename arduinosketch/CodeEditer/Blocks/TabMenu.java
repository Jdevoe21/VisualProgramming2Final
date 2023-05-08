/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fproject.arduinosketch.CodeEditer.Blocks;

import fproject.arduinosketch.CodeEditer.ToolBar;
import java.awt.Color;
import java.util.ArrayList;

/**
 *
 * @author Jack
 */
public class TabMenu extends FunctionBlock{
    private ToolBar toolbar;
    /**
     * Constructor:
     * @param x x pos
     * @param y y pos
     * @param toolbar if is the hard coded toolbar blocks.
     */
    public TabMenu(int x, int y, ToolBar toolbar){
        super("", Color.BLACK, x, y, false);
        this.toolbar = toolbar;
        String[] tabKeys = toolbar.blockTable.keySet().toArray(new String [0]);
        inputs = new Input[tabKeys.length];
        int place = 0;
        for(String key : tabKeys){
            inputs[place] = new InputButton(key, this, true, ()->{
                toolbar.setSelectedKey(key);
            });
            place++;
        }
        super.update();
        this.w = 51;
        this.h = 68;
    }
}
