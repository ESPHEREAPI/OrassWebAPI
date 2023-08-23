/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.Collection;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import modele.Titre;

/**
 *
 * @author hypoass
 */
@Stateless
@LocalBean
public class TitreDao extends AbstractJpaDAO<Titre> {
    private static final long serialVersionUID = 1L;
     
    @PersistenceContext(unitName=AbstractJpaDAO.PersistanceUnit)
    private EntityManager em;

    @Override
    protected Class<Titre> getEntityClass() {
        return Titre.class;
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    public Collection <Titre> getAllTitre(){
        return super.findAll();
    }
}
