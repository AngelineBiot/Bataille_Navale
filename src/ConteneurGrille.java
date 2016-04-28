import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by Michael on 17/04/2016.
 */
public class ConteneurGrille extends JPanel {

    JPanel afficheButton;
    JPanel[] gridPanel;
    int dimensionCarre=50;
    private int coord1D;

    public ConteneurGrille(){
        initAttribut();
        addWidget();
    }



    public void initAttribut(){
        afficheButton = new JPanel(new GridLayout(10,10));
        gridPanel = new JPanel[100];
        for (int i=0;i<gridPanel.length;i++){
            gridPanel[i]=new JPanel();
            gridPanel[i].setPreferredSize(new Dimension(dimensionCarre, dimensionCarre));
            gridPanel[i].setBorder(BorderFactory.createLineBorder(Color.black));
        }
    }

    private void addWidget() {
        for (int i=0;i<gridPanel.length;i++){
            afficheButton.add(gridPanel[i]);
        }
        afficheButton.addMouseListener(new MouseAdapter() {
            private Color background;
            private double coordX;
            private double coordY;
            @Override
            public void mousePressed(MouseEvent e) {
                //calcul la case de la grille
                coordX=(e.getX())/dimensionCarre;
                coordY=(e.getY())/dimensionCarre;
                coord1D=(int)(coordX+coordY*10);
                //permet de tester en changeant la couleur
                background = gridPanel[coord1D].getBackground();
                gridPanel[coord1D].setBackground(Color.RED);
                gridPanel[coord1D].repaint();

            }

            @Override
            public void mouseReleased(MouseEvent e) {
                //remet la case de la couleur originelle
                gridPanel[coord1D].setBackground(background);
            }
        });
        this.add(afficheButton);
    }
}
