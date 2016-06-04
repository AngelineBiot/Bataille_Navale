package Modele;

import java.sql.*;

/**
 * Created by michael on 21/05/2016.
 */
public class ModelAchievement {
    public void initJoueur(int idJ1,int idJ2){
        String pilote = "com.mysql.jdbc.Driver";
        String url="jdbc:mysql://localhost/Bataille_navale?useSSL=false";
        String user="root";
        String pass ="";
        try{
            Class.forName(pilote);

            Connection connexion = DriverManager.getConnection(url,user,pass);

            Statement instruction = connexion.createStatement();
            String order="";
            if (idJ2>idJ1){
                order="ORDER BY idJoueur DESC";
            }

            ResultSet resultat = instruction.executeQuery("SELECT * FROM joueur WHERE idJoueur="+idJ1+" OR idJoueur="+idJ2+order);
            ResultSetMetaData rsmd= resultat.getMetaData();
            int columncount = rsmd.getColumnCount();
            int rowcount = 0;
            if (resultat.last()) {
                rowcount = resultat.getRow();
                resultat.beforeFirst(); // not rs.first() because the rs.next() below will move on, missing the first element
            }

            joueur = new Object[rowcount][columncount];
            while(resultat.next()){
                for (int i=0;i<columncount;i++) {
                    joueur[resultat.getRow() - 1][i] = resultat.getObject(i+1);
                }
            }
        }
        catch (Exception e){

            System.out.println("echec pilote sur joueur: "+e);
        }
    }

    public void initAchievement(){
        String pilote = "com.mysql.jdbc.Driver";
        String url="jdbc:mysql://localhost/Bataille_navale?useSSL=false";
        String user="root";
        String pass ="";
        try{
            Class.forName(pilote);

            Connection connexion = DriverManager.getConnection(url,user,pass);

            Statement instruction = connexion.createStatement();

            ResultSet resultat = instruction.executeQuery("SELECT * FROM achievement");
            ResultSetMetaData rsmd= resultat.getMetaData();
            int columncount = rsmd.getColumnCount();
            int rowcount = 0;
            if (resultat.last()) {
                rowcount = resultat.getRow();
                resultat.beforeFirst(); // not rs.first() because the rs.next() below will move on, missing the first element
            }
            achievement = new Object[rowcount][columncount];
            while(resultat.next()){
                for (int i=0;i<columncount;i++) {
                    achievement[resultat.getRow() - 1][i] = resultat.getObject(i+1);
                }
            }
        }
        catch (Exception e){

            System.out.println("echec pilote sur achievement: "+e);
        }
    }
    public void initJoueurAchievement(){
        String pilote = "com.mysql.jdbc.Driver";
        String url="jdbc:mysql://localhost/Bataille_navale?useSSL=false";
        String user="root";
        String pass ="";
        try{
            Class.forName(pilote);

            Connection connexion = DriverManager.getConnection(url,user,pass);

            Statement instruction = connexion.createStatement();

            ResultSet resultat = instruction.executeQuery("SELECT * FROM joueurachievement");
            ResultSetMetaData rsmd= resultat.getMetaData();
            int columncount = rsmd.getColumnCount();
            int rowcount = 0;
            if (resultat.last()) {
                rowcount = resultat.getRow();
                resultat.beforeFirst(); // not rs.first() because the rs.next() below will move on, missing the first element
            }
            joueurAchievement = new Object[rowcount][columncount];
            while(resultat.next()){
                for (int i=0;i<columncount;i++) {
                    joueurAchievement[resultat.getRow() - 1][i] = resultat.getObject(i+1);
                }
            }

        }
        catch (Exception e){

            System.out.println("echec pilote sur joueurachievement : "+e);
        }
    }


    public Object[][] getJoueur() {
        return joueur;
    }

    public Object[][] getAchievement() {
        return achievement;
    }

    public Object[][] getJoueurAchievement() {
        return joueurAchievement;
    }

    private Object[][] joueur;
    private Object[][] achievement;
    private Object[][] joueurAchievement;
}
