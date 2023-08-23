/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.primefaces.rain.view;

import dao.TemplateDao;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import modele.OrclassEntreprises;
import modele.Templates;
import utils.GlobalFonctions;

/**
 *
 * @author hp
 */
@Named(value = "guestPreferences")
@SessionScoped
//@ViewScoped
public class GuestPreferences implements Serializable{

    @EJB
    TemplateDao templateDao;
    private Templates choix_template;
    private String menuMode = "layout-static layout-static-active";

    private String darkMode = "light";

    private String layoutPrimaryColor = "cyan";

    private String componentTheme = "cyan";

    private String topbarTheme = "colored";

    private String menuTheme = "dim";

    private String profileMode = "popup";

    private String inputStyle = "outlined";
//      private String inputStyle = "inline";

//    private boolean groupedMenu = true;
    private boolean groupedMenu = false;

//    private boolean lightLogo = true;
    private boolean lightLogo = false;

    private List<ComponentTheme> componentThemes = new ArrayList<ComponentTheme>();

    private List<LayoutPrimaryColor> layoutPrimaryColors = new ArrayList<LayoutPrimaryColor>();
    private String message = "";
    private OrclassEntreprises entreprise=null;

    @PostConstruct
    public void init() {
        
        componentThemes.add(new ComponentTheme("Blue", "blue", "#2c84d8"));
        componentThemes.add(new ComponentTheme("Wisteria", "wisteria", "#A864AE"));
        componentThemes.add(new ComponentTheme("Cyan", "cyan", "#25A4D4"));
        componentThemes.add(new ComponentTheme("Amber", "amber", "#DB8519"));
        componentThemes.add(new ComponentTheme("Pink", "pink", "#F5487F"));
        componentThemes.add(new ComponentTheme("Orange", "orange", "#CB623A"));
        componentThemes.add(new ComponentTheme("Victoria", "victoria", "#594791"));
        componentThemes.add(new ComponentTheme("Chateau Green", "chateau-green", "#3C9462"));
        componentThemes.add(new ComponentTheme("Paradiso", "paradiso", "#3B9195"));
        componentThemes.add(new ComponentTheme("Chambray", "chambray", "#3161BA"));
        componentThemes.add(new ComponentTheme("Tapestry", "tapestry", "#A2527F"));

        layoutPrimaryColors.add(new LayoutPrimaryColor("Blue", "blue", "#2c84d8"));
        layoutPrimaryColors.add(new LayoutPrimaryColor("Wisteria", "wisteria", "#A053A7"));
        layoutPrimaryColors.add(new LayoutPrimaryColor("Cyan", "cyan", "#25A4D4"));
        layoutPrimaryColors.add(new LayoutPrimaryColor("Amber", "amber", "#DB8519"));
        layoutPrimaryColors.add(new LayoutPrimaryColor("Pink", "pink", "#F5487F"));
        layoutPrimaryColors.add(new LayoutPrimaryColor("Orange", "orange", "#CB623A"));
        layoutPrimaryColors.add(new LayoutPrimaryColor("Victoria", "victoria", "#705BB1"));
        layoutPrimaryColors.add(new LayoutPrimaryColor("Chateau Green", "chateau-green", "#3C9462"));
        layoutPrimaryColors.add(new LayoutPrimaryColor("Paradiso", "paradiso", "#3B9195"));
        layoutPrimaryColors.add(new LayoutPrimaryColor("Chambray", "chambray", "#3161BA"));
        layoutPrimaryColors.add(new LayoutPrimaryColor("Tapestry", "tapestry", "#924470"));
        saveDefautTemplate();

    }

