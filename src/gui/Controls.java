package gui;

import algos.City;
import algos.Route;
import algos.Taboo;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Controls extends JPanel {
    private JRadioButton algo1, algo2, algo3;
    private RoadMap roadMap;

    public Controls(RoadMap roadMap) {
        super();
        this.roadMap = roadMap;
        // Titre
        this.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black),
                "Options de configuration", TitledBorder.CENTER, TitledBorder.TOP, new Font("Arial", Font.BOLD, 20)));
        this.setPreferredSize(new Dimension(400, 1000));

        // Boutons radios
        JPanel panAlgos = new JPanel();
        panAlgos.setBorder(BorderFactory.createTitledBorder("Choix des algorithmes"));
        panAlgos.setPreferredSize(new Dimension(350, 150));
        panAlgos.setLayout(new GridLayout(3, 1));
        algo1 = new JRadioButton("Tabou");
        algo1.setSelected(true);
        algo2 = new JRadioButton("Recuit-Simulé");
        algo3 = new JRadioButton("Algorithme génétique");

        ButtonGroup bg = new ButtonGroup();
        bg.add(algo1);
        bg.add(algo2);
        bg.add(algo3);
        panAlgos.add(algo1);
        panAlgos.add(algo2);
        panAlgos.add(algo3);
        this.add(panAlgos, BorderLayout.NORTH);
        // Boutons run
        JButton runButton = new JButton("Lancement de l'algorithme");
        this.add(runButton);

        // Labels
        JLabel labelBestDistance = new JLabel("Meilleure distance trouvée : -------");
        JLabel labelBestSolution = new JLabel("Meilleure solution trouvée : -------");
        this.add(labelBestDistance);
        this.add(labelBestSolution);

        // Listener bouton
        runButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                if (algo1.isSelected()) {
                    runTabou();
                } else if (algo2.isSelected()) {
                    //runAG();
                } else {
                    //runRS();
                }

            }

            public void runTabou() {
                ArrayList<City> cities = new ArrayList<City>();
                cities.add(new City("Brest", -4.498893, 48.406102));
                cities.add(new City("Paris", 2.3752, 48.845));
                cities.add(new City("Bordeaux", -0.587876, 44.853383));
                Route route = new Route(cities);
                Taboo taboo = new Taboo(route);
                double bestDistance = taboo.run();
                Route bestSolution = taboo.getBestSolution();
                labelBestDistance.setText("Meilleure distance trouvée : " + bestDistance);
                labelBestSolution.setText("Meilleure solution trouvée : " + bestSolution);
                setRoute(route);
            }

        });

    }

    public void setRoute(Route route) {
        this.roadMap.setRoute(route);
        this.roadMap.repaint();
    }

    public void setCities(ArrayList<City> cities) {
        this.roadMap.addCities(cities);
        this.roadMap.repaint();
    }

}
