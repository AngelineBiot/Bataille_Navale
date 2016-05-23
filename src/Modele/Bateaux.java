package Modele;


import java.io.Serializable;
import java.util.ResourceBundle;


/**
 * Created by angel on 16/04/2016.
 *
 */
public class Bateaux implements Serializable{


    private int taille;
    private Case[] position;
    private boolean coule;
    private boolean estOrienteVerticalement;
    private String typeBateau;
    private String typeBateauInternational;

    public Bateaux(){}

    Bateaux(String typeDeBateau){
        typeBateau = typeDeBateau;
        if (typeDeBateau.equals("porte-avion")){
            this.taille=5;
        }
        if (typeDeBateau.equals("croiseur")){
            this.taille=4;
        }
        if (typeDeBateau.equals("sous-marin")){
            this.taille=3;
        }
        if (typeDeBateau.equals("torpilleur")){
            this.taille=2;
        }



        ResourceBundle texteInternational = ResourceBundle.getBundle("traductions.Bateaux");
        typeBateauInternational = texteInternational.getString(typeDeBateau);
    }

    public void setBateaux(String typeDeBateau){  //Porte-avion, Croiseur, Sous-marin, Torpilleur
        if (typeDeBateau.equals("porte-avion")){
            this.taille=5;
        }
        if (typeDeBateau.equals("croiseur")){
            this.taille=4;
        }
        if (typeDeBateau.equals("sous-marin")){
            this.taille=3;
        }
        if (typeDeBateau.equals("torpilleur")){
            this.taille=2;
        }
    }
    public int getTaille() {
        return taille;
    }
    public boolean getCoule(){
        return coule;
    }
    public boolean getEstOrienteVerticalement(){
        return this.estOrienteVerticalement;
    }
    public void setEstOrienteVerticalement(boolean orientation){
        this.estOrienteVerticalement=orientation;
    }

    public void updateEstCoule(){
        int i = 0;
        for (Case coordonnees : position) {
            if (coordonnees.getToucher()) i++;
        }
        coule = (i == this.taille);
    }
    public boolean estTouche(){
        boolean estTouche = false;
        for(Case coordonnees : position){
            if(coordonnees.getToucher()){
                estTouche = true;
            }
        }
        return estTouche;
    }

    public void setPosition(Case[] tabCases, boolean estVertical){
        position = tabCases;
        estOrienteVerticalement = estVertical;
    }

    public Case[] getPosition(){
        return position;
    }

    public int getCoordonneesPremiereCase(){
        return position[0].getCoord1D();
    }

    public String getTypeBateau(){
        return typeBateau;
    }

    public int getIndiceCaseBateau(Case c){
        int i;
        int retour = 0;

        for(i=0 ; i<position.length ; i++){
            if(c == position[i]){
                retour = i;
            }
        }

        return retour;
    }

    public String getTypeBateauInternational() {
        return typeBateauInternational;
    }
}