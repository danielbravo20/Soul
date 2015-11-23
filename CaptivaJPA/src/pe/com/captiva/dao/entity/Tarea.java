package pe.com.captiva.dao.entity;

// Generated 23/11/2015 04:50:15 PM by Hibernate Tools 4.3.1

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
 * Tarea generated by hbm2java
 */
@Entity
@Table(name = "tarea", schema = "soul")
public class Tarea implements java.io.Serializable {

	private int codTarea;
	private Consulta consultaByCodConTrabajar;
	private Consulta consultaByCodConCompletar;
	private Proceso proceso;
	private Tarea tareaByCodTareaObservado;
	private Tarea tareaByCodTareaSiguiente;
	private String nombre;
	private String clase;
	private char webAccCompletar;
	private char webAccGrabar;
	private char webAccCancelar;
	private char webAccRechazar;
	private char webAccObservar;
	private int webTieRojo;
	private int webTieAmarillo;
	private Character tipoVista;
	private Set<TareaAtrCompletar> tareaAtrCompletars = new HashSet<TareaAtrCompletar>(
			0);
	private Set<Proceso> procesos = new HashSet<Proceso>(0);
	private Set<TareaAtrCancelar> tareaAtrCancelars = new HashSet<TareaAtrCancelar>(
			0);
	private Set<Rol> rols = new HashSet<Rol>(0);
	private Set<Tarea> tareasForCodTareaObservado = new HashSet<Tarea>(0);
	private Set<Tarea> tareasForCodTareaSiguiente = new HashSet<Tarea>(0);
	private Set<TareaAtrRechazar> tareaAtrRechazars = new HashSet<TareaAtrRechazar>(
			0);
	private Set<TareaAtrObservar> tareaAtrObservars = new HashSet<TareaAtrObservar>(
			0);
	private Set<Rol> rols_1 = new HashSet<Rol>(0);

	public Tarea() {
	}

	public Tarea(int codTarea, Consulta consultaByCodConTrabajar,
			Consulta consultaByCodConCompletar, String nombre, String clase,
			char webAccCompletar, char webAccGrabar, char webAccCancelar,
			char webAccRechazar, char webAccObservar, int webTieRojo,
			int webTieAmarillo) {
		this.codTarea = codTarea;
		this.consultaByCodConTrabajar = consultaByCodConTrabajar;
		this.consultaByCodConCompletar = consultaByCodConCompletar;
		this.nombre = nombre;
		this.clase = clase;
		this.webAccCompletar = webAccCompletar;
		this.webAccGrabar = webAccGrabar;
		this.webAccCancelar = webAccCancelar;
		this.webAccRechazar = webAccRechazar;
		this.webAccObservar = webAccObservar;
		this.webTieRojo = webTieRojo;
		this.webTieAmarillo = webTieAmarillo;
	}

