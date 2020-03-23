import algos.*;
import gui.GUI;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static ArrayList<City> cities = new ArrayList<City>();

    public static void loadFile(String path) {
        try {
            BufferedReader file_source = new BufferedReader(new FileReader(path));
            String s = file_source.readLine();
            System.out.println(s);
            String[] tab = s.split(",");

            while ((s = file_source.readLine()) != null) {
                System.out.println(s);
                tab = s.split(",");
                cities.add(new City(tab[0], Double.parseDouble(tab[1]), Double.parseDouble(tab[2])));
            }
            file_source.close();
            System.out.println("File opened successfully");
        } catch (FileNotFoundException e) {
            System.out.println("Error File Not Found !");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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
        //String accept = myObj.nextLine();
        //if (accept.equals("o") || accept.equals("y")) {
            JFrame GUI = new GUI();
       // } else {
            loadFile("cities.csv");
            //testAG();
            //testRS();
            //testTaboo();
        //}
    }

}
