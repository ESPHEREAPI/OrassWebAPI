/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entreprise;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author JIATOU FRANCK
 */
public class LicenceConnection {
    private String server;
    String service_Id = "orclass";
    String user = "LICENCE";
    String password = "Root14101987";
    int port = 1521;
    String virgule=",";
    Connection con = null;

    public LicenceConnection(String server) {
        this.server = server;
    }

    public LicenceConnection() {
        
    }
    
 public Connection   getRemoteConnection(){
     try {
          Class.forName("oracle.jdbc.driver.OracleDriver");
            String url = "jdbc:oracle:thin:@";
            url += server + ":" + port + ":" + service_Id ;
            con = DriverManager.getConnection(url, user, password);

        } catch (SQLException e) {
           Logger.getLogger("Erreur survenu connection", e.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(LicenceConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return con;
 }
 
 /*
 INSERER un exercice pour la licence
 */
 
    

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public String getService_Id() {
        return service_Id;
    }

    public void setService_Id(String service_Id) {
        this.service_Id = service_Id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getVirgule() {
        return virgule;
    }

    public void setVirgule(String virgule) {
        this.virgule = virgule;
    }

    public Connection getCon() {
        return con;
    }

    public void setCon(Connection con) {
        this.con = con;
    }
    
    
}
