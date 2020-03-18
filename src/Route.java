import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Route {

    private ArrayList<City> cities = new ArrayList<City>();
    private ArrayList<City> previousCities = new ArrayList<>();


    public String toString() {
        return Arrays.toString(cities.toArray());
    }

    public Route(Route route) {
        this.cities.addAll(route.cities);
    }

    public Route(ArrayList<City> cities) {
        this.cities.addAll(cities);
    }

    public ArrayList<City> getCities() {
        return cities;
    }

    public double getTotalDistance() {
        int citiesSize = this.cities.size();
        double distTotal = 0;
        for (int i = 0; i < citiesSize - 1; i++) {
            distTotal += this.cities.get(i).measureDistance(this.cities.get(i + 1));
        }
        distTotal += this.cities.get(citiesSize - 1).measureDistance(this.cities.get(0));
        return distTotal;
    }

    public void swapCities() {
        int a = generateRandomIndex();
        int b = generateRandomIndex();
        previousCities = cities;
        City x = cities.get(a);
        City y = cities.get(b);
        cities.set(a, y);
        cities.set(b, x);
    }

    public void revertSwap() {
        cities = previousCities;
    }

    private int generateRandomIndex() {
        return (int) (Math.random() * cities.size());
    }
}
