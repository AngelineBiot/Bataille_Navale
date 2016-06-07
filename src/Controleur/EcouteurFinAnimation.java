package Controleur;

import Modele.BaseDeDonnees;
import Modele.Jeu;
import Vue.*;

import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by fparty2 on 23/05/16.
 *
 */
public class EcouteurFinAnimation implements ActionListener {
    private Fenetre fenetre;
    private Jeu jeu;
    private BaseDeDonnees baseDeDonnees;

    private Animation fenetreAnimation;
    private Timer timer;
    private boolean partieFinie;

    public EcouteurFinAnimation(Animation fenSec, Fenetre fen, Jeu j, BaseDeDonnees base){
        fenetreAnimation=fenSec;
        fenetre = fen;
        jeu=j;
        baseDeDonnees = base;
        partieFinie = false;

    }

    public EcouteurFinAnimation(Animation fenSec, Fenetre fen, Jeu j, BaseDeDonnees base, boolean fini){
        fenetreAnimation=fenSec;
        fenetre = fen;
        jeu=j;
        baseDeDonnees = base;
        partieFinie = fini;
    }

    public void setTimer(Timer t){
        timer = t;
    }

    public void actionPerformed(ActionEvent e) {
        fenetreAnimation.coupeSon();
        timer.stop();
        fenetreAnimation.dispose();

        if(partieFinie) {
            ConteneurVictoire conteneurVictoire = new ConteneurVictoire(jeu);
            fenetre.setContentPane(conteneurVictoire);

            fenetre.validate();
        }
        else{
            jeu.echangeConcerneJoueur1();

            ConteneurAttente conteneurAttente = new ConteneurAttente(jeu);
            new EcouteurConteneurAttente(conteneurAttente, fenetre, jeu, baseDeDonnees);

            fenetre.setContentPane(conteneurAttente);
            fenetre.validate();
        }


    }
}
