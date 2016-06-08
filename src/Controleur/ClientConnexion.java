package Controleur;

import Modele.Jeu;

import javax.swing.*;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Random;

public class ClientConnexion{

    private Socket connexion = null;
    private PrintWriter writer = null;
    private BufferedInputStream reader = null;
    private Jeu jeu;

    //Notre liste de commandes. Le serveur nous répondra différemment selon la commande utilisée.
    private static int count = 0;
    private String name = "Client-";

    public ClientConnexion(String host, int port,Jeu jeu){
        this.jeu=jeu;
        name += ++count;
        try {
            connexion = new Socket(host, port);
        } catch (UnknownHostException e) {
            JOptionPane.showMessageDialog(null,"Erreur de connexion","Erreur de connexion",JOptionPane.ERROR_MESSAGE);
            jeu.setOnline(false);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,"Erreur de connexion","Erreur de connexion",JOptionPane.ERROR_MESSAGE);
            jeu.setOnline(false);
        }
    }

    public String sendCommand(String commande){
        String response="";
        try {
            writer = new PrintWriter(connexion.getOutputStream(), true);
            reader = new BufferedInputStream(connexion.getInputStream());
            //On envoie la commande au serveur

            writer.write(commande);
            //TOUJOURS UTILISER flush() POUR ENVOYER RÉELLEMENT DES INFOS AU SERVEUR
            writer.flush();

            System.out.println("Commande " + commande + " envoyée au serveur");

            //On attend la réponse
            response = read();
            System.out.println("\t * " + name + " : Réponse reçue " + response);
            if (response.split("-")[0].equals("pseudo")){
                jeu.getJoueur2().setNomJoueur(response.split("-")[1]);
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        writer.close();
        return response;
    }

    //Méthode pour lire les réponses du serveur
    private String read() throws IOException{
        String response = "";
        int stream;
        byte[] b = new byte[4096];
        stream = reader.read(b);
        response = new String(b, 0, stream);
        return response;
    }
}