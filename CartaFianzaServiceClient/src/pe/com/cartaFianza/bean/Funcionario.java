package pe.com.cartaFianza.bean;

import java.io.Serializable;

public class Funcionario implements Serializable{
 
	private static final long serialVersionUID = 1L;

	private String nombreAsistente;
	private boolean estado;
	private String usuarioGerente;
	private java.sql.Timestamp fechaCreacion;
	private String codigoTipoBanca;
	private String usuarioCreacion;
	private String usuarioFuncionario;
	private String nombreFuncionario;
	private java.sql.Timestamp fechaModificacion;
	private String usuarioAsistente;
	private String codigoIBS;
	private String usuarioModificacion;
	private String nombreGerente;

	public String getNombreAsistente(){
		return nombreAsistente;
	}

	public void setNombreAsistente(String nombreAsistente) {
		this.nombreAsistente = nombreAsistente;
	}

	public boolean isEstado(){
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	public String getUsuarioGerente(){
		return usuarioGerente;
	}

	public void setUsuarioGerente(String usuarioGerente) {
		this.usuarioGerente = usuarioGerente;
	}

	public java.sql.Timestamp getFechaCreacion(){
		return fechaCreacion;
	}

	public void setFechaCreacion(java.sql.Timestamp fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public String getCodigoTipoBanca(){
		return codigoTipoBanca;
	}

	public void setCodigoTipoBanca(String codigoTipoBanca) {
		this.codigoTipoBanca = codigoTipoBanca;
	}

	public String getUsuarioCreacion(){
		return usuarioCreacion;
	}

	public void setUsuarioCreacion(String usuarioCreacion) {
		this.usuarioCreacion = usuarioCreacion;
	}

	public String getUsuarioFuncionario(){
		return usuarioFuncionario;
	}

	public void setUsuarioFuncionario(String usuarioFuncionario) {
		this.usuarioFuncionario = usuarioFuncionario;
	}

	public String getNombreFuncionario(){
		return nombreFuncionario;
	}

	public void setNombreFuncionario(String nombreFuncionario) {
		this.nombreFuncionario = nombreFuncionario;
	}

	public java.sql.Timestamp getFechaModificacion(){
		return fechaModificacion;
	}

	public void setFechaModificacion(java.sql.Timestamp fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}

	public String getUsuarioAsistente(){
		return usuarioAsistente;
	}

	public void setUsuarioAsistente(String usuarioAsistente) {
		this.usuarioAsistente = usuarioAsistente;
	}

	public String getCodigoIBS(){
		return codigoIBS;
	}

	public void setCodigoIBS(String codigoIBS) {
		this.codigoIBS = codigoIBS;
	}

	public String getUsuarioModificacion(){
		return usuarioModificacion;
	}

	public void setUsuarioModificacion(String usuarioModificacion) {
		this.usuarioModificacion = usuarioModificacion;
	}

	public String getNombreGerente(){
		return nombreGerente;
	}

	public void setNombreGerente(String nombreGerente) {
		this.nombreGerente = nombreGerente;
	}

}