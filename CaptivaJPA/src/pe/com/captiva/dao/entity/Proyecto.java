package pe.com.captiva.dao.entity;

// Generated 21/10/2015 10:31:13 PM by Hibernate Tools 4.3.1

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Proyecto generated by hbm2java
 */
@Entity
@Table(name = "proyecto", schema = "soul")
public class Proyecto implements java.io.Serializable {

	private int codProyecto;
	private String nombre;
	private String javProLibreria;
	private String javProEjb;
	private String javProCliEjb;
	private String javProWeb;
	private String javPaquete;
	private String javPaqControlador;
	private String javPreControlador;
	private String javProEjbExt;
	private Set<Catalogo> catalogos = new HashSet<Catalogo>(0);
	private Set<Mantenimiento> mantenimientos = new HashSet<Mantenimiento>(0);
	private Set<Proceso> procesos = new HashSet<Proceso>(0);
	private Set<Configuracion> configuracions = new HashSet<Configuracion>(0);
	private Set<Esquema> esquemas = new HashSet<Esquema>(0);
	private Set<Datasource> datasources = new HashSet<Datasource>(0);
	private Set<Rol> rols = new HashSet<Rol>(0);
	private Set<Consulta> consultas = new HashSet<Consulta>(0);
	private Set<Tabla> tablas = new HashSet<Tabla>(0);
	private Set<Clase> clases = new HashSet<Clase>(0);

	public Proyecto() {
	}

	public Proyecto(int codProyecto, String nombre, String javProLibreria,
			String javProEjb, String javProCliEjb, String javProWeb,
			String javPaquete, String javPaqControlador,
			String javPreControlador) {
		this.codProyecto = codProyecto;
		this.nombre = nombre;
		this.javProLibreria = javProLibreria;
		this.javProEjb = javProEjb;
		this.javProCliEjb = javProCliEjb;
		this.javProWeb = javProWeb;
		this.javPaquete = javPaquete;
		this.javPaqControlador = javPaqControlador;
		this.javPreControlador = javPreControlador;
	}

	public Proyecto(int codProyecto, String nombre, String javProLibreria,
			String javProEjb, String javProCliEjb, String javProWeb,
			String javPaquete, String javPaqControlador,
			String javPreControlador, String javProEjbExt,
			Set<Catalogo> catalogos, Set<Mantenimiento> mantenimientos,
			Set<Proceso> procesos, Set<Configuracion> configuracions,
			Set<Esquema> esquemas, Set<Datasource> datasources, Set<Rol> rols,
			Set<Consulta> consultas, Set<Tabla> tablas, Set<Clase> clases) {
		this.codProyecto = codProyecto;
		this.nombre = nombre;
		this.javProLibreria = javProLibreria;
		this.javProEjb = javProEjb;
		this.javProCliEjb = javProCliEjb;
		this.javProWeb = javProWeb;
		this.javPaquete = javPaquete;
		this.javPaqControlador = javPaqControlador;
		this.javPreControlador = javPreControlador;
		this.javProEjbExt = javProEjbExt;
		this.catalogos = catalogos;
		this.mantenimientos = mantenimientos;
		this.procesos = procesos;
		this.configuracions = configuracions;
		this.esquemas = esquemas;
		this.datasources = datasources;
		this.rols = rols;
		this.consultas = consultas;
		this.tablas = tablas;
		this.clases = clases;
	}

	@Id
	@Column(name = "cod_proyecto", unique = true, nullable = false)
	public int getCodProyecto() {
		return this.codProyecto;
	}

	public void setCodProyecto(int codProyecto) {
		this.codProyecto = codProyecto;
	}

	@Column(name = "nombre", nullable = false, length = 120)
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Column(name = "jav_pro_libreria", nullable = false)
	public String getJavProLibreria() {
		return this.javProLibreria;
	}

	public void setJavProLibreria(String javProLibreria) {
		this.javProLibreria = javProLibreria;
	}

	@Column(name = "jav_pro_ejb", nullable = false)
	public String getJavProEjb() {
		return this.javProEjb;
	}

	public void setJavProEjb(String javProEjb) {
		this.javProEjb = javProEjb;
	}

	@Column(name = "jav_pro_cli_ejb", nullable = false)
	public String getJavProCliEjb() {
		return this.javProCliEjb;
	}

	public void setJavProCliEjb(String javProCliEjb) {
		this.javProCliEjb = javProCliEjb;
	}

	@Column(name = "jav_pro_web", nullable = false)
	public String getJavProWeb() {
		return this.javProWeb;
	}

	public void setJavProWeb(String javProWeb) {
		this.javProWeb = javProWeb;
	}

	@Column(name = "jav_paquete", nullable = false)
	public String getJavPaquete() {
		return this.javPaquete;
	}

	public void setJavPaquete(String javPaquete) {
		this.javPaquete = javPaquete;
	}

	@Column(name = "jav_paq_controlador", nullable = false)
	public String getJavPaqControlador() {
		return this.javPaqControlador;
	}

	public void setJavPaqControlador(String javPaqControlador) {
		this.javPaqControlador = javPaqControlador;
	}

	@Column(name = "jav_pre_controlador", nullable = false, length = 50)
	public String getJavPreControlador() {
		return this.javPreControlador;
	}

	public void setJavPreControlador(String javPreControlador) {
		this.javPreControlador = javPreControlador;
	}

	@Column(name = "jav_pro_ejb_ext")
	public String getJavProEjbExt() {
		return this.javProEjbExt;
	}

	public void setJavProEjbExt(String javProEjbExt) {
		this.javProEjbExt = javProEjbExt;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "proyecto")
	public Set<Catalogo> getCatalogos() {
		return this.catalogos;
	}

	public void setCatalogos(Set<Catalogo> catalogos) {
		this.catalogos = catalogos;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "proyecto")
	public Set<Mantenimiento> getMantenimientos() {
		return this.mantenimientos;
	}

	public void setMantenimientos(Set<Mantenimiento> mantenimientos) {
		this.mantenimientos = mantenimientos;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "proyecto")
	public Set<Proceso> getProcesos() {
		return this.procesos;
	}

	public void setProcesos(Set<Proceso> procesos) {
		this.procesos = procesos;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "proyecto")
	public Set<Configuracion> getConfiguracions() {
		return this.configuracions;
	}

	public void setConfiguracions(Set<Configuracion> configuracions) {
		this.configuracions = configuracions;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "proyecto")
	public Set<Esquema> getEsquemas() {
		return this.esquemas;
	}

	public void setEsquemas(Set<Esquema> esquemas) {
		this.esquemas = esquemas;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "proyecto")
	public Set<Datasource> getDatasources() {
		return this.datasources;
	}

	public void setDatasources(Set<Datasource> datasources) {
		this.datasources = datasources;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "proyecto")
	public Set<Rol> getRols() {
		return this.rols;
	}

	public void setRols(Set<Rol> rols) {
		this.rols = rols;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "proyecto")
	public Set<Consulta> getConsultas() {
		return this.consultas;
	}

	public void setConsultas(Set<Consulta> consultas) {
		this.consultas = consultas;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "proyecto")
	public Set<Tabla> getTablas() {
		return this.tablas;
	}

	public void setTablas(Set<Tabla> tablas) {
		this.tablas = tablas;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "proyecto")
	public Set<Clase> getClases() {
		return this.clases;
	}

	public void setClases(Set<Clase> clases) {
		this.clases = clases;
	}

}