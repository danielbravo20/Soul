package pe.com.captiva.dao.entity;
// Generated 01/12/2015 04:35:12 PM by Hibernate Tools 4.3.1.Final

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * MaeObservacionId generated by hbm2java
 */
@Embeddable
public class MaeObservacionId implements java.io.Serializable {

	private int codProyecto;
	private int codTarea;
	private int codMaeObservacion;

	public MaeObservacionId() {
	}

	public MaeObservacionId(int codProyecto, int codTarea, int codMaeObservacion) {
		this.codProyecto = codProyecto;
		this.codTarea = codTarea;
		this.codMaeObservacion = codMaeObservacion;
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

	@Column(name = "cod_mae_observacion", nullable = false)
	public int getCodMaeObservacion() {
		return this.codMaeObservacion;
	}

	public void setCodMaeObservacion(int codMaeObservacion) {
		this.codMaeObservacion = codMaeObservacion;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof MaeObservacionId))
			return false;
		MaeObservacionId castOther = (MaeObservacionId) other;

		return (this.getCodProyecto() == castOther.getCodProyecto()) && (this.getCodTarea() == castOther.getCodTarea())
				&& (this.getCodMaeObservacion() == castOther.getCodMaeObservacion());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getCodProyecto();
		result = 37 * result + this.getCodTarea();
		result = 37 * result + this.getCodMaeObservacion();
		return result;
	}

}
