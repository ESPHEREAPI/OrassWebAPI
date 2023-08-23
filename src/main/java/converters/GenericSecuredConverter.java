
package converters;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

/**
 *
 * @author fabrice
 */
@ManagedBean(name="genericConverter")
@ViewScoped
//@FacesConverter(value="genericConverter")
public class GenericSecuredConverter implements Converter, Serializable {
 
    private Map <UUID, Object> temporaryStore = new HashMap <UUID, Object> ();
 
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) throws ConverterException {
        return temporaryStore.get(UUID.fromString(value));
    }
 
    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) throws ConverterException {
        UUID id = UUID.randomUUID();
        temporaryStore.put(id, value);
        return id.toString();
        
    }
}