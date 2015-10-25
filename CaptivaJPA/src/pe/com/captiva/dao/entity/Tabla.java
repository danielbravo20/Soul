package pe.com.captiva.dao.entity;

// Generated 25/10/2015 06:37:17 AM by Hibernate Tools 4.3.1

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Tabla generated by hbm2java
 */
@Entity
@Table(name = "tabla", schema = "soul")
public class Tabla implements java.io.Serializable {

	private int codTabla;
	private Proyecto proyecto;
	private String esquema;
	private String nombre;
	private Integer orden;
	private Set<ConsultaTabla> consultaTablasForCodTabPadre = new HashSet<ConsultaTabla>(
			0);
	private Set<AtributoSql> atributoSqls = new HashSet<AtributoSql>(0);
	private Set<ConsultaTabla> consultaTablasForCodTabla = new HashSet<ConsultaTabla>(
			0);

	public Tabla() {
	}

	public Tabla(int codTabla, Proyecto proyecto, String esquema, String nombre) {
		this.codTabla = codTabla;
		this.proyecto = proyecto;
		this.esquema = esquema;
		this.nombre = nombre;
	}

	public Tabla(int codTabla, Proyecto proyecto, String esquema,
			String nombre, Integer orden,
			Set<ConsultaTabla> consultaTablasForCodTabPadre,
			Set<AtributoSql> atributoSqls,
			Set<ConsultaTabla> consultaTablasForCodTabla) {
		this.codTabla = codTabla;
		this.proyecto = proyecto;
		this.esquema = esquema;
		this.nombre = nombre;
		this.orden = orden;
		this.consultaTablasForCodTabPadre = consultaTablasForCodTabPadre;
		this.atributoSqls = atributoSqls;
		this.consultaTablasForCodTabla = consultaTablasForCodTabla;
	}

	@Id
	@Column(name = "cod_tabla", unique = true, nullable = false)
	public int getCodTabla() {
		return this.codTabla;
	}

	public void setCodTabla(int codTabla) {
		this.codTabla = codTabla;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cod_proyecto", nullable = false)
	public Proyecto getProyecto() {
		return this.proyecto;
	}

	public void setProyecto(Proyecto proyecto) {
		this.proyecto = proyecto;
	}

	@Column(name = "esquema", nullable = false, length = 50)
	public String getEsquema() {
		return this.esquema;
	}

	public void setEsquema(String esquema) {
		this.esquema = esquema;
	}

	@Column(name = "nombre", nullable = false, length = 50)
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Column(name = "orden")
	public Integer getOrden() {
		return this.orden;
	}

	public void setOrden(Integer orden) {
		this.orden = orden;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tablaByCodTabPadre")
	public Set<ConsultaTabla> getConsultaTablasForCodTabPadre() {
		return this.consultaTablasForCodTabPadre;
	}

	public void setConsultaTablasForCodTabPadre(
			Set<ConsultaTabla> consultaTablasForCodTabPadre) {
		this.consultaTablasForCodTabPadre = consultaTablasForCodTabPadre;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tabla")
	public Set<AtributoSql> getAtributoSqls() {
		return this.atributoSqls;
	}

	public void setAtributoSqls(Set<AtributoSql> atributoSqls) {
		this.atributoSqls = atributoSqls;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tablaByCodTabla")
	public Set<ConsultaTabla> getConsultaTablasForCodTabla() {
		return this.consultaTablasForCodTabla;
	}

	public void setConsultaTablasForCodTabla(
			Set<ConsultaTabla> consultaTablasForCodTabla) {
		this.consultaTablasForCodTabla = consultaTablasForCodTabla;
	}

}
