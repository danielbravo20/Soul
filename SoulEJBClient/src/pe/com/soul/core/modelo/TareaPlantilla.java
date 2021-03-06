package pe.com.soul.core.modelo;

public class TareaPlantilla extends BaseBean{
	
	private static final long serialVersionUID = 1L;
	
	private long codigoTareaPlantilla;
	private String version;
	private int estado;
	private String nombre;
	private String aleas;
	private int prioridad;
	private int orden;
	
	public TareaPlantilla() {
	}
	
	public TareaPlantilla(	long codigoTareaPlantilla, 
							String version,
							int estado,
							String nombre,
							String aleas,
							int prioridad,
							int orden) {
		this.codigoTareaPlantilla = codigoTareaPlantilla;
		this.version = version;
		this.estado = estado;
		this.nombre = nombre;
		this.aleas = aleas;
		this.prioridad = prioridad;
		this.orden = orden;
	}
	
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
	
	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
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
