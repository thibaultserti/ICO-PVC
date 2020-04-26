package sma;

import algos.Tabu;
import conf.Colors;
import conf.Defaults;

public class AgentTabu extends AgentMetaHeuristic {
    private int nbIteration = Defaults.numberOfIterationsTabu;

    private class CollaborationBehaviour extends InteractionBehaviour {
        public CollaborationBehaviour() {
            super(new String[]{"rs", "ag"});
        }

        public void action() {
            if (getTimeElapsed() <= getTimeMax()) {

                Tabu tabu = new Tabu(getBestSolution(), false);
                Double a = tabu.run(false);
                if (tabu.getBestDistance() < getBestSolution().getTotalDistance()) {
                    setBestSolution(tabu.getBestSolution());
                }
                myAgent.addBehaviour(new Sender(getBestSolution(), dest));
            } else {
                System.out.println(Colors.ANSI_BLUE + "Done Tabu #" + Colors.ANSI_RESET);
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
                Tabu tabu = new Tabu(getBestSolution(), nbIteration, Defaults.tabuListMaxSize, false);
                tabu.run(false);
                if (tabu.getBestDistance() < getBestSolution().getTotalDistance()) {
                    setBestSolution(tabu.getBestSolution());
                }
                myAgent.addBehaviour(new Sender(getBestSolution(), dest));
            } else {
                System.out.println(Colors.ANSI_BLUE + "Done Tabu #" + Colors.ANSI_RESET);
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
        nbIteration += (int) (Defaults.numberOfIterationsTabu / 10.);
    }

}
