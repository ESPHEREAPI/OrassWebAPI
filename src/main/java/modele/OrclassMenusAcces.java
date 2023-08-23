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
import javax.validation.constraints.NotNull;

/**
 *
 * @author hp
 */
//@MappedSuperclass
@Entity
@Table(name = "ESPH_MENU_ACCES")
//@XmlRootElement
public class OrclassMenusAcces implements DAOEntry {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator ="ESPH_SEQ")
    @SequenceGenerator( name="ESPH_SEQ", sequenceName="SEQUENCE1", allocationSize=1)
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_MENU_ACCES")
    private Short idMenuAcces;
//    @Basic(optional = false)
//    @NotNull
//    @Column(name = "ID_MENU")
//    private short idMenu;
    @JoinColumn(name = "ID_MENU", referencedColumnName = "ID_MENU")
    @ManyToOne
    private OrclassMenus idMenu;
    @JoinColumn(name = "ID_ACTION", referencedColumnName = "ID_ACTION")
    @ManyToOne
    private OrclassActions idAction;
    @JoinColumn(name = "ID_FONCTIONNALITE", referencedColumnName = "ID_FONCTIONNALITE")
    @ManyToOne
    private OrclassFonctionnalites idFonctionnalite;

    public OrclassMenusAcces() {
        idAction = new OrclassActions();
        idFonctionnalite = new OrclassFonctionnalites();
        idMenu = new OrclassMenus();
    }

    public OrclassMenusAcces(Short idMenuAcces) {
        this.idMenuAcces = idMenuAcces;
    }

    public Short getIdMenuAcces() {
        return idMenuAcces;
    }

    public void setIdMenuAcces(Short idMenuAcces) {
        this.idMenuAcces = idMenuAcces;
    }

    public OrclassMenus getIdMenu() {
        return idMenu;
    }

    public void setIdMenu(OrclassMenus idMenu) {
        this.idMenu = idMenu;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMenuAcces != null ? idMenuAcces.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OrclassMenusAcces)) {
            return false;
        }
        OrclassMenusAcces other = (OrclassMenusAcces) object;
        if ((this.idMenuAcces == null && other.idMenuAcces != null) || (this.idMenuAcces != null && !this.idMenuAcces.equals(other.idMenuAcces))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modele.OrclassMenusAcces[ idMenuAcces=" + idMenuAcces + " ]";
    }

}
