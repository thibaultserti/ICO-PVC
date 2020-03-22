package algos;

import java.util.ArrayList;
import java.util.Collections;

public class RS {
    private Route route;

    private double startingTemperature;
    private double numberOfIterations;
    private double coolingRate;

    public RS() {
        startingTemperature = 10;
        numberOfIterations = 1000;
        coolingRate = 0.99;
    }

    public RS(Route route) {
        this();
        this.route = route;
        Collections.shuffle(this.route.getCities());
    }

    public RS(ArrayList<City> cities) {
        this();
        this.route = new Route(cities);
        Collections.shuffle(this.route.getCities());

    }

    public RS(Route route, double startingTemperature, double numberOfIterations, double coolingRate) {
        this.route = route;
        Collections.shuffle(this.route.getCities());
        this.startingTemperature = startingTemperature;
        this.numberOfIterations = numberOfIterations;
        this.coolingRate = coolingRate;
    }

    public RS(ArrayList<City> cities, double startingTemperature, double numberOfIterations, double coolingRate) {
        this.route = new Route(cities);
        Collections.shuffle(this.route.getCities());
        this.startingTemperature = startingTemperature;
        this.numberOfIterations = numberOfIterations;
        this.coolingRate = coolingRate;
    }

    public double run() {
        double t = startingTemperature;

        Route currentSolution = route;
        Route bestSolution = new Route(currentSolution.getCities());

        double currentDistance = currentSolution.getTotalDistance();
        double bestDistance = route.getTotalDistance();

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

            if (i % 100 == 0) {
                System.out.println(Colors.ANSI_BLUE + "Iteration #" + i + Colors.ANSI_RESET);
                System.out.println(Colors.ANSI_CYAN + "Température : " + Colors.ANSI_RESET + t);
                System.out.println(Colors.ANSI_CYAN + "Solution : " + Colors.ANSI_RESET + currentSolution);
                System.out.println(Colors.ANSI_CYAN + "Distance actuelle : " + Colors.ANSI_RESET + currentDistance);
                System.out.println(Colors.ANSI_CYAN + "Meilleure distance : " + Colors.ANSI_RESET + bestDistance);
                System.out.println(Colors.ANSI_CYAN + "Meilleure solution : " + Colors.ANSI_RESET + bestSolution);

            }
        }

        return bestDistance;
    }

}
