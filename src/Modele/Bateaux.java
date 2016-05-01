package Modele;

/**
 * Created by angel on 16/04/2016.
 * Updated today
 */
public class Bateaux{

    private int taille;
    private Case[] position;
    private boolean coule;
    private boolean estOrienteVerticalement; //v ou h
    private String typeBateau;

    public Bateaux(){}

    public Bateaux(String typeDeBateau){
        coule = false;

        typeBateau = typeDeBateau;

        switch (typeBateau) {
            case "porte-avion":
                this.taille = 5;
                break;
            case "croiseur":
                this.taille = 4;
                break;
            case "sous-marin":
                this.taille = 3;
                break;
            case "torpilleur":
                this.taille = 2;
                break;
        }
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
    public boolean estCoule(){
        int i = 0;
        for (Case coordonnees : position) {
                if (coordonnees.getToucher()) i++;
        }
        return i == this.taille;
    }

    public void setPosition(Case[] tabCases, boolean estVertical){
        position = tabCases;
        estOrienteVerticalement = estVertical;
    }

    public String getTypeBateau(){
        return typeBateau;
    }
}