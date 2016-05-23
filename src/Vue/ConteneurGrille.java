package Vue;

import Modele.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;

/**
 * Created by Michael on  17/04/2016.
 *
 */
public class ConteneurGrille extends JPanel {

    private JPanel afficheButton;
    private JLabel[] gridPanel;
    private Joueur joueur;

    public ConteneurGrille(Joueur j) {
        joueur = j;
        initAttribut();
        addWidget();
    }

    private void initAttribut() {
        afficheButton = new JPanel(new GridLayout(10,10));
        gridPanel = new JLabel[100];
        for (int i=0;i<gridPanel.length;i++){
            gridPanel[i]=new JLabel();
            int dimensionCarre = 50;
            gridPanel[i].setPreferredSize(new Dimension(dimensionCarre, dimensionCarre));
            gridPanel[i].setBorder(BorderFactory.createLineBorder(Color.black));
        }
    }

    private void addWidget() {
        for (JLabel aGridPanel : gridPanel) {
            afficheButton.add(aGridPanel);

        }
        this.add(afficheButton);
    }

    public JLabel[] getGridPanel(){
        return gridPanel;
    }

    private void setBackgroundCase(int coord, Color couleur){
        gridPanel[coord].setBackground(couleur);
    }

    public void setControl(MouseAdapter ecouteur){
        afficheButton.addMouseListener(ecouteur);

    }

    public void repaintBateauxDejaPlaces(){
        for(Bateaux bat : joueur.getFlotte().getFlotte()){
            Case[] tabCaseBateaux = bat.getPosition();
            if(bat.getPosition() != null){
                int i;
                for(i=0 ; i< tabCaseBateaux.length ;i++){
                    int coord = tabCaseBateaux[i].getCoord1D();
                    ImageIcon image = ImageBateau.getImageBateau(bat.getTypeBateau(), bat.getEstOrienteVerticalement(), i, false);
                    gridPanel[coord].setIcon(image);
                }
            }
        }
    }


    public void afficherBateauxDeSaFlotte(){


        for(Bateaux bat : joueur.getFlotte().getFlotte()){
            int coord = bat.getCoordonneesPremiereCase();

            int i=0;
            boolean estVertical = bat.getEstOrienteVerticalement();

            while(i<bat.getTaille()){
                ImageIcon image = ImageBateau.getImageBateau(bat.getTypeBateau(), estVertical, i, false);
                gridPanel[coord].setIcon(image);


                if(estVertical){
                    coord+=10;
                }
                else{
                    coord++;
                }

                i++;
            }
        }
    }

    public void afficherCaseTouche(){

        for(Case currentCase : joueur.getGrille().getGrille()){
            if (currentCase.getToucher()){
                if (currentCase.getBat()!=null){
                    gridPanel[currentCase.getCoord1D()].setBackground(Color.RED);
                }else {
                    setBackgroundCase(currentCase.getCoord1D(),Color.BLUE);
                }
                gridPanel[currentCase.getCoord1D()].setOpaque(true);
                gridPanel[currentCase.getCoord1D()].updateUI();

            }
        }
    }

    public void afficherCaseToucheMaGrille() {
        for(Case currentCase : joueur.getGrille().getGrille()){
            if (currentCase.getToucher()){
                if (currentCase.getBat()!=null){
                    gridPanel[currentCase.getCoord1D()].setIcon(ImageBateau.getImageBateau(currentCase.getBat().getTypeBateau(),
                                                                currentCase.getBat().getEstOrienteVerticalement(),
                                                                currentCase.getBat().getIndiceCaseBateau(currentCase), true));
                }else {
                    setBackgroundCase(currentCase.getCoord1D(),Color.BLUE);
                }
                gridPanel[currentCase.getCoord1D()].setOpaque(true);
                gridPanel[currentCase.getCoord1D()].updateUI();
            }
        }
    }

    public void afficherBateauxCoulesMaGrille() {
        for(Bateaux bat : joueur.getFlotte().getFlotte()){
            if (bat.getCoule()){
                int i = 0;
                for(Case c : bat.getPosition()){
                    gridPanel[c.getCoord1D()].setIcon(ImageBateau.getImageBateau(bat.getTypeBateau(), bat.getEstOrienteVerticalement(), i, true));
                    i++;
                }
            }

        }
    }
}
