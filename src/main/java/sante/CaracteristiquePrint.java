/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sante;

import java.util.Comparator;

/**
 *
 * @author hp
 */
public class CaracteristiquePrint {

    String libelle, valeur;
    String libelleGroup;
    String unite;

    public CaracteristiquePrint() {
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

    public static Comparator<CaracteristiquePrint> ByLibelleSC = new Comparator<CaracteristiquePrint>() {
        public int compare(CaracteristiquePrint o1, CaracteristiquePrint o2) {
            return o1.libelle.compareTo(o2.libelle);
        }
    };

    public String getLibelleGroup() {
        return libelleGroup;
    }

    public void setLibelleGroup(String libelleGroup) {
        this.libelleGroup = libelleGroup;
    }

    public String getUnite() {
        return unite;
    }

    public void setUnite(String unite) {
        this.unite = unite;
    }

}
