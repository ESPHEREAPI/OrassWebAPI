/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.administration;

import static Entreprise.EntrepriseServices.logger;
//import Entreprise.OracleConnection;
import controllers.CurrentInstance;
import dao.OrclassActionsDao;
import dao.OrclassFonctionnalitesDao;
import dao.OrclassMenusAccesDao;
import dao.OrclassMenusDao;
import dao.OrclassModulesDao;
import droitAcces.IDroitAcces;
import enums.Actions;
import enums.FonctionnaliteModuleAdministration;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.validation.ConstraintViolationException;
import modele.OrclassActions;
import modele.OrclassEntreprises;
import modele.OrclassFonctionnalites;
import modele.OrclassMenus;
import modele.OrclassMenusAcces;
import modele.OrclassModules;
import modele.OrclassUtilisateurs;
import org.primefaces.PrimeFaces;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.TransferEvent;
import org.primefaces.event.UnselectEvent;
import org.primefaces.model.DualListModel;
import parametrage.ISecurite;
import utils.GlobalFonctions;
import utils.LocaleHelper;
import utils.RecupBundle;

/**
 *
 * @author hp
 */
@Named(value = "menuAccessController")
@ViewScoped
public class MenuAccessController implements Serializable {

    /**
     * Creates a new instance of MenuAccessController
     */
    @EJB
    private ISecurite securiteService;
    @EJB
    OrclassFonctionnalitesDao fonctionnalitesDao;
    @EJB
    OrclassActionsDao orclassActionsDao;
    @EJB
    OrclassModulesDao modulesDao;
    @EJB
    OrclassMenusAccesDao menusAccesDao;

    private DualListModel<OrclassFonctionnalites> fonctionaliteModel;
    private List<OrclassFonctionnalites> menusSourceFonctionnalite;
    private List<OrclassFonctionnalites> menusTargetFonctionnalite;
    private DualListModel<OrclassActions> actionModel;
    private List< OrclassActions> menusSourceAction;
    private List< OrclassActions> menusTargetAction;
    private List<OrclassModules> listeModuleByEntreprise;
    private List<OrclassMenus> listMenuByModule;
    private OrclassModules module;
    private OrclassFonctionnalites fonctionnalites;
    private List<OrclassFonctionnalites> listeFonctionnalite = new ArrayList<>();
    private OrclassFonctionnalites fonctionnaliteAdd;
    OrclassMenus menus;
    OrclassEntreprises entreprise;
    @Inject
    CurrentInstance currentInstance;
    private List<Actions> selectedActionByFonctionalite = new ArrayList<>();
    @EJB
    IDroitAcces serviceAccess;
    @EJB
    OrclassMenusDao menusDao;
    private OrclassMenus menu;
    private OrclassModules moduleAcces;
    OrclassUtilisateurs user;

    public MenuAccessController() {
        module = new OrclassModules();
        menus = new OrclassMenus();
        fonctionnalites = new OrclassFonctionnalites();
        fonctionnaliteAdd = new OrclassFonctionnalites();
        selectedActionByFonctionalite = new ArrayList<>();

    }

    public Boolean accessCree() {
        OrclassActions action = null;
        OrclassFonctionnalites fon = null;
        OrclassMenusAcces ma = null;
        if (menu != null && menu.getIdMenu() != null && module != null && module.getIdModule() != null) {
            fon = serviceAccess.getFonctionnaliteByCode(FonctionnaliteModuleAdministration.gestion_profil.name());
            action = serviceAccess.getActionByCodeByFonctionnalit(Actions.creer.name(), fon);

            return serviceAccess.accessAction(user, action, menu);
        }
        return Boolean.FALSE;
    }

    public Boolean accessModifier() {
        OrclassActions action = null;
        OrclassFonctionnalites fon = null;
        if (menu != null && menu.getIdMenu() != null && module != null && module.getIdModule() != null) {
            fon = serviceAccess.getFonctionnaliteByCode(FonctionnaliteModuleAdministration.gestion_profil.name());
            action = serviceAccess.getActionByCodeByFonctionnalit(Actions.modifier.name(), fon);
            return serviceAccess.accessAction(user, action, menu);
        }
        return Boolean.FALSE;
    }

