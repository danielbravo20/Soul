package pe.com.captiva.dao.entity;

// Generated 21/10/2015 10:31:13 PM by Hibernate Tools 4.3.1

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * CatalogoId generated by hbm2java
 */
@Embeddable
public class CatalogoId implements java.io.Serializable {

	private String codCatalogo;
	private String codAtributo;

	public CatalogoId() {
	}

	public CatalogoId(String codCatalogo, String codAtributo) {
		this.codCatalogo = codCatalogo;
		this.codAtributo = codAtributo;
	}

	@Column(name = "cod_catalogo", nullable = false, length = 50)
	public String getCodCatalogo() {
		return this.codCatalogo;
	}

	public void setCodCatalogo(String codCatalogo) {
		this.codCatalogo = codCatalogo;
	}

	@Column(name = "cod_atributo", nullable = false, length = 50)
	public String getCodAtributo() {
		return this.codAtributo;
	}

	public void setCodAtributo(String codAtributo) {
		this.codAtributo = codAtributo;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof CatalogoId))
			return false;
		CatalogoId castOther = (CatalogoId) other;

		return ((this.getCodCatalogo() == castOther.getCodCatalogo()) || (this
				.getCodCatalogo() != null && castOther.getCodCatalogo() != null && this
				.getCodCatalogo().equals(castOther.getCodCatalogo())))
				&& ((this.getCodAtributo() == castOther.getCodAtributo()) || (this
						.getCodAtributo() != null
						&& castOther.getCodAtributo() != null && this
						.getCodAtributo().equals(castOther.getCodAtributo())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getCodCatalogo() == null ? 0 : this.getCodCatalogo()
						.hashCode());
		result = 37
				* result
				+ (getCodAtributo() == null ? 0 : this.getCodAtributo()
						.hashCode());
		return result;
	}

}