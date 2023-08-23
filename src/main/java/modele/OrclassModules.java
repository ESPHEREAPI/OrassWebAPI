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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author hp
 */
//@MappedSuperclass
@Entity
//@Table(name = "ORCLASS_MODULE")
@Table(name = "ESPH_MODULES")

//@XmlRootElement
@NamedQueries({
    @NamedQuery(name = OrclassModules.FIND_BY_CODE, query = "SELECT m FROM OrclassModules m WHERE m.code = :code"),
    @NamedQuery(name = OrclassModules.FIND_BY_DESCRIPTION, query = "SELECT m FROM OrclassModules m WHERE m.libelle = :description")
})
public class OrclassModules implements DAOEntry {

    public static final String FIND_BY_CODE = "Modulesecurite.findByCode";
    public static final String FIND_BY_DESCRIPTION = "Modulesecurite.findByDescription";
    private static final long serialVersionUID = 1L;
    @Id
     @GeneratedValue(strategy = GenerationType.SEQUENCE, generator ="ESPH_SEQ")
    @SequenceGenerator( name="ESPH_SEQ", sequenceName="SEQUENCE1", allocationSize=1)
    
//    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_MOD")
    private Short idModule;
    @Size(max = 32)
    @Column(name = "CODE_MOD")
    private String code;
    @Size(max = 255)
    @Column(name = "LIBELLE_MOD")
    private String libelle;
    @Size(max = 255)
    @Column(name = "CHEMIN_MOD")
    private String chemin;
    @Column(name = "DATE_CREATION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreation;
    @Column(name = "DATE_MODIFICATION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateModification;
    @OneToMany(mappedBy = "idModule")
    private List<OrclassUtilisateursAcces> orclassUtilisateursAccesList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "orclassModules")
    private List<OrclassEntreprisesModules> orclassEntreprisesModulesList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idModule")
    private List<OrclassMenus> orclassMenusList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idModule")
    private List<OrclassFonctionnalites> orclassFonctionnalitesList;
    @OneToMany(mappedBy = "idModule")
    private List<OrclassProfilsAcces> orclassProfilsAccesList;
    @Transient
    int indiceTableau = -1;

    public OrclassModules() {
       this.idModule=Short.parseShort("1"); 
    }

    public OrclassModules(Short idModule) {
        this.idModule = idModule;
    }

    public OrclassModules(String code, String libelle) {
        this.code = code;
        this.libelle = libelle;
        this.setDateCreation(new Date());
    }

    public Short getIdModule() {
        return idModule;
    }

    public void setIdModule(Short idModule) {
        this.idModule = idModule;
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

    public String getChemin() {
        return chemin;
    }

    public void setChemin(String chemin) {
        this.chemin = chemin;
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
    public List<OrclassEntreprisesModules> getOrclassEntreprisesModulesList() {
        return orclassEntreprisesModulesList;
    }

    public void setOrclassEntreprisesModulesList(List<OrclassEntreprisesModules> orclassEntreprisesModulesList) {
        this.orclassEntreprisesModulesList = orclassEntreprisesModulesList;
    }

    @XmlTransient
    public List<OrclassMenus> getOrclassMenusList() {
        return orclassMenusList;
    }

    public void setOrclassMenusList(List<OrclassMenus> orclassMenusList) {
        this.orclassMenusList = orclassMenusList;
    }

    @XmlTransient
    public List<OrclassFonctionnalites> getOrclassFonctionnalitesList() {
        return orclassFonctionnalitesList;
    }

    public void setOrclassFonctionnalitesList(List<OrclassFonctionnalites> orclassFonctionnalitesList) {
        this.orclassFonctionnalitesList = orclassFonctionnalitesList;
    }

    @XmlTransient
    public List<OrclassProfilsAcces> getOrclassProfilsAccesList() {
        return orclassProfilsAccesList;
    }

    public void setOrclassProfilsAccesList(List<OrclassProfilsAcces> orclassProfilsAccesList) {
        this.orclassProfilsAccesList = orclassProfilsAccesList;
    }

    public int getIndiceTableau() {
        return indiceTableau;
    }

    public void setIndiceTableau(int indiceTableau) {
        this.indiceTableau = indiceTableau;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idModule != null ? idModule.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OrclassModules)) {
            return false;
        }
        OrclassModules other = (OrclassModules) object;
        if ((this.idModule == null && other.idModule != null) || (this.idModule != null && !this.idModule.equals(other.idModule))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modules[ id =" + idModule + " ]";
    }

}
