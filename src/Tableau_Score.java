/**
 * Created by ANTOINE on 25/04/2016.
 */
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
public class Tableau_Score extends JFrame{
    public Tableau_Score(){
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

                //Les données du tableau
                Object[][] data = {
                        {"1","Zuzhi", "2", "1","1","1"},
                        {"2","Antoine", "3", "0","2","1"},
                        {"3","Florian", "4", "5","3","1"},
                        {"4","Michael", "5", "14777","4","1"},
                        {"5","Fabrice", "78", "147","3","1"},
                        {"6","Angeline", "3478", "1247","3","1"},
                        {"7","Etienne", "758", "1474","3","1"},
                };

                //Les titres des colonnes
                String  title[] = {"Rang","Nom", "Nombre de coups", "Nombre de bateaux ennemis coulés","nombre de tirs","efficacité"};
                JTable tableau = new JTable(data, title);
                //Nous ajoutons notre tableau à notre contentPane dans un scroll
                //Sinon les titres des colonnes ne s'afficheront pas !
                this.getContentPane().add(new JScrollPane(tableau));
            }
    public static void main(String[] args) {

        javax.swing.SwingUtilities.invokeLater( new Runnable() {

            public void run() {

                Tableau_Score f = new Tableau_Score();
            }

        });
    }
}
