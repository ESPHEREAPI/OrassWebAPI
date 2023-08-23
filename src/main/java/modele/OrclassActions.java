/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author hp
 */
//@MappedSuperclass
@Entity
@Table(name = "ESPH_ACTION")
//@NamedQueries({
//    @NamedQuery(name = OrclassActions.FIND_BY_CODE, query = "SELECT a FROM OrclassActions a WHERE a.code = :code")
//    ,
//    @NamedQuery(name = OrclassActions.FIND_BY_FonctionaliteId, query = "SELECT a FROM OrclassActions a WHERE a.idFonctionnalite.idFonctionnalite = :idFonctionnalite")})
public class OrclassActions implements DAOEntry {

//    public static final String FIND_BY_CODE = "OrclassActions.findByCode";
//    public static final String FIND_BY_FonctionaliteId = "OrclassActions.findByModuleid";
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ESPH_SEQ")
    @SequenceGenerator(name = "ESPH_SEQ", sequenceName = "SEQUENCE1", allocationSize = 1)
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_ACTION")
    private Long idAction;
    @Size(max = 500)
    @Column(name = "CODE")
    private String code;
    @Size(max = 500)
    @Column(name = "LIBELLE")
    private String libelle;
    @Column(name = "DATE_CREATION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreation;
    @Column(name = "DATE_MODIFICATION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateModification;
    @OneToMany(mappedBy = "idAction")
    private List<OrclassUtilisateursAcces> orclassUtilisateursAccesList;
    @OneToMany(mappedBy = "idAction")
    private List<OrclassMenusAcces> orclassMenusAccesList;
    @JoinColumn(name = "ID_FONCTIONNALITE", referencedColumnName = "ID_FONCTIONNALITE")
    @ManyToOne(optional = false)
    private OrclassFonctionnalites idFonctionnalite;
    @OneToMany(mappedBy = "idAction")
    private List<OrclassProfilsAcces> orclassProfilsAccesList;

    public OrclassActions() {

    }

    public OrclassActions(String code, String libelle, Date dateCreation, OrclassFonctionnalites idFonctionnalite) {
        this.code = code;
        this.libelle = libelle;
        this.dateCreation = dateCreation;
        this.idFonctionnalite = idFonctionnalite;
    }

    public OrclassActions(Long idAction) {
        this.idAction = idAction;
    }

    public Long getIdAction() {
        return idAction;
    }

    public void setIdAction(Long idAction) {
        this.idAction = idAction;
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

    public List<OrclassUtilisateursAcces> getOrclassUtilisateursAccesList() {
        return orclassUtilisateursAccesList;
    }

    public void setOrclassUtilisateursAccesList(List<OrclassUtilisateursAcces> orclassUtilisateursAccesList) {
        this.orclassUtilisateursAccesList = orclassUtilisateursAccesList;
    }

    public List<OrclassMenusAcces> getOrclassMenusAccesList() {
        return orclassMenusAccesList;
    }

    public void setOrclassMenusAccesList(List<OrclassMenusAcces> orclassMenusAccesList) {
        this.orclassMenusAccesList = orclassMenusAccesList;
    }

    public OrclassFonctionnalites getIdFonctionnalite() {
        return idFonctionnalite;
    }

    public void setIdFonctionnalite(OrclassFonctionnalites idFonctionnalite) {
        this.idFonctionnalite = idFonctionnalite;
    }

//    @XmlTransient
    public List<OrclassProfilsAcces> getOrclassProfilsAccesList() {
        return orclassProfilsAccesList;
    }

    public void setOrclassProfilsAccesList(List<OrclassProfilsAcces> orclassProfilsAccesList) {
        this.orclassProfilsAccesList = orclassProfilsAccesList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAction != null ? idAction.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OrclassActions)) {
            return false;
        }
        OrclassActions other = (OrclassActions) object;
        if ((this.idAction == null && other.idAction != null) || (this.idAction != null && !this.idAction.equals(other.idAction))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Actions[ id =" + idAction + " ]";
    }

}
