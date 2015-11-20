package pe.com.cartaFianza.bean;

import pe.com.soul.core.bean.BasePadreBean;

import java.io.Serializable;

public class Solicitud extends BasePadreBean implements Serializable{
 
	private static final long serialVersionUID = 1L;

	private java.math.BigDecimal monto;
	private Long codigoSolicitud;
	private String evento;
	private java.util.Date vigencia;

	public java.math.BigDecimal getMonto(){
		return monto;
	}

	public void setMonto(java.math.BigDecimal monto) {
		this.monto = monto;
	}

	public Long getCodigoSolicitud(){
		return codigoSolicitud;
	}

	public void setCodigoSolicitud(Long codigoSolicitud) {
		this.codigoSolicitud = codigoSolicitud;
	}

	public String getEvento(){
		return evento;
	}

	public void setEvento(String evento) {
		this.evento = evento;
	}

	public java.util.Date getVigencia(){
		return vigencia;
	}

	public void setVigencia(java.util.Date vigencia) {
		this.vigencia = vigencia;
	}

}