package pe.com.cartaFianza.emision.tarea;

import java.math.BigDecimal;

import pe.com.cartaFianza.bean.Cliente;
import pe.com.cartaFianza.bean.Solicitud;
import pe.com.soul.core.modelo.Tarea;
import pe.com.soul.core.modelo.TareaPlantilla;
import pe.com.soul.core.servicio.BaseTareaServicio;
import pe.com.soul.core.servicio.impl.BaseTareaServicioImpl;

public abstract class PreEmisionEvaluarSolicitudV1 extends BaseTareaServicioImpl implements BaseTareaServicio{
	
	@Override
	public Object trabajar(Tarea tarea) throws Exception {
		Solicitud solicitud = new Solicitud();
		solicitud.setCodigoTipoFianza("LIC");
		solicitud.setCodigoMoneda("USD");
		solicitud.setMonto(new BigDecimal(10000));
		Cliente cliente = new Cliente();
		cliente.setTipoDocumento("RUC");
		cliente.setNumeroDocumento("10012251001");
		cliente.setRazonSocial("Metales Arequipa S.A.C.");
		solicitud.setCliente(cliente);
		return solicitud;
	}
	
	@Override
	public Object completar(Tarea tarea) throws Exception {
		//se proceso y guarda la info que llego del formulario
		System.out.println("se guardo el objeto: "+tarea.getObjeto());
		return tarea.getObjeto();
	}
	
	@Override
	public TareaPlantilla proximaTareaCompletar(Tarea tarea) throws Exception {
		return null;
	}

	@Override
	public String proximoDuenoCompletar(Tarea tarea) throws Exception {
		return null;
	}
	
	@Override
	public Object cancelar(Tarea tarea) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object rechazar(Tarea tarea) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object observar(Tarea tarea) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TareaPlantilla proximaTareaObservar(Tarea tarea) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String proximoDuenoObservar(Tarea tarea) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Object transferir(Tarea tarea, String nuevoUsuario) throws Exception {
		return null;
	}
	
}
