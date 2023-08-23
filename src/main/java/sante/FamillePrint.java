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
public class FamillePrint {

    String membre, lien_parente, naissance, entre_le, sortie_le,sexe,adherent;
    String libelleGroup;

    public FamillePrint() {

    }

    public FamillePrint(String adherent) {
        this.adherent = adherent;
    }
    
    

    public String getMembre() {
        return membre;
    }

    public void setMembre(String membre) {
        this.membre = membre;
    }

    public String getLien_parente() {
        return lien_parente;
    }

    public void setLien_parente(String lien_parente) {
        this.lien_parente = lien_parente;
    }

    

    public String getNaissance() {
        return naissance;
    }

    public void setNaissance(String naissance) {
        this.naissance = naissance;
    }

    public String getEntre_le() {
        return entre_le;
    }

    public void setEntre_le(String entre_le) {
        this.entre_le = entre_le;
    }

    public String getSortie_le() {
        return sortie_le;
    }

    public void setSortie_le(String sortie_le) {
        this.sortie_le = sortie_le;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public String getAdherent() {
        return adherent;
    }

    public void setAdherent(String adherent) {
        this.adherent = adherent;
    }

    public String getLibelleGroup() {
        return libelleGroup;
    }

    public void setLibelleGroup(String libelleGroup) {
        this.libelleGroup = libelleGroup;
    }
    
    
}
