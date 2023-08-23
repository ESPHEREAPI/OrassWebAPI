/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.Collection;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import modele.IndicatifPays;

/**
 *
 * @author JEANNE
 */
@Stateless
public class IndicatifPaysDao extends AbstractJpaDAO<IndicatifPays> {

    private static final long serialVersionUID = 1L;
    @PersistenceContext(unitName = AbstractJpaDAO.PersistanceUnit)
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    @Override
    protected Class<IndicatifPays> getEntityClass() {
        return IndicatifPays.class;
    }

    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public Collection<IndicatifPays> getAllIndicatif() {
        return super.findAll();
    }
}
