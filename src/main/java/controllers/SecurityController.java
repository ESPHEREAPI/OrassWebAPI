package controllers;

//import dao.LicenceDao;
import dao.PersonneDao;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.faces.application.Application;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import modele.OrclassMenus;
import modele.OrclassModules;
import modele.OrclassUtilisateurs;

import modele.Personne;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;
import parametrage.ISecurite;

import utils.GlobalFonctions;
import utils.LocaleHelper;
import utils.RecupBundle;

/**
 *
 * @author evrado
 */
@Named(value = "securityController")
@SessionScoped
public class SecurityController implements Serializable {

    private MenuModel Module;

    @EJB
    private ISecurite securiteUser;
    @EJB
    private PersonneDao userDao;
    private OrclassUtilisateurs user;
    private OrclassModules modulesecurite;
private Locale myLoc;
    public SecurityController() {
        Module = new DefaultMenuModel();
    }

    public void init() {
        user = new OrclassUtilisateurs();
        modulesecurite = new OrclassModules();
    }

    @PostConstruct
    void chargeModule() {
         
        this.init();

        FacesContext facesContext = FacesContext.getCurrentInstance();//contexte courant
        facesContext.getExternalContext().getSession(true); //empeche la session de s'expirer
//        String fr = System.getProperty("os.local");
        Locale myLoc;
//        String localeCode = "fr";

        if (facesContext.getViewRoot() == null) {

            myLoc = this.getMyLoc();
        } else {
            myLoc = facesContext.getViewRoot().getLocale();//la locale du systeme   
        }
        //   Locale myLoc = new Locale(locale);
//        myLoc = facesContext.getViewRoot().getLocale();//la locale du systeme   
        Application application = facesContext.getApplication();//context application

        //pouvoir manipuler les EL 
        ExpressionFactory expressionFactory = application.getExpressionFactory();
        ELContext elContext = facesContext.getELContext();

        String cheminPath = facesContext.getExternalContext().getRequestContextPath();
        Integer licenceValue = (Integer) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(GlobalFonctions.LICENCE_STATUT);

//        if (licenceValue != null && licenceValue == LicenceDao.LICENCE_GOOD) {
        //recuperation de l'etat de l'application en configuration ou non
//            Integer conf = (Integer) facesContext.getExternalContext().getSessionMap().get("sessionconfig");
        Integer conf = 2;
        // Ici on vide le menubar des modules
        Module.getElements().clear();

        DefaultSubMenu moduleSubmenu;

        user = (OrclassUtilisateurs) facesContext.getExternalContext().getSessionMap().get(GlobalFonctions.SESSION_USER);
//        if (conf == null) {
//            conf = 1;
//        }
        if (conf == 2 && user!=null) {
            Collection<OrclassModules> colModSecu = new ArrayList<>();

//            colModSecu = securiteUser.getModuleByUser(user);
//            if (colModSecu != null && !colModSecu.isEmpty()) {
//                //recuperation de tous les modules attribuer a l utilisateur courant
//                for (OrclassModules mod : colModSecu) {
//                    moduleSubmenu = new DefaultSubMenu();
//                    //moduleItem= new MenuItem();
//                    String ValMod = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, mod.getCode(), null, myLoc);
//                    //Si la traduction n existe pas dans le fichier poperty on prend la description du module 
//                    if (ValMod == null) {
//                        ValMod = "";
//                    }
//                    if (ValMod.compareToIgnoreCase("ERROR MESSAGE!") == 0 || ValMod.trim().isEmpty()) {
//                        ValMod = mod.getLibelle();//Si la desciption est vide alors on charge le code
//                        if (ValMod == null) {
//                            ValMod = "";
//                        }
//
//                        if (ValMod.trim().isEmpty()) {
//                            ValMod = mod.getCode();
//                        }
//                    }
//
//                    modulesecurite = new OrclassModules();
//                    modulesecurite.setIdModule(mod.getIdModule());
//                    modulesecurite = securiteUser.getModule(modulesecurite);
//
//                    // Chargement des menus du module concerner
//                    Collection<OrclassMenus> colMenu = new ArrayList<>();
//                    List<DefaultMenuItem> colmenuItem = new ArrayList<>();
//                    colMenu = securiteUser.getMenusbyModuleForUser(user, modulesecurite);
//                    if (colMenu != null && !colMenu.isEmpty()) {
//
//                        for (OrclassMenus menu : colMenu) {
//
//                            if (menu.getEtat() != null && menu.getEtat() == true) {
//                                continue;
//                            }
//                            DefaultMenuItem menuItem = new DefaultMenuItem();
//
//                            String ValMenu = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, menu.getCode(), null, myLoc);
//
//                            if (ValMenu == null) {
//                                ValMenu = "";
//                            }
//                            if (ValMenu.compareToIgnoreCase("ERROR MESSAGE!") == 0 || ValMenu.trim().isEmpty()) {
//                                ValMenu = menu.getLibelle();
//                                if (ValMenu == null) {
//                                    ValMenu = "";
//                                }
//                                if (ValMenu.trim().isEmpty()) {
//                                    ValMenu = menu.getCode();
//                                }
//                            }
//                            if (ValMenu == null || ValMenu.equals("")) {
//                                ValMenu = "undefined";
//                            }
//
//                            String chemin = menu.getChemin();
//
//                            if (chemin.endsWith(".xhtml") == false) {
//                                chemin += ".xhtml";
//                            }
//                            menuItem.setValue(ValMenu);
//                            menuItem.setUrl(chemin);
//                            menuItem.setAjax(true);
//                            colmenuItem.add(menuItem);
//                           
//                        }
////                         moduleSubmenu.setElements(colmenuItem);
//                    }
//                    if (ValMod == null || ValMod.equals("")) {
//                        ValMod = "undefined";
//                    }
//
//                    moduleSubmenu.setLabel(ValMod);
//                    String IdModule = "mod" + mod.getIdModule().toString();
//                    moduleSubmenu.setId(IdModule);
//
//                    moduleSubmenu.setIcon("ui-icon-arrow-1-e");
//                    Module.addElement(moduleSubmenu);
//
//                }
//            }
        }
//        }

    }

    public MenuModel getModule() {
        return Module;
    }

    public ISecurite getSecuriteUser() {
        return securiteUser;
    }

    public void setSecuriteUser(ISecurite securiteUser) {
        this.securiteUser = securiteUser;
    }

    public OrclassUtilisateurs getUser() {
        return user;
    }

    public void setUser(OrclassUtilisateurs user) {
        this.user = user;
    }

    public OrclassModules getModulesecurite() {
        return modulesecurite;
    }

    public void setModulesecurite(OrclassModules modulesecurite) {
        this.modulesecurite = modulesecurite;
    }

//    public String getLocale() {
//        return locale;
//    }
//
//    public void setLocale(String locale) {
//        this.locale = locale;
//    }

    public Locale getMyLoc() {
        if (myLoc==null) {
            myLoc=new Locale("fr");
        }
        return myLoc;
    }

    public void setMyLoc(Locale myLoc) {
        this.myLoc = myLoc;
    }
    
}
