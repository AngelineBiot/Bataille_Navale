package Vue;


import Controleur.EcouteurMenu;
import Modele.Jeu;


import javax.swing.*;
import java.awt.*;
import java.util.ResourceBundle;

import static javax.swing.JOptionPane.INFORMATION_MESSAGE;
import static javax.swing.JOptionPane.showMessageDialog;


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

    private JRadioButtonMenuItem itemAnglais;
    private JRadioButtonMenuItem itemFrancais;
    private JRadioButtonMenuItem itemItalien;
    private JRadioButtonMenuItem itemChinois;

    protected Jeu jeu;

    public Fenetre(Jeu jeu) {
        ResourceBundle texteInternational = ResourceBundle.getBundle("traductions.Fenetre");

        setTitle(texteInternational.getString("titre"));
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
        JMenu choixLangue = new JMenu(texteInternational.getString("langue"));

        nouvellePartie      = new JMenuItem(texteInternational.getString("nouvellePartie"));
        sauvegarderPartie   = new JMenuItem(texteInternational.getString("sauvegarderPartie"));
        quitterPartie       = new JMenuItem(texteInternational.getString("quitterPartie"));
        reprendrePartie     = new JMenuItem(texteInternational.getString("reprendrePartie"));
        itemAide            = new JMenuItem(texteInternational.getString("aide"));
        itemApropos         = new JMenuItem(texteInternational.getString("aPropos"));

        itemAnglais         = new JRadioButtonMenuItem(texteInternational.getString("anglais"));
        itemFrancais        = new JRadioButtonMenuItem(texteInternational.getString("francais"));
        itemItalien         = new JRadioButtonMenuItem(texteInternational.getString("italien"));
        itemChinois         = new JRadioButtonMenuItem(texteInternational.getString("chinois"));

        aide.add(itemAide);
        aide.add(itemApropos);

        partie.add(nouvellePartie);
        partie.add(reprendrePartie);
        partie.add(sauvegarderPartie);
        partie.add(quitterPartie);

        choixLangue.add(itemAnglais);
        choixLangue.add(itemFrancais);
        choixLangue.add(itemItalien);
        choixLangue.add(itemChinois);

        ButtonGroup groupeLangues = new ButtonGroup();
        groupeLangues.add(itemAnglais);
        groupeLangues.add(itemFrancais);
        groupeLangues.add(itemItalien);
        groupeLangues.add(itemChinois);

        selectBonneLangue();


        menuBar.add(partie);
        menuBar.add(choixLangue);
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

        itemAnglais.addActionListener(control);
        itemFrancais.addActionListener(control);
        itemItalien.addActionListener(control);
        itemChinois.addActionListener(control);
    }

    private void selectBonneLangue(){
        if(jeu.getLangue().equals("en")){
            itemAnglais.setSelected(true);
        }
        else if(jeu.getLangue().equals("fr")){
            itemFrancais.setSelected(true);
        }
        if(jeu.getLangue().equals("cn")){
            itemChinois.setSelected(true);
        }
        if(jeu.getLangue().equals("it")){
            itemItalien.setSelected(true);
        }
    }

    public String getLangueSelectionne(JRadioButtonMenuItem button){
        if(button == itemAnglais){
            return "en";
        }
        else if(button == itemFrancais){
            return "fr";
        }
        else if(button == itemItalien){
            return "it";
        }
        else{
            return "cn";
        }
    }

    public void affichePopupErreur(String messageErreur){
        ResourceBundle texteInternational = ResourceBundle.getBundle("traductions.Fenetre");

        JOptionPane.showMessageDialog(this, texteInternational.getString(messageErreur),
                texteInternational.getString("erreur"), JOptionPane.ERROR_MESSAGE);
    }

    public void affichePopupErreurBDD(boolean afficheReessayerPlusTrad){
        ResourceBundle texteInternational = ResourceBundle.getBundle("traductions.Database");

        String partieDeuxduTexte;

        if(afficheReessayerPlusTrad){
            partieDeuxduTexte = texteInternational.getString("plus_tard");
        }
        else{
            partieDeuxduTexte = texteInternational.getString("peut_pas_sauvegarder");
        }


        showMessageDialog(null,texteInternational.getString("pas_accessible")+" "+partieDeuxduTexte,
                texteInternational.getString("erreur"), JOptionPane.ERROR_MESSAGE);
    }



    public int affichePopupDemande(String messageErreur){
        ResourceBundle texteInternational = ResourceBundle.getBundle("traductions.Fenetre");

        return JOptionPane.showConfirmDialog(this,texteInternational.getString(messageErreur),
                texteInternational.getString("quitter"),JOptionPane.YES_NO_OPTION);
    }


    public void affichePopupAPropos(){
        ResourceBundle texteInternational = ResourceBundle.getBundle("traductions.Fenetre");

        ImageIcon icon = new ImageIcon("ressources/images/dep_info.png");
        String msgApropos = texteInternational.getString("jeuCree") +
                "BIOT Angeline\n" +
                "GUERRE Michael\n" +
                "LIAO Zuzhi\n" +
                "PARTY Florian\n" +
                "PY Antoine\n" +
                "SURLEAU Etienne\n" +
                texteInternational.getString("etudiants");

        showMessageDialog(this,msgApropos,texteInternational.getString("aPropos"), INFORMATION_MESSAGE, icon);
    }

    public int affichePopupChoixDifficulteIA(){
        ResourceBundle texteInternational = ResourceBundle.getBundle("traductions.Fenetre");

        String[] buttons = {texteInternational.getString("EASY"),texteInternational.getString("MEDIUM"),
                texteInternational.getString("CANCEL")};
        return JOptionPane.showOptionDialog(this, texteInternational.getString("niveau_difficulte"),
                texteInternational.getString("confirmation"),
                JOptionPane.INFORMATION_MESSAGE,0,null,buttons,buttons[1]);
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
