package Controleur;

import Vue.Fenetre;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

/**
 * Created by angel on 03/05/2016.
 * Updated by angel ON 03/05/2016.
 */
public class ControlMenu implements ActionListener{

    private Fenetre fenetre;

    public ControlMenu(Fenetre fenetre){
        this.fenetre = fenetre;
        fenetre.setControlMenu(this);
    }

    public void actionPerformed(ActionEvent e){

        Object source = e.getSource();

        String msgAide = "Vous tirez sur les bateaux et on vous tire dessus !";
        String msgApropos = "Jeu créé par nous";

        if (source.equals(fenetre.getItemAide()))
        {
            JOptionPane jopAide = new JOptionPane();
            jopAide.showMessageDialog(null,msgAide,"Aide",JOptionPane.INFORMATION_MESSAGE);

            JDialog fenErrAide = jopAide.createDialog(null,"Aide");
        }
        else
        {
            JOptionPane jopApropos = new JOptionPane();
            jopApropos.showMessageDialog(null,msgApropos,"A propos",JOptionPane.INFORMATION_MESSAGE);

            JDialog fenErrApropos = jopApropos.createDialog(null,"A propos");


        }
    }
}
