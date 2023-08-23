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
import modele.Ville;

/**
 *
 * @author JIATOU FRANCK
 */
@Stateless
public class VilleDao extends AbstractJpaDAO<Ville> {

    private static final long serialVersionUID = 1L;
    @PersistenceContext(unitName = AbstractJpaDAO.PersistanceUnit)
    private EntityManager em;

    @Override
    protected Class<Ville> getEntityClass() {
        return Ville.class;
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    public Ville getSocieteByCode(int code) {
        Map<String, Object> param = new HashMap<>();
        param.put("codevill", code);

        return super.findEntityByUsingQueryName(Ville.FIND_BY_CODEVILLE, param);
    }

}
