///*
// * To change this template, choose Tools | Templates
// * and open the template in the editor.
// */
//package validators;
//
//import java.util.Collection;
//import java.util.Locale;
//import javax.faces.application.FacesMessage;
//import javax.faces.component.UIComponent;
//import javax.faces.context.FacesContext;
//import javax.faces.validator.FacesValidator;
//import javax.faces.validator.Validator;
//import javax.faces.validator.ValidatorException;
//import utils.LocaleHelper;
//import utils.RecupBundle;
////import utils.LocaleHelper;
////import utils.RecupBundle;
//
///**
// *
// * @author fabrice
// */
//@FacesValidator(value="collectionValidator")
//public class CollectionValidator implements Validator{
//
//    @Override
//    public void validate(FacesContext fc, UIComponent uic, Collection<Object> o) throws ValidatorException {
//       
//            Locale myLoc = fc.getViewRoot().getLocale();
//             String msgRequired = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "required.message",new Object[]{uic.getId()}, myLoc);
//            String entete = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "champ.obligatoire",null, myLoc);
//        
//       
//        if(o == null || o.equals("")){
//        FacesMessage message=new FacesMessage(FacesMessage.SEVERITY_ERROR,msgRequired,null);
//            
//            throw new ValidatorException(message);
//        }
//        }
//
//    @Override
//    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//    }
//
