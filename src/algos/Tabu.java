package algos;

import java.util.ArrayList;
import java.util.Collections;

public class Tabu {
    private Route route;
    private final int numberOfIterations;
    private final int tabuListMaxSize;

    private Route bestSolution = null;
    private double bestDistance = Double.POSITIVE_INFINITY;

    public Tabu() {
        tabuListMaxSize = 3;
        numberOfIterations = 1000;
    }

    public Tabu(Route route) {
        this();
        this.route = route;
        Collections.shuffle(this.route.getCities());
    }

    public Tabu(ArrayList<City> cities) {
        this();
        this.route = new Route(cities);
        Collections.shuffle(this.route.getCities());
    }

    public Tabu(Route route, int numberOfIterations, int maxTabu) {
        this.route = route;
        this.tabuListMaxSize = maxTabu;
        this.numberOfIterations = numberOfIterations;
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
        System.out.println(neighbors);
        return neighbors;
    }

    public double run() {
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