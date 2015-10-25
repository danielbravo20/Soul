package pe.com.cartaFianza.bean;

import java.io.Serializable;

public class Liquidacion implements Serializable{
 
	private static final long serialVersionUID = 1L;

	private String numeroCuenta;
	private java.math.BigDecimal importeCuenta;
	private java.math.BigDecimal tasaCXL;
	private java.math.BigDecimal importeCXL;
	private String codigoMoneda;
	private long plazoCXL;
	private String crearCXL;
	private String tipoCuenta;
	private boolean sobregiro;
	private String numeroCXL;
	private String descripcionTipoCuenta;
	private Long codigoSolicitud;

	public String getNumeroCuenta(){
		return numeroCuenta;
	}

	public void setNumeroCuenta(String numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}

	public java.math.BigDecimal getImporteCuenta(){
		return importeCuenta;
	}

	public void setImporteCuenta(java.math.BigDecimal importeCuenta) {
		this.importeCuenta = importeCuenta;
	}

	public java.math.BigDecimal getTasaCXL(){
		return tasaCXL;
	}

	public void setTasaCXL(java.math.BigDecimal tasaCXL) {
		this.tasaCXL = tasaCXL;
	}

	public java.math.BigDecimal getImporteCXL(){
		return importeCXL;
	}

	public void setImporteCXL(java.math.BigDecimal importeCXL) {
		this.importeCXL = importeCXL;
	}

	public String getCodigoMoneda(){
		return codigoMoneda;
	}

	public void setCodigoMoneda(String codigoMoneda) {
		this.codigoMoneda = codigoMoneda;
	}

	public long getPlazoCXL(){
		return plazoCXL;
	}

	public void setPlazoCXL(long plazoCXL) {
		this.plazoCXL = plazoCXL;
	}

	public String getCrearCXL(){
		return crearCXL;
	}

	public void setCrearCXL(String crearCXL) {
		this.crearCXL = crearCXL;
	}

	public String getTipoCuenta(){
		return tipoCuenta;
	}

	public void setTipoCuenta(String tipoCuenta) {
		this.tipoCuenta = tipoCuenta;
	}

	public boolean isSobregiro(){
		return sobregiro;
	}

	public void setSobregiro(boolean sobregiro) {
		this.sobregiro = sobregiro;
	}

	public String getNumeroCXL(){
		return numeroCXL;
	}

	public void setNumeroCXL(String numeroCXL) {
		this.numeroCXL = numeroCXL;
	}

	public String getDescripcionTipoCuenta(){
		return descripcionTipoCuenta;
	}

	public void setDescripcionTipoCuenta(String descripcionTipoCuenta) {
		this.descripcionTipoCuenta = descripcionTipoCuenta;
	}

	public Long getCodigoSolicitud(){
		return codigoSolicitud;
	}

	public void setCodigoSolicitud(Long codigoSolicitud) {
		this.codigoSolicitud = codigoSolicitud;
	}

}