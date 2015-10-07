package pe.com.soul.core.web.controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import pe.com.soul.core.modelo.MensajeValidacion;
import pe.com.soul.core.modelo.Usuario;
import pe.com.soul.core.servicio.BaseTareaServicio;
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
			
			Usuario usuario = obtenerUsuario(request, session);
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
			}
		}
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter printWriter = response.getWriter();
		printWriter.print(gson.toJson(respuesta));
	}
	
	public Respuesta accionReclamar(HttpServletRequest request, HttpServletResponse response, Usuario usuario, String tkiid) throws Exception {
		Respuesta respuesta = new Respuesta();
		try{
			respuesta.setRespuesta(getBaseTareaService().accionReclamar(new Long(tkiid)));
			respuesta.setResultado(true);
		}catch(Exception e){
			respuesta.setResultado(false);
			respuesta.setMensajeError(e.getMessage());
		}
		return respuesta;
	}
	
	public Respuesta accionLiberar(HttpServletRequest request, HttpServletResponse response, Usuario usuario, String tkiid) throws Exception {
		Respuesta respuesta = new Respuesta();
		try{
			respuesta.setRespuesta(getBaseTareaService().accionLiberar(new Long(tkiid)));
			respuesta.setResultado(true);
		}catch(Exception e){
			respuesta.setResultado(false);
			respuesta.setMensajeError(e.getMessage());
		}
		return respuesta;
	}
	
	public Respuesta accionTrabajar(HttpServletRequest request, HttpServletResponse response, Usuario usuario, String tkiid) throws Exception {
		Respuesta respuesta = new Respuesta();
		try{
			respuesta.setRespuesta(getBaseTareaService().accionTrabajar(new Long(tkiid)));
			respuesta.setResultado(true);
		}catch(Exception e){
			respuesta.setResultado(false);
			respuesta.setMensajeError(e.getMessage());
		}
		return respuesta;
	}
	
	public Respuesta accionCompletar(HttpServletRequest request, HttpServletResponse response, Usuario usuario, String tkiid) throws Exception {
		Respuesta respuesta = new Respuesta();
		MensajeValidacion mensajeValidacion = getTareaUtil().validacionCompletar(request, response);
		if(mensajeValidacion.isConforme()){
			Object objeto = getTareaUtil().poblarCompletar(request, response);
			respuesta.setRespuesta(getBaseTareaService().accionCompletar(new Long(tkiid), objeto));
			respuesta.setResultado(true);
		}else{
			respuesta.setResultado(false);
			respuesta.setMensajeError(mensajeValidacion.getMensaje());
		}
		return respuesta;
	}
	
	public Respuesta accionCancelar(HttpServletRequest request, HttpServletResponse response, Usuario usuario, String tkiid) throws Exception {
		Respuesta respuesta = new Respuesta();
		
		return respuesta;
	}
	
	public Respuesta accionRechazar(HttpServletRequest request, HttpServletResponse response, Usuario usuario, String tkiid) throws Exception {
		Respuesta respuesta = new Respuesta();
		
		return respuesta;
	}
	
	public Respuesta accionObservar(HttpServletRequest request, HttpServletResponse response, Usuario usuario, String tkiid) throws Exception {
		Respuesta respuesta = new Respuesta();
		
		return respuesta;
	}
	
	public abstract TareaUtil getTareaUtil();
	
	public abstract BaseTareaServicio getBaseTareaService();
	
}
