import Controleur.EcouteurConteneurInscription;
import Modele.*;
import Vue.*;

import javax.swing.*;

public class Main_Grille_test {
    public static void main(String[] args) {

        ImageBateau.initTableauImagesBateau();

        Grille grillej1 = new Grille();
        Grille grillej2 = new Grille();

        Flotte flottej1 = Flotte.creerFlotte6bateaux();
        Flotte flottej2 = Flotte.creerFlotte6bateaux();

        Joueur j1 = new Joueur(flottej1, grillej1);
        Joueur j2 = new Joueur(flottej2, grillej2);

        Jeu jeu = new Jeu(j1, j2);


        Fenetre fenetre = new Fenetre("Bataille navale",jeu);

        ConteneurInscription conteneurInscription = new ConteneurInscription();
        int hauteurConteneur = (int)(conteneurInscription.getPreferredSize().getHeight());
        int hauteurBox = (700-hauteurConteneur)/2;

        JPanel conteneurGlobal = new JPanel();
        conteneurGlobal.setLayout(new BoxLayout(conteneurGlobal, BoxLayout.Y_AXIS));
        conteneurGlobal.add(Box.createVerticalStrut(hauteurBox));
        conteneurGlobal.add(conteneurInscription);

        fenetre.setContentPane(conteneurGlobal);
        fenetre.validate();

        new EcouteurConteneurInscription(conteneurInscription, jeu, fenetre);

        }

}
