package pe.com.soul.core.modelo;

import java.util.List;

public class Usuario extends BaseBean{

	private static final long serialVersionUID = 1L;
	
	public static final String SESSION_USUARIO_WEB_SOUL = "SESSION_USUARIO_WEB_SOUL";
	
	private long codigo;
	private char estado;
	private String usuario;
	private String clave;
	private String nombreCompleto;
	private String correo;
	
	private String sesionId;
	private String ipRemoto;
	private String hostRemoto;
	
	private List<Rol> roles;
	private List<Modulo> modulos;
	private List<Proceso> procesos;
	private List<ProcesoPlantilla> procesoPotenciales;
	
	public long getCodigo() {
		return codigo;
	}
	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}
	public char getEstado() {
		return estado;
	}
	public void setEstado(char estado) {
		this.estado = estado;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getClave() {
		return clave;
	}
	public void setClave(String clave) {
		this.clave = clave;
	}
	public String getNombreCompleto() {
		return nombreCompleto;
	}
	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public List<Rol> getRoles() {
		return roles;
	}
	public void setRoles(List<Rol> roles) {
		this.roles = roles;
	}
	public String getIpRemoto() {
		return ipRemoto;
	}
	public void setIpRemoto(String ipRemoto) {
		this.ipRemoto = ipRemoto;
	}
	public String getHostRemoto() {
		return hostRemoto;
	}
	public void setHostRemoto(String hostRemoto) {
		this.hostRemoto = hostRemoto;
	}
	public String getSesionId() {
		return sesionId;
	}
	public void setSesionId(String sesionId) {
		this.sesionId = sesionId;
	}
	public List<Modulo> getModulos() {
		return modulos;
	}
	public void setModulos(List<Modulo> modulos) {
		this.modulos = modulos;
	}
	public List<Proceso> getProcesos() {
		return procesos;
	}
	public void setProcesos(List<Proceso> procesos) {
		this.procesos = procesos;
	}
	public List<ProcesoPlantilla> getProcesoPotenciales() {
		return procesoPotenciales;
	}
	public void setProcesoPotenciales(List<ProcesoPlantilla> procesoPotenciales) {
		this.procesoPotenciales = procesoPotenciales;
	}
	
}
