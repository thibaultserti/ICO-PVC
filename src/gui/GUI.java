package gui;

import javax.swing.*;
import java.awt.*;

public class GUI extends JFrame {
    private static final int WIDTH = 1300;
    private static final int HEIGHT = 900;

    public GUI() {
        this.setTitle("Problème du voyageur de commerce");
        this.setSize(WIDTH, HEIGHT);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        Image icon = Toolkit.getDefaultToolkit().getImage("img/france.png");
        //System.out.println(System.getProperty("user.dir"));
        this.setIconImage(icon);

        RoadMap roadMap = new RoadMap();
        Controls controls = new Controls(roadMap);

        JPanel content = new JPanel();
        content.setPreferredSize(new Dimension(1400, 1000));
        content.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 10;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        content.add(roadMap, gbc);
        gbc.gridx = 10;
        gbc.gridy = 0;
        gbc.gridwidth = 4;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        content.add(controls, gbc);


        this.setContentPane(content);
        this.setVisible(true);

    }

}