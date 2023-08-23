package modele;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author fabrice
 */
@Entity
@Table(name = "esph_titre")
@NamedQueries({
    @NamedQuery(name = "Titre.findAll", query = "SELECT t FROM Titre t"),
    @NamedQuery(name = "Titre.findById", query = "SELECT t FROM Titre t WHERE t.id = :id"),
    @NamedQuery(name = "Titre.findByNomTitre", query = "SELECT t FROM Titre t WHERE t.nomTitre = :nomTitre"),
    @NamedQuery(name = "Titre.findByDescription", query = "SELECT t FROM Titre t WHERE t.description = :description")})
public class Titre implements DAOEntry {
    private static final long serialVersionUID = 1L;
    @Id 
     @GeneratedValue(strategy = GenerationType.SEQUENCE, generator ="ESPH_SEQ")
    @SequenceGenerator( name="ESPH_SEQ", sequenceName="SEQUENCE1", allocationSize=1)
    @Column(name = "id")
    private Integer id;
    @Size(max = 50)
    @Column(name = "Nom",unique=true)
    private String nomTitre;
    @Size(max = 255)
    @Column(name = "Description")
    private String description;

    public Titre() {
    }

    public Titre(String nomTitre, String description) {
        this.nomTitre = nomTitre;
        this.description = description;
    }

    public Titre(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNomTitre() {
        return nomTitre;
    }

    public void setNomTitre(String nomTitre) {
        this.nomTitre = nomTitre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
        if (!(object instanceof Titre)) {
            return false;
        }
        Titre other = (Titre) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.better.modele.Titre[ id=" + id + " ]";
    }
    
}
