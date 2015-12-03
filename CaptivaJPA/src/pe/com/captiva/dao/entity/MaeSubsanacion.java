package pe.com.captiva.dao.entity;
// Generated 01/12/2015 04:35:12 PM by Hibernate Tools 4.3.1.Final

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * MaeSubsanacion generated by hbm2java
 */
@Entity
@Table(name = "mae_subsanacion", schema = "soul")
public class MaeSubsanacion implements java.io.Serializable {

	private MaeSubsanacionId id;
	private String nombre;
	private String descripcion;
	private char estado;
	private char tipoSustento;
	private Integer codMaeDocumentoTarea;

	public MaeSubsanacion() {
	}

	public MaeSubsanacion(MaeSubsanacionId id, String nombre, char estado, char tipoSustento) {
		this.id = id;
		this.nombre = nombre;
		this.estado = estado;
		this.tipoSustento = tipoSustento;
	}

	public MaeSubsanacion(MaeSubsanacionId id, String nombre, String descripcion, char estado, char tipoSustento,
			Integer codMaeDocumentoTarea) {
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.estado = estado;
		this.tipoSustento = tipoSustento;
		this.codMaeDocumentoTarea = codMaeDocumentoTarea;
	}

	@EmbeddedId

	@AttributeOverrides({
			@AttributeOverride(name = "codProyecto", column = @Column(name = "cod_proyecto", nullable = false) ),
			@AttributeOverride(name = "codTarea", column = @Column(name = "cod_tarea", nullable = false) ),
			@AttributeOverride(name = "codMaeObservacion", column = @Column(name = "cod_mae_observacion", nullable = false) ),
			@AttributeOverride(name = "codMaeSubsanacion", column = @Column(name = "cod_mae_subsanacion", nullable = false) ) })
	public MaeSubsanacionId getId() {
		return this.id;
	}

	public void setId(MaeSubsanacionId id) {
		this.id = id;
	}

	@Column(name = "nombre", nullable = false, length = 100)
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Column(name = "descripcion", length = 250)
	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Column(name = "estado", nullable = false, length = 1)
	public char getEstado() {
		return this.estado;
	}

	public void setEstado(char estado) {
		this.estado = estado;
	}

	@Column(name = "tipo_sustento", nullable = false, length = 1)
	public char getTipoSustento() {
		return this.tipoSustento;
	}

	public void setTipoSustento(char tipoSustento) {
		this.tipoSustento = tipoSustento;
	}

	@Column(name = "cod_mae_documento_tarea")
	public Integer getCodMaeDocumentoTarea() {
		return this.codMaeDocumentoTarea;
	}

	public void setCodMaeDocumentoTarea(Integer codMaeDocumentoTarea) {
		this.codMaeDocumentoTarea = codMaeDocumentoTarea;
	}

}
