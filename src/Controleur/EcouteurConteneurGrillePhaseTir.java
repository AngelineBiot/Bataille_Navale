package Controleur;

import Vue.*;
import Modele.*;

import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;



/**
 * Created by Michael on 24/04/2016.
 *
 */
public class EcouteurConteneurGrillePhaseTir extends MouseAdapter implements ActionListener {
    private ModelConteneurTir model_tir;
    private ConteneurTir panelTir;
    private Jeu jeu;
    private Fenetre fenetre;
    private BaseDeDonnees baseDeDonnees;



    EcouteurConteneurGrillePhaseTir(ConteneurTir panTir, ModelConteneurTir model, BaseDeDonnees base, Jeu j, Fenetre fen) {
        jeu = j;
        baseDeDonnees = base;
        fenetre = fen;
        model_tir =model;
        panelTir =panTir;
        model_tir.setDimensionCarre(50);
        model_tir.setCaseOuEstTire(null);
        panelTir.setControlTire(this);
    }


    @Override
    public void mousePressed(MouseEvent e) {
        if (model_tir.getCaseOuEstTire() != null) {
            panelTir.effaceContourCaseSelectionnee();
        }
        model_tir.setCoordonnees(e.getX(), e.getY());
        model_tir.updateCaseOuEstTire(jeu);
        panelTir.creeContourCaseSelectionnee();

    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("validation")) {
            if (model_tir.getCaseOuEstTire() != null) {
                panelTir.desactiveValidation();

                boolean dejaTirSurCase = model_tir.getCaseOuEstTire().getToucher();
                jeu.getJoueurConcerne().setNbcoups();
                model_tir.getCaseOuEstTire().setToucher();

                Animation fenetreAnimation;
                boolean partieFinie = false;
                int tempsAnimation;


                if (model_tir.getCaseOuEstTire().getBat() != null && !dejaTirSurCase) {    //S'il y a un bateau sur la case, et que le joueur
                                                                                            // n'a pas deja tire sur la case

                    if (jeu.getJoueurConcerne().getNbCoups()==1){   //Si le joueur touche un bateau du premier coup, il débloque un achievement
                        baseDeDonnees.debloqueLuckyShot(jeu);        //On met donc a jour la bdd
                    }

                    jeu.getJoueurNonConcerne().getFlotte().incrementeNbBateauxTouche();
                    model_tir.getCaseOuEstTire().getBat().updateEstCoule();


                    if(model_tir.getCaseOuEstTire().getBat().getCoule()){

                        jeu.getJoueurNonConcerne().getFlotte().incrementeNbBateauxCoule();



                        if(jeu.getJoueurNonConcerne().getFlotte().flotteCoulee()){
                            if (jeu.getJoueurConcerne().getNbCoups()==jeu.getJoueurNonConcerne().getFlotte().getNbTouches()){
                                baseDeDonnees.debloqueSharpshooter();
                            }
                            jeu.setPartieFinie();
                            try{
                                Object[][] experienceJoueur= baseDeDonnees.recupereExperienceJoueur();

                                baseDeDonnees.updateExperience(experienceJoueur);
                            }
                            catch(BDDException e3){
                                new PopUpErreurBDD(false);
                            }


                            fenetreAnimation = new AnimationFin();
                            partieFinie = true;
                            tempsAnimation = 4400;

                        }
                        else{
                            fenetreAnimation = new AnimationCoule();
                            tempsAnimation = 2250;
                        }


                    }
                    else{

                        fenetreAnimation = new AnimationTouche();
                        tempsAnimation = 1530;
                    }

                } else {

                    fenetreAnimation = new AnimationRate();
                    tempsAnimation = 3000;
                }

                EcouteurFinAnimation ecouteurFinAnimation = new EcouteurFinAnimation(fenetreAnimation, fenetre, jeu, baseDeDonnees, partieFinie);
                Timer timer = new Timer(tempsAnimation, ecouteurFinAnimation);
                ecouteurFinAnimation.setTimer(timer);
                timer.start();


            }
        }
    }
}