package modele;

import enums.BackupData;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


/**
 * @author Fabrice
 */
@Entity
@Table(name = "ESPH_PROPERTYS")
public class Property implements DAOEntry {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator ="ESPH_SEQ")
    @SequenceGenerator( name="ESPH_SEQ", sequenceName="SEQUENCE1", allocationSize=1)
    @Column(name = "property_id")
    private Long id;

    //les options du module d'inscription
    @Column(name = "matriculeAuto")
    private Boolean matriculeAuto;
    
    @Column(name = "matrEtudiant")
    //Options Notifications
    private Boolean envoiMail = Boolean.TRUE;
   
    private Boolean sms_paie = Boolean.FALSE;
  
    private Boolean ExchangeMail = Boolean.FALSE;
   
     private String pathDossierReporting;
   
    /**
     * permettre d activer le service Microsoft exchange
     */
    Boolean serviceMicrosoft = Boolean.FALSE;
  

    //pour la passerelle
    private String login;
    private String pwd;
//    private int compte;
    private String senderId;
    //pour la longueur des champs du matricule
//pour les notification
    private String serveurSTMP = "";
    private String portSTMP = "";
    private String username = "";
    private String password = "";
   
  
    @Column(name = "BackupData", unique = true)
    @Enumerated(EnumType.STRING)
    private BackupData backupData;


    /* @Column(name = "DateJour")
     @Temporal(TemporalType.TIMESTAMP)
     private Date dateJour;
     les infos pr la base de donnee
     */
    String serverMysql = "";
    String portMysql = "";
    String userMysql = "";
    String passwordMysql = "";
    String dbMysql = "";
    String pathFileMysql = "";
  

  


  private String compte;


   
    public Property() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getMatriculeAuto() {
        return matriculeAuto;
    }

    public void setMatriculeAuto(Boolean matriculeAuto) {
        this.matriculeAuto = matriculeAuto;
    }

    public Boolean getEnvoiMail() {
        if (envoiMail == null) {
            return Boolean.FALSE;
        }
        return envoiMail;
    }

    public void setEnvoiMail(Boolean envoiMail) {

        this.envoiMail = envoiMail;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

  

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

   


  
    public String getServerMysql() {
        return serverMysql;
    }

    public void setServerMysql(String serverMysql) {
        this.serverMysql = serverMysql;
    }

    public String getPortMysql() {
        return portMysql;
    }

    public void setPortMysql(String portMysql) {
        this.portMysql = portMysql;
    }

    public String getUserMysql() {
        return userMysql;
    }

    public void setUserMysql(String userMysql) {
        this.userMysql = userMysql;
    }

    public String getPasswordMysql() {
        return passwordMysql;
    }

    public void setPasswordMysql(String passwordMysql) {
        this.passwordMysql = passwordMysql;
    }

    public String getDbMysql() {
        return dbMysql;
    }

    public void setDbMysql(String dbMysql) {
        this.dbMysql = dbMysql;
    }

    public String getPathFileMysql() {
        return pathFileMysql;
    }

    public void setPathFileMysql(String pathFileMysql) {
        this.pathFileMysql = pathFileMysql;
    }

  
    public String getServeurSTMP() {
        return serveurSTMP;
    }

    public void setServeurSTMP(String serveurSTMP) {
        this.serveurSTMP = serveurSTMP;
    }

    public String getPortSTMP() {
        return portSTMP;
    }

    public void setPortSTMP(String portSTMP) {
        this.portSTMP = portSTMP;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getSms_paie() {
        return sms_paie;
    }

    public void setSms_paie(Boolean sms_paie) {
        this.sms_paie = sms_paie;
    }

    public String getCompte() {
        return compte;
    }

    public void setCompte(String compte) {
        this.compte = compte;
    }

    

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Property other = (Property) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 23 * hash + (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }

    
    public BackupData getBackupData() {
        return backupData;
    }

    public void setBackupData(BackupData backupData) {
        this.backupData = backupData;
    }

    public Boolean getExchangeMail() {
        return ExchangeMail;
    }

    public void setExchangeMail(Boolean ExchangeMail) {
        this.ExchangeMail = ExchangeMail;
    }

    public Boolean getServiceMicrosoft() {
        return serviceMicrosoft;
    }

    public void setServiceMicrosoft(Boolean serviceMicrosoft) {
        this.serviceMicrosoft = serviceMicrosoft;
    }

    
    public String getPathDossierReporting() {
        return pathDossierReporting;
    }

    public void setPathDossierReporting(String pathDossierReporting) {
        this.pathDossierReporting = pathDossierReporting;
    }

   
    
    

}
