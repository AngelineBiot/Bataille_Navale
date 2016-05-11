package Controleur;

import Modele.*;
import Vue.*;

import javax.swing.*;
import java.awt.event.*;

/**
 * Created by Florian on 18/04/2016.
 */
public class EcouteurConteneurGrillePhasePlacement extends MouseAdapter implements ActionListener {
    /*Note : il est possible que certains traitements soient à deplacer dans le modele*/


    private ModelConteneurPlacement model_place;

    private ConteneurPlacement panelPlacement;
    private ConteneurGrille conteneurGrille;
    private Fenetre fenetre;
    private Jeu jeu;
    private Flotte flotte;


    public EcouteurConteneurGrillePhasePlacement(ModelConteneurPlacement model, ConteneurPlacement panPlace, Fenetre fen, Jeu j) {
        model_place=model;
        fenetre = fen;
        jeu = j;

        flotte = j.getJoueurConcerne().getFlotte();

        panelPlacement=panPlace;
        model.setDirectionVerticale(true);
        //model.setIndiceBateauEnCours(jeu.getJoueurConcerne().getFlotte().getNbBateauxPlaces());
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

        int longueur = jeu.getJoueurConcerne().getFlotte().getFlotte()[flotte.getNbBateauxPlaces()].getTaille();
        boolean xBon = model_place.getCoordX()>=0 && ((model_place.isDirectionVerticale() && model_place.getCoordX()<10) || (model_place.getCoordX()<=10-longueur));
        boolean yBon = model_place.getCoordY()>=0 && ((!model_place.isDirectionVerticale() && model_place.getCoordY()<10) || (model_place.getCoordY()<=10-longueur));
        boolean bon = xBon && yBon;

        if(bon && verifAucuneCaseDejaPrise(model_place.getCoordX(),model_place.getCoordY(),model_place.isDirectionVerticale(),longueur)){
            int i;
            if(model_place.getCaseOuEstBateauEnCoursPlacement() != null){
                int x= model_place.getCaseOuEstBateauEnCoursPlacement().getCoordoneX();
                int y = model_place.getCaseOuEstBateauEnCoursPlacement().getCoordoneY();

                effaceCase(longueur, x, y);

            }


            model_place.setCaseOuEstBateauEnCoursPlacement(jeu.getJoueurConcerne().getGrille().getGrille()[model_place.getCoord1D()]);
            String typeBateau = jeu.getJoueurConcerne().getFlotte().getFlotte()[flotte.getNbBateauxPlaces()].getTypeBateau();

            int x = model_place.getCaseOuEstBateauEnCoursPlacement().getCoordoneX();
            int y =model_place.getCaseOuEstBateauEnCoursPlacement().getCoordoneY();

            if(model_place.isDirectionVerticale()){
                for(i=0 ; i<longueur ; i++){
                    conteneurGrille.getGridPanel()[x+ 10*(y+i)].setIcon(ImageBateau.getImageBateau(typeBateau,true,i, false));
                }
            }
            else{
                for(i=0 ; i<longueur ; i++){
                    conteneurGrille.getGridPanel()[y*10 +x+i].setIcon(ImageBateau.getImageBateau(typeBateau,false,i, false));
                }
            }

        }


    }


