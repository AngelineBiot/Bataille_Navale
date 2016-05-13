package Controleur;

import Modele.*;
import Vue.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;
import java.util.ResourceBundle;

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
        Locale locale = new Locale("");     // mettre "" pou anglais, code de deux lettres du pays pour les autres

        //!!!!!!!
        //Ca : que pour les tests : ENLEVER CA A LA FIN
        //!!!!!!!

        ResourceBundle texteInternational = ResourceBundle.getBundle("traductions.EcouteurConteneurInscription", locale);


        String pseudoJoueur1 = conteneurInscription.getlabelJoueur1().getText();
        String pseudoJoueur2 = conteneurInscription.getlabelJoueur2().getText();

        if (pseudoJoueur1.equals("") || pseudoJoueur2.equals("")) {
            JOptionPane jopErreurInscriptionVide = new JOptionPane();
            JOptionPane.showMessageDialog(null, texteInternational.getString("pseudoVide"),
                                        texteInternational.getString("erreur"), JOptionPane.ERROR_MESSAGE);
        } else if (pseudoJoueur1.equals(pseudoJoueur2)) {
            JOptionPane jopErreurInscriptionIdentique = new JOptionPane();
            JOptionPane.showMessageDialog(null, texteInternational.getString("memePseudos"),
                                        texteInternational.getString("erreur"), JOptionPane.ERROR_MESSAGE);
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
