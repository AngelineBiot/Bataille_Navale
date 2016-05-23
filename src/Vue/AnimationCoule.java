package Vue;

/**
 * Created by ANTOINE on 11/05/2016.
 */
import java.awt.*;
import javax.swing.*;
import java.awt.BorderLayout;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by ANTOINE on 09/05/2016.
 */
public class AnimationCoule extends JFrame{
    JPanel contentPane;
    JLabel imageLabel = new JLabel();
    JLabel headerLabel = new JLabel();

    public AnimationCoule() {



        ResourceBundle texteInternational = ResourceBundle.getBundle("traductions.Animation");
        String coule = texteInternational.getString("coule");


        try {

            contentPane = (JPanel) getContentPane();
            contentPane.setBackground(Color.LIGHT_GRAY);

            contentPane.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
            setSize(new Dimension(400, 300));
            setTitle(coule);
            // add the header label
            headerLabel.setFont(new java.awt.Font("Comic Sans MS", Font.BOLD, 16));
            headerLabel.setText(coule);
            headerLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
            contentPane.add(headerLabel);

            contentPane.add(Box.createRigidArea(new Dimension(0, 30)));
            // add the image label
            ImageIcon ii = new ImageIcon("ressources/images/couler.gif");
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