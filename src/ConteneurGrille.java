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

    public ConteneurGrille(EcouteurConteneurGrillePhasePlacement ecouteur){
        initAttribut();
        addWidget(ecouteur);
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

    private void addWidget(EcouteurConteneurGrillePhasePlacement ecouteur) {
        for (int i=0;i<gridPanel.length;i++){
            afficheButton.add(gridPanel[i]);

        }
        afficheButton.addMouseListener(ecouteur);
        this.add(afficheButton);
    }

    public JLabel[] getGridPanel(){
        return gridPanel;
    }
}
