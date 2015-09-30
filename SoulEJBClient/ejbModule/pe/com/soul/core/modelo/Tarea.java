package pe.com.soul.core.modelo;

import java.util.Date;

public class Tarea extends BaseBean{

	private static final long serialVersionUID = 1L;

	public static final char ESTADO_PENDIENTE = '1';
	public static final char ESTADO_RECLAMADO = '2';
	public static final char ESTADO_ATENDIDO = '3';
	
	private long codigoTarea;
	private String version;
	private Usuario dueno;
	private char estado;
	private String nombre;
	private String aleas;
	private int prioridad;
	private Date fechaCreacion;
	private Date fechaReclamo;
	private Date fechaTermino;
	private Date fechaUltimaModificacion;
	private Proceso proceso;
	private TareaPlantilla tareaPlantilla;
	
	public long getCodigoTarea() {
		return codigoTarea;
	}
	public void setCodigoTarea(long codigoTarea) {
		this.codigoTarea = codigoTarea;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public Usuario getDueno() {
		return dueno;
	}
	public void setDueno(Usuario dueno) {
		this.dueno = dueno;
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
	public int getPrioridad() {
		return prioridad;
	}
	public void setPrioridad(int prioridad) {
		this.prioridad = prioridad;
	}
	public Date getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	public Date getFechaReclamo() {
		return fechaReclamo;
	}
	public void setFechaReclamo(Date fechaReclamo) {
		this.fechaReclamo = fechaReclamo;
	}
	public Date getFechaTermino() {
		return fechaTermino;
	}
	public void setFechaTermino(Date fechaTermino) {
		this.fechaTermino = fechaTermino;
	}
	public Date getFechaUltimaModificacion() {
		return fechaUltimaModificacion;
	}
	public void setFechaUltimaModificacion(Date fechaUltimaModificacion) {
		this.fechaUltimaModificacion = fechaUltimaModificacion;
	}
	public Proceso getProceso() {
		return proceso;
	}
	public void setProceso(Proceso proceso) {
		this.proceso = proceso;
	}
	public TareaPlantilla getTareaPlantilla() {
		return tareaPlantilla;
	}
	public void setTareaPlantilla(TareaPlantilla tareaPlantilla) {
		this.tareaPlantilla = tareaPlantilla;
	}
	
}
