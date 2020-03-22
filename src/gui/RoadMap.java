package gui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class RoadMap extends JPanel {
    private double x0 = 51.845539;
    private double x1 = 41.496396;
    private double y0 = -5.352568;
    private double y1 = 8.450785;


    public void paintComponent(Graphics g) {
        try {
            Image img = ImageIO.read(new File("img/france.png"));
            g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}