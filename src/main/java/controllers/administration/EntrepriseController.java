/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.administration;

import Entreprise.IEntreprise;
import static com.sun.org.apache.xerces.internal.impl.io.ASCIIReader.DEFAULT_BUFFER_SIZE;

import controllers.CurrentInstance;

import dao.OrclassEntreprisesModulesDao;
import dao.OrclassMenusDao;
import dao.OrclassModulesDao;
import dao.PaysDao;
import dao.SocieteDao;

import droitAcces.IDroitAcces;
import enums.Actions;
import enums.FonctionnaliteModuleAdministration;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.application.NavigationHandler;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.validation.ConstraintViolationException;
import modele.Adresse;
import modele.OrclassActions;
import modele.OrclassEntreprises;
import modele.OrclassEntreprisesModules;
import modele.OrclassEntreprisesModulesPK;
import modele.OrclassFonctionnalites;
import modele.OrclassMenus;
import modele.OrclassMenusAcces;
import modele.OrclassModules;
import modele.OrclassUtilisateurs;
import modele.Societe;
//import static org.eclipse.jdt.internal.core.util.CharArrayBuffer.DEFAULT_BUFFER_SIZE;
import org.primefaces.component.wizard.Wizard;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.GlobalFonctions;
import utils.LocaleHelper;
import utils.RecupBundle;

/**
 *
 * @author hp
 */
@Named(value = "entrepriseController")
@ViewScoped
public class EntrepriseController implements Serializable {

    private static final Logger logger = LoggerFactory.getLogger(EntrepriseController.class);
    /**
     * Creates a new instance of EntrepriseController
     */
    @EJB
    private SocieteDao societeDao;
    private Societe societe;
    @EJB
    private IEntreprise serviceEntreprise;
    @EJB
    private OrclassModulesDao orclassModulesDao;
    OrclassModules modules;
    @EJB
    OrclassEntreprisesModulesDao orclassEntreprisesModulesDao;
    OrclassEntreprisesModules orclassEntreprisesModules;
    @EJB
    PaysDao paysDao;
    @Inject
    CurrentInstance currentInstance;
    private String absolutePathImages;

    private String currentFolder = "/photos";
    private String imagePath;
    String summary = "";
    String msgdetail = "";
    private byte[] file1;
    private Wizard wizard;
    private StreamedContent logoimages;
    private Collection<OrclassModules> listModules = new ArrayList<>();
    private Collection<OrclassModules> selectedModules = new ArrayList<>();
    private List<Societe> currentEntreprise = new ArrayList<>();
    Date debut, fin;
    private OrclassMenus menu;
    private OrclassModules module;
    OrclassUtilisateurs user;
    OrclassUtilisateurs utilisateurs;
    @EJB
    IDroitAcces serviceAccess;
    @EJB
    OrclassMenusDao menusDao;
    @EJB
    OrclassModulesDao modulesDao;

    public EntrepriseController() {
        societe = new Societe();
        modules = new OrclassModules();

    }

    //initialiser l'etablissement
    public Boolean accessCree() {
        OrclassActions action = null;
        OrclassFonctionnalites fon = null;
        OrclassMenusAcces ma = null;
        if (menu != null && menu.getIdMenu() != null && module != null && module.getIdModule() != null) {

            fon = serviceAccess.getFonctionnaliteByCode(FonctionnaliteModuleAdministration.gestion_entreprise.name());
            action = serviceAccess.getActionByCodeByFonctionnalit(Actions.creer.name(), fon);

            return serviceAccess.accessAction(user, action, menu);
        }
        return Boolean.FALSE;
    }

    public Boolean accessModifier() {
        OrclassActions action = null;
        OrclassFonctionnalites fon = null;
        if (menu != null && menu.getIdMenu() != null && module != null && module.getIdModule() != null) {
            fon = serviceAccess.getFonctionnaliteByCode(FonctionnaliteModuleAdministration.gestion_entreprise.name());
            action = serviceAccess.getActionByCodeByFonctionnalit(Actions.modifier.name(), fon);
            return serviceAccess.accessAction(user, action, menu);
        }
        return Boolean.FALSE;
    }

