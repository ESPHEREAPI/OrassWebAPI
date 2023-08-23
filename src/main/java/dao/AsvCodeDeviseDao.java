/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package dao;

import java.util.HashMap;
import java.util.Map;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import modele.AsvCodeDevise;
import modele.Societe;

/**
 *
 * @author JIATOU FRANCK
 */
@Stateless
public class AsvCodeDeviseDao extends AbstractJpaDAO<AsvCodeDevise> {
   private static final long serialVersionUID = 1L;
    @PersistenceContext(unitName = AbstractJpaDAO.PersistanceUnit)
    private EntityManager em;
    @Override
    protected Class<AsvCodeDevise> getEntityClass() {
    return AsvCodeDevise.class;
    }

    @Override
    protected EntityManager getEntityManager() {
   return em;
    }
    
    public AsvCodeDevise getDeviByCode(String code) {
        Map<String, Object> param = new HashMap<>();
        param.put("codedevi", code);

        return super.findEntityByUsingQueryName(AsvCodeDevise.FIND_BY_CODE_DEVI, param);
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
