package pe.com.soul.core.dao;

import java.util.List;

import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;

public class BaseDaoImpl<T> {

	private final static String UNIT_NAME = "soulJPA";
	
	@PersistenceContext(unitName = UNIT_NAME)
	protected EntityManager em;
	
	private Class<T> entityClass;

    public BaseDaoImpl(Class<T> entityClass) {
    	this.entityClass = entityClass;
    }

    public void guardar(T entity) {
    	em.persist(entity);
    }
    
    public void eliminar(T entity) {
    	T entityToBeRemoved = em.merge(entity);
    	em.remove(entityToBeRemoved);

    }
    
    public T actualizar(T entity) {
    	return em.merge(entity);
    }
    
    public T buscar(int entityID) {
    	return em.find(entityClass, entityID);
    }
    
    @SuppressWarnings({"unchecked", "rawtypes" })
    public List<T> obtenerTodo() {
    	CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
    	cq.select(cq.from(entityClass));
    	return em.createQuery(cq).getResultList();
    }
    
    @SuppressWarnings("unchecked")
    protected T buscar(String namedQuery, Map<String, Object> parameters) {
    	T result = null;
    	try {
    		Query query = em.createNamedQuery(namedQuery);
    		if (parameters != null && !parameters.isEmpty()) {
    			populateQueryParameters(query, parameters);
    		}
    		result = (T) query.getSingleResult();
    	} catch (Exception e) {	
    	  System.out.println("Error while running query: " + e.getMessage());
    	  e.printStackTrace();
      	}
    	return result;
    }
    
    private void populateQueryParameters(Query query, Map<String, Object> parameters) {
    	for (Entry<String, Object> entry : parameters.entrySet()) {
    		query.setParameter(entry.getKey(), entry.getValue());
    	}
    }
    
    @SuppressWarnings("unchecked")
    protected T buscar(String consulta, String campo, Object valor) {
    	T result = null;
    	try {
    		Query query = em.createQuery(consulta);
    		query.setParameter(campo, valor);
    		result = (T) query.getSingleResult();
    	} catch (Exception e) {	
    	  System.out.println("Error while running query: " + e.getMessage());
    	  e.printStackTrace();
      	}
    	return result;
    }
	
}
