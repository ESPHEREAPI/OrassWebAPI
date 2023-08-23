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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
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
@Table(name = "ESPH_FONCTIONNALITE")
@NamedQueries({
    @NamedQuery(name = OrclassFonctionnalites.FIND_BY_CODE, query = "SELECT f FROM OrclassFonctionnalites f WHERE f.code = :code"),
    @NamedQuery(name = OrclassFonctionnalites.FIND_BY_MODULEID, query = "SELECT f FROM OrclassFonctionnalites f WHERE f.idModule.idModule = :moduleid")})
//@XmlRootElement
public class OrclassFonctionnalites implements DAOEntry {

    public static final String FIND_ALL = "Fonctionnalite.findAll";
    public static final String FIND_BY_CODE = "Fonctionnalite.findByCode";
    public static final String FIND_BY_MODULEID = "Fonctionnalite.findByModuleid";
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ESPH_SEQ")
    @SequenceGenerator(name = "ESPH_SEQ", sequenceName = "SEQUENCE1", allocationSize = 1)
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_FONCTIONNALITE")
    private Short idFonctionnalite;
    @Size(max = 255)
    @Column(name = "CODE")
    private String code;
    @Size(max = 255)
    @Column(name = "LIBELLE")
    private String libelle;
    @Column(name = "DATE_CREATION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreation;
    @Column(name = "DATE_MODIFICATION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateModification;
    @OneToMany(mappedBy = "idFonctionnalite")
    private List<OrclassUtilisateursAcces> orclassUtilisateursAccesList;
    @OneToMany(mappedBy = "idFonctionnalite")
    private List<OrclassMenusAcces> orclassMenusAccesList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idFonctionnalite")
    private List<OrclassActions> orclassActionsList;
    @JoinColumn(name = "ID_MOD", referencedColumnName = "ID_MOD")
    @ManyToOne(optional = false)
    private OrclassModules idModule;
    @OneToMany(mappedBy = "idFonctionnalite")
    private List<OrclassProfilsAcces> orclassProfilsAccesList;

    public OrclassFonctionnalites() {

    }

    public OrclassFonctionnalites(String code, String libelle, Date dateCreation, OrclassModules modules) {
        this.code = code;
        this.libelle = libelle;
        this.dateCreation = dateCreation;
        this.idModule = modules;
    }

    public OrclassFonctionnalites(Short idFonctionnalite) {
        this.idFonctionnalite = idFonctionnalite;
    }

    public Short getIdFonctionnalite() {
        return idFonctionnalite;
    }

    public void setIdFonctionnalite(Short idFonctionnalite) {
        this.idFonctionnalite = idFonctionnalite;
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
    public List<OrclassUtilisateursAcces> getOrclassUtilisateursAccesList() {
        return orclassUtilisateursAccesList;
    }

    public void setOrclassUtilisateursAccesList(List<OrclassUtilisateursAcces> orclassUtilisateursAccesList) {
        this.orclassUtilisateursAccesList = orclassUtilisateursAccesList;
    }

    @XmlTransient
    public List<OrclassMenusAcces> getOrclassMenusAccesList() {
        return orclassMenusAccesList;
    }

    public void setOrclassMenusAccesList(List<OrclassMenusAcces> orclassMenusAccesList) {
        this.orclassMenusAccesList = orclassMenusAccesList;
    }

    @XmlTransient
    public List<OrclassActions> getOrclassActionsList() {
        return orclassActionsList;
    }

    public void setOrclassActionsList(List<OrclassActions> orclassActionsList) {
        this.orclassActionsList = orclassActionsList;
    }

    public OrclassModules getIdModule() {
        return idModule;
    }

    public void setIdModule(OrclassModules idModule) {
        this.idModule = idModule;
    }

    @XmlTransient
    public List<OrclassProfilsAcces> getOrclassProfilsAccesList() {
        return orclassProfilsAccesList;
    }

    public void setOrclassProfilsAccesList(List<OrclassProfilsAcces> orclassProfilsAccesList) {
        this.orclassProfilsAccesList = orclassProfilsAccesList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idFonctionnalite != null ? idFonctionnalite.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OrclassFonctionnalites)) {
            return false;
        }
        OrclassFonctionnalites other = (OrclassFonctionnalites) object;
        if ((this.idFonctionnalite == null && other.idFonctionnalite != null) || (this.idFonctionnalite != null && !this.idFonctionnalite.equals(other.idFonctionnalite))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Fonctionnalites[ id =" + idFonctionnalite + " ]";
    }

}
