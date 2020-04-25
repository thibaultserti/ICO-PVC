package sma;

import algos.RS;
import conf.Colors;
import conf.Defaults;

public class AgentRS extends AgentMetaHeuristic {
    private int nbIteration = Defaults.numberOfIterationsRS;

    private class CollaborationBehaviour extends InteractionBehaviour {
        public CollaborationBehaviour() {
            super(new String[]{"ag", "tabu"});
        }

        public void action() {
            if (getTimeElapsed() <= getTimeMax()) {
                RS rs = new RS(getBestSolution(), false);
                rs.run(false);
                if (rs.getBestDistance() < getBestSolution().getTotalDistance()) {
                    setBestSolution(rs.getBestSolution());
                }
                myAgent.addBehaviour(new Sender(getBestSolution(), dest));
            } else {
                System.out.println(Colors.ANSI_BLUE + "Done RS #" + Colors.ANSI_RESET);
                end = true;
                done();
                doDelete();
            }
        }

    }

    private class CompetitionBehaviour extends InteractionBehaviour {
        public CompetitionBehaviour() {
            super(new String[]{"ag", "tabu"});
        }

        public void action() {
            if (getTimeElapsed() <= getTimeMax()) {
                RS rs = new RS(getBestSolution(), 10, nbIteration, 0.9999, false);
                rs.run(false);
                if (rs.getBestDistance() < getBestSolution().getTotalDistance()) {
                    setBestSolution(rs.getBestSolution());
                }
                myAgent.addBehaviour(new Sender(getBestSolution(), dest));
            } else {
                System.out.println(Colors.ANSI_BLUE + "Done RS #" + Colors.ANSI_RESET);
                end = true;
                done();
                doDelete();
            }
        }


    }

    protected void setup() {
        super.setup();
        if (getTypeOfInteraction().equals("collaboration")) {
            addBehaviour(new CollaborationBehaviour());
        } else if (getTypeOfInteraction().equals(("competition"))) {
            addBehaviour(new CompetitionBehaviour());
        }
    }

    public void incrNbIteration() {
        nbIteration += (int) (Defaults.numberOfIterationsRS);
    }

}