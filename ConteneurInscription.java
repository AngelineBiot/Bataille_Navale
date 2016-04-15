/**
 * Created by belfort on 14/04/2016.
 */
import javax.swing.*;
import java.awt.*;

class ConteneurInscription extends JPanel{
    private JTextField joueur1;
    private JTextField joueur2;
    private JButton valider;

    public ConteneurInscription(Fenetre fen){

       //this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));


        initAttributs(fen);
        addwidgets();

    }

    public void initAttributs(Fenetre fen){
        joueur1 = new JTextField();
        joueur2 = new JTextField();
        valider = new JButton("Valider");

        joueur1.setColumns(15);
        joueur2.setColumns(15);

        valider.addActionListener(new ListenerInscription(fen, this));
    }

    public void addwidgets(){
        JPanel placementGrille = new JPanel();
        GridLayout g = new GridLayout(2,2);
        g.setVgap(10);
        placementGrille.setLayout(g);

        placementGrille.add(new JLabel("Pseudo du joueur 1 : "));
        placementGrille.add(joueur1);
        placementGrille.add(new JLabel("Pseudo du joueur 2 : "));
        placementGrille.add(joueur2);

        JPanel placementGeneral = new JPanel();
        placementGeneral.setLayout(new BoxLayout(placementGeneral, BoxLayout.Y_AXIS));
        placementGeneral.add(placementGrille);
        placementGeneral.add(Box.createVerticalStrut(30));
        valider.setAlignmentX(Component.CENTER_ALIGNMENT);
        placementGeneral.add(valider);


        add(placementGeneral);

    }

    public JTextField getJoueur1(){
        return joueur1;
    }

    public JTextField getJoueur2(){
        return joueur2;
    }

}
