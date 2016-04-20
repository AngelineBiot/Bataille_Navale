/**
 * Created by michael on 31/03/2016.
 */
public class Case{
    public int coordoneX;
    public int coordoneY;
    Bateaux bat;
    boolean toucher;
    Case(int x,int y){
        this.toucher = false;
        this.coordoneX=x;
        this.coordoneY=y;
    }
    Case(int x, int y, Bateaux b ){
        this.coordoneX=x;
        this.coordoneY=y;
        this.toucher = false;
        this.bat=b;
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
    public int[] getCoord(){return new int[]{coordoneX,coordoneY};}
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