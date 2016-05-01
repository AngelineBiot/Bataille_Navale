package Vue;

import javax.swing.*;
import java.awt.*;

import Controleur.EcouteurConteneurAttente;
import Modele.*;

/**
 * Created by ANTOINE on 17/04/2016.
 */
public class ConteneurAttente extends JPanel {
    ModelAttente modelAttente;
    JButton pret;

    public ConteneurAttente(ModelAttente model) {

        modelAttente = model;
        creerWidgetJoueur();

    }

    public void creerWidgetJoueur() {
        //Message
        JLabel lab = new JLabel(modelAttente.getNomJoueur()+" à toi de jouer",SwingConstants.CENTER); // SwingConstants.center sert à centrer le texte

        // Affichage du bouton prêt
        pret = new JButton("Prêt ?");;
        this.setLayout(new BorderLayout());
        add(lab, BorderLayout.CENTER);
        add(pret, BorderLayout.SOUTH);

    }

    public void setEcouteurConteneurAttente(EcouteurConteneurAttente ecouteur){
        pret.addActionListener(ecouteur);
    }


    /*public static void main(String[] args) {

        javax.swing.SwingUtilities.invokeLater( new Runnable() {

            public void run() {
                Vue.ConteneurAttente f = new Vue.ConteneurAttente("Nom_fictif");
            }

        });
    }*/
}