    @PostConstruct
    void initialiseSession() {
        FacesContext.getCurrentInstance().getExternalContext().getSession(true);

        listeModuleByEntreprise = (List<OrclassModules>) modulesDao.findAll();

    }

    public void chargeFonctionNaliteByFonctionnaliteTager() {
        if (!fonctionaliteModel.getTarget().isEmpty()) {
            listeFonctionnalite = fonctionaliteModel.getTarget();
        }
    }

    public void reset() {

        this.init();
        PrimeFaces.current().ajax().update("form1");
//        this.currentInstance.getAjaxRequestBuilder().reset();

    }

    public void addActions() {
        OrclassActions orclassActions = null;
        List<OrclassActions> listAction = new ArrayList();
        if (fonctionnaliteAdd != null && fonctionnaliteAdd.getIdFonctionnalite() != null) {
            for (Actions act : selectedActionByFonctionalite) {
                orclassActions = orclassActionsDao.finkey(fonctionnaliteAdd, module, act.name());
                if (orclassActions == null) {
                    orclassActions = new OrclassActions();
                    orclassActions.setCode(act.name());
                    orclassActions.setDateCreation(new Date());
                    orclassActions.setIdFonctionnalite(fonctionnaliteAdd);
                    orclassActions.setLibelle(act.name());
                    listAction.add(orclassActions);

                }

            }
            if (!listAction.isEmpty()) {
                orclassActionsDao.createAll(listAction);
                this.setFonctionnalites(fonctionnalites);
                this.populateAction();
                PrimeFaces.current().ajax().update("tabprincipal:pickListAction");
            }
        }
    }

    public void init() {
//        module = new OrclassModules();
//        menus = new OrclassMenus();
//        fonctionnalites = new OrclassFonctionnalites();
        this.populateFonctionnalite();
        this.populateAction();

    }

    public void chargeMenuByModule() {
        if (module != null && module.getIdModule() != null) {
            listMenuByModule = new ArrayList<>();
            for (OrclassMenus menu : securiteService.getMenusByModule(module)) {
                if ("#".equals(menu.getChemin())) {
                    continue;
                }
                listMenuByModule.add(menu);
            }

        }
    }

    public void populateFonctionnalite() {
        menusSourceFonctionnalite = new ArrayList<>();
        menusTargetFonctionnalite = new ArrayList<>();
        Collection<OrclassFonctionnalites> menu1 = new ArrayList<>();
        Collection<OrclassFonctionnalites> menu2 = new ArrayList<>();
        menusSourceFonctionnalite.clear();
        menusTargetFonctionnalite.clear();

        if (menus != null && menus.getIdMenu() != null && module != null && module.getIdModule() != null) {
            menu1 = securiteService.getFonctionnaliterByModule(module);
            menu2 = fonctionnalitesDao.getAllFonctionnaliteByMenuAccess(module, menus);
            if (!menu2.isEmpty()) {
                menu1.removeAll(menu2);
                menusTargetFonctionnalite.addAll(menu2);
            }
            if (menu1 != null || menu1.isEmpty() == false) {
                menusSourceFonctionnalite.addAll(menu1);
            }
        } else {
            menusSourceFonctionnalite = new ArrayList<>();
            menusTargetFonctionnalite = new ArrayList<>();

        }

    }

