import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Created by Florian on 18/04/2016.
 * Updated today
 */
public class EcouteurConteneurGrillePhasePlacement extends MouseAdapter implements ActionListener {

    private int coordX;
    private int coordY;
    private int coord1D;

    private int dimensionCarre;
    private ConteneurGrille grille;
    private ConteneurPlacement panelPlacement;

    private boolean directionVerticale;
    private int indiceBateauEnCours;
    private Case caseOuEstBateauEnCoursPlacement;

    public EcouteurConteneurGrillePhasePlacement(int dimension){
        dimensionCarre = dimension;
        directionVerticale = true;
        indiceBateauEnCours = 0;
        caseOuEstBateauEnCoursPlacement = null;
    }

    public void setGrille(ConteneurGrille gr){
        grille = gr;
    }
    public void setPanelPlacement(ConteneurPlacement placement){
        panelPlacement = placement;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        System.out.println((e.getX())/dimensionCarre);
        System.out.println((e.getY())/dimensionCarre);
        System.out.println(directionVerticale);
        coordX=(e.getX())/dimensionCarre;
        coordY=(e.getY())/dimensionCarre;
        coord1D=coordX+coordY*10;
        int longueur = panelPlacement.getFlotte().getFlotte()[indiceBateauEnCours].getTaille();


        boolean xBon = coordX>=0 && ((directionVerticale && coordX<10) || (coordX<=10-longueur));
        boolean yBon = coordY>=0 && ((!directionVerticale && coordY<10) || (coordY<=10-longueur));
        boolean bon = xBon && yBon;

        if(bon && verifAucuneCaseDejaPrise(coordX,coordY,directionVerticale,longueur)){
            int i;
            if(caseOuEstBateauEnCoursPlacement != null){
                int x= caseOuEstBateauEnCoursPlacement.getCoordoneX();
                int y = caseOuEstBateauEnCoursPlacement.getCoordoneY();

                effaceCase(longueur, x, y);

            }


            caseOuEstBateauEnCoursPlacement =  panelPlacement.getGrille().getGrille()[coord1D];
            String typeBateau = panelPlacement.getFlotte().getFlotte()[indiceBateauEnCours].getTypeBateau();

            int x = caseOuEstBateauEnCoursPlacement.getCoordoneX();
            int y =caseOuEstBateauEnCoursPlacement.getCoordoneY();

            if(directionVerticale){
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
            boolean bon = (texteCaseACocher.equals("Placer le bateau horizontalement") && directionVerticale) ||
                    (texteCaseACocher.equals("Placer le bateau verticalement") && !directionVerticale);
            if(bon){
                if(caseOuEstBateauEnCoursPlacement != null){
                    coordX=caseOuEstBateauEnCoursPlacement.getCoordoneX();
                    coordY=caseOuEstBateauEnCoursPlacement.getCoordoneY();
                    int longueur = panelPlacement.getFlotte().getFlotte()[indiceBateauEnCours].getTaille();

                    boolean xBon = coordX>=0 && ((!directionVerticale && coordX<10) || (coordX<=10-longueur));
                    boolean yBon = coordY>=0 && ((directionVerticale && coordY<10) || (coordY<=10-longueur));



                    int x= caseOuEstBateauEnCoursPlacement.getCoordoneX();
                    int y = caseOuEstBateauEnCoursPlacement.getCoordoneY();

                    int xEff = x;
                    int yEff = y;

                    if (!(xBon && yBon)){
                        if(directionVerticale){
                            x=10-longueur;
                        }
                        else{
                            y=10-longueur;
                        }
                    }

                    effaceCase(longueur, xEff, yEff);
                    int i;

                    System.out.println(x+" "+y+" "+!directionVerticale+" "+longueur);
                    if(verifAucuneCaseDejaPrise(x, y, !directionVerticale, longueur)){
                        String typeBateau = panelPlacement.getFlotte().getFlotte()[indiceBateauEnCours].getTypeBateau();
                        if(directionVerticale){
                            for(i=0 ; i<longueur ; i++){
                                grille.getGridPanel()[y*10 +x+i].setIcon(ImageBateau.getImageBateau(typeBateau,false,i));
                            }
                        }
                        else{
                            for(i=0 ; i<longueur ; i++){
                                grille.getGridPanel()[x+ 10*(y+i)].setIcon(ImageBateau.getImageBateau(typeBateau,true,i));
                            }

                        }

                        caseOuEstBateauEnCoursPlacement =  panelPlacement.getGrille().getGrille()[x+y*10];
                    }
                    else{
                        caseOuEstBateauEnCoursPlacement = null;
                    }


                }
                directionVerticale = !directionVerticale;

            }

        }
        else if(e.getActionCommand().equals("validation")){
            if(caseOuEstBateauEnCoursPlacement != null){
                int longueur = panelPlacement.getFlotte().getFlotte()[indiceBateauEnCours].getTaille();
                Case[] tabCasesDuBateau = new Case[longueur];
                int i;

                int x = caseOuEstBateauEnCoursPlacement.getCoordoneX();
                int y = caseOuEstBateauEnCoursPlacement.getCoordoneY();

                Bateaux bateauPlace = panelPlacement.getFlotte().getFlotte()[indiceBateauEnCours];

                if(directionVerticale){
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

                bateauPlace.setPosition(tabCasesDuBateau,directionVerticale);


                indiceBateauEnCours += 1;

                if(indiceBateauEnCours<panelPlacement.getFlotte().getFlotte().length){
                    String typeNouvBateau = panelPlacement.getFlotte().getFlotte()[indiceBateauEnCours].getTypeBateau();
                    panelPlacement.getInfoPlacement().setText("Veuillez placer votre "+typeNouvBateau);
                    panelPlacement.getImageBateau().setIcon(ImageBateau.getImageBateau(typeNouvBateau,false));
                    panelPlacement.getImageBateau().updateUI();
                }
                else{
                    panelPlacement.getInfoPlacement().setText("Vous avez fini de placer vos bateaux");
                    panelPlacement.getImageBateau().setIcon(null);
                    //Mettre ici traitement à faire quand fini de placer les bateaux
                }

                caseOuEstBateauEnCoursPlacement = null;
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

    private void effaceCase(int longueur, int xEff, int yEff){
        int i;

        if(directionVerticale){
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
