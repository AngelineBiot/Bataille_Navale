package Vue;

/**
 * Created by ANTOINE on 11/05/2016.
 *
 */
import java.awt.*;
import javax.swing.*;
import java.util.ResourceBundle;

public class AnimationCoule extends JFrame{

    public AnimationCoule() {

        ResourceBundle texteInternational = ResourceBundle.getBundle("traductions.Animation");
        String coule = texteInternational.getString("coule");

        try {

            JPanel contentPane = (JPanel) getContentPane();
            contentPane.setBackground(Color.LIGHT_GRAY);

            contentPane.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
            setSize(new Dimension(400, 300));
            setTitle(coule);
            // add the header label
            JLabel headerLabel = new JLabel();
            headerLabel.setFont(new java.awt.Font("Comic Sans MS", Font.BOLD, 16));
            headerLabel.setText(coule);
            headerLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
            contentPane.add(headerLabel);

            contentPane.add(Box.createRigidArea(new Dimension(0, 30)));
            // add the image label
            ImageIcon ii = new ImageIcon("ressources/images/couler.gif");
            JLabel imageLabel = new JLabel();
            imageLabel.setIcon(ii);
            imageLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
            contentPane.add(imageLabel);
            // show it
            this.setLocationRelativeTo(null);
            this.setUndecorated(true);
            this.setAlwaysOnTop(true);
            this.setVisible(true);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}