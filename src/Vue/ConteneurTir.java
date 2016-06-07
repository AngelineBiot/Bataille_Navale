package Vue;

import Controleur.EcouteurConteneurGrillePhaseTir;
import Modele.ModelConteneurTir;

import javax.swing.*;
import java.awt.*;
import java.util.ResourceBundle;

/**
 * Created by Michael on 24/04/2016.
 *
 */
public class ConteneurTir extends JTabbedPane {

    private JButton boutonValidation;
    private ConteneurGrille conteneurGrilleJoueur;
    private ConteneurGrille conteneurGrilleAutreJoueur;
    private TableauScores score;
    private ConteneurAchievement conteneurAchievement;
    private ModelConteneurTir modeleTir;


    public ConteneurTir(ConteneurGrille contGrJoueur, ConteneurGrille contGrAutreJoueur, TableauScores sc, ConteneurAchievement cA, ModelConteneurTir m) {
        modeleTir = m;
        conteneurGrilleAutreJoueur = contGrAutreJoueur;
        conteneurGrilleJoueur = contGrJoueur;
        conteneurAchievement=cA;
        score=sc;
        addWidgets();

    }
    private void addWidgets() {

        ResourceBundle texteInternational = ResourceBundle.getBundle("traductions.ConteneurTir");

        JPanel choixTir = new JPanel();
        choixTir.setLayout(new BoxLayout(choixTir, BoxLayout.Y_AXIS));

        JLabel infoTire = new JLabel(texteInternational.getString("choixTir"));
        infoTire.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        choixTir.add(infoTire);

        boutonValidation = new JButton(texteInternational.getString("validerTir"));
        boutonValidation.setActionCommand("validation");
        boutonValidation.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        choixTir.add(Box.createVerticalStrut(20));
        choixTir.add(boutonValidation);

        JPanel conteneurGrilleTir = new JPanel();
        JPanel conteneurChoixTir = new JPanel();


        conteneurChoixTir.add(choixTir);


        conteneurGrilleTir.add(conteneurGrilleAutreJoueur);
        conteneurGrilleTir.add(Box.createHorizontalStrut(25));
        conteneurGrilleTir.add(conteneurChoixTir);


        addTab(texteInternational.getString("ongletTir"), conteneurGrilleTir);


        conteneurGrilleJoueur.afficherBateauxDeSaFlotte();
        addTab(texteInternational.getString("ongletMaGrille"), conteneurGrilleJoueur);
        addTab(texteInternational.getString("ongletScores"), score);
        addTab(texteInternational.getString("ongletSucces"),conteneurAchievement);

    }
    public void setControlTire(EcouteurConteneurGrillePhaseTir ecouteur){
        boutonValidation.addActionListener(ecouteur);

        conteneurGrilleAutreJoueur.setControl(ecouteur);

    }

    public ConteneurGrille getConteneurGrille(){
        return conteneurGrilleAutreJoueur;
    }

    public void desactiveValidation(){
        boutonValidation.setEnabled(false);
    }

    public void effaceContourCaseSelectionnee(){
        conteneurGrilleAutreJoueur.getGridPanel()[modeleTir.getCoord1D()].setBorder(BorderFactory.createLineBorder(Color.black));
    }

    public void creeContourCaseSelectionnee(){
        getConteneurGrille().getGridPanel()[modeleTir.getCoord1D()].setBorder(BorderFactory.createLineBorder(Color.BLUE,3));
        getConteneurGrille().getGridPanel()[modeleTir.getCoord1D()].setOpaque(true);
    }

}
