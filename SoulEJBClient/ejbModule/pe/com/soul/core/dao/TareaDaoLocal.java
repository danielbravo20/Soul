package pe.com.soul.core.dao;

import java.util.List;

import javax.ejb.Local;

import pe.com.soul.core.modelo.Tarea;
import pe.com.soul.core.modelo.Usuario;

@Local
public interface TareaDaoLocal {

	Tarea guardar(Tarea tarea) throws Exception;
	List<Tarea> obtenerReclamadas(Usuario usuario) throws Exception;
	List<Tarea> obtenerDisponibles(Usuario usuario) throws Exception;
}
