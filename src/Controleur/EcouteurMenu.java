package Controleur;

import Modele.*;
import Vue.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by angel on 03/05/2016.
 * Updated by angel ON 03/05/2016.
 */
public class EcouteurMenu implements ActionListener{

    private Fenetre fenetre;
    private Jeu jeu;

    public EcouteurMenu(Fenetre fenetre,Jeu jeu){
        this.fenetre = fenetre;
        this.jeu=jeu;
        fenetre.setControlMenu(this);
    }

    public void actionPerformed(ActionEvent e){



        ResourceBundle texteInternational = ResourceBundle.getBundle("traductions.EcouteurMenu");



        Object source = e.getSource();

        String msgApropos = texteInternational.getString("jeuCree") +
                                "BIOT Angeline\n" +
                                "GUERRE Michael\n" +
                                "LIAO Zuzhi\n" +
                                "PARTY Florian\n" +
                                "PY Antoine\n" +
                                "SURLEAU Etienne\n" +
                                texteInternational.getString("etudiants");

        if (source.equals(fenetre.getItemAide()))
        {
            JOptionPane jopAide = new JOptionPane();
            jopAide.showMessageDialog(null,texteInternational.getString("messageAide"),texteInternational.getString("aide"),JOptionPane.INFORMATION_MESSAGE);

            jopAide.createDialog(null,texteInternational.getString("aide"));
        }
        else if(e.getSource()==fenetre.getItemApropos())
        {
            JOptionPane jopApropos = new JOptionPane();
            jopApropos.showMessageDialog(null,msgApropos,texteInternational.getString("aPropos"),JOptionPane.INFORMATION_MESSAGE);

            jopApropos.createDialog(null,texteInternational.getString("aPropos"));


        }
        else if (source.equals(fenetre.getSauvegarderPartie())){
            try{
                Jeu.Sauvegarde(jeu);
            }
            catch(SauvegardeException e2){
                JOptionPane jopApropos = new JOptionPane();
                jopApropos.showMessageDialog(fenetre,texteInternational.getString("erreurSauvegarde"),
                                            texteInternational.getString("erreur"),JOptionPane.INFORMATION_MESSAGE);

                jopApropos.createDialog(fenetre, texteInternational.getString("erreur"));
            }

        }
        else if (source.equals(fenetre.getReprendrePartie())){
            try{
                this.jeu=Jeu.resumeGame();
                fenetre.setJeu(this.jeu);

                if(jeu.getEstPhasePlacement()){

                    ModelConteneurPlacement modelConteneurPlacement = new ModelConteneurPlacement();
                    modelConteneurPlacement.setDimensionCarre(50);

                    ConteneurGrille conteneurGrille = new ConteneurGrille(jeu.getJoueurConcerne());
                    conteneurGrille.repaintBateauxDejaPlaces();
                    ConteneurPlacement conteneur = new ConteneurPlacement(modelConteneurPlacement, jeu.getJoueurConcerne().getFlotte(), conteneurGrille);
                    new EcouteurConteneurGrillePhasePlacement(modelConteneurPlacement, conteneur, fenetre, jeu);

                    fenetre.setContentPane(conteneur);
                    fenetre.validate();

                }
                else{
                    ConteneurGrille conteneurGrilleAutreJoueur = new ConteneurGrille(jeu.getJoueurNonConcerne());
                    ConteneurGrille conteneurGrilleJoueur = new ConteneurGrille(jeu.getJoueurConcerne());

                    ModelConteneurTir modelConteneurTir = new ModelConteneurTir();
                    TableauScores score = new TableauScores(jeu);

                    ConteneurTir conteneurTir = new ConteneurTir(modelConteneurTir, conteneurGrilleJoueur, conteneurGrilleAutreJoueur,score);


                    new EcouteurConteneurGrillePhaseTir(conteneurTir, modelConteneurTir, jeu, fenetre);

                    fenetre.setContentPane(conteneurTir);
                    fenetre.validate();


                    conteneurGrilleJoueur.afficherBateauxDeSaFlotte();
                    conteneurGrilleJoueur.afficherCaseToucheMaGrille();
                    conteneurGrilleAutreJoueur.afficherCaseTouche();
                    conteneurGrilleAutreJoueur.afficherBateauxCoulesMaGrille();
                    conteneurGrilleAutreJoueur.updateUI();
                }
            }
            catch(SauvegardeException e1){
                JOptionPane jopApropos = new JOptionPane();
                jopApropos.showMessageDialog(fenetre,texteInternational.getString("sauvegardeAbsente"),
                                            texteInternational.getString("erreur"),JOptionPane.INFORMATION_MESSAGE);

                jopApropos.createDialog(fenetre,texteInternational.getString("erreur"));
            }
            // changer pour reset le panel

            /*

            ConteneurInscription conteneurInscription = new ConteneurInscription();
            int hauteurConteneur = (int)(conteneurInscription.getPreferredSize().getHeight());
            int hauteurBox = (700-hauteurConteneur)/2;

            JPanel conteneurGlobal = new JPanel();
            conteneurGlobal.setLayout(new BoxLayout(conteneurGlobal, BoxLayout.Y_AXIS));
            conteneurGlobal.add(Box.createVerticalStrut(hauteurBox));
            conteneurGlobal.add(conteneurInscription);

            fenetre.setContentPane(conteneurGlobal);
            fenetre.validate();

            new EcouteurConteneurInscription(conteneurInscription, jeu, fenetre);*/




        }
        // nouvelle partie
        else if (e.getSource() ==fenetre.getNouvellePartie())
        {

            Grille grillej1 = new Grille();
            Grille grillej2 = new Grille();

            Flotte flottej1 = Flotte.creerFlotte6bateaux();
            Flotte flottej2 = Flotte.creerFlotte6bateaux();

            Joueur j1 = new Joueur(flottej1, grillej1);
            Joueur j2 = new Joueur(flottej2, grillej2);

            jeu = new Jeu(j1, j2);

            ConteneurInscription conteneurInscription = new ConteneurInscription();
            int hauteurConteneur = (int)(conteneurInscription.getPreferredSize().getHeight());
            int hauteurBox = (700-hauteurConteneur)/2;

            JPanel conteneurGlobal = new JPanel();
            conteneurGlobal.setLayout(new BoxLayout(conteneurGlobal, BoxLayout.Y_AXIS));
            conteneurGlobal.add(Box.createVerticalStrut(hauteurBox));
            conteneurGlobal.add(conteneurInscription);

            fenetre.setContentPane(conteneurGlobal);
            fenetre.validate();

            new EcouteurConteneurInscription(conteneurInscription, jeu, fenetre);
        }
        //quitter partie
        else if (e.getSource()==fenetre.getQuitterPartie())
        {
            int resultQuitter = JOptionPane.showConfirmDialog(fenetre,texteInternational.getString("demandeQuitter"),
                                            texteInternational.getString("quitter"),JOptionPane.YES_NO_OPTION);
            if  (resultQuitter==JOptionPane.YES_OPTION){
                int resultSauvegarde = JOptionPane.showConfirmDialog(fenetre,texteInternational.getString("demandeSauvegarde"),
                                                                    texteInternational.getString("quitter"),JOptionPane.YES_NO_OPTION);
                if  (resultSauvegarde==JOptionPane.YES_OPTION){
                    try {
                        Jeu.Sauvegarde(fenetre.getJeu());

                    }
                    catch(SauvegardeException e1){
                        JOptionPane jopApropos = new JOptionPane();
                        jopApropos.showMessageDialog(fenetre,texteInternational.getString("erreurSauvegarde"),
                                                texteInternational.getString("erreur"),JOptionPane.INFORMATION_MESSAGE);

                        jopApropos.createDialog(fenetre,texteInternational.getString("erreur"));
                    }

                }


                fenetre.dispose();
            }
        }
    }
}
