package Modele;

import java.sql.*;
import java.util.Arrays;

/**
 * Created by belfort on 07/06/2016.
 */
public class BaseDeDonnees {
    private Connection connexion;
    private Jeu jeu;
    private Object[][] joueur;


    public BaseDeDonnees(Jeu j) throws BDDException {
        jeu = j;

        String pilote = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost/Bataille_navale?useSSL=false";
        String user = "root";
        String pass = "";
        try {
            Class.forName(pilote);

            connexion = DriverManager.getConnection(url, user, pass);

        } catch (Exception e) {
            throw new BDDException();
        }
    }

    public void execRequeteNonQuery(String requete) throws BDDException{

        try{
            Statement instruction = connexion.createStatement();
            instruction.executeUpdate(requete);
        }
        catch (Exception e){
            throw new BDDException();
        }
    }


    public Object[][] execQuery(String requete) throws BDDException{

        Object[][] result;

        try{

            Statement instruction = connexion.createStatement();

            ResultSet resultat = instruction.executeQuery(requete);
            ResultSetMetaData rsmd= resultat.getMetaData();
            int columncount = rsmd.getColumnCount();
            int rowcount = 0;
            if (resultat.last()) {
                rowcount = resultat.getRow();
                resultat.beforeFirst();
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



    public void debloqueLuckyShot(Jeu jeu){
        try {
            if (execQuery("SELECT * FROM joueurAchievement WHERE idJoueur=" + jeu.getJoueurConcerne().getIdJoueur() + " AND idAchievement=2").length == 0) {
                execRequeteNonQuery("INSERT INTO joueurachievement (idJoueur, idAchievement) VALUES (" + jeu.getJoueurConcerne().getIdJoueur() + ",2)");
            }
        }
        catch(BDDException e1){
        }
    }

    public void debloqueSharpshooter(){
        try {
            if (execQuery("SELECT * FROM joueurAchievement WHERE idJoueur=" + jeu.getJoueurConcerne().getIdJoueur() + " AND idAchievement=1").length == 0) {
                execRequeteNonQuery("INSERT INTO joueurachievement (idJoueur, idAchievement) VALUES (" + jeu.getJoueurConcerne().getIdJoueur() + ",1)");
            }
        }
        catch(BDDException e2){
        }
    }

    public Object[][] recupereExperienceJoueur() throws BDDException{
        return execQuery("SELECT idJoueur,expJoueur,levelJoueur FROM joueur WHERE idJoueur="+jeu.getJoueurConcerne().getIdJoueur()+" OR "+jeu.getJoueurNonConcerne().getIdJoueur());

    }

    public void updateExperience(Object[][] experienceJoueur) throws BDDException{
        float expJConcerne;
        float expJNonConcerne;
        for (int i=0;i<experienceJoueur.length;i++){
            if ((int)experienceJoueur[i][0]==jeu.getJoueurConcerne().getIdJoueur()){
                expJConcerne=((jeu.getJoueurNonConcerne().getFlotte().getNbTouches()/(float)jeu.getJoueurConcerne().getNbCoups())*100+(float)experienceJoueur[i][1]);
                if (expJConcerne>1000){
                    expJConcerne=expJConcerne%1000;
                    float niveauJoueur=(float)experienceJoueur[i][2]+1;
                    execRequeteNonQuery("UPDATE JOUEUR SET levelJoueur ="+niveauJoueur+" WHERE idJoueur="+jeu.getJoueurConcerne().getIdJoueur());
                }
                execRequeteNonQuery("UPDATE JOUEUR SET expJoueur ="+expJConcerne+" WHERE idJoueur="+jeu.getJoueurConcerne().getIdJoueur());
            }
            if ((int)experienceJoueur[i][0]==jeu.getJoueurNonConcerne().getIdJoueur()){
                expJNonConcerne=((jeu.getJoueurNonConcerne().getFlotte().getNbTouches()/(float)jeu.getJoueurConcerne().getNbCoups())*50+(float)experienceJoueur[i][1]);
                if (expJNonConcerne>1000){
                    expJNonConcerne=expJNonConcerne%1000;
                    float niveauJoueur=(float)experienceJoueur[i][2]+1;
                    execRequeteNonQuery("UPDATE JOUEUR SET levelJoueur ="+niveauJoueur+" WHERE idJoueur="+jeu.getJoueurNonConcerne().getIdJoueur());
                }
                execRequeteNonQuery("UPDATE JOUEUR SET expJoueur ="+expJNonConcerne+" WHERE idJoueur="+jeu.getJoueurNonConcerne().getIdJoueur());
            }
        }
    }

    public int insertJoueur(ModelConteneurInscription model, String pseudoJoueur1, String pseudoJoueur2) throws BDDException{
        int increm = 0;

        if (!Arrays.stream(model.getListPseudo()).anyMatch(x -> x == pseudoJoueur1)) {
            execRequeteNonQuery("INSERT INTO JOUEUR (idJoueur, pseudoJoueur) VALUES (NULL, '" + pseudoJoueur1 + "')");
            increm++;
        }
        if (!Arrays.stream(model.getListPseudo()).anyMatch(x -> x == pseudoJoueur2)) {
            execRequeteNonQuery("INSERT INTO JOUEUR (idJoueur, pseudoJoueur) VALUES (NULL, '" + pseudoJoueur2 + "')");
            increm++;
        }

        return increm;
    }

    public void fermeConnexion(){
        try{
            connexion.close();
        }
        catch(SQLException  e){

        }
    }

    public void initJoueur() throws BDDException{

        try{
            Statement instruction = connexion.createStatement();

            ResultSet resultat = instruction.executeQuery("SELECT * FROM joueur");
            ResultSetMetaData rsmd= resultat.getMetaData();
            int columncount = rsmd.getColumnCount();
            int rowcount = 0;
            if (resultat.last()) {
                rowcount = resultat.getRow();
                resultat.beforeFirst();
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


    public Object[][] getJoueur(){
        return joueur;
    }



}
