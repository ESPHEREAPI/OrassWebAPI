///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package controllers.parametrage;
//
//import static Entreprise.EntrepriseServices.logger;
//import Excell.IExcell;
//import dao.OrclassBranchesDao;
//import dao.OrclassCaracteristiquesDao;
//import dao.OrclassElement_Liste_CaracteristiqueDao;
//import dao.OrclassMenusDao;
//import dao.OrclassModulesDao;
//import dao.OrclassType_CaracteristiqueDao;
//import dao.OrclassUnite_CaracteristiqueDao;
//import dao.Orclass_ObjetDao;
//import droitAcces.IDroitAcces;
//import enums.Actions;
//import enums.FonctionnaliteModuleParametrage;
//import enums.LibelleBranche;
//import exception.GlobalException;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.InputStream;
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
//import javax.inject.Named;
//import javax.faces.view.ViewScoped;
//import javax.validation.ConstraintViolationException;
//import modele.OrclassActions;
//import modele.OrclassBranches;
//import modele.OrclassCaracteristiques;
//import modele.OrclassElement_Liste_Caracteristique;
//import modele.OrclassEntreprises;
//import modele.OrclassFonctionnalites;
//import modele.OrclassMenus;
//import modele.OrclassMenusAcces;
//import modele.OrclassModules;
//import modele.OrclassType_Caracteristique;
//import modele.OrclassUnite_Caracteristique;
//import modele.OrclassUtilisateurs;
//import modele.Orclass_Objet;
//import org.primefaces.PrimeFaces;
//import utils.GlobalFonctions;
//import utils.LocaleHelper;
//import utils.RecupBundle;
//
///**
// *
// * @author hp
// */
//@Named(value = "caracteristiqueController")
//@ViewScoped
//public class CracteristiqueController implements Serializable {
//
//    /**
//     * Creates a new instance of CracteristiqueController
//     */
//    @EJB
//    OrclassCaracteristiquesDao caracteristiquesDao;
//    OrclassCaracteristiques caracteristiques;
//    @EJB
//    IExcell serviceExcell;
//    @EJB
//    OrclassType_CaracteristiqueDao type_CaracteristiqueDao;
//    @EJB
//    OrclassUnite_CaracteristiqueDao unite_CaracteristiqueDao;
//    @EJB
//    OrclassElement_Liste_CaracteristiqueDao element_Liste_CaracteristiqueDao;
//    OrclassElement_Liste_Caracteristique element_Liste_Caracteristique;
//    @EJB
//    OrclassMenusDao menusDao;
//    @EJB
//    OrclassModulesDao modulesDao;
//    @EJB
//    IDroitAcces serviceAccess;
//    @EJB
//    OrclassBranchesDao branchesDao;
//    @EJB
//    Orclass_ObjetDao objetDao;
//
//    String summary = "";
//    String msgdetail = "";
//    private OrclassEntreprises entreprise;
//    private String currentFolder = "/photos";
//    private String pahtRubrique = currentFolder + "/caract.xls";
//    private String pahtRubrique2 = currentFolder + "/caract2.xls";
//
//    private List<OrclassCaracteristiques> listeCaracteristique = new ArrayList<>();
//    private List<OrclassCaracteristiques> filterCaracteristique = new ArrayList<>();
//
//    private List< OrclassUnite_Caracteristique> listeUnite_Caracteristique = new ArrayList<>();
//    private List< OrclassType_Caracteristique> listeType_Caracteristique = new ArrayList<>();
//    private List< OrclassElement_Liste_Caracteristique> listeElement_Liste_Caracteristique = new ArrayList<>();
//    private List<Orclass_Objet> listeObjet = new ArrayList<>();
//    private Boolean objetfForclass = Boolean.FALSE;
//    private List< OrclassBranches> lisBranche = new ArrayList<>();
//    private OrclassBranches branches;
//
//    private OrclassMenus menu;
//    private OrclassModules module;
//    OrclassUtilisateurs user;
//
//    public CracteristiqueController() {
//        caracteristiques = new OrclassCaracteristiques();
//
//    }
//
//    public void choixForTypeCaracteristique() {
//        String libelle = "Liste à Classe";
//        if (caracteristiques != null && caracteristiques.getType_Caracteristique() != null) {
//            if (caracteristiques.getType_Caracteristique().getLibelle().equals(libelle)) {
//                objetfForclass = Boolean.TRUE;
//                lisBranche = branchesDao.listAllBrancheShowAllCompagnie();
//                lisBranche.addAll(branchesDao.listAllBrancheByCompagnie(entreprise));
//            }
//        }
//    }
//
//  
//
//    @PostConstruct
//    void initialiseSession() {
//        FacesContext.getCurrentInstance().getExternalContext().getSession(true);
//        entreprise = (OrclassEntreprises) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(GlobalFonctions.ENTREPRISE_ACTIF);
//        if (entreprise == null) {
//            entreprise = (OrclassEntreprises) FacesContext.getCurrentInstance().getExternalContext().getApplicationMap().get(GlobalFonctions.ENTREPRISE_ACTIF);
//
//        }
//        creatRubriqueByExcell();
//        menu = menusDao.findEntityHavingValue("code", "caracteristique");
//        module = modulesDao.findEntityHavingValue("code", "mod.ref");
//        user = (OrclassUtilisateurs) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(GlobalFonctions.SESSION_USER);
//        listeType_Caracteristique = (List<OrclassType_Caracteristique>) type_CaracteristiqueDao.findAll();
//        listeUnite_Caracteristique = (List<OrclassUnite_Caracteristique>) unite_CaracteristiqueDao.findAll();
//        creatRubriqueByExcell2();
//        listeCaracteristique = caracteristiquesDao.listCaracteristiqueByCompagnie(entreprise);
//        listeCaracteristique.addAll(caracteristiquesDao.listeCaracteristiqueForAllCompagnies());
//        try {
//            this.updateTableRubrique();
//        } catch (Exception e) {
//        }
//
//    }
//
//    public void updateTableRubrique() {
//        UIComponent table = FacesContext.getCurrentInstance().getViewRoot().findComponent(":form1:idcaracteristiqueTable");
//        table.setValueExpression("sortBy", null);
//
//        PrimeFaces.current().executeScript("PF('caracteristiqueTable').clearFilters();");
//
//    }
//
//    public void chargeListeElementCaracterisqtique(OrclassCaracteristiques item) {
//        if (item != null && item.getId() != null) {
//            this.setCaracteristiques(item);
//            if (Objects.equals(item.getShowAllCompagnies(), Boolean.TRUE)) {
//                listeElement_Liste_Caracteristique = element_Liste_CaracteristiqueDao.listCaracteristiqueByCompagnieHaveListeElment(caracteristiques, entreprise);
//
//            } else {
//                listeElement_Liste_Caracteristique = element_Liste_CaracteristiqueDao.listCaracteristiqueByCompagnieHaveListeElment(caracteristiques, caracteristiques.getIdEntreprise());
//
//            }
//            element_Liste_Caracteristique = new OrclassElement_Liste_Caracteristique();
//            element_Liste_Caracteristique.setIdCaracteristiques(caracteristiques);
//            element_Liste_Caracteristique.setIdEntreprise(entreprise);
//            listeElement_Liste_Caracteristique.add(element_Liste_Caracteristique);
//            this.reverseListeElement();
//            this.updateTableElementList();
//            PrimeFaces.current().executeScript("PF('listDialog').show()");
//
//        }
//    }
//
//    public void addElemntList() {
//        if (element_Liste_Caracteristique != null && element_Liste_Caracteristique.getLibelle() != null && !"".equals(element_Liste_Caracteristique.getLibelle()) && element_Liste_CaracteristiqueDao.finKey(caracteristiques, entreprise, element_Liste_Caracteristique.getLibelle()) == null) {
//            if (element_Liste_CaracteristiqueDao.finKey(element_Liste_Caracteristique.getIdCaracteristiques(), entreprise, element_Liste_Caracteristique.getLibelle()) == null) {
//                element_Liste_CaracteristiqueDao.create(element_Liste_Caracteristique);
//                listeElement_Liste_Caracteristique = element_Liste_CaracteristiqueDao.listCaracteristiqueByCompagnieHaveListeElment(element_Liste_Caracteristique.getIdCaracteristiques(), entreprise);
//                element_Liste_Caracteristique = new OrclassElement_Liste_Caracteristique();
//                element_Liste_Caracteristique.setIdCaracteristiques(caracteristiques);
//                element_Liste_Caracteristique.setIdEntreprise(entreprise);
//                listeElement_Liste_Caracteristique.add(element_Liste_Caracteristique);
//                this.reverseListeElement();
//                this.updateTableElementList();
//                PrimeFaces.current().executeScript("PF('listDialog').show()");
//            }
//        } else {
//            PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage(exception.Error.DUPLICATE_ERROR_INSERT.name(), "PLEASE CHANGE VALUE"));
////             PrimeFaces.current().executeScript("PF('listDialog').show()");
//        }
//    }
//
//    public void updateElementList() {
//        if (element_Liste_Caracteristique != null && element_Liste_Caracteristique.getId() != null) {
//            element_Liste_CaracteristiqueDao.update(element_Liste_Caracteristique);
//            listeElement_Liste_Caracteristique = element_Liste_CaracteristiqueDao.listCaracteristiqueByCompagnieHaveListeElment(element_Liste_Caracteristique.getIdCaracteristiques(), entreprise);
//            element_Liste_Caracteristique = new OrclassElement_Liste_Caracteristique();
//            element_Liste_Caracteristique.setIdCaracteristiques(caracteristiques);
//            element_Liste_Caracteristique.setIdEntreprise(entreprise);
//            listeElement_Liste_Caracteristique.add(element_Liste_Caracteristique);
//            this.reverseListeElement();
//            this.updateTableElementList();
//            PrimeFaces.current().executeScript("PF('listDialog').show()");
//        }
//    }
//
//    public void deleteElementListe() {
//        if (element_Liste_Caracteristique != null && element_Liste_Caracteristique.getId() != null) {
//            element_Liste_CaracteristiqueDao.delete(element_Liste_Caracteristique);
//            listeElement_Liste_Caracteristique = element_Liste_CaracteristiqueDao.listCaracteristiqueByCompagnieHaveListeElment(element_Liste_Caracteristique.getIdCaracteristiques(), entreprise);
//            element_Liste_Caracteristique = new OrclassElement_Liste_Caracteristique();
//            element_Liste_Caracteristique.setIdCaracteristiques(caracteristiques);
//            element_Liste_Caracteristique.setIdEntreprise(entreprise);
//            listeElement_Liste_Caracteristique.add(element_Liste_Caracteristique);
//            this.reverseListeElement();
//            this.updateTableElementList();
//            PrimeFaces.current().executeScript("PF('listDialog').show()");
//        }
//    }
//
//    public Boolean afficherListe(OrclassCaracteristiques item) {
//        try {
//            if (item != null && item.getId() != null && item.getType_Caracteristique().getLibelle().equals("Liste")) {
//
//                return Boolean.TRUE;
//            }
//        } catch (Exception e) {
//            return Boolean.FALSE;
//        }
//        return Boolean.FALSE;
//
//    }
//
//    public void shwoDetailElementList(OrclassElement_Liste_Caracteristique item) {
//        this.setElement_Liste_Caracteristique(item);
//    }
//
//    public void updateTableElementList() {
//        UIComponent table = FacesContext.getCurrentInstance().getViewRoot().findComponent("form12:idEltTable");
//        table.setValueExpression("sortBy", null);
//
//        PrimeFaces.current().executeScript("PF('eltTable').clearFilters();");
////         PrimeFaces.current().ajax().update("form1_1");
//
//    }
//
//    public void reverseListeElement() {
//
//        List<OrclassElement_Liste_Caracteristique> result = new ArrayList<>();
//        for (int i = listeElement_Liste_Caracteristique.size() - 1; i >= 0; i--) {
//            result.add(listeElement_Liste_Caracteristique.get(i));
//        }
//
//        this.setListeElement_Liste_Caracteristique(result);
////        this.updateTableRubriqueCategorie();
//    }
//
//    public void chargeFileExcell() {
//
//    }
//
//    public void creatRubriqueByExcell() {
//        FacesContext ctx = FacesContext.getCurrentInstance();
//        ExternalContext extContext = ctx.getExternalContext();
//        List<OrclassCaracteristiques> listCaracteristiques = new ArrayList<>();
//        OrclassCaracteristiques ca;
//        OrclassEntreprises en = null;
//        try {
//            String path = extContext.getRealPath("") + this.pahtRubrique;
//            File file = new File(path);
//            InputStream targetStream = new FileInputStream(file);
//            listCaracteristiques = serviceExcell.recuperListeCaracterisqueByExcell(targetStream, path);
//            if (listCaracteristiques == null) {
//
//                System.err.println("Une erruer est survenue dans l extraction des donnes sur le fichier Excell");
//
//            }
//
//            for (OrclassCaracteristiques car : listCaracteristiques) {
//                ca = caracteristiquesDao.findEntityHavingValue("code", car.getCode());
//                if (ca == null) {
//                    ca = new OrclassCaracteristiques(car.getCode(), car.getLibelle());
//                    ca.setDateCreation(new Date());
////                    ca.setIdEntreprise(entreprise);
//                    ca.setShowAllCompagnies(Boolean.TRUE);
//                    ca.setType_Caracteristique(car.getType_Caracteristique());
//                    ca.setUnite_Caracteristique(car.getUnite_Caracteristique());
//                    caracteristiquesDao.create(ca);
//                }
//
//            }
//        } catch (Exception e) {
//            System.err.println("Une erruer est survenue dans l insertion des donneés");
//
//        }
//    }
//
//    public void creatRubriqueByExcell2() {
//        FacesContext ctx = FacesContext.getCurrentInstance();
//        ExternalContext extContext = ctx.getExternalContext();
//        List<OrclassCaracteristiques> listCaracteristiques = new ArrayList<>();
//        OrclassCaracteristiques ca;
//        OrclassEntreprises en = null;
//        try {
//            String path = extContext.getRealPath("") + this.pahtRubrique2;
//            File file = new File(path);
//            InputStream targetStream = new FileInputStream(file);
//            listCaracteristiques = serviceExcell.recuperListeCaracterisqueByExcell(targetStream, path);
//            if (listCaracteristiques == null) {
//
//                System.err.println("Une erruer est survenue dans l extraction des donnes sur le fichier Excell");
//
//            }
//
//            for (OrclassCaracteristiques car : listCaracteristiques) {
//                ca = caracteristiquesDao.findEntityHavingValue("code", car.getCode());
//                if (ca == null) {
//                    ca = new OrclassCaracteristiques(car.getCode(), car.getLibelle());
//                    ca.setDateCreation(new Date());
////                    ca.setIdEntreprise(entreprise);
//                    ca.setShowAllCompagnies(Boolean.TRUE);
//                    ca.setType_Caracteristique(car.getType_Caracteristique());
//                    ca.setUnite_Caracteristique(car.getUnite_Caracteristique());
//                    caracteristiquesDao.create(ca);
//                }
//
//            }
//        } catch (Exception e) {
//            System.err.println("Une erruer est survenue dans l insertion des donneés");
//
//        }
//    }
//
//    public void reset() {
//
//        this.init();
//
//        PrimeFaces.current().ajax().update("form1");
//        this.updateTableRubrique();
//
//    }
//
//    public void init() {
//        caracteristiques = new OrclassCaracteristiques();
//    }
//
//    public String deleteCaracteristique() {
//        FacesContext ctx = FacesContext.getCurrentInstance();
//        //get default locale
//        Locale myLoc = ctx.getViewRoot().getLocale();
//        String entete[] = {LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "caracteristique", null, myLoc)};
//
//        // on recupere tous les modules qui lui sont attribuer puis on inserre
//        String[] detail = {entete[0], "Module(s)"};
//        try {
//            if (caracteristiques != null && caracteristiques.getId() != null) {
//
//                caracteristiquesDao.delete(caracteristiques);
//                summary = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "summary.delete.succes", entete, myLoc);
//                msgdetail = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "detail.delete.succes", detail, myLoc);
//                ctx.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_INFO, summary, msgdetail));
//
//            } else {
//                ctx.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR PLEASE TRY AGAINST", ""));
//            }
//        } catch (ConstraintViolationException ejb) {
//            GlobalFonctions.displayMessages("msg", ctx, FacesMessage.SEVERITY_WARN, ejb.getCause().getClass().getSimpleName(), exception.Error.DUPLICATE_ERROR_UPDATE + "", null);
//            throw ejb;
//        } catch (Exception th) {
//            GlobalFonctions.displayMessages(null, ctx, FacesMessage.SEVERITY_FATAL, "caracteristique", exception.Error.FATAL_ERROR + "", new Object[]{"caracteristique"});
//            logger.error("Erreur Survenu", th);
//        }
//        listeCaracteristique.remove(caracteristiques);
//        this.reset();
//        return null;
//    }
//
//    public String addCaracterisque() {
//
//        FacesContext ctx = FacesContext.getCurrentInstance();
//        //get default locale
//        Locale myLoc = ctx.getViewRoot().getLocale();
//        String entete[] = {LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "caracteristique", null, myLoc)};
//
//        // on recupere tous les modules qui lui sont attribuer puis on inserre
//        String[] detail = {entete[0], "Module(s)"};
//        if ("".equals(caracteristiques.getCode()) || caracteristiques.getCode()==null) {
//               ctx.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR PLEASE TRY AGAINST", "VALUE CODE IS NULL"));
//            return null;
//        }
//       if ("".equals(caracteristiques.getLibelle()) || caracteristiques.getLibelle()==null) {
//               ctx.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR PLEASE TRY AGAINST", "VALUE  LIBELLE IS NULL"));
//            return null;
//        }
//        try {
//            if (caracteristiquesDao.findEntityHavingValue("code", caracteristiques.getCode()) == null && caracteristiques.getCode() != null && caracteristiques.getType_Caracteristique() != null) {
//                caracteristiques.setDateCreation(new Date());
//                caracteristiques.setIdEntreprise(entreprise);
//                caracteristiques.setShowAllCompagnies(Boolean.FALSE);
//                caracteristiquesDao.create(caracteristiques);
//
//                summary = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "summary.ajout.succes", entete, myLoc);
//                msgdetail = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "detail.ajout.succes", detail, myLoc);
//                ctx.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_INFO, summary, msgdetail));
//
//            } else {
//                ctx.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR PLEASE TRY AGAINST", "VALUE IS  EXISTS"));
//                return null;
//            }
//        } catch (GlobalException e) {
//
//            ctx.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getCode(), e.getCode()));
////throw e;
//        } catch (ConstraintViolationException ejb) {
//            GlobalFonctions.displayMessages("msg", ctx, FacesMessage.SEVERITY_WARN, ejb.getCause().getClass().getSimpleName(), exception.Error.DUPLICATE_ERROR_INSERT + "", null);
//            throw ejb;
//        } catch (Exception th) {
//            GlobalFonctions.displayMessages("msg", ctx, FacesMessage.SEVERITY_FATAL, "caracteristique", exception.Error.FATAL_ERROR + "", new Object[]{"caracteristique"});
//            logger.error("Erreur Survenu", th);
//        }
//        listeCaracteristique.add(caracteristiquesDao.findEntityHavingValue("code", caracteristiques.getCode()));
//        this.reset();
//        this.chargeDialog();
//        return null;
//
//    }
//
//    public String valueObjectByLibelleAutres(OrclassBranches cl) {
//        FacesContext ctx = FacesContext.getCurrentInstance();
//        Locale myLoc = ctx.getViewRoot().getLocale();
//        if (cl != null && cl.getLibelle().equals(LibelleBranche.autres)) {
//            return cl.getLibelleAutre();
//        }
//        if (cl != null) {
//            return LocaleHelper.getLocaleString(RecupBundle.FichierBundle, cl.getLibelle().toString(), null, myLoc);
//        }
//        return "";
//    }
//
//    public void chargeObjetByBranche() {
//        if (branches != null && branches.getIdBranche() != null && Objects.equals(objetfForclass, Boolean.TRUE)) {
//            listeObjet = (List<Orclass_Objet>) objetDao.findAllEntitiesHavingValue("idBranche", branches);
//            PrimeFaces.current().executeScript("PF('manageCaracteristiqueDialog').show()");
//        }
//    }
//
//    public String updateCaracterisque() {
//        String success = null;
//        FacesContext ctx = FacesContext.getCurrentInstance();
//
//        //get default locale
//        Locale myLoc = ctx.getViewRoot().getLocale();
//        //Locale myLoc =new Locale("fr");
//        String entete[] = {LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "caracteristique", null, myLoc)};
//        String[] detail = {entete[0], "Parametres"};
//
//        try {
//
//            if (caracteristiques != null && caracteristiques.getId() != null) {
//                listeCaracteristique.remove(caracteristiques);
//                caracteristiques.setDateModification(new Date());
//                caracteristiquesDao.update(caracteristiques);
//
//                summary = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "" + exception.Success.OPERATION_SUCCESS.toString(), entete, myLoc);
//
//                ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, summary, null));
//
//            } else {
//                //ecrire dans le fichier de log  
//                summary = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "summary.update.error", entete, myLoc);
//                msgdetail = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "detail.error", null, myLoc);
//                ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, summary, msgdetail));
//
//            }
//
//        } catch (Throwable th) {
//
//            //ecrire dans le fichier de log  
//            summary = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "summary.update.error", entete, myLoc);
//            msgdetail = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "detail.error", null, myLoc);
//            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, summary, msgdetail + th.getMessage() + " " + th.getCause() + "-" + th.getClass()));
//
//        }
//        listeCaracteristique.add(caracteristiques);
//
//        reset();
//        return null;
//
//    }
//
//    public OrclassCaracteristiques getCaracteristiques() {
//        return caracteristiques;
//    }
//
//    public void setCaracteristiques(OrclassCaracteristiques caracteristiques) {
//        this.caracteristiques = caracteristiques;
//    }
//
//    public List<OrclassCaracteristiques> getListeCaracteristique() {
//        return listeCaracteristique;
//    }
//
//    public void setListeCaracteristique(List<OrclassCaracteristiques> listeCaracteristique) {
//        this.listeCaracteristique = listeCaracteristique;
//    }
//
//    public List<OrclassCaracteristiques> getFilterCaracteristique() {
//        return filterCaracteristique;
//    }
//
//    public void setFilterCaracteristique(List<OrclassCaracteristiques> filterCaracteristique) {
//        this.filterCaracteristique = filterCaracteristique;
//    }
//
//    public List<OrclassUnite_Caracteristique> getListeUnite_Caracteristique() {
//        return listeUnite_Caracteristique;
//    }
//
//    public void setListeUnite_Caracteristique(List<OrclassUnite_Caracteristique> listeUnite_Caracteristique) {
//        this.listeUnite_Caracteristique = listeUnite_Caracteristique;
//    }
//
//    public List<OrclassType_Caracteristique> getListeType_Caracteristique() {
//        return listeType_Caracteristique;
//    }
//
//    public void setListeType_Caracteristique(List<OrclassType_Caracteristique> listeType_Caracteristique) {
//        this.listeType_Caracteristique = listeType_Caracteristique;
//    }
//
//    public void showDetails(OrclassCaracteristiques item) {
//        if (caracteristiques == null && caracteristiques.getId() == null) {
//            this.setCaracteristiques(item);
//        }
//
//        PrimeFaces.current().ajax().update("form11");
//
//    }
//
//    public void chargeDialog() {
//        PrimeFaces.current().executeScript("PF('manageCaracteristiqueDialog').show();");
//    }
//
//    public Boolean accessCree() {
//        OrclassActions action = null;
//        OrclassFonctionnalites fon = null;
//        OrclassMenusAcces ma = null;
//        if (menu != null && menu.getIdMenu() != null && module != null && module.getIdModule() != null) {
//            fon = serviceAccess.getFonctionnaliteByCode(FonctionnaliteModuleParametrage.caracteristique_assure.name());
//            action = serviceAccess.getActionByCodeByFonctionnalit(Actions.creer.name(), fon);
//            if (action == null) {
//                return Boolean.FALSE;
//            }
//            return serviceAccess.accessAction(user, action, menu);
//        }
//        return Boolean.FALSE;
//    }
//
//    public Boolean accessModifier() {
//        OrclassActions action = null;
//        OrclassFonctionnalites fon = null;
//        if (menu != null && menu.getIdMenu() != null && module != null && module.getIdModule() != null) {
//            fon = serviceAccess.getFonctionnaliteByCode(FonctionnaliteModuleParametrage.caracteristique_assure.name());
//            action = serviceAccess.getActionByCodeByFonctionnalit(Actions.modifier.name(), fon);
//            if (action == null) {
//                return Boolean.FALSE;
//            }
//            return serviceAccess.accessAction(user, action, menu);
//        }
//        return Boolean.FALSE;
//    }
//
//    public Boolean accessSupprimer() {
//        OrclassActions action = null;
//        OrclassFonctionnalites fon = null;
//        if (menu != null && menu.getIdMenu() != null && module != null && module.getIdModule() != null) {
//            fon = serviceAccess.getFonctionnaliteByCode(FonctionnaliteModuleParametrage.caracteristique_assure.name());
//            action = serviceAccess.getActionByCodeByFonctionnalit(Actions.supprimer.name(), fon);
//            if (action == null) {
//                return Boolean.FALSE;
//            }
//            return serviceAccess.accessAction(user, action, menu);
//        }
//        return Boolean.FALSE;
//    }
//
//    public List<OrclassElement_Liste_Caracteristique> getListeElement_Liste_Caracteristique() {
//        return listeElement_Liste_Caracteristique;
//    }
//
//    public void setListeElement_Liste_Caracteristique(List<OrclassElement_Liste_Caracteristique> listeElement_Liste_Caracteristique) {
//        this.listeElement_Liste_Caracteristique = listeElement_Liste_Caracteristique;
//    }
//
//    public OrclassElement_Liste_Caracteristique getElement_Liste_Caracteristique() {
//        return element_Liste_Caracteristique;
//    }
//
//    public void setElement_Liste_Caracteristique(OrclassElement_Liste_Caracteristique element_Liste_Caracteristique) {
//        this.element_Liste_Caracteristique = element_Liste_Caracteristique;
//    }
//
//    public List<Orclass_Objet> getListeObjet() {
//        return listeObjet;
//    }
//
//    public void setListeObjet(List<Orclass_Objet> listeObjet) {
//        this.listeObjet = listeObjet;
//    }
//
//    public List<OrclassBranches> getLisBranche() {
//        return lisBranche;
//    }
//
//    public void setLisBranche(List<OrclassBranches> lisBranche) {
//        this.lisBranche = lisBranche;
//    }
//
//    public Boolean getObjetfForclass() {
//        return objetfForclass;
//    }
//
//    public void setObjetfForclass(Boolean objetfForclass) {
//        this.objetfForclass = objetfForclass;
//    }
//
//   
//    public OrclassBranches getBranches() {
//        return branches;
//    }
//
//    public void setBranches(OrclassBranches branches) {
//        this.branches = branches;
//    }
//
//}
