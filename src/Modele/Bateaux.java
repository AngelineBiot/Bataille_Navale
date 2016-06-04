package Modele;


import java.io.Serializable;


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

    public void updateEstCoule(){
        int i = 0;
        for (Case coordonnees : position) {
            if (coordonnees.getToucher()) i++;
        }
        coule = (i == this.taille);
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



    public void initPosition(ModelConteneurPlacement modele, Grille grille){
        position = new Case[taille];
        int i;

        int x = modele.getCaseOuEstBateauEnCoursPlacement().getCoordoneX();
        int y = modele.getCaseOuEstBateauEnCoursPlacement().getCoordoneY();



        if(modele.isDirectionVerticale()){
            for(i=0 ; i<taille ; i++){
                position[i] = grille.getGrille()[x+(i+y)*10];
                position[i].setBat(this);
            }
        }
        else{
            for(i=0 ; i<taille ; i++){
                position[i] = grille.getGrille()[x+i+y*10];
                position[i].setBat(this);
            }
        }

        estOrienteVerticalement=modele.isDirectionVerticale();
    }
}