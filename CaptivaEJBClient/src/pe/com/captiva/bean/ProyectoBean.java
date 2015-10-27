package pe.com.captiva.bean;

import java.util.List;

public class ProyectoBean extends BaseBean{

	private static final long serialVersionUID = 1L;
	
	public static final String SUFIJO_PROYECTO_EAR 			= "App";
	public static final String SUFIJO_PROYECTO_WEB 			= "Web";
	public static final String SUFIJO_PROYECTO_SERVICE 		= "Service";
	public static final String SUFIJO_PROYECTO_SERVICELIB 	= "ServiceClient";
	public static final String SUFIJO_SQL 	= "SQL";
	
	private int codProyecto;
	private String nombre;
	private String proyecto;
	private String paquete;
	private EquipoBean equipoBean;
	
	private List<ClaseBean> clases;
	private List<TablaBean> tablas;
	private List<ConsultaBean> consultas;
	private List<ProcesoBean> procesos;
	private List<TareaBean> tareas;
	private List<ProductoBean> productos;
	private List<CatalogoBean> catalogos;
	private ClaseBean clasePadre;
	
	public int getCodProyecto() {
		return codProyecto;
	}
	public void setCodProyecto(int codProyecto) {
		this.codProyecto = codProyecto;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
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
	public List<ClaseBean> getClases() {
		return clases;
	}
	public void setClases(List<ClaseBean> clases) {
		this.clases = clases;
	}
	public EquipoBean getEquipoBean() {
		return equipoBean;
	}
	public void setEquipoBean(EquipoBean equipoBean) {
		this.equipoBean = equipoBean;
	}
	public List<TablaBean> getTablas() {
		return tablas;
	}
	public void setTablas(List<TablaBean> tablas) {
		this.tablas = tablas;
	}
	public List<ConsultaBean> getConsultas() {
		return consultas;
	}
	public void setConsultas(List<ConsultaBean> consultas) {
		this.consultas = consultas;
	}
	public List<ProcesoBean> getProcesos() {
		return procesos;
	}
	public void setProcesos(List<ProcesoBean> procesos) {
		this.procesos = procesos;
	}
	public List<TareaBean> getTareas() {
		return tareas;
	}
	public void setTareas(List<TareaBean> tareas) {
		this.tareas = tareas;
	}
	
	public List<ProductoBean> getProductos() {
		return productos;
	}
	public void setProductos(List<ProductoBean> productos) {
		this.productos = productos;
	}
	public List<CatalogoBean> getCatalogos() {
		return catalogos;
	}
	public void setCatalogos(List<CatalogoBean> catalogos) {
		this.catalogos = catalogos;
	}
	public ClaseBean getClasePadre() {
		return clasePadre;
	}
	public void setClasePadre(ClaseBean clasePadre) {
		this.clasePadre = clasePadre;
	}
			
}
