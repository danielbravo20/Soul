package pe.com.soul.core.modelo;

import java.util.Date;

public class Proceso extends BaseBean{

	private static final long serialVersionUID = 1L;

	public static final char ESTADO_EJECUTANDO = '1';
	public static final char ESTADO_FINALIZADO = '2';
	public static final char ESTADO_TERMINADO = '3';
	
	private long codigoProceso;
	private long codigoProcesoPlantilla;
	private String version;
	private char estado;
	private String nombre;
	private String aleas;
	private Date fechaCreacion;
	private Date fechaTermino;
	private Usuario usuario;
	private Tarea tareaInicial;
	
	public long getCodigoProceso() {
		return codigoProceso;
	}
	public void setCodigoProceso(long codigoProceso) {
		this.codigoProceso = codigoProceso;
	}
	public long getCodigoProcesoPlantilla() {
		return codigoProcesoPlantilla;
	}
	public void setCodigoProcesoPlantilla(long codigoProcesoPlantilla) {
		this.codigoProcesoPlantilla = codigoProcesoPlantilla;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public char getEstado() {
		return estado;
	}
	public void setEstado(char estado) {
		this.estado = estado;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getAleas() {
		return aleas;
	}
	public void setAleas(String aleas) {
		this.aleas = aleas;
	}
	public Date getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	public Date getFechaTermino() {
		return fechaTermino;
	}
	public void setFechaTermino(Date fechaTermino) {
		this.fechaTermino = fechaTermino;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public Tarea getTareaInicial() {
		return tareaInicial;
	}
	public void setTareaInicial(Tarea tareaInicial) {
		this.tareaInicial = tareaInicial;
	}

}
