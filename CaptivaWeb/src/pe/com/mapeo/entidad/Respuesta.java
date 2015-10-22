package pe.com.mapeo.entidad;

public class Respuesta {

	private static final long serialVersionUID = 1L;
	
	private Object objeto;
	private boolean resultado = false;
	private String mensaje;
	
	public Respuesta(boolean resultado) {
		this.resultado = resultado;
	}
	
	public Respuesta(boolean resultado,String mensaje) {
		this.resultado = resultado;
		this.mensaje = mensaje;
	}
	
	public Respuesta(Object objeto) {
		this.resultado = true;
		this.objeto = objeto;
	}
	
	public Respuesta(boolean resultado,Object objeto) {
		this.resultado = resultado;
		this.objeto = objeto;
	}
	
	public Respuesta(boolean resultado,String mensaje,Object objeto) {
		this.resultado = resultado;
		this.mensaje = mensaje;
		this.objeto = objeto;
	}

	public Object getObjeto() {
		return objeto;
	}

	public void setObjeto(Object objeto) {
		this.objeto = objeto;
	}

	public boolean isResultado() {
		return resultado;
	}

	public void setResultado(boolean resultado) {
		this.resultado = resultado;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
		
}