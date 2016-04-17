import javax.swing.*;

public class Main_Grille_test {
    public static void main(String[] args) {
        //juste pour tester
        Grille J1 = new Grille();
        JFrame test = new JFrame();
        test.setContentPane(J1.affiche());
        test.pack();
        test.setVisible(true);

    }

}
