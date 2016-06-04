package Modele;

import java.io.*;
import java.util.Locale;

/**
 * Created by fparty2 on 06/05/16.
 *
 */
public class Jeu implements Serializable{

    private Joueur joueur1;
    private Joueur joueur2;
    private String langue;
    private boolean estPhasePlacement;
    private boolean concerneJoueur1;
    private boolean partieFinie;


    public Jeu(Joueur j1, Joueur j2, String l){
        joueur1 = j1;
        joueur2 = j2;
        langue = l;

        estPhasePlacement = true;
        concerneJoueur1 = true;
        partieFinie = false;
    }


    public static void Sauvegarde(Jeu jeu) throws SauvegardeException{
        ObjectOutputStream oos;
        if(jeu.getJoueur1().getNomJoueur() == null || jeu.getJoueur2().getNomJoueur() == null || jeu.isPartieFinie()){
            throw new SauvegardeException();
        }

        try {
            oos=new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(new File("ressources/sauvegarde/Jeu"))));
            oos.writeObject(jeu);
            oos.close();
        }
        catch (IOException ioe){
            ioe.printStackTrace();
            throw new SauvegardeException();
        }
    }
    public static Jeu resumeGame() throws SauvegardeException{
        ObjectInputStream ois;
        Jeu jeu;
        try {
            ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream("ressources/sauvegarde/Jeu")));
            jeu=(Jeu)ois.readObject();
            ois.close();

        }
        catch (Exception e){
            throw new SauvegardeException();
        }
        return jeu;
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

    public void setPartieFinie(){
        partieFinie = true;
    }

    public boolean isPartieFinie(){
        return partieFinie;
    }

    public String getLangue(){
        return langue;
    }

    public void setLangue(String l){
        langue =l;

        Locale locale = new Locale(langue);
        Locale.setDefault(locale);

        try{
            BufferedWriter fichierSauvegardeLangue = new BufferedWriter(new FileWriter("ressources/sauvegarde/choixLangue"));
            fichierSauvegardeLangue.write(langue);
            fichierSauvegardeLangue.close();
        }
        catch(Exception e){
        }
    }
}
