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
            if(jeu.getJoueurConcerne().getIdJoueur()!=-1){
                ModelConteneurPlacement modelConteneurPlacement = new ModelConteneurPlacement();
                modelConteneurPlacement.setDimensionCarre(50);

                ConteneurGrille conteneurGrille = new ConteneurGrille(jeu.getJoueurConcerne());
                conteneurGrille.setModelPlacement(modelConteneurPlacement);
                ConteneurPlacement conteneur = new ConteneurPlacement(jeu.getJoueurConcerne().getFlotte(), conteneurGrille, modelConteneurPlacement);
                new EcouteurConteneurGrillePhasePlacement(modelConteneurPlacement, conteneur, fenetre, jeu);

                fenetre.setContentPane(conteneur);
                fenetre.validate();

                conteneur.setFocusable(true);
                conteneur.requestFocus();
            }else {
                jeu.getJoueurConcerne().getComputer().placerBateau(fenetre,jeu);
            }

        }
        else{
            if(jeu.getJoueurConcerne().getIdJoueur()!=-1) {

                ConteneurGrille conteneurGrilleAutreJoueur = new ConteneurGrille(jeu.getJoueurNonConcerne());
                ConteneurGrille conteneurGrilleJoueur = new ConteneurGrille(jeu.getJoueurConcerne());

                ModelConteneurTir modelConteneurTir = new ModelConteneurTir();
                TableauScores score = new TableauScores(jeu);
                ConteneurAchievement conteneurAchievement = new ConteneurAchievement(jeu);
                ConteneurTir conteneurTir = new ConteneurTir(conteneurGrilleJoueur, conteneurGrilleAutreJoueur, score, conteneurAchievement, modelConteneurTir);


                new EcouteurConteneurGrillePhaseTir(conteneurTir, modelConteneurTir, jeu, fenetre);

                fenetre.setContentPane(conteneurTir);
                fenetre.validate();


                conteneurGrilleJoueur.afficherBateauxDeSaFlotte();
                conteneurGrilleJoueur.afficherCaseToucheMaGrille();
                conteneurGrilleAutreJoueur.afficherCaseTouche();
                conteneurGrilleAutreJoueur.afficherBateauxCoulesMaGrille();
                conteneurGrilleAutreJoueur.updateUI();
            }else {
                jeu.getJoueurConcerne().getComputer().tirer(conteneurAttente,fenetre,jeu);
            }
        }
    }
}
