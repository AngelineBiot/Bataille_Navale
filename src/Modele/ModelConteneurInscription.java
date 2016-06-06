package Modele;

import java.sql.*;

/**
 * Created by michael on 18/05/2016.
 */
public class ModelConteneurInscription {
    public void execRequeteNonQuery(String requete) throws BDDException{
        String pilote = "com.mysql.jdbc.Driver";
        String url="jdbc:mysql://localhost/Bataille_navale?useSSL=false";
        String user="root";
        String pass ="";
        try{
            Class.forName(pilote);

            Connection connexion = DriverManager.getConnection(url,user,pass);

            Statement instruction = connexion.createStatement();
            System.out.println(requete);
            instruction.executeUpdate(requete);
        }
        catch (Exception e){

            throw new BDDException();
        }
    }
    public Object[][] getJoueur(){
        return joueur;
    }
    public void initJoueur() throws BDDException{
        String pilote = "com.mysql.jdbc.Driver";
        String url="jdbc:mysql://localhost/Bataille_navale?useSSL=false";
        String user="root";
        String pass ="";
        try{
            Class.forName(pilote);

            Connection connexion = DriverManager.getConnection(url,user,pass);

            Statement instruction = connexion.createStatement();

            ResultSet resultat = instruction.executeQuery("SELECT * FROM joueur");
            ResultSetMetaData rsmd= resultat.getMetaData();
            int columncount = rsmd.getColumnCount();
            int rowcount = 0;
            if (resultat.last()) {
                rowcount = resultat.getRow();
                resultat.beforeFirst(); // not rs.first() because the rs.next() below will move on, missing the first element
            }
            joueur = new Object[rowcount][columncount];
            while(resultat.next()){
                joueur[resultat.getRow()-1][0]=resultat.getObject(1);
                joueur[resultat.getRow()-1][1]=resultat.getObject(2);
            }
        }
        catch (Exception e){

            throw new BDDException();
        }
    }
    public Object[][] execQuery(String requete) throws BDDException{
        String pilote = "com.mysql.jdbc.Driver";
        String url="jdbc:mysql://localhost/Bataille_navale?useSSL=false";
        String user="root";
        String pass ="";
        Object[][] result=null;
        try{
            Class.forName(pilote);

            Connection connexion = DriverManager.getConnection(url,user,pass);

            Statement instruction = connexion.createStatement();

            ResultSet resultat = instruction.executeQuery(requete);
            ResultSetMetaData rsmd= resultat.getMetaData();
            int columncount = rsmd.getColumnCount();
            int rowcount = 0;
            if (resultat.last()) {
                rowcount = resultat.getRow();
                resultat.beforeFirst(); // not rs.first() because the rs.next() below will move on, missing the first element
            }
            result = new Object[rowcount][columncount];
            while(resultat.next()){
                result[resultat.getRow()-1][0]=resultat.getObject(1);
                result[resultat.getRow()-1][1]=resultat.getObject(2);
            }
        }
        catch (Exception e){

            throw new BDDException();
        }
        return result;
    }

    public void initListPseudo(Object[][] j){
        listPseudo=new String[j.length-1];
        for (int i=0;i<j.length-1;i++){
            if (!j[i+1][1].equals("GLaDAS")) {
                listPseudo[i] = (String) j[i+1][1];
            }
        }
    }

    public String[] getListPseudo() {
        return listPseudo;
    }

    private String[] listPseudo;
    private Object[][] joueur;
}
