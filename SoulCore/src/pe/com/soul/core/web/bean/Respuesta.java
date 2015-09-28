package pe.com.soul.core.web.bean;

import java.io.Serializable;

public class Respuesta implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Object respuesta;
	private boolean resultado;
	private String mensajeError;
	
	public Object getRespuesta() {
		return respuesta;
	}
	public void setRespuesta(Object respuesta) {
		this.respuesta = respuesta;
	}
	public boolean isResultado() {
		return resultado;
	}
	public void setResultado(boolean resultado) {
		this.resultado = resultado;
	}
	public String getMensajeError() {
		return mensajeError;
	}
	public void setMensajeError(String mensajeError) {
		this.mensajeError = mensajeError;
	}
	
}