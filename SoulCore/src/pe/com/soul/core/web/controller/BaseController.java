package pe.com.soul.core.web.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import pe.com.soul.core.modelo.Usuario;
import pe.com.soul.core.seguridad.service.SeguridadServiceLocal;
import pe.com.soul.core.service.portal.PortalServiceLocal;
import pe.com.soul.core.web.bean.Respuesta;

public abstract class BaseController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@EJB
	SeguridadServiceLocal seguridadServiceLocal;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			operacion(request, response);
		} catch (Exception e) {
			request.setCharacterEncoding("UTF-8");
			Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").create();
			Respuesta respuesta = new Respuesta();
			respuesta.setResultado(false);
			respuesta.setMensajeError(e.getMessage());
			e.printStackTrace();
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter printWriter = response.getWriter();
			printWriter.print(gson.toJson(respuesta));
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			operacion(request, response);
		} catch (Exception e) {
			request.setCharacterEncoding("UTF-8");
			Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").create();
			Respuesta respuesta = new Respuesta();
			respuesta.setResultado(false);
			respuesta.setMensajeError(e.getMessage());
			e.printStackTrace();
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter printWriter = response.getWriter();
			printWriter.print(gson.toJson(respuesta));
		}
	}
	
	protected Usuario obtenerUsuario(HttpServletRequest request, HttpSession session) throws Exception{
		Usuario usuario = (Usuario)session.getAttribute(Usuario.SESSION_USUARIO_WEB_SOUL);
		if(usuario==null){
			usuario = seguridadServiceLocal.registrarUsuario(request.getUserPrincipal().getName(), session.getId(), request.getRemoteHost(), request.getRemoteAddr());
			session.setAttribute(Usuario.SESSION_USUARIO_WEB_SOUL, usuario);
		}
		return usuario;
	}
	
	protected abstract void operacion(HttpServletRequest request, HttpServletResponse response) throws Exception;
	
}