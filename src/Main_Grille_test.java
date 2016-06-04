import Controleur.EcouteurConteneurInscription;
import Controleur.EcouteurFermeture;
import Controleur.EcouteurMenu;
import Modele.*;
import Vue.*;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Locale;
import java.util.ResourceBundle;

public class Main_Grille_test {
    public static void main(String[] args) {

        ImageBateau.initTableauImagesBateau();
        String langue = "en";

        try{
            BufferedReader fichierSauvegardeLangue = new BufferedReader(new FileReader("ressources/sauvegarde/choixLangue"));
            String ligne1 = fichierSauvegardeLangue.readLine();
            if(ligne1 != null && !ligne1.equals("")){
                langue = ligne1;
            }
            fichierSauvegardeLangue.close();
        }
        catch(Exception e){
        }



        Locale locale = new Locale(langue);
        Locale.setDefault(locale);



        Grille grillej1 = new Grille();
        Grille grillej2 = new Grille();

        Flotte flottej1 = Flotte.creerFlotte6bateaux();
        Flotte flottej2 = Flotte.creerFlotte6bateaux();

        Joueur j1 = new Joueur(flottej1, grillej1);
        Joueur j2 = new Joueur(flottej2, grillej2);

        Jeu jeu = new Jeu(j1, j2, langue);




        Fenetre fenetre = new Fenetre(jeu);
        ModelConteneurInscription modelConteneurInscription=new ModelConteneurInscription();
        ConteneurInscription conteneurInscription = new ConteneurInscription(modelConteneurInscription);
        int hauteurConteneur = (int)(conteneurInscription.getPreferredSize().getHeight());
        int hauteurBox = (700-hauteurConteneur)/2;

        JPanel conteneurGlobal = new JPanel();
        conteneurGlobal.setLayout(new BoxLayout(conteneurGlobal, BoxLayout.Y_AXIS));
        conteneurGlobal.add(Box.createVerticalStrut(hauteurBox));
        conteneurGlobal.add(conteneurInscription);

        fenetre.setContentPane(conteneurGlobal);
        fenetre.validate();

        new EcouteurConteneurInscription(conteneurInscription, jeu, fenetre,modelConteneurInscription);
        new EcouteurFermeture(fenetre);
        new EcouteurMenu(fenetre,jeu);
        fenetre.setVisible(true);

    }

}
