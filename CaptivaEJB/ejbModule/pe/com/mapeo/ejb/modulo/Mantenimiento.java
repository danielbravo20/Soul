package pe.com.mapeo.ejb.modulo;

import java.util.HashMap;
import java.util.Map;

import javax.ejb.Stateless;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pe.com.mapeo.dao.Jpo;
import pe.com.mapeo.dao.Tabla;
import pe.com.mapeo.ejb.controller.GestionBase;

@Stateless
public class Mantenimiento extends GestionBase implements MantenimientoLocal {

	public Object registrar(Jpo jpo, HttpServletRequest request,HttpServletResponse response) throws Exception {
		jpo.autoCommit(false);
			Tabla mantenimiento = jpo.tabla("MANTENIMIENTO","MAN");
				mantenimiento.registrar();
			Tabla mantenimientoRoles = jpo.tabla("MANTENIMIENTO_ROL","MRO");
				mantenimientoRoles.registrarMultiple();
			Tabla atributos = jpo.tabla("MANTENIMIENTO_ATRIBUTO","AMA");
				atributos.registrarMultiple();
		jpo.commitear();
		return true;
	}
	
	public Object eliminar(Jpo jpo, HttpServletRequest request,HttpServletResponse response) throws Exception {
		jpo.autoCommit(false);
			jpo.tabla("MANTENIMIENTO","MAN").eliminar();
			jpo.tabla("MANTENIMIENTO_ROL","MAN").eliminar();
			jpo.tabla("MANTENIMIENTO_ATRIBUTO","MAN").eliminar();
		jpo.commitear();
		return true;
	}
	
	public Object listar(Jpo jpo, HttpServletRequest request,HttpServletResponse response) throws Exception {
		Map<String,Object> carga = new HashMap<String,Object>();
		jpo.autoCommit(false);
			carga.put("MANTENIMIENTO", jpo.tabla("MANTENIMIENTO","MAN").seleccionar("COD_MANTENIMIENTO,NOMBRE,COD_ESQUEMA,COD_DATASOURCE"));
			carga.put("MANTENIMIENTO_ROL", jpo.tabla("MANTENIMIENTO_ROL","MAN").seleccionar("*"));
		jpo.commitear();
		return carga;
	}
	
	public Object editarCargar(Jpo jpo, HttpServletRequest request,HttpServletResponse response) throws Exception {
		Map<String,Object> carga = new HashMap<String,Object>();
		jpo.autoCommit(false);
			carga.put("MANTENIMIENTO", jpo.tabla("MANTENIMIENTO","MAN").obtener("*"));
			carga.put("MANTENIMIENTO_ATRIBUTO", jpo.tabla("MANTENIMIENTO_ATRIBUTO","MAN").seleccionar("*"));
			carga.put("MANTENIMIENTO_ROL", jpo.tabla("MANTENIMIENTO_ROL","MAN").seleccionar("*"));
		jpo.commitear();
		return carga;
	}
	
	public Object editar(Jpo jpo, HttpServletRequest request,HttpServletResponse response) throws Exception {
		jpo.autoCommit(false);
		if(((Boolean) jpo.tabla("MANTENIMIENTO","MAN").editar())){
			
			Tabla mantenimientoRoles = jpo.tabla("MANTENIMIENTO_ROL","MRO");
			if(((Boolean) mantenimientoRoles.eliminar())){
				mantenimientoRoles.registrarMultiple();
			}
			
			Tabla atributos = jpo.tabla("MANTENIMIENTO_ATRIBUTO","AMA");
			if(((Boolean) atributos.eliminar())){
				atributos.registrarMultiple();
			}
			jpo.commitear();
			return true;
		} else {
			return false;
		}
		
	}

}