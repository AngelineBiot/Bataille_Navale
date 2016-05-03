package Modele;

/**
 * Created by fparty2 on 28/04/16.
 */
public class Joueur {
    private String nomJoueur;
    private Flotte flotte;
    private Grille grille;

    private static Joueur joueur1;
    private static Joueur joueur2;




    private Joueur(){
        flotte = Flotte.creerFlotte6bateaux();
        grille = new Grille();
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

    public static void initJoueurs(){
        joueur1 = new Joueur();
        joueur2 = new Joueur();
    }

    public static Joueur getJoueur(int numeroJoueur){
        Joueur joueur;
        if(numeroJoueur == 1){
            joueur = joueur1;
        }
        else{
            joueur = joueur2;
        }
        return joueur;
    }
}
