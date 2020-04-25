package sma;

import algos.AG;
import conf.Colors;
import conf.Defaults;

public class AgentAG extends AgentMetaHeuristic {
    private int nbIteration = Defaults.numberOfIterationsAG;

    private class CollaborationBehaviour extends InteractionBehaviour {
        public CollaborationBehaviour() {
            super(new String[]{"tabu", "rs"});
        }

        public void action() {
            if (getTimeElapsed() <= getTimeMax()) {
                AG ag = new AG(getBestSolution());
                ag.run(false);
                if (ag.getBestDistance() < getBestSolution().getTotalDistance()) {
                    setBestSolution(ag.getBestSolution());
                }
                myAgent.addBehaviour(new Sender(getBestSolution(), dest));
            } else {
                System.out.println(Colors.ANSI_BLUE + "Done AG #" + Colors.ANSI_RESET);
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
                AG ag = new AG(getBestSolution(), Defaults.mutationRate, Defaults.arenaSize, nbIteration, Defaults.populationSize);
                ag.run(false);
                if (ag.getBestDistance() < getBestSolution().getTotalDistance()) {
                    setBestSolution(ag.getBestSolution());
                }
                myAgent.addBehaviour(new Sender(getBestSolution(), dest));
            } else {
                System.out.println(Colors.ANSI_BLUE + "Done AG #" + Colors.ANSI_RESET);
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
        nbIteration += (int) (Defaults.numberOfIterationsAG / 10.);
    }

}