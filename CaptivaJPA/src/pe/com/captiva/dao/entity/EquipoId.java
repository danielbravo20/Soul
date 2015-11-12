package pe.com.captiva.dao.entity;

// Generated 11/11/2015 09:33:32 PM by Hibernate Tools 4.3.1

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * EquipoId generated by hbm2java
 */
@Embeddable
public class EquipoId implements java.io.Serializable {

	private int codProyecto;
	private String codUsuario;

	public EquipoId() {
	}

	public EquipoId(int codProyecto, String codUsuario) {
		this.codProyecto = codProyecto;
		this.codUsuario = codUsuario;
	}

	@Column(name = "cod_proyecto", nullable = false)
	public int getCodProyecto() {
		return this.codProyecto;
	}

	public void setCodProyecto(int codProyecto) {
		this.codProyecto = codProyecto;
	}

	@Column(name = "cod_usuario", nullable = false, length = 50)
	public String getCodUsuario() {
		return this.codUsuario;
	}

	public void setCodUsuario(String codUsuario) {
		this.codUsuario = codUsuario;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof EquipoId))
			return false;
		EquipoId castOther = (EquipoId) other;

		return (this.getCodProyecto() == castOther.getCodProyecto())
				&& ((this.getCodUsuario() == castOther.getCodUsuario()) || (this
						.getCodUsuario() != null
						&& castOther.getCodUsuario() != null && this
						.getCodUsuario().equals(castOther.getCodUsuario())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getCodProyecto();
		result = 37
				* result
				+ (getCodUsuario() == null ? 0 : this.getCodUsuario()
						.hashCode());
		return result;
	}

}
