package Vue;

import Controleur.EcouteurConteneurEnLigne;
import Modele.ModelEnLigne;

import javax.swing.*;
import java.awt.*;

/**
 * Created by michael on 04/06/2016.
 */
public class ConteneurEnLigne extends JPanel{
    private ModelEnLigne model;
    private JLabel jLabelVotreAdresse;
    private JButton jButtonHebergement;
    private JLabel jLabelEntrerAdresse;
    private JTextField jTextFieldRejoindre;
    private JButton jButtonRejoindre;

    public ConteneurEnLigne(ModelEnLigne model) {
        this.model=model;
        initAttributs();
        addwidgets();
    }

    private void initAttributs() {
        model.initAdresseOrdi();
        jLabelVotreAdresse=new JLabel("Votre adresse est : "+model.getAdresseOrdi());
        jLabelVotreAdresse.setAlignmentX(Component.CENTER_ALIGNMENT);
        jButtonHebergement=new JButton("Héberger");
        jButtonHebergement.setAlignmentX(Component.CENTER_ALIGNMENT);
        jLabelEntrerAdresse =new JLabel("Veuillez entrée l'adresse de l'hébergeur");
        jLabelEntrerAdresse.setAlignmentX(Component.CENTER_ALIGNMENT);
        jTextFieldRejoindre=new JTextField(10);
        jTextFieldRejoindre.setAlignmentX(Component.CENTER_ALIGNMENT);
        jButtonRejoindre=new JButton("Rejoindre");
        jButtonRejoindre.setAlignmentX(Component.CENTER_ALIGNMENT);
    }

    private void addwidgets() {
        JPanel placementGeneral=new JPanel();
        JPanel jPanelLabelTextfield=new JPanel();
        placementGeneral.setLayout(new BoxLayout(placementGeneral, BoxLayout.Y_AXIS));
        placementGeneral.add(jLabelVotreAdresse);
        placementGeneral.add(Box.createVerticalStrut(10));
        placementGeneral.add(jButtonHebergement);
        placementGeneral.add(Box.createVerticalStrut(20));
        jPanelLabelTextfield.add(jLabelEntrerAdresse);
        jPanelLabelTextfield.add(jTextFieldRejoindre);
        placementGeneral.add(jPanelLabelTextfield);
        placementGeneral.add(Box.createVerticalStrut(10));
        placementGeneral.add(jButtonRejoindre);
        this.add(placementGeneral);
    }


    public JButton getjButtonHebergement() {
        return jButtonHebergement;
    }

    public JTextField getjTextFieldRejoindre() {
        return jTextFieldRejoindre;
    }

    public JButton getjButtonRejoindre() {
        return jButtonRejoindre;
    }

    public void setEcouteurConteneurEnLigne(EcouteurConteneurEnLigne ecouteur){
        jButtonHebergement.addActionListener(ecouteur);
        jButtonRejoindre.addActionListener(ecouteur);
    }
}
