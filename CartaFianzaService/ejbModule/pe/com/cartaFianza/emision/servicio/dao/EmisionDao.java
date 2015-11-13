package pe.com.cartaFianza.emision.servicio.dao;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import pe.com.cartaFianza.bean.Solicitud;
import pe.com.cartaFianza.dao.entity.SolicitudEntity;

@Stateless
@LocalBean
public class EmisionDao extends BaseDao<SolicitudEntity> implements EmisionDaoLocal {

	public EmisionDao() {
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