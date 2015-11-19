package pe.com.captiva.dao.entity;

// Generated 19/11/2015 03:54:12 PM by Hibernate Tools 4.3.1

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Usuario generated by hbm2java
 */
@Entity
@Table(name = "usuario", schema = "soul")
public class Usuario implements java.io.Serializable {

	private String codUsuario;
	private String clave;
	private String nombre;
	private String perfil;
	private String descripcion;
	private Set<Equipo> equipos = new HashSet<Equipo>(0);

	public Usuario() {
	}

	public Usuario(String codUsuario, String clave, String nombre, String perfil) {
		this.codUsuario = codUsuario;
		this.clave = clave;
		this.nombre = nombre;
		this.perfil = perfil;
	}

	public Usuario(String codUsuario, String clave, String nombre,
			String perfil, String descripcion, Set<Equipo> equipos) {
		this.codUsuario = codUsuario;
		this.clave = clave;
		this.nombre = nombre;
		this.perfil = perfil;
		this.descripcion = descripcion;
		this.equipos = equipos;
	}

	@Id
	@Column(name = "cod_usuario", unique = true, nullable = false, length = 50)
	public String getCodUsuario() {
		return this.codUsuario;
	}

	public void setCodUsuario(String codUsuario) {
		this.codUsuario = codUsuario;
	}

	@Column(name = "clave", nullable = false, length = 100)
	public String getClave() {
		return this.clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	@Column(name = "nombre", nullable = false, length = 100)
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Column(name = "perfil", nullable = false, length = 50)
	public String getPerfil() {
		return this.perfil;
	}

	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}

	@Column(name = "descripcion", length = 250)
	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "usuario")
	public Set<Equipo> getEquipos() {
		return this.equipos;
	}

	public void setEquipos(Set<Equipo> equipos) {
		this.equipos = equipos;
	}

}
