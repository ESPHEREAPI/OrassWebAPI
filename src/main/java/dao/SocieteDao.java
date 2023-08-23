/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import modele.Societe;

/**
 *
 * @author hp
 */
@Stateless
public class SocieteDao extends AbstractJpaDAO<Societe> {

    private static final long serialVersionUID = 1L;
    @PersistenceContext(unitName = AbstractJpaDAO.PersistanceUnit)
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public Societe getSocieteByCode(Short code) {
        Map<String, Object> param = new HashMap<>();
        param.put("codesoci", code);

        return super.findEntityByUsingQueryName(Societe.FIND_BY_CODESOCI, param);
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Override
    protected Class<Societe> getEntityClass() {
        return Societe.class;
    }
    
    public List<Short> getSocieteCodeWithFilters( String Filter) {

        Map<String, Object> param = new HashMap<>();

        Query q = null;

        //si le filtre existe
        String request = "SELECT s.codesoci FROM Societe s  WHERE";
//        Query q;
        if (Filter != null ) {
            request += " s.codesoci like :filter ";

            q = em.createQuery(request)
                    
                    .setParameter("filter", Short.parseShort(Filter)) ;

        }

        return q.getResultList();

    }
    
        public List<String> getSocieteRaisonWithFilters( String Filter) {

        Map<String, Object> param = new HashMap<>();

        Query q = null;

        //si le filtre existe
        String request = "SELECT s.raissoci FROM Societe s  WHERE";
//        Query q;
        if (Filter != null && !Filter.trim().equals("")) {
            request += " s.raissoci like :filter ";

            q = em.createQuery(request)
                    
                    .setParameter("filter", "%" + Filter + "%") ;

        }

        return q.getResultList();

    }
}