    public void populateAction() {;
        menusTargetAction = new ArrayList<>();
        menusSourceAction = new ArrayList<>();
        Collection<OrclassActions> menu1 = new ArrayList<>();
        Collection<OrclassActions> menu2 = new ArrayList<>();
        Collection<OrclassActions> menu3 = new ArrayList<>();
        Collection<String> menu4 = new ArrayList<>();
        menusSourceAction.clear();
        menusTargetAction.clear();

        if (fonctionnalites != null && fonctionnalites.getIdFonctionnalite() != null && module != null && module.getIdModule() != null) {
//            menu1 = orclassActionsDao.findAll();
            menu1 = orclassActionsDao.getAllactionByFonctionnalite(fonctionnalites, module);
            menu2 = orclassActionsDao.getAllactionByFonctionnaliteForUserAccess(fonctionnalites, module);
//            menu1.removeAll(menu2);
            if (menu2 != null || menu2.isEmpty() == false) {
                menusTargetAction.addAll(menu2);
                menu1.removeAll(menu2);
            }
//            if (menu1 != null || menu1.isEmpty() == false) {
//                for (OrclassActions act : menu1) {
//                    if (menu4.isEmpty()) {
//                        menu4.add(act.getCode());
//                        menu3.add(act);
//                    } else if (menu4.contains(act.getCode()) == Boolean.FALSE) {
//                        menu4.add(act.getCode());
//                        menu3.add(act);
//                    }
//                   
//
//                }
//                menu3.removeAll(menu2);
            menusSourceAction.addAll(menu1);
//            }

        } else {
            menusTargetAction = new ArrayList<>();
            menusSourceAction = new ArrayList<>();
        }
    }

    public DualListModel<OrclassFonctionnalites> getFonctionaliteModel() {
        this.populateFonctionnalite();
        fonctionaliteModel = new DualListModel<OrclassFonctionnalites>(menusSourceFonctionnalite, menusTargetFonctionnalite);
        return fonctionaliteModel;
    }

    public void setFonctionaliteModel(DualListModel<OrclassFonctionnalites> fonctionaliteModel) {
        this.fonctionaliteModel = fonctionaliteModel;
    }

    public DualListModel<OrclassActions> getActionModel() {
        this.populateAction();
        actionModel = new DualListModel<OrclassActions>(menusSourceAction, menusTargetAction);
        return actionModel;
    }

    public String saveMenuAcces() {
        FacesContext ctx = FacesContext.getCurrentInstance();
        //get default locale
        Locale myLoc = ctx.getViewRoot().getLocale();
        String entete[] = {LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "cree.profil", null, myLoc)};

        // on recupere tous les modules qui lui sont attribuer puis on inserre
        String[] detail = {entete[0], "Module(s)"};
//        OracleConnection con = new OracleConnection();
        OrclassMenusAcces ma = null;
        List<OrclassMenusAcces> listUa = new ArrayList<>();
        List<OrclassActions> listeActionByFonctionnalite = new ArrayList<>();
        try {
            if (menus != null && menus.getIdMenu() != null && module != null && module.getIdModule() != null) {
                for (OrclassFonctionnalites f : fonctionaliteModel.getSource()) {
                    if (Objects.equals(menusAccesDao.fonctionnaliteByMenyExiste(menus, f), Boolean.FALSE)) {
                        continue;
                    }
                    // supprimer cette fonctionnalite lie a ceux menu
//                    con.deleteFonctionnalitByMenuAccess(f, menus);

                }
                for (OrclassFonctionnalites f : fonctionaliteModel.getTarget()) {
                    listUa.clear();

                    listeActionByFonctionnalite = orclassActionsDao.getAllactionByFonctionnalite(f, module);
                    if (listeActionByFonctionnalite.isEmpty() == true) {
                        /*
                        nous executons un dialo enfin d informer l utilisateur sur la fonctionnalite en lui precisant que cette fonctionnalite n a aucune action 
                         */
                        this.setFonctionnaliteAdd(f);
                        this.chargeFonctionNaliteByFonctionnaliteTager();

                        PrimeFaces.current().executeScript("PF('message').show();");
                        PrimeFaces.current().ajax().update("form2:actions");
                        return "";
                    }
                    for (OrclassActions act : listeActionByFonctionnalite) {
                        /*
                    cette boucle va s execute dans la messure ou cette fonctionnalite est lieé au moins a une option 
                    dans le cas ou elle n est pas lier a une fonctionnalite nous devons la creer directement à parttie du formulaire
                         */
                        ma = menusAccesDao.finkey(menus, f, act);
                        if (ma != null && ma.getIdMenuAcces() != null) {
                            continue;
                        }
                        ma = new OrclassMenusAcces();
                        ma.setIdAction(act);
                        ma.setIdFonctionnalite(f);
                        ma.setIdMenu(menus);
                        listUa.add(ma);
                    }
                    if (!listUa.isEmpty()) {
                        menusAccesDao.createAll(listUa);
                    }

                }
                ctx.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_INFO, "" + exception.Success.OPERATION_SUCCESS, null));

            }

        } catch (ConstraintViolationException ejb) {
            GlobalFonctions.displayMessages("msg", ctx, FacesMessage.SEVERITY_WARN, ejb.getCause().getClass().getSimpleName(), exception.Error.DUPLICATE_ERROR_INSERT + "", null);
            throw ejb;
        } catch (Exception th) {
            GlobalFonctions.displayMessages(null, ctx, FacesMessage.SEVERITY_FATAL, th.getMessage(), exception.Error.FATAL_ERROR + "", null);
            logger.error("Erreur Survenu", th.getLocalizedMessage());
            logger.error("Erreur cause", th.getCause().getMessage());
        }
