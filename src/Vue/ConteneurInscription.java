package Vue;

import javax.swing.*;
import java.awt.*;
import java.util.ResourceBundle;

import Controleur.EcouteurConteneurInscription;
import Modele.BDDException;
import Modele.ModelConteneurInscription;

import static javax.swing.JOptionPane.showMessageDialog;

/**
 * Created by Florian on 14/04/2016.
 *
 */
public class ConteneurInscription extends JPanel{
    ModelConteneurInscription model;
    private JTextField labelJoueur1;
    private JTextField labelJoueur2;
    private JButton valider;
    private JComboBox jComboBoxJoueur1;
    private JComboBox jComboBoxJoueur2;


    public ConteneurInscription(ModelConteneurInscription m){
        model=m;
        ResourceBundle texteInternational = ResourceBundle.getBundle("traductions.ConteneurInscription");
        try{
            model.initJoueur();
        }
        catch(BDDException e){
            showMessageDialog(null,"The database is not accessible. Please try later.","Database error", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }

        if (model.getJoueur()!=null){
            model.initListPseudo(model.getJoueur());
        }
        initAttributs(texteInternational.getString("valider"));
        addwidgets(texteInternational.getString("pseudo1"), texteInternational.getString("pseudo2"));

    }

    private void initAttributs(String texteValider){
        labelJoueur1 = new JTextField();
        labelJoueur2 = new JTextField();
        valider = new JButton(texteValider);
        if (model.getListPseudo()!=null){
            jComboBoxJoueur1 =new JComboBox(model.getListPseudo());
        }else {
            jComboBoxJoueur1=new JComboBox();
        }
        jComboBoxJoueur1.setEditable(true);
        String[] listPseudo2=new String[model.getListPseudo().length+1];
        for (int i=0;i<model.getListPseudo().length;i++){
            listPseudo2[i]=model.getListPseudo()[i];
        }
        listPseudo2[listPseudo2.length-1]="GLaDAS";
        if (model.getListPseudo()!=null){
            jComboBoxJoueur2 =new JComboBox(listPseudo2);
            jComboBoxJoueur2.setEditable(true);
        }else {
            jComboBoxJoueur2=new JComboBox();
        }
        jComboBoxJoueur2.setEditable(true);
        labelJoueur1.setColumns(15);
        labelJoueur2.setColumns(15);
    }

    private void addwidgets(String textePseudo1, String textePseudo2){
        JPanel placementGrille = new JPanel();
        GridLayout g = new GridLayout(2,2);
        g.setVgap(10);
        placementGrille.setLayout(g);

        placementGrille.add(new JLabel(textePseudo1));
        placementGrille.add(jComboBoxJoueur1);
        placementGrille.add(new JLabel(textePseudo2));
        placementGrille.add(jComboBoxJoueur2);

        JPanel placementGeneral = new JPanel();
        placementGeneral.setLayout(new BoxLayout(placementGeneral, BoxLayout.Y_AXIS));
        placementGeneral.add(placementGrille);
        placementGeneral.add(Box.createVerticalStrut(30));
        valider.setAlignmentX(Component.CENTER_ALIGNMENT);
        placementGeneral.add(valider);

        add(placementGeneral);
    }

    public JComboBox getjComboBoxJoueur1() {
        return jComboBoxJoueur1;
    }

    public JComboBox getjComboBoxJoueur2() {
        return jComboBoxJoueur2;
    }

    public void setEcouteurConteneurInscription(EcouteurConteneurInscription ecouteur){
        valider.addActionListener(ecouteur);
    }



}
