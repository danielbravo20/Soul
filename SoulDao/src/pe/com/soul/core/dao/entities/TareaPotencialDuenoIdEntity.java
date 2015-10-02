package pe.com.soul.core.dao.entities;

// Generated 02/10/2015 05:12:22 PM by Hibernate Tools 4.3.1

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * TareaPotencialDuenoId generated by hbm2java
 */
@Embeddable
public class TareaPotencialDuenoIdEntity implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long codigoTarea;
	private Long codigoProceso;
	private Long codigoTareaPlantilla;
	private Integer estadoTarea;
	private String nombreTarea;
	private String aleasTarea;
	private String versionTarea;
	private Integer prioridadTarea;
	private Date fechaCreacionTarea;
	private Date fechaReclamoTarea;
	private Date fechaTerminoTarea;
	private Date fechaUltimaModificacionTarea;
	private String duenoTarea;
	private Long codigoProcesoPlantilla;
	private Integer estadoProceso;
	private String nombreProceso;
	private String aleasProceso;
	private String versionProceso;
	private Date fechaCreacionProceso;
	private Date fechaTerminoProceso;
	private String usuarioCreacionProceso;

	public TareaPotencialDuenoIdEntity() {
	}

	public TareaPotencialDuenoIdEntity(Long codigoTarea, Long codigoProceso,
			Long codigoTareaPlantilla, Integer estadoTarea, String nombreTarea,
			String aleasTarea, String versionTarea, Integer prioridadTarea,
			Date fechaCreacionTarea, Date fechaReclamoTarea,
			Date fechaTerminoTarea, Date fechaUltimaModificacionTarea,
			Long codigoProcesoPlantilla, Integer estadoProceso,
			String nombreProceso, String aleasProceso, String versionProceso,
			Date fechaCreacionProceso, Date fechaTerminoProceso,
			String usuarioCreacionProceso) {
		this.codigoTarea = codigoTarea;
		this.codigoProceso = codigoProceso;
		this.codigoTareaPlantilla = codigoTareaPlantilla;
		this.estadoTarea = estadoTarea;
		this.nombreTarea = nombreTarea;
		this.aleasTarea = aleasTarea;
		this.versionTarea = versionTarea;
		this.prioridadTarea = prioridadTarea;
		this.fechaCreacionTarea = fechaCreacionTarea;
		this.fechaReclamoTarea = fechaReclamoTarea;
		this.fechaTerminoTarea = fechaTerminoTarea;
		this.fechaUltimaModificacionTarea = fechaUltimaModificacionTarea;
		this.codigoProcesoPlantilla = codigoProcesoPlantilla;
		this.estadoProceso = estadoProceso;
		this.nombreProceso = nombreProceso;
		this.aleasProceso = aleasProceso;
		this.versionProceso = versionProceso;
		this.fechaCreacionProceso = fechaCreacionProceso;
		this.fechaTerminoProceso = fechaTerminoProceso;
		this.usuarioCreacionProceso = usuarioCreacionProceso;
	}

	@Column(name = "codigo_tarea")
	public Long getCodigoTarea() {
		return this.codigoTarea;
	}

	public void setCodigoTarea(Long codigoTarea) {
		this.codigoTarea = codigoTarea;
	}

	@Column(name = "codigo_proceso")
	public Long getCodigoProceso() {
		return this.codigoProceso;
	}

	public void setCodigoProceso(Long codigoProceso) {
		this.codigoProceso = codigoProceso;
	}

	@Column(name = "codigo_tarea_plantilla")
	public Long getCodigoTareaPlantilla() {
		return this.codigoTareaPlantilla;
	}

	public void setCodigoTareaPlantilla(Long codigoTareaPlantilla) {
		this.codigoTareaPlantilla = codigoTareaPlantilla;
	}

	@Column(name = "estado_tarea")
	public Integer getEstadoTarea() {
		return this.estadoTarea;
	}

	public void setEstadoTarea(Integer estadoTarea) {
		this.estadoTarea = estadoTarea;
	}

	@Column(name = "nombre_tarea", length = 120)
	public String getNombreTarea() {
		return this.nombreTarea;
	}

	public void setNombreTarea(String nombreTarea) {
		this.nombreTarea = nombreTarea;
	}

	@Column(name = "aleas_tarea", length = 100)
	public String getAleasTarea() {
		return this.aleasTarea;
	}

	public void setAleasTarea(String aleasTarea) {
		this.aleasTarea = aleasTarea;
	}

	@Column(name = "version_tarea", length = 12)
	public String getVersionTarea() {
		return this.versionTarea;
	}

	public void setVersionTarea(String versionTarea) {
		this.versionTarea = versionTarea;
	}

	@Column(name = "prioridad_tarea")
	public Integer getPrioridadTarea() {
		return this.prioridadTarea;
	}

	public void setPrioridadTarea(Integer prioridadTarea) {
		this.prioridadTarea = prioridadTarea;
	}

	@Column(name = "fecha_creacion_tarea", length = 29)
	public Date getFechaCreacionTarea() {
		return this.fechaCreacionTarea;
	}

	public void setFechaCreacionTarea(Date fechaCreacionTarea) {
		this.fechaCreacionTarea = fechaCreacionTarea;
	}

	@Column(name = "fecha_reclamo_tarea", length = 29)
	public Date getFechaReclamoTarea() {
		return this.fechaReclamoTarea;
	}

	public void setFechaReclamoTarea(Date fechaReclamoTarea) {
		this.fechaReclamoTarea = fechaReclamoTarea;
	}

	@Column(name = "fecha_termino_tarea", length = 29)
	public Date getFechaTerminoTarea() {
		return this.fechaTerminoTarea;
	}

	public void setFechaTerminoTarea(Date fechaTerminoTarea) {
		this.fechaTerminoTarea = fechaTerminoTarea;
	}

	@Column(name = "fecha_ultima_modificacion_tarea", length = 29)
	public Date getFechaUltimaModificacionTarea() {
		return this.fechaUltimaModificacionTarea;
	}

	public void setFechaUltimaModificacionTarea(
			Date fechaUltimaModificacionTarea) {
		this.fechaUltimaModificacionTarea = fechaUltimaModificacionTarea;
	}

	@Column(name = "dueno_tarea", length = 40)
	public String getDuenoTarea() {
		return duenoTarea;
	}

	public void setDuenoTarea(String duenoTarea) {
		this.duenoTarea = duenoTarea;
	}

	@Column(name = "codigo_proceso_plantilla", length = 40)
	public Long getCodigoProcesoPlantilla() {
		return this.codigoProcesoPlantilla;
	}

	public void setCodigoProcesoPlantilla(Long codigoProcesoPlantilla) {
		this.codigoProcesoPlantilla = codigoProcesoPlantilla;
	}

	@Column(name = "estado_proceso")
	public Integer getEstadoProceso() {
		return this.estadoProceso;
	}

	public void setEstadoProceso(Integer estadoProceso) {
		this.estadoProceso = estadoProceso;
	}

	@Column(name = "nombre_proceso", length = 120)
	public String getNombreProceso() {
		return this.nombreProceso;
	}

	public void setNombreProceso(String nombreProceso) {
		this.nombreProceso = nombreProceso;
	}

	@Column(name = "aleas_proceso", length = 100)
	public String getAleasProceso() {
		return this.aleasProceso;
	}

	public void setAleasProceso(String aleasProceso) {
		this.aleasProceso = aleasProceso;
	}

	@Column(name = "version_proceso", length = 12)
	public String getVersionProceso() {
		return this.versionProceso;
	}

	public void setVersionProceso(String versionProceso) {
		this.versionProceso = versionProceso;
	}

	@Column(name = "fecha_creacion_proceso", length = 29)
	public Date getFechaCreacionProceso() {
		return this.fechaCreacionProceso;
	}

	public void setFechaCreacionProceso(Date fechaCreacionProceso) {
		this.fechaCreacionProceso = fechaCreacionProceso;
	}

	@Column(name = "fecha_termino_proceso", length = 29)
	public Date getFechaTerminoProceso() {
		return this.fechaTerminoProceso;
	}

	public void setFechaTerminoProceso(Date fechaTerminoProceso) {
		this.fechaTerminoProceso = fechaTerminoProceso;
	}

	@Column(name = "usuario_creacion_proceso", length = 40)
	public String getUsuarioCreacionProceso() {
		return this.usuarioCreacionProceso;
	}

	public void setUsuarioCreacionProceso(String usuarioCreacionProceso) {
		this.usuarioCreacionProceso = usuarioCreacionProceso;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof TareaPotencialDuenoIdEntity))
			return false;
		TareaPotencialDuenoIdEntity castOther = (TareaPotencialDuenoIdEntity) other;

		return ((this.getCodigoTarea() == castOther.getCodigoTarea()) || (this
				.getCodigoTarea() != null && castOther.getCodigoTarea() != null && this
				.getCodigoTarea().equals(castOther.getCodigoTarea())))
				&& ((this.getCodigoProceso() == castOther.getCodigoProceso()) || (this
						.getCodigoProceso() != null
						&& castOther.getCodigoProceso() != null && this
						.getCodigoProceso()
						.equals(castOther.getCodigoProceso())))
				&& ((this.getCodigoTareaPlantilla() == castOther
						.getCodigoTareaPlantilla()) || (this
						.getCodigoTareaPlantilla() != null
						&& castOther.getCodigoTareaPlantilla() != null && this
						.getCodigoTareaPlantilla().equals(
								castOther.getCodigoTareaPlantilla())))
				&& ((this.getEstadoTarea() == castOther.getEstadoTarea()) || (this
						.getEstadoTarea() != null
						&& castOther.getEstadoTarea() != null && this
						.getEstadoTarea().equals(castOther.getEstadoTarea())))
				&& ((this.getNombreTarea() == castOther.getNombreTarea()) || (this
						.getNombreTarea() != null
						&& castOther.getNombreTarea() != null && this
						.getNombreTarea().equals(castOther.getNombreTarea())))
				&& ((this.getAleasTarea() == castOther.getAleasTarea()) || (this
						.getAleasTarea() != null
						&& castOther.getAleasTarea() != null && this
						.getAleasTarea().equals(castOther.getAleasTarea())))
				&& ((this.getVersionTarea() == castOther.getVersionTarea()) || (this
						.getVersionTarea() != null
						&& castOther.getVersionTarea() != null && this
						.getVersionTarea().equals(castOther.getVersionTarea())))
				&& ((this.getPrioridadTarea() == castOther.getPrioridadTarea()) || (this
						.getPrioridadTarea() != null
						&& castOther.getPrioridadTarea() != null && this
						.getPrioridadTarea().equals(
								castOther.getPrioridadTarea())))
				&& ((this.getFechaCreacionTarea() == castOther
						.getFechaCreacionTarea()) || (this
						.getFechaCreacionTarea() != null
						&& castOther.getFechaCreacionTarea() != null && this
						.getFechaCreacionTarea().equals(
								castOther.getFechaCreacionTarea())))
				&& ((this.getFechaReclamoTarea() == castOther
						.getFechaReclamoTarea()) || (this
						.getFechaReclamoTarea() != null
						&& castOther.getFechaReclamoTarea() != null && this
						.getFechaReclamoTarea().equals(
								castOther.getFechaReclamoTarea())))
				&& ((this.getFechaTerminoTarea() == castOther
						.getFechaTerminoTarea()) || (this
						.getFechaTerminoTarea() != null
						&& castOther.getFechaTerminoTarea() != null && this
						.getFechaTerminoTarea().equals(
								castOther.getFechaTerminoTarea())))
				&& ((this.getFechaUltimaModificacionTarea() == castOther
						.getFechaUltimaModificacionTarea()) || (this
						.getFechaUltimaModificacionTarea() != null
						&& castOther.getFechaUltimaModificacionTarea() != null && this
						.getFechaUltimaModificacionTarea().equals(
								castOther.getFechaUltimaModificacionTarea())))
				&& ((this.getCodigoProcesoPlantilla() == castOther
						.getCodigoProcesoPlantilla()) || (this
						.getCodigoProcesoPlantilla() != null
						&& castOther.getCodigoProcesoPlantilla() != null && this
						.getCodigoProcesoPlantilla().equals(
								castOther.getCodigoProcesoPlantilla())))
				&& ((this.getEstadoProceso() == castOther.getEstadoProceso()) || (this
						.getEstadoProceso() != null
						&& castOther.getEstadoProceso() != null && this
						.getEstadoProceso()
						.equals(castOther.getEstadoProceso())))
				&& ((this.getNombreProceso() == castOther.getNombreProceso()) || (this
						.getNombreProceso() != null
						&& castOther.getNombreProceso() != null && this
						.getNombreProceso()
						.equals(castOther.getNombreProceso())))
				&& ((this.getAleasProceso() == castOther.getAleasProceso()) || (this
						.getAleasProceso() != null
						&& castOther.getAleasProceso() != null && this
						.getAleasProceso().equals(castOther.getAleasProceso())))
				&& ((this.getVersionProceso() == castOther.getVersionProceso()) || (this
						.getVersionProceso() != null
						&& castOther.getVersionProceso() != null && this
						.getVersionProceso().equals(
								castOther.getVersionProceso())))
				&& ((this.getFechaCreacionProceso() == castOther
						.getFechaCreacionProceso()) || (this
						.getFechaCreacionProceso() != null
						&& castOther.getFechaCreacionProceso() != null && this
						.getFechaCreacionProceso().equals(
								castOther.getFechaCreacionProceso())))
				&& ((this.getFechaTerminoProceso() == castOther
						.getFechaTerminoProceso()) || (this
						.getFechaTerminoProceso() != null
						&& castOther.getFechaTerminoProceso() != null && this
						.getFechaTerminoProceso().equals(
								castOther.getFechaTerminoProceso())))
				&& ((this.getUsuarioCreacionProceso() == castOther
						.getUsuarioCreacionProceso()) || (this
						.getUsuarioCreacionProceso() != null
						&& castOther.getUsuarioCreacionProceso() != null && this
						.getUsuarioCreacionProceso().equals(
								castOther.getUsuarioCreacionProceso())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getCodigoTarea() == null ? 0 : this.getCodigoTarea()
						.hashCode());
		result = 37
				* result
				+ (getCodigoProceso() == null ? 0 : this.getCodigoProceso()
						.hashCode());
		result = 37
				* result
				+ (getCodigoTareaPlantilla() == null ? 0 : this
						.getCodigoTareaPlantilla().hashCode());
		result = 37
				* result
				+ (getEstadoTarea() == null ? 0 : this.getEstadoTarea()
						.hashCode());
		result = 37
				* result
				+ (getNombreTarea() == null ? 0 : this.getNombreTarea()
						.hashCode());
		result = 37
				* result
				+ (getAleasTarea() == null ? 0 : this.getAleasTarea()
						.hashCode());
		result = 37
				* result
				+ (getVersionTarea() == null ? 0 : this.getVersionTarea()
						.hashCode());
		result = 37
				* result
				+ (getPrioridadTarea() == null ? 0 : this.getPrioridadTarea()
						.hashCode());
		result = 37
				* result
				+ (getFechaCreacionTarea() == null ? 0 : this
						.getFechaCreacionTarea().hashCode());
		result = 37
				* result
				+ (getFechaReclamoTarea() == null ? 0 : this
						.getFechaReclamoTarea().hashCode());
		result = 37
				* result
				+ (getFechaTerminoTarea() == null ? 0 : this
						.getFechaTerminoTarea().hashCode());
		result = 37
				* result
				+ (getFechaUltimaModificacionTarea() == null ? 0 : this
						.getFechaUltimaModificacionTarea().hashCode());
		result = 37
				* result
				+ (getCodigoProcesoPlantilla() == null ? 0 : this
						.getCodigoProcesoPlantilla().hashCode());
		result = 37
				* result
				+ (getEstadoProceso() == null ? 0 : this.getEstadoProceso()
						.hashCode());
		result = 37
				* result
				+ (getNombreProceso() == null ? 0 : this.getNombreProceso()
						.hashCode());
		result = 37
				* result
				+ (getAleasProceso() == null ? 0 : this.getAleasProceso()
						.hashCode());
		result = 37
				* result
				+ (getVersionProceso() == null ? 0 : this.getVersionProceso()
						.hashCode());
		result = 37
				* result
				+ (getFechaCreacionProceso() == null ? 0 : this
						.getFechaCreacionProceso().hashCode());
		result = 37
				* result
				+ (getFechaTerminoProceso() == null ? 0 : this
						.getFechaTerminoProceso().hashCode());
		result = 37
				* result
				+ (getUsuarioCreacionProceso() == null ? 0 : this
						.getUsuarioCreacionProceso().hashCode());
		return result;
	}

}
