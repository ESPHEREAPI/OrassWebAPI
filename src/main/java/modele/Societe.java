/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modele;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
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
 * @author JIATOU FRANCK
 */
@Entity
@Table(name = "SOCIETE")

@NamedQueries({
    @NamedQuery(name = "Societe.findAll", query = "SELECT s FROM Societe s"),
    @NamedQuery(name = "Societe.findByCodesoci", query = "SELECT s FROM Societe s WHERE s.codesoci = :codesoci"),
    @NamedQuery(name = "Societe.findByRaissoci", query = "SELECT s FROM Societe s WHERE s.raissoci = :raissoci"),
    @NamedQuery(name = "Societe.findByAbresoci", query = "SELECT s FROM Societe s WHERE s.abresoci = :abresoci"),
    @NamedQuery(name = "Societe.findByAdresoci", query = "SELECT s FROM Societe s WHERE s.adresoci = :adresoci"),
    @NamedQuery(name = "Societe.findByNpSoci", query = "SELECT s FROM Societe s WHERE s.npSoci = :npSoci"),
    @NamedQuery(name = "Societe.findByIfSoci", query = "SELECT s FROM Societe s WHERE s.ifSoci = :ifSoci"),
    @NamedQuery(name = "Societe.findByTvaSoci", query = "SELECT s FROM Societe s WHERE s.tvaSoci = :tvaSoci"),
    @NamedQuery(name = "Societe.findBySsSoci", query = "SELECT s FROM Societe s WHERE s.ssSoci = :ssSoci"),
    @NamedQuery(name = "Societe.findByTelesoci", query = "SELECT s FROM Societe s WHERE s.telesoci = :telesoci"),
    @NamedQuery(name = "Societe.findByTelxsoci", query = "SELECT s FROM Societe s WHERE s.telxsoci = :telxsoci"),
    @NamedQuery(name = "Societe.findByFaxSoci", query = "SELECT s FROM Societe s WHERE s.faxSoci = :faxSoci"),
    @NamedQuery(name = "Societe.findByCapisoci", query = "SELECT s FROM Societe s WHERE s.capisoci = :capisoci"),
    @NamedQuery(name = "Societe.findByFlagsecu", query = "SELECT s FROM Societe s WHERE s.flagsecu = :flagsecu"),
    @NamedQuery(name = "Societe.findByAlgoId", query = "SELECT s FROM Societe s WHERE s.algoId = :algoId"),
    @NamedQuery(name = "Societe.findByNumeLot", query = "SELECT s FROM Societe s WHERE s.numeLot = :numeLot"),
    @NamedQuery(name = "Societe.findByFlagmodu", query = "SELECT s FROM Societe s WHERE s.flagmodu = :flagmodu"),
    @NamedQuery(name = "Societe.findByRegicomm", query = "SELECT s FROM Societe s WHERE s.regicomm = :regicomm"),
    @NamedQuery(name = "Societe.findByUnimonca", query = "SELECT s FROM Societe s WHERE s.unimonca = :unimonca"),
    @NamedQuery(name = "Societe.findByStajurso", query = "SELECT s FROM Societe s WHERE s.stajurso = :stajurso"),
    @NamedQuery(name = "Societe.findByPertrare", query = "SELECT s FROM Societe s WHERE s.pertrare = :pertrare"),
    @NamedQuery(name = "Societe.findByMasqdate", query = "SELECT s FROM Societe s WHERE s.masqdate = :masqdate"),
    @NamedQuery(name = "Societe.findByDecimonn", query = "SELECT s FROM Societe s WHERE s.decimonn = :decimonn"),
    @NamedQuery(name = "Societe.findByMasqassu", query = "SELECT s FROM Societe s WHERE s.masqassu = :masqassu"),
    @NamedQuery(name = "Societe.findByOldnumlo", query = "SELECT s FROM Societe s WHERE s.oldnumlo = :oldnumlo"),
    @NamedQuery(name = "Societe.findByCommsoci", query = "SELECT s FROM Societe s WHERE s.commsoci = :commsoci"),
    @NamedQuery(name = "Societe.findByCodlanme", query = "SELECT s FROM Societe s WHERE s.codlanme = :codlanme"),
    @NamedQuery(name = "Societe.findByLencomco", query = "SELECT s FROM Societe s WHERE s.lencomco = :lencomco"),
    @NamedQuery(name = "Societe.findByModedepl", query = "SELECT s FROM Societe s WHERE s.modedepl = :modedepl"),
    @NamedQuery(name = "Societe.findByFlagtrac", query = "SELECT s FROM Societe s WHERE s.flagtrac = :flagtrac"),
    @NamedQuery(name = "Societe.findByTypecomp", query = "SELECT s FROM Societe s WHERE s.typecomp = :typecomp"),
    @NamedQuery(name = "Societe.findByOrasvers", query = "SELECT s FROM Societe s WHERE s.orasvers = :orasvers"),
    @NamedQuery(name = "Societe.findByComsocba", query = "SELECT s FROM Societe s WHERE s.comsocba = :comsocba"),
    @NamedQuery(name = "Societe.findByComsocb0", query = "SELECT s FROM Societe s WHERE s.comsocb0 = :comsocb0"),
    @NamedQuery(name = "Societe.findByCodeenti", query = "SELECT s FROM Societe s WHERE s.codeenti = :codeenti"),
    @NamedQuery(name = "Societe.findByDgcontact", query = "SELECT s FROM Societe s WHERE s.dgcontact = :dgcontact"),
    @NamedQuery(name = "Societe.findByPlanumas", query = "SELECT s FROM Societe s WHERE s.planumas = :planumas"),
    @NamedQuery(name = "Societe.findByModcodas", query = "SELECT s FROM Societe s WHERE s.modcodas = :modcodas"),
    @NamedQuery(name = "Societe.findByCosopotv", query = "SELECT s FROM Societe s WHERE s.cosopotv = :cosopotv"),
    @NamedQuery(name = "Societe.findByOrasadmi", query = "SELECT s FROM Societe s WHERE s.orasadmi = :orasadmi")})
