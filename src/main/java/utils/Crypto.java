package utils;

import java.security.MessageDigest;

/**
 *
 * @author fabrice
 */
public class Crypto {

    public Crypto() {
    }
    
    
    
public static String sha256(String base) {
    try{
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(base.getBytes("UTF-8"));
        StringBuilder hexString = new StringBuilder();

        for (int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(0xff & hash[i]);
            if(hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }

        return hexString.toString();
    } catch(Exception ex){
       throw new RuntimeException(ex);
    }
}
    

    public static String cryptoMD5(String wordToHash){
        
         StringBuilder hexString = new StringBuilder();
        try {
            byte hash[] = MessageDigest.getInstance("MD5").digest(wordToHash.getBytes("UTF-8"));
         
            for ( int i = 0; i < hash.length; ++i ) {
                    String hex = Integer.toHexString(hash[i]);
                    if ( hex.length() == 1 ) {
                        hexString.append('0');
                        hexString.append(hex.charAt(hex.length()-1));
                    } else {
                        hexString.append(hex.substring(hex.length()-2));
                    }
                }
            return hexString.toString();
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    public static void main(String[] args){
        
        String texte="admin";
        System.out.println(Crypto.sha256(texte));
         //System.out.println(new Crypto().cryptoMD5(texte));
        
    }
    
}
