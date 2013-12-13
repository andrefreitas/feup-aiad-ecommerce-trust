package simulation;

import ecommerce.*;
import java.awt.Color;
import java.util.ArrayList;


import uchicago.src.sim.gui.Drawable;
import uchicago.src.sim.gui.SimGraphics;
import uchicago.src.sim.space.Object2DTorus;

public class Agent extends User implements Drawable{

    private int x, y;
    private Color color;
    private Object2DTorus space;
    
    public Agent(int x, int y, Color color, Object2DTorus space, String name, String country, String behaviour, ArrayList<String> categories) {
        super(name, country);
        this.behaviour = behaviour;
        this.categories = categories;
        this.x = x;
        this.y = y;
        this.color = color;
        this.space = space;
    }

    @Override
    public void draw(SimGraphics g) {
        g.drawFastCircle(color);
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
    

}
