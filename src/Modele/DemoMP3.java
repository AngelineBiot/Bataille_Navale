package Modele;

/**
 * Created by ANTOINE on 08/05/2016.
 *
 */

//import javazoom.jlme.util.Player;

import javazoom.jl.player.Player;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;


import javax.swing.JFrame;

import static javax.swing.JFrame.EXIT_ON_CLOSE;


public class DemoMP3 {

    public static void main(String[] args) {

        JFrame frame = new JFrame("Démo");

        // pour l'exécuter au moment ou la fenêtre s'ouvre
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                // on doit utiliser un thread pour éviter de bloquer l'IHM
                new Thread() {

                    public void run() {
                        //URL url = DemoMP3.class.getResource("ressource/sons/Slipknot.mp3");
                        try {
                            FileInputStream fis = new FileInputStream("ressources/sons/eau.mp3");
                            //on laisse Slipknot pour compiler en attendant le bon mp3 (2952.mp3)
                            Player fil = new Player(fis);
                            fil.play();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }.start();
            }
        });

        frame.setSize(300, 300);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        frame.setVisible(true); // affichage donc ouverture

    }

}
