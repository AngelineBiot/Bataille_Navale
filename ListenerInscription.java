/**
 * Created by belfort on 14/04/2016.
 */
import javax.swing.*;
import java.awt.event.*;

class ListenerInscription implements ActionListener{
    private Fenetre fenetre;
    private ConteneurInscription conteneur;

    public ListenerInscription(Fenetre fen, ConteneurInscription ci){
        fenetre = fen;
        conteneur = ci;

    }


    public void actionPerformed(ActionEvent e){
        fenetre.finInscriptionJoueurs(conteneur);
    }

}
