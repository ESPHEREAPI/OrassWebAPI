package modele;

import enums.LicenseType;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author Fabrice
 */
@Entity
@Table(name = "orclass_licence")
@NamedQueries({
    @NamedQuery(name = "Licence.findAll", query = "SELECT l FROM Licence l")
    ,
    @NamedQuery(name = "Licence.findById", query = "SELECT l FROM Licence l WHERE l.id = :id")
})
public class Licence implements DAOEntry {

    // public String sta
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
//    @Basic(optional = false)
    @Column(name = "id")
    private Long id;

    @Enumerated(EnumType.STRING)
    private LicenseType LicenseType;

    @Column(name = "dateCreation")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateCreation;

    @Column(name = "dateFinLicense")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateDateFin;
    @JoinColumn(name = "ID_ENTREPRISE", referencedColumnName = "ID_ENTREPRISE")
    @ManyToOne(optional = false)
    private OrclassEntreprises idEntreprise;
    @JoinColumn(name = "ID_UTILISATEUR", referencedColumnName = "ID_UTILISATEUR")
    @ManyToOne(optional = false)
    private OrclassUtilisateurs idUtilisateurs;

    private Integer duree;

    private String licenseNumber;
    private String codeActivation;
    private int nombreUtilisateur;
    @Column(name = "adresseMac")
    private String mac;

    private String version;

    public Licence() {
    }

    public Licence(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public LicenseType getLicenseType() {
        return LicenseType;
    }

    public void setLicenseType(LicenseType LicenseType) {
        this.LicenseType = LicenseType;
    }

    public Integer getDuree() {
        return duree;
    }

    public void setDuree(Integer duree) {
        this.duree = duree;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public Date getDateDateFin() {
        return dateDateFin;
    }

    public void setDateDateFin(Date dateDateFin) {
        this.dateDateFin = dateDateFin;
    }

    public String getCodeActivation() {
        return codeActivation;
    }

    public void setCodeActivation(String codeActivation) {
        this.codeActivation = codeActivation;
    }

    public OrclassEntreprises getIdEntreprise() {
        return idEntreprise;
    }

    public void setIdEntreprise(OrclassEntreprises idEntreprise) {
        this.idEntreprise = idEntreprise;
    }

    public OrclassUtilisateurs getIdUtilisateurs() {
        return idUtilisateurs;
    }

    public void setIdUtilisateurs(OrclassUtilisateurs idUtilisateurs) {
        this.idUtilisateurs = idUtilisateurs;
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
        if (!(object instanceof Licence)) {
            return false;
        }
        Licence other = (Licence) object;
        if (this.id == null) {
            return super.equals(object);
        } else {
            return this.id.equals(other.id);
        }
    }

}
