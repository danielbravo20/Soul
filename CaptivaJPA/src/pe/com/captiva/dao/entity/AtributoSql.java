package pe.com.captiva.dao.entity;

// Generated 21/10/2015 10:31:13 PM by Hibernate Tools 4.3.1

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
 * AtributoSql generated by hbm2java
 */
@Entity
@Table(name = "atributo_sql", schema = "soul")
public class AtributoSql implements java.io.Serializable {

	private AtributoSqlId id;
	private Atributo atributo;
	private Tabla tabla;
	private String campo;
	private String tipo;
	private Integer longitud;
	private Integer precision;
	private char pk;
	private char obligatorio;
	private Integer fkTabla;
	private Character fkUnoMucho;
	private String fnBusNombre;
	private String fnBusCatalogo;
	private String valDefecto;
	private Integer fkCampo;

	public AtributoSql() {
	}

	public AtributoSql(AtributoSqlId id, Atributo atributo, Tabla tabla,
			String campo, String tipo, char pk, char obligatorio) {
		this.id = id;
		this.atributo = atributo;
		this.tabla = tabla;
		this.campo = campo;
		this.tipo = tipo;
		this.pk = pk;
		this.obligatorio = obligatorio;
	}

	public AtributoSql(AtributoSqlId id, Atributo atributo, Tabla tabla,
			String campo, String tipo, Integer longitud, Integer precision,
			char pk, char obligatorio, Integer fkTabla, Character fkUnoMucho,
			String fnBusNombre, String fnBusCatalogo, String valDefecto,
			Integer fkCampo) {
		this.id = id;
		this.atributo = atributo;
		this.tabla = tabla;
		this.campo = campo;
		this.tipo = tipo;
		this.longitud = longitud;
		this.precision = precision;
		this.pk = pk;
		this.obligatorio = obligatorio;
		this.fkTabla = fkTabla;
		this.fkUnoMucho = fkUnoMucho;
		this.fnBusNombre = fnBusNombre;
		this.fnBusCatalogo = fnBusCatalogo;
		this.valDefecto = valDefecto;
		this.fkCampo = fkCampo;
	}

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "codTabla", column = @Column(name = "cod_tabla", nullable = false)),
			@AttributeOverride(name = "codAtributo", column = @Column(name = "cod_atributo", nullable = false)) })
	public AtributoSqlId getId() {
		return this.id;
	}

	public void setId(AtributoSqlId id) {
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
	@JoinColumn(name = "cod_tabla", nullable = false, insertable = false, updatable = false)
	public Tabla getTabla() {
		return this.tabla;
	}

	public void setTabla(Tabla tabla) {
		this.tabla = tabla;
	}

	@Column(name = "campo", nullable = false, length = 50)
	public String getCampo() {
		return this.campo;
	}

	public void setCampo(String campo) {
		this.campo = campo;
	}

	@Column(name = "tipo", nullable = false, length = 50)
	public String getTipo() {
		return this.tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
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

	@Column(name = "pk", nullable = false, length = 1)
	public char getPk() {
		return this.pk;
	}

	public void setPk(char pk) {
		this.pk = pk;
	}

	@Column(name = "obligatorio", nullable = false, length = 1)
	public char getObligatorio() {
		return this.obligatorio;
	}

	public void setObligatorio(char obligatorio) {
		this.obligatorio = obligatorio;
	}

	@Column(name = "fk_tabla")
	public Integer getFkTabla() {
		return this.fkTabla;
	}

	public void setFkTabla(Integer fkTabla) {
		this.fkTabla = fkTabla;
	}

	@Column(name = "fk_uno_mucho", length = 1)
	public Character getFkUnoMucho() {
		return this.fkUnoMucho;
	}

	public void setFkUnoMucho(Character fkUnoMucho) {
		this.fkUnoMucho = fkUnoMucho;
	}

	@Column(name = "fn_bus_nombre", length = 120)
	public String getFnBusNombre() {
		return this.fnBusNombre;
	}

	public void setFnBusNombre(String fnBusNombre) {
		this.fnBusNombre = fnBusNombre;
	}

	@Column(name = "fn_bus_catalogo", length = 50)
	public String getFnBusCatalogo() {
		return this.fnBusCatalogo;
	}

	public void setFnBusCatalogo(String fnBusCatalogo) {
		this.fnBusCatalogo = fnBusCatalogo;
	}

	@Column(name = "val_defecto", length = 120)
	public String getValDefecto() {
		return this.valDefecto;
	}

	public void setValDefecto(String valDefecto) {
		this.valDefecto = valDefecto;
	}

	@Column(name = "fk_campo")
	public Integer getFkCampo() {
		return this.fkCampo;
	}

	public void setFkCampo(Integer fkCampo) {
		this.fkCampo = fkCampo;
	}

}
