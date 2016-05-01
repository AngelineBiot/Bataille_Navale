package Vue;

import Controleur.EcouteurConteneurGrillePhaseTire;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Michael on 24/04/2016.
 */
public class ConteneurTire extends JPanel {

    private JLabel infoTire;
    private JLabel imageBateau;
    private JButton boutonValidation;

    public ConteneurTire(){


        BoxLayout layout = new BoxLayout(this, BoxLayout.Y_AXIS);
        setLayout(layout);
        addWidgets();
    }
    private void addWidgets(){
        infoTire = new JLabel("Veuillez choisir ou vous tirez");
        infoTire.setAlignmentX(JComponent.CENTER_ALIGNMENT);

        boutonValidation = new JButton("Valider le tire");
        boutonValidation.setActionCommand("validation");
        boutonValidation.setAlignmentX(JComponent.CENTER_ALIGNMENT);

        add(infoTire);
        add(Box.createRigidArea(new Dimension(0,40)));
        add(boutonValidation);

    }
    public void setControlTire(EcouteurConteneurGrillePhaseTire ecouteur){
        boutonValidation.addActionListener(ecouteur);

    }
}
