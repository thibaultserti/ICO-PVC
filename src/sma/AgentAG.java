package sma;

import algos.AG;
import algos.City;
import algos.Route;
import conf.Settings;
import jade.core.Agent;
import jade.core.behaviours.SimpleBehaviour;

import java.util.ArrayList;

public class AgentAG extends Agent {
    private Route bestSolution = new Route(new ArrayList<City>());

    private class CollaborationBehaviour extends SimpleBehaviour {
        private final Route route;
        private boolean end = false;
        private final String[] dest = {"rs", "tabu"};

        public CollaborationBehaviour(Route route) {
            this.route = route;
        }

        public void action() {
            AG ag = new AG(route);
            ag.run(false);
            bestSolution = new Route(ag.getBestSolution());
            myAgent.addBehaviour(new Sender(bestSolution, dest));
            end = true;
        }

        public boolean done() {
            return end;
        }
    }

    protected void setup() {
        System.out.println("Création de l'agent " + getLocalName());
        ArrayList<City> cities = Settings.loadFile("data/cities10.csv", true);
        addBehaviour(new CollaborationBehaviour(new Route(cities)));
        addBehaviour(new Receiver());

    }

    protected void takeDown() {
        System.out.println("Destruction de l'agent " + getLocalName());
    }
}

