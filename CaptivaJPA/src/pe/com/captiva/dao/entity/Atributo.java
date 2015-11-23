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
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Atributo generated by hbm2java
 */
@Entity
@Table(name = "atributo", schema = "soul")
public class Atributo implements java.io.Serializable {

	private int codAtributo;
	private Clase clase;
	private String nombre;
	private String tipo;
	private char flgLista;
	private String webNombre;
	private String webFormato;
	private Set<TareaAtrObservar> tareaAtrObservars = new HashSet<TareaAtrObservar>(
			0);
	private Set<TareaAtrCompletar> tareaAtrCompletars = new HashSet<TareaAtrCompletar>(
			0);
	private Set<ProcesoInicio> procesoInicios = new HashSet<ProcesoInicio>(0);
	private Set<TareaAtrCancelar> tareaAtrCancelars = new HashSet<TareaAtrCancelar>(
			0);
	private Set<TareaAtrRechazar> tareaAtrRechazars = new HashSet<TareaAtrRechazar>(
			0);
	private AtributoSql atributoSql;
	private Set<ConsultaAtributo> consultaAtributos = new HashSet<ConsultaAtributo>(
			0);

	public Atributo() {
	}

	public Atributo(int codAtributo, Clase clase, String nombre, String tipo,
			char flgLista) {
		this.codAtributo = codAtributo;
		this.clase = clase;
		this.nombre = nombre;
		this.tipo = tipo;
		this.flgLista = flgLista;
	}

	public Atributo(int codAtributo, Clase clase, String nombre, String tipo,
			char flgLista, String webNombre, String webFormato,
			Set<TareaAtrObservar> tareaAtrObservars,
			Set<TareaAtrCompletar> tareaAtrCompletars,
			Set<ProcesoInicio> procesoInicios,
			Set<TareaAtrCancelar> tareaAtrCancelars,
			Set<TareaAtrRechazar> tareaAtrRechazars, AtributoSql atributoSql,
			Set<ConsultaAtributo> consultaAtributos) {
		this.codAtributo = codAtributo;
		this.clase = clase;
		this.nombre = nombre;
		this.tipo = tipo;
		this.flgLista = flgLista;
		this.webNombre = webNombre;
		this.webFormato = webFormato;
		this.tareaAtrObservars = tareaAtrObservars;
		this.tareaAtrCompletars = tareaAtrCompletars;
		this.procesoInicios = procesoInicios;
		this.tareaAtrCancelars = tareaAtrCancelars;
		this.tareaAtrRechazars = tareaAtrRechazars;
		this.atributoSql = atributoSql;
		this.consultaAtributos = consultaAtributos;
	}

	@Id
	@Column(name = "cod_atributo", unique = true, nullable = false)
	public int getCodAtributo() {
		return this.codAtributo;
	}

	public void setCodAtributo(int codAtributo) {
		this.codAtributo = codAtributo;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cod_clase", nullable = false)
	public Clase getClase() {
		return this.clase;
	}

	public void setClase(Clase clase) {
		this.clase = clase;
	}

	@Column(name = "nombre", nullable = false, length = 120)
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Column(name = "tipo", nullable = false)
	public String getTipo() {
		return this.tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	@Column(name = "flg_lista", nullable = false, length = 1)
	public char getFlgLista() {
		return this.flgLista;
	}

	public void setFlgLista(char flgLista) {
		this.flgLista = flgLista;
	}

	@Column(name = "web_nombre", length = 120)
	public String getWebNombre() {
		return this.webNombre;
	}

	public void setWebNombre(String webNombre) {
		this.webNombre = webNombre;
	}

	@Column(name = "web_formato", length = 120)
	public String getWebFormato() {
		return this.webFormato;
	}

	public void setWebFormato(String webFormato) {
		this.webFormato = webFormato;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "atributo")
	public Set<TareaAtrObservar> getTareaAtrObservars() {
		return this.tareaAtrObservars;
	}

	public void setTareaAtrObservars(Set<TareaAtrObservar> tareaAtrObservars) {
		this.tareaAtrObservars = tareaAtrObservars;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "atributo")
	public Set<TareaAtrCompletar> getTareaAtrCompletars() {
		return this.tareaAtrCompletars;
	}

	public void setTareaAtrCompletars(Set<TareaAtrCompletar> tareaAtrCompletars) {
		this.tareaAtrCompletars = tareaAtrCompletars;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "atributo")
	public Set<ProcesoInicio> getProcesoInicios() {
		return this.procesoInicios;
	}

	public void setProcesoInicios(Set<ProcesoInicio> procesoInicios) {
		this.procesoInicios = procesoInicios;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "atributo")
	public Set<TareaAtrCancelar> getTareaAtrCancelars() {
		return this.tareaAtrCancelars;
	}

	public void setTareaAtrCancelars(Set<TareaAtrCancelar> tareaAtrCancelars) {
		this.tareaAtrCancelars = tareaAtrCancelars;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "atributo")
	public Set<TareaAtrRechazar> getTareaAtrRechazars() {
		return this.tareaAtrRechazars;
	}

	public void setTareaAtrRechazars(Set<TareaAtrRechazar> tareaAtrRechazars) {
		this.tareaAtrRechazars = tareaAtrRechazars;
	}

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "atributo")
	public AtributoSql getAtributoSql() {
		return this.atributoSql;
	}

	public void setAtributoSql(AtributoSql atributoSql) {
		this.atributoSql = atributoSql;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "atributo")
	public Set<ConsultaAtributo> getConsultaAtributos() {
		return this.consultaAtributos;
	}

	public void setConsultaAtributos(Set<ConsultaAtributo> consultaAtributos) {
		this.consultaAtributos = consultaAtributos;
	}

}
