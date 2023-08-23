/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.administration;

import static com.sun.org.apache.xerces.internal.impl.io.ASCIIReader.DEFAULT_BUFFER_SIZE;

import dao.OrclassMenusDao;

import dao.OrclassModulesDao;
import dao.SeverMessagerieDao;
import dao.SocieteDao;
import droitAcces.IDroitAcces;
import enums.Actions;
import enums.FonctionnaliteModuleAdministration;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.Locale;
import java.util.Objects;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.validation.ConstraintViolationException;
import modele.OrclassActions;
import modele.OrclassEntreprises;
import modele.OrclassFonctionnalites;
import modele.OrclassMenus;
import modele.OrclassModules;
import modele.OrclassUtilisateurs;
import modele.Societe;
import org.primefaces.PrimeFaces;
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
 * @author JIATOU FRANCK
 */
@Named(value = "optionEntrepriseController")
@ViewScoped
public class OptionEntrepriseController implements Serializable {

    private static final Logger logger = LoggerFactory.getLogger(OptionEntrepriseController.class);

    @EJB
    private SocieteDao societeDao;
    private Societe societe;
    @EJB
   SeverMessagerieDao severMessagerieDao;

    private String absolutePathImages;


    private String currentFolder = "/photos";
    private String imagePath;
    String summary = "";
    String msgdetail = "";
    private byte[] file1;
    private Wizard wizard;
    private StreamedContent logoimages;

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

    /**
     * Creates a new instance of OptionEntrepriseController
     */
    public OptionEntrepriseController() {

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

    @PostConstruct
    void initialiseSession() {
        FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        societe = (Societe) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(GlobalFonctions.ENTREPRISE_ACTIF);

        menu = menusDao.findEntityHavingValue("code", "entite.entreprise");
        module = modulesDao.findEntityHavingValue("code", "mod.admin");
        user = (OrclassUtilisateurs) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(GlobalFonctions.SESSION_USER);
//        PrimeFaces.current().ajax().update(":form1:accord");

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
//    public void chargeChoicNotification(){
//        if (Objects.equals(entreprise.getEnvoiMail(), Boolean.TRUE)) {
//            entreprise.setServiceMicrosoft(Boolean.FALSE);
//        }else if(Objects.equals(entreprise.getServiceMicrosoft(), Boolean.TRUE)){
//            entreprise.setEnvoiMail(Boolean.FALSE);
//           
//        }
//    }
    
     public String updateEntreprise() {
        FacesContext ctx = FacesContext.getCurrentInstance();
        //get default locale
        Locale myLoc = ctx.getViewRoot().getLocale();
        String entete[] = {LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "entite.entreprise", null, myLoc)};
        try {
            //teste si le pays existe
            if (societe.getCodesoci()!= null) {

                societeDao.update(societe);
                String[] detail = {entete[0], societe.getRaissoci()};
                summary = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "summary.update.succes", entete, myLoc);
                msgdetail = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "detail.update.succes", detail, myLoc);
                ctx.getExternalContext().getSessionMap().put(GlobalFonctions.ENTREPRISE_ACTIF, societe);
                ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, summary, msgdetail));
            } else {
                String[] detail = {entete[0],societe.getRaissoci()};
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
    
        return null;
    }

    public Societe getSociete() {
        return societe;
    }

    public void setSociete(Societe societe) {
        this.societe = societe;
    }

    public OrclassUtilisateurs getUser() {
        return user;
    }

    public void setUser(OrclassUtilisateurs user) {
        this.user = user;
    }

    public OrclassUtilisateurs getUtilisateurs() {
        return utilisateurs;
    }

    public void setUtilisateurs(OrclassUtilisateurs utilisateurs) {
        this.utilisateurs = utilisateurs;
    }
     
     


    public String getAbsolutePathImages() {
        return absolutePathImages;
    }

    public void setAbsolutePathImages(String absolutePathImages) {
        this.absolutePathImages = absolutePathImages;
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

    public StreamedContent getLogoimages() {
        return logoimages;
    }

    public void setLogoimages(StreamedContent logoimages) {
        this.logoimages = logoimages;
    }

    public SeverMessagerieDao getSeverMessagerieDao() {
        return severMessagerieDao;
    }

    public void setSeverMessagerieDao(SeverMessagerieDao severMessagerieDao) {
        this.severMessagerieDao = severMessagerieDao;
    }

    
    
    


}
