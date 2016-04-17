/**
 * Created by angel on 17/04/2016.
 */
public class Flotte {

    private Bateaux[] flotte;
    private int nbBateaux;

    public Flotte() {
        nbBateaux = 6;
        initFlotte(nbBateaux);
    }

    public void initFlotte(int nbBateaux){
        Bateaux[] flotte = new Bateaux[nbBateaux];
        flotte[0] = new Bateaux("Porte-avion");
        flotte[1] = new Bateaux("Croiseur");
        flotte[2] = new Bateaux("Sous-marin");
        flotte[3] = new Bateaux("Sous-marin");
        flotte[4] = new Bateaux("Torpilleur");
        flotte[5] = new Bateaux("Torpilleur");
    }
}
