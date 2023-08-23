/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modele;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
@Table(name = "ASV_CODE_DEVISE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AsvCodeDevise.findAll", query = "SELECT a FROM AsvCodeDevise a"),
    @NamedQuery(name = "AsvCodeDevise.findByCodedevi", query = "SELECT a FROM AsvCodeDevise a WHERE a.codedevi = :codedevi"),
    @NamedQuery(name = "AsvCodeDevise.findByLibedevi", query = "SELECT a FROM AsvCodeDevise a WHERE a.libedevi = :libedevi"),
    @NamedQuery(name = "AsvCodeDevise.findByNumeLot", query = "SELECT a FROM AsvCodeDevise a WHERE a.numeLot = :numeLot"),
    @NamedQuery(name = "AsvCodeDevise.findBySigldevi", query = "SELECT a FROM AsvCodeDevise a WHERE a.sigldevi = :sigldevi"),
    @NamedQuery(name = "AsvCodeDevise.findByNombdeci", query = "SELECT a FROM AsvCodeDevise a WHERE a.nombdeci = :nombdeci"),
    @NamedQuery(name = "AsvCodeDevise.findBySymbdevi", query = "SELECT a FROM AsvCodeDevise a WHERE a.symbdevi = :symbdevi"),
    @NamedQuery(name = "AsvCodeDevise.findByNumedevi", query = "SELECT a FROM AsvCodeDevise a WHERE a.numedevi = :numedevi"),
    @NamedQuery(name = "AsvCodeDevise.findByPaysdevi", query = "SELECT a FROM AsvCodeDevise a WHERE a.paysdevi = :paysdevi")})
public class AsvCodeDevise implements DAOEntry {

    private static final long serialVersionUID = 1L;
    public static final String FIND_BY_CODE_DEVI = "AsvCodeDevise.findByCodedevi";
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 4)
    @Column(name = "CODEDEVI")
    private String codedevi;
    @Size(max = 50)
    @Column(name = "LIBEDEVI")
    private String libedevi;
    @Column(name = "NUME_LOT")
    private BigInteger numeLot;
    @Size(max = 10)
    @Column(name = "SIGLDEVI")
    private String sigldevi;
    @Column(name = "NOMBDECI")
    private Short nombdeci;
    @Size(max = 4)
    @Column(name = "SYMBDEVI")
    private String symbdevi;
    @Size(max = 3)
    @Column(name = "NUMEDEVI")
    private String numedevi;
    @Size(max = 100)
    @Column(name = "PAYSDEVI")
    private String paysdevi;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "monnsoci")
    private List<Societe> societeList;

    public AsvCodeDevise() {
    }

    public AsvCodeDevise(String codedevi) {
        this.codedevi = codedevi;
    }

    public String getCodedevi() {
        return codedevi;
    }

    public void setCodedevi(String codedevi) {
        this.codedevi = codedevi;
    }

    public String getLibedevi() {
        return libedevi;
    }

    public void setLibedevi(String libedevi) {
        this.libedevi = libedevi;
    }

    public BigInteger getNumeLot() {
        return numeLot;
    }

    public void setNumeLot(BigInteger numeLot) {
        this.numeLot = numeLot;
    }

    public String getSigldevi() {
        return sigldevi;
    }

    public void setSigldevi(String sigldevi) {
        this.sigldevi = sigldevi;
    }

    public Short getNombdeci() {
        return nombdeci;
    }

    public void setNombdeci(Short nombdeci) {
        this.nombdeci = nombdeci;
    }

    public String getSymbdevi() {
        return symbdevi;
    }

    public void setSymbdevi(String symbdevi) {
        this.symbdevi = symbdevi;
    }

    public String getNumedevi() {
        return numedevi;
    }

    public void setNumedevi(String numedevi) {
        this.numedevi = numedevi;
    }

    public String getPaysdevi() {
        return paysdevi;
    }

    public void setPaysdevi(String paysdevi) {
        this.paysdevi = paysdevi;
    }

    @XmlTransient
    public List<Societe> getSocieteList() {
        return societeList;
    }

    public void setSocieteList(List<Societe> societeList) {
        this.societeList = societeList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codedevi != null ? codedevi.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AsvCodeDevise)) {
            return false;
        }
        AsvCodeDevise other = (AsvCodeDevise) object;
        if ((this.codedevi == null && other.codedevi != null) || (this.codedevi != null && !this.codedevi.equals(other.codedevi))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modele.AsvCodeDevise[ codedevi=" + codedevi + " ]";
    }
    
}
