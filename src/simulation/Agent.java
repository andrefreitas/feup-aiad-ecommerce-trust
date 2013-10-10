package simulation;

import uchicago.src.sim.space.Object2DTorus;
import uchicago.src.sim.util.Random;
import uchicago.src.sim.gui.Drawable;
import uchicago.src.sim.gui.SimGraphics;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

import javax.imageio.ImageIO;


public class Agent implements Drawable  {
	private int x, y;
	private Color color;
	private Object2DTorus space;

	public Agent (int x, int y, Color color, Object2DTorus space){
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
