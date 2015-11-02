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
@Table(name = "CLIENTE", schema = "BFP_CARTA_FIANZA")
public class ClienteEntity implements Serializable{

	private static final long serialVersionUID = 1L;

	private String codigoFuncionario;
	private String codigoDTR;
	private Integer codigoIBS;
	private String descripcionDTR;
	private String correoContacto;
	private String descripcionClasificacionBanco;
	private String tipoDocumento;
	private String nombreContacto;
	private String descripcionTipificacion;
	private Long codigoSolicitud;
	private String correoRemitente;
	private String nombreFuncionario;
	private String numeroDocumento;
	private String razonSocial;
	private String vinculado;
	private String descripcionBanca;
	private String codigoBanca;
	private String telefonoContacto;
	private String descripcionClasificacionSBS;
	private String descripcionEmbargo;

	public String getCodigoFuncionario(){
		return codigoFuncionario;
	}

	public void setCodigoFuncionario(String codigoFuncionario) {
		this.codigoFuncionario = codigoFuncionario;
	}

	public String getCodigoDTR(){
		return codigoDTR;
	}

	public void setCodigoDTR(String codigoDTR) {
		this.codigoDTR = codigoDTR;
	}

	public Integer getCodigoIBS(){
		return codigoIBS;
	}

	public void setCodigoIBS(Integer codigoIBS) {
		this.codigoIBS = codigoIBS;
	}

	public String getDescripcionDTR(){
		return descripcionDTR;
	}

	public void setDescripcionDTR(String descripcionDTR) {
		this.descripcionDTR = descripcionDTR;
	}

	public String getCorreoContacto(){
		return correoContacto;
	}

	public void setCorreoContacto(String correoContacto) {
		this.correoContacto = correoContacto;
	}

	public String getDescripcionClasificacionBanco(){
		return descripcionClasificacionBanco;
	}

	public void setDescripcionClasificacionBanco(String descripcionClasificacionBanco) {
		this.descripcionClasificacionBanco = descripcionClasificacionBanco;
	}

	public String getTipoDocumento(){
		return tipoDocumento;
	}

	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public String getNombreContacto(){
		return nombreContacto;
	}

	public void setNombreContacto(String nombreContacto) {
		this.nombreContacto = nombreContacto;
	}

	public String getDescripcionTipificacion(){
		return descripcionTipificacion;
	}

	public void setDescripcionTipificacion(String descripcionTipificacion) {
		this.descripcionTipificacion = descripcionTipificacion;
	}

	public Long getCodigoSolicitud(){
		return codigoSolicitud;
	}

	public void setCodigoSolicitud(Long codigoSolicitud) {
		this.codigoSolicitud = codigoSolicitud;
	}

	public String getCorreoRemitente(){
		return correoRemitente;
	}

	public void setCorreoRemitente(String correoRemitente) {
		this.correoRemitente = correoRemitente;
	}

	public String getNombreFuncionario(){
		return nombreFuncionario;
	}

	public void setNombreFuncionario(String nombreFuncionario) {
		this.nombreFuncionario = nombreFuncionario;
	}

	public String getNumeroDocumento(){
		return numeroDocumento;
	}

	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	public String getRazonSocial(){
		return razonSocial;
	}

	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}

	public String getVinculado(){
		return vinculado;
	}

	public void setVinculado(String vinculado) {
		this.vinculado = vinculado;
	}

	public String getDescripcionBanca(){
		return descripcionBanca;
	}

	public void setDescripcionBanca(String descripcionBanca) {
		this.descripcionBanca = descripcionBanca;
	}

	public String getCodigoBanca(){
		return codigoBanca;
	}

	public void setCodigoBanca(String codigoBanca) {
		this.codigoBanca = codigoBanca;
	}

	public String getTelefonoContacto(){
		return telefonoContacto;
	}

	public void setTelefonoContacto(String telefonoContacto) {
		this.telefonoContacto = telefonoContacto;
	}

	public String getDescripcionClasificacionSBS(){
		return descripcionClasificacionSBS;
	}

	public void setDescripcionClasificacionSBS(String descripcionClasificacionSBS) {
		this.descripcionClasificacionSBS = descripcionClasificacionSBS;
	}

	public String getDescripcionEmbargo(){
		return descripcionEmbargo;
	}

	public void setDescripcionEmbargo(String descripcionEmbargo) {
		this.descripcionEmbargo = descripcionEmbargo;
	}

}