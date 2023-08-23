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
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

/**
 *@Table(name = "inscription", uniqueConstraints
        = @UniqueConstraint(columnNames = {"Personne_id", "Classe_id"}))
 * @author hp
 */
//@MappedSuperclass
@Entity
@Table(name = "ESPH_PROFIL_ACCESS",uniqueConstraints=@UniqueConstraint(columnNames = {"ID_ACTION", "ID_FONCTIONNALITE","ID_MOD","ID_PROFIL"}))
//@XmlRootElement ORCLASS_PROFILS_ACCES
public class OrclassProfilsAcces implements DAOEntry {

    private static final long serialVersionUID = 1L;
    @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator ="ESPH_SEQ")
    @SequenceGenerator( name="ESPH_SEQ", sequenceName="SEQUENCE1", allocationSize=1)
//    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_PROFIL_ACCES")
    private Short idProfilAcces;
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
    @ManyToOne(optional = false)
    private OrclassProfils idProfil;
    private Boolean autorisation;

    public OrclassProfilsAcces() {
    }

    public OrclassProfilsAcces(OrclassActions idAction, OrclassFonctionnalites idFonctionnalite, OrclassModules idModule, OrclassProfils idProfil, Boolean autorisation) {
        this.idAction = idAction;
        this.idFonctionnalite = idFonctionnalite;
        this.idModule = idModule;
        this.idProfil = idProfil;
        this.autorisation = autorisation;
    }

    public OrclassProfilsAcces(Short idProfilAcces) {
        this.idProfilAcces = idProfilAcces;
    }

    public Short getIdProfilAcces() {
        return idProfilAcces;
    }

    public void setIdProfilAcces(Short idProfilAcces) {
        this.idProfilAcces = idProfilAcces;
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

    public Boolean getAutorisation() {
        return autorisation;
    }

    public void setAutorisation(Boolean autorisation) {
        this.autorisation = autorisation;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idProfilAcces != null ? idProfilAcces.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OrclassProfilsAcces)) {
            return false;
        }
        OrclassProfilsAcces other = (OrclassProfilsAcces) object;
        if ((this.idProfilAcces == null && other.idProfilAcces != null) || (this.idProfilAcces != null && !this.idProfilAcces.equals(other.idProfilAcces))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modele.OrclassProfilsAcces[ idProfilAcces=" + idProfilAcces + " ]";
    }
    
}
