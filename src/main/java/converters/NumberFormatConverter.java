package converters;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Fabrice
 */
@FacesConverter("numberFormatConverter")
public class NumberFormatConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
       throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        Double valeur = new Double(value.toString());
        
        DecimalFormat format = new DecimalFormat(); 
        DecimalFormatSymbols s = format.getDecimalFormatSymbols();
        s.setGroupingSeparator(' ');
        format.setDecimalFormatSymbols(s);
       
        return format.format(valeur);
    }
    
}
