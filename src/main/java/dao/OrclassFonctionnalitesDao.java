package dao;
import dao.AbstractJpaDAO;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import modele.OrclassFonctionnalites;
import modele.OrclassMenus;
import modele.OrclassModules;
import modele.OrclassProfils;

@Stateless
public class OrclassFonctionnalitesDao extends AbstractJpaDAO<OrclassFonctionnalites> {
  private static final long serialVersionUID = 1L;
  
  @PersistenceContext(unitName = AbstractJpaDAO.PersistanceUnit)
  private EntityManager em;
  
  protected Class<OrclassFonctionnalites> getEntityClass() {
    return OrclassFonctionnalites.class;
  }
  
  protected EntityManager getEntityManager() {
    return this.em;
  }
  
  public List<OrclassFonctionnalites> listeFonctionaliteByProfil(OrclassProfils p, OrclassModules m) {
    Query q = this.em.createQuery("SELECT DISTINCT f.idFonctionnalite FROM OrclassProfilsAcces f WHERE f.idProfil.idProfil= :idProfil and f.idModule.idModule= :idModule").setParameter("idProfil", p.getIdProfil()).setParameter("idModule", m.getIdModule());
    return q.getResultList();
  }
  
  public List<OrclassFonctionnalites> getAllFonctionnaireByModule(OrclassModules m) {
    Query q = this.em.createQuery("SELECT f FROM OrclassFonctionnalites  f WHERE f.idModule.idModule= :idModule").setParameter("idModule", m.getIdModule());
    return q.getResultList();
  }
  
  public List<OrclassFonctionnalites> getAllFonctionnaliteByMenuAccess(OrclassModules m, OrclassMenus menu) {
    Query q = this.em.createQuery("SELECT  Distinct ma.idFonctionnalite FROM OrclassMenusAcces ma WHERE  ma.idFonctionnalite.idModule.idModule= :idModule AND ma.idMenu.idMenu= :idMenu").setParameter("idModule", m.getIdModule()).setParameter("idMenu", menu.getIdMenu());
    return q.getResultList();
  }
  
  public OrclassFonctionnalites getFonctionnaliteByModule(OrclassModules m, String libelle) {
    Query q = this.em.createQuery("SELECT f FROM OrclassFonctionnalites f WHERE f.idModule.idModule= :idModule and f.libelle= :libelle").setParameter("libelle", libelle).setParameter("idModule", m.getIdModule());
    if (q.getResultList().isEmpty())
      return null; 
    return (OrclassFonctionnalites)q.getResultList().toArray()[0];
  }
}