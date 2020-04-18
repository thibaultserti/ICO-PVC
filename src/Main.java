import algos.*;
import gui.GUI;
import tests.Test;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;

import static conf.Settings.loadFile;

public class Main {
    private static ArrayList<City> cities = new ArrayList<City>();

    public static void testRS() {
        System.out.println("Test de l'algorithme Recuit-Simulé :");
        Route route = new Route(cities);
        System.out.println(cities);
        RS rs = new RS(route);
        double bestDistance = rs.run();
        System.out.println("La meilleure distance trouvée est : " + bestDistance);
    }

    public static void testTabu() {
        System.out.println("Test de l'algorithme Tabu :");
        Route route = new Route(cities);
        System.out.println(cities);
        Tabu tabu = new Tabu(route);
        double bestDistance = tabu.run();
        System.out.println("La meilleure distance trouvée est : " + bestDistance);
    }

    public static void testAG() {
        System.out.println("Test de l'algorithme Génétique :");
        Route route = new Route(cities);
        System.out.println(cities);
        AG ag = new AG(route);
        double bestDistance = ag.run();
        System.out.println("La meilleure distance trouvée est : " + bestDistance);
    }

    public static void main(String[] args) {
        if ((args.length != 0) && Arrays.asList(args).contains("--gui")) {
            JFrame GUI = new GUI();
        } else if ((args.length != 0) && Arrays.asList(args).contains("--benchmark")){
            Test test = new Test();
            test.run(10);
        }
        else {
            cities = loadFile("data/cities.csv");
            //testAG();
            //testRS();
            //*testTabu();
        }
    }

}
