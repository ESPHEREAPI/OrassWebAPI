package dao;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import modele.OrclassProfilsAcces;
import modele.OrclassUtilisateurs;
import modele.OrclassUtilisateursAcces;

@Stateless
public class OrclassUtilisateursAccesDao extends AbstractJpaDAO<OrclassUtilisateursAcces> {
  private static final long serialVersionUID = 1L;
  
 @PersistenceContext(unitName = AbstractJpaDAO.PersistanceUnit)
  private EntityManager em;
  
  protected Class<OrclassUtilisateursAcces> getEntityClass() {
    return OrclassUtilisateursAcces.class;
  }
  
  protected EntityManager getEntityManager() {
    return this.em;
  }
  
  public OrclassUtilisateursAcces finKey(OrclassProfilsAcces pa, OrclassUtilisateurs u) {
    Query q = this.em.createQuery("SELECT ua FROM OrclassUtilisateursAcces ua WHERE ua.idAction.idAction= :idAction and ua.idFonctionnalite.idFonctionnalite= :idFonctionnalite AND ua.idProfil.idProfil= :idProfil AND ua.idModule.idModule= :idModule AND ua.idUtilisateur.idUtilisateur= :idUtilisateur").setParameter("idModule", pa.getIdModule().getIdModule()).setParameter("idProfil", pa.getIdProfil().getIdProfil()).setParameter("idFonctionnalite", pa.getIdFonctionnalite().getIdFonctionnalite()).setParameter("idAction", pa.getIdAction().getIdAction()).setParameter("idUtilisateur", u.getIdUtilisateur());
    try {
      if (q.getResultList() == null || q.getResultList().isEmpty())
        return null; 
    } catch (Exception e) {
      return null;
    } 
    return (OrclassUtilisateursAcces)q.getResultList().toArray()[0];
  }
  
  public List<OrclassUtilisateursAcces> getAllAccessUserByProfilAccess(OrclassProfilsAcces pa) {
    Query q = this.em.createQuery("SELECT ua FROM OrclassUtilisateursAcces ua WHERE ua.idAction.idAction= :idAction and ua.idFonctionnalite.idFonctionnalite= :idFonctionnalite AND ua.idProfil.idProfil= :idProfil AND ua.idModule.idModule= :idModule ").setParameter("idModule", pa.getIdModule().getIdModule()).setParameter("idProfil", pa.getIdProfil().getIdProfil()).setParameter("idFonctionnalite", pa.getIdFonctionnalite().getIdFonctionnalite()).setParameter("idAction", pa.getIdAction().getIdAction());
    return q.getResultList();
  }
}