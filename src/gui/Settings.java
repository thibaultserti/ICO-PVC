package gui;

import algos.City;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Settings {
    public static ArrayList<City> loadFile(String path) {
        ArrayList<City> cities = new ArrayList<City>();
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
        return cities;
    }
}
