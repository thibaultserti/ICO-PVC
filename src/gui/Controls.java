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
import java.util.StringJoiner;

import static gui.Settings.loadFile;


public class Controls extends JPanel {
    private JRadioButton algo1RadioButton, algo2RadioButton, algo3RadioButton;
    private RoadMap roadMap;
    private ArrayList<City> cities;
    private int nbCities = 10;

    // Tabu paramètres d'initialisation
    int numberOfIterationsTabu = 500;
    int tabuListMaxSize = 3;

    // Recuit-Simulé paramètres d'initialisation
    double startingTemperature = 10;
    int numberOfIterationsRS = 1000;
    double coolingRate = 0.99;

    // Génétique paramètres d'initialisation
    double mutationRate = 0.015;
    int arenaSize = 5;
    int numberOfIterationsAG = 100;
    int populationSize = 50;

    public Controls(RoadMap roadMap) {
        super();
        this.roadMap = roadMap;

        // Titre
        this.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black),
                "Options de configuration", TitledBorder.CENTER, TitledBorder.TOP, new Font("Arial", Font.BOLD, 20)));
        this.setPreferredSize(new Dimension(400, 800));

        // Choix du nombre de villes
        JPanel panNbCities = new JPanel();
        panNbCities.setBorder(BorderFactory.createTitledBorder("Nombre de villes"));
        panNbCities.setPreferredSize(new Dimension(350, 100));

        JSlider slideNbCities = new JSlider();
        JLabel labelNbCities = new JLabel("Nombre de villes : " + nbCities);
        slideNbCities.setMaximum(20);
        slideNbCities.setMinimum(1);
        slideNbCities.setValue((int) nbCities/10);
        slideNbCities.setPaintTicks(true);
        slideNbCities.setMinorTickSpacing(1);
        slideNbCities.setMajorTickSpacing(2);
        panNbCities.add(labelNbCities);
        panNbCities.add(slideNbCities);
        this.add(panNbCities, BorderLayout.NORTH);

        // Choix des données
        JPanel panData = new JPanel();
        panData.setBorder(BorderFactory.createTitledBorder("Choix des données"));
        panData.setPreferredSize(new Dimension(350, 100));
        panData.setLayout(new GridLayout(1, 2));
        JButton loadButton = new JButton("<html>Charger les données depuis le CSV</html>");
        JButton scriptButton = new JButton("<html>Exécuter le script de récupération de données</html>");
        panData.add(scriptButton);
        panData.add(loadButton);
        this.add(panData, BorderLayout.NORTH);

        // Choix de l'algorithme
        JPanel panAlgos = new JPanel();
        panAlgos.setBorder(BorderFactory.createTitledBorder("Choix des algorithmes"));
        panAlgos.setPreferredSize(new Dimension(350, 150));
        panAlgos.setLayout(new GridLayout(3, 1));
        algo1RadioButton = new JRadioButton("Algorithme Tabou");
        algo1RadioButton.setSelected(true);
        algo2RadioButton = new JRadioButton("Algorithme Recuit-Simulé");
        algo3RadioButton = new JRadioButton("Algorithme Génétique");

        ButtonGroup bg = new ButtonGroup();
        bg.add(algo1RadioButton);
        bg.add(algo2RadioButton);
        bg.add(algo3RadioButton);
        panAlgos.add(algo1RadioButton);
        panAlgos.add(algo2RadioButton);
        panAlgos.add(algo3RadioButton);
        this.add(panAlgos, BorderLayout.NORTH);

        // -----Configurations des algorithmes-----
        JPanel panConfig = new JPanel();
        panConfig.setBorder(BorderFactory.createTitledBorder("Configuration des algorithmes"));
        panConfig.setPreferredSize(new Dimension(350, 240));
        this.add(panConfig, BorderLayout.NORTH);

        // Tabu
        // Slide Nb iterations
        JLabel labelNbIterationsTabu = new JLabel("Nombre d'itérations : " + numberOfIterationsTabu);
        JSlider slideNbIterationsTabu = new JSlider();
        slideNbIterationsTabu.setMaximum(1000);
        slideNbIterationsTabu.setMinimum(100);
        slideNbIterationsTabu.setValue(numberOfIterationsTabu);
        slideNbIterationsTabu.setPaintTicks(true);
        slideNbIterationsTabu.setMinorTickSpacing(50);
        slideNbIterationsTabu.setMajorTickSpacing(100);
        slideNbIterationsTabu.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent event) {
                labelNbIterationsTabu.setText("Nombre d'itérations : " + ((JSlider) event.getSource()).getValue());
                numberOfIterationsTabu = ((JSlider) event.getSource()).getValue();
            }
        });

        // Slide TabuListMaxSize
        JLabel labelTabuListMaxSize = new JLabel("Taille max tabou : " + tabuListMaxSize);
        JSlider slideTabuListMaxSize = new JSlider();
        slideTabuListMaxSize.setMaximum(10);
        slideTabuListMaxSize.setMinimum(0);
        slideTabuListMaxSize.setValue((int) tabuListMaxSize);
        slideTabuListMaxSize.setPaintTicks(true);
        slideTabuListMaxSize.setMinorTickSpacing(1);
        slideTabuListMaxSize.setMajorTickSpacing(2);
        slideTabuListMaxSize.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent event) {
                labelTabuListMaxSize.setText("Taille max tabou : " + ((JSlider) event.getSource()).getValue());
                tabuListMaxSize = ((JSlider) event.getSource()).getValue();
            }
        });

        // Recuit-Simulé
        // Slide Nb iterations
        JLabel labelNbIterationsRS = new JLabel("Nombre d'itérations : " + numberOfIterationsRS);
        JSlider slideNbIterationsRS = new JSlider();
        slideNbIterationsRS.setMaximum(10000);
        slideNbIterationsRS.setMinimum(1000);
        slideNbIterationsRS.setValue(numberOfIterationsRS);
        slideNbIterationsRS.setPaintTicks(true);
        slideNbIterationsRS.setMinorTickSpacing(500);
        slideNbIterationsRS.setMajorTickSpacing(1000);
        slideNbIterationsRS.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent event) {
                labelNbIterationsRS.setText("Nombre d'itérations : " + ((JSlider) event.getSource()).getValue());
                numberOfIterationsRS = ((JSlider) event.getSource()).getValue();
            }
        });

        // Slide Température initiale
        JLabel labelStartingTemperature = new JLabel("Température initiale : " + (int) startingTemperature);
        JSlider slideStartingTemperature = new JSlider();
        slideStartingTemperature.setMaximum(50);
        slideStartingTemperature.setMinimum(0);
        slideStartingTemperature.setValue((int) startingTemperature);
        slideStartingTemperature.setPaintTicks(true);
        slideStartingTemperature.setPaintLabels(true);
        slideStartingTemperature.setMinorTickSpacing(5);
        slideStartingTemperature.setMajorTickSpacing(10);
        slideStartingTemperature.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent event) {
                labelStartingTemperature.setText("Température initiale : " + ((JSlider) event.getSource()).getValue());
                startingTemperature = ((JSlider) event.getSource()).getValue();
            }
        });

        // Slide Taux de refroidissement
        JLabel labelCoolingRate = new JLabel("Taux de refroidissement : " + coolingRate);
        JSlider slideCoolingRate = new JSlider();
        slideCoolingRate.setMaximum(1000);
        slideCoolingRate.setMinimum(950);
        slideCoolingRate.setValue(((int) (coolingRate * 1000)));
        slideCoolingRate.setPaintTicks(true);
        slideCoolingRate.setMinorTickSpacing(5);
        slideCoolingRate.setMajorTickSpacing(10);
        slideCoolingRate.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent event) {
                labelCoolingRate.setText("Taux de refroidissement : " + (double) ((JSlider) event.getSource()).getValue() / 1000.0);
                coolingRate = (double) ((JSlider) event.getSource()).getValue() / 1000.0;
            }
        });

        // Génétique
        // Slide Nb iterations
        JLabel labelNbIterationsAG = new JLabel("Nombre d'itérations : " + numberOfIterationsAG);
        JSlider slideNbIterationsAG = new JSlider();
        slideNbIterationsAG.setMaximum(1000);
        slideNbIterationsAG.setMinimum(100);
        slideNbIterationsAG.setValue(numberOfIterationsAG);
        slideNbIterationsAG.setPaintTicks(true);
        slideNbIterationsAG.setMinorTickSpacing(50);
        slideNbIterationsAG.setMajorTickSpacing(100);
        slideNbIterationsAG.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent event) {
                labelNbIterationsAG.setText("Nombre d'itérations : " + ((JSlider) event.getSource()).getValue());
                numberOfIterationsAG = ((JSlider) event.getSource()).getValue();
            }
        });

        // Slide Taille de la population
        JLabel labelPop = new JLabel("Taille de la population : " + populationSize);
        JSlider slidePop = new JSlider();
        slidePop.setMaximum(200);
        slidePop.setMinimum(50);
        slidePop.setValue(populationSize);
        slidePop.setPaintTicks(true);
        slidePop.setMinorTickSpacing(10);
        slidePop.setMajorTickSpacing(50);
        slidePop.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent event) {
                labelPop.setText("Taille de la population : " + ((JSlider) event.getSource()).getValue());
                populationSize = ((JSlider) event.getSource()).getValue();
            }
        });

        // Slide Taille de l'arène
        JLabel labelArenaSize = new JLabel("Taille de l'arène : " + arenaSize);
        JSlider slideArenaSize = new JSlider();
        slideArenaSize.setMaximum(10);
        slideArenaSize.setMinimum(2);
        slideArenaSize.setValue(arenaSize);
        slideArenaSize.setPaintTicks(true);
        slideArenaSize.setMinorTickSpacing(1);
        slideArenaSize.setMajorTickSpacing(2);
        slideArenaSize.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent event) {
                labelArenaSize.setText("Taille de l'arène : " + ((JSlider) event.getSource()).getValue());
                arenaSize = ((JSlider) event.getSource()).getValue();
            }
        });

        // Slide Taux de mutation
        JLabel labelMutationRate = new JLabel("Taux de mutation : " + mutationRate);
        JSlider slideMutationRate = new JSlider();
        slideMutationRate.setMaximum(20);
        slideMutationRate.setMinimum(0);
        slideMutationRate.setValue((int) (mutationRate * 1000));
        slideMutationRate.setPaintTicks(true);
        slideMutationRate.setMinorTickSpacing(1);
        slideMutationRate.setMajorTickSpacing(5);
        slideMutationRate.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent event) {
                labelMutationRate.setText("Taux de mutation: " + (double) ((JSlider) event.getSource()).getValue() / 1000.0);
                mutationRate = (double) ((JSlider) event.getSource()).getValue() / 1000.0;
            }
        });

        // ------ Boutons run -------
        JButton runButton = new JButton("Lancement de l'algorithme");
        this.add(runButton);

        // --------- Résultats ----------
        JPanel panResult = new JPanel();
        panResult.setBorder(BorderFactory.createTitledBorder("Résultats"));
        panResult.setPreferredSize(new Dimension(380, 120));
        JLabel labelBestDistance = new JLabel("Meilleure distance trouvée : -------");
        JLabel labelBestSolution = new JLabel("Meilleure solution trouvée : -------");
        panResult.add(labelBestDistance);
        panResult.add(labelBestSolution);
        this.add(panResult, BorderLayout.NORTH);

        // Listener RadioBox choix des algorithmes

        // Algo Tabu
        algo1RadioButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                // On retire les slides précédentes
                panConfig.remove(labelMutationRate);
                panConfig.remove(slideMutationRate);
                panConfig.remove(labelArenaSize);
                panConfig.remove(slideArenaSize);
                panConfig.remove(labelNbIterationsAG);
                panConfig.remove(slideNbIterationsAG);
                panConfig.remove(labelPop);
                panConfig.remove(slidePop);
                panConfig.remove(labelNbIterationsRS);
                panConfig.remove(slideNbIterationsRS);
                panConfig.remove(labelStartingTemperature);
                panConfig.remove(slideStartingTemperature);
                panConfig.remove(labelCoolingRate);
                panConfig.remove(slideCoolingRate);
                panConfig.remove(labelNbIterationsTabu);
                panConfig.remove(slideNbIterationsTabu);
                panConfig.remove(labelTabuListMaxSize);
                panConfig.remove(slideTabuListMaxSize);

                // ajoute les nouvelles
                panConfig.add(labelNbIterationsTabu);
                panConfig.add(slideNbIterationsTabu);
                panConfig.add(labelTabuListMaxSize);
                panConfig.add(slideTabuListMaxSize);
                panConfig.updateUI();
            }
        });

        // Algo Recuit-Simulé
        algo2RadioButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                // On retire les slides précédentes
                panConfig.remove(labelMutationRate);
                panConfig.remove(slideMutationRate);
                panConfig.remove(labelArenaSize);
                panConfig.remove(slideArenaSize);
                panConfig.remove(labelNbIterationsAG);
                panConfig.remove(slideNbIterationsAG);
                panConfig.remove(labelPop);
                panConfig.remove(slidePop);
                panConfig.remove(labelNbIterationsRS);
                panConfig.remove(slideNbIterationsRS);
                panConfig.remove(labelStartingTemperature);
                panConfig.remove(slideStartingTemperature);
                panConfig.remove(labelCoolingRate);
                panConfig.remove(slideCoolingRate);
                panConfig.remove(labelNbIterationsTabu);
                panConfig.remove(slideNbIterationsTabu);
                panConfig.remove(labelTabuListMaxSize);
                panConfig.remove(slideTabuListMaxSize);

                //On ajoutes les nouvelles
                panConfig.add(labelNbIterationsRS);
                panConfig.add(slideNbIterationsRS);
                panConfig.add(labelStartingTemperature);
                panConfig.add(slideStartingTemperature);
                panConfig.add(labelCoolingRate);
                panConfig.add(slideCoolingRate);
                panConfig.updateUI();
            }
        });

        // Algo Génétique
        algo3RadioButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                // On retire les slides précédentes
                panConfig.remove(labelMutationRate);
                panConfig.remove(slideMutationRate);
                panConfig.remove(labelArenaSize);
                panConfig.remove(slideArenaSize);
                panConfig.remove(labelNbIterationsAG);
                panConfig.remove(slideNbIterationsAG);
                panConfig.remove(labelPop);
                panConfig.remove(slidePop);
                panConfig.remove(labelNbIterationsRS);
                panConfig.remove(slideNbIterationsRS);
                panConfig.remove(labelStartingTemperature);
                panConfig.remove(slideStartingTemperature);
                panConfig.remove(labelCoolingRate);
                panConfig.remove(slideCoolingRate);
                panConfig.remove(labelNbIterationsTabu);
                panConfig.remove(slideNbIterationsTabu);
                panConfig.remove(labelTabuListMaxSize);
                panConfig.remove(slideTabuListMaxSize);

                //On ajoutes les nouvelles
                panConfig.add(labelNbIterationsAG);
                panConfig.add(slideNbIterationsAG);
                panConfig.add(labelPop);
                panConfig.add(slidePop);
                panConfig.add(labelArenaSize);
                panConfig.add(slideArenaSize);
                panConfig.add(labelMutationRate);
                panConfig.add(slideMutationRate);
                panConfig.updateUI();
            }
        });


        // Listener bouton run
        runButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                Route bestSolution;

                if (algo1RadioButton.isSelected()) {
                    bestSolution = runTabu();
                } else if (algo2RadioButton.isSelected()) {
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

            public Route runTabu() {
                Route route = new Route(cities);
                Tabu Tabu = new Tabu(route, numberOfIterationsTabu, tabuListMaxSize);
                double bestDistance = Tabu.run();
                return Tabu.getBestSolution();
            }

            public Route runAG() {
                Route route = new Route(cities);
                AG ag = new AG(route, mutationRate, arenaSize, numberOfIterationsAG, populationSize);
                double bestDistance = ag.run();
                return ag.getBestSolution();
            }

            public Route runRS() {
                Route route = new Route(cities);
                RS rs = new RS(route, startingTemperature, numberOfIterationsRS, coolingRate);
                double bestDistance = rs.run();
                return rs.getBestSolution();
            }
        });

        // Listener bouton LoadFile
        loadButton.addActionListener(arg0 -> {
            cities = loadFile("data/cities.csv");
            setCities(cities);
        });

        // Listener bouton execute script
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


        // Listener Slider nombre de villes
        slideNbCities.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent event) {
                labelNbCities.setText("Nombre de villes : " + ((JSlider) event.getSource()).getValue()*10);
                nbCities = ((JSlider) event.getSource()).getValue() * 10;
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
