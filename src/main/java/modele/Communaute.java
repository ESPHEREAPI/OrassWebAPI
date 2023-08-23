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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author JIATOU FRANCK
 */
@Entity
@Table(name = "COMMUNAUTE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Communaute.findAll", query = "SELECT c FROM Communaute c"),
    @NamedQuery(name = "Communaute.findByCodecomm", query = "SELECT c FROM Communaute c WHERE c.codecomm = :codecomm"),
    @NamedQuery(name = "Communaute.findByLibecomm", query = "SELECT c FROM Communaute c WHERE c.libecomm = :libecomm"),
    @NamedQuery(name = "Communaute.findByNumeLot", query = "SELECT c FROM Communaute c WHERE c.numeLot = :numeLot"),
    @NamedQuery(name = "Communaute.findByFamicomm", query = "SELECT c FROM Communaute c WHERE c.famicomm = :famicomm")})
public class Communaute implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "CODECOMM")
    private String codecomm;
    @Size(max = 50)
    @Column(name = "LIBECOMM")
    private String libecomm;
    @Column(name = "NUME_LOT")
    private BigInteger numeLot;
    @Size(max = 10)
    @Column(name = "FAMICOMM")
    private String famicomm;
    @OneToMany(mappedBy = "codecomm")
    private List<Region> regionList;

    public Communaute() {
    }

    public Communaute(String codecomm) {
        this.codecomm = codecomm;
    }

    public String getCodecomm() {
        return codecomm;
    }

    public void setCodecomm(String codecomm) {
        this.codecomm = codecomm;
    }

    public String getLibecomm() {
        return libecomm;
    }

    public void setLibecomm(String libecomm) {
        this.libecomm = libecomm;
    }

    public BigInteger getNumeLot() {
        return numeLot;
    }

    public void setNumeLot(BigInteger numeLot) {
        this.numeLot = numeLot;
    }

    public String getFamicomm() {
        return famicomm;
    }

    public void setFamicomm(String famicomm) {
        this.famicomm = famicomm;
    }

    @XmlTransient
    public List<Region> getRegionList() {
        return regionList;
    }

    public void setRegionList(List<Region> regionList) {
        this.regionList = regionList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codecomm != null ? codecomm.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Communaute)) {
            return false;
        }
        Communaute other = (Communaute) object;
        if ((this.codecomm == null && other.codecomm != null) || (this.codecomm != null && !this.codecomm.equals(other.codecomm))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modele.Communaute[ codecomm=" + codecomm + " ]";
    }
    
}
