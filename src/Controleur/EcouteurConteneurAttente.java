package Controleur;

import Modele.*;
import Vue.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by fparty2 on 29/04/16.
 */
public class EcouteurConteneurAttente implements ActionListener {
    private ConteneurAttente conteneurAttente;
    private ModelAttente modelAttente;

    public EcouteurConteneurAttente(ConteneurAttente conteneur, ModelAttente model){

        conteneurAttente =conteneur;
        modelAttente=model;

        conteneurAttente.setEcouteurConteneurAttente(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(modelAttente.getConcernePhasePlacement()){
            ModelConteneurPlacement modelConteneurPlacement;
            if(modelAttente.getConcerneJoueur1()){
                modelConteneurPlacement = new ModelConteneurPlacement(1);
            }
            else{
                modelConteneurPlacement = new ModelConteneurPlacement(2);
            }

            modelConteneurPlacement.setDimensionCarre(50);

            ConteneurPlacement conteneur = new ConteneurPlacement(modelConteneurPlacement);
            EcouteurConteneurGrillePhasePlacement ecouteur = new EcouteurConteneurGrillePhasePlacement(modelConteneurPlacement, conteneur);

            Fenetre.getFenetre().setContentPane(conteneur);
            Fenetre.getFenetre().validate();

        }
        else{
            ModelConteneurTire modelConteneurTire;
            int numeroJoueur= 1 ;
            if(!modelAttente.getConcerneJoueur1()){
                numeroJoueur = 2;

            }
            modelConteneurTire = new ModelConteneurTire(numeroJoueur);

            ConteneurTire conteneurTire = new ConteneurTire(modelConteneurTire);

            EcouteurConteneurGrillePhaseTire ecouteur= new EcouteurConteneurGrillePhaseTire(numeroJoueur, conteneurTire, modelConteneurTire);

            Fenetre.getFenetre().setContentPane(conteneurTire);
            Fenetre.getFenetre().validate();

        }

    }
}
