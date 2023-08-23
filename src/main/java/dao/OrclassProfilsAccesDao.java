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
import modele.OrclassActions;
import modele.OrclassFonctionnalites;
import modele.OrclassModules;
import modele.OrclassProfils;
import modele.OrclassProfilsAcces;
import modele.Societe;

/**
 *
 * @author hp
 */
@Stateless
public class OrclassProfilsAccesDao extends AbstractJpaDAO<OrclassProfilsAcces> {

    private static final long serialVersionUID = 1L;
    @PersistenceContext(unitName = AbstractJpaDAO.PersistanceUnit)
    private EntityManager em;

    @Override
    protected Class<OrclassProfilsAcces> getEntityClass() {
        return OrclassProfilsAcces.class;
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public OrclassProfilsAcces finkey(OrclassModules m, OrclassFonctionnalites f, OrclassActions action, OrclassProfils p) {
        Query q;
        if ("admin".equals(p.getCode())) {
            q = em.createQuery("SELECT pa FROM OrclassProfilsAcces pa where pa.idModule.idModule= :idModule and pa.idProfil.idProfil= :idProfil AND pa.idFonctionnalite.idFonctionnalite= :idFonctionnalite and pa.autorisation= :value and pa.idProfil.actif= :value"
                    + " AND pa.idAction.idAction= :idAction")
                    .setParameter("idModule", m.getIdModule())
                    .setParameter("idProfil", p.getIdProfil())
                    .setParameter("idFonctionnalite", f.getIdFonctionnalite())
                    .setParameter("idAction", action.getIdAction())
                    .setParameter("value", Boolean.TRUE);
            if (q.getResultList().isEmpty() == Boolean.TRUE) {
                return null;
            }
        } else {
            q = em.createQuery("SELECT pa FROM OrclassProfilsAcces pa  where  pa.idModule.idModule= :idModule and pa.idProfil.idProfil= :idProfil AND pa.idFonctionnalite.idFonctionnalite= :idFonctionnalite and pa.autorisation= :value and pa.idProfil.actif= :value and pa.idProfil.idSociete.codesoci = :code"
                    + " AND pa.idAction.idAction= :idAction")
                    .setParameter("idModule", m.getIdModule())
                    .setParameter("idProfil", p.getIdProfil())
                    .setParameter("idFonctionnalite", f.getIdFonctionnalite())
                    .setParameter("idAction", action.getIdAction())
                    .setParameter("code", p.getIdSociete().getCodesoci())
                    .setParameter("value", Boolean.TRUE);
            if (q.getResultList().isEmpty() == Boolean.TRUE) {
                return null;
            }

        }

        return (OrclassProfilsAcces) q.getResultList().toArray()[0];
    }

    /*
    retourne les acces d un profil existant
     */
    public List<OrclassProfilsAcces> getAllAccesByProfil(OrclassProfils p) {
        Query q;
        if (p.getCode().equals("admin")) {
            q = em.createQuery("SELECT pa FROM OrclassProfilsAcces pa WHERE pa.idProfil.idProfil= :idProfil and pa.autorisation= :values  and pa.idProfil.actif= :values ")
                    .setParameter("values", Boolean.TRUE)
                    //                .setParameter("idEntreprise", p.getIdEntreprise())
                    .setParameter("idProfil", p.getIdProfil());

        } else {

            q = em.createQuery("SELECT pa FROM OrclassProfilsAcces pa WHERE pa.idProfil.idProfil= :idProfil and pa.autorisation= :values  and pa.idProfil.actif= :values and pa.idProfil.idSociete.codesoci= :code")
                    .setParameter("values", Boolean.TRUE)
                    .setParameter("code", p.getIdSociete().getCodesoci())
                    .setParameter("idProfil", p.getIdProfil());

        }

        return q.getResultList();
    }
    

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    public List<OrclassProfilsAcces> getAllAccesByEntreprise(Societe s) {
        Query q;
        List<OrclassProfilsAcces> listPa = new ArrayList<>();
        OrclassProfilsAcces pa;
        q = em.createQuery("SELECT  pa FROM OrclassProfilsAcces pa WHERE  pa.autorisation= :values  and pa.idProfil.actif= :values and pa.idProfil.idSociete.codesoci = :idEntreprise ")
                .setParameter("values", Boolean.TRUE)
                .setParameter("code", s.getCodesoci());
//               for (Object p : q.getResultList()) {
//                   if (listPa.isEmpty()) {
//                       pa=(OrclassProfilsAcces) p;
//                   listPa.add(pa);
//                   }else{
//                       pa=(OrclassProfilsAcces) p;
//                       if (listPa.contains(pa)==false) {
//                           listPa.add(pa);
//                       }
//                   }
//               
//           }
        return q.getResultList();
    }
    public List<OrclassProfilsAcces> getAllProfilByFonctionnalite(OrclassFonctionnalites f,Societe s ){
        Query q;
        q=em.createQuery("SELECT p FROM OrclassProfilsAcces p WHERE p.idFonctionnalite.idFonctionnalite= :idFonctionnalite and p.idProfil.actif= :value and p.idProfil.idSociete.codesoci = :code")
                .setParameter("idFonctionnalite", f.getIdFonctionnalite())
                .setParameter("code", s.getCodesoci())
                .setParameter("value", Boolean.TRUE);
        return q.getResultList();
                
                
    }
     public List<OrclassProfilsAcces> getAllProfilByFonctionnalite(OrclassFonctionnalites f,OrclassProfils p,Societe s){
        Query q;
        q=em.createQuery("SELECT p FROM OrclassProfilsAcces p WHERE p.idFonctionnalite.idFonctionnalite= :idFonctionnalite and p.idProfil.actif= :value and p.idProfil.idSociete.codesoci = :code and p.idProfil= :idprofil")
                .setParameter("idFonctionnalite", f.getIdFonctionnalite())
                .setParameter("code", s.getCodesoci())
                 .setParameter("idprofil", p)
                .setParameter("value", Boolean.TRUE);
        return q.getResultList();
                
                
    }
    
  
  
}
