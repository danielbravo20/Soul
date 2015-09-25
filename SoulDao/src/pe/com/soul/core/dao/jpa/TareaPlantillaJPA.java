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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * TarPlantilla generated by hbm2java
 */
@Entity
@Table(name = "tar_plantilla", schema = "proceso")
public class TareaPlantillaJPA extends JpaBase {
	
	private static final long serialVersionUID = 1L;
	
	private long codTarPlantilla;
	private ProcesoPlantillaJPA proPlantilla;
	private char estado;
	private Set<RolJPA> rols = new HashSet<RolJPA>(0);
	private Set<TareaJPA> tarInstancias = new HashSet<TareaJPA>(0);

	public TareaPlantillaJPA() {
	}

	public TareaPlantillaJPA(long codTarPlantilla, ProcesoPlantillaJPA proPlantilla,
			char estado) {
		this.codTarPlantilla = codTarPlantilla;
		this.proPlantilla = proPlantilla;
		this.estado = estado;
	}

	public TareaPlantillaJPA(long codTarPlantilla, ProcesoPlantillaJPA proPlantilla,
			char estado, Set<RolJPA> rols, Set<TareaJPA> tarInstancias) {
		this.codTarPlantilla = codTarPlantilla;
		this.proPlantilla = proPlantilla;
		this.estado = estado;
		this.rols = rols;
		this.tarInstancias = tarInstancias;
	}

	@Id
	@Column(name = "cod_tar_plantilla", unique = true, nullable = false)
	public long getCodTarPlantilla() {
		return this.codTarPlantilla;
	}

	public void setCodTarPlantilla(long codTarPlantilla) {
		this.codTarPlantilla = codTarPlantilla;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cod_pro_plantilla", nullable = false)
	public ProcesoPlantillaJPA getProPlantilla() {
		return this.proPlantilla;
	}

	public void setProPlantilla(ProcesoPlantillaJPA proPlantilla) {
		this.proPlantilla = proPlantilla;
	}

	@Column(name = "estado", nullable = false, length = 1)
	public char getEstado() {
		return this.estado;
	}

	public void setEstado(char estado) {
		this.estado = estado;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "tar_pla_rol", schema = "proceso", joinColumns = { @JoinColumn(name = "cod_tar_plantilla", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "cod_rol", nullable = false, updatable = false) })
	public Set<RolJPA> getRols() {
		return this.rols;
	}

	public void setRols(Set<RolJPA> rols) {
		this.rols = rols;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tarPlantilla")
	public Set<TareaJPA> getTarInstancias() {
		return this.tarInstancias;
	}

	public void setTarInstancias(Set<TareaJPA> tarInstancias) {
		this.tarInstancias = tarInstancias;
	}

}
