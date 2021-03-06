package pe.com.captiva.dao.entity;
// Generated 01/12/2015 04:35:12 PM by Hibernate Tools 4.3.1.Final

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * TareaAccionId generated by hbm2java
 */
@Embeddable
public class TareaAccionId implements java.io.Serializable {

	private Integer codTarea;
	private String codSeccion;
	private Integer codSubSeccion;
	private int codTareaAccion;
	private char codTipoAccion;
	private int codAtributo;
	private String webEtiqueta;
	private String webTipo;
	private Character webTipoCampo;
	private Character webTipoLista;
	private String webCatalogo;
	private char webRequerido;
	private String webMensajeValidacion;
	private String valOmision;

	public TareaAccionId() {
	}

	public TareaAccionId(String codSeccion, int codTareaAccion, char codTipoAccion, int codAtributo,
			char webRequerido) {
		this.codSeccion = codSeccion;
		this.codTareaAccion = codTareaAccion;
		this.codTipoAccion = codTipoAccion;
		this.codAtributo = codAtributo;
		this.webRequerido = webRequerido;
	}

	public TareaAccionId(Integer codTarea, String codSeccion, Integer codSubSeccion, int codTareaAccion,
			char codTipoAccion, int codAtributo, String webEtiqueta, String webTipo, Character webTipoCampo,
			Character webTipoLista, String webCatalogo, char webRequerido, String webMensajeValidacion,
			String valOmision) {
		this.codTarea = codTarea;
		this.codSeccion = codSeccion;
		this.codSubSeccion = codSubSeccion;
		this.codTareaAccion = codTareaAccion;
		this.codTipoAccion = codTipoAccion;
		this.codAtributo = codAtributo;
		this.webEtiqueta = webEtiqueta;
		this.webTipo = webTipo;
		this.webTipoCampo = webTipoCampo;
		this.webTipoLista = webTipoLista;
		this.webCatalogo = webCatalogo;
		this.webRequerido = webRequerido;
		this.webMensajeValidacion = webMensajeValidacion;
		this.valOmision = valOmision;
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

	@Column(name = "cod_sub_seccion")
	public Integer getCodSubSeccion() {
		return this.codSubSeccion;
	}

	public void setCodSubSeccion(Integer codSubSeccion) {
		this.codSubSeccion = codSubSeccion;
	}

	@Column(name = "cod_tarea_accion", nullable = false)
	public int getCodTareaAccion() {
		return this.codTareaAccion;
	}

	public void setCodTareaAccion(int codTareaAccion) {
		this.codTareaAccion = codTareaAccion;
	}

	@Column(name = "cod_tipo_accion", nullable = false, length = 1)
	public char getCodTipoAccion() {
		return this.codTipoAccion;
	}

	public void setCodTipoAccion(char codTipoAccion) {
		this.codTipoAccion = codTipoAccion;
	}

	@Column(name = "cod_atributo", nullable = false)
	public int getCodAtributo() {
		return this.codAtributo;
	}

	public void setCodAtributo(int codAtributo) {
		this.codAtributo = codAtributo;
	}

	@Column(name = "web_etiqueta")
	public String getWebEtiqueta() {
		return this.webEtiqueta;
	}

	public void setWebEtiqueta(String webEtiqueta) {
		this.webEtiqueta = webEtiqueta;
	}

	@Column(name = "web_tipo", length = 60)
	public String getWebTipo() {
		return this.webTipo;
	}

	public void setWebTipo(String webTipo) {
		this.webTipo = webTipo;
	}

	@Column(name = "web_tipo_campo", length = 1)
	public Character getWebTipoCampo() {
		return this.webTipoCampo;
	}

	public void setWebTipoCampo(Character webTipoCampo) {
		this.webTipoCampo = webTipoCampo;
	}

	@Column(name = "web_tipo_lista", length = 1)
	public Character getWebTipoLista() {
		return this.webTipoLista;
	}

	public void setWebTipoLista(Character webTipoLista) {
		this.webTipoLista = webTipoLista;
	}

	@Column(name = "web_catalogo", length = 60)
	public String getWebCatalogo() {
		return this.webCatalogo;
	}

	public void setWebCatalogo(String webCatalogo) {
		this.webCatalogo = webCatalogo;
	}

	@Column(name = "web_requerido", nullable = false, length = 1)
	public char getWebRequerido() {
		return this.webRequerido;
	}

	public void setWebRequerido(char webRequerido) {
		this.webRequerido = webRequerido;
	}

	@Column(name = "web_mensaje_validacion")
	public String getWebMensajeValidacion() {
		return this.webMensajeValidacion;
	}

	public void setWebMensajeValidacion(String webMensajeValidacion) {
		this.webMensajeValidacion = webMensajeValidacion;
	}

	@Column(name = "val_omision")
	public String getValOmision() {
		return this.valOmision;
	}

	public void setValOmision(String valOmision) {
		this.valOmision = valOmision;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof TareaAccionId))
			return false;
		TareaAccionId castOther = (TareaAccionId) other;

