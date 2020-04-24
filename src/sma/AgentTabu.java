package sma;

import algos.Tabu;
import conf.Colors;
import jade.core.behaviours.SimpleBehaviour;

public class AgentTabu extends AgentMetaHeuristic {

    private class CollaborationBehaviour extends InteractionBehaviour {

        public CollaborationBehaviour() {
            super(new String[]{"rs", "ag"});
        }

        public void action() {
            if (getCounter() <= nbIterMax) {

                Tabu tabu = new Tabu(getBestSolution(), false);
                Double a = tabu.run(false);
                if (tabu.getBestDistance() < getBestSolution().getTotalDistance()) {
                    setBestSolution(tabu.getBestSolution());
                }
                myAgent.addBehaviour(new Sender(getBestSolution(), dest));
                incrCounter();
            } else {
                System.out.println(Colors.ANSI_BLUE + "Done Tabu #" + Colors.ANSI_RESET);
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
