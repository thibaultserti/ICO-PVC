package gui;

import javax.swing.JFrame;

public class GUI extends JFrame {
    public GUI(){
        this.setTitle("Problème du voyageur de commerce");
        this.setSize(1000, 1000);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        RoadMap roadMap = new RoadMap();
        this.setContentPane(roadMap);

        this.setVisible(true);
    }
    public static void main(String[] args){
        JFrame GUI = new GUI();


    }
}