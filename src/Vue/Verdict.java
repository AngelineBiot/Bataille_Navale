package Vue;

import Modele.Bateaux;

import javax.swing.*;

public class Verdict extends JFrame {

    private Verdict() {
 
    creerWidget();
 
    setSize(200,200);                                // Fixe la taille par défaut
    setVisible(true);                                // Affiche la fenetre
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // Gestion de la fermeture
  }
 
  private void creerWidget() {
    JPanel pano = new JPanel();   // Création d'un JPanel qui va contenir
    setContentPane(pano);  }


   private void creerDialog(String s) {
       JOptionPane jop = new JOptionPane();
        JOptionPane.showMessageDialog(null, s, "Information", JOptionPane.INFORMATION_MESSAGE);
    }



   public void coule(Bateaux b){
       if(b.getCoule()){
           creerDialog("coule!");
       }
       else if (b.estTouche()) {
           creerDialog("touche");
       }
       else {
           creerDialog("miss!!");
       }
   }

   public static void main(String[] args) {
 
    javax.swing.SwingUtilities.invokeLater(() -> {
      Verdict f = new Verdict();

    });
  }

}
//
