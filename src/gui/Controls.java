package gui;

import algos.*;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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
                if (algo1.isSelected()) {
                    runTabou();
                } else if (algo2.isSelected()) {
                    runRS();
                } else {
                    runAG();
                }
            }

            public void runTabou() {
                Route route = new Route(cities);
                Taboo taboo = new Taboo(route);
                double bestDistance = taboo.run();
                Route bestSolution = taboo.getBestSolution();
                labelBestDistance.setText("<html> Meilleure distance trouvée : " + bestDistance + "</html>");
                labelBestSolution.setText("<html>Meilleure solution trouvée : " + bestSolution + "</html>");
                setRoute(bestSolution);
            }
            public void runAG() {
                Route route = new Route(cities);
                AG ag = new AG(route);
                double bestDistance = ag.run();
                Route bestSolution = ag.getBestSolution();
                labelBestDistance.setText("<html> Meilleure distance trouvée : " + bestDistance + "</html>");
                labelBestSolution.setText("<html>Meilleure solution trouvée : " + bestSolution + "</html>");
                setRoute(bestSolution);
            }
            public void runRS() {
                Route route = new Route(cities);
                RS rs = new RS(route);
                double bestDistance = rs.run();
                Route bestSolution = rs.getBestSolution();
                labelBestDistance.setText("<html> Meilleure distance trouvée : " + bestDistance + "</html>");
                labelBestSolution.setText("<html>Meilleure solution trouvée : " + bestSolution + "</html>");
                setRoute(bestSolution);
            }
        });

        loadButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                cities = loadFile("cities.csv");
                setCities(cities);
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
