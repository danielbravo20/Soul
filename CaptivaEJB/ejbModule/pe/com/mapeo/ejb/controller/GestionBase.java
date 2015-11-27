package pe.com.mapeo.ejb.controller;

import java.lang.reflect.InvocationTargetException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pe.com.mapeo.dao.Jpo;
import pe.com.mapeo.ejb.controller.GestionLocal;

public abstract class GestionBase implements GestionLocal {

	@Override
	public Object cargarAccion(HttpServletRequest request,HttpServletResponse response) throws Exception {
		java.lang.reflect.Method method;
		try {
			method = this.getClass().getMethod(request.getParameter("metodo"), Jpo.class, HttpServletRequest.class, HttpServletResponse.class);
		} catch (SecurityException e) {
			throw new Exception("Acceso a un m�todo protegido");
		} catch (NoSuchMethodException e) {
			throw new Exception("Verifique el nombre del m�todo: "+request.getParameter("metodo")); 
		} catch (UnsupportedOperationException e) {
			throw new Exception("Metodo incorrecto"); 
		}
		Jpo jpo = new Jpo(request,true);
		try {
			return method.invoke(this, jpo, request, response) ;
		} catch (IllegalArgumentException e) {
			throw new Exception("Metodo Argumentos incorrectos");
		} catch (IllegalAccessException e) {
			throw new Exception("Metodo Acceso protegido");
		} catch (InvocationTargetException e) {
			System.out.println("excepcion");
			e.getCause().printStackTrace();
			throw new Exception(e.getCause().getMessage()+" | ");
		} finally {
			//System.out.println("finalizando");
			jpo.finalizar();
		}
	}

}