    public void saveDefautTemplate() {
        if ((templateDao.findAll() == null || templateDao.findAll().isEmpty() == Boolean.TRUE) ) {
            choix_template = new Templates();
            choix_template.setComponentTheme(componentTheme);
            choix_template.setDarkMode(darkMode);
            choix_template.setGroupedMenu(groupedMenu);
            choix_template.setDefautTemplate(Boolean.TRUE);
            choix_template.setInputStyle(inputStyle);
            choix_template.setLayoutPrimaryColor(layoutPrimaryColor);
            choix_template.setLightLogo(lightLogo);
            choix_template.setMenuTheme(menuTheme);
            choix_template.setProfileMode(profileMode);
            choix_template.setTopbarTheme(topbarTheme);
            choix_template.setMenuMode(menuMode);
            templateDao.create(choix_template);
        } else if(this.getEntreprise()!=null && this.getEntreprise().getIdEntreprise()!=null) {
            /*
            A REFAIRE LA PENSEE
            */
//            choix_template = templateDao.getlastTempla(entreprise);
//            this.setComponentTheme(choix_template.getComponentTheme());
//            this.setDarkMode(choix_template.getDarkMode());
//            this.setGroupedMenu(choix_template.getGroupedMenu());
//            this.setInputStyle(choix_template.getInputStyle());
//            this.setLayoutPrimaryColor(choix_template.getLayoutPrimaryColor());
//            this.setMenuMode(choix_template.getMenuMode());
//            this.setMenuTheme(choix_template.getMenuTheme());
//            this.setProfileMode(choix_template.getProfileMode());
//            this.setTopbarTheme(choix_template.getTopbarTheme());
//            this.setMenuMode(choix_template.getMenuMode());
            
        }
    }

    public void chargeDeafautTemplate() {
        FacesContext ctx = FacesContext.getCurrentInstance();
        ExternalContext etx = ctx.getExternalContext();
        String chemin = etx.getRequestContextPath();
        //get default locale
        Locale myLoc = ctx.getViewRoot().getLocale();
        Templates tem = null;
        choix_template = templateDao.selectTemplateByDefault();
        this.setComponentTheme(choix_template.getComponentTheme());
        this.setDarkMode(choix_template.getDarkMode());
        this.setGroupedMenu(choix_template.getGroupedMenu());
        this.setInputStyle(choix_template.getInputStyle());
        this.setLayoutPrimaryColor(choix_template.getLayoutPrimaryColor());
        this.setMenuMode(choix_template.getMenuMode());
        this.setMenuTheme(choix_template.getMenuTheme());
        this.setProfileMode(choix_template.getProfileMode());
        this.setTopbarTheme(choix_template.getTopbarTheme());
        this.setMenuMode(choix_template.getMenuMode());
        for (Templates t : templateDao.findAll()) {
            if (t.equals(choix_template)) {
                continue;
            }
            tem = t;
            if (t.getIdEntreprise().getIdEntreprise().equals(entreprise.getIdEntreprise())) {
                 templateDao.delete(t);
            }
           

        }
        doRedirect(chemin+"/accueil.xhtml");

    }

