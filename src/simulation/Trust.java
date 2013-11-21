package simulation;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import uchicago.src.sim.gui.Drawable;
import uchicago.src.sim.gui.SimGraphics;

public class Trust implements Drawable {
	
	private double value;
	private int x;
	private int y;
	
	Trust(int x, int y, double value) {
		this.x = x;
        this.y = y;
        
		this.value = Math.round(value * 100.0) / 100.0;
	}

	public double getValue() {
		return value;
	}
	
	public void setValue(double value) {
		this.value = value;
	}
	
	@Override
	public void draw(SimGraphics g) {
		Font font = new Font("Arial", Font.PLAIN, 18);

	    g.setFont(font);
	    g.setDrawingParameters(50, 20, 1);
	    Color rect_color;
	    if (Math.floor(value) < 3) {
	    	rect_color = Color.red;
	    }
	    else if (Math.floor(value) == 3) {
	    	rect_color = Color.yellow;
	    }
	    else {
	    	rect_color = Color.green;
	    }
		g.drawStringInRoundRect(rect_color, Color.black, String.valueOf(value));
		
	}

	@Override
	public int getX() {
		return x;
	}

	@Override
	public int getY() {
		return y;
	}
	
	

}
