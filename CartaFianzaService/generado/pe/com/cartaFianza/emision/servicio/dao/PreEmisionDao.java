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
				solicitud.setValoNumerico(solicitudEntity.getValoNumerico());
				solicitud.setDescripcion(solicitudEntity.getDescripcion());
				solicitud.setMonto(solicitudEntity.getMonto());
				solicitud.setCodigoSolicitud(solicitudEntity.getCodigoSolicitud());
				solicitud.setValorNumericoC(solicitudEntity.getValorNumericoC());
				solicitud.setFlagAdicional(solicitudEntity.isFlagAdicional());
				solicitud.setValorNumericoB(solicitudEntity.getValorNumericoB());
				solicitud.setFlagPrincipal(solicitudEntity.isFlagPrincipal());
				solicitud.setVigencia(solicitudEntity.getVigencia());
				solicitud.setEvento(solicitudEntity.getEvento());
				solicitud.setFechaFin(solicitudEntity.getFechaFin());
				solicitud.setMontoFinal(solicitudEntity.getMontoFinal());
			}
		}
		return solicitud;
	}

	private SolicitudEntity parseRegistrarSolicitudEntity(Solicitud solicitud){
		SolicitudEntity solicitudEntity = null;
		if(solicitud!=null){
			solicitudEntity = new SolicitudEntity();
			solicitudEntity.setCodigoProceso(solicitud.getCodigoProceso());
			solicitudEntity.setValoNumerico(solicitud.getValoNumerico());
			solicitudEntity.setDescripcion(solicitud.getDescripcion());
			solicitudEntity.setFechaFin(solicitud.getFechaFin());
			solicitudEntity.setCodigoSolicitud(solicitud.getCodigoSolicitud());
			solicitudEntity.setFlagAdicional(solicitud.isFlagAdicional());
			solicitudEntity.setValorNumericoC(solicitud.getValorNumericoC());
			solicitudEntity.setCodigoSolicitud(solicitud.getCodigoSolicitud());
			solicitudEntity.setValorNumericoC(solicitud.getValorNumericoC());
			solicitudEntity.setEvento(solicitud.getEvento());
			solicitudEntity.setMonto(solicitud.getMonto());
			solicitudEntity.setMontoFinal(solicitud.getMontoFinal());
			solicitudEntity.setValorNumericoB(solicitud.getValorNumericoB());
			solicitudEntity.setFlagPrincipal(solicitud.isFlagPrincipal());
			solicitudEntity.setVigencia(solicitud.getVigencia());
		}
		return solicitudEntity;
	}

	private Solicitud parseRegistrarSolicitud(SolicitudEntity solicitudEntity){
		Solicitud solicitud = null;
		if(solicitudEntity!=null){
			solicitud = new Solicitud();
			solicitud.setCodigoProceso(solicitudEntity.getCodigoProceso());
			solicitud.setValoNumerico(solicitudEntity.getValoNumerico());
			solicitud.setDescripcion(solicitudEntity.getDescripcion());
			solicitud.setFechaFin(solicitudEntity.getFechaFin());
			solicitud.setCodigoSolicitud(solicitudEntity.getCodigoSolicitud());
			solicitud.setFlagAdicional(solicitudEntity.isFlagAdicional());
			solicitud.setValorNumericoC(solicitudEntity.getValorNumericoC());
			solicitud.setCodigoSolicitud(solicitudEntity.getCodigoSolicitud());
			solicitud.setValorNumericoC(solicitudEntity.getValorNumericoC());
			solicitud.setEvento(solicitudEntity.getEvento());
			solicitud.setMonto(solicitudEntity.getMonto());
			solicitud.setMontoFinal(solicitudEntity.getMontoFinal());
			solicitud.setValorNumericoB(solicitudEntity.getValorNumericoB());
			solicitud.setFlagPrincipal(solicitudEntity.isFlagPrincipal());
			solicitud.setVigencia(solicitudEntity.getVigencia());
		}
		return solicitud;
	}

}
