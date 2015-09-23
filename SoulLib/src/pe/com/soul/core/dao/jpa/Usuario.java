package pe.com.soul.core.dao.jpa;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the "USUARIO" database table.
 * 
 */
@Entity
@Table(schema="SEGURIDAD", name="USUARIO" )
@NamedQuery(name="Usuario.findAll", query="SELECT u FROM Usuario u")
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="GER_SEQ_USUARIO")
	@SequenceGenerator(name = "GER_SEQ_USUARIO", sequenceName = "SEQ_USUARIO")
	@Column(name="COD_USUARIO")
	private Long codigo;

	@Column(name="USUARIO")
	private String usuario;

	@Column(name="CLAVE")
	private String clave;

	@Column(name="NOM_COMPLETO")
	private String nombre;

	public Usuario() {
	}

	public Long getCodigo() {
		return this.codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}