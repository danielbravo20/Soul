package pe.com.captiva.dao.entity;
// Generated 01/12/2015 04:35:12 PM by Hibernate Tools 4.3.1.Final

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Datasource generated by hbm2java
 */
@Entity
@Table(name = "datasource", schema = "soul")
public class Datasource implements java.io.Serializable {

	private String codDatasource;
	private Proyecto proyecto;
	private Character estado;
	private String descripcion;

	public Datasource() {
	}

	public Datasource(String codDatasource, String descripcion) {
		this.codDatasource = codDatasource;
		this.descripcion = descripcion;
	}

	public Datasource(String codDatasource, Proyecto proyecto, Character estado, String descripcion) {
		this.codDatasource = codDatasource;
		this.proyecto = proyecto;
		this.estado = estado;
		this.descripcion = descripcion;
	}

	@Id

	@Column(name = "cod_datasource", unique = true, nullable = false, length = 100)
	public String getCodDatasource() {
		return this.codDatasource;
	}

	public void setCodDatasource(String codDatasource) {
		this.codDatasource = codDatasource;
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

}
