package sma;

import algos.City;
import algos.Route;
import conf.Settings;
import jade.core.Agent;

import java.util.ArrayList;

public abstract class AgentMetaHeuristic extends Agent {
    private Route bestSolution;
    private ArrayList<Route> bestSolutions = new ArrayList<Route>();
    private ArrayList<City> cities;

    protected void setup() {
        System.out.println("Création de l'agent " + getLocalName());
        cities  = Settings.loadFile("data/cities10.csv", false);
        addBehaviour(new Receiver(this));

    }

    protected void takeDown() {
        System.out.println("Destruction de l'agent " + getLocalName());
    }

    protected ArrayList<City> getCities(){
        return cities;
    }

    protected Route getBestSolution() {
        return bestSolution;
    }
    protected void setBestSolution(Route route){
        bestSolution = new Route(route);
    }

    protected ArrayList<Route> getBestSolutions() {
        return bestSolutions;
    }

    public void addSolution(Route solution) {
        bestSolutions.add(solution);
        for (Route route : bestSolutions) {
            if (route.getTotalDistance() < bestSolution.getTotalDistance()) {
                bestSolution = new Route(route);
            }
        }
    }
}
