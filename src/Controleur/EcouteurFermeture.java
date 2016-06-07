package Controleur;

import Modele.BaseDeDonnees;
import Modele.Jeu;
import Modele.SauvegardeException;
import Vue.Fenetre;


import javax.swing.JOptionPane;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Created by fparty2 on 11/05/16.
 *
 */
public class EcouteurFermeture extends WindowAdapter {
    private Fenetre fenetre;
    private BaseDeDonnees baseDeDonnees;

    public EcouteurFermeture(Fenetre fen, BaseDeDonnees base){
        baseDeDonnees = base;
        fenetre = fen;
        fenetre.addWindowListener(this);
    }

    public void windowClosing(WindowEvent e){




        int resultQuitter = fenetre.affichePopupDemande("demandeQuitter");
        if  (resultQuitter==JOptionPane.YES_OPTION){
            int resultSauvegarde = fenetre.affichePopupDemande("demandeSauvegarde");
            if  (resultSauvegarde==JOptionPane.YES_OPTION){
                try {
                    Jeu.Sauvegarde(fenetre.getJeu());

                }
                catch(SauvegardeException e1){
                    fenetre.affichePopupErreur("erreurSauvegarde");
                }

            }

            baseDeDonnees.fermeConnexion();
            System.exit(0);
        }

    }

}
