package pe.com.captiva.dao.entity;

// Generated 30/10/2015 11:10:41 AM by Hibernate Tools 4.3.1

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Rol generated by hbm2java
 */
@Entity
@Table(name = "rol", schema = "soul")
public class Rol implements java.io.Serializable {

	private String codRol;
	private Proyecto proyecto;
	private Character estado;
	private String descripcion;
	private Set<Mantenimiento> mantenimientos = new HashSet<Mantenimiento>(0);
	private Set<Tarea> tareas = new HashSet<Tarea>(0);
	private Set<Tarea> tareas_1 = new HashSet<Tarea>(0);
	private Set<Proceso> procesos = new HashSet<Proceso>(0);

	public Rol() {
	}

	public Rol(String codRol, String descripcion) {
		this.codRol = codRol;
		this.descripcion = descripcion;
	}

	public Rol(String codRol, Proyecto proyecto, Character estado,
			String descripcion, Set<Mantenimiento> mantenimientos,
			Set<Tarea> tareas, Set<Tarea> tareas_1, Set<Proceso> procesos) {
		this.codRol = codRol;
		this.proyecto = proyecto;
		this.estado = estado;
		this.descripcion = descripcion;
		this.mantenimientos = mantenimientos;
		this.tareas = tareas;
		this.tareas_1 = tareas_1;
		this.procesos = procesos;
	}

	@Id
	@Column(name = "cod_rol", unique = true, nullable = false, length = 120)
	public String getCodRol() {
		return this.codRol;
	}

	public void setCodRol(String codRol) {
		this.codRol = codRol;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cod_proyecto")
	public Proyecto getProyecto() {
		return this.proyecto;
	}

	public void setProyecto(Proyecto proyecto) {
		this.proyecto = proyecto;
	}

	@Column(name = "estado", length = 1)
	public Character getEstado() {
		return this.estado;
	}

	public void setEstado(Character estado) {
		this.estado = estado;
	}

	@Column(name = "descripcion", nullable = false)
	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "mantenimiento_rol", schema = "soul", joinColumns = { @JoinColumn(name = "cod_rol", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "cod_mantenimiento", nullable = false, updatable = false) })
	public Set<Mantenimiento> getMantenimientos() {
		return this.mantenimientos;
	}

	public void setMantenimientos(Set<Mantenimiento> mantenimientos) {
		this.mantenimientos = mantenimientos;
	}

	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "rols")
	public Set<Tarea> getTareas() {
		return this.tareas;
	}

	public void setTareas(Set<Tarea> tareas) {
		this.tareas = tareas;
	}

	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "rols")
	public Set<Tarea> getTareas_1() {
		return this.tareas_1;
	}

	public void setTareas_1(Set<Tarea> tareas_1) {
		this.tareas_1 = tareas_1;
	}

	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "rols")
	public Set<Proceso> getProcesos() {
		return this.procesos;
	}

	public void setProcesos(Set<Proceso> procesos) {
		this.procesos = procesos;
	}

}
