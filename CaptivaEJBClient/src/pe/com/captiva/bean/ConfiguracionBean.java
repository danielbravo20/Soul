package pe.com.captiva.bean;

public class ConfiguracionBean extends BaseBean{

	private static final long serialVersionUID = 1L;
	
	private Integer codigoProyecto;
	private String usuario;
	
	public Integer getCodigoProyecto() {
		return codigoProyecto;
	}
	public void setCodigoProyecto(Integer codigoProyecto) {
		this.codigoProyecto = codigoProyecto;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getRutaWorkSpace() {
		return rutaWorkSpace;
	}
	public void setRutaWorkSpace(String rutaWorkSpace) {
		this.rutaWorkSpace = rutaWorkSpace;
	}
	public String getRutaSQL() {
		return rutaSQL;
	}
	public void setRutaSQL(String rutaSQL) {
		this.rutaSQL = rutaSQL;
	}
	private String rutaWorkSpace;
	private String rutaSQL;
	
	
}
