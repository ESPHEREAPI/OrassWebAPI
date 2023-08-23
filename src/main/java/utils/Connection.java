/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URISyntaxException;
import java.net.URL;
import modele.OrclssMailInscription;

/**
 *
 * @author JIATOU FRANCK
 */
public class Connection {

    private URL url;
    private HttpURLConnection server;
    private int Cnx;
    private String reponse;

    public Connection() {
    }

    private int connect(String method) throws Exception {
        try {

            server = (HttpURLConnection) url.openConnection();
            server.setDoInput(true);
            server.setDoOutput(true);
            server.setRequestMethod(method);
            server.setRequestProperty("Content-type",
                    "application/json");
            server.connect();
                        System.out.println("url " + url.toURI().toString());

            return 1;
        } catch (URISyntaxException | IOException e) {
            //throw new Exception("Connection to the http server failed");
            System.err.println(e.toString());
            System.err.println("erreur de connection ");

            return -1;
        }
    }

    public String displayResponse() throws Exception {
        String line;
        String b = "";

        try {
            BufferedReader s = new BufferedReader(
                    new InputStreamReader(
                            server.getInputStream()));
            String url = server.getURL().toString();
            b = s.readLine();

            s.close();
            return b;

        } catch (Exception e) {
            //throw new Exception("Unable to read input stream");
            System.err.println(e.toString());
            System.err.println("Impossible de lire la reponse de la passerelle 1");

            return null;

        }
    }

    public boolean isHostServiceAvailable() {
        try {

            url = new URL("http://localhost:6000/mails/connect");
            //server3.setConnectTimeout(0);
            Cnx = this.connect("GET");
            if (Cnx == 1) {
                reponse = this.displayResponse();
                System.out.println("Serveur Connecter.. http://localhost:6000/mails/connect");
                return true;
            } else {
                return false;
            }

        } catch (Exception ex) {

        }
        return false;
    }
    
    public String send(OrclssMailInscription m) {

        String params;
//        params = "/" + email + "&subject=" + subject + "&body=" + body + "&path=" + path;
        params = "/" + m.getId();
        try {
//            params = replaceURL(params);
            url = new URL("http://localhost:6000/mails" + params);
            //server3.setConnectTimeout(0);
            Cnx = this.connect("DELETE");
            if (Cnx == 1) {
                reponse = this.displayResponse();
                return reponse;
            } else {
                return "echec";
            }

        } catch (Exception ex) {

        }
        return reponse;
    }

}
