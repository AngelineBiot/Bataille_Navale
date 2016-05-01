/**
 * Created by angel on 17/04/2016.
 * Updated 01/05/2016
 */
class Flotte {

    private Bateaux[] flotte;
    private int nbBateaux;
    private int nbBateauxCoule;

    private Flotte(Bateaux[] flotte) {
        this.flotte = flotte;
        nbBateaux = flotte.length;
        nbBateauxCoule = 0;
    }

    static Flotte creerFlotte6bateaux(){
        Bateaux[] flotte = new Bateaux[6];
        flotte[0] = new Bateaux("porte-avion");
        flotte[1] = new Bateaux("croiseur");
        flotte[2] = new Bateaux("sous-marin");
        flotte[3] = new Bateaux("sous-marin");
        flotte[4] = new Bateaux("torpilleur");
        flotte[5] = new Bateaux("torpilleur");
        return new Flotte(flotte);
    }

    public void setNbBateauxCoules(Flotte f, int i){
        f.nbBateauxCoule += i;
    }

    public boolean flotteCoulee(){
        return nbBateauxCoule == nbBateaux;
    }

    Bateaux[] getFlotte(){
        return flotte;
    }
}
