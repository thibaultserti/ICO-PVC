package sma;

import jade.core.behaviours.SimpleBehaviour;

public abstract class InteractionBehaviour extends SimpleBehaviour {
    protected String[] dest;
    protected boolean end = false;

    public InteractionBehaviour(String[] dest) {
        this.dest = dest;
    }

    public boolean done() {
        return end;
    }
}
