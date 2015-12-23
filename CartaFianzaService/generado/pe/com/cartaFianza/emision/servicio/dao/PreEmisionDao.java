package pe.com.cartaFianza.emision.servicio.dao;

import java.util.List;

import pe.com.cartaFianza.bean.*;
import pe.com.cartaFianza.dao.entity.*;

public abstract class PreEmisionDao extends BaseDao<SolicitudEntity>{

	public PreEmisionDao() {
		super(SolicitudEntity.class);
	}

	public Solicitud registrar(Solicitud solicitud) throws Exception {
		return parseRegistrarSolicitud(guardarEntity(parseRegistrarSolicitudEntity(solicitud)));
	}

	public Solicitud verResumen(Solicitud solicitud) throws Exception {
		if(solicitud!=null){
			String consulta = "select a from SolicitudEntity a where a.codigoProceso =:parametro ";
			List<SolicitudEntity> solicitudEntitys = buscarRegistros(consulta, "parametro", solicitud.getCodigoProceso());
			if(solicitudEntitys!=null){
				SolicitudEntity solicitudEntity = solicitudEntitys.get(0);
				solicitud.setCodigoSolicitud(solicitudEntity.getCodigoSolicitud());
				solicitud.setEvento(solicitudEntity.getEvento());
			}
		}
		return solicitud;
	}

	public Solicitud verDetalle(Solicitud solicitud) throws Exception {
		if(solicitud!=null){
			String consulta = "select a from SolicitudEntity a where a.codigoProceso =:parametro ";
			List<SolicitudEntity> solicitudEntitys = buscarRegistros(consulta, "parametro", solicitud.getCodigoProceso());
			if(solicitudEntitys!=null){
				SolicitudEntity solicitudEntity = solicitudEntitys.get(0);
				solicitud.setEvento(solicitudEntity.getEvento());
				solicitud.setValoNumerico(solicitudEntity.getValoNumerico());
				solicitud.setVigencia(solicitudEntity.getVigencia());
				solicitud.setValorNumericoB(solicitudEntity.getValorNumericoB());
				solicitud.setMontoFinal(solicitudEntity.getMontoFinal());
				solicitud.setFlagPrincipal(solicitudEntity.isFlagPrincipal());
				solicitud.setDescripcion(solicitudEntity.getDescripcion());
				solicitud.setFechaFin(solicitudEntity.getFechaFin());
				solicitud.setCodigoSolicitud(solicitudEntity.getCodigoSolicitud());
				solicitud.setValorNumericoC(solicitudEntity.getValorNumericoC());
				solicitud.setMonto(solicitudEntity.getMonto());
				solicitud.setFlagAdicional(solicitudEntity.isFlagAdicional());
			}
		}
		return solicitud;
	}

	private SolicitudEntity parseRegistrarSolicitudEntity(Solicitud solicitud){
		SolicitudEntity solicitudEntity = null;
		if(solicitud!=null){
			solicitudEntity = new SolicitudEntity();
			solicitudEntity.setCodigoProceso(solicitud.getCodigoProceso());
			solicitudEntity.setValorNumericoC(solicitud.getValorNumericoC());
			solicitudEntity.setCodigoSolicitud(solicitud.getCodigoSolicitud());
			solicitudEntity.setValorNumericoB(solicitud.getValorNumericoB());
			solicitudEntity.setVigencia(solicitud.getVigencia());
			solicitudEntity.setFechaFin(solicitud.getFechaFin());
			solicitudEntity.setEvento(solicitud.getEvento());
			solicitudEntity.setDescripcion(solicitud.getDescripcion());
			solicitudEntity.setFlagAdicional(solicitud.isFlagAdicional());
			solicitudEntity.setValorNumericoC(solicitud.getValorNumericoC());
			solicitudEntity.setFlagPrincipal(solicitud.isFlagPrincipal());
			solicitudEntity.setMonto(solicitud.getMonto());
			solicitudEntity.setValoNumerico(solicitud.getValoNumerico());
			solicitudEntity.setCodigoSolicitud(solicitud.getCodigoSolicitud());
			solicitudEntity.setMontoFinal(solicitud.getMontoFinal());
		}
		return solicitudEntity;
	}

	private Solicitud parseRegistrarSolicitud(SolicitudEntity solicitudEntity){
		Solicitud solicitud = null;
		if(solicitudEntity!=null){
			solicitud = new Solicitud();
			solicitud.setCodigoProceso(solicitudEntity.getCodigoProceso());
			solicitud.setValorNumericoC(solicitudEntity.getValorNumericoC());
			solicitud.setCodigoSolicitud(solicitudEntity.getCodigoSolicitud());
			solicitud.setValorNumericoB(solicitudEntity.getValorNumericoB());
			solicitud.setVigencia(solicitudEntity.getVigencia());
			solicitud.setFechaFin(solicitudEntity.getFechaFin());
			solicitud.setEvento(solicitudEntity.getEvento());
			solicitud.setDescripcion(solicitudEntity.getDescripcion());
			solicitud.setFlagAdicional(solicitudEntity.isFlagAdicional());
			solicitud.setValorNumericoC(solicitudEntity.getValorNumericoC());
			solicitud.setFlagPrincipal(solicitudEntity.isFlagPrincipal());
			solicitud.setMonto(solicitudEntity.getMonto());
			solicitud.setValoNumerico(solicitudEntity.getValoNumerico());
			solicitud.setCodigoSolicitud(solicitudEntity.getCodigoSolicitud());
			solicitud.setMontoFinal(solicitudEntity.getMontoFinal());
		}
		return solicitud;
	}

}
