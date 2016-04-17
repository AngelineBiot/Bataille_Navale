import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by angel on 16/04/2016.
 */
class Bateaux{
    static final Scanner input = new Scanner(System.in);
    private int taille;
    private ArrayList<Case> position;
    private boolean coule;
    private char orientation='v'; //v ou h

    public Bateaux(){}

    public Bateaux(String typeDeBateau){
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

    public void setBateaux(String typeDeBateau){  //Porte-avion, Croiseur, Sous-marin, Torpilleur
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
        for (Case coordonnees : position) {
                if (coordonnees.getToucher()) i++;
        }
        return i == this.taille;
    }
    public void initPosition(Case premiere_case){
        for (int i = 1; i <= taille-1; i++) {
            position.set(0, premiere_case);
            if (this.getOrientation()=='h') {
                Case nouvelleCoordonnee = new Case(premiere_case.getCoordoneX(),premiere_case.getCoordoneY()+1);
                position.set(i, nouvelleCoordonnee);
            }
            else {
                Case nouvelleCoordonnee = new Case(premiere_case.getCoordoneX()+1,premiere_case.getCoordoneY());
                position.set(i, nouvelleCoordonnee);
            }
        }
    }
}