package Controleur;

import Vue.*;
import Modele.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by Michael on 24/04/2016.
 */
public class EcouteurConteneurGrillePhaseTire extends MouseAdapter implements ActionListener {
    private ModelConteneurTire model_tire;
    //private ConteneurGrille conteneur_Grille;
    private ConteneurTire panelTire;

    private int numeroJoueur;


    public EcouteurConteneurGrillePhaseTire(int numero, ConteneurTire panTir, ModelConteneurTire model) {
        model_tire=model;
        numeroJoueur = numero;
        //conteneur_Grille=Joueur.getJoueur(numeroJoueur).getGrille();
        panelTire=panTir;
        model_tire.setDimensionCarre(50);
        model_tire.setCaseOuEstTire(null);
        panelTire.setControlTire(this);
        //conteneur_Grille.setControl(this);
    }

    /*public void setConteneur_Grille(ConteneurGrille gr){
        conteneur_Grille =gr;
    }
    public void setPanelTire(ConteneurTire tire){
        panelTire=tire;
    }*/

    @Override
    public void mousePressed(MouseEvent e) {
        if (model_tire.getCaseOuEstTire()!=null){
            panelTire.getConteneurGrille().setBackgroundCase(model_tire.getCoord1D(),model_tire.getBackground());
        }
        model_tire.setCoordX((e.getX()) / model_tire.getDimensionCarre());
        model_tire.setCoordY((e.getY()) / model_tire.getDimensionCarre());
        model_tire.setCoord1D(model_tire.getCoordX() + model_tire.getCoordY() * 10);
        model_tire.setCaseOuEstTire(model_tire.getGrille().getGrille()[model_tire.getCoord1D()]);
        model_tire.setBackground(panelTire.getConteneurGrille().getGridPanel()[model_tire.getCoord1D()].getBackground());
        panelTire.getConteneurGrille().getGridPanel()[model_tire.getCoord1D()].setBackground(Color.red);
        panelTire.getConteneurGrille().getGridPanel()[model_tire.getCoord1D()].setOpaque(true);

    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("validation")) {
            if (model_tire.getCaseOuEstTire() != null) {
                model_tire.getCaseOuEstTire().setToucher();
                //remplacer par l'affichage du resulat
                if (model_tire.getCaseOuEstTire().getBat() != null) {
                    System.out.println("toucher");
                }else {
                    System.out.println("rater");
                }
            }
        }
    }
}
