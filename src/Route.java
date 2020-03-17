import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Route {

    private ArrayList<City> cities = new ArrayList<City>();

    public String toString() {
        return Arrays.toString(cities.toArray());
    }

    //constructeurs
    //-->à partir d'une autre route
    public Route(Route route) {
        this.cities.addAll(route.cities);
    }

    //-->à partir d'une liste de villes qu'on mélange pour ne pas garder le même ordre
    public Route(ArrayList<City> cities) {
        this.cities.addAll(cities);
//mélanger aléatoirement les villes de la route
        Collections.shuffle(this.cities);
        this.cities.addAll(cities);
    }

    //get methods
    public ArrayList<City> getCities() {
        return cities;
    }

    public double getTotalDistance() {
        int citiesSize = this.cities.size();
        double distTotal = 0;
        for (int i = 0; i < citiesSize - 1; i++){
            distTotal += this.cities.get(i).measureDistance(this.cities.get(i+1));
        }
        distTotal += this.cities.get(citiesSize-1).measureDistance(this.cities.get(0));
        return distTotal;
    }
}
