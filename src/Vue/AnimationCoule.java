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
            contentPane.setLayout(new BorderLayout());
            setSize(new Dimension(400, 300));
            setTitle(coule);
            // add the header label
            headerLabel.setFont(new java.awt.Font("Comic Sans MS", Font.BOLD, 16));
            headerLabel.setText(coule);
            contentPane.add(headerLabel, java.awt.BorderLayout.NORTH);
            // add the image label
            ImageIcon ii = new ImageIcon("ressources/images/couler.gif");
            imageLabel.setIcon(ii);
            contentPane.add(imageLabel, java.awt.BorderLayout.CENTER);
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