package pe.com.mapeo.ejb.seguridad;

import javax.ejb.Stateless;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pe.com.mapeo.dao.Jpo;
import pe.com.mapeo.dao.Tabla;
import pe.com.mapeo.ejb.controller.GestionBase;
import pe.com.mapeo.util.MapeoUtil;

@Stateless
public class Acceso extends GestionBase implements AccesoLocal {
	
	public final static String rutaPrincipal = "mapeo/index.html";

    public Object acceder(Jpo jpo,HttpServletRequest request,HttpServletResponse response) throws Exception{
    	
    	Tabla tUsuario = jpo.tabla("USUARIO","USU");
    	
    	tUsuario.setDataWhere("CLAVE",MapeoUtil.getMD5(tUsuario.getDataWhere("CLAVE")));
    	
    	if(tUsuario.contar()>0){
    		return rutaPrincipal+"?usuario="+tUsuario.getDataWhere("COD_USUARIO");
    	} else {
    		return false;
    	}
    	
    }

}