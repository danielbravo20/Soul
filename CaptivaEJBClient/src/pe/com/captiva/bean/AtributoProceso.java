package pe.com.captiva.bean;

public class AtributoProceso extends AtributoBean{

	private static final long serialVersionUID = 1L;
	
	private int 		codigoProyecto;
	private int 		codigoProceso;
	
	private boolean 	webFlgRequerido;
	private String 		webMensajeValidacion;
	private String 		webNombreCatalogoCombo;
	private String 		webEtiqueta;
	
	private String 		valorOmision;
	
	private CampoSQLProcesoBean campoSQLProceso;
	private ClaseBean clase;
	
	public int getCodigoProyecto() {
		return codigoProyecto;
	}
	public void setCodigoProyecto(int codigoProyecto) {
		this.codigoProyecto = codigoProyecto;
	}
	public int getCodigoProceso() {
		return codigoProceso;
	}
	public void setCodigoProceso(int codigoProceso) {
		this.codigoProceso = codigoProceso;
	}
	public String getWebMensajeValidacion() {
		return webMensajeValidacion;
	}
	public void setWebMensajeValidacion(String webMensajeValidacion) {
		this.webMensajeValidacion = webMensajeValidacion;
	}
	public boolean isWebFlgRequerido() {
		return webFlgRequerido;
	}
	public void setWebFlgRequerido(boolean webFlgRequerido) {
		this.webFlgRequerido = webFlgRequerido;
	}
	public String getWebNombreCatalogoCombo() {
		return webNombreCatalogoCombo;
	}
	public void setWebNombreCatalogoCombo(String webNombreCatalogoCombo) {
		this.webNombreCatalogoCombo = webNombreCatalogoCombo;
	}
	
	public CampoSQLProcesoBean getCampoSQLProceso() {
		return campoSQLProceso;
	}
	public void setCampoSQLProceso(CampoSQLProcesoBean campoSQLProceso) {
		this.campoSQLProceso = campoSQLProceso;
	}

	public boolean isFlgExisteCampoSQL(){
		if(getCampoSQLProceso()!=null && getCampoSQLProceso().getCodigo()>0){
			return true;
		}
		return false;
	}
	
	public boolean isJavaTieneValorOmision(){
		if(this.valorOmision!=null && this.valorOmision.length()>0){
			return true;
		}
		return false;
	}	
	
	public ClaseBean getClase() {
		return clase;
	}
	public void setClase(ClaseBean clase) {
		this.clase = clase;
	}
	public String getValorOmision() {
		return valorOmision;
	}
	public void setValorOmision(String valorOmision) {
		this.valorOmision = valorOmision;
	}
	public String getWebEtiqueta() {
		return webEtiqueta;
	}
	public void setWebEtiqueta(String webEtiqueta) {
		this.webEtiqueta = webEtiqueta;
	}
	
}
