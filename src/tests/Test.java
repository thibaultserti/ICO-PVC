package tests;

import algos.*;
import conf.Defaults;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import static conf.Settings.loadFile;
import static conf.Settings.writeToFile;

// to execute this class you need to have all cities10.csv to cities200.csv downloaded
public class Test {
    private static ArrayList<City> cities = new ArrayList<>();

    private double startingTemperature;
    private int numberOfIterationsRS;
    private double coolingRate;

    private double mutationRate;
    private int arenaSize;
    private int populationSize;
    private int numberOfIterationsAG;

    private int numberOfIterationsTabu;
    private int tabuListMaxSize;

    private final static int nbIterMaxRS = 100000;
    private final static int nbIterMaxAG = 1000;
    private final static int nbIterMaxTabu = 200;


    public Test() {
        startingTemperature = Defaults.startingTemperature;
        numberOfIterationsRS = Defaults.numberOfIterationsRS;
        coolingRate = Defaults.coolingRate;

        mutationRate = Defaults.mutationRate;
        arenaSize = Defaults.arenaSize;
        numberOfIterationsAG = Defaults.numberOfIterationsAG;
        populationSize = Defaults.populationSize;

        tabuListMaxSize = Defaults.tabuListMaxSize;
        numberOfIterationsTabu = Defaults.numberOfIterationsTabu;
    }

    public Test(double startingTemperature, int numberOfIterationsRS, double coolingRate, double mutationRate,
                int arenaSize, int populationSize, int numberOfIterationsAG, int numberOfIterationsTabu,
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
        coolingRate = (numberOfIterationsRS - 1) /100.;
        cities = loadFile(file, false);
        Route route = new Route(cities);
        RS rs = new RS(route, startingTemperature, numberOfIterationsRS, coolingRate);
        long startTime = System.nanoTime();
        double bestDistance = rs.run(false);
        long stopTime = System.nanoTime();
        return new double[]{bestDistance, stopTime - startTime};
    }

    private double[] testAG(String file) {
        cities = loadFile(file, false);
        Route route = new Route(cities);
        AG ag = new AG(route, mutationRate, arenaSize, numberOfIterationsAG, populationSize);
        long startTime = System.nanoTime();
        double bestDistance = ag.run(false);
        long stopTime = System.nanoTime();
        return new double[]{bestDistance, stopTime - startTime};
    }

    private double[] testTabu(String file) {
        cities = loadFile(file, false);
        Route route = new Route(cities);
        Tabu tabu = new Tabu(route, numberOfIterationsTabu, tabuListMaxSize);
        long startTime = System.nanoTime();
        double bestDistance = tabu.run(false);
        long stopTime = System.nanoTime();
        return new double[]{bestDistance, stopTime - startTime};
    }

    public void createDataset(int tailleDataset) {
        writeToFile("tests/data/dataset.csv", "algo;taillePVC;nbIterationAlgo;param1;param2;param3;distanceOpt;tpsMs\n");

        new Thread(() -> {
            for (int i = 0; i < tailleDataset; i++) {
                numberOfIterationsAG = (int) (100 + Math.random() * 901);
                populationSize = (int) (2 + Math.random() * 19);
                arenaSize = (int) (2 + Math.random() * 14);
                mutationRate = Math.random() * 0.1;
                int nbCities = (int) (1 + Math.random() * 10) * 10;
                String file = "data/cities" + nbCities + ".csv";
                System.out.println("Pourcentage d'avancement AG : " + ((double) i / (double) tailleDataset) * 100 + "%");
                double[] res;
                res = testAG(file);
                String s = "ag;" + nbCities + ";" + numberOfIterationsAG + ";" + populationSize + ";" + arenaSize + ";"
                        + mutationRate + ";" + res[0] + ";" + res[1] / 1000000 + "\n";
                writeToFile("tests/data/dataset.csv", s);
            }
        }).start();

        new Thread(() -> {
            for (int i = 0; i < tailleDataset; i++) {
                numberOfIterationsRS = (int) (100 + Math.random() * 9901);
                startingTemperature = (int) (10 + Math.random() * 901);
                coolingRate = 0.98 + (Math.random() * 0.02);
                int nbCities = (int) (1 + Math.random() * 10) * 10;
                String file = "data/cities" + nbCities + ".csv";
                System.out.println("Pourcentage d'avancement RS : " + ((double) i / (double) tailleDataset) * 100 + "%");
                double[] res;
                res = testAG(file);
                String s = "rs;" + nbCities + ";" + numberOfIterationsAG + ";" + startingTemperature + ";" + coolingRate + ";"
                        + "NaN" + ";" + res[0] + ";" + res[1] / 1000000 + "\n";
                writeToFile("tests/data/dataset.csv", s);
            }
        }).start();
        new Thread(() -> {
            for (int i = 0; i < tailleDataset; i++) {
                numberOfIterationsTabu = (int) (100 + Math.random() * 101);
                tabuListMaxSize = (int) (1 + Math.random() * 9);
                int nbCities = (int) (1 + Math.random() * 10) * 10;
                String file = "data/cities" + nbCities + ".csv";
                System.out.println("Pourcentage d'avancement Tabu : " + ((double) i / (double) tailleDataset) * 100 + "%");
                double[] res;
                res = testAG(file);
                String s = "tabu;" + nbCities + ";" + numberOfIterationsAG + ";" + tabuListMaxSize + ";" + "NaN" + ";"
                        + "NaN" + ";" + res[0] + ";" + res[1] / 1000000 + "\n";
                writeToFile("tests/data/dataset.csv", s);
            }
        }).start();
    }


