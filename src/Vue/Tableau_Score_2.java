package Vue; /**
 * Created by ANTOINE on 02/05/2016.
 */
import Modele.Flotte;
import Modele.Grille;
import Modele.Jeu;
import Modele.Joueur;

import javax.swing.*;

public class Tableau_Score_2 {
    public Tableau_Score_2(Jeu jeu){
        creerWidget(jeu);
        //this.setSize(300, 120);
    }
    public void creerWidget(Jeu jeu){
        //double efficacite=(Flotte.getFlotteCoulee()/Joueur.getNbCoups());

        //Les données du tableau
        Object[][] data = {
                {jeu.getJoueur1(),Joueur.getNbCoups(),Flotte.getFlotteCoulee(),Flotte.getNbTouches(),"efficacite"},
                {jeu.getJoueur2(),Joueur.getNbCoups(), Flotte.getFlotteCoulee(),Flotte.getNbTouches(),"efficacite"},
        };

        //Les titres des colonnes
        String  title[] = {"Nom", "Nombre de tours", "Bateaux ennemis coulés","Bateaux ennemis touchés","Efficacité"};
        JTable tableau = new JTable(data, title);
        //Nous ajoutons notre tableau à notre contentPane dans un scroll
        //Sinon les titres des colonnes ne s'afficheront pas !
        //this.getContentPane().add(new JScrollPane(tableau));
    }
    /*public static void main(String[] args) {

        javax.swing.SwingUtilities.invokeLater( new Runnable() {

            public void run() {
                Grille grillej1 = new Grille();
                Grille grillej2 = new Grille();

                Flotte flottej1 = Flotte.creerFlotte6bateaux();
                Flotte flottej2 = Flotte.creerFlotte6bateaux();

                Joueur j1 = new Joueur(flottej1, grillej1);
                Joueur j2 = new Joueur(flottej2, grillej2);


                Jeu jeu = new Jeu(j1, j2);
                Tableau_Score_2 f = new Tableau_Score_2(jeu);
            }

        });
    }*/
}