	public Tarea(int codTarea, Consulta consultaByCodConTrabajar,
			Consulta consultaByCodConCompletar, Proceso proceso,
			Tarea tareaByCodTareaObservado, Tarea tareaByCodTareaSiguiente,
			String nombre, String clase, char webAccCompletar,
			char webAccGrabar, char webAccCancelar, char webAccRechazar,
			char webAccObservar, int webTieRojo, int webTieAmarillo,
			Character tipoVista, Set<TareaAtrCompletar> tareaAtrCompletars,
			Set<Proceso> procesos, Set<TareaAtrCancelar> tareaAtrCancelars,
			Set<Rol> rols, Set<Tarea> tareasForCodTareaObservado,
			Set<Tarea> tareasForCodTareaSiguiente,
			Set<TareaAtrRechazar> tareaAtrRechazars,
			Set<TareaAtrObservar> tareaAtrObservars, Set<Rol> rols_1) {
		this.codTarea = codTarea;
		this.consultaByCodConTrabajar = consultaByCodConTrabajar;
		this.consultaByCodConCompletar = consultaByCodConCompletar;
		this.proceso = proceso;
		this.tareaByCodTareaObservado = tareaByCodTareaObservado;
		this.tareaByCodTareaSiguiente = tareaByCodTareaSiguiente;
		this.nombre = nombre;
		this.clase = clase;
		this.webAccCompletar = webAccCompletar;
		this.webAccGrabar = webAccGrabar;
		this.webAccCancelar = webAccCancelar;
		this.webAccRechazar = webAccRechazar;
		this.webAccObservar = webAccObservar;
		this.webTieRojo = webTieRojo;
		this.webTieAmarillo = webTieAmarillo;
		this.tipoVista = tipoVista;
		this.tareaAtrCompletars = tareaAtrCompletars;
		this.procesos = procesos;
		this.tareaAtrCancelars = tareaAtrCancelars;
		this.rols = rols;
		this.tareasForCodTareaObservado = tareasForCodTareaObservado;
		this.tareasForCodTareaSiguiente = tareasForCodTareaSiguiente;
		this.tareaAtrRechazars = tareaAtrRechazars;
		this.tareaAtrObservars = tareaAtrObservars;
		this.rols_1 = rols_1;
	}

	@Id
	@Column(name = "cod_tarea", unique = true, nullable = false)
	public int getCodTarea() {
		return this.codTarea;
	}

