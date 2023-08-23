package converters;

import utils.LocaleHelper;
import utils.RecupBundle;
import java.util.Locale;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author fabrice
 */
@FacesConverter(value="booleanConverter")
public class BooleanConverter implements Converter{
   @Override
        public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
            throw new UnsupportedOperationException("Not supported yet.");
        }
        //convertir en string
        @Override
        public String getAsString(FacesContext fc, UIComponent uic, Object value) {
            FacesContext ctx = FacesContext.getCurrentInstance();
            Locale myLoc = ctx.getViewRoot().getLocale();
             String yes = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "oui", null, myLoc);
             String no = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "non", null, myLoc);
           return value.equals(true)?yes:no;    
        }   
}
