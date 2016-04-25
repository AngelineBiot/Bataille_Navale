import com.sun.org.apache.bcel.internal.classfile.FieldOrMethod;

/**
 * Created by angel on 17/04/2016.
 * Updated by angel ON 17/04/2016.
 */
class Flotte {

    private Bateaux[] flotte;
    private int nbBateaux;
    private int nbBateauxCoule;

    public Flotte() {
        nbBateaux = 6;
        nbBateauxCoule = 0;
        initFlotte(nbBateaux);
    }

    private void initFlotte(int nbBateaux){
        Bateaux[] flotte = new Bateaux[nbBateaux];
        flotte[0] = new Bateaux("Porte-avion");
        flotte[1] = new Bateaux("Croiseur");
        flotte[2] = new Bateaux("Sous-marin");
        flotte[3] = new Bateaux("Sous-marin");
        flotte[4] = new Bateaux("Torpilleur");
        flotte[5] = new Bateaux("Torpilleur");
    }

    public boolean flotteCoulee(Flotte f){
        return f.nbBateauxCoule == f.nbBateaux;
    }
}
