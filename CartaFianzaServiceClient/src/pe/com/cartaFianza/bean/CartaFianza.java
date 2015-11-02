package pe.com.cartaFianza.bean;

import java.io.Serializable;

public class CartaFianza implements Serializable{
 
	private static final long serialVersionUID = 1L;

	private java.math.BigDecimal tasaEmision;
	private java.math.BigDecimal minimaComisionRequerimiento;
	private java.math.BigDecimal minimaComisionEmision;
	private String descripcionTipoFianza;
	private long numeroCartaFianza;
	private String codigoMoneda;
	private String descripcionModalidadCobro;
	private String descripcionCodigoMoneda;
	private String codigoPostalBeneficiario;
	private String direccionBeneficiario;
	private String modalidadCobro;
	private String codigoEstado;
	private java.math.BigDecimal tasaRequerimiento;
	private java.sql.Date fechaProximoCobroRequerimiento;
	private String tipoBloqueo;
	private java.math.BigDecimal comisionBase;
	private String tipoCobranza;
	private String deudorGarantizado;
	private String nombreBeneficiario;
	private String flgRequerido;
	private long codigoSolicitud;
	private String tipoRiesgo;
	private java.sql.Date fechaInicioRequerimiento;
	private long valoradoRequerido;
	private java.sql.Date fechaUltimoCobroRequerimiento;
	private String flgBloqueo;
	private java.sql.Date fechaUltimaEnmienda;
	private java.math.BigDecimal maximaComisionEmision;
	private long numeroValorado;
	private java.sql.Date fechaEmision;
	private java.math.BigDecimal montoRequerimientoCobrado;
	private java.math.BigDecimal importeRequerimiento;
	private java.math.BigDecimal tipoCambioBE;
	private java.sql.Date fechaVigencia;
	private String descripcionTipoRiesgo;
	private String descripcionEstado;
	private String flagCobraRequerimiento;
	private java.sql.Date fechaVencimiento;
	private java.math.BigDecimal tasaRenovacion;
	private java.math.BigDecimal monto;
	private java.math.BigDecimal maximaComisionRequerimiento;
	private String codigoTipoFianza;
	private String descripcionCodigoPostalBeneficiario;
	private String descripcionTipoCobranza;
	private String flgCobroRequerimientoPrimerDia;

	public java.math.BigDecimal getTasaEmision(){
		return tasaEmision;
	}

	public void setTasaEmision(java.math.BigDecimal tasaEmision) {
		this.tasaEmision = tasaEmision;
	}

	public java.math.BigDecimal getMinimaComisionRequerimiento(){
		return minimaComisionRequerimiento;
	}

	public void setMinimaComisionRequerimiento(java.math.BigDecimal minimaComisionRequerimiento) {
		this.minimaComisionRequerimiento = minimaComisionRequerimiento;
	}

	public java.math.BigDecimal getMinimaComisionEmision(){
		return minimaComisionEmision;
	}

	public void setMinimaComisionEmision(java.math.BigDecimal minimaComisionEmision) {
		this.minimaComisionEmision = minimaComisionEmision;
	}

	public String getDescripcionTipoFianza(){
		return descripcionTipoFianza;
	}

	public void setDescripcionTipoFianza(String descripcionTipoFianza) {
		this.descripcionTipoFianza = descripcionTipoFianza;
	}

	public long getNumeroCartaFianza(){
		return numeroCartaFianza;
	}

	public void setNumeroCartaFianza(long numeroCartaFianza) {
		this.numeroCartaFianza = numeroCartaFianza;
	}

	public String getCodigoMoneda(){
		return codigoMoneda;
	}

	public void setCodigoMoneda(String codigoMoneda) {
		this.codigoMoneda = codigoMoneda;
	}

	public String getDescripcionModalidadCobro(){
		return descripcionModalidadCobro;
	}

	public void setDescripcionModalidadCobro(String descripcionModalidadCobro) {
		this.descripcionModalidadCobro = descripcionModalidadCobro;
	}

	public String getDescripcionCodigoMoneda(){
		return descripcionCodigoMoneda;
	}

	public void setDescripcionCodigoMoneda(String descripcionCodigoMoneda) {
		this.descripcionCodigoMoneda = descripcionCodigoMoneda;
	}

	public String getCodigoPostalBeneficiario(){
		return codigoPostalBeneficiario;
	}

