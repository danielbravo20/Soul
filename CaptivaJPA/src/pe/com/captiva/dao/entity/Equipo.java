package pe.com.captiva.dao.entity;

// Generated 11/11/2015 09:33:32 PM by Hibernate Tools 4.3.1

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
 * Equipo generated by hbm2java
 */
@Entity
@Table(name = "equipo", schema = "soul")
public class Equipo implements java.io.Serializable {

	private EquipoId id;
	private Proyecto proyecto;
	private Usuario usuario;
	private char esResponsable;
	private String carpetaDestinoWorkspace;
	private String carpetaDestinoParcial;

	public Equipo() {
	}

	public Equipo(EquipoId id, Proyecto proyecto, Usuario usuario,
			char esResponsable, String carpetaDestinoWorkspace,
			String carpetaDestinoParcial) {
		this.id = id;
		this.proyecto = proyecto;
		this.usuario = usuario;
		this.esResponsable = esResponsable;
		this.carpetaDestinoWorkspace = carpetaDestinoWorkspace;
		this.carpetaDestinoParcial = carpetaDestinoParcial;
	}

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "codProyecto", column = @Column(name = "cod_proyecto", nullable = false)),
			@AttributeOverride(name = "codUsuario", column = @Column(name = "cod_usuario", nullable = false, length = 50)) })
	public EquipoId getId() {
		return this.id;
	}

	public void setId(EquipoId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cod_proyecto", nullable = false, insertable = false, updatable = false)
	public Proyecto getProyecto() {
		return this.proyecto;
	}

	public void setProyecto(Proyecto proyecto) {
		this.proyecto = proyecto;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cod_usuario", nullable = false, insertable = false, updatable = false)
	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Column(name = "es_responsable", nullable = false, length = 1)
	public char getEsResponsable() {
		return this.esResponsable;
	}

	public void setEsResponsable(char esResponsable) {
		this.esResponsable = esResponsable;
	}

	@Column(name = "carpeta_destino_workspace", nullable = false)
	public String getCarpetaDestinoWorkspace() {
		return this.carpetaDestinoWorkspace;
	}

	public void setCarpetaDestinoWorkspace(String carpetaDestinoWorkspace) {
		this.carpetaDestinoWorkspace = carpetaDestinoWorkspace;
	}

	@Column(name = "carpeta_destino_parcial", nullable = false, length = 250)
	public String getCarpetaDestinoParcial() {
		return this.carpetaDestinoParcial;
	}

	public void setCarpetaDestinoParcial(String carpetaDestinoParcial) {
		this.carpetaDestinoParcial = carpetaDestinoParcial;
	}

}
