import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by Michael on 24/04/2016.
 */
public class EcouteurConteneurGrillePhaseTire extends MouseAdapter implements ActionListener {
    private Model_ConteneurTire model_tire;
    private ConteneurGrille conteneur_Grille;
    private ConteneurTire panelTire;


    public EcouteurConteneurGrillePhaseTire(int dimension,ConteneurGrille g,ConteneurTire panTir,Model_ConteneurTire model) {
        model_tire=model;
        conteneur_Grille=g;
        panelTire=panTir;
        model_tire.setDimensionCarre(dimension);
        model_tire.setCaseOuEstTire(null);
        panelTire.setControlTire(this);
        conteneur_Grille.setControl(this);
    }

    public void setConteneur_Grille(ConteneurGrille gr){
        conteneur_Grille =gr;
    }
    public void setPanelTire(ConteneurTire tire){
        panelTire=tire;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (model_tire.getCaseOuEstTire()!=null){
            conteneur_Grille.setBackgroundCase(model_tire.getCoord1D(),model_tire.getBackground());
        }
        model_tire.setCoordX((e.getX()) / model_tire.getDimensionCarre());
        model_tire.setCoordY((e.getY()) / model_tire.getDimensionCarre());
        model_tire.setCoord1D(model_tire.getCoordX() + model_tire.getCoordY() * 10);
        model_tire.setCaseOuEstTire(model_tire.getGrille().getGrille()[model_tire.getCoord1D()]);
        model_tire.setBackground(conteneur_Grille.gridPanel[model_tire.getCoord1D()].getBackground());
        conteneur_Grille.gridPanel[model_tire.getCoord1D()].setBackground(Color.red);
        conteneur_Grille.gridPanel[model_tire.getCoord1D()].setOpaque(true);

    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("validation")) {
            if (model_tire.getCaseOuEstTire() != null) {
                model_tire.getCaseOuEstTire().setToucher();
                //remplacer par l'affichage du resulat
                if (model_tire.getCaseOuEstTire().getBat() != null) {
                    System.out.println("toucher");
                }else {
                    System.out.println("rater");
                }
            }
        }
    }
}
