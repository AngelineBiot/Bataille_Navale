package Vue;

import javax.swing.*;
import java.awt.*;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by ANTOINE on 13/05/2016.
 */
public class AnimationFin extends JFrame implements Runnable{
    Thread t;
    JPanel contentPane;
    JLabel imageLabel = new JLabel();
    JLabel headerLabel = new JLabel();

    public AnimationFin() {



        ResourceBundle texteInternational = ResourceBundle.getBundle("traductions.Animation");
        String fin = texteInternational.getString("fin");

        try {
            t=new Thread(this);
            t.start();


            contentPane = (JPanel) getContentPane();
            contentPane.setBackground(Color.LIGHT_GRAY);
            contentPane.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
            setSize(new Dimension(400, 300));
            setTitle(fin);
            // add the header label
            headerLabel.setFont(new java.awt.Font("Comic Sans MS", Font.BOLD, 16));
            headerLabel.setText(fin);
            headerLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
            contentPane.add(headerLabel);

            contentPane.add(Box.createRigidArea(new Dimension(0, 30)));
            // add the image label
            ImageIcon ii = new ImageIcon("ressources/images/fin.gif");
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