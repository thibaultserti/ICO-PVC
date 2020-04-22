package algos;

import conf.Colors;
import conf.Defaults;

import java.util.ArrayList;
import java.util.Collections;

public class RS implements Algo {
    private Route route;

    private final double startingTemperature;
    private final int numberOfIterations;
    private final double coolingRate;

    private Route bestSolution = null;
    private double bestDistance = Double.POSITIVE_INFINITY;

    public RS() {
        startingTemperature = Defaults.startingTemperature;
        numberOfIterations = Defaults.numberOfIterationsRS;
        coolingRate = Defaults.coolingRate;
    }

    public RS(Route route, boolean... b) {
        this();
        this.route = route;
        if (b.length == 0 || b[0]){
            shuffle();
        }
    }

    public RS(ArrayList<City> cities, boolean... b) {
        this();
        this.route = new Route(cities);
        if (b.length == 0 || b[0]){
            shuffle();
        }

    }

    public RS(Route route, double startingTemperature, int numberOfIterations, double coolingRate, boolean... b) {
        this.route = route;
        this.startingTemperature = startingTemperature;
        this.numberOfIterations = numberOfIterations;
        this.coolingRate = coolingRate;
        if (b.length == 0 || b[0]){
            shuffle();
        }
    }

    public RS(ArrayList<City> cities, double startingTemperature, int numberOfIterations, double coolingRate, boolean... b) {
        this.route = new Route(cities);
        this.startingTemperature = startingTemperature;
        this.numberOfIterations = numberOfIterations;
        this.coolingRate = coolingRate;
        if (b.length == 0 || b[0]){
            shuffle();
        }
    }

    private void shuffle() {
        Collections.shuffle(this.route.getCities());
    }

    public double run(boolean... b) {
        double t = startingTemperature;

        Route currentSolution = route;
        bestSolution = new Route(currentSolution.getCities());

        double currentDistance = currentSolution.getTotalDistance();
        bestDistance = route.getTotalDistance();

        for (int i = 0; i < numberOfIterations; i++) {
            if (t > 0.1) {
                currentSolution.swapCities();
                currentDistance = currentSolution.getTotalDistance();
                if (currentDistance < bestDistance) {
                    bestDistance = currentDistance;
                    bestSolution = new Route(currentSolution.getCities());
                } else if (Math.exp((bestDistance - currentDistance) / t) < Math.random()) {
                    currentSolution.revertSwap();
                }
                t *= coolingRate;
            }
            if ((b.length == 0) || (b[0])) {
                if (i % 100 == 0) {
                    System.out.println(Colors.ANSI_BLUE + "Iteration #" + i + Colors.ANSI_RESET);
                    System.out.println(Colors.ANSI_CYAN + "Température : " + Colors.ANSI_RESET + t);
                    System.out.println(Colors.ANSI_CYAN + "Solution : " + Colors.ANSI_RESET + currentSolution);
                    System.out.println(Colors.ANSI_CYAN + "Distance actuelle : " + Colors.ANSI_RESET + currentDistance);
                    System.out.println(Colors.ANSI_CYAN + "Meilleure distance : " + Colors.ANSI_RESET + bestDistance);
                    System.out.println(Colors.ANSI_CYAN + "Meilleure solution : " + Colors.ANSI_RESET + bestSolution);

                }
            }
        }
        return bestDistance;
    }

    public Route getBestSolution() {
        return bestSolution;
    }

    public double getBestDistance() {
        return bestDistance;
    }
}
