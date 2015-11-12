package pe.com.captiva.dao.entity;

// Generated 11/11/2015 09:33:32 PM by Hibernate Tools 4.3.1

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * TareaRolPotencial generated by hbm2java
 */
@Entity
@Table(name = "tarea_rol_potencial", schema = "public")
public class TareaRolPotencial implements java.io.Serializable {

	private TareaRolPotencialId id;

	public TareaRolPotencial() {
	}

	public TareaRolPotencial(TareaRolPotencialId id) {
		this.id = id;
	}

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "codTarea", column = @Column(name = "cod_tarea", nullable = false)),
			@AttributeOverride(name = "codRol", column = @Column(name = "cod_rol", nullable = false, length = 120)) })
	public TareaRolPotencialId getId() {
		return this.id;
	}

	public void setId(TareaRolPotencialId id) {
		this.id = id;
	}

}
