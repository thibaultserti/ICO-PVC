import algos.*;
import gui.GUI;
import tests.Test;

import javax.swing.*;
import java.util.*;

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
        final Map<String, List<String>> params = new HashMap<>();
        List<String> options = null;
        for (int i = 0; i < args.length; i++) {
            final String a = args[i];

            if (a.charAt(0) == '-') {
                if (a.length() < 2) {
                    System.err.println("Error at argument " + a);
                    return;
                }

                options = new ArrayList<>();
                params.put(a.substring(1), options);
            }
            else if (options != null) {
                options.add(a);
            }
            else {
                System.err.println("Illegal parameter usage");
                return;
            }
        }

        if (params.containsKey("-gui")) {
            JFrame GUI = new GUI();
        } else if (params.containsKey("-benchmark")){
            int n = Integer.parseInt(params.get("-benchmark").get(0));
            Test test = new Test();
            test.run(n);
        }
        else {
            cities = loadFile("data/cities.csv");
            testAG();
            testRS();
            testTabu();
        }
    }

}
