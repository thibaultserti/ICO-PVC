package sma;

import algos.AG;
import conf.Colors;
import jade.core.behaviours.SimpleBehaviour;

public class AgentAG extends AgentMetaHeuristic {

    private class CollaborationBehaviour extends InteractionBehaviour {
        public CollaborationBehaviour() {
            super(new String[]{"tabu", "rs"});
        }

        public void action() {
            if (getCounter() <= nbIterMax) {
                AG ag = new AG(getBestSolution());
                ag.run(false);
                if (ag.getBestDistance() < getBestSolution().getTotalDistance()) {
                    setBestSolution(ag.getBestSolution());
                }
                myAgent.addBehaviour(new Sender(getBestSolution(), dest));
                incrCounter();
            } else {
                System.out.println(Colors.ANSI_BLUE + "Done AG #" + Colors.ANSI_RESET);
                end = true;
                done();
            }
        }

        public boolean done() {
            return end;
        }
    }

    protected void setup() {
        super.setup();
        addBehaviour(new CollaborationBehaviour());
    }

}