/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sante;

/**
 *
 * @author JIATOU FRANCK
 */
public class SousCaracteristique {
    String libelle;
    String valeur;

    public SousCaracteristique() {
    }

    public SousCaracteristique(String libelle, String valeur) {
        this.libelle = libelle;
        this.valeur = valeur;
    }
    
    

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getValeur() {
        return valeur;
    }

    public void setValeur(String valeur) {
        this.valeur = valeur;
    }
    
    
}
