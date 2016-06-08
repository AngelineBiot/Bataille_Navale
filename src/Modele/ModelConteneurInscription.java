package Modele;

import java.sql.*;

/**
 * Created by michael on 18/05/2016.
 */
public class ModelConteneurInscription {


    private boolean online;

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

    public void setOnline(boolean online) {
        this.online = online;
    }

    public boolean isOnline() {
        return online;
    }
}
