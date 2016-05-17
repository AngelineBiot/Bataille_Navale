package Vue;

import javax.swing.*;
import java.awt.*;

/**
 * Created by angel on 17/05/2016.
 * Updated by angel ON 17/05/2016.
 */
public class Popup extends JFrame{

    private Icon icon = null;

    public Popup(String nomFenetre) {

        setTitle(nomFenetre);

        ImageIcon im = new ImageIcon("ressources/images/help.png");
        this.icon = im;

        initContenu();

        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLocationRelativeTo(null);
        setResizable(false);

        setVisible(true);
    }

    private void initContenu() {

        JPanel panelPopup = new JPanel();



        setContentPane(panelPopup);
    }
}
