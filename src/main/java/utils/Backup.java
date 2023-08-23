/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 *
 * @author frank
 */
public class Backup {

    private static int BUFFER = 10485760;

//    public Backup() {
//
//    }
     /*
     sauvegarde de la base de donnee
     */
    public final static String USER = "root";
    public final static String PASSWORD = "root";
    public final static String SERVER = "localhost";
    public final static String PORT = "3306";
    public final static String DBBASE = "paie";
    public final static String EXTENTION = ".sql";
    private static ResultSet res;
    private static Connection con;
    private Statement st;
//    private int BUFFER = 99999;

    
    public static void getData(String host, String port, String user,
            String password, String db, String path) throws Exception {
        Process run = Runtime.getRuntime().exec(
                "mysqldump --host=" + host + " --port=" + port
                + " --user=" + user + " --password=" + password
                + " --compact --complete-insert --extended-insert  "
                + "--skip-comments --skip-triggers  --add-drop-table --database " + db);
        InputStream in = run.getInputStream();
        path += IdleDate.getDayMonth(new Date()) + "_" + IdleDate.getMonth(new Date()) + "_" + IdleDate.getYear(new Date()) + EXTENTION;
        File ticket = new File(path);
//        Document document = null;
        if (ticket.exists() == false) {
            ticket.createNewFile();
        }
        FileOutputStream fos = new FileOutputStream(ticket);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));

        BufferedReader br = new BufferedReader(new InputStreamReader(in));

        StringBuffer temp = new StringBuffer();

        int count;
        char[] cbuf = new char[BUFFER];

        while ((count = br.read(cbuf, 0, BUFFER)) != -1) {
//            temp.append(cbuf, 0, count);
            bw.write(cbuf, 0, count);
        }

        bw.close();
        br.close();
        in.close();

//        return temp.toString();
    }

    public static void getData(String path) throws Exception {
        Process run = Runtime.getRuntime().exec(
                "mysqldump --host=" + SERVER + " --port=" + PORT
                + " --user=" + USER + " --password=" + PASSWORD
                + " --compact --complete-insert --extended-insert  "
                + "--skip-comments --skip-triggers  --add-drop-table --database " + DBBASE);
        InputStream in = run.getInputStream();
        path += IdleDate.getDayMonth(new Date()) + "_" + IdleDate.getMonth(new Date()) + "_" + IdleDate.getYear(new Date()) + EXTENTION;
        File ticket = new File(path);
//        Document document = null;
        if (ticket.exists() == false) {
            ticket.createNewFile();
        }
//        else {

//            String nameFile = path.split("/.sql")[0];
//            nameFile += "_" + new Date().getTime() + ".sql";
//            ticket = new File(nameFile);
//            if (ticket.exists() == false) {
//                ticket.createNewFile();
//            }
//        }
        FileOutputStream fos = new FileOutputStream(ticket);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));

        BufferedReader br = new BufferedReader(new InputStreamReader(in));

        StringBuffer temp = new StringBuffer();

        int count;
        char[] cbuf = new char[BUFFER];

        while ((count = br.read(cbuf, 0, BUFFER)) != -1) {
//            temp.append(cbuf, 0, count);
            bw.write(cbuf, 0, count);
        }

        bw.close();
        br.close();
        in.close();

//        return temp.toString();
    }
    /*
     compresser le fichier de sauvegarde de la base de donnee
     */

    public static void fileZip(String path) throws FileNotFoundException, IOException {
        // input file 
        path += "_"+IdleDate.getDayMonth(new Date()) + "_" + IdleDate.getMonth(new Date()) + "_" + IdleDate.getYear(new Date()) + EXTENTION;
        FileInputStream in = new FileInputStream(path);

        // out put file 
        String outFileZipe = path.replace(".sql", ".zip");
        ZipOutputStream out = new ZipOutputStream(new FileOutputStream(outFileZipe));

        // name the file inside the zip  file 
        out.putNextEntry(new ZipEntry("database.sql"));

        // buffer size
        byte[] b = new byte[1024];
        int count;

        while ((count = in.read(b)) > 0) {
            System.out.println();
            out.write(b, 0, count);
        }
        out.close();
        in.close();

    }

 
}
