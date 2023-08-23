/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author hp
 */
//@MappedSuperclass
@Entity
@Table(name = "ORCLASS_ENTREPRISES")
//@XmlRootElement
public class OrclassEntreprises implements DAOEntry {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_ENTREPRISE")
    private Short idEntreprise;
    @Size(max = 32)
    @Column(name = "CODE")
    private String code;
    @Size(max = 255)
    @Column(name = "RAISON_SOCIALE")
    private String raisonSociale;
    @Size(max = 32)
    @Column(name = "IMMATRICULATION")
    private String immatriculation;
    @Size(max = 32)
    @Column(name = "NO_CONTRIBUABLE")
    private String noContribuable;
//  logo de l entreprise
    @Basic(fetch = FetchType.LAZY)
    @Lob
    @Column(name = "photo")
    private byte[] image;
    @Size(max = 50)
    @Column(name = "chemin")
    private String chemin_logo;
    @Embedded
    private Adresse adresse;
    @Column(name = "DATE_CREATION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreation;
    @Column(name = "DATE_MODIFICATION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateModification;
    private String responsable;
    private String typeResponsabilite;
    
    @Column(name = "SiteWeb")
    private String siteWeb;

    
    
   
    private String pathDossierReporting;//chemin racine pour les dossiers reporting personaliser pour une compagnies precis
     /**
     * permettre d activer le service Microsoft exchange
     */
    Boolean serviceMicrosoft = Boolean.FALSE;
     //pour la passerelle des sms pour une compagnies
    private String login;
    private String pwd;
//    private int compte;
    private String senderId;
    
    //pour les notification mail pour une compagnie
    private String serveurSTMP = "";
    private String portSTMP = "";
    private String username = "";
    private String password = "";
      private String languageByDefaut;// langue pardefaut de la compagnie
      //Options Notifications
    private Boolean envoiMail = Boolean.TRUE;
     private Boolean sms = Boolean.FALSE;
     private Boolean profil_code_automatique=Boolean.FALSE;
     private String prefix_code_profile;
     private Boolean login_user_automatique=Boolean.FALSE;
     private String prefix_login_user;
    

    public OrclassEntreprises() {
        adresse = new Adresse();

    }

    public Adresse getAdresse() {
        return adresse;
    }

    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }

    public String getResponsable() {
        return responsable;
    }

    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }

    public String getTypeResponsabilite() {
        return typeResponsabilite;
    }

    public void setTypeResponsabilite(String typeResponsabilite) {
        this.typeResponsabilite = typeResponsabilite;
    }

   
    public String getSiteWeb() {
        return siteWeb;
    }

    public void setSiteWeb(String siteWeb) {
        this.siteWeb = siteWeb;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getChemin_logo() {
        return chemin_logo;
    }

    public void setChemin_logo(String chemin_logo) {
        this.chemin_logo = chemin_logo;
    }

    public OrclassEntreprises(Short idEntreprise) {
        this.idEntreprise = idEntreprise;
    }

    public Short getIdEntreprise() {
        return idEntreprise;
    }

    public void setIdEntreprise(Short idEntreprise) {
        this.idEntreprise = idEntreprise;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getRaisonSociale() {
        return raisonSociale;
    }

    public void setRaisonSociale(String raisonSociale) {
        this.raisonSociale = raisonSociale;
    }

    public String getImmatriculation() {
        return immatriculation;
    }

    public void setImmatriculation(String immatriculation) {
        this.immatriculation = immatriculation;
    }

    public String getNoContribuable() {
        return noContribuable;
    }

    public void setNoContribuable(String noContribuable) {
        this.noContribuable = noContribuable;
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

   

    

   

    public String getPathDossierReporting() {
        return pathDossierReporting;
    }

    public void setPathDossierReporting(String pathDossierReporting) {
        this.pathDossierReporting = pathDossierReporting;
    }

    public Boolean getServiceMicrosoft() {
        return serviceMicrosoft;
    }

    public void setServiceMicrosoft(Boolean serviceMicrosoft) {
        this.serviceMicrosoft = serviceMicrosoft;
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

    public String getLanguageByDefaut() {
        return languageByDefaut;
    }

    public void setLanguageByDefaut(String languageByDefaut) {
        this.languageByDefaut = languageByDefaut;
    }

    public Boolean getEnvoiMail() {
        return envoiMail;
    }

    public void setEnvoiMail(Boolean envoiMail) {
        this.envoiMail = envoiMail;
    }

    public Boolean getSms() {
        return sms;
    }

    public void setSms(Boolean sms) {
        this.sms = sms;
    }

    public Boolean getProfil_code_automatique() {
        return profil_code_automatique;
    }

    public void setProfil_code_automatique(Boolean profil_code_automatique) {
        this.profil_code_automatique = profil_code_automatique;
    }

    public String getPrefix_code_profile() {
        return prefix_code_profile;
    }

    public void setPrefix_code_profile(String prefix_code_profile) {
        this.prefix_code_profile = prefix_code_profile;
    }

    public Boolean getLogin_user_automatique() {
        return login_user_automatique;
    }

    public void setLogin_user_automatique(Boolean login_user_automatique) {
        this.login_user_automatique = login_user_automatique;
    }

    public String getPrefix_login_user() {
        return prefix_login_user;
    }

    public void setPrefix_login_user(String prefix_login_user) {
        this.prefix_login_user = prefix_login_user;
    }
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEntreprise != null ? idEntreprise.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OrclassEntreprises)) {
            return false;
        }
        OrclassEntreprises other = (OrclassEntreprises) object;
        if ((this.idEntreprise == null && other.idEntreprise != null) || (this.idEntreprise != null && !this.idEntreprise.equals(other.idEntreprise))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modele.OrclassEntreprises[ idEntreprise=" + idEntreprise + " ]";
    }

}
