package Vue;

import javax.swing.*;
import java.awt.*;

import Controleur.EcouteurConteneurAttente;
import Modele.*;

/**
 * Created by ANTOINE on 17/04/2016.
 */
public class ConteneurAttente extends JPanel {
    private JButton pret;

    public ConteneurAttente(Jeu j) {

        creerWidgetJoueur(j.getJoueurConcerne().getNomJoueur());

    }

    public void creerWidgetJoueur(String nomJoueur) {
        //Message
        JLabel lab = new JLabel(nomJoueur+" à toi de jouer",SwingConstants.CENTER); // SwingConstants.center sert à centrer le texte

        // Affichage du bouton prêt
        pret = new JButton("Prêt ?");;
        this.setLayout(new BorderLayout());
        add(lab, BorderLayout.CENTER);
        add(pret, BorderLayout.SOUTH);

    }

    public void setEcouteurConteneurAttente(EcouteurConteneurAttente ecouteur){
        pret.addActionListener(ecouteur);
    }

}