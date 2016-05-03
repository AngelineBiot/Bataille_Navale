package Vue;

import Controleur.EcouteurConteneurGrillePhaseTire;
import Modele.Grille;
import Modele.ModelConteneurTire;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Michael on 24/04/2016.
 */
public class ConteneurTire extends JTabbedPane {

    private JLabel infoTire;
    private JLabel imageBateau;
    private JButton boutonValidation;
    private ConteneurGrille conteneurGrilleJoueur;
    private ConteneurGrille conteneurGrilleAutreJoueur;

    private ModelConteneurTire modelConteneurTire;

    public ConteneurTire(ModelConteneurTire modele){
        modelConteneurTire = modele;
        addWidgets();


        /*BoxLayout layout = new BoxLayout(this, BoxLayout.Y_AXIS);
        setLayout(layout);*/

    }
    private void addWidgets(){
        infoTire = new JLabel("Veuillez choisir où vous tirez");
        infoTire.setAlignmentX(JComponent.CENTER_ALIGNMENT);

        boutonValidation = new JButton("Valider le tire");
        boutonValidation.setActionCommand("validation");
        boutonValidation.setAlignmentX(JComponent.CENTER_ALIGNMENT);

        JPanel conteneurGrilleTir = new JPanel();
        JPanel conteneurChoixTir = new JPanel();

        conteneurChoixTir.add(infoTire);
        conteneurChoixTir.add(Box.createRigidArea(new Dimension(0,40)));
        conteneurChoixTir.add(boutonValidation);

        conteneurGrilleTir.add(conteneurChoixTir);
        conteneurGrilleAutreJoueur = new ConteneurGrille();   //Avant d'y prendre dans Joueur
        conteneurGrilleTir.add(conteneurGrilleAutreJoueur);

        addTab("À l'attaque !", conteneurGrilleTir);



        conteneurGrilleJoueur = new ConteneurGrille();
        conteneurGrilleJoueur.afficherBateauxDeSaFlotte(modelConteneurTire.getNumeroJoueur());
        addTab("Voir ma grille", conteneurGrilleJoueur);


    }
    public void setControlTire(EcouteurConteneurGrillePhaseTire ecouteur){
        boutonValidation.addActionListener(ecouteur);

        conteneurGrilleAutreJoueur.setControl(ecouteur);

    }

    public ConteneurGrille getConteneurGrille(){
        return conteneurGrilleAutreJoueur;
    }


}
