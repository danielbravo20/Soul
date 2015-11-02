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
@Table(name = "COMISION", schema = "BFP_CARTA_FIANZA")
public class ComisionEntity implements Serializable{

	private static final long serialVersionUID = 1L;

	private String codEvento;
	private Long codigoSolicitud;
	private String codMoneda;
	private String tipoComision;
	private java.math.BigDecimal importe;
	private boolean flagExonera;
	private String descripcionTipoComision;
	private java.math.BigDecimal minimo;
	private java.math.BigDecimal maximo;

	public String getCodEvento(){
		return codEvento;
	}

	public void setCodEvento(String codEvento) {
		this.codEvento = codEvento;
	}

	public Long getCodigoSolicitud(){
		return codigoSolicitud;
	}

	public void setCodigoSolicitud(Long codigoSolicitud) {
		this.codigoSolicitud = codigoSolicitud;
	}

	public String getCodMoneda(){
		return codMoneda;
	}

	public void setCodMoneda(String codMoneda) {
		this.codMoneda = codMoneda;
	}

	public String getTipoComision(){
		return tipoComision;
	}

	public void setTipoComision(String tipoComision) {
		this.tipoComision = tipoComision;
	}

	public java.math.BigDecimal getImporte(){
		return importe;
	}

	public void setImporte(java.math.BigDecimal importe) {
		this.importe = importe;
	}

	public boolean isFlagExonera(){
		return flagExonera;
	}

	public void setFlagExonera(boolean flagExonera) {
		this.flagExonera = flagExonera;
	}

	public String getDescripcionTipoComision(){
		return descripcionTipoComision;
	}

	public void setDescripcionTipoComision(String descripcionTipoComision) {
		this.descripcionTipoComision = descripcionTipoComision;
	}

	public java.math.BigDecimal getMinimo(){
		return minimo;
	}

	public void setMinimo(java.math.BigDecimal minimo) {
		this.minimo = minimo;
	}

	public java.math.BigDecimal getMaximo(){
		return maximo;
	}

	public void setMaximo(java.math.BigDecimal maximo) {
		this.maximo = maximo;
	}

}