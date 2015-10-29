package pe.com.cartaFianza.dao.entity;

import java.io.Serializable;

public class RiesgoEntity implements Serializable{
 
	private static final long serialVersionUID = 1L;

	private java.math.BigDecimal lineaAprobada;
	private java.math.BigDecimal lineaUtilizada;
	private Long codigoSolicitud;
	private String codigoCriterio;
	private java.math.BigDecimal porcentaje;
	private java.math.BigDecimal lineaDisponible;
	private String descripcionCriterio;

	public java.math.BigDecimal getLineaAprobada(){
		return lineaAprobada;
	}

	public void setLineaAprobada(java.math.BigDecimal lineaAprobada) {
		this.lineaAprobada = lineaAprobada;
	}

	public java.math.BigDecimal getLineaUtilizada(){
		return lineaUtilizada;
	}

	public void setLineaUtilizada(java.math.BigDecimal lineaUtilizada) {
		this.lineaUtilizada = lineaUtilizada;
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

	public String getDescripcionCriterio(){
		return descripcionCriterio;
	}

	public void setDescripcionCriterio(String descripcionCriterio) {
		this.descripcionCriterio = descripcionCriterio;
	}

}