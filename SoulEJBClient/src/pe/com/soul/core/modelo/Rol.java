package pe.com.soul.core.modelo;

public class Rol extends BaseBean{

	private static final long serialVersionUID = 1L;
	
	private String codRol;
	private String nombre;
	
	public String getCodRol() {
		return codRol;
	}
	public void setCodRol(String codRol) {
		this.codRol = codRol;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
