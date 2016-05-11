package Controleur;

import Modele.Jeu;
import Modele.SauvegardeException;
import Vue.Fenetre;


import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Created by fparty2 on 11/05/16.
 */
public class EcouteurFermeture extends WindowAdapter {
    private Fenetre fenetre;

    public EcouteurFermeture(Fenetre fen){
        fenetre = fen;
        fenetre.addWindowListener(this);
    }

    public void windowClosing(WindowEvent e){
        int resultQuitter = JOptionPane.showConfirmDialog(fenetre,"Vous voulez quitter?","Confirm",JOptionPane.YES_NO_OPTION);
        fenetre.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        if  (resultQuitter==JOptionPane.YES_OPTION){
            int resultSauvegarde = JOptionPane.showConfirmDialog(fenetre,"Voulez-vous sauvegarder la partie en cours ?","Confirm",JOptionPane.YES_NO_OPTION);
            if  (resultSauvegarde==JOptionPane.YES_OPTION){
                try {
                    Jeu.Sauvegarde(fenetre.getJeu());

                }
                catch(SauvegardeException e1){
                    JOptionPane jopApropos = new JOptionPane();
                    jopApropos.showMessageDialog(fenetre,"Impossible de sauvegarder la partie !","Erreur",JOptionPane.INFORMATION_MESSAGE);

                    jopApropos.createDialog(fenetre,"Erreur");
                }

            }


            fenetre.dispose();
        }

    }

}
