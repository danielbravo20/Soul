package pe.com.mapeo.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pe.com.captiva.servicio.FabricaSoulServiceLocal;

/**
 * Servlet implementation class GenerarController
 */
@WebServlet("/generarController")
public class GenerarController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@EJB
	FabricaSoulServiceLocal fabricaSoulServiceLocal;
    
    public GenerarController() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		crear(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		crear(request, response);
	}

	protected void crear(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String usuario 			= request.getParameter("usuario");
		String codigoProyecto 	= request.getParameter("codigoProyecto");
		
		PrintWriter retorno = response.getWriter();
		
		try {
			fabricaSoulServiceLocal.crearProyecto(usuario, new Integer(codigoProyecto),request);
			retorno.print("true");
		} catch (Exception e) {
			e.printStackTrace();
			retorno.print(e.getMessage());
		}
	}
	
}
