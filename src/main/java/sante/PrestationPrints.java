/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sante;

/**
 *
 * @author hp
 */
public class PrestationPrints {

    String rubrique, libellePrestation, modeCalcul, tauxPrestation, forfait, type, plafond, bareme, mode_controle_plafond, domaine_application_plafond, montant_plafond, periode_couverture_plafond, unit_plafond, nbre_actes_plafond;
    String texte, devise;
    String libelleGroup;

    public PrestationPrints() {

    }

    public PrestationPrints(String domaine_application_plafond, String montant_plafond, String periode_couverture_plafond, String unit_plafond, String nbre_actes_plafond, String devise) {
        this.domaine_application_plafond = domaine_application_plafond;
        this.montant_plafond = montant_plafond;
        this.periode_couverture_plafond = periode_couverture_plafond;
        this.unit_plafond = unit_plafond;
        this.nbre_actes_plafond = nbre_actes_plafond;
        this.devise = devise;
    }
    
    

    public String getDevise() {
        return devise;
    }

    public void setDevise(String devise) {
        this.devise = devise;
    }
    
    

    public String getRubrique() {
        return rubrique;
    }

    public void setRubrique(String rubrique) {
        this.rubrique = rubrique;
    }

    public String getLibellePrestation() {
        return libellePrestation;
    }

    public void setLibellePrestation(String libellePrestation) {
        this.libellePrestation = libellePrestation;
    }

    public String getModeCalcul() {
        return modeCalcul;
    }

    public void setModeCalcul(String modeCalcul) {
        this.modeCalcul = modeCalcul;
    }

    public String getTauxPrestation() {
        return tauxPrestation;
    }

    public void setTauxPrestation(String tauxPrestation) {
        this.tauxPrestation = tauxPrestation;
    }

    public String getForfait() {
        return forfait;
    }

    public void setForfait(String forfait) {
        this.forfait = forfait;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPlafond() {
        return plafond;
    }

    public void setPlafond(String plafond) {
        this.plafond = plafond;
    }

    public String getBareme() {
        return bareme;
    }

    public void setBareme(String bareme) {
        this.bareme = bareme;
    }

    public String getMode_controle_plafond() {
        return mode_controle_plafond;
    }

    public void setMode_controle_plafond(String mode_controle_plafond) {
        this.mode_controle_plafond = mode_controle_plafond;
    }

    public String getDomaine_application_plafond() {
        return domaine_application_plafond;
    }

    public void setDomaine_application_plafond(String domaine_application_plafond) {
        this.domaine_application_plafond = domaine_application_plafond;
    }

    public String getMontant_plafond() {
        return montant_plafond;
    }

    public void setMontant_plafond(String montant_plafond) {
        this.montant_plafond = montant_plafond;
    }

    public String getPeriode_couverture_plafond() {
        return periode_couverture_plafond;
    }

    public void setPeriode_couverture_plafond(String periode_couverture_plafond) {
        this.periode_couverture_plafond = periode_couverture_plafond;
    }

    public String getUnit_plafond() {
        return unit_plafond;
    }

    public void setUnit_plafond(String unit_plafond) {
        this.unit_plafond = unit_plafond;
    }

    public String getNbre_actes_plafond() {
        return nbre_actes_plafond;
    }

    public void setNbre_actes_plafond(String nbre_actes_plafond) {
        this.nbre_actes_plafond = nbre_actes_plafond;
    }

    public String getTexte() {
   
        return texte;
    }

    public void setTexte(String texte) {
        this.texte = texte;
    }

    public String getLibelleGroup() {
        return libelleGroup;
    }

    public void setLibelleGroup(String libelleGroup) {
        this.libelleGroup = libelleGroup;
    }

}
