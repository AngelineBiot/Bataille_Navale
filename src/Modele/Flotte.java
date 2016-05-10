package Modele;


import java.io.Serializable;

/**
 * Created by angel on 17/04/2016.
 */
public class Flotte implements Serializable{

    private Bateaux[] flotte;
    private int nbBateaux;
    private int nbBateauxCoule;
    private int nbTouches;

    public Flotte(Bateaux[] flotte) {
        this.flotte = flotte;
        nbBateaux = flotte.length;
        nbBateauxCoule = 0;
    }

    public static Flotte creerFlotte6bateaux(){
        Bateaux[] flotte = new Bateaux[6];
        flotte[0] = new Bateaux("porte-avion");
        flotte[1] = new Bateaux("croiseur");
        flotte[2] = new Bateaux("sous-marin");
        flotte[3] = new Bateaux("sous-marin");
        flotte[4] = new Bateaux("torpilleur");
        flotte[5] = new Bateaux("torpilleur");
        return new Flotte(flotte);
    }

    public void incrementeNbBateauxCoule(){
        nbBateauxCoule++;
    }

    public int getNbBateauxCoule(){
        return nbBateauxCoule;
    }

    public int getNbTouches(){
        return nbTouches;
    }

    public void incrementeNbBateauxTouche(){
        nbTouches++;
    }

    public boolean flotteCoulee(){
        return nbBateauxCoule == nbBateaux;
    }

    public Bateaux[] getFlotte(){
        return flotte;
    }

}
