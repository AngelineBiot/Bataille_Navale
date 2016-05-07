package Vue;

import Controleur.EcouteurMenu;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Florian on 14/04/2016.
 */
public class Fenetre extends JFrame{

    private JMenuBar 		menuBar;
    private JMenu 			partie;
    private JMenu			aide;

    protected JMenuItem		nouvellePartie;
    protected JMenuItem		sauvegarderPartie;
    protected JMenuItem		quitterPartie;
    protected JMenuItem		reprendrePartie;



    protected JMenuItem		itemAide;
    protected JMenuItem		itemApropos;

    public Fenetre(String nomFenetre){
        setTitle(nomFenetre);


        Image im = Toolkit.getDefaultToolkit().getImage("TorpilleurHorizontal.png");
        setIconImage(im);

        setSize(900,700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        creerMenu();
        new EcouteurMenu(this);


        setVisible(true);
    }

    public void creerMenu(){

        menuBar = new JMenuBar();

        partie  = new JMenu("Partie");
        aide    = new JMenu("?");

        nouvellePartie      = new JMenuItem("Nouvelle Partie");
        sauvegarderPartie   = new JMenuItem("Sauvegarder la partie");
        quitterPartie       = new JMenuItem("Quitter la partie");
        reprendrePartie     = new JMenuItem("Reprendre une partie");
        itemAide            = new JMenuItem("Aide");
        itemApropos         = new JMenuItem("A propos");

        aide.add(itemAide);
        aide.add(itemApropos);

        partie.add(nouvellePartie);
        partie.add(reprendrePartie);
        partie.add(sauvegarderPartie);
        partie.add(quitterPartie);

        menuBar.add(partie);
        menuBar.add(aide);
        setJMenuBar(menuBar);
    }

    public void setControlMenu(EcouteurMenu control){

        itemAide.addActionListener(control);
        itemApropos.addActionListener(control);
    }



    public JMenuItem getItemApropos() {
        return itemApropos;
    }


    public JMenuItem getItemAide() {
        return itemAide;
    }


    public JMenuItem getReprendrePartie() {
        return reprendrePartie;
    }


    public JMenuItem getQuitterPartie() {
        return quitterPartie;
    }


    public JMenuItem getSauvegarderPartie() {
        return sauvegarderPartie;
    }


    public JMenuItem getNouvellePartie() {
        return nouvellePartie;
    }
}
