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

    /*public int getIndiceBateauEnCours() {
        return indiceBateauEnCours;
    }

    public void setIndiceBateauEnCours(int indiceBateauEnCours) {
        this.indiceBateauEnCours = indiceBateauEnCours;
    }*/



}
