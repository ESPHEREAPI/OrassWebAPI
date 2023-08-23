package validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author fabrice
 */
@FacesValidator(value = "emailValidator")
public class EmailValidator implements Validator {

//    public static String emailReg="(\\w+)@(\\w+\\.)(\\w+)(\\.\\w+)*";
// public static String emailReg= " ^[_a-z0-9-]+(\\.[_a-z0-9-]+)*@[a-z0-9-]+(\\.[a-z0-9-]+)+$";
    String emailReg = "^(.+)@(.+)$";

    @Override
    public void validate(FacesContext fc, UIComponent uic, Object o) throws ValidatorException {
        String email = "";
        if (o != null && !o.toString().trim().equals("")) {
            email = o.toString();

            Pattern mask = null;
            mask = Pattern.compile(emailReg);
            Matcher matcher = mask.matcher(email);
            if (!matcher.matches()) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid Email", "Invalid Email");

                throw new ValidatorException(message);
            }
        }
    }

}
