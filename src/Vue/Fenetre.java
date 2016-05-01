package Vue;

import javax.swing.*;

/**
 * Created by Florian on 14/04/2016.
 */
public class Fenetre extends JFrame{

    private static Fenetre fenetre;

    private Fenetre(String nomFenetre){
        setTitle(nomFenetre);
        //setSize(400,150);
        setSize(800,800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        /*Vue.ConteneurInscription conteneur =new Vue.ConteneurInscription(this);
        setContentPane(conteneur);*/

        setVisible(true);

    }


    public static void initFenetre(){
        fenetre = new Fenetre("Inscription");
    }

    public static Fenetre getFenetre(){
        return fenetre;
    }
}
