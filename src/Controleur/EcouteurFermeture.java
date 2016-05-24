package Controleur;

import Modele.Jeu;
import Modele.SauvegardeException;
import Vue.Fenetre;


import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ResourceBundle;

/**
 * Created by fparty2 on 11/05/16.
 *
 */
public class EcouteurFermeture extends WindowAdapter {
    private Fenetre fenetre;

    public EcouteurFermeture(Fenetre fen){
        fenetre = fen;
        fenetre.addWindowListener(this);
    }

    public void windowClosing(WindowEvent e){


        ResourceBundle texteInternational = ResourceBundle.getBundle("traductions.EcouteurMenu");


        int resultQuitter = JOptionPane.showConfirmDialog(fenetre,texteInternational.getString("demandeQuitter"),
                texteInternational.getString("quitter"),JOptionPane.YES_NO_OPTION);
        if  (resultQuitter==JOptionPane.YES_OPTION){
            int resultSauvegarde = JOptionPane.showConfirmDialog(fenetre,texteInternational.getString("demandeSauvegarde"),
                    texteInternational.getString("quitter"),JOptionPane.YES_NO_OPTION);
            if  (resultSauvegarde==JOptionPane.YES_OPTION){
                try {
                    Jeu.Sauvegarde(fenetre.getJeu());

                }
                catch(SauvegardeException e1){
                    JOptionPane.showMessageDialog(fenetre,texteInternational.getString("erreurSauvegarde"),
                            texteInternational.getString("erreur"),JOptionPane.INFORMATION_MESSAGE);
                }

            }


            System.exit(0);
        }

    }

}
