/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fproject.arduinosketch.circuit;

import com.google.gson.Gson;
import java.awt.Color;
import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jack
 */
public class GPS extends Component{
    private static String apiKey = "97527fe36f6a42898e7fa1f59c4c2f29";
    private static URL apiUrl;
    private static URL getApiUrl(){
        if(apiUrl == null){
            try {
                apiUrl = new URL("https://api.ipgeolocation.io/ipgeo?apiKey=" + apiKey);
            } catch (MalformedURLException ex) {
                //Logger.getLogger(GPS.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return apiUrl;
    }
    public static class Request{
        public String latitude;
        public String longitude;
    }
    
    
    public GPS(int x, int y, boolean isTool){
        super(x, y, 50, 50, 4, new Color(180, 180, 180), isTool);
        pins.add(new MalePin(x, y + h, 0, 0, gridSize, true, this));
        pins.add(new MalePin(x, y + h, 1, 0, gridSize, true, this));
        pins.add(new MalePin(x, y + h, 2, 0, gridSize, false));
        pins.add(new MalePin(x, y + h, 3, 0, gridSize, false));
        get();
    }
    @Override
    public void update(){
        voltage = pins.get(0).getVoltage();
    }
 
    /**
     * Moves this light to the new x and y.
     * @param nx new x position.
     * @param ny new y position.
     */
    
    /**
     * Paints each LED Light
     * @param g Java Graphics instance from the JPanel.
     */
    @Override
    public void paint(Graphics g){
        super.paint(g);
        g.setColor(Color.BLACK);
        g.drawString("GPS", x + w/2, y + h/2);
        if(voltage > 2.5f){
            g.setColor(Color.BLUE);
            g.fillOval(x + 5, y + 5, 5, 5);
        }
        else{
            g.setColor(Color.GRAY);
            g.fillOval(x + 5, y + 5, 5, 5);
        }
    }
    @Override
    public String readData(){
        return get();
    }
    private String get(){
        try {
            Gson g = new Gson();
            HttpURLConnection con = (HttpURLConnection) getApiUrl().openConnection();
            InputStreamReader isr = new InputStreamReader(con.getInputStream());
            Request r = g.fromJson(isr, Request.class);
            return r.latitude + ", " + r.longitude;
        } catch (IOException ex) {
            //Logger.getLogger(GPS.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "null, null";
    }
    @Override
    public Component spawn(ArrayList<Component> comps){
        GPS newGPS = this;
        if(isTool){
            newGPS = new GPS(x, y, false);
            comps.add(newGPS);
            goHome();
        }
        return newGPS;
    }
}
