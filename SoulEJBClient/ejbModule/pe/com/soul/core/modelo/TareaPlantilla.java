package pe.com.soul.core.modelo;

public class TareaPlantilla extends BaseBean{
	
	private static final long serialVersionUID = 1L;
	
	private long codigoTareaPlantilla;
	private String version;
	private char estado;
	private String nombre;
	private String aleas;
	private int prioridad;
	private int orden;
	
	public long getCodigoTareaPlantilla() {
		return codigoTareaPlantilla;
	}
	public void setCodigoTareaPlantilla(long codigoTareaPlantilla) {
		this.codigoTareaPlantilla = codigoTareaPlantilla;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public char getEstado() {
		return estado;
	}
	public void setEstado(char estado) {
		this.estado = estado;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getAleas() {
		return aleas;
	}
	public void setAleas(String aleas) {
		this.aleas = aleas;
	}
	public int getPrioridad() {
		return prioridad;
	}
	public void setPrioridad(int prioridad) {
		this.prioridad = prioridad;
	}
	public int getOrden() {
		return orden;
	}
	public void setOrden(int orden) {
		this.orden = orden;
	}
	
}
