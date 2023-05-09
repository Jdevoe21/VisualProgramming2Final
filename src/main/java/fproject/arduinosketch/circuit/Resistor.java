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
public class Resistor extends Component{
   
    public Resistor(int x, int y, boolean isTool){
        super(x, y, 15, 30, 1, Color.CYAN, isTool);
        pins.add(new MalePin(x, y, 0, 0, gridSize, true, this));
        pins.add(new MalePin(x, y, 0, 1, gridSize, false));
    }
    @Override
    public void update(){
        pins.get(1).setVoltage(pins.get(0).getVoltage());
    }
    @Override
    public void move(int nx, int ny){
        this.setPos(nx, ny);
        for(MalePin mp : pins){
            mp.move(nx, ny, gridSize);
        }
//        pins.get(0).setPos(nx, ny + h);
//        pins.get(1).setPos(nx + (w/2), ny + h);
    }
    @Override
    public Component spawn(ArrayList<Component> comps){
        Resistor newRes = this;
        if(isTool){
            newRes = new Resistor(x, y, false);
            comps.add(newRes);
            goHome();
        }
        return newRes;
    }
}
