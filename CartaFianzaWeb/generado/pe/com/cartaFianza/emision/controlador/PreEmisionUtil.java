package pe.com.cartaFianza.emision.controlador;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pe.com.soul.core.modelo.MensajeValidacion;
import pe.com.soul.core.web.util.ValidacionUtil;
import pe.com.soul.core.web.util.ProcesoUtil;

import pe.com.cartaFianza.bean.*;

public class PreEmisionUtil implements ProcesoUtil{

	@Override
	public MensajeValidacion validacionCampos(HttpServletRequest request, HttpServletResponse response) {
		MensajeValidacion mensajeValidacion = new MensajeValidacion();
		if (ValidacionUtil.cadenaNoValidaRequestParameter(request.getParameter("solicitud.descripcion"))){
			mensajeValidacion.setConforme(false);
			mensajeValidacion.setMensaje("descripcion es invalido");
			return mensajeValidacion;
		}

		if (ValidacionUtil.decimalNoValidoRequestParameter(request.getParameter("solicitud.montoFinal"))){
			mensajeValidacion.setConforme(false);
			mensajeValidacion.setMensaje("montoFinal es invalido");
			return mensajeValidacion;
		}

		if (ValidacionUtil.longNoValidoRequestParameter(request.getParameter("solicitud.valorNumericoB"))){
			mensajeValidacion.setConforme(false);
			mensajeValidacion.setMensaje("valorNumericoB es invalido");
			return mensajeValidacion;
		}

		mensajeValidacion.setConforme(true);
		return mensajeValidacion;
	}

	@Override
	public Object poblarObjetos(HttpServletRequest request, HttpServletResponse response) {
		Solicitud solicitud = new Solicitud();
		if(request.getParameter("solicitud.valoNumerico")!=null && request.getParameter("solicitud.valoNumerico").trim().length()>0){
			solicitud.setValoNumerico(Long.parseLong(request.getParameter("solicitud.valoNumerico")));
		}
		solicitud.setDescripcion(request.getParameter("solicitud.descripcion"));
		if(request.getParameter("solicitud.fechaFin")!=null && request.getParameter("solicitud.fechaFin").length() > 0 ){
			try{
				java.text.SimpleDateFormat fechafinSDF = new java.text.SimpleDateFormat("dd/MM/yyyy");
				java.util.Date dateFechaFin= fechafinSDF.parse(request.getParameter("solicitud.fechaFin"));
				solicitud.setFechaFin(new java.util.Date(dateFechaFin.getTime()));
			}catch (java.text.ParseException pe) { 
				pe.printStackTrace();
			}
		}
		if(request.getParameter("solicitud.codigoSolicitud")!=null && request.getParameter("solicitud.codigoSolicitud").trim().length()>0){
			solicitud.setCodigoSolicitud(Long.parseLong(request.getParameter("solicitud.codigoSolicitud")));
		}
		if(request.getParameter("solicitud.flagAdicional")!=null && ( request.getParameter("solicitud.flagAdicional").equals(1) || request.getParameter("solicitud.flagAdicional").equalsIgnoreCase("true"))) {
			solicitud.setFlagAdicional(true);}
		else{
			solicitud.setFlagAdicional(false);
		}
		if(request.getParameter("solicitud.valorNumericoC")!=null && request.getParameter("solicitud.valorNumericoC").trim().length()>0){
			solicitud.setValorNumericoC(Integer.parseInt(request.getParameter("solicitud.valorNumericoC")));
		}
		if(request.getParameter("solicitud.codigoSolicitud")!=null && request.getParameter("solicitud.codigoSolicitud").trim().length()>0){
			solicitud.setCodigoSolicitud(Long.parseLong(request.getParameter("solicitud.codigoSolicitud")));
		}
		if(request.getParameter("solicitud.valorNumericoC")!=null && request.getParameter("solicitud.valorNumericoC").trim().length()>0){
			solicitud.setValorNumericoC(Integer.parseInt(request.getParameter("solicitud.valorNumericoC")));
		}
		solicitud.setEvento(request.getParameter("solicitud.evento"));
		if(request.getParameter("solicitud.monto")!=null && request.getParameter("solicitud.monto").trim().length()>0){
			solicitud.setMonto(new java.math.BigDecimal(request.getParameter("solicitud.monto").trim()));
		}
		if(request.getParameter("solicitud.montoFinal")!=null && request.getParameter("solicitud.montoFinal").trim().length()>0){
			solicitud.setMontoFinal(new java.math.BigDecimal(request.getParameter("solicitud.montoFinal").trim()));
		}
		if(request.getParameter("solicitud.valorNumericoB")!=null && request.getParameter("solicitud.valorNumericoB").trim().length()>0){
			solicitud.setValorNumericoB(Long.parseLong(request.getParameter("solicitud.valorNumericoB")));
		}
		if(request.getParameter("solicitud.flagPrincipal")!=null && ( request.getParameter("solicitud.flagPrincipal").equals(1) || request.getParameter("solicitud.flagPrincipal").equalsIgnoreCase("true"))) {
			solicitud.setFlagPrincipal(true);}
		else{
			solicitud.setFlagPrincipal(false);
		}
		if(request.getParameter("solicitud.vigencia")!=null && request.getParameter("solicitud.vigencia").length() > 0 ){
			try{
				java.text.SimpleDateFormat vigenciaSDF = new java.text.SimpleDateFormat("dd/MM/yyyy");
				java.util.Date dateVigencia= vigenciaSDF.parse(request.getParameter("solicitud.vigencia"));
				solicitud.setVigencia(new java.util.Date(dateVigencia.getTime()));
			}catch (java.text.ParseException pe) { 
				pe.printStackTrace();
			}
		}
		return solicitud;
	}
	@Override
	public MensajeValidacion validacionCamposVerResumen(HttpServletRequest request, HttpServletResponse response) {
		MensajeValidacion mensajeValidacion = new MensajeValidacion();
		if (ValidacionUtil.longNoValidoRequestParameter(request.getParameter("codigoProceso"))){
			mensajeValidacion.setConforme(false);
			mensajeValidacion.setMensaje("ingrese el codigo de proceso");
			return mensajeValidacion;
		}
		mensajeValidacion.setConforme(true);
		return mensajeValidacion;
	}
	@Override
	public Object poblarObjetosVerResumen(HttpServletRequest request, HttpServletResponse response) {
		Solicitud solicitud = new Solicitud();
		solicitud.setCodigoProceso(new Long(request.getParameter("codigoProceso").trim()));
		return solicitud;
	}
	@Override
	public MensajeValidacion validacionCamposVerDetalle(HttpServletRequest request, HttpServletResponse response) {
		MensajeValidacion mensajeValidacion = new MensajeValidacion();
		if (ValidacionUtil.longNoValidoRequestParameter(request.getParameter("codigoProceso"))){
			mensajeValidacion.setConforme(false);
			mensajeValidacion.setMensaje("ingrese el codigo de proceso");
			return mensajeValidacion;
		}
		mensajeValidacion.setConforme(true);
		return mensajeValidacion;
	}
	@Override
	public Object poblarObjetosVerDetalle(HttpServletRequest request, HttpServletResponse response) {
		Solicitud solicitud = new Solicitud();
		solicitud.setCodigoProceso(new Long(request.getParameter("codigoProceso").trim()));
		return solicitud;
	}
}