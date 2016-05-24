package Vue;


import Controleur.EcouteurMenu;
import Modele.Jeu;


import javax.swing.*;
import java.awt.*;
import java.util.ResourceBundle;


/**
 * Created by Florian on 14/04/2016.
 /* Updates by Angie on 17/05/2016.
 */
public class Fenetre extends JFrame{

    private JMenuItem		nouvellePartie;
    private JMenuItem		sauvegarderPartie;
    private JMenuItem		quitterPartie;
    private JMenuItem		reprendrePartie;

    private JMenuItem		itemAide;
    private JMenuItem		itemApropos;

    protected Jeu jeu;

    public Fenetre(String nomFenetre,Jeu jeu) {

        setTitle(nomFenetre);
        this.jeu = jeu;

        Image im = Toolkit.getDefaultToolkit().getImage("ressources/images/TorpilleurHorizontal.png");
        setIconImage(im);

        setSize(900,700);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        setLocationRelativeTo(null);
        setResizable(false);
        creerMenu();


        setVisible(true);
    }

    private void creerMenu() {

        ResourceBundle texteInternational = ResourceBundle.getBundle("traductions.Fenetre");

        JMenuBar menuBar = new JMenuBar();

        JMenu partie = new JMenu(texteInternational.getString("partie"));
        JMenu aide = new JMenu("?");

        nouvellePartie      = new JMenuItem(texteInternational.getString("nouvellePartie"));
        sauvegarderPartie   = new JMenuItem(texteInternational.getString("sauvegarderPartie"));
        quitterPartie       = new JMenuItem(texteInternational.getString("quitterPartie"));
        reprendrePartie     = new JMenuItem(texteInternational.getString("reprendrePartie"));
        itemAide            = new JMenuItem(texteInternational.getString("aide"));
        itemApropos         = new JMenuItem(texteInternational.getString("aPropos"));

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


    public void setControlMenu(EcouteurMenu control) {

        itemAide.addActionListener(control);
        itemApropos.addActionListener(control);
        nouvellePartie.addActionListener(control);
        sauvegarderPartie.addActionListener(control);
        reprendrePartie.addActionListener(control);
        quitterPartie.addActionListener(control);
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

    public void setJeu(Jeu jeu) {
        this.jeu = jeu;
    }

    public Jeu getJeu() {
        return jeu;
    }
}
