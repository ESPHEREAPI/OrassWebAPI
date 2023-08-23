///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package controllers.adp;
//
//import dao.OrclassCaracteristiqueSinistreMaladieDao;
//import dao.OrclassCaracteristiquesDao;
//import dao.OrclassEvenementDao;
//import dao.OrclassEvenementSinistreMaladieDao;
//import dao.OrclassIntervenantDao;
//import dao.OrclassIntervenantSinistreMaladieDao;
//import dao.OrclassMedicamentSinistreMaladieDao;
//import dao.OrclassSinistreDao;
//import dao.OrclassSinistreMaladieDao;
//import enums.SortSinistre;
//import java.io.IOException;
//import java.io.Serializable;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//import java.util.Locale;
//import java.util.Objects;
//import javax.annotation.PostConstruct;
//import javax.ejb.EJB;
//import javax.faces.application.FacesMessage;
//import javax.faces.component.UIComponent;
//import javax.faces.context.ExternalContext;
//import javax.faces.context.FacesContext;
//import javax.faces.view.ViewScoped;
//import javax.inject.Named;
//import javax.servlet.http.HttpServletRequest;
//import modele.OrclassAssure;
//import modele.OrclassAvenant;
//import modele.OrclassBordereau;
//import modele.OrclassCaracteristiqueSinistreMaladie;
//import modele.OrclassCaracteristiques;
//import modele.OrclassConvention;
//import modele.OrclassEntreprises;
//import modele.OrclassEvenement;
//import modele.OrclassEvenementSinistreMaladie;
//import modele.OrclassExercice;
//import modele.OrclassGarantie;
//import modele.OrclassGroupePolice;
//import modele.OrclassIntervenant;
//import modele.OrclassIntervenantSinistreMaladie;
//import modele.OrclassMedicamentSinistreMaladie;
//import modele.OrclassNatureMaladie;
//import modele.OrclassPolice;
//import modele.OrclassPoliceGarantie;
//import modele.OrclassPrestataire;
//import modele.OrclassRisque;
//import modele.OrclassRisqueFamille;
//import modele.OrclassRubrique;
//import modele.OrclassSinistre;
//import modele.OrclassSinistreMaladie;
//import modele.OrclassUtilisateurs;
//import modele.OrclasseFacture;
//import org.primefaces.PrimeFaces;
//import org.primefaces.event.TabChangeEvent;
//import utils.GlobalFonctions;
//
///**
// *
// * @author JIATOU FRANCK
// */
//@Named(value = "sinistreController")
//
//@ViewScoped
//public class SinistreController implements Serializable {
//
//    @EJB
//    OrclassSinistreDao sinistreDao;
//    @EJB
//    OrclassSinistreMaladieDao sinistreMaladieDao;
//    @EJB
//    OrclassCaracteristiquesDao caracteristiquesDao;
//    @EJB
//    OrclassEvenementDao evenementDao;
//    @EJB
//    OrclassIntervenantDao intervenantDao;
//    @EJB
//    OrclassMedicamentSinistreMaladieDao medicamentSinistreMaladieDao;
//    @EJB
//    OrclassIntervenantSinistreMaladieDao intervenantSinistreMaladieDao;
//    @EJB
//    OrclassEvenementSinistreMaladieDao evenementSinistreMaladieDao;
//    @EJB
//    OrclassCaracteristiqueSinistreMaladieDao caracteristiqueSinistreMaladieDao;
//
//    private List<OrclassSinistre> listeSinistre = new ArrayList<>();
//    private List<OrclassSinistreMaladie> listeSinistreMaladie = new ArrayList<>();
//    private List<OrclassMedicamentSinistreMaladie> listesMedicamentSinistreMaladie = new ArrayList<>();
//    private List<OrclassCaracteristiqueSinistreMaladie> listesCaracteristiqueSinistreMaladi = new ArrayList<>();
//    private List<OrclassIntervenantSinistreMaladie> listesIntervenantSinistreMaladie = new ArrayList<>();
//    private List<OrclassEvenementSinistreMaladie> listesEvenementSinistreMaladie = new ArrayList<>();
//    private OrclassUtilisateurs user;
//    private OrclassEntreprises entreprise;
//    private OrclassRubrique rubrique;
//    private OrclassGarantie garantie;
//    private OrclassNatureMaladie natureMaladie;
//    private String souscripteur;
//    OrclassAssure assure;
//    OrclassConvention convention;
//    OrclassRisque risque, risqueSelecte;
//    OrclassPoliceGarantie policeGarantie;
//    OrclassPrestataire prestataire;
//    OrclassSinistre sinistre;
//    OrclassSinistreMaladie sinistreMaladie;
//    private OrclassPolice police;
//    int code_agence = 0;
//    private OrclassRisqueFamille risqueFamille;
//    OrclassExercice exercice_sinistre;
//    private Boolean valider = Boolean.FALSE;
//    private int numero_sinistre = 0;
//    private int code_exercice = 0;
//    private OrclasseFacture facture;
//    private OrclassBordereau bordereau;
//    OrclassSinistreMaladie sinistreMaladie_infos;
//    OrclassAvenant avenant;
//    private OrclassGroupePolice groupePolice;
//    private Double total_debours_reels = 0.0, total_exclure = 0.0, total_base_remb = 0.0, total_remb_externe = 0.0, total_montant_remb = 0.0;
//    private Date effet, echeance;
//
//    @PostConstruct
//    public void initialiseSession() {
//        FacesContext.getCurrentInstance().getExternalContext().getSession(true);
//        entreprise = (OrclassEntreprises) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(GlobalFonctions.ENTREPRISE_ACTIF);
//        if (entreprise == null) {
//            entreprise = (OrclassEntreprises) FacesContext.getCurrentInstance().getExternalContext().getApplicationMap().get(GlobalFonctions.ENTREPRISE_ACTIF);
//
//        }
//
//        user = (OrclassUtilisateurs) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(GlobalFonctions.SESSION_USER);
//
//    }
//
//    public SinistreController() {
//        exercice_sinistre = new OrclassExercice();
//        exercice_sinistre.setCode(0);
//        sinistre = new OrclassSinistre();
//        numero_sinistre = 0;
//        code_exercice = 0;
//        code_agence = 0;
//        sinistreMaladie_infos = new OrclassSinistreMaladie();
//        avenant = new OrclassAvenant();
//        groupePolice = new OrclassGroupePolice();
//        bordereau = new OrclassBordereau();
//        facture = new OrclasseFacture();
//
//    }
//
//    public void init() {
//        sinistre = new OrclassSinistre();
//        avenant = new OrclassAvenant();
//        police = new OrclassPolice();
//        risque = new OrclassRisque();
//        garantie = new OrclassGarantie();
//        prestataire = new OrclassPrestataire();
//        groupePolice = new OrclassGroupePolice();
//        natureMaladie = new OrclassNatureMaladie();
//        risqueFamille = new OrclassRisqueFamille();
//        facture = new OrclasseFacture();
//        listeSinistreMaladie = new ArrayList<>();
//        effet = null;
//        echeance = null;
//
//    }
//
//    public void selecteSinistreByNumeroSinistre() {
//        if (exercice_sinistre.getCode() != null && code_exercice != 0 && code_agence != 0 && numero_sinistre != 0) {
//            sinistre = sinistreDao.finKey(entreprise, "" + numero_sinistre, code_exercice, "" + code_agence);
//            if (sinistre == null) {
//                sinistre = new OrclassSinistre();
//                PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage("THE VALUE NOT EXISTS", "PLEASE TRY AGAINST..."));
//                return;
//            }
//            if (sinistre != null && sinistre.getId() != null && Objects.equals(sinistre.getValide_pec(), Boolean.FALSE)) {
//                this.init();
//
//                PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage("OPERATION NOT VALIDE", "PLEASE PLEASE SELECT OPERATION VALITE..."));
//                this.updateTableSinistreMaladie();
//                PrimeFaces.current().ajax().update(":form1");
//                return;
//            }
//            this.setPolice(sinistre.getIdPolice());
//            this.setRisque(sinistre.getIdRisque());
//            this.setGarantie(sinistre.getIdGarantie());
//            this.setRubrique(sinistre.getIdRubrique());
//            this.setPrestataire(sinistre.getIdPrestataire());
//            this.setAvenant(sinistre.getIdAvenant() == null ? new OrclassAvenant() : sinistre.getIdAvenant());
//            this.setGroupePolice(sinistre.getIdGroupePolice());
//            this.setNatureMaladie(sinistre.getIdNatureMaladie() == null ? new OrclassNatureMaladie() : sinistre.getIdNatureMaladie());
//            this.setRisqueFamille(sinistre.getIdRisqueFamille() == null ? new OrclassRisqueFamille() : sinistre.getIdRisqueFamille());
//            this.setFacture(sinistre.getIdFacture() == null ? new OrclasseFacture() : sinistre.getIdFacture());
//            this.setBordereau(sinistre.getIdBordereau() == null ? new OrclassBordereau() : sinistre.getIdBordereau());
//            listeSinistreMaladie = sinistreMaladieDao.listeSinistreMaladieByCompagnieForSinistre(entreprise, sinistre);
//            effet = ((avenant == null || avenant.getId() == null) || (avenant != null && avenant.getId() != null && avenant.getDate_effet() != null)) ? police.getDate_effet() : avenant.getDate_effet();
//            echeance = ((avenant == null || avenant.getId() == null) || (avenant != null && avenant.getId() != null && avenant.getDate_echeance() != null)) ? police.getDate_echeance() : avenant.getDate_echeance();
//            if (effet == null) {
//                effet = police.getDate_effet();
//
//            }
//            if (echeance == null) {
//                echeance = police.getDate_echeance();
//            }
//            souscripteur = police.getIdAssure().getRaison_social() == null ? police.getIdAssure().getNom() : police.getIdAssure().getRaison_social();
//            this.updateTableSinistreMaladie();
//            PrimeFaces.current().ajax().update(":form1");
//
//        }
//    }
//
//    public void chargeInfosPrestation(OrclassSinistreMaladie item) {
//        if (item != null && item.getIdPrestation() != null && item.getIdPrestation().getId() != null) {
//            this.setSinistreMaladie_infos(item);
//        }
//
//    }
//
//    public void onTabChange(TabChangeEvent event) {
//        FacesContext ctx = FacesContext.getCurrentInstance();
//        Locale myLoc = ctx.getViewRoot().getLocale();
//
//        if (null != event.getTab().getId()) {
//            switch (event.getTab().getId()) {
//                case "idmedicament":
//                    if (sinistreMaladie_infos == null || sinistreMaladie_infos.getId() == null) {
//                        PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage("FATAL ERROR ", "PLEASE SELECT THE VALUE OF TABLE PRESTATION..."));
//                        PrimeFaces.current().executeScript("PF('accordP').select(0);");
//                        return;
//                    }
//                    listesMedicamentSinistreMaladie = medicamentSinistreMaladieDao.listeMedicaleBySinistreMaladie(entreprise, sinistreMaladie_infos);
//
//                    break;
////        PrimeFaces.current().ajax().update("form1:tabprincipal");
//                case "idintervenant":
//                    if (sinistreMaladie_infos == null || sinistreMaladie_infos.getId() == null) {
//                        PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage("FATAL ERROR ", "PLEASE SELECT THE VALUE OF TABLE PRESTATION..."));
//                        PrimeFaces.current().executeScript("PF('accordP').select(0);");
//                        return;
//                    }
//                    listesIntervenantSinistreMaladie = intervenantSinistreMaladieDao.listIntervenantsBySinistreMaladie(entreprise, sinistreMaladie_infos);
//                    this.updateTableIntervenant();
//                    break;
//                case "idevenement":
//                    if (sinistreMaladie_infos == null || sinistreMaladie_infos.getId() == null) {
//                        PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage("FATAL ERROR ", "PLEASE SELECT THE VALUE OF TABLE PRESTATION..."));
//                        PrimeFaces.current().executeScript("PF('accordP').select(0);");
//                        return;
//                    }
//                    listesEvenementSinistreMaladie = evenementSinistreMaladieDao.listEnementBySinistreMaladie(entreprise, sinistreMaladie_infos);
//
//                    this.updateTableEvenement();
//                    break;
//                case "icaracteristique":
//                    if (sinistreMaladie_infos == null || sinistreMaladie_infos.getId() == null) {
//                        PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage("FATAL ERROR ", "PLEASE SELECT THE VALUE OF TABLE PRESTATION..."));
//                        PrimeFaces.current().executeScript("PF('accordP').select(0);");
//                        return;
//                    }
//                    listesCaracteristiqueSinistreMaladi = caracteristiqueSinistreMaladieDao.listCaractreristiqueBySinistreMaladie(entreprise, sinistreMaladie_infos);
//
//                    break;
//                default:
//                    break;
//            }
//        }
//
//    }
//
//    public void devaliderPEC() throws IOException {
//        FacesContext ctx = FacesContext.getCurrentInstance();
//        ExternalContext extContext = ctx.getExternalContext();
//        Locale myLoc = ctx.getViewRoot().getLocale();
//        if (sinistre != null && sinistre.getId() != null) {
//            sinistre.setValide_pec(Boolean.FALSE);
//            sinistre.setSort(SortSinistre.ouvert);
//            sinistre.setDate_devalider(new Date());
//            sinistre.setDevalider_par(user.getPrenom() == null ? user.getNom() : user.getNom() + " " + user.getPrenom());
//            sinistreDao.update(sinistre);
//            PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage(FacesMessage.SEVERITY_INFO.toString(), "OPERATION COMPLETED SUCCESSFULLY"));
////            this.reload();
//            this.init();
//            PrimeFaces.current().ajax().update(":form1");
//            this.updateTableSinistreMaladie();
//            
//        }
//    }
//
//    public void reload() throws IOException {
//        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
//        ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());
//    }
//
//    public void updateTableSinistreMaladie() {
//        UIComponent table = FacesContext.getCurrentInstance().getViewRoot().findComponent("form1:tabprincipal:idsinistre_maladie");
//        table.setValueExpression("sortBy", null);
//
//        PrimeFaces.current().executeScript("PF('sinistre_maladie').clearFilters();");
////         PrimeFaces.current().ajax().update("form1_1");
//
//    }
//
//    public void updateTableEvenement() {
//        UIComponent table = FacesContext.getCurrentInstance().getViewRoot().findComponent("form1:tabprincipal:ideventTable");
//        table.setValueExpression("sortBy", null);
//
//        PrimeFaces.current().executeScript("PF('eventTable').clearFilters();");
////         PrimeFaces.current().ajax().update("form1_1");
//
//    }
//
//    public void updateTableIntervenant() {
//        UIComponent table = FacesContext.getCurrentInstance().getViewRoot().findComponent("form1:tabprincipal:idintervenantTable");
//        table.setValueExpression("sortBy", null);
//
//        PrimeFaces.current().executeScript("PF('intervenantTable').clearFilters();");
//
//    }
//
//    public List<OrclassSinistre> getListeSinistre() {
//        return listeSinistre;
//    }
//
//    public void setListeSinistre(List<OrclassSinistre> listeSinistre) {
//        this.listeSinistre = listeSinistre;
//    }
//
//    public List<OrclassSinistreMaladie> getListeSinistreMaladie() {
//        return listeSinistreMaladie;
//    }
//
//    public void setListeSinistreMaladie(List<OrclassSinistreMaladie> listeSinistreMaladie) {
//        this.listeSinistreMaladie = listeSinistreMaladie;
//    }
//
//    public List<OrclassMedicamentSinistreMaladie> getListesMedicamentSinistreMaladie() {
//        return listesMedicamentSinistreMaladie;
//    }
//
//    public void setListesMedicamentSinistreMaladie(List<OrclassMedicamentSinistreMaladie> listesMedicamentSinistreMaladie) {
//        this.listesMedicamentSinistreMaladie = listesMedicamentSinistreMaladie;
//    }
//
//    public List<OrclassCaracteristiqueSinistreMaladie> getListesCaracteristiqueSinistreMaladi() {
//        return listesCaracteristiqueSinistreMaladi;
//    }
//
//    public void setListesCaracteristiqueSinistreMaladi(List<OrclassCaracteristiqueSinistreMaladie> listesCaracteristiqueSinistreMaladi) {
//        this.listesCaracteristiqueSinistreMaladi = listesCaracteristiqueSinistreMaladi;
//    }
//
//    public List<OrclassIntervenantSinistreMaladie> getListesIntervenantSinistreMaladie() {
//        return listesIntervenantSinistreMaladie;
//    }
//
//    public void setListesIntervenantSinistreMaladie(List<OrclassIntervenantSinistreMaladie> listesIntervenantSinistreMaladie) {
//        this.listesIntervenantSinistreMaladie = listesIntervenantSinistreMaladie;
//    }
//
//    public List<OrclassEvenementSinistreMaladie> getListesEvenementSinistreMaladie() {
//        return listesEvenementSinistreMaladie;
//    }
//
//    public void setListesEvenementSinistreMaladie(List<OrclassEvenementSinistreMaladie> listesEvenementSinistreMaladie) {
//        this.listesEvenementSinistreMaladie = listesEvenementSinistreMaladie;
//    }
//
//    public OrclassUtilisateurs getUser() {
//        return user;
//    }
//
//    public void setUser(OrclassUtilisateurs user) {
//        this.user = user;
//    }
//
//    public OrclassRubrique getRubrique() {
//        return rubrique;
//    }
//
//    public void setRubrique(OrclassRubrique rubrique) {
//        this.rubrique = rubrique;
//    }
//
//    public OrclassGarantie getGarantie() {
//        return garantie;
//    }
//
//    public void setGarantie(OrclassGarantie garantie) {
//        this.garantie = garantie;
//    }
//
//    public OrclassNatureMaladie getNatureMaladie() {
//        return natureMaladie;
//    }
//
//    public void setNatureMaladie(OrclassNatureMaladie natureMaladie) {
//        this.natureMaladie = natureMaladie;
//    }
//
//    public OrclassRisque getRisque() {
//        return risque;
//    }
//
//    public void setRisque(OrclassRisque risque) {
//        this.risque = risque;
//    }
//
//    public OrclassRisque getRisqueSelecte() {
//        return risqueSelecte;
//    }
//
//    public void setRisqueSelecte(OrclassRisque risqueSelecte) {
//        this.risqueSelecte = risqueSelecte;
//    }
//
//    public OrclassPoliceGarantie getPoliceGarantie() {
//        return policeGarantie;
//    }
//
//    public void setPoliceGarantie(OrclassPoliceGarantie policeGarantie) {
//        this.policeGarantie = policeGarantie;
//    }
//
//    public OrclassPrestataire getPrestataire() {
//        return prestataire;
//    }
//
//    public void setPrestataire(OrclassPrestataire prestataire) {
//        this.prestataire = prestataire;
//    }
//
//    public OrclassSinistre getSinistre() {
//        return sinistre;
//    }
//
//    public void setSinistre(OrclassSinistre sinistre) {
//        this.sinistre = sinistre;
//    }
//
//    public OrclassSinistreMaladie getSinistreMaladie() {
//        return sinistreMaladie;
//    }
//
//    public void setSinistreMaladie(OrclassSinistreMaladie sinistreMaladie) {
//        this.sinistreMaladie = sinistreMaladie;
//    }
//
//    public OrclassPolice getPolice() {
//        return police;
//    }
//
//    public void setPolice(OrclassPolice police) {
//        this.police = police;
//    }
//
//    public OrclassRisqueFamille getRisqueFamille() {
//        return risqueFamille;
//    }
//
//    public void setRisqueFamille(OrclassRisqueFamille risqueFamille) {
//        this.risqueFamille = risqueFamille;
//    }
//
//    public Boolean getValider() {
//        return valider;
//    }
//
//    public void setValider(Boolean valider) {
//        this.valider = valider;
//    }
//
//    public Double getTotal_debours_reels() {
//        return total_debours_reels;
//    }
//
//    public void setTotal_debours_reels(Double total_debours_reels) {
//        this.total_debours_reels = total_debours_reels;
//    }
//
//    public Double getTotal_exclure() {
//        return total_exclure;
//    }
//
//    public void setTotal_exclure(Double total_exclure) {
//        this.total_exclure = total_exclure;
//    }
//
//    public Double getTotal_remb_externe() {
//        return total_remb_externe;
//    }
//
//    public void setTotal_remb_externe(Double total_remb_externe) {
//        this.total_remb_externe = total_remb_externe;
//    }
//
//    public Double getTotal_montant_remb() {
//        return total_montant_remb;
//    }
//
//    public void setTotal_montant_remb(Double total_montant_remb) {
//        this.total_montant_remb = total_montant_remb;
//    }
//
//    public String getSouscripteur() {
//        return souscripteur;
//    }
//
//    public void setSouscripteur(String souscripteur) {
//        this.souscripteur = souscripteur;
//    }
//
//    public OrclassAssure getAssure() {
//        return assure;
//    }
//
//    public void setAssure(OrclassAssure assure) {
//        this.assure = assure;
//    }
//
//    public int getCode_agence() {
//        return code_agence;
//    }
//
//    public void setCode_agence(int code_agence) {
//        this.code_agence = code_agence;
//    }
//
//    public int getNumero_sinistre() {
//        return numero_sinistre;
//    }
//
//    public void setNumero_sinistre(int numero_sinistre) {
//        this.numero_sinistre = numero_sinistre;
//    }
//
//    public int getCode_exercice() {
//        return code_exercice;
//    }
//
//    public void setCode_exercice(int code_exercice) {
//        this.code_exercice = code_exercice;
//    }
//
//    public OrclasseFacture getFacture() {
//        return facture;
//    }
//
//    public void setFacture(OrclasseFacture facture) {
//        this.facture = facture;
//    }
//
//    public OrclassBordereau getBordereau() {
//        return bordereau;
//    }
//
//    public void setBordereau(OrclassBordereau bordereau) {
//        this.bordereau = bordereau;
//    }
//
//    public OrclassSinistreMaladie getSinistreMaladie_infos() {
//        return sinistreMaladie_infos;
//    }
//
//    public void setSinistreMaladie_infos(OrclassSinistreMaladie sinistreMaladie_infos) {
//        this.sinistreMaladie_infos = sinistreMaladie_infos;
//    }
//
//    public Double getTotal_base_remb() {
//        return total_base_remb;
//    }
//
//    public void setTotal_base_remb(Double total_base_remb) {
//        this.total_base_remb = total_base_remb;
//    }
//
//    public OrclassAvenant getAvenant() {
//        return avenant;
//    }
//
//    public void setAvenant(OrclassAvenant avenant) {
//        this.avenant = avenant;
//    }
//
//    public OrclassExercice getExercice_sinistre() {
//        return exercice_sinistre;
//    }
//
//    public void setExercice_sinistre(OrclassExercice exercice_sinistre) {
//        this.exercice_sinistre = exercice_sinistre;
//    }
//
//    public OrclassGroupePolice getGroupePolice() {
//        return groupePolice;
//    }
//
//    public void setGroupePolice(OrclassGroupePolice groupePolice) {
//        this.groupePolice = groupePolice;
//    }
//
//    public Date getEffet() {
//        return effet;
//    }
//
//    public void setEffet(Date effet) {
//        this.effet = effet;
//    }
//
//    public Date getEcheance() {
//        return echeance;
//    }
//
//    public void setEcheance(Date echeance) {
//        this.echeance = echeance;
//    }
//
//}
