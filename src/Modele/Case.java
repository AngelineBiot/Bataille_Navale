package Modele;

import java.io.Serializable;

/**
 * Created by michael on 31/03/2016.
 *
 */
public class Case implements Serializable {

    private int coordoneX;
    private int coordoneY;
    private Bateaux bat;
    private boolean toucher;

    Case(int x,int y){
        this.toucher = false;
        this.coordoneX=x;
        this.coordoneY=y;
    }


    public int getCoordoneX() {
        return coordoneX;
    }

    public int getCoordoneY() {
        return coordoneY;
    }

    public int getCoord1D(){
        return coordoneX+coordoneY*10;
    }

    public Bateaux getBat() {
        return bat;
    }
    public void setBat(Bateaux b){
        this.bat=b;
    }
    public void setToucher(){
        this.toucher=true;
    }

    public boolean getToucher() {
        return toucher;
    }
}