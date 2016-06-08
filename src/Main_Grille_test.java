import Controleur.EcouteurConteneurInscription;
import Controleur.EcouteurFermeture;
import Controleur.EcouteurMenu;
import Modele.*;
import Vue.*;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Locale;

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
        BaseDeDonnees baseDeDonnees = null;

        try {
            baseDeDonnees = new BaseDeDonnees(jeu);
        }
        catch(BDDException e){
            fenetre.affichePopupErreurBDD(true);
            System.exit(2);
        }


        ModelConteneurInscription modelConteneurInscription=new ModelConteneurInscription();

        try{
            baseDeDonnees.initListeJoueurs();
        }
        catch(BDDException e){
            fenetre.affichePopupErreurBDD(true);
            System.exit(1);
        }

        if (baseDeDonnees.getJoueur()!=null){
            modelConteneurInscription.initListPseudo(baseDeDonnees.getJoueur());
        }

        ConteneurInscription conteneurInscription = new ConteneurInscription(modelConteneurInscription,false, baseDeDonnees);
        int hauteurConteneur = (int)(conteneurInscription.getPreferredSize().getHeight());
        int hauteurBox = (700-hauteurConteneur)/2;

        JPanel conteneurGlobal = new JPanel();
        conteneurGlobal.setLayout(new BoxLayout(conteneurGlobal, BoxLayout.Y_AXIS));
        conteneurGlobal.add(Box.createVerticalStrut(hauteurBox));
        conteneurGlobal.add(conteneurInscription);

        fenetre.setContentPane(conteneurGlobal);
        fenetre.validate();

        new EcouteurConteneurInscription(conteneurInscription, jeu, baseDeDonnees, fenetre,modelConteneurInscription);
        new EcouteurFermeture(fenetre, baseDeDonnees);
        new EcouteurMenu(fenetre,jeu, baseDeDonnees);
        fenetre.setVisible(true);

    }

}
