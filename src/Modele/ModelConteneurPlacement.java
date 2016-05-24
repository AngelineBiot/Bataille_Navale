package Modele;

/**
 * Created by michael on 28/04/2016.
 *
 */
public class ModelConteneurPlacement {
    private int coordX;
    private int coordY;
    private int coord1D;

    private int dimensionCarre;

    private boolean directionVerticale;
    private Case caseOuEstBateauEnCoursPlacement;

    public ModelConteneurPlacement(){

    }

    public int getCoordX() {
        return coordX;
    }

    public void setCoordX(int coordX) {
        this.coordX = coordX;
    }

    public int getCoordY() {
        return coordY;
    }

    public void setCoordY(int coordY) {
        this.coordY = coordY;
    }

    public int getCoord1D() {
        return coord1D;
    }

    public void setCoord1D(int coord1D) {
        this.coord1D = coord1D;
    }

    public int getDimensionCarre() {
        return dimensionCarre;
    }

    public void setDimensionCarre(int dimensionCarre) {
        this.dimensionCarre = dimensionCarre;
    }

    public boolean isDirectionVerticale() {
        return directionVerticale;
    }

    public void setDirectionVerticale(boolean directionVerticale) {
        this.directionVerticale = directionVerticale;
    }

    public Case getCaseOuEstBateauEnCoursPlacement() {
        return caseOuEstBateauEnCoursPlacement;
    }

    public void setCaseOuEstBateauEnCoursPlacement(Case caseOuEstBateauEnCoursPlacement) {
        this.caseOuEstBateauEnCoursPlacement = caseOuEstBateauEnCoursPlacement;
    }



    public boolean bateauEstPlacable(int longueur){
        boolean xBon = coordX>=0 && ((isDirectionVerticale() && coordX<10) || (coordX<=10-longueur));
        boolean yBon = coordY>=0 && ((!isDirectionVerticale() && coordY<10) || (coordY<=10-longueur));
        return xBon && yBon;
    }

    public boolean verifAucuneCaseDejaPrise(Grille grille, int longueur) {
        boolean resultat = true;
        int i;

        Case[] grilleModele = grille.getGrille();

        if(directionVerticale) {
            for(i=0 ; i<longueur ; i++) {
                if(grilleModele[coordX+ 10*(coordY+i)].getBat() != null) {
                    resultat = false;
                }
            }
        }
        else {
            for(i=0 ; i<longueur ; i++) {
                if(grilleModele[coordY*10 +coordX+i].getBat() != null) {
                    resultat = false;
                }

            }
        }


        return resultat;
    }




}
