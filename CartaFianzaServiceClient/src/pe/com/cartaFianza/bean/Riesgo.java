package pe.com.cartaFianza.bean;

import java.io.Serializable;

public class Riesgo implements Serializable{
 
	private static final long serialVersionUID = 1L;

	private String descripcionCriterio;
	private java.math.BigDecimal lineaAprobada;
	private Long codigoSolicitud;
	private String codigoCriterio;
	private java.math.BigDecimal porcentaje;
	private java.math.BigDecimal lineaDisponible;
	private java.math.BigDecimal lineaUtilizada;

	public String getDescripcionCriterio(){
		return descripcionCriterio;
	}

	public void setDescripcionCriterio(String descripcionCriterio) {
		this.descripcionCriterio = descripcionCriterio;
	}

	public java.math.BigDecimal getLineaAprobada(){
		return lineaAprobada;
	}

	public void setLineaAprobada(java.math.BigDecimal lineaAprobada) {
		this.lineaAprobada = lineaAprobada;
	}

	public Long getCodigoSolicitud(){
		return codigoSolicitud;
	}

	public void setCodigoSolicitud(Long codigoSolicitud) {
		this.codigoSolicitud = codigoSolicitud;
	}

	public String getCodigoCriterio(){
		return codigoCriterio;
	}

	public void setCodigoCriterio(String codigoCriterio) {
		this.codigoCriterio = codigoCriterio;
	}

	public java.math.BigDecimal getPorcentaje(){
		return porcentaje;
	}

	public void setPorcentaje(java.math.BigDecimal porcentaje) {
		this.porcentaje = porcentaje;
	}

	public java.math.BigDecimal getLineaDisponible(){
		return lineaDisponible;
	}

	public void setLineaDisponible(java.math.BigDecimal lineaDisponible) {
		this.lineaDisponible = lineaDisponible;
	}

	public java.math.BigDecimal getLineaUtilizada(){
		return lineaUtilizada;
	}

	public void setLineaUtilizada(java.math.BigDecimal lineaUtilizada) {
		this.lineaUtilizada = lineaUtilizada;
	}

}