//        this.reset();
        return null;
    }

    public void chargeAndAddAction() {
        if (fonctionnalites != null && fonctionnalites.getIdFonctionnalite() != null) {
            this.setFonctionnaliteAdd(fonctionnalites);
            this.chargeFonctionNaliteByFonctionnaliteTager();
            this.getActions();

            PrimeFaces.current().executeScript("PF('manageActionDialog').show();");
            PrimeFaces.current().ajax().update("form2:actions");
            PrimeFaces.current().ajax().update("form2:actionEvenement");
        } else {
//           PrimeFaces.current().executeScript("PF('infos').show();");
            PrimeFaces.current().executeScript("PF('manageActionDialog').show();");
            PrimeFaces.current().ajax().update("form2:actions");
            PrimeFaces.current().ajax().update("form2:actionEvenement");
        }
    }

    public String saveMenuAccesForAction() {
        FacesContext ctx = FacesContext.getCurrentInstance();
        //get default locale
        Locale myLoc = ctx.getViewRoot().getLocale();
        String entete[] = {LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "cree.profil", null, myLoc)};

        // on recupere tous les modules qui lui sont attribuer puis on inserre
        String[] detail = {entete[0], "Module(s)"};
//        OracleConnection con = new OracleConnection();
        OrclassMenusAcces ma = null;
        List<OrclassMenusAcces> listUa = new ArrayList<>();
        try {
            if (menus != null && menus.getIdMenu() != null && module != null && module.getIdModule() != null && fonctionnalites != null && fonctionnalites.getIdFonctionnalite() != null) {

                listUa.clear();
                for (OrclassActions act : actionModel.getSource()) {
                    ma = menusAccesDao.finkey(menus, fonctionnalites, act);
                    if (ma != null && ma.getIdMenuAcces() != null) {
                        // on supprime la ligne de l action
//                        con.deleteMenuAccess(ma);

                    }

                }
                for (OrclassActions act : actionModel.getTarget()) {
                    ma = menusAccesDao.finkey(menus, fonctionnalites, act);
                    if (ma != null && ma.getIdMenuAcces() != null) {
                        continue;
                    }
                    ma = new OrclassMenusAcces();
                    ma.setIdAction(act);
                    ma.setIdFonctionnalite(fonctionnalites);
                    ma.setIdMenu(menus);
                    menusAccesDao.create(ma);

                }

                ctx.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_INFO, "" + exception.Success.OPERATION_SUCCESS, null));

            }

        } catch (ConstraintViolationException ejb) {
            GlobalFonctions.displayMessages("msg", ctx, FacesMessage.SEVERITY_WARN, ejb.getCause().getClass().getSimpleName(), exception.Error.DUPLICATE_ERROR_INSERT + "", null);
            throw ejb;
        } catch (Exception th) {
            GlobalFonctions.displayMessages(null, ctx, FacesMessage.SEVERITY_FATAL, th.getMessage(), exception.Error.FATAL_ERROR + "", null);
            logger.error("Erreur Survenu", th.getLocalizedMessage());
            logger.error("Erreur cause", th.getCause().getMessage());
        }
        this.reset();
        return null;
    }

    public List<SelectItem> getActions() {
        List<SelectItem> items = new ArrayList<>();
        List<String> actionExiste = new ArrayList<>();
        FacesContext ctx = FacesContext.getCurrentInstance();
        Locale myLoc = ctx.getViewRoot().getLocale();
        if (fonctionnalites != null && fonctionnalites.getIdFonctionnalite() != null) {

            for (Actions actions : Actions.values()) {
                if (orclassActionsDao.finkey(fonctionnalites, module, actions.name()) != null) {
                    continue;
                }
                items.add(new SelectItem(actions, LocaleHelper.getLocaleString(RecupBundle.FichierBundle, actions.name(), null, myLoc)));

            }
        } else {
            for (Actions actions : Actions.values()) {
                items.add(new SelectItem(actions, LocaleHelper.getLocaleString(RecupBundle.FichierBundle, actions.name(), null, myLoc)));

            }
        }

        return items;
    }

    public void setActionModel(DualListModel<OrclassActions> actionModel) {
        this.actionModel = actionModel;
    }

    public List<OrclassModules> getListeModuleByEntreprise() {
        return listeModuleByEntreprise;
    }

    public void setListeModuleByEntreprise(List<OrclassModules> listeModuleByEntreprise) {
        this.listeModuleByEntreprise = listeModuleByEntreprise;
    }

    public List<OrclassMenus> getListMenuByModule() {
        return listMenuByModule;
    }

    public void setListMenuByModule(List<OrclassMenus> listMenuByModule) {
        this.listMenuByModule = listMenuByModule;
    }

    public OrclassModules getModule() {
        return module;
    }

    public void setModule(OrclassModules module) {
        this.module = module;
    }

    public OrclassMenus getMenus() {
        return menus;
    }

    public void setMenus(OrclassMenus menus) {
        this.menus = menus;
    }

    public void onTransfer(TransferEvent event) {
        StringBuilder builder = new StringBuilder();
        for (Object item : event.getItems()) {
            builder.append(((Object) item).toString()).append("<br />");
        }

        FacesMessage msg = new FacesMessage();
        msg.setSeverity(FacesMessage.SEVERITY_INFO);
        msg.setSummary("Items Transferred");
        msg.setDetail(builder.toString());

        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onSelect(SelectEvent<Object> event) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Item Selected", event.getObject().toString()));
    }

    public void onUnselect(UnselectEvent<Object> event) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Item Unselected", event.getObject().toString()));
    }

    public void onReorder() {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "List Reordered", null));
    }

    public OrclassFonctionnalites getFonctionnalites() {
        return fonctionnalites;
    }

    public void setFonctionnalites(OrclassFonctionnalites fonctionnalites) {
        this.fonctionnalites = fonctionnalites;
    }

    public List<OrclassFonctionnalites> getListeFonctionnalite() {
        return listeFonctionnalite;
    }

    public void setListeFonctionnalite(List<OrclassFonctionnalites> listeFonctionnalite) {
        this.listeFonctionnalite = listeFonctionnalite;
    }

    public OrclassFonctionnalites getFonctionnaliteAdd() {
        return fonctionnaliteAdd;
    }

    public void setFonctionnaliteAdd(OrclassFonctionnalites fonctionnaliteAdd) {
        this.fonctionnaliteAdd = fonctionnaliteAdd;
    }

    public List<Actions> getSelectedActionByFonctionalite() {
        return selectedActionByFonctionalite;
    }

    public void setSelectedActionByFonctionalite(List<Actions> selectedActionByFonctionalite) {
        this.selectedActionByFonctionalite = selectedActionByFonctionalite;
    }

}
