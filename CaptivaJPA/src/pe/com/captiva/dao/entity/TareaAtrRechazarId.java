package pe.com.captiva.dao.entity;

// Generated 30/10/2015 11:10:41 AM by Hibernate Tools 4.3.1

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * TareaAtrRechazarId generated by hbm2java
 */
@Embeddable
public class TareaAtrRechazarId implements java.io.Serializable {

	private int codTarea;
	private int codAtributo;

	public TareaAtrRechazarId() {
	}

	public TareaAtrRechazarId(int codTarea, int codAtributo) {
		this.codTarea = codTarea;
		this.codAtributo = codAtributo;
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
		if (!(other instanceof TareaAtrRechazarId))
			return false;
		TareaAtrRechazarId castOther = (TareaAtrRechazarId) other;

		return (this.getCodTarea() == castOther.getCodTarea())
				&& (this.getCodAtributo() == castOther.getCodAtributo());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getCodTarea();
		result = 37 * result + this.getCodAtributo();
		return result;
	}

}
