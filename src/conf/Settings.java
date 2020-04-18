package conf;

import algos.City;

import java.io.*;
import java.util.ArrayList;

public class Settings {

    public static void writeToFile(String fileName, String text) {
        try {
            File file = new File(fileName);
            FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(text);
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<City> loadFile(String path, boolean... b) {
        ArrayList<City> cities = new ArrayList<City>();
        try {
            BufferedReader file_source = new BufferedReader(new FileReader(path));
            String s = file_source.readLine();
            String[] tab;

            while ((s = file_source.readLine()) != null) {
                if ((b.length == 0) || (b[0] == true)) {
                    System.out.println(s);
                }
                tab = s.split(",");
                cities.add(new City(tab[0], Double.parseDouble(tab[1]), Double.parseDouble(tab[2])));
            }
            file_source.close();
            if ((b.length == 0) || (b[0] == true)) {
                System.out.println("File opened successfully");
            }
        } catch (

        FileNotFoundException e) {
            System.out.println("Error File Not Found !");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return cities;
    }
}
