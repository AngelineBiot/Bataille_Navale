package Controleur;

import Modele.*;
import Vue.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;

import static javax.swing.JOptionPane.*;

/**
 * Created by angeline on 03/05/2016.
 *
 */
public class EcouteurMenu implements ActionListener {

    private Fenetre fenetre;
    private Jeu jeu;

    public EcouteurMenu(Fenetre fenetre,Jeu jeu) {
        this.fenetre = fenetre;
        this.jeu=jeu;
        fenetre.setControlMenu(this);
    }

    public void actionPerformed(ActionEvent e) {

        ResourceBundle texteInternational = ResourceBundle.getBundle("traductions.EcouteurMenu");
        Object source = e.getSource();


        if (source.equals(fenetre.getItemAide()))
        {

            new fenPopUp(texteInternational.getString("aide"));
        }
        else if(source==fenetre.getItemApropos())
        {
            ImageIcon icon = new ImageIcon("ressources/images/dep_info.png");
            String msgApropos = texteInternational.getString("jeuCree") +
                    "BIOT Angeline\n" +
                    "GUERRE Michael\n" +
                    "LIAO Zuzhi\n" +
                    "PARTY Florian\n" +
                    "PY Antoine\n" +
                    "SURLEAU Etienne\n" +
                    texteInternational.getString("etudiants");

            showMessageDialog(null,msgApropos,texteInternational.getString("aPropos"), INFORMATION_MESSAGE, icon);



        }
        else if (source.equals(fenetre.getSauvegarderPartie())){
            try{
                Jeu.Sauvegarde(jeu);
            }
            catch(SauvegardeException e2){
                showMessageDialog(fenetre,texteInternational.getString("erreurSauvegarde"),
                                            texteInternational.getString("erreur"), INFORMATION_MESSAGE);

            }

        }
        else if (source.equals(fenetre.getReprendrePartie())) {
            try{
                this.jeu=Jeu.resumeGame();
                fenetre.setJeu(this.jeu);
                recreerContenuFenetre();
            }
            catch(SauvegardeException e1) {
                showMessageDialog(fenetre,texteInternational.getString("sauvegardeAbsente"),
                                            texteInternational.getString("erreur"), INFORMATION_MESSAGE);

            }


        }
        // nouvelle partie
        else if (source == fenetre.getNouvellePartie())
        {

            Grille grillej1 = new Grille();
            Grille grillej2 = new Grille();

            Flotte flottej1 = Flotte.creerFlotte6bateaux();
            Flotte flottej2 = Flotte.creerFlotte6bateaux();

            Joueur j1 = new Joueur(flottej1, grillej1);
            Joueur j2 = new Joueur(flottej2, grillej2);

            jeu = new Jeu(j1, j2, jeu.getLangue());
            ModelConteneurInscription modelConteneurInscription=new ModelConteneurInscription();

            ConteneurInscription conteneurInscription = new ConteneurInscription(modelConteneurInscription);
            int hauteurConteneur = (int)(conteneurInscription.getPreferredSize().getHeight());
            int hauteurBox = (700-hauteurConteneur)/2;

            JPanel conteneurGlobal = new JPanel();
            conteneurGlobal.setLayout(new BoxLayout(conteneurGlobal, BoxLayout.Y_AXIS));
            conteneurGlobal.add(Box.createVerticalStrut(hauteurBox));
            conteneurGlobal.add(conteneurInscription);

            fenetre.setContentPane(conteneurGlobal);
            fenetre.validate();

            new EcouteurConteneurInscription(conteneurInscription, jeu, fenetre,modelConteneurInscription);
        }
        //quitter partie
        else if (source==fenetre.getQuitterPartie())
        {
            int resultQuitter = showConfirmDialog(fenetre,texteInternational.getString("demandeQuitter"),
                                            texteInternational.getString("quitter"), YES_NO_OPTION);
            if  (resultQuitter== YES_OPTION){
                int resultSauvegarde = showConfirmDialog(fenetre,texteInternational.getString("demandeSauvegarde"),
                                                                    texteInternational.getString("quitter"), YES_NO_OPTION);
                if  (resultSauvegarde== YES_OPTION){
                    try {
                        Jeu.Sauvegarde(fenetre.getJeu());
                    }
                    catch(SauvegardeException e1){
                        showMessageDialog(fenetre,texteInternational.getString("erreurSauvegarde"),
                                                texteInternational.getString("erreur"), INFORMATION_MESSAGE);

                    }
                }


                System.exit(0);
            }
        }
        else{
            String langue = fenetre.getLangueSelectionne((JRadioButtonMenuItem)source);
            jeu.setLangue(langue);

            fenetre.dispose();
            fenetre = new Fenetre(jeu);
            recreerContenuFenetre();

            new EcouteurFermeture(fenetre);
            new EcouteurMenu(fenetre,jeu);
            fenetre.setVisible(true);
        }
    }

    public void recreerContenuFenetre(){
        if (jeu.getJoueurConcerne().getIdJoueur()!=-1) {
            if (jeu.getEstPhasePlacement()) {

                ModelConteneurPlacement modelConteneurPlacement = new ModelConteneurPlacement();
                modelConteneurPlacement.setDimensionCarre(50);

                ConteneurGrille conteneurGrille = new ConteneurGrille(jeu.getJoueurConcerne());
                conteneurGrille.repaintBateauxDejaPlaces();
                conteneurGrille.setModelPlacement(modelConteneurPlacement);
                ConteneurPlacement conteneur = new ConteneurPlacement(jeu.getJoueurConcerne().getFlotte(), conteneurGrille, modelConteneurPlacement);
                new EcouteurConteneurGrillePhasePlacement(modelConteneurPlacement, conteneur, fenetre, jeu);

                fenetre.setContentPane(conteneur);
                fenetre.validate();

            } else {
                ConteneurGrille conteneurGrilleAutreJoueur = new ConteneurGrille(jeu.getJoueurNonConcerne());
                ConteneurGrille conteneurGrilleJoueur = new ConteneurGrille(jeu.getJoueurConcerne());

                ModelConteneurTir modelConteneurTir = new ModelConteneurTir();
                TableauScores score = new TableauScores(jeu);
                ConteneurAchievement conteneurAchievement = new ConteneurAchievement(jeu);

                ConteneurTir conteneurTir = new ConteneurTir(conteneurGrilleJoueur, conteneurGrilleAutreJoueur, score, conteneurAchievement);


                new EcouteurConteneurGrillePhaseTir(conteneurTir, modelConteneurTir, jeu, fenetre);

                fenetre.setContentPane(conteneurTir);
                fenetre.validate();


                conteneurGrilleJoueur.afficherBateauxDeSaFlotte();
                conteneurGrilleJoueur.afficherCaseToucheMaGrille();
                conteneurGrilleAutreJoueur.afficherCaseTouche();
                conteneurGrilleAutreJoueur.afficherBateauxCoulesMaGrille();
                conteneurGrilleAutreJoueur.updateUI();
            }
        }else {
            ConteneurAttente conteneurAttente = new ConteneurAttente(jeu);
            new EcouteurConteneurAttente(conteneurAttente, fenetre, jeu);

            fenetre.setContentPane(conteneurAttente);
            fenetre.validate();
        }
    }
}
