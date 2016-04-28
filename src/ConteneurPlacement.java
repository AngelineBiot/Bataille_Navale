import javax.swing.*;
import java.awt.*;

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


    public ConteneurPlacement(){
//prend de Joueur
        grille = new Grille();
        flotte = new Flotte();

        BoxLayout layout = new BoxLayout(this, BoxLayout.Y_AXIS);
        setLayout(layout);
        addWidgets();
    }

    private void addWidgets(){
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

        add(infoPlacement);
        add(Box.createRigidArea(new Dimension(0,30)));
        add(imageBateau);
        add(Box.createRigidArea(new Dimension(0,40)));
        add(conteneurChoixDirection);
        add(Box.createRigidArea(new Dimension(0,40)));
        add(boutonValidation);

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
    public void setControlTire(EcouteurConteneurGrillePhasePlacement ecouteur){
        placerBateauVertical.addActionListener(ecouteur);
        placerBateauHorizontal.addActionListener(ecouteur);
        boutonValidation.addActionListener(ecouteur);

    }
}

