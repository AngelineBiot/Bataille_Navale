package Modele;


/**
 * Created by michael on 28/04/2016.
 *
 */
public class ModelConteneurTir {

    private int coordX;
    private int coordY;
    private int coord1D;
    private int dimensionCarre;
    private Case caseOuEstTire;


    //Utilisation du constructeur vide par defaut


    public void setCoordX(int coordX) {
        this.coordX = coordX;
    }



    public void setCoordY(int coordY) {
        this.coordY = coordY;
    }

    public int getDimensionCarre() {
        return dimensionCarre;
    }

    public void setDimensionCarre(int dimensionCarre) {
        this.dimensionCarre = dimensionCarre;
    }


    public Case getCaseOuEstTire() {
        return caseOuEstTire;
    }

    public void setCaseOuEstTire(Case caseOuEstTire) {
        this.caseOuEstTire = caseOuEstTire;
    }

    public void updateCaseOuEstTire(Jeu jeu) {
        this.caseOuEstTire = jeu.getJoueurNonConcerne().getGrille().getGrille()[coord1D];
    }

    public int getCoord1D() {
        return coord1D;
    }

    public void setCoord1D(int coord1D) {
        this.coord1D = coord1D;
    }

    public void setCoordonnees(int x, int y){
        setCoordX(x / getDimensionCarre());
        setCoordY(y / getDimensionCarre());
        setCoord1D(coordX + coordY * 10);
    }



}
