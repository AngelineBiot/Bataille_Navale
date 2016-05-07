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

            if(!jeu.getConcerneJoueur1()){
                jeu.echangeEstPhasePlacement();
            }

            jeu.echangeConcerneJoueur1();

            ModelConteneurPlacement modelConteneurPlacement = new ModelConteneurPlacement();
            modelConteneurPlacement.setDimensionCarre(50);

            ConteneurGrille conteneurGrille = new ConteneurGrille(jeu.getJoueurConcerne());
            ConteneurPlacement conteneur = new ConteneurPlacement(modelConteneurPlacement, jeu.getJoueurConcerne().getFlotte(), conteneurGrille);
            new EcouteurConteneurGrillePhasePlacement(modelConteneurPlacement, conteneur, fenetre, jeu);

            fenetre.setContentPane(conteneur);
            fenetre.validate();

        }
        else{

            jeu.echangeConcerneJoueur1();

            ConteneurGrille conteneurGrilleAutreJoueur = new ConteneurGrille(jeu.getJoueurNonConcerne());
            ConteneurGrille conteneurGrilleJoueur = new ConteneurGrille(jeu.getJoueurConcerne());

            ModelConteneurTir modelConteneurTir = new ModelConteneurTir();

            ConteneurTir conteneurTir = new ConteneurTir(modelConteneurTir, conteneurGrilleJoueur, conteneurGrilleAutreJoueur);
            conteneurGrilleJoueur.afficherBateauxDeSaFlotte();

            new EcouteurConteneurGrillePhaseTir(conteneurTir, modelConteneurTir, jeu);

            fenetre.setContentPane(conteneurTir);
            fenetre.validate();

        }

    }
}
