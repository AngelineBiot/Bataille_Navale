package Vue;
import Modele.Jeu;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.util.ResourceBundle;



/**
 * Created by ANTOINE on 02/05/2016.
 *
 */
public class TableauScores extends JPanel {

    public TableauScores(Jeu jeu){
        creerWidget(jeu);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    }

    private void creerWidget(Jeu jeu) {

        ResourceBundle texteInternational = ResourceBundle.getBundle("traductions.TableauScores");

        this.setSize(300, 120);
        float efficaciteJoueur1;
        float efficaciteJoueur2;

        if (jeu.getJoueur1().getNbCoups() == 0) {
            efficaciteJoueur1 = 0;
        } else {    //On le calcule a partir du nombre de bateaux touches dans la flotte de l'autre joueur
            efficaciteJoueur1 = arrondi(((float)(jeu.getJoueur2().getFlotte().getNbTouches())/jeu.getJoueur1().getNbCoups())*100);
        }

        if (jeu.getJoueur2().getNbCoups() == 0) {
            efficaciteJoueur2 = 0;
        } else {
            efficaciteJoueur2 = arrondi(((float)(jeu.getJoueur1().getFlotte().getNbTouches())/jeu.getJoueur2().getNbCoups())*100);
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
        String debut = "<html><body>";
        String fin = "</html></body>";
        String  title[] = {debut+texteInternational.getString("nom")+fin,
                            debut+texteInternational.getString("nombreTours")+fin,
                            debut+texteInternational.getString("nombreCoules")+fin,
                            debut+texteInternational.getString("nombreTouches")+fin,
                            debut+texteInternational.getString("efficacite")+fin};
        JTable tableau = new JTable(data, title){
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };
        //zoneTexteAPropos.setContentType("text/html");
        tableau.setPreferredSize(new Dimension(600, 200));
        tableau.setRowHeight(100);
        tableau.getTableHeader().setPreferredSize(new Dimension(tableau.getTableHeader().getWidth(), 50));
        tableau.setShowGrid(true);

        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
        cellRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i=0 ; i<tableau.getColumnCount() ; i++) {
            tableau.getColumnModel().getColumn(i).setCellRenderer(cellRenderer);
        }

        //Nous ajoutons notre tableau à notre contentPane dans un scroll
        //Sinon les titres des colonnes ne s'afficheront pas !
        JScrollPane pano = new JScrollPane(tableau);
        pano.setPreferredSize(new Dimension(700, 253));

        JPanel conteneurTableau = new JPanel();
        conteneurTableau.add(Box.createHorizontalStrut(50));
        conteneurTableau.add(pano);
        conteneurTableau.add(Box.createHorizontalStrut(50));

        this.add(Box.createVerticalStrut(50));
        this.add(conteneurTableau);
    }

    private float arrondi(float nombre){
        return ((float)Math.floor(nombre*100))/100;
    }

}
