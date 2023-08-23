/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author fabrice
 */
@Entity
@Table(name = "ESPH_PHOTOS")
//@XmlRootElement
@NamedQueries({
    @NamedQuery(name = Photo.findAll, query = "SELECT p FROM Photo p")
    ,
    @NamedQuery(name = Photo.Find_By_Utilisateur, query = "SELECT p FROM Photo p WHERE p.idUtilisateur.id = :idUtilisateur")})
public class Photo implements DAOEntry {

    public static final String findAll = "Photo.findAll";
    public static final String Find_By_Utilisateur = "Photo.Find_By_Personne";

    private static final long serialVersionUID = 1L;
    @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator ="ESPH_SEQ")
    @SequenceGenerator( name="ESPH_SEQ", sequenceName="SEQUENCE1", allocationSize=1)
    @Column(name = "Id")
    private Long id;
    @Basic(fetch = FetchType.LAZY)
    @Lob
    @Column(name = "photo")
    private byte[] image;
    @Size(max = 50)
    @Column(name = "libelle")
    private String libelle;
    @JoinColumn(name = "ID_UTILISATEUR", referencedColumnName = "ID_UTILISATEUR")
    @ManyToOne(optional = false)
    private OrclassUtilisateurs idUtilisateur;
      

    public Photo() {
        idUtilisateur = new OrclassUtilisateurs();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public OrclassUtilisateurs getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(OrclassUtilisateurs idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
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
        if (!(object instanceof Photo)) {
            return false;
        }
        Photo other = (Photo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.better.modele.Photo[ id=" + id + " ]";
    }

}
