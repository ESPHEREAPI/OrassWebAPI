///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package controllers.parametrage;
//
//import static Entreprise.EntrepriseServices.logger;
//import dao.OrclassBranchesDao;
//import dao.OrclassCaracteristiquesDao;
//import dao.OrclassCategoriesDao;
//import dao.OrclassMenusDao;
//import dao.OrclassModulesDao;
//import dao.Orclass_AccessAvenat_CaracteristiqueDao;
//import dao.Orclass_Access_AvenantDao;
//import dao.Orclass_ObjetDao;
//import dao.Orclass_TypeAvenantDao;
//import droitAcces.IDroitAcces;
//import enums.LibelleBranche;
//import exception.GlobalException;
//import exception.Success;
//import java.io.IOException;
//import java.io.Serializable;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//import java.util.Locale;
//import java.util.Objects;
//import javax.annotation.PostConstruct;
//import javax.ejb.EJB;
//import javax.inject.Named;
//import javax.faces.application.FacesMessage;
//import javax.faces.component.UIComponent;
//import javax.faces.context.ExternalContext;
//import javax.faces.context.FacesContext;
//import javax.faces.view.ViewScoped;
//import modele.OrclassBranches;
//import modele.OrclassCaracteristiques;
//import modele.OrclassCategories;
//import modele.OrclassEntreprises;
//import modele.OrclassMenus;
//import modele.OrclassModules;
//import modele.OrclassUtilisateurs;
//import modele.Orclass_AccessAvenat_Caracteristique;
//import modele.Orclass_Access_Avenant;
//import modele.Orclass_Objet;
//import modele.Orclass_TypeAvenant;
//import org.hibernate.exception.ConstraintViolationException;
//import org.primefaces.PrimeFaces;
//import org.primefaces.event.TabChangeEvent;
//import org.primefaces.event.UnselectEvent;
//import utils.GlobalFonctions;
//
///**
// *
// * @author frankjiatou
// */
//@Named(value = "accessDroitAvenatController")
//@ViewScoped
//public class AccessDroitAvenatController implements Serializable {
//
//    /**
//     * Creates a new instance of AccessDroitAvenatController
//     */
//    @EJB
//    OrclassBranchesDao branchesDao;
//    OrclassBranches branches;
//    @EJB
//    OrclassCategoriesDao categoriesDao;
//    OrclassCategories categories;
//
//    @EJB
//    OrclassMenusDao menusDao;
//    @EJB
//    OrclassModulesDao modulesDao;
//    @EJB
//    IDroitAcces serviceAccess;
//    @EJB
//    Orclass_ObjetDao objetDao;
//    Orclass_Objet orclass_Objet;
//    @EJB
//    OrclassCaracteristiquesDao caracteristiquesDao;
//    OrclassCaracteristiques caracteristiques;
//    @EJB
//    Orclass_TypeAvenantDao typeAvenantDao;
//    Orclass_TypeAvenant typeAvenant;
//    @EJB
//    Orclass_AccessAvenat_CaracteristiqueDao accessAvenat_CaracteristiqueDao;
//    Orclass_AccessAvenat_Caracteristique accessAvenat_Caracteristique;
//    @EJB
//    Orclass_Access_AvenantDao access_AvenantDao;
//    Orclass_Access_Avenant access_Avenant;
//
//    private List<OrclassBranches> listeBranches = new ArrayList<>();
//    private List<OrclassCategories> listeCategories = new ArrayList<>();
//    private List<Orclass_Objet> listeObjet = new ArrayList<>();
//    private List<OrclassCaracteristiques> listeCaracteristiques = new ArrayList<>();
//    private List<OrclassCaracteristiques> listeSelectCaracteristiques = new ArrayList<>();
//    private List<Orclass_TypeAvenant> listeTypeAvenant = new ArrayList<>();
//    private List< Orclass_AccessAvenat_Caracteristique> listeAccessAvenat_Caracteristique = new ArrayList<>();
//    private List< Orclass_AccessAvenat_Caracteristique> listeElement_Liste_Caracteristique = new ArrayList<>();
//
//    private List<Orclass_AccessAvenat_Caracteristique> filterAccessAvenat_Caracteristique = new ArrayList<>();
//    private List< Orclass_Access_Avenant> listeAccess_Avenant = new ArrayList<>();
//    private List<Orclass_Access_Avenant> filterAccessAvenat = new ArrayList<>();
//    private List<OrclassCaracteristiques> listeCaracteristiqueObjetNotHaveAccess = new ArrayList<>();
//    private List<Orclass_AccessAvenat_Caracteristique> listeCaracteristiqueHaveAccess = new ArrayList<>();
//
//    String summary = "";
//    String msgdetail = "";
//    private OrclassEntreprises entreprise;
//    private OrclassMenus menu;
//    private OrclassModules module;
//    OrclassUtilisateurs user;
//
//    public AccessDroitAvenatController() {
//
//        branches = new OrclassBranches();
//        categories = new OrclassCategories();
//        accessAvenat_Caracteristique = new Orclass_AccessAvenat_Caracteristique();
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
////        menu = menusDao.findEntityHavingValue("code", "intermediaire.prime");
////        module = modulesDao.findEntityHavingValue("code", "parametrage");
//
//        user = (OrclassUtilisateurs) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(GlobalFonctions.SESSION_USER);
//
//        branches = branchesDao.findBrancheByLibelleBranche(LibelleBranche.sante);
//        this.chargeBranche();
//        this.chargerCategoriesByBranche();
//        listeTypeAvenant = typeAvenantDao.listeTypeAvenantByCompagnie(entreprise);
//        listeAccess_Avenant = access_AvenantDao.listeAccessAvenantByCompagnies(entreprise, branches);
//        this.updateDataTableAccessAvenant();
//
//    }
//
//    public void chargeBranche() {
//        listeBranches = branchesDao.listAllBrancheShowAllCompagnie();
//        listeBranches.addAll(branchesDao.listAllBrancheByCompagnie(entreprise));
//    }
//
//    public void chargerCategoriesByBranche() {
//        if (branches != null && branches.getIdBranche() != null && Objects.equals(branches.getShowAllCompagnies(), Boolean.TRUE)) {
//            listeCategories = categoriesDao.listeCategorieByBrancheCreateBySysteme(branches);
//        } else if (branches != null && branches.getIdBranche() != null && Objects.equals(branches.getShowAllCompagnies(), Boolean.FALSE)) {
//            listeCategories = categoriesDao.listeCategorieByBranche(branches, entreprise);
//        }
//    }
//
//    public void chargeObjetByBrancheAndTypeAvenat(Orclass_AccessAvenat_Caracteristique item) {
//        List<Orclass_Objet> listeObjetAll = new ArrayList<>();
//        if (branches != null && branches.getIdBranche() != null && item != null) {
//            listeObjetAll = (List<Orclass_Objet>) objetDao.findAllEntitiesHavingValue("idBranche", branches);
//            if (item.getIdAccess_Avenant().getIdTypeAvenant() != null && item.getIdAccess_Avenant().getIdTypeAvenant().getId() != null) {
//                listeObjet = access_AvenantDao.listeAccessAvenantByCompagniesAndTypeAvenant(entreprise, branches, item.getIdAccess_Avenant().getIdTypeAvenant());
//                listeObjetAll.removeAll(listeObjet);
//                this.setListeObjet(listeObjetAll);
////                        objetDao.listObjetNotHaveAccessCompagnie(entreprise, item.getIdAccess_Avenant().getIdTypeAvenant(), branches);
//
//            }
//
//        }
//    }
//
//    public void chargeCaracteristiqueByObjet(Orclass_Objet objet) {
//        if (objet != null && objet.getId() != null) {
//            listeCaracteristiques = caracteristiquesDao.listCaracteristiqueHaveByCompagnieByObjet(entreprise, objet);
//            listeSelectCaracteristiques = accessAvenat_CaracteristiqueDao.listCaracteristiqueByObjet(entreprise, objet);
//        }
//
//    }
//
//    public void chargeCaracteristiqueByObjetAndbranche(Orclass_AccessAvenat_Caracteristique item) {
//        if (branches != null && branches.getIdBranche() != null && item != null) {
//            if (Objects.equals(item.getIdAccess_Avenant().getModifier(), Boolean.TRUE)) {
//                listeCaracteristiques = caracteristiquesDao.listCaracteristiqueHaveByCompagnieByObjet(entreprise, item.getIdAccess_Avenant().getIdObjet());
//                listeSelectCaracteristiques = accessAvenat_CaracteristiqueDao.listCaracteristiqueByObjet(entreprise, item.getIdAccess_Avenant().getIdObjet(), item.getIdAccess_Avenant());
//                item.setListeCaracteristiqueSelect(listeSelectCaracteristiques);
//            }
//        }
//
//    }
//
//    public void onItemUnselect(UnselectEvent event) {
//        FacesContext context = FacesContext.getCurrentInstance();
//
//        FacesMessage msg = new FacesMessage();
//        msg.setSummary("Item unselected: " + event.getObject().toString());
//        msg.setSeverity(FacesMessage.SEVERITY_INFO);
//
//        context.addMessage(null, msg);
//    }
//
//    public void onTabChange(TabChangeEvent event) {
//        FacesContext ctx = FacesContext.getCurrentInstance();
//        Locale myLoc = ctx.getViewRoot().getLocale();
//        if (event.getTab().getId().equals("consultation")) {
////            if (listeAccess_Avenant.isEmpty()) {
//            listeAccess_Avenant = access_AvenantDao.listeAccessAvenantByCompagnies(entreprise, branches);
////            }
//            PrimeFaces.current().ajax().update(":form1:tabprincipal:idaccessAvenantTable");
//            this.updateDataTableAccessAvenant();
//            this.updateTableElementList();
//        }
//        if (event.getTab().getId().equals("edition")) {
//            if (listeAccessAvenat_Caracteristique.isEmpty()) {
//                listeAccessAvenat_Caracteristique.clear();
//                accessAvenat_Caracteristique = new Orclass_AccessAvenat_Caracteristique();
//                listeAccessAvenat_Caracteristique.add(accessAvenat_Caracteristique);
//                this.updateTableAccessAvenant();
//            }
//
//        }
//
//    }
//
//    public void chargeCaracteristiqueObjetByAccessAvenant(Orclass_Access_Avenant item) {
//        if (item != null && item.getId() != null) {
//            this.setAccess_Avenant(item);
//            listeCaracteristiqueObjetNotHaveAccess = caracteristiquesDao.listCaracteristiqueHaveByCompagnieByObjet(entreprise, item.getIdObjet());
//
//            listeElement_Liste_Caracteristique = accessAvenat_CaracteristiqueDao.listCaracteristiqueByAccessAvenant(item, entreprise);
//            for (Orclass_AccessAvenat_Caracteristique a : listeElement_Liste_Caracteristique) {
//                if (listeCaracteristiqueObjetNotHaveAccess.contains(a.getIdCaracteristiques()) == Boolean.TRUE) {
//                    listeCaracteristiqueObjetNotHaveAccess.remove(a.getIdCaracteristiques());
//                }
//            }
////            listeCaracteristiqueObjetNotHaveAccess.removeAll(listeElement_Liste_Caracteristique);
//
////            accessAvenat_Caracteristique = new Orclass_AccessAvenat_Caracteristique();
////            accessAvenat_Caracteristique.setIdAccess_Avenant(item);
////            accessAvenat_Caracteristique.setIdEntreprise(entreprise);
////            listeAccessAvenat_Caracteristique.add(accessAvenat_Caracteristique);
//            this.reverseListeElement();
//            this.updateTableElementList();
//            this.updateDataTableAccessAvenant();
//            PrimeFaces.current().executeScript("PF('listDialog').show()");
//
//        }
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
//    public void updateTableAccessAvenant() {
//        UIComponent table = FacesContext.getCurrentInstance().getViewRoot().findComponent("form1:tabprincipal:idaccessTable");
//        table.setValueExpression("sortBy", null);
//
//        PrimeFaces.current().executeScript("PF('accessTable').clearFilters();");
////         PrimeFaces.current().ajax().update("form1_1");
//
//    }
//
//    public void shwoDetailElementList(Orclass_AccessAvenat_Caracteristique item) {
//        this.setAccessAvenat_Caracteristique(item);
//    }
//
//    public void reverseListeElement() {
//
//        List<Orclass_AccessAvenat_Caracteristique> result = new ArrayList<>();
//        for (int i = listeAccessAvenat_Caracteristique.size() - 1; i >= 0; i--) {
//            result.add(listeAccessAvenat_Caracteristique.get(i));
//        }
//
//        this.setListeAccessAvenat_Caracteristique(result);
////        this.updateTableRubriqueCategorie();
//    }
//
//    public void addElemntList() {
//
//        if (caracteristiques != null && caracteristiques.getId() != null && access_Avenant != null && access_Avenant.getId() != null) {
//            if (accessAvenat_CaracteristiqueDao.finKey(entreprise, access_Avenant, caracteristiques) == null) {
//                accessAvenat_Caracteristique = new Orclass_AccessAvenat_Caracteristique();
//                accessAvenat_Caracteristique.setIdCaracteristiques(caracteristiques);
//                accessAvenat_Caracteristique.setIdAccess_Avenant(access_Avenant);
//                accessAvenat_Caracteristique.setIdEntreprise(entreprise);
//
//                accessAvenat_CaracteristiqueDao.create(accessAvenat_Caracteristique);
////                PrimeFaces.current().executeScript("PF('listDialog').hide()");
//                this.chargeCaracteristiqueObjetByAccessAvenant(access_Avenant);
//                PrimeFaces.current().ajax().update(":form12");
//                PrimeFaces.current().executeScript("PF('listDialog').show()");
//
////                listeAccessAvenat_Caracteristique = accessAvenat_CaracteristiqueDao.listCaracteristiqueByAccessAvenant(access_Avenant, entreprise);
//////                this.setAccess_Avenant(accessAvenat_Caracteristique.getIdAccess_Avenant());
////                accessAvenat_Caracteristique = new Orclass_AccessAvenat_Caracteristique();
////                accessAvenat_Caracteristique.setIdAccess_Avenant(this.access_Avenant);
////                accessAvenat_Caracteristique.setIdEntreprise(entreprise);
////                listeAccessAvenat_Caracteristique.add(accessAvenat_Caracteristique);
////                access_Avenant = new Orclass_Access_Avenant();
////                this.reverseListeElement();
////                this.updateTableElementList();
////                PrimeFaces.current().executeScript("PF('listDialog').show()");
//            }
//        } else {
//            PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage(exception.Error.DUPLICATE_ERROR_INSERT.name(), "PLEASE CHANGE VALUE"));
////             PrimeFaces.current().executeScript("PF('listDialog').show()");
//        }
//    }
//
//    public void deleteElementListe() {
//        if (accessAvenat_Caracteristique != null && accessAvenat_Caracteristique.getId() != null) {
//            accessAvenat_CaracteristiqueDao.delete(accessAvenat_Caracteristique);
////            listeAccessAvenat_Caracteristique = accessAvenat_CaracteristiqueDao.listCaracteristiqueByAccessAvenant(accessAvenat_Caracteristique.getIdAccess_Avenant(), entreprise);
////            this.setAccess_Avenant(accessAvenat_Caracteristique.getIdAccess_Avenant());
////            accessAvenat_Caracteristique = new Orclass_AccessAvenat_Caracteristique();
////            accessAvenat_Caracteristique.setIdAccess_Avenant(this.access_Avenant);
////            accessAvenat_Caracteristique.setIdEntreprise(entreprise);
////            listeAccessAvenat_Caracteristique.add(accessAvenat_Caracteristique);
////            access_Avenant = new Orclass_Access_Avenant();
////            this.reverseListeElement();
////            this.updateTableElementList();
//            this.chargeCaracteristiqueObjetByAccessAvenant(access_Avenant);
//            PrimeFaces.current().ajax().update(":form12");
//            PrimeFaces.current().executeScript("PF('listDialog').show()");
////            PrimeFaces.current().executeScript("PF('listDialog').show()");
//        }
//    }
//
//    public void updateDataTableAccessAvenant() {
//        UIComponent table = FacesContext.getCurrentInstance().getViewRoot().findComponent(":form1:tabprincipal:idaccessAvenantTable");
//        table.setValueExpression("sortBy", null);
//
//        PrimeFaces.current().executeScript("PF('accessAvenantTable').clearFilters();");
//
//    }
//
//    public void updateAccessAvenant() throws IOException {
//        // etudiant.setMatricule(GenerationController.genererMatricule());
//        FacesContext ctx = FacesContext.getCurrentInstance();
//        ExternalContext extContext = ctx.getExternalContext();
//        Locale myLoc = ctx.getViewRoot().getLocale();
//        Boolean valider = Boolean.FALSE;
//        try {
//
//            //traitement de la photo
//            if ((access_Avenant != null && access_Avenant.getId() != null)) {
//                access_Avenant.setDateModification(new Date());
//
//                access_AvenantDao.update(access_Avenant);
//                GlobalFonctions.displayMessages(null, ctx, FacesMessage.SEVERITY_INFO, "access.droit.typeAvenant", Success.UPDATE_SUCCESS + "", new Object[]{access_Avenant});
//
//            } else {
////                GlobalFonctions.displayMessages(null, ctx, FacesMessage.SEVERITY_WARN, "police", exception.Error.DUPLICATE_ERROR_INSERT + "" + numero_police, null);
//                GlobalFonctions.displayMessages(null, ctx, FacesMessage.SEVERITY_WARN, "access.droit.typeAvenant", exception.Error.UPDATE_ERROR + "", new Object[]{access_Avenant});
//                return;
//            }
//
//        } catch (GlobalException e) {
//            GlobalFonctions.displayMessages(null, ctx, FacesMessage.SEVERITY_ERROR, access_Avenant.toString(), e.getCode(), e.getParam());
//            //throw e;
//        } catch (ConstraintViolationException ejb) {
//            GlobalFonctions.displayMessages(null, ctx, FacesMessage.SEVERITY_WARN, ejb.getCause().getClass().getSimpleName(), exception.Error.FATAL_ERROR + "", null);
//            throw ejb;
//        } catch (Throwable th) {
//            GlobalFonctions.displayMessages(null, ctx, FacesMessage.SEVERITY_FATAL, "access.droit.typeAvenant", exception.Error.FATAL_ERROR + "", new Object[]{access_Avenant});
//            logger.error("Erreur Survenu", th);
//        }
////        if (Objects.equals(valider, Boolean.TRUE)) {
////            this.reload();
////        }
//
//    }
//
//    public void addAccessAvenant() {
//        // etudiant.setMatricule(GenerationController.genererMatricule());
//        FacesContext ctx = FacesContext.getCurrentInstance();
//        ExternalContext extContext = ctx.getExternalContext();
//        Locale myLoc = ctx.getViewRoot().getLocale();
//        Boolean valider = Boolean.FALSE;
//        Orclass_Access_Avenant access_Avenant = null;
//        try {
//
//            if (categories == null || categories.getIdCategorie() == null) {
//                PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage("Please Select Value", "Category is Null"));
//                return;
//            }
//            if ((accessAvenat_Caracteristique == null || accessAvenat_Caracteristique.getId() == null)) {
//                access_Avenant = new Orclass_Access_Avenant();
//                access_Avenant.setDateCreation(new Date());
//                access_Avenant.setIdBranche(branches);
//                if (categories != null && categories.getIdCategorie() != null) {
//                    access_Avenant.setIdCategories(categories);
//                }
//                access_Avenant.setIdEntreprises(entreprise);
//                access_Avenant.setIdObjet(accessAvenat_Caracteristique.getIdAccess_Avenant().getIdObjet());
//                access_Avenant.setIdTypeAvenant(accessAvenat_Caracteristique.getIdAccess_Avenant().getIdTypeAvenant());
//                access_Avenant.setAjouter(accessAvenat_Caracteristique.getIdAccess_Avenant().getAjouter());
//                access_Avenant.setSupprimer(accessAvenat_Caracteristique.getIdAccess_Avenant().getSupprimer());
//                access_Avenant.setModifier(accessAvenat_Caracteristique.getIdAccess_Avenant().getModifier());
//                if (access_AvenantDao.finKey(entreprise, branches, access_Avenant.getIdObjet(), access_Avenant.getIdTypeAvenant(), access_Avenant.getAjouter(), access_Avenant.getModifier(), access_Avenant.getSupprimer()) == null) {
//                    access_AvenantDao.create(access_Avenant);
//                    access_Avenant = access_AvenantDao.finKey(entreprise, branches, access_Avenant.getIdObjet(), access_Avenant.getIdTypeAvenant(), access_Avenant.getAjouter(), access_Avenant.getModifier(), access_Avenant.getSupprimer());
//                }
//                if (access_Avenant == null || access_Avenant.getId() == null) {
//                    GlobalFonctions.displayMessages(null, ctx, FacesMessage.SEVERITY_FATAL, "Access", "ERROR FOR INSERTION VALUE IS NULL" + " PLEASE CHECK AND TRY AGAINST", new Object[]{access_Avenant});
//                    return;
//
//                }
//                if (Objects.equals(access_Avenant.getModifier(), Boolean.TRUE)) {
//                    listeSelectCaracteristiques = accessAvenat_Caracteristique.getListeCaracteristiqueSelect();
//                    for (OrclassCaracteristiques c : listeSelectCaracteristiques) {
//                        accessAvenat_Caracteristique = new Orclass_AccessAvenat_Caracteristique();
//                        accessAvenat_Caracteristique.setIdAccess_Avenant(access_Avenant);
//                        accessAvenat_Caracteristique.setIdCaracteristiques(c);
//                        accessAvenat_Caracteristique.setIdEntreprise(entreprise);
//                        accessAvenat_CaracteristiqueDao.create(accessAvenat_Caracteristique);
//
//                    }
//                }
//
//                GlobalFonctions.displayMessages(null, ctx, FacesMessage.SEVERITY_INFO, "access.droit.typeAvenant", Success.OPERATION_SUCCESS + "", new Object[]{access_Avenant});
//
//                this.removetableAccessCaracteristique();
//            } else {
////                GlobalFonctions.displayMessages(null, ctx, FacesMessage.SEVERITY_WARN, "police", exception.Error.DUPLICATE_ERROR_INSERT + "" + numero_police, null);
//                GlobalFonctions.displayMessages(null, ctx, FacesMessage.SEVERITY_WARN, "access.droit.typeAvenant", exception.Error.FATAL_ERROR + "", new Object[]{access_Avenant});
//                return;
//            }
//
//        } catch (GlobalException e) {
//            GlobalFonctions.displayMessages(null, ctx, FacesMessage.SEVERITY_ERROR, access_Avenant.toString(), e.getCode(), e.getParam());
//            //throw e;
//        } catch (ConstraintViolationException ejb) {
//            GlobalFonctions.displayMessages(null, ctx, FacesMessage.SEVERITY_WARN, ejb.getCause().getClass().getSimpleName(), exception.Error.FATAL_ERROR + "", null);
//            throw ejb;
//        } catch (Throwable th) {
//            GlobalFonctions.displayMessages(null, ctx, FacesMessage.SEVERITY_FATAL, "access.droit.typeAvenant", exception.Error.FATAL_ERROR + "", new Object[]{"access.droit.typeAvenant"});
//            logger.error("Erreur Survenu", th);
//        }
////        if (Objects.equals(valider, Boolean.TRUE)) {
////            this.reload();
////        }
//
//    }
//
//    public void deleteAccessAvenant() throws IOException {
//        // etudiant.setMatricule(GenerationController.genererMatricule());
//        FacesContext ctx = FacesContext.getCurrentInstance();
//        ExternalContext extContext = ctx.getExternalContext();
//        Locale myLoc = ctx.getViewRoot().getLocale();
//        Boolean valider = Boolean.FALSE;
//        int retournValeur = 0;
//        try {
//
//            //traitement de la photo
//            if ((access_Avenant != null && access_Avenant.getId() != null)) {
//                for (Orclass_AccessAvenat_Caracteristique acc : accessAvenat_CaracteristiqueDao.listCaracteristiqueByAccessAvenant(access_Avenant, entreprise)) {
//                    accessAvenat_CaracteristiqueDao.delete(acc);
//                }
//                access_AvenantDao.delete(access_Avenant);
////                retournValeur = access_AvenantDao.deleteAccessAvenant(entreprise, access_Avenant);
////                if (retournValeur == 1) {
//                GlobalFonctions.displayMessages(null, ctx, FacesMessage.SEVERITY_INFO, "access.droit.typeAvenant", Success.DELETE_SUCCESS + "", new Object[]{access_Avenant});
////
////                } else {
////                    GlobalFonctions.displayMessages(null, ctx, FacesMessage.SEVERITY_WARN, "access.droit.typeAvenant", exception.Error.DELETE_ERROR + "", new Object[]{access_Avenant});
////
////                }
//                listeAccess_Avenant = access_AvenantDao.listeAccessAvenantByCompagnies(entreprise, branches);
//            } else {
////                GlobalFonctions.displayMessages(null, ctx, FacesMessage.SEVERITY_WARN, "police", exception.Error.DUPLICATE_ERROR_INSERT + "" + numero_police, null);
//                GlobalFonctions.displayMessages(null, ctx, FacesMessage.SEVERITY_WARN, "access.droit.typeAvenant", exception.Error.FATAL_ERROR + "", new Object[]{access_Avenant});
//                return;
//            }
//
//        } catch (GlobalException e) {
//            GlobalFonctions.displayMessages(null, ctx, FacesMessage.SEVERITY_ERROR, access_Avenant.toString(), e.getCode(), e.getParam());
//            //throw e;
//        } catch (ConstraintViolationException ejb) {
//            GlobalFonctions.displayMessages(null, ctx, FacesMessage.SEVERITY_WARN, ejb.getCause().getClass().getSimpleName(), exception.Error.FATAL_ERROR + "", null);
//            throw ejb;
//        } catch (Throwable th) {
//            GlobalFonctions.displayMessages(null, ctx, FacesMessage.SEVERITY_FATAL, "access.droit.typeAvenant", exception.Error.FATAL_ERROR + "", new Object[]{access_Avenant});
//            logger.error("Erreur Survenu", th);
//        }
////        if (Objects.equals(valider, Boolean.TRUE)) {
////            this.reload();
////        }
//
//    }
//
//    public Boolean afficherListe(Orclass_Access_Avenant item) {
//        try {
//            if (item != null && item.getId() != null && Objects.equals(item.getModifier(), Boolean.TRUE)) {
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
//    public void showAccessDroitAvenant(Orclass_AccessAvenat_Caracteristique item) {
//        this.setAccessAvenat_Caracteristique(item);
//    }
//
//    public void removetableAccessCaracteristique() {
//        listeAccessAvenat_Caracteristique.clear();
//        accessAvenat_Caracteristique = new Orclass_AccessAvenat_Caracteristique();
//        accessAvenat_Caracteristique.setIdEntreprise(entreprise);
//        listeAccessAvenat_Caracteristique.add(accessAvenat_Caracteristique);
//        this.updateTableAccessAvenant();
//    }
//
//    public Orclass_TypeAvenant getTypeAvenant() {
//        return typeAvenant;
//    }
//
//    public void setTypeAvenant(Orclass_TypeAvenant typeAvenant) {
//        this.typeAvenant = typeAvenant;
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
//    public List<OrclassCategories> getListeCategories() {
//        return listeCategories;
//    }
//
//    public void setListeCategories(List<OrclassCategories> listeCategories) {
//        this.listeCategories = listeCategories;
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
//    public List<OrclassCaracteristiques> getListeCaracteristiques() {
//        return listeCaracteristiques;
//    }
//
//    public void setListeCaracteristiques(List<OrclassCaracteristiques> listeCaracteristiques) {
//        this.listeCaracteristiques = listeCaracteristiques;
//    }
//
//    public List<OrclassCaracteristiques> getListeSelectCaracteristiques() {
//        return listeSelectCaracteristiques;
//    }
//
//    public void setListeSelectCaracteristiques(List<OrclassCaracteristiques> listeSelectCaracteristiques) {
//        this.listeSelectCaracteristiques = listeSelectCaracteristiques;
//    }
//
//    public List<Orclass_TypeAvenant> getListeTypeAvenant() {
//        return listeTypeAvenant;
//    }
//
//    public void setListeTypeAvenant(List<Orclass_TypeAvenant> listeTypeAvenant) {
//        this.listeTypeAvenant = listeTypeAvenant;
//    }
//
//    public OrclassBranches getBranches() {
//        return branches;
//    }
//
//    public void setBranches(OrclassBranches branches) {
//        this.branches = branches;
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
//    public Orclass_Objet getOrclass_Objet() {
//        return orclass_Objet;
//    }
//
//    public void setOrclass_Objet(Orclass_Objet orclass_Objet) {
//        this.orclass_Objet = orclass_Objet;
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
//    public List<Orclass_AccessAvenat_Caracteristique> getListeAccessAvenat_Caracteristique() {
//        return listeAccessAvenat_Caracteristique;
//    }
//
//    public void setListeAccessAvenat_Caracteristique(List<Orclass_AccessAvenat_Caracteristique> listeAccessAvenat_Caracteristique) {
//        this.listeAccessAvenat_Caracteristique = listeAccessAvenat_Caracteristique;
//    }
//
//    public List<Orclass_AccessAvenat_Caracteristique> getFilterAccessAvenat_Caracteristique() {
//        return filterAccessAvenat_Caracteristique;
//    }
//
//    public void setFilterAccessAvenat_Caracteristique(List<Orclass_AccessAvenat_Caracteristique> filterAccessAvenat_Caracteristique) {
//        this.filterAccessAvenat_Caracteristique = filterAccessAvenat_Caracteristique;
//    }
//
//    public Orclass_AccessAvenat_Caracteristique getAccessAvenat_Caracteristique() {
//        return accessAvenat_Caracteristique;
//    }
//
//    public void setAccessAvenat_Caracteristique(Orclass_AccessAvenat_Caracteristique accessAvenat_Caracteristique) {
//        this.accessAvenat_Caracteristique = accessAvenat_Caracteristique;
//    }
//
//    public Orclass_Access_Avenant getAccess_Avenant() {
//        return access_Avenant;
//    }
//
//    public void setAccess_Avenant(Orclass_Access_Avenant access_Avenant) {
//        this.access_Avenant = access_Avenant;
//    }
//
//    public List<Orclass_Access_Avenant> getListeAccess_Avenant() {
//        return listeAccess_Avenant;
//    }
//
//    public void setListeAccess_Avenant(List<Orclass_Access_Avenant> listeAccess_Avenant) {
//        this.listeAccess_Avenant = listeAccess_Avenant;
//    }
//
//    public List<Orclass_Access_Avenant> getFilterAccessAvenat() {
//        return filterAccessAvenat;
//    }
//
//    public void setFilterAccessAvenat(List<Orclass_Access_Avenant> filterAccessAvenat) {
//        this.filterAccessAvenat = filterAccessAvenat;
//    }
//
//    public List<OrclassCaracteristiques> getListeCaracteristiqueObjetNotHaveAccess() {
//        return listeCaracteristiqueObjetNotHaveAccess;
//    }
//
//    public void setListeCaracteristiqueObjetNotHaveAccess(List<OrclassCaracteristiques> listeCaracteristiqueObjetNotHaveAccess) {
//        this.listeCaracteristiqueObjetNotHaveAccess = listeCaracteristiqueObjetNotHaveAccess;
//    }
//
//    public List<Orclass_AccessAvenat_Caracteristique> getListeCaracteristiqueHaveAccess() {
//        return listeCaracteristiqueHaveAccess;
//    }
//
//    public void setListeCaracteristiqueHaveAccess(List<Orclass_AccessAvenat_Caracteristique> listeCaracteristiqueHaveAccess) {
//        this.listeCaracteristiqueHaveAccess = listeCaracteristiqueHaveAccess;
//    }
//
//    public List<Orclass_AccessAvenat_Caracteristique> getListeElement_Liste_Caracteristique() {
//        return listeElement_Liste_Caracteristique;
//    }
//
//    public void setListeElement_Liste_Caracteristique(List<Orclass_AccessAvenat_Caracteristique> listeElement_Liste_Caracteristique) {
//        this.listeElement_Liste_Caracteristique = listeElement_Liste_Caracteristique;
//    }
//
//}
