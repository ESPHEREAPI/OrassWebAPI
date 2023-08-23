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
import modele.ORCLASS_PROFILS_UTILISATEURS;
import modele.OrclassProfils;
import modele.OrclassUtilisateurs;

/**
 *
 * @author hp
 */
@Stateless
public class ORCLASS_PROFILS_UTILISATEURSDao extends AbstractJpaDAO<ORCLASS_PROFILS_UTILISATEURS> {

    private static final long serialVersionUID = 1L;
    @PersistenceContext(unitName = AbstractJpaDAO.PersistanceUnit)
    private EntityManager em;
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
    protected Class<ORCLASS_PROFILS_UTILISATEURS> getEntityClass() {
        return ORCLASS_PROFILS_UTILISATEURS.class;
    }

    @Override
    protected EntityManager getEntityManager() {

        return em;
    }

    public List<ORCLASS_PROFILS_UTILISATEURS> getProfilByUtilisateu(OrclassUtilisateurs u) {
        Query q;
        q = em.createQuery("SELECT p FROM ORCLASS_PROFILS_UTILISATEURS p WHERE p.idUtilisateur.idUtilisateur= :iduser ")
                .setParameter("iduser", u.getIdUtilisateur());
        return q.getResultList();
    }

    public ORCLASS_PROFILS_UTILISATEURS finkey(OrclassUtilisateurs u, OrclassProfils pr) {
        Query q;
        q = em.createQuery("SELECT p FROM ORCLASS_PROFILS_UTILISATEURS p WHERE p.idUtilisateur.idUtilisateur= :iduser and p.idProfil.idProfil= :idprofil")
                .setParameter("iduser", u.getIdUtilisateur())
                .setParameter("idprofil", pr.getIdProfil());
        if (q.getResultList().isEmpty()) {
            return null;
        }
        return (ORCLASS_PROFILS_UTILISATEURS) q.getResultList().toArray()[0];
    }
}
