package Vue;


import Controleur.EcouteurMenu;
import Modele.Jeu;


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

    protected Tableau_Score tableauScore;

    public void setJeu(Jeu jeu) {
        this.jeu = jeu;
    }

    protected Jeu jeu;

    public Fenetre(String nomFenetre,Jeu jeu){
        setTitle(nomFenetre);
        this.jeu = jeu;
        creerScores();

        Image im = Toolkit.getDefaultToolkit().getImage("ressource/images/TorpilleurHorizontal.png");
        setIconImage(im);

        setSize(900,700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        creerMenu();
        new EcouteurMenu(this,jeu);

        /*Vue.ConteneurInscription conteneur =new Vue.ConteneurInscription(this);
        setContentPane(conteneur);*/

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

    public void creerScores() {
        //Les données du tableau
        Object[][] data = {
                {jeu.getJoueur1(), jeu.getJoueur1().getNbCoups(), jeu.getJoueur1().getFlotte().getNbBateauxCoule(),
                                    jeu.getJoueur1().getFlotte().getNbTouches(), "efficacite"},
                {jeu.getJoueur2(), jeu.getJoueur2().getNbCoups(), jeu.getJoueur2().getFlotte().getNbBateauxCoule(),
                                    jeu.getJoueur2().getFlotte().getNbTouches(), "efficacite"},
        };

        //Les titres des colonnes
        String  title[] = {"Nom", "Nombre de tirs", "Bateaux ennemis coulés", "Bateaux ennemis touchés", "Efficacité"};
        JTable tableau = new JTable(data, title);
        //Nous ajoutons notre tableau à notre contentPane dans un scroll
        //Sinon les titres des colonnes ne s'afficheront pas !
        //this.getContentPane().add(new JScrollPane(tableau));

        JPanel pano = new JPanel();
        pano.add(tableau);
        setContentPane(pano);
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
}
