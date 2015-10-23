package pe.com.cartaFianza.bean;

import java.io.Serializable;

public class Liquidacion implements Serializable{
 
	private static final long serialVersionUID = 1L;

	private String numeroCuenta;
	private java.math.BigDecimal tasaCXL;
	private java.math.BigDecimal importeCXL;
	private String tipoCuenta;
	private String crearCXL;
	private long plazoCXL;
	private Long codigoSolicitud;
	private String descripcionTipoCuenta;
	private String numeroCXL;
	private boolean sobregiro;
	private java.math.BigDecimal importeCuenta;
	private String codigoMoneda;

	public String getNumeroCuenta(){
		return numeroCuenta;
	}

	public void setNumeroCuenta(String numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
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

	public String getTipoCuenta(){
		return tipoCuenta;
	}

	public void setTipoCuenta(String tipoCuenta) {
		this.tipoCuenta = tipoCuenta;
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

	public String getDescripcionTipoCuenta(){
		return descripcionTipoCuenta;
	}

	public void setDescripcionTipoCuenta(String descripcionTipoCuenta) {
		this.descripcionTipoCuenta = descripcionTipoCuenta;
	}

	public String getNumeroCXL(){
		return numeroCXL;
	}

	public void setNumeroCXL(String numeroCXL) {
		this.numeroCXL = numeroCXL;
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

	public String getCodigoMoneda(){
		return codigoMoneda;
	}

	public void setCodigoMoneda(String codigoMoneda) {
		this.codigoMoneda = codigoMoneda;
	}

}