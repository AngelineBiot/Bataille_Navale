/**
 * Created by angel on 17/04/2016.
 */
public class Flotte {

    private Bateaux[] flotte;
    private int nbBateaux;
    private int nbBateauxCoule;

    public Flotte() {
        nbBateaux = 6;
        nbBateauxCoule = 0;
        initFlotte(nbBateaux);
    }

    public void initFlotte(int nbBateaux){
        flotte = new Bateaux[nbBateaux];
        flotte[0] = new Bateaux("porte-avion");
        flotte[1] = new Bateaux("croiseur");
        flotte[2] = new Bateaux("sous-marin");
        flotte[3] = new Bateaux("sous-marin");
        flotte[4] = new Bateaux("torpilleur");
        flotte[5] = new Bateaux("torpilleur");

    }

    public Bateaux[] getFlotte(){
        return flotte;
    }
}
