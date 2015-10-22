package pe.com.mapeo.ejb.gestion;
import javax.ejb.Local;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pe.com.mapeo.dao.Jpo;
import pe.com.mapeo.ejb.controller.GestionLocal;

@Local
public interface PrincipalLocal extends GestionLocal {
	
	// Mapeo_v4Web/gestorController?paquete=gestion&modulo=Principal&metodo=listarProyectos
	public Object listarProyectos(Jpo jpo,HttpServletRequest request,HttpServletResponse response) throws Exception;
	
	// Mapeo_v4Web/gestorController?paquete=gestion&modulo=Principal&metodo=listarVersiones&VER_W_codigoProyecto=BECartaFianza
	public Object listarVersiones(Jpo jpo,HttpServletRequest request,HttpServletResponse response) throws Exception;
	
	// Mapeo_v4Web/gestorController?paquete=gestion&modulo=Principal&metodo=registrarVersiones&VER_codigoProyecto=BECartaFianza&VER_numeroVersion=1&VER_tiempoEstimado=22&VER_fechaCreacion=2015-01-28
	public Object registrarVersiones(Jpo jpo, HttpServletRequest request,HttpServletResponse response) throws Exception;
	
	public Object listarRegistros(Jpo jpo, HttpServletRequest request,HttpServletResponse response) throws Exception;

}