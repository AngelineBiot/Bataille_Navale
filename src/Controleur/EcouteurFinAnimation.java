package Controleur;

import Modele.Jeu;
import Vue.ConteneurAttente;
import Vue.Fenetre;
import Vue.TableauScores;

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

    private JFrame fenetreAnimation;
    private Timer timer;
    private boolean partieFinie;

    EcouteurFinAnimation(JFrame fenSec, Fenetre fen, Jeu j) {
        fenetreAnimation=fenSec;
        fenetre = fen;
        jeu=j;
        partieFinie = false;
    }

    EcouteurFinAnimation(JFrame fenSec, Fenetre fen, Jeu j, boolean fini) {
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
        fenetreAnimation.dispose();

        if(partieFinie) {



            ResourceBundle texteInternational = ResourceBundle.getBundle("traductions.EcouteurConteneurGrillePhaseTir");



            JPanel conteneurVictoire = new JPanel();
            conteneurVictoire.setLayout(new BoxLayout(conteneurVictoire, BoxLayout.Y_AXIS));

            JLabel messageVictoire = new JLabel(texteInternational.getString("bravo")+
                    jeu.getJoueurConcerne().getNomJoueur()+
                    texteInternational.getString("gagne"));
            conteneurVictoire.add(messageVictoire);

            TableauScores tableauScore = new TableauScores(jeu);
            conteneurVictoire.add(tableauScore);

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
