package pe.com.soul.core.modelo;

public class MensajeValidacion extends BaseBean{

	private static final long serialVersionUID = 1L;
		
	private boolean conforme = false;
	private String parametro;
	private String mensaje;
	private Object respuesta;
	
	public MensajeValidacion() {
	}
	
	public MensajeValidacion(boolean conforme, String mensaje, String parametro) {
		super();
		this.conforme = conforme;
		this.mensaje = mensaje;
		this.parametro = parametro;
	}
	
	public MensajeValidacion(boolean conforme, String mensaje) {
		super();
		this.conforme = conforme;
		this.mensaje = mensaje;
	}
	
	public MensajeValidacion(boolean conforme, Object respuesta) {
		super();
		this.conforme = conforme;
		this.respuesta = respuesta;
	}
	
	public MensajeValidacion(boolean conforme) {
		super();
		this.conforme = conforme;
	}
	
	public boolean isConforme() {
		return conforme;
	}
	public void setConforme(boolean conforme) {
		this.conforme = conforme;
	}
	public String getParametro() {
		return parametro;
	}
	public void setParametro(String parametro) {
		this.parametro = parametro;
	}
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public Object getRespuesta() {
		return respuesta;
	}

	public void setRespuesta(Object respuesta) {
		this.respuesta = respuesta;
	}
	
	
}
