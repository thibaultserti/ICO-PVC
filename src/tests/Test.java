package tests;

import algos.*;

import java.util.ArrayList;

import static gui.Settings.loadFile;
import static gui.Settings.writeToFile;

// to execute this class you need to have all cities10.csv to cities200.csv downloaded
public class Test {
    private static ArrayList<City> cities = new ArrayList<>();

    private final double startingTemperature;
    private final double numberOfIterationsRS;
    private final double coolingRate;

    private final double mutationRate;
    private final int arenaSize;
    private final int populationSize;
    private final int numberOfIterationsAG;

    private final int numberOfIterationsTabu;
    private final int tabuListMaxSize;

    public Test() {
        startingTemperature = 10;
        numberOfIterationsRS = 1000;
        coolingRate = 0.99;

        mutationRate = 0.015;
        arenaSize = 5;
        numberOfIterationsAG = 100;
        populationSize = 50;

        tabuListMaxSize = 3;
        numberOfIterationsTabu = 1000;
    }

    public Test(double startingTemperature,
                double numberOfIterationsRS,
                double coolingRate,
                double mutationRate,
                int arenaSize,
                int populationSize,
                int numberOfIterationsAG,
                int numberOfIterationsTabu,
                int tabuListMaxSize) {
        this.startingTemperature = startingTemperature;
        this.numberOfIterationsRS = numberOfIterationsRS;
        this.coolingRate = coolingRate;
        this.mutationRate = mutationRate;
        this.arenaSize = arenaSize;
        this.populationSize = populationSize;
        this.numberOfIterationsAG = numberOfIterationsAG;
        this.numberOfIterationsTabu = numberOfIterationsTabu;
        this.tabuListMaxSize = tabuListMaxSize;
    }

    private double[] testRS(String file) {
        cities = loadFile(file);
        Route route = new Route(cities);
        RS rs = new RS(route, startingTemperature, numberOfIterationsRS, coolingRate);
        long startTime = System.nanoTime();
        double bestDistance = rs.run();
        long stopTime = System.nanoTime();
        return new double[]{bestDistance, stopTime - startTime};
    }

    private double[] testAG(String file) {
        cities = loadFile(file);
        Route route = new Route(cities);
        AG ag = new AG(route, mutationRate, arenaSize, numberOfIterationsAG, populationSize);
        long startTime = System.nanoTime();
        double bestDistance = ag.run();
        long stopTime = System.nanoTime();
        return new double[]{bestDistance, stopTime - startTime};
    }

    private double[] testTabu(String file) {
        cities = loadFile(file);
        Route route = new Route(cities);
        Tabu tabu = new Tabu(route, numberOfIterationsTabu, tabuListMaxSize);
        long startTime = System.nanoTime();
        double bestDistance = tabu.run();
        long stopTime = System.nanoTime();
        return new double[]{bestDistance, stopTime - startTime};
    }

    public void run(int nbTests) {

        for (int i = 10; i <= 10; i += 10) {
            String file = "data/cities" + i + ".csv";
            for (int j = 0; j < nbTests; j++) {
                double[] res1;
                double[] res2;
                double[] res3;

                res1 = testAG(file);
                res2 = testRS(file);
                res3 = testTabu(file);

                writeToFile("data/ag.csv", i + ";" + res1[0] + ";" + res1[1] / 1000 + "\n");
                writeToFile("data/rs.csv", i + ";" + res2[0] + ";" + res2[1] / 1000 + "\n");
                writeToFile("data/tabu.csv", i + ";" + res3[0] + ";" + res3[1] / 1000 + "\n");
            }
        }
    }


}
