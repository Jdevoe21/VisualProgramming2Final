/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fproject.arduinosketch.circuit;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

/**
 *
 * @author Jack
 */
public class LED extends Component{ 
    /**
     * Constructor:
     */
    public LED(int x, int y, boolean isTool){
        super(x, y, 30, 30, 2, new Color(255, 0, 0, 20), isTool);
        pins.add(new MalePin(x, y + h, 0, 0, gridSize, true, this));
        pins.add(new MalePin(x, y + h, 1, 0, gridSize, false));
    }
    @Override
    public void update(){
        int alpha = (int)(46.8f * pins.get(0).getVoltage()) + 20;
        alpha = (alpha > 255)? 255: (alpha < 0)? 0:alpha;
        Color c = new Color(255, 0, 0, alpha);
        color = c;
    }
 
    /**
     * Moves this light to the new x and y.
     * @param nx new x position.
     * @param ny new y position.
     */
    @Override
    public Component spawn(ArrayList<Component> comps){
        LED newLED = this;
        if(isTool){
            newLED = new LED(x, y, false);
            comps.add(newLED);
            goHome();
        }
        return newLED;
    }
}
