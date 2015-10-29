package pe.com.cartaFianza.dao.entity;

import java.io.Serializable;

public class LiquidacionEntity implements Serializable{
 
	private static final long serialVersionUID = 1L;

	private String tipoCuenta;
	private String descripcionTipoCuenta;
	private java.math.BigDecimal tasaCXL;
	private String crearCXL;
	private long plazoCXL;
	private Long codigoSolicitud;
	private String numeroCuenta;
	private boolean sobregiro;
	private java.math.BigDecimal importeCuenta;
	private String numeroCXL;
	private String codigoMoneda;
	private java.math.BigDecimal importeCXL;

	public String getTipoCuenta(){
		return tipoCuenta;
	}

	public void setTipoCuenta(String tipoCuenta) {
		this.tipoCuenta = tipoCuenta;
	}

	public String getDescripcionTipoCuenta(){
		return descripcionTipoCuenta;
	}

	public void setDescripcionTipoCuenta(String descripcionTipoCuenta) {
		this.descripcionTipoCuenta = descripcionTipoCuenta;
	}

	public java.math.BigDecimal getTasaCXL(){
		return tasaCXL;
	}

	public void setTasaCXL(java.math.BigDecimal tasaCXL) {
		this.tasaCXL = tasaCXL;
	}

	public String getCrearCXL(){
		return crearCXL;
	}

	public void setCrearCXL(String crearCXL) {
		this.crearCXL = crearCXL;
	}

	public long getPlazoCXL(){
		return plazoCXL;
	}

	public void setPlazoCXL(long plazoCXL) {
		this.plazoCXL = plazoCXL;
	}

	public Long getCodigoSolicitud(){
		return codigoSolicitud;
	}

	public void setCodigoSolicitud(Long codigoSolicitud) {
		this.codigoSolicitud = codigoSolicitud;
	}

	public String getNumeroCuenta(){
		return numeroCuenta;
	}

	public void setNumeroCuenta(String numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}

	public boolean isSobregiro(){
		return sobregiro;
	}

	public void setSobregiro(boolean sobregiro) {
		this.sobregiro = sobregiro;
	}

	public java.math.BigDecimal getImporteCuenta(){
		return importeCuenta;
	}

	public void setImporteCuenta(java.math.BigDecimal importeCuenta) {
		this.importeCuenta = importeCuenta;
	}

	public String getNumeroCXL(){
		return numeroCXL;
	}

	public void setNumeroCXL(String numeroCXL) {
		this.numeroCXL = numeroCXL;
	}

	public String getCodigoMoneda(){
		return codigoMoneda;
	}

	public void setCodigoMoneda(String codigoMoneda) {
		this.codigoMoneda = codigoMoneda;
	}

	public java.math.BigDecimal getImporteCXL(){
		return importeCXL;
	}

	public void setImporteCXL(java.math.BigDecimal importeCXL) {
		this.importeCXL = importeCXL;
	}

}