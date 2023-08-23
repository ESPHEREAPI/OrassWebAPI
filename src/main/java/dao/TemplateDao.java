/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import modele.OrclassEntreprises;
import modele.Templates;

/**
 *
 * @author hp
 */
@Stateless
public class TemplateDao extends AbstractJpaDAO<Templates> {
    
 private static final long serialVersionUID = 1L;
    @PersistenceContext(unitName = AbstractJpaDAO.PersistanceUnit)
    private EntityManager em;
    @Override
    protected Class<Templates> getEntityClass() {
        return Templates.class;
    }

    @Override
    protected EntityManager getEntityManager() {
       return em;
    }
    
    public Templates selectTemplateByDefault(){
        Query q;
        q=em.createQuery("SELECT t FROM Templates t where t.defautTemplate= :valeur")
                .setParameter("valeur", Boolean.TRUE);
        if (q.getResultList().isEmpty()) {
            return null;
            
        }
        return (Templates) q.getResultList().toArray()[0];
    }
    public Templates getlastTempla( OrclassEntreprises e){
        Query q;
        q=em.createQuery("select t from Templates t WHERE t.idEntreprise.idEntreprise= :idEntreprise order By t.id DESC")
                .setParameter("idEntreprise", e.getIdEntreprise());
        if (q.getResultList().isEmpty()) {
            return null;
        }
        return (Templates) q.getResultList().toArray()[0];
    }
    
     public List <Templates> getlisttTemplaByEntreprise( OrclassEntreprises e){
        Query q;
        q=em.createQuery("select t from Templates t WHERE t.idEntreprise.idEntreprise= :idEntreprise ")
                .setParameter("idEntreprise", e.getIdEntreprise());
        if (q.getResultList().isEmpty()) {
            return null;
        }
        return q.getResultList();
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
