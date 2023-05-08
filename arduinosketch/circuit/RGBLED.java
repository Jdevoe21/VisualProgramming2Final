/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fproject.arduinosketch.circuit;

import java.awt.Color;
import java.util.ArrayList;

/**
 *
 * @author Jack
 */
public class RGBLED extends Component{
    
    public RGBLED(int x, int y, boolean isTool){
        super(x, y, 60, 60, 4, new Color(220, 220, 220, 70), isTool);
        pins.add(new MalePin(x, y + h, 0, 0, gridSize, true, this));
        pins.add(new MalePin(x, y + h, 1, 0, gridSize, true, this));
        pins.add(new MalePin(x, y + h, 2, 0, gridSize, true, this));
        pins.add(new MalePin(x, y + h, 3, 0, gridSize, false, this));
    }
    @Override
    public void update(){
        int red = (int)(46.8f * pins.get(0).getVoltage()) + 20;
        int green = (int)(46.8f * pins.get(1).getVoltage()) + 20;
        int blue = (int)(46.8f * pins.get(2).getVoltage()) + 20;
        red = (red > 255)? 255: (red < 0)? 0:red;
        green = (green > 255)? 255: (green < 0)? 0:green;
        blue = (blue > 255)? 255: (blue < 0)? 0:blue;
        this.color = new Color(red, green, blue);
    }
    @Override
    public Component spawn(ArrayList<Component> comps){
        RGBLED newLED = this;
        if(isTool){
            newLED = new RGBLED(x, y, false);
            comps.add(newLED);
            goHome();
        }
        return newLED;
    }
}
