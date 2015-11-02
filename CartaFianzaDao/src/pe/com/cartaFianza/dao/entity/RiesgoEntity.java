package pe.com.cartaFianza.dao.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "RIESGO", schema = "BFP_CARTA_FIANZA")
public class RiesgoEntity implements Serializable{

	private static final long serialVersionUID = 1L;

	private String codigoCriterio;
	private Long codigoSolicitud;
	private java.math.BigDecimal lineaAprobada;
	private java.math.BigDecimal lineaDisponible;
	private java.math.BigDecimal porcentaje;
	private String descripcionCriterio;
	private java.math.BigDecimal lineaUtilizada;

	public String getCodigoCriterio(){
		return codigoCriterio;
	}

	public void setCodigoCriterio(String codigoCriterio) {
		this.codigoCriterio = codigoCriterio;
	}

	public Long getCodigoSolicitud(){
		return codigoSolicitud;
	}

	public void setCodigoSolicitud(Long codigoSolicitud) {
		this.codigoSolicitud = codigoSolicitud;
	}

	public java.math.BigDecimal getLineaAprobada(){
		return lineaAprobada;
	}

	public void setLineaAprobada(java.math.BigDecimal lineaAprobada) {
		this.lineaAprobada = lineaAprobada;
	}

	public java.math.BigDecimal getLineaDisponible(){
		return lineaDisponible;
	}

	public void setLineaDisponible(java.math.BigDecimal lineaDisponible) {
		this.lineaDisponible = lineaDisponible;
	}

	public java.math.BigDecimal getPorcentaje(){
		return porcentaje;
	}

	public void setPorcentaje(java.math.BigDecimal porcentaje) {
		this.porcentaje = porcentaje;
	}

	public String getDescripcionCriterio(){
		return descripcionCriterio;
	}

	public void setDescripcionCriterio(String descripcionCriterio) {
		this.descripcionCriterio = descripcionCriterio;
	}

	public java.math.BigDecimal getLineaUtilizada(){
		return lineaUtilizada;
	}

	public void setLineaUtilizada(java.math.BigDecimal lineaUtilizada) {
		this.lineaUtilizada = lineaUtilizada;
	}

}