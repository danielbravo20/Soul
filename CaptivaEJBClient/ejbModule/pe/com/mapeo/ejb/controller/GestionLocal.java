package pe.com.mapeo.ejb.controller;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface GestionLocal {
	
	Object cargarAccion(HttpServletRequest request, HttpServletResponse response) throws Exception;

}