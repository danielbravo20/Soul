package pe.com.cartaFianza.bean;

import java.io.Serializable;

public class Comision implements Serializable{
 
	private static final long serialVersionUID = 1L;

	private java.math.BigDecimal importe;
	private java.math.BigDecimal minimo;
	private Long codigoSolicitud;
	private java.math.BigDecimal maximo;
	private String descripcionTipoComision;
	private String codEvento;
	private String codMoneda;
	private boolean flagExonera;
	private String tipoComision;

	public java.math.BigDecimal getImporte(){
		return importe;
	}

	public void setImporte(java.math.BigDecimal importe) {
		this.importe = importe;
	}

	public java.math.BigDecimal getMinimo(){
		return minimo;
	}

	public void setMinimo(java.math.BigDecimal minimo) {
		this.minimo = minimo;
	}

	public Long getCodigoSolicitud(){
		return codigoSolicitud;
	}

	public void setCodigoSolicitud(Long codigoSolicitud) {
		this.codigoSolicitud = codigoSolicitud;
	}

	public java.math.BigDecimal getMaximo(){
		return maximo;
	}

	public void setMaximo(java.math.BigDecimal maximo) {
		this.maximo = maximo;
	}

	public String getDescripcionTipoComision(){
		return descripcionTipoComision;
	}

	public void setDescripcionTipoComision(String descripcionTipoComision) {
		this.descripcionTipoComision = descripcionTipoComision;
	}

	public String getCodEvento(){
		return codEvento;
	}

	public void setCodEvento(String codEvento) {
		this.codEvento = codEvento;
	}

	public String getCodMoneda(){
		return codMoneda;
	}

	public void setCodMoneda(String codMoneda) {
		this.codMoneda = codMoneda;
	}

	public boolean isFlagExonera(){
		return flagExonera;
	}

	public void setFlagExonera(boolean flagExonera) {
		this.flagExonera = flagExonera;
	}

	public String getTipoComision(){
		return tipoComision;
	}

	public void setTipoComision(String tipoComision) {
		this.tipoComision = tipoComision;
	}

}