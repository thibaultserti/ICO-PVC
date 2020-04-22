package sma;

import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

public class Receiver extends CyclicBehaviour {
    public void action() {
        ACLMessage msg = myAgent.receive();
        if (msg != null) {
            System.out.println(" - " + myAgent.getLocalName() + " <- " + msg.getContent());
        } else {
            block();
        }
    }
}