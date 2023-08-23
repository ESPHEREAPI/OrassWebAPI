/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
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
@Table(name = "ESPH_MENU")
@NamedQueries({
    @NamedQuery(name = OrclassMenus.FIND_BY_CODE_MENU, query = "SELECT m FROM OrclassMenus m WHERE m.code = :code")
    ,
    @NamedQuery(name = OrclassMenus.FIND_BY_MODULEID_MENU, query = "SELECT m FROM OrclassMenus m WHERE m.idModule.idModule = :moduleid")})
public class OrclassMenus implements DAOEntry {

    public static final String FIND_ALL = "OrclassMenus.findAll";
    public static final String FIND_BY_CODE_MENU = "OrclassMenus.findByCodeMenus";
    public static final String FIND_BY_MODULEID_MENU = "OrclassMenus.findByModuleidMenus";
    private static final long serialVersionUID = 1L;
    @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator ="ESPH_SEQ")
    @SequenceGenerator( name="ESPH_SEQ", sequenceName="SEQUENCE1", allocationSize=1)
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_MENU")
    private Short idMenu;
    @Size(max = 255)
    @Column(name = "CODE",unique = true)
    private String code;
    @Size(max = 255)
    @Column(name = "LIBELLE")
    private String libelle;
    @Size(max = 255)
    @Column(name = "HIERACHIE")
    private String hierachie;
    @Column(name = "DATE_CREATION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreation;
    @Column(name = "DATE_MODIFICATION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateModification;
    @JoinColumn(name = "ID_MOD", referencedColumnName = "ID_MOD")
    @ManyToOne(optional = false)
    private OrclassModules idModule;
    @Column(name = "etat")
    private Boolean etat = Boolean.FALSE;//savoir si c le menu Etat
    private String chemin;

    public OrclassMenus() {

    }

    public OrclassMenus(String code, String libelle, boolean etat, String chemin, OrclassModules moduleid,String hierachie ) {
        this.code = code;
        this.libelle = libelle;
        this.chemin = chemin;
        this.idModule = moduleid;
        this.etat = etat;
        this.dateCreation=new Date();
        this.setHierachie(hierachie);
    }

    public OrclassMenus(Short idMenu) {
        this.idMenu = idMenu;
    }

    public Short getIdMenu() {
        return idMenu;
    }

    public void setIdMenu(Short idMenu) {
        this.idMenu = idMenu;
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

    public String getHierachie() {
        return hierachie;
    }

    public void setHierachie(String hierachie) {
        this.hierachie = hierachie;
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

    public OrclassModules getIdModule() {
        return idModule;
    }

    public void setIdModule(OrclassModules idModule) {
        this.idModule = idModule;
    }

    public Boolean getEtat() {
        return etat;
    }

    public void setEtat(Boolean etat) {
        this.etat = etat;
    }

    public String getChemin() {
        return chemin;
    }

    public void setChemin(String chemin) {
        this.chemin = chemin;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMenu != null ? idMenu.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OrclassMenus)) {
            return false;
        }
        OrclassMenus other = (OrclassMenus) object;
        if ((this.idMenu == null && other.idMenu != null) || (this.idMenu != null && !this.idMenu.equals(other.idMenu))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modele.OrclassMenus[ id=" + idMenu + " ]";
    }

}
