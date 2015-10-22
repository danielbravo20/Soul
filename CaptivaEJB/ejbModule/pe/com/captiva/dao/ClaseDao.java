package pe.com.captiva.dao;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import pe.com.captiva.dao.entity.Clase;

/**
 * Session Bean implementation class ClaseDao
 */
@Stateless
@LocalBean
public class ClaseDao extends BaseDao<Clase> implements ClaseDaoLocal {

    /**
     * Default constructor. 
     */
    public ClaseDao() {
        super(Clase.class);
    }
    
    

}
