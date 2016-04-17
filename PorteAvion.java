/**
 * Created by angel on 16/04/2016.
 */
public class PorteAvion extends Bateaux{

    public PorteAvion(Coordonnees premiere_case){
        this.setTaille(5);
        initPosition(premiere_case);
    }

    public void initPosition(Coordonnees premiere_case){
        int taille = getTaille();
        for (int i = 1; i <= taille-1; i++) {

        }
    }
}
