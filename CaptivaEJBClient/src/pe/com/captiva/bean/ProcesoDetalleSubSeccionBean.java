package pe.com.captiva.bean;

public class ProcesoDetalleSubSeccionBean extends AtributoBean{

	private static final long serialVersionUID = 1L;
	
	private int codigoProceso;
	private String codSeccion;
	private int codSubSeccion;
	private String nombre;
	
	public int getCodigoProceso() {
		return codigoProceso;
	}
	public void setCodigoProceso(int codigoProceso) {
		this.codigoProceso = codigoProceso;
	}
	public String getCodSeccion() {
		return codSeccion;
	}
	public void setCodSeccion(String codSeccion) {
		this.codSeccion = codSeccion;
	}
	public int getCodSubSeccion() {
		return codSubSeccion;
	}
	public void setCodSubSeccion(int codSubSeccion) {
		this.codSubSeccion = codSubSeccion;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
}