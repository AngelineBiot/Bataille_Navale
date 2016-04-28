import javax.swing.*;
import java.awt.*;

/**
 * Created by Florian on 18/04/2016.
 * Updated today
 */
public class ConteneurPlacement extends JPanel {

    private Grille grille;
    private Flotte flotte;

    private JLabel infoPlacement;
    private JLabel imageBateau;
    private JButton boutonValidation;




    public ConteneurPlacement(EcouteurConteneurGrillePhasePlacement ecouteur){

        grille = new Grille();
        flotte = Flotte.creerFlotte6bateaux();

        BoxLayout layout = new BoxLayout(this, BoxLayout.Y_AXIS);
        setLayout(layout);
        addWidgets(ecouteur);
    }

    private void addWidgets(EcouteurConteneurGrillePhasePlacement ecouteur){
        infoPlacement = new JLabel("Veuillez placer votre "+flotte.getFlotte()[0].getTypeBateau());
        infoPlacement.setAlignmentX(JComponent.CENTER_ALIGNMENT);

        imageBateau = new JLabel();
        imageBateau.setIcon(ImageBateau.getImageBateau(flotte.getFlotte()[0].getTypeBateau(),false));
        imageBateau.setAlignmentX(JComponent.CENTER_ALIGNMENT);


        JRadioButton placerBateauVertical = new JRadioButton("Placer le bateau verticalement");
        JRadioButton placerBateauHorizontal = new JRadioButton("Placer le bateau horizontalement");

        ButtonGroup groupeBoutonChoixDirection = new ButtonGroup();
        groupeBoutonChoixDirection.add(placerBateauVertical);
        groupeBoutonChoixDirection.add(placerBateauHorizontal);
        placerBateauVertical.setSelected(true);

        placerBateauVertical.setActionCommand("changementDirection");
        placerBateauVertical.addActionListener(ecouteur);


        placerBateauHorizontal.setActionCommand("changementDirection");
        placerBateauHorizontal.addActionListener(ecouteur);


        JPanel conteneurChoixDirection = new JPanel();
        conteneurChoixDirection.setLayout(new GridLayout(2,1));
        conteneurChoixDirection.add(placerBateauVertical);
        conteneurChoixDirection.add(placerBateauHorizontal);
        conteneurChoixDirection.setAlignmentX(JPanel.CENTER_ALIGNMENT);



        boutonValidation = new JButton("Valider le placement du bateau");
        boutonValidation.setActionCommand("validation");
        boutonValidation.addActionListener(ecouteur);
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
}