	public void setCodigoPostalBeneficiario(String codigoPostalBeneficiario) {
		this.codigoPostalBeneficiario = codigoPostalBeneficiario;
	}

	public String getDireccionBeneficiario(){
		return direccionBeneficiario;
	}

	public void setDireccionBeneficiario(String direccionBeneficiario) {
		this.direccionBeneficiario = direccionBeneficiario;
	}

	public String getModalidadCobro(){
		return modalidadCobro;
	}

	public void setModalidadCobro(String modalidadCobro) {
		this.modalidadCobro = modalidadCobro;
	}

	public String getCodigoEstado(){
		return codigoEstado;
	}

	public void setCodigoEstado(String codigoEstado) {
		this.codigoEstado = codigoEstado;
	}

	public java.math.BigDecimal getTasaRequerimiento(){
		return tasaRequerimiento;
	}

	public void setTasaRequerimiento(java.math.BigDecimal tasaRequerimiento) {
		this.tasaRequerimiento = tasaRequerimiento;
	}

	public java.sql.Date getFechaProximoCobroRequerimiento(){
		return fechaProximoCobroRequerimiento;
	}

	public void setFechaProximoCobroRequerimiento(java.sql.Date fechaProximoCobroRequerimiento) {
		this.fechaProximoCobroRequerimiento = fechaProximoCobroRequerimiento;
	}

	public String getTipoBloqueo(){
		return tipoBloqueo;
	}

	public void setTipoBloqueo(String tipoBloqueo) {
		this.tipoBloqueo = tipoBloqueo;
	}

	public java.math.BigDecimal getComisionBase(){
		return comisionBase;
	}

	public void setComisionBase(java.math.BigDecimal comisionBase) {
		this.comisionBase = comisionBase;
	}

	public String getTipoCobranza(){
		return tipoCobranza;
	}

	public void setTipoCobranza(String tipoCobranza) {
		this.tipoCobranza = tipoCobranza;
	}

	public String getDeudorGarantizado(){
		return deudorGarantizado;
	}

	public void setDeudorGarantizado(String deudorGarantizado) {
		this.deudorGarantizado = deudorGarantizado;
	}

	public String getNombreBeneficiario(){
		return nombreBeneficiario;
	}

	public void setNombreBeneficiario(String nombreBeneficiario) {
		this.nombreBeneficiario = nombreBeneficiario;
	}

	public String getFlgRequerido(){
		return flgRequerido;
	}

	public void setFlgRequerido(String flgRequerido) {
		this.flgRequerido = flgRequerido;
	}

	public long getCodigoSolicitud(){
		return codigoSolicitud;
	}

	public void setCodigoSolicitud(long codigoSolicitud) {
		this.codigoSolicitud = codigoSolicitud;
	}

	public String getTipoRiesgo(){
		return tipoRiesgo;
	}

	public void setTipoRiesgo(String tipoRiesgo) {
		this.tipoRiesgo = tipoRiesgo;
	}

	public java.sql.Date getFechaInicioRequerimiento(){
		return fechaInicioRequerimiento;
	}

	public void setFechaInicioRequerimiento(java.sql.Date fechaInicioRequerimiento) {
		this.fechaInicioRequerimiento = fechaInicioRequerimiento;
	}

	public long getValoradoRequerido(){
		return valoradoRequerido;
	}

	public void setValoradoRequerido(long valoradoRequerido) {
		this.valoradoRequerido = valoradoRequerido;
	}

	public java.sql.Date getFechaUltimoCobroRequerimiento(){
		return fechaUltimoCobroRequerimiento;
	}

	public void setFechaUltimoCobroRequerimiento(java.sql.Date fechaUltimoCobroRequerimiento) {
		this.fechaUltimoCobroRequerimiento = fechaUltimoCobroRequerimiento;
	}

	public String getFlgBloqueo(){
		return flgBloqueo;
	}

	public void setFlgBloqueo(String flgBloqueo) {
		this.flgBloqueo = flgBloqueo;
	}

	public java.sql.Date getFechaUltimaEnmienda(){
		return fechaUltimaEnmienda;
	}

	public void setFechaUltimaEnmienda(java.sql.Date fechaUltimaEnmienda) {
		this.fechaUltimaEnmienda = fechaUltimaEnmienda;
	}

	public java.math.BigDecimal getMaximaComisionEmision(){
		return maximaComisionEmision;
	}

