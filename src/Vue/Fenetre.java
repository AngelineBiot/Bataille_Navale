package Vue;


import Controleur.EcouteurMenu;
import Modele.Jeu;


import javax.swing.*;
import java.awt.*;
import java.util.Locale;
import java.util.ResourceBundle;


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

    protected TableauScores tableauScore;



    protected Jeu jeu;

    public Fenetre(String nomFenetre,Jeu jeu){
        setTitle(nomFenetre);
        this.jeu = jeu;

        Image im = Toolkit.getDefaultToolkit().getImage("ressources/images/TorpilleurHorizontal.png");
        setIconImage(im);

        setSize(900,700);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLocationRelativeTo(null);
        setResizable(false);
        creerMenu();


        /*Vue.ConteneurInscription conteneur =new Vue.ConteneurInscription(this);
        setContentPane(conteneur);*/

        setVisible(true);
    }

    public void creerMenu(){
        Locale locale = new Locale("");     // mettre "" pou anglais, code de deux lettres du pays pour les autres

        //!!!!!!!
        //Ca : que pour les tests : ENLEVER CA A LA FIN
        //!!!!!!!

        ResourceBundle texteInternational = ResourceBundle.getBundle("traductions.Fenetre", locale);



        menuBar = new JMenuBar();

        partie  = new JMenu(texteInternational.getString("partie"));
        aide    = new JMenu("?");

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



    public void setControlMenu(EcouteurMenu control){

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
