package Vue;

import Modele.*;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by michael on 21/05/2016.
 */
public class ConteneurAchievement extends JPanel{
   private Jeu jeu;
    private ModelAchievement model;
    private JPanel jPanelGlobal;
    private JPanel jPanelniveau;
    private JLabel jLabelniveau;
    private JProgressBar jProgressBarExp;
    private JScrollPane jScrollPaneTableau;
    private JTable jTableAchievement;
    public ConteneurAchievement(Jeu j){
        jeu=j;
        model=new ModelAchievement();
        InitAttributs();
    }

    private void InitAttributs() {
        model.initJoueur(jeu.getJoueur1().getIdJoueur(),jeu.getJoueur2().getIdJoueur());
        model.initAchievement();
        model.initJoueurAchievement();
        jPanelGlobal=new JPanel();
        jPanelGlobal.setLayout(new BoxLayout(jPanelGlobal,BoxLayout.Y_AXIS));
        jProgressBarExp=new JProgressBar(0,1000);
        jPanelniveau=new JPanel(new GridBagLayout());
        long niveau=-1;
        float exp=0;
        for (int i=0; i<model.getJoueur().length;i++){
            if ((int)model.getJoueur()[i][0]==jeu.getJoueurConcerne().getIdJoueur()){
                niveau=(long)model.getJoueur()[i][2];
                exp=(float)model.getJoueur()[i][3];
            }
        }
        jLabelniveau=new JLabel(jeu.getJoueurConcerne().getNomJoueur()+" votre niveau est : "+String.valueOf(niveau)+" avec "+String.valueOf(exp)+" point d'éxperience");
        jProgressBarExp.setValue((int)(exp));
        jProgressBarExp.setStringPainted(true);
        jProgressBarExp.setBorderPainted(true);


        Object[][] data=new Object[model.getAchievement().length][3];
        String[]  title =new String[]{"Succes",jeu.getJoueur1().getNomJoueur(),jeu.getJoueur2().getNomJoueur()};
        for (int i=0; i<data.length;i++){
            data[i][0]=model.getAchievement()[i][1];
            boolean gotAch=false;
            for (int j=0;j<model.getJoueurAchievement().length;j++){
                if (((int)model.getAchievement()[i][0])==(int)(model.getJoueurAchievement()[j][1]) && ((int)model.getJoueur()[0][0])==((int)model.getJoueurAchievement()[j][0] ) || gotAch){
                    data[i][1]="Dévérrouillé";
                    gotAch=true;
                }else {
                    data[i][1]="Vérouillé";
                }
            }
            gotAch=false;
            for (int j=0;j<model.getJoueurAchievement().length;j++){
                if (((int)model.getAchievement()[i][0])==((int)model.getJoueurAchievement()[j][1]) && ((int)model.getJoueur()[1][0])==((int)model.getJoueurAchievement()[j][0] ) || gotAch){
                    data[i][2]="Dévérrouillé";
                    gotAch=true;
                }else {
                    data[i][2]="Vérouillé";
                }
            }
        }

        jTableAchievement=new JTable(data,title){
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };


        jTableAchievement.setPreferredSize(new Dimension(600,300));
        jTableAchievement.setRowHeight(100);

        jTableAchievement.getTableHeader().setPreferredSize(new Dimension(jTableAchievement.getTableHeader().getWidth(), 50));
        jTableAchievement.setShowGrid(true);

        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
        cellRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i=0 ; i<jTableAchievement.getColumnCount() ; i++) {
            jTableAchievement.getColumnModel().getColumn(i).setCellRenderer(cellRenderer);
        }

        jScrollPaneTableau=new JScrollPane(jTableAchievement);
        jScrollPaneTableau.setPreferredSize(new Dimension(600, 353));

        jPanelniveau.add(jLabelniveau);
        JPanel conteneurTableau = new JPanel();
        conteneurTableau.add(Box.createHorizontalStrut(50));
        conteneurTableau.add(jScrollPaneTableau);
        conteneurTableau.add(Box.createHorizontalStrut(50));
        jPanelGlobal.add(jPanelniveau);
        jPanelGlobal.add(jProgressBarExp);
        jPanelGlobal.add(conteneurTableau);
        this.add(Box.createVerticalStrut(50));
        this.add(jPanelGlobal);
    }

}
