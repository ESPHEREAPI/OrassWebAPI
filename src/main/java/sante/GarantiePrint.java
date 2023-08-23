/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sante;

import java.math.BigDecimal;

/**
 *
 * @author JIATOU FRANCK
 */
public class GarantiePrint {
    String garantie, capital, taux, pourcentage, prime, accessoire, totalTaxe, total_a_paye,prorate;
    BigDecimal totalPrime,taxePrime;
    String libelleGroup;

    public GarantiePrint() {
        
    }

    public String getGarantie() {
        return garantie;
    }

    public void setGarantie(String garantie) {
        this.garantie = garantie;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public String getTaux() {
        return taux;
    }

    public void setTaux(String taux) {
        this.taux = taux;
    }

    public String getPourcentage() {
        return pourcentage;
    }

    public void setPourcentage(String pourcentage) {
        this.pourcentage = pourcentage;
    }

    public String getPrime() {
        return prime;
    }

    public void setPrime(String prime) {
        this.prime = prime;
    }

    public String getAccessoire() {
        return accessoire;
    }

    public void setAccessoire(String accessoire) {
        this.accessoire = accessoire;
    }

    public String getTotalTaxe() {
        return totalTaxe;
    }

    public void setTotalTaxe(String totalTaxe) {
        this.totalTaxe = totalTaxe;
    }

    public String getTotal_a_paye() {
        return total_a_paye;
    }

    public void setTotal_a_paye(String total_a_paye) {
        this.total_a_paye = total_a_paye;
    }

    public String getProrate() {
        return prorate;
    }

    public void setProrate(String prorate) {
        this.prorate = prorate;
    }

    public BigDecimal getTotalPrime() {
        return totalPrime;
    }

    public void setTotalPrime(BigDecimal totalPrime) {
        this.totalPrime = totalPrime;
    }

    public BigDecimal getTaxePrime() {
        return taxePrime;
    }

    public void setTaxePrime(BigDecimal taxePrime) {
        this.taxePrime = taxePrime;
    }

    public String getLibelleGroup() {
        return libelleGroup;
    }

    public void setLibelleGroup(String libelleGroup) {
        this.libelleGroup = libelleGroup;
    }
    
    
}
