/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modele;

import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author JIATOU FRANCK
 */
@Entity
@Table(name = "PAYS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pays.findAll", query = "SELECT p FROM Pays p"),
    @NamedQuery(name = "Pays.findByCodepays", query = "SELECT p FROM Pays p WHERE p.codepays = :codepays"),
    @NamedQuery(name = "Pays.findByLibepays", query = "SELECT p FROM Pays p WHERE p.libepays = :libepays"),
    @NamedQuery(name = "Pays.findByNumeLot", query = "SELECT p FROM Pays p WHERE p.numeLot = :numeLot"),
    @NamedQuery(name = "Pays.findByLibenati", query = "SELECT p FROM Pays p WHERE p.libenati = :libenati")})
public class Pays implements DAOEntry {

    private static final long serialVersionUID = 1L;
     public static final String FIND_BY_CODEPAYS = "Pays.findByCodepays";
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "CODEPAYS")
    private String codepays;
    @Size(max = 100)
    @Column(name = "LIBEPAYS")
    private String libepays;
    @Column(name = "NUME_LOT")
    private BigInteger numeLot;
    @Size(max = 60)
    @Column(name = "LIBENATI")
    private String libenati;

    public Pays() {
    }

    public Pays(String codepays) {
        this.codepays = codepays;
    }

    public String getCodepays() {
        return codepays;
    }

    public void setCodepays(String codepays) {
        this.codepays = codepays;
    }

    public String getLibepays() {
        return libepays;
    }

    public void setLibepays(String libepays) {
        this.libepays = libepays;
    }

    public BigInteger getNumeLot() {
        return numeLot;
    }

    public void setNumeLot(BigInteger numeLot) {
        this.numeLot = numeLot;
    }

    public String getLibenati() {
        return libenati;
    }

    public void setLibenati(String libenati) {
        this.libenati = libenati;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codepays != null ? codepays.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pays)) {
            return false;
        }
        Pays other = (Pays) object;
        if ((this.codepays == null && other.codepays != null) || (this.codepays != null && !this.codepays.equals(other.codepays))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modele.Pays[ codepays=" + codepays + " ]";
    }
    
}
