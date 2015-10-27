package pe.com.cartaFianza.ejecucioncartafianzav1.controlador;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pe.com.soul.core.modelo.MensajeValidacion;
import pe.com.soul.core.web.util.ValidacionUtil;
import pe.com.soul.core.web.util.ProcesoUtil;

import pe.com.cartaFianza.bean.*;

public class PreEjecucionCartaFianzaV1Util implements ProcesoUtil{

	@Override
	public MensajeValidacion validacionCampos(HttpServletRequest request, HttpServletResponse response) {
		MensajeValidacion mensajeValidacion = new MensajeValidacion();
		if (ValidacionUtil.cadenaNoValidaRequestParameter(request.getParameter("solicitud.cliente.numeroDocumento"))){
			mensajeValidacion.setConforme(false);
			mensajeValidacion.setMensaje("numeroDocumento es invalido");
			return mensajeValidacion;
		}

		if (ValidacionUtil.longNoValidoRequestParameter(request.getParameter("solicitud.numeroCartaFianza"))){
			mensajeValidacion.setConforme(false);
			mensajeValidacion.setMensaje("numeroCartaFianza es invalido");
			return mensajeValidacion;
		}

		if (ValidacionUtil.cadenaNoValidaRequestParameter(request.getParameter("solicitud.cliente.tipoDocumento"))){
			mensajeValidacion.setConforme(false);
			mensajeValidacion.setMensaje("tipoDocumento es invalido");
			return mensajeValidacion;
		}

		if (ValidacionUtil.decimalNoValidoRequestParameter(request.getParameter("solicitud.tipoCambioSBS"))){
			mensajeValidacion.setConforme(false);
			mensajeValidacion.setMensaje("tipoCambioSBS es invalido");
			return mensajeValidacion;
		}

		if (ValidacionUtil.cadenaNoValidaRequestParameter(request.getParameter("solicitud.codigoAgenciaOrigen"))){
			mensajeValidacion.setConforme(false);
			mensajeValidacion.setMensaje("codigoAgenciaOrigen es invalido");
			return mensajeValidacion;
		}

		if (ValidacionUtil.cadenaNoValidaRequestParameter(request.getParameter("solicitud.cliente.razonSocial"))){
			mensajeValidacion.setConforme(false);
			mensajeValidacion.setMensaje("razonSocial es invalido");
			return mensajeValidacion;
		}

		mensajeValidacion.setConforme(true);
		return mensajeValidacion;
	}

	@Override
	public Object poblarObjetos(HttpServletRequest request, HttpServletResponse response) {
		Solicitud solicitud = new Solicitud();
		solicitud.setPlazoVigencia(15);
		solicitud.setTipoSolicitud("EJE");
		solicitud.setFechaModificacion(new java.sql.Timestamp(new java.util.Date().getTime()));
		solicitud.setFechaRegistro(new java.sql.Timestamp(new java.util.Date().getTime()));
		solicitud.setUsuarioModificacion(request.getUserPrincipal().getName());
		if(request.getParameter(solicitud.numeroCartaFianza)!=null && request.getParameter(solicitud.numeroCartaFianza).trim().length()>0){
			solicitud.setNumeroCartaFianza(Long.parseLong(request.getParameter(solicitud.numeroCartaFianza)));
		}
		solicitud.setAleasProceso("BECFEJ");
		solicitud.setEstado("ING");
		if(request.getParameter(solicitud.tipoCambioSBS)!=null && request.getParameter(solicitud.tipoCambioSBS).trim().length()>0){
			solicitud.setTipoCambioSBS(new java.math.BigDecimal(request.getParameter(solicitud.tipoCambioSBS).trim()));
		}
		solicitud.setPiid(request.getParameter(piid));
		solicitud.setCodigoAgenciaOrigen(request.getParameter(solicitud.codigoAgenciaOrigen));
		solicitud.setUsuarioRegistro(request.getUserPrincipal().getName());
		solicitud.setFlagErrorServicioFirmas(false);
		if(request.getParameter(solicitud.codigo)!=null && request.getParameter(solicitud.codigo).trim().length()>0){
			solicitud.setCodigo(Long.parseLong(request.getParameter(solicitud.codigo)));
		}
		Liquidacion liquidacion = new Liquidacion();
		if(request.getParameter(null)!=null && request.getParameter(null).trim().length()>0){
			liquidacion.setCodigoSolicitud(Long.parseLong(request.getParameter(null)));
		}
		solicitud.setLiquidacion(liquidacion);
		Linea linea = new Linea();
		if(request.getParameter(null)!=null && request.getParameter(null).trim().length()>0){
			linea.setCodigoSolicitud(Long.parseLong(request.getParameter(null)));
		}
		solicitud.setLinea(linea);
		Cliente cliente = new Cliente();
		cliente.setNumeroDocumento(request.getParameter(solicitud.cliente.numeroDocumento));
		cliente.setTipoDocumento(request.getParameter(solicitud.cliente.tipoDocumento));
		if(request.getParameter(solicitud.cliente.codigoSolicitud)!=null && request.getParameter(solicitud.cliente.codigoSolicitud).trim().length()>0){
			cliente.setCodigoSolicitud(Long.parseLong(request.getParameter(solicitud.cliente.codigoSolicitud)));
		}
		if(request.getParameter(solicitud.cliente.codigoIBS)!=null && request.getParameter(solicitud.cliente.codigoIBS).trim().length()>0){
			cliente.setCodigoIBS(Integer.parseInt(request.getParameter(solicitud.cliente.codigoIBS)));
		}
		cliente.setRazonSocial(request.getParameter(solicitud.cliente.razonSocial));
		solicitud.setCliente(cliente);
		Cuenta cuenta = new Cuenta();
		if(request.getParameter(null)!=null && request.getParameter(null).trim().length()>0){
			cuenta.setCodigoSolicitud(Long.parseLong(request.getParameter(null)));
		}
		solicitud.setCuenta(cuenta);
		CartaFianza cartafianza = new CartaFianza();
		if(request.getParameter(null)!=null && request.getParameter(null).trim().length()>0){
			cartafianza.setCodigoSolicitud(Long.parseLong(request.getParameter(null)));
		}
		solicitud.setCartaFianza(cartafianza);
		return solicitud;
	}
}