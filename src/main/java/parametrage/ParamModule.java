/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package parametrage;

import dao.PropertyDao;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import modele.Property;

/**
 *
 * @author Frankjiatou
 */
//@Singleton
@Stateless
public class ParamModule implements IParamModule{

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    
   // private Map<Long,Object> cacheProperty = new HashMap<Long, Object>();
    @EJB
    PropertyDao propertyDao;
    
    Property prop;
    
    public ParamModule() {
        prop=new Property();
    }
/*
 * *permet d'initialiser l'ensemble des proprietes
 */
    @PostConstruct
    @Override
    public void initProperty(){
        prop=new Property();
       //je cherche si il y'a deja eu un parametrage
        Collection<Property> colProperty=new ArrayList<Property>();
        colProperty=propertyDao.findAll();
        if(colProperty==null || colProperty.isEmpty()==true){
            //je mets les proprietes par defaut
            //module inscription
            prop.setMatriculeAuto(false);
           
            //module payement         
            propertyDao.create(prop);
        }
    }

  

    @Override
    public void update(Property p) {
        propertyDao.update(p);
    }
    @Override
    public Property getParamModule() {
        Property p=new Property();
        //je verifie qu'il est une seule ligne
        if(propertyDao.count()==1){
      
               p= (Property) propertyDao.findAll().toArray()[0];
        }
       return p;
    }
    
   

    

    
    
}
