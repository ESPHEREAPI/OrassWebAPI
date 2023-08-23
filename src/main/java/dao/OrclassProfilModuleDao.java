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
import modele.OrclassModules;
import modele.OrclassProfilModule;
import modele.OrclassProfils;

/**
 *
 * @author hp
 */
@Stateless
public class OrclassProfilModuleDao extends AbstractJpaDAO<OrclassProfilModule> {
  private static final long serialVersionUID = 1L;
    @PersistenceContext(unitName = AbstractJpaDAO.PersistanceUnit)
    private EntityManager em;
    @Override
    protected Class<OrclassProfilModule> getEntityClass() {
     return OrclassProfilModule.class;
   
    
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    public OrclassProfilModule finkey(OrclassProfils  p,OrclassModules m){
        Query q;
        q=em.createQuery("SELECT pm FROM OrclassProfilModule pm WHERE pm.idModule.idModule= :idModule  and pm.idProfil.idProfil= :idProfil ")
                .setParameter("idModule", m.getIdModule())
                .setParameter("idProfil", p.getIdProfil())
                ;
        if (q.getResultList().isEmpty()) {
            return null;
        }
        return (OrclassProfilModule) q.getResultList().toArray()[0];
    }
}
