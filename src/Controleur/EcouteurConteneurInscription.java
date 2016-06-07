package Controleur;

import Modele.*;
import Vue.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.ResourceBundle;

/**
 * Created by fparty2 on 29/04/16.
 *
 */
public class EcouteurConteneurInscription implements ActionListener {

    private ConteneurInscription conteneurInscription;
    private Fenetre fenetre;
    private Jeu jeu;
    private ModelConteneurInscription model;
    private BaseDeDonnees baseDeDonnees;


    public EcouteurConteneurInscription(ConteneurInscription conteneur, Jeu j, BaseDeDonnees base, Fenetre fen,ModelConteneurInscription m) {
        model=m;
        conteneurInscription = conteneur;
        jeu = j;
        fenetre = fen;
        baseDeDonnees = base;

        conteneurInscription.setEcouteurConteneurInscription(this);
    }

    public void actionPerformed(ActionEvent e){


        ResourceBundle texteInternational = ResourceBundle.getBundle("traductions.EcouteurConteneurInscription");


        String pseudoJoueur1 =(String) conteneurInscription.getjComboBoxJoueur1().getSelectedItem();
        String pseudoJoueur2 = (String)conteneurInscription.getjComboBoxJoueur2().getSelectedItem();

        if (pseudoJoueur1.equals("") || pseudoJoueur2.equals("")) {
            JOptionPane.showMessageDialog(null, texteInternational.getString("pseudoVide"),
                                        texteInternational.getString("erreur"), JOptionPane.ERROR_MESSAGE);
        } else if (pseudoJoueur1.equals(pseudoJoueur2)) {
            JOptionPane.showMessageDialog(null, texteInternational.getString("memePseudos"),
                                        texteInternational.getString("erreur"), JOptionPane.ERROR_MESSAGE);
        } else {
            int increm=0;
            try {
                if (!pseudoJoueur2.equals("GLaDAS")) {
                    int increment = baseDeDonnees.insertJoueur(model, pseudoJoueur1, pseudoJoueur2);
                    increm += increment;
                }
                baseDeDonnees.initJoueur();
            }
            catch(BDDException e1){
                new PopUpErreurBDD(true);
                baseDeDonnees.fermeConnexion();
                System.exit(2);
            }

            if (baseDeDonnees.getJoueur()!=null){
                model.initListPseudo(baseDeDonnees.getJoueur());
            }
            jeu.getJoueur1().setNomJoueur(pseudoJoueur1);


            if (conteneurInscription.getjComboBoxJoueur1().getSelectedIndex()<0){
                jeu.getJoueur1().setIdJoueur((int)baseDeDonnees.getJoueur()[conteneurInscription.getjComboBoxJoueur1().getItemCount()][0]);
            }else {
                jeu.getJoueur1().setIdJoueur((int)baseDeDonnees.getJoueur()[conteneurInscription.getjComboBoxJoueur1().getSelectedIndex()+1][0]);
            }
            jeu.getJoueur2().setNomJoueur(pseudoJoueur2);


            if (!pseudoJoueur2.equals("GLaDAS")){
                if (conteneurInscription.getjComboBoxJoueur2().getSelectedIndex()<0){
                    jeu.getJoueur2().setIdJoueur((int)baseDeDonnees.getJoueur()[conteneurInscription.getjComboBoxJoueur2().getItemCount()+increm-1][0]);
                }else {
                    jeu.getJoueur2().setIdJoueur((int)baseDeDonnees.getJoueur()[conteneurInscription.getjComboBoxJoueur2().getSelectedIndex()+1][0]);
                }
            }else {
                jeu.getJoueur2().setIdJoueur(-1);
                String[] buttons = {"EASY","MEDIUM","CANCEL"};
                int result = JOptionPane.showOptionDialog(fenetre, "Niveau de difficulté de l'IA","Confirmation",JOptionPane.INFORMATION_MESSAGE,0,null,buttons,buttons[1]);
                System.out.println(result);
                jeu.getJoueur2().setComputer(new Computer(baseDeDonnees, result));
            }
            ConteneurAttente conteneur = new ConteneurAttente(jeu);
            new EcouteurConteneurAttente(conteneur, fenetre, jeu, baseDeDonnees);

            fenetre.setContentPane(conteneur);
            fenetre.validate();
        }
    }
}
