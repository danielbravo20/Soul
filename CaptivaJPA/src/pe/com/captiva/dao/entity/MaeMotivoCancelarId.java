package pe.com.captiva.dao.entity;
// Generated 01/12/2015 04:35:12 PM by Hibernate Tools 4.3.1.Final

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * MaeMotivoCancelarId generated by hbm2java
 */
@Embeddable
public class MaeMotivoCancelarId implements java.io.Serializable {

	private int codProyecto;
	private int codTarea;
	private int codMaeMotivoCancelar;

	public MaeMotivoCancelarId() {
	}

	public MaeMotivoCancelarId(int codProyecto, int codTarea, int codMaeMotivoCancelar) {
		this.codProyecto = codProyecto;
		this.codTarea = codTarea;
		this.codMaeMotivoCancelar = codMaeMotivoCancelar;
	}

	@Column(name = "cod_proyecto", nullable = false)
	public int getCodProyecto() {
		return this.codProyecto;
	}

	public void setCodProyecto(int codProyecto) {
		this.codProyecto = codProyecto;
	}

	@Column(name = "cod_tarea", nullable = false)
	public int getCodTarea() {
		return this.codTarea;
	}

	public void setCodTarea(int codTarea) {
		this.codTarea = codTarea;
	}

	@Column(name = "cod_mae_motivo_cancelar", nullable = false)
	public int getCodMaeMotivoCancelar() {
		return this.codMaeMotivoCancelar;
	}

	public void setCodMaeMotivoCancelar(int codMaeMotivoCancelar) {
		this.codMaeMotivoCancelar = codMaeMotivoCancelar;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof MaeMotivoCancelarId))
			return false;
		MaeMotivoCancelarId castOther = (MaeMotivoCancelarId) other;

		return (this.getCodProyecto() == castOther.getCodProyecto()) && (this.getCodTarea() == castOther.getCodTarea())
				&& (this.getCodMaeMotivoCancelar() == castOther.getCodMaeMotivoCancelar());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getCodProyecto();
		result = 37 * result + this.getCodTarea();
		result = 37 * result + this.getCodMaeMotivoCancelar();
		return result;
	}

}
