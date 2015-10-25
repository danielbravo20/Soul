package pe.com.soul.core.dao;

import java.util.List;

import javax.ejb.Local;

import pe.com.soul.core.modelo.Tarea;
import pe.com.soul.core.modelo.UsuarioPortal;

@Local
public interface TareaDaoLocal {

	Tarea guardar(Tarea tarea) throws Exception;
	List<Tarea> consultarTarea(long codigoProceso) throws Exception;
	List<Tarea> obtenerReclamadas(UsuarioPortal usuario) throws Exception;
	List<Tarea> obtenerDisponibles(UsuarioPortal usuario) throws Exception;
	Tarea obtener(Long codigoTarea) throws Exception;
	Tarea actualizar(Tarea tarea) throws Exception;
}