	public void setCodTarea(int codTarea) {
		this.codTarea = codTarea;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cod_con_trabajar", nullable = false)
	public Consulta getConsultaByCodConTrabajar() {
		return this.consultaByCodConTrabajar;
	}

	public void setConsultaByCodConTrabajar(Consulta consultaByCodConTrabajar) {
		this.consultaByCodConTrabajar = consultaByCodConTrabajar;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cod_con_completar", nullable = false)
	public Consulta getConsultaByCodConCompletar() {
		return this.consultaByCodConCompletar;
	}

	public void setConsultaByCodConCompletar(Consulta consultaByCodConCompletar) {
		this.consultaByCodConCompletar = consultaByCodConCompletar;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cod_proceso")
	public Proceso getProceso() {
		return this.proceso;
	}

	public void setProceso(Proceso proceso) {
		this.proceso = proceso;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cod_tarea_observado")
	public Tarea getTareaByCodTareaObservado() {
		return this.tareaByCodTareaObservado;
	}

	public void setTareaByCodTareaObservado(Tarea tareaByCodTareaObservado) {
		this.tareaByCodTareaObservado = tareaByCodTareaObservado;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cod_tarea_siguiente")
	public Tarea getTareaByCodTareaSiguiente() {
		return this.tareaByCodTareaSiguiente;
	}

	public void setTareaByCodTareaSiguiente(Tarea tareaByCodTareaSiguiente) {
		this.tareaByCodTareaSiguiente = tareaByCodTareaSiguiente;
	}

	@Column(name = "nombre", nullable = false, length = 120)
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Column(name = "clase", nullable = false)
	public String getClase() {
		return this.clase;
	}

	public void setClase(String clase) {
		this.clase = clase;
	}

	@Column(name = "web_acc_completar", nullable = false, length = 1)
	public char getWebAccCompletar() {
		return this.webAccCompletar;
	}

	public void setWebAccCompletar(char webAccCompletar) {
		this.webAccCompletar = webAccCompletar;
	}

	@Column(name = "web_acc_grabar", nullable = false, length = 1)
	public char getWebAccGrabar() {
		return this.webAccGrabar;
	}

	public void setWebAccGrabar(char webAccGrabar) {
		this.webAccGrabar = webAccGrabar;
	}

	@Column(name = "web_acc_cancelar", nullable = false, length = 1)
	public char getWebAccCancelar() {
		return this.webAccCancelar;
	}

	public void setWebAccCancelar(char webAccCancelar) {
		this.webAccCancelar = webAccCancelar;
	}

	@Column(name = "web_acc_rechazar", nullable = false, length = 1)
	public char getWebAccRechazar() {
		return this.webAccRechazar;
	}

	public void setWebAccRechazar(char webAccRechazar) {
		this.webAccRechazar = webAccRechazar;
	}

	@Column(name = "web_acc_observar", nullable = false, length = 1)
	public char getWebAccObservar() {
		return this.webAccObservar;
	}

	public void setWebAccObservar(char webAccObservar) {
		this.webAccObservar = webAccObservar;
	}

	@Column(name = "web_tie_rojo", nullable = false)
	public int getWebTieRojo() {
		return this.webTieRojo;
	}

	public void setWebTieRojo(int webTieRojo) {
		this.webTieRojo = webTieRojo;
	}

	@Column(name = "web_tie_amarillo", nullable = false)
	public int getWebTieAmarillo() {
		return this.webTieAmarillo;
	}

	public void setWebTieAmarillo(int webTieAmarillo) {
		this.webTieAmarillo = webTieAmarillo;
	}

	@Column(name = "tipo_vista", length = 1)
	public Character getTipoVista() {
		return this.tipoVista;
	}

	public void setTipoVista(Character tipoVista) {
		this.tipoVista = tipoVista;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tarea")
	public Set<TareaAtrCompletar> getTareaAtrCompletars() {
		return this.tareaAtrCompletars;
	}

	public void setTareaAtrCompletars(Set<TareaAtrCompletar> tareaAtrCompletars) {
		this.tareaAtrCompletars = tareaAtrCompletars;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tarea")
	public Set<Proceso> getProcesos() {
		return this.procesos;
	}

	public void setProcesos(Set<Proceso> procesos) {
		this.procesos = procesos;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tarea")
	public Set<TareaAtrCancelar> getTareaAtrCancelars() {
		return this.tareaAtrCancelars;
	}

	public void setTareaAtrCancelars(Set<TareaAtrCancelar> tareaAtrCancelars) {
		this.tareaAtrCancelars = tareaAtrCancelars;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "tarea_rol_potencial", schema = "soul", joinColumns = { @JoinColumn(name = "cod_tarea", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "cod_rol", nullable = false, updatable = false) })
	public Set<Rol> getRols() {
		return this.rols;
	}

	public void setRols(Set<Rol> rols) {
		this.rols = rols;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tareaByCodTareaObservado")
	public Set<Tarea> getTareasForCodTareaObservado() {
		return this.tareasForCodTareaObservado;
	}

	public void setTareasForCodTareaObservado(
			Set<Tarea> tareasForCodTareaObservado) {
		this.tareasForCodTareaObservado = tareasForCodTareaObservado;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tareaByCodTareaSiguiente")
	public Set<Tarea> getTareasForCodTareaSiguiente() {
		return this.tareasForCodTareaSiguiente;
	}

	public void setTareasForCodTareaSiguiente(
			Set<Tarea> tareasForCodTareaSiguiente) {
		this.tareasForCodTareaSiguiente = tareasForCodTareaSiguiente;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tarea")
	public Set<TareaAtrRechazar> getTareaAtrRechazars() {
		return this.tareaAtrRechazars;
	}

	public void setTareaAtrRechazars(Set<TareaAtrRechazar> tareaAtrRechazars) {
		this.tareaAtrRechazars = tareaAtrRechazars;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tarea")
	public Set<TareaAtrObservar> getTareaAtrObservars() {
		return this.tareaAtrObservars;
	}

	public void setTareaAtrObservars(Set<TareaAtrObservar> tareaAtrObservars) {
		this.tareaAtrObservars = tareaAtrObservars;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "tarea_rol_administrador", schema = "soul", joinColumns = { @JoinColumn(name = "cod_tarea", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "cod_rol", nullable = false, updatable = false) })
	public Set<Rol> getRols_1() {
		return this.rols_1;
	}

	public void setRols_1(Set<Rol> rols_1) {
		this.rols_1 = rols_1;
	}

}
