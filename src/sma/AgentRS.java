package sma;

import algos.RS;
import conf.Colors;

public class AgentRS extends AgentMetaHeuristic {

    private class CollaborationBehaviour extends InteractionBehaviour {
        public CollaborationBehaviour() {
            super(new String[]{"ag", "tabu"});
        }

        public void action() {
            if (getCounter() <= nbIterMax) {
                RS rs = new RS(getBestSolution(), 10, 10000, 0.9999, false);
                rs.run(false);
                if (rs.getBestDistance() < getBestSolution().getTotalDistance()) {
                    setBestSolution(rs.getBestSolution());
                }
                myAgent.addBehaviour(new Sender(getBestSolution(), dest));
                incrCounter();
            } else {
                System.out.println(Colors.ANSI_BLUE + "Done RS #" + Colors.ANSI_RESET);
                end = true;
                done();
                doDelete();
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