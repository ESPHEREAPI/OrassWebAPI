/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;


import java.io.Serializable;
import java.math.BigInteger;
import java.util.Collection;



/**
 *
 * @author jiatoufrank
 */
public class FonctionTable implements Serializable{

// private Collection<Mention> listMention = new ArrayList<Mention>();
private static BigInteger champMin[];//represente les valeurs du champ Min dans la table baremeImpot
private static BigInteger champMax[];//represente les valeurs du champ max dans la table baremeImpot
// int taille=0;
    public FonctionTable() {
        
    }
    
      
         //initialisation des tables

//    public  void initTable(Double[] t, int longueur) {
//        for (int i = 0; i < longueur; i++) {
//            t[i] = 0.0;
//        }
//
//    }
    
    

//tri d un tableau
    public static void triInsertion(BigInteger[] t) {
        for (int j = 1; j < t.length; j++) {
            BigInteger x = t[j];
            int i = j - 1;
            if (t[i] != null && x != null) {
                while (i >= 0 && t[i].intValue() > x.intValue()) {
                    t[i + 1] = t[i];
                    i = i - 1;
                }
                t[i + 1] = x;
            }
            // x doit être inséré dans le tableau ordonné 0..j-1

        }
    }

//     
    
  
    
     public static void localMemory(int taille) {
        champMin = new BigInteger[taille];
        champMax = new BigInteger[taille];
        
        
    }

    public static BigInteger[] getChampMin() {
       return  champMin;
    }

    public static void setChampMin(BigInteger[] champMin) {
        FonctionTable.champMin = champMin;
    }

    public static BigInteger[] getChampMax() {
        return champMax;
    }

    public static void setChampMax(BigInteger[] champMax) {
        FonctionTable.champMax = champMax;
    }
     
    
    }