import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
    private static ArrayList<City> cities = new ArrayList<City>();

    public static void loadFile(String path) {
        try {
            BufferedReader file_source = new BufferedReader(new FileReader(path));
            String s = file_source.readLine();
            do {
                System.out.println(s);
                String[] tab = s.split(",");
                cities.add(new City(tab[0], Double.parseDouble(tab[1]), Double.parseDouble(tab[2])));
            }
            while ((s = file_source.readLine()) != null);
            file_source.close();
            System.out.println("File opened succesfully");
        } catch (FileNotFoundException e) {
            System.out.println("Error File Not Found !");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

      
  
    public static void main(String[] args) {

        System.out.println("Test de l'algorithme Recuit-Simulé :");
        loadFile("cities.csv");
        Route route = new Route(cities);
        System.out.println(cities);
        RS rs = new RS(route);
        double bestDistance = rs.simulate();
        System.out.println("La meilleure distance trouvée est : " + bestDistance);
        double distanceOpt = (new Route(cities)).getTotalDistance();
        System.out.println("La distance optimale est : " + distanceOpt);

        Route route = new Route(cities);
        System.out.println(cities);
    }

}
