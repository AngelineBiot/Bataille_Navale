import Controleur.EcouteurConteneurInscription;
import Modele.*;
import Vue.*;

import javax.swing.*;

public class Main_Grille_test {
    public static void main(String[] args) {

        ImageBateau.initTableauImagesBateau();
        Joueur.initJoueurs();
        Fenetre.initFenetre();

        ConteneurInscription conteneurInscription = new ConteneurInscription();
        int hauteurConteneur = (int)(conteneurInscription.getPreferredSize().getHeight());
        int hauteurBox = (700-hauteurConteneur)/2;

        JPanel conteneurGlobal = new JPanel();
        conteneurGlobal.setLayout(new BoxLayout(conteneurGlobal, BoxLayout.Y_AXIS));
        conteneurGlobal.add(Box.createVerticalStrut(hauteurBox));
        conteneurGlobal.add(conteneurInscription);

        Fenetre.getFenetre().setContentPane(conteneurGlobal);
        Fenetre.getFenetre().validate();

        EcouteurConteneurInscription ecouteur = new EcouteurConteneurInscription(conteneurInscription);

        }

}
