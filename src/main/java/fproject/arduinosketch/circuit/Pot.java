/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fproject.arduinosketch.circuit;

import fproject.arduinosketch.MainWindow;
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author Jack
 */
public class Pot extends Component{
    private ChangeListener listener;
    private int value;
     public Pot(int x, int y, boolean isTool){
        super(x, y, 40, 30, 3, Color.GRAY, isTool);
        pins.add(new MalePin(x, y + h, 0, 0, gridSize, true, this));
        pins.add(new MalePin(x, y + h, 1, 0, gridSize, false));
        pins.add(new MalePin(x, y + h, 2, 0, gridSize, false));
        listener = new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                value = MainWindow.getInstance().getPotSliderValue();
            }
        };
    }
    public boolean checkMouse(int mx, int my){
        isClicked = this.isColliding(mx, my);
        if(isClicked){
            MainWindow.getInstance().setPotSliderValue(value);
            MainWindow.getInstance().addPotListener(listener);
            MainWindow.getInstance().showPotControls(true);
            
        }
        
        return isClicked;
    }
    @Override
    public void reset(){
        super.reset();
        MainWindow.getInstance().showPotControls(false);
        MainWindow.getInstance().removePotListener(listener);
    }
    @Override
    public void update(){
        float ratio = value / 100.0f;
        System.out.println(ratio + ", " +pins.get(0).getVoltage() * ratio);
        pins.get(1).setVoltage(pins.get(0).getVoltage() * ratio);
    }
//    @Override
//    public void move(int nx, int ny){
//        this.setPos(nx, ny);
//        for(MalePin mp : pins){
//            mp.move(nx, ny, gridSize);
//        }
//    }
    @Override
    public Component spawn(ArrayList<Component> comps){
        Pot newPot = this;
        if(isTool){
            newPot = new Pot(x, y, false);
            comps.add(newPot);
            goHome();
        }
        return newPot;
    }
}
