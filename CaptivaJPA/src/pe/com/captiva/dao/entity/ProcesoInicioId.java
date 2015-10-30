package pe.com.captiva.dao.entity;

// Generated 30/10/2015 11:10:41 AM by Hibernate Tools 4.3.1

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * ProcesoInicioId generated by hbm2java
 */
@Embeddable
public class ProcesoInicioId implements java.io.Serializable {

	private int codProceso;
	private int codAtributo;

	public ProcesoInicioId() {
	}

	public ProcesoInicioId(int codProceso, int codAtributo) {
		this.codProceso = codProceso;
		this.codAtributo = codAtributo;
	}

	@Column(name = "cod_proceso", nullable = false)
	public int getCodProceso() {
		return this.codProceso;
	}

	public void setCodProceso(int codProceso) {
		this.codProceso = codProceso;
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
		if (!(other instanceof ProcesoInicioId))
			return false;
		ProcesoInicioId castOther = (ProcesoInicioId) other;

		return (this.getCodProceso() == castOther.getCodProceso())
				&& (this.getCodAtributo() == castOther.getCodAtributo());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getCodProceso();
		result = 37 * result + this.getCodAtributo();
		return result;
	}

}
