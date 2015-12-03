package pe.com.captiva.dao.entity;
// Generated 01/12/2015 04:35:12 PM by Hibernate Tools 4.3.1.Final

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
 * TareaAtrCompletar generated by hbm2java
 */
@Entity
@Table(name = "tarea_atr_completar", schema = "soul")
public class TareaAtrCompletar implements java.io.Serializable {

	private TareaAtrCompletarId id;
	private Atributo atributo;
	private Tarea tarea;
	private int codProceso;
	private String javValOmision;
	private char webFlgValidacion;
	private Integer webTabCampo;
	private Integer webOrdValidacion;

	public TareaAtrCompletar() {
	}

	public TareaAtrCompletar(TareaAtrCompletarId id, Atributo atributo, Tarea tarea, int codProceso,
			char webFlgValidacion) {
		this.id = id;
		this.atributo = atributo;
		this.tarea = tarea;
		this.codProceso = codProceso;
		this.webFlgValidacion = webFlgValidacion;
	}

	public TareaAtrCompletar(TareaAtrCompletarId id, Atributo atributo, Tarea tarea, int codProceso,
			String javValOmision, char webFlgValidacion, Integer webTabCampo, Integer webOrdValidacion) {
		this.id = id;
		this.atributo = atributo;
		this.tarea = tarea;
		this.codProceso = codProceso;
		this.javValOmision = javValOmision;
		this.webFlgValidacion = webFlgValidacion;
		this.webTabCampo = webTabCampo;
		this.webOrdValidacion = webOrdValidacion;
	}

	@EmbeddedId

	@AttributeOverrides({
			@AttributeOverride(name = "codTarea", column = @Column(name = "cod_tarea", nullable = false) ),
			@AttributeOverride(name = "codAtributo", column = @Column(name = "cod_atributo", nullable = false) ) })
	public TareaAtrCompletarId getId() {
		return this.id;
	}

	public void setId(TareaAtrCompletarId id) {
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

	@Column(name = "web_flg_validacion", nullable = false, length = 1)
	public char getWebFlgValidacion() {
		return this.webFlgValidacion;
	}

	public void setWebFlgValidacion(char webFlgValidacion) {
		this.webFlgValidacion = webFlgValidacion;
	}

	@Column(name = "web_tab_campo")
	public Integer getWebTabCampo() {
		return this.webTabCampo;
	}

	public void setWebTabCampo(Integer webTabCampo) {
		this.webTabCampo = webTabCampo;
	}

	@Column(name = "web_ord_validacion")
	public Integer getWebOrdValidacion() {
		return this.webOrdValidacion;
	}

	public void setWebOrdValidacion(Integer webOrdValidacion) {
		this.webOrdValidacion = webOrdValidacion;
	}

}
