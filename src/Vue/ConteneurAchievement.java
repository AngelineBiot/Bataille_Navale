package Vue;

import Modele.*;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.util.ResourceBundle;

/**
 * Created by michael on 21/05/2016.
 */
public class ConteneurAchievement extends JPanel{
   private Jeu jeu;
    private JPanel jPanelGlobal;
    private JPanel jPanelniveau;
    private JLabel jLabelniveau;
    private JProgressBar jProgressBarExp;
    private JScrollPane jScrollPaneTableau;
    private JTable jTableAchievement;
    private BaseDeDonnees baseDeDonnees;

    public ConteneurAchievement(Jeu j, BaseDeDonnees base){

        baseDeDonnees = base;
        jeu=j;
        InitAttributs();
    }

    private void InitAttributs() {
        ResourceBundle texteInternational = ResourceBundle.getBundle("traductions.ConteneurAchievement");


        jPanelGlobal=new JPanel();
        jPanelGlobal.setLayout(new BoxLayout(jPanelGlobal,BoxLayout.Y_AXIS));
        jProgressBarExp=new JProgressBar(0,1000);
        jPanelniveau=new JPanel(new GridBagLayout());
        long niveau=-1;
        float exp=0;
        for (int i=0; i<baseDeDonnees.getJoueur().length;i++){
            if ((int)baseDeDonnees.getJoueur()[i][0]==jeu.getJoueurConcerne().getIdJoueur()){
                niveau=(long)baseDeDonnees.getJoueur()[i][2];
                exp=(float)baseDeDonnees.getJoueur()[i][3];
            }
        }

        String texteNiveau = texteInternational.getString("niveau");
        String texteAvec = texteInternational.getString("avec");
        String texteExperience = texteInternational.getString("experience");

        jLabelniveau=new JLabel(jeu.getJoueurConcerne().getNomJoueur()+", "+texteNiveau+" "+String.valueOf(niveau)+" "+texteAvec+" "+String.valueOf(exp)+" "+texteExperience);
        jProgressBarExp.setValue((int)(exp));
        jProgressBarExp.setStringPainted(true);
        jProgressBarExp.setBorderPainted(true);


        Object[][] data=new Object[baseDeDonnees.getAchievement().length][3];
        String[]  title =new String[]{"Succes",jeu.getJoueur1().getNomJoueur(),jeu.getJoueur2().getNomJoueur()};
        for (int i=0; i<data.length;i++){
            String nomSucces = supprEspace((String)baseDeDonnees.getAchievement()[i][1]);
            data[i][0]=texteInternational.getString(nomSucces);

            boolean gotAch=false;
            for (int j=0;j<baseDeDonnees.getJoueurAchievement().length;j++){
                if (((int)baseDeDonnees.getAchievement()[i][0])==(int)(baseDeDonnees.getJoueurAchievement()[j][1]) &&
                        ((int)baseDeDonnees.getJoueur()[0][0])==((int)baseDeDonnees.getJoueurAchievement()[j][0] ) || gotAch){
                    data[i][1]=texteInternational.getString("valide");
                    gotAch=true;
                }else {
                    data[i][1]=texteInternational.getString("non_valide");
                }
            }
            gotAch=false;
            for (int j=0;j<baseDeDonnees.getJoueurAchievement().length;j++){
                if (((int)baseDeDonnees.getAchievement()[i][0])==((int)baseDeDonnees.getJoueurAchievement()[j][1]) &&
                        ((int)baseDeDonnees.getJoueur()[1][0])==((int)baseDeDonnees.getJoueurAchievement()[j][0] ) || gotAch){
                    data[i][2]=texteInternational.getString("valide");
                    gotAch=true;
                }else {
                    data[i][2]=texteInternational.getString("non_valide");
                }
            }
        }

        jTableAchievement=new JTable(data,title){
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };


        jTableAchievement.setPreferredSize(new Dimension(600,200));
        jTableAchievement.setRowHeight(100);

        jTableAchievement.getTableHeader().setPreferredSize(new Dimension(jTableAchievement.getTableHeader().getWidth(), 50));
        jTableAchievement.setShowGrid(true);

        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
        cellRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i=0 ; i<jTableAchievement.getColumnCount() ; i++) {
            jTableAchievement.getColumnModel().getColumn(i).setCellRenderer(cellRenderer);
        }

        jScrollPaneTableau=new JScrollPane(jTableAchievement);
        jScrollPaneTableau.setPreferredSize(new Dimension(600, 253));

        jPanelniveau.add(jLabelniveau);
        JPanel conteneurTableau = new JPanel();
        conteneurTableau.add(Box.createHorizontalStrut(50));
        conteneurTableau.add(jScrollPaneTableau);
        conteneurTableau.add(Box.createHorizontalStrut(50));
        jPanelGlobal.add(jPanelniveau);
        jPanelGlobal.add(Box.createVerticalStrut(10));
        jPanelGlobal.add(jProgressBarExp);
        jPanelGlobal.add(Box.createVerticalStrut(70));
        jPanelGlobal.add(conteneurTableau);
        this.add(Box.createVerticalStrut(50));
        this.add(jPanelGlobal);
    }

    private String supprEspace(String succes){
        String[] partiesSucces = succes.split(" ");
        String retour = "";

        for(String partie:partiesSucces){
            retour += partie;
        }

        return retour;
    }

}
