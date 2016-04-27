import java.util.List;

/**
 * Created by angel on 17/04/2016.
 * Updated by angel on 25/04/2016.
 */
class Flotte {

    private Bateaux[] flotte;
    private int nbBateaux;
    private int nbBateauxCoule;

    public Flotte(Bateaux[] flotte) {
        this.flotte = flotte;
        nbBateaux = flotte.length;
        nbBateauxCoule = 0;
    }

    public static Flotte creerFlotte6bateaux(){
        Bateaux[] flotte = new Bateaux[6];
        flotte[0] = new Bateaux("Porte-avion");
        flotte[1] = new Bateaux("Croiseur");
        flotte[2] = new Bateaux("Sous-marin");
        flotte[3] = new Bateaux("Sous-marin");
        flotte[4] = new Bateaux("Torpilleur");
        flotte[5] = new Bateaux("Torpilleur");
        return new Flotte(flotte);
    }

    public void setNbBateauxCoule(Flotte f){

    }

    public boolean flotteCoulee(){
        return nbBateauxCoule == nbBateaux;
    }
}
