package dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import modele.OrclassFonctionnalites;
import modele.OrclassMenus;
import modele.OrclassModules;

@Stateless
public class OrclassMenusDao extends AbstractJpaDAO<OrclassMenus> {
  private static final long serialVersionUID = 1L;
  
   @PersistenceContext(unitName = AbstractJpaDAO.PersistanceUnit)
  private EntityManager em;
  
  protected Class<OrclassMenus> getEntityClass() {
    return OrclassMenus.class;
  }
  
  protected EntityManager getEntityManager() {
    return this.em;
  }
  
  public OrclassMenus getMenuByCode(String code) {
    Map<String, Object> params = new HashMap<>();
    params.put("code", code);
    return (OrclassMenus)findEntityByUsingQueryName("OrclassMenus.findByCodeMenus", params);
  }
  
  public List<OrclassMenus> getMenuByModule(OrclassModules code) {
    Map<String, Object> params = new HashMap<>();
    params.put("moduleid", code.getIdModule());
    return (List)findAllEntitiesByUsingQueryName("OrclassMenus.findByModuleidMenus", params);
  }
  
  public Collection<OrclassMenus> getAllMenu() {
    return (Collection)findAll();
  }
  
  public List<OrclassMenus> getAllMenuByFonctionnalite(OrclassFonctionnalites f) {
    Query q = this.em.createQuery("SELECT distinct ma.idMenu FROM OrclassMenusAcces ma WHERE   ma.idFonctionnalite.idFonctionnalite= :idFonctionnalite ").setParameter("idFonctionnalite", f.getIdFonctionnalite());
    return q.getResultList();
  }
}
