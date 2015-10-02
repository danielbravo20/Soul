package pe.com.soul.core.modelo;

import java.util.Date;

public class Tarea extends BaseBean{

	private static final long serialVersionUID = 1L;

	public static final int ESTADO_PENDIENTE = 1;
	public static final int ESTADO_RECLAMADO = 2;
	public static final int ESTADO_TERMINADO = 3;
	
	public Tarea() {
	}
	
	public Tarea(	long codigoTarea,
					long codigoProceso,
					long codigoTareaPlantilla,
					int estadoTarea,
					String nombreTarea,
					String aleasTarea,
					String versionTarea,
					int prioridadTarea,
					Date fechaCreacionTarea,
					Date fechaReclamoTarea,
					Date fechaTerminoTarea,
					Date fechaUltimaModificacionTarea,
					String duenoTarea, 
					long codigoProcesoPlantilla,
					int estadoProceso,
					String nombreProceso,
					String aleasProceso,
					String versionProceso,
					Date fechaCreacionProceso,
					Date fechaTerminoProceso,
					String usuarioProceso) {
	
		this.codigoTarea = codigoTarea;
		this.version = versionTarea;
		this.estado = estadoTarea;
		this.nombre = nombreTarea;
		this.aleas = aleasTarea;
		this.prioridad = prioridadTarea;
		this.fechaCreacion = fechaCreacionTarea;
		this.fechaReclamo = fechaReclamoTarea;
		this.fechaTermino = fechaTerminoTarea;
		this.fechaUltimaModificacion = fechaUltimaModificacionTarea;
		this.dueno = duenoTarea;
		
		Proceso proceso = new Proceso();
		proceso.setCodigoProceso(codigoProceso);
		proceso.setCodigoProcesoPlantilla(codigoProcesoPlantilla);
		proceso.setEstado(estadoProceso);
		proceso.setNombre(nombreProceso);
		proceso.setAleas(aleasProceso);
		proceso.setVersion(versionProceso);
		proceso.setFechaCreacion(fechaCreacionProceso);
		proceso.setFechaTermino(fechaTerminoProceso);
		proceso.setCreador(usuarioProceso);
		
		this.proceso = proceso;
		
	}
	
	private long codigoTarea;
	private String version;
	private String dueno;
	private int estado;
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

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
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

	public String getDueno() {
		return dueno;
	}

	public void setDueno(String dueno) {
		this.dueno = dueno;
	}
	
}
