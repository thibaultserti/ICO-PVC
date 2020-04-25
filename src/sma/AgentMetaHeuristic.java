package sma;

import algos.City;
import algos.Route;
import conf.Colors;
import conf.Defaults;
import conf.Settings;
import jade.content.lang.Codec;
import jade.content.lang.sl.SLCodec;
import jade.content.onto.Ontology;
import jade.content.onto.basic.Action;
import jade.core.Agent;
import jade.domain.JADEAgentManagement.JADEManagementOntology;
import jade.domain.JADEAgentManagement.ShutdownPlatform;
import jade.lang.acl.ACLMessage;

import java.util.ArrayList;

import static conf.Settings.writeToFile;

public abstract class AgentMetaHeuristic extends Agent {
    private Route bestSolution;
    private ArrayList<City> cities;
    private int nbCities = Defaults.nbCitiesSMA;
    private int timeMax = Defaults.timeMaxSMA;
    private String typeOfInteraction;
    private static long timeStart;
    private static int nbAgentsDown = 0;

    protected void setup() {
        Object[] args = getArguments();
        this.typeOfInteraction = args[0].toString();
        if (args.length >= 2) {
            this.nbCities = Integer.parseInt(args[1].toString());
        }
        System.out.println("Création de l'agent " + getLocalName());
        cities = Settings.loadFile("data/cities" + nbCities + ".csv", false);
        if (args.length == 3) {
            timeMax = Integer.parseInt(args[2].toString());
        }
        bestSolution = new Route(cities);
        if (typeOfInteraction.equals("collaboration")) {
            addBehaviour(new Receiver(this, "collaboration"));
        } else if (typeOfInteraction.equals("competition")) {
            addBehaviour(new Receiver(this, "competition"));
        } else {
            System.out.println(Colors.ANSI_RED + "ERROR" + Colors.ANSI_RESET);
            takeDown();
        }
        timeStart = System.nanoTime();
    }

    public abstract void incrNbIteration();

    protected void takeDown() {
        System.out.println("La meilleure solution de " + getLocalName() + " est " + bestSolution);
        System.out.println("de distance totale " + bestSolution.getTotalDistance());
        System.out.println(Colors.ANSI_CYAN + "Destruction de l'agent " + getLocalName() + Colors.ANSI_RESET);
        nbAgentsDown++;
        long timeStop = System.nanoTime();
        double time = (timeStop - timeStart) / 1000000.;
        System.out.println("Temps écoulé : " + time + "ms");
        writeToFile("data/sma.csv", typeOfInteraction + ";" + nbCities + ";" +
                bestSolution.getTotalDistance() + ";" + time + "\n");
        if (nbAgentsDown == 3) {
            terminate();
        }
    }

    protected void terminate() {
        Codec codec = new SLCodec();
        Ontology jmo = JADEManagementOntology.getInstance();
        getContentManager().registerLanguage(codec);
        getContentManager().registerOntology(jmo);
        ACLMessage msg = new ACLMessage(ACLMessage.REQUEST);
        msg.addReceiver(getAMS());
        msg.setLanguage(codec.getName());
        msg.setOntology(jmo.getName());
        try {
            getContentManager().fillContent(msg, new Action(getAID(), new ShutdownPlatform()));
            send(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected ArrayList<City> getCities() {
        return cities;
    }

    protected double getTimeElapsed() {
        return (System.nanoTime() - timeStart) / 1000000.;
    }

    protected double getTimeMax() {
        return timeMax;
    }

    protected Route getBestSolution() {
        return bestSolution;
    }

    protected void setBestSolution(Route route) {
        bestSolution = new Route(route);
    }


    protected String getTypeOfInteraction() {
        return typeOfInteraction;
    }
}
