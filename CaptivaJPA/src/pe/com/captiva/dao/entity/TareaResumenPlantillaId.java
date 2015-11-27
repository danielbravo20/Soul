package pe.com.captiva.dao.entity;
// Generated 26/11/2015 04:49:30 PM by Hibernate Tools 4.3.1.Final

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * TareaResumenPlantillaId generated by hbm2java
 */
@Embeddable
public class TareaResumenPlantillaId implements java.io.Serializable {

	private String codPlantilla;
	private String nombre;

	public TareaResumenPlantillaId() {
	}

	public TareaResumenPlantillaId(String codPlantilla, String nombre) {
		this.codPlantilla = codPlantilla;
		this.nombre = nombre;
	}

	@Column(name = "cod_plantilla", length = 20)
	public String getCodPlantilla() {
		return this.codPlantilla;
	}

	public void setCodPlantilla(String codPlantilla) {
		this.codPlantilla = codPlantilla;
	}

	@Column(name = "nombre", length = 100)
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof TareaResumenPlantillaId))
			return false;
		TareaResumenPlantillaId castOther = (TareaResumenPlantillaId) other;

		return ((this.getCodPlantilla() == castOther.getCodPlantilla()) || (this.getCodPlantilla() != null
				&& castOther.getCodPlantilla() != null && this.getCodPlantilla().equals(castOther.getCodPlantilla())))
				&& ((this.getNombre() == castOther.getNombre()) || (this.getNombre() != null
						&& castOther.getNombre() != null && this.getNombre().equals(castOther.getNombre())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getCodPlantilla() == null ? 0 : this.getCodPlantilla().hashCode());
		result = 37 * result + (getNombre() == null ? 0 : this.getNombre().hashCode());
		return result;
	}

}
