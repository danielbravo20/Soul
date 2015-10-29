package pe.com.captiva.dao;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import pe.com.captiva.bean.CampoSQLBean;
import pe.com.captiva.bean.TablaBean;
import pe.com.captiva.dao.entity.AtributoSql;
import pe.com.captiva.dao.entity.AtributoSqlId;
import pe.com.captiva.dao.entity.Tabla;

@Stateless
@LocalBean
public class AtributoSQLDao extends BaseDao<AtributoSql> implements AtributoSQLDaoLocal{
    
	public AtributoSQLDao() {
        super(AtributoSql.class);
    }
	
	 public CampoSQLBean obtenerCampoSQLBean(Integer codigoTabla, Integer codigoAtributo){
		 if(codigoTabla!=null && codigoAtributo!=null){
			 AtributoSqlId atributoSqlId = new AtributoSqlId(codigoTabla, codigoAtributo);
			 AtributoSql atributoSql = obtenerEntity(atributoSqlId);
			 return parseCampoSQLBean(atributoSql);
		 }else{
			 return null;
		 }
	 }
	 
	 private CampoSQLBean parseCampoSQLBean(AtributoSql atributoSql){
		 CampoSQLBean campoSQLBean = null;
	    	if(atributoSql!=null){
	    		campoSQLBean = new CampoSQLBean();
	    		campoSQLBean.setCodigo(atributoSql.getId().getCodAtributo());
	    		campoSQLBean.setCodigoAtributo(atributoSql.getId().getCodAtributo());
	    		campoSQLBean.setNombre(atributoSql.getCampo()); 
	    		campoSQLBean.setTipo(atributoSql.getTipo());
	    		campoSQLBean.setFuncionBusqueda(atributoSql.getFnBusNombre());
	    		campoSQLBean.setFuncionBusquedaCatalogo(atributoSql.getFnBusCatalogo());
	    		campoSQLBean.setLongitud(atributoSql.getLongitud()!=null?atributoSql.getLongitud():0);
	    		campoSQLBean.setPrecision(atributoSql.getPrecision()!=null?atributoSql.getPrecision():0);
	    		campoSQLBean.setFlgObligatorio(atributoSql.getObligatorio()==1?true:false);
	    		campoSQLBean.setFkUnoMuchos(atributoSql.getFkUnoMucho()==1?true:false);
	    		campoSQLBean.setValorDefecto(atributoSql.getValDefecto());
	    		campoSQLBean.setFk(obtenerCampoSQLBean(atributoSql.getFkTabla(), atributoSql.getFkCampo()));
	    		campoSQLBean.setTabla(parseTablaBean(atributoSql.getTabla()));
	    	}
	    	return campoSQLBean;
	 }
	 
	 private TablaBean parseTablaBean(Tabla tabla){
	    	TablaBean tablaBean = null;
	    	if(tabla!=null){
	    		tablaBean = new TablaBean();
	    		tablaBean.setEsquema(tabla.getEsquema());
	    		tablaBean.setNombre(tabla.getNombre());
	    		tablaBean.setCodigo(tabla.getCodTabla());
	    	}
	    	return tablaBean;
	    }
}
