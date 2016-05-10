package Modele;

import java.io.*;

/**
 * Created by fparty2 on 06/05/16.
 */
public class Jeu implements Serializable{

    private Joueur joueur1;
    private Joueur joueur2;
    private boolean estPhasePlacement;
    private boolean concerneJoueur1;


    public Jeu(Joueur j1, Joueur j2){
        joueur1 = j1;
        joueur2 = j2;

        estPhasePlacement = true;
        concerneJoueur1 = true;
    }


    public void Sauvegarde(){
        ObjectOutputStream oos;
        ObjectInputStream ois;
        try {
            oos=new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(new File("ressource/Sauvegarde/Jeu.txt"))));
            oos.writeObject(this);
            oos.close();
        }
        catch (FileNotFoundException fnfe){
            fnfe.printStackTrace();
            System.out.println("fnfe");
        }
        catch (IOException ioe){
            ioe.printStackTrace();
            System.out.println("ioe"+ ioe.getMessage());
        }
    }

    public Joueur getJoueurConcerne(){
        Joueur joueur;
        if(concerneJoueur1){
           joueur = joueur1;
        }
        else {
            joueur = joueur2;
        }

        return joueur;
    }

    public Joueur getJoueurNonConcerne(){
        Joueur joueur;
        if(concerneJoueur1){
            joueur = joueur2;
        }
        else {
            joueur = joueur1;
        }

        return joueur;
    }

    public Joueur getJoueur1() {
        return joueur1;
    }

    public Joueur getJoueur2() {
        return joueur2;
    }


    public boolean getEstPhasePlacement() {
        return estPhasePlacement;
    }

    public void echangeEstPhasePlacement() {
        this.estPhasePlacement = !estPhasePlacement;
    }

    public boolean getConcerneJoueur1() {
        return concerneJoueur1;
    }

    public void echangeConcerneJoueur1() {
        this.concerneJoueur1 = !concerneJoueur1;
    }
}
