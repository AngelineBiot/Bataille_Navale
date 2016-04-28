/**
 * Created by fparty2 on 28/04/16.
 */
public class Joueur {
    private String nomJoueur;
    private Flotte flotte;
    private Grille grille;

    public Joueur(){
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

    public Grille getGrille(){
        return grille;
    }
}
