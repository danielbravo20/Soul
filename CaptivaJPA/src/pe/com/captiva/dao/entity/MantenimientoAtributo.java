package pe.com.captiva.dao.entity;

// Generated 21/10/2015 10:31:13 PM by Hibernate Tools 4.3.1

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * MantenimientoAtributo generated by hbm2java
 */
@Entity
@Table(name = "mantenimiento_atributo", schema = "soul")
public class MantenimientoAtributo implements java.io.Serializable {

	private int codAtributo;
	private Mantenimiento mantenimiento;
	private String nombre;
	private String tipoDato;
	private Integer longitud;
	private Integer precision;
	private Character esLlavePrimaria;
	private Character esListado;
	private Character esBusqueda;
	private Character esObligatorio;
	private String descripcion;

	public MantenimientoAtributo() {
	}

	public MantenimientoAtributo(int codAtributo, String nombre, String tipoDato) {
		this.codAtributo = codAtributo;
		this.nombre = nombre;
		this.tipoDato = tipoDato;
	}

	public MantenimientoAtributo(int codAtributo, Mantenimiento mantenimiento,
			String nombre, String tipoDato, Integer longitud,
			Integer precision, Character esLlavePrimaria, Character esListado,
			Character esBusqueda, Character esObligatorio, String descripcion) {
		this.codAtributo = codAtributo;
		this.mantenimiento = mantenimiento;
		this.nombre = nombre;
		this.tipoDato = tipoDato;
		this.longitud = longitud;
		this.precision = precision;
		this.esLlavePrimaria = esLlavePrimaria;
		this.esListado = esListado;
		this.esBusqueda = esBusqueda;
		this.esObligatorio = esObligatorio;
		this.descripcion = descripcion;
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
	@JoinColumn(name = "cod_mantenimiento")
	public Mantenimiento getMantenimiento() {
		return this.mantenimiento;
	}

	public void setMantenimiento(Mantenimiento mantenimiento) {
		this.mantenimiento = mantenimiento;
	}

	@Column(name = "nombre", nullable = false, length = 50)
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Column(name = "tipo_dato", nullable = false, length = 50)
	public String getTipoDato() {
		return this.tipoDato;
	}

	public void setTipoDato(String tipoDato) {
		this.tipoDato = tipoDato;
	}

	@Column(name = "longitud")
	public Integer getLongitud() {
		return this.longitud;
	}

	public void setLongitud(Integer longitud) {
		this.longitud = longitud;
	}

	@Column(name = "precision")
	public Integer getPrecision() {
		return this.precision;
	}

	public void setPrecision(Integer precision) {
		this.precision = precision;
	}

	@Column(name = "es_llave_primaria", length = 1)
	public Character getEsLlavePrimaria() {
		return this.esLlavePrimaria;
	}

	public void setEsLlavePrimaria(Character esLlavePrimaria) {
		this.esLlavePrimaria = esLlavePrimaria;
	}

	@Column(name = "es_listado", length = 1)
	public Character getEsListado() {
		return this.esListado;
	}

	public void setEsListado(Character esListado) {
		this.esListado = esListado;
	}

	@Column(name = "es_busqueda", length = 1)
	public Character getEsBusqueda() {
		return this.esBusqueda;
	}

	public void setEsBusqueda(Character esBusqueda) {
		this.esBusqueda = esBusqueda;
	}

	@Column(name = "es_obligatorio", length = 1)
	public Character getEsObligatorio() {
		return this.esObligatorio;
	}

	public void setEsObligatorio(Character esObligatorio) {
		this.esObligatorio = esObligatorio;
	}

	@Column(name = "descripcion")
	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}