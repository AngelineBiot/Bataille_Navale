package Modele;

/**
 * Created by fparty2 on 28/04/16.
 */
public class Joueur {
    private String nomJoueur;
    private Flotte flotte;
    private Grille grille;


    public Joueur(Flotte fl, Grille gr){
        flotte = fl;
        grille = gr;
    }

    public void setNomJoueur(String nom){
        nomJoueur = nom;
    }

    public String getNomJoueur(){
        return nomJoueur;
    }

    public Flotte getFlotte(){
        return flotte;
    }

    public static int getNbCoups(){
        int nbcoups=0;
        return nbcoups;
    }

    public Grille getGrille(){
        return grille;
    }


}
