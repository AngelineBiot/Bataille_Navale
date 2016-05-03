import Controleur.EcouteurConteneurGrillePhaseTire;
import Modele.Grille;
import Modele.ModelConteneurTire;
import Vue.ConteneurGrille;
import Vue.ConteneurTire;
import Vue.ImageBateau;

import javax.swing.*;

/**
 * Created by michael on 28/04/2016.
 */
public class Main_Tir {
    public static void main(String[] args) {
        //juste pour tester
        ImageBateau.initTableauImagesBateau();
        /*ModelConteneurTire model = new ModelConteneurTire();
        ConteneurTire placeurDeBateaux = new ConteneurTire();
        ConteneurGrille J1 = new ConteneurGrille();
        Grille grille_J1 = new Grille();
        EcouteurConteneurGrillePhaseTire ecouteur = new EcouteurConteneurGrillePhaseTire(50,J1,placeurDeBateaux,model);
        model.setGrille(grille_J1);
        JPanel conteneur = new JPanel();
        conteneur.add(J1);
        conteneur.add(placeurDeBateaux);*/

        ConteneurTire placeurDeBateaux = new ConteneurTire();

        JFrame test = new JFrame();
        test.setContentPane(placeurDeBateaux);
        test.pack();
        test.setVisible(true);}
}
