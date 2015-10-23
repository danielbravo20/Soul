package pe.com.cartaFianza.bean;

import java.io.Serializable;

public class Plantilla implements Serializable{
 
	private static final long serialVersionUID = 1L;

	private String usuarioActualizacion;
	private Integer codigoPlantilla;
	private String codigoTipoBanca;
	private boolean estado;
	private String usuarioCreacion;
	private String codigoTipoSolicitud;
	private java.sql.Timestamp fechaActualizacion;
	private String archivo;
	private String nombre;
	private String descripcion;
	private java.sql.Timestamp fechaCreacion;

	public String getUsuarioActualizacion(){
		return usuarioActualizacion;
	}

	public void setUsuarioActualizacion(String usuarioActualizacion) {
		this.usuarioActualizacion = usuarioActualizacion;
	}

	public Integer getCodigoPlantilla(){
		return codigoPlantilla;
	}

	public void setCodigoPlantilla(Integer codigoPlantilla) {
		this.codigoPlantilla = codigoPlantilla;
	}

	public String getCodigoTipoBanca(){
		return codigoTipoBanca;
	}

	public void setCodigoTipoBanca(String codigoTipoBanca) {
		this.codigoTipoBanca = codigoTipoBanca;
	}

	public boolean isEstado(){
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	public String getUsuarioCreacion(){
		return usuarioCreacion;
	}

	public void setUsuarioCreacion(String usuarioCreacion) {
		this.usuarioCreacion = usuarioCreacion;
	}

	public String getCodigoTipoSolicitud(){
		return codigoTipoSolicitud;
	}

	public void setCodigoTipoSolicitud(String codigoTipoSolicitud) {
		this.codigoTipoSolicitud = codigoTipoSolicitud;
	}

	public java.sql.Timestamp getFechaActualizacion(){
		return fechaActualizacion;
	}

	public void setFechaActualizacion(java.sql.Timestamp fechaActualizacion) {
		this.fechaActualizacion = fechaActualizacion;
	}

	public String getArchivo(){
		return archivo;
	}

	public void setArchivo(String archivo) {
		this.archivo = archivo;
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

	public java.sql.Timestamp getFechaCreacion(){
		return fechaCreacion;
	}

	public void setFechaCreacion(java.sql.Timestamp fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

}