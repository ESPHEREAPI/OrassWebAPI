/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import enums.Actions;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import modele.OrclassActions;
import modele.OrclassFonctionnalites;
import modele.OrclassMenus;
import modele.OrclassModules;
import utils.FonctionTable;

/**
 *
 * @author hp
 */
@Stateless
public class OrclassActionsDao extends AbstractJpaDAO<OrclassActions> {

    private static final long serialVersionUID = 1L;
    @PersistenceContext(unitName = AbstractJpaDAO.PersistanceUnit)
    private EntityManager em;
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
    protected Class<OrclassActions> getEntityClass() {
        return OrclassActions.class;
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public OrclassActions finkey(OrclassFonctionnalites f, OrclassModules m, String action) {
        Query q;
        q = em.createQuery("SELECT a FROM OrclassActions a WHERE a.idFonctionnalite.idFonctionnalite= :idFonctionnalite AND a.idFonctionnalite.idModule.idModule= :idModule and a.libelle= :action ")
                .setParameter("idFonctionnalite", f.getIdFonctionnalite())
                .setParameter("idModule", m.getIdModule())
                .setParameter("action", action);
        if (q.getResultList().isEmpty()) {
            return null;
        }
        System.out.println("action sortie:"+action +" return "+(OrclassActions) q.getResultList().toArray()[0]);
        return (OrclassActions) q.getResultList().toArray()[0];
    }

    public List<OrclassActions> getAllactionByFonctionnalite(OrclassFonctionnalites f, OrclassModules m) {
        Query q;
        q = em.createQuery("SELECT a FROM OrclassActions a where a.idFonctionnalite.idFonctionnalite= :idFonctionnalite and a.idFonctionnalite.idModule.idModule= :idModule")
                .setParameter("idFonctionnalite", f.getIdFonctionnalite())
                .setParameter("idModule", m.getIdModule());
        return q.getResultList();

    }

    public List<OrclassActions> getAllactionByFonctionnaliteForUserAccess(OrclassFonctionnalites f, OrclassModules m) {
        Query q;
        q = em.createQuery("SELECT DISTINCT ua.idAction  FROM OrclassMenusAcces ua WHERE ua.idFonctionnalite.idFonctionnalite = :idFonctionnalite and ua.idFonctionnalite.idModule.idModule= :idModule ")
                .setParameter("idFonctionnalite", f.getIdFonctionnalite())
                .setParameter("idModule", m.getIdModule());
        
        return q.getResultList();
        

    }
    
     public List<OrclassActions> getAllactionByFonctionnaliteForUserAccess(OrclassFonctionnalites f, OrclassModules m,OrclassMenus me) {
        Query q;
        q = em.createQuery("SELECT DISTINCT ua.idAction  FROM OrclassMenusAcces ua WHERE ua.idFonctionnalite.idFonctionnalite = :idFonctionnalite and ua.idFonctionnalite.idModule.idModule= :idModule and ua.idMenu= :me ")
                .setParameter("idFonctionnalite", f.getIdFonctionnalite())
                 .setParameter("me", me)
                .setParameter("idModule", m.getIdModule());
        
        return q.getResultList();
        

    }
     
     public List<OrclassActions> getAllactionByFonctionnaliteForUserAccess( OrclassModules m) {
        Query q;
        q = em.createQuery("SELECT DISTINCT ua.idAction  FROM OrclassMenusAcces ua WHERE  ua.idFonctionnalite.idModule.idModule= :idModule ")
//                .setParameter("idFonctionnalite", f.getIdFonctionnalite())
//                 .setParameter("me", me)
                .setParameter("idModule", m.getIdModule());
        
        return q.getResultList();
        

    }
    
    /*
    retourner la liste des action n ayant pas une fonctionnalite 
    */
    public List<String> getAllTypeActionNotHaveFonctionnalite(OrclassMenus menu,OrclassFonctionnalites f){
        Query q;
        q=em.createQuery("SELECT DISTINCT a.code  FROM OrclassActions a WHERE NOT EXISTS ( select ma.idAction.code from OrclassMenusAcces ma  where ma.idFonctionnalite.idFonctionnalite= :idFonctionnalite and ma.idMenu.idMenu= :idMenu) ")
                .setParameter("idFonctionnalite", f.getIdFonctionnalite())
                .setParameter("idMenu", menu.getIdMenu());
        return q.getResultList();
    }
    

}
