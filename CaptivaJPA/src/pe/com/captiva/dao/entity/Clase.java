package pe.com.captiva.dao.entity;

// Generated 23/11/2015 04:50:15 PM by Hibernate Tools 4.3.1

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
 * Clase generated by hbm2java
 */
@Entity
@Table(name = "clase", schema = "soul")
public class Clase implements java.io.Serializable {

	private int codClase;
	private Proyecto proyecto;
	private Tabla tabla;
	private String nombre;
	private Integer nivel;
	private Set<Atributo> atributos = new HashSet<Atributo>(0);

	public Clase() {
	}

	public Clase(int codClase, Proyecto proyecto, String nombre) {
		this.codClase = codClase;
		this.proyecto = proyecto;
		this.nombre = nombre;
	}

	public Clase(int codClase, Proyecto proyecto, Tabla tabla, String nombre,
			Integer nivel, Set<Atributo> atributos) {
		this.codClase = codClase;
		this.proyecto = proyecto;
		this.tabla = tabla;
		this.nombre = nombre;
		this.nivel = nivel;
		this.atributos = atributos;
	}

	@Id
	@Column(name = "cod_clase", unique = true, nullable = false)
	public int getCodClase() {
		return this.codClase;
	}

	public void setCodClase(int codClase) {
		this.codClase = codClase;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cod_proyecto", nullable = false)
	public Proyecto getProyecto() {
		return this.proyecto;
	}

	public void setProyecto(Proyecto proyecto) {
		this.proyecto = proyecto;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cod_tabla")
	public Tabla getTabla() {
		return this.tabla;
	}

	public void setTabla(Tabla tabla) {
		this.tabla = tabla;
	}

	@Column(name = "nombre", nullable = false, length = 120)
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Column(name = "nivel")
	public Integer getNivel() {
		return this.nivel;
	}

	public void setNivel(Integer nivel) {
		this.nivel = nivel;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "clase")
	public Set<Atributo> getAtributos() {
		return this.atributos;
	}

	public void setAtributos(Set<Atributo> atributos) {
		this.atributos = atributos;
	}

}
