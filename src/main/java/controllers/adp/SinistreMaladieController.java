///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package controllers.adp;
//
//import dao.OrclassAssureDao;
//import dao.OrclassAvenantDao;
//import dao.OrclassCaracteristiqueSinistreMaladieDao;
//import dao.OrclassCaracteristiquesDao;
//import dao.OrclassCategoriesDao;
//import dao.OrclassDetailPolicePlafondDao;
//import dao.OrclassEvenementDao;
//import dao.OrclassEvenementSinistreMaladieDao;
//import dao.OrclassExerciceDao;
//import dao.OrclassGroupeGarantiePoliceDao;
//import dao.OrclassGroupePoliceDao;
//import dao.OrclassIntervenantDao;
//import dao.OrclassIntervenantSinistreMaladieDao;
//import dao.OrclassMedicamentSinistreMaladieDao;
//import dao.OrclassNatureMaladieDao;
//import dao.OrclassPoliceDao;
//import dao.OrclassPoliceGarantieDao;
//import dao.OrclassPrestataireDao;
//import dao.OrclassRegistreProductionDao;
//import dao.OrclassRegistreSinistreDao;
//import dao.OrclassRisqueDao;
//import dao.OrclassRisqueFamilleDao;
//import dao.OrclassSinistreDao;
//import dao.OrclassSinistreMaladieDao;
//import enums.LibelleBranche;
//import enums.SortSinistre;
//import enums.SortSinistreMaladie;
//import exception.GlobalException;
//import exception.Success;
//import java.io.IOException;
//import java.io.Serializable;
//import java.math.BigDecimal;
//import java.math.BigInteger;
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
//import javax.faces.model.SelectItem;
//import javax.inject.Named;
//import javax.faces.view.ViewScoped;
//import javax.servlet.http.HttpServletRequest;
//import javax.validation.ConstraintViolationException;
//import modele.OrclassAssure;
//import modele.OrclassAvenant;
//import modele.OrclassBordereau;
//import modele.OrclassCaracteristiqueSinistreMaladie;
//import modele.OrclassCaracteristiques;
//import modele.OrclassCategories;
//import modele.OrclassConvention;
//import modele.OrclassDetailPolicePlafond;
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
//import modele.OrclassPrestation;
//import modele.OrclassRegistreProduction;
//import modele.OrclassRegistreSinistre;
//import modele.OrclassRisque;
//import modele.OrclassRisqueFamille;
//import modele.OrclassRubrique;
//import modele.OrclassSinistre;
//import modele.OrclassSinistreMaladie;
//import modele.OrclassUtilisateurs;
//import modele.OrclasseFacture;
//import org.primefaces.PrimeFaces;
//import org.primefaces.event.TabChangeEvent;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import sante.ISinistre;
//import utils.GlobalFonctions;
//import utils.IdleDate;
//import utils.LocaleHelper;
//import utils.RecupBundle;
//
///**
// *
// * @author JIATOU FRANCK
// */
//@Named(value = "sinistreMaladieController")
//
//@ViewScoped
//public class SinistreMaladieController implements Serializable {
//
//    private static final Logger logger = LoggerFactory.getLogger(SinistreMaladieController.class);
//
//    /**
//     * Creates a new instance of SinistreMaladieController
//     */
//    @EJB
//    OrclassSinistreDao sinistreDao;
//    @EJB
//    OrclassPoliceDao policeDao;
//    @EJB
//    OrclassSinistreMaladieDao sinistreMaladieDao;
//    @EJB
//    OrclassEvenementDao evenementDao;
//    @EJB
//    OrclassEvenementSinistreMaladieDao evenementSinistreMaladieDao;
//    @EJB
//    OrclassIntervenantDao intervenantDao;
//    @EJB
//    OrclassIntervenantSinistreMaladieDao intervenantSinistreMaladieDao;
//    @EJB
//    OrclassCaracteristiqueSinistreMaladieDao caracteristiqueSinistreMaladieDao;
//    @EJB
//    OrclassMedicamentSinistreMaladieDao medicamentSinistreMaladieDao;
//    @EJB
//    OrclassNatureMaladieDao natureMaladieDao;
//    @EJB
//    OrclassRisqueDao risqueDao;
//    @EJB
//    OrclassRisqueFamilleDao risqueFamilleDao;
//    @EJB
//    OrclassGroupeGarantiePoliceDao groupeGarantiePoliceDao;
//    @EJB
//    OrclassPoliceGarantieDao policeGarantieDao;
//    @EJB
//    OrclassDetailPolicePlafondDao detailPolicePlafondDao;
//    OrclassDetailPolicePlafond detailPolicePlafond;
//    @EJB
//    OrclassAvenantDao avenantDao;
//    OrclassAvenant avenant;
//    @EJB
//    OrclassAssureDao assureDao;
//    @EJB
//    OrclassGroupePoliceDao groupePoliceDao;
//    @EJB
//    OrclassPrestataireDao prestataireDao;
//    @EJB
//    ISinistre sinistreService;
//    @EJB
//    OrclassRegistreProductionDao registreProductionDao;
//    @EJB
//    OrclassCategoriesDao categoriesDao;
//    @EJB
//    OrclassCaracteristiquesDao caracteristiquesDao;
//    @EJB
//    OrclassRegistreSinistreDao registreSinistreDao;
//    @EJB
//    OrclassExerciceDao exerciceDao;
//    @EJB
//    ISinistre serviceSinistre;
//
//    private List<OrclassSinistre> listeSinistre = new ArrayList<>();
//    private List<OrclassSinistreMaladie> listeSinistreMaladie = new ArrayList<>();
//    private List<OrclassPolice> listePolice = new ArrayList<>();
//    private List<OrclassRisque> listeRisque = new ArrayList<>();
//    private List<OrclassRisqueFamille> listeRisqueFamille = new ArrayList<>();
//    private List<OrclassNatureMaladie> listeNatureMaladie = new ArrayList<>();
//    private List<OrclassPrestataire> listePrestataire = new ArrayList<>();
////    private List<OrclassIntervenant> listeIntervenant = new ArrayList<>();
////    private List<OrclassEvenement> listeEvenement = new ArrayList<>();
////    private List<OrclassCaracteristiques> listeCaracteristique = new ArrayList<>();
//    private List<OrclassRubrique> listeRubrique = new ArrayList<>();
//    private List<OrclassGarantie> listeGarantie = new ArrayList<>();
//    private List<OrclassGroupePolice> listeGroupePolice = new ArrayList<>();
//    private List<OrclassDetailPolicePlafond> listesDetailPolicePlafond = new ArrayList<>();
//    private List<OrclassDetailPolicePlafond> listesDetailPolicePlafondLocal = new ArrayList<>();
//    private List<OrclassMedicamentSinistreMaladie> listesMedicamentSinistreMaladie = new ArrayList<>();
//    private List<OrclassIntervenant> listesIntervenant = new ArrayList<>();
//    private List<OrclassEvenement> listesEvenement = new ArrayList<>();
//    private List<OrclassCaracteristiques> listesCaracteristiques = new ArrayList<>();
//    private OrclassSinistreMaladie sinistreMaladieSelected;
//    private OrclassDetailPolicePlafond detailPolicePlafondSelected;
//    private OrclassSinistreMaladie sinistreMaladie_infos;
//
//    private OrclassCategories categories;
//    private OrclassGroupePolice groupePolice;
//    private Date effet, echeance;
//    private OrclassRubrique rubrique;
//    private OrclassGarantie garantie;
//    private OrclassUtilisateurs user;
//    private OrclassEntreprises entreprise;
//    OrclassPolice police;
//    BigDecimal num_police = BigDecimal.ZERO;
//    private OrclassNatureMaladie natureMaladie;
//    private String souscripteur;
//    private String police_value;
//    private int indice = 0;
//
//    OrclassAssure assure;
//    OrclassConvention convention;
//    OrclassRisque risque, risqueSelecte;
//    OrclassPoliceGarantie policeGarantie;
//    OrclassPrestataire prestataire;
//    OrclassSinistre sinistre;
//    OrclassSinistreMaladie sinistreMaladie;
//    private OrclassRisqueFamille risqueFamille;
//    private String motif_rejet, observation;
//    OrclassRegistreSinistre registreSinistre = null;
//    int exercice = 0;
//    OrclassExercice exercice_sinistre;
//    private Boolean valider = Boolean.FALSE;
//    private Double total_debours_reels = 0.0, total_exclure = 0.0, total_base_remb = 0.0, total_remb_externe = 0.0, total_montant_remb = 0.0;
//    private int numero_sinistre = 0;
//    private int code_exercice = 0;
//    int code_agence = 0;
//    private OrclasseFacture facture;
//    private OrclassBordereau bordereau;
//
//    public SinistreMaladieController() {
//        categories = new OrclassCategories();
//        rubrique = new OrclassRubrique();
//        groupePolice = new OrclassGroupePolice();
//        garantie = new OrclassGarantie();
//        risqueSelecte = new OrclassRisque();
//        risqueFamille = new OrclassRisqueFamille();
//        natureMaladie = new OrclassNatureMaladie();
//        police = new OrclassPolice();
//        sinistreMaladieSelected = new OrclassSinistreMaladie();
//        detailPolicePlafondSelected = new OrclassDetailPolicePlafond();
//        sinistreMaladie_infos = new OrclassSinistreMaladie();
//        sinistre = new OrclassSinistre();
//        facture = new OrclasseFacture();
//        bordereau = new OrclassBordereau();
//    }
//
//    public void chargeInfosPrestation(OrclassSinistreMaladie item) {
//        if (item != null && item.getIdPrestation() != null && item.getIdPrestation().getId() != null) {
//            this.setSinistreMaladie_infos(item);
//        }
//
//    }
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
////        listeSinistre = sinistreDao.listeSinistreByCompagnie(entreprise);
////        if (user != null && Objects.equals(user.getAllAccessForIntermediaire(), Boolean.TRUE)) {
////            listePolice = policeDao.listeContraByCompagny(entreprise);
////        } else {
////            listePolice = policeDao.listeContraValideByAgence(user.getIdIntermediaire(), entreprise);
////        }
//        // charge les prestataire 
//        listePrestataire = prestataireDao.listePrstataireByCompagnie(entreprise);
//        listeNatureMaladie = natureMaladieDao.listeNatureMaladieByCompagnie(entreprise);
//        exercice = IdleDate.getYear(new Date());
//        exercice_sinistre = exerciceDao.findEntityHavingValue("code", exercice);
//
//        this.initailiseTableSinistreMaladie();
//
//    }
//
//    public void selecteSinistreByNumeroSinistre() {
//        if (code_agence == 0 || code_exercice == 0 || numero_sinistre == 0) {
//            PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage("VALUE IS EMPTY", "PLEASE TRY AGAINST..."));
//            PrimeFaces.current().executeScript("PF('manageSinistreDialog').show()");
//            return;
//        }
//        if (code_exercice != 0 && code_agence != 0 && numero_sinistre != 0) {
//            sinistre = sinistreDao.finKey(entreprise, "" + numero_sinistre, code_exercice, "" + code_agence);
//            if (sinistre == null) {
//                sinistre = new OrclassSinistre();
//                PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage("THE VALUE NOT EXISTS", "PLEASE TRY AGAINST..."));
//                return;
//            }
//            if (sinistre != null && sinistre.getId() != null && Objects.equals(sinistre.getValide_pec(), Boolean.TRUE)) {
//                this.init();
//
//                PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage("OPERATION IS VALID", "PLEASE PLEASE SELECT OPERATION NOT VALID..."));
//                this.updateTableSinistreMaladie();
//                PrimeFaces.current().ajax().update(":form1");
//                return;
//            }
//            this.setPolice(sinistre.getIdPolice());
//            this.setRisqueSelecte(sinistre.getIdRisque());
//            this.setGarantie(sinistre.getIdGarantie());
//            this.setRubrique(sinistre.getIdRubrique());
//            this.setPrestataire(sinistre.getIdPrestataire());
//            this.setAvenant(sinistre.getIdAvenant() == null ? new OrclassAvenant() : sinistre.getIdAvenant());
//            this.setGroupePolice(sinistre.getIdGroupePolice());
//            this.setNatureMaladie(sinistre.getIdNatureMaladie() == null ? new OrclassNatureMaladie() : sinistre.getIdNatureMaladie());
//            this.setRisqueFamille(sinistre.getIdRisqueFamille() == null ? new OrclassRisqueFamille() : sinistre.getIdRisqueFamille());
//            this.setFacture(sinistre.getIdFacture() == null ? new OrclasseFacture() : sinistre.getIdFacture());
//            this.setBordereau(sinistre.getIdBordereau() == null ? new OrclassBordereau() : sinistre.getIdBordereau());
//            this.setPolice_value("" + police.getPolice());
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
//            sinistreMaladieSelected = new OrclassSinistreMaladie();
//            sinistreMaladieSelected.setIdEntreprise(entreprise);
//            sinistreMaladieSelected.setIdPrestation(new OrclassPrestation());
//            listeSinistreMaladie.add(sinistreMaladieSelected);
//            // chageons les donnes
//            if (groupePolice == null || groupePolice.getId() == null) {
//
//                listeRisque = (avenant == null || avenant.getId() == null) ? risqueDao.listeRisqueByPoliceHaveGroup(entreprise, police) : risqueDao.listeRisqueByPoliceHaveAvenant(entreprise, police, avenant);
//                listeGarantie = (avenant == null || avenant.getId() == null) ? policeGarantieDao.listeGarantiesNotHaveAvenant(entreprise, police) : policeGarantieDao.listeGarantiesHaveAvenant(entreprise, police, avenant);
//            } else {
//                listeRisque = (avenant == null || avenant.getId() == null) ? risqueDao.listeRisqueByGroupe(entreprise, groupePolice.getIdRefGroupe(), police) : risqueDao.listeRisqueByGroupe(entreprise, groupePolice.getIdRefGroupe(), police, avenant);
//                listeGarantie = (avenant == null || avenant.getId() == null) ? policeGarantieDao.listeGarantiesByPoliceGarantiesNotHaveAvenant(entreprise, police, groupePolice.getIdRefGroupe()) : policeGarantieDao.listeGarantiesByPoliceGarantiesNotHaveAvenant(entreprise, police, groupePolice.getIdRefGroupe(), avenant);
//
//            }
//
//            listeRisqueFamille = (avenant == null || avenant.getId() == null) ? risqueFamilleDao.listeFamilleRisqueByRisqueHavePoliceForGroup(entreprise, police, risqueSelecte) : risqueFamilleDao.listeRisqueFamilleFoPoliceByAvenant(entreprise, police, avenant, risqueSelecte);
//            listePrestataire = prestataireDao.listePrstataireByCompagnie(entreprise);
//            listeNatureMaladie = natureMaladieDao.listeNatureMaladieByCompagnie(entreprise);
//            listeRubrique = detailPolicePlafondDao.listeRubrique(police);
//
//            if (listeRisque.isEmpty() && risqueSelecte != null && risqueSelecte.getId() != null) {
//                listeRisque.add(risqueSelecte);
//            }
//            chargePrestationByRubrique();
//            if (listeGarantie.isEmpty() && garantie!=null && garantie.getId()!=null) {
//                listeGarantie = policeGarantieDao.listeGarantiesHavePoliceGarantie(entreprise, police);
//
//                if (avenant != null && avenant.getId() != null) {
//                    for (OrclassPoliceGarantie pgg : policeGarantieDao.listeGarantiesForPoliceHaveAvenant(entreprise, police, avenant)) {
//                        if (Objects.equals(pgg.getRetire_de_la_police(), Boolean.TRUE)) {
//                            policeGarantie = policeGarantieDao.find(pgg.getCodeid_retirer());
//                            if (policeGarantie != null && policeGarantie.getId() != null) {
//                                listeGarantie.remove(policeGarantie.getIdGarantie());
//                            }
//                        } else if (Objects.equals(pgg.getModifier(), Boolean.TRUE)) {
//                            policeGarantie = policeGarantieDao.OrclassPoliceGarantieByLibelle(entreprise, police, pgg.getIdGarantie());
//                            if (policeGarantie != null && policeGarantie.getId() != null) {
//                                listeGarantie.remove(policeGarantie.getIdGarantie());
//                            }
//                        } else if (Objects.equals(pgg.getAjouter(), Boolean.TRUE)) {
//                            listeGarantie.add(policeGarantie.getIdGarantie());
//                        }
//                    }
//                }
//            }
//
//            souscripteur = police.getIdAssure().getRaison_social() == null ? police.getIdAssure().getNom() : police.getIdAssure().getRaison_social();
//            this.updateTableSinistreMaladie();
//            PrimeFaces.current().ajax().update(":form1");
//
//        }
//    }
//
//    //charger les details prestation
//    public void chargePrestationDetail() {
//        if (rubrique != null && rubrique.getId() != null && police != null && police.getId() != null) {
//            listesDetailPolicePlafond = detailPolicePlafondDao.listeDetailPlafondByRubrique(police, rubrique);
//            this.updateTableDetailPrestation();
//            listesDetailPolicePlafondLocal.clear();
//        }
//    }
//
//    /*
//    valide la prise en charge
//     */
//    public void validPEC() {
//        valider = Boolean.TRUE;
//        sinistre.setSort(SortSinistre.fermer);
//        sinistre.setRejetter(Boolean.FALSE);
//        this.createSinistreMaladie();
//    }
//
//    public void enregistreOrPrintPEC() {
//        valider = Boolean.FALSE;
//        sinistre.setSort(SortSinistre.ouvert);
//        sinistre.setRejetter(Boolean.FALSE);
//        this.createSinistreMaladie();
//        /*
//        servive print 
//         */
//    }
//
//    public void rejetterPEC() {
//        valider = Boolean.FALSE;
//        sinistre.setSort(SortSinistre.fermer);
//        sinistre.setRejetter(Boolean.TRUE);
//        motif_rejet = "";
//        observation = "";
//        indice = -1;
//        PrimeFaces.current().executeScript("PF('manageMotifDialog').show()");
////        this.createSinistreMaladie();
//    }
//
//    public void updateTableDetailPrestation() {
//        UIComponent table = FacesContext.getCurrentInstance().getViewRoot().findComponent("form2:idprestation");
//        table.setValueExpression("sortBy", null);
//
//        PrimeFaces.current().executeScript("PF('prestationTable').clearFilters();");
////         PrimeFaces.current().ajax().update("form1_1");
//
//    }
//
//    public void initailiseTableSinistreMaladie() {
//        if (listeSinistreMaladie.isEmpty()) {
//            sinistreMaladieSelected = new OrclassSinistreMaladie();
//            sinistreMaladieSelected.setIdEntreprise(entreprise);
//            sinistreMaladieSelected.setIdPrestation(new OrclassPrestation());
//            listeSinistreMaladie.add(sinistreMaladieSelected);
//            this.updateTableSinistreMaladie();
//        }
//
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
//    public void chargeDetailPrestation() {
//        if (detailPolicePlafondSelected != null && detailPolicePlafondSelected.getId() != null) {
//            if (!listesDetailPolicePlafondLocal.isEmpty()) {
//                if (listesDetailPolicePlafondLocal.contains(detailPolicePlafondSelected)) {
//                    PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage("PLEASE CHANGE VALUE", "The value is already"));
//                    return;
//                }
//            }
//
//            sinistreMaladieSelected = new OrclassSinistreMaladie();
//            sinistreMaladieSelected = listeSinistreMaladie.get(0);
//            sinistreMaladieSelected.setIdPrestation(detailPolicePlafondSelected.getIdPrestation());
//            sinistreMaladieSelected.setIdEntreprise(entreprise);
//            sinistreMaladieSelected.setTaux(detailPolicePlafondSelected.getTaux().doubleValue());
//            if (detailPolicePlafondSelected.getForfait().intValue() != 0) {
//                sinistreMaladieSelected.setForfait(detailPolicePlafondSelected.getForfait().doubleValue());
//            } else if (detailPolicePlafondSelected.getBarem().intValue() != 0) {
//                sinistreMaladieSelected.setForfait(detailPolicePlafondSelected.getBarem().doubleValue());
//            } else {
//                sinistreMaladieSelected.setForfait(detailPolicePlafondSelected.getPlafond().doubleValue());
//            }
////            sinistreMaladieSelected.setForfait(detailPolicePlafondSelected.getBarem().intValue()==0 ? detailPolicePlafondSelected.getBarem().doubleValue(): detailPolicePlafondSelected.getPlafond().intValue()==0 ? detailPolicePlafondSelected.getForfait().doubleValue():detailPolicePlafondSelected.getPlafond().doubleValue());
//            listeSinistreMaladie.set(0, sinistreMaladieSelected);
//            sinistreMaladieSelected = new OrclassSinistreMaladie();
//            sinistreMaladieSelected.setIdPrestation(new OrclassPrestation());
//            listeSinistreMaladie.add(sinistreMaladieSelected);
//
//            this.reverseListeSinistreMaladie();
//            this.updateTableSinistreMaladie();
//            listesDetailPolicePlafondLocal.add(detailPolicePlafondSelected);
//        }
//    }
//
//    public void onTabChange(TabChangeEvent event) {
//        FacesContext ctx = FacesContext.getCurrentInstance();
//        Locale myLoc = ctx.getViewRoot().getLocale();
//        List<OrclassMedicamentSinistreMaladie> listesMedicamentSinistreMaladie = new ArrayList<>();
//        List<OrclassIntervenant> listesIntervenant = new ArrayList<>();
//        List<OrclassEvenement> listesEvenement = new ArrayList<>();
//        List<OrclassCaracteristiques> listesCaracteristiques = new ArrayList<>();
//        int nombre_affiche = 0;//permet de verifier si une ligne est a l etat affiche puis il y aura ou tous les elements seront affiche = false lors qu on changera l etat de prestation
//
//        if ("idmedicament".equals(event.getTab().getId())) {
//            if (sinistreMaladie_infos == null || sinistreMaladie_infos.getIdPrestation() == null || sinistreMaladie_infos.getIdPrestation().getId() == null) {
//                PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage("FATAL ERROR ", "PLEASE SELECT THE VALUE OF TABLE PRESTATION..."));
//                PrimeFaces.current().executeScript("PF('accordP').select(0);");
//                return;
//            }
//            if (sinistre != null && sinistre.getId() != null) {
//                if (!this.listesMedicamentSinistreMaladie.isEmpty() && this.listesMedicamentSinistreMaladie.size() > 1) {
//                    listesMedicamentSinistreMaladie = new ArrayList<>(this.listesMedicamentSinistreMaladie);
//                }
//
//                this.listesMedicamentSinistreMaladie = medicamentSinistreMaladieDao.listeMedicaleBySinistreMaladie(entreprise, sinistreMaladie_infos);
//                if (!listesMedicamentSinistreMaladie.isEmpty() && !this.listesMedicamentSinistreMaladie.isEmpty()) {
//                    for (OrclassMedicamentSinistreMaladie smd : listesMedicamentSinistreMaladie) {
//                        if (smd.getId() != null) {
//                            continue;
//                        }
//                        this.listesMedicamentSinistreMaladie.add(smd);
//                    }
////                    this.listesMedicamentSinistreMaladie.addAll(listesMedicamentSinistreMaladie);
//                    this.reverseListeSinistreMaladie();
//                }
//            }
//            if (this.listesMedicamentSinistreMaladie.isEmpty()) {
//                OrclassMedicamentSinistreMaladie medicamentSinistreMaladie = new OrclassMedicamentSinistreMaladie();
//                medicamentSinistreMaladie.setIdSinistreMaladie(sinistreMaladie_infos);
//                medicamentSinistreMaladie.setAfficher(Boolean.TRUE);
//                this.listesMedicamentSinistreMaladie.add(medicamentSinistreMaladie);
//            } else {
//                listesMedicamentSinistreMaladie = new ArrayList<>(this.listesMedicamentSinistreMaladie);
//                this.listesMedicamentSinistreMaladie.clear();
//                for (OrclassMedicamentSinistreMaladie md : listesMedicamentSinistreMaladie) {
//                    if (md.getIdSinistreMaladie().getIdPrestation().getLibelle().equals(sinistreMaladie_infos.getIdPrestation().getLibelle())) {
//                        md.setAfficher(Boolean.TRUE);
//                        this.listesMedicamentSinistreMaladie.add(md);
//                        nombre_affiche = nombre_affiche + 1;
//                    } else {
//                        md.setAfficher(Boolean.FALSE);
//                        this.listesMedicamentSinistreMaladie.add(md);
//                    }
//
//                }
//                if (nombre_affiche == 0) {
//                    OrclassMedicamentSinistreMaladie medicamentSinistreMaladie = new OrclassMedicamentSinistreMaladie();
//                    medicamentSinistreMaladie.setIdSinistreMaladie(sinistreMaladie_infos);
//                    medicamentSinistreMaladie.setAfficher(Boolean.TRUE);
//                    this.listesMedicamentSinistreMaladie.add(medicamentSinistreMaladie);
//                }
//            }
//
//        } else if ("idintervenant".equals(event.getTab().getId())) {
//
//            if (sinistreMaladie_infos == null || sinistreMaladie_infos.getIdPrestation() == null || sinistreMaladie_infos.getIdPrestation().getId() == null) {
//                PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage("FATAL ERROR ", "PLEASE SELECT THE VALUE OF TABLE PRESTATION..."));
//                PrimeFaces.current().executeScript("PF('accordP').select(0);");
//                return;
//            }
//            if (sinistre != null && sinistre.getId() != null) {
//                if (!this.listesIntervenant.isEmpty()) {
//                    listesIntervenant = new ArrayList<>(this.listesIntervenant);
//                }
//                this.listesIntervenant = new ArrayList<>();
//                for (OrclassIntervenantSinistreMaladie intv : intervenantSinistreMaladieDao.listIntervenantsBySinistreMaladie(entreprise, sinistreMaladie_infos)) {
//                    OrclassIntervenant intervenant = intv.getIdIntervenant();
//                    intervenant.setSinistreMaladie(sinistreMaladie_infos);
//                    intervenant.setAfficher(Boolean.TRUE);
//                    intervenant.setHoraire(intv.getHoraire());
//                    intervenant.setDesignation(intv.getDesignation());
//                    intervenant.setNature(intv.getNature());
//                    intervenant.setObservation(intv.getObservation());
//                    intervenant.setIdEntreprise(intv.getIdEntreprise());
//                    intervenant.setDesignation(intv.getDesignation());
//                    this.listesIntervenant.add(intervenant);
//                }
//                for (OrclassIntervenant intve : listesIntervenant) {
//                    if (intve.getId() != null && intve.getSinistreMaladie().getId() != null) {
//                        continue;
//                    }
//                    this.listesIntervenant.add(intve);
//                }
//            }
//
//            if (this.listesIntervenant.isEmpty()) {
//                OrclassIntervenant intervenant = new OrclassIntervenant();
//                intervenant.setSinistreMaladie(sinistreMaladie_infos);
//                intervenant.setAfficher(Boolean.TRUE);
//                this.listesIntervenant.add(intervenant);
//            } else {
//                listesIntervenant = new ArrayList<>(this.listesIntervenant);
//                this.listesIntervenant.clear();
//                for (OrclassIntervenant md : listesIntervenant) {
//                    if (md.getSinistreMaladie().getIdPrestation().getLibelle().equals(sinistreMaladie_infos.getIdPrestation().getLibelle())) {
//                        md.setAfficher(Boolean.TRUE);
//                        this.listesIntervenant.add(md);
//                        nombre_affiche = nombre_affiche + 1;
//                    } else {
//                        md.setAfficher(Boolean.FALSE);
//                        this.listesIntervenant.add(md);
//                    }
//
//                }
//                if (nombre_affiche == 0) {
//                    OrclassIntervenant intervenant = new OrclassIntervenant();
//                    intervenant.setSinistreMaladie(sinistreMaladie_infos);
//                    intervenant.setAfficher(Boolean.TRUE);
//                    this.listesIntervenant.add(intervenant);
//                }
//            }
//            this.updateTableIntervenant();
//
//        } else if ("idevenement".equals(event.getTab().getId())) {
//            if (sinistreMaladie_infos == null || sinistreMaladie_infos.getIdPrestation() == null || sinistreMaladie_infos.getIdPrestation().getId() == null) {
//                PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage("FATAL ERROR ", "PLEASE SELECT THE VALUE OF TABLE PRESTATION..."));
//                PrimeFaces.current().executeScript("PF('accordP').select(0);");
//                return;
//            }
//
//            if (sinistre != null && sinistre.getId() != null) {
//                if (!this.listesEvenement.isEmpty()) {
//                    listesEvenement = new ArrayList<>(this.listesEvenement);
//                }
//                this.listesEvenement = new ArrayList<>();
//                for (OrclassEvenementSinistreMaladie ev : evenementSinistreMaladieDao.listEnementBySinistreMaladie(entreprise, sinistreMaladie_infos)) {
//                    OrclassEvenement evenement = ev.getIdEvenement();
//                    evenement.setSinistreMaladie(sinistreMaladie_infos);
//                    evenement.setTraite(ev.getTraite());
//                    evenement.setObservation(ev.getObservation());
//                    evenement.setIdEntreprise(ev.getIdEntreprise());
//                    evenement.setDate_evenement(ev.getDate_evenement());
//                    evenement.setAfficher(Boolean.TRUE);
//                    this.listesEvenement.add(evenement);
//                }
//                for (OrclassEvenement e : listesEvenement) {
//                    if (e.getId() != null && e.getSinistreMaladie().getId() != null) {
//                        continue;
//                    }
//                    this.listesEvenement.add(e);
//                }
//            }
//            if (this.listesEvenement.isEmpty()) {
//                OrclassEvenement evenement = new OrclassEvenement();
//                evenement.setSinistreMaladie(sinistreMaladie_infos);
//                evenement.setAfficher(Boolean.TRUE);
//                this.listesEvenement.add(evenement);
//            } else {
//                listesEvenement = new ArrayList<>(this.listesEvenement);
//                this.listesEvenement.clear();
//                for (OrclassEvenement md : listesEvenement) {
//                    if (md.getSinistreMaladie().getIdPrestation().getLibelle().equals(sinistreMaladie_infos.getIdPrestation().getLibelle())) {
//                        md.setAfficher(Boolean.TRUE);
//                        this.listesEvenement.add(md);
//                        nombre_affiche = nombre_affiche + 1;
//                    } else {
//                        md.setAfficher(Boolean.FALSE);
//                        this.listesEvenement.add(md);
//                    }
//
//                }
//                if (nombre_affiche == 0) {
//                    OrclassEvenement evenement = new OrclassEvenement();
//                    evenement.setSinistreMaladie(sinistreMaladie_infos);
//                    evenement.setAfficher(Boolean.TRUE);
//                    this.listesEvenement.add(evenement);
//                }
//            }
//            this.updateTableEvenement();
//
//        } else if ("icaracteristique".equals(event.getTab().getId())) {
//            if (sinistreMaladie_infos == null || sinistreMaladie_infos.getIdPrestation() == null || sinistreMaladie_infos.getIdPrestation().getId() == null) {
//                PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage("FATAL ERROR ", "PLEASE SELECT THE VALUE OF TABLE PRESTATION..."));
//                PrimeFaces.current().executeScript("PF('accordP').select(0);");
//                return;
//            }
//            if (sinistre != null && sinistre.getId() != null) {
//                if (!this.listesCaracteristiques.isEmpty()) {
//                    listesCaracteristiques = new ArrayList<>(this.listesCaracteristiques);
//                }
//                this.listesCaracteristiques = new ArrayList<>();
//                for (OrclassCaracteristiqueSinistreMaladie cmd : caracteristiqueSinistreMaladieDao.listCaractreristiqueBySinistreMaladie(entreprise, sinistreMaladie_infos)) {
//                    OrclassCaracteristiques caract = cmd.getIdCaracteristiques();
//                    caract.setSinistreMaladie(sinistreMaladie_infos);
//
//                    caract.setAfficher(Boolean.TRUE);
//
//                    this.listesCaracteristiques.add(caract);
//                }
//                for (OrclassCaracteristiques c : listesCaracteristiques) {
//                    if (c.getId() != null && c.getSinistreMaladie().getId() != null) {
//                        continue;
//                    }
//                    this.listesCaracteristiques.add(c);
//                }
//            }
//            if (this.listesCaracteristiques.isEmpty()) {
//                OrclassCaracteristiques caracteristiques = new OrclassCaracteristiques();
//                caracteristiques.setSinistreMaladie(sinistreMaladie_infos);
//                caracteristiques.setAfficher(Boolean.TRUE);
//                this.listesCaracteristiques.add(caracteristiques);
//            } else {
//                listesCaracteristiques = new ArrayList<>(this.listesCaracteristiques);
//                this.listesCaracteristiques.clear();
//                for (OrclassCaracteristiques md : listesCaracteristiques) {
//                    if (md.getSinistreMaladie().getIdPrestation().getLibelle().equals(sinistreMaladie_infos.getIdPrestation().getLibelle())) {
//                        md.setAfficher(Boolean.TRUE);
//                        this.listesCaracteristiques.add(md);
//                        nombre_affiche = nombre_affiche + 1;
//                    } else {
//                        md.setAfficher(Boolean.FALSE);
//                        this.listesCaracteristiques.add(md);
//                    }
//
//                }
//                if (nombre_affiche == 0) {
//                    OrclassCaracteristiques caracteristiques = new OrclassCaracteristiques();
//                    caracteristiques.setSinistreMaladie(sinistreMaladie_infos);
//                    caracteristiques.setAfficher(Boolean.TRUE);
//                    this.listesCaracteristiques.add(caracteristiques);
//                }
//            }
//
//        }
////        PrimeFaces.current().ajax().update("form1:tabprincipal");
//
//    }
//
////    public void init() {
////        sinistre = new OrclassSinistre();
////        sinistreMaladie = new OrclassSinistreMaladie();
////        sinistreMaladieSelected = new OrclassSinistreMaladie();
////        sinistreMaladie_infos = new OrclassSinistreMaladie();
////        police = new OrclassPolice();
////        avenant = new OrclassAvenant();
////        risqueSelecte = new OrclassRisque();
////        risqueFamille = new OrclassRisqueFamille();
////        prestataire = new OrclassPrestataire();
////        natureMaladie = new OrclassNatureMaladie();
////        rubrique = new OrclassRubrique();
////        garantie = new OrclassGarantie();
////        listeSinistreMaladie = new ArrayList<>();
////        listesMedicamentSinistreMaladie = new ArrayList<>();
////        listesIntervenant = new ArrayList<>();
////        listesEvenement = new ArrayList<>();
////        listesEvenement = new ArrayList<>();
////        listesCaracteristiques = new ArrayList<>();
////        listeGarantie = new ArrayList<>();
////        listeRisque = new ArrayList<>();
////        listeGroupePolice = new ArrayList<>();
////        listeRisqueFamille = new ArrayList<>();
////        listeRubrique = new ArrayList<>();
////                this.souscripteur = "";
////        
////    }
//
//    /*
//    creation des sinistres
//     */
//    public void createSinistreMaladie() {
//
//        FacesContext ctx = FacesContext.getCurrentInstance();
//        ExternalContext extContext = ctx.getExternalContext();
//        Locale myLoc = ctx.getViewRoot().getLocale();
//        Boolean reponse = Boolean.FALSE;
//
//        try {
//
//            if (sinistre.getDate_Declaration() == null) {
//                PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage("FATAL ERROR ", "PLEASE WRITE DECLARATION DATE..."));
//                PrimeFaces.current().executeScript("PF('accordP').select(0);");
//                return;
//            }
//            if (sinistre.getDate_Survenu() == null) {
//                PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage("FATAL ERROR ", "PLEASE WRITE DATE OCCURED ON..."));
//                PrimeFaces.current().executeScript("PF('accordP').select(0);");
//                return;
//            }
//            if (risqueSelecte == null || risqueSelecte.getId() == null) {
//                PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage("FATAL ERROR ", "PLEASE WRITE THE MEMBER..."));
//                PrimeFaces.current().executeScript("PF('accordP').select(0);");
//                return;
//            }
//            if (prestataire == null || prestataire.getId() == null) {
//                PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage("FATAL ERROR ", "PLEASE WRITE THE SERVICE PROVIDER..."));
//                PrimeFaces.current().executeScript("PF('accordP').select(0);");
//                return;
//            }
//            if (rubrique == null || rubrique.getId() == null) {
//                PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage("FATAL ERROR ", "PLEASE WRITE THE HEADING..."));
//                PrimeFaces.current().executeScript("PF('accordP').select(0);");
//                return;
//            }
//            if (garantie == null || garantie.getId() == null) {
//                PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage("FATAL ERROR ", "PLEASE WRITE THE GUARANTE..."));
//                PrimeFaces.current().executeScript("PF('accordP').select(0);");
//                return;
//            }
//            if (sinistre.getIdRegistreSinistre() == null || sinistre.getIdRegistreSinistre().getId() == null) {
//                PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage("FATAL ERROR ", "PLEASE REGISTRY IS NULL..."));
//                PrimeFaces.current().executeScript("PF('accordP').select(0);");
//                return;
//            }
//            if (sinistre != null && sinistre.getId() != null) {
//                reponse = serviceSinistre.updateSinistreMaladie(entreprise, police, avenant, sinistre, risqueSelecte, risqueFamille, prestataire, natureMaladie, rubrique, garantie, groupePolice, listeSinistreMaladie, listesMedicamentSinistreMaladie, listesIntervenant, listesEvenement, listesCaracteristiques, valider, user);
//
//            } else {
//                reponse = serviceSinistre.createSinistreMaladie(entreprise, police, avenant, sinistre, risqueSelecte, risqueFamille, prestataire, natureMaladie, rubrique, garantie, groupePolice, listeSinistreMaladie, listesMedicamentSinistreMaladie, listesIntervenant, listesEvenement, listesCaracteristiques, valider, user);
//
//            }
//
//            if (Objects.equals(reponse, Boolean.TRUE)) {
//                GlobalFonctions.displayMessages("msg", ctx, FacesMessage.SEVERITY_INFO, "sinistre", Success.INSERT_SUCCESS + "", new Object[]{sinistre.getNumero_sinistre()});
//                this.reload();
//            } else {
//                GlobalFonctions.displayMessages("msg", ctx, FacesMessage.SEVERITY_FATAL, "sinistre", exception.Error.INSERT_ERROR + "", new Object[]{sinistre.getNumero_sinistre()});
//
//            }
//        } catch (GlobalException e) {
//            GlobalFonctions.displayMessages("msg", ctx, FacesMessage.SEVERITY_ERROR, sinistre.getNumero_sinistre(), e.getCode(), e.getParam());
//            //throw e;
//        } catch (org.hibernate.exception.ConstraintViolationException ejb) {
//            GlobalFonctions.displayMessages("msg", ctx, FacesMessage.SEVERITY_WARN, ejb.getCause().getClass().getSimpleName(), exception.Error.DUPLICATE_ERROR_INSERT + "", null);
//            throw ejb;
//        } catch (Throwable th) {
//            GlobalFonctions.displayMessages("msg", ctx, FacesMessage.SEVERITY_FATAL, "police", exception.Error.FATAL_ERROR + "", new Object[]{"police"});
//            logger.error("Erreur Survenu", th);
//        }
//
//    }
//
//    public void addRowForMedicament() {
//        if (sinistreMaladie_infos != null && sinistreMaladie_infos.getIdPrestation() != null && sinistreMaladie_infos.getIdPrestation().getId() != null) {
//            OrclassMedicamentSinistreMaladie medicamentSinistreMaladie = new OrclassMedicamentSinistreMaladie();
//            medicamentSinistreMaladie.setIdSinistreMaladie(sinistreMaladie_infos);
//            medicamentSinistreMaladie.setAfficher(Boolean.TRUE);
//            listesMedicamentSinistreMaladie.add(medicamentSinistreMaladie);
//        }
//    }
//
//    public void removeRowForMedicament(int indice) {
//        listesMedicamentSinistreMaladie.remove(indice);
//    }
//
//    public void addRowForIntervenant() {
//        if (sinistreMaladie_infos != null && sinistreMaladie_infos.getIdPrestation() != null && sinistreMaladie_infos.getIdPrestation().getId() != null) {
//            OrclassIntervenant intervenant = new OrclassIntervenant();
//            intervenant.setSinistreMaladie(sinistreMaladie_infos);
//
//            intervenant.setAfficher(Boolean.TRUE);
//            listesIntervenant.add(intervenant);
//            this.updateTableIntervenant();
//        }
//    }
//
//    public void removeRowForIntervenant(int indice) {
//        OrclassIntervenant intv = listesIntervenant.get(indice);
//        if (intv != null && intv.getId() != null && intv.getSinistreMaladie() != null && intv.getSinistreMaladie().getId() != null) {
//            OrclassIntervenantSinistreMaladie intvmd = intervenantSinistreMaladieDao.intervenantsBySinistreMaladie(entreprise, intv.getSinistreMaladie(), intv);
//            if (intvmd != null && intvmd.getId() != null) {
//                intervenantSinistreMaladieDao.delete(intvmd);
//
//            }
//        }
//        listesIntervenant.remove(indice);
//        this.updateTableIntervenant();
//    }
//
//    public void addRowForEvenement() {
//        if (sinistreMaladie_infos != null && sinistreMaladie_infos.getIdPrestation() != null && sinistreMaladie_infos.getIdPrestation().getId() != null) {
//            OrclassEvenement evenement = new OrclassEvenement();
//            evenement.setSinistreMaladie(sinistreMaladie_infos);
//
//            evenement.setAfficher(Boolean.TRUE);
//            listesEvenement.add(evenement);
//            this.updateTableEvenement();
//        }
//    }
//
//    public void removeRowForEvenement(int indice) {
//        OrclassEvenement event = listesEvenement.get(indice);
//        OrclassEvenementSinistreMaladie eventsin = null;
//        if (event != null && event.getId() != null && event.getSinistreMaladie() != null && event.getSinistreMaladie().getId() != null) {
//            eventsin = evenementSinistreMaladieDao.evenementBySinistreMaladie(entreprise, event.getSinistreMaladie(), event);
//            if (eventsin != null && eventsin.getId() != null) {
//                evenementSinistreMaladieDao.delete(eventsin);
//            }
//
//        }
//        listesEvenement.remove(indice);
//        this.updateTableEvenement();
//    }
//
//    public void addRowForCaracteristique() {
//        if (sinistreMaladie_infos != null && sinistreMaladie_infos.getIdPrestation() != null && sinistreMaladie_infos.getIdPrestation().getId() != null) {
//            OrclassCaracteristiques caracteristiques = new OrclassCaracteristiques();
//            caracteristiques.setSinistreMaladie(sinistreMaladie_infos);
//
//            caracteristiques.setAfficher(Boolean.TRUE);
//            listesCaracteristiques.add(caracteristiques);
//
//        }
//    }
//
//    public void removeRowForCaracteristique(int indice) {
//        OrclassCaracteristiques c = listesCaracteristiques.get(indice);
//        OrclassCaracteristiqueSinistreMaladie csin = null;
//        if (c != null && c.getId() != null && c.getSinistreMaladie() != null && c.getSinistreMaladie().getId() != null) {
//            csin = caracteristiqueSinistreMaladieDao.caracteristiqueBySinistreMaladie(entreprise, c.getSinistreMaladie(), c);
//            if (csin != null && csin.getId() != null) {
//                caracteristiqueSinistreMaladieDao.delete(csin);
//            }
//
//        }
//        listesCaracteristiques.remove(indice);
//    }
//
//    public void calculMontantRembMedical(OrclassMedicamentSinistreMaladie item, int indice) {
//        if (item != null && item.getMontant() != null && item.getTaux() != null && item.getQuantie() != null) {
//            BigDecimal montant = item.getQuantie().multiply(item.getMontant());
//            montant = (montant.multiply(item.getTaux()));
//            montant = montant.divide(BigDecimal.valueOf(100.0));
//            item.setMontant_remb(montant);
//            listesMedicamentSinistreMaladie.set(indice, item);
//        }
//    }
//
//    public void calculDebourReel(OrclassSinistreMaladie item, int indice) {
//        if (item.getNombre_acte_reel() != null && item.getNombre_acte_reel().intValue() != 0 && item.getMontant_unitant() != null && item.getMontant_unitant().intValue() != 0) {
//            item.setDeboursement_reel((item.getNombre_acte_reel() * item.getNombre_cle_reel()) * (item.getMontant_unitant()));
//            listeSinistreMaladie.set(indice, item);
//        }
//    }
//
//    public void calculDebourRemboursement(OrclassSinistreMaladie item, int indice) {
//        if (item.getNombre_acte_remb() != null && item.getNombre_acte_remb().intValue() != 0 && item.getMontant_unitant() != null && item.getMontant_unitant().intValue() != 0) {
//            item.setBase_remboursement((item.getNombre_acte_remb() * item.getNombre_cle_remb()) * (item.getMontant_unitant()));
//            listeSinistreMaladie.set(indice, item);
//
//        }
//    }
//
//    public void calculMontantRemboursement(OrclassSinistreMaladie item, int indice) {
//        if (item.getTaux().intValue() == 100) {
//            if (item.getBase_remboursement() != null && item.getBase_remboursement().intValue() != 0 && item.getRemb_externe() != null && item.getMontant_exclure() != null) {
//                item.setMontant_remb(item.getBase_remboursement() - (item.getRemb_externe()) - item.getMontant_exclure());
//                listeSinistreMaladie.set(indice, item);
//            }
//
//        } else {
//            if (item.getBase_remboursement() != null && item.getBase_remboursement().intValue() != 0 && item.getRemb_externe() != null) {
//                Double montant_a_paye = (item.getBase_remboursement() * (item.getTaux().doubleValue())) / 100.0;
//                item.setMontant_remb(montant_a_paye - (item.getRemb_externe()) - item.getMontant_exclure());
//                listeSinistreMaladie.set(indice, item);
//            }
//        }
//
//    }
//
//    public void reverseListeSinistreMaladie() {
//
//        List<OrclassSinistreMaladie> result = new ArrayList<>();
//        for (int i = listeSinistreMaladie.size() - 1; i >= 0; i--) {
//            result.add(listeSinistreMaladie.get(i));
//        }
//
//        this.setListeSinistreMaladie(result);
//
//    }
//
//    public void selectLastNumerSinistre() {
//        if (police != null && police.getId() != null) {
//            BigInteger lastNumberSinistre = BigInteger.ZERO;
//            OrclassSinistre sinistre = sinistreDao.selectLastSinistreByCompagnieForPolice(entreprise, police);
////            if (sinistre != null && sinistre.getId() != null) {
//            //informont le numero du sinistre a utiliser
//            lastNumberSinistre = new BigInteger(sinistre == null ? "0" : sinistre.getNumero_sinistre().trim());
//            lastNumberSinistre = lastNumberSinistre.add(BigInteger.ONE);
//            this.sinistre = new OrclassSinistre();
//            this.sinistre.setNumero_sinistre(lastNumberSinistre.toString());
//            this.sinistre.setIdIntermediaire(police.getIdIntermediaire());
//            this.sinistre.setIdRegistreSinistre(registreSinistre);
//
////            }
//        }
//    }
//
//    public List<String> completeMethode(String query) {
//        List<String> results = new ArrayList<>();
//        if (query != null && !query.trim().equals("")) {
//            results = caracteristiquesDao.finKeyCaracteristideByLibelleWithfilter(query);
//        }
//
//        return results;
//    }
//
//    public List<BigDecimal> completeText(String query) {
//        List<BigDecimal> results = new ArrayList<>();
//        OrclassPolice p = null;
//        OrclassCategories cat = null;
//        OrclassRegistreProduction registreProduction = null;
//        StringBuilder builder = new StringBuilder();
//        int taille = 0;
//        String[] chaine;
//        if (query.contains("%") == Boolean.TRUE) {
//            chaine = query.split("%");
//            if (chaine.length == 1) {
//                return null;
//            }
//            cat = categoriesDao.findEntityHavingValue("code", chaine[0].trim());
//
//            registreProduction = registreProductionDao.finKeyRegistreProductionByCompagnieWithPrefixe(entreprise, cat.getCode());
//            if (cat != null && cat.getIdCategorie() != null && registreProduction != null && registreProduction.getId() != null) {
//                if (cat.getIdBranche().getLibelle().equals(LibelleBranche.sante) == Boolean.FALSE) {
//                    PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage("ERROR BRANCH", "Branch is not Sante"));
//                    return null;
//                }
//                taille = registreProduction.getPlage_numero() - chaine[1].length();
//                builder.append(chaine[0].trim());
//                for (int i = 0; i < (taille); i++) {
//                    builder.append("0");
//                }
//                builder.append(chaine[1]);
//                query = builder.toString();
//
//            } else {
//                return null;
//            }
//
//        }
//        if (query != null && !query.trim().equals("")) {
//            p = policeDao.finKey(new BigDecimal(query), entreprise);
//            if (p != null && p.getId() != null) {
//                results.add(p.getPolice());
//                this.setNum_police(p.getPolice());
//            }
//
//        }
//
//        return results;
//    }
//
//    public void init() {
//        this.listeRisqueFamille = new ArrayList();
//        this.listeRisque = new ArrayList();
//        this.listeRisque = new ArrayList();
//        this.risque = new OrclassRisque();
//        this.risqueFamille = new OrclassRisqueFamille();
//        this.prestataire = new OrclassPrestataire();
//        this.natureMaladie = new OrclassNatureMaladie();
//        this.rubrique = new OrclassRubrique();
//        this.garantie = new OrclassGarantie();
//        this.souscripteur = "";
//        this.sinistre = new OrclassSinistre();
//
//    }
//
//    public void reload() throws IOException {
//        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
//        ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());
//    }
//
//    public List<SelectItem> getSorts() {
//        List<SelectItem> items = new ArrayList<>();
//
//        FacesContext ctx = FacesContext.getCurrentInstance();
//        Locale myLoc = ctx.getViewRoot().getLocale();
//
//        for (SortSinistre s : SortSinistre.values()) {
//            items.add(new SelectItem(s, LocaleHelper.getLocaleString(RecupBundle.FichierBundle, s.name(), null, myLoc)));
//        }
//
//        return items;
//    }
//
//    public List<SelectItem> getSortSinistreMaladie() {
//        List<SelectItem> items = new ArrayList<>();
//
//        FacesContext ctx = FacesContext.getCurrentInstance();
//        Locale myLoc = ctx.getViewRoot().getLocale();
//
//        for (SortSinistreMaladie s : SortSinistreMaladie.values()) {
//            items.add(new SelectItem(s, LocaleHelper.getLocaleString(RecupBundle.FichierBundle, s.name(), null, myLoc)));
//        }
//
//        return items;
//    }
//// recupere la police avec le numero de police selectionner
//
//    public void chargePoliceByNumPolice() {
//        //initialiser lors de l appelle du numero de police qui montre le choix selectionner
//        this.init();
//        if (num_police != BigDecimal.ZERO && num_police.intValue() != 0) {
//            police = policeDao.finKey(num_police, entreprise);
//            if (police != null && police.getId() != null) {
//                this.selectePolice();
//                return;
//            }
//            PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage("FATAL ERROR", "Police Not Valid or Not Exist"));
//
//        }
//    }
////    choisir sur la police 
//
//    public void selectePolice() {
//        if (this.getPolice() != null && this.getPolice().getId() != null) {
//            // souscripteur
//            if (exercice_sinistre == null || exercice_sinistre.getCode() == null || exercice_sinistre.getCode().longValue() == 0) {
//                PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage("FATAL ERROR", "PLEASE CREATE  EXERCISE..." + exercice));
//                return;
//            }
//            registreSinistre = registreSinistreDao.registreSinistreByExerciceAndAgence(entreprise, exercice_sinistre, police.getIdIntermediaire());
//            if (registreSinistre == null || registreSinistre.getId() == null) {
//                PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage("FATAL ERROR", "PLEASE  CREATE REGISTER OF EXERCISE..." + exercice));
//                return;
//            }
//            if (police.getIdAssure() != null && police.getId() != null) {
//                if (police.getIdAssure().getRaison_social() == null) {
//                    souscripteur = police.getIdAssure().getPrenom() == null ? police.getIdAssure().getNom() : police.getIdAssure().getNom() + " " + police.getIdAssure().getPrenom();
//                } else {
//                    souscripteur = police.getIdAssure().getRaison_social();
//                }
//            }
//            //recuperer le numero de sinistre
//            this.selectLastNumerSinistre();
//            //verifiont si un avenant existe pour cette police
//
//            this.avenant = avenantDao.lastAvenantByPolice(police, entreprise);
//            if (this.avenant == null) {
//                this.setEffet(this.police.getDate_effet());
//                this.setEcheance(this.police.getDate_echeance());
//                this.setAssure(police.getIdAssure());
//
//            } else if (this.avenant != null && this.avenant.getId() != null) {
//                this.setEffet(this.avenant.getDate_effet());
//                this.setEcheance(this.avenant.getDate_echeance());
//                if (this.police.getIdAssure().getRaison_social() != null) {
//                    if (!"".equals(avenant.getRaison_social()) && avenant.getRaison_social() != null) {
//                        assure = assureDao.getLastRowForAgence(police.getIdIntermediaire(), entreprise);
//                        assure.setRaison_social(avenant.getRaison_social());
//
//                    } else {
//                        this.setAssure(police.getIdAssure());
//                    }
//                } else {
//                    if (!"".equals(avenant.getNom()) && avenant.getNom() != null) {
//                        assure = assureDao.getLastRowForAgence(police.getIdIntermediaire(), entreprise);
//                        assure.setNom(avenant.getNom());
//                        if (!"".equals(avenant.getPrenom()) && avenant.getPrenom() != null) {
//                            assure.setNom(avenant.getNom() + " " + avenant.getPrenom());
//                        }
//
//                    } else {
//                        this.setAssure(police.getIdAssure());
//                    }
//                }
//
//            }
//
//            this.setCategories(police.getIdCategories());
//            if (police.getIdConvention() == null) {
//                convention = new OrclassConvention();
//            } else {
//                convention = police.getIdConvention();
//            }
//
//            // recuperation des group de la police 
//            listeGroupePolice = groupePoliceDao.ListeGroupByCompagnieForPolice(entreprise, police);
//            // des recuperations des risque de la police
//            listeGroupePolice = groupePoliceDao.ListeGroupByCompagnieForPolice(entreprise, police);
//            if (listeGroupePolice.isEmpty()) {
//                listeRisque = risqueDao.listeRisqueByPolice(entreprise, police);
//            }
//
//            if (avenant != null && avenant.getId() != null) {
//                for (OrclassRisque rr : risqueDao.listeRisqueByPoliceHaveAvenant(entreprise, police, avenant)) {
//                    if (Objects.equals(rr.getRetire_de_la_police(), Boolean.TRUE)) {
//                        risque = risqueDao.find(rr.getCodeid_retirer());
//                        if (risque != null && risque.getId() != null) {
//                            listeRisque.remove(risque);
//                        }
//
//                    } else if (Objects.equals(rr.getModifier(), Boolean.TRUE)) {
//                        risque = risqueDao.risqueByLibelle(entreprise, police, rr.getLibelle());
//                        if (risque != null && risque.getId() != null) {
//                            listeRisque.remove(risque);
//                            listeRisque.add(rr);
//                        }
//                    } else if (Objects.equals(rr.getAjouter(), Boolean.TRUE)) {
//                        listeRisque.add(rr);
//                    }
//                }
//            }
//
//            //recuperations des garanties pour la police
//            listeGarantie = policeGarantieDao.listeGarantiesHavePoliceGarantie(entreprise, police);
//
//            if (avenant != null && avenant.getId() != null) {
//                for (OrclassPoliceGarantie pgg : policeGarantieDao.listeGarantiesForPoliceHaveAvenant(entreprise, police, avenant)) {
//                    if (Objects.equals(pgg.getRetire_de_la_police(), Boolean.TRUE)) {
//                        policeGarantie = policeGarantieDao.find(pgg.getCodeid_retirer());
//                        if (policeGarantie != null && policeGarantie.getId() != null) {
//                            listeGarantie.remove(policeGarantie.getIdGarantie());
//                        }
//                    } else if (Objects.equals(pgg.getModifier(), Boolean.TRUE)) {
//                        policeGarantie = policeGarantieDao.OrclassPoliceGarantieByLibelle(entreprise, police, pgg.getIdGarantie());
//                        if (policeGarantie != null && policeGarantie.getId() != null) {
//                            listeGarantie.remove(policeGarantie.getIdGarantie());
//                        }
//                    } else if (Objects.equals(pgg.getAjouter(), Boolean.TRUE)) {
//                        listeGarantie.add(policeGarantie.getIdGarantie());
//                    }
//                }
//            }
//            // recuperation rubrique pour la police
//            listeRubrique = detailPolicePlafondDao.listeRubrique(police);
//
//        }
//
//    }
//
//    public void chargeMotifRejet(int index, OrclassSinistreMaladie item) {
//        if (item != null && item.getSort().equals(SortSinistreMaladie.rejetter)) {
//            this.indice = index;
//            this.setSinistreMaladieSelected(item);
//            PrimeFaces.current().executeScript("PF('manageMotifDialog').show()");
//        }
//    }
//
//    public void saveMotifRejet() {
//        if (motif_rejet != null && indice != 0) {
////            sinistreMaladieSelected = listeSinistreMaladie.get(indice);
//            sinistreMaladieSelected.setMotif_sort(motif_rejet);
//            sinistreMaladieSelected.setObservation(observation);
//            listeSinistreMaladie.set(indice, sinistreMaladieSelected);
//        } else if (motif_rejet != null && indice == -1) {
//            sinistre.setMotif_rejet(motif_rejet);
//            sinistre.setObservation(observation);
//            this.createSinistreMaladie();
//        }
//    }
//
////charger les risque parb groupe
//    public void chargeRisqueByGroup() {
//        if (groupePolice != null && groupePolice.getId() != null) {
//            listeRisque = risqueDao.listeRisqueByGroupe(entreprise, groupePolice.getIdRefGroupe(), police);
//            if (avenant != null && avenant.getId() != null) {
//                for (OrclassRisque rr : risqueDao.listeRisqueByPoliceHaveAvenant(entreprise, police, avenant)) {
//                    if (Objects.equals(rr.getRetire_de_la_police(), Boolean.TRUE)) {
//                        risque = risqueDao.find(rr.getCodeid_retirer());
//                        if (risque != null && risque.getId() != null) {
//                            listeRisque.remove(risque);
//                        }
//
//                    } else if (Objects.equals(rr.getModifier(), Boolean.TRUE)) {
//                        risque = risqueDao.risqueByLibelle(entreprise, police, rr.getLibelle());
//                        if (risque != null && risque.getId() != null) {
//                            listeRisque.remove(risque);
//                            listeRisque.add(rr);
//                        }
//                    } else if (Objects.equals(rr.getAjouter(), Boolean.TRUE)) {
//                        listeRisque.add(rr);
//                    }
//                }
//            }
//
//        }
//    }
//
//    //charger les membre de famille pour un adherent choisir
//    public void chargerRisqueFamilleByRisque() {
//        OrclassRisqueFamille rf = null;
//        if (risqueSelecte != null && risqueSelecte.getId() != null && police != null && police.getId() != null) {
//            listeRisqueFamille = risqueFamilleDao.listeFamilleRisqueByRisqueHavePoliceForGroup(entreprise, police, risqueSelecte);
//            if (avenant != null && avenant.getId() != null) {
//                for (OrclassRisqueFamille rff : risqueFamilleDao.allRisqueFamilleByPolice(entreprise, police, risqueSelecte)) {
//                    if (Objects.equals(rff.getRetire_de_la_police(), Boolean.TRUE)) {
//                        rf = risqueFamilleDao.find(rff.getCodeid_retirer());
//                        if (rf != null && rf.getId() != null) {
//                            listeRisqueFamille.remove(rf);
//
//                        }
//                    } else if (Objects.equals(rff.getModifier(), Boolean.TRUE)) {
////                        rf = risqueFamilleDao.risqueFamilleFoPoliceBylibelle(entreprise, police, rff.getNom_membre(), risqueSelecte);
////                        if (rf != null && rf.getId() != null) {
////                            listeRisqueFamille.remove(rf);
//                            listeRisqueFamille.add(rff);
////                        }
//                    } 
////                    else if (Objects.equals(rff.getAjouter(), Boolean.TRUE)) {
////                        listeRisqueFamille.add(rff);
////                    }
//                }
//            }
//
//        }
//    }
//
//    // charger les prestation  pour cette police en des rubrique selectionner
//    public void chargePrestationByRubrique() {
//        OrclassDetailPolicePlafond dpp = null;
//        if (rubrique != null && rubrique.getId() != null && police != null && police.getId() != null) {
//            listesDetailPolicePlafond = detailPolicePlafondDao.listeDetailPlafondByRubrique(police, rubrique);
//            if (avenant != null && avenant.getId() != null) {
//                for (OrclassDetailPolicePlafond dp : detailPolicePlafondDao.listeDetailPolicePlafondHaveAvenant(entreprise, police, avenant, rubrique)) {
//                    if (Objects.equals(dp.getRetire_de_la_police(), Boolean.TRUE)) {
//                        dpp = detailPolicePlafondDao.find(dp.getCodeid_retirer());
//                        if (dpp != null && dpp.getId() != null) {
//                            listesDetailPolicePlafond.remove(dpp);
//                        }
//                    } else if (Objects.equals(dp.getAjouter(), Boolean.TRUE)) {
//                        listesDetailPolicePlafond.add(dp);
//                    } else if (Objects.equals(dp.getModifier(), Boolean.TRUE)) {
//                        dpp = detailPolicePlafondDao.detailPolicePlafondHaveAvenantForPrestation(entreprise, police, dp.getIdPrestation(), rubrique);
//                        if (dpp != null && dpp.getId() != null) {
//                            listesDetailPolicePlafond.remove(dpp);
//                            listesDetailPolicePlafond.add(dp);
//                        }
//                    }
//                }
//            }
//        }
//    }
//
//    public void valideSinistre() {
//        FacesContext ctx = FacesContext.getCurrentInstance();
//        Locale myLoc = ctx.getViewRoot().getLocale();
//        String entete[] = {LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "prise.charge", null, myLoc)};
//
//        try {
//            if (listeSinistreMaladie.isEmpty()) {
//                ctx.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_WARN, "LIST IS EMPTY", "PLEASE TRY AGAINST"));
//                return;
//            }
//
//            // fonction de validation
////              String[] detail = {entete[0], sinis.getCode() + "," + refPrestataire.getLibelle()};
//        } catch (ConstraintViolationException ejb) {
//            GlobalFonctions.displayMessages("msg", ctx, FacesMessage.SEVERITY_WARN, ejb.getCause().getClass().getSimpleName(), exception.Error.DUPLICATE_ERROR_INSERT + "", null);
//            throw ejb;
//        } catch (Exception th) {
//            GlobalFonctions.displayMessages(null, ctx, FacesMessage.SEVERITY_FATAL, "prise.charge", exception.Error.FATAL_ERROR + "", new Object[]{"prise.charge"});
//            logger.error("Erreur Survenu", th);
//        }
//
//    }
//
//    public OrclassCategories getCategories() {
//        return categories;
//    }
//
//    public void setCategories(OrclassCategories categories) {
//        this.categories = categories;
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
//    public List<OrclassPolice> getListePolice() {
//        return listePolice;
//    }
//
//    public void setListePolice(List<OrclassPolice> listePolice) {
//        this.listePolice = listePolice;
//    }
//
//    public List<OrclassRisque> getListeRisque() {
//        return listeRisque;
//    }
//
//    public void setListeRisque(List<OrclassRisque> listeRisque) {
//        this.listeRisque = listeRisque;
//    }
//
//    public List<OrclassRisqueFamille> getListeRisqueFamille() {
//        return listeRisqueFamille;
//    }
//
//    public void setListeRisqueFamille(List<OrclassRisqueFamille> listeRisqueFamille) {
//        this.listeRisqueFamille = listeRisqueFamille;
//    }
//
//    public List<OrclassNatureMaladie> getListeNatureMaladie() {
//        return listeNatureMaladie;
//    }
//
//    public void setListeNatureMaladie(List<OrclassNatureMaladie> listeNatureMaladie) {
//        this.listeNatureMaladie = listeNatureMaladie;
//    }
//
//    public List<OrclassRubrique> getListeRubrique() {
//        return listeRubrique;
//    }
//
//    public void setListeRubrique(List<OrclassRubrique> listeRubrique) {
//        this.listeRubrique = listeRubrique;
//    }
//
//    public List<OrclassGarantie> getListeGarantie() {
//        return listeGarantie;
//    }
//
//    public void setListeGarantie(List<OrclassGarantie> listeGarantie) {
//        this.listeGarantie = listeGarantie;
//    }
//
//    public List<OrclassGroupePolice> getListeGroupePolice() {
//        return listeGroupePolice;
//    }
//
//    public void setListeGroupePolice(List<OrclassGroupePolice> listeGroupePolice) {
//        this.listeGroupePolice = listeGroupePolice;
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
//    public OrclassPolice getPolice() {
//        return police;
//    }
//
//    public void setPolice(OrclassPolice police) {
//        this.police = police;
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
//    public OrclassAssure getAssure() {
//        return assure;
//    }
//
//    public void setAssure(OrclassAssure assure) {
//        this.assure = assure;
//    }
//
//    public OrclassDetailPolicePlafond getDetailPolicePlafond() {
//        return detailPolicePlafond;
//    }
//
//    public void setDetailPolicePlafond(OrclassDetailPolicePlafond detailPolicePlafond) {
//        this.detailPolicePlafond = detailPolicePlafond;
//    }
//
//    public ISinistre getSinistreService() {
//        return sinistreService;
//    }
//
//    public void setSinistreService(ISinistre sinistreService) {
//        this.sinistreService = sinistreService;
//    }
//
//    public List<OrclassPrestataire> getListePrestataire() {
//        return listePrestataire;
//    }
//
//    public void setListePrestataire(List<OrclassPrestataire> listePrestataire) {
//        this.listePrestataire = listePrestataire;
//    }
//
//    public List<OrclassDetailPolicePlafond> getListesDetailPolicePlafond() {
//        return listesDetailPolicePlafond;
//    }
//
//    public void setListesDetailPolicePlafond(List<OrclassDetailPolicePlafond> listesDetailPolicePlafond) {
//        this.listesDetailPolicePlafond = listesDetailPolicePlafond;
//    }
//
//    public OrclassConvention getConvention() {
//        return convention;
//    }
//
//    public void setConvention(OrclassConvention convention) {
//        this.convention = convention;
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
//    public BigDecimal getNum_police() {
//        return num_police;
//    }
//
//    public void setNum_police(BigDecimal num_police) {
//        this.num_police = num_police;
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
//    public OrclassNatureMaladie getNatureMaladie() {
//        return natureMaladie;
//    }
//
//    public void setNatureMaladie(OrclassNatureMaladie natureMaladie) {
//        this.natureMaladie = natureMaladie;
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
//    public String getPolice_value() {
//        return police_value;
//    }
//
//    public void setPolice_value(String police_value) {
//        this.police_value = police_value;
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
//    public List<OrclassIntervenant> getListesIntervenant() {
//        return listesIntervenant;
//    }
//
//    public void setListesIntervenant(List<OrclassIntervenant> listesIntervenant) {
//        this.listesIntervenant = listesIntervenant;
//    }
//
//    public List<OrclassEvenement> getListesEvenement() {
//        return listesEvenement;
//    }
//
//    public void setListesEvenement(List<OrclassEvenement> listesEvenement) {
//        this.listesEvenement = listesEvenement;
//    }
//
//    public List<OrclassCaracteristiques> getListesCaracteristiques() {
//        return listesCaracteristiques;
//    }
//
//    public void setListesCaracteristiques(List<OrclassCaracteristiques> listesCaracteristiques) {
//        this.listesCaracteristiques = listesCaracteristiques;
//    }
//
//    public OrclassSinistreMaladie getSinistreMaladieSelected() {
//        return sinistreMaladieSelected;
//    }
//
//    public void setSinistreMaladieSelected(OrclassSinistreMaladie sinistreMaladieSelected) {
//        this.sinistreMaladieSelected = sinistreMaladieSelected;
//    }
//
//    public OrclassDetailPolicePlafond getDetailPolicePlafondSelected() {
//        return detailPolicePlafondSelected;
//    }
//
//    public void setDetailPolicePlafondSelected(OrclassDetailPolicePlafond detailPolicePlafondSelected) {
//        this.detailPolicePlafondSelected = detailPolicePlafondSelected;
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
//    public String getMotif_rejet() {
//        return motif_rejet;
//    }
//
//    public void setMotif_rejet(String motif_rejet) {
//        this.motif_rejet = motif_rejet;
//    }
//
//    public String getObservation() {
//        return observation;
//    }
//
//    public void setObservation(String observation) {
//        this.observation = observation;
//    }
//
//    public Double getTotal_debours_reels() {
//        if (!this.listeSinistreMaladie.isEmpty()) {
//            total_debours_reels = 0.0;
//            for (OrclassSinistreMaladie sm : listeSinistreMaladie) {
//                if (sm.getIdPrestation() == null || sm.getIdPrestation().getId() == null) {
//                    continue;
//                }
//                total_debours_reels = total_debours_reels + sm.getDeboursement_reel();
//            }
//        }
//        return total_debours_reels;
//    }
//
//    public void setTotal_debours_reels(Double total_debours_reels) {
//        this.total_debours_reels = total_debours_reels;
//    }
//
//    public Double getTotal_exclure() {
//        if (!this.listeSinistreMaladie.isEmpty()) {
//            total_exclure = 0.0;
//            for (OrclassSinistreMaladie sm : listeSinistreMaladie) {
//                if (sm.getIdPrestation() == null || sm.getIdPrestation().getId() == null) {
//                    continue;
//                }
//                total_exclure = total_exclure + sm.getMontant_exclure();
//            }
//        }
//        return total_exclure;
//    }
//
//    public void setTotal_exclure(Double total_exclure) {
//        this.total_exclure = total_exclure;
//    }
//
//    public Double getTotal_base_remb() {
//        if (!this.listeSinistreMaladie.isEmpty()) {
//            total_base_remb = 0.0;
//            for (OrclassSinistreMaladie sm : listeSinistreMaladie) {
//                if (sm.getIdPrestation() == null || sm.getIdPrestation().getId() == null) {
//                    continue;
//                }
//                total_base_remb = total_base_remb + sm.getBase_remboursement();
//            }
//        }
//        return total_base_remb;
//    }
//
//    public void setTotal_base_remb(Double total_base_remb) {
//        this.total_base_remb = total_base_remb;
//    }
//
//    public Double getTotal_remb_externe() {
//        if (!this.listeSinistreMaladie.isEmpty()) {
//            total_remb_externe = 0.0;
//            for (OrclassSinistreMaladie sm : listeSinistreMaladie) {
//                if (sm.getIdPrestation() == null || sm.getIdPrestation().getId() == null) {
//                    continue;
//                }
//                total_remb_externe = total_remb_externe + sm.getRemb_externe();
//            }
//        }
//        return total_remb_externe;
//    }
//
//    public void setTotal_remb_externe(Double total_remb_externe) {
//        this.total_remb_externe = total_remb_externe;
//    }
//
//    public Double getTotal_montant_remb() {
//        if (!this.listeSinistreMaladie.isEmpty()) {
//            total_montant_remb = 0.0;
//            for (OrclassSinistreMaladie sm : listeSinistreMaladie) {
//                if (sm.getIdPrestation() == null || sm.getIdPrestation().getId() == null) {
//                    continue;
//                }
//                total_montant_remb = total_montant_remb + sm.getMontant_remb();
//            }
//        }
//        return total_montant_remb;
//    }
//
//    public void setTotal_montant_remb(Double total_montant_remb) {
//        this.total_montant_remb = total_montant_remb;
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
//    public int getCode_agence() {
//        return code_agence;
//    }
//
//    public void setCode_agence(int code_agence) {
//        this.code_agence = code_agence;
//    }
//
//}
