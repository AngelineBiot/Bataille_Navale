package Controleur;

import Modele.Jeu;
import Vue.AnimationFin;
import Vue.ConteneurAttente;
import Vue.Fenetre;
import Vue.TableauScores;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;

/**
 * Created by fparty2 on 23/05/16.
 */
public class EcouteurFinAnimation implements ActionListener{
    private Fenetre fenetre;
    private Jeu jeu;

    private JFrame fenetreAnimation;
    private Timer timer;
    public EcouteurFinAnimation(JFrame fenSec, Fenetre fen, Jeu j){
        fenetreAnimation=fenSec;
        fenetre = fen;
        jeu=j;
    }

    public void setTimer(Timer t){
        timer = t;
    }

    public void actionPerformed(ActionEvent e){


        if(jeu.getJoueurNonConcerne().getFlotte().flotteCoulee()){


            ResourceBundle texteInternational = ResourceBundle.getBundle("traductions.EcouteurConteneurGrillePhaseTir");



            JPanel conteneurVictoire = new JPanel();
            conteneurVictoire.setLayout(new BoxLayout(conteneurVictoire, BoxLayout.Y_AXIS));
            AnimationFin af = new AnimationFin();
            af.setVisible(true);
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

        timer.stop();
        fenetreAnimation.dispose();
    }
}
