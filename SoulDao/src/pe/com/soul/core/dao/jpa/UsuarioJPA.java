package pe.com.soul.core.dao.jpa;

// Generated 24/09/2015 09:34:46 PM by Hibernate Tools 4.3.1

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

import pe.com.soul.core.modelo.Usuario;

/**
 * Usuario generated by hbm2java
 */
@Entity
@Table(name = "usuario", schema = "seguridad")
public class UsuarioJPA extends Usuario{

	private static final long serialVersionUID = 1L;
	
	protected Set<RolJPA> rols = new HashSet<RolJPA>(0);

	public UsuarioJPA() {
	}

	public UsuarioJPA(long codigo, char estado, String usuario, String clave, String nombreCompleto, String correo) {
		this.codigo = codigo;
		this.estado = estado;
		this.usuario = usuario;
		this.clave = clave;
		this.nombreCompleto = nombreCompleto;
		this.correo = correo;
	}

	public UsuarioJPA(long codigo, char estado, String usuario, String clave, String nombreCompleto, String correo, Set<RolJPA> rols) {
		this.codigo = codigo;
		this.estado = estado;
		this.usuario = usuario;
		this.clave = clave;
		this.nombreCompleto = nombreCompleto;
		this.correo = correo;
		this.rols = rols;
	}

	@Id
	@Column(name = "cod_usuario", unique = true, nullable = false)
	public long getCodigo() {
		return this.codigo;
	}

	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}

	@Column(name = "estado", nullable = false, length = 1)
	public char getEstado() {
		return this.estado;
	}

	public void setEstado(char estado) {
		this.estado = estado;
	}

	@Column(name = "usuario", nullable = false, length = 40)
	public String getUsuario() {
		return this.usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	@Column(name = "clave", nullable = false, length = 20)
	public String getClave() {
		return this.clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	@Column(name = "nom_completo", nullable = false, length = 120)
	public String getNombreCompleto() {
		return this.nombreCompleto;
	}

	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}

	@Column(name = "correo", nullable = false, length = 120)
	public String getCorreo() {
		return this.correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "usu_rol", schema = "seguridad", joinColumns = { @JoinColumn(name = "cod_usuario", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "cod_rol", nullable = false, updatable = false) })
	public Set<RolJPA> getRols() {
		return this.rols;
	}

	public void setRols(Set<RolJPA> rols) {
		this.rols = rols;
	}

}
