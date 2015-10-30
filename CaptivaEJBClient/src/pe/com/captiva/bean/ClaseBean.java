package pe.com.captiva.bean;

import java.util.List;

public class ClaseBean extends BaseBean{

	private static final long serialVersionUID = 1L;
	
	public static final String SUFIJO_PAQUETE = ".bean";

	private int codigoClase;
	private String nombre;
	private Integer nivel;
	private List<AtributoBean> atributos;
	private TablaBean tablaBean;
	
	public int getCodigoClase() {
		return codigoClase;
	}
	public void setCodigoClase(int codigoClase) {
		this.codigoClase = codigoClase;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Integer getNivel() {
		return nivel;
	}
	public void setNivel(Integer nivel) {
		this.nivel = nivel;
	}
	public List<AtributoBean> getAtributos() {
		return atributos;
	}
	public void setAtributos(List<AtributoBean> atributos) {
		this.atributos = atributos;
	}
	public TablaBean getTablaBean() {
		return tablaBean;
	}
	public void setTablaBean(TablaBean tablaBean) {
		this.tablaBean = tablaBean;
	}
	
	public boolean isTablaBean() {
		if(getTablaBean()!=null){
			return true;
		}
		return false;
	}
}
