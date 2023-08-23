//
///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package controllers.reporting;
//
//import dao.OrclassApporteurDao;
//import dao.OrclassBranchesDao;
//import dao.OrclassCategoriesDao;
//import dao.OrclassIntermediairesDao;
//import dao.OrclassQuitanceDao;
//import dao.OrclassRefApporteurDao;
//import enums.LibelleBranche;
//import enums.LibelleCategorie;
//import enums.TypeActe;
//import enums.TypeEtat;
//import enums.TypeQuittance;
//import java.io.File;
//import java.io.IOException;
//import java.io.Serializable;
//import java.math.BigDecimal;
//import java.math.BigInteger;
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Locale;
//import java.util.Map;
//import java.util.Objects;
//import javax.annotation.PostConstruct;
//import javax.ejb.EJB;
//import javax.faces.application.FacesMessage;
//import javax.faces.context.ExternalContext;
//import javax.faces.context.FacesContext;
//import javax.faces.model.SelectItem;
//import javax.inject.Named;
//import javax.faces.view.ViewScoped;
//import javax.servlet.http.HttpServletRequest;
//import javax.swing.ImageIcon;
//import modele.OrclassApporteur;
//import modele.OrclassBranches;
//import modele.OrclassCategories;
//import modele.OrclassEntreprises;
//import modele.OrclassIntermediaires;
//import modele.OrclassQuitance;
//import modele.OrclassRefApporteur;
//import modele.OrclassUtilisateurs;
//import net.sf.jasperreports.engine.JRException;
//import org.apache.commons.collections.comparators.ComparatorChain;
//import org.primefaces.PrimeFaces;
//import org.primefaces.event.SelectEvent;
//import org.primefaces.event.UnselectEvent;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import quittance.IQuittance;
//import utils.GlobalFonctions;
//import utils.LocaleHelper;
//import utils.RecupBundle;
//
///**
// *
// * @author JIATOU FRANCK
// */
//@Named(value = "etatProductionApporteurController")
//@ViewScoped
//public class EtatProductionApporteurController implements Serializable {
//
//    private static final Logger logger = LoggerFactory.getLogger(EtatProductionApporteurController.class);
//
//    /**
//     * Creates a new instance of EtatProductionApporteurController
//     */
//    @EJB
//    OrclassIntermediairesDao agenceDao;
//    OrclassIntermediaires agence;
//    @EJB
//    OrclassBranchesDao brancheDao;
//    OrclassBranches branche;
//    @EJB
//    OrclassApporteurDao apporteurDao;
//    OrclassApporteur apporteur;
//    @EJB
//    OrclassQuitanceDao quitanceDao;
//    OrclassQuitance quitance;
//    @EJB
//    OrclassCategoriesDao categoriesDao;
//    @EJB
//    IQuittance quittanceService;
//    @EJB
//    OrclassRefApporteurDao refApporteurDao;
//
//    private List<OrclassIntermediaires> listeIntermediaires = new ArrayList<>();
//    private List<OrclassIntermediaires> listeIntermediairesSelect = new ArrayList<>();
//    private List<OrclassBranches> listeBranches = new ArrayList<>();
//    private List<OrclassBranches> listeBranchesSelectect = new ArrayList<>();
//    private List<OrclassApporteur> listeApporteur = new ArrayList<>();
//    private List<OrclassApporteur> listeApporteurSecte = new ArrayList<>();
//    private List<OrclassCategories> listeCategories = new ArrayList<>();
//    private List<OrclassCategories> listeCategoriesSecte = new ArrayList<>();
//    private List<OrclassQuitance> listeQuittanceByPeriode = new ArrayList<>();
//    private OrclassEntreprises entreprise;
//    private OrclassUtilisateurs user;
//    private Date debut;
//    private Date fin;
//    private Boolean allBranche, allApporteur, allCategories = Boolean.FALSE;
//    private Boolean allTypesQuittance, allTypesActe = Boolean.FALSE;
//    private OrclassCategories categoriesDebut, categoriesFin;
//    private OrclassBranches brancheDebut, brancheFin;
//    private OrclassApporteur apporteurDebut, apporteurFin;
//    private Boolean allAgence = Boolean.FALSE;
//    private Boolean personnaliser_apporteur = Boolean.FALSE;
//    private Boolean personnaliser_branche = Boolean.FALSE;
//    private Boolean personnaliser_categories = Boolean.FALSE;
//    private TypeActe typeActe;
//    private TypeQuittance typeQuittance;
//    private TypeEtat typeEtat;
//
//    public EtatProductionApporteurController() {
//        agence = new OrclassIntermediaires();
//        categoriesDebut = new OrclassCategories();
//        categoriesFin = new OrclassCategories();
//        brancheDebut = new OrclassBranches();
//        apporteurDebut = new OrclassApporteur();
//        apporteurFin = new OrclassApporteur();
//        allAgence = Boolean.FALSE;
//        personnaliser_apporteur = Boolean.FALSE;
//        personnaliser_branche = Boolean.FALSE;
//        personnaliser_categories = Boolean.FALSE;
//        allCategories = Boolean.FALSE;
//        allBranche = Boolean.FALSE;
//        allApporteur = Boolean.FALSE;
//
//    }
//
//    @PostConstruct
//    void initialiseSession() {
//        FacesContext.getCurrentInstance().getExternalContext().getSession(true);
//        entreprise = (OrclassEntreprises) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(GlobalFonctions.ENTREPRISE_ACTIF);
//        if (entreprise == null) {
//            entreprise = (OrclassEntreprises) FacesContext.getCurrentInstance().getExternalContext().getApplicationMap().get(GlobalFonctions.ENTREPRISE_ACTIF);
//
//        }
//
//        user = (OrclassUtilisateurs) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(GlobalFonctions.SESSION_USER);
////        listeIntermediaires = agenceDao.listeIntermediaireByEntreprise(entreprise);
////        listeBranches = brancheDao.getAllBrancheByEntreprise(entreprise);
//////        if (listeBranches.isEmpty()) {
//////           listeBranches=brancheDao.listAllBrancheShowAllCompagnie();
//////        }else{
//////            listeBranches.addAll(brancheDao.listAllBrancheShowAllCompagnie());
//////        }
//////        listeApporteur=apporteurDao.apporteurByCompagnies(entreprise);
//        PrimeFaces.current().executeScript("PF('detail_etat').show();");
//
//    }
//
//    public void valideChoixTypeEtat() {
//        if (typeEtat == null) {
//            PrimeFaces.current().executeScript("PF('detail_etat').show();");
//        } else {
//            PrimeFaces.current().executeScript("PF('detail_etat').hide();");
//        }
//    }
//
//    public List<SelectItem> getTypeQuittances() {
//        List<SelectItem> items = new ArrayList<>();
//
//        FacesContext ctx = FacesContext.getCurrentInstance();
//        Locale myLoc = ctx.getViewRoot().getLocale();
//
//        for (TypeQuittance em : TypeQuittance.values()) {
//            items.add(new SelectItem(em, LocaleHelper.getLocaleString(RecupBundle.FichierBundle, em.name(), null, myLoc)));
//        }
//
//        return items;
//    }
//
//    public List<SelectItem> getTypeActes() {
//        List<SelectItem> items = new ArrayList<>();
//
//        FacesContext ctx = FacesContext.getCurrentInstance();
//        Locale myLoc = ctx.getViewRoot().getLocale();
//
//        for (TypeActe em : TypeActe.values()) {
//            items.add(new SelectItem(em, LocaleHelper.getLocaleString(RecupBundle.FichierBundle, em.name(), null, myLoc)));
//        }
//
//        return items;
//    }
//
//    public List<SelectItem> getTypeEtats() {
//        List<SelectItem> items = new ArrayList<>();
//
//        FacesContext ctx = FacesContext.getCurrentInstance();
//        Locale myLoc = ctx.getViewRoot().getLocale();
//
//        for (TypeEtat te : TypeEtat.values()) {
//            items.add(new SelectItem(te, LocaleHelper.getLocaleString(RecupBundle.FichierBundle, te.name(), null, myLoc)));
//        }
//
//        return items;
//    }
//
//    //charger les intermediaire ayant eut a etablir les contrat pour une periode precis 
//    public void chargeIntermediaireByPeriode() {
//        listeIntermediaires.clear();
//        if (debut != null && fin != null) {
//            listeIntermediaires = agenceDao.listeIntermediaireBYPeriode(entreprise, debut, fin);
//            if (listeIntermediaires.isEmpty()) {
//                PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage("List is empty", "Please change Date"));
//            }
//        }
//    }
//
//    public void chargeApporteurByIntermediaireForPeriode() {
//        listeApporteur.clear();
//        if (debut != null && fin != null && agence != null && agence.getIdIntermediaire() != null && Objects.equals(allBranche, Boolean.FALSE)) {
//            listeApporteur = apporteurDao.listeApporteurByPeriode(entreprise, agence, debut, fin);
//        } else if (debut != null && fin != null && Objects.equals(allBranche, Boolean.TRUE)) {
//            listeApporteur = apporteurDao.listeApporteurByPeriode(entreprise, debut, fin);
//        }
//
//        this.chargeBrancheForPeriode();
//    }
//
//    public void chargeBrancheForPeriode() {
//        listeBranches.clear();
//        if (debut != null && fin != null && agence != null && agence.getIdIntermediaire() != null && Objects.equals(allBranche, Boolean.FALSE)) {
//            listeBranches = brancheDao.listeBrancherByPeriode(entreprise, agence, debut, fin);
//        } else if (debut != null && fin != null && Objects.equals(allBranche, Boolean.TRUE)) {
//            listeBranches = brancheDao.listeBrancherByPeriode(entreprise, debut, fin);
//        }
//    }
//
////    public void onItemUnselect(UnselectEvent event) {
////        FacesContext context = FacesContext.getCurrentInstance();
////        
////        FacesMessage msg = new FacesMessage();
////        msg.setSummary("Item unselected: " + event.getObject().toString());
////        msg.setSeverity(FacesMessage.SEVERITY_INFO);
////        
////        context.addMessage(null, msg);
////    }
//    public void onItemUnselect(UnselectEvent event) {
//        FacesMessage msg = new FacesMessage();
//        msg.setSummary("Item unselected: " + event.getObject().toString());
//        msg.setSeverity(FacesMessage.SEVERITY_INFO);
//
//        FacesContext.getCurrentInstance().addMessage(null, msg);
//    }
//
//    public void onItemSelect(SelectEvent event) {
////        FacesContext context = FacesContext.getCurrentInstance();
////        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Item Selected", event.getObject().toString()));
//        FacesMessage msg = new FacesMessage();
//        msg.setSummary("Item selected: " + event.getObject().toString());
//        msg.setSeverity(FacesMessage.SEVERITY_INFO);
//
//        FacesContext.getCurrentInstance().addMessage(null, msg);
//    }
//
//    public void onUnselect(UnselectEvent<Object> event) {
//        FacesContext context = FacesContext.getCurrentInstance();
//        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Item Unselected", event.getObject().toString()));
//    }
//
//    public void chargeCategoriesBySelecteBranche() {
////        if (bra) {
////            
////        }
//        FacesMessage msg = new FacesMessage();
//        msg.setSummary("Item selected: c est bon");
//        msg.setSeverity(FacesMessage.SEVERITY_INFO);
//        System.out.println("branche debut: " + brancheDebut.getCode());
//        BigInteger codePrincipal_1 = BigInteger.ZERO;
//        BigInteger codePrincipal_2 = BigInteger.ZERO;
//        OrclassBranches branches = null;
//
//        FacesContext.getCurrentInstance().addMessage(null, msg);
//        List<OrclassCategories> listeCategoriesByBrancheHaveQuittance = new ArrayList<>();
//        listeCategories.clear();
//        if (!listeBranchesSelectect.isEmpty() && Objects.equals(this.allBranche, Boolean.FALSE) && Objects.equals(this.personnaliser_branche, Boolean.FALSE)) {
//            for (OrclassBranches br : listeBranchesSelectect) {
//                listeCategoriesByBrancheHaveQuittance = categoriesDao.listeCategorieByBrancheHaveQuittance(br, entreprise, debut, fin);
//                listeCategories.addAll(listeCategoriesByBrancheHaveQuittance);
//            }
//        } else if (Objects.equals(this.allBranche, Boolean.TRUE) && Objects.equals(this.personnaliser_branche, Boolean.FALSE)) {
//            for (OrclassBranches br : listeBranches) {
//                listeCategoriesByBrancheHaveQuittance = categoriesDao.listeCategorieByBrancheHaveQuittance(br, entreprise, debut, fin);
//                listeCategories.addAll(listeCategoriesByBrancheHaveQuittance);
//            }
//        } else {
//            if (brancheDebut.getCode() != null && brancheFin.getCode() != null && !"".equals(brancheDebut.getCode()) && !"".equals(brancheFin.getCode())) {
//                System.out.println("branche debut: " + brancheDebut.getCode());
//                branches = brancheDao.findEntityHavingValue("code", brancheDebut.getCode().split("-")[0]);
//                if (branches != null && branches.getIdBranche() != null) {
//                    if (branches.getCodePrincipal() == null || branches.getCodePrincipal().intValue() == 0) {
//                        codePrincipal_1 = branches.getCodePrincipal();
//                        branches.setCodePrincipal(new BigInteger(branches.getCode()));
//                        brancheDao.update(branches);
//                    }else{
//                          codePrincipal_1 = branches.getCodePrincipal();
//                    }
//                }
//
//                System.out.println("branche fin: " + brancheFin.getCode());
//                branches = brancheDao.findEntityHavingValue("code", brancheFin.getCode().split("-")[0]);
//                if (branches != null && branches.getIdBranche() != null) {
////                    if (!brancheDebut.getCode().equals(brancheDebut.getCodePrincipal().toString())) {
////                        brancheDebut.setCodePrincipal(new BigInteger(brancheDebut.getCode().trim()));
////                    }
//                    if (branches.getCodePrincipal() == null || branches.getCodePrincipal().intValue() == 0) {
//                        codePrincipal_2 = branches.getCodePrincipal();
//                        branches.setCodePrincipal(new BigInteger(branches.getCode()));
//                        brancheDao.update(branches);
//                    }else{
//                          codePrincipal_2 = branches.getCodePrincipal();
//                    }
//
//                }
//                listeCategories = categoriesDao.listeCategorieByBrancheHaveQuittance(entreprise, debut, fin, codePrincipal_1,codePrincipal_2);
//
////                if (brancheFin != null && brancheFin.getIdBranche() != null) {
////                    if (!brancheFin.getCode().equals(brancheFin.getCodePrincipal().toString())) {
////                        brancheFin.setCodePrincipal(new BigInteger(brancheFin.getCode().trim()));
////                    }
////
////                }
////                   if (brancheDebut.getLibelleAutre() != null) {
////                    brancheDebut.setCode(brancheDebut.getCode() + "-" + brancheDebut.getLibelleAutre());
////                } else {
////                    brancheDebut.setCode(brancheDebut.getCode() + "-" + GlobalFonctions.valueObject(brancheDebut.getLibelle()));
////
////                }
////                if (brancheFin.getLibelleAutre() != null) {
////                    brancheFin.setCode(brancheFin.getCode() + "-" + brancheFin.getLibelleAutre());
////                } else {
////                    brancheFin.setCode(brancheFin.getCode() + "-" + GlobalFonctions.valueObject(brancheFin.getLibelle()));
////
////                }
//            }
//        }
////        PrimeFaces.current().ajax().update(":form1");
//    }
//
//    public List<String> completeText(String query) {
//        List<String> results = new ArrayList<>();
////        for (int i = 0; i < 10; i++) {
////            results.add(query + i);
////        }
////        if (Objects.equals(allApporteur, Boolean.TRUE)) {
//        for (OrclassApporteur app : listeApporteur) {
//            results.add(app.getIdRefApporteur().getCode() + "-" + app.getIdRefApporteur().getRaisonSociale());
////            }
//        }
//
//        return results;
//    }
//
//    public List<String> completeTextCategorie(String query) {
//        List<String> results = new ArrayList<>();
////        for (int i = 0; i < 10; i++) {
////            results.add(query + i);
////        }
//        for (OrclassCategories c : listeCategories) {
//
//            if (c.getLibelleAutre() != null) {
//                if (c.getLibelleAutre().equals(LibelleCategorie.autres)) {
//                    results.add(c.getCode() + "-" + c.getLibelleAutre());
//                }
//
//            } else {
//                results.add(c.getCode() + "-" + GlobalFonctions.valueObject(c.getLibelle()));
//            }
//        }
//
//        return results;
//    }
//
//    public List<String> completeTextBranche(String query) {
//        List<String> results = new ArrayList<>();
////        for (int i = 0; i < 10; i++) {
////            results.add(query + i);
////        }
//        for (OrclassBranches br : listeBranches) {
//            if (br.getLibelleAutre() != null) {
//                if (br.getLibelleAutre().equals(LibelleBranche.autres)) {
//                    results.add(br.getCode() + "-" + br.getLibelle());
//                }
//
//            } else {
//                results.add(br.getCode() + "-" + GlobalFonctions.valueObject(br.getLibelle()));
//            }
//
//        }
//
//        return results;
//    }
//    public void selecteQuittanceByPeriodeFormatPdf() throws IOException, JRException{
//        this.selecteQuittanceByPeriode();
//            this.print();
//    }
//    
//     public void selecteQuittanceByPeriodeFormatExcell() throws IOException, JRException{
//        this.selecteQuittanceByPeriode();
//            this.telechargerForExcell();
//    }
//
//    public void selecteQuittanceByPeriode() throws IOException, JRException {
//        // premier cas l agence est secetionner
//        if (debut != null && fin != null) {
//
//            quittanceService.chargePeriode(entreprise, debut, fin);
//            System.out.println("debut" + debut);
//            System.out.println("debut" + fin);
//            BigInteger codePrincipal_1 = BigInteger.ZERO;
//            BigInteger codePrincipal_2 = BigInteger.ZERO;
//            listeQuittanceByPeriode.clear();
//
//            if (Objects.equals(this.getAllAgence(), Boolean.FALSE)) {
//                // dans  ce cas une seule agence est selectionner 
//                // premier chois par defaut  le chois est unique 
//                if (!this.listeApporteurSecte.isEmpty() && !this.listeBranchesSelectect.isEmpty() && !this.listeCategoriesSecte.isEmpty() && typeActe != null && typeQuittance != null) {
//                    listeQuittanceByPeriode = quittanceService.listeQuittanceByAgenceOrChoixMultiple(agence, listeApporteurSecte, listeBranchesSelectect, listeCategoriesSecte, typeActe, typeQuittance);
//                    System.out.println("taille " + listeQuittanceByPeriode.size());
//                    for (OrclassQuitance qp : listeQuittanceByPeriode) {
//                        System.out.println("quittance Id " + qp.getId() + " numero police " + qp.getIdPolice().getPolice().toString());
//                        System.out.println("avenant" + qp.getIdAvenant());
//                    }
//                } else if (this.listeApporteurSecte.isEmpty() && !this.listeBranchesSelectect.isEmpty() && !this.listeCategoriesSecte.isEmpty() && typeActe != null && typeQuittance != null && this.getAllApporteur() == Boolean.TRUE && this.getPersonnaliser_apporteur() == Boolean.FALSE) {
//                    /*
//                tous les apporteur sont prit en compte a la foi
//                     */
//                    listeQuittanceByPeriode = quittanceService.listeQuittanceByAgenceOrChoixMultiple(agence, listeApporteur, listeBranchesSelectect, listeCategoriesSecte, typeActe, typeQuittance);
//                    System.out.println("taille " + listeQuittanceByPeriode.size());
//                    for (OrclassQuitance qp : listeQuittanceByPeriode) {
//                        System.out.println("quittance Id " + qp.getId() + " numero police " + qp.getIdPolice().getPolice().toString());
//                        System.out.println("avenant" + qp.getIdAvenant());
//                        System.out.println("agence" + qp.getIdPolice().getIdIntermediaire().getIdRefIntermediaire().getRaisonSociale() + "-" + qp.getIdPolice().getIdIntermediaire().getIdRefIntermediaire().getRaisonSociale());
//                    }
//                } else if (this.listeApporteurSecte.isEmpty() && this.listeBranchesSelectect.isEmpty() && !this.listeCategoriesSecte.isEmpty() && typeActe != null && typeQuittance != null && this.getAllApporteur() == Boolean.TRUE && this.getAllBranche() == Boolean.TRUE && this.getPersonnaliser_apporteur() == Boolean.FALSE && this.getPersonnaliser_branche() == Boolean.FALSE) {
//
//                    /*
//                tous les apporteurs et les branche  sont prit en compte a la foi
//                     */
//                    listeQuittanceByPeriode = quittanceService.listeQuittanceByAgenceOrChoixMultiple(agence, listeApporteur, listeBranches, listeCategoriesSecte, typeActe, typeQuittance);
//                    System.out.println("taille " + listeQuittanceByPeriode.size());
//                    for (OrclassQuitance qp : listeQuittanceByPeriode) {
//                        System.out.println("quittance Id " + qp.getId() + " numero police " + qp.getIdPolice().getPolice().toString());
//                        System.out.println("avenant" + qp.getIdAvenant());
//                        System.out.println("agence" + qp.getIdPolice().getIdIntermediaire().getIdRefIntermediaire().getRaisonSociale() + "-" + qp.getIdPolice().getIdIntermediaire().getIdRefIntermediaire().getRaisonSociale());
//                    }
//
//                } else if (this.listeApporteurSecte.isEmpty() && this.listeBranchesSelectect.isEmpty() && this.listeCategoriesSecte.isEmpty() && typeActe != null && typeQuittance != null && this.getAllApporteur() == Boolean.TRUE && this.getAllBranche() == Boolean.TRUE && this.getAllCategories() == Boolean.TRUE && this.getPersonnaliser_apporteur() == Boolean.FALSE && this.getPersonnaliser_branche() == Boolean.FALSE && this.getPersonnaliser_categories() == Boolean.FALSE) {
//
//                    /*
//                tous les apporteurs et les branches et les categories   sont prit en compte a la foi
//                     */
//                    listeQuittanceByPeriode = quittanceService.listeQuittanceByAgenceOrChoixMultiple(agence, listeApporteur, listeBranches, listeCategories, typeActe, typeQuittance);
//                    System.out.println("taille " + listeQuittanceByPeriode.size());
//                    for (OrclassQuitance qp : listeQuittanceByPeriode) {
//                        System.out.println("quittance Id " + qp.getId() + " numero police " + qp.getIdPolice().getPolice().toString());
//                        System.out.println("avenant" + qp.getIdAvenant());
//                        System.out.println("agence" + qp.getIdPolice().getIdIntermediaire().getIdRefIntermediaire().getRaisonSociale() + "-" + qp.getIdPolice().getIdIntermediaire().getIdRefIntermediaire().getRaisonSociale());
//                    }
//
//                } else /*
//                 le choix va etre personnaliser
//                 */ /*
//                 en premier le choix personnaliser porte sur les apporteurs 
//                 */ if (this.listeApporteurSecte.isEmpty() && !this.listeBranchesSelectect.isEmpty() && !this.listeCategoriesSecte.isEmpty() && typeActe != null && typeQuittance != null && apporteurDebut.getIdRefApporteur().getCode() != null && apporteurFin.getIdRefApporteur().getCode() != null && this.getPersonnaliser_apporteur() == Boolean.TRUE && this.getAllApporteur() == Boolean.FALSE && this.getAllBranche() == Boolean.FALSE && this.getAllCategories() == Boolean.FALSE) {
//
//                    /*
//                tous les apporteurs et les branches et les categories   sont prit en compte a la foi
//                     */
////                    OrclassRefApporteur refapp1 = refApporteurDao.findEntityHavingValue("code", apporteurDebut.getIdRefApporteur().getCode());
////                    OrclassRefApporteur refapp2=refApporteurDao.findEntityHavingValue("code", apporteurFin.getIdRefApporteur().getCode());
////                    apporteurDebut=apporteurDao.findEntityHavingValue("idRefApporteur", refapp1);
////                    apporteurFin=apporteurDao.findEntityHavingValue("idRefApporteur", refapp2);
//                    String[] valeur = apporteurDebut.getIdRefApporteur().getCode().split("-");
//                    codePrincipal_1 = new BigInteger(valeur[0].trim());
//                    valeur = apporteurFin.getIdRefApporteur().getCode().split("-");
//                    codePrincipal_2 = new BigInteger(valeur[0].trim());
//                    listeApporteur = apporteurDao.listeApporteurBetweenCodePrincipale(entreprise, debut, fin, codePrincipal_1, codePrincipal_2);
//
//                    listeQuittanceByPeriode = quittanceService.listeQuittanceByAgenceOrChoixMultiple(agence, listeApporteur, listeBranchesSelectect, listeCategories, typeActe, typeQuittance);
//                    System.out.println("taille " + listeQuittanceByPeriode.size());
//                    for (OrclassQuitance qp : listeQuittanceByPeriode) {
//                        System.out.println("quittance Id " + qp.getId() + " numero police " + qp.getIdPolice().getPolice().toString());
//                        System.out.println("avenant" + qp.getIdAvenant());
//                        System.out.println("agence" + qp.getIdPolice().getIdIntermediaire().getIdRefIntermediaire().getRaisonSociale() + "-" + qp.getIdPolice().getIdIntermediaire().getIdRefIntermediaire().getRaisonSociale());
//                    }
//
//                } else if (this.listeApporteurSecte.isEmpty() && this.listeBranchesSelectect.isEmpty() && !this.listeCategoriesSecte.isEmpty() && typeActe != null && typeQuittance != null && brancheDebut.getCode() != null && brancheFin.getCode() != null && apporteurDebut.getIdRefApporteur().getCode() != null && apporteurFin.getIdRefApporteur().getCode() != null && this.getPersonnaliser_apporteur() == Boolean.TRUE && this.getPersonnaliser_branche() == Boolean.TRUE && this.getAllCategories() == Boolean.FALSE) {
//                    String[] valeur = brancheDebut.getCode().split("-");
//                    codePrincipal_1 = new BigInteger(valeur[0].trim());
//                    valeur = brancheFin.getCode().split("-");
//                    codePrincipal_2 = new BigInteger(valeur[0].trim());
//                    listeBranches = brancheDao.listeBrancherByChoixPersonnalise(entreprise, debut, fin, codePrincipal_1, codePrincipal_2);
//                    valeur = apporteurDebut.getIdRefApporteur().getCode().split("-");
//                    codePrincipal_1 = new BigInteger(valeur[0].trim());
//                    valeur = apporteurFin.getIdRefApporteur().getCode().split("-");
//                    codePrincipal_2 = new BigInteger(valeur[0].trim());
//                    listeApporteur = apporteurDao.listeApporteurBetweenCodePrincipale(entreprise, debut, fin, codePrincipal_1, codePrincipal_2);
//                    listeQuittanceByPeriode = quittanceService.listeQuittanceByAgenceOrChoixMultiple(agence, listeApporteur, listeBranches, listeCategories, typeActe, typeQuittance);
//                    System.out.println("taille " + listeQuittanceByPeriode.size());
//                    for (OrclassQuitance qp : listeQuittanceByPeriode) {
//                        System.out.println("quittance Id " + qp.getId() + " numero police " + qp.getIdPolice().getPolice().toString());
//                        System.out.println("avenant" + qp.getIdAvenant());
//                        System.out.println("agence" + qp.getIdPolice().getIdIntermediaire().getIdRefIntermediaire().getRaisonSociale() + "-" + qp.getIdPolice().getIdIntermediaire().getIdRefIntermediaire().getRaisonSociale());
//                    }
//
//                } else if (!this.listeApporteurSecte.isEmpty() && this.listeBranchesSelectect.isEmpty() && !this.listeCategoriesSecte.isEmpty() && typeActe != null && typeQuittance != null && brancheDebut.getCode() != null && brancheFin.getCode() != null && this.getPersonnaliser_branche() == Boolean.TRUE && this.getAllCategories() == Boolean.FALSE && this.getAllApporteur() == Boolean.FALSE) {
////                    listeBranches = brancheDao.listeBrancherByChoixPersonnalise(entreprise, debut, fin, brancheDebut.getCodePrincipal(), brancheFin.getCodePrincipal());
////                    listeApporteur = apporteurDao.listeApporteurBetweenCodePrincipale(entreprise, debut, fin, new BigInteger(apporteurDebut.getIdRefApporteur().getCode()), new BigInteger(apporteurFin.getIdRefApporteur().getCode()));
//                    String[] valeur = brancheDebut.getCode().split("-");
//                    codePrincipal_1 = new BigInteger(valeur[0].trim());
//                    valeur = brancheFin.getCode().split("-");
//                    codePrincipal_2 = new BigInteger(valeur[0].trim());
//                    listeBranches = brancheDao.listeBrancherByChoixPersonnalise(entreprise, debut, fin, codePrincipal_1, codePrincipal_2);
//
//                    listeQuittanceByPeriode = quittanceService.listeQuittanceByAgenceOrChoixMultiple(agence, listeApporteurSecte, listeBranches, listeCategories, typeActe, typeQuittance);
//                    System.out.println("taille " + listeQuittanceByPeriode.size());
//                    for (OrclassQuitance qp : listeQuittanceByPeriode) {
//                        System.out.println("quittance Id " + qp.getId() + " numero police " + qp.getIdPolice().getPolice().toString());
//                        System.out.println("avenant" + qp.getIdAvenant());
//                        System.out.println("agence" + qp.getIdPolice().getIdIntermediaire().getIdRefIntermediaire().getRaisonSociale() + "-" + qp.getIdPolice().getIdIntermediaire().getIdRefIntermediaire().getRaisonSociale());
//                    }
//
//                } else if (!this.listeApporteurSecte.isEmpty() && !this.listeBranchesSelectect.isEmpty() && this.listeCategoriesSecte.isEmpty() && typeActe != null && typeQuittance != null && categoriesDebut.getCode() != null && categoriesFin.getCode() != null && this.getPersonnaliser_categories() == Boolean.TRUE && this.getAllBranche() == Boolean.FALSE && this.getAllApporteur() == Boolean.FALSE) {
//                    String[] valeur = categoriesDebut.getCode().split("-");
//                    codePrincipal_1 = new BigInteger(valeur[0].trim());
//                    valeur = categoriesFin.getCode().split("-");
//                    codePrincipal_2 = new BigInteger(valeur[0].trim());
//
//                    listeCategories = categoriesDao.listeCategorieByChoixPersonnaliser(entreprise, debut, fin, codePrincipal_1, codePrincipal_2);
//                    listeQuittanceByPeriode = quittanceService.listeQuittanceByAgenceOrChoixMultiple(agence, listeApporteurSecte, listeBranchesSelectect, listeCategories, typeActe, typeQuittance);
//                    System.out.println("taille " + listeQuittanceByPeriode.size());
//                    for (OrclassQuitance qp : listeQuittanceByPeriode) {
//                        System.out.println("quittance Id " + qp.getId() + " numero police " + qp.getIdPolice().getPolice().toString());
//                        System.out.println("avenant" + qp.getIdAvenant());
//                        System.out.println("agence" + qp.getIdPolice().getIdIntermediaire().getIdRefIntermediaire().getRaisonSociale() + "-" + qp.getIdPolice().getIdIntermediaire().getIdRefIntermediaire().getRaisonSociale());
//                    }
//
//                } else if (this.listeApporteurSecte.isEmpty() && this.listeBranchesSelectect.isEmpty() && this.listeCategoriesSecte.isEmpty() && typeActe != null && typeQuittance != null && categoriesDebut.getCode() != null && categoriesFin.getCode() != null && apporteurDebut.getIdRefApporteur().getCode() != null && apporteurFin.getIdRefApporteur().getCode() != null && brancheDebut.getCode() != null && brancheFin.getCode() != null && this.getPersonnaliser_apporteur() == Boolean.TRUE && this.getPersonnaliser_branche() == Boolean.TRUE && this.getPersonnaliser_categories() == Boolean.TRUE) {
//                    String[] valeur = categoriesDebut.getCode().split("-");
//                    codePrincipal_1 = new BigInteger(valeur[0].trim());
//                    valeur = categoriesFin.getCode().split("-");
//                    codePrincipal_2 = new BigInteger(valeur[0].trim());
//                    listeCategories = categoriesDao.listeCategorieByChoixPersonnaliser(entreprise, debut, fin, codePrincipal_1, codePrincipal_2);
//                    valeur = brancheDebut.getCode().split("-");
//                    codePrincipal_1 = new BigInteger(valeur[0].trim());
//                    valeur = brancheFin.getCode().split("-");
//                    codePrincipal_2 = new BigInteger(valeur[0].trim());
//                    listeBranches = brancheDao.listeBrancherByChoixPersonnalise(entreprise, debut, fin, codePrincipal_1, codePrincipal_2);
//
//                    valeur = apporteurDebut.getIdRefApporteur().getCode().split("-");
//                    codePrincipal_1 = new BigInteger(valeur[0].trim());
//                    valeur = apporteurFin.getIdRefApporteur().getCode().split("-");
//                    codePrincipal_2 = new BigInteger(valeur[0].trim());
//                    listeApporteur = apporteurDao.listeApporteurBetweenCodePrincipale(entreprise, debut, fin, codePrincipal_1, codePrincipal_2);
//
//                    listeQuittanceByPeriode = quittanceService.listeQuittanceByAgenceOrChoixMultiple(agence, listeApporteur, listeBranches, listeCategories, typeActe, typeQuittance);
//                    System.out.println("taille " + listeQuittanceByPeriode.size());
//                    for (OrclassQuitance qp : listeQuittanceByPeriode) {
//                        System.out.println("quittance Id " + qp.getId() + " numero police " + qp.getIdPolice().getPolice().toString());
//                        System.out.println("avenant" + qp.getIdAvenant());
//                        System.out.println("agence" + qp.getIdPolice().getIdIntermediaire().getIdRefIntermediaire().getRaisonSociale() + "-" + qp.getIdPolice().getIdIntermediaire().getIdRefIntermediaire().getRaisonSociale());
//                    }
//
//                } else if (this.listeApporteurSecte.isEmpty() && this.listeBranchesSelectect.isEmpty() && this.listeCategoriesSecte.isEmpty() && typeActe != null && typeQuittance != null  && apporteurDebut.getIdRefApporteur().getCode() != null && apporteurFin.getIdRefApporteur().getCode() != null && brancheDebut.getCode() != null && brancheFin.getCode() != null && this.getPersonnaliser_apporteur() == Boolean.TRUE && this.getPersonnaliser_branche() == Boolean.TRUE && this.getAllCategories() == Boolean.TRUE) {
//
//                    String[] valeur = brancheDebut.getCode().split("-");
//                    codePrincipal_1 = new BigInteger(valeur[0].trim());
//                    valeur = brancheFin.getCode().split("-");
//                    codePrincipal_2 = new BigInteger(valeur[0].trim());
//                    listeBranches = brancheDao.listeBrancherByChoixPersonnalise(entreprise, debut, fin, codePrincipal_1, codePrincipal_2);
//
//                    valeur = apporteurDebut.getIdRefApporteur().getCode().split("-");
//                    codePrincipal_1 = new BigInteger(valeur[0].trim());
//                    valeur = apporteurFin.getIdRefApporteur().getCode().split("-");
//                    codePrincipal_2 = new BigInteger(valeur[0].trim());
//                    listeApporteur = apporteurDao.listeApporteurBetweenCodePrincipale(entreprise, debut, fin, codePrincipal_1, codePrincipal_2);
//
//                    listeQuittanceByPeriode = quittanceService.listeQuittanceByAgenceOrChoixMultiple(agence, listeApporteur, listeBranches, listeCategories, typeActe, typeQuittance);
//                    System.out.println("taille " + listeQuittanceByPeriode.size());
//                    for (OrclassQuitance qp : listeQuittanceByPeriode) {
//                        System.out.println("quittance Id " + qp.getId() + " numero police " + qp.getIdPolice().getPolice().toString());
//                        System.out.println("avenant" + qp.getIdAvenant());
//                        System.out.println("agence" + qp.getIdPolice().getIdIntermediaire().getIdRefIntermediaire().getRaisonSociale() + "-" + qp.getIdPolice().getIdIntermediaire().getIdRefIntermediaire().getRaisonSociale());
//                    }
//
//                } else if (this.listeApporteurSecte.isEmpty() && this.listeBranchesSelectect.isEmpty() && this.listeCategoriesSecte.isEmpty() && typeActe != null && typeQuittance != null && this.getAllApporteur() == Boolean.TRUE && this.getAllBranche() == Boolean.TRUE && this.getAllCategories() == Boolean.TRUE) {
//
//                    listeQuittanceByPeriode = quittanceService.listeQuittanceByAgenceOrChoixMultiple(agence, listeApporteur, listeBranches, listeCategories, typeActe, typeQuittance);
//                    System.out.println("taille " + listeQuittanceByPeriode.size());
//                    for (OrclassQuitance qp : listeQuittanceByPeriode) {
//                        System.out.println("quittance Id " + qp.getId() + " numero police " + qp.getIdPolice().getPolice().toString());
//                        System.out.println("avenant" + qp.getIdAvenant());
//                        System.out.println("agence" + qp.getIdPolice().getIdIntermediaire().getIdRefIntermediaire().getRaisonSociale() + "-" + qp.getIdPolice().getIdIntermediaire().getIdRefIntermediaire().getRaisonSociale());
//                    }
//                }
//
//            } else if (Objects.equals(this.getAllAgence(), Boolean.TRUE)) {
//                if (!listeIntermediaires.isEmpty() && !this.listeApporteurSecte.isEmpty() && !this.listeBranchesSelectect.isEmpty() && !this.listeCategoriesSecte.isEmpty() && typeActe != null && typeQuittance != null) {
//                    listeQuittanceByPeriode = quittanceService.listeQuittanceByAllAgenceOrChoixMultiple(listeIntermediaires, listeApporteur, listeBranches, listeCategories, typeActe, typeQuittance);
//
//                    System.out.println("taille " + listeQuittanceByPeriode.size());
//                    for (OrclassQuitance qp : listeQuittanceByPeriode) {
//                        System.out.println("quittance Id " + qp.getId() + " numero police " + qp.getIdPolice().getPolice().toString());
//                        System.out.println("avenant" + qp.getIdAvenant());
//                        System.out.println("agence" + qp.getIdPolice().getIdIntermediaire().getIdRefIntermediaire().getRaisonSociale() + "-" + qp.getIdPolice().getIdIntermediaire().getIdRefIntermediaire().getRaisonSociale());
//                    }
//                }else if (this.listeApporteurSecte.isEmpty() && !this.listeBranchesSelectect.isEmpty() && !this.listeCategoriesSecte.isEmpty() && typeActe != null && typeQuittance != null && this.getAllApporteur() == Boolean.TRUE && this.getPersonnaliser_apporteur() == Boolean.FALSE) {
//                    /*
//                tous les apporteur sont prit en compte a la foi
//                     */
//                    listeQuittanceByPeriode = quittanceService.listeQuittanceByAllAgenceOrChoixMultiple(listeIntermediaires, listeApporteur, listeBranchesSelectect, listeCategoriesSecte, typeActe, typeQuittance);
//                    System.out.println("taille " + listeQuittanceByPeriode.size());
//                    for (OrclassQuitance qp : listeQuittanceByPeriode) {
//                        System.out.println("quittance Id " + qp.getId() + " numero police " + qp.getIdPolice().getPolice().toString());
//                        System.out.println("avenant" + qp.getIdAvenant());
//                        System.out.println("agence" + qp.getIdPolice().getIdIntermediaire().getIdRefIntermediaire().getRaisonSociale() + "-" + qp.getIdPolice().getIdIntermediaire().getIdRefIntermediaire().getRaisonSociale());
//                    }
//                } else if (this.listeApporteurSecte.isEmpty() && this.listeBranchesSelectect.isEmpty() && !this.listeCategoriesSecte.isEmpty() && typeActe != null && typeQuittance != null && this.getAllApporteur() == Boolean.TRUE && this.getAllBranche() == Boolean.TRUE && this.getPersonnaliser_apporteur() == Boolean.FALSE && this.getPersonnaliser_branche() == Boolean.FALSE) {
//
//                    /*
//                tous les apporteurs et les branche  sont prit en compte a la foi
//                     */
//                    listeQuittanceByPeriode = quittanceService.listeQuittanceByAllAgenceOrChoixMultiple(listeIntermediaires, listeApporteur, listeBranches, listeCategoriesSecte, typeActe, typeQuittance);
//                    System.out.println("taille " + listeQuittanceByPeriode.size());
//                    for (OrclassQuitance qp : listeQuittanceByPeriode) {
//                        System.out.println("quittance Id " + qp.getId() + " numero police " + qp.getIdPolice().getPolice().toString());
//                        System.out.println("avenant" + qp.getIdAvenant());
//                        System.out.println("agence" + qp.getIdPolice().getIdIntermediaire().getIdRefIntermediaire().getRaisonSociale() + "-" + qp.getIdPolice().getIdIntermediaire().getIdRefIntermediaire().getRaisonSociale());
//                    }
//
//                } else if (this.listeApporteurSecte.isEmpty() && this.listeBranchesSelectect.isEmpty() && this.listeCategoriesSecte.isEmpty() && typeActe != null && typeQuittance != null && this.getAllApporteur() == Boolean.TRUE && this.getAllBranche() == Boolean.TRUE && this.getAllCategories() == Boolean.TRUE && this.getPersonnaliser_apporteur() == Boolean.FALSE && this.getPersonnaliser_branche() == Boolean.FALSE && this.getPersonnaliser_categories() == Boolean.FALSE) {
//
//                    /*
//                tous les apporteurs et les branches et les categories   sont prit en compte a la foi
//                     */
//                    listeQuittanceByPeriode = quittanceService.listeQuittanceByAllAgenceOrChoixMultiple(listeIntermediaires, listeApporteur, listeBranches, listeCategories, typeActe, typeQuittance);
//                    System.out.println("taille " + listeQuittanceByPeriode.size());
//                    for (OrclassQuitance qp : listeQuittanceByPeriode) {
//                        System.out.println("quittance Id " + qp.getId() + " numero police " + qp.getIdPolice().getPolice().toString());
//                        System.out.println("avenant" + qp.getIdAvenant());
//                        System.out.println("agence" + qp.getIdPolice().getIdIntermediaire().getIdRefIntermediaire().getRaisonSociale() + "-" + qp.getIdPolice().getIdIntermediaire().getIdRefIntermediaire().getRaisonSociale());
//                    }
//
//                } else /*
//                 le choix va etre personnaliser
//                 */ /*
//                 en premier le choix personnaliser porte sur les apporteurs 
//                 */ if (this.listeApporteurSecte.isEmpty() && !this.listeBranchesSelectect.isEmpty() && !this.listeCategoriesSecte.isEmpty() && typeActe != null && typeQuittance != null && apporteurDebut.getIdRefApporteur().getCode() != null && apporteurFin.getIdRefApporteur().getCode() != null && this.getPersonnaliser_apporteur() == Boolean.TRUE && this.getAllApporteur() == Boolean.FALSE && this.getAllBranche() == Boolean.FALSE && this.getAllCategories() == Boolean.FALSE) {
//
//                    /*
//                tous les apporteurs et les branches et les categories   sont prit en compte a la foi
//                     */
////                    OrclassRefApporteur refapp1 = refApporteurDao.findEntityHavingValue("code", apporteurDebut.getIdRefApporteur().getCode());
////                    OrclassRefApporteur refapp2=refApporteurDao.findEntityHavingValue("code", apporteurFin.getIdRefApporteur().getCode());
////                    apporteurDebut=apporteurDao.findEntityHavingValue("idRefApporteur", refapp1);
////                    apporteurFin=apporteurDao.findEntityHavingValue("idRefApporteur", refapp2);
//                    String[] valeur = apporteurDebut.getIdRefApporteur().getCode().split("-");
//                    codePrincipal_1 = new BigInteger(valeur[0].trim());
//                    valeur = apporteurFin.getIdRefApporteur().getCode().split("-");
//                    codePrincipal_2 = new BigInteger(valeur[0].trim());
//                    listeApporteur = apporteurDao.listeApporteurBetweenCodePrincipale(entreprise, debut, fin, codePrincipal_1, codePrincipal_2);
//
//                    listeQuittanceByPeriode = quittanceService.listeQuittanceByAllAgenceOrChoixMultiple(listeIntermediaires, listeApporteur, listeBranchesSelectect, listeCategories, typeActe, typeQuittance);
//                    System.out.println("taille " + listeQuittanceByPeriode.size());
//                    for (OrclassQuitance qp : listeQuittanceByPeriode) {
//                        System.out.println("quittance Id " + qp.getId() + " numero police " + qp.getIdPolice().getPolice().toString());
//                        System.out.println("avenant" + qp.getIdAvenant());
//                        System.out.println("agence" + qp.getIdPolice().getIdIntermediaire().getIdRefIntermediaire().getRaisonSociale() + "-" + qp.getIdPolice().getIdIntermediaire().getIdRefIntermediaire().getRaisonSociale());
//                    }
//
//                } else if (this.listeApporteurSecte.isEmpty() && this.listeBranchesSelectect.isEmpty() && !this.listeCategoriesSecte.isEmpty() && typeActe != null && typeQuittance != null && brancheDebut.getCode() != null && brancheFin.getCode() != null && apporteurDebut.getIdRefApporteur().getCode() != null && apporteurFin.getIdRefApporteur().getCode() != null && this.getPersonnaliser_apporteur() == Boolean.TRUE && this.getPersonnaliser_branche() == Boolean.TRUE && this.getAllCategories() == Boolean.FALSE) {
//                    String[] valeur = brancheDebut.getCode().split("-");
//                    codePrincipal_1 = new BigInteger(valeur[0].trim());
//                    valeur = brancheFin.getCode().split("-");
//                    codePrincipal_2 = new BigInteger(valeur[0].trim());
//                    listeBranches = brancheDao.listeBrancherByChoixPersonnalise(entreprise, debut, fin, codePrincipal_1, codePrincipal_2);
//                    valeur = apporteurDebut.getIdRefApporteur().getCode().split("-");
//                    codePrincipal_1 = new BigInteger(valeur[0].trim());
//                    valeur = apporteurFin.getIdRefApporteur().getCode().split("-");
//                    codePrincipal_2 = new BigInteger(valeur[0].trim());
//                    listeApporteur = apporteurDao.listeApporteurBetweenCodePrincipale(entreprise, debut, fin, codePrincipal_1, codePrincipal_2);
//                    listeQuittanceByPeriode = quittanceService.listeQuittanceByAllAgenceOrChoixMultiple(listeIntermediaires, listeApporteur, listeBranches, listeCategories, typeActe, typeQuittance);
//                    System.out.println("taille " + listeQuittanceByPeriode.size());
//                    for (OrclassQuitance qp : listeQuittanceByPeriode) {
//                        System.out.println("quittance Id " + qp.getId() + " numero police " + qp.getIdPolice().getPolice().toString());
//                        System.out.println("avenant" + qp.getIdAvenant());
//                        System.out.println("agence" + qp.getIdPolice().getIdIntermediaire().getIdRefIntermediaire().getRaisonSociale() + "-" + qp.getIdPolice().getIdIntermediaire().getIdRefIntermediaire().getRaisonSociale());
//                    }
//
//                } else if (!this.listeApporteurSecte.isEmpty() && this.listeBranchesSelectect.isEmpty() && !this.listeCategoriesSecte.isEmpty() && typeActe != null && typeQuittance != null && brancheDebut.getCode() != null && brancheFin.getCode() != null && this.getPersonnaliser_branche() == Boolean.TRUE && this.getAllCategories() == Boolean.FALSE && this.getAllApporteur() == Boolean.FALSE) {
////                    listeBranches = brancheDao.listeBrancherByChoixPersonnalise(entreprise, debut, fin, brancheDebut.getCodePrincipal(), brancheFin.getCodePrincipal());
////                    listeApporteur = apporteurDao.listeApporteurBetweenCodePrincipale(entreprise, debut, fin, new BigInteger(apporteurDebut.getIdRefApporteur().getCode()), new BigInteger(apporteurFin.getIdRefApporteur().getCode()));
//                    String[] valeur = brancheDebut.getCode().split("-");
//                    codePrincipal_1 = new BigInteger(valeur[0].trim());
//                    valeur = brancheFin.getCode().split("-");
//                    codePrincipal_2 = new BigInteger(valeur[0].trim());
//                    listeBranches = brancheDao.listeBrancherByChoixPersonnalise(entreprise, debut, fin, codePrincipal_1, codePrincipal_2);
//
//                    listeQuittanceByPeriode = quittanceService.listeQuittanceByAllAgenceOrChoixMultiple(listeIntermediaires, listeApporteurSecte, listeBranches, listeCategories, typeActe, typeQuittance);
//                    System.out.println("taille " + listeQuittanceByPeriode.size());
//                    for (OrclassQuitance qp : listeQuittanceByPeriode) {
//                        System.out.println("quittance Id " + qp.getId() + " numero police " + qp.getIdPolice().getPolice().toString());
//                        System.out.println("avenant" + qp.getIdAvenant());
//                        System.out.println("agence" + qp.getIdPolice().getIdIntermediaire().getIdRefIntermediaire().getRaisonSociale() + "-" + qp.getIdPolice().getIdIntermediaire().getIdRefIntermediaire().getRaisonSociale());
//                    }
//
//                } else if (!this.listeApporteurSecte.isEmpty() && !this.listeBranchesSelectect.isEmpty() && this.listeCategoriesSecte.isEmpty() && typeActe != null && typeQuittance != null && categoriesDebut.getCode() != null && categoriesFin.getCode() != null && this.getPersonnaliser_categories() == Boolean.TRUE && this.getAllBranche() == Boolean.FALSE && this.getAllApporteur() == Boolean.FALSE) {
//                    String[] valeur = categoriesDebut.getCode().split("-");
//                    codePrincipal_1 = new BigInteger(valeur[0].trim());
//                    valeur = categoriesFin.getCode().split("-");
//                    codePrincipal_2 = new BigInteger(valeur[0].trim());
//
//                    listeCategories = categoriesDao.listeCategorieByChoixPersonnaliser(entreprise, debut, fin, codePrincipal_1, codePrincipal_2);
//                    listeQuittanceByPeriode = quittanceService.listeQuittanceByAllAgenceOrChoixMultiple(listeIntermediaires, listeApporteurSecte, listeBranchesSelectect, listeCategories, typeActe, typeQuittance);
//                    System.out.println("taille " + listeQuittanceByPeriode.size());
//                    for (OrclassQuitance qp : listeQuittanceByPeriode) {
//                        System.out.println("quittance Id " + qp.getId() + " numero police " + qp.getIdPolice().getPolice().toString());
//                        System.out.println("avenant" + qp.getIdAvenant());
//                        System.out.println("agence" + qp.getIdPolice().getIdIntermediaire().getIdRefIntermediaire().getRaisonSociale() + "-" + qp.getIdPolice().getIdIntermediaire().getIdRefIntermediaire().getRaisonSociale());
//                    }
//
//                } else if (this.listeApporteurSecte.isEmpty() && this.listeBranchesSelectect.isEmpty() && this.listeCategoriesSecte.isEmpty() && typeActe != null && typeQuittance != null && categoriesDebut.getCode() != null && categoriesFin.getCode() != null && apporteurDebut.getIdRefApporteur().getCode() != null && apporteurFin.getIdRefApporteur().getCode() != null && brancheDebut.getCode() != null && brancheFin.getCode() != null && this.getPersonnaliser_apporteur() == Boolean.TRUE && this.getPersonnaliser_branche() == Boolean.TRUE && this.getPersonnaliser_categories() == Boolean.TRUE) {
//                    String[] valeur = categoriesDebut.getCode().split("-");
//                    codePrincipal_1 = new BigInteger(valeur[0].trim());
//                    valeur = categoriesFin.getCode().split("-");
//                    codePrincipal_2 = new BigInteger(valeur[0].trim());
//                    listeCategories = categoriesDao.listeCategorieByChoixPersonnaliser(entreprise, debut, fin, codePrincipal_1, codePrincipal_2);
//                    valeur = brancheDebut.getCode().split("-");
//                    codePrincipal_1 = new BigInteger(valeur[0].trim());
//                    valeur = brancheFin.getCode().split("-");
//                    codePrincipal_2 = new BigInteger(valeur[0].trim());
//                    listeBranches = brancheDao.listeBrancherByChoixPersonnalise(entreprise, debut, fin, codePrincipal_1, codePrincipal_2);
//
//                    valeur = apporteurDebut.getIdRefApporteur().getCode().split("-");
//                    codePrincipal_1 = new BigInteger(valeur[0].trim());
//                    valeur = apporteurFin.getIdRefApporteur().getCode().split("-");
//                    codePrincipal_2 = new BigInteger(valeur[0].trim());
//                    listeApporteur = apporteurDao.listeApporteurBetweenCodePrincipale(entreprise, debut, fin, codePrincipal_1, codePrincipal_2);
//
//                    listeQuittanceByPeriode = quittanceService.listeQuittanceByAllAgenceOrChoixMultiple(listeIntermediaires, listeApporteur, listeBranches, listeCategories, typeActe, typeQuittance);
//                    System.out.println("taille " + listeQuittanceByPeriode.size());
//                    for (OrclassQuitance qp : listeQuittanceByPeriode) {
//                        System.out.println("quittance Id " + qp.getId() + " numero police " + qp.getIdPolice().getPolice().toString());
//                        System.out.println("avenant" + qp.getIdAvenant());
//                        System.out.println("agence" + qp.getIdPolice().getIdIntermediaire().getIdRefIntermediaire().getRaisonSociale() + "-" + qp.getIdPolice().getIdIntermediaire().getIdRefIntermediaire().getRaisonSociale());
//                    }
//
//                } else if (this.listeApporteurSecte.isEmpty() && this.listeBranchesSelectect.isEmpty() && this.listeCategoriesSecte.isEmpty() && typeActe != null && typeQuittance != null  && apporteurDebut.getIdRefApporteur().getCode() != null && apporteurFin.getIdRefApporteur().getCode() != null && brancheDebut.getCode() != null && brancheFin.getCode() != null && this.getPersonnaliser_apporteur() == Boolean.TRUE && this.getPersonnaliser_branche() == Boolean.TRUE && this.getAllCategories() == Boolean.TRUE) {
//
//                    String[] valeur = brancheDebut.getCode().split("-");
//                    codePrincipal_1 = new BigInteger(valeur[0].trim());
//                    valeur = brancheFin.getCode().split("-");
//                    codePrincipal_2 = new BigInteger(valeur[0].trim());
//                    listeBranches = brancheDao.listeBrancherByChoixPersonnalise(entreprise, debut, fin, codePrincipal_1, codePrincipal_2);
//
//                    valeur = apporteurDebut.getIdRefApporteur().getCode().split("-");
//                    codePrincipal_1 = new BigInteger(valeur[0].trim());
//                    valeur = apporteurFin.getIdRefApporteur().getCode().split("-");
//                    codePrincipal_2 = new BigInteger(valeur[0].trim());
//                    listeApporteur = apporteurDao.listeApporteurBetweenCodePrincipale(entreprise, debut, fin, codePrincipal_1, codePrincipal_2);
//
//                    listeQuittanceByPeriode = quittanceService.listeQuittanceByAllAgenceOrChoixMultiple(listeIntermediaires, listeApporteur, listeBranches, listeCategories, typeActe, typeQuittance);
//                    System.out.println("taille " + listeQuittanceByPeriode.size());
//                    for (OrclassQuitance qp : listeQuittanceByPeriode) {
//                        System.out.println("quittance Id " + qp.getId() + " numero police " + qp.getIdPolice().getPolice().toString());
//                        System.out.println("avenant" + qp.getIdAvenant());
//                        System.out.println("agence" + qp.getIdPolice().getIdIntermediaire().getIdRefIntermediaire().getRaisonSociale() + "-" + qp.getIdPolice().getIdIntermediaire().getIdRefIntermediaire().getRaisonSociale());
//                    }
//
//                } else if (this.listeApporteurSecte.isEmpty() && this.listeBranchesSelectect.isEmpty() && this.listeCategoriesSecte.isEmpty() && typeActe != null && typeQuittance != null && this.getAllApporteur() == Boolean.TRUE && this.getAllBranche() == Boolean.TRUE && this.getAllCategories() == Boolean.TRUE) {
//
//                    listeQuittanceByPeriode = quittanceService.listeQuittanceByAllAgenceOrChoixMultiple(listeIntermediaires, listeApporteur, listeBranches, listeCategories, typeActe, typeQuittance);
//                    System.out.println("taille " + listeQuittanceByPeriode.size());
//                    for (OrclassQuitance qp : listeQuittanceByPeriode) {
//                        System.out.println("quittance Id " + qp.getId() + " numero police " + qp.getIdPolice().getPolice().toString());
//                        System.out.println("avenant" + qp.getIdAvenant());
//                        System.out.println("agence" + qp.getIdPolice().getIdIntermediaire().getIdRefIntermediaire().getRaisonSociale() + "-" + qp.getIdPolice().getIdIntermediaire().getIdRefIntermediaire().getRaisonSociale());
//                    }
//                }
//
//            }
//        }
//        if (!listeQuittanceByPeriode.isEmpty()) {
//         //  this.print();//
//        } else {
//             PrimeFaces.current().ajax().update(":form1");
//            PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage("LIST IS EMPTY", "Aucune donnée... ou la demande n est prie en compte... essayez à nouveau"));
//        
//        }
//    }
//
//    public void print() throws IOException, JRException {
//
//        Map<String, Object> map = new HashMap<>();
//        ComparatorChain chain;
//        ImageIcon logo = null;
//        ImageIcon img = null;
//        FacesContext ctx = FacesContext.getCurrentInstance();
//        Locale myLoc = ctx.getViewRoot().getLocale();
//        if (this.entreprise.getImage() != null) {
//            logo = new ImageIcon(this.entreprise.getImage());
//            map.put("logo", logo.getImage());
//        }
//        map.put("societe", this.entreprise.getRaisonSociale());
//        map.put("tel", this.entreprise.getAdresse().getTel());
//        map.put("bp", this.entreprise.getAdresse().getBp());
//        map.put("code", this.entreprise.getCode());
//        map.put("date", GlobalFonctions.formatDate(new Date()));
//        map.put("etat", GlobalFonctions.valueObject(typeEtat));
//        map.put("quittance", GlobalFonctions.valueObject(typeActe));
//        map.put("acte", GlobalFonctions.valueObject(typeActe));
//        map.put("debut", GlobalFonctions.formatDate(debut));
//        map.put("fin", GlobalFonctions.formatDate(debut));
//
//        switch (typeEtat) {
//            case etat_detail_agence:
//                chain = new ComparatorChain();
//                chain.addComparator(OrclassQuitance.ByCodeAgenceASC);
//                Collections.sort((List<OrclassQuitance>) this.listeQuittanceByPeriode, chain);
//                GlobalFonctions.printPdf(GlobalFonctions.dossierPrincipal + File.separator + GlobalFonctions.dossierContratSante + File.separator + GlobalFonctions.dossierDefaut + File.separator + "namejasper", listeQuittanceByPeriode, map, null);
////   
//                break;
//            case etat_detail_apporteur:
//                chain = new ComparatorChain();
//                chain.addComparator(OrclassQuitance.ByCodeApporteurASC);
//                chain.addComparator(OrclassQuitance.ByIdQuittanceASC);
//                Collections.sort((List<OrclassQuitance>) this.listeQuittanceByPeriode, chain);
//                GlobalFonctions.printPdf(GlobalFonctions.dossierPrincipal + File.separator + GlobalFonctions.dossierContratSante + File.separator + GlobalFonctions.dossierDefaut + File.separator + "detail_apporteur", listeQuittanceByPeriode, map, null);
////   
//                break;
//            case etat_detail_branche:
//                chain = new ComparatorChain();
//                chain.addComparator(OrclassQuitance.ByCodeBrancheASC);
//                Collections.sort((List<OrclassQuitance>) this.listeQuittanceByPeriode, chain);
////                GlobalFonctions.printPdf(GlobalFonctions.dossierPrincipal + File.separator + GlobalFonctions.dossierContratSante + File.separator + GlobalFonctions.dossierDefaut + File.separator + "namejasper", listeQuittanceByPeriode, map, null);
////   
//                PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage("Etat deliver ... file inexistant"));
//                break;
//            case etat_detail_categories:
//                chain = new ComparatorChain();
//                chain.addComparator(OrclassQuitance.ByCodeCategoriesASC);
//                Collections.sort((List<OrclassQuitance>) this.listeQuittanceByPeriode, chain);
////                GlobalFonctions.printPdf(GlobalFonctions.dossierPrincipal + File.separator + GlobalFonctions.dossierContratSante + File.separator + GlobalFonctions.dossierDefaut + File.separator + "namejasper", listeQuittanceByPeriode, map, null);
//                PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage("Etat deliver ... file inexistant"));
//                break;
//        }
//
//    }
//
//    public void telechargerForExcell() throws IOException, JRException {
//   Map<String, Object> map = new HashMap<>();
//        ComparatorChain chain;
//        ImageIcon logo = null;
//        ImageIcon img = null;
//        FacesContext ctx = FacesContext.getCurrentInstance();
//        Locale myLoc = ctx.getViewRoot().getLocale();
//        if (this.entreprise.getImage() != null) {
//            logo = new ImageIcon(this.entreprise.getImage());
//            map.put("logo", logo.getImage());
//        }
//        map.put("societe", this.entreprise.getRaisonSociale());
//        map.put("tel", this.entreprise.getAdresse().getTel());
//        map.put("bp", this.entreprise.getAdresse().getBp());
//        map.put("code", this.entreprise.getCode());
//        map.put("date", GlobalFonctions.formatDate(new Date()));
//        map.put("etat", GlobalFonctions.valueObject(typeEtat));
//        map.put("quittance", GlobalFonctions.valueObject(typeActe));
//        map.put("acte", GlobalFonctions.valueObject(typeActe));
//        map.put("debut", GlobalFonctions.formatDate(debut));
//        map.put("fin", GlobalFonctions.formatDate(debut));
//
//        switch (typeEtat) {
//            case etat_detail_agence:
//                chain = new ComparatorChain();
//                chain.addComparator(OrclassQuitance.ByCodeAgenceASC);
//                Collections.sort((List<OrclassQuitance>) this.listeQuittanceByPeriode, chain);
//                 PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage("Etat deliver ... file inexistant"));
////                GlobalFonctions.printExcell(GlobalFonctions.dossierPrincipal + File.separator + GlobalFonctions.dossierContratSante + File.separator + GlobalFonctions.dossierDefaut + File.separator + "namejasper", listeQuittanceByPeriode, map);
////   
//                break;
//            case etat_detail_apporteur:
//                chain = new ComparatorChain();
//                chain.addComparator(OrclassQuitance.ByCodeApporteurASC);
//                chain.addComparator(OrclassQuitance.ByIdQuittanceASC);
//                Collections.sort((List<OrclassQuitance>) this.listeQuittanceByPeriode, chain);
//                GlobalFonctions.printExcell(GlobalFonctions.dossierPrincipal + File.separator + GlobalFonctions.dossierContratSante + File.separator + GlobalFonctions.dossierDefaut + File.separator + "detail_apporteur", listeQuittanceByPeriode, map,typeEtat.name());
////   
//                break;
//            case etat_detail_branche:
//                chain = new ComparatorChain();
//                chain.addComparator(OrclassQuitance.ByCodeBrancheASC);
//                Collections.sort((List<OrclassQuitance>) this.listeQuittanceByPeriode, chain);
////                GlobalFonctions.printPdf(GlobalFonctions.dossierPrincipal + File.separator + GlobalFonctions.dossierContratSante + File.separator + GlobalFonctions.dossierDefaut + File.separator + "namejasper", listeQuittanceByPeriode, map, null);
////   
//                PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage("Etat deliver ... file inexistant"));
//                break;
//            case etat_detail_categories:
//                chain = new ComparatorChain();
//                chain.addComparator(OrclassQuitance.ByCodeCategoriesASC);
//                Collections.sort((List<OrclassQuitance>) this.listeQuittanceByPeriode, chain);
////                GlobalFonctions.printPdf(GlobalFonctions.dossierPrincipal + File.separator + GlobalFonctions.dossierContratSante + File.separator + GlobalFonctions.dossierDefaut + File.separator + "namejasper", listeQuittanceByPeriode, map, null);
//                PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage("Etat deliver ... file inexistant"));
//                break;
//        }
//    }
//
//    public void reload() throws IOException {
//        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
//        ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());
//    }
//
//    public void controlleAllApporteur() {
//        if (Objects.equals(allApporteur, Boolean.TRUE)) {
//            personnaliser_apporteur = Boolean.FALSE;
//            apporteurDebut = new OrclassApporteur();
//            apporteurFin = new OrclassApporteur();
//            listeApporteurSecte.clear();
//        }
//    }
//
//    public void controllePersonnalistionApporteur() {
//        if (Objects.equals(personnaliser_apporteur, Boolean.TRUE)) {
//            allApporteur = Boolean.FALSE;
//            apporteurDebut = new OrclassApporteur();
//            if (apporteurDebut.getIdRefApporteur() == null && apporteurDebut.getIdRefApporteur().getCode() == null) {
//                apporteurDebut.setIdRefApporteur(new OrclassRefApporteur());
//                apporteurDebut.getIdRefApporteur().setCode("");
//            }
//            apporteurFin = new OrclassApporteur();
//            if (apporteurFin.getIdRefApporteur() == null && apporteurFin.getIdRefApporteur().getCode() == null) {
//                apporteurFin.setIdRefApporteur(new OrclassRefApporteur());
//                apporteurFin.getIdRefApporteur().setCode("");
//            }
//            listeApporteurSecte.clear();
//        }
//    }
//
//    public void controlleAllBranche() {
//        if (Objects.equals(allBranche, Boolean.TRUE)) {
//            personnaliser_branche = Boolean.FALSE;
//            brancheDebut = new OrclassBranches();
//            brancheFin = new OrclassBranches();
//            listeBranchesSelectect.clear();
//
//            this.chargeCategoriesBySelecteBranche();
//        }
//    }
//
//    public void controllePersonnalistionBranche() {
//        if (Objects.equals(this.getPersonnaliser_branche(), Boolean.TRUE)) {
//            allBranche = Boolean.FALSE;
//            if (brancheDebut == null) {
//                brancheDebut = new OrclassBranches();
//                if (brancheDebut.getCode() == null) {
//                    brancheDebut.setCode("");
//                }
//            }
//            if (brancheFin == null) {
//                brancheFin = new OrclassBranches();
//                if (brancheFin.getCode() == null) {
//                    brancheFin.setCode("");
//                }
//            }
//
//            listeBranchesSelectect.clear();
//            this.chargeCategoriesBySelecteBranche();
//        }
//    }
//
//    public void controlleAllCategorie() {
//        if (Objects.equals(allCategories, Boolean.TRUE)) {
//            personnaliser_categories = Boolean.FALSE;
//            categoriesDebut = new OrclassCategories();
//            categoriesFin = new OrclassCategories();
//            listeCategoriesSecte.clear();
//        }
//    }
//
//    public void controllePersonnalistionCategorie() {
//        if (Objects.equals(personnaliser_categories, Boolean.TRUE)) {
//            allCategories = Boolean.FALSE;
//            categoriesDebut = new OrclassCategories();
//            categoriesFin = new OrclassCategories();
//            listeCategoriesSecte.clear();
//        }
//    }
//
//    public OrclassIntermediairesDao getAgenceDao() {
//        return agenceDao;
//    }
//
//    public void setAgenceDao(OrclassIntermediairesDao agenceDao) {
//        this.agenceDao = agenceDao;
//    }
//
//    public OrclassBranchesDao getBrancheDao() {
//        return brancheDao;
//    }
//
//    public void setBrancheDao(OrclassBranchesDao brancheDao) {
//        this.brancheDao = brancheDao;
//    }
//
//    public OrclassBranches getBranche() {
//        return branche;
//    }
//
//    public void setBranche(OrclassBranches branche) {
//        this.branche = branche;
//    }
//
//    public OrclassApporteurDao getApporteurDao() {
//        return apporteurDao;
//    }
//
//    public void setApporteurDao(OrclassApporteurDao apporteurDao) {
//        this.apporteurDao = apporteurDao;
//    }
//
//    public OrclassApporteur getApporteur() {
//        return apporteur;
//    }
//
//    public void setApporteur(OrclassApporteur apporteur) {
//        this.apporteur = apporteur;
//    }
//
//    public List<OrclassIntermediaires> getListeIntermediaires() {
//        return listeIntermediaires;
//    }
//
//    public void setListeIntermediaires(List<OrclassIntermediaires> listeIntermediaires) {
//        this.listeIntermediaires = listeIntermediaires;
//    }
//
//    public List<OrclassIntermediaires> getListeIntermediairesSelect() {
//        return listeIntermediairesSelect;
//    }
//
//    public void setListeIntermediairesSelect(List<OrclassIntermediaires> listeIntermediairesSelect) {
//        this.listeIntermediairesSelect = listeIntermediairesSelect;
//    }
//
//    public List<OrclassBranches> getListeBranches() {
//        return listeBranches;
//    }
//
//    public void setListeBranches(List<OrclassBranches> listeBranches) {
//        this.listeBranches = listeBranches;
//    }
//
//    public List<OrclassBranches> getListeBranchesSelectect() {
//        return listeBranchesSelectect;
//    }
//
//    public void setListeBranchesSelectect(List<OrclassBranches> listeBranchesSelectect) {
//        this.listeBranchesSelectect = listeBranchesSelectect;
//    }
//
//    public List<OrclassApporteur> getListeApporteur() {
//        return listeApporteur;
//    }
//
//    public void setListeApporteur(List<OrclassApporteur> listeApporteur) {
//        this.listeApporteur = listeApporteur;
//    }
//
//    public List<OrclassApporteur> getListeApporteurSecte() {
//        return listeApporteurSecte;
//    }
//
//    public void setListeApporteurSecte(List<OrclassApporteur> listeApporteurSecte) {
//        this.listeApporteurSecte = listeApporteurSecte;
//    }
//
//    public Date getDebut() {
//        return debut;
//    }
//
//    public void setDebut(Date debut) {
//        this.debut = debut;
//    }
//
//    public Date getFin() {
//        return fin;
//    }
//
//    public void setFin(Date fin) {
//        this.fin = fin;
//    }
//
//    public Boolean getAllBranche() {
//
//        return allBranche;
//    }
//
//    public void setAllBranche(Boolean allBranche) {
//        this.allBranche = allBranche;
//    }
//
//    public Boolean getAllApporteur() {
//        return allApporteur;
//    }
//
//    public void setAllApporteur(Boolean allApporteur) {
//        this.allApporteur = allApporteur;
//    }
//
//    public Boolean getAllCategories() {
//        return allCategories;
//    }
//
//    public void setAllCategories(Boolean allCategories) {
//        this.allCategories = allCategories;
//    }
//
//    public Boolean getAllTypesQuittance() {
//        return allTypesQuittance;
//    }
//
//    public void setAllTypesQuittance(Boolean allTypesQuittance) {
//        this.allTypesQuittance = allTypesQuittance;
//    }
//
//    public Boolean getAllTypesActe() {
//        return allTypesActe;
//    }
//
//    public void setAllTypesActe(Boolean allTypesActe) {
//        this.allTypesActe = allTypesActe;
//    }
//
//    public OrclassIntermediaires getAgence() {
//
//        return agence;
//    }
//
//    public void setAgence(OrclassIntermediaires agence) {
//        this.agence = agence;
//    }
//
//    public OrclassEntreprises getEntreprise() {
//        return entreprise;
//    }
//
//    public void setEntreprise(OrclassEntreprises entreprise) {
//        this.entreprise = entreprise;
//    }
//
//    public OrclassCategories getCategoriesDebut() {
//        return categoriesDebut;
//    }
//
//    public void setCategoriesDebut(OrclassCategories categoriesDebut) {
//        this.categoriesDebut = categoriesDebut;
//    }
//
//    public OrclassCategories getCategoriesFin() {
//        return categoriesFin;
//    }
//
//    public void setCategoriesFin(OrclassCategories categoriesFin) {
//        this.categoriesFin = categoriesFin;
//    }
//
//    public OrclassBranches getBrancheDebut() {
//        return brancheDebut;
//    }
//
//    public void setBrancheDebut(OrclassBranches brancheDebut) {
//        this.brancheDebut = brancheDebut;
//    }
//
//    public OrclassBranches getBrancheFin() {
//        return brancheFin;
//    }
//
//    public void setBrancheFin(OrclassBranches brancheFin) {
//        this.brancheFin = brancheFin;
//    }
//
//    public OrclassApporteur getApporteurDebut() {
//        return apporteurDebut;
//    }
//
//    public void setApporteurDebut(OrclassApporteur apporteurDebut) {
//        this.apporteurDebut = apporteurDebut;
//    }
//
//    public OrclassApporteur getApporteurFin() {
//        return apporteurFin;
//    }
//
//    public void setApporteurFin(OrclassApporteur apporteurFin) {
//        this.apporteurFin = apporteurFin;
//    }
//
//    public Boolean getAllAgence() {
//
//        if (allAgence == Boolean.TRUE) {
//            if (debut != null && fin != null) {
//                listeApporteur = apporteurDao.listeApporteurByPeriode(entreprise, debut, fin);
//                listeBranches = brancheDao.listeBrancherByPeriode(entreprise, debut, fin);
//            }
//
//        }
//        return allAgence;
//    }
//
//    public void setAllAgence(Boolean allAgence) {
//        this.allAgence = allAgence;
//    }
//
//    public Boolean getPersonnaliser_apporteur() {
//        return personnaliser_apporteur;
//    }
//
//    public void setPersonnaliser_apporteur(Boolean personnaliser_apporteur) {
//        this.personnaliser_apporteur = personnaliser_apporteur;
//    }
//
//    public Boolean getPersonnaliser_branche() {
//        return personnaliser_branche;
//    }
//
//    public void setPersonnaliser_branche(Boolean personnaliser_branche) {
//        this.personnaliser_branche = personnaliser_branche;
//    }
//
//    public Boolean getPersonnaliser_categories() {
//        return personnaliser_categories;
//    }
//
//    public void setPersonnaliser_categories(Boolean personnaliser_categories) {
//        this.personnaliser_categories = personnaliser_categories;
//    }
//
//    public List<OrclassCategories> getListeCategories() {
//        return listeCategories;
//    }
//
//    public void setListeCategories(List<OrclassCategories> listeCategories) {
//        this.listeCategories = listeCategories;
//    }
//
//    public List<OrclassCategories> getListeCategoriesSecte() {
//        return listeCategoriesSecte;
//    }
//
//    public void setListeCategoriesSecte(List<OrclassCategories> listeCategoriesSecte) {
//        this.listeCategoriesSecte = listeCategoriesSecte;
//    }
//
//    public TypeActe getTypeActe() {
//        return typeActe;
//    }
//
//    public void setTypeActe(TypeActe typeActe) {
//        this.typeActe = typeActe;
//    }
//
//    public TypeQuittance getTypeQuittance() {
//        return typeQuittance;
//    }
//
//    public void setTypeQuittance(TypeQuittance typeQuittance) {
//        this.typeQuittance = typeQuittance;
//    }
//
//    public TypeEtat getTypeEtat() {
//        return typeEtat;
//    }
//
//    public void setTypeEtat(TypeEtat typeEtat) {
//        this.typeEtat = typeEtat;
//    }
//
//}
