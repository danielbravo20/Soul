package pe.com.soul.cartaFianza.proceso;

import javax.annotation.security.DeclareRoles;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import pe.com.soul.cartaFianza.proceso.v1.PreEmisionCartaFianzaService;
import pe.com.soul.core.modelo.Proceso;
import pe.com.soul.core.modelo.TareaPlantilla;
import pe.com.soul.core.modelo.Usuario;

/**
 * Session Bean implementation class EmisionCartaFianzaService
 */
@Stateless
@LocalBean
@DeclareRoles("Administrador")
public class EmisionCartaFianzaService extends PreEmisionCartaFianzaService implements EmisionCartaFianzaServiceLocal {

	@PermitAll
	@Override
	public Proceso accionCrearInstancia(Usuario usuario) throws Exception {
		// TODO Auto-generated method stub
		return super.accionCrearInstancia(usuario);
	}
	
	@RolesAllowed("Administrador")
	@Override
	public TareaPlantilla definirPrimeraTarea(Proceso proceso, Usuario usuario)
			throws Exception {
		// TODO Auto-generated method stub
		return super.definirPrimeraTarea(proceso, usuario);
	}

}
