import javax.swing.*;

/**
 * Created by michael on 26/04/2016.
 */
public class Main_tire {
    public static void main(String[] args) {
        Model_ConteneurTire model= new Model_ConteneurTire();
        ConteneurTire phaseTire = new ConteneurTire();
        ConteneurGrille J1 = new ConteneurGrille();
        EcouteurConteneurGrillePhaseTire ecouteur_tire = new EcouteurConteneurGrillePhaseTire(50,J1,phaseTire,model);
        Grille grille_J1 = new Grille();
        model.setGrille(grille_J1);

        JPanel conteneur = new JPanel();
        conteneur.add(J1);
        conteneur.add(phaseTire);

        JFrame test = new JFrame();
        test.setContentPane(conteneur);
        test.pack();
        test.setVisible(true);
    }

}
