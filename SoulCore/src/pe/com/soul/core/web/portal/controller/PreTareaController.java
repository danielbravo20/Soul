package pe.com.soul.core.web.portal.controller;

import java.io.PrintWriter;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import pe.com.soul.core.modelo.UsuarioPortal;
import pe.com.soul.core.service.portal.TareaServiceLocal;
import pe.com.soul.core.web.bean.Respuesta;
import pe.com.soul.core.web.controller.BaseController;

/**
 * Servlet implementation class TareaController
 */
public class PreTareaController extends BaseController {
	
	private static final long serialVersionUID = 1L;

	@EJB
	TareaServiceLocal tareaServiceLocal;  
	
	@Override
	protected void operacion(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("UTF-8");
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").create();
		
		String accion = request.getParameter("accion");
		Respuesta respuesta = new Respuesta();
		HttpSession session = request.getSession(false);
		
		if(accion != null && session!=null){
			
			UsuarioPortal usuario = obtenerUsuario(request, session);
			
			if("reclamadas".equals(accion)){
				respuesta.setRespuesta(tareaServiceLocal.obtenerReclamadas(usuario));
				respuesta.setResultado(true);
			}else if("disponibles".equals(accion)){
				respuesta.setRespuesta(tareaServiceLocal.obtenerDisponibles(usuario));
				respuesta.setResultado(true);
			}else if("consultar".equals(accion)){
				respuesta.setRespuesta(tareaServiceLocal.consultarTarea(Long.parseLong(request.getParameter("codigoProceso"))));
				respuesta.setResultado(true);
			}
		}
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter printWriter = response.getWriter();
		printWriter.print(gson.toJson(respuesta));
		
	}
       


}
