package pe.com.cartaFianza.dao.entity;

import java.io.Serializable;

public class FuncionarioEntity implements Serializable{
 
	private static final long serialVersionUID = 1L;

	private String usuarioFuncionario;
	private java.sql.Timestamp fechaModificacion;
	private java.sql.Timestamp fechaCreacion;
	private String usuarioAsistente;
	private String codigoTipoBanca;
	private boolean estado;
	private String usuarioCreacion;
	private String nombreFuncionario;
	private String usuarioModificacion;
	private String codigoIBS;
	private String usuarioGerente;
	private String nombreGerente;
	private String nombreAsistente;

	public String getUsuarioFuncionario(){
		return usuarioFuncionario;
	}

	public void setUsuarioFuncionario(String usuarioFuncionario) {
		this.usuarioFuncionario = usuarioFuncionario;
	}

	public java.sql.Timestamp getFechaModificacion(){
		return fechaModificacion;
	}

	public void setFechaModificacion(java.sql.Timestamp fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}

	public java.sql.Timestamp getFechaCreacion(){
		return fechaCreacion;
	}

	public void setFechaCreacion(java.sql.Timestamp fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public String getUsuarioAsistente(){
		return usuarioAsistente;
	}

	public void setUsuarioAsistente(String usuarioAsistente) {
		this.usuarioAsistente = usuarioAsistente;
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

	public String getNombreFuncionario(){
		return nombreFuncionario;
	}

	public void setNombreFuncionario(String nombreFuncionario) {
		this.nombreFuncionario = nombreFuncionario;
	}

	public String getUsuarioModificacion(){
		return usuarioModificacion;
	}

	public void setUsuarioModificacion(String usuarioModificacion) {
		this.usuarioModificacion = usuarioModificacion;
	}

	public String getCodigoIBS(){
		return codigoIBS;
	}

	public void setCodigoIBS(String codigoIBS) {
		this.codigoIBS = codigoIBS;
	}

	public String getUsuarioGerente(){
		return usuarioGerente;
	}

	public void setUsuarioGerente(String usuarioGerente) {
		this.usuarioGerente = usuarioGerente;
	}

	public String getNombreGerente(){
		return nombreGerente;
	}

	public void setNombreGerente(String nombreGerente) {
		this.nombreGerente = nombreGerente;
	}

	public String getNombreAsistente(){
		return nombreAsistente;
	}

	public void setNombreAsistente(String nombreAsistente) {
		this.nombreAsistente = nombreAsistente;
	}

}