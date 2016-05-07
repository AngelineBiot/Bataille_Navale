package Modele;


/**
 * Created by michael on 31/03/2016.
 */
public class Grille {

    private Case[] grille;
    public Grille(){
        grille=new Case[100];
        for (int i=0;i<100;i++){
            int x=i%10;
            int y=i/10;
            grille[i]=new Case(x,y);
        }
    }

    public Case[] getGrille(){
        return grille;
    }
}

