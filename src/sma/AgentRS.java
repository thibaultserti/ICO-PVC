package sma;

import algos.RS;
import algos.Route;
import jade.core.behaviours.SimpleBehaviour;

public class AgentRS extends AgentMetaHeuristic {

    private class CollaborationBehaviour extends SimpleBehaviour {
        private final Route route;
        private boolean end = false;
        private final String[] dest = {"tabu", "ag"};

        public CollaborationBehaviour(Route route) {
            this.route = route;
        }

        public void action() {
            RS rs = new RS(route);
            rs.run(false);
            setBestSolution(rs.getBestSolution());
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