package Vue;

import javax.swing.*;

/**
 * Created by angel on 17/05/2016.
 * Updated by angel ON 17/05/2016.
 */
public class Popup extends JFrame{

    public Popup(String nomFenetre) {

        setTitle(nomFenetre);

        Icon icon = new ImageIcon("ressources/images/help.png");

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
