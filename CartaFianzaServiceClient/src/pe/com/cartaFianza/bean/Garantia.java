package pe.com.cartaFianza.bean;

import java.io.Serializable;

public class Garantia implements Serializable{
 
	private static final long serialVersionUID = 1L;

	private String tipoGarantia;
	private String nombreCliente;
	private String moneda;
	private java.math.BigDecimal importe;
	private String tipoCobertura;
	private java.math.BigDecimal porcentajeCobertura;
	private String descripcionTipoCobertura;
	private String descripcionTipoGarantia;
	private Long numeroGarantia;
	private Long codigoSolicitud;
	private Long codigoIbs;

	public String getTipoGarantia(){
		return tipoGarantia;
	}

	public void setTipoGarantia(String tipoGarantia) {
		this.tipoGarantia = tipoGarantia;
	}

	public String getNombreCliente(){
		return nombreCliente;
	}

	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}

	public String getMoneda(){
		return moneda;
	}

	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}

	public java.math.BigDecimal getImporte(){
		return importe;
	}

	public void setImporte(java.math.BigDecimal importe) {
		this.importe = importe;
	}

	public String getTipoCobertura(){
		return tipoCobertura;
	}

	public void setTipoCobertura(String tipoCobertura) {
		this.tipoCobertura = tipoCobertura;
	}

	public java.math.BigDecimal getPorcentajeCobertura(){
		return porcentajeCobertura;
	}

	public void setPorcentajeCobertura(java.math.BigDecimal porcentajeCobertura) {
		this.porcentajeCobertura = porcentajeCobertura;
	}

	public String getDescripcionTipoCobertura(){
		return descripcionTipoCobertura;
	}

	public void setDescripcionTipoCobertura(String descripcionTipoCobertura) {
		this.descripcionTipoCobertura = descripcionTipoCobertura;
	}

	public String getDescripcionTipoGarantia(){
		return descripcionTipoGarantia;
	}

	public void setDescripcionTipoGarantia(String descripcionTipoGarantia) {
		this.descripcionTipoGarantia = descripcionTipoGarantia;
	}

	public Long getNumeroGarantia(){
		return numeroGarantia;
	}

	public void setNumeroGarantia(Long numeroGarantia) {
		this.numeroGarantia = numeroGarantia;
	}

	public Long getCodigoSolicitud(){
		return codigoSolicitud;
	}

	public void setCodigoSolicitud(Long codigoSolicitud) {
		this.codigoSolicitud = codigoSolicitud;
	}

	public Long getCodigoIbs(){
		return codigoIbs;
	}

	public void setCodigoIbs(Long codigoIbs) {
		this.codigoIbs = codigoIbs;
	}

}