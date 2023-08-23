/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import java.io.Serializable;
import java.util.Date;
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

/**
 *
 * @author hp
 */
@Entity
@Table(name = "ESPH_CONNECTIONLOGGER")
@NamedQueries({
    @NamedQuery(name = ConnectionLogger.FIND_BY_LAST_CONNEXION, query = "SELECT c FROM ConnectionLogger c ORDER BY c.id")
})
public class ConnectionLogger implements DAOEntry {

    private static final long serialVersionUID = 1L;
    public static final String FIND_BY_LAST_CONNEXION = "ConnectionLogger.getLastConnexion";
    @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator ="ESPH_SEQ")
    @SequenceGenerator( name="ESPH_SEQ", sequenceName="SEQUENCE1", allocationSize=1)
    private Short id;

    

    @Temporal(TemporalType.TIMESTAMP)
    private Date dateConnexion;

    private Boolean on_line = Boolean.TRUE;
    
     @JoinColumn(name = "ID_UTILISATEUR", referencedColumnName = "ID_UTILISATEUR")
    @ManyToOne(optional = false)
    private OrclassUtilisateurs idUtilisateur;

    public ConnectionLogger() {

    }

    public Short getId() {
        return id;
    }

    public void setId(Short id) {
        this.id = id;
    }

    

    public Date getDateConnexion() {
        return dateConnexion;
    }

    public void setDateConnexion(Date dateConnexion) {
        this.dateConnexion = dateConnexion;
    }

  

    public Boolean getOn_line() {
        return on_line;
    }

    public void setOn_line(Boolean on_line) {
        this.on_line = on_line;
    }

    public OrclassUtilisateurs getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(OrclassUtilisateurs idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ConnectionLogger)) {
            return false;
        }
        ConnectionLogger other = (ConnectionLogger) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modele.ConnectionLogger[ id=" + id + " ]";
    }

}
