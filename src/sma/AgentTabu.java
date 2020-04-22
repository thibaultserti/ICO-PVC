package sma;

import algos.City;
import algos.Route;
import algos.Tabu;
import conf.Settings;
import jade.core.Agent;
import jade.core.behaviours.SimpleBehaviour;

import java.util.ArrayList;

public class AgentTabu extends Agent {
    private Route bestSolution;

    private class CollaborationBehaviour extends SimpleBehaviour {
        private final Route route;
        private boolean end = false;
        private final String[] dest = {"ag", "rs"};

        public CollaborationBehaviour(Route route) {
            this.route = route;
        }

        public void action() {
            Tabu tabu = new Tabu(route);
            tabu.run(false);
            bestSolution = new Route(tabu.getBestSolution());
            end = true;
            myAgent.addBehaviour(new Sender(bestSolution, dest));
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
