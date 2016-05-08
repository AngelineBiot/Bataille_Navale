package Modele;

/**
 * Created by ANTOINE on 08/05/2016.
 */

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * Created by ANTOINE on 05/05/2016.
 */
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.swing.JFrame;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

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
                        URL url = DemoMP3.class.getResource("2952.mp3");
                        // exemple avec autre dossier : URL url = DemoMP3.class.getResource("/musix/othermusic.mp3");
                        try (InputStream audioIn = url.openStream()) {
                            Player clip = new Player(audioIn);
                            clip.play();
                        } catch (IOException | JavaLayerException e1) {
                            e1.printStackTrace();
                        }
                    };

                }.start();
            }
        });

        frame.setSize(300, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        frame.setVisible(true); // affichage donc ouverture

    }

}