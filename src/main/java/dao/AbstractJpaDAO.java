package dao;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.persistence.EntityManager;
import javax.persistence.NamedQuery;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import modele.DAOEntry;

// CLASSE ABSTRAITE PERMETTANT DE MANIPULER L INTERFACE DAO
public abstract class AbstractJpaDAO<T extends DAOEntry> implements DAO<T> {

    private static final long serialVersionUID = 1L;
    public static final String PersistanceUnit = "ESphereOrassPU";

    /**
     * @return The type of the class of the entity to work with.
     */
    protected abstract Class<T> getEntityClass();

    protected abstract EntityManager getEntityManager();

//    protected EntityManager getEntityManager() {
//        return em;
//    }
    protected AbstractJpaDAO() {
    }

    /**
     * @param range start a O and dont keep the last range
     * @return
     */
    public List<T> findRange(int start, int end) {

        CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(this.getEntityClass()));
        Query q = getEntityManager().createQuery(cq);
        q.setMaxResults(end - start);
        q.setFirstResult(start);
        return q.getResultList();
    }

    /**
     * @param stringQuery
     * @param range
     * @return
     */
    public List<T> findWithNamedQuery(String stringQuery, int start, int end) {
        Query q = getEntityManager().createNamedQuery(stringQuery);
        q.setMaxResults(end - start);
        q.setFirstResult(start);
        return (List<T>) q.getResultList();
    }

    /**
     * @param stringQuery
     * @param params
     * @param range
     * @return
     */
    public List<T> findWithNamedQuery(String stringQuery, Map<String, Object> params, int start, int end) {

        Query q = getEntityManager().createNamedQuery(stringQuery);
        Iterator<Entry<String, Object>> it
                = params.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, Object> pairs
                    = (Map.Entry<String, Object>) it.next();
            q.setParameter(pairs.getKey(), pairs.getValue());
        }
        q.setMaxResults(end - start);
        q.setFirstResult(start);
        return (List<T>) q.getResultList();
    }

    public List<T> findWithQuery(String stringQuery, Map<String, Object> params, int start, int end) {
        Query q = getEntityManager().createQuery(stringQuery);
        Iterator<Entry<String, Object>> it
                = params.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, Object> pairs
                    = (Map.Entry<String, Object>) it.next();
            q.setParameter(pairs.getKey(), pairs.getValue());
        }
        q.setMaxResults(end - start);
        q.setFirstResult(start);
        return (List<T>) q.getResultList();
    }

    @Override
    public void create(T entry) {

        this.getEntityManager().persist(entry);
        //this.getEntityManager().merge(entry);

    }

    @Override
    public void createAll(Collection<? extends DAOEntry> entries) {

        for (DAOEntry entry : entries) {
            this.getEntityManager().persist(entry);
        }

    }

    @Override
    public T find(Object id) {
        return this.getEntityManager().find(this.getEntityClass(), id);
    }

    @Override
    public Collection<T> findAll() {
        CriteriaQuery<T> cq = this.getEntityManager().getCriteriaBuilder().createQuery(this.getEntityClass());
        cq.select(cq.from(this.getEntityClass()));
        return this.getEntityManager().createQuery(cq).getResultList();
    }

    @Override
    public void update(T entry) {

        this.getEntityManager().merge(entry);

    }

    @Override
    public void delete(T entry) {
        //il est important de rendre l 'etat de l'entite persistant
        this.getEntityManager().remove(this.getEntityManager().merge(entry));

        this.getEntityManager().flush();
    }

    /**
     * Retrieves the object/entity/model using the key/value pair.
     *
     * @param fieldName - The name of the key/field.
     * @param value - The value for the specified key/field.
     * @return The entity/object or <code>null</code> if not found.
     */
    @SuppressWarnings("unchecked")
    public T findEntityHavingValue(String fieldName, Object value) {
        final String jpql = "SELECT x FROM " + this.getEntityClass().getName() + " x WHERE x." + fieldName + " = :"
                + fieldName;
        Query q = this.getEntityManager().createQuery(jpql, this.getEntityClass());
        q.setParameter(fieldName, value);
        T result = null;
        try {
            result = (T) q.getSingleResult();
        } catch (NoResultException nre) {
            // No result found !
        }
        return result;
    }

    @SuppressWarnings("unchecked")
    public int deleteAll(String fieldName, Object value) {
        final String jpql = "DELETE x FROM " + this.getEntityClass().getName() + " x WHERE x." + fieldName + " = :"
                + fieldName;
        Query q = this.getEntityManager().createQuery(jpql, this.getEntityClass());
        q.setParameter(fieldName, value);
        Integer result = 0;
        try {
            result = q.executeUpdate();
        } catch (NoResultException nre) {
            // No result found !
        }
        return result;
    }

    /**
     * Retrieves the list of all entities/objects having the specified value for
     * the specified field.
     *
     * @param fieldName - The name of the field.
     * @param value - The value for the specified field.
     * @return The list of objects found or an empty list.
     */
    public Collection<T> findAllEntitiesHavingValue(String fieldName, Object value) {
        final String jpql = "SELECT x FROM " + this.getEntityClass().getName() + " x WHERE x." + fieldName + " = :"
                + fieldName;
        Query q = this.getEntityManager().createQuery(jpql, this.getEntityClass());
        q.setParameter(fieldName, value);
        @SuppressWarnings("unchecked")
        List<T> result = q.getResultList();
        return result;
    }

    /**
     * Retrieves the list of all entities/objects don't have the specified value
     * for the specified field.
     *
     * @param fieldName - The name of the field.
     * @param value - The value for the specified field.
     * @return The list of objects found or an empty list.
     */
    public Collection<T> findAllEntitiesNotHavingValue(String fieldName, Object value) {
        final String jpql = "SELECT x FROM " + this.getEntityClass().getName() + " x WHERE x." + fieldName + " <> :"
                + fieldName;
        Query q = this.getEntityManager().createQuery(jpql, this.getEntityClass());
        q.setParameter(fieldName, value);
        @SuppressWarnings("unchecked")
        List<T> result = q.getResultList();
        return result;
    }

    /**
     * Retrieves the specified entity/object using the specified
     * {@link NamedQuery} having the specified name.
     *
     * @param queryName - The name of the {@link NamedQuery} to use.
     * @param parameters - The list of parameters to passe to the
     * {@link NamedQuery}.
     * @return The entity/object or <tt>null</tt> if not found.
     */
    @SuppressWarnings("unchecked")
    public T findEntityByUsingQueryName(String queryName, Map<String, Object> parameters) {
        Query q = this.getEntityManager().createNamedQuery(queryName);
        if (parameters != null) {
            Iterator<Entry<String, Object>> iter = parameters.entrySet().iterator();
            while (iter.hasNext()) {
                Entry<String, Object> o = iter.next();
                q.setParameter(o.getKey(), o.getValue());
            }
        }
        T result = null;
        try {
            result = (T) q.getSingleResult();
        } catch (NoResultException nre) {
            // Pas de rÃ©sultat trouvÃ© !
        }
        return result;
    }

    @SuppressWarnings("unchecked")
    public T findEntityByUsingQuery(String queryName, Map<String, Object> parameters) {
        Query q = this.getEntityManager().createQuery(queryName);
        if (parameters != null) {
            Iterator<Entry<String, Object>> iter = parameters.entrySet().iterator();
            while (iter.hasNext()) {
                Entry<String, Object> o = iter.next();
                q.setParameter(o.getKey(), o.getValue());
            }
        }
        T result = null;
        try {
            result = (T) q.getSingleResult();
        } catch (NoResultException nre) {
            // Pas de rÃ©sultat trouvÃ© !
        }
        return result;
    }

    /**
     * Retrieves the list of entities/object using the specified
     * {@link NamedQuery} having the specified name.
     *
     * @param queryName - The name of the {@link NamedQuery} to use.
     * @param parameters - The list of parameters to pass to the
     * {@link NamedQuery}.
     * @return The list of entities/objects or an empty list.
     */
    public Collection<T> findAllEntitiesByUsingQueryName(String queryName, Map<String, Object> parameters) {
        Query q = this.getEntityManager().createNamedQuery(queryName);
        if (parameters != null) {
            Iterator<Entry<String, Object>> iter = parameters.entrySet().iterator();
            while (iter.hasNext()) {
                Entry<String, Object> o = iter.next();
                q.setParameter(o.getKey(), o.getValue());
            }
        }
        @SuppressWarnings("unchecked")
        List<T> result = q.getResultList();
        return result;
    }

    public Collection<T> findAllEntitiesByUsingQuery(String queryName, Map<String, Object> parameters) {
        Query q = this.getEntityManager().createQuery(queryName);
        if (parameters != null) {
            Iterator<Entry<String, Object>> iter = parameters.entrySet().iterator();
            while (iter.hasNext()) {
                Entry<String, Object> o = iter.next();
                q.setParameter(o.getKey(), o.getValue());
            }
        }
        @SuppressWarnings("unchecked")
        List<T> result = q.getResultList();
        return result;
    }

    /**
     * Permits to close the {@link EntityManager} which is use internally by the
     * {@link DAO}.
     */
    public void closeEntityManager() {
        this.getEntityManager().close();
    }

    /**
     * Permits to give a total of recors in entity/object
     *
     * @return total records of entity
     */
    @Override
    public Long count() {
        CriteriaQuery cq = this.getEntityManager().getCriteriaBuilder().createQuery();
        Root<T> rt = cq.from(getEntityClass());
        cq.select(this.getEntityManager().getCriteriaBuilder().count(rt));
        Query q = this.getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).longValue();
    }
}
