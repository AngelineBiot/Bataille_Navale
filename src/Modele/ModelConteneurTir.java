package Modele;


import java.sql.*;

/**
 * Created by michael on 28/04/2016.
 *
 */
public class ModelConteneurTir {

    private int coordX;
    private int coordY;
    private int coord1D;
    private int dimensionCarre;
    private Case caseOuEstTire;


    //Utilisation du constructeur vide par defaut

    public void execRequeteNonQuery(String requete) throws BDDException{
        String pilote = "com.mysql.jdbc.Driver";
        String url="jdbc:mysql://localhost/Bataille_navale?useSSL=false";
        String user="root";
        String pass ="";
        try{
            Class.forName(pilote);

            Connection connexion = DriverManager.getConnection(url,user,pass);

            Statement instruction = connexion.createStatement();
            instruction.executeUpdate(requete);
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

    public int getCoordX() {
        return coordX;
    }

    public void setCoordX(int coordX) {
        this.coordX = coordX;
    }

    public int getCoordY() {
        return coordY;
    }

    public void setCoordY(int coordY) {
        this.coordY = coordY;
    }

    public int getDimensionCarre() {
        return dimensionCarre;
    }

    public void setDimensionCarre(int dimensionCarre) {
        this.dimensionCarre = dimensionCarre;
    }


    public Case getCaseOuEstTire() {
        return caseOuEstTire;
    }

    public void setCaseOuEstTire(Case caseOuEstTire) {
        this.caseOuEstTire = caseOuEstTire;
    }

    public void updateCaseOuEstTire(Jeu jeu) {
        this.caseOuEstTire = jeu.getJoueurNonConcerne().getGrille().getGrille()[coord1D];
    }

    public int getCoord1D() {
        return coord1D;
    }

    public void setCoord1D(int coord1D) {
        this.coord1D = coord1D;
    }

    public void setCoordonnees(int x, int y){
        setCoordX(x / getDimensionCarre());
        setCoordY(y / getDimensionCarre());
        setCoord1D(coordX + coordY * 10);
    }


}
