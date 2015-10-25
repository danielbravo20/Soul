package pe.com.captiva.bean;

import java.util.List;

public class Proyecto {
	private int codigoProyecto;
	private String nombre;
	private String javaNombreProyectoLibreria;
	private String javaNombreProyectoEJB;
	private String javaNombreProyectoEJBExtension;
	private String javaNombreProyectoEJBCliente;
	private String javaNombreProyectoWeb;
	private String javaNombrePaquete;
	private String javaNombrePaqueteControlador;
	private String javaPreSufijoControlador;
	private List<TablaBean> tablas;
	private List<ConsultaBean> consultas;
	private List<ProcesoBean> procesos;
	private List<TareaBean> tareas;
	private List<ProductoBean> productos;
	private List<CatalogoBean> catalogos;
	
	public int getCodigoProyecto() {
		return codigoProyecto;
	}
	public void setCodigoProyecto(int codigoProyecto) {
		this.codigoProyecto = codigoProyecto;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getJavaNombreProyectoLibreria() {
		return javaNombreProyectoLibreria;
	}
	public void setJavaNombreProyectoLibreria(String javaNombreProyectoLibreria) {
		this.javaNombreProyectoLibreria = javaNombreProyectoLibreria;
	}
	public String getJavaNombreProyectoEJB() {
		return javaNombreProyectoEJB;
	}
	public void setJavaNombreProyectoEJB(String javaNombreProyectoEJB) {
		this.javaNombreProyectoEJB = javaNombreProyectoEJB;
	}
	public String getJavaNombreProyectoEJBCliente() {
		return javaNombreProyectoEJBCliente;
	}
	public void setJavaNombreProyectoEJBCliente(String javaNombreProyectoEJBCliente) {
		this.javaNombreProyectoEJBCliente = javaNombreProyectoEJBCliente;
	}
	public String getJavaNombreProyectoWeb() {
		return javaNombreProyectoWeb;
	}
	public void setJavaNombreProyectoWeb(String javaNombreProyectoWeb) {
		this.javaNombreProyectoWeb = javaNombreProyectoWeb;
	}
	public String getJavaNombrePaquete() {
		return javaNombrePaquete;
	}
	public void setJavaNombrePaquete(String javaNombrePaquete) {
		this.javaNombrePaquete = javaNombrePaquete;
	}
	public String getJavaNombrePaqueteControlador() {
		return javaNombrePaqueteControlador;
	}
	public void setJavaNombrePaqueteControlador(String javaNombrePaqueteControlador) {
		this.javaNombrePaqueteControlador = javaNombrePaqueteControlador;
	}
	public String getJavaPreSufijoControlador() {
		return javaPreSufijoControlador;
	}
	public void setJavaPreSufijoControlador(String javaPreSufijoControlador) {
		this.javaPreSufijoControlador = javaPreSufijoControlador;
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
	public String getJavaNombreProyectoEJBExtension() {
		return javaNombreProyectoEJBExtension;
	}
	public void setJavaNombreProyectoEJBExtension(
			String javaNombreProyectoEJBExtension) {
		this.javaNombreProyectoEJBExtension = javaNombreProyectoEJBExtension;
	}

}
