package pe.com.cartaFianza.bean;

import java.io.Serializable;

public class Cliente implements Serializable{
 
	private static final long serialVersionUID = 1L;

	private String telefonoContacto;
	private String descripcionClasificacionBanco;
	private String razonSocial;
	private String nombreContacto;
	private String codigoBanca;
	private String descripcionTipificacion;
	private String codigoDTR;
	private String tipoDocumento;
	private Integer codigoIBS;
	private String codigoFuncionario;
	private String descripcionBanca;
	private String correoContacto;
	private String vinculado;
	private String numeroDocumento;
	private String nombreFuncionario;
	private String correoRemitente;
	private String descripcionDTR;
	private String descripcionEmbargo;
	private Long codigoSolicitud;
	private String descripcionClasificacionSBS;

	public String getTelefonoContacto(){
		return telefonoContacto;
	}

	public void setTelefonoContacto(String telefonoContacto) {
		this.telefonoContacto = telefonoContacto;
	}

	public String getDescripcionClasificacionBanco(){
		return descripcionClasificacionBanco;
	}

	public void setDescripcionClasificacionBanco(String descripcionClasificacionBanco) {
		this.descripcionClasificacionBanco = descripcionClasificacionBanco;
	}

	public String getRazonSocial(){
		return razonSocial;
	}

	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}

	public String getNombreContacto(){
		return nombreContacto;
	}

	public void setNombreContacto(String nombreContacto) {
		this.nombreContacto = nombreContacto;
	}

	public String getCodigoBanca(){
		return codigoBanca;
	}

	public void setCodigoBanca(String codigoBanca) {
		this.codigoBanca = codigoBanca;
	}

	public String getDescripcionTipificacion(){
		return descripcionTipificacion;
	}

	public void setDescripcionTipificacion(String descripcionTipificacion) {
		this.descripcionTipificacion = descripcionTipificacion;
	}

	public String getCodigoDTR(){
		return codigoDTR;
	}

	public void setCodigoDTR(String codigoDTR) {
		this.codigoDTR = codigoDTR;
	}

	public String getTipoDocumento(){
		return tipoDocumento;
	}

	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public Integer getCodigoIBS(){
		return codigoIBS;
	}

	public void setCodigoIBS(Integer codigoIBS) {
		this.codigoIBS = codigoIBS;
	}

	public String getCodigoFuncionario(){
		return codigoFuncionario;
	}

	public void setCodigoFuncionario(String codigoFuncionario) {
		this.codigoFuncionario = codigoFuncionario;
	}

	public String getDescripcionBanca(){
		return descripcionBanca;
	}

	public void setDescripcionBanca(String descripcionBanca) {
		this.descripcionBanca = descripcionBanca;
	}

	public String getCorreoContacto(){
		return correoContacto;
	}

	public void setCorreoContacto(String correoContacto) {
		this.correoContacto = correoContacto;
	}

	public String getVinculado(){
		return vinculado;
	}

	public void setVinculado(String vinculado) {
		this.vinculado = vinculado;
	}

	public String getNumeroDocumento(){
		return numeroDocumento;
	}

	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	public String getNombreFuncionario(){
		return nombreFuncionario;
	}

	public void setNombreFuncionario(String nombreFuncionario) {
		this.nombreFuncionario = nombreFuncionario;
	}

	public String getCorreoRemitente(){
		return correoRemitente;
	}

	public void setCorreoRemitente(String correoRemitente) {
		this.correoRemitente = correoRemitente;
	}

	public String getDescripcionDTR(){
		return descripcionDTR;
	}

	public void setDescripcionDTR(String descripcionDTR) {
		this.descripcionDTR = descripcionDTR;
	}

	public String getDescripcionEmbargo(){
		return descripcionEmbargo;
	}

	public void setDescripcionEmbargo(String descripcionEmbargo) {
		this.descripcionEmbargo = descripcionEmbargo;
	}

	public Long getCodigoSolicitud(){
		return codigoSolicitud;
	}

	public void setCodigoSolicitud(Long codigoSolicitud) {
		this.codigoSolicitud = codigoSolicitud;
	}

	public String getDescripcionClasificacionSBS(){
		return descripcionClasificacionSBS;
	}

	public void setDescripcionClasificacionSBS(String descripcionClasificacionSBS) {
		this.descripcionClasificacionSBS = descripcionClasificacionSBS;
	}

}