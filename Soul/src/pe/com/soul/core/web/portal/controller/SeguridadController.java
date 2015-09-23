package pe.com.soul.core.web.portal.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import pe.com.soul.core.service.SeguridadServiceLocal;
import pe.com.soul.core.web.bean.Respuesta;

/**
 * Servlet implementation class SeguridadController
 */
@WebServlet("/portal/seguridadController")
public class SeguridadController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
    
	@EJB
	private SeguridadServiceLocal seguridadServiceLocal;
    
    public SeguridadController() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter printWriter = response.getWriter();
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").create();
		Respuesta respuesta = new Respuesta();
		
		try{
			respuesta.setResultado(true);
			respuesta.setRespuesta(seguridadServiceLocal.obtenerUsuarios());
		}catch (Exception e) {
			respuesta.setResultado(false);
			respuesta.setMensajeError(e.getMessage());
		}
		printWriter.print(gson.toJson(respuesta));
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
