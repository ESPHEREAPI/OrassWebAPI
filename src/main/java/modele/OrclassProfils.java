/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author hp
 */
//@MappedSuperclass
@Entity
@Table(name = "ESPH_PROFIL")
//@XmlRootElement
public class OrclassProfils implements DAOEntry {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator ="ESPH_SEQ")
    @SequenceGenerator( name="ESPH_SEQ", sequenceName="SEQUENCE1", allocationSize=1)
//    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_PROFIL")
    private Short idProfil;
    @Size(max = 32)
    @Column(name = "CODE")
    private String code;
    @Size(max = 255)
    @Column(name = "LIBELLE")
    private String libelle;
    @Column(name = "ACTIF")
    private Boolean actif;
    @Column(name = "DATE_CREATION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreation;
    @Column(name = "DATE_MODIFICATION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateModification;
    @JoinTable(name = "ORCLASS_PROFILS_UTILISATEURS", joinColumns = {
        @JoinColumn(name = "ID_PROFIL", referencedColumnName = "ID_PROFIL")}, inverseJoinColumns = {
        @JoinColumn(name = "ID_UTILISATEUR", referencedColumnName = "ID_UTILISATEUR")})
    @ManyToMany
    private List<OrclassUtilisateurs> orclassUtilisateursList;
    @OneToMany(mappedBy = "idProfil")
    private List<OrclassUtilisateursAcces> orclassUtilisateursAccesList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idProfil")
    private List<OrclassProfilsAcces> orclassProfilsAccesList;
    @JoinColumn(name = "CODESOCI", referencedColumnName = "CODESOCI")
    @ManyToOne(optional = true)
    private Societe idSociete;

    public OrclassProfils() {
    }

    public OrclassProfils(Short idProfil) {
        this.idProfil = idProfil;
    }

    public Short getIdProfil() {
        return idProfil;
    }

    public void setIdProfil(Short idProfil) {
        this.idProfil = idProfil;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public Boolean getActif() {
        return actif;
    }

    public void setActif(Boolean actif) {
        this.actif = actif;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public Date getDateModification() {
        return dateModification;
    }

    public void setDateModification(Date dateModification) {
        this.dateModification = dateModification;
    }

    @XmlTransient
    public List<OrclassUtilisateurs> getOrclassUtilisateursList() {
        return orclassUtilisateursList;
    }

    public void setOrclassUtilisateursList(List<OrclassUtilisateurs> orclassUtilisateursList) {
        this.orclassUtilisateursList = orclassUtilisateursList;
    }

    @XmlTransient
    public List<OrclassUtilisateursAcces> getOrclassUtilisateursAccesList() {
        return orclassUtilisateursAccesList;
    }

    public void setOrclassUtilisateursAccesList(List<OrclassUtilisateursAcces> orclassUtilisateursAccesList) {
        this.orclassUtilisateursAccesList = orclassUtilisateursAccesList;
    }

    @XmlTransient
    public List<OrclassProfilsAcces> getOrclassProfilsAccesList() {
        return orclassProfilsAccesList;
    }

    public void setOrclassProfilsAccesList(List<OrclassProfilsAcces> orclassProfilsAccesList) {
        this.orclassProfilsAccesList = orclassProfilsAccesList;
    }

    public Societe getIdSociete() {
        return idSociete;
    }

    public void setIdSociete(Societe idSociete) {
        this.idSociete = idSociete;
    }

   
    
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idProfil != null ? idProfil.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OrclassProfils)) {
            return false;
        }
        OrclassProfils other = (OrclassProfils) object;
        if ((this.idProfil == null && other.idProfil != null) || (this.idProfil != null && !this.idProfil.equals(other.idProfil))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modele.OrclassProfils[ idProfil=" + idProfil + " ]";
    }

}
