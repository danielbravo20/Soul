package pe.com.cartaFianza.emision;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import pe.com.cartaFianza.emision.EmisionCartaFianzaServiceLocal;
import pe.com.cartaFianza.emision.PreEmisionCartaFianzaService;

/**
 * Session Bean implementation class EmisionCartaFianzaService
 */
@Stateless
@LocalBean
public class EmisionCartaFianzaService extends PreEmisionCartaFianzaService implements EmisionCartaFianzaServiceLocal {
	

}
