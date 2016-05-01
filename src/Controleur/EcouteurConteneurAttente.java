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
            ModelConteneurPlacement modele;
            if(modelAttente.getConcerneJoueur1()){
                modele = new ModelConteneurPlacement(1);
            }
            else{
                modele = new ModelConteneurPlacement(2);
            }

            modele.setDimensionCarre(50);

            ConteneurPlacement conteneur = new ConteneurPlacement(modele);
            EcouteurConteneurGrillePhasePlacement ecouteur = new EcouteurConteneurGrillePhasePlacement(modele, conteneur);

            Fenetre.getFenetre().setContentPane(conteneur);
            Fenetre.getFenetre().validate();

        }

    }
}
