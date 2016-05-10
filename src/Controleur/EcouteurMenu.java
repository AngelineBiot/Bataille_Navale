package Controleur;

import Modele.Flotte;
import Modele.Grille;
import Modele.Jeu;
import Modele.Joueur;
import Vue.ConteneurInscription;
import Vue.Fenetre;
import Vue.ImageBateau;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

/**
 * Created by angel on 03/05/2016.
 * Updated by angel ON 03/05/2016.
 */
public class EcouteurMenu implements ActionListener{

    private Fenetre fenetre;

    public EcouteurMenu(Fenetre fenetre){
        this.fenetre = fenetre;
        fenetre.setControlMenu(this);
    }

    public void actionPerformed(ActionEvent e){

        Object source = e.getSource();

        String msgAide = "Cliquez sur une case pour la sélectionner\n" +
                            "Puis sur 'Valider le tir' pour tirer\n" +
                            "Si vous touchez un bateau, la case devient rouge, sinon elle devient bleue\n" +
                            "Si vous touchez toutes les cases sur lesquelles se trouve un bateau : vous l'avez coulé !\n" +
                            "Coulez toute la flotte de votre adversaire pour gagner !";
        String msgApropos = "Jeu créé par :\n\n" +
                                "BIOT Angeline\n" +
                                "GUERRE Michael\n" +
                                "LIAO Zuzhi\n" +
                                "PARTY Florian\n" +
                                "PY Antoine\n" +
                                "SURLEAU Etienne\n" +
                                "\n Etudiants en S2 DUT Informatique - IUT-BM (2016)";

        if (source.equals(fenetre.getItemAide()))
        {
            JOptionPane jopAide = new JOptionPane();
            jopAide.showMessageDialog(null,msgAide,"Aide",JOptionPane.INFORMATION_MESSAGE);

            jopAide.createDialog(null,"Aide");
        }
        else if(e.getSource()==fenetre.getItemApropos())
        {
            JOptionPane jopApropos = new JOptionPane();
            jopApropos.showMessageDialog(null,msgApropos,"A propos",JOptionPane.INFORMATION_MESSAGE);

            jopApropos.createDialog(null,"A propos");


        }
        // nouvelle partie
        if (e.getSource() ==fenetre.getNouvellePartie())
        {

            Grille grillej1 = new Grille();
            Grille grillej2 = new Grille();

            Flotte flottej1 = Flotte.creerFlotte6bateaux();
            Flotte flottej2 = Flotte.creerFlotte6bateaux();

            Joueur j1 = new Joueur(flottej1, grillej1);
            Joueur j2 = new Joueur(flottej2, grillej2);

            Jeu jeu = new Jeu(j1, j2);

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
        //quitter partie
        if (e.getSource()==fenetre.getQuitterPartie())
        {
            int result = JOptionPane.showConfirmDialog(null,"Vous voulez quitter?","Confirm",JOptionPane.YES_NO_OPTION);
            if  (result==JOptionPane.YES_OPTION)
                System.exit(0);
        }
    }
}