public class Societe implements DAOEntry {

    private static final long serialVersionUID = 1L;
    public static final String FIND_BY_CODESOCI = "Societe.findByCodesoci";
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODESOCI")
    private Short codesoci;
    @Size(max = 60)
    @Column(name = "RAISSOCI")
    private String raissoci;
    @Size(max = 20)
    @Column(name = "ABRESOCI")
    private String abresoci;
    @Size(max = 60)
    @Column(name = "ADRESOCI")
    private String adresoci;
    @Size(max = 15)
    @Column(name = "NP__SOCI")
    private String npSoci;
    @Size(max = 15)
    @Column(name = "IF__SOCI")
    private String ifSoci;
    @Size(max = 15)
    @Column(name = "TVA_SOCI")
    private String tvaSoci;
    @Size(max = 15)
    @Column(name = "SS__SOCI")
    private String ssSoci;
    @Size(max = 60)
    @Column(name = "TELESOCI")
    private String telesoci;
    @Size(max = 20)
    @Column(name = "TELXSOCI")
    private String telxsoci;
    @Size(max = 20)
    @Column(name = "FAX_SOCI")
    private String faxSoci;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "CAPISOCI")
    private BigDecimal capisoci;
    @Size(max = 1)
    @Column(name = "FLAGSECU")
    private String flagsecu;
    @Size(max = 10)
    @Column(name = "ALGO__ID")
    private String algoId;
    @Column(name = "NUME_LOT")
    private BigInteger numeLot;
    @Size(max = 1)
    @Column(name = "FLAGMODU")
    private String flagmodu;
    @Size(max = 20)
    @Column(name = "REGICOMM")
    private String regicomm;
    @Size(max = 30)
    @Column(name = "UNIMONCA")
    private String unimonca;
    @Size(max = 10)
    @Column(name = "STAJURSO")
    private String stajurso;
    @Size(max = 1)
    @Column(name = "PERTRARE")
    private String pertrare;
    @Size(max = 10)
    @Column(name = "MASQDATE")
    private String masqdate;
    @Column(name = "DECIMONN")
    private Short decimonn;
    @Size(max = 24)
    @Column(name = "MASQASSU")
    private String masqassu;
    @Column(name = "OLDNUMLO")
    private BigInteger oldnumlo;
    @Lob
    @Column(name = "LOGOSOCI")
    private byte[] logosoci;
    @Size(max = 500)
    @Column(name = "COMMSOCI")
    private String commsoci;
    @Size(max = 2)
    @Column(name = "CODLANME")
    private String codlanme;
    @Column(name = "LENCOMCO")
    private BigInteger lencomco;
    @Size(max = 3)
    @Column(name = "MODEDEPL")
    private String modedepl;
    @Size(max = 1)
    @Column(name = "FLAGTRAC")
    private String flagtrac;
    @Column(name = "TYPECOMP")
    private Character typecomp;
    @Size(max = 30)
    @Column(name = "ORASVERS")
    private String orasvers;
    @Size(max = 600)
    @Column(name = "COMSOCBA")
    private String comsocba;
    @Size(max = 600)
    @Column(name = "COMSOCB0")
    private String comsocb0;
    @Size(max = 5)
    @Column(name = "CODEENTI")
    private String codeenti;
    @Size(max = 60)
    @Column(name = "DGCONTACT")
    private String dgcontact;
    @Column(name = "PLANUMAS")
    private Short planumas;
    @Size(max = 2)
    @Column(name = "MODCODAS")
    private String modcodas;
    @Column(name = "COSOPOTV")
    private Integer cosopotv;
    @Size(max = 20)
    @Column(name = "ORASADMI")
    private String orasadmi;
    @JoinColumn(name = "MONNSOCI", referencedColumnName = "CODEDEVI")
    @ManyToOne(optional = true)
    private AsvCodeDevise monnsoci;
    @JoinColumn(name = "VILLSOCI", referencedColumnName = "CODEVILL")
     @ManyToOne(optional = true)
    private Ville villsoci;
    @Column(name = "PATHLOGO")
    private String chemin_logo;
    @Column(name = "DATE_CREATION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreation;
    @Column(name = "DATE_MODIFICATION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateModification;
    private Boolean login_user_automatique = Boolean.FALSE;
    private String prefix_login_user;
    private String profil_code_automatique;
    private String prefix_code_profile;
    private String typeResponsabilite;
    private String email;
    private String siteWeb;

    public Societe() {
    }

    public Societe(Short codesoci) {
        this.codesoci = codesoci;
    }

    public Short getCodesoci() {
        return codesoci;
    }

    public void setCodesoci(Short codesoci) {
        this.codesoci = codesoci;
    }

    public String getRaissoci() {
        return raissoci;
    }

    public void setRaissoci(String raissoci) {
        this.raissoci = raissoci;
    }

    public String getAbresoci() {
        return abresoci;
    }

    public void setAbresoci(String abresoci) {
        this.abresoci = abresoci;
    }

    public String getAdresoci() {
        return adresoci;
    }

    public void setAdresoci(String adresoci) {
        this.adresoci = adresoci;
    }

    public String getNpSoci() {
        return npSoci;
    }

    public void setNpSoci(String npSoci) {
        this.npSoci = npSoci;
    }

    public String getIfSoci() {
        return ifSoci;
    }

    public void setIfSoci(String ifSoci) {
        this.ifSoci = ifSoci;
    }

    public String getTvaSoci() {
        return tvaSoci;
    }

    public void setTvaSoci(String tvaSoci) {
        this.tvaSoci = tvaSoci;
    }

    public String getSsSoci() {
        return ssSoci;
    }

    public void setSsSoci(String ssSoci) {
        this.ssSoci = ssSoci;
    }

    public String getTelesoci() {
        return telesoci;
    }

    public void setTelesoci(String telesoci) {
        this.telesoci = telesoci;
    }

    public String getTelxsoci() {
        return telxsoci;
    }

    public void setTelxsoci(String telxsoci) {
        this.telxsoci = telxsoci;
    }

    public String getFaxSoci() {
        return faxSoci;
    }

    public void setFaxSoci(String faxSoci) {
        this.faxSoci = faxSoci;
    }

    public BigDecimal getCapisoci() {
        return capisoci;
    }

    public void setCapisoci(BigDecimal capisoci) {
        this.capisoci = capisoci;
    }

    public String getFlagsecu() {
        return flagsecu;
    }

    public void setFlagsecu(String flagsecu) {
        this.flagsecu = flagsecu;
    }

    public String getAlgoId() {
        return algoId;
    }

    public void setAlgoId(String algoId) {
        this.algoId = algoId;
    }

    public BigInteger getNumeLot() {
        return numeLot;
    }

    public void setNumeLot(BigInteger numeLot) {
        this.numeLot = numeLot;
    }

    public String getFlagmodu() {
        return flagmodu;
    }

    public void setFlagmodu(String flagmodu) {
        this.flagmodu = flagmodu;
    }

    public String getRegicomm() {
        return regicomm;
    }

    public void setRegicomm(String regicomm) {
        this.regicomm = regicomm;
    }

    public String getUnimonca() {
        return unimonca;
    }

    public void setUnimonca(String unimonca) {
        this.unimonca = unimonca;
    }

    public String getStajurso() {
        return stajurso;
    }

    public void setStajurso(String stajurso) {
        this.stajurso = stajurso;
    }

    public String getPertrare() {
        return pertrare;
    }

    public void setPertrare(String pertrare) {
        this.pertrare = pertrare;
    }

    public String getMasqdate() {
        return masqdate;
    }

    public void setMasqdate(String masqdate) {
        this.masqdate = masqdate;
    }

    public Short getDecimonn() {
        return decimonn;
    }

    public void setDecimonn(Short decimonn) {
        this.decimonn = decimonn;
    }

    public String getMasqassu() {
        return masqassu;
    }

    public void setMasqassu(String masqassu) {
        this.masqassu = masqassu;
    }

    public BigInteger getOldnumlo() {
        return oldnumlo;
    }

    public void setOldnumlo(BigInteger oldnumlo) {
        this.oldnumlo = oldnumlo;
    }

    public byte[] getLogosoci() {
        return logosoci;
    }

    public void setLogosoci(byte[] logosoci) {
        this.logosoci = logosoci;
    }

    public String getCommsoci() {
        return commsoci;
    }

    public void setCommsoci(String commsoci) {
        this.commsoci = commsoci;
    }

    public String getCodlanme() {
        return codlanme;
    }

    public void setCodlanme(String codlanme) {
        this.codlanme = codlanme;
    }

    public BigInteger getLencomco() {
        return lencomco;
    }

    public void setLencomco(BigInteger lencomco) {
        this.lencomco = lencomco;
    }

    public String getModedepl() {
        return modedepl;
    }

    public void setModedepl(String modedepl) {
        this.modedepl = modedepl;
    }

    public String getFlagtrac() {
        return flagtrac;
    }

    public void setFlagtrac(String flagtrac) {
        this.flagtrac = flagtrac;
    }

    public Character getTypecomp() {
        return typecomp;
    }

    public void setTypecomp(Character typecomp) {
        this.typecomp = typecomp;
    }

    public String getOrasvers() {
        return orasvers;
    }

    public void setOrasvers(String orasvers) {
        this.orasvers = orasvers;
    }

    public String getComsocba() {
        return comsocba;
    }

    public void setComsocba(String comsocba) {
        this.comsocba = comsocba;
    }

    public String getComsocb0() {
        return comsocb0;
    }

    public void setComsocb0(String comsocb0) {
        this.comsocb0 = comsocb0;
    }

    public String getCodeenti() {
        return codeenti;
    }

    public void setCodeenti(String codeenti) {
        this.codeenti = codeenti;
    }

    public String getDgcontact() {
        return dgcontact;
    }

    public void setDgcontact(String dgcontact) {
        this.dgcontact = dgcontact;
    }

    public Short getPlanumas() {
        return planumas;
    }

    public void setPlanumas(Short planumas) {
        this.planumas = planumas;
    }

    public String getModcodas() {
        return modcodas;
    }

    public void setModcodas(String modcodas) {
        this.modcodas = modcodas;
    }

    public Integer getCosopotv() {
        return cosopotv;
    }

    public void setCosopotv(Integer cosopotv) {
        this.cosopotv = cosopotv;
    }

    public String getOrasadmi() {
        return orasadmi;
    }

    public void setOrasadmi(String orasadmi) {
        this.orasadmi = orasadmi;
    }

    public AsvCodeDevise getMonnsoci() {
        return monnsoci;
    }

    public void setMonnsoci(AsvCodeDevise monnsoci) {
        this.monnsoci = monnsoci;
    }

    public Ville getVillsoci() {
        return villsoci;
    }

    public String getChemin_logo() {
        return chemin_logo;
    }

    public void setChemin_logo(String chemin_logo) {
        this.chemin_logo = chemin_logo;
    }

    public void setVillsoci(Ville villsoci) {
        this.villsoci = villsoci;
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

    public String getProfil_code_automatique() {
        return profil_code_automatique;
    }

    public void setProfil_code_automatique(String profil_code_automatique) {
        this.profil_code_automatique = profil_code_automatique;
    }

    public String getPrefix_code_profile() {
        return prefix_code_profile;
    }

    public void setPrefix_code_profile(String prefix_code_profile) {
        this.prefix_code_profile = prefix_code_profile;
    }

    public String getTypeResponsabilite() {
        return typeResponsabilite;
    }

    public void setTypeResponsabilite(String typeResponsabilite) {
        this.typeResponsabilite = typeResponsabilite;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSiteWeb() {
        return siteWeb;
    }

    public void setSiteWeb(String siteWeb) {
        this.siteWeb = siteWeb;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codesoci != null ? codesoci.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Societe)) {
            return false;
        }
        Societe other = (Societe) object;
        if ((this.codesoci == null && other.codesoci != null) || (this.codesoci != null && !this.codesoci.equals(other.codesoci))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modele.Societe[ codesoci=" + codesoci + " ]";
    }

}
