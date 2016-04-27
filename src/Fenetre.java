/**
 * Created by belfort on 14/04/2016.
 */
import javax.swing.*;

class Fenetre extends JFrame{

    public Fenetre(String nomFenetre){
        setTitle(nomFenetre);
        //setSize(400,150);
        setSize(800,800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ConteneurInscription conteneur =new ConteneurInscription(this);
        setContentPane(conteneur);

        setVisible(true);

    }

    public void finInscriptionJoueurs(ConteneurInscription conteneur){
        String pseudoJoueur1 = conteneur.getJoueur1().getText();
        String pseudoJoueur2 = conteneur.getJoueur2().getText();
        //Inserer ici instructions pour creer des objets Joueur à partir de ça

        JPanel grille = new JPanel();
        JLabel boucheTrou = new JLabel("Mettre la grille ici");
        grille.add(boucheTrou);

        setContentPane(grille);
        setSize(800,800);
        validate();     //Mise à jour de la fenetre
    }


    public static void main(String[] args){
        Fenetre fen = new Fenetre("Bataille Navale");
    }
}
