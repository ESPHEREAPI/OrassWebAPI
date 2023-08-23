package modele;

import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author JEANNE
 */
@Entity
@Table(name = "orclass_annee")
@NamedQueries({
    @NamedQuery(name = Annee.FIND_ALL, query = "SELECT A FROM Annee A"),
    @NamedQuery(name = Annee.FIND_BYID, query = "SELECT A FROM Annee A where A.Id= :fannee")})
public class Annee implements DAOEntry {

    public static final String FIND_BYID = "Annee.findById";
    public static final String FIND_ALL = "Annee.findALL";
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "Id")
    private Integer Id;

    @Column(name = "Code", nullable = false, unique = true)
    private String Code;

    @Column(name = "Libelle")
    private String libelle;
//     @OneToMany( mappedBy = "annee")
//    private Collection<Entreprise> EntrepriseCollection;


    public Annee() {
    }

    public Annee(Integer Id) {
        this.Id = Id;
    }

    public Annee(Integer Id, String Code) {
        this.Id = Id;
        this.Code = Code;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer Id) {
        this.Id = Id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getCode() {
        return Code;
    }

    public void setCode(String Code) {
        this.Code = Code;
    }

//    public Collection<Entreprise> getEntrepriseCollection() {
//        return EntrepriseCollection;
//    }
//
//    public void setEntrepriseCollection(Collection<Entreprise> EntrepriseCollection) {
//        this.EntrepriseCollection = EntrepriseCollection;
//    }
    
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (Id != null ? Id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Annee)) {
            return false;
        }
        Annee other = (Annee) object;
        if ((this.Id == null && other.Id != null) || (this.Id != null && !this.Id.equals(other.Id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.better.modele.Ann\u00e9e[ id=" + Id + " ]";
    }

}
