package pe.com.cartaFianza.bean;

import java.io.Serializable;

public class ListaVerificacion implements Serializable{
 
	private static final long serialVersionUID = 1L;

	private String usuarioCreacion;
	private boolean verificado;
	private Integer codigoVerificacion;
	private Long codigoSolicitud;
	private java.sql.Timestamp fechaCreacion;
	private String nombre;
	private String descripcion;

	public String getUsuarioCreacion(){
		return usuarioCreacion;
	}

	public void setUsuarioCreacion(String usuarioCreacion) {
		this.usuarioCreacion = usuarioCreacion;
	}

	public boolean isVerificado(){
		return verificado;
	}

	public void setVerificado(boolean verificado) {
		this.verificado = verificado;
	}

	public Integer getCodigoVerificacion(){
		return codigoVerificacion;
	}

	public void setCodigoVerificacion(Integer codigoVerificacion) {
		this.codigoVerificacion = codigoVerificacion;
	}

	public Long getCodigoSolicitud(){
		return codigoSolicitud;
	}

	public void setCodigoSolicitud(Long codigoSolicitud) {
		this.codigoSolicitud = codigoSolicitud;
	}

	public java.sql.Timestamp getFechaCreacion(){
		return fechaCreacion;
	}

	public void setFechaCreacion(java.sql.Timestamp fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public String getNombre(){
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion(){
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}