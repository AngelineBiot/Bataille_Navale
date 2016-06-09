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
    private List<Integer> caseToucheNonCoule;
    public static final int EASY = 0;
    public static final int MEDIUM = 1;
    public static final int HARD = 2;
    public static final Random random = new Random();



    public Computer(BaseDeDonnees base, int niveau){
        this.niveau=niveau;
        baseDeDonnees = base;

        casePossible=new ArrayList();
        caseImpossible=new ArrayList<>();
        caseToucheNonCoule=new ArrayList<>();
        initCasePossible();
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
        System.out.println("case possible :");
        for (int i=0; i<casePossible.size();i++){
            System.out.println(casePossible.get(i));
        }
        System.out.println();
        System.out.println();
        System.out.println("case touche");
        System.out.println(casePossible.get(randomCase));
        System.out.println();
        System.out.println();
        System.out.println("index");
        System.out.println(randomCase);
        System.out.println();
        System.out.println();

        int caseTouche=casePossible.get(randomCase);

        model_tir.setCaseOuEstTire(jeu.getJoueurNonConcerne().getGrille().getGrille()[caseTouche]);
        casePossible.remove(randomCase);
        caseImpossible.add(caseTouche);
        boolean dejaTirSurCase = model_tir.getCaseOuEstTire().getToucher();
        jeu.getJoueurConcerne().setNbcoups();
        model_tir.getCaseOuEstTire().setToucher();

        boolean estPasRate = model_tir.getCaseOuEstTire().getBat() != null && !dejaTirSurCase;
        if (estPasRate) {
            caseToucheNonCoule.add(caseTouche);
            if (jeu.getJoueurConcerne().getNbCoups() == 1) {   //Si le joueur touche un bateau du premier coup, il débloque un achievement
                baseDeDonnees.debloqueLuckyShot(jeu);        //On met donc a jour la bdd
            }
            System.out.println("case touché non coulé :");
            for (int i=0; i<caseToucheNonCoule.size();i++){
                System.out.println(caseToucheNonCoule.get(i));
            }
            System.out.println();
            System.out.println();
            jeu.getJoueurNonConcerne().getFlotte().incrementeNbBateauxTouche();
            model_tir.getCaseOuEstTire().getBat().updateEstCoule();

            if (niveau == MEDIUM) {
                if (model_tir.getCaseOuEstTire().getBat().getCoule()){
                    int aSuppr = (model_tir.getCaseOuEstTire().getBat().getEstOrienteVerticalement())?10:1;
                    for (int i=0;i<model_tir.getCaseOuEstTire().getBat().getTaille();i++){
                        System.out.println("case enlevé");
                        System.out.println(i);
                        caseToucheNonCoule.remove(new Integer(model_tir.getCaseOuEstTire().getBat().getCoordonneesPremiereCase()+i*aSuppr));
                    }
                }
                initCasePossible();
            }
        }

        return estPasRate;

    }

    public void initCasePossible(){
        casePossible.clear();
        if (caseToucheNonCoule.size()==0){
            for (int i=0;i<100;i++){
                if (caseImpossible.size()==0 || !caseImpossible.contains(i)) {
                    casePossible.add(i);
                }
            }
        }else {
            for (int i=0;i<caseToucheNonCoule.size();i++){
                if (caseToucheNonCoule.get(i) + 1 < 100 && !caseImpossible.contains(caseToucheNonCoule.get(i) + 1) && !(caseToucheNonCoule.get(i)%10==9 && (caseToucheNonCoule.get(i)+1)%10==0)) {
                    casePossible.add(caseToucheNonCoule.get(i) + 1);
                }
                if (caseToucheNonCoule.get(i) - 1 > 0 && !caseImpossible.contains(caseToucheNonCoule.get(i) - 1) && !(caseToucheNonCoule.get(i)%10==0 && (caseToucheNonCoule.get(i)-1)%10==9)) {
                    casePossible.add(caseToucheNonCoule.get(i) - 1);
                }
                if (caseToucheNonCoule.get(i) + 10 < 100 && !caseImpossible.contains(caseToucheNonCoule.get(i) + 10)) {
                    casePossible.add(caseToucheNonCoule.get(i) + 10);
                }
                if (caseToucheNonCoule.get(i) - 10 > 0 && !caseImpossible.contains(caseToucheNonCoule.get(i) - 10)) {
                    casePossible.add(caseToucheNonCoule.get(i) - 10);
                }
            }
        }
    }

    public void setBaseDeDonnees(BaseDeDonnees base){
        baseDeDonnees = base;
    }
}
