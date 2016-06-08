package Controleur;

/**
 * Created by michael on 04/06/2016.
 */

import Controleur.ClientProcessor;
import Modele.Jeu;

import javax.swing.*;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class TimeServer {

    //On initialise des valeurs par défaut
    private int port = 2345;
    private String host = "127.0.0.1";
    private ServerSocket server = null;
    private boolean isRunning = true;
    private Jeu jeu;

    public TimeServer(){
        try {
            server = new ServerSocket(port, 100, InetAddress.getByName(host));
        } catch (UnknownHostException e) {
            JOptionPane.showMessageDialog(null,"Erreur de connexion","Erreur de connexion",JOptionPane.ERROR_MESSAGE);
            jeu.setOnline(false);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,"Erreur de connexion","Erreur de connexion",JOptionPane.ERROR_MESSAGE);
            jeu.setOnline(false);
        }
    }

    public TimeServer(String pHost, int pPort,Jeu jeu){
        this.jeu=jeu;
        host = pHost;
        port = pPort;
        try {
            server = new ServerSocket(port, 100, InetAddress.getByName(host));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //On lance notre serveur
    public void open(){

        //Toujours dans un thread à part vu qu'il est dans une boucle infinie
        Thread t = new Thread(new Runnable(){
            public void run(){
                while(isRunning == true){

                    try {
                        //On attend une connexion d'un client
                        Socket client = server.accept();

                        //Une fois reçue, on la traite dans un thread séparé
                        System.out.println("Connexion cliente reçue.");
                        Thread t = new Thread(new ClientProcessor(client,jeu));
                        t.start();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                try {
                    server.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    server = null;
                }
            }
        });

        t.start();
    }

    public void close(){
        isRunning = false;
    }
}
