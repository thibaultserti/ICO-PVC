package sma;

import algos.City;
import algos.Route;
import conf.Colors;
import conf.Defaults;
import conf.Settings;
import jade.core.Agent;

import java.util.ArrayList;

public abstract class AgentMetaHeuristic extends Agent {
    private Route bestSolution;
    private ArrayList<City> cities;
    private int counter = 0;
    private final int nbIterMax = Defaults.nbIterMaxSMA;
    private String typeOfInteraction;

    protected void setup() {
        Object[] args = getArguments();
        this.typeOfInteraction = args[0].toString();
        System.out.println("Création de l'agent " + getLocalName());
        cities = Settings.loadFile("data/cities.csv", false);
        bestSolution = new Route(cities);
        if (typeOfInteraction.equals("collaboration")) {
            addBehaviour(new Receiver(this, "collaboration"));
        } else if (typeOfInteraction.equals("competition")) {
            addBehaviour(new Receiver(this, "competition"));
        } else {
            System.out.println(Colors.ANSI_RED + "ERROR" + Colors.ANSI_RESET);
            takeDown();
        }
    }

    public abstract void incrNbIteration();

    protected void takeDown() {
        System.out.println("La meilleure solution de " + getLocalName() + " est " + bestSolution);
        System.out.println("de distance totale " + bestSolution.getTotalDistance());
        System.out.println(Colors.ANSI_CYAN + "Destruction de l'agent " + getLocalName() + Colors.ANSI_RESET);
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
        this.counter++;
    }

    protected int getNbIterMax() {
        return nbIterMax;
    }

    protected String getTypeOfInteraction() {
        return typeOfInteraction;
    }
}
