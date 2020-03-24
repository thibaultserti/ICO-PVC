package gui;

import algos.*;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringJoiner;

import static gui.Settings.loadFile;


public class Controls extends JPanel {
    private JRadioButton algo1, algo2, algo3;
    private RoadMap roadMap;
    private ArrayList<City> cities;
    private int nbCities = 7;

    public Controls(RoadMap roadMap) {
        super();
        this.roadMap = roadMap;

        // Titre
        this.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black),
                "Options de configuration", TitledBorder.CENTER, TitledBorder.TOP, new Font("Arial", Font.BOLD, 20)));
        this.setPreferredSize(new Dimension(400, 1000));

        // Choix du nombre de villes
        JPanel panNbCities = new JPanel();
        panNbCities.setBorder(BorderFactory.createTitledBorder("Nombre de villes"));
        panNbCities.setPreferredSize(new Dimension(350, 100));

        JSlider slideNbCities = new JSlider();
        JLabel labelNbCities = new JLabel("Nombre de villes : " + nbCities);
        slideNbCities.setMaximum(30);
        slideNbCities.setMinimum(5);
        slideNbCities.setValue(nbCities);
        slideNbCities.setPaintTicks(true);
        slideNbCities.setPaintLabels(true);
        slideNbCities.setMinorTickSpacing(1);
        slideNbCities.setMajorTickSpacing(5);
        slideNbCities.addChangeListener(new ChangeListener(){
            public void stateChanged(ChangeEvent event){
                labelNbCities.setText("Nombre de villes : " + ((JSlider)event.getSource()).getValue());
                nbCities = ((JSlider)event.getSource()).getValue();
            }
        });
        panNbCities.add(labelNbCities);
        panNbCities.add(slideNbCities);
        this.add(panNbCities,BorderLayout.NORTH);
        // Choix des données
        JPanel panData = new JPanel();
        panData.setBorder(BorderFactory.createTitledBorder("Choix des données"));
        panData.setPreferredSize(new Dimension(350, 100));
        panData.setLayout(new GridLayout(1, 2));
        JButton loadButton = new JButton("<html>Charger les données depuis le CSV</html>");
        JButton scriptButton = new JButton("<html>Exécuter le script de récupération de données</html>");
        panData.add(loadButton);
        panData.add(scriptButton);
        this.add(panData, BorderLayout.NORTH);

        // Boutons radios
        JPanel panAlgos = new JPanel();
        panAlgos.setBorder(BorderFactory.createTitledBorder("Choix des algorithmes"));
        panAlgos.setPreferredSize(new Dimension(350, 150));
        panAlgos.setLayout(new GridLayout(3, 1));
        algo1 = new JRadioButton("Algorithme Tabou");
        algo1.setSelected(true);
        algo2 = new JRadioButton("Algorithme Recuit-Simulé");
        algo3 = new JRadioButton("Algorithme Génétique");

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
        // Choix des données
        JPanel panResult = new JPanel();
        panResult.setBorder(BorderFactory.createTitledBorder("Résultats"));
        panResult.setPreferredSize(new Dimension(380, 300));
        JLabel labelBestDistance = new JLabel("Meilleure distance trouvée : -------");
        JLabel labelBestSolution = new JLabel("Meilleure solution trouvée : -------");
        panResult.add(labelBestDistance);
        panResult.add(labelBestSolution);
        this.add(panResult, BorderLayout.NORTH);

        // Listener bouton
        runButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                Route bestSolution;

                if (algo1.isSelected()) {
                    bestSolution = runTabou();
                } else if (algo2.isSelected()) {
                    bestSolution = runRS();
                } else {
                    bestSolution = runAG();
                }
                double bestDistance = bestSolution.getTotalDistance();
                // Pour rajouter des <br> entre chaque ville
                String[] bestSolution_ = bestSolution.toString().split(",");
                StringJoiner joiner = new StringJoiner(",");
                for (int i = 0; i < bestSolution_.length; i++) {
                    if (i % 5 == 4) {
                        joiner.add(bestSolution_[i] + "<br>");
                    } else {
                        joiner.add(bestSolution_[i]);
                    }
                }
                String bestSolution__ = joiner.toString();
                labelBestDistance.setText("<html> Meilleure distance trouvée : " + bestDistance + "</html>");
                labelBestSolution.setText("<html> Meilleure solution trouvée : -----------------------------<br>" + bestSolution__ + "</html>");
                setRoute(bestSolution);
            }

            public Route runTabou() {
                Route route = new Route(cities);
                Taboo taboo = new Taboo(route);
                double bestDistance = taboo.run();
                return taboo.getBestSolution();
            }

            public Route runAG() {
                Route route = new Route(cities);
                AG ag = new AG(route);
                double bestDistance = ag.run();
                return ag.getBestSolution();
            }

            public Route runRS() {
                Route route = new Route(cities);
                RS rs = new RS(route);
                double bestDistance = rs.run();
                return rs.getBestSolution();
            }
        });

        loadButton.addActionListener(arg0 -> {
            cities = loadFile("cities.csv");
            setCities(cities);
        });

        scriptButton.addActionListener(arg0 -> {
            try {
                Process proc = Runtime.getRuntime().exec("bash cities.sh --size " + nbCities);
                BufferedReader stdInput = new BufferedReader(new InputStreamReader(proc.getInputStream()));
                String s = null;
                while ((s = stdInput.readLine()) != null) {
                    System.out.println(s);
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
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
