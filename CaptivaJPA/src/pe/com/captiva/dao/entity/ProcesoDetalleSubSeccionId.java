package pe.com.captiva.dao.entity;

// Generated 19/11/2015 03:54:12 PM by Hibernate Tools 4.3.1

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * ProcesoDetalleSubSeccionId generated by hbm2java
 */
@Embeddable
public class ProcesoDetalleSubSeccionId implements java.io.Serializable {

	private Integer codProceso;
	private String codSeccion;
	private int codSubSeccion;
	private String nombre;

	public ProcesoDetalleSubSeccionId() {
	}

	public ProcesoDetalleSubSeccionId(String codSeccion, int codSubSeccion,
			String nombre) {
		this.codSeccion = codSeccion;
		this.codSubSeccion = codSubSeccion;
		this.nombre = nombre;
	}

	public ProcesoDetalleSubSeccionId(Integer codProceso, String codSeccion,
			int codSubSeccion, String nombre) {
		this.codProceso = codProceso;
		this.codSeccion = codSeccion;
		this.codSubSeccion = codSubSeccion;
		this.nombre = nombre;
	}

	@Column(name = "cod_proceso")
	public Integer getCodProceso() {
		return this.codProceso;
	}

	public void setCodProceso(Integer codProceso) {
		this.codProceso = codProceso;
	}

	@Column(name = "cod_seccion", nullable = false, length = 20)
	public String getCodSeccion() {
		return this.codSeccion;
	}

	public void setCodSeccion(String codSeccion) {
		this.codSeccion = codSeccion;
	}

	@Column(name = "cod_sub_seccion", nullable = false)
	public int getCodSubSeccion() {
		return this.codSubSeccion;
	}

	public void setCodSubSeccion(int codSubSeccion) {
		this.codSubSeccion = codSubSeccion;
	}

	@Column(name = "nombre", nullable = false, length = 100)
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
		if (!(other instanceof ProcesoDetalleSubSeccionId))
			return false;
		ProcesoDetalleSubSeccionId castOther = (ProcesoDetalleSubSeccionId) other;

		return ((this.getCodProceso() == castOther.getCodProceso()) || (this
				.getCodProceso() != null && castOther.getCodProceso() != null && this
				.getCodProceso().equals(castOther.getCodProceso())))
				&& ((this.getCodSeccion() == castOther.getCodSeccion()) || (this
						.getCodSeccion() != null
						&& castOther.getCodSeccion() != null && this
						.getCodSeccion().equals(castOther.getCodSeccion())))
				&& (this.getCodSubSeccion() == castOther.getCodSubSeccion())
				&& ((this.getNombre() == castOther.getNombre()) || (this
						.getNombre() != null && castOther.getNombre() != null && this
						.getNombre().equals(castOther.getNombre())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getCodProceso() == null ? 0 : this.getCodProceso()
						.hashCode());
		result = 37
				* result
				+ (getCodSeccion() == null ? 0 : this.getCodSeccion()
						.hashCode());
		result = 37 * result + this.getCodSubSeccion();
		result = 37 * result
				+ (getNombre() == null ? 0 : this.getNombre().hashCode());
		return result;
	}

}
