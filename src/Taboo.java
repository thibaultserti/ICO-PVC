import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Taboo {
   
    private Route route;
    private int maxIterations;

    public Taboo() {
        maxIterations = 500;
    }

    public Taboo(Route route) {
        this();
        this.route = route;
        Collections.shuffle(this.route.getCities());
    }

    public Taboo(ArrayList<City> cities) {
        this();
        this.route = new Route(cities);
        Collections.shuffle(this.route.getCities());

    }

    public Taboo(Route route, int maxIterations) {
        this.route = route;
        Collections.shuffle(this.route.getCities());
        this.maxIterations = maxIterations;
    }

    public Taboo(ArrayList<City> cities, int maxIterations) {
        this.route = new Route(cities);
        Collections.shuffle(this.route.getCities());
        this.maxIterations = maxIterations;
    }

    public double run() {

        Route currentSolution = route;
        Route bestSolution = new Route(currentSolution.getCities());

        double currentDistance = currentSolution.getTotalDistance();
        double bestDistance = route.getTotalDistance();

        double optimalDistanceBound = 0;

        ArrayList<Route> tabooList = new ArrayList<Route>();
        ArrayList<Route> adjacentSolutions;
        
        initAspiration();
        setAbsorption(currentDistance, Double.POSITIVE_INFINITY);

        int i = 0;
        int bestIteration = 0;

        Route consideredSolution = null;
        double consideredDistance = 0;

        Route tempRoute = null;
        int tempLength = 0;        
        
        int n = route.getCities().size();

        while (currentDistance > optimalDistanceBound
                && i - bestIteration < maxIterations) {
            
            i++;

            adjacentSolutions = generateAdjacentSolutions(currentSolution, n);
            tempLength = adjacentSolutions.size();

            for (int j = 0; j < tempLength; j++) {
                tempRoute = adjacentSolutions.get(j);
                if(tempRoute.getTotalDistance() <= getAbsorption(currentDistance)
                    || !tabooList.contains(tempRoute)) {
                    consideredSolution = new Route(tempRoute);
                }
            }

            consideredDistance = consideredSolution.getTotalDistance();

            setAbsorption(consideredDistance, Double.POSITIVE_INFINITY);
            setAbsorption(currentDistance, consideredDistance);
            
            tabooList = new ArrayList<Route>(adjacentSolutions);
            tabooList.remove(consideredSolution);

            currentSolution = consideredSolution;
            currentDistance = consideredDistance;

            if (currentDistance < bestDistance) {            
                bestDistance = currentDistance;
                bestIteration = i;
            }

            if (i % 100 == 0 || i == 1) {
                System.out.println(Colors.ANSI_BLUE + "Iteration #" + i + Colors.ANSI_RESET);
                System.out.println(Colors.ANSI_CYAN + "Solution : " + Colors.ANSI_RESET + currentSolution);
                System.out.println(Colors.ANSI_CYAN + "Distance actuelle : " + Colors.ANSI_RESET + currentDistance);
                System.out.println(Colors.ANSI_CYAN + "Meilleure distance : " + Colors.ANSI_RESET + bestDistance);
                System.out.println(Colors.ANSI_CYAN + "Meilleure solution : " + Colors.ANSI_RESET + bestSolution);
            }

        }

        return bestDistance;

    }
    
    private HashMap<Double,Double> absorption;

    private double getAbsorption(double solutionScore) {
        return absorption.get(solutionScore);
    }

    private void setAbsorption(double solutionScore, double value) {
        absorption.put(solutionScore, value);
    }

    private void initAspiration() {
        absorption = new HashMap<>();
    }

    private ArrayList<Route> generateAdjacentSolutions(Route solution, int n) {
        ArrayList<Route> res = new ArrayList<>();
        for (int k = 0; k < n; k++) {
            solution.swapCities();
            res.add(new Route(solution));
            solution.revertSwap();
        }
        return res;
    }

}
