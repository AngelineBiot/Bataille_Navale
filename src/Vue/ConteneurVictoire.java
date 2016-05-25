package Vue;

import Modele.Jeu;

import javax.swing.*;
import java.util.ResourceBundle;

/**
 * Created by fparty2 on 24/05/16.
 */
public class ConteneurVictoire extends JPanel{
    public ConteneurVictoire(Jeu jeu){
        ResourceBundle texteInternational = ResourceBundle.getBundle("traductions.EcouteurConteneurGrillePhaseTir");


        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel messageVictoire = new JLabel(texteInternational.getString("bravo")+
                jeu.getJoueurConcerne().getNomJoueur()+
                texteInternational.getString("gagne"));
        messageVictoire.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        add(messageVictoire);

        TableauScores tableauScore = new TableauScores(jeu);
        add(tableauScore);


    }
}
