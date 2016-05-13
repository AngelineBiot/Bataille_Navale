package Controleur;

import Modele.*;
import Vue.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by fparty2 on 29/04/16.
 */
public class EcouteurConteneurAttente implements ActionListener {
    private ConteneurAttente conteneurAttente;

    private Fenetre fenetre;
    private Jeu jeu;

    public EcouteurConteneurAttente(ConteneurAttente conteneur, Fenetre fen, Jeu j){

        conteneurAttente =conteneur;
        fenetre = fen;
        jeu = j;

        conteneurAttente.setEcouteurConteneurAttente(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(jeu.getEstPhasePlacement()){

            ModelConteneurPlacement modelConteneurPlacement = new ModelConteneurPlacement();
            modelConteneurPlacement.setDimensionCarre(50);

            ConteneurGrille conteneurGrille = new ConteneurGrille(jeu.getJoueurConcerne());
            ConteneurPlacement conteneur = new ConteneurPlacement(modelConteneurPlacement, jeu.getJoueurConcerne().getFlotte(), conteneurGrille);
            new EcouteurConteneurGrillePhasePlacement(modelConteneurPlacement, conteneur, fenetre, jeu);

            fenetre.setContentPane(conteneur);
            fenetre.validate();

        }
        else{

            ConteneurGrille conteneurGrilleAutreJoueur = new ConteneurGrille(jeu.getJoueurNonConcerne());
            ConteneurGrille conteneurGrilleJoueur = new ConteneurGrille(jeu.getJoueurConcerne());

            ModelConteneurTir modelConteneurTir = new ModelConteneurTir();
            TableauScores score = new TableauScores(jeu);

            ConteneurTir conteneurTir = new ConteneurTir(modelConteneurTir, conteneurGrilleJoueur, conteneurGrilleAutreJoueur,score);


            new EcouteurConteneurGrillePhaseTir(conteneurTir, modelConteneurTir, jeu, fenetre);

            fenetre.setContentPane(conteneurTir);
            fenetre.validate();


            conteneurGrilleJoueur.afficherBateauxDeSaFlotte();
            conteneurGrilleJoueur.afficherCaseToucheMaGrille();
            conteneurGrilleAutreJoueur.afficherCaseTouche();
            conteneurGrilleAutreJoueur.afficherBateauxCoulesMaGrille();
            conteneurGrilleAutreJoueur.updateUI();

        }

    }
}
