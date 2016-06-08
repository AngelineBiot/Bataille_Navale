package Modele;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.*;
import java.util.Enumeration;

/**
 * Created by michael on 04/06/2016.
 */
public class ModelEnLigne {
    public static String getIpAddress()
    {
        String ip;
        try {
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
            while (interfaces.hasMoreElements()) {
                NetworkInterface iface = interfaces.nextElement();
                // filters out 127.0.0.1 and inactive interfaces
                if (iface.isLoopback() || !iface.isUp())
                    continue;

                Enumeration<InetAddress> addresses = iface.getInetAddresses();
                while(addresses.hasMoreElements()) {
                    InetAddress addr = addresses.nextElement();
                    ip = addr.getHostAddress();
                   return (ip);
                }
            }
        } catch (SocketException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public void initAdresseOrdi(){
        adresseOrdi=ModelEnLigne.getIpAddress();
    }

    public String getAdresseOrdi() {
        return adresseOrdi;
    }

    private String adresseOrdi;
}
