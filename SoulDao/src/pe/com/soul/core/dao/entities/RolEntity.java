package pe.com.soul.core.dao.entities;

// Generated 25/10/2015 05:15:08 AM by Hibernate Tools 4.3.1

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * Rol generated by hbm2java
 */
@Entity
@Table(name = "rol", schema = "seguridad")
public class RolEntity implements java.io.Serializable {

	private String codigoRol;
	private String nombreRol;
	private Set<TareaPlantillaEntity> tareaPlantillas = new HashSet<TareaPlantillaEntity>(0);
	private Set<ModuloEntity> modulos = new HashSet<ModuloEntity>(0);
	private Set<UsuarioEntity> usuarios = new HashSet<UsuarioEntity>(0);
	private Set<ProcesoPlantillaEntity> procesoPlantillas = new HashSet<ProcesoPlantillaEntity>(
			0);
	private Set<TareaPlantillaEntity> tareaPlantillas_1 = new HashSet<TareaPlantillaEntity>(
			0);
	private Set<TareaPlantillaEntity> tareaPlantillas_2 = new HashSet<TareaPlantillaEntity>(
			0);
	private Set<ProcesoPlantillaEntity> procesoPlantillas_1 = new HashSet<ProcesoPlantillaEntity>(
			0);

	public RolEntity() {
	}

	public RolEntity(String codigoRol, String nombreRol) {
		this.codigoRol = codigoRol;
		this.nombreRol = nombreRol;
	}

	public RolEntity(String codigoRol, String nombreRol,
			Set<TareaPlantillaEntity> tareaPlantillas, Set<ModuloEntity> modulos,
			Set<UsuarioEntity> usuarios, Set<ProcesoPlantillaEntity> procesoPlantillas,
			Set<TareaPlantillaEntity> tareaPlantillas_1,
			Set<TareaPlantillaEntity> tareaPlantillas_2,
			Set<ProcesoPlantillaEntity> procesoPlantillas_1) {
		this.codigoRol = codigoRol;
		this.nombreRol = nombreRol;
		this.tareaPlantillas = tareaPlantillas;
		this.modulos = modulos;
		this.usuarios = usuarios;
		this.procesoPlantillas = procesoPlantillas;
		this.tareaPlantillas_1 = tareaPlantillas_1;
		this.tareaPlantillas_2 = tareaPlantillas_2;
		this.procesoPlantillas_1 = procesoPlantillas_1;
	}

	@Id
	@Column(name = "codigo_rol", unique = true, nullable = false, length = 120)
	public String getCodigoRol() {
		return this.codigoRol;
	}

	public void setCodigoRol(String codigoRol) {
		this.codigoRol = codigoRol;
	}

	@Column(name = "nombre_rol", nullable = false, length = 120)
	public String getNombreRol() {
		return this.nombreRol;
	}

	public void setNombreRol(String nombreRol) {
		this.nombreRol = nombreRol;
	}

	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "rols")
	public Set<TareaPlantillaEntity> getTareaPlantillas() {
		return this.tareaPlantillas;
	}

	public void setTareaPlantillas(Set<TareaPlantillaEntity> tareaPlantillas) {
		this.tareaPlantillas = tareaPlantillas;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "modulo_rol", schema = "seguridad", joinColumns = { @JoinColumn(name = "codigo_rol", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "codigo_modulo", nullable = false, updatable = false) })
	public Set<ModuloEntity> getModulos() {
		return this.modulos;
	}

	public void setModulos(Set<ModuloEntity> modulos) {
		this.modulos = modulos;
	}

	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "rols")
	public Set<UsuarioEntity> getUsuarios() {
		return this.usuarios;
	}

	public void setUsuarios(Set<UsuarioEntity> usuarios) {
		this.usuarios = usuarios;
	}

	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "rols_1")
	public Set<ProcesoPlantillaEntity> getProcesoPlantillas() {
		return this.procesoPlantillas;
	}

	public void setProcesoPlantillas(Set<ProcesoPlantillaEntity> procesoPlantillas) {
		this.procesoPlantillas = procesoPlantillas;
	}

	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "rols")
	public Set<TareaPlantillaEntity> getTareaPlantillas_1() {
		return this.tareaPlantillas_1;
	}

	public void setTareaPlantillas_1(Set<TareaPlantillaEntity> tareaPlantillas_1) {
		this.tareaPlantillas_1 = tareaPlantillas_1;
	}

	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "rols")
	public Set<TareaPlantillaEntity> getTareaPlantillas_2() {
		return this.tareaPlantillas_2;
	}

	public void setTareaPlantillas_2(Set<TareaPlantillaEntity> tareaPlantillas_2) {
		this.tareaPlantillas_2 = tareaPlantillas_2;
	}

	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "rols")
	public Set<ProcesoPlantillaEntity> getProcesoPlantillas_1() {
		return this.procesoPlantillas_1;
	}

	public void setProcesoPlantillas_1(Set<ProcesoPlantillaEntity> procesoPlantillas_1) {
		this.procesoPlantillas_1 = procesoPlantillas_1;
	}

}
