package pe.com.cartaFianza.bean;

import java.io.Serializable;

public class Cuenta implements Serializable{
 
	private static final long serialVersionUID = 1L;

	private Long codigoSolicitud;
	private String codigoAgencia;
	private java.math.BigDecimal saldoRetenido;
	private String codigoTipoCuenta;
	private java.math.BigDecimal saldoContable;
	private String estado;
	private String codigoEstado;
	private String numeroCuenta;
	private java.math.BigDecimal saldoDisponible;
	private String codigoMoneda;

	public Long getCodigoSolicitud(){
		return codigoSolicitud;
	}

	public void setCodigoSolicitud(Long codigoSolicitud) {
		this.codigoSolicitud = codigoSolicitud;
	}

	public String getCodigoAgencia(){
		return codigoAgencia;
	}

	public void setCodigoAgencia(String codigoAgencia) {
		this.codigoAgencia = codigoAgencia;
	}

	public java.math.BigDecimal getSaldoRetenido(){
		return saldoRetenido;
	}

	public void setSaldoRetenido(java.math.BigDecimal saldoRetenido) {
		this.saldoRetenido = saldoRetenido;
	}

	public String getCodigoTipoCuenta(){
		return codigoTipoCuenta;
	}

	public void setCodigoTipoCuenta(String codigoTipoCuenta) {
		this.codigoTipoCuenta = codigoTipoCuenta;
	}

	public java.math.BigDecimal getSaldoContable(){
		return saldoContable;
	}

	public void setSaldoContable(java.math.BigDecimal saldoContable) {
		this.saldoContable = saldoContable;
	}

	public String getEstado(){
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getCodigoEstado(){
		return codigoEstado;
	}

	public void setCodigoEstado(String codigoEstado) {
		this.codigoEstado = codigoEstado;
	}

	public String getNumeroCuenta(){
		return numeroCuenta;
	}

	public void setNumeroCuenta(String numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}

	public java.math.BigDecimal getSaldoDisponible(){
		return saldoDisponible;
	}

	public void setSaldoDisponible(java.math.BigDecimal saldoDisponible) {
		this.saldoDisponible = saldoDisponible;
	}

	public String getCodigoMoneda(){
		return codigoMoneda;
	}

	public void setCodigoMoneda(String codigoMoneda) {
		this.codigoMoneda = codigoMoneda;
	}

}