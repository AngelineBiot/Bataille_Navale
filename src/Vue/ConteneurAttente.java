package Vue;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

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



        ResourceBundle texteInternational = ResourceBundle.getBundle("traductions.ConteneurAttente");


        JLabel lab = new JLabel(nomJoueur+" "+ texteInternational.getString("tour"),SwingConstants.CENTER); // SwingConstants.center sert à centrer le texte

        // Affichage du bouton prêt
        pret = new JButton(texteInternational.getString("pret"));
        this.setLayout(new BorderLayout());
        add(lab, BorderLayout.CENTER);
        add(pret, BorderLayout.SOUTH);

    }

    public void setEcouteurConteneurAttente(EcouteurConteneurAttente ecouteur){
        pret.addActionListener(ecouteur);
    }

}