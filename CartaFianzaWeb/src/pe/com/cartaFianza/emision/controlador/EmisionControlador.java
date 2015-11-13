package pe.com.cartaFianza.emision.controlador;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pe.com.soul.core.modelo.UsuarioPortal;
import pe.com.soul.core.web.bean.Respuesta;

import javax.servlet.annotation.WebServlet;

@WebServlet("/portal/emision")
public class EmisionControlador extends PreEmisionControlador{

	private static final long serialVersionUID = 1L;

	@Override
	public Respuesta accionCrear(HttpServletRequest request, HttpServletResponse response, UsuarioPortal usuarioPortal) throws Exception {
		return super.accionCrear(request, response, usuarioPortal);
	}

	@Override
	public Respuesta accionResumen(HttpServletRequest request, HttpServletResponse response, UsuarioPortal usuarioPortal) throws Exception {
		return super.accionResumen(request, response, usuarioPortal);
	}

	@Override
	public Respuesta accionDetalle(HttpServletRequest request, HttpServletResponse response, UsuarioPortal usuarioPortal) throws Exception {
		return super.accionDetalle(request, response, usuarioPortal);
	}

}