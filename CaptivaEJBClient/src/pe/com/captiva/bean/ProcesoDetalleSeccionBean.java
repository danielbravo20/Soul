package pe.com.captiva.bean;

public class ProcesoDetalleSeccionBean extends AtributoBean{

	private static final long serialVersionUID = 1L;
	
	private int codigoProceso;
	private String codSeccion;
	private Character tipoSeccion;
	private String tipoWidget;
	private String nombre;
	private String codSeccionPadre;
	
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
	public Character getTipoSeccion() {
		return tipoSeccion;
	}
	public void setTipoSeccion(Character tipoSeccion) {
		this.tipoSeccion = tipoSeccion;
	}
	public String getTipoWidget() {
		return tipoWidget;
	}
	public void setTipoWidget(String tipoWidget) {
		this.tipoWidget = tipoWidget;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getCodSeccionPadre() {
		return codSeccionPadre;
	}
	public void setCodSeccionPadre(String codSeccionPadre) {
		this.codSeccionPadre = codSeccionPadre;
	}
	
}