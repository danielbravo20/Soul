package pe.com.captiva.bean;

import java.util.List;

public class SubseccionProceso extends AtributoBean{

	private static final long serialVersionUID = 1L;
	
	private int 		codigoProceso;
	private int 		codigoSubseccion;
	private String 		nombre;
	private List<AtributoProceso> atributoProceso;
	
	public int getCodigoProceso() {
		return codigoProceso;
	}
	public void setCodigoProceso(int codigoProceso) {
		this.codigoProceso = codigoProceso;
	}
	public int getCodigoSubseccion() {
		return codigoSubseccion;
	}
	public void setCodigoSubseccion(int codigoSubseccion) {
		this.codigoSubseccion = codigoSubseccion;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public List<AtributoProceso> getAtributoProceso() {
		return atributoProceso;
	}
	public void setAtributoProceso(List<AtributoProceso> atributoProceso) {
		this.atributoProceso = atributoProceso;
	}
	
}