package pe.com.soul.core.proceso.web.controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import pe.com.soul.core.modelo.Usuario;
import pe.com.soul.core.web.bean.Respuesta;
import pe.com.soul.core.web.controller.BaseController;

public abstract class BaseProcesoController extends BaseController{

	private static final long serialVersionUID = 1L;

	protected void operacion(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("UTF-8");
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").create();
		
		String accion = request.getParameter("accion");
		Respuesta respuesta = null;
		HttpSession session = request.getSession(false);
		
		if(accion != null && session!=null){
			
			Usuario usuario = obtenerUsuario(request, session);
			
			if("crear".equals(accion)){
				respuesta = accionCrear(request, response, usuario);
			}else if("resumen".equals(accion)){
				respuesta = accionResumen(request, response, usuario);
			}else if("detalle".equals(accion)){
				respuesta = accionDetalle(request, response, usuario);
			}else if("reclamar".equals(accion)){
				
			}else if("trabajar".equals(accion)){
			
			}else if("completar".equals(accion)){
				
			}
		}
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter printWriter = response.getWriter();
		printWriter.print(gson.toJson(respuesta));
	}
	
	protected abstract Respuesta accionCrear(HttpServletRequest request, HttpServletResponse response, Usuario usuario);
	
	protected abstract Respuesta accionResumen(HttpServletRequest request, HttpServletResponse response, Usuario usuario);
	
	protected abstract Respuesta accionDetalle(HttpServletRequest request, HttpServletResponse response, Usuario usuario);
	
}
