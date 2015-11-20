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
				solicitud.setMonto(solicitudEntity.getMonto());
				solicitud.setEvento(solicitudEntity.getEvento());
				solicitud.setVigencia(solicitudEntity.getVigencia());
				solicitud.setCodigoSolicitud(solicitudEntity.getCodigoSolicitud());
			}
		}
		return solicitud;
	}

	private SolicitudEntity parseRegistrarSolicitudEntity(Solicitud solicitud){
		SolicitudEntity solicitudEntity = null;
		if(solicitud!=null){
			solicitudEntity = new SolicitudEntity();
			solicitudEntity.setCodigoProceso(solicitud.getCodigoProceso());
			solicitudEntity.setMonto(solicitud.getMonto());
		}
		return solicitudEntity;
	}

	private Solicitud parseRegistrarSolicitud(SolicitudEntity solicitudEntity){
		Solicitud solicitud = null;
		if(solicitudEntity!=null){
			solicitud = new Solicitud();
			solicitud.setCodigoProceso(solicitudEntity.getCodigoProceso());
			solicitud.setMonto(solicitudEntity.getMonto());
		}
		return solicitud;
	}

}
