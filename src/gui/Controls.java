package gui;

import algos.*;

import javax.swing.*;
import javax.swing.border.TitledBorder;
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

    public Controls(RoadMap roadMap) {
        super();
        this.roadMap = roadMap;
        // Titre
        this.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black),
                "Options de configuration", TitledBorder.CENTER, TitledBorder.TOP, new Font("Arial", Font.BOLD, 20)));
        this.setPreferredSize(new Dimension(400, 1000));

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
        JLabel labelBestDistance = new JLabel("Meilleure distance trouvée : -------");
        JLabel labelBestSolution = new JLabel("Meilleure solution trouvée : -------");
        this.add(labelBestDistance);
        this.add(labelBestSolution);

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
                Process proc = Runtime.getRuntime().exec("bash cities.sh");
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
