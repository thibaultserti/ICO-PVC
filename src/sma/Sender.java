package sma;

import algos.Route;
import jade.core.AID;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;

public class Sender extends OneShotBehaviour {
    String[] destinations;
    Route route;

    public Sender(Route route, String... names) {
        this.destinations = names;
        this.route = route;
    }

    public void action() {
        ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
        for (String agent : destinations) {
            msg.addReceiver(new AID(agent, AID.ISLOCALNAME));
        }
        msg.setContent(route.toString());
        myAgent.send(msg);
    }
}
