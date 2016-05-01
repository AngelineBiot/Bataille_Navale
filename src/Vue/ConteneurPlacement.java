package Vue;

import javax.swing.*;
import java.awt.*;

import Controleur.EcouteurConteneurGrillePhasePlacement;
import Modele.*;

/**
 * Created by Florian on 18/04/2016.
 */
public class ConteneurPlacement extends JPanel {

    private Grille grille;
    private Flotte flotte;

    private JLabel infoPlacement;
    private JLabel imageBateau;
    private JButton boutonValidation;
    JRadioButton placerBateauVertical;
    JRadioButton placerBateauHorizontal;
    ConteneurGrille conteneurGrille;

    private ModelConteneurPlacement modelConteneurPlacement;


    public ConteneurPlacement(ModelConteneurPlacement modele){

        modelConteneurPlacement = modele;
        grille = Joueur.getJoueur(modele.getNumeroJoueur()).getGrille();
        flotte = Joueur.getJoueur(modele.getNumeroJoueur()).getFlotte();

        setLayout(new FlowLayout());
        addWidgets();

    }

    private void addWidgets(){
        conteneurGrille = new ConteneurGrille();
        add(conteneurGrille);


        JPanel control = new JPanel();
        BoxLayout layout = new BoxLayout(control, BoxLayout.Y_AXIS);
        control.setLayout(layout);




        infoPlacement = new JLabel("Veuillez placer votre "+flotte.getFlotte()[0].getTypeBateau());
        infoPlacement.setAlignmentX(JComponent.CENTER_ALIGNMENT);

        imageBateau = new JLabel();
        imageBateau.setIcon(ImageBateau.getImageBateau(flotte.getFlotte()[0].getTypeBateau(),false));
        imageBateau.setAlignmentX(JComponent.CENTER_ALIGNMENT);


        placerBateauVertical = new JRadioButton("Placer le bateau verticalement");
        placerBateauHorizontal = new JRadioButton("Placer le bateau horizontalement");;

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



        boutonValidation = new JButton("Valider le placement du bateau");
        boutonValidation.setActionCommand("validation");

        boutonValidation.setAlignmentX(JComponent.CENTER_ALIGNMENT);

        control.add(infoPlacement);
        control.add(Box.createRigidArea(new Dimension(0,30)));
        control.add(imageBateau);
        control.add(Box.createRigidArea(new Dimension(0,40)));
        control.add(conteneurChoixDirection);
        control.add(Box.createRigidArea(new Dimension(0,40)));
        control.add(boutonValidation);



        add(control);

    }

    public Flotte getFlotte(){
        return flotte;
    }

    public Grille getGrille(){
        return grille;
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

    }

    public ConteneurGrille getConteneurGrille(){
        return  conteneurGrille;
    }
}

