package pe.com.soul.core.web.portal.controller;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pe.com.soul.core.bean.Usuario;
import pe.com.soul.core.seguridad.service.SeguridadServiceLocal;

/**
 * Servlet implementation class PortalController
 */
@WebServlet("/portal/portalController")
public class PortalController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	@EJB
	SeguridadServiceLocal seguridadServiceLocal;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PortalController() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		try {
			System.out.println("userPrincipal.name: "+request.getUserPrincipal().getName());
			Usuario usuario = (Usuario)seguridadServiceLocal.obtenerUsuario(request.getUserPrincipal().getName());
			System.out.println("usuario:: "+usuario);
			//System.out.println("usuario: "+usuario.getNomCompleto());
			//System.out.println("id: "+usuario.getCodUsuario());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
