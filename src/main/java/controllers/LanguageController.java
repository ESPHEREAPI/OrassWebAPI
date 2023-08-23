package controllers;

/**
 *
 * @author NMS
 */
import java.io.Serializable;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import javax.ejb.EJB;
import javax.faces.bean.ManagedProperty;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Inject;
import javax.inject.Named;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import parametrage.IParamModule;

@Named(value = "languageController")
@SessionScoped
public class LanguageController implements Serializable {

    @EJB
    IParamModule paramModule;
    private static final long serialVersionUID = 1L;
    Logger logger = LoggerFactory.getLogger(LanguageController.class);
    @Inject

    private SecurityController secuController;
    private String localeCode;
    private static Map<String, Object> countries;
//     static {
//        countries.put("English", new Locale("en"));
//        countries.put("French", new Locale("fr"));
//    }

    public LanguageController() {
        countries = new HashMap<>();
    }

    public Map<String, Object> getCountriesInMap() {
        if (countries == null || countries.isEmpty()) {
            countries = new HashMap<>();
            countries.put("French", new Locale("fr"));
            countries.put("English", new Locale("en"));
            countries.put("Espagnol", new Locale("es"));

        }
        return countries;
    }

    public String getLocaleCode() {
        if (localeCode == null) {
            if (localeCode == null) {
             localeCode = System.getProperty("user.language");
//                localeCode = "en";
            }
//            controleLanguageExiste();
//            localeCode = "fr";
            Locale lc = new Locale(localeCode);
//            FacesContext.getCurrentInstance().getExternalContext().getSession(true);
//            FacesContext.getCurrentInstance().getViewRoot().setLocale(lc);
//            FacesContext.getCurrentInstance().getApplication().setDefaultLocale(lc);
            setLocaleCode(localeCode);
            secuController.setMyLoc(lc);
            secuController.chargeModule();
        } else if (localeCode != null && secuController.getModule().getElements().isEmpty()) {
            Locale lc = new Locale(localeCode);
//            secuController.setMyLoc(lc);
//            secuController.chargeModule();
        }
        return localeCode;
    }

    public String controleLanguageExiste() {
        if (localeCode.equals("fr")) {
            return "fr";
        }
        if (localeCode.equals("en")) {
            return "en";
        }
        if (localeCode.equals("es")) {
            return "es";
        }
        return "fr";
    }

    public void setLocaleCode(String localeCode) {
        this.localeCode = localeCode;
    }

    //value change event listener
    public void countryLocaleCodeChanged(ValueChangeEvent e) {

        String newLocaleValue = e.getNewValue().toString();
        System.out.println("locale:" + newLocaleValue);
        //loop country map to compare the locale code
        for (Map.Entry<String, Object> entry : countries.entrySet()) {

            if (entry.getValue().toString().equals(newLocaleValue)) {
                Locale lc = new Locale(newLocaleValue);
                FacesContext.getCurrentInstance().getViewRoot().setLocale(lc);
                FacesContext.getCurrentInstance().getApplication().setDefaultLocale(lc);
                setLocaleCode(newLocaleValue);

//                secuController.chargeModule();

            }
        }
    }

    public SecurityController getSecuController() {
        return secuController;
    }

    public void setSecuController(SecurityController secuController) {
        this.secuController = secuController;
    }
}
