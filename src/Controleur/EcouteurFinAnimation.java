package Controleur;

import Modele.Jeu;
import Vue.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;

/**
 * Created by fparty2 on 23/05/16.
 *
 */
class EcouteurFinAnimation implements ActionListener {
    private Fenetre fenetre;
    private Jeu jeu;

    private Animation fenetreAnimation;
    private Timer timer;
    private boolean partieFinie;

    EcouteurFinAnimation(Animation fenSec, Fenetre fen, Jeu j){
        fenetreAnimation=fenSec;
        fenetre = fen;
        jeu=j;
        partieFinie = false;
    }

    EcouteurFinAnimation(Animation fenSec, Fenetre fen, Jeu j, boolean fini){
        fenetreAnimation=fenSec;
        fenetre = fen;
        jeu=j;
        partieFinie = fini;
    }

    void setTimer(Timer t){
        timer = t;
    }

    public void actionPerformed(ActionEvent e) {
        timer.stop();
        fenetreAnimation.coupeSon();
        fenetreAnimation.dispose();

        if(partieFinie) {
            ConteneurVictoire conteneurVictoire = new ConteneurVictoire(jeu);
            fenetre.setContentPane(conteneurVictoire);

            fenetre.validate();
        }
        else{
            jeu.echangeConcerneJoueur1();

            ConteneurAttente conteneurAttente = new ConteneurAttente(jeu);
            new EcouteurConteneurAttente(conteneurAttente, fenetre, jeu);

            fenetre.setContentPane(conteneurAttente);
            fenetre.validate();
        }


    }
}
