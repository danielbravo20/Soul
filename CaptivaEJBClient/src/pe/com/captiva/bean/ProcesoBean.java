package pe.com.captiva.bean;

import java.util.List;

public class ProcesoBean {
	
	private int codigo;
	private int codigoProyecto;
	private String proyecto;
	private String paquete;
	private String clase;
	private int actividadInicio;
	private List<AtributoProceso> atributosEntrada;
	private List<SubseccionProceso> subseccionEntrada;
	private List<CampoSQLProcesoBean> camposSQLProceso;
	private String datasource;
	private ConsultaBean consultaResumen;
	private ConsultaBean consultaDetalle;
	private TablaBean tablaResumen;
	private TablaBean tablaDetalle;
	private String nombre;
	private List<TareaBean> tareas;
	private List<AtributoProceso> objetosDOEntreda;
	private List<AtributoProceso> objetosDOsinEntreda;
	private String nombreSecuenciaDocumentos;
	private List<RolBean> rolPotencial;
	private TareaBean tareaInicial;
	private String aleas;
	
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public int getCodigoProyecto() {
		return codigoProyecto;
	}
	public void setCodigoProyecto(int codigoProyecto) {
		this.codigoProyecto = codigoProyecto;
	}
	public String getProyecto() {
		return proyecto;
	}
	public void setProyecto(String proyecto) {
		this.proyecto = proyecto;
	}
	public String getPaquete() {
		return paquete;
	}
	public void setPaquete(String paquete) {
		this.paquete = paquete;
	}
	public String getClase() {
		return clase;
	}
	public void setClase(String clase) {
		this.clase = clase;
	}
	public int getActividadInicio() {
		return actividadInicio;
	}
	public void setActividadInicio(int actividadInicio) {
		this.actividadInicio = actividadInicio;
	}
	public List<AtributoProceso> getAtributosEntrada() {
		return atributosEntrada;
	}
	public void setAtributosEntrada(List<AtributoProceso> atributosEntrada) {
		this.atributosEntrada = atributosEntrada;
	}
	public List<SubseccionProceso> getSubseccionEntrada() {
		return subseccionEntrada;
	}
	public void setSubseccionEntrada(List<SubseccionProceso> subseccionEntrada) {
		this.subseccionEntrada = subseccionEntrada;
	}
	public List<CampoSQLProcesoBean> getCamposSQLProceso() {
		return camposSQLProceso;
	}
	public void setCamposSQLProceso(List<CampoSQLProcesoBean> camposSQLProceso) {
		this.camposSQLProceso = camposSQLProceso;
	}
	public String getDatasource() {
		return datasource;
	}
	public void setDatasource(String datasource) {
		this.datasource = datasource;
	}
	public ConsultaBean getConsultaResumen() {
		return consultaResumen;
	}
	public void setConsultaResumen(ConsultaBean consultaResumen) {
		this.consultaResumen = consultaResumen;
	}
	public ConsultaBean getConsultaDetalle() {
		return consultaDetalle;
	}
	public void setConsultaDetalle(ConsultaBean consultaDetalle) {
		this.consultaDetalle = consultaDetalle;
	}
	public TablaBean getTablaResumen() {
		return tablaResumen;
	}
	public void setTablaResumen(TablaBean tablaResumen) {
		this.tablaResumen = tablaResumen;
	}
	public TablaBean getTablaDetalle() {
		return tablaDetalle;
	}
	public void setTablaDetalle(TablaBean tablaDetalle) {
		this.tablaDetalle = tablaDetalle;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public List<TareaBean> getTareas() {
		return tareas;
	}
	public void setTareas(List<TareaBean> tareas) {
		this.tareas = tareas;
	}
	public List<AtributoProceso> getObjetosDOEntreda() {
		return objetosDOEntreda;
	}
	public void setObjetosDOEntreda(List<AtributoProceso> objetosDOEntreda) {
		this.objetosDOEntreda = objetosDOEntreda;
	}
	public List<AtributoProceso> getObjetosDOsinEntreda() {
		return objetosDOsinEntreda;
	}
	public void setObjetosDOsinEntreda(List<AtributoProceso> objetosDOsinEntreda) {
		this.objetosDOsinEntreda = objetosDOsinEntreda;
	}
	public String getNombreSecuenciaDocumentos() {
		return nombreSecuenciaDocumentos;
	}
	public void setNombreSecuenciaDocumentos(String nombreSecuenciaDocumentos) {
		this.nombreSecuenciaDocumentos = nombreSecuenciaDocumentos;
	}
	public List<RolBean> getRolPotencial() {
		return rolPotencial;
	}
	public void setRolPotencial(List<RolBean> rolPotencial) {
		this.rolPotencial = rolPotencial;
	}
	public TareaBean getTareaInicial() {
		return tareaInicial;
	}
	public void setTareaInicial(TareaBean tareaInicial) {
		this.tareaInicial = tareaInicial;
	}
	public String getAleas() {
		return aleas;
	}
	public void setAleas(String aleas) {
		this.aleas = aleas;
	}

}
