package pe.com.captiva.dao.entity;

// Generated 22/10/2015 09:43:27 PM by Hibernate Tools 4.3.1

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * TareaAtrObservarId generated by hbm2java
 */
@Embeddable
public class TareaAtrObservarId implements java.io.Serializable {

	private int codProceso;
	private int codTarea;
	private int codAtributo;

	public TareaAtrObservarId() {
	}

	public TareaAtrObservarId(int codProceso, int codTarea, int codAtributo) {
		this.codProceso = codProceso;
		this.codTarea = codTarea;
		this.codAtributo = codAtributo;
	}

	@Column(name = "cod_proceso", nullable = false)
	public int getCodProceso() {
		return this.codProceso;
	}

	public void setCodProceso(int codProceso) {
		this.codProceso = codProceso;
	}

	@Column(name = "cod_tarea", nullable = false)
	public int getCodTarea() {
		return this.codTarea;
	}

	public void setCodTarea(int codTarea) {
		this.codTarea = codTarea;
	}

	@Column(name = "cod_atributo", nullable = false)
	public int getCodAtributo() {
		return this.codAtributo;
	}

	public void setCodAtributo(int codAtributo) {
		this.codAtributo = codAtributo;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof TareaAtrObservarId))
			return false;
		TareaAtrObservarId castOther = (TareaAtrObservarId) other;

		return (this.getCodProceso() == castOther.getCodProceso())
				&& (this.getCodTarea() == castOther.getCodTarea())
				&& (this.getCodAtributo() == castOther.getCodAtributo());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getCodProceso();
		result = 37 * result + this.getCodTarea();
		result = 37 * result + this.getCodAtributo();
		return result;
	}

}
