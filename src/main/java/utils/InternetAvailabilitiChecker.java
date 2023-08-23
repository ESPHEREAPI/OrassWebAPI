/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.URL;
import java.rmi.UnknownHostException;
import modele.OrclssMailInscription;

/**
 *
 * @author JIATOU FRANCK
 */
  
public class InternetAvailabilitiChecker {
    
    
    public static boolean  isInternetAvailable() throws IOException {
        return isHostAvailable("google.com")|| isHostAvailable("Amazon.com")
                ||isHostAvailable("facebook.com")|| isHostAvailable("Apple.com");
    }
    private static boolean  isHostAvailable(String hostName)throws IOException{
        try  (Socket socket=new Socket()){
          
            int port=80;
            InetSocketAddress socketAddress=new InetSocketAddress(hostName, port);
            socket.connect(socketAddress, 3000);
            return true;
        } catch (UnknownHostException e) {
            
        }
        return false;
    }
    
   
  
    
}
