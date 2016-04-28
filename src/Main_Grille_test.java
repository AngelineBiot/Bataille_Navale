import javax.swing.*;

public class Main_Grille_test {
    public static void main(String[] args) {
        //juste pour tester
        ImageBateau.initTableauImagesBateau();
        ModelConteneurPlacement model = new ModelConteneurPlacement();
        ConteneurPlacement placeurDeBateaux = new ConteneurPlacement();
        ConteneurGrille J1 = new ConteneurGrille();
        Grille grille_J1 = new Grille();
        EcouteurConteneurGrillePhasePlacement ecouteur = new EcouteurConteneurGrillePhasePlacement(50,J1,placeurDeBateaux,model);

        JPanel conteneur = new JPanel();
        conteneur.add(J1);
        conteneur.add(placeurDeBateaux);

        JFrame test = new JFrame();
        test.setContentPane(conteneur);
        test.pack();
        test.setVisible(true);}

}
