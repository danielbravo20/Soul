package pe.com.soul.core.web.portal.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import pe.com.soul.core.modelo.Usuario;
import pe.com.soul.core.web.bean.Respuesta;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Servlet implementation class ProcesoController
 */
@WebServlet("/procesoController")
public class ProcesoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProcesoController() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		operacion(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		operacion(request, response);
	}

	protected void operacion(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").create();
		
		String accion = request.getParameter("accion");
		Respuesta respuesta = new Respuesta();
		HttpSession session = request.getSession(false);
		
		if(accion != null && session!=null){
			try {
				if("crear".equalsIgnoreCase(accion)){
					
					respuesta.setResultado(true);
					//respuesta.setRespuesta(usuario);				
				
				}else if("detalle".equalsIgnoreCase(accion)){
				
				}else if("resumen".equalsIgnoreCase(accion)){
					
				}
			} catch (Exception e) {
				respuesta.setResultado(false);
				respuesta.setMensajeError(e.getMessage());
				e.printStackTrace();
			}
		}
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter printWriter = response.getWriter();
		printWriter.print(gson.toJson(respuesta));
	}
	
}
