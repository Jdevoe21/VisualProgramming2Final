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
public class ComponentCollection extends Sprite{
    ArrayList<Component> comps;
    public ComponentCollection(int x, int y){
        super(x, y, 300, 300, new Color(60, 60, 60));
        comps = new ArrayList<>();
        comps.add(new LED(x, y, true));
        comps.add(new RGBLED(x, y + 60, true));
        comps.add(new GPS(x, y + 140, true));
        comps.add(new Resistor(x, y + 210, true));
        comps.add(new Pot(x, y + 240, true));     
    }
    public void resetClick(){
        for(Component comp : comps){
            comp.reset();
        }
    }
    public Component getClicked(int mx, int my){
        for(Component comp : comps){
            if(comp.checkMouse(mx, my)){
                return comp;
            }
        }
        return null;
    }
    public Pin getClickedPin(int mx, int my){
        Pin temp;
        for(Component comp : comps){
            temp = comp.getClickedPin(mx, my);
            if(temp != null){
                return temp;
            }
        }
        return null;
    }
    @Override
    public void paint(Graphics g){
        for(Component comp : comps){
            comp.paint(g);
        }
    }
}
