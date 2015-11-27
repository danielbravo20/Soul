package pe.com.captiva.dao.entity;
// Generated 26/11/2015 04:49:30 PM by Hibernate Tools 4.3.1.Final

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * TareaResumenPlantillaAtributo generated by hbm2java
 */
@Entity
@Table(name = "tarea_resumen_plantilla_atributo", schema = "soul")
public class TareaResumenPlantillaAtributo implements java.io.Serializable {

	private TareaResumenPlantillaAtributoId id;
	private int codAtributo;
	private String webEtiqueta;

	public TareaResumenPlantillaAtributo() {
	}

	public TareaResumenPlantillaAtributo(TareaResumenPlantillaAtributoId id, int codAtributo, String webEtiqueta) {
		this.id = id;
		this.codAtributo = codAtributo;
		this.webEtiqueta = webEtiqueta;
	}

	@EmbeddedId

	@AttributeOverrides({
			@AttributeOverride(name = "codPlantilla", column = @Column(name = "cod_plantilla", nullable = false, length = 20) ),
			@AttributeOverride(name = "codSubSeccion", column = @Column(name = "cod_sub_seccion", nullable = false) ),
			@AttributeOverride(name = "codTareaResumenPlantillaAtributo", column = @Column(name = "cod_tarea_resumen_plantilla_atributo", nullable = false) ) })
	public TareaResumenPlantillaAtributoId getId() {
		return this.id;
	}

	public void setId(TareaResumenPlantillaAtributoId id) {
		this.id = id;
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

}
