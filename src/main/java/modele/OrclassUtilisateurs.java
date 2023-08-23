/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import enums.TypeUtilisateur;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author hp
 */
@Entity
//@MappedSuperclass
@Table(name = "ESPH_UTILISATEUR")
//@XmlRootElement
public class OrclassUtilisateurs implements DAOEntry {

    private static final long serialVersionUID = 1L;
    @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator ="ESPH_SEQ")
    @SequenceGenerator( name="ESPH_SEQ", sequenceName="SEQUENCE1", allocationSize=1)
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_UTILISATEUR")
    private Short idUtilisateur;
    @Size(max = 255)
    @Column(name = "NOM")
    private String nom;
    @Size(max = 255)
    @Column(name = "PRENOM")
    private String prenom;
    @Size(max = 255)
    @Column(name = "LOGIN")
    private String login;
    @Size(max = 255)
    @Column(name = "PASSWORD")
    private String password;
    private String langue;
    private String numeroCNI;
    private String sexe;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
//    @Size(max = 255)
//    @Column(name = "EMAIL")
//    private String email;
//    @Size(max = 255)
//    @Column(name = "TELEPHONE")
//    private String telephone;
    @Embedded
    private Adresse adresse;
    @Column(name = "ACTIF")
    private Boolean actif;
    @Column(name = "DATE_CREATION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreation;
     @Column(name = "DATE_NAISSANCE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datenaissance;
    @Column(name = "DATE_MODIFICATION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateModification;
 
    @ManyToMany(mappedBy = "orclassUtilisateursList")
    private List<OrclassProfils> orclassProfilsList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUtilisateur")
    private List<OrclassUtilisateursAcces> orclassUtilisateursAccesList;
  
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUtilisateur", fetch = FetchType.LAZY)
    private List<Photo> photoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUtilisateur")
    private List<ConnectionLogger> connectionLoggerList;
    @JoinColumn(name = "CODESOCI", referencedColumnName = "CODESOCI")
    @ManyToOne(optional = true)
    
    private Societe idSociete;
    @Column(name = "type_utilisateur")
    @Enumerated(EnumType.STRING)
    private TypeUtilisateur typeUtilisateur;
    private String situationMatrimoniale;
    @Transient
    Boolean connecter = Boolean.FALSE;
    @Transient
    int statut = -1;
    @Transient
    int j = 0;
    Boolean allAccessForIntermediaire=Boolean.FALSE;
      @Size(max = 255)
    @Column(name = "CLESECURITE")
    private String cle_securite; 
      private Boolean mailEnvoye=Boolean.FALSE;

    public OrclassUtilisateurs() {
        adresse = new Adresse();

    }

    public List<ConnectionLogger> getConnectionLoggerList() {
        return connectionLoggerList;
    }

    public void setConnectionLoggerList(List<ConnectionLogger> connectionLoggerList) {
        this.connectionLoggerList = connectionLoggerList;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public OrclassUtilisateurs(Short idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public Short getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(Short idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Adresse getAdresse() {
        return adresse;
    }

    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }

    public Boolean getActif() {
        return actif;
    }

    public void setActif(Boolean actif) {
        this.actif = actif;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public Date getDateModification() {
        return dateModification;
    }

    public void setDateModification(Date dateModification) {
        this.dateModification = dateModification;
    }

    public String getNumeroCNI() {
        return numeroCNI;
    }

    public void setNumeroCNI(String numeroCNI) {
        this.numeroCNI = numeroCNI;
    }

   
    @XmlTransient
    public List<OrclassProfils> getOrclassProfilsList() {
        return orclassProfilsList;
    }

    public void setOrclassProfilsList(List<OrclassProfils> orclassProfilsList) {
        this.orclassProfilsList = orclassProfilsList;
    }

    @XmlTransient
    public List<OrclassUtilisateursAcces> getOrclassUtilisateursAccesList() {
        return orclassUtilisateursAccesList;
    }

    public void setOrclassUtilisateursAccesList(List<OrclassUtilisateursAcces> orclassUtilisateursAccesList) {
        this.orclassUtilisateursAccesList = orclassUtilisateursAccesList;
    }

   
   

    public TypeUtilisateur getTypeUtilisateur() {
        return typeUtilisateur;
    }

    public void setTypeUtilisateur(TypeUtilisateur typeUtilisateur) {
        this.typeUtilisateur = typeUtilisateur;
    }

    public String getLangue() {
        return langue;
    }

    public void setLangue(String langue) {
        this.langue = langue;
    }

    public List<Photo> getPhotoList() {
        return photoList;
    }

    public void setPhotoList(List<Photo> photoList) {
        this.photoList = photoList;
    }

    public String getSituationMatrimoniale() {
        return situationMatrimoniale;
    }

    public void setSituationMatrimoniale(String situationMatrimoniale) {
        this.situationMatrimoniale = situationMatrimoniale;
    }

    public Societe getIdSociete() {
        return idSociete;
    }

    public void setIdSociete(Societe idSociete) {
        this.idSociete = idSociete;
    }

   

    public Boolean getConnecter() {
        return connecter;
    }

    public void setConnecter(Boolean connecter) {
        this.connecter = connecter;
    }

    public int getStatut() {
        return statut;
    }

    public void setStatut(int statut) {
        this.statut = statut;
    }

    public int getJ() {
       
       
           
        
        return j;
    }

    public void setJ(int j) {
        this.j =  j;
    }

    public Boolean getAllAccessForIntermediaire() {
        return allAccessForIntermediaire;
    }

    public void setAllAccessForIntermediaire(Boolean allAccessForIntermediaire) {
        this.allAccessForIntermediaire = allAccessForIntermediaire;
    }

    public String getCle_securite() {
        return cle_securite;
    }

    public void setCle_securite(String cle_securite) {
        this.cle_securite = cle_securite;
    }

    public Date getDatenaissance() {
        return datenaissance;
    }

    public void setDatenaissance(Date datenaissance) {
        this.datenaissance = datenaissance;
    }

    public Boolean getMailEnvoye() {
        return mailEnvoye;
    }

    public void setMailEnvoye(Boolean mailEnvoye) {
        this.mailEnvoye = mailEnvoye;
    }

    
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUtilisateur != null ? idUtilisateur.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OrclassUtilisateurs)) {
            return false;
        }
        OrclassUtilisateurs other = (OrclassUtilisateurs) object;
        if ((this.idUtilisateur == null && other.idUtilisateur != null) || (this.idUtilisateur != null && !this.idUtilisateur.equals(other.idUtilisateur))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modele.OrclassUtilisateurs[ idUtilisateur=" + idUtilisateur + " ]";
    }

}
