package Modele;

/**
 * Created by ANTOINE on 11/05/2016.
 */

import javax.swing.*;
import java.awt.*;
import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

/**
 * Created by ANTOINE on 09/05/2016.
 */
public class AnimationRate extends JFrame implements Runnable{
    Thread t;
    JPanel contentPane;
    JLabel imageLabel = new JLabel();
    JLabel headerLabel = new JLabel();


    public AnimationRate() {
        try {
            t=new Thread(this);
            t.start();
            contentPane = (JPanel) getContentPane();
            contentPane.setLayout(new BorderLayout());
            setSize(new Dimension(400, 300));
            setTitle("Raté");
            // add the header label
            headerLabel.setFont(new java.awt.Font("Comic Sans MS", Font.BOLD, 16));
            headerLabel.setText("Raté");
            contentPane.add(headerLabel, java.awt.BorderLayout.NORTH);
            // add the image label
            ImageIcon ii = new ImageIcon("ressources/images/rate.gif");
            imageLabel.setIcon(ii);
            contentPane.add(imageLabel, java.awt.BorderLayout.CENTER);
            // show it
            this.setLocationRelativeTo(null);
            this.setVisible(true);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }


    public static void main(String[] args) {

        new AnimationRate();
    }

    @Override
    public void run() {
        try {
            t.sleep(7000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        this.setVisible(false);

    }
}