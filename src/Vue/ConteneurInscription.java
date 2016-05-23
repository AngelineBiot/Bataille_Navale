package Vue;

import javax.swing.*;
import java.awt.*;
import java.util.ResourceBundle;

import Controleur.EcouteurConteneurInscription;

/**
 * Created by Florian on 14/04/2016.
 *
 */
public class ConteneurInscription extends JPanel{
    private JTextField labelJoueur1;
    private JTextField labelJoueur2;
    private JButton valider;
    


    public ConteneurInscription(){

        ResourceBundle texteInternational = ResourceBundle.getBundle("traductions.ConteneurInscription");


        initAttributs(texteInternational.getString("valider"));
        addwidgets(texteInternational.getString("pseudo1"), texteInternational.getString("pseudo2"));

    }

    private void initAttributs(String texteValider){
        labelJoueur1 = new JTextField();
        labelJoueur2 = new JTextField();
        valider = new JButton(texteValider);

        labelJoueur1.setColumns(15);
        labelJoueur2.setColumns(15);
    }

    private void addwidgets(String textePseudo1, String textePseudo2){
        JPanel placementGrille = new JPanel();
        GridLayout g = new GridLayout(2,2);
        g.setVgap(10);
        placementGrille.setLayout(g);

        placementGrille.add(new JLabel(textePseudo1));
        placementGrille.add(labelJoueur1);
        placementGrille.add(new JLabel(textePseudo2));
        placementGrille.add(labelJoueur2);

        JPanel placementGeneral = new JPanel();
        placementGeneral.setLayout(new BoxLayout(placementGeneral, BoxLayout.Y_AXIS));
        placementGeneral.add(placementGrille);
        placementGeneral.add(Box.createVerticalStrut(30));
        valider.setAlignmentX(Component.CENTER_ALIGNMENT);
        placementGeneral.add(valider);

        add(placementGeneral);
    }

    public JTextField getlabelJoueur1(){
        return labelJoueur1;
    }

    public JTextField getlabelJoueur2(){
        return labelJoueur2;
    }

    public void setEcouteurConteneurInscription(EcouteurConteneurInscription ecouteur){
        valider.addActionListener(ecouteur);
    }



}
