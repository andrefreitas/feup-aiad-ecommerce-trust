package simulation;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import uchicago.src.sim.gui.Drawable;
import uchicago.src.sim.gui.SimGraphics;

public class Background implements Drawable {

    private BufferedImage img;

    Background() {
        try {
            img = ImageIO.read(new File("res/background.png"));
        } catch (IOException e) {
            System.out.println("[ERROR] Invalid image!");
        }

    }

    @Override
    public void draw(SimGraphics g) {
        g.drawImage(img);
    }

    @Override
    public int getX() {
        return 0;
    }

    @Override
    public int getY() {
        return 0;
    }

}
