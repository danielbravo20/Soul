package pe.com.captiva.dao.entity;

// Generated 11/11/2015 09:33:32 PM by Hibernate Tools 4.3.1

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Catalogo generated by hbm2java
 */
@Entity
@Table(name = "catalogo", schema = "soul")
public class Catalogo implements java.io.Serializable {

	private CatalogoId id;
	private Proyecto proyecto;
	private String valor1;
	private String valor2;
	private String descripcion;
	private Integer limCodAtributo;
	private Integer limValor1;
	private Integer limValor2;
	private char cabecera;

	public Catalogo() {
	}

	public Catalogo(CatalogoId id, char cabecera) {
		this.id = id;
		this.cabecera = cabecera;
	}

	public Catalogo(CatalogoId id, Proyecto proyecto, String valor1,
			String valor2, String descripcion, Integer limCodAtributo,
			Integer limValor1, Integer limValor2, char cabecera) {
		this.id = id;
		this.proyecto = proyecto;
		this.valor1 = valor1;
		this.valor2 = valor2;
		this.descripcion = descripcion;
		this.limCodAtributo = limCodAtributo;
		this.limValor1 = limValor1;
		this.limValor2 = limValor2;
		this.cabecera = cabecera;
	}

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "codCatalogo", column = @Column(name = "cod_catalogo", nullable = false, length = 50)),
			@AttributeOverride(name = "codAtributo", column = @Column(name = "cod_atributo", nullable = false, length = 50)) })
	public CatalogoId getId() {
		return this.id;
	}

	public void setId(CatalogoId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cod_proyecto")
	public Proyecto getProyecto() {
		return this.proyecto;
	}

	public void setProyecto(Proyecto proyecto) {
		this.proyecto = proyecto;
	}

	@Column(name = "valor_1", length = 100)
	public String getValor1() {
		return this.valor1;
	}

	public void setValor1(String valor1) {
		this.valor1 = valor1;
	}

	@Column(name = "valor_2", length = 100)
	public String getValor2() {
		return this.valor2;
	}

	public void setValor2(String valor2) {
		this.valor2 = valor2;
	}

	@Column(name = "descripcion", length = 100)
	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Column(name = "lim_cod_atributo")
	public Integer getLimCodAtributo() {
		return this.limCodAtributo;
	}

	public void setLimCodAtributo(Integer limCodAtributo) {
		this.limCodAtributo = limCodAtributo;
	}

	@Column(name = "lim_valor_1")
	public Integer getLimValor1() {
		return this.limValor1;
	}

	public void setLimValor1(Integer limValor1) {
		this.limValor1 = limValor1;
	}

	@Column(name = "lim_valor_2")
	public Integer getLimValor2() {
		return this.limValor2;
	}

	public void setLimValor2(Integer limValor2) {
		this.limValor2 = limValor2;
	}

	@Column(name = "cabecera", nullable = false, length = 1)
	public char getCabecera() {
		return this.cabecera;
	}

	public void setCabecera(char cabecera) {
		this.cabecera = cabecera;
	}

}
