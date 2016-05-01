package Vue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by Michael on 17/04/2016.
 */
public class ConteneurGrille extends JPanel {
    private JPanel afficheButton;
    private JLabel[] gridPanel;

    private static int dimensionCarre=50;

    public ConteneurGrille(){
        initAttribut();
        addWidget();
    }



    public void initAttribut(){
        afficheButton = new JPanel(new GridLayout(10,10));
        gridPanel = new JLabel[100];
        for (int i=0;i<gridPanel.length;i++){
            gridPanel[i]=new JLabel();
            gridPanel[i].setPreferredSize(new Dimension(dimensionCarre, dimensionCarre));
            gridPanel[i].setBorder(BorderFactory.createLineBorder(Color.black));
        }
    }

    private void addWidget() {
        for (int i=0;i<gridPanel.length;i++){
            afficheButton.add(gridPanel[i]);

        }
        this.add(afficheButton);
    }

    public JLabel[] getGridPanel(){
        return gridPanel;
    }

    public void setBackgroundCase(int coord,Color couleur){
        gridPanel[coord].setBackground(couleur);
        gridPanel[coord].setOpaque(false);
    }

    public void setControl(MouseAdapter ecouteur){
        afficheButton.addMouseListener(ecouteur);

    }

}
