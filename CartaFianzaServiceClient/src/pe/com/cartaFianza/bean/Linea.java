package pe.com.cartaFianza.bean;

import java.io.Serializable;

public class Linea implements Serializable{
 
	private static final long serialVersionUID = 1L;

	private String codigoCategoria;
	private String moneda;
	private java.math.BigDecimal montoDisponibleIBS;
	private java.sql.Date fechaVencimiento;
	private String descripcionCategoria;
	private java.math.BigDecimal montoDisponible;
	private String codigoEstado;
	private String descripcionEstado;
	private String codigoLinea;
	private Long codigoSolicitud;
	private java.math.BigDecimal montoTramite;
	private java.math.BigDecimal montoAprobado;

	public String getCodigoCategoria(){
		return codigoCategoria;
	}

	public void setCodigoCategoria(String codigoCategoria) {
		this.codigoCategoria = codigoCategoria;
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

	public java.sql.Date getFechaVencimiento(){
		return fechaVencimiento;
	}

	public void setFechaVencimiento(java.sql.Date fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}

	public String getDescripcionCategoria(){
		return descripcionCategoria;
	}

	public void setDescripcionCategoria(String descripcionCategoria) {
		this.descripcionCategoria = descripcionCategoria;
	}

	public java.math.BigDecimal getMontoDisponible(){
		return montoDisponible;
	}

	public void setMontoDisponible(java.math.BigDecimal montoDisponible) {
		this.montoDisponible = montoDisponible;
	}

	public String getCodigoEstado(){
		return codigoEstado;
	}

	public void setCodigoEstado(String codigoEstado) {
		this.codigoEstado = codigoEstado;
	}

	public String getDescripcionEstado(){
		return descripcionEstado;
	}

	public void setDescripcionEstado(String descripcionEstado) {
		this.descripcionEstado = descripcionEstado;
	}

	public String getCodigoLinea(){
		return codigoLinea;
	}

	public void setCodigoLinea(String codigoLinea) {
		this.codigoLinea = codigoLinea;
	}

	public Long getCodigoSolicitud(){
		return codigoSolicitud;
	}

	public void setCodigoSolicitud(Long codigoSolicitud) {
		this.codigoSolicitud = codigoSolicitud;
	}

	public java.math.BigDecimal getMontoTramite(){
		return montoTramite;
	}

	public void setMontoTramite(java.math.BigDecimal montoTramite) {
		this.montoTramite = montoTramite;
	}

	public java.math.BigDecimal getMontoAprobado(){
		return montoAprobado;
	}

	public void setMontoAprobado(java.math.BigDecimal montoAprobado) {
		this.montoAprobado = montoAprobado;
	}

}