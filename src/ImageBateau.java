import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Florian on 20/04/2016.
 * Updated today
 */
public class ImageBateau{
    private static BufferedImage[] tableauImagesBateauVertical;
    private static BufferedImage[] tableauImagesBateauHorizontal;


    public static ImageIcon getImageBateau(String typeBateau, boolean imageVerticale){
        int indexBateau = recupereIndexBateau(typeBateau);
        ImageIcon resultat;

        if(imageVerticale){
            resultat = new ImageIcon(tableauImagesBateauVertical[indexBateau]);
        }
        else{
            resultat = new ImageIcon(tableauImagesBateauHorizontal[indexBateau]);
        }

        return resultat;
    }

    public static ImageIcon getImageBateau(String typeBateau, boolean imageVerticale, int partieVoulue){
        int indexBateau = recupereIndexBateau(typeBateau);
        BufferedImage imagePartieBateauIntermediaire;
        ImageIcon resultat;

        if(imageVerticale){
            imagePartieBateauIntermediaire = tableauImagesBateauVertical[indexBateau].getSubimage(0, 50*partieVoulue, 50, 50);
        }
        else{
            imagePartieBateauIntermediaire = tableauImagesBateauHorizontal[indexBateau].getSubimage(50*partieVoulue, 0, 50, 50);
        }

        resultat = new ImageIcon(imagePartieBateauIntermediaire);

        return resultat;
    }



    private static int recupereIndexBateau(String typeBateau){
        int resultat =0;

        switch (typeBateau) {
            case "torpilleur":
                resultat = 3;
                break;
            case "sous-marin":
                resultat = 2;
                break;
            case "croiseur":
                resultat = 1;
                break;
            case "porte-avion":
                resultat = 0;
                break;
        }

        return resultat;
    }



    public static void initTableauImagesBateau(){
        tableauImagesBateauVertical = new BufferedImage[4];
        tableauImagesBateauHorizontal = new BufferedImage[4];

        try{

            tableauImagesBateauVertical[3] = ImageIO.read(new File("images/TorpilleurVertical.png"));
            tableauImagesBateauVertical[2] = ImageIO.read(new File("images/SousMarinVertical.png"));
            tableauImagesBateauVertical[1] = ImageIO.read(new File("images/CroiseurVertical.png"));
            tableauImagesBateauVertical[0] = ImageIO.read(new File("images/PorteAvionVertical.png"));

            tableauImagesBateauHorizontal[3] = ImageIO.read(new File("images/TorpilleurHorizontal.png"));
            tableauImagesBateauHorizontal[2] = ImageIO.read(new File("images/SousMarinHorizontal.png"));
            tableauImagesBateauHorizontal[1] = ImageIO.read(new File("images/CroiseurHorizontal.png"));
            tableauImagesBateauHorizontal[0] = ImageIO.read(new File("images/PorteAvionHorizontal.png"));
        }
        catch(IOException e){
            e.printStackTrace();
            System.exit(8);
        }

    }
}