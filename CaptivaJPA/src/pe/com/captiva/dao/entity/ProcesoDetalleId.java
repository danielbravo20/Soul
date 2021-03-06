package pe.com.captiva.dao.entity;
// Generated 01/12/2015 04:35:12 PM by Hibernate Tools 4.3.1.Final

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * ProcesoDetalleId generated by hbm2java
 */
@Embeddable
public class ProcesoDetalleId implements java.io.Serializable {

	private Integer codProceso;
	private String codSeccion;
	private int codSubSeccion;
	private int codProcesoDetalle;
	private int codAtributo;
	private String webEtiqueta;

	public ProcesoDetalleId() {
	}

	public ProcesoDetalleId(String codSeccion, int codSubSeccion, int codProcesoDetalle, int codAtributo,
			String webEtiqueta) {
		this.codSeccion = codSeccion;
		this.codSubSeccion = codSubSeccion;
		this.codProcesoDetalle = codProcesoDetalle;
		this.codAtributo = codAtributo;
		this.webEtiqueta = webEtiqueta;
	}

	public ProcesoDetalleId(Integer codProceso, String codSeccion, int codSubSeccion, int codProcesoDetalle,
			int codAtributo, String webEtiqueta) {
		this.codProceso = codProceso;
		this.codSeccion = codSeccion;
		this.codSubSeccion = codSubSeccion;
		this.codProcesoDetalle = codProcesoDetalle;
		this.codAtributo = codAtributo;
		this.webEtiqueta = webEtiqueta;
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

	@Column(name = "cod_proceso_detalle", nullable = false)
	public int getCodProcesoDetalle() {
		return this.codProcesoDetalle;
	}

	public void setCodProcesoDetalle(int codProcesoDetalle) {
		this.codProcesoDetalle = codProcesoDetalle;
	}

	@Column(name = "cod_atributo", nullable = false)
	public int getCodAtributo() {
		return this.codAtributo;
	}

	public void setCodAtributo(int codAtributo) {
		this.codAtributo = codAtributo;
	}

	@Column(name = "web_etiqueta", nullable = false)
	public String getWebEtiqueta() {
		return this.webEtiqueta;
	}

	public void setWebEtiqueta(String webEtiqueta) {
		this.webEtiqueta = webEtiqueta;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof ProcesoDetalleId))
			return false;
		ProcesoDetalleId castOther = (ProcesoDetalleId) other;

		return ((this.getCodProceso() == castOther.getCodProceso()) || (this.getCodProceso() != null
				&& castOther.getCodProceso() != null && this.getCodProceso().equals(castOther.getCodProceso())))
				&& ((this.getCodSeccion() == castOther.getCodSeccion()) || (this.getCodSeccion() != null
						&& castOther.getCodSeccion() != null && this.getCodSeccion().equals(castOther.getCodSeccion())))
				&& (this.getCodSubSeccion() == castOther.getCodSubSeccion())
				&& (this.getCodProcesoDetalle() == castOther.getCodProcesoDetalle())
				&& (this.getCodAtributo() == castOther.getCodAtributo())
				&& ((this.getWebEtiqueta() == castOther.getWebEtiqueta())
						|| (this.getWebEtiqueta() != null && castOther.getWebEtiqueta() != null
								&& this.getWebEtiqueta().equals(castOther.getWebEtiqueta())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getCodProceso() == null ? 0 : this.getCodProceso().hashCode());
		result = 37 * result + (getCodSeccion() == null ? 0 : this.getCodSeccion().hashCode());
		result = 37 * result + this.getCodSubSeccion();
		result = 37 * result + this.getCodProcesoDetalle();
		result = 37 * result + this.getCodAtributo();
		result = 37 * result + (getWebEtiqueta() == null ? 0 : this.getWebEtiqueta().hashCode());
		return result;
	}

}
