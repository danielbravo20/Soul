package pe.com.soul.core.dao.entities;

// Generated 07/10/2015 10:05:11 AM by Hibernate Tools 4.3.1

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * TareaPotencialDueno generated by hbm2java
 */
@Entity
@Table(name = "tarea_potencial_dueno", schema = "proceso")
public class TareaPotencialDuenoEntity implements java.io.Serializable {

	private TareaPotencialDuenoId id;

	public TareaPotencialDuenoEntity() {
	}

	public TareaPotencialDuenoEntity(TareaPotencialDuenoId id) {
		this.id = id;
	}

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "codigoTarea", column = @Column(name = "codigo_tarea")),
			@AttributeOverride(name = "codigoProceso", column = @Column(name = "codigo_proceso")),
			@AttributeOverride(name = "codigoTareaPlantilla", column = @Column(name = "codigo_tarea_plantilla")),
			@AttributeOverride(name = "estadoTarea", column = @Column(name = "estado_tarea")),
			@AttributeOverride(name = "nombreTarea", column = @Column(name = "nombre_tarea", length = 120)),
			@AttributeOverride(name = "aleasTarea", column = @Column(name = "aleas_tarea", length = 100)),
			@AttributeOverride(name = "versionTarea", column = @Column(name = "version_tarea", length = 12)),
			@AttributeOverride(name = "prioridadTarea", column = @Column(name = "prioridad_tarea")),
			@AttributeOverride(name = "fechaCreacionTarea", column = @Column(name = "fecha_creacion_tarea", length = 29)),
			@AttributeOverride(name = "fechaReclamoTarea", column = @Column(name = "fecha_reclamo_tarea", length = 29)),
			@AttributeOverride(name = "fechaTerminoTarea", column = @Column(name = "fecha_termino_tarea", length = 29)),
			@AttributeOverride(name = "fechaUltimaModificacionTarea", column = @Column(name = "fecha_ultima_modificacion_tarea", length = 29)),
			@AttributeOverride(name = "duenoTarea", column = @Column(name = "dueno_tarea", length = 40)),
			@AttributeOverride(name = "codigoProcesoPlantilla", column = @Column(name = "codigo_proceso_plantilla")),
			@AttributeOverride(name = "estadoProceso", column = @Column(name = "estado_proceso")),
			@AttributeOverride(name = "nombreProceso", column = @Column(name = "nombre_proceso", length = 120)),
			@AttributeOverride(name = "aleasProceso", column = @Column(name = "aleas_proceso", length = 100)),
			@AttributeOverride(name = "versionProceso", column = @Column(name = "version_proceso", length = 12)),
			@AttributeOverride(name = "fechaCreacionProceso", column = @Column(name = "fecha_creacion_proceso", length = 29)),
			@AttributeOverride(name = "fechaTerminoProceso", column = @Column(name = "fecha_termino_proceso", length = 29)),
			@AttributeOverride(name = "usuarioCreacionProceso", column = @Column(name = "usuario_creacion_proceso", length = 40)),
			@AttributeOverride(name = "duenoPotencial", column = @Column(name = "dueno_potencial", length = 40)) })
	public TareaPotencialDuenoId getId() {
		return this.id;
	}

	public void setId(TareaPotencialDuenoId id) {
		this.id = id;
	}

}
