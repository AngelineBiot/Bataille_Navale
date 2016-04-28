import javax.swing.*;

/**
 * Created by michael on 28/04/2016.
 */
public class Main_Tir {
    public static void main(String[] args) {
        //juste pour tester
        ImageBateau.initTableauImagesBateau();
        Model_ConteneurTire model = new Model_ConteneurTire();
        ConteneurTire placeurDeBateaux = new ConteneurTire();
        ConteneurGrille J1 = new ConteneurGrille();
        Grille grille_J1 = new Grille();
        EcouteurConteneurGrillePhaseTire ecouteur = new EcouteurConteneurGrillePhaseTire(50,J1,placeurDeBateaux,model);
        model.setGrille(grille_J1);
        JPanel conteneur = new JPanel();
        conteneur.add(J1);
        conteneur.add(placeurDeBateaux);

        JFrame test = new JFrame();
        test.setContentPane(conteneur);
        test.pack();
        test.setVisible(true);}
}
