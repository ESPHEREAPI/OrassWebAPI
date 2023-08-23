///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package controllers.parametrage;
//
//import dao.OrclassGestionCompagnieAgenceDao;
//import dao.OrclassGestionStockAgenceDao;
//import dao.OrclassGestionStockCompagnieDao;
//import dao.OrclassIntermediairesDao;
//import dao.OrclassMenusDao;
//import dao.OrclassModulesDao;
//import dao.OrclassNatureDocumentDao;
//import dao.OrclassOperationStockDao;
//import dao.OrclassTypeDocumentDao;
//import droitAcces.IDroitAcces;
//import enums.Actions;
//import enums.Entite_Emmettrice;
//import enums.FonctionnaliteModuleParametrage;
//import gestionStock.IGestionStock;
//import java.io.IOException;
//import javax.inject.Named;
//import java.io.Serializable;
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
//import javax.faces.view.ViewScoped;
//import javax.servlet.http.HttpServletRequest;
//import javax.validation.ConstraintViolationException;
//import modele.OrclassActions;
//import modele.OrclassEntreprises;
//import modele.OrclassFonctionnalites;
//import modele.OrclassGestionCompagnieAgence;
//import modele.OrclassGestionStockAgence;
//import modele.OrclassGestionStockCompagnie;
//import modele.OrclassIntermediaires;
//import modele.OrclassMenus;
//import modele.OrclassMenusAcces;
//import modele.OrclassModules;
//import modele.OrclassNatureDocument;
//import modele.OrclassOperationStock;
//import modele.OrclassTarif;
//import modele.OrclassTypeDocument;
//import modele.OrclassUtilisateurs;
//import org.primefaces.PrimeFaces;
//import org.primefaces.event.TabChangeEvent;
//import parametrage.ModuleMenu;
//import utils.GlobalFonctions;
//import utils.LocaleHelper;
//import utils.RecupBundle;
//
///**
// *
// * @author JIATOU FRANCK
// */
//@Named(value = "gestionStockController")
//@ViewScoped
//public class GestionStockController implements Serializable {
//
//    /**
//     * Creates a new instance of GestionStockController
//     */
//    @EJB
//    OrclassGestionStockCompagnieDao gestionStockCompagnieDao;
//    OrclassGestionStockCompagnie gestionStockCompagnie;
//    @EJB
//    OrclassGestionCompagnieAgenceDao gestionCompagnieAgenceDao;
//    OrclassGestionCompagnieAgence gestionCompagnieAgence;
//    @EJB
//    OrclassGestionStockAgenceDao gestionStockAgenceDao;
//    OrclassGestionStockAgence gestionStockAgence;
//    @EJB
//    OrclassTypeDocumentDao typeDocumentDao;
//    OrclassTypeDocument typeDocument;
//    @EJB
//    OrclassNatureDocumentDao natureDocumentDao;
//    OrclassNatureDocument natureDocument;
//    @EJB
//    OrclassOperationStockDao operationStockDao;
//    OrclassOperationStock operationStock;
//    @EJB
//    OrclassIntermediairesDao intermediairesDao;
//    OrclassIntermediaires intermediairesEmetrice;
//    OrclassIntermediaires intermediairesReceptrice;
//
//    @EJB
//    OrclassMenusDao menusDao;
//    @EJB
//    OrclassModulesDao modulesDao;
//    @EJB
//    IDroitAcces serviceAccess;
//    @EJB
//    IGestionStock serviceGestion;
//
//    private List<OrclassOperationStock> listeOperationStock = new ArrayList<>();
//    private List<OrclassNatureDocument> listeNatureDocument = new ArrayList<>();
//    private List<OrclassTypeDocument> listeTypeDocument = new ArrayList<>();
//    private List<OrclassIntermediaires> listeIntermediaires = new ArrayList<>();
//    private List<OrclassGestionStockAgence> listeGestionStockAgence = new ArrayList<>();
//    private List<OrclassGestionCompagnieAgence> listeGestionCompagnieAgence = new ArrayList<>();
//    private List<OrclassGestionStockCompagnie> listeGestionStockCompagnie = new ArrayList<>();
//    private List<OrclassGestionStockCompagnie> filterTypeGestionStockCompagnie = new ArrayList<>();
//    private List<OrclassGestionCompagnieAgence> filterGestionCompagnieAgence = new ArrayList<>();
//    private List<OrclassGestionStockAgence> filterGestionStockAgence = new ArrayList<>();
//    Entite_Emmettrice entite_Emmettrice = Entite_Emmettrice.entite_externe;
//    private Boolean entite_externe = Boolean.FALSE;
//    private Date date_effet = new Date();
//    BigInteger debut_serie = BigInteger.ZERO;
//    BigInteger fin_serie = BigInteger.ZERO;
//    BigInteger totalNombre_serie = BigInteger.ZERO;
//    private int choix0perations = 0;
//
//    String summary = "";
//    String msgdetail = "";
//    private OrclassMenus menu;
//    private OrclassModules module;
//    OrclassUtilisateurs user;
//    OrclassEntreprises entreprise;
//
//    public GestionStockController() {
//        gestionStockCompagnie = new OrclassGestionStockCompagnie();
//        gestionCompagnieAgence = new OrclassGestionCompagnieAgence();
//        gestionStockAgence = new OrclassGestionStockAgence();
//        operationStock = new OrclassOperationStock();
//        typeDocument = new OrclassTypeDocument();
//        natureDocument = new OrclassNatureDocument();
//        intermediairesEmetrice = new OrclassIntermediaires();
//        intermediairesReceptrice = new OrclassIntermediaires();
//
//    }
//
//    public void initial() {
//        gestionStockCompagnie = new OrclassGestionStockCompagnie();
//        gestionCompagnieAgence = new OrclassGestionCompagnieAgence();
//        gestionStockAgence = new OrclassGestionStockAgence();
//        operationStock = new OrclassOperationStock();
//        typeDocument = new OrclassTypeDocument();
//        natureDocument = new OrclassNatureDocument();
//        debut_serie = BigInteger.ZERO;
//        fin_serie = BigInteger.ZERO;
//        totalNombre_serie = BigInteger.ZERO;
//        entite_externe = Boolean.FALSE;
//        choix0perations = 0;
//
//    }
//
//    public void reset() {
//        initial();
//        PrimeFaces.current().ajax().update(":form1:tabprincipal");
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
//        menu = menusDao.findEntityHavingValue("code", ModuleMenu.mGestionDocumentStock);
//        module = modulesDao.findEntityHavingValue("code", "mod.ref");
//        user = (OrclassUtilisateurs) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(GlobalFonctions.SESSION_USER);
//        listeTypeDocument = (List<OrclassTypeDocument>) typeDocumentDao.findAll();
//        listeOperationStock = (List<OrclassOperationStock>) operationStockDao.findAll();
//        listeNatureDocument = (List<OrclassNatureDocument>) natureDocumentDao.findAll();
//        listeGestionStockCompagnie = gestionStockCompagnieDao.listeGestionCompagnie(entreprise);
//        this.updateTableGestionStockCompagnie();
//        listeGestionCompagnieAgence = gestionCompagnieAgenceDao.listeGestionCompagnieByAgence(entreprise);
//        this.updateTableGestionStockCompagnieAgence();
//    }
//
//    public void choixOperationStock() {
//        if (operationStock != null && operationStock.getId() != null) {
//            switch (operationStock.getTypeOperation()) {
//                case alimentation_stock_siege:// dans ce cas on ravitail la compagnie d ou  entite Emettrice est une entite Externe et receptrice est La compagnie
//                    entite_externe = Boolean.TRUE;
//                    choix0perations = 1;
////                    gestionStockCompagnie = gestionStockCompagnieDao.lastRowGestioCompagnie(entreprise);
////                    if (gestionStockCompagnie == null || gestionStockCompagnie.getId() == null) {
//                    gestionStockCompagnie = new OrclassGestionStockCompagnie();
//                    gestionStockCompagnie.setIdEntreprise(entreprise);
////                    }
//
//                    break;
//                case affectation:// dans ce cas nous ravitaillons les agence  d ou entite emetrice est la compagnie et receptrice est la  agence
//                    //nous recuperons la dernier ligne concernant la compagnie;
////                    gestionStockCompagnie = gestionStockCompagnieDao.lastRowGestioCompagnie(entreprise);
////                    if (gestionStockCompagnie == null || gestionStockCompagnie.getStockFinal() == null || gestionStockCompagnie.getStockFinal().intValue() == 0) {
////                        PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage("FATAL ERROR COMPAGNY DON'T HAVE DOCUMENT FOR AFFECTATION ", "OPPERATION FAIL"));
////                        return;
////                    }
//                    entite_externe = Boolean.FALSE;
//                    choix0perations = 2;
//                    listeIntermediaires = intermediairesDao.listeIntermediaireByEntreprise(entreprise);
//                    gestionStockCompagnie = new OrclassGestionStockCompagnie();
//                    gestionStockCompagnie.setIdEntreprise(entreprise);
//                    if (listeIntermediaires.isEmpty()) {
//                        PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage("LIST AGENCY IS EMPTY ", "OPPERATION NOT FOUND"));
//                        return;
//                    }
//
//                    break;
//                case transfert_doc_entre_entite:// le transfert se entre agence l emettrice ici est une agence et le receptrice est aussi une agence
////                    entite_externe = Boolean.FALSE;
////                    choix0perations = 3;
////                    if (listeIntermediaires.isEmpty()) {
////                        listeIntermediaires = intermediairesDao.listeIntermediaireByEntreprise(entreprise);
////                        if (listeIntermediaires.isEmpty()) {
////                            PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage("LIST AGENCY IS EMPTY ", "OPPERATION NOT FOUND"));
////                            return;
////                        }
////                    }
//                    PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage("OPERATION NOT SUPPORTED ", "MAKE ANOTHER CHOICE"));
//
//                    break;
//                case annulation_document_siege:
//                    PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage("OPERATION NOT SUPPORTED ", "MAKE ANOTHER CHOICE"));
//
//                    break;
//                case correction_annulation_document:
//                    PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage("OPERATION NOT SUPPORTED ", "MAKE ANOTHER CHOICE"));
//
//                    break;
//                case retour_au_stock:
//                    PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage("OPERATION NOT SUPPORTED ", "MAKE ANOTHER CHOICE"));
//
//                    break;
//                case retour_document_annuller:
//                    PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage("OPERATION NOT SUPPORTED ", "MAKE ANOTHER CHOICE"));
//
//                    break;
//
//            }
//        }
//    }
//
//    public void onTabChange(TabChangeEvent event) {
//        OrclassTarif t = null;
//        BigInteger ordre = BigInteger.ZERO;
//        if ((gestionStockCompagnie != null && gestionStockCompagnie.getId() != null)) {
//
//            return;
//        }
//        if ((gestionCompagnieAgence != null && gestionCompagnieAgence.getId() != null)) {
//            return;
//        }
//        if ("gestino_document".equals(event.getTab().getId())) {
////            this.reset();
////            debut_serie = BigInteger.ZERO;
////            fin_serie = BigInteger.ZERO;
////            totalNombre_serie = BigInteger.ZERO;
////            entite_Emmettrice = Entite_Emmettrice.entite_externe;
////            entite_externe = Boolean.TRUE;
//            PrimeFaces.current().ajax().update(":form1:tabprincipal");
////            PrimeFaces.current().executeScript("PF('principal').select(0);");
//
//        } else if ("gestino_document_compagnies".equals(event.getTab().getId())) {
//            listeGestionStockCompagnie = gestionStockCompagnieDao.listeGestionCompagnie(entreprise);
//            this.updateTableGestionStockCompagnie();
//            PrimeFaces.current().ajax().update(":form1:tabprincipal:idStockCompagnieTable");
////            PrimeFaces.current().executeScript("PF('principal').select(1);");
//
//        } else if ("gestino_document_agence".equals(event.getTab().getId())) {
//            listeGestionCompagnieAgence = gestionCompagnieAgenceDao.listeGestionCompagnieByAgence(entreprise);
//
////            PrimeFaces.current().executeScript("PF('principal').select(2);");
//        }
//
//    }
//
//    public void updateTableGestionStockCompagnie() {
//        UIComponent table = FacesContext.getCurrentInstance().getViewRoot().findComponent(":form1:tabprincipal:idStockCompagnieTable");
//        table.setValueExpression("sortBy", null);
//
//        PrimeFaces.current().executeScript("PF('stockCompagnieTable').clearFilters();");
////         PrimeFaces.current().ajax().update("form1_1");
//
//    }
//
//    public void updateTableGestionStockCompagnieAgence() {
//        UIComponent table = FacesContext.getCurrentInstance().getViewRoot().findComponent(":form1:tabprincipal:idStockCompagnieAgenceTable");
//        table.setValueExpression("sortBy", null);
//
//        PrimeFaces.current().executeScript("PF('stockCompagnieAgenceTable').clearFilters();");
////         PrimeFaces.current().ajax().update("form1_1");
//
//    }
//
//    public String showDetailsStcokCompagnie(OrclassGestionStockCompagnie item) {
//        System.err.println("item :" + item.getId());
//        if (item != null && item.getId() != null) {
//            if (gestionStockCompagnie == null && gestionStockCompagnie.getId() == null) {
//                this.setGestionStockCompagnie(item);
//            }
//            this.setOperationStock(gestionStockCompagnie.getIdOperationStock());
//            this.setDate_effet(gestionStockCompagnie.getDateEffet());
//            this.setEntite_Emmettrice(gestionStockCompagnie.getEntite_Emmettrice());
//            this.setDebut_serie(gestionStockCompagnie.getDebut_serie());
//            this.setFin_serie(gestionStockCompagnie.getFin_serie());
//            this.setTypeDocument(gestionStockCompagnie.getIdTypeDocument());
//            this.setNatureDocument(gestionStockCompagnie.getIdNatureDocument());
//            PrimeFaces.current().ajax().update(":form1:tabprincipal");
////            PrimeFaces.current().executeScript("PF('principal').select(0);");
//        }
//        return null;
//    }
//
//    public String showDetailsStockCompagnieAgence(OrclassGestionCompagnieAgence item) {
//        if (item != null && item.getId() != null) {
//            if (gestionCompagnieAgence == null && gestionCompagnieAgence.getId() == null) {
//                this.setGestionCompagnieAgence(item);
//            }
//            this.setOperationStock(getGestionCompagnieAgence().getIdOperationStock());
//            this.setDate_effet(getGestionCompagnieAgence().getDateEffet());
//            this.setGestionStockCompagnie(getGestionCompagnieAgence().getIdGestionStockCompagnie());
//            this.setDebut_serie(getGestionCompagnieAgence().getDebut_serie());
//            this.setFin_serie(getGestionCompagnieAgence().getFin_serie());
//            this.setTypeDocument(getGestionCompagnieAgence().getIdTypeDocument());
//            this.setNatureDocument(getGestionCompagnieAgence().getIdNatureDocument());
//            PrimeFaces.current().ajax().update(":form1");
//            PrimeFaces.current().executeScript("PF('principal').select(0);");
//        }
//        return null;
//    }
//
//    public String deleteStcokCompagnie() {
//        FacesContext ctx = FacesContext.getCurrentInstance();
//        //get default locale
//        Locale myLoc = ctx.getViewRoot().getLocale();
//        String entete[] = {LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "entite.classe", null, myLoc)};
//        OrclassGestionStockCompagnie oldNewGestionStockCompagnie = null;// ceci permettra de recuperer la la dernier ligne juste apresla suppression de ce dernier
//        try {
//            if (gestionStockCompagnie.getStockSortie() != null && gestionStockCompagnie.getStockSortie().intValue() != 0) {
//                PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage("OPERATION NOT FOUND", "THE VALUE HAS ALREADY BEEN USED "));
//                return null;
//            }
//          
//            
//            gestionStockCompagnieDao.delete(gestionStockCompagnie);
////            oldNewGestionStockCompagnie = gestionStockCompagnieDao.lastRowGestioCompagnie(entreprise, gestionStockCompagnie.getIdOperationStock(), gestionStockCompagnie.getIdNatureDocument(), gestionStockCompagnie.getIdTypeDocument());
////            if (oldNewGestionStockCompagnie != null && oldNewGestionStockCompagnie.getId() != null) {
////                if (gestionCompagnieAgenceDao.findAllEntitiesHavingValue("idGestionStockCompagnie", oldNewGestionStockCompagnie) == null) {
////                    oldNewGestionStockCompagnie.setPermission_modifier(Boolean.TRUE);
////                } else {
////                    oldNewGestionStockCompagnie.setPermission_modifier(Boolean.FALSE);
////                }
////                gestionStockCompagnieDao.update(oldNewGestionStockCompagnie);
////            }
//            String[] detail = {entete[0], gestionStockCompagnie.getIdEntreprise().getCode() + "-" + gestionStockCompagnie.getIdTypeDocument().getLibelle()};
//            listeGestionStockCompagnie = gestionStockCompagnieDao.listeGestionCompagnie(entreprise);
//            this.updateTableGestionStockCompagnie();
//            summary = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "summary.delete.succes", entete, myLoc);
//            msgdetail = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "detail.delete.succes", detail, myLoc);
//
//            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, summary, msgdetail));
//
//        } catch (ConstraintViolationException E) {
//            summary = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "summary.delete.error", entete, myLoc);
//            msgdetail = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "detail.error", null, myLoc);
//
//            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, summary, msgdetail + E.getMessage() + " " + E.getCause()));
//            throw E;
//        } catch (Throwable th) {
//            //ecrire dans le fichier de log  
//            summary = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "summary.delete.error", entete, myLoc);
//            msgdetail = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "detail.error", null, myLoc);
//
//            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, summary, msgdetail + th.getMessage() + " " + th.getCause() + "-" + th.getClass()));
//
//        }
//        this.reset();
//        return null;
//    }
//
//    public String deleteStcokCompagnieAgence() {
//        FacesContext ctx = FacesContext.getCurrentInstance();
//        //get default locale
//        Locale myLoc = ctx.getViewRoot().getLocale();
//        String entete[] = {LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "entite.classe", null, myLoc)};
//        OrclassGestionStockCompagnie oldNewGestionStockCompagnie = null;// ceci permettra de recuperer la la dernier ligne juste apresla suppression de ce dernier
//        try {
//            if (gestionCompagnieAgence.getStockSortie() != null && gestionCompagnieAgence.getStockSortie().intValue() != 0) {
//                PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage("OPERATION NOT FOUND", "THE VALUE HAS ALREADY BEEN USED "));
//                return null;
//            }
//            
//            if (gestionCompagnieAgence.getStockSortie() != null && gestionCompagnieAgence.getStockSortie().intValue() == 0) {
//                PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage("OPERATION NOT FOUND", "FONCTION IS NOT ACTIVE"));
//                return null;
//            }
//            
//            
//            gestionCompagnieAgenceDao.delete(gestionCompagnieAgence);
////            oldNewGestionStockCompagnie = gestionStockCompagnieDao.lastRowGestioCompagnie(entreprise, gestionStockCompagnie.getIdOperationStock(), gestionStockCompagnie.getIdNatureDocument(), gestionStockCompagnie.getIdTypeDocument());
////            if (oldNewGestionStockCompagnie != null && oldNewGestionStockCompagnie.getId() != null) {
////                if (gestionCompagnieAgenceDao.findAllEntitiesHavingValue("idGestionStockCompagnie", oldNewGestionStockCompagnie) == null) {
////                    oldNewGestionStockCompagnie.setPermission_modifier(Boolean.TRUE);
////                } else {
////                    oldNewGestionStockCompagnie.setPermission_modifier(Boolean.FALSE);
////                }
////                gestionStockCompagnieDao.update(oldNewGestionStockCompagnie);
////            }
//            String[] detail = {entete[0], gestionCompagnieAgence.getIdGestionStockCompagnie().getIdEntreprise().getCode() + "-" + gestionCompagnieAgence.getIdTypeDocument().getLibelle()};
//            listeGestionStockCompagnie = gestionStockCompagnieDao.listeGestionCompagnie(entreprise);
//            this.updateTableGestionStockCompagnie();
//            summary = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "summary.delete.succes", entete, myLoc);
//            msgdetail = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "detail.delete.succes", detail, myLoc);
//
//            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, summary, msgdetail));
//
//        } catch (ConstraintViolationException E) {
//            summary = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "summary.delete.error", entete, myLoc);
//            msgdetail = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "detail.error", null, myLoc);
//
//            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, summary, msgdetail + E.getMessage() + " " + E.getCause()));
//            throw E;
//        } catch (Throwable th) {
//            //ecrire dans le fichier de log  
//            summary = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "summary.delete.error", entete, myLoc);
//            msgdetail = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "detail.error", null, myLoc);
//
//            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, summary, msgdetail + th.getMessage() + " " + th.getCause() + "-" + th.getClass()));
//
//        }
//        this.reset();
//        return null;
//    }
//
//    public void reload() throws IOException {
//
//        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
//        ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());
//
//    }
//
//    // charge l emetrice lors du choix faite ce cas se porte sur deux operations celui de la compagnie vers l agence et celui de lagence vers l agence
//    public void controleGestionStockByEmmetrice() {
//        if (Objects.equals(entite_externe, Boolean.FALSE) && operationStock != null && operationStock.getId() != null && this.intermediairesReceptrice != null && this.intermediairesReceptrice.getIdIntermediaire() != null) {
//            switch (operationStock.getTypeOperation()) {
//                case affectation:
//                    gestionStockCompagnie = gestionStockCompagnieDao.lastRowGestioCompagnie(entreprise, natureDocument, typeDocument);
//                    if (gestionStockCompagnie == null) {
//                        PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage("OPPERATION FAIL", " FATAL ERROR COMPAGNY DON'T HAVE DOCUMENT FOR AFFECTATION "));
//                        return;
//                    }
////                    gestionCompagnieAgence = gestionCompagnieAgenceDao.lasteStockCompagnieAgence(entreprise, gestionStockCompagnie, operationStock, natureDocument, typeDocument, intermediairesEmetrice);
////                    if (gestionStockCompagnie == null || gestionCompagnieAgence.getStockFinal() == null || gestionCompagnieAgence.getStockFinal().intValue() == 0) {
////                        PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage("FATAL ERROR AGENCY DON'T HAVE DOCUMENT FOR TRANSFER ", "OPPERATION FAIL"));
////                        return;
////                    }
//
//                    break;
//            }
//        }
//    }
//
//    //on controlle si le total est soit egal au stock final
//    public void validationGestionStock() {
//        FacesContext ctx = FacesContext.getCurrentInstance();
//        BigInteger stockFinal = BigInteger.ZERO;
//        Boolean resultat;
//        OrclassGestionStockCompagnie gsc=null;
//        if (Objects.equals(entite_externe, Boolean.FALSE) && operationStock != null && operationStock.getId() != null) {
//            this.getTotalNombre_serie();
//           
//            switch (operationStock.getTypeOperation()) {
//                case affectation:
//                    this.controleGestionStockByEmmetrice();
//                    stockFinal = gestionStockCompagnie.getStockFinal();
//                    this.getTotalNombre_serie();
//                    if (totalNombre_serie.signum() == -1) {
//                        return;
//                    }
//                    if (stockFinal.compareTo(this.totalNombre_serie) == -1) {
//                        PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage("CHANGE QUANTITY", "THE TOTAL NUMBER OF SERIES TO BE AFFECTED IS GREATER THAN THE TOTAL NUMBER OF EXISTING SERIES FOR THIS COMPANY.." + GlobalFonctions.formatNumberGeneral(gestionStockCompagnie.getStockFinal().longValue())));
//                        return;
//                    }
//
//                    if (date_effet != null && operationStock != null && operationStock.getId() != null && natureDocument != null && natureDocument.getId() != null && typeDocument != null && typeDocument.getId() != null) {
//                        gestionCompagnieAgence = gestionCompagnieAgenceDao.finKey(entreprise, gestionStockCompagnie, date_effet, operationStock, natureDocument, typeDocument, intermediairesReceptrice);
//                        if (gestionCompagnieAgence != null && gestionCompagnieAgence.getId() != null) {
//                            PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage("CHANGE  DATE", "OPERATION IS EXIST PLEASE CHANGE DATE.."));
//                            return;
//                        }
//                    }
////                    // on  appelle la fonction poue inserer les donnees 
////                    stockFinal = gestionCompagnieAgence.getStockFinal();
////                    if (stockFinal.compareTo(totalNombre_serie) == -1) {
////                        PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage("CHANGE QUANTITY", "THE TOTAL NUMBER OF SERIES TO BE AFFECTED IS GREATER THAN THE TOTAL NUMBER OF EXISTING SERIES FOR THIS AGENCY.."));
////                        return;
////                    }
//                    resultat = this.serviceGestion.approvisionnementAgenceByCompagnie(entreprise, gestionStockCompagnie, intermediairesReceptrice, operationStock, typeDocument, natureDocument, date_effet, debut_serie, fin_serie, entite_Emmettrice, user);
//                    if (Objects.equals(resultat, Boolean.TRUE)) {
//                        ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, exception.Success.OPERATION_SUCCESS.toString(), exception.Success.INSERT_SUCCESS.toString()));
//                        reset();
//                        listeGestionStockCompagnie = gestionStockCompagnieDao.listeGestionCompagnie(entreprise);
//                        this.updateTableGestionStockCompagnie();
//                        listeGestionCompagnieAgence = gestionCompagnieAgenceDao.listeGestionCompagnieByAgence(entreprise);
//                        this.updateTableGestionStockCompagnieAgence();
//                    } else {
//                        ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, exception.Error.INSERT_ERROR.name(), exception.Error.OPERATION_FAILED.name()));
//                        listeGestionStockCompagnie = gestionStockCompagnieDao.listeGestionCompagnie(entreprise);
//                        this.updateTableGestionStockCompagnie();
//                        listeGestionCompagnieAgence = gestionCompagnieAgenceDao.listeGestionCompagnieByAgence(entreprise);
//                        this.updateTableGestionStockCompagnieAgence();
//                    }
//
//                    break;
//
//                case transfert_doc_entre_entite:
//                    stockFinal = gestionCompagnieAgence.getStockFinal();
//                    if (stockFinal.compareTo(totalNombre_serie) == -1) {
//                        PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage("CHANGE QUANTITY", "THE TOTAL NUMBER OF SERIES TO BE AFFECTED IS GREATER THAN THE TOTAL NUMBER OF EXISTING SERIES FOR THIS AGENCY.."));
//                        return;
//                    }
//
//                    // appelle de la fonction pour  transfert des document
//                    break;
//
//            }
//        } else if (Objects.equals(entite_externe, Boolean.TRUE) && operationStock != null && operationStock.getId() != null) {
//            this.getTotalNombre_serie();
//            if (totalNombre_serie.signum() == -1) {
//                return;
//            }
//            // appellation la fonction pour charger les documents a la compagnie
//            if (date_effet != null && operationStock != null && operationStock.getId() != null && natureDocument != null && natureDocument.getId() != null && typeDocument != null && typeDocument.getId() != null) {
//                gestionStockCompagnie = gestionStockCompagnieDao.finKey(entreprise, date_effet, operationStock, natureDocument, typeDocument);
//                if (gestionStockCompagnie != null && gestionStockCompagnie.getId() != null) {
//                    PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage("CHANGE  DATE", "OPERATION IS EXIST PLEASE CHANGE DATE.."));
//                    return;
//                }
//                resultat = this.serviceGestion.approvisionnementCompagnie(entreprise, operationStock, typeDocument, natureDocument, date_effet, debut_serie, fin_serie, entite_Emmettrice, user);
//                if (Objects.equals(resultat, Boolean.TRUE)) {
//                    ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, exception.Success.OPERATION_SUCCESS.toString(), exception.Success.INSERT_SUCCESS.toString()));
//                    reset();
//                    listeGestionStockCompagnie = gestionStockCompagnieDao.listeGestionCompagnie(entreprise);
//                    this.updateTableGestionStockCompagnie();
//                    listeGestionCompagnieAgence = gestionCompagnieAgenceDao.listeGestionCompagnieByAgence(entreprise);
//                } else {
//                    ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, exception.Error.INSERT_ERROR.name(), exception.Error.OPERATION_FAILED.name()));
//                    listeGestionStockCompagnie = gestionStockCompagnieDao.listeGestionCompagnie(entreprise);
//                    this.updateTableGestionStockCompagnie();
//                    listeGestionCompagnieAgence = gestionCompagnieAgenceDao.listeGestionCompagnieByAgence(entreprise);
//                }
//            }
//
//        }
//    }
//
//    public BigInteger getTotalNombre_serie() {
//        if (debut_serie != null && fin_serie != null) {
//            totalNombre_serie = fin_serie.subtract(debut_serie);
////            if (totalNombre_serie.signum() == -1) {
////                PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage("THE NUMBER IS NOT CORRECT ", "PLEASE CHECK THE VALUE END SERIE"));
////
////            }
//        }
//        return totalNombre_serie;
//    }
//
//    public void setTotalNombre_serie(BigInteger totalNombre_serie) {
//        this.totalNombre_serie = totalNombre_serie;
//    }
//
//    public OrclassGestionStockCompagnie getGestionStockCompagnie() {
//        return gestionStockCompagnie;
//    }
//
//    public void setGestionStockCompagnie(OrclassGestionStockCompagnie gestionStockCompagnie) {
//        this.gestionStockCompagnie = gestionStockCompagnie;
//    }
//
//    public OrclassGestionCompagnieAgence getGestionCompagnieAgence() {
//        return gestionCompagnieAgence;
//    }
//
//    public void setGestionCompagnieAgence(OrclassGestionCompagnieAgence gestionCompagnieAgence) {
//        this.gestionCompagnieAgence = gestionCompagnieAgence;
//    }
//
//    public OrclassGestionStockAgence getGestionStockAgence() {
//        return gestionStockAgence;
//    }
//
//    public void setGestionStockAgence(OrclassGestionStockAgence gestionStockAgence) {
//        this.gestionStockAgence = gestionStockAgence;
//    }
//
//    public OrclassTypeDocument getTypeDocument() {
//        return typeDocument;
//    }
//
//    public void setTypeDocument(OrclassTypeDocument typeDocument) {
//        this.typeDocument = typeDocument;
//    }
//
//    public OrclassIntermediaires getIntermediairesEmetrice() {
//        return intermediairesEmetrice;
//    }
//
//    public void setIntermediairesEmetrice(OrclassIntermediaires intermediairesEmetrice) {
//        this.intermediairesEmetrice = intermediairesEmetrice;
//    }
//
//    public OrclassIntermediaires getIntermediairesReceptrice() {
//        return intermediairesReceptrice;
//    }
//
//    public void setIntermediairesReceptrice(OrclassIntermediaires intermediairesReceptrice) {
//        this.intermediairesReceptrice = intermediairesReceptrice;
//    }
//
//    public List<OrclassOperationStock> getListeOperationStock() {
//        return listeOperationStock;
//    }
//
//    public void setListeOperationStock(List<OrclassOperationStock> listeOperationStock) {
//        this.listeOperationStock = listeOperationStock;
//    }
//
//    public List<OrclassNatureDocument> getListeNatureDocument() {
//        return listeNatureDocument;
//    }
//
//    public void setListeNatureDocument(List<OrclassNatureDocument> listeNatureDocument) {
//        this.listeNatureDocument = listeNatureDocument;
//    }
//
//    public List<OrclassTypeDocument> getListeTypeDocument() {
//        return listeTypeDocument;
//    }
//
//    public void setListeTypeDocument(List<OrclassTypeDocument> listeTypeDocument) {
//        this.listeTypeDocument = listeTypeDocument;
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
//    public List<OrclassGestionStockAgence> getListeGestionStockAgence() {
//        return listeGestionStockAgence;
//    }
//
//    public void setListeGestionStockAgence(List<OrclassGestionStockAgence> listeGestionStockAgence) {
//        this.listeGestionStockAgence = listeGestionStockAgence;
//    }
//
//    public List<OrclassGestionCompagnieAgence> getListeGestionCompagnieAgence() {
//        return listeGestionCompagnieAgence;
//    }
//
//    public void setListeGestionCompagnieAgence(List<OrclassGestionCompagnieAgence> listeGestionCompagnieAgence) {
//        this.listeGestionCompagnieAgence = listeGestionCompagnieAgence;
//    }
//
//    public List<OrclassGestionStockCompagnie> getListeGestionStockCompagnie() {
//        return listeGestionStockCompagnie;
//    }
//
//    public void setListeGestionStockCompagnie(List<OrclassGestionStockCompagnie> listeGestionStockCompagnie) {
//        this.listeGestionStockCompagnie = listeGestionStockCompagnie;
//    }
//
//    public List<OrclassGestionStockCompagnie> getFilterTypeGestionStockCompagnie() {
//        return filterTypeGestionStockCompagnie;
//    }
//
//    public void setFilterTypeGestionStockCompagnie(List<OrclassGestionStockCompagnie> filterTypeGestionStockCompagnie) {
//        this.filterTypeGestionStockCompagnie = filterTypeGestionStockCompagnie;
//    }
//
//    public List<OrclassGestionCompagnieAgence> getFilterGestionCompagnieAgence() {
//        return filterGestionCompagnieAgence;
//    }
//
//    public void setFilterGestionCompagnieAgence(List<OrclassGestionCompagnieAgence> filterGestionCompagnieAgence) {
//        this.filterGestionCompagnieAgence = filterGestionCompagnieAgence;
//    }
//
//    public List<OrclassGestionStockAgence> getFilterGestionStockAgence() {
//        return filterGestionStockAgence;
//    }
//
//    public void setFilterGestionStockAgence(List<OrclassGestionStockAgence> filterGestionStockAgence) {
//        this.filterGestionStockAgence = filterGestionStockAgence;
//    }
//
//    public Entite_Emmettrice getEntite_Emmettrice() {
//        return entite_Emmettrice;
//    }
//
//    public void setEntite_Emmettrice(Entite_Emmettrice entite_Emmettrice) {
//        this.entite_Emmettrice = entite_Emmettrice;
//    }
//
//    public Boolean getEntite_externe() {
//        return entite_externe;
//    }
//
//    public void setEntite_externe(Boolean entite_externe) {
//        this.entite_externe = entite_externe;
//    }
//
//    public Date getDate_effet() {
//        return date_effet;
//    }
//
//    public void setDate_effet(Date date_effet) {
//        this.date_effet = date_effet;
//    }
//
//    public BigInteger getDebut_serie() {
//        return debut_serie;
//    }
//
//    public void setDebut_serie(BigInteger debut_serie) {
//        this.debut_serie = debut_serie;
//    }
//
//    public BigInteger getFin_serie() {
//        return fin_serie;
//    }
//
//    public void setFin_serie(BigInteger fin_serie) {
//        this.fin_serie = fin_serie;
//    }
//
//    public OrclassOperationStock getOperationStock() {
//        return operationStock;
//    }
//
//    public void setOperationStock(OrclassOperationStock operationStock) {
//        this.operationStock = operationStock;
//    }
//
//    public OrclassNatureDocument getNatureDocument() {
//        return natureDocument;
//    }
//
//    public void setNatureDocument(OrclassNatureDocument natureDocument) {
//        this.natureDocument = natureDocument;
//    }
//
//    public int getChoix0perations() {
//        return choix0perations;
//    }
//
//    public void setChoix0perations(int choix0perations) {
//        this.choix0perations = choix0perations;
//    }
//
//    public Boolean accessValidation() {
//        OrclassActions action = null;
//        OrclassFonctionnalites fon = null;
//        OrclassMenusAcces ma = null;
//        if (menu != null && menu.getIdMenu() != null && module != null && module.getIdModule() != null) {
//            fon = serviceAccess.getFonctionnaliteByCode(FonctionnaliteModuleParametrage.gestionDocument_stock.name());
//            action = serviceAccess.getActionByCodeByFonctionnalit(Actions.valider.name(), fon);
//            return serviceAccess.accessAction(user, action, menu);
//        }
//        return Boolean.FALSE;
//    }
//
//    public Boolean accessModifierCompagnieEntiteExterne() {
//        OrclassActions action = null;
//        OrclassFonctionnalites fon = null;
//        if (menu != null && menu.getIdMenu() != null && module != null && module.getIdModule() != null) {
//            fon = serviceAccess.getFonctionnaliteByCode(FonctionnaliteModuleParametrage.gestionDocument_stock.name());
//            action = serviceAccess.getActionByCodeByFonctionnalit(Actions.modifier.name(), fon);
//            return serviceAccess.accessAction(user, action, menu);
//        }
//        return Boolean.FALSE;
//    }
//
//    public Boolean accessModifierCompagnieAgence() {
//        OrclassActions action = null;
//        OrclassFonctionnalites fon = null;
//        if (menu != null && menu.getIdMenu() != null && module != null && module.getIdModule() != null) {
//            fon = serviceAccess.getFonctionnaliteByCode(FonctionnaliteModuleParametrage.gestionDocument_agence_detail.name());
//            action = serviceAccess.getActionByCodeByFonctionnalit(Actions.modifier.name(), fon);
//            return serviceAccess.accessAction(user, action, menu);
//        }
//        return Boolean.FALSE;
//    }
//
//    public Boolean accessDeleteCompagnieEntiteExterne() {
//        OrclassActions action = null;
//        OrclassFonctionnalites fon = null;
//        if (menu != null && menu.getIdMenu() != null && module != null && module.getIdModule() != null) {
//            fon = serviceAccess.getFonctionnaliteByCode(FonctionnaliteModuleParametrage.gestionDocument_stock.name());
//            action = serviceAccess.getActionByCodeByFonctionnalit(Actions.supprimer.name(), fon);
//            return serviceAccess.accessAction(user, action, menu);
//        }
//        return Boolean.FALSE;
//    }
//
//    public Boolean accessDeleterCompagnieAgence() {
//        OrclassActions action = null;
//        OrclassFonctionnalites fon = null;
//        if (menu != null && menu.getIdMenu() != null && module != null && module.getIdModule() != null) {
//            fon = serviceAccess.getFonctionnaliteByCode(FonctionnaliteModuleParametrage.gestionDocument_agence_detail.name());
//            action = serviceAccess.getActionByCodeByFonctionnalit(Actions.supprimer.name(), fon);
//            return serviceAccess.accessAction(user, action, menu);
//        }
//        return Boolean.FALSE;
//    }
//
//}
