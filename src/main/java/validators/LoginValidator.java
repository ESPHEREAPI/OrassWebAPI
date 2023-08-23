package validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author fabrice
 *Nous allons tacher de valider ou de reconnaitre ce qui pourrait 
 * bien être un login… seulement des minuscules, des chiffres, 
 * l’utilisation de _ ou de -, mais pas de caractères spéciaux 
 * et faisant entre 5 et 15 caractères.
 */
@FacesValidator(value="loginValidator")
public class LoginValidator implements Validator{

    public static String motif="[a-z0-9_-]{5,15}";


    @Override
    public void validate(FacesContext fc, UIComponent uic, Object o) throws ValidatorException {
        String chaine=(String)o.toString();
        Pattern mask=null;
        mask=Pattern.compile(motif);
        Matcher matcher=mask.matcher(chaine);
        if(!matcher.matches()){
            FacesMessage message=new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid Login", "Invalid Login");
            
            throw new ValidatorException(message);
        }
    }
  
}
