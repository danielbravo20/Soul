package pe.com.cartaFianza.emision.servicio.dao;

import javax.ejb.Local;

import pe.com.cartaFianza.bean.Solicitud;

@Local
public interface EmisionDaoLocal{

	Solicitud registrar(Solicitud solicitud) throws Exception;

	Solicitud verResumen(Solicitud solicitud) throws Exception;

	Solicitud verDetalle(Solicitud solicitud) throws Exception;

}
