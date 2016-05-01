package Modele;

/**
 * Created by fparty2 on 29/04/16.
 */
public class ModelAttente {
    private boolean concerneJoueur1;
    private boolean concernePhasePlacement;
    private String nomJoueur;

    public ModelAttente(boolean concerneJ1, boolean concernePlacement, String nom){
        concerneJoueur1 = concerneJ1;
        concernePhasePlacement = concernePlacement;
        nomJoueur = nom;
    }

    public String getNomJoueur(){
        return nomJoueur;
    }

    public boolean getConcerneJoueur1(){
        return concerneJoueur1;
    }

    public boolean getConcernePhasePlacement(){
        return concernePhasePlacement;
    }
}
