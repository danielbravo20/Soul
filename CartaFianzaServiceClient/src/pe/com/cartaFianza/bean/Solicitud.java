package pe.com.cartaFianza.bean;

import java.io.Serializable;

public class Solicitud implements Serializable{
 
	private static final long serialVersionUID = 1L;

	private java.sql.Date vigencia;
	private String beneficiario;
	private java.math.BigDecimal monto;
	private Long codigoSolicitud;
	private String estado;

	public java.sql.Date getVigencia(){
		return vigencia;
	}

	public void setVigencia(java.sql.Date vigencia) {
		this.vigencia = vigencia;
	}

	public String getBeneficiario(){
		return beneficiario;
	}

	public void setBeneficiario(String beneficiario) {
		this.beneficiario = beneficiario;
	}

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

	public String getEstado(){
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

}