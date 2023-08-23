/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modele;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author JIATOU FRANCK
 */
@Entity
@Table(name = "VILLE")

@NamedQueries({
    @NamedQuery(name = "Ville.findAll", query = "SELECT v FROM Ville v"),
    @NamedQuery(name = "Ville.findByCodevill", query = "SELECT v FROM Ville v WHERE v.codevill = :codevill"),
    @NamedQuery(name = "Ville.findByLibevill", query = "SELECT v FROM Ville v WHERE v.libevill = :libevill"),
    @NamedQuery(name = "Ville.findByNumeLot", query = "SELECT v FROM Ville v WHERE v.numeLot = :numeLot")})
public class Ville implements DAOEntry {

    private static final long serialVersionUID = 1L;
       public static final String FIND_BY_CODEVILLE =  "Ville.findByCodevill";
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODEVILL")
    private Integer codevill;
    @Size(max = 30)
    @Column(name = "LIBEVILL")
    private String libevill;
    @Column(name = "NUME_LOT")
    private BigInteger numeLot;
    @OneToMany(mappedBy = "villsoci")
    private List<Societe> societeList;
    @JoinColumn(name = "CODEREGI", referencedColumnName = "CODEREGI")
    @ManyToOne
    private Region coderegi;

    public Ville() {
    }

    public Ville(Integer codevill) {
        this.codevill = codevill;
    }

    public Integer getCodevill() {
        return codevill;
    }

    public void setCodevill(Integer codevill) {
        this.codevill = codevill;
    }

    public String getLibevill() {
        return libevill;
    }

    public void setLibevill(String libevill) {
        this.libevill = libevill;
    }

    public BigInteger getNumeLot() {
        return numeLot;
    }

    public void setNumeLot(BigInteger numeLot) {
        this.numeLot = numeLot;
    }

    @XmlTransient
    public List<Societe> getSocieteList() {
        return societeList;
    }

    public void setSocieteList(List<Societe> societeList) {
        this.societeList = societeList;
    }

    public Region getCoderegi() {
        return coderegi;
    }

    public void setCoderegi(Region coderegi) {
        this.coderegi = coderegi;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codevill != null ? codevill.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ville)) {
            return false;
        }
        Ville other = (Ville) object;
        if ((this.codevill == null && other.codevill != null) || (this.codevill != null && !this.codevill.equals(other.codevill))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modele.Ville[ codevill=" + codevill + " ]";
    }
    
}
