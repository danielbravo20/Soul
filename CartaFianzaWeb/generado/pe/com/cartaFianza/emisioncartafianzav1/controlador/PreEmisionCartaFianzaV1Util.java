package pe.com.cartaFianza.emisioncartafianzav1.controlador;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pe.com.soul.core.modelo.MensajeValidacion;
import pe.com.soul.core.web.util.ValidacionUtil;
import pe.com.soul.core.web.util.ProcesoUtil;

import pe.com.cartaFianza.bean.*;

public class PreEmisionCartaFianzaV1Util implements ProcesoUtil{

	@Override
	public MensajeValidacion validacionCampos(HttpServletRequest request, HttpServletResponse response) {
		MensajeValidacion mensajeValidacion = new MensajeValidacion();
		if (ValidacionUtil.cadenaNoValidaRequestParameter(request.getParameter("solicitud.codigoTipoFianza"))){
			mensajeValidacion.setConforme(false);
			mensajeValidacion.setMensaje("codigoTipoFianza es invalido");
			return mensajeValidacion;
		}

		if (ValidacionUtil.cadenaNoValidaRequestParameter(request.getParameter("solicitud.cliente.tipoDocumento"))){
			mensajeValidacion.setConforme(false);
			mensajeValidacion.setMensaje("tipoDocumento es invalido");
			return mensajeValidacion;
		}

		if (ValidacionUtil.cadenaNoValidaRequestParameter(request.getParameter("solicitud.cliente.razonSocial"))){
			mensajeValidacion.setConforme(false);
			mensajeValidacion.setMensaje("razonSocial es invalido");
			return mensajeValidacion;
		}

		if (ValidacionUtil.cadenaNoValidaRequestParameter(request.getParameter("solicitud.cliente.numeroDocumento"))){
			mensajeValidacion.setConforme(false);
			mensajeValidacion.setMensaje("numeroDocumento es invalido");
			return mensajeValidacion;
		}

		if (ValidacionUtil.cadenaNoValidaRequestParameter(request.getParameter("solicitud.codigoAgenciaOrigen"))){
			mensajeValidacion.setConforme(false);
			mensajeValidacion.setMensaje("codigoAgenciaOrigen es invalido");
			return mensajeValidacion;
		}

		if (ValidacionUtil.cadenaNoValidaRequestParameter(request.getParameter("solicitud.codigoMoneda"))){
			mensajeValidacion.setConforme(false);
			mensajeValidacion.setMensaje("codigoMoneda es invalido");
			return mensajeValidacion;
		}

		if (ValidacionUtil.decimalNoValidoRequestParameter(request.getParameter("solicitud.monto"))){
			mensajeValidacion.setConforme(false);
			mensajeValidacion.setMensaje("monto es invalido");
			return mensajeValidacion;
		}

		mensajeValidacion.setConforme(true);
		return mensajeValidacion;
	}

	@Override
	public Object poblarObjetos(HttpServletRequest request, HttpServletResponse response) {
		Solicitud solicitud = new Solicitud();
		solicitud.setFechaRegistro(new java.sql.Timestamp(new java.util.Date().getTime()));
		solicitud.setUsuarioModificacion(request.getUserPrincipal().getName());
		solicitud.setPlazoVigencia(15);
		solicitud.setPiid(request.getParameter("piid"));
		solicitud.setEstado("ING");
		solicitud.setUsuarioRegistro(request.getUserPrincipal().getName());
		solicitud.setCodigoTipoFianza(request.getParameter("solicitud.codigoTipoFianza"));
		solicitud.setTipoSolicitud("EMI");
		if(request.getParameter("solicitud.codigo")!=null && request.getParameter("solicitud.codigo").trim().length()>0){
			solicitud.setCodigo(Long.parseLong(request.getParameter("solicitud.codigo")));
		}
		solicitud.setFlagErrorServicioFirmas(false);
		solicitud.setAleasProceso("BECFEM");
		solicitud.setCodigoAgenciaOrigen(request.getParameter("solicitud.codigoAgenciaOrigen"));
		solicitud.setCodigoMoneda(request.getParameter("solicitud.codigoMoneda"));
		if(request.getParameter("solicitud.monto")!=null && request.getParameter("solicitud.monto").trim().length()>0){
			solicitud.setMonto(new java.math.BigDecimal(request.getParameter("solicitud.monto").trim()));
		}
		solicitud.setFechaModificacion(new java.sql.Timestamp(new java.util.Date().getTime()));
		Linea linea = new Linea();
		if(request.getParameter("null")!=null && request.getParameter("null").trim().length()>0){
			linea.setCodigoSolicitud(Long.parseLong(request.getParameter("null")));
		}
		solicitud.setLinea(linea);
		Cliente cliente = new Cliente();
		cliente.setTipoDocumento(request.getParameter("solicitud.cliente.tipoDocumento"));
		cliente.setRazonSocial(request.getParameter("solicitud.cliente.razonSocial"));
		if(request.getParameter("solicitud.cliente.codigoIBS")!=null && request.getParameter("solicitud.cliente.codigoIBS").trim().length()>0){
			cliente.setCodigoIBS(Integer.parseInt(request.getParameter("solicitud.cliente.codigoIBS")));
		}
		if(request.getParameter("solicitud.cliente.codigoSolicitud")!=null && request.getParameter("solicitud.cliente.codigoSolicitud").trim().length()>0){
			cliente.setCodigoSolicitud(Long.parseLong(request.getParameter("solicitud.cliente.codigoSolicitud")));
		}
		cliente.setNumeroDocumento(request.getParameter("solicitud.cliente.numeroDocumento"));
		solicitud.setCliente(cliente);
		Cuenta cuenta = new Cuenta();
		if(request.getParameter("null")!=null && request.getParameter("null").trim().length()>0){
			cuenta.setCodigoSolicitud(Long.parseLong(request.getParameter("null")));
		}
		solicitud.setCuenta(cuenta);
		return solicitud;
	}
}