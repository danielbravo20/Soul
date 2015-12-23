package pe.com.cartaFianza.bean;

import pe.com.soul.core.bean.BasePadreBean;

import java.io.Serializable;

public class Solicitud extends BasePadreBean implements Serializable{
 
	private static final long serialVersionUID = 1L;

	private Long valoNumerico;
	private Long codigoSolicitud;
	private long valorNumericoB;
	private String evento;
	private boolean flagAdicional;
	private java.util.Date fechaFin;
	private Integer valorNumericoC;
	private boolean flagPrincipal;
	private java.math.BigDecimal monto;
	private String descripcion;
	private java.math.BigDecimal montoFinal;
	private java.util.Date vigencia;

	public Long getValoNumerico(){
		return valoNumerico;
	}

	public void setValoNumerico(Long valoNumerico) {
		this.valoNumerico = valoNumerico;
	}

	public Long getCodigoSolicitud(){
		return codigoSolicitud;
	}

	public void setCodigoSolicitud(Long codigoSolicitud) {
		this.codigoSolicitud = codigoSolicitud;
	}

	public long getValorNumericoB(){
		return valorNumericoB;
	}

	public void setValorNumericoB(long valorNumericoB) {
		this.valorNumericoB = valorNumericoB;
	}

	public String getEvento(){
		return evento;
	}

	public void setEvento(String evento) {
		this.evento = evento;
	}

	public boolean isFlagAdicional(){
		return flagAdicional;
	}

	public void setFlagAdicional(boolean flagAdicional) {
		this.flagAdicional = flagAdicional;
	}

	public java.util.Date getFechaFin(){
		return fechaFin;
	}

	public void setFechaFin(java.util.Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public Integer getValorNumericoC(){
		return valorNumericoC;
	}

	public void setValorNumericoC(Integer valorNumericoC) {
		this.valorNumericoC = valorNumericoC;
	}

	public boolean isFlagPrincipal(){
		return flagPrincipal;
	}

	public void setFlagPrincipal(boolean flagPrincipal) {
		this.flagPrincipal = flagPrincipal;
	}

	public java.math.BigDecimal getMonto(){
		return monto;
	}

	public void setMonto(java.math.BigDecimal monto) {
		this.monto = monto;
	}

	public String getDescripcion(){
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public java.math.BigDecimal getMontoFinal(){
		return montoFinal;
	}

	public void setMontoFinal(java.math.BigDecimal montoFinal) {
		this.montoFinal = montoFinal;
	}

	public java.util.Date getVigencia(){
		return vigencia;
	}

	public void setVigencia(java.util.Date vigencia) {
		this.vigencia = vigencia;
	}

}