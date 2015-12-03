package pe.com.captiva.bean;

public class ProcesoDetalleBean extends AtributoBean{

	private static final long serialVersionUID = 1L;
	
	private int codigoProceso;
	private String codSeccion;
	private int codSubSeccion;
	private int codProcesoDetalle;
	private int codAtributo;
	private String webEtiqueta;
	
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
	public int getCodProcesoDetalle() {
		return codProcesoDetalle;
	}
	public void setCodProcesoDetalle(int codProcesoDetalle) {
		this.codProcesoDetalle = codProcesoDetalle;
	}
	public int getCodAtributo() {
		return codAtributo;
	}
	public void setCodAtributo(int codAtributo) {
		this.codAtributo = codAtributo;
	}
	public String getWebEtiqueta() {
		return webEtiqueta;
	}
	public void setWebEtiqueta(String webEtiqueta) {
		this.webEtiqueta = webEtiqueta;
	}
	
}