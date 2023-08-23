/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import modele.Region;

/**
 *
 * @author JIATOU FRANCK
 */
@Stateless
public class RegionDao extends AbstractJpaDAO<Region>{
       
    @PersistenceContext(unitName=AbstractJpaDAO.PersistanceUnit)
    private EntityManager em;

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
    protected Class<Region> getEntityClass() {
      return Region.class;
    }

    @Override
    protected EntityManager getEntityManager() {
     return em;
    }
}
