/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import modele.Annee;

/**
 *
 * @author JEANNE
 */
@Stateless
public class AnneeDao extends AbstractJpaDAO<Annee> {

    private static final long serialVersionUID = 1L;
    @PersistenceContext(unitName = AbstractJpaDAO.PersistanceUnit)
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    @Override
    protected Class<Annee> getEntityClass() {
        return Annee.class;
    }

    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public Collection<Annee> getAllannee() {
        return super.findAll();
    }
    
    public Collection<Annee> getAnneeIsEtabInactif(){
        Map<String,Object> params=new HashMap<>();
        params.put("bool", Boolean.FALSE);
        String sql="select a from Annee a join a.etablissements etab where etab.actif= :bool";
        return super.findAllEntitiesByUsingQuery(sql, params);
    }
     /*
    retourne les annees ayant u la paie
    */
    public Collection<Annee> getAnneeHavePaie(){
        Query q;
     q=em.createQuery("SELECT b.salarier.entreprise.annee FROM Bulletin b GROUP BY b.salarier.entreprise.annee.Id");
     return q.getResultList();
        
    }
}
