import javax.swing.*;

public class Main_Grille_test {
    public static void main(String[] args) {
        //juste pour tester
        ImageBateau.initTableauImagesBateau();

        EcouteurConteneurGrillePhasePlacement ecouteur = new EcouteurConteneurGrillePhasePlacement(50);

        ConteneurPlacement placeurDeBateaux = new ConteneurPlacement(ecouteur);
        ConteneurGrille J1 = new ConteneurGrille(ecouteur);

        ecouteur.setGrille(J1);
        ecouteur.setPanelPlacement(placeurDeBateaux);

        JPanel conteneur = new JPanel();
        conteneur.add(J1);
        conteneur.add(placeurDeBateaux);

        JFrame test = new JFrame();
        test.setContentPane(conteneur);
        test.pack();
        test.setVisible(true);}

}
