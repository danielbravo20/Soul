package pe.com.captiva.bean;

import java.util.List;

public class ProyectoBean extends BaseBean{

	private static final long serialVersionUID = 1L;
	
	public static final String SUFIJO_PROYECTO_EAR 			= "App";
	public static final String SUFIJO_PROYECTO_WEB 			= "Web";
	public static final String SUFIJO_PROYECTO_SERVICE 		= "Service";
	public static final String SUFIJO_PROYECTO_SERVICELIB 	= "ServiceClient";
	
	private int codProyecto;
	private String nombre;
	private String proyecto;
	private String paquete;
	private EquipoBean equipoBean;
	
	private List<ClaseBean> clases;
	
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
			
}
