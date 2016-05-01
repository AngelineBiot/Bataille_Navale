import java.awt.*;

/**
 * Created by michael on 28/04/2016.
 */
public class Model_ConteneurTire {

    private int coordX;
    private int coordY;
    private int coord1D;
    private int dimensionCarre;
    private Color background;
    private Case caseOuEstTire;
    private Grille grille;
    private Flotte flotte;




    public Grille getGrille() {
        return grille;
    }

    public void setGrille(Grille grille) {
        this.grille = grille;
    }

    public Flotte getFlotte() {
        return flotte;
    }

    public void setFlotte(Flotte flotte) {
        this.flotte = flotte;
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

    public int getDimensionCarre() {
        return dimensionCarre;
    }

    public void setDimensionCarre(int dimensionCarre) {
        this.dimensionCarre = dimensionCarre;
    }

    public Color getBackground() {
        return background;
    }

    public void setBackground(Color background) {
        this.background = background;
    }


    public Case getCaseOuEstTire() {
        return caseOuEstTire;
    }

    public void setCaseOuEstTire(Case caseOuEstTire) {
        this.caseOuEstTire = caseOuEstTire;
    }

    public int getCoord1D() {
        return coord1D;
    }

    public void setCoord1D(int coord1D) {
        this.coord1D = coord1D;
    }


}