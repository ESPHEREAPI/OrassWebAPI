/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author hp
 */
@Entity
@Table(name = "ESPH_PROFIL_MODULES")
public class OrclassProfilModule implements DAOEntry {

    private static final long serialVersionUID = 1L;
    @Id
    
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator ="ESPH_SEQ")
    @SequenceGenerator( name="ESPH_SEQ", sequenceName="SEQUENCE1", allocationSize=1)
    private Long id;
    @JoinColumn(name = "ID_MOD", referencedColumnName = "ID_MOD")
    @ManyToOne
    private OrclassModules idModule;
    @JoinColumn(name = "ID_PROFIL", referencedColumnName = "ID_PROFIL")
    @ManyToOne(optional = false)
    private OrclassProfils idProfil;

    public OrclassProfilModule() {
        
    }

    
    public OrclassProfilModule(OrclassModules idModule, OrclassProfils idProfil) {
        this.idModule = idModule;
        this.idProfil = idProfil;
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
        if (!(object instanceof OrclassProfilModule)) {
            return false;
        }
        OrclassProfilModule other = (OrclassProfilModule) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modele.OrclassProfilModule[ id=" + id + " ]";
    }

}
