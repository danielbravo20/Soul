package pe.com.soul.cartaFianza.emision.tarea;

import java.math.BigDecimal;

import pe.com.soul.cartaFianza.bean.Cliente;
import pe.com.soul.cartaFianza.bean.Solicitud;
import pe.com.soul.core.modelo.Tarea;
import pe.com.soul.core.modelo.TareaPlantilla;
import pe.com.soul.core.servicio.BaseTareaServicio;
import pe.com.soul.core.servicio.impl.BaseTareaServicioImpl;

public abstract class PreEmisionCompletarSolicitudV1 extends BaseTareaServicioImpl implements BaseTareaServicio{
	
	@Override
	public Object trabajar(Tarea tarea) throws Exception {
		Solicitud solicitud = new Solicitud();
		solicitud.setTipoFianza("LIC");
		solicitud.setMonedaFianza("USD");
		solicitud.setMontoFianza(new BigDecimal(10000));
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
	public TareaPlantilla definirProximaTareaCompletar(Tarea tarea) throws Exception {
		TareaPlantilla tareaPlantilla = new TareaPlantilla();
		tareaPlantilla.setCodigoTareaPlantilla(2);
		tareaPlantilla.setAleas("evaluarSolicitudV1");
		tareaPlantilla.setEstado(1);
		tareaPlantilla.setNombre("Evaluar Solicitud");
		tareaPlantilla.setOrden(2);
		tareaPlantilla.setPrioridad(1);
		tareaPlantilla.setVersion("v1.0.0");
		return tareaPlantilla;
	}

	@Override
	public String definirProximoDuenoCompletar(Tarea tarea) throws Exception {
		return null;
	}
}
