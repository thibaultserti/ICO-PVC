package sma;

import algos.RS;
import conf.Colors;
import jade.core.behaviours.SimpleBehaviour;

public class AgentRS extends AgentMetaHeuristic {

    private class CollaborationBehaviour extends SimpleBehaviour {
        private boolean end = false;
        private final String[] dest = {"tabu", "ag"};

        public void action() {
            if (getCounter() <= nbIterMax) {
                RS rs = new RS(getBestSolution(),10,10000,0.9999, false);
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