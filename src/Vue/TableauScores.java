package Vue;

/**
 * Created by ANTOINE on 02/05/2016.
 *
 */
import Modele.Flotte;
import Modele.Grille;
import Modele.Jeu;
import Modele.Joueur;

import javax.swing.*;
import java.util.Locale;
import java.util.ResourceBundle;

public class TableauScores extends JPanel {

    public TableauScores(Jeu jeu){
        creerWidget(jeu);
    }

    private void creerWidget(Jeu jeu) {

        ResourceBundle texteInternational = ResourceBundle.getBundle("traductions.TableauScores");

        this.setSize(300, 120);
        double efficaciteJoueur1;
        double efficaciteJoueur2;

        if (jeu.getJoueur1().getNbCoups() == 0) {
            efficaciteJoueur1 = 0;
        } else {    //On le calcule a partir du nombre de bateaux touches dans la flotte de l'autre joueur
            efficaciteJoueur1 = ((float)(jeu.getJoueur2().getFlotte().getNbTouches())/jeu.getJoueur1().getNbCoups())*100;
        }

        if (jeu.getJoueur2().getNbCoups() == 0) {
            efficaciteJoueur2 = 0;
        } else {
            efficaciteJoueur2 = ((float)(jeu.getJoueur1().getFlotte().getNbTouches())/jeu.getJoueur2().getNbCoups())*100;
        }

        //Les données du tableau
        Object[][] data = {
                {jeu.getJoueur1().getNomJoueur(),
                        String.valueOf(jeu.getJoueur1().getNbCoups()),
                        String.valueOf(jeu.getJoueur2().getFlotte().getNbBateauxCoule()),
                        String.valueOf(jeu.getJoueur2().getFlotte().getNbTouches()),
                        String.valueOf(efficaciteJoueur1)+"%"},
                {jeu.getJoueur2().getNomJoueur(),
                        String.valueOf(jeu.getJoueur2().getNbCoups()),
                        String.valueOf(jeu.getJoueur1().getFlotte().getNbBateauxCoule()),
                        String.valueOf(jeu.getJoueur1().getFlotte().getNbTouches()),
                        String.valueOf(efficaciteJoueur2)+"%"},
        };

        //Les titres des colonnes
        String  title[] = {texteInternational.getString("nom"),
                            texteInternational.getString("nombreTours"),
                            texteInternational.getString("nombreCoules"),
                            texteInternational.getString("nombreTouches"),
                            texteInternational.getString("efficacite")};
        JTable tableau = new JTable(data, title);
        //Nous ajoutons notre tableau à notre contentPane dans un scroll
        //Sinon les titres des colonnes ne s'afficheront pas !
        JScrollPane pano = new JScrollPane(tableau);
        this.add(pano);
    }
    public static void main(String[] args) {

        javax.swing.SwingUtilities.invokeLater( new Runnable() {

            public void run() {
                Grille grillej1 = new Grille();
                Grille grillej2 = new Grille();

                Flotte flottej1 = Flotte.creerFlotte6bateaux();
                Flotte flottej2 = Flotte.creerFlotte6bateaux();

                Joueur j1 = new Joueur(flottej1, grillej1);
                Joueur j2 = new Joueur(flottej2, grillej2);


                Jeu jeu = new Jeu(j1, j2);
                TableauScores f = new TableauScores(jeu);
                JFrame test = new JFrame();
                test.setContentPane(f);
                test.setVisible(true);
            }

        });
    }
}
