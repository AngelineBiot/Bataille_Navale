import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Scanner;

import static com.sun.java.accessibility.util.AWTEventMonitor.addMouseListener;
import static java.awt.MouseInfo.getPointerInfo;

/**
 * Created by michael on 31/03/2016.
 */


public class Grille {
    final static Scanner input= new Scanner(System.in);

    int dimensionCarre=50;
    private int coord1D;
    Grille(){}

    public JPanel affiche(){
        JPanel grille = new JPanel();
        JPanel afficheButton = new JPanel(new GridLayout(10,10));
        JPanel[] gridButton = new JPanel[100];
        for (int i=0;i<gridButton.length;i++){
            gridButton[i]=new JPanel();
            gridButton[i].setPreferredSize(new Dimension(dimensionCarre, dimensionCarre));
            gridButton[i].setBorder(BorderFactory.createLineBorder(Color.black));
            afficheButton.add(gridButton[i]);
        }
        afficheButton.addMouseListener(new MouseAdapter() {
            private Color background;
            private double coordX;
            private double coordY;
            @Override
            public void mousePressed(MouseEvent e) {
                //calcul la case de la grille
                coordX=(e.getX()-grille.getX())/dimensionCarre;
                coordY=(e.getY()-grille.getY())/dimensionCarre;
                coord1D=(int)(coordX+coordY*10);
                //permet de tester en changeant la couleur
                System.out.println(coordX+" "+coordY+" "+coord1D);
                background = gridButton[coord1D].getBackground();
                gridButton[coord1D].setBackground(Color.RED);
                gridButton[coord1D].repaint();

            }

            @Override
            public void mouseReleased(MouseEvent e) {
                //remet la case de la couleur originelle
                gridButton[coord1D].setBackground(Color.RED);
            }
        });
        grille.add(afficheButton);
        return grille;
    }
}

