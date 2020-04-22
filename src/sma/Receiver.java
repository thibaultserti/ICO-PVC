package sma;

import algos.City;
import algos.Route;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

import java.util.ArrayList;

public class Receiver extends CyclicBehaviour {
    private AgentMetaHeuristic agent;

    public Receiver(AgentMetaHeuristic agent) {
        this.agent = agent;
    }

    public void action() {
        ACLMessage msg = myAgent.receive();
        if (msg != null) {
            System.out.println(" - " + myAgent.getLocalName() + " <- " + msg.getContent());
            // Jade ne permet pas de d'envoyer des objets Java donc on envoie une string
            // et on parse ...
            String[] message = msg.getContent().split(",");
            ArrayList<City> cities = new ArrayList<>();
            for (String s : message) {
                String[] tab = s.split(";");
                cities.add(new City(tab[0], Double.parseDouble(tab[1]), Double.parseDouble(tab[2]),true));
            }
            Route solution = new Route(cities);
            if (agent.getBestSolution().getTotalDistance() > solution.getTotalDistance()) {
                agent.setBestSolution(solution);
            }
        } else {
            block();
        }
    }
}