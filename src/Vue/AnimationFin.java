package Vue;

import javax.swing.*;
import java.awt.*;
import java.util.ResourceBundle;

/**
 * Created by ANTOINE on 13/05/2016.
 *
 */
public class AnimationFin extends JFrame{

    public AnimationFin() {

        ResourceBundle texteInternational = ResourceBundle.getBundle("traductions.Animation");
        String fin = texteInternational.getString("fin");

        try {

            JPanel contentPane = (JPanel) getContentPane();
            contentPane.setBackground(Color.LIGHT_GRAY);
            contentPane.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
            setSize(new Dimension(400, 300));
            setTitle(fin);
            // add the header label
            JLabel headerLabel = new JLabel();
            headerLabel.setFont(new java.awt.Font("Comic Sans MS", Font.BOLD, 16));
            headerLabel.setText(fin);
            headerLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
            contentPane.add(headerLabel);

            contentPane.add(Box.createRigidArea(new Dimension(0, 30)));
            // add the image label
            ImageIcon ii = new ImageIcon("ressources/images/fin.gif");
            JLabel imageLabel = new JLabel();
            imageLabel.setIcon(ii);
            imageLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);


            contentPane.add(imageLabel);
            // show it
            this.setLocationRelativeTo(null);
            this.setAlwaysOnTop(true);
            this.setUndecorated(true);
            this.setVisible(true);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}