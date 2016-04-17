import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by angel on 16/04/2016.
 */
public class Bateaux{
    static final Scanner input = new Scanner(System.in);
    private int taille;
    protected ArrayList<Coordonnees> position;
    private boolean coule;
    private char orientation='v'; //v ou h

    public Bateaux(){}

    public Bateaux(String typeDeBateau){  //Porte-avion, Croiseur, Sous-marin, Torpilleur
        if (typeDeBateau.equals("Porte-avion")){
            this.taille=5;
        }
        if (typeDeBateau.equals("Croiseur")){
            this.taille=4;
        }
        if (typeDeBateau.equals("Sous-marin")){
            this.taille=3;
        }
        if (typeDeBateau.equals("Torpilleur")){
            this.taille=2;
        }
    }
    public int getTaille() {
        return taille;
    }
    public boolean getCoule(){
        return coule;
    }
    public void getCoordonnees(){
        position.forEach(System.out::println);
    }
    public char getOrientation(){
        return this.orientation;
    }
    public void setOrientation(char orientation){
        this.orientation=orientation;
    }
    public void setTaille(int taille){
        this.taille=taille;
    }
    public boolean estCoule(){
        int i = 0;
        for (Coordonnees coordonnees : position) {
                if (coordonnees.getTouche()) i++;
        }
        return i == this.taille;
    }
    public void initPosition(Coordonnees premiere_case){
        for (int i = 1; i <= taille-1; i++) {
            position.set(0, premiere_case);
            if (this.getOrientation()=='h') {
                Coordonnees nouvelleCoordonnee = new Coordonnees(premiere_case.getX(),premiere_case.getY()+1);
                position.set(i, nouvelleCoordonnee);
            }
            else {
                Coordonnees nouvelleCoordonnee = new Coordonnees(premiere_case.getX()+1,premiere_case.getY());
                position.set(i, nouvelleCoordonnee);
            }
        }
    }
}