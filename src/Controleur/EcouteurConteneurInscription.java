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


    public EcouteurConteneurInscription(ConteneurInscription conteneur, Jeu j, Fenetre fen,ModelConteneurInscription m) {
        model=m;
        conteneurInscription = conteneur;
        jeu = j;
        fenetre = fen;

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
            if (!pseudoJoueur2.equals("GLaDAS")){
                if (!Arrays.stream(model.getListPseudo()).anyMatch(x -> x == pseudoJoueur1)){
                    model.execRequeteNonQuery("INSERT INTO JOUEUR (idJoueur, pseudoJoueur) VALUES (NULL, '"+pseudoJoueur1+"')");
                    increm++;
                }
                if (!Arrays.stream(model.getListPseudo()).anyMatch(x -> x == pseudoJoueur2)){
                    model.execRequeteNonQuery("INSERT INTO JOUEUR (idJoueur, pseudoJoueur) VALUES (NULL, '"+pseudoJoueur2+"')");
                    increm++;
                }
            }
            model.initJoueur();
            if (model.getJoueur()!=null){
                model.initListPseudo(model.getJoueur());
            }
            jeu.getJoueur1().setNomJoueur(pseudoJoueur1);
            if (conteneurInscription.getjComboBoxJoueur1().getSelectedIndex()<0){
                jeu.getJoueur1().setIdJoueur((int)model.getJoueur()[conteneurInscription.getjComboBoxJoueur1().getItemCount()][0]);
            }else {
                jeu.getJoueur1().setIdJoueur((int)model.getJoueur()[conteneurInscription.getjComboBoxJoueur1().getSelectedIndex()+1][0]);
            }
            jeu.getJoueur2().setNomJoueur(pseudoJoueur2);
            if (!pseudoJoueur2.equals("GLaDAS")){
                if (conteneurInscription.getjComboBoxJoueur2().getSelectedIndex()<0){
                    jeu.getJoueur2().setIdJoueur((int)model.getJoueur()[conteneurInscription.getjComboBoxJoueur2().getItemCount()+increm-1][0]);
                }else {
                    jeu.getJoueur2().setIdJoueur((int)model.getJoueur()[conteneurInscription.getjComboBoxJoueur2().getSelectedIndex()+1][0]);
                }
            }else {
                jeu.getJoueur2().setIdJoueur(-1);
                String[] buttons = {"EASY","MEDIUM","CANCEL"};
                int result = JOptionPane.showOptionDialog(fenetre, "Niveau de difficulté de l'IA","Confirmation",JOptionPane.INFORMATION_MESSAGE,0,null,buttons,buttons[1]);
                System.out.println(result);
                jeu.getJoueur2().setComputer(new Computer(result));
            }
            ConteneurAttente conteneur = new ConteneurAttente(jeu);
            new EcouteurConteneurAttente(conteneur, fenetre, jeu);

            fenetre.setContentPane(conteneur);
            fenetre.validate();
        }
    }
}