    public void init() {
        societe = new Societe();
        selectedModules = new ArrayList<>();
        orclassEntreprisesModules = new OrclassEntreprisesModules();
        this.chargeCurentEntrerpise();

    }

    public void reset() {
        this.init();
        currentInstance.getAjaxRequestBuilder().reset();
//        RequestContext.getCurrentInstance().reset(":form1");
    }

    //permet d'eviter l'expiration illegal d'une session
    @PostConstruct
    void initialiseSession() {
        FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        societe = (Societe) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(GlobalFonctions.ENTREPRISE_ACTIF);
        currentEntreprise.add(societe);
        societe = new Societe();
        menu = menusDao.findEntityHavingValue("code", "entite.entreprise");
        module = modulesDao.findEntityHavingValue("code", "mod.admin");
        user = (OrclassUtilisateurs) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(GlobalFonctions.SESSION_USER);

    }

    public void chargeCurentEntrerpise() {
        currentEntreprise.clear();
        societe = (Societe) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(GlobalFonctions.ENTREPRISE_ACTIF);
        currentEntreprise.add(societe);

    }

    public void handleFileUpload(FileUploadEvent event) {

        file1 = event.getFile().getContent();
        String contentType = event.getFile().getContentType();
        societe.setLogosoci(file1);
        FacesContext ctx = FacesContext.getCurrentInstance();
        ExternalContext extContext = ctx.getExternalContext();
        imagePath = extContext.getRealPath(currentFolder);
        //imagePath+=File.separator;
//        String fileName = employeur.getSociete();
        String fileName = event.getFile().getFileName();
        societe.setChemin_logo(uploadFile(event, imagePath, fileName));
        FacesMessage msg = new FacesMessage("Success", event.getFile().getFileName() + " was Upload! .");
        FacesContext.getCurrentInstance().addMessage("growl", msg);
        //Refresh image
//        logoimages = new DefaultStreamedContent(new ByteArrayInputStream(file1), contentType);
        logoimages = new DefaultStreamedContent();

    }

    public String uploadFile(FileUploadEvent event, String folderDestination, String nameFile) {
        InputStream inputStream = null;
        OutputStream out = null;

        FacesContext ctx = FacesContext.getCurrentInstance();

        File dossiers = new File(folderDestination);

        //cree le dossier s'il n'existe pas
        if (!dossiers.exists()) {
            dossiers.mkdir();
        }

        try {

            String contentType = event.getFile().getContentType();
            String ext = contentType.split("/")[1];

            if (ext.equals("jpeg")) {
                ext = "jpg";
            }
            //je concatene le fichier avec l'extension
            nameFile += "." + ext;

            inputStream = event.getFile().getInputStream();

            out = new FileOutputStream(new File(dossiers, nameFile));
            int read = 0;
            byte[] bytes = new byte[DEFAULT_BUFFER_SIZE];

            while ((read = inputStream.read(bytes)) != -1) {
                out.write(bytes, 0, read);

            }

        } catch (IOException e) {
            GlobalFonctions.displayMessages(null, ctx, FacesMessage.SEVERITY_WARN, nameFile, "FILE_WRITTING_FAILED", null);
        } finally {
            try {
                inputStream.close();
                out.flush();
                out.close();
            } catch (IOException ex) {
                logger.error("Erreur Survenu", ex);
            }

        }

        absolutePathImages = currentFolder + "/" + nameFile;
        return absolutePathImages;
    }

