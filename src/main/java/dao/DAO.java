
package dao;

import java.io.Serializable;
import java.util.Collection;
import modele.DAOEntry;
 
/**     
 INTERFACE GENERIQUE POUR MANIPULER LES PROPRIETES COMMUNES AUX ENTITY()
 * @param <T>
 */
//@Local
public interface DAO<T extends DAOEntry> extends Serializable {
 
// Creation d un enregistrement
    public void create(T entry);
 
// Enregistrement en bloc d une collecion
    public void createAll(Collection<? extends DAOEntry> entries);
 
 // Recheche pour affiche avec parametre de recherche  
    public T find(Object id);
 
 // Affichage de tous les enregistrements
    public Collection<T> findAll();
 
  // Mis a jour d enregistrement(s)
    public void update(T entry);
    
 // Suppression d enregistrement(s)
    public void delete(T entry);
 
    // compte les enregistrements
    public Long count();
    
    
 
}