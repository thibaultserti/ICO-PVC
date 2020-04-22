package sma;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

public class Receiver extends CyclicBehaviour {
    private Agent agent;
    public Receiver(Agent agent){
        this.agent = agent;
    }
    public void action() {
        ACLMessage msg = myAgent.receive();
        if (msg != null) {
            System.out.println(" - " + myAgent.getLocalName() + " <- " + msg.getContent()); *
        } else {
            block();
        }
    }
}