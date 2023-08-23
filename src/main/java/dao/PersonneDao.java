package dao;

//import interceptors.AddRoleToPersonneInterceptor;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import modele.Mois;
import modele.Personne;



/**
 *
 * @author KAMDEM
 */
@Stateless
//@Interceptors({AddRoleToPersonneInterceptor.class})
public class PersonneDao extends AbstractJpaDAO<Personne> {

    private static final long serialVersionUID = 1L;
    @PersistenceContext(unitName = AbstractJpaDAO.PersistanceUnit)
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    @Override
    protected Class<Personne> getEntityClass() {
        return Personne.class;
    }

    public Collection<Personne> getAllPersonne() {
        return super.findAll();
    }
    
//    /*
//    retourne les personne d un poste
//    */
//   public List<Personne> listePersonneByPersonne(Poste p){
//       Query q;
//       q=em.createQuery("SELECT p FROM Personnel p join p.")
//   }
   
//    public Collection<Personne> findByProfil(Profil profil) {
//         //String jpql = "select personne from Personne personne where personne.profilid.description=:description";
//
//        Query query = em.createNamedQuery(Personne.FIND_BY_CODE_PROFIL);
//        query.setParameter("code", profil.getCode());
//        return query.getResultList();
//    }

    /**
     * Charger les types de user qui pourront etre administrer dans les menu
     *
     * @return
     */
//      public Collection<Personne> getUserForSecurite(){
//         String jpql = "select personne from Personne personne where personne.profilid.description=:description";
//               
//         Query query = em.createNamedQuery(Personne.FIND_BY_CODE_PROFIL);
//         query.setParameter("code", profil.getCode());
//         return query.getResultList();
//     }
//    public Collection<Personne> findByEvent(Evenement evenement) {
//
//        Query query = em.createNamedQuery(Personne.FIND_BY_ID_EVENT);
//        query.setParameter("evenement_id", evenement.getId());
//        return query.getResultList();
//    }

    /**
     * Recuperer le personnel hormis les enseignants
     *
     * @return
     */
    public Collection<Personne> getPersonnelAdministration() {
        String sql = "Select * from Personne where TYPE_ENTITE='User'";
        Query q = em.createNativeQuery(sql);
        return q.getResultList();
    }
    /*
     * retourner une personne 
     */

//    public Personne getPersonneByInscrip() {
//        Map<String, Object> param = new HashMap<>();
//
//        param.put("matricule", i.getEtudiant().getMatricule());
//        return super.findEntityByUsingQueryName(Personne.FIND_BY_MATRICULE, param);
//
//    }
    
       public Personne getPersonneByMatricule(String mat) {
        Map<String, Object> param = new HashMap<>();
      
        param.put("matricule", mat);
        return super.findEntityByUsingQueryName(Personne.FIND_BY_MATRICULE, param);

    }

      
      
}
