package pe.com.cartaFianza.bean;

import java.io.Serializable;

public class Linea implements Serializable{
 
	private static final long serialVersionUID = 1L;

	private String codigoLinea;
	private String moneda;
	private java.math.BigDecimal montoDisponibleIBS;
	private String codigoCategoria;
	private java.sql.Date fechaVencimiento;
	private String descripcionEstado;
	private java.math.BigDecimal montoTramite;
	private String codigoEstado;
	private java.math.BigDecimal montoDisponible;
	private java.math.BigDecimal montoAprobado;
	private Long codigoSolicitud;
	private String descripcionCategoria;

	public String getCodigoLinea(){
		return codigoLinea;
	}

	public void setCodigoLinea(String codigoLinea) {
		this.codigoLinea = codigoLinea;
	}

	public String getMoneda(){
		return moneda;
	}

	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}

	public java.math.BigDecimal getMontoDisponibleIBS(){
		return montoDisponibleIBS;
	}

	public void setMontoDisponibleIBS(java.math.BigDecimal montoDisponibleIBS) {
		this.montoDisponibleIBS = montoDisponibleIBS;
	}

	public String getCodigoCategoria(){
		return codigoCategoria;
	}

	public void setCodigoCategoria(String codigoCategoria) {
		this.codigoCategoria = codigoCategoria;
	}

	public java.sql.Date getFechaVencimiento(){
		return fechaVencimiento;
	}

	public void setFechaVencimiento(java.sql.Date fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}

	public String getDescripcionEstado(){
		return descripcionEstado;
	}

	public void setDescripcionEstado(String descripcionEstado) {
		this.descripcionEstado = descripcionEstado;
	}

	public java.math.BigDecimal getMontoTramite(){
		return montoTramite;
	}

	public void setMontoTramite(java.math.BigDecimal montoTramite) {
		this.montoTramite = montoTramite;
	}

	public String getCodigoEstado(){
		return codigoEstado;
	}

	public void setCodigoEstado(String codigoEstado) {
		this.codigoEstado = codigoEstado;
	}

	public java.math.BigDecimal getMontoDisponible(){
		return montoDisponible;
	}

	public void setMontoDisponible(java.math.BigDecimal montoDisponible) {
		this.montoDisponible = montoDisponible;
	}

	public java.math.BigDecimal getMontoAprobado(){
		return montoAprobado;
	}

	public void setMontoAprobado(java.math.BigDecimal montoAprobado) {
		this.montoAprobado = montoAprobado;
	}

	public Long getCodigoSolicitud(){
		return codigoSolicitud;
	}

	public void setCodigoSolicitud(Long codigoSolicitud) {
		this.codigoSolicitud = codigoSolicitud;
	}

	public String getDescripcionCategoria(){
		return descripcionCategoria;
	}

	public void setDescripcionCategoria(String descripcionCategoria) {
		this.descripcionCategoria = descripcionCategoria;
	}

}