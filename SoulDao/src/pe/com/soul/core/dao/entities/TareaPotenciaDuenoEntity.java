package pe.com.soul.core.dao.entities;

// Generated 01/10/2015 10:53:53 PM by Hibernate Tools 4.3.1

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * TareaPotenciaDueno generated by hbm2java
 */
@Entity
@Table(name = "tarea_potencia_dueno", schema = "proceso")
public class TareaPotenciaDuenoEntity implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private TareaPotenciaDuenoIdEntity id;

	public TareaPotenciaDuenoEntity() {
	}

	public TareaPotenciaDuenoEntity(TareaPotenciaDuenoIdEntity id) {
		this.id = id;
	}

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "codigoTarea", column = @Column(name = "codigo_tarea")),
			@AttributeOverride(name = "codigoProceso", column = @Column(name = "codigo_proceso")),
			@AttributeOverride(name = "codigoTareaPlantilla", column = @Column(name = "codigo_tarea_plantilla")),
			@AttributeOverride(name = "estado", column = @Column(name = "estado", length = 1)),
			@AttributeOverride(name = "nombre", column = @Column(name = "nombre", length = 120)),
			@AttributeOverride(name = "aleas", column = @Column(name = "aleas", length = 100)),
			@AttributeOverride(name = "version", column = @Column(name = "version", length = 12)),
			@AttributeOverride(name = "prioridad", column = @Column(name = "prioridad")),
			@AttributeOverride(name = "fechaCreacion", column = @Column(name = "fecha_creacion", length = 29)),
			@AttributeOverride(name = "fechaReclamo", column = @Column(name = "fecha_reclamo", length = 29)),
			@AttributeOverride(name = "fechaTermino", column = @Column(name = "fecha_termino", length = 29)),
			@AttributeOverride(name = "fechaUltimaModificacion", column = @Column(name = "fecha_ultima_modificacion", length = 29)),
			@AttributeOverride(name = "dueno", column = @Column(name = "dueno", length = 40)),
			@AttributeOverride(name = "codigoUsuario", column = @Column(name = "codigo_usuario")) })
	public TareaPotenciaDuenoIdEntity getId() {
		return this.id;
	}

	public void setId(TareaPotenciaDuenoIdEntity id) {
		this.id = id;
	}

}
