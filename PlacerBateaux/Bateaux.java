import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by angel on 16/04/2016.
 */
public class Bateaux{

    private int taille;
    private Case[] position;
    private boolean coule;
    private boolean estOrienteVerticalement; //v ou h
    private String typeBateau;

    public Bateaux(){}

    public Bateaux(String typeDeBateau){
        coule = false;

        typeBateau = typeDeBateau;

        if (typeBateau.equals("porte-avion")){
            this.taille=5;
        }
        else if (typeBateau.equals("croiseur")){
            this.taille=4;
        }
        else if (typeBateau.equals("sous-marin")){
            this.taille=3;
        }
        else if (typeBateau.equals("torpilleur")){
            this.taille=2;
        }
    }

    public void setBateaux(String typeDeBateau){  //Porte-avion, Croiseur, Sous-marin, Torpilleur
        if (typeDeBateau.equals("porte-avion")){
            this.taille=5;
        }
        if (typeDeBateau.equals("croiseur")){
            this.taille=4;
        }
        if (typeDeBateau.equals("sous-marin")){
            this.taille=3;
        }
        if (typeDeBateau.equals("torpilleur")){
            this.taille=2;
        }
    }

    public int getTaille() {
        return taille;
    }
    public boolean getCoule(){
        return coule;
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

    /*public void initPosition(Case premiere_case) {
        for (int i = 1; i <= taille - 1; i++) {
            position.set(0, premiere_case);
            if (this.getOrientation() == 'h') {
                Case nouvelleCoordonnee = new Case(premiere_case.getCoordoneX(), premiere_case.getCoordoneY() + 1);
                position.set(i, nouvelleCoordonnee);
            } else {
                Case nouvelleCoordonnee = new Case(premiere_case.getCoordoneX() + 1, premiere_case.getCoordoneY());
                position.set(i, nouvelleCoordonnee);
            }
        }
    }*/

    public void setPosition(Case[] tabCases, boolean estVertical){
        position = tabCases;
        estOrienteVerticalement = estVertical;
    }

    public String getTypeBateau(){
        return typeBateau;
    }
}