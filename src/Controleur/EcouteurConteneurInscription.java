package Controleur;

import Modele.*;
import Vue.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by fparty2 on 29/04/16.
 */
public class EcouteurConteneurInscription implements ActionListener {

    private ConteneurInscription conteneurInscription;
    private Fenetre fenetre;
    private Jeu jeu;



    public EcouteurConteneurInscription(ConteneurInscription conteneur, Jeu j, Fenetre fen) {
        conteneurInscription = conteneur;
        jeu = j;
        fenetre = fen;

        conteneurInscription.setEcouteurConteneurInscription(this);
    }

    public void actionPerformed(ActionEvent e){
        String pseudoJoueur1 = conteneurInscription.getlabelJoueur1().getText();
        String pseudoJoueur2 = conteneurInscription.getlabelJoueur2().getText();

        if (pseudoJoueur1.equals("") || pseudoJoueur2.equals("")) {
            JOptionPane jopErreurInscriptionVide = new JOptionPane();
            JOptionPane.showMessageDialog(null, "Veuillez entrer un pseudo pour chaque joueur", "Erreur", JOptionPane.ERROR_MESSAGE);
        } else if (pseudoJoueur1.equals(pseudoJoueur2)) {
            JOptionPane jopErreurInscriptionIdentique = new JOptionPane();
            JOptionPane.showMessageDialog(null, "Les deux joueurs doivent avoir des pseudos disctincts", "Erreur", JOptionPane.ERROR_MESSAGE);
        } else {
            jeu.getJoueur1().setNomJoueur(pseudoJoueur1);
            jeu.getJoueur2().setNomJoueur(pseudoJoueur2);

            ConteneurAttente conteneur = new ConteneurAttente(jeu);
            new EcouteurConteneurAttente(conteneur, fenetre, jeu);

            fenetre.setContentPane(conteneur);
            fenetre.validate();
        }
    }
}
