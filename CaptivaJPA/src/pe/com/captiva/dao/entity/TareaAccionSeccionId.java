package pe.com.captiva.dao.entity;
// Generated 01/12/2015 04:35:12 PM by Hibernate Tools 4.3.1.Final

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * TareaAccionSeccionId generated by hbm2java
 */
@Embeddable
public class TareaAccionSeccionId implements java.io.Serializable {

	private Integer codTarea;
	private String codSeccion;
	private char tipo;
	private String tipoWidget;
	private String nombre;
	private String codSeccionPadre;

	public TareaAccionSeccionId() {
	}

	public TareaAccionSeccionId(String codSeccion, char tipo) {
		this.codSeccion = codSeccion;
		this.tipo = tipo;
	}

	public TareaAccionSeccionId(Integer codTarea, String codSeccion, char tipo, String tipoWidget, String nombre,
			String codSeccionPadre) {
		this.codTarea = codTarea;
		this.codSeccion = codSeccion;
		this.tipo = tipo;
		this.tipoWidget = tipoWidget;
		this.nombre = nombre;
		this.codSeccionPadre = codSeccionPadre;
	}

	@Column(name = "cod_tarea")
	public Integer getCodTarea() {
		return this.codTarea;
	}

	public void setCodTarea(Integer codTarea) {
		this.codTarea = codTarea;
	}

	@Column(name = "cod_seccion", nullable = false, length = 20)
	public String getCodSeccion() {
		return this.codSeccion;
	}

	public void setCodSeccion(String codSeccion) {
		this.codSeccion = codSeccion;
	}

	@Column(name = "tipo", nullable = false, length = 1)
	public char getTipo() {
		return this.tipo;
	}

	public void setTipo(char tipo) {
		this.tipo = tipo;
	}

	@Column(name = "tipo_widget", length = 20)
	public String getTipoWidget() {
		return this.tipoWidget;
	}

	public void setTipoWidget(String tipoWidget) {
		this.tipoWidget = tipoWidget;
	}

	@Column(name = "nombre", length = 100)
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Column(name = "cod_seccion_padre", length = 20)
	public String getCodSeccionPadre() {
		return this.codSeccionPadre;
	}

	public void setCodSeccionPadre(String codSeccionPadre) {
		this.codSeccionPadre = codSeccionPadre;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof TareaAccionSeccionId))
			return false;
		TareaAccionSeccionId castOther = (TareaAccionSeccionId) other;

		return ((this.getCodTarea() == castOther.getCodTarea()) || (this.getCodTarea() != null
				&& castOther.getCodTarea() != null && this.getCodTarea().equals(castOther.getCodTarea())))
				&& ((this.getCodSeccion() == castOther.getCodSeccion()) || (this.getCodSeccion() != null
						&& castOther.getCodSeccion() != null && this.getCodSeccion().equals(castOther.getCodSeccion())))
				&& (this.getTipo() == castOther.getTipo())
				&& ((this.getTipoWidget() == castOther.getTipoWidget()) || (this.getTipoWidget() != null
						&& castOther.getTipoWidget() != null && this.getTipoWidget().equals(castOther.getTipoWidget())))
				&& ((this.getNombre() == castOther.getNombre()) || (this.getNombre() != null
						&& castOther.getNombre() != null && this.getNombre().equals(castOther.getNombre())))
				&& ((this.getCodSeccionPadre() == castOther.getCodSeccionPadre())
						|| (this.getCodSeccionPadre() != null && castOther.getCodSeccionPadre() != null
								&& this.getCodSeccionPadre().equals(castOther.getCodSeccionPadre())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getCodTarea() == null ? 0 : this.getCodTarea().hashCode());
		result = 37 * result + (getCodSeccion() == null ? 0 : this.getCodSeccion().hashCode());
		result = 37 * result + this.getTipo();
		result = 37 * result + (getTipoWidget() == null ? 0 : this.getTipoWidget().hashCode());
		result = 37 * result + (getNombre() == null ? 0 : this.getNombre().hashCode());
		result = 37 * result + (getCodSeccionPadre() == null ? 0 : this.getCodSeccionPadre().hashCode());
		return result;
	}

}
