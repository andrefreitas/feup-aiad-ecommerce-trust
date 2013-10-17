package simulation;

import ecommerce.*;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import uchicago.src.sim.gui.Drawable;
import uchicago.src.sim.gui.SimGraphics;
import uchicago.src.sim.space.Object2DTorus;


public class Agent extends User implements Drawable  {
	private int x, y;
	private Color color;
	private Object2DTorus space;

	public Agent (int x, int y, Color color, Object2DTorus space, String name, String country){
		super(name, country);
		this.x = 0;
		this.y = 0;
		this.color = color;
		this.space = space;
	}

	public void draw(SimGraphics g) {
		BufferedImage img = null;
		try {
		    img = ImageIO.read(new File("res/buyer.png"));
		} catch (IOException e) {
			System.out.println("[ERROR] Invalid image!");
		}
		g.drawImage(img);
	}

	
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public Color getColor() {
		return color;
	}
	
}