    public void actionPerformed(ActionEvent e){
        if(e.getActionCommand().equals("changementDirection")){
            String texteCaseACocher = ((JRadioButton) e.getSource()).getText();
            boolean bon = (texteCaseACocher.equals("Placer le bateau horizontalement") && model_place.isDirectionVerticale()) ||
                    (texteCaseACocher.equals("Placer le bateau verticalement") && !model_place.isDirectionVerticale());
            if(bon){
                if(model_place.getCaseOuEstBateauEnCoursPlacement() != null){
                    model_place.setCoordX(model_place.getCaseOuEstBateauEnCoursPlacement().getCoordoneX());
                    model_place.setCoordY(model_place.getCaseOuEstBateauEnCoursPlacement().getCoordoneY());
                    int longueur = jeu.getJoueurConcerne().getFlotte().getFlotte()[flotte.getNbBateauxPlaces()].getTaille();

                    boolean xBon = model_place.getCoordX()>=0 && ((!model_place.isDirectionVerticale() && model_place.getCoordX()<10) || (model_place.getCoordX()<=10-longueur));
                    boolean yBon = model_place.getCoordY()>=0 && ((model_place.isDirectionVerticale() && model_place.getCoordY()<10) || (model_place.getCoordY()<=10-longueur));



                    int x= model_place.getCaseOuEstBateauEnCoursPlacement().getCoordoneX();
                    int y = model_place.getCaseOuEstBateauEnCoursPlacement().getCoordoneY();

                    int xEff = x;
                    int yEff = y;

                    if (!(xBon && yBon)){
                        if(model_place.isDirectionVerticale()){
                            x=10-longueur;
                        }
                        else{
                            y=10-longueur;
                        }
                    }

                    effaceCase(longueur, xEff, yEff);
                    int i;

                    if(verifAucuneCaseDejaPrise(x, y, !model_place.isDirectionVerticale(), longueur)){
                        String typeBateau = jeu.getJoueurConcerne().getFlotte().getFlotte()[flotte.getNbBateauxPlaces()].getTypeBateau();
                        if(model_place.isDirectionVerticale()){
                            for(i=0 ; i<longueur ; i++){
                                conteneurGrille.getGridPanel()[y*10 +x+i].setIcon(ImageBateau.getImageBateau(typeBateau,false,i, false));
                            }
                        }
                        else{
                            for(i=0 ; i<longueur ; i++){
                                conteneurGrille.getGridPanel()[x+ 10*(y+i)].setIcon(ImageBateau.getImageBateau(typeBateau,true,i, false));
                            }

                        }

                        model_place.setCaseOuEstBateauEnCoursPlacement(jeu.getJoueurConcerne().getGrille().getGrille()[x+y*10]);
                    }
                    else{
                        model_place.setCaseOuEstBateauEnCoursPlacement(null);
                    }


                }
                model_place.setDirectionVerticale(!model_place.isDirectionVerticale());

            }

        }
        else if(e.getActionCommand().equals("validation")){
            if(model_place.getCaseOuEstBateauEnCoursPlacement() != null){
                int longueur = jeu.getJoueurConcerne().getFlotte().getFlotte()[flotte.getNbBateauxPlaces()].getTaille();
                Case[] tabCasesDuBateau = new Case[longueur];
                int i;

                int x = model_place.getCaseOuEstBateauEnCoursPlacement().getCoordoneX();
                int y = model_place.getCaseOuEstBateauEnCoursPlacement().getCoordoneY();

                Bateaux bateauPlace = jeu.getJoueurConcerne().getFlotte().getFlotte()[flotte.getNbBateauxPlaces()];

                if(model_place.isDirectionVerticale()){
                    for(i=0 ; i<longueur ; i++){
                        tabCasesDuBateau[i] = jeu.getJoueurConcerne().getGrille().getGrille()[x+(i+y)*10];
                        tabCasesDuBateau[i].setBat(bateauPlace);
                    }
                }
                else{
                    for(i=0 ; i<longueur ; i++){
                        tabCasesDuBateau[i] = jeu.getJoueurConcerne().getGrille().getGrille()[x+i+y*10];
                        tabCasesDuBateau[i].setBat(bateauPlace);
                    }
                }

                bateauPlace.setPosition(tabCasesDuBateau,model_place.isDirectionVerticale());


                flotte.incrementeNbBateauxPlaces();

                if(flotte.getNbBateauxPlaces()<jeu.getJoueurConcerne().getFlotte().getFlotte().length){
                    String typeNouvBateau = jeu.getJoueurConcerne().getFlotte().getFlotte()[flotte.getNbBateauxPlaces()].getTypeBateau();
                    panelPlacement.getInfoPlacement().setText("Veuillez placer votre "+typeNouvBateau);
                    panelPlacement.getImageBateau().setIcon(ImageBateau.getImageBateau(typeNouvBateau,false));
                    panelPlacement.getImageBateau().updateUI();
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

    private boolean verifAucuneCaseDejaPrise(int x, int y, boolean vertical, int longueur){
        boolean resultat = true;
        int i;

        Case[] grilleModele = jeu.getJoueurConcerne().getGrille().getGrille();

        if(vertical){
            int j;
            for(i=0 ; i<longueur ; i++){
                if(grilleModele[x+ 10*(y+i)].getBat() != null){
                    resultat = false;
                }
            }
        }
        else{
            for(i=0 ; i<longueur ; i++){
                if(grilleModele[y*10 +x+i].getBat() != null){
                    resultat = false;
                }

            }
        }


        return resultat;
    }

    public void effaceCase(int longueur, int xEff, int yEff){
        int i;

        if(model_place.isDirectionVerticale()){
            for(i=0 ; i<longueur ; i++){
                conteneurGrille.getGridPanel()[xEff+ 10*(yEff+i)].setIcon(null);
            }
        }
        else{
            for(i=0 ; i<longueur ; i++){
                conteneurGrille.getGridPanel()[yEff*10 +xEff+i].setIcon(null);
            }

        }
    }

}
