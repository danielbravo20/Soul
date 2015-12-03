package pe.com.captiva.dao.entity;
// Generated 01/12/2015 04:35:12 PM by Hibernate Tools 4.3.1.Final

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * TareaResumenPlantillaSubSeccionId generated by hbm2java
 */
@Embeddable
public class TareaResumenPlantillaSubSeccionId implements java.io.Serializable {

	private String codPlantilla;
	private int codSubSeccion;

	public TareaResumenPlantillaSubSeccionId() {
	}

	public TareaResumenPlantillaSubSeccionId(String codPlantilla, int codSubSeccion) {
		this.codPlantilla = codPlantilla;
		this.codSubSeccion = codSubSeccion;
	}

	@Column(name = "cod_plantilla", nullable = false, length = 20)
	public String getCodPlantilla() {
		return this.codPlantilla;
	}

	public void setCodPlantilla(String codPlantilla) {
		this.codPlantilla = codPlantilla;
	}

	@Column(name = "cod_sub_seccion", nullable = false)
	public int getCodSubSeccion() {
		return this.codSubSeccion;
	}

	public void setCodSubSeccion(int codSubSeccion) {
		this.codSubSeccion = codSubSeccion;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof TareaResumenPlantillaSubSeccionId))
			return false;
		TareaResumenPlantillaSubSeccionId castOther = (TareaResumenPlantillaSubSeccionId) other;

		return ((this.getCodPlantilla() == castOther.getCodPlantilla()) || (this.getCodPlantilla() != null
				&& castOther.getCodPlantilla() != null && this.getCodPlantilla().equals(castOther.getCodPlantilla())))
				&& (this.getCodSubSeccion() == castOther.getCodSubSeccion());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getCodPlantilla() == null ? 0 : this.getCodPlantilla().hashCode());
		result = 37 * result + this.getCodSubSeccion();
		return result;
	}

}
