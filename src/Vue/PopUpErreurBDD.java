package Vue;

import javax.swing.*;
import java.util.ResourceBundle;

/**
 * Created by belfort on 07/06/2016.
 */
public class PopUpErreurBDD extends JOptionPane{
    public PopUpErreurBDD(boolean afficheReessayerPlusTrad){
        super();
        ResourceBundle texteInternational = ResourceBundle.getBundle("traductions.Database");

        String partieDeuxduTexte;

        if(afficheReessayerPlusTrad){
            partieDeuxduTexte = texteInternational.getString("plus_tard");
        }
        else{
            partieDeuxduTexte = texteInternational.getString("peut_pas_sauvegarder");
        }


        showMessageDialog(null,texteInternational.getString("pas_accessible")+" "+partieDeuxduTexte,
                    texteInternational.getString("erreur"), JOptionPane.ERROR_MESSAGE);


    }
}
