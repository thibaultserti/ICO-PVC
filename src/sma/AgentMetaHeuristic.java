package sma;

import algos.City;
import algos.Route;
import conf.Colors;
import conf.Settings;
import jade.core.Agent;

import java.util.ArrayList;

public abstract class AgentMetaHeuristic extends Agent {
    private Route bestSolution;
    private ArrayList<City> cities;
    private int counter = 0;
    private final int nbIterMax = 100;

    protected void setup() {
        System.out.println("Création de l'agent " + getLocalName());
        cities = Settings.loadFile("data/cities20.csv", false);
        bestSolution = new Route(cities);
        addBehaviour(new Receiver(this));

    }

    protected void takeDown() {
        System.out.println("La meilleure solution de " + getLocalName() + " est " + bestSolution);
        System.out.println("de distance totale " + bestSolution.getTotalDistance());
        System.out.println(Colors.ANSI_CYAN +"Destruction de l'agent " + getLocalName() + Colors.ANSI_RESET);
    }

    protected ArrayList<City> getCities() {
        return cities;
    }

    protected Route getBestSolution() {
        return bestSolution;
    }

    protected void setBestSolution(Route route) {
        bestSolution = new Route(route);
    }

    protected int getCounter() {
        return counter;
    }

     protected void incrCounter() {
        this.counter ++;
    }

    public int getNbIterMax() {
        return nbIterMax;
    }
}
