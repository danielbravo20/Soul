package pe.com.captiva.dao.entity;

// Generated 11/11/2015 09:33:32 PM by Hibernate Tools 4.3.1

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * ConsultaAtributoId generated by hbm2java
 */
@Embeddable
public class ConsultaAtributoId implements java.io.Serializable {

	private int codConsulta;
	private int codAtributo;

	public ConsultaAtributoId() {
	}

	public ConsultaAtributoId(int codConsulta, int codAtributo) {
		this.codConsulta = codConsulta;
		this.codAtributo = codAtributo;
	}

	@Column(name = "cod_consulta", nullable = false)
	public int getCodConsulta() {
		return this.codConsulta;
	}

	public void setCodConsulta(int codConsulta) {
		this.codConsulta = codConsulta;
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
		if (!(other instanceof ConsultaAtributoId))
			return false;
		ConsultaAtributoId castOther = (ConsultaAtributoId) other;

		return (this.getCodConsulta() == castOther.getCodConsulta())
				&& (this.getCodAtributo() == castOther.getCodAtributo());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getCodConsulta();
		result = 37 * result + this.getCodAtributo();
		return result;
	}

}
