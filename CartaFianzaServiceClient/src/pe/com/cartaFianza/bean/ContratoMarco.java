package pe.com.cartaFianza.bean;

import java.io.Serializable;

public class ContratoMarco implements Serializable{
 
	private static final long serialVersionUID = 1L;

	private String usuarioModificacion;
	private String usuarioCreacion;
	private String numero;
	private java.sql.Date fechaVencimiento;
	private String nombreCliente;
	private String tipoDocumento;
	private String ruta;
	private String numeroDocumento;
	private String nombreArchivoContratoMarco;
	private boolean flagContratoMarco;
	private boolean estado;
	private java.sql.Timestamp fechaCreacion;
	private java.sql.Timestamp fechaModificacion;
	private String descripcion;
	private boolean flagPagare;
	private String nombreArchivoPagare;

	public String getUsuarioModificacion(){
		return usuarioModificacion;
	}

	public void setUsuarioModificacion(String usuarioModificacion) {
		this.usuarioModificacion = usuarioModificacion;
	}

	public String getUsuarioCreacion(){
		return usuarioCreacion;
	}

	public void setUsuarioCreacion(String usuarioCreacion) {
		this.usuarioCreacion = usuarioCreacion;
	}

	public String getNumero(){
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public java.sql.Date getFechaVencimiento(){
		return fechaVencimiento;
	}

	public void setFechaVencimiento(java.sql.Date fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}

	public String getNombreCliente(){
		return nombreCliente;
	}

	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}

	public String getTipoDocumento(){
		return tipoDocumento;
	}

	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public String getRuta(){
		return ruta;
	}

	public void setRuta(String ruta) {
		this.ruta = ruta;
	}

	public String getNumeroDocumento(){
		return numeroDocumento;
	}

	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	public String getNombreArchivoContratoMarco(){
		return nombreArchivoContratoMarco;
	}

	public void setNombreArchivoContratoMarco(String nombreArchivoContratoMarco) {
		this.nombreArchivoContratoMarco = nombreArchivoContratoMarco;
	}

	public boolean isFlagContratoMarco(){
		return flagContratoMarco;
	}

	public void setFlagContratoMarco(boolean flagContratoMarco) {
		this.flagContratoMarco = flagContratoMarco;
	}

	public boolean isEstado(){
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	public java.sql.Timestamp getFechaCreacion(){
		return fechaCreacion;
	}

	public void setFechaCreacion(java.sql.Timestamp fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public java.sql.Timestamp getFechaModificacion(){
		return fechaModificacion;
	}

	public void setFechaModificacion(java.sql.Timestamp fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}

	public String getDescripcion(){
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public boolean isFlagPagare(){
		return flagPagare;
	}

	public void setFlagPagare(boolean flagPagare) {
		this.flagPagare = flagPagare;
	}

	public String getNombreArchivoPagare(){
		return nombreArchivoPagare;
	}

	public void setNombreArchivoPagare(String nombreArchivoPagare) {
		this.nombreArchivoPagare = nombreArchivoPagare;
	}

}