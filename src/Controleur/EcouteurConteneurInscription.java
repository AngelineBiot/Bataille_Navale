package Controleur;

import Modele.*;
import Vue.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by fparty2 on 29/04/16.
 */
public class EcouteurConteneurInscription implements ActionListener{

    private ConteneurInscription conteneurInscription;



    public EcouteurConteneurInscription(ConteneurInscription conteneur) {
        conteneurInscription = conteneur;

        conteneurInscription.setEcouteurConteneurInscription(this);
    }

    public void actionPerformed(ActionEvent e){
        String pseudoJoueur1 = conteneurInscription.getlabelJoueur1().getText();
        String pseudoJoueur2 = conteneurInscription.getlabelJoueur2().getText();

        if(!(pseudoJoueur1.equals("")) && !(pseudoJoueur2.equals(""))){
            Joueur.getJoueur(1).setNomJoueur(pseudoJoueur1);
            Joueur.getJoueur(2).setNomJoueur(pseudoJoueur2);

            ModelAttente modele = new ModelAttente(true, true, pseudoJoueur1);
            ConteneurAttente conteneur = new ConteneurAttente(modele);

            EcouteurConteneurAttente ecouteur = new EcouteurConteneurAttente(conteneur, modele);

            Fenetre.getFenetre().setContentPane(conteneur);
            Fenetre.getFenetre().validate();

        }


    }


}
