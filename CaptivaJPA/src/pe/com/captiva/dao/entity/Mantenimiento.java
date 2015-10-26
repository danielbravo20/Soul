package pe.com.captiva.dao.entity;

// Generated 25/10/2015 10:35:30 PM by Hibernate Tools 4.3.1

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Mantenimiento generated by hbm2java
 */
@Entity
@Table(name = "mantenimiento", schema = "soul")
public class Mantenimiento implements java.io.Serializable {

	private String codMantenimiento;
	private Proyecto proyecto;
	private String nombre;
	private String descripcion;
	private String codEsquema;
	private String codDatasource;
	private Set<MantenimientoAtributo> mantenimientoAtributos = new HashSet<MantenimientoAtributo>(
			0);
	private Set<Rol> rols = new HashSet<Rol>(0);

	public Mantenimiento() {
	}

	public Mantenimiento(String codMantenimiento, String nombre,
			String codEsquema, String codDatasource) {
		this.codMantenimiento = codMantenimiento;
		this.nombre = nombre;
		this.codEsquema = codEsquema;
		this.codDatasource = codDatasource;
	}

	public Mantenimiento(String codMantenimiento, Proyecto proyecto,
			String nombre, String descripcion, String codEsquema,
			String codDatasource,
			Set<MantenimientoAtributo> mantenimientoAtributos, Set<Rol> rols) {
		this.codMantenimiento = codMantenimiento;
		this.proyecto = proyecto;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.codEsquema = codEsquema;
		this.codDatasource = codDatasource;
		this.mantenimientoAtributos = mantenimientoAtributos;
		this.rols = rols;
	}

	@Id
	@Column(name = "cod_mantenimiento", unique = true, nullable = false, length = 80)
	public String getCodMantenimiento() {
		return this.codMantenimiento;
	}

	public void setCodMantenimiento(String codMantenimiento) {
		this.codMantenimiento = codMantenimiento;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cod_proyecto")
	public Proyecto getProyecto() {
		return this.proyecto;
	}

	public void setProyecto(Proyecto proyecto) {
		this.proyecto = proyecto;
	}

	@Column(name = "nombre", nullable = false, length = 100)
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Column(name = "descripcion")
	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Column(name = "cod_esquema", nullable = false, length = 100)
	public String getCodEsquema() {
		return this.codEsquema;
	}

	public void setCodEsquema(String codEsquema) {
		this.codEsquema = codEsquema;
	}

	@Column(name = "cod_datasource", nullable = false, length = 100)
	public String getCodDatasource() {
		return this.codDatasource;
	}

	public void setCodDatasource(String codDatasource) {
		this.codDatasource = codDatasource;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "mantenimiento")
	public Set<MantenimientoAtributo> getMantenimientoAtributos() {
		return this.mantenimientoAtributos;
	}

	public void setMantenimientoAtributos(
			Set<MantenimientoAtributo> mantenimientoAtributos) {
		this.mantenimientoAtributos = mantenimientoAtributos;
	}

	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "mantenimientos")
	public Set<Rol> getRols() {
		return this.rols;
	}

	public void setRols(Set<Rol> rols) {
		this.rols = rols;
	}

}
