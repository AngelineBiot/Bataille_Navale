package Vue;

import Controleur.EcouteurConteneurGrillePhaseTir;
import Modele.ModelConteneurTir;

import javax.swing.*;

/**
 * Created by Michael on 24/04/2016.
 */
public class ConteneurTir extends JTabbedPane {

    private JLabel infoTire;
    private JLabel imageBateau;
    private JButton boutonValidation;
    private ConteneurGrille conteneurGrilleJoueur;
    private ConteneurGrille conteneurGrilleAutreJoueur;
    private Tableau_Score score;


    private ModelConteneurTir modelConteneurTir;    //Servira peut etre plus tard

    public ConteneurTir(ModelConteneurTir modele, ConteneurGrille contGrJoueur, ConteneurGrille contGrAutreJoueur, Tableau_Score sc){
        conteneurGrilleAutreJoueur = contGrAutreJoueur;
        conteneurGrilleJoueur = contGrJoueur;
        modelConteneurTir = modele;
        score=sc;
        addWidgets();

    }
    private void addWidgets(){
        JPanel choixTir = new JPanel();
        choixTir.setLayout(new BoxLayout(choixTir, BoxLayout.Y_AXIS));

        infoTire = new JLabel("Veuillez choisir où vous tirez");
        infoTire.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        choixTir.add(infoTire);

        boutonValidation = new JButton("Valider le tir");
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


        addTab("À l'attaque !", conteneurGrilleTir);


        conteneurGrilleJoueur.afficherBateauxDeSaFlotte();
        addTab("Voir ma grille", conteneurGrilleJoueur);
        addTab("Voir le tableau des scores", score);


    }
    public void setControlTire(EcouteurConteneurGrillePhaseTir ecouteur){
        boutonValidation.addActionListener(ecouteur);

        conteneurGrilleAutreJoueur.setControl(ecouteur);

    }

    public ConteneurGrille getConteneurGrille(){
        return conteneurGrilleAutreJoueur;
    }


}
