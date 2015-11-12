package pe.com.cartaFianza.emision.servicio.dao;

import pe.com.cartaFianza.bean.Solicitud;
import pe.com.cartaFianza.dao.entity.SolicitudEntity;
import pe.com.soul.core.servicio.dao.BaseDao;


public abstract class PreEmisionDao extends BaseDao<SolicitudEntity>{

	public PreEmisionDao() {
		super(SolicitudEntity.class);
	}

	public Solicitud registrar(Solicitud solicitud) throws Exception {
		return parseSolicitud(guardarEntity(parseSolicitudEntity(solicitud)));
	}

	public Solicitud verResumen(Solicitud solicitud) throws Exception {
		return null;
	}

	public Solicitud verDetalle(Solicitud solicitud) throws Exception {
		return null;
	}
	
	private SolicitudEntity parseSolicitudEntity(Solicitud solicitud){
		SolicitudEntity solicitudEntity = null;
		if(solicitud!=null){
			solicitudEntity = new SolicitudEntity();
			solicitudEntity.setEvento(solicitud.getEvento());
			solicitudEntity.setMonto(solicitud.getMonto());
		}
		return solicitudEntity;
	}
	
	private Solicitud parseSolicitud(SolicitudEntity solicitudEntity){
		Solicitud solicitud = null;
		if(solicitudEntity!=null){
			solicitud = new Solicitud();
			solicitud.setCodigoSolicitud(solicitudEntity.getCodigoSolicitud());
			solicitud.setEvento(solicitudEntity.getEvento());
			solicitud.setMonto(solicitudEntity.getMonto());
			solicitud.setVigencia(solicitudEntity.getVigencia());
		}
		return solicitud;
	}

}