    private void doRedirect(String url) {
        try {

            FacesContext context = FacesContext.getCurrentInstance();

            context.getExternalContext().redirect(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void savetemplate() {
        FacesContext ctx = FacesContext.getCurrentInstance();

        //get default locale
        Locale myLoc = ctx.getViewRoot().getLocale();
        choix_template = new Templates();
        choix_template.setComponentTheme(componentTheme);
        choix_template.setDarkMode(darkMode);
        choix_template.setGroupedMenu(groupedMenu);
        choix_template.setDefautTemplate(Boolean.FALSE);
        choix_template.setInputStyle(inputStyle);
        choix_template.setLayoutPrimaryColor(layoutPrimaryColor);
        choix_template.setLightLogo(lightLogo);
        choix_template.setMenuTheme(menuTheme);
        choix_template.setProfileMode(profileMode);
        choix_template.setTopbarTheme(topbarTheme);
        choix_template.setMenuMode(menuMode);
        choix_template.setIdEntreprise(this.getEntreprise());
        templateDao.create(choix_template);
        message = exception.Success.OPERATION_SUCCESS.toString();
        ctx.addMessage("msg1", new FacesMessage(FacesMessage.SEVERITY_INFO, exception.Success.OPERATION_SUCCESS.toString(), "VALIDE"));
    }

    public String getDarkMode() {
        return darkMode;
    }

    public boolean isLightLogo() {
        return lightLogo;
    }

    public void setDarkMode(String darkMode) {
        this.darkMode = darkMode;
        this.menuTheme = darkMode;
        this.topbarTheme = darkMode;
        this.lightLogo = !this.topbarTheme.equals("light");
    }

    public String getLayout() {
        return "layout-" + this.layoutPrimaryColor + '-' + this.darkMode;
    }

    public String getTheme() {
        return this.componentTheme + '-' + this.darkMode;
    }

    public String getLayoutPrimaryColor() {
        return layoutPrimaryColor;
    }

    public void setLayoutPrimaryColor(String layoutPrimaryColor) {
        this.layoutPrimaryColor = layoutPrimaryColor;
        this.componentTheme = layoutPrimaryColor;
    }

    public String getComponentTheme() {
        return componentTheme;
    }

    public void setComponentTheme(String componentTheme) {
        this.componentTheme = componentTheme;
    }

    public String getMenuTheme() {
        return menuTheme;
    }

    public void setMenuTheme(String menuTheme) {
        this.menuTheme = menuTheme;
    }

    public String getTopbarTheme() {
        return topbarTheme;
    }

    public void setTopbarTheme(String topbarTheme) {
        this.topbarTheme = topbarTheme;
        this.lightLogo = !this.topbarTheme.equals("light");
    }

    public String getMenuMode() {
        return this.menuMode;
    }

    public void setMenuMode(String menuMode) {
        this.menuMode = menuMode;
    }

    public boolean isGroupedMenu() {
        return this.groupedMenu;
    }

    public void setGroupedMenu(boolean value) {
        this.groupedMenu = value;
    }

    public String getProfileMode() {
        return this.profileMode;
    }

    public void setProfileMode(String profileMode) {
        this.profileMode = profileMode;
    }

    public String getInputStyle() {
        return inputStyle;
    }

    public void setInputStyle(String inputStyle) {
        this.inputStyle = inputStyle;
    }

    public String getInputStyleClass() {
        return this.inputStyle.equals("filled") ? "ui-input-filled" : "";
    }

    public List<ComponentTheme> getComponentThemes() {
        return componentThemes;
    }

    public class ComponentTheme {

        String name;
        String file;
        String color;

        public ComponentTheme(String name, String file, String color) {
            this.name = name;
            this.file = file;
            this.color = color;
        }

        public String getName() {
            return this.name;
        }

        public String getFile() {
            return this.file;
        }

        public String getColor() {
            return this.color;
        }
    }

    public List<LayoutPrimaryColor> getLayoutPrimaryColors() {
        return layoutPrimaryColors;
    }

    public class LayoutPrimaryColor {

        String name;
        String file;
        String color;

        public LayoutPrimaryColor(String name, String file, String color) {
            this.name = name;
            this.file = file;
            this.color = color;
        }

        public String getName() {
            return this.name;
        }

        public String getFile() {
            return this.file;
        }

        public String getColor() {
            return this.color;
        }
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public OrclassEntreprises getEntreprise() {
         FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        entreprise = (OrclassEntreprises) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(GlobalFonctions.ENTREPRISE_ACTIF);
        if (entreprise == null) {
            entreprise = (OrclassEntreprises) FacesContext.getCurrentInstance().getExternalContext().getApplicationMap().get(GlobalFonctions.ENTREPRISE_ACTIF);
            
        }
        return entreprise;
    }

    public void setEntreprise(OrclassEntreprises entreprise) {
        this.entreprise = entreprise;
    }
    
    
    
}
