/**
 * Created by ANTOINE on 02/05/2016.
 */
import Modele.Flotte;
import Modele.Joueur;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
public class Tableau_Score_2 extends JFrame{
    public Tableau_Score_2(){
        creerWidget();
        setSize(800,800);
        setVisible(true);                                // Affiche la fenetre
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public void creerWidget(){
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Tableau des Scores");
        this.setSize(300, 120);
        //double efficacite=(Flotte.getFlotteCoulee()/Joueur.getNbCoups());

        //Les données du tableau
        Object[][] data = {
                {Joueur.getJoueur(1),Joueur.getNbCoups(),Flotte.getFlotteCoulee(),Flotte.getNbTouches(),"efficacite"},
                {Joueur.getJoueur(2),Joueur.getNbCoups(), Flotte.getFlotteCoulee(),Flotte.getNbTouches(),"efficacite"},
        };

        //Les titres des colonnes
        String  title[] = {"Nom", "Nombre de tours", "Nombre de bateaux ennemis coulés","nombre de touchés","efficacité"};
        JTable tableau = new JTable(data, title);
        //Nous ajoutons notre tableau à notre contentPane dans un scroll
        //Sinon les titres des colonnes ne s'afficheront pas !
        this.getContentPane().add(new JScrollPane(tableau));
    }
    public static void main(String[] args) {

        javax.swing.SwingUtilities.invokeLater( new Runnable() {

            public void run() {

                Tableau_Score_2 f = new Tableau_Score_2();
            }

        });
    }
}
