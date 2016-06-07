package Vue;

import javax.swing.*;
import java.util.ResourceBundle;

/**
 * Created by angel on 17/05/2016.
 *
 */
public class PopUpAide extends JFrame{

    private Icon icon = null;
    ImageIcon im = new ImageIcon("ressources/images/help.png");
    ResourceBundle texteInternational = ResourceBundle.getBundle("traductions.EcouteurMenu");

    public PopUpAide(String nomFenetre) {

        setTitle(nomFenetre);

        this.icon = im;

        initContenu();

        pack();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setLocationRelativeTo(null);
        setResizable(false);

        setVisible(true);
    }

    private void initContenu() {

        String[] tabAide = texteInternational.getString("messageAide").split("<br>");

        JPanel panelPopup = new JPanel();
        JLabel texte1 = new JLabel("<html><body>" + texteInternational.getString("messageAide") + "</body></html>");


        texte1.setIcon(im);

        panelPopup.add(texte1);

        setContentPane(panelPopup);
    }
}
