package Controleur;

import Modele.*;
import Vue.ConteneurEnLigne;
import Vue.ConteneurInscription;
import Vue.Fenetre;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by michael on 04/06/2016.
 */
public class EcouteurConteneurEnLigne implements ActionListener{
    private ConteneurEnLigne conteneurEnLigne;
    private ModelEnLigne model;
    private Fenetre fenetre;
    private Jeu jeu;

    public EcouteurConteneurEnLigne(ConteneurEnLigne conteneurEnLigne, ModelEnLigne model, Jeu jeu, Fenetre fenetre) {
        this.conteneurEnLigne = conteneurEnLigne;
        this.model=model;
        this.jeu = jeu;
        this.fenetre = fenetre;
        conteneurEnLigne.setEcouteurConteneurEnLigne(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        jeu.setOnline(true);
            if (e.getSource().equals(conteneurEnLigne.getjButtonHebergement())){
                jeu.setTimeServer(new TimeServer(model.getAdresseOrdi(),226,jeu));
            }else {
                jeu.setClientConnexion(new ClientConnexion(conteneurEnLigne.getjTextFieldRejoindre().getText(),226,jeu));
            }
        ModelConteneurInscription modelConteneurInscription=new ModelConteneurInscription();

        BaseDeDonnees baseDeDonnees = null;

        try {
            baseDeDonnees = new BaseDeDonnees(jeu);
        }
        catch(BDDException e1){
            fenetre.affichePopupErreurBDD(true);
            System.exit(2);
        }

        try{
            baseDeDonnees.initListeJoueurs();
        }
        catch(BDDException e1){
            fenetre.affichePopupErreurBDD(true);
            System.exit(1);
        }

        if (baseDeDonnees.getJoueur()!=null){
            modelConteneurInscription.initListPseudo(baseDeDonnees.getJoueur());
        }

        ConteneurInscription conteneurInscription = new ConteneurInscription(modelConteneurInscription,jeu.isOnline(),baseDeDonnees);
        new EcouteurConteneurInscription(conteneurInscription, jeu,baseDeDonnees, fenetre,modelConteneurInscription);

        int hauteurConteneur = (int)(conteneurInscription.getPreferredSize().getHeight());
        int hauteurBox = (700-hauteurConteneur)/2;

        JPanel conteneurGlobal = new JPanel();
        conteneurGlobal.setLayout(new BoxLayout(conteneurGlobal, BoxLayout.Y_AXIS));
        conteneurGlobal.add(Box.createVerticalStrut(hauteurBox));
        conteneurGlobal.add(conteneurInscription);

        fenetre.setContentPane(conteneurGlobal);
        fenetre.validate();
    }
}
