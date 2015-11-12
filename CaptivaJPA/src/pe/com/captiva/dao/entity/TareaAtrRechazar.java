package pe.com.captiva.dao.entity;

// Generated 12/11/2015 12:28:21 PM by Hibernate Tools 4.3.1

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * TareaAtrRechazar generated by hbm2java
 */
@Entity
@Table(name = "tarea_atr_rechazar", schema = "soul")
public class TareaAtrRechazar implements java.io.Serializable {

	private TareaAtrRechazarId id;
	private Atributo atributo;
	private Tarea tarea;
	private int codProceso;
	private String javValOmision;
	private Character webFlgValidacion;

	public TareaAtrRechazar() {
	}

	public TareaAtrRechazar(TareaAtrRechazarId id, Atributo atributo,
			Tarea tarea, int codProceso) {
		this.id = id;
		this.atributo = atributo;
		this.tarea = tarea;
		this.codProceso = codProceso;
	}

	public TareaAtrRechazar(TareaAtrRechazarId id, Atributo atributo,
			Tarea tarea, int codProceso, String javValOmision,
			Character webFlgValidacion) {
		this.id = id;
		this.atributo = atributo;
		this.tarea = tarea;
		this.codProceso = codProceso;
		this.javValOmision = javValOmision;
		this.webFlgValidacion = webFlgValidacion;
	}

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "codTarea", column = @Column(name = "cod_tarea", nullable = false)),
			@AttributeOverride(name = "codAtributo", column = @Column(name = "cod_atributo", nullable = false)) })
	public TareaAtrRechazarId getId() {
		return this.id;
	}

	public void setId(TareaAtrRechazarId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cod_atributo", nullable = false, insertable = false, updatable = false)
	public Atributo getAtributo() {
		return this.atributo;
	}

	public void setAtributo(Atributo atributo) {
		this.atributo = atributo;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cod_tarea", nullable = false, insertable = false, updatable = false)
	public Tarea getTarea() {
		return this.tarea;
	}

	public void setTarea(Tarea tarea) {
		this.tarea = tarea;
	}

	@Column(name = "cod_proceso", nullable = false)
	public int getCodProceso() {
		return this.codProceso;
	}

	public void setCodProceso(int codProceso) {
		this.codProceso = codProceso;
	}

	@Column(name = "jav_val_omision", length = 250)
	public String getJavValOmision() {
		return this.javValOmision;
	}

	public void setJavValOmision(String javValOmision) {
		this.javValOmision = javValOmision;
	}

	@Column(name = "web_flg_validacion", length = 1)
	public Character getWebFlgValidacion() {
		return this.webFlgValidacion;
	}

	public void setWebFlgValidacion(Character webFlgValidacion) {
		this.webFlgValidacion = webFlgValidacion;
	}

}
