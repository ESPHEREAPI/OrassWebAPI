///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package Entreprise;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.Statement;
////import modele.OrclassBranches;
////import modele.OrclassCategories;
//import modele.OrclassFonctionnalites;
//import modele.OrclassMenus;
//import modele.OrclassMenusAcces;
////import modele.OrclassPlafondMaladie;
//import modele.OrclassProfils;
//import modele.OrclassProfilsAcces;
//import modele.OrclassUtilisateursAcces;
//
///**
// *
// * @author hp
// */
//public class OracleConnection {
//
//    String server = "localhost";
//    // travail en local 
////    String server = "192.168.2.192";
//    // serveur en ligne
//    String service_Id = "orclass"; 
////     String service_Id = "BDTEST";
//    String user = "orclass";
//    String password = "Root14101987";
//    String dataBase = "orclass";
//    int port = 1521;
//    String virgule=",";
//    Connection con = null;
//
//    public OracleConnection() {
//
//        try {
//
//            Class.forName("oracle.jdbc.driver.OracleDriver");
//
//        } catch (Exception e) {
//        }
//    }
//
//    public Connection connectionForDataBase() {
//        try {
//            String url = "jdbc:oracle:thin:@";
//            url += server + ":" + port + ":" + service_Id ;
//            con = DriverManager.getConnection(url, user, password);
//
//        } catch (Exception e) {
//            return null;
//        }
//        return con;
//    }
//    
//    public Boolean deleteProfilAcces(OrclassProfilsAcces pa){
//        this.connectionForDataBase();
//        if (con==null) {
//            return Boolean.FALSE;
//        }
//         int i=0;
//        try {
//            Statement stmt=con.createStatement();  
//           i=  stmt.executeUpdate(" DELETE FROM ORCLASS_PROFILS_ACCES WHERE ID_PROFIL_ACCES = '"+pa.getIdProfilAcces()+"'");
//           
//        } catch (Exception e) {
//            i=-1;
//        }
//       return  i!=-1;
//    }
//    
//     public Boolean deleteUserAccess(OrclassUtilisateursAcces ua){
//        this.connectionForDataBase();
//        if (con==null) {
//            return Boolean.FALSE;
//        }
//         int i=0;
//        try {
//            Statement stmt=con.createStatement();  
//           i=  stmt.executeUpdate(" DELETE FROM ORCLASS_UTILISATEURS_ACCES WHERE ID_UTILISATEUR_ACCES = '"+ua.getIdUtilisateurAcces()+"'");
//           
//        } catch (Exception e) {
//            i=-1;
//        }
//       return  i!=-1;
//    }
//       public Boolean deleteFonctionnalitByMenuAccess(OrclassFonctionnalites f,OrclassMenus m){
//        this.connectionForDataBase();
//        if (con==null) {
//            return Boolean.FALSE;
//        }
//         int i=0;
//        try {
//            Statement stmt=con.createStatement();  
//           i=  stmt.executeUpdate(" DELETE FROM ORCLASS_MENUS_ACCES WHERE ID_FONCTIONNALITE = '"+f.getIdFonctionnalite()+"' and ID_MENU= '"+m.getIdMenu()+"'");
//           
//        } catch (Exception e) {
//            i=-1;
//        }
//       return  i!=-1;
//    }
//  public Boolean deleteMenuAccess(OrclassMenusAcces m){
//        this.connectionForDataBase();
//        if (con==null) {
//            return Boolean.FALSE;
//        }
//         int i=0;
//        try {
//            Statement stmt=con.createStatement();  
//           i=  stmt.executeUpdate(" DELETE FROM ORCLASS_MENUS_ACCES WHERE ID_MENU = '"+m.getIdMenuAcces()+"'");
//           
//        } catch (Exception e) {
//            i=-1;
//        }
//       return  i!=-1;
//    }
//  public Boolean deleteBranche(OrclassBranches br){
//        this.connectionForDataBase();
//        if (con==null) {
//            return Boolean.FALSE;
//        }
//         int i=0;
//        try {
//            Statement stmt=con.createStatement();  
//           i=  stmt.executeUpdate(" DELETE FROM ORCLASS_BRANCHES WHERE ID_BRANCHE = '"+br.getIdBranche()+"'");
//           
//        } catch (Exception e) {
//            i=-1;
//        }
//       return  i!=-1;
//  }
//  
//  public Boolean deleteCategories(OrclassCategories cat){
//        this.connectionForDataBase();
//        if (con==null) {
//            return Boolean.FALSE;
//        }
//         int i=0;
//        try {
//            Statement stmt=con.createStatement();  
//           i=  stmt.executeUpdate(" DELETE FROM ORCLASS_CATEGORIES WHERE ID_CATEGORIE = '"+cat.getIdCategorie()+"'");
//           
//        } catch (Exception e) {
//            i=-1;
//        }
//       return  i!=-1;
//  }
//  public Boolean deleteProfilAccessByProfil(OrclassProfils pa){
//        this.connectionForDataBase();
//        if (con==null) {
//            return Boolean.FALSE;
//        }
//         int i=0;
//        try {
//            Statement stmt=con.createStatement();  
//           i=  stmt.executeUpdate(" DELETE FROM ORCLASS_PROFILS_ACCES WHERE ID_PROFIL_ACCES = '"+pa.getIdProfil()+"'");
//           
//        } catch (Exception e) {
//            i=-1;
//        }
//       return  i!=-1;
//    }
//     public Boolean deleteDetailPlafondMaladieByPlafondMaladie(OrclassPlafondMaladie pm){
//        this.connectionForDataBase();
//        if (con==null) {
//            return Boolean.FALSE;
//        }
//         int i=0;
//        try {
//            Statement stmt=con.createStatement();  
//           i=  stmt.executeUpdate(" DELETE FROM ORCLASS_DETAIL_PLAF_MALA WHERE ID_PLAFOND = '"+pm.getId()+"'");
//           
//        } catch (Exception e) {
//            i=-1;
//        }
//       return  i!=-1;
//    }
//
//}