    public void run(int nbTests) {
        try {
            Process proc = Runtime.getRuntime().exec("rm -f tests/data/ag.csv tests/data/rs.csv tests/data/tabu.csv");
            BufferedReader stdInput = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            String s = null;
            while ((s = stdInput.readLine()) != null) {
                System.out.println(s);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        writeToFile("tests/data/ag.csv", "taillePVC;nbIterationAlgo;distanceOpt;tpsMs\n");
        writeToFile("tests/data/rs.csv", "taillePVC;nbIterationAlgo;distanceOpt;tpsMs\n");
        writeToFile("tests/data/tabu.csv", "taillePVC;nbIterationAlgo;distanceOpt;tpsMs\n");

        new Thread(() -> {
            for (int i = 10; i <= 200; i += 10) {
                String file = "data/cities" + i + ".csv";
                for (numberOfIterationsAG = Defaults.numberOfIterationsAG; numberOfIterationsAG <= nbIterMaxAG; numberOfIterationsAG += 100) {
                    for (int j = 0; j < nbTests; j++) {
                        System.out.println("Pourcentage d'avancement AG : " + i + "/200 " + numberOfIterationsAG + "/"
                                + nbIterMaxAG + " " + j + "/" + nbTests);
                        double[] res;
                        res = testAG(file);
                        writeToFile("tests/data/ag.csv",
                                i + ";" + numberOfIterationsAG + ";" + res[0] + ";" + res[1] / 1000000 + "\n");
                    }
                }
            }
        }).start();
        new Thread(() -> {
            for (int i = 10; i <= 200; i += 10) {
                String file = "data/cities" + i + ".csv";
                for (numberOfIterationsRS = Defaults.numberOfIterationsRS; numberOfIterationsRS <= nbIterMaxRS; numberOfIterationsRS += 1000) {
                    for (int j = 0; j < nbTests; j++) {
                        System.out.println("Pourcentage d'avancement RS : " + i + "/200 " + numberOfIterationsRS + "/"
                                + nbIterMaxRS + " " + j + "/" + nbTests);
                        double[] res;
                        res = testRS(file);
                        writeToFile("tests/data/rs.csv",
                                i + ";" + numberOfIterationsRS + ";" + res[0] + ";" + res[1] / 1000000 + "\n");
                    }
                }
            }
        }).start();
        new Thread(() -> {
            for (int i = 10; i <= 100; i += 10) {
                String file = "data/cities" + i + ".csv";
                for (numberOfIterationsTabu = Defaults.numberOfIterationsTabu; numberOfIterationsTabu <= nbIterMaxTabu; numberOfIterationsTabu += 10) {
                    for (int j = 0; j < nbTests; j++) {
                        System.out.println("Pourcentage d'avancement Tabu : " + i + "/100 " + numberOfIterationsTabu
                                + "/" + nbIterMaxTabu + " " + j + "/" + nbTests);
                        double[] res;
                        res = testTabu(file);
                        writeToFile("tests/data/tabu.csv",
                                i + ";" + numberOfIterationsTabu + ";" + res[0] + ";" + res[1] / 1000000 + "\n");
                    }
                }
            }
        }).start();
    }

}
