package Vue;

import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

/**
 * Created by ANTOINE on 09/05/2016.
 *
 */
public class DemoAnimation extends JFrame {

    private DemoAnimation() {
        try {
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            JPanel contentPane = (JPanel) getContentPane();
            contentPane.setLayout(new BorderLayout());
            setSize(new Dimension(400, 300));
            setTitle("Raté");
            // add the header label
            JLabel headerLabel = new JLabel();
            headerLabel.setFont(new java.awt.Font("Comic Sans MS", Font.BOLD, 16));
            headerLabel.setText("Raté");
            contentPane.add(headerLabel, java.awt.BorderLayout.NORTH);
            // add the image label
            ImageIcon ii = new ImageIcon("ressources/images/eau.gif");
            JLabel imageLabel = new JLabel();
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

        new DemoAnimation();
    }

}