/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import modele.OrclassUtilisateurs;
import modele.Personne;
import modele.Photo;

/**
 *
 * @author KAMDEM
 */
@Stateless
public class PhotoDao extends AbstractJpaDAO<Photo>{
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
    protected Class<Photo> getEntityClass() {
        return Photo.class;
    } 
     public Collection<Photo> getAllPhoto()
      {
          return super.findAll();
      }
     public Photo getPhotoByPersonne(OrclassUtilisateurs u){
         Map<String,Object> param=new HashMap<>();
         param.put("idUtilisateur", u.getIdUtilisateur());
          Collection<Photo> photo=new ArrayList<>();
          photo =super.findAllEntitiesByUsingQueryName(Photo.Find_By_Utilisateur, param);
          for (Photo photo1 : photo) {
             if(photo1.getImage()==null){
                 continue;
             }
             return photo1;
         }
     return null;
     }
     
}
