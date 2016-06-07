package Controleur;

import Modele.*;
import Vue.*;

import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by fparty2 on 29/04/16.
 */
public class EcouteurConteneurAttente implements ActionListener {
    private ConteneurAttente conteneurAttente;

    private Fenetre fenetre;
    private Jeu jeu;
    private BaseDeDonnees baseDeDonnees;

    public EcouteurConteneurAttente(ConteneurAttente conteneur, Fenetre fen, Jeu j, BaseDeDonnees base) {
        baseDeDonnees = base;
        conteneurAttente = conteneur;
        fenetre = fen;
        jeu = j;

        conteneurAttente.setEcouteurConteneurAttente(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (jeu.getEstPhasePlacement()) {
            if (jeu.getJoueurConcerne().getIdJoueur() != -1) {
                ModelConteneurPlacement modelConteneurPlacement = new ModelConteneurPlacement();
                modelConteneurPlacement.setDimensionCarre(50);

                ConteneurGrille conteneurGrille = new ConteneurGrille(jeu.getJoueurConcerne());
                conteneurGrille.setModelPlacement(modelConteneurPlacement);
                ConteneurPlacement conteneur = new ConteneurPlacement(jeu.getJoueurConcerne().getFlotte(), conteneurGrille, modelConteneurPlacement);
                new EcouteurConteneurGrillePhasePlacement(modelConteneurPlacement, conteneur, fenetre, jeu, baseDeDonnees);

                fenetre.setContentPane(conteneur);
                fenetre.validate();

                conteneur.setFocusable(true);
                conteneur.requestFocus();
            } else {
                jeu.getJoueurConcerne().getComputer().placerBateau(jeu);
                jeu.echangeConcerneJoueur1();
                jeu.echangeEstPhasePlacement();
                ConteneurAttente conteneurAttente = new ConteneurAttente(jeu);
                new EcouteurConteneurAttente(conteneurAttente, fenetre, jeu, baseDeDonnees);

                fenetre.setContentPane(conteneurAttente);
                fenetre.validate();
            }

        } else {
            if (jeu.getJoueurConcerne().getIdJoueur() != -1) {

                ConteneurGrille conteneurGrilleAutreJoueur = new ConteneurGrille(jeu.getJoueurNonConcerne());
                ConteneurGrille conteneurGrilleJoueur = new ConteneurGrille(jeu.getJoueurConcerne());

                ModelConteneurTir modelConteneurTir = new ModelConteneurTir();
                TableauScores score = new TableauScores(jeu);
                ConteneurAchievement conteneurAchievement = new ConteneurAchievement(jeu);
                ConteneurTir conteneurTir = new ConteneurTir(conteneurGrilleJoueur, conteneurGrilleAutreJoueur, score, conteneurAchievement, modelConteneurTir);


                new EcouteurConteneurGrillePhaseTir(conteneurTir, modelConteneurTir, baseDeDonnees, jeu, fenetre);

                fenetre.setContentPane(conteneurTir);
                fenetre.validate();


                conteneurGrilleJoueur.afficherBateauxDeSaFlotte();
                conteneurGrilleJoueur.afficherCaseToucheMaGrille();
                conteneurGrilleAutreJoueur.afficherCaseTouche();
                conteneurGrilleAutreJoueur.afficherBateauxCoulesMaGrille();
                conteneurGrilleAutreJoueur.updateUI();
            } else {
                conteneurAttente.desactivePret();

                ModelConteneurTir modele_tir = new ModelConteneurTir();
                boolean estPasRate = jeu.getJoueurConcerne().getComputer().tirer(jeu, modele_tir);

                Animation fenetreAnimation;
                boolean partieFinie = false;
                int tempsAnimation;

                if (estPasRate) {
                    if (modele_tir.getCaseOuEstTire().getBat().getCoule()) {

                        jeu.getJoueurNonConcerne().getFlotte().incrementeNbBateauxCoule();

                        if (jeu.getJoueurNonConcerne().getFlotte().flotteCoulee()) {
                            if (jeu.getJoueurConcerne().getNbCoups() == jeu.getJoueurNonConcerne().getFlotte().getNbTouches()) {
                                baseDeDonnees.debloqueSharpshooter();
                            }
                            jeu.setPartieFinie();

                            try {
                                Object[][] experienceJoueur = baseDeDonnees.recupereExperienceJoueur();

                                baseDeDonnees.updateExperience(experienceJoueur);
                            } catch (BDDException e3) {
                                new PopUpErreurBDD(false);
                            }

                            fenetreAnimation = new AnimationFin();
                            partieFinie = true;
                            tempsAnimation = 4700;


                        } else {
                            fenetreAnimation = new AnimationCoule();
                            tempsAnimation=2250;
                        }


                    } else {

                        fenetreAnimation = new AnimationTouche();
                        tempsAnimation = 1530;

                    }
                } else {

                    fenetreAnimation = new AnimationRate();
                    tempsAnimation=3000;
                }

                EcouteurFinAnimation ecouteurFinAnimation = new EcouteurFinAnimation(fenetreAnimation, fenetre, jeu, baseDeDonnees, partieFinie);
                Timer timer = new Timer(tempsAnimation, ecouteurFinAnimation);
                ecouteurFinAnimation.setTimer(timer);
                timer.start();

            }

        }
    }
}
