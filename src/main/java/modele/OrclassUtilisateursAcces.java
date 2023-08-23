/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author hp
 */
//@MappedSuperclass
@Entity
@Table(name = "ESPH_UTILISATEUR_ACCES")
//@XmlRootElement
public class OrclassUtilisateursAcces implements DAOEntry {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator ="ESPH_SEQ")
    @SequenceGenerator( name="ESPH_SEQ", sequenceName="SEQUENCE1", allocationSize=1)
    @Basic(optional = false)
    @NotNull

    @Column(name = "ID_UTILISATEUR_ACCES")
    private Short idUtilisateurAcces;
    @JoinColumn(name = "ID_ACTION", referencedColumnName = "ID_ACTION")
    @ManyToOne
    private OrclassActions idAction;
    @JoinColumn(name = "ID_FONCTIONNALITE", referencedColumnName = "ID_FONCTIONNALITE")
    @ManyToOne
    private OrclassFonctionnalites idFonctionnalite;
    @JoinColumn(name = "ID_MOD", referencedColumnName = "ID_MOD")
    @ManyToOne
    private OrclassModules idModule;
    @JoinColumn(name = "ID_PROFIL", referencedColumnName = "ID_PROFIL")
    @ManyToOne(optional = true)
    private OrclassProfils idProfil;
    @JoinColumn(name = "ID_UTILISATEUR", referencedColumnName = "ID_UTILISATEUR")
    @ManyToOne(optional = false)
    private OrclassUtilisateurs idUtilisateur;
    @Column(name = "autorisation")
    private Boolean autorisation_principal;
    @Column(name = "autorisation_fonctionnalite")
    private Boolean autorisation_fonctionnalite;
    @Column(name = "autorisation_action")
    private Boolean autorisation_action;

    public OrclassUtilisateursAcces() {
    }

    public OrclassUtilisateursAcces(Short idUtilisateurAcces) {
        this.idUtilisateurAcces = idUtilisateurAcces;
    }

    public Short getIdUtilisateurAcces() {
        return idUtilisateurAcces;
    }

    public void setIdUtilisateurAcces(Short idUtilisateurAcces) {
        this.idUtilisateurAcces = idUtilisateurAcces;
    }

    public OrclassActions getIdAction() {
        return idAction;
    }

    public void setIdAction(OrclassActions idAction) {
        this.idAction = idAction;
    }

    public OrclassFonctionnalites getIdFonctionnalite() {
        return idFonctionnalite;
    }

    public void setIdFonctionnalite(OrclassFonctionnalites idFonctionnalite) {
        this.idFonctionnalite = idFonctionnalite;
    }

    public OrclassModules getIdModule() {
        return idModule;
    }

    public void setIdModule(OrclassModules idModule) {
        this.idModule = idModule;
    }

    public OrclassProfils getIdProfil() {
        return idProfil;
    }

    public void setIdProfil(OrclassProfils idProfil) {
        this.idProfil = idProfil;
    }

    public OrclassUtilisateurs getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(OrclassUtilisateurs idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public Boolean getAutorisation_principal() {
        return autorisation_principal;
    }

    public void setAutorisation_principal(Boolean autorisation_principal) {
        this.autorisation_principal = autorisation_principal;
    }

    public Boolean getAutorisation_fonctionnalite() {
        return autorisation_fonctionnalite;
    }

    public void setAutorisation_fonctionnalite(Boolean autorisation_fonctionnalite) {
        this.autorisation_fonctionnalite = autorisation_fonctionnalite;
    }

    public Boolean getAutorisation_action() {
        return autorisation_action;
    }

    public void setAutorisation_action(Boolean autorisation_action) {
        this.autorisation_action = autorisation_action;
    }

   

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUtilisateurAcces != null ? idUtilisateurAcces.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OrclassUtilisateursAcces)) {
            return false;
        }
        OrclassUtilisateursAcces other = (OrclassUtilisateursAcces) object;
        if ((this.idUtilisateurAcces == null && other.idUtilisateurAcces != null) || (this.idUtilisateurAcces != null && !this.idUtilisateurAcces.equals(other.idUtilisateurAcces))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modele.OrclassUtilisateursAcces[ idUtilisateurAcces=" + idUtilisateurAcces + " ]";
    }

}
