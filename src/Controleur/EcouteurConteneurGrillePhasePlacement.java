package Controleur;

import Modele.*;
import Vue.*;

import javax.swing.*;
import java.awt.event.*;

import java.util.ResourceBundle;

/**
 * Created by Florian on 18/04/2016.
 *
 */
public class EcouteurConteneurGrillePhasePlacement extends MouseAdapter implements ActionListener, KeyListener {
    /*Note : il est possible que certains traitements soient à deplacer dans le modele*/


    private ModelConteneurPlacement model_place;

    private ConteneurPlacement panelPlacement;
    private ConteneurGrille conteneurGrille;
    private Fenetre fenetre;
    private Jeu jeu;
    private Flotte flotte;
    private Grille grille;


    EcouteurConteneurGrillePhasePlacement(ModelConteneurPlacement model, ConteneurPlacement panPlace, Fenetre fen, Jeu j) {
        model_place=model;
        fenetre = fen;
        jeu = j;

        flotte = j.getJoueurConcerne().getFlotte();
        grille = j.getJoueurConcerne().getGrille();

        panelPlacement=panPlace;
        model.setDirectionVerticale(true);
        model.setCaseOuEstBateauEnCoursPlacement(null);

        panelPlacement.setControl(this);

        conteneurGrille = panelPlacement.getConteneurGrille();
        conteneurGrille.setControl(this);
    }


    @Override
    public void mousePressed(MouseEvent e) {
        model_place.setCoordX((e.getX())/model_place.getDimensionCarre());
        model_place.setCoordY((e.getY())/model_place.getDimensionCarre());
        model_place.setCoord1D(model_place.getCoordX()+model_place.getCoordY()*10);

        int longueur = flotte.getFlotte()[flotte.getNbBateauxPlaces()].getTaille();
        boolean bon = model_place.bateauEstPlacable(longueur);

        if(bon && model_place.verifAucuneCaseDejaPrise(grille,longueur)){
            if(model_place.getCaseOuEstBateauEnCoursPlacement() != null){
                int x= model_place.getCaseOuEstBateauEnCoursPlacement().getCoordoneX();
                int y = model_place.getCaseOuEstBateauEnCoursPlacement().getCoordoneY();

                panelPlacement.effaceCase(longueur, x, y);

            }

            model_place.setCaseOuEstBateauEnCoursPlacement(grille.getGrille()[model_place.getCoord1D()]);

            String typeBateau = flotte.getFlotte()[flotte.getNbBateauxPlaces()].getTypeBateau();
            conteneurGrille.ajoutBateau(typeBateau, longueur);

        }


    }


    public void keyPressed(KeyEvent e){
        int code = e.getKeyCode();

        boolean bon = (code == KeyEvent.VK_RIGHT  && model_place.isDirectionVerticale()) ||
                (code == KeyEvent.VK_DOWN && !model_place.isDirectionVerticale());
        if(bon){
            panelPlacement.echangeCheckboxPositionnement();
            changeDirection();
        }
    }


    public void actionPerformed(ActionEvent e){


        ResourceBundle texteInternational = ResourceBundle.getBundle("traductions.ConteneurPlacement");


        if(e.getActionCommand().equals("changementDirection")){
            String texteCaseACocher = ((JRadioButton) e.getSource()).getText();
            boolean bon = (texteCaseACocher.equals(texteInternational.getString("horizontal")) && model_place.isDirectionVerticale()) ||
                    (texteCaseACocher.equals(texteInternational.getString("vertical")) && !model_place.isDirectionVerticale());
            if(bon){
                changeDirection();

            }

        }
        else if(e.getActionCommand().equals("validation")){
            if(model_place.getCaseOuEstBateauEnCoursPlacement() != null){
                Bateaux bateauPlace = flotte.getFlotte()[flotte.getNbBateauxPlaces()];
                bateauPlace.initPosition(model_place, grille);


                flotte.incrementeNbBateauxPlaces();

                if(flotte.getNbBateauxPlaces()<flotte.getFlotte().length){
                    panelPlacement.changeBateau(texteInternational.getString("place"));

                }
                else{

                    if(!jeu.getConcerneJoueur1()){
                        jeu.echangeEstPhasePlacement();
                    }

                    jeu.echangeConcerneJoueur1();

                    ConteneurAttente conteneurAttente = new ConteneurAttente(jeu);
                    new EcouteurConteneurAttente(conteneurAttente, fenetre, jeu);

                    fenetre.setContentPane(conteneurAttente);
                    fenetre.validate();


                }

                model_place.setCaseOuEstBateauEnCoursPlacement(null);
            }
        }
    }



    private void changeDirection() {
        if(model_place.getCaseOuEstBateauEnCoursPlacement() != null) {
            model_place.setCoordX(model_place.getCaseOuEstBateauEnCoursPlacement().getCoordoneX());
            model_place.setCoordY(model_place.getCaseOuEstBateauEnCoursPlacement().getCoordoneY());
            int longueur = flotte.getFlotte()[flotte.getNbBateauxPlaces()].getTaille();

            boolean xBon = model_place.getCoordX()>=0
                    && ((!model_place.isDirectionVerticale()
                    && model_place.getCoordX()<10)
                    || (model_place.getCoordX()<=10-longueur));
            boolean yBon = model_place.getCoordY()>=0
                    && ((model_place.isDirectionVerticale()
                    && model_place.getCoordY()<10)
                    || (model_place.getCoordY()<=10-longueur));


            int x= model_place.getCaseOuEstBateauEnCoursPlacement().getCoordoneX();
            int y = model_place.getCaseOuEstBateauEnCoursPlacement().getCoordoneY();

            int xEff = x;
            int yEff = y;

            if (!(xBon && yBon)) {
                if(model_place.isDirectionVerticale()) {
                    x=10-longueur;
                }
                else {
                    y=10-longueur;
                }
            }

            panelPlacement.effaceCase(longueur, xEff, yEff);
            int i;

            if(model_place.verifAucuneCaseDejaPrise(grille, longueur)) {
                String typeBateau = flotte.getFlotte()[flotte.getNbBateauxPlaces()].getTypeBateau();
                if(model_place.isDirectionVerticale()) {
                    for(i = 0 ; i < longueur ; i++) {
                        conteneurGrille.getGridPanel()[y*10 +x+i].setIcon(ImageBateau.getImageBateau(typeBateau, false, i, false));
                    }
                }
                else {
                    for(i = 0 ; i < longueur ; i++) {
                        conteneurGrille.getGridPanel()[x+ 10*(y+i)].setIcon(ImageBateau.getImageBateau(typeBateau, true, i, false));
                    }

                }

                model_place.setCaseOuEstBateauEnCoursPlacement(grille.getGrille()[x+y*10]);
            }
            else {
                model_place.setCaseOuEstBateauEnCoursPlacement(null);
            }


        }
        model_place.setDirectionVerticale(!model_place.isDirectionVerticale());
    }

    public void keyTyped(KeyEvent e) {
    }

    public void keyReleased(KeyEvent e) {
    }

}
