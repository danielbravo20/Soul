package pe.com.soul.core.web.controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import pe.com.soul.core.modelo.MensajeValidacion;
import pe.com.soul.core.modelo.Usuario;
import pe.com.soul.core.servicio.BaseProcesoServicio;
import pe.com.soul.core.web.bean.Respuesta;
import pe.com.soul.core.web.util.ProcesoUtil;

public abstract class BaseProcesoController extends BaseController{

	private static final long serialVersionUID = 1L;

	protected void operacion(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("UTF-8");
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").create();
		
		String accion = request.getParameter("accion");
		String codigoProceso = request.getParameter("codigoProceso");
		
		Respuesta respuesta = null;
		HttpSession session = request.getSession(false);
		
		if(accion != null && session!=null){
			
			Usuario usuario = obtenerUsuario(request, session);
			if("crear".equals(accion)){
				respuesta = accionCrear(request, response, usuario);
			}else if(codigoProceso!=null){
				if("resumen".equals(accion)){
					respuesta = accionResumen(request, response, usuario);
				}else if("detalle".equals(accion)){
					respuesta = accionDetalle(request, response, usuario);
				}
			}
			
		}
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter printWriter = response.getWriter();
		printWriter.print(gson.toJson(respuesta));
	}
	
	public Respuesta accionCrear(HttpServletRequest request, HttpServletResponse response, Usuario usuario) throws Exception {
		Respuesta respuesta = new Respuesta();
		MensajeValidacion mensajeValidacion =  getProcesoUtil().validacionCampos(request, response);
		
		if(mensajeValidacion.isConforme()){
			respuesta.setResultado(true);
			respuesta.setRespuesta(getBaseProcesoService().accionCrearInstancia(usuario));
		}else{
			respuesta.setResultado(false);
			respuesta.setRespuesta(mensajeValidacion);
		}
		
		return respuesta;
	}
	
	public abstract ProcesoUtil getProcesoUtil();
	
	public abstract BaseProcesoServicio getBaseProcesoService();
	
	protected abstract Respuesta accionResumen(HttpServletRequest request, HttpServletResponse response, Usuario usuario) throws Exception;
	
	protected abstract Respuesta accionDetalle(HttpServletRequest request, HttpServletResponse response, Usuario usuario) throws Exception;
	
}
