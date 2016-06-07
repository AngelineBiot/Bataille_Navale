package Controleur;

import Vue.*;
import Modele.*;

import javax.swing.*;
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


                            AnimationFin ac = new AnimationFin();
                            EcouteurFinAnimation ecouteurFinAnimation = new EcouteurFinAnimation(ac, fenetre, jeu, baseDeDonnees, true);
                            Timer timer=new Timer(4400, ecouteurFinAnimation);
                            ecouteurFinAnimation.setTimer(timer);
                            timer.start();

                        }
                        else{
                            AnimationCoule ac = new AnimationCoule();
                            EcouteurFinAnimation ecouteurFinAnimation = new EcouteurFinAnimation(ac, fenetre, jeu, baseDeDonnees);
                            Timer timer=new Timer(2250, ecouteurFinAnimation);
                            ecouteurFinAnimation.setTimer(timer);
                            timer.start();
                        }


                    }
                    else{

                        AnimationTouche ac = new AnimationTouche();
                        EcouteurFinAnimation ecouteurFinAnimation = new EcouteurFinAnimation(ac, fenetre, jeu, baseDeDonnees);
                        Timer timer=new Timer(1530, ecouteurFinAnimation);
                        ecouteurFinAnimation.setTimer(timer);
                        timer.start();
                    }

                } else {

                    AnimationRate at = new AnimationRate();
                    EcouteurFinAnimation ecouteurFinAnimation = new EcouteurFinAnimation(at, fenetre, jeu, baseDeDonnees);
                    Timer timer=new Timer(3000, ecouteurFinAnimation);
                    ecouteurFinAnimation.setTimer(timer);
                    timer.start();
                }


            }
        }
    }
}