/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modele;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author JIATOU FRANCK
 */
@Entity
@Table(name = "ORCLASS_MODUL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OrclassModule.findAll", query = "SELECT o FROM OrclassModule o"),
    @NamedQuery(name = "OrclassModule.findByIdModule", query = "SELECT o FROM OrclassModule o WHERE o.idModule = :idModule"),
    @NamedQuery(name = "OrclassModule.findByChemin", query = "SELECT o FROM OrclassModule o WHERE o.chemin = :chemin"),
    @NamedQuery(name = "OrclassModule.findByCode", query = "SELECT o FROM OrclassModule o WHERE o.code = :code"),
    @NamedQuery(name = "OrclassModule.findByDateCreation", query = "SELECT o FROM OrclassModule o WHERE o.dateCreation = :dateCreation"),
    @NamedQuery(name = "OrclassModule.findByDateModification", query = "SELECT o FROM OrclassModule o WHERE o.dateModification = :dateModification"),
    @NamedQuery(name = "OrclassModule.findByLibelle", query = "SELECT o FROM OrclassModule o WHERE o.libelle = :libelle")})
public class OrclassModule implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
     @GeneratedValue(strategy = GenerationType.SEQUENCE, generator ="ESPH_SEQ")
    @SequenceGenerator( name="ESPH_SEQ", sequenceName="SEQUENCE1", allocationSize=1)
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_MODULE")
    private Integer idModule;
    @Size(max = 255)
    @Column(name = "CHEMIN")
    private String chemin;
    @Size(max = 32)
    @Column(name = "CODE")
    private String code;
    @Column(name = "DATE_CREATION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreation;
    @Column(name = "DATE_MODIFICATION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateModification;
    @Size(max = 255)
    @Column(name = "LIBELLE")
    private String libelle;

    public OrclassModule() {
    }

    public OrclassModule(Integer idModule) {
        this.idModule = idModule;
    }

    public Integer getIdModule() {
        return idModule;
    }

    public void setIdModule(Integer idModule) {
        this.idModule = idModule;
    }

    public String getChemin() {
        return chemin;
    }

    public void setChemin(String chemin) {
        this.chemin = chemin;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
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
        if (!(object instanceof OrclassModule)) {
            return false;
        }
        OrclassModule other = (OrclassModule) object;
        if ((this.idModule == null && other.idModule != null) || (this.idModule != null && !this.idModule.equals(other.idModule))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modele.OrclassModule[ idModule=" + idModule + " ]";
    }
    
}
