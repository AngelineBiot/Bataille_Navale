package Controleur;

import Vue.*;
import Modele.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ResourceBundle;

import static javax.swing.JOptionPane.showMessageDialog;


/**
 * Created by Michael on 24/04/2016.
 *
 */
public class EcouteurConteneurGrillePhaseTir extends MouseAdapter implements ActionListener {
    private ModelConteneurTir model_tir;
    private ConteneurTir panelTir;
    private Jeu jeu;
    private Fenetre fenetre;



    EcouteurConteneurGrillePhaseTir(ConteneurTir panTir, ModelConteneurTir model, Jeu j, Fenetre fen) {
        jeu = j;
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
                        model_tir.debloqueAchievement1(jeu);        //On met donc a jour la bdd
                    }

                    jeu.getJoueurNonConcerne().getFlotte().incrementeNbBateauxTouche();
                    model_tir.getCaseOuEstTire().getBat().updateEstCoule();


                    if(model_tir.getCaseOuEstTire().getBat().getCoule()){

                        jeu.getJoueurNonConcerne().getFlotte().incrementeNbBateauxCoule();

                        if(jeu.getJoueurNonConcerne().getFlotte().flotteCoulee()){
                            if (jeu.getJoueurConcerne().getNbCoups()==jeu.getJoueurNonConcerne().getFlotte().getNbTouches()){
                                try {
                                    if (model_tir.execQuery("SELECT * FROM joueurAchievement WHERE idJoueur=" + jeu.getJoueurConcerne().getIdJoueur() + " AND idAchievement=1").length == 0) {
                                        model_tir.execRequeteNonQuery("INSERT INTO joueurachievement (idJoueur, idAchievement) VALUES (" + jeu.getJoueurConcerne().getIdJoueur() + ",1)");
                                    }
                                }
                                catch(BDDException e2){

                                }
                            }
                            jeu.setPartieFinie();
                            try{
                                Object[][] experienceJoueur= model_tir.execQuery("SELECT idJoueur,expJoueur,levelJoueur FROM joueur WHERE idJoueur="+jeu.getJoueurConcerne().getIdJoueur()+" OR "+jeu.getJoueurNonConcerne().getIdJoueur());
                                float expJConcerne=0;
                                float expJNonConcerne=0;
                                for (int i=0;i<experienceJoueur.length;i++){
                                    if ((int)experienceJoueur[i][0]==jeu.getJoueurConcerne().getIdJoueur()){
                                        expJConcerne=((jeu.getJoueurNonConcerne().getFlotte().getNbTouches()/(float)jeu.getJoueurConcerne().getNbCoups())*100+(float)experienceJoueur[i][1]);
                                        if (expJConcerne>1000){
                                            expJConcerne=expJConcerne%1000;
                                            float niveauJoueur=(float)experienceJoueur[i][2]+1;
                                            model_tir.execRequeteNonQuery("UPDATE JOUEUR SET levelJoueur ="+niveauJoueur+" WHERE idJoueur="+jeu.getJoueurConcerne().getIdJoueur());
                                        }
                                        model_tir.execRequeteNonQuery("UPDATE JOUEUR SET expJoueur ="+expJConcerne+" WHERE idJoueur="+jeu.getJoueurConcerne().getIdJoueur());
                                    }
                                    if ((int)experienceJoueur[i][0]==jeu.getJoueurNonConcerne().getIdJoueur()){
                                        expJNonConcerne=((jeu.getJoueurNonConcerne().getFlotte().getNbTouches()/(float)jeu.getJoueurConcerne().getNbCoups())*50+(float)experienceJoueur[i][1]);
                                        if (expJNonConcerne>1000){
                                            expJNonConcerne=expJNonConcerne%1000;
                                            float niveauJoueur=(float)experienceJoueur[i][2]+1;
                                            model_tir.execRequeteNonQuery("UPDATE JOUEUR SET levelJoueur ="+niveauJoueur+" WHERE idJoueur="+jeu.getJoueurNonConcerne().getIdJoueur());
                                        }
                                        model_tir.execRequeteNonQuery("UPDATE JOUEUR SET expJoueur ="+expJNonConcerne+" WHERE idJoueur="+jeu.getJoueurNonConcerne().getIdJoueur());
                                    }
                                }
                            }
                            catch(BDDException e3){
                                ResourceBundle texteInternational = ResourceBundle.getBundle("traductions.Database");
                                showMessageDialog(null,texteInternational.getString("pas_accessible")+" "+texteInternational.getString("peut_pas_sauvegarder"),
                                        texteInternational.getString("erreur"), JOptionPane.ERROR_MESSAGE);
                            }


                            AnimationFin ac = new AnimationFin();
                            EcouteurFinAnimation ecouteurFinAnimation = new EcouteurFinAnimation(ac, fenetre, jeu, true);
                            Timer timer=new Timer(4400, ecouteurFinAnimation);
                            ecouteurFinAnimation.setTimer(timer);
                            timer.start();

                        }
                        else{
                            AnimationCoule ac = new AnimationCoule();
                            EcouteurFinAnimation ecouteurFinAnimation = new EcouteurFinAnimation(ac, fenetre, jeu);
                            Timer timer=new Timer(2250, ecouteurFinAnimation);
                            ecouteurFinAnimation.setTimer(timer);
                            timer.start();
                        }


                    }
                    else{

                        AnimationTouche ac = new AnimationTouche();
                        EcouteurFinAnimation ecouteurFinAnimation = new EcouteurFinAnimation(ac, fenetre, jeu);
                        Timer timer=new Timer(1530, ecouteurFinAnimation);
                        ecouteurFinAnimation.setTimer(timer);
                        timer.start();
                    }

                } else {

                    AnimationRate at = new AnimationRate();
                    EcouteurFinAnimation ecouteurFinAnimation = new EcouteurFinAnimation(at, fenetre, jeu);
                    Timer timer=new Timer(3000, ecouteurFinAnimation);
                    ecouteurFinAnimation.setTimer(timer);
                    timer.start();
                }


            }
        }
    }
}