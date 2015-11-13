package pe.com.cartaFianza.emision.servicio;

import pe.com.soul.core.modelo.Proceso;
import pe.com.soul.core.modelo.UsuarioPortal;
import javax.ejb.Local;

import pe.com.cartaFianza.bean.Solicitud;

import pe.com.soul.core.servicio.BaseProcesoServicioLocal;

@Local
public interface EmisionServicioLocal extends BaseProcesoServicioLocal{

	Proceso accionCrearInstancia(UsuarioPortal usuarioPortal, Object objeto) throws Exception;

	Solicitud accionVerResumen(UsuarioPortal usuarioPortal, Solicitud solicitud) throws Exception;

	Solicitud accionVerDetalle(UsuarioPortal usuarioPortal, Solicitud solicitud) throws Exception;

}