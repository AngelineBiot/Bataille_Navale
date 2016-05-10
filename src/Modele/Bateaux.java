package Modele;




/**
 * Created by angel on 16/04/2016.
 */
public class Bateaux{


    private int taille;
    protected Case[] position;
    private boolean coule;
    private boolean estOrienteVerticalement;
    private String typeBateau;

    public Bateaux(){}

    public Bateaux(String typeDeBateau){
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

    public int getCoordonneesPremiereCase(){
        int coord = position[0].getCoord1D();
        return coord;
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
}