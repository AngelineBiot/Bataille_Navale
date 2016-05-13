package Vue;

import javax.swing.*;
import java.awt.*;

/**
 * Created by ANTOINE on 13/05/2016.
 */
public class AnimationFin extends JFrame implements Runnable{
    Thread t;
    JPanel contentPane;
    JLabel imageLabel = new JLabel();
    JLabel headerLabel = new JLabel();

    public AnimationFin() {
        try {
            t=new Thread(this);
            t.start();
            contentPane = (JPanel) getContentPane();
            contentPane.setBackground(Color.LIGHT_GRAY);
            contentPane.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            contentPane.setLayout(new BorderLayout());
            setSize(new Dimension(400, 300));
            setTitle("FIN");
            // add the header label
            headerLabel.setFont(new java.awt.Font("Comic Sans MS", Font.BOLD, 16));
            headerLabel.setText("FIN");
            contentPane.add(headerLabel, java.awt.BorderLayout.NORTH);
            // add the image label
            ImageIcon ii = new ImageIcon("ressources/images/fin.gif");
            imageLabel.setIcon(ii);
            contentPane.add(imageLabel, java.awt.BorderLayout.CENTER);
            // show it
            this.setLocationRelativeTo(null);
            this.setAlwaysOnTop(true);
            this.setUndecorated(true);
            this.setVisible(true);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }


    public static void main(String[] args) {

        new AnimationFin();
    }

    @Override
    public void run() {
        try {
            t.sleep(4700);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        this.setVisible(false);

    }
}