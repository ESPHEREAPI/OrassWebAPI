/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ejb.Stateless;
import javax.persistence.Query;
import modele.OrclassEntreprises;
import modele.OrclassProfils;
import modele.OrclassProfilsAcces;
import modele.Property;
/**
 *
 * @author KAMDEM
 */
@Stateless
public class PropertyDao extends AbstractJpaDAO<Property>{
   /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
@PersistenceContext(unitName=AbstractJpaDAO.PersistanceUnit)
    private EntityManager em;

  

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    @Override
    protected Class<Property> getEntityClass() {
        return Property.class;
    }
    
    
}
