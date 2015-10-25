package pe.com.soul.core.web.controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import pe.com.soul.core.modelo.MensajeValidacion;
import pe.com.soul.core.modelo.UsuarioPortal;
import pe.com.soul.core.servicio.BaseTareaServicioLocal;
import pe.com.soul.core.web.bean.Respuesta;
import pe.com.soul.core.web.util.TareaUtil;

public abstract class BaseTareaController extends BaseController{

	private static final long serialVersionUID = 1L;

	protected void operacion(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("UTF-8");
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").create();
		
		String accion = request.getParameter("accion");
		String tkiid = request.getParameter("tkiid");
		
		Respuesta respuesta = null;
		HttpSession session = request.getSession(false);
		
		if(accion != null && session!=null && tkiid!=null){
			
			UsuarioPortal usuario = obtenerUsuario(request, session);
			if("reclamar".equals(accion)){
				respuesta = accionReclamar(request, response, usuario, tkiid);
			}else if("liberar".equals(accion)){
				respuesta = accionLiberar(request, response, usuario, tkiid);
			}else if("trabajar".equals(accion)){
				respuesta = accionTrabajar(request, response, usuario, tkiid);
			}else if("completar".equals(accion)){
				respuesta = accionCompletar(request, response, usuario, tkiid);
			}else if("cancelar".equals(accion)){
				respuesta = accionCancelar(request, response, usuario, tkiid);
			}else if("rechazar".equals(accion)){
				respuesta = accionRechazar(request, response, usuario, tkiid);
			}else if("observar".equals(accion)){
				respuesta = accionObservar(request, response, usuario, tkiid);
			}else if("transferir".equals(accion) && request.getParameter("nuevoDueno")!=null){
				respuesta = accionTransferir(request, response, usuario, tkiid);
			}
		}
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter printWriter = response.getWriter();
		printWriter.print(gson.toJson(respuesta));
	}
	
	public Respuesta accionReclamar(HttpServletRequest request, HttpServletResponse response, UsuarioPortal usuario, String tkiid) throws Exception {
		Respuesta respuesta = new Respuesta();
		try{
			respuesta.setRespuesta(getBaseTareaServicioLocal().accionReclamar(new Long(tkiid)));
			respuesta.setResultado(true);
		}catch(Exception e){
			respuesta.setResultado(false);
			respuesta.setMensajeError(e.getMessage());
		}
		return respuesta;
	}
	
	public Respuesta accionLiberar(HttpServletRequest request, HttpServletResponse response, UsuarioPortal usuario, String tkiid) throws Exception {
		Respuesta respuesta = new Respuesta();
		try{
			respuesta.setRespuesta(getBaseTareaServicioLocal().accionLiberar(new Long(tkiid)));
			respuesta.setResultado(true);
		}catch(Exception e){
			respuesta.setResultado(false);
			respuesta.setMensajeError(e.getMessage());
		}
		return respuesta;
	}
	
	public Respuesta accionTrabajar(HttpServletRequest request, HttpServletResponse response, UsuarioPortal usuario, String tkiid) throws Exception {
		Respuesta respuesta = new Respuesta();
		try{
			respuesta.setRespuesta(getBaseTareaServicioLocal().accionTrabajar(new Long(tkiid)));
			respuesta.setResultado(true);
		}catch(Exception e){
			respuesta.setResultado(false);
			respuesta.setMensajeError(e.getMessage());
		}
		return respuesta;
	}
	
	public Respuesta accionCompletar(HttpServletRequest request, HttpServletResponse response, UsuarioPortal usuario, String tkiid) throws Exception {
		Respuesta respuesta = new Respuesta();
		MensajeValidacion mensajeValidacion = getTareaUtil().validacionCompletar(request, response);
		if(mensajeValidacion.isConforme()){
			Object objeto = getTareaUtil().poblarCompletar(request, response);
			respuesta.setRespuesta(getBaseTareaServicioLocal().accionCompletar(new Long(tkiid), objeto));
			respuesta.setResultado(true);
		}else{
			respuesta.setResultado(false);
			respuesta.setMensajeError(mensajeValidacion.getMensaje());
		}
		return respuesta;
	}
	
	public Respuesta accionCancelar(HttpServletRequest request, HttpServletResponse response, UsuarioPortal usuario, String tkiid) throws Exception {
		Respuesta respuesta = new Respuesta();
		MensajeValidacion mensajeValidacion = getTareaUtil().validacionCancelar(request, response);
		if(mensajeValidacion.isConforme()){
			Object objeto = getTareaUtil().poblarCancelar(request, response);
			respuesta.setRespuesta(getBaseTareaServicioLocal().accionCancelar(new Long(tkiid), objeto));
			respuesta.setResultado(true);
		}else{
			respuesta.setResultado(false);
			respuesta.setMensajeError(mensajeValidacion.getMensaje());
		}
		return respuesta;
	}
	
	public Respuesta accionRechazar(HttpServletRequest request, HttpServletResponse response, UsuarioPortal usuario, String tkiid) throws Exception {
		Respuesta respuesta = new Respuesta();
		MensajeValidacion mensajeValidacion = getTareaUtil().validacionRechazar(request, response);
		if(mensajeValidacion.isConforme()){
			Object objeto = getTareaUtil().poblarRechazar(request, response);
			respuesta.setRespuesta(getBaseTareaServicioLocal().accionRechazar(new Long(tkiid), objeto));
			respuesta.setResultado(true);
		}else{
			respuesta.setResultado(false);
			respuesta.setMensajeError(mensajeValidacion.getMensaje());
		}
		return respuesta;
	}
	
	public Respuesta accionObservar(HttpServletRequest request, HttpServletResponse response, UsuarioPortal usuario, String tkiid) throws Exception {
		Respuesta respuesta = new Respuesta();
		MensajeValidacion mensajeValidacion = getTareaUtil().validacionObservar(request, response);
		if(mensajeValidacion.isConforme()){
			Object objeto = getTareaUtil().poblarObservar(request, response);
			respuesta.setRespuesta(getBaseTareaServicioLocal().accionObservar(new Long(tkiid), objeto));
			respuesta.setResultado(true);
		}else{
			respuesta.setResultado(false);
			respuesta.setMensajeError(mensajeValidacion.getMensaje());
		}
		return respuesta;
	}
	
	public Respuesta accionTransferir(HttpServletRequest request, HttpServletResponse response, UsuarioPortal usuario, String tkiid) throws Exception {
		Respuesta respuesta = new Respuesta();
		try{
			respuesta.setRespuesta(getBaseTareaServicioLocal().accionTransferir(new Long(tkiid), request.getParameter("nuevoDueno")));
			respuesta.setResultado(true);
		}catch(Exception e){
			respuesta.setResultado(false);
			respuesta.setMensajeError(e.getMessage());
		}
		return respuesta;
	}
	
	public abstract TareaUtil getTareaUtil();
	
	public abstract BaseTareaServicioLocal getBaseTareaServicioLocal();
	
}
