/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author hp
 */
//@MappedSuperclass
@Entity
@Table(name = "ESPH_SOCIETE_MODULES")
//@XmlRootElement
public class OrclassEntreprisesModules implements DAOEntry {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected OrclassEntreprisesModulesPK orclassEntreprisesModulesPK;
    @Column(name = "DATE_DEBUT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateDebut;
    @Column(name = "DATE_FIN")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateFin;
    @JoinColumn(name = "CODESOCI", referencedColumnName = "CODESOCI", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Societe societe;
    @JoinColumn(name = "ID_MOD", referencedColumnName = "ID_MOD", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private OrclassModules orclassModules;

    public OrclassEntreprisesModules() {
        societe=new Societe();
        orclassModules=new OrclassModules();
        orclassEntreprisesModulesPK=new OrclassEntreprisesModulesPK();
    }

    public OrclassEntreprisesModules(OrclassEntreprisesModulesPK orclassEntreprisesModulesPK) {
        this.orclassEntreprisesModulesPK = orclassEntreprisesModulesPK;
    }

    public OrclassEntreprisesModules(short idModule, short idEntreprise) {
        this.orclassEntreprisesModulesPK = new OrclassEntreprisesModulesPK(idModule, idEntreprise);
    }

    public OrclassEntreprisesModulesPK getOrclassEntreprisesModulesPK() {
        return orclassEntreprisesModulesPK;
    }

    public void setOrclassEntreprisesModulesPK(OrclassEntreprisesModulesPK orclassEntreprisesModulesPK) {
        this.orclassEntreprisesModulesPK = orclassEntreprisesModulesPK;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public Societe getSociete() {
        return societe;
    }

    public void setSociete(Societe societe) {
        this.societe = societe;
    }

    

    public OrclassModules getOrclassModules() {
        return orclassModules;
    }

    public void setOrclassModules(OrclassModules orclassModules) {
        this.orclassModules = orclassModules;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (orclassEntreprisesModulesPK != null ? orclassEntreprisesModulesPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OrclassEntreprisesModules)) {
            return false;
        }
        OrclassEntreprisesModules other = (OrclassEntreprisesModules) object;
        if ((this.orclassEntreprisesModulesPK == null && other.orclassEntreprisesModulesPK != null) || (this.orclassEntreprisesModulesPK != null && !this.orclassEntreprisesModulesPK.equals(other.orclassEntreprisesModulesPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modele.OrclassEntreprisesModules[ orclassEntreprisesModulesPK=" + orclassEntreprisesModulesPK + " ]";
    }
    
}
