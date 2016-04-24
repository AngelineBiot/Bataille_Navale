/**
 * Created by ANTOINE on 17/04/2016.
 */
    import javax.swing.*;
    import java.awt.*;

    public class Attente extends JFrame {
        public Attente(String Joueur) {

            creerWidgetJoueur(Joueur);

            //pack();                                          // Fixe la taille par défaut
            setSize(800,800);
            setVisible(true);                                // Affiche la fenetre
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // Gestion de la fermeture
        }

        public void creerWidgetJoueur(String nomJoueur) {
            //Message
            JLabel lab = new JLabel(nomJoueur+" à toi de jouer",SwingConstants.CENTER); // SwingConstants.center sert à centrer le texte
            //Une méthode get pour récupérer le nom du joueur ?
            //JLabel lab = new JLabel(+conteneur.getJoueur1().getText()+ "à toi de jouer",SwingConstants.CENTER);
            // Affichage du bouton prêt
            JButton pret = new JButton("Prêt ?");;
            JPanel pano = new JPanel(new BorderLayout());
            pano.add(lab, BorderLayout.CENTER);
            pano.add(pret, BorderLayout.SOUTH);
            setContentPane(pano);
        }

        public static void main(String[] args) {

            javax.swing.SwingUtilities.invokeLater( new Runnable() {

                public void run() {
                    Attente f = new Attente("Nom_fictif");
                }

            });
        }
    }

