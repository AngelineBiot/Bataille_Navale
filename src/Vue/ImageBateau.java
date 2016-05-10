package Vue;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Florian on 20/04/2016.
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

    public static ImageIcon getImageBateau(String typeBateau, boolean imageVerticale, int partieVoulue, boolean coule){
        int indexBateau = recupereIndexBateau(typeBateau);
        if(coule){
            indexBateau += 4;
        }

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

        if(typeBateau.equals("torpilleur")){
            resultat = 3;
        }
        else if(typeBateau.equals("sous-marin")){
            resultat = 2;
        }
        else if(typeBateau.equals("croiseur")){
            resultat = 1;
        }
        else if(typeBateau.equals("porte-avion")){
            resultat = 0;
        }

        return resultat;
    }



    public static void initTableauImagesBateau(){
        tableauImagesBateauVertical = new BufferedImage[8];
        tableauImagesBateauHorizontal = new BufferedImage[8];

        try{

            tableauImagesBateauVertical[7] = ImageIO.read(new File("ressource/images/TorpilleurVerticalCoule.png"));
            tableauImagesBateauVertical[6] = ImageIO.read(new File("ressource/images/SousMarinVerticalCoule.png"));
            tableauImagesBateauVertical[5] = ImageIO.read(new File("ressource/images/CroiseurVerticalCoule.png"));
            tableauImagesBateauVertical[4] = ImageIO.read(new File("ressource/images/PorteAvionVerticalCoule.png"));
            tableauImagesBateauVertical[3] = ImageIO.read(new File("ressource/images/TorpilleurVertical.png"));
            tableauImagesBateauVertical[2] = ImageIO.read(new File("ressource/images/SousMarinVertical.png"));
            tableauImagesBateauVertical[1] = ImageIO.read(new File("ressource/images/CroiseurVertical.png"));
            tableauImagesBateauVertical[0] = ImageIO.read(new File("ressource/images/PorteAvionVertical.png"));

            tableauImagesBateauHorizontal[7] = ImageIO.read(new File("ressource/images/TorpilleurHorizontalCoule.png"));
            tableauImagesBateauHorizontal[6] = ImageIO.read(new File("ressource/images/SousMarinHorizontalCoule.png"));
            tableauImagesBateauHorizontal[5] = ImageIO.read(new File("ressource/images/CroiseurHorizontalCoule.png"));
            tableauImagesBateauHorizontal[4] = ImageIO.read(new File("ressource/images/PorteAvionHorizontalCoule.png"));
            tableauImagesBateauHorizontal[3] = ImageIO.read(new File("ressource/images/TorpilleurHorizontal.png"));
            tableauImagesBateauHorizontal[2] = ImageIO.read(new File("ressource/images/SousMarinHorizontal.png"));
            tableauImagesBateauHorizontal[1] = ImageIO.read(new File("ressource/images/CroiseurHorizontal.png"));
            tableauImagesBateauHorizontal[0] = ImageIO.read(new File("ressource/images/PorteAvionHorizontal.png"));
        }
        catch(IOException e){
            e.printStackTrace();
            System.exit(8);
        }

    }
}