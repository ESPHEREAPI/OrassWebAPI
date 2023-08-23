/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package modele;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Frankjiatou
 */
@Entity
public class SeverMessagerie implements DAOEntry {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    String smtp;
    String port;
    String Descrition;

    public SeverMessagerie() {
        
    }

    public SeverMessagerie(String smtp, String port, String Descrition) {
        this.smtp = smtp;
        this.port = port;
        this.Descrition = Descrition;
    }
    

    public String getSmtp() {
        return smtp;
    }

    public void setSmtp(String smtp) {
        this.smtp = smtp;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getDescrition() {
        return Descrition;
    }

    public void setDescrition(String Descrition) {
        this.Descrition = Descrition;
    }
    
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        if (!(object instanceof SeverMessagerie)) {
            return false;
        }
        SeverMessagerie other = (SeverMessagerie) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modele.SeverMessagerie[ id=" + id + " ]";
    }
    
}
