package pe.com.soul.core.modelo;

public class Modulo extends BeanBase{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int codigoModulo;
	private String nombre;
	private int orden;
	private String descripcion;
	private String url;
	
	public int getCodigoModulo() {
		return codigoModulo;
	}
	public void setCodigoModulo(int codigoModulo) {
		this.codigoModulo = codigoModulo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getOrden() {
		return orden;
	}
	public void setOrden(int orden) {
		this.orden = orden;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
}
