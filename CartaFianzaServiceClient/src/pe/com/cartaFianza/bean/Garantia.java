package pe.com.cartaFianza.bean;

import java.io.Serializable;

public class Garantia implements Serializable{
 
	private static final long serialVersionUID = 1L;

	private Long codigoIbs;
	private String descripcionTipoCobertura;
	private java.math.BigDecimal porcentajeCobertura;
	private String moneda;
	private String nombreCliente;
	private Long codigoSolicitud;
	private java.math.BigDecimal importe;
	private String tipoGarantia;
	private String tipoCobertura;
	private Long numeroGarantia;
	private String descripcionTipoGarantia;

	public Long getCodigoIbs(){
		return codigoIbs;
	}

	public void setCodigoIbs(Long codigoIbs) {
		this.codigoIbs = codigoIbs;
	}

	public String getDescripcionTipoCobertura(){
		return descripcionTipoCobertura;
	}

	public void setDescripcionTipoCobertura(String descripcionTipoCobertura) {
		this.descripcionTipoCobertura = descripcionTipoCobertura;
	}

	public java.math.BigDecimal getPorcentajeCobertura(){
		return porcentajeCobertura;
	}

	public void setPorcentajeCobertura(java.math.BigDecimal porcentajeCobertura) {
		this.porcentajeCobertura = porcentajeCobertura;
	}

	public String getMoneda(){
		return moneda;
	}

	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}

	public String getNombreCliente(){
		return nombreCliente;
	}

	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}

	public Long getCodigoSolicitud(){
		return codigoSolicitud;
	}

	public void setCodigoSolicitud(Long codigoSolicitud) {
		this.codigoSolicitud = codigoSolicitud;
	}

	public java.math.BigDecimal getImporte(){
		return importe;
	}

	public void setImporte(java.math.BigDecimal importe) {
		this.importe = importe;
	}

	public String getTipoGarantia(){
		return tipoGarantia;
	}

	public void setTipoGarantia(String tipoGarantia) {
		this.tipoGarantia = tipoGarantia;
	}

	public String getTipoCobertura(){
		return tipoCobertura;
	}

	public void setTipoCobertura(String tipoCobertura) {
		this.tipoCobertura = tipoCobertura;
	}

	public Long getNumeroGarantia(){
		return numeroGarantia;
	}

	public void setNumeroGarantia(Long numeroGarantia) {
		this.numeroGarantia = numeroGarantia;
	}

	public String getDescripcionTipoGarantia(){
		return descripcionTipoGarantia;
	}

	public void setDescripcionTipoGarantia(String descripcionTipoGarantia) {
		this.descripcionTipoGarantia = descripcionTipoGarantia;
	}

}