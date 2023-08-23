/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import exception.LicenceException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import modele.ConnectionLogger;
import modele.OrclassUtilisateurs;

/**
 *
 * @author hp
 */
@Stateless
public class ConnectionLoggerDao extends AbstractJpaDAO<ConnectionLogger> {

    private static final long serialVersionUID = 1L;
    @PersistenceContext(unitName = AbstractJpaDAO.PersistanceUnit)
    private EntityManager em;
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
    protected Class<ConnectionLogger> getEntityClass() {
        return ConnectionLogger.class;
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public void insertUserInLogger(OrclassUtilisateurs u) {

    }

    public Boolean testeFirstConnection(OrclassUtilisateurs user) {
        Query q = em.createQuery("select c from ConnectionLogger c WHERE c.idUtilisateur.idUtilisateur= :idUtilisateur")
                .setParameter("idUtilisateur", user.getIdUtilisateur());
        try {
               if (q.getResultList().isEmpty()) {
            /*
             l utilisateur user ne c est jamais connecter d ou il se connect comme sa pour la premier fois
             */
            return Boolean.TRUE;
        }
        } catch (Exception e) {
            return Boolean.TRUE;
        }
     
        return Boolean.FALSE;
    }

    public void checkDateConnexionWithLastConnexion(Date date) throws LicenceException {

        Query q = em.createQuery("select c from ConnectionLogger c order By c.id DESC");
        ConnectionLogger con = null;
        try {
            if (q.getResultList().isEmpty()) {
                con = null;
                return;
            }
            List<ConnectionLogger> listCon = (List<ConnectionLogger>) q.getResultList();
            if (listCon != null && !listCon.isEmpty()) {
                con = (ConnectionLogger) q.getResultList().get(0);
            }
        } catch (Exception e) {

        }
        //toute premiere connexion
        if (con != null) {
            System.out.println("controle de date");
            Date lastConnectionDate = con.getDateConnexion();

            Calendar calLastConnexion = Calendar.getInstance();
            Calendar calCurrent = Calendar.getInstance();
            if (lastConnectionDate == null) {
                System.out.println("PLEASE CHECK YOUR DATA BASE");
                throw new LicenceException("PLEASE CHECK YOUR DATA BASE ...LAST CONNECTION OF DATE IS NULL");
            }
            calLastConnexion.setTime(lastConnectionDate);
            calCurrent.setTime(date);

            //bandit t'as modifie la date systeme
            if (calCurrent.before(calLastConnexion)) {
                System.out.println("PLEASE CHECK YOUR SYSTEM DATE.");
                throw new LicenceException("PLEASE CHECK YOUR SYSTEM DATE.");
            }
//            if(date.before(lastConnectionDate)==false){
//                System.out.println("PLEASE CHECK YOUR SYSTEM DATE.");
//                throw new LicenceException("PLEASE CHECK YOUR SYSTEM DATE.");
//            }

        }
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
