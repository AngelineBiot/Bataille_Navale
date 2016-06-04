package Modele;

import Controleur.EcouteurConteneurAttente;
import Controleur.EcouteurFinAnimation;
import Vue.*;

import javax.swing.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Created by michael on 03/06/2016.
 */
public class Computer  implements Serializable {
    private int niveau;
    private List<Integer> casePossible;
    private List<Integer> caseImpossible;
    public static final int EASY = 0;
    public static final int MEDIUM = 1;
    public static final int HARD = 2;
    public static final Random random = new Random();
    public Computer(int niveau){
        this.niveau=niveau;
        casePossible=new ArrayList();
        for (int i=0;i<100;i++){
            casePossible.add(i);
        }
        caseImpossible=new ArrayList<>();
    }

    public void placerBateau(Fenetre fenetre,Jeu jeu){
        ModelConteneurPlacement model =new ModelConteneurPlacement();
        model.setCaseOuEstBateauEnCoursPlacement(null);

        int longueur = jeu.getJoueurConcerne().getFlotte().getFlotte()[jeu.getJoueurConcerne().getFlotte().getNbBateauxPlaces()].getTaille();
        boolean bon = false;
        for (int i=0; i<6;i++){
            while (!(bon && model.verifAucuneCaseDejaPrise(jeu.getJoueurConcerne().getGrille(),longueur))) {
                model.setDirectionVerticale(random.nextBoolean());
                model.setCoordX(random.nextInt(10));
                model.setCoordY(random.nextInt(10));
                model.setCoord1D(model.getCoordX() + model.getCoordY() * 10);
                bon = model.bateauEstPlacable(longueur);
            }
            model.setCaseOuEstBateauEnCoursPlacement(jeu.getJoueurConcerne().getGrille().getGrille()[model.getCoord1D()]);
            jeu.getJoueurConcerne().getFlotte().getFlotte()[jeu.getJoueurConcerne().getFlotte().getNbBateauxPlaces()].initPosition(model, jeu.getJoueurConcerne().getGrille());
            jeu.getJoueurConcerne().getFlotte().incrementeNbBateauxPlaces();
        }
        jeu.echangeConcerneJoueur1();
        jeu.echangeEstPhasePlacement();
        ConteneurAttente conteneurAttente = new ConteneurAttente(jeu);
        new EcouteurConteneurAttente(conteneurAttente, fenetre, jeu);

        fenetre.setContentPane(conteneurAttente);
        fenetre.validate();
    }
    public void tirer(ConteneurAttente conteneurAttente,Fenetre fenetre,Jeu jeu){
        conteneurAttente.desactivePret();
        ModelConteneurTir model_tire = new ModelConteneurTir();
        int randomCase=random.nextInt(casePossible.size());
        while (caseImpossible.contains(casePossible.get(randomCase))) {
            randomCase = random.nextInt(casePossible.size());
        }
        for (int i=0; i<casePossible.size();i++){
            System.out.println(casePossible.get(i));
        }
        model_tire.setCaseOuEstTire(jeu.getJoueurNonConcerne().getGrille().getGrille()[casePossible.get(randomCase)]);
        int caseTouche=casePossible.get(randomCase);
        casePossible.remove(randomCase);
        caseImpossible.add(randomCase);
        boolean dejaTirSurCase = model_tire.getCaseOuEstTire().getToucher();
        jeu.getJoueurConcerne().setNbcoups();
        model_tire.getCaseOuEstTire().setToucher();


        if (model_tire.getCaseOuEstTire().getBat() != null && !dejaTirSurCase) {
            if (jeu.getJoueurConcerne().getNbCoups()==1){
                if (model_tire.execQuery("SELECT * FROM joueurAchievement WHERE idJoueur="+jeu.getJoueurConcerne().getIdJoueur()+" AND idAchievement=2").length==0) {
                    model_tire.execRequeteNonQuery("INSERT INTO joueurachievement (idJoueur, idAchievement) VALUES (" + jeu.getJoueurConcerne().getIdJoueur() + ",2)");
                }
            }
            jeu.getJoueurNonConcerne().getFlotte().incrementeNbBateauxTouche();
            model_tire.getCaseOuEstTire().getBat().updateEstCoule();

            if(niveau==MEDIUM) {
                System.out.println("test : " + jeu.getJoueurNonConcerne().getFlotte().getNbTouches());
                if (jeu.getJoueurNonConcerne().getFlotte().getNbTouches() == 1) {
                    if (jeu.getJoueurConcerne().getNbCoups() == 1) {
                        casePossible.clear();
                    }
                }
                if (caseTouche + 1 < 100 && !caseImpossible.contains(caseTouche + 1)) {
                    casePossible.add(caseTouche + 1);
                }
                if (caseTouche - 1 > 0 && !caseImpossible.contains(caseTouche - 1)) {
                    casePossible.add(caseTouche - 1);
                }
                if (caseTouche + 10 < 100 && !caseImpossible.contains(randomCase + 10)) {
                    casePossible.add(caseTouche + 10);
                }
                if (caseTouche - 10 > 0 && !caseImpossible.contains(caseTouche - 10)) {
                    casePossible.add(caseTouche - 10);
                }
            }
            if(model_tire.getCaseOuEstTire().getBat().getCoule()){

                jeu.getJoueurNonConcerne().getFlotte().incrementeNbBateauxCoule();

                if(jeu.getJoueurNonConcerne().getFlotte().flotteCoulee()){
                    if (jeu.getJoueurConcerne().getNbCoups()==jeu.getJoueurNonConcerne().getFlotte().getNbTouches()){
                        if (model_tire.execQuery("SELECT * FROM joueurAchievement WHERE idJoueur="+jeu.getJoueurConcerne().getIdJoueur()+" AND idAchievement=1").length==0) {
                            model_tire.execRequeteNonQuery("INSERT INTO joueurachievement (idJoueur, idAchievement) VALUES (" + jeu.getJoueurConcerne().getIdJoueur() + ",1)");
                        }
                    }
                    jeu.setPartieFinie();
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
                    AnimationFin ac = new AnimationFin();
                    EcouteurFinAnimation ecouteurFinAnimation = new EcouteurFinAnimation(ac, fenetre, jeu, true);
                    Timer timer=new Timer(4700, ecouteurFinAnimation);
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