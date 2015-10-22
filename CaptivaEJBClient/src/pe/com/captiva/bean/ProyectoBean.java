package pe.com.captiva.bean;

public class ProyectoBean extends BaseBean{

	private static final long serialVersionUID = 1L;
	
	private int codProyecto;
	private String nombre;
	private String javPaquete;
	
	public int getCodProyecto() {
		return codProyecto;
	}
	public void setCodProyecto(int codProyecto) {
		this.codProyecto = codProyecto;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getJavPaquete() {
		return javPaquete;
	}
	public void setJavPaquete(String javPaquete) {
		this.javPaquete = javPaquete;
	}
	
}
