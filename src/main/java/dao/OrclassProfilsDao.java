/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import modele.OrclassProfils;
import modele.OrclassProfilsAcces;
import modele.OrclassUtilisateurs;
import modele.Societe;

/**
 *
 * @author hp
 */
@Stateless
public class OrclassProfilsDao  extends AbstractJpaDAO<OrclassProfils> {
 @PersistenceContext(unitName = AbstractJpaDAO.PersistanceUnit)
    private EntityManager em;
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
    protected Class<OrclassProfils> getEntityClass() {
       return OrclassProfils.class;
    }

    @Override
    protected EntityManager getEntityManager() {
       return em;
    }
     public List<OrclassProfils> getProfilByUtilisateu(OrclassUtilisateurs u) {
        Query q;
        q = em.createQuery("SELECT p.idProfil FROM ORCLASS_PROFILS_UTILISATEURS p WHERE p.idUtilisateur.idUtilisateur= :iduser  and p.idProfil.actif= :value")
                .setParameter("iduser", u.getIdUtilisateur())
                .setParameter("value", Boolean.TRUE)
                ;
        return q.getResultList();
    }
     
      public List<OrclassProfils> getAllProfilHaveAccesByEntreprise(Societe s) {
             Query q;
             List<OrclassProfils> listPa=new ArrayList<>();
         OrclassProfilsAcces    pa;
          if (s== null || s.getCodesoci()==null) {
            q = em.createQuery("SELECT DISTINCT pa.idProfil FROM OrclassProfilsAcces pa WHERE  pa.autorisation= :values  and pa.idProfil.actif= :values")
                .setParameter("values", Boolean.TRUE)
//                .setParameter("idEntreprise", entreprise.getIdEntreprise())
               ;  
          }else{
              q = em.createQuery("SELECT DISTINCT pa.idProfil FROM OrclassProfilsAcces pa WHERE  pa.autorisation= :values  and pa.idProfil.actif= :values and pa.idProfil.idSociete.codesoci = :code ")
                .setParameter("values", Boolean.TRUE)
                .setParameter("code", s.getCodesoci())
               ;
          }
               
         
               return q.getResultList();
       }
      
      public List<OrclassProfils> allProfilByEntrePrise(Societe s) {
           Query q;
             List<OrclassProfils> listPa=new ArrayList<>();
          if (s!= null && s.getCodesoci()!=null) {
              q=em.createQuery("SELECT p FROM OrclassProfils p WHERE  p.idSociete  = :code")
//                       .setParameter("values", Boolean.TRUE)
                      .setParameter("code", s.getCodesoci())
                      ;
              listPa=q.getResultList();
          }
        return listPa;
      }
      public Long nbreProfileCreateByCompagny(Societe s){
          Query q;
          q=em.createQuery("SELECT COUNT(p.code) FROM OrclassProfils p WHERE p.idSociete= :s")
                  .setParameter("s", s);
          return  ((Long) q.getSingleResult());
      }
}
