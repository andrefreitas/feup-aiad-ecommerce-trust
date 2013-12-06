package simulation;

import ecommerce.*;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import uchicago.src.sim.gui.Drawable;
import uchicago.src.sim.gui.SimGraphics;
import uchicago.src.sim.space.Object2DTorus;
import uchicago.src.sim.engine.Stepable;

public class Agent extends User implements Drawable{

    private int x, y;
    private Color color;
    private Object2DTorus space;
    private Trust trust;
    
    public Agent(int x, int y, Color color, Object2DTorus space, String name, String country) {
        super(name, country);
        this.x = x;
        this.y = y;
        this.color = color;
        this.space = space;
    }

    @Override
    public void draw(SimGraphics g) {
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File("res/buyer.png"));
        } catch (IOException e) {
            System.out.println("[ERROR] Invalid image!");
        }
        g.drawImage(img);
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    public Color getColor() {
        return color;
    }
    
    public void setTrust(Trust t){
        trust = t;
    }
    
    public void setTrustValue(double v){
        trust.setValue(v);
    }
}
