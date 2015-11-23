package pe.com.captiva.dao.entity;

// Generated 23/11/2015 04:50:15 PM by Hibernate Tools 4.3.1

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * MaeDocumentoId generated by hbm2java
 */
@Embeddable
public class MaeDocumentoId implements java.io.Serializable {

	private int codProyecto;
	private int codMaeDocumento;

	public MaeDocumentoId() {
	}

	public MaeDocumentoId(int codProyecto, int codMaeDocumento) {
		this.codProyecto = codProyecto;
		this.codMaeDocumento = codMaeDocumento;
	}

	@Column(name = "cod_proyecto", nullable = false)
	public int getCodProyecto() {
		return this.codProyecto;
	}

	public void setCodProyecto(int codProyecto) {
		this.codProyecto = codProyecto;
	}

	@Column(name = "cod_mae_documento", nullable = false)
	public int getCodMaeDocumento() {
		return this.codMaeDocumento;
	}

	public void setCodMaeDocumento(int codMaeDocumento) {
		this.codMaeDocumento = codMaeDocumento;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof MaeDocumentoId))
			return false;
		MaeDocumentoId castOther = (MaeDocumentoId) other;

		return (this.getCodProyecto() == castOther.getCodProyecto())
				&& (this.getCodMaeDocumento() == castOther.getCodMaeDocumento());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getCodProyecto();
		result = 37 * result + this.getCodMaeDocumento();
		return result;
	}

}
