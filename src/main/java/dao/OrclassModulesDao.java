package dao;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import modele.Societe;
import modele.OrclassModules;

@Stateless
public class OrclassModulesDao extends AbstractJpaDAO<OrclassModules> {
  private static final long serialVersionUID = 1L;
  
  @PersistenceContext(unitName = AbstractJpaDAO.PersistanceUnit)
  private EntityManager em;
  
  @Override
  protected Class<OrclassModules> getEntityClass() {
    return OrclassModules.class;
  }
  
  @Override
  protected EntityManager getEntityManager() {
    return this.em;
  }
  
  public List<OrclassModules> listeModulesNotHaveEntreprise(Societe s) {
    Query q = this.em.createQuery("SELECT  m FROM OrclassModules m WHERE not exists(select me.orclassModules from OrclassEntreprisesModules me where me.societe = :s and me.orclassModules= m)  ").setParameter("s", s);
    return q.getResultList();
  }
  
  public List<OrclassModules> listeModulesByEntreprise(Societe s) {
    Query q = this.em.createQuery("SELECT me.orclassModules FROM  OrclassEntreprisesModules me WHERE me.societe = :s").setParameter("e", s);
    return q.getResultList();
  }
}
