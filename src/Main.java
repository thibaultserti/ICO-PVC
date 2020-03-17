import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
    ArrayList<City> cities = new ArrayList<City>();
    public Main() {
        try {
            String path = "../cities.csv";
            BufferedReader file_source = new BufferedReader(new FileReader(path));
            String s;
            int i = 1;

            while ((s = file_source.readLine()) != null) {
                if (i > 1) {
                    String[] tab = s.split(",");
                    this.cities.add(new City(tab[0], Double.parseDouble(tab[1]),Double.parseDouble(tab[2])));
                }
                i++;
            }
            file_source.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error File not found !");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

    }
}