    public String save() {
        String success = null;
        FacesContext ctx = FacesContext.getCurrentInstance();

        //get default locale
        Locale myLoc = ctx.getViewRoot().getLocale();
        //Locale myLoc =new Locale("fr");
        String entete[] = {LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "entite.entreprise", null, myLoc)};
        String[] detail = {entete[0], "Parametres"};
        try {
//            employeur.setMatricule(matricule);

//            entreprise = serviceEntreprise.AddEntreprise((List<OrclassModules>) selectedModules, entreprise, debut, fin,OrclassUtilisateurs u);
            if (societe != null && societe.getCodesoci() != null) {
                FacesContext.getCurrentInstance().getExternalContext().getApplicationMap().put(GlobalFonctions.ENTREPRISE_ACTIF, societe);
                success = societe.getRaissoci();

                summary = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "" + exception.Success.OPERATION_SUCCESS.toString(), entete, myLoc);
//            msgdetail = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "detail.update.succes", detail, myLoc);
                ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, summary, null));

            } else {
                summary = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "summary.ajout.error", entete, myLoc);
                msgdetail = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "detail.error", null, myLoc);
                ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, summary, msgdetail));

            }

        } catch (Throwable th) {

            //ecrire dans le fichier de log  
            summary = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "summary.ajout.error", entete, myLoc);
            msgdetail = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "detail.error", null, myLoc);
            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, summary, msgdetail + th.getMessage() + " " + th.getCause() + "-" + th.getClass()));

        }

        return success;
    }

    //getter setter
    private void doRedirect() {
//         String chemin="administration/optionEtab.xhtml";
//      this.doRedirect(chemin);
        FacesContext fc = FacesContext.getCurrentInstance();
        NavigationHandler nh = fc.getApplication().getNavigationHandler();

        nh.handleNavigation(fc, null, "property");

    }

    public String updateEntreprise() {
        FacesContext ctx = FacesContext.getCurrentInstance();
        //get default locale
        Locale myLoc = ctx.getViewRoot().getLocale();
        String entete[] = {LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "entite.entreprise", null, myLoc)};
        try {
            //teste si le pays existe
            if (societe.getCodesoci() != null) {

                societeDao.update(societe);
                String[] detail = {entete[0], societe.getRaissoci()};
                summary = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "summary.update.succes", entete, myLoc);
                msgdetail = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "detail.update.succes", detail, myLoc);
                ctx.getExternalContext().getSessionMap().put(GlobalFonctions.ENTREPRISE_ACTIF, societe);
                ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, summary, msgdetail));
            } else {
                String[] detail = {entete[0], societe.getRaissoci()};
                summary = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "summary.update.succes", entete, myLoc);
                msgdetail = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "detail.update.ErrUpdate", detail, myLoc);
                ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, summary, msgdetail));

            }

        } catch (ConstraintViolationException E) {
            summary = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "summary.update.error", entete, myLoc);
            msgdetail = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "detail.error", null, myLoc);

            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, summary, msgdetail + E.getMessage() + " " + E.getCause()));
            throw E;
        } catch (Throwable th) {
            summary = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "summary.update.error", entete, myLoc);
            msgdetail = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "detail.error", null, myLoc);

            //ecrire dans le fichier de log  
            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, summary, msgdetail + th.getMessage() + " " + th.getCause() + "-" + th.getClass()));

        }
        this.reset();
        return null;
    }

    //supprime un module a une entreprise
    public void deleteModuleforEntreprise() {

        FacesContext ctx = FacesContext.getCurrentInstance();

        //get default locale
        Locale myLoc = ctx.getViewRoot().getLocale();
        //Locale myLoc =new Locale("fr");
        String entete[] = {LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "entite.entreprise", null, myLoc)};
        String[] detail = {entete[0], "Parametres"};
        Collection<OrclassEntreprisesModules> mes = new ArrayList<>();
        OrclassEntreprisesModules me;
        OrclassEntreprisesModulesPK mepk;
        try {

            if (orclassEntreprisesModules.getOrclassEntreprisesModulesPK() != null) {
                orclassEntreprisesModulesDao.delete(orclassEntreprisesModules);
            }

            if (societe != null && societe.getCodesoci() != null) {

                summary = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "" + exception.Success.OPERATION_SUCCESS.toString(), entete, myLoc);

                ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, summary, null));

            } else {
                summary = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "summary.ajout.error", entete, myLoc);
                msgdetail = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "detail.error", null, myLoc);
                ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, summary, msgdetail));

            }

        } catch (Throwable th) {

            //ecrire dans le fichier de log  
            summary = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "summary.ajout.error", entete, myLoc);
            msgdetail = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "detail.error", null, myLoc);
            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, summary, msgdetail + th.getMessage() + " " + th.getCause() + "-" + th.getClass()));

        }
        this.reset();
    }

    public String showDetails(Societe item) {
        if ((societe == null || societe.getCodesoci() == null) && (item != null && item.getCodesoci()!= null)) {

            this.setSociete(item);

//            this.setAbsolutePathImages(item.getChemin_logo());
        }
//        else {
        if (societe.getChemin_logo() != null && societe.getLogosoci() != null) {
            this.setAbsolutePathImages(societe.getChemin_logo());
            File file = new File(this.getAbsolutePathImages());
            if (file.exists() == false) {
                GlobalFonctions.createImageByLibellePhotos(societe.getChemin_logo(), societe.getLogosoci());
            }

        }
        

//        }
        return null;
    }

    public Societe getSociete() {
        return societe;
    }

    public void setSociete(Societe societe) {
        this.societe = societe;
    }

   

    public OrclassModulesDao getOrclassModulesDao() {
        return orclassModulesDao;
    }

    public void setOrclassModulesDao(OrclassModulesDao orclassModulesDao) {
        this.orclassModulesDao = orclassModulesDao;
    }

    public OrclassModules getModules() {
        return modules;
    }

    public void setModules(OrclassModules modules) {
        this.modules = modules;
    }

    public OrclassEntreprisesModulesDao getOrclassEntreprisesModulesDao() {
        return orclassEntreprisesModulesDao;
    }

    public void setOrclassEntreprisesModulesDao(OrclassEntreprisesModulesDao orclassEntreprisesModulesDao) {
        this.orclassEntreprisesModulesDao = orclassEntreprisesModulesDao;
    }

    public OrclassEntreprisesModules getOrclassEntreprisesModules() {
        return orclassEntreprisesModules;
    }

    public void setOrclassEntreprisesModules(OrclassEntreprisesModules orclassEntreprisesModules) {
        this.orclassEntreprisesModules = orclassEntreprisesModules;
    }

    public CurrentInstance getCurrentInstance() {
        return currentInstance;
    }

    public void setCurrentInstance(CurrentInstance currentInstance) {
        this.currentInstance = currentInstance;
    }

    public String getAbsolutePathImages() {
        return absolutePathImages;
    }

    public void setAbsolutePathImages(String absolutePathImages) {
        this.absolutePathImages = absolutePathImages;
    }

    public String getCurrentFolder() {
        return currentFolder;
    }

    public void setCurrentFolder(String currentFolder) {
        this.currentFolder = currentFolder;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public byte[] getFile1() {
        return file1;
    }

    public void setFile1(byte[] file1) {
        this.file1 = file1;
    }

    public Wizard getWizard() {
        return wizard;
    }

    public void setWizard(Wizard wizard) {
        this.wizard = wizard;
    }

    public StreamedContent getLogoimages() {
        return logoimages;
    }

    public void setLogoimages(StreamedContent logoimages) {
        this.logoimages = logoimages;
    }

    public Collection<OrclassModules> getListModules() {
        return listModules;
    }

    public void setListModules(Collection<OrclassModules> listModules) {
        this.listModules = listModules;
    }

    public Collection<OrclassModules> getSelectedModules() {
        return selectedModules;
    }

    public void setSelectedModules(Collection<OrclassModules> selectedModules) {
        this.selectedModules = selectedModules;
    }

    public Date getDebut() {
        return debut;
    }

    public void setDebut(Date debut) {
        this.debut = debut;
    }

    public Date getFin() {
        return fin;
    }

    public void setFin(Date fin) {
        this.fin = fin;
    }

    public PaysDao getPaysDao() {
        return paysDao;
    }

    public void setPaysDao(PaysDao paysDao) {
        this.paysDao = paysDao;
    }

    public List<Societe> getCurrentEntreprise() {
        return currentEntreprise;
    }

    public void setCurrentEntreprise(List<Societe> currentEntreprise) {
        this.currentEntreprise = currentEntreprise;
    }

   
}
