package pe.com.cartaFianza.emision.servicio.dao;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

public abstract class BaseDao<T> {

	private final static String UNIT_NAME = "CartaFianzaDao";
	
	@PersistenceContext(unitName = UNIT_NAME)
	protected EntityManager em;
	
	@Resource
	private SessionContext sessionContext;
	
	private Class<T> entityClass;

    public BaseDao(Class<T> entityClass) {
    	this.entityClass = entityClass;
    }

    public T guardarEntity(T entity) throws Exception {
    	em.persist(entity);
    	return entity;
    }
    
    
    public void eliminar(T entity) {
    	T entityToBeRemoved = em.merge(entity);
    	em.remove(entityToBeRemoved);

    }
    
    public T actualizarEntity(T entity) {
    	return em.merge(entity);
    }
    
    public T obtenerEntity(Object entityID) {
    	return em.find(entityClass, entityID);
    }
    
    @SuppressWarnings("unchecked")
    protected T buscarRegistro(String namedQuery, Map<String, Object> parameters) {
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
    
    @SuppressWarnings("unchecked")
    protected List<T> buscarRegistros(String namedQuery, Map<String, Object> parameters) {
    	List<T> result = null;
    	try {
    		Query query = em.createNamedQuery(namedQuery);
    		if (parameters != null && !parameters.isEmpty()) {
    			populateQueryParameters(query, parameters);
    		}
    		result = query.getResultList();
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
    protected T buscarRegistro(String consulta, String campo, Object valor) {
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
    
    @SuppressWarnings("unchecked")
    protected List<T> buscarRegistros(String consulta, String campo, Object valor) {
    	List<T> result = null;
    	try {
    		Query query = em.createQuery(consulta);
    		query.setParameter(campo, valor);
    		result = query.getResultList();
    	} catch (Exception e) {	
    	  System.out.println("Error while running query: " + e.getMessage());
    	  e.printStackTrace();
      	}
    	return result;
    }
	
}
