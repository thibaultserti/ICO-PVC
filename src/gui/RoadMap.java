package gui;

import algos.City;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;


public class RoadMap extends JPanel {

    private static final double CONVERT_RADIANS_TO_DEGREES = 180D / Math.PI;
    private double x0 = -5.352568;
    private double x1 = 8.450785;
    private double y0 = 51.845539;
    private double y1 = 41.496396;


    public void paintComponent(Graphics g) {
        try {
            Image img = ImageIO.read(new File("img/france.png"));
            g.drawImage(img, 0, 0, this.getWidth()-200, this.getHeight(), this);
            drawCity(new City("Brest", -4.498893, 48.406102), g);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void drawCity(City city, Graphics g) {
        double x_ = CONVERT_RADIANS_TO_DEGREES * city.getLatitude();
        double y_ = CONVERT_RADIANS_TO_DEGREES * city.getLongitude();
        // DON'T TOUCH
        double x = ((x_ - x0) / (x1 - x0)) * this.getWidth();
        double y = ((y1 - y_) / (y0 - y1)) * this.getHeight() + this.getHeight();
        g.fillOval((int) x, (int) y, 10, 10);
        g.drawString(city.getName(), (int) x, (int) y);
    }

    public void drawRoute(City city1, City city2, Graphics g) {
        g.drawLine((int) city1.getLongitude(), (int) city1.getLatitude(), (int) city2.getLongitude(), (int) city2.getLatitude());
    }


}