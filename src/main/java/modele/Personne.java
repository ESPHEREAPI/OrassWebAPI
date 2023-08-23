package modele;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
/**
 *
 * @author evrado
 */
@Entity
@Inheritance(strategy= InheritanceType.JOINED)
@Table(name = "ESPH_PERSONNES")
@DiscriminatorColumn(name="TYPE_ENTITE")
@NamedQueries({
    
    //les anciennes requetes nommees de l'entite Personne
    @NamedQuery(name = Personne.FIND_ALL, query = "SELECT p FROM Personne p"),
    @NamedQuery(name = Personne.FIND_BY_MATRICULE, query = "SELECT p FROM Personne p WHERE p.matricule = :matricule"),
    @NamedQuery(name = Personne.FIND_BY_NOM, query = "SELECT p FROM Personne p WHERE p.nom = :nom"),
    @NamedQuery(name = Personne.FIND_BY_PAYS, query = "SELECT p FROM Personne p WHERE p.idPays.codepays = :codepays"),
    @NamedQuery(name = Personne.FIND_BY_COMPTEACTIF, query = "SELECT p FROM Personne p WHERE p.compteActif = :compteActif"),
    @NamedQuery(name = Personne.FIND_BY_PROFESSION, query = "SELECT p FROM Personne p WHERE p.profession = :profession"),
   
})
public class Personne implements DAOEntry {
    //les anciennes constantes de l'entite Personne
    public static final String FIND_ALL = "Personne.findAll";
    public static final String FIND_BY_MATRICULE = "Personne.findByMatricule";
    public static final String FIND_BY_NOM = "Personne.findByNom";
    public static final String FIND_BY_PAYS = "Personne.findByPays";
    public static final String FIND_BY_COMPTEACTIF = "Personne.findByCompteActif";
    public static final String FIND_BY_PROFESSION = "Personne.findByProfession";
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Id")
    private Long id;

//    @Index(name = "idxMatricule")
    @NotNull
    @Column(name = "Matricule",unique = true)
    private String matricule;
   
    @Size(max = 254)
    @Column(name = "pwd")
    private String pwd;
    
    @Size(max = 254)
    @Column(name = "statut")
    private String statut;

    @Column(name = "lastModifDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifDate;
    
    @Size(max = 254)
    @Column(name = "lastUserModif")
    private String lastUserModif;
    
    @Column(name = "deleteDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date deleteDate;
    
    @Size(max = 254)
    @Column(name = "userDelete")
    private String userDelete;
    
    @Column(name = "lastDatePwdModif")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastDatePwdModif;
    
    @Size(max = 254)
    @Column(name = "userCreate")
    private String userCreate;
    
    @Size(max = 150)
    @Column(name = "Nom")
    private String nom;
    
    @Size(max = 150)
    @Column(name = "Prenom")
    private String prenom;
    
    @Size(max = 15)
    @Column(name = "Sexe")
    private String sexe;
    
    @Column(name = "DateNaissance")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateNaissance;
    
    @Size(max = 255)
    @Column(name = "LieuNaissance")
    private String lieuNaissance;
    
    @Size(max = 50)
    @Column(name = "SituationMatrimoniale")
    private String situationMatrimoniale;
    
    @Column(name = "NbreEnfant")
    private Integer nbreEnfant;
    
    @Embedded 
    private Adresse adresse;
    
    @Column(name = "DateEnregistrement")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateEnregistrement;

    @Size(max = 150)
    @Column(name = "Remarques")
    private String remarques;

    @Column(name = "CompteActif")
    private boolean compteActif;
    
    @Size(max = 255)
    @Column(name = "Profession")
    private String profession;
    
    @JoinColumn(name = "CODEPAYS", referencedColumnName = "CODEPAYS")
    @ManyToOne
    private Pays idPays;
   
    
//    @ElementCollection
//    @LazyCollection(LazyCollectionOption.FALSE)
//    @CollectionTable(name = "UserEtats")
//    @Column(name = "Valeur")
//    private List<String> listEtats=new ArrayList<>();
    
    //relation avec la table menu
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
//    private Collection<Usermenu> usermenuCollection;
//    
//   
//
//     //relation avec la table Module
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
//    private Collection<Usermodule> usermoduleCollection;
//    
    //les relations de l'entite Personne
    
    
    //les constructeurs
    public Personne() {
        this.adresse=new Adresse();
    }

    public Personne(String matricule) {
        this.matricule = matricule;
    }

    public Pays getIdPays() {
        return idPays;
    }

    public void setIdPays(Pays idPays) {
        this.idPays = idPays;
    }

 
  

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public Date getLastModifDate() {
        return lastModifDate;
    }

    public void setLastModifDate(Date lastModifDate) {
        this.lastModifDate = lastModifDate;
    }

    public String getLastUserModif() {
        return lastUserModif;
    }

    public void setLastUserModif(String lastUserModif) {
        this.lastUserModif = lastUserModif;
    }

    public Date getDeleteDate() {
        return deleteDate;
    }

    public void setDeleteDate(Date deleteDate) {
        this.deleteDate = deleteDate;
    }

    public String getUserDelete() {
        return userDelete;
    }

    public void setUserDelete(String userDelete) {
        this.userDelete = userDelete;
    }

    public Date getLastDatePwdModif() {
        return lastDatePwdModif;
    }

    public void setLastDatePwdModif(Date lastDatePwdModif) {
        this.lastDatePwdModif = lastDatePwdModif;
    }

    public String getUserCreate() {
        return userCreate;
    }

    public void setUserCreate(String userCreate) {
        this.userCreate = userCreate;
    }



    

   
    
//    public Collection<Usermenu> getUsermenuCollection() {
//        return usermenuCollection;
//    }
//
//    public void setUsermenuCollection(Collection<Usermenu> usermenuCollection) {
//        this.usermenuCollection = usermenuCollection;
//    }
//    
//    public Collection<Usermodule> getUsermoduleCollection() {
//        return usermoduleCollection;
//    }
//
//    public void setUsermoduleCollection(Collection<Usermodule> usermoduleCollection) {
//        this.usermoduleCollection = usermoduleCollection;
//    }
    
    //les getters et les setters de l'entite Personne
    
    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matriculePers) {
        this.matricule = matriculePers;
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
        if(prenom==null){
            prenom="";
        }
        this.prenom = prenom;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }


    public Date getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getLieuNaissance() {
        return lieuNaissance;
    }

    public void setLieuNaissance(String lieuNaissance) {
        this.lieuNaissance = lieuNaissance;
    }

    public String getSituationMatrimoniale() {
        return situationMatrimoniale;
    }

    public void setSituationMatrimoniale(String situationMatrimoniale) {
        this.situationMatrimoniale = situationMatrimoniale;
    }

    public Integer getNbreEnfant() {
        return nbreEnfant;
    }

    public void setNbreEnfant(Integer nbreEnfant) {
        this.nbreEnfant = nbreEnfant;
    }

    public Date getDateEnregistrement() {
        return dateEnregistrement;
    }

    public void setDateEnregistrement(Date dateEnregistrement) {
        this.dateEnregistrement = dateEnregistrement;
    }

   

    public String getRemarques() {
        return remarques;
    }

    public void setRemarques(String remarques) {
        this.remarques = remarques;
    }

    public Adresse getAdresse() {
        return adresse;
    }

    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }

    public boolean isCompteActif() {
        return compteActif;
    }

    public void setCompteActif(boolean compteActif) {
        this.compteActif = compteActif;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

   
//    public List<String> getListEtats() {
//        return listEtats;
//    }
//
//    public void setListEtats(List<String> listEtats) {
//        this.listEtats = listEtats;
//    }
    
    
    
       
    //redefinition des methodes herite de la super classe Object
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (matricule != null ? matricule.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Personne)) {
            return false;
        }
        Personne other = (Personne) object;
        if ((this.matricule == null && other.matricule != null) || (this.matricule != null && !this.matricule.equals(other.matricule))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modele.User[ matricule=" + matricule + " ]";
    }
    
}
