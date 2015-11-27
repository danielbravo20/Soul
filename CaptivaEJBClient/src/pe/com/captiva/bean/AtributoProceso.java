package pe.com.captiva.bean;

public class AtributoProceso extends AtributoBean{

	private static final long serialVersionUID = 1L;
	
	private int codigoProyecto;
	private int codigoProceso;
	private int codSubSeccion;
	private int codProcesoInicio;
	
	private String webEtiqueta;
	private String webTipo;
	private Character webTipoCampo;
	private Character webTipoLista;
	private String webCatalogo;
	private boolean flgWebRequerido;
	private String webMensajeValidacion;
	private String valorOmision;
	
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
	public int getCodSubSeccion() {
		return codSubSeccion;
	}
	public void setCodSubSeccion(int codSubSeccion) {
		this.codSubSeccion = codSubSeccion;
	}
	public int getCodProcesoInicio() {
		return codProcesoInicio;
	}
	public void setCodProcesoInicio(int codProcesoInicio) {
		this.codProcesoInicio = codProcesoInicio;
	}
	public String getWebMensajeValidacion() {
		return webMensajeValidacion;
	}
	public void setWebMensajeValidacion(String webMensajeValidacion) {
		this.webMensajeValidacion = webMensajeValidacion;
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
	public String getWebTipo() {
		return webTipo;
	}
	public void setWebTipo(String webTipo) {
		this.webTipo = webTipo;
	}
	public Character getWebTipoCampo() {
		return webTipoCampo;
	}
	public void setWebTipoCampo(Character webTipoCampo) {
		this.webTipoCampo = webTipoCampo;
	}
	public Character getWebTipoLista() {
		return webTipoLista;
	}
	public void setWebTipoLista(Character webTipoLista) {
		this.webTipoLista = webTipoLista;
	}
	public String getWebCatalogo() {
		return webCatalogo;
	}
	public void setWebCatalogo(String webCatalogo) {
		this.webCatalogo = webCatalogo;
	}
	public boolean isFlgWebRequerido() {
		return flgWebRequerido;
	}
	public void setFlgWebRequerido(boolean flgWebRequerido) {
		this.flgWebRequerido = flgWebRequerido;
	}
	
}
