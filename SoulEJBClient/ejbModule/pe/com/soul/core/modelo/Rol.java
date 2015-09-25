package pe.com.soul.core.modelo;

public class Rol extends BeanBase{

	private static final long serialVersionUID = 1L;
	
	private long codRol;
	private String nombre;
	
	public long getCodRol() {
		return codRol;
	}
	public void setCodRol(long codRol) {
		this.codRol = codRol;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
