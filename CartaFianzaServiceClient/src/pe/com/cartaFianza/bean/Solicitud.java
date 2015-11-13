package pe.com.cartaFianza.bean;

import java.io.Serializable;

public class Solicitud implements Serializable{
 
	private static final long serialVersionUID = 1L;

	private java.math.BigDecimal monto;
	private java.util.Date vigencia;
	private String evento;
	private Long codigoSolicitud;

	public java.math.BigDecimal getMonto(){
		return monto;
	}

	public void setMonto(java.math.BigDecimal monto) {
		this.monto = monto;
	}

	public java.util.Date getVigencia(){
		return vigencia;
	}

	public void setVigencia(java.util.Date vigencia) {
		this.vigencia = vigencia;
	}

	public String getEvento(){
		return evento;
	}

	public void setEvento(String evento) {
		this.evento = evento;
	}

	public Long getCodigoSolicitud(){
		return codigoSolicitud;
	}

	public void setCodigoSolicitud(Long codigoSolicitud) {
		this.codigoSolicitud = codigoSolicitud;
	}

}