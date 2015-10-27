package pe.com.mapeo.ejb.modulo;

import javax.ejb.Stateless;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pe.com.mapeo.dao.Jpo;
import pe.com.mapeo.dao.Tabla;
import pe.com.mapeo.ejb.controller.GestionBase;

@Stateless
public class Proyecto extends GestionBase implements ProyectoLocal {
	
	public Object editar(Jpo jpo, HttpServletRequest request,HttpServletResponse response) throws Exception {
		jpo.autoCommit(false);
		jpo.tabla("PROYECTO","PRO").editar();
		Tabla equipo = jpo.tabla("EQUIPO","EQU");
		if(((Boolean) equipo.eliminar())){
			equipo.registrarMultiple();
		}
		jpo.commitear();
		return true;
	}
	
	public Object registrar(Jpo jpo, HttpServletRequest request,HttpServletResponse response) throws Exception {
		jpo.autoCommit(false);
		jpo.tabla("PROYECTO","PRO").registrar();
		jpo.tabla("EQUIPO","EQU").registrarMultiple();
		jpo.commitear();
		return true;
	}
	
	public Object eliminar(Jpo jpo, HttpServletRequest request,HttpServletResponse response) throws Exception {
		jpo.autoCommit(false);
		jpo.tabla("EQUIPO","PRO").eliminar();
		jpo.tabla("PROYECTO","PRO").eliminar();
		/*
		jpo.tabla("Configuracion","PRO").eliminar();
		jpo.tabla("Rol","PRO").eliminar();
		jpo.tabla("Mantenimiento","PRO").eliminar();
		jpo.tabla("MantenimientoAtributo","PRO").eliminar();
		jpo.tabla("MantenimientoRoles","PRO").eliminar();
		jpo.tabla("Esquema","PRO").eliminar();
		jpo.tabla("DataSource","PRO").eliminar();
		jpo.tabla("Clase","PRO").eliminar();
		jpo.tabla("Atributo","PRO").eliminar();
		*/
		jpo.commitear();
		return true;
	}
	
	// EQUIPO
	public Object editarEquipo(Jpo jpo, HttpServletRequest request,HttpServletResponse response) throws Exception {
		jpo.tabla("EQUIPO","EQU").editar();
		return jpo.tabla("EQUIPO","EQU").obtener("*");
	}
	
}