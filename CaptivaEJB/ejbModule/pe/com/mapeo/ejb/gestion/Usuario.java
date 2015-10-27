package pe.com.mapeo.ejb.gestion;

import javax.ejb.Stateless;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pe.com.mapeo.dao.Jpo;
import pe.com.mapeo.dao.Tabla;
import pe.com.mapeo.ejb.controller.GestionBase;
import pe.com.mapeo.util.MapeoUtil;

@Stateless
public class Usuario extends GestionBase implements UsuarioLocal {

	// Cuando acceder carga los datos del usuario
	public Object cargarUsuario(Jpo jpo, HttpServletRequest request,HttpServletResponse response) throws Exception {
		return jpo.tabla("USUARIO","USU").obtener("*");
	}
	
	public Object listar(Jpo jpo, HttpServletRequest request,HttpServletResponse response) throws Exception {
		return jpo.tabla("USUARIO").seleccionar("*");
	}
	
	public Object eliminar(Jpo jpo, HttpServletRequest request,HttpServletResponse response) throws Exception {
		jpo.autoCommit(false);
			jpo.tabla("EQUIPO","USU").eliminar();
			jpo.tabla("USUARIO","USU").eliminar();
		jpo.commitear();
		return true;
	}
	
	public Object consultarClave(Jpo jpo, HttpServletRequest request,HttpServletResponse response) throws Exception {
		Tabla usuario = jpo.tabla("USUARIO","USU");
		String claveAnterior = request.getParameter("claveAnterior");
		if(claveAnterior!=null && claveAnterior.length()>0){
			usuario.setDataWhere("clave", MapeoUtil.getMD5(request.getParameter("claveAnterior")));
		}
		return usuario.obtener("*");
	}
	
	public Object editar(Jpo jpo, HttpServletRequest request,HttpServletResponse response) throws Exception {
		//jpo.autoCommit(false);
		Tabla usuario = jpo.tabla("USUARIO","USU");
			String claveAnterior = request.getParameter("claveAnterior");
			if(claveAnterior!=null && claveAnterior.length()>0){
				usuario.setDataWhere("clave", MapeoUtil.getMD5(request.getParameter("claveAnterior")));
				usuario.setData("clave", MapeoUtil.getMD5(usuario.getData("clave")));
			} else {
				usuario.removeData("clave");
			}
			if(usuario.obtener("*")!=null){
				usuario.editar();
			}
		jpo.commitear();
		return true;
	}
	
	public Object registrar(Jpo jpo, HttpServletRequest request,HttpServletResponse response) throws Exception {
		//jpo.autoCommit(false);
		Tabla usuario = jpo.tabla("USUARIO","USU");
			usuario.setData("clave", MapeoUtil.getMD5(usuario.getData("clave")));
		usuario.registrar();
		jpo.commitear();
		return true;
	}
	
}