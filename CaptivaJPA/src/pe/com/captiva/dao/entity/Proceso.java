package pe.com.captiva.dao.entity;

// Generated 19/11/2015 03:54:12 PM by Hibernate Tools 4.3.1

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
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Proceso generated by hbm2java
 */
@Entity
@Table(name = "proceso", schema = "soul")
public class Proceso implements java.io.Serializable {

	private int codProceso;
	private Consulta consultaByCodConResumen;
	private Consulta consultaByCodConDetalle;
	private Proyecto proyecto;
	private Tarea tarea;
	private String nombre;
	private String javClase;
	private String javDatasource;
	private String webDetalleTipovista;
	private Set<ProcesoInicio> procesoInicios = new HashSet<ProcesoInicio>(0);
	private Set<Rol> rols = new HashSet<Rol>(0);
	private Set<Tarea> tareas = new HashSet<Tarea>(0);

	public Proceso() {
	}

	public Proceso(int codProceso, Consulta consultaByCodConResumen,
			Consulta consultaByCodConDetalle, Proyecto proyecto, String nombre,
			String javClase, String javDatasource) {
		this.codProceso = codProceso;
		this.consultaByCodConResumen = consultaByCodConResumen;
		this.consultaByCodConDetalle = consultaByCodConDetalle;
		this.proyecto = proyecto;
		this.nombre = nombre;
		this.javClase = javClase;
		this.javDatasource = javDatasource;
	}

	public Proceso(int codProceso, Consulta consultaByCodConResumen,
			Consulta consultaByCodConDetalle, Proyecto proyecto, Tarea tarea,
			String nombre, String javClase, String javDatasource,
			String webDetalleTipovista, Set<ProcesoInicio> procesoInicios,
			Set<Rol> rols, Set<Tarea> tareas) {
		this.codProceso = codProceso;
		this.consultaByCodConResumen = consultaByCodConResumen;
		this.consultaByCodConDetalle = consultaByCodConDetalle;
		this.proyecto = proyecto;
		this.tarea = tarea;
		this.nombre = nombre;
		this.javClase = javClase;
		this.javDatasource = javDatasource;
		this.webDetalleTipovista = webDetalleTipovista;
		this.procesoInicios = procesoInicios;
		this.rols = rols;
		this.tareas = tareas;
	}

	@Id
	@Column(name = "cod_proceso", unique = true, nullable = false)
	public int getCodProceso() {
		return this.codProceso;
	}

	public void setCodProceso(int codProceso) {
		this.codProceso = codProceso;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cod_con_resumen", nullable = false)
	public Consulta getConsultaByCodConResumen() {
		return this.consultaByCodConResumen;
	}

	public void setConsultaByCodConResumen(Consulta consultaByCodConResumen) {
		this.consultaByCodConResumen = consultaByCodConResumen;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cod_con_detalle", nullable = false)
	public Consulta getConsultaByCodConDetalle() {
		return this.consultaByCodConDetalle;
	}

	public void setConsultaByCodConDetalle(Consulta consultaByCodConDetalle) {
		this.consultaByCodConDetalle = consultaByCodConDetalle;
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
	@JoinColumn(name = "cod_tarea")
	public Tarea getTarea() {
		return this.tarea;
	}

	public void setTarea(Tarea tarea) {
		this.tarea = tarea;
	}

	@Column(name = "nombre", nullable = false, length = 120)
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Column(name = "jav_clase", nullable = false, length = 120)
	public String getJavClase() {
		return this.javClase;
	}

	public void setJavClase(String javClase) {
		this.javClase = javClase;
	}

	@Column(name = "jav_datasource", nullable = false, length = 120)
	public String getJavDatasource() {
		return this.javDatasource;
	}

	public void setJavDatasource(String javDatasource) {
		this.javDatasource = javDatasource;
	}

	@Column(name = "web_detalle_tipovista", length = 1)
	public String getWebDetalleTipovista() {
		return this.webDetalleTipovista;
	}

	public void setWebDetalleTipovista(String webDetalleTipovista) {
		this.webDetalleTipovista = webDetalleTipovista;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "proceso")
	public Set<ProcesoInicio> getProcesoInicios() {
		return this.procesoInicios;
	}

	public void setProcesoInicios(Set<ProcesoInicio> procesoInicios) {
		this.procesoInicios = procesoInicios;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "proceso_rol_potencial", schema = "soul", joinColumns = { @JoinColumn(name = "cod_proceso", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "cod_rol", nullable = false, updatable = false) })
	public Set<Rol> getRols() {
		return this.rols;
	}

	public void setRols(Set<Rol> rols) {
		this.rols = rols;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "proceso")
	public Set<Tarea> getTareas() {
		return this.tareas;
	}

	public void setTareas(Set<Tarea> tareas) {
		this.tareas = tareas;
	}

}