		return ((this.getCodTarea() == castOther.getCodTarea()) || (this.getCodTarea() != null
				&& castOther.getCodTarea() != null && this.getCodTarea().equals(castOther.getCodTarea())))
				&& ((this.getCodSeccion() == castOther.getCodSeccion()) || (this.getCodSeccion() != null
						&& castOther.getCodSeccion() != null && this.getCodSeccion().equals(castOther.getCodSeccion())))
				&& ((this.getCodSubSeccion() == castOther.getCodSubSeccion())
						|| (this.getCodSubSeccion() != null && castOther.getCodSubSeccion() != null
								&& this.getCodSubSeccion().equals(castOther.getCodSubSeccion())))
				&& (this.getCodTareaAccion() == castOther.getCodTareaAccion())
				&& (this.getCodTipoAccion() == castOther.getCodTipoAccion())
				&& (this.getCodAtributo() == castOther.getCodAtributo())
				&& ((this.getWebEtiqueta() == castOther.getWebEtiqueta())
						|| (this.getWebEtiqueta() != null && castOther.getWebEtiqueta() != null
								&& this.getWebEtiqueta().equals(castOther.getWebEtiqueta())))
				&& ((this.getWebTipo() == castOther.getWebTipo()) || (this.getWebTipo() != null
						&& castOther.getWebTipo() != null && this.getWebTipo().equals(castOther.getWebTipo())))
				&& ((this.getWebTipoCampo() == castOther.getWebTipoCampo())
						|| (this.getWebTipoCampo() != null && castOther.getWebTipoCampo() != null
								&& this.getWebTipoCampo().equals(castOther.getWebTipoCampo())))
				&& ((this.getWebTipoLista() == castOther.getWebTipoLista())
						|| (this.getWebTipoLista() != null && castOther.getWebTipoLista() != null
								&& this.getWebTipoLista().equals(castOther.getWebTipoLista())))
				&& ((this.getWebCatalogo() == castOther.getWebCatalogo())
						|| (this.getWebCatalogo() != null && castOther.getWebCatalogo() != null
								&& this.getWebCatalogo().equals(castOther.getWebCatalogo())))
				&& (this.getWebRequerido() == castOther.getWebRequerido())
				&& ((this.getWebMensajeValidacion() == castOther.getWebMensajeValidacion())
						|| (this.getWebMensajeValidacion() != null && castOther.getWebMensajeValidacion() != null
								&& this.getWebMensajeValidacion().equals(castOther.getWebMensajeValidacion())))
				&& ((this.getValOmision() == castOther.getValOmision())
						|| (this.getValOmision() != null && castOther.getValOmision() != null
								&& this.getValOmision().equals(castOther.getValOmision())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getCodTarea() == null ? 0 : this.getCodTarea().hashCode());
		result = 37 * result + (getCodSeccion() == null ? 0 : this.getCodSeccion().hashCode());
		result = 37 * result + (getCodSubSeccion() == null ? 0 : this.getCodSubSeccion().hashCode());
		result = 37 * result + this.getCodTareaAccion();
		result = 37 * result + this.getCodTipoAccion();
		result = 37 * result + this.getCodAtributo();
		result = 37 * result + (getWebEtiqueta() == null ? 0 : this.getWebEtiqueta().hashCode());
		result = 37 * result + (getWebTipo() == null ? 0 : this.getWebTipo().hashCode());
		result = 37 * result + (getWebTipoCampo() == null ? 0 : this.getWebTipoCampo().hashCode());
		result = 37 * result + (getWebTipoLista() == null ? 0 : this.getWebTipoLista().hashCode());
		result = 37 * result + (getWebCatalogo() == null ? 0 : this.getWebCatalogo().hashCode());
		result = 37 * result + this.getWebRequerido();
		result = 37 * result + (getWebMensajeValidacion() == null ? 0 : this.getWebMensajeValidacion().hashCode());
		result = 37 * result + (getValOmision() == null ? 0 : this.getValOmision().hashCode());
		return result;
	}

}
