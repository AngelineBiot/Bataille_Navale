package Controleur;

import Vue.*;
import Modele.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by Michael on 24/04/2016.
 */
public class EcouteurConteneurGrillePhaseTir extends MouseAdapter implements ActionListener {
    private ModelConteneurTir model_tire;
    private ConteneurTir panelTire;
    private Jeu jeu;
    private Fenetre fenetre;



    public EcouteurConteneurGrillePhaseTir(ConteneurTir panTir, ModelConteneurTir model, Jeu j, Fenetre fen) {
        jeu = j;
        fenetre = fen;
        model_tire=model;
        panelTire=panTir;
        model_tire.setDimensionCarre(50);
        model_tire.setCaseOuEstTire(null);
        panelTire.setControlTire(this);
    }


    @Override
    public void mousePressed(MouseEvent e) {
        if (model_tire.getCaseOuEstTire() != null) {
            panelTire.getConteneurGrille().getGridPanel()[model_tire.getCoord1D()].setBorder(BorderFactory.createLineBorder(Color.black));
        }
        model_tire.setCoordX((e.getX()) / model_tire.getDimensionCarre());
        model_tire.setCoordY((e.getY()) / model_tire.getDimensionCarre());
        model_tire.setCoord1D(model_tire.getCoordX() + model_tire.getCoordY() * 10);
        model_tire.setCaseOuEstTire(jeu.getJoueurNonConcerne().getGrille().getGrille()[model_tire.getCoord1D()]);
        panelTire.getConteneurGrille().getGridPanel()[model_tire.getCoord1D()].setBorder(BorderFactory.createLineBorder(Color.BLUE,3));
        panelTire.getConteneurGrille().getGridPanel()[model_tire.getCoord1D()].setOpaque(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("validation")) {
            if (model_tire.getCaseOuEstTire() != null) {
                boolean dejaTirSurCase = model_tire.getCaseOuEstTire().getToucher();
                jeu.getJoueurConcerne().setNbcoups();
                model_tire.getCaseOuEstTire().setToucher();


                if (model_tire.getCaseOuEstTire().getBat() != null && !dejaTirSurCase) {
                    jeu.getJoueurNonConcerne().getFlotte().incrementeNbBateauxTouche();
                    model_tire.getCaseOuEstTire().getBat().updateEstCoule();

                    if(model_tire.getCaseOuEstTire().getBat().getCoule()){
                        jeu.getJoueurNonConcerne().getFlotte().incrementeNbBateauxCoule();
//                        JOptionPane jop = new JOptionPane();
//                        jop.showMessageDialog(null, "Coulé ! ", "Attaque", JOptionPane.INFORMATION_MESSAGE);
                        AnimationCoule ac = new AnimationCoule();
                        ac.setVisible(true);
                    }
                    else{
                        //JOptionPane jop = new JOptionPane();
                        //jop.showMessageDialog(null, "Touché ! ", "Attaque", JOptionPane.INFORMATION_MESSAGE);
                        AnimationTouche at = new AnimationTouche();
                        at.setVisible(true);
                    }

                } else {
//                    JOptionPane jop = new JOptionPane();
//                    jop.showMessageDialog(null, "Raté ! ", "Attaque", JOptionPane.INFORMATION_MESSAGE);
                    AnimationRate at = new AnimationRate();
                    at.setVisible(true);
                }

                if(jeu.getJoueurNonConcerne().getFlotte().flotteCoulee()){

                    JPanel conteneurVictoire = new JPanel();
                    conteneurVictoire.setLayout(new BoxLayout(conteneurVictoire, BoxLayout.Y_AXIS));
                    AnimationFin af = new AnimationFin();
                    af.setVisible(true);
                    JLabel messageVictoire = new JLabel("Bravo "+jeu.getJoueurConcerne().getNomJoueur()+", vous avez gagne !");
                    conteneurVictoire.add(messageVictoire);

                    TableauScores tableauScore = new TableauScores(jeu);
                    conteneurVictoire.add(tableauScore);

                    fenetre.setContentPane(conteneurVictoire);
                    fenetre.validate();
                }
                else{
                    jeu.echangeConcerneJoueur1();

                    ConteneurAttente conteneurAttente = new ConteneurAttente(jeu);
                    new EcouteurConteneurAttente(conteneurAttente, fenetre, jeu);

                    fenetre.setContentPane(conteneurAttente);
                    fenetre.validate();
                }


            }
        }
    }
}