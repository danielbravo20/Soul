package pe.com.cartaFianza.dao.entity;

import java.io.Serializable;

public class ComisionEntity implements Serializable{
 
	private static final long serialVersionUID = 1L;

	private String codMoneda;
	private String descripcionTipoComision;
	private java.math.BigDecimal importe;
	private java.math.BigDecimal maximo;
	private boolean flagExonera;
	private String codEvento;
	private String tipoComision;
	private Long codigoSolicitud;
	private java.math.BigDecimal minimo;

	public String getCodMoneda(){
		return codMoneda;
	}

	public void setCodMoneda(String codMoneda) {
		this.codMoneda = codMoneda;
	}

	public String getDescripcionTipoComision(){
		return descripcionTipoComision;
	}

	public void setDescripcionTipoComision(String descripcionTipoComision) {
		this.descripcionTipoComision = descripcionTipoComision;
	}

	public java.math.BigDecimal getImporte(){
		return importe;
	}

	public void setImporte(java.math.BigDecimal importe) {
		this.importe = importe;
	}

	public java.math.BigDecimal getMaximo(){
		return maximo;
	}

	public void setMaximo(java.math.BigDecimal maximo) {
		this.maximo = maximo;
	}

	public boolean isFlagExonera(){
		return flagExonera;
	}

	public void setFlagExonera(boolean flagExonera) {
		this.flagExonera = flagExonera;
	}

	public String getCodEvento(){
		return codEvento;
	}

	public void setCodEvento(String codEvento) {
		this.codEvento = codEvento;
	}

	public String getTipoComision(){
		return tipoComision;
	}

	public void setTipoComision(String tipoComision) {
		this.tipoComision = tipoComision;
	}

	public Long getCodigoSolicitud(){
		return codigoSolicitud;
	}

	public void setCodigoSolicitud(Long codigoSolicitud) {
		this.codigoSolicitud = codigoSolicitud;
	}

	public java.math.BigDecimal getMinimo(){
		return minimo;
	}

	public void setMinimo(java.math.BigDecimal minimo) {
		this.minimo = minimo;
	}

}