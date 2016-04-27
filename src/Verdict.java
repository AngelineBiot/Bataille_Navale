//import javax.swing.*;
//import java.awt.*;
//
//public class Verdict extends JFrame {
//  JOptionPane jop;
//  public Verdict() {
//
//    creerWidget();
//
//    setSize(200,200);                                // Fixe la taille par défaut
//    setVisible(true);                                // Affiche la fenetre
//    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // Gestion de la fermeture
//  }
//
//  public void creerWidget() {
//
//
//
//    JPanel pano = new JPanel();   // Création d'un JPanel qui va contenir
//
//
//    setContentPane(pano);
//             // Ajoute pano à la fenêtre principale
//  }
//   public void creerDialog(String s) {
//       	jop = new JOptionPane();
//        jop.showMessageDialog(null, s, "Information", JOptionPane.INFORMATION_MESSAGE);
//    }
//   public static void main(String[] args) {
//
//    javax.swing.SwingUtilities.invokeLater( new Runnable() {
//
//      public void run() {
//        Verdict f = new Verdict();
//        Case c = new Case();
//        Bateaux b = new Bateaux();
//        if (c.getToucher()) {
//        	f.creerDialog("touche");
//        }
//        else {
//        	f.creerDialog("miss!!");
//        }
//        if (b.estCoule()) {
//        	f.creerDialog("Coule");
//        }
//      }
//
//    });
//  }
//
//}
//
