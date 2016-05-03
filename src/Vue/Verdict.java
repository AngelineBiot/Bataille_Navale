package Vue;

import Modele.Bateaux;
import Modele.Case;

import javax.swing.*;

public class Verdict extends JFrame {
  JOptionPane jop;
  public Verdict() {
 
    creerWidget();
 
    setSize(200,200);                                // Fixe la taille par défaut
    setVisible(true);                                // Affiche la fenetre
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // Gestion de la fermeture
  }
 
  public void creerWidget() {
    JPanel pano = new JPanel();   // Création d'un JPanel qui va contenir
    setContentPane(pano);  }


   public void creerDialog(String s) {
       	jop = new JOptionPane();
        jop.showMessageDialog(null, s, "Information", JOptionPane.INFORMATION_MESSAGE);
    }



   public void coule(Bateaux b){
       if(b.estCoule()){
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
 
    javax.swing.SwingUtilities.invokeLater( new Runnable() {
 
      public void run() {
        Verdict f = new Verdict();
      
      }
 
    });
  }

}
//
