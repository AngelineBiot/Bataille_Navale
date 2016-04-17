import java.util.ArrayList;

/**
 * Created by angel on 16/04/2016.
 */
public class Bateaux{
    private int taille;
    protected ArrayList<Coordonnees> position;
    private boolean coule;
    private char orientation='v'; //v ou h

    public Bateaux(){}

    public int getTaille(){
        return taille;
    }
    public boolean getCoule() {
        return coule;
    }
    public void getCoordonnees() {
        position.forEach(System.out::println);
    }
    public char getOrientation(){
        return this.orientation;
    }
    public void setOrientation(char orientation){
        this.orientation = orientation;
    }
    public void setTaille(int taille){
        this.taille = taille;
    }
    public boolean estCoule(){
        int i;
        i = 0;
        for (Coordonnees coordonnees : position) {
            if (coordonnees.getTouche()) i++;
        }
        return i == this.getTaille();
    }
}
