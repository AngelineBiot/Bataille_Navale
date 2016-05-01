import Controleur.EcouteurConteneurInscription;
import Modele.*;
import Vue.*;

public class Main_Grille_test {
    public static void main(String[] args) {

        ImageBateau.initTableauImagesBateau();
        Joueur.initJoueurs();
        Fenetre.initFenetre();

        ConteneurInscription conteneurInscription = new ConteneurInscription();
        Fenetre.getFenetre().setContentPane(conteneurInscription);
        Fenetre.getFenetre().validate();

        EcouteurConteneurInscription ecouteur = new EcouteurConteneurInscription(conteneurInscription);

        }

}
