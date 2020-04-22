package sma;

import algos.AG;
import algos.Route;
import jade.core.behaviours.SimpleBehaviour;

public class AgentAG extends AgentMetaHeuristic {

    private class CollaborationBehaviour extends SimpleBehaviour {
        private final Route route;
        private boolean end = false;
        private final String[] dest = {"rs", "tabu"};

        public CollaborationBehaviour(Route route) {
            this.route = route;
        }

        public void action() {
            AG ag = new AG(route);
            ag.run(false);
            if (ag.getBestDistance() < getBestSolution().getTotalDistance())
            {
                setBestSolution(ag.getBestSolution());
            }
            myAgent.addBehaviour(new Sender(getBestSolution(), dest));
            end = true;
        }

        public boolean done() {
            return end;
        }
    }

    protected void setup() {
        super.setup();
        addBehaviour(new CollaborationBehaviour(new Route(getCities())));
    }

}