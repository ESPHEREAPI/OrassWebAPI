package dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import modele.OrclassActions;
import modele.OrclassFonctionnalites;
import modele.OrclassMenus;
import modele.OrclassMenusAcces;

@Stateless
public class OrclassMenusAccesDao extends AbstractJpaDAO<OrclassMenusAcces> {
  private static final long serialVersionUID = 1L;
  
  @PersistenceContext(unitName = AbstractJpaDAO.PersistanceUnit)
  private EntityManager em;
  
  protected Class<OrclassMenusAcces> getEntityClass() {
    return OrclassMenusAcces.class;
  }
  
  protected EntityManager getEntityManager() {
    return this.em;
  }
  
  public OrclassMenusAcces finkey(OrclassMenus menu, OrclassFonctionnalites f, OrclassActions action) {
    Query q = this.em.createQuery("SELECT ma FROM OrclassMenusAcces ma WHERE ma.idAction.idAction= :idAction AND  ma.idFonctionnalite.idFonctionnalite= :idFonctionnalite AND ma.idMenu.idMenu= :idMenu").setParameter("idAction", action.getIdAction()).setParameter("idFonctionnalite", f.getIdFonctionnalite()).setParameter("idMenu", menu.getIdMenu());
    if (q.getResultList().isEmpty())
      return null; 
    return (OrclassMenusAcces)q.getResultList().toArray()[0];
  }
  
  public Boolean fonctionnaliteByMenyExiste(OrclassMenus menu, OrclassFonctionnalites f) {
    Query q = this.em.createQuery("SELECT ma FROM OrclassMenusAcces ma WHERE  ma.idFonctionnalite.idFonctionnalite= :idFonctionnalite AND ma.idMenu.idMenu= :idMenu").setParameter("idFonctionnalite", f.getIdFonctionnalite()).setParameter("idMenu", menu.getIdMenu());
    if (q.getResultList().isEmpty())
      return Boolean.FALSE; 
    return Boolean.TRUE;
  }
}
