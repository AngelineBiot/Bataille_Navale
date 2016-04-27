import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Scanner;

import static com.sun.java.accessibility.util.AWTEventMonitor.addMouseListener;
import static java.awt.MouseInfo.getPointerInfo;

/**
 * Created by michael on 31/03/2016.
 */


public class Grille {
    final static Scanner input= new Scanner(System.in);


    private Case[] grille;
    Grille(){
        grille=new Case[100];
        for (int i=0;i<100;i++){
            int x=i%10;
            int y=i/10;
            grille[i]=new Case(x,y);
        }
    }
}

