package mdb;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.HttpURLConnection;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author fabrice
 */
public class Passerelle {

    /**
     * @param args the command line arguments
     */
    public static final String LOGIN = "uwin";
    public static final String PWD = "uwin";
    public static final String SENDERID = "GBSchool";
    private URL url;
    private HttpURLConnection server;
    private int Cnx;
    private String reponse;
    private String[]retour;

    /**
     * @return the LOGIN
     */
    public int connect(String method) throws Exception {
        try {
            System.out.println("url "+url.toURI().toString());
            server = (HttpURLConnection) url.openConnection();
            server.setDoInput(true);
            server.setDoOutput(true);
            server.setRequestMethod(method);
            server.setRequestProperty("Content-type",
                    "application/x-www-form-urlencoded");
            server.connect();
           

            return 1;
        } catch (URISyntaxException | IOException e) {
            //throw new Exception("Connection to the http server failed");
            System.err.println(e.toString());
            System.err.println("erreur de connection à la passerelle sms");

            return -1;
        }
    }

    public static String replaceURL(String l) {
        l = l.replaceAll(" ", "%20");
        l = l.replaceAll("\"", "%22");
        l = l.replaceAll("#", "%23");
        //l=l.replaceAll("%","%25");
        //l.replaceAll("&","%26");
        l = l.replaceAll("\\(", "%28");
        l = l.replaceAll("\\)", "%29");
        l = l.replaceAll("\\+", "%2B");
        l = l.replaceAll(",", "%2C");
        l = l.replaceAll("\\.", "%2E");
        //l.replaceAll("\\","%2F");
        l = l.replaceAll(":", "%3A");
        l = l.replaceAll(";", "%3B");
        l = l.replaceAll("<", "%3C");
        //l=l.replaceAll("=","%3D");
        l = l.replaceAll(">", "%3E");
        //l.replaceAll("?","%3F");
        l = l.replaceAll("@", "%40");
        l = l.replaceAll("\\[", "%5B");
        l = l.replaceAll("]", "%5D");
        // l=l.replaceAll("^","%5E");
        l = l.replaceAll("'", "%60");
        l = l.replaceAll("\\{", "%7B");
        // l=l.replaceAll("|","%7C");
        l = l.replaceAll("}", "%7D");
        l = l.replaceAll("~", "%7D");



        return l;
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
//            while (line != null) {
//                System.out.println(line);
//                b = line;
////                line = s.readLine();
//                
//
//            }
            //System.out.println(Integer.parseInt(line.substring(0, 1)));

            s.close();
            return b;

        } catch (Exception e) {
            //throw new Exception("Unable to read input stream");
            System.err.println(e.toString());
            System.err.println("Impossible de lire la reponse de la passerelle 1");

            return "NOK";

        }
    }
      public String[] displayResponseT() throws Exception {
        String line;
        String [] b ;
        String donnee;

        try {
             BufferedReader s = new BufferedReader(
                    new InputStreamReader(
                    server.getInputStream()));
            String url = server.getURL().toString();
            line = s.readLine();
            if (line!=null) {
                System.out.println(line);
                retour = line.split("\\|");
            }
 
//                line = s.readLine();
            //System.out.println(Integer.parseInt(line.substring(0, 1)));

            s.close();
//            server.
            return retour;

        } catch (Exception e) {
            //throw new Exception("Unable to read input stream");
            System.err.println(e.toString());
            System.err.println("Impossible de lire la reponse de la passerelle 1");

            return null;

        }
    }
   
    
//      public String[] displayResponse() throws Exception {
//        String line;
//        String b = "";
//
//        try {
//            BufferedReader s = new BufferedReader(
//                    new InputStreamReader(
//                    server.getInputStream()));
//            String[] url = server.;
//            line = s.readLine();
//            while (line != null) {
//                System.out.println(line);
//                b = line;
//                line = s.readLine();
//
//            }
//            //System.out.println(Integer.parseInt(line.substring(0, 1)));
//
//            s.close();
//            return b;
//
//        } catch (Exception e) {
//            //throw new Exception("Unable to read input stream");
//            System.err.println(e.toString());
//            System.err.println("Impossible de lire la reponse de la passerelle 1");
//
//            return "NOK";
//
//        }
//    }

      

    public String SendSms2( String login,String pwd,String compte,String sender,String phone, String msg) {
        String params;
        params = "login=" + login + "&pwd=" + pwd + "&compte="+compte+"&sender=" + sender+ "&receiver=" + phone + "&message=" + msg;
        try {
            params = replaceURL(params);
             url = new URL("http://betterplanning.net/smsgateway/usepass.php?" +params);
            //server3.setConnectTimeout(0);
            Cnx = this.connect("GET");
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
    public int soldeCompte(String login,String pwd,String sender){
          String params;
          int rep=-1;
        params = "UserName=" + login + "&Password=" + pwd;
        try {
//           params = replaceURL(params);
           url = new URL("http://lmtgroup.dyndns.org/sendsms/soldeGold.php?"+params);
            //server3.setConnectTimeout(0);
             System.out.println("url avant connection "+url.toString());
            Cnx = this.connect("GET");
            if (Cnx == 1) {
              reponse= this.displayResponse();
              rep=Integer.parseInt(reponse);
                return rep;
            } else if (Cnx == -2) {//paramettre de connection incorrect
                return -2;
            }
           
        }catch (Exception ex) {
           
        }
        
      return -1;//utilisateur desactiver
    }
    
     public String[] SendSms( String login,String pwd,String sender,String phone, String msg) {
        String params;
        params = "UserName=" + login + "&Password=" + pwd + "&SOA="+sender+"&MN=" + phone + "&SM=" + msg;
        try {
            params = replaceURL(params);
             url = new URL("http://lmtgroup.dyndns.org/sendsms/sendsmsGold.php?"+params);
            //server3.setConnectTimeout(0);
             System.out.println("url avant connection "+url.toString());
            Cnx = this.connect("GET");
            if (Cnx == 1) {
              retour= this.displayResponseT();
                return retour;
            } else {
                return null;
            }

        } catch (Exception ex) {
           
        }
         
         
        return retour;
    }
   
    
    public static void main(String[] args)  {
        
        double totalExam=41.2857;
   // totalExam=BigDecimal.valueOf(totalExam).round(new MathContext(4, RoundingMode.HALF_UP)).doubleValue();
        totalExam=(new BigDecimal(totalExam).setScale(2,RoundingMode.HALF_UP)).doubleValue();
        System.out.println("total:"+totalExam);
        
        
//        long timeNow = System.currentTimeMillis();
//            System.out.println(new Date(timeNow));
//            
//            int timeNow2=Calendar.getInstance().get(Calendar.MILLISECOND);
//            System.out.println(new Date(timeNow));
        
//        Connection con=Passerelle.getRemoteConnection("192.185.48.219", "3306", "betahost_licenceserver", "betahost_lserver", "lserver@");
//      //   Connection con=Passerelle.getRemoteConnection("localhost", "3306", "globalschoolbd", "fabrice", "kokodi");
//        
//         if(con==null){
//            System.out.println("erreur de connection");
//        }else{
//           System.out.println("connection OK1"); 
//        }
//        
        
     
    }
     public static Connection getRemoteConnection(String ip,String port,String bd,String user,String pwd) {
        Connection con = null;
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            con = DriverManager.getConnection("jdbc:mysql://"+ip+":"+port+"/" + bd, user, pwd);
            System.out.println("Connection Established!");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
        }
        return con;
    }
}