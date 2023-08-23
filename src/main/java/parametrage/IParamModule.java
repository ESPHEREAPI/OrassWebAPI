/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package parametrage;

import java.math.BigDecimal;
import javax.ejb.Local;
import modele.Property;

/**
 *
 * @author Frankjiatou
 */
@Local
public interface IParamModule {
     //recuperer toutes les proprietes
    public Property getParamModule();
        
    //modifier une property
    public void update(Property p);
    
    //initialiser le remplissage
      public void initProperty();
      /*
      recuperation de la valeur de l indice renumeration mensuel
      */
     
}
