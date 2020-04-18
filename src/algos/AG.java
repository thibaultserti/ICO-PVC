package algos;

import conf.Colors;
import conf.Defaults;

import java.util.ArrayList;
import java.util.Collections;

import static java.lang.Integer.max;
import static java.lang.Integer.min;

public class AG implements Algo {
    private ArrayList<Route> routes = new ArrayList<Route>();
    private Route route;
    private double mutationRate;
    private int arenaSize;
    private int populationSize;
    private int numberOfIterations;

    private Route bestSolution = null;
    private double bestDistance = Double.POSITIVE_INFINITY;

    public AG() {
        mutationRate = Defaults.mutationRate;
        arenaSize = Defaults.arenaSize;
        numberOfIterations = Defaults.numberOfIterationsAG;
        populationSize = Defaults.populationSize;
    }

    public AG(Route route) {
        this();
        this.route = route;
        generateInit();
    }

    public AG(Route route, double mutationRate, int arenaSize, int numberOfIterations, int populationSize) {
        this();
        this.route = route;
        this.mutationRate = mutationRate;
        this.arenaSize = arenaSize;
        this.numberOfIterations = numberOfIterations;
        this.populationSize = populationSize;
        generateInit();

    }

    private void generateInit() {
        for (int i = 0; i < populationSize; i++) {
            Route r = new Route(route);
            Collections.shuffle(r.getCities());
            routes.add(r);
        }
    }

    private Route cross(Route r1, Route r2) {
        int point1 = r1.generateRandomIndex();
        int point2 = r2.generateRandomIndex();
        ArrayList<City> newCities = new ArrayList<City>();
        for (int i = 0; i < route.size(); i++) {
            newCities.add(null);
        }
        for (int i = min(point1, point2); i < max(point1, point2); i++) {
            newCities.set(i, (r1.getCities()).get(i));
        }
        for (int i = 0; i < r2.size(); i++) {
            City c = (r2.getCities()).get(i);
            if (!newCities.contains(c)) {
                for (int j = 0; j < newCities.size(); j++) {
                    if (newCities.get(j) == null) {
                        newCities.set(j, c);
                        break;
                    }
                }
            }
        }
        return (new Route(newCities));
    }

    private void mutate(Route route) {
        for (int i = 0; i < route.size(); i++) {
            if (Math.random() < mutationRate) {
                int j = (int) (Math.random() * route.size());
                route.swapCities(i, j);
            }
        }
    }

    private Route selectBestByTournament(ArrayList<Route> routes) {
        ArrayList<Route> arena = new ArrayList<Route>();
        // On ajoute les routes à l'arène
        for (int i = 0; i < arenaSize; i++) {
            arena.add(routes.get((int) (Math.random() * populationSize)));
        }
        // On sélectionne la meilleure
        return getBestRoute(arena);

    }

    private Route getBestRoute(ArrayList<Route> routes) {
        double bestDistance = routes.get(0).getTotalDistance();
        Route bestRoute = routes.get(0);
        for (Route r : routes) {
            if (r.getTotalDistance() < bestDistance) {
                bestDistance = r.getTotalDistance();
                bestRoute = r;
            }
        }
        return bestRoute;
    }

    private ArrayList<Route> evolve(ArrayList<Route> routes) {
        ArrayList<Route> newRoutes = new ArrayList<Route>();
        newRoutes.add(0, getBestRoute(routes));

        for (int j = 1; j < populationSize; j++) {
            Route parent1 = selectBestByTournament(routes);
            Route parent2 = selectBestByTournament(routes);
            Route child = cross(parent1, parent2);
            newRoutes.add(j, child);
        }
        for (int j = 1; j < populationSize; j++) {
            mutate(newRoutes.get(j));
        }
        return newRoutes;

    }

    public double run(boolean... b) {
        // on va garder la meilleure route entre chaque itération pour être sûr de ne
        // pas la perdre
        bestSolution = getBestRoute(routes);
        bestDistance = bestSolution.getTotalDistance();
        for (int i = 0; i < numberOfIterations; i++) {
            routes = evolve(routes);
            bestSolution = getBestRoute(routes);
            bestDistance = bestSolution.getTotalDistance();
            if ((b.length == 0)  || (b[0] == true)){
                if (i % 10 == 0) {
                    System.out.println(Colors.ANSI_BLUE + "Iteration #" + i + Colors.ANSI_RESET);
                    System.out.println(Colors.ANSI_CYAN + "Routes : " + Colors.ANSI_RESET + routes);
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
