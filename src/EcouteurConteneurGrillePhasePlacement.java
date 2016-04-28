import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Created by Florian on 18/04/2016.
 */
public class EcouteurConteneurGrillePhasePlacement extends MouseAdapter implements ActionListener {

    ModelConteneurPlacement model_place;
    private ConteneurGrille grille;
    private ConteneurPlacement panelPlacement;

    public EcouteurConteneurGrillePhasePlacement(int dimension,ConteneurGrille g,ConteneurPlacement panPlace,ModelConteneurPlacement model) {
        model_place=model;
        grille=g;
        panelPlacement=panPlace;
        model.setDirectionVerticale(true);
        model.setIndiceBateauEnCours(0);
        model.setCaseOuEstBateauEnCoursPlacement(null);
        model_place.setDimensionCarre(dimension);
        panelPlacement.setControlTire(this);
        grille.setControl(this);
    }

    public void setGrille(ConteneurGrille gr){
        grille = gr;
    }
    public void setPanelPlacement(ConteneurPlacement placement){
        panelPlacement = placement;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        model_place.setCoordX((e.getX())/model_place.getDimensionCarre());
        model_place.setCoordY((e.getY())/model_place.getDimensionCarre());
        model_place.setCoord1D(model_place.getCoordX()+model_place.getCoordY()*10);
        int longueur = panelPlacement.getFlotte().getFlotte()[model_place.getIndiceBateauEnCours()].getTaille();


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


            model_place.setCaseOuEstBateauEnCoursPlacement(panelPlacement.getGrille().getGrille()[model_place.getCoord1D()]);
            String typeBateau = panelPlacement.getFlotte().getFlotte()[model_place.getIndiceBateauEnCours()].getTypeBateau();

            int x = model_place.getCaseOuEstBateauEnCoursPlacement().getCoordoneX();
            int y =model_place.getCaseOuEstBateauEnCoursPlacement().getCoordoneY();

            if(model_place.isDirectionVerticale()){
                for(i=0 ; i<longueur ; i++){
                    grille.getGridPanel()[x+ 10*(y+i)].setIcon(ImageBateau.getImageBateau(typeBateau,true,i));
                }
            }
            else{
                for(i=0 ; i<longueur ; i++){
                    grille.getGridPanel()[y*10 +x+i].setIcon(ImageBateau.getImageBateau(typeBateau,false,i));
                }
            }

        }


    }


    public void actionPerformed(ActionEvent e){
        if(e.getActionCommand().equals("changementDirection")){
            String texteCaseACocher = ((JRadioButton) e.getSource()).getText();
            System.out.println(texteCaseACocher);
            boolean bon = (texteCaseACocher.equals("Placer le bateau horizontalement") && model_place.isDirectionVerticale()) ||
                    (texteCaseACocher.equals("Placer le bateau verticalement") && !model_place.isDirectionVerticale());
            if(bon){
                if(model_place.getCaseOuEstBateauEnCoursPlacement() != null){
                    model_place.setCoordX(model_place.getCaseOuEstBateauEnCoursPlacement().getCoordoneX());
                    model_place.setCoordY(model_place.getCaseOuEstBateauEnCoursPlacement().getCoordoneY());
                    int longueur = panelPlacement.getFlotte().getFlotte()[model_place.getIndiceBateauEnCours()].getTaille();

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

                    System.out.println(x+" "+y+" "+!model_place.isDirectionVerticale()+" "+longueur);
                    if(verifAucuneCaseDejaPrise(x, y, !model_place.isDirectionVerticale(), longueur)){
                        String typeBateau = panelPlacement.getFlotte().getFlotte()[model_place.getIndiceBateauEnCours()].getTypeBateau();
                        if(model_place.isDirectionVerticale()){
                            for(i=0 ; i<longueur ; i++){
                                grille.getGridPanel()[y*10 +x+i].setIcon(ImageBateau.getImageBateau(typeBateau,false,i));
                            }
                        }
                        else{
                            for(i=0 ; i<longueur ; i++){
                                grille.getGridPanel()[x+ 10*(y+i)].setIcon(ImageBateau.getImageBateau(typeBateau,true,i));
                            }

                        }

                        model_place.setCaseOuEstBateauEnCoursPlacement(panelPlacement.getGrille().getGrille()[x+y*10]);
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
                int longueur = panelPlacement.getFlotte().getFlotte()[model_place.getIndiceBateauEnCours()].getTaille();
                Case[] tabCasesDuBateau = new Case[longueur];
                int i;

                int x = model_place.getCaseOuEstBateauEnCoursPlacement().getCoordoneX();
                int y = model_place.getCaseOuEstBateauEnCoursPlacement().getCoordoneY();

                Bateaux bateauPlace = panelPlacement.getFlotte().getFlotte()[model_place.getIndiceBateauEnCours()];

                if(model_place.isDirectionVerticale()){
                    for(i=0 ; i<longueur ; i++){
                        tabCasesDuBateau[i] = panelPlacement.getGrille().getGrille()[x+(i+y)*10];
                        tabCasesDuBateau[i].setBat(bateauPlace);
                    }
                }
                else{
                    for(i=0 ; i<longueur ; i++){
                        tabCasesDuBateau[i] = panelPlacement.getGrille().getGrille()[x+i+y*10];
                        tabCasesDuBateau[i].setBat(bateauPlace);
                    }
                }

                bateauPlace.setPosition(tabCasesDuBateau,model_place.isDirectionVerticale());


                model_place.setIndiceBateauEnCours(model_place.getIndiceBateauEnCours()+1);

                if(model_place.getIndiceBateauEnCours()<panelPlacement.getFlotte().getFlotte().length){
                    String typeNouvBateau = panelPlacement.getFlotte().getFlotte()[model_place.getIndiceBateauEnCours()].getTypeBateau();
                    panelPlacement.getInfoPlacement().setText("Veuillez placer votre "+typeNouvBateau);
                    panelPlacement.getImageBateau().setIcon(ImageBateau.getImageBateau(typeNouvBateau,false));
                    panelPlacement.getImageBateau().updateUI();
                }
                else{
                    panelPlacement.getInfoPlacement().setText("Vous avez fini de placer vos bateaux");
                    panelPlacement.getImageBateau().setIcon(null);
                    //Mettre ici traitement à faire quand fini de placer les bateaux
                }

                model_place.setCaseOuEstBateauEnCoursPlacement(null);
            }
        }
    }

    private boolean verifAucuneCaseDejaPrise(int x, int y, boolean vertical, int longueur){
        boolean resultat = true;
        int i;

        Case[] grilleModele = panelPlacement.getGrille().getGrille();

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
                grille.getGridPanel()[xEff+ 10*(yEff+i)].setIcon(null);
            }
        }
        else{
            for(i=0 ; i<longueur ; i++){
                grille.getGridPanel()[yEff*10 +xEff+i].setIcon(null);
            }

        }
    }

}
