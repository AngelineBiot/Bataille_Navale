package Modele;

/**
 * Created by fparty2 on 28/04/16.
 */
public class Joueur {
    private String nomJoueur;
    private Flotte flotte;
    private Grille grille;
    private int nbcoups;

    public Joueur(Flotte fl, Grille gr){
        flotte = fl;
        grille = gr;
        nbcoups = 0;
    }

    public void setNomJoueur(String nom){
        nomJoueur = nom;
    }

    public void setNbcoups() {
        nbcoups++;
    }

    public String getNomJoueur(){
        return nomJoueur;
    }

    public Flotte getFlotte(){
        return flotte;
    }

    public int getNbCoups(){
        return nbcoups;
    }

    public Grille getGrille(){
        return grille;
    }


}
