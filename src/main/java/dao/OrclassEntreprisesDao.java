/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import modele.OrclassEntreprises;

/**
 *
 * @author hp
 */
@Stateless
public class OrclassEntreprisesDao  extends AbstractJpaDAO<OrclassEntreprises>{
   private static final long serialVersionUID = 1L;
    @PersistenceContext(unitName = AbstractJpaDAO.PersistanceUnit)
    private EntityManager em;
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
    protected Class<OrclassEntreprises> getEntityClass() {
        return OrclassEntreprises.class;
    }

    @Override
    protected EntityManager getEntityManager() {
        
       return em;
    
    }
     public OrclassEntreprises finkey(String code){
        Query q;
        
    q=em.createQuery("SELECT e From OrclassEntreprises e where e.code= :code ")
            .setParameter("code", code);
    if(q.getResultList().isEmpty()==Boolean.TRUE){
        return null;
    }
    return (OrclassEntreprises) q.getResultList().toArray()[0];
    }

}
