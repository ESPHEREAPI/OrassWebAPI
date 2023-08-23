
package dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import modele.Pays;

@Stateless
public class PaysDao extends AbstractJpaDAO<Pays>{
    
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@PersistenceContext(unitName=AbstractJpaDAO.PersistanceUnit)
    private EntityManager em;

  

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    @Override
    protected Class<Pays> getEntityClass() {
        return Pays.class;
    }
    
    public Collection<Pays> getAllPays(){
        return super.findAll();
    }
    
    public Pays getPaysByCode(String code){
        Map<String,Object> params=new HashMap<>();
        params.put("codepays", code);
        return super.findEntityByUsingQueryName(Pays.FIND_BY_CODEPAYS, params);
    }
    
}
