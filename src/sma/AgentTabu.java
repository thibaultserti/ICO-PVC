package sma;

import algos.Route;
import algos.Tabu;
import jade.core.behaviours.SimpleBehaviour;

public class AgentTabu extends AgentMetaHeuristic {

    private class CollaborationBehaviour extends SimpleBehaviour {
        private final Route route;
        private boolean end = false;
        private final String[] dest = {"ag", "rs"};

        public CollaborationBehaviour(Route route) {
            this.route = route;
        }

        public void action() {
            Tabu tabu = new Tabu(route);
            tabu.run(false);
            if (tabu.getBestDistance() < getBestSolution().getTotalDistance()) {
                setBestSolution(tabu.getBestSolution());
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
        addBehaviour(new AgentTabu.CollaborationBehaviour(new Route(getCities())));
    }

}
