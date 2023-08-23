/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author fabrice
 */
@FacesConverter(value="upperConverter")
public class UpperConverter implements Converter{

         @Override
        public Object getAsObject(FacesContext fc, UIComponent uic, String val) {
        throw new UnsupportedOperationException("Not supported yet.");
        }
        //convertir en string
        @Override
        public String getAsString(FacesContext fc, UIComponent uic, Object value) {
            
      return value.toString().toUpperCase();  
        }
        
    }

