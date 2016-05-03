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

    public ConteneurTire(){
        addWidgets();


        /*BoxLayout layout = new BoxLayout(this, BoxLayout.Y_AXIS);
        setLayout(layout);*/

    }
    private void addWidgets(){
        infoTire = new JLabel("Veuillez choisir ou vous tirez");
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
        ConteneurGrille J1 = new ConteneurGrille();   //Avant d'y prendre dans Joueur
        conteneurGrilleTir.add(J1);

        addTab("À l'attaque !", conteneurGrilleTir);



        J1 = new ConteneurGrille();
        addTab("Voir sa grille", J1);
        addTab("rr",new JPanel());




    }
    public void setControlTire(EcouteurConteneurGrillePhaseTire ecouteur){
        boutonValidation.addActionListener(ecouteur);

    }
}
