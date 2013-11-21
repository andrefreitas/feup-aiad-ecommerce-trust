package simulation;

import java.awt.Color;
import java.awt.Font;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import uchicago.src.sim.gui.Drawable;
import uchicago.src.sim.gui.SimGraphics;

public class Trust implements Drawable {
	
	private String value;
	private int x;
	private int y;
	
	Trust(int x, int y, String value) {
		this.x = x;
        this.y = y;
		this.value = value;
	}

	public String getValue() {
		return value;
	}
	
	@Override
	public void draw(SimGraphics g) {
		/*Font font = new Font("sans-serif", Font.BOLD, 12);
	    
	    g.setFont(font);
		//g.drawCircle(Color.red);
		g.drawString(value, Color.white);*/
		int fontSize = 20;
		//g.setFont(new Font("TimesRoman", Font.PLAIN, fontSize));
	     
	    
	    
	    g.drawString("2", Color.white);
		
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
