package Modele;



import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by michael on 03/06/2016.
 */
public class Computer  implements Serializable {
    private int niveau;
    private transient BaseDeDonnees baseDeDonnees;
    private List<Integer> casePossible;
    private List<Integer> caseImpossible;
    public static final int EASY = 0;
    public static final int MEDIUM = 1;
    public static final int HARD = 2;
    public static final Random random = new Random();



    public Computer(BaseDeDonnees base, int niveau){
        this.niveau=niveau;
        baseDeDonnees = base;

        casePossible=new ArrayList();
        for (int i=0;i<100;i++){
            casePossible.add(i);
        }
        caseImpossible=new ArrayList<>();
    }

    public void placerBateau(Jeu jeu){
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

    }
    public boolean tirer(Jeu jeu, ModelConteneurTir model_tir){
        int randomCase=random.nextInt(casePossible.size());
        while (caseImpossible.contains(casePossible.get(randomCase))) {
            randomCase = random.nextInt(casePossible.size());
        }
        System.out.println("5555555555");
        for (int i=0; i<casePossible.size();i++){
            System.out.println(casePossible.get(i));
        }
        System.out.println("5555555555");
        System.out.println(randomCase);
        int caseTouche=casePossible.get(randomCase);

        model_tir.setCaseOuEstTire(jeu.getJoueurNonConcerne().getGrille().getGrille()[caseTouche]);
        casePossible.remove(randomCase);
        caseImpossible.add(caseTouche);
        boolean dejaTirSurCase = model_tir.getCaseOuEstTire().getToucher();
        jeu.getJoueurConcerne().setNbcoups();
        model_tir.getCaseOuEstTire().setToucher();

        boolean estPasRate = model_tir.getCaseOuEstTire().getBat() != null && !dejaTirSurCase;
        if (estPasRate) {
            if (jeu.getJoueurConcerne().getNbCoups() == 1) {   //Si le joueur touche un bateau du premier coup, il débloque un achievement
                baseDeDonnees.debloqueLuckyShot(jeu);        //On met donc a jour la bdd
            }

            jeu.getJoueurNonConcerne().getFlotte().incrementeNbBateauxTouche();
            model_tir.getCaseOuEstTire().getBat().updateEstCoule();

            if (niveau == MEDIUM) {

                if (jeu.getJoueurNonConcerne().getFlotte().getNbTouches() == 1) {
                    casePossible.clear();
                    System.out.println("merde");
                }
                if (caseTouche + 1 < 100 && !caseImpossible.contains(caseTouche + 1)) {
                    casePossible.add(caseTouche + 1);
                }
                if (caseTouche - 1 > 0 && !caseImpossible.contains(caseTouche - 1)) {
                    casePossible.add(caseTouche - 1);
                }
                if (caseTouche + 10 < 100 && !caseImpossible.contains(caseTouche + 10)) {
                    casePossible.add(caseTouche + 10);
                }
                if (caseTouche - 10 > 0 && !caseImpossible.contains(caseTouche - 10)) {
                    casePossible.add(caseTouche - 10);
                }
            }
        }

        return estPasRate;

    }

    public void setBaseDeDonnees(BaseDeDonnees base){
        baseDeDonnees = base;
    }
}
