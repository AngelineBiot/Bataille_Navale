package Vue;

import javazoom.jl.player.Player;

import javax.swing.*;
import java.awt.*;
import java.io.FileInputStream;
import java.util.ResourceBundle;

/**
 * Created by belfort on 24/05/2016.
 *
 */
public class Animation extends JFrame {
    protected String message;
    protected String image;
    protected String son;
    protected Player fil;

    protected ThreadSon threadSon;

    public Animation(String m, String i, String s){
        message=m;
        image=i;
        son=s;
        composeFenetre();
    }

    public void composeFenetre(){
        ResourceBundle texteInternational = ResourceBundle.getBundle("traductions.Animation");
        String coule = texteInternational.getString(message);


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
            JLabel imageLabel = new JLabel();
            ImageIcon ii = new ImageIcon(image);
            imageLabel.setIcon(ii);
            imageLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
            contentPane.add(imageLabel);
            // show it
            this.setLocationRelativeTo(null);
            this.setUndecorated(true);
            this.setAlwaysOnTop(true);


            threadSon = new ThreadSon();
            this.setVisible(true);
            threadSon.start();


        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }


    private class ThreadSon extends Thread{
        public void run(){
            try {
                FileInputStream fis = new FileInputStream(son);
                fil = new Player(fis);
                fil.play();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void coupeSon(){
        fil.close();
        threadSon.stop();
    }

}
