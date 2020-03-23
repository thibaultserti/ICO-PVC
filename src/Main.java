import algos.*;
import gui.GUI;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Scanner;

import static gui.Settings.loadFile;

public class Main {
    private static ArrayList<City> cities = new ArrayList<City>();

    public static void testRS() {
        System.out.println("Test de l'algorithme Recuit-Simulé :");
        Route route = new Route(cities);
        System.out.println(cities);
        RS rs = new RS(route);
        double bestDistance = rs.run();
        System.out.println("La meilleure distance trouvée est : " + bestDistance);
        double distanceOpt = (new Route(cities)).getTotalDistance();
        System.out.println("La distance optimale est : " + distanceOpt);
    }

    public static void testTaboo() {
        System.out.println("Test de l'algorithme Tabou :");
        Route route = new Route(cities);
        System.out.println(cities);
        Taboo taboo = new Taboo(route);
        double bestDistance = taboo.run();
        System.out.println("La meilleure distance trouvée est : " + bestDistance);
        double distanceOpt = (new Route(cities)).getTotalDistance();
        System.out.println("La distance optimale est : " + distanceOpt);
    }

    public static void testAG() {
        System.out.println("Test de l'algorithme Génétique :");
        Route route = new Route(cities);
        System.out.println(cities);
        AG ag = new AG(route);
        double bestDistance = ag.run();
        System.out.println("La meilleure distance trouvée est : " + bestDistance);
        double distanceOpt = (new Route(cities)).getTotalDistance();
        System.out.println("La distance optimale est : " + distanceOpt);
    }

    public static void main(String[] args) {
        Scanner myObj = new Scanner(System.in);
        System.out.println("Voulez-vous lancer le programme en mode graphique (o/n) ?");
        String accept = myObj.nextLine();
        if (accept.equals("o") || accept.equals("y")) {
            JFrame GUI = new GUI();
        } else {
            cities = loadFile("cities.csv");
            //testAG();
            //testRS();
            //testTaboo();
        }
    }

}
