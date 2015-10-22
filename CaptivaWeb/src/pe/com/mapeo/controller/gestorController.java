package pe.com.mapeo.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pe.com.mapeo.ejb.controller.GestionLocal;
import pe.com.mapeo.entidad.Respuesta;

import com.google.gson.Gson;

/**
 * Servlet implementation class gestorController
 */
public class gestorController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Context context;   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public gestorController() {
        super();
        // TODO Auto-generated constructor stub
    }
    
	public void init() throws ServletException {
		super.init();
		try {
			context = new InitialContext();
		} catch (NamingException e) {
			e.printStackTrace();
			throw new ServletException("no se pudo iniciar el contexto...", e.getCause());
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ejecutar(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ejecutar(request,response);
	}
	
	private void ejecutar(HttpServletRequest request, HttpServletResponse response) throws IOException{
		response.setContentType("application/json; charset=UTF-8");
		Gson json = new Gson();
		PrintWriter retorno = response.getWriter();
		GestionLocal gestionLocal;
		try {
			System.out.println("paquete: "+request.getParameter("paquete"));
			System.out.println("clase: "+request.getParameter("clase"));
			
			
			gestionLocal = (GestionLocal) context.lookup("java:app/CaptivaEJB/"+request.getParameter("clase")+"!pe.com.mapeo.ejb."+request.getParameter("paquete")+"."+request.getParameter("clase")+"Local");
			//gestionLocal = (GestionLocal) context.lookup("ejblocal:pe.com.mapeo.ejb."+request.getParameter("paquete")+"."+request.getParameter("clase")+"Local");
			String encabezado = request.getParameter("encabezado");
			if(encabezado != null && encabezado.equals("0")){
				retorno.print(json.toJson(gestionLocal.cargarAccion(request,response)));
			} else {
				retorno.print(json.toJson(new Respuesta(gestionLocal.cargarAccion(request,response))));
			}
		} catch (Exception e) {
			retorno.print(json.toJson(new Respuesta(false,e.getMessage(),e.getClass().toString())));
			e.printStackTrace();
		}
	};

}