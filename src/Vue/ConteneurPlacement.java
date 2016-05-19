package Vue;

import javax.swing.*;
import java.awt.*;
import java.util.Locale;
import java.util.ResourceBundle;

import Controleur.EcouteurConteneurGrillePhasePlacement;
import Modele.*;

/**
 * Created by Florian on 18/04/2016.
 */
public class ConteneurPlacement extends JPanel {

    private Flotte flotte;

    private JLabel infoPlacement;
    private JLabel imageBateau;
    private JButton boutonValidation;
    private JRadioButton placerBateauVertical;
    private JRadioButton placerBateauHorizontal;
    private ConteneurGrille conteneurGrille;

    private ModelConteneurPlacement modelConteneurPlacement;



    public ConteneurPlacement(ModelConteneurPlacement modele, Flotte f, ConteneurGrille contGr){

        conteneurGrille = contGr;
        modelConteneurPlacement = modele;
        flotte = f;

        setLayout(new FlowLayout());
        addWidgets();

    }

    private void addWidgets(){


        ResourceBundle texteInternational = ResourceBundle.getBundle("traductions.ConteneurPlacement");



        add(conteneurGrille);


        JPanel control = new JPanel();
        BoxLayout layout = new BoxLayout(control, BoxLayout.Y_AXIS);
        control.setLayout(layout);




        infoPlacement = new JLabel(texteInternational.getString("place")+flotte.getFlotte()[flotte.getNbBateauxPlaces()].getTypeBateauInternational());
        infoPlacement.setAlignmentX(JComponent.CENTER_ALIGNMENT);

        imageBateau = new JLabel();
        imageBateau.setIcon(ImageBateau.getImageBateau(flotte.getFlotte()[flotte.getNbBateauxPlaces()].getTypeBateau(),false));
        imageBateau.setAlignmentX(JComponent.CENTER_ALIGNMENT);


        placerBateauVertical = new JRadioButton(texteInternational.getString("vertical"));
        placerBateauHorizontal = new JRadioButton(texteInternational.getString("horizontal"));



        ButtonGroup groupeBoutonChoixDirection = new ButtonGroup();
        groupeBoutonChoixDirection.add(placerBateauVertical);
        groupeBoutonChoixDirection.add(placerBateauHorizontal);
        placerBateauVertical.setSelected(true);

        placerBateauVertical.setActionCommand("changementDirection");
        placerBateauHorizontal.setActionCommand("changementDirection");



        JPanel conteneurChoixDirection = new JPanel();
        conteneurChoixDirection.setLayout(new GridLayout(2,1));
        conteneurChoixDirection.add(placerBateauVertical);
        conteneurChoixDirection.add(placerBateauHorizontal);
        conteneurChoixDirection.setAlignmentX(JPanel.CENTER_ALIGNMENT);



        boutonValidation = new JButton(texteInternational.getString("valider"));
        boutonValidation.setActionCommand("validation");
        boutonValidation.setAlignmentX(JComponent.CENTER_ALIGNMENT);


        placerBateauVertical.setFocusable(false);        //Ils ne doivent pas prendre le focus a la grille lors du placement
        placerBateauHorizontal.setFocusable(false);      //Sinon, impossible de choisir la direction du bateau au clavier
        boutonValidation.setFocusable(false);


        control.add(infoPlacement);
        control.add(Box.createRigidArea(new Dimension(0,30)));
        control.add(imageBateau);
        control.add(Box.createRigidArea(new Dimension(0,40)));
        control.add(conteneurChoixDirection);
        control.add(Box.createRigidArea(new Dimension(0,40)));
        control.add(boutonValidation);



        add(control);

    }


    public JLabel getInfoPlacement(){
        return infoPlacement;
    }

    public JLabel getImageBateau(){
        return imageBateau;
    }

    public void setControl(EcouteurConteneurGrillePhasePlacement ecouteur){
        placerBateauVertical.addActionListener(ecouteur);
        placerBateauHorizontal.addActionListener(ecouteur);
        boutonValidation.addActionListener(ecouteur);
        addKeyListener(ecouteur);
    }

    public void echangeCheckboxPositionnement(){
        if(placerBateauVertical.isSelected()){
            placerBateauHorizontal.setSelected(true);
        }
        else{
            placerBateauVertical.setSelected(true);
        }
    }

    public ConteneurGrille getConteneurGrille(){
        return  conteneurGrille;
    }
}