	public void setMaximaComisionEmision(java.math.BigDecimal maximaComisionEmision) {
		this.maximaComisionEmision = maximaComisionEmision;
	}

	public long getNumeroValorado(){
		return numeroValorado;
	}

	public void setNumeroValorado(long numeroValorado) {
		this.numeroValorado = numeroValorado;
	}

	public java.sql.Date getFechaEmision(){
		return fechaEmision;
	}

	public void setFechaEmision(java.sql.Date fechaEmision) {
		this.fechaEmision = fechaEmision;
	}

	public java.math.BigDecimal getMontoRequerimientoCobrado(){
		return montoRequerimientoCobrado;
	}

	public void setMontoRequerimientoCobrado(java.math.BigDecimal montoRequerimientoCobrado) {
		this.montoRequerimientoCobrado = montoRequerimientoCobrado;
	}

	public java.math.BigDecimal getImporteRequerimiento(){
		return importeRequerimiento;
	}

	public void setImporteRequerimiento(java.math.BigDecimal importeRequerimiento) {
		this.importeRequerimiento = importeRequerimiento;
	}

	public java.math.BigDecimal getTipoCambioBE(){
		return tipoCambioBE;
	}

	public void setTipoCambioBE(java.math.BigDecimal tipoCambioBE) {
		this.tipoCambioBE = tipoCambioBE;
	}

	public java.sql.Date getFechaVigencia(){
		return fechaVigencia;
	}

	public void setFechaVigencia(java.sql.Date fechaVigencia) {
		this.fechaVigencia = fechaVigencia;
	}

	public String getDescripcionTipoRiesgo(){
		return descripcionTipoRiesgo;
	}

	public void setDescripcionTipoRiesgo(String descripcionTipoRiesgo) {
		this.descripcionTipoRiesgo = descripcionTipoRiesgo;
	}

	public String getDescripcionEstado(){
		return descripcionEstado;
	}

	public void setDescripcionEstado(String descripcionEstado) {
		this.descripcionEstado = descripcionEstado;
	}

	public String getFlagCobraRequerimiento(){
		return flagCobraRequerimiento;
	}

	public void setFlagCobraRequerimiento(String flagCobraRequerimiento) {
		this.flagCobraRequerimiento = flagCobraRequerimiento;
	}

	public java.sql.Date getFechaVencimiento(){
		return fechaVencimiento;
	}

	public void setFechaVencimiento(java.sql.Date fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}

	public java.math.BigDecimal getTasaRenovacion(){
		return tasaRenovacion;
	}

	public void setTasaRenovacion(java.math.BigDecimal tasaRenovacion) {
		this.tasaRenovacion = tasaRenovacion;
	}

	public java.math.BigDecimal getMonto(){
		return monto;
	}

	public void setMonto(java.math.BigDecimal monto) {
		this.monto = monto;
	}

	public java.math.BigDecimal getMaximaComisionRequerimiento(){
		return maximaComisionRequerimiento;
	}

	public void setMaximaComisionRequerimiento(java.math.BigDecimal maximaComisionRequerimiento) {
		this.maximaComisionRequerimiento = maximaComisionRequerimiento;
	}

	public String getCodigoTipoFianza(){
		return codigoTipoFianza;
	}

	public void setCodigoTipoFianza(String codigoTipoFianza) {
		this.codigoTipoFianza = codigoTipoFianza;
	}

	public String getDescripcionCodigoPostalBeneficiario(){
		return descripcionCodigoPostalBeneficiario;
	}

	public void setDescripcionCodigoPostalBeneficiario(String descripcionCodigoPostalBeneficiario) {
		this.descripcionCodigoPostalBeneficiario = descripcionCodigoPostalBeneficiario;
	}

	public String getDescripcionTipoCobranza(){
		return descripcionTipoCobranza;
	}

	public void setDescripcionTipoCobranza(String descripcionTipoCobranza) {
		this.descripcionTipoCobranza = descripcionTipoCobranza;
	}

	public String getFlgCobroRequerimientoPrimerDia(){
		return flgCobroRequerimientoPrimerDia;
	}

	public void setFlgCobroRequerimientoPrimerDia(String flgCobroRequerimientoPrimerDia) {
		this.flgCobroRequerimientoPrimerDia = flgCobroRequerimientoPrimerDia;
	}

}