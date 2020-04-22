package algos;

import conf.Colors;
import conf.Defaults;

import java.util.ArrayList;
import java.util.Collections;

public class Tabu implements Algo {
    private Route route;
    private final int numberOfIterations;
    private final int tabuListMaxSize;

    private Route bestSolution = null;
    private double bestDistance = Double.POSITIVE_INFINITY;

    public Tabu() {
        tabuListMaxSize = Defaults.tabuListMaxSize;
        numberOfIterations = Defaults.numberOfIterationsTabu;
    }

    public Tabu(Route route, boolean... b) {
        this();
        this.route = route;
        if (b.length == 0 || b[0]) {
            shuffle();
        }
    }

    public Tabu(ArrayList<City> cities, boolean... b) {
        this();
        this.route = new Route(cities);
        if (b.length == 0 || b[0]) {
            shuffle();
        }
    }

    public Tabu(Route route, int numberOfIterations, int maxTabu, boolean... b) {
        this.route = route;
        this.tabuListMaxSize = maxTabu;
        this.numberOfIterations = numberOfIterations;
        if (b.length == 0 || b[0]) {
            shuffle();
        }
    }

    private void shuffle() {
        Collections.shuffle(this.route.getCities());
    }

    private ArrayList<Route> getNeighbors(Route route) {
        ArrayList<Route> neighbors = new ArrayList<Route>();
        for (int i = 0; i < route.size() - 1; i++) {
            for (int j = i + 1; j < route.size(); j++) {
                route.swapCities(i, j);
                neighbors.add(new Route(route));
                route.revertSwap();
            }
        }
        return neighbors;
    }

    public double run(boolean... b) {
        Route currentSolution = route;
        bestSolution = new Route(currentSolution.getCities());

        double currentDistance = currentSolution.getTotalDistance();
        bestDistance = route.getTotalDistance();

        ArrayList<Route> tabuList = new ArrayList<Route>();
        tabuList.add(currentSolution);

        for (int i = 0; i < numberOfIterations; i++) {
            ArrayList<Route> neighbors = getNeighbors(currentSolution);
            currentSolution = neighbors.get(0);
            currentDistance = currentSolution.getTotalDistance();
            for (Route candidate : neighbors) {
                if (!(tabuList.contains(candidate)) && (candidate.getTotalDistance() < currentDistance)) {
                    currentSolution = new Route(candidate.getCities());
                    currentDistance = currentSolution.getTotalDistance();
                }
            }
            if (currentDistance < bestDistance) {
                bestSolution = new Route(currentSolution);
                bestDistance = bestSolution.getTotalDistance();
            }
            tabuList.add(currentSolution);
            if (tabuList.size() > tabuListMaxSize) {
                tabuList.remove(0);
            }
            if ((b.length == 0) || (b[0])) {

                if (i % 100 == 0) {
                    System.out.println(Colors.ANSI_BLUE + "Iteration #" + i + Colors.ANSI_RESET);
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
