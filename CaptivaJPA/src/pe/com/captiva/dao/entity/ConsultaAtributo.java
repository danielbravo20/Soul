package pe.com.captiva.dao.entity;

// Generated 25/10/2015 10:35:30 PM by Hibernate Tools 4.3.1

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
 * ConsultaAtributo generated by hbm2java
 */
@Entity
@Table(name = "consulta_atributo", schema = "soul")
public class ConsultaAtributo implements java.io.Serializable {

	private ConsultaAtributoId id;
	private Atributo atributo;
	private Consulta consulta;
	private char flgCondicion;
	private char flgVisible;
	private int codTabla;

	public ConsultaAtributo() {
	}

	public ConsultaAtributo(ConsultaAtributoId id, Atributo atributo,
			Consulta consulta, char flgCondicion, char flgVisible, int codTabla) {
		this.id = id;
		this.atributo = atributo;
		this.consulta = consulta;
		this.flgCondicion = flgCondicion;
		this.flgVisible = flgVisible;
		this.codTabla = codTabla;
	}

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "codConsulta", column = @Column(name = "cod_consulta", nullable = false)),
			@AttributeOverride(name = "codAtributo", column = @Column(name = "cod_atributo", nullable = false)) })
	public ConsultaAtributoId getId() {
		return this.id;
	}

	public void setId(ConsultaAtributoId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cod_atributo", nullable = false, insertable = false, updatable = false)
	public Atributo getAtributo() {
		return this.atributo;
	}

	public void setAtributo(Atributo atributo) {
		this.atributo = atributo;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cod_consulta", nullable = false, insertable = false, updatable = false)
	public Consulta getConsulta() {
		return this.consulta;
	}

	public void setConsulta(Consulta consulta) {
		this.consulta = consulta;
	}

	@Column(name = "flg_condicion", nullable = false, length = 1)
	public char getFlgCondicion() {
		return this.flgCondicion;
	}

	public void setFlgCondicion(char flgCondicion) {
		this.flgCondicion = flgCondicion;
	}

	@Column(name = "flg_visible", nullable = false, length = 1)
	public char getFlgVisible() {
		return this.flgVisible;
	}

	public void setFlgVisible(char flgVisible) {
		this.flgVisible = flgVisible;
	}

	@Column(name = "cod_tabla", nullable = false)
	public int getCodTabla() {
		return this.codTabla;
	}

	public void setCodTabla(int codTabla) {
		this.codTabla = codTabla;
	}

}
