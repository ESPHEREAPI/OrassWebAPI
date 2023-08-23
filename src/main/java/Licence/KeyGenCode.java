/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Licence;

import java.util.Random;

/**
 *
 * @author JIATOU FRANCK
 */
public class KeyGenCode {
   private static String CARS="ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static int TAILLE_CLE = 6;
    private static int TAILLE_PASS = 12;
    
       public static String gen() {
        // Clé de départ
//        int nbre=0;
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        StringBuilder sb1 = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
//        if ("".equals(valeur) || valeur == null) {
            for (int j = 0; j < TAILLE_CLE; j++) {

                sb.append(CARS.charAt(random.nextInt(TAILLE_CLE)));
            }
            return sb.toString();


    }
        public static String genPassWord() {
        // Clé de départ
//        int nbre=0;
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        StringBuilder sb1 = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
//        if ("".equals(valeur) || valeur == null) {
            for (int j = 0; j < TAILLE_PASS; j++) {

                sb.append(CARS.charAt(random.nextInt(TAILLE_PASS)));
            }
            return sb.toString();


    }
    
}
