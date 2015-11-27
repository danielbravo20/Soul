package pe.com.captiva.dao.entity;
// Generated 26/11/2015 04:49:30 PM by Hibernate Tools 4.3.1.Final

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * MaeDocumentoTarea generated by hbm2java
 */
@Entity
@Table(name = "mae_documento_tarea", schema = "soul")
public class MaeDocumentoTarea implements java.io.Serializable {

	private MaeDocumentoTareaId id;
	private int codMaeDocumento;
	private char tipo;
	private char esObligatorio;
	private char estado;

	public MaeDocumentoTarea() {
	}

	public MaeDocumentoTarea(MaeDocumentoTareaId id, int codMaeDocumento, char tipo, char esObligatorio, char estado) {
		this.id = id;
		this.codMaeDocumento = codMaeDocumento;
		this.tipo = tipo;
		this.esObligatorio = esObligatorio;
		this.estado = estado;
	}

	@EmbeddedId

	@AttributeOverrides({
			@AttributeOverride(name = "codProyecto", column = @Column(name = "cod_proyecto", nullable = false) ),
			@AttributeOverride(name = "codTarea", column = @Column(name = "cod_tarea", nullable = false) ),
			@AttributeOverride(name = "codMaeDocumentoTarea", column = @Column(name = "cod_mae_documento_tarea", nullable = false) ) })
	public MaeDocumentoTareaId getId() {
		return this.id;
	}

	public void setId(MaeDocumentoTareaId id) {
		this.id = id;
	}

	@Column(name = "cod_mae_documento", nullable = false)
	public int getCodMaeDocumento() {
		return this.codMaeDocumento;
	}

	public void setCodMaeDocumento(int codMaeDocumento) {
		this.codMaeDocumento = codMaeDocumento;
	}

	@Column(name = "tipo", nullable = false, length = 1)
	public char getTipo() {
		return this.tipo;
	}

	public void setTipo(char tipo) {
		this.tipo = tipo;
	}

	@Column(name = "es_obligatorio", nullable = false, length = 1)
	public char getEsObligatorio() {
		return this.esObligatorio;
	}

	public void setEsObligatorio(char esObligatorio) {
		this.esObligatorio = esObligatorio;
	}

	@Column(name = "estado", nullable = false, length = 1)
	public char getEstado() {
		return this.estado;
	}

	public void setEstado(char estado) {
		this.estado = estado;
	}

}
