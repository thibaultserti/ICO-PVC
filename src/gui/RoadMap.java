package gui;

import algos.City;
import algos.Route;
import jdk.internal.util.xml.impl.Pair;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


public class RoadMap extends JPanel {

    private static final double CONVERT_RADIANS_TO_DEGREES = 180D / Math.PI;
    private static final double x0 = -5.352568;
    private static final double x1 = 8.450785;
    private static final double y0 = 51.845539;
    private static final double y1 = 41.496396;

    private ArrayList<City> cities = new ArrayList<City>();
    private Route route = new Route(cities);

    public RoadMap() {
        super();
        this.setPreferredSize(new Dimension(1000, 1000));
    }

    public void paintComponent(Graphics g) {
        try {
            Image img = ImageIO.read(new File("img/france.png"));
            g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
            if (!cities.isEmpty()) {
                for (City city : cities) {
                    drawCity(city, g);
                }
            }
            if (!route.getCities().isEmpty()) {
                for (int i = 0; i < this.route.size() - 1; i++) {
                    drawRoute(route.getCities().get(i), route.getCities().get(i + 1), g);
                }
                drawRoute(route.getCities().get(0), route.getCities().get(route.size() - 1), g);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private double[] convert(City city) {
        double x_ = CONVERT_RADIANS_TO_DEGREES * city.getLatitude();
        double y_ = CONVERT_RADIANS_TO_DEGREES * city.getLongitude();
        // DON'T TOUCH
        double x = ((x_ - x0) / (x1 - x0)) * this.getWidth();
        double y = ((y1 - y_) / (y0 - y1)) * this.getHeight() + this.getHeight();
        return new double[]{x, y};
    }

    public void drawCity(City city, Graphics g) {
        double x = convert(city)[0];
        double y = convert(city)[1];
        g.fillOval((int) x, (int) y, 10, 10);
        g.drawString(city.getName(), (int) x, (int) y);
    }

    public void drawRoute(City city1, City city2, Graphics g) {
        double x1 = convert(city1)[0];
        double y1 = convert(city1)[1];
        double x2 = convert(city2)[0];
        double y2 = convert(city2)[1];
        g.drawLine((int) x1, (int) y1, (int) x2, (int) y2);
    }

    public ArrayList<City> getCities() {
        return cities;
    }

    public void addCities(ArrayList<City> cities) {
        this.cities.addAll(cities);
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public Route getRoute() {
        return route;
    }
}