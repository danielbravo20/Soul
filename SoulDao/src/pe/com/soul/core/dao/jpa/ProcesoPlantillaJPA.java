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
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * ProPlantilla generated by hbm2java
 */
@Entity
@Table(name = "pro_plantilla", schema = "proceso")
public class ProcesoPlantillaJPA extends JpaBase {
	
	private static final long serialVersionUID = 1L;
	
	private long codProPlantilla;
	private char estado;
	private String nombre;
	private Set<ProcesoJPA> proInstancias = new HashSet<ProcesoJPA>(0);
	private Set<TareaPlantillaJPA> tarPlantillas = new HashSet<TareaPlantillaJPA>(0);
	private Set<RolJPA> rols = new HashSet<RolJPA>(0);

	public ProcesoPlantillaJPA() {
	}

	public ProcesoPlantillaJPA(long codProPlantilla, char estado, String nombre) {
		this.codProPlantilla = codProPlantilla;
		this.estado = estado;
		this.nombre = nombre;
	}

	public ProcesoPlantillaJPA(long codProPlantilla, char estado, String nombre,
			Set<ProcesoJPA> proInstancias, Set<TareaPlantillaJPA> tarPlantillas,
			Set<RolJPA> rols) {
		this.codProPlantilla = codProPlantilla;
		this.estado = estado;
		this.nombre = nombre;
		this.proInstancias = proInstancias;
		this.tarPlantillas = tarPlantillas;
		this.rols = rols;
	}

	@Id
	@Column(name = "cod_pro_plantilla", unique = true, nullable = false)
	public long getCodProPlantilla() {
		return this.codProPlantilla;
	}

	public void setCodProPlantilla(long codProPlantilla) {
		this.codProPlantilla = codProPlantilla;
	}

	@Column(name = "estado", nullable = false, length = 1)
	public char getEstado() {
		return this.estado;
	}

	public void setEstado(char estado) {
		this.estado = estado;
	}

	@Column(name = "nombre", nullable = false, length = 120)
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "proPlantilla")
	public Set<ProcesoJPA> getProInstancias() {
		return this.proInstancias;
	}

	public void setProInstancias(Set<ProcesoJPA> proInstancias) {
		this.proInstancias = proInstancias;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "proPlantilla")
	public Set<TareaPlantillaJPA> getTarPlantillas() {
		return this.tarPlantillas;
	}

	public void setTarPlantillas(Set<TareaPlantillaJPA> tarPlantillas) {
		this.tarPlantillas = tarPlantillas;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "pro_pla_rol", schema = "proceso", joinColumns = { @JoinColumn(name = "cod_pro_plantilla", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "cod_rol", nullable = false, updatable = false) })
	public Set<RolJPA> getRols() {
		return this.rols;
	}

	public void setRols(Set<RolJPA> rols) {
		this.rols = rols;
	}

}
