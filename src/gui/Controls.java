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

    public Controls() {
        super();
        this.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black),
                "Options de configuration", TitledBorder.CENTER, TitledBorder.TOP, new Font("Arial", Font.BOLD, 20)));
        this.setPreferredSize(new Dimension(400, 1000));


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

        JButton runButton = new JButton("Lancement de l'algorithme");
      
        this.add(runButton);

    }

}
