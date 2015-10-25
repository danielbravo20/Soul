package pe.com.soul.core.dao.entities;

// Generated 25/10/2015 05:15:08 AM by Hibernate Tools 4.3.1

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Tarea generated by hbm2java
 */
@Entity
@Table(name = "tarea", schema = "proceso")
public class TareaEntity implements java.io.Serializable {

	private long codigoTarea;
	private ProcesoEntity proceso;
	private int estadoTarea;
	private String nombreTarea;
	private String aleasTarea;
	private String versionTarea;
	private int prioridadTarea;
	private Date fechaCreacionTarea;
	private Date fechaReclamoTarea;
	private Date fechaTerminoTarea;
	private Date fechaUltimaModificacionTarea;
	private String duenoTarea;
	private long codigoTareaPlantilla;

	public TareaEntity() {
	}

	public TareaEntity(long codigoTarea, ProcesoEntity proceso, int estadoTarea,
			String nombreTarea, String aleasTarea, String versionTarea,
			int prioridadTarea, Date fechaCreacionTarea,
			Date fechaUltimaModificacionTarea, long codigoTareaPlantilla) {
		this.codigoTarea = codigoTarea;
		this.proceso = proceso;
		this.estadoTarea = estadoTarea;
		this.nombreTarea = nombreTarea;
		this.aleasTarea = aleasTarea;
		this.versionTarea = versionTarea;
		this.prioridadTarea = prioridadTarea;
		this.fechaCreacionTarea = fechaCreacionTarea;
		this.fechaUltimaModificacionTarea = fechaUltimaModificacionTarea;
		this.codigoTareaPlantilla = codigoTareaPlantilla;
	}

	public TareaEntity(long codigoTarea, ProcesoEntity proceso, int estadoTarea,
			String nombreTarea, String aleasTarea, String versionTarea,
			int prioridadTarea, Date fechaCreacionTarea,
			Date fechaReclamoTarea, Date fechaTerminoTarea,
			Date fechaUltimaModificacionTarea, String duenoTarea,
			long codigoTareaPlantilla) {
		this.codigoTarea = codigoTarea;
		this.proceso = proceso;
		this.estadoTarea = estadoTarea;
		this.nombreTarea = nombreTarea;
		this.aleasTarea = aleasTarea;
		this.versionTarea = versionTarea;
		this.prioridadTarea = prioridadTarea;
		this.fechaCreacionTarea = fechaCreacionTarea;
		this.fechaReclamoTarea = fechaReclamoTarea;
		this.fechaTerminoTarea = fechaTerminoTarea;
		this.fechaUltimaModificacionTarea = fechaUltimaModificacionTarea;
		this.duenoTarea = duenoTarea;
		this.codigoTareaPlantilla = codigoTareaPlantilla;
	}

	@Id
	@Column(name = "codigo_tarea", unique = true, nullable = false)
	@GeneratedValue(generator="id_seq_codigo_tarea") 
    @SequenceGenerator(name="id_seq_codigo_tarea",sequenceName="proceso.seq_codigo_tarea", allocationSize=0)
	public long getCodigoTarea() {
		return this.codigoTarea;
	}

	public void setCodigoTarea(long codigoTarea) {
		this.codigoTarea = codigoTarea;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "codigo_proceso", nullable = false)
	public ProcesoEntity getProceso() {
		return this.proceso;
	}

	public void setProceso(ProcesoEntity proceso) {
		this.proceso = proceso;
	}

	@Column(name = "estado_tarea", nullable = false)
	public int getEstadoTarea() {
		return this.estadoTarea;
	}

	public void setEstadoTarea(int estadoTarea) {
		this.estadoTarea = estadoTarea;
	}

	@Column(name = "nombre_tarea", nullable = false, length = 120)
	public String getNombreTarea() {
		return this.nombreTarea;
	}

	public void setNombreTarea(String nombreTarea) {
		this.nombreTarea = nombreTarea;
	}

	@Column(name = "aleas_tarea", nullable = false, length = 100)
	public String getAleasTarea() {
		return this.aleasTarea;
	}

	public void setAleasTarea(String aleasTarea) {
		this.aleasTarea = aleasTarea;
	}

	@Column(name = "version_tarea", nullable = false, length = 12)
	public String getVersionTarea() {
		return this.versionTarea;
	}

	public void setVersionTarea(String versionTarea) {
		this.versionTarea = versionTarea;
	}

	@Column(name = "prioridad_tarea", nullable = false)
	public int getPrioridadTarea() {
		return this.prioridadTarea;
	}

	public void setPrioridadTarea(int prioridadTarea) {
		this.prioridadTarea = prioridadTarea;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fecha_creacion_tarea", nullable = false, length = 29)
	public Date getFechaCreacionTarea() {
		return this.fechaCreacionTarea;
	}

	public void setFechaCreacionTarea(Date fechaCreacionTarea) {
		this.fechaCreacionTarea = fechaCreacionTarea;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fecha_reclamo_tarea", length = 29)
	public Date getFechaReclamoTarea() {
		return this.fechaReclamoTarea;
	}

	public void setFechaReclamoTarea(Date fechaReclamoTarea) {
		this.fechaReclamoTarea = fechaReclamoTarea;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fecha_termino_tarea", length = 29)
	public Date getFechaTerminoTarea() {
		return this.fechaTerminoTarea;
	}

	public void setFechaTerminoTarea(Date fechaTerminoTarea) {
		this.fechaTerminoTarea = fechaTerminoTarea;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fecha_ultima_modificacion_tarea", nullable = false, length = 29)
	public Date getFechaUltimaModificacionTarea() {
		return this.fechaUltimaModificacionTarea;
	}

	public void setFechaUltimaModificacionTarea(
			Date fechaUltimaModificacionTarea) {
		this.fechaUltimaModificacionTarea = fechaUltimaModificacionTarea;
	}

	@Column(name = "dueno_tarea", length = 40)
	public String getDuenoTarea() {
		return this.duenoTarea;
	}

	public void setDuenoTarea(String duenoTarea) {
		this.duenoTarea = duenoTarea;
	}

	@Column(name = "codigo_tarea_plantilla", nullable = false)
	public long getCodigoTareaPlantilla() {
		return this.codigoTareaPlantilla;
	}

	public void setCodigoTareaPlantilla(long codigoTareaPlantilla) {
		this.codigoTareaPlantilla = codigoTareaPlantilla;
	}

}
