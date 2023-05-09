/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fproject.arduinosketch.circuit;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Jack
 */
public class Arduino extends Sprite{
    public static Arduino.Code code;
    
    private static SerialMonitor serialInstance;
    public static SerialMonitor getSerialMonitor(){
        if(serialInstance == null){
            serialInstance = new SerialMonitor();
        }
        return serialInstance;
    }
    public static class Code implements Runnable{
        private boolean shouldStop;
        private Runnable setup, loop;
        public Code(Runnable setup, Runnable loop){
            shouldStop = false;
            this.setup = setup;
            this.loop = loop;
        }
        @Override
        public void run() {
            setup.run();
            while(true){
               if(shouldStop){
                   break;
               }
               loop.run();
            }
        }

        public void setShouldStop(boolean shouldStop) {
            this.shouldStop = shouldStop;
        }
        
    }       
    public static class SerialMonitor extends Sprite{
        private static int maxLines = 10;
        private Color textColor;
        private ArrayList<String> lines;
        private SerialMonitor(){
            super(800, 400, 300, 300, Color.WHITE);
            lines = new ArrayList<>();
            
            textColor = Color.BLACK;
            
        }
        public void println(String s){
//            for(String line : lines){
//                System.out.println(line);
//            }
            if(lines.size() >= maxLines){
                lines.remove(0);
            }
            lines.add(s);
        }
        public void paint(Graphics g){
            super.paint(g);
            g.setColor(textColor);
            int offset = 20;
            for(String line : lines){
                g.drawString(line, x, y + offset);
                offset += 12;
            }
        }
    }
    private static Arduino instance;
    public static Arduino getInstance(){
        if(instance == null){
            instance = new Arduino();
        }
        return instance;
    }
    
    public HashMap<String, Pin> pins;
    
    /**
     * Constructor:
     */
    private Arduino(){
        super(15, 45, 200, 500, Color.GREEN);
//        digitalPin = new ArrayList<>();
//        digitalPin2 = new ArrayList<>();
//        analogPin = new ArrayList<>();
        int k = 0;
        String volt = "";
        String side = "";
        pins = new HashMap<>();
        for(int i = 0; i < 26; i++){
            String key = String.valueOf(i);
            pins.put(key, new Pin(x + this.w, y + (Pin.startHeight * i), key, "left"));   
        }
        for(int i = 0; i < 24; i++){
            String key = "A" + String.valueOf(i);
            pins.put(key, new Pin(x - 15, y + 45 + (Pin.startHeight * i), key, "right"));   
        }
        for(int i = 0; i < 2; i++){
//            pins.add(new ArrayList<>());
            for(int j = 0; j < 12; j++){
                String key = String.valueOf(j + k);
                if(i == 0){
                    k = 26;
                    side = "up";
                }else if(i == 1){
                    k = 38;
                    side = "down";
                }
                pins.put(key, new Pin(x + 15 + (Pin.startWidth * j), y + this.h - 70 + (Pin.startHeight * i), key, side));
            }
        }
        for(int i = 0; i < 4; i++){
            if(i % 2 == 0){
                volt = "GND";
                side = "up";
            }else{
                volt = "5V";
                side = "down";
            }
            pins.put(volt + '_' + String.valueOf(i), new Pin(x + 30 + (Pin.startWidth * i), y, volt, side));
        }
        
    }
    public void resetClick(){
        for(Pin p: pins.values()){
            p.resetMouse();
        }
//        for(Pin p: digitalPin){
//            p.resetMouse();
//        }
//        for(Pin p: analogPin){
//            p.resetMouse();
//        }
//        for(ArrayList<Pin> row:digitalPin2){
//            for(Pin p: row){
//                p.resetMouse();
//            }
//        }
    }
    public Pin getClicked(int x, int y){
        for(Pin p: pins.values()){
            if(p.checkMouse(x, y)){
                p.activate();
                return p;
            }
        }
//        for(Pin p: digitalPin){
//            if(p.checkMouse(x, y)){
//                p.activate();
//                return p;
//            }
//        }
//        for(Pin p: analogPin){
//            if(p.checkMouse(x, y)){
//                p.activate();
//                return p;
//            }
//        }
//
//        for(ArrayList<Pin> row:digitalPin2){
//            for(Pin p: row){
//                if(p.checkMouse(x, y)){
//                    p.activate();
//                    return p;
//                }
//            }
//        }
        return null;
    }
    public void pinMode(String pinKey, int voltage){
        if(pins.get(pinKey) == null){
            pins.get(pinKey).activate();
        }
    }
    public void digitalWrite(String pinKey, float voltage){
        if(pins.get(pinKey) != null){
            pins.get(pinKey).setVoltage(voltage);
        }
    }
    @Override
    public void paint(Graphics g){
        g.setColor(Color.GREEN);
        g.fillRect(x, y, w, h);
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(x + (w/2) + 10, y-30, 45, 60);
        ArrayList<Pin.Wire> wires = new ArrayList<>();
        for(Pin p: pins.values()){
            p.paint(g, wires);
        }
        for(Pin.Wire wire:wires){
            wire.paint(g);
        }
    }
    public void paint(Graphics g, ArrayList<Pin.Wire> wires){
        g.setColor(Color.GREEN);
        g.fillRect(x, y, w, h);
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(x + (w/2) + 10, y-30, 45, 60);
        for(Pin p: pins.values()){
            p.paint(g, wires);
        }
    }
    public String readGPS(String sdaPinKey){
        if(pins.get(sdaPinKey) != null){
            Pin sda = pins.get(sdaPinKey);
            return sda.readData();
        }
        return "Arduino Pass";
    }
}
