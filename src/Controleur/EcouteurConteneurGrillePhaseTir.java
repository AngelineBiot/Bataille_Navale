package Controleur;

import Vue.*;
import Modele.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static javax.swing.JOptionPane.showMessageDialog;


/**
 * Created by Michael on 24/04/2016.
 *
 */
public class EcouteurConteneurGrillePhaseTir extends MouseAdapter implements ActionListener {
    private ModelConteneurTir model_tire;
    private ConteneurTir panelTire;
    private Jeu jeu;
    private Fenetre fenetre;



    EcouteurConteneurGrillePhaseTir(ConteneurTir panTir, ModelConteneurTir model, Jeu j, Fenetre fen) {
        jeu = j;
        fenetre = fen;
        model_tire=model;
        panelTire=panTir;
        model_tire.setDimensionCarre(50);
        model_tire.setCaseOuEstTire(null);
        panelTire.setControlTire(this);
    }


    @Override
    public void mousePressed(MouseEvent e) {
        if (model_tire.getCaseOuEstTire() != null) {
            panelTire.getConteneurGrille().getGridPanel()[model_tire.getCoord1D()].setBorder(BorderFactory.createLineBorder(Color.black));
        }
        model_tire.setCoordX((e.getX()) / model_tire.getDimensionCarre());
        model_tire.setCoordY((e.getY()) / model_tire.getDimensionCarre());
        model_tire.setCoord1D(model_tire.getCoordX() + model_tire.getCoordY() * 10);
        model_tire.setCaseOuEstTire(jeu.getJoueurNonConcerne().getGrille().getGrille()[model_tire.getCoord1D()]);
        panelTire.getConteneurGrille().getGridPanel()[model_tire.getCoord1D()].setBorder(BorderFactory.createLineBorder(Color.BLUE,3));
        panelTire.getConteneurGrille().getGridPanel()[model_tire.getCoord1D()].setOpaque(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("validation")) {
            if (model_tire.getCaseOuEstTire() != null) {
                panelTire.desactiveValidation();

                boolean dejaTirSurCase = model_tire.getCaseOuEstTire().getToucher();
                jeu.getJoueurConcerne().setNbcoups();
                model_tire.getCaseOuEstTire().setToucher();


                if (model_tire.getCaseOuEstTire().getBat() != null && !dejaTirSurCase) {
                    if (jeu.getJoueurConcerne().getNbCoups()==1){
                        try {
                            if (model_tire.execQuery("SELECT * FROM joueurAchievement WHERE idJoueur=" + jeu.getJoueurConcerne().getIdJoueur() + " AND idAchievement=2").length == 0) {
                                model_tire.execRequeteNonQuery("INSERT INTO joueurachievement (idJoueur, idAchievement) VALUES (" + jeu.getJoueurConcerne().getIdJoueur() + ",2)");
                            }
                        }
                        catch(BDDException e1){

                        }
                    }
                    jeu.getJoueurNonConcerne().getFlotte().incrementeNbBateauxTouche();
                    model_tire.getCaseOuEstTire().getBat().updateEstCoule();


                    if(model_tire.getCaseOuEstTire().getBat().getCoule()){

                        jeu.getJoueurNonConcerne().getFlotte().incrementeNbBateauxCoule();

                        if(jeu.getJoueurNonConcerne().getFlotte().flotteCoulee()){
                            if (jeu.getJoueurConcerne().getNbCoups()==jeu.getJoueurNonConcerne().getFlotte().getNbTouches()){
                                try {
                                    if (model_tire.execQuery("SELECT * FROM joueurAchievement WHERE idJoueur=" + jeu.getJoueurConcerne().getIdJoueur() + " AND idAchievement=1").length == 0) {
                                        model_tire.execRequeteNonQuery("INSERT INTO joueurachievement (idJoueur, idAchievement) VALUES (" + jeu.getJoueurConcerne().getIdJoueur() + ",1)");
                                    }
                                }
                                catch(BDDException e2){

                                }
                            }
                            jeu.setPartieFinie();
                            try{
                                Object[][] experienceJoueur=model_tire.execQuery("SELECT idJoueur,expJoueur,levelJoueur FROM joueur WHERE idJoueur="+jeu.getJoueurConcerne().getIdJoueur()+" OR "+jeu.getJoueurNonConcerne().getIdJoueur());
                                float expJConcerne=0;
                                float expJNonConcerne=0;
                                for (int i=0;i<experienceJoueur.length;i++){
                                    if ((int)experienceJoueur[i][0]==jeu.getJoueurConcerne().getIdJoueur()){
                                        expJConcerne=((jeu.getJoueurNonConcerne().getFlotte().getNbTouches()/(float)jeu.getJoueurConcerne().getNbCoups())*100+(float)experienceJoueur[i][1]);
                                        model_tire.execRequeteNonQuery("UPDATE JOUEUR SET expJoueur ="+expJConcerne+" WHERE idJoueur="+jeu.getJoueurConcerne().getIdJoueur());
                                        if (expJConcerne>1000){
                                            expJConcerne=expJConcerne%1000;
                                            float niveauJoueur=(float)experienceJoueur[i][2]+1;
                                            model_tire.execRequeteNonQuery("UPDATE JOUEUR SET levelJoueur ="+niveauJoueur+" WHERE idJoueur="+jeu.getJoueurConcerne().getIdJoueur());
                                        }
                                    }
                                    if ((int)experienceJoueur[i][0]==jeu.getJoueurNonConcerne().getIdJoueur()){
                                        expJNonConcerne=((jeu.getJoueurNonConcerne().getFlotte().getNbTouches()/(float)jeu.getJoueurConcerne().getNbCoups())*50+(float)experienceJoueur[i][1]);
                                        model_tire.execRequeteNonQuery("UPDATE JOUEUR SET expJoueur ="+expJNonConcerne+" WHERE idJoueur="+jeu.getJoueurNonConcerne().getIdJoueur());
                                        if (expJNonConcerne>1000){
                                            expJNonConcerne=expJNonConcerne%1000;
                                            float niveauJoueur=(float)experienceJoueur[i][2]+1;
                                            model_tire.execRequeteNonQuery("UPDATE JOUEUR SET levelJoueur ="+niveauJoueur+" WHERE idJoueur="+jeu.getJoueurNonConcerne().getIdJoueur());
                                        }
                                    }
                                }
                            }
                            catch(BDDException e3){
                                showMessageDialog(null,"The database is not accessible. The changements can't be save.","Database error", JOptionPane.ERROR_MESSAGE);
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