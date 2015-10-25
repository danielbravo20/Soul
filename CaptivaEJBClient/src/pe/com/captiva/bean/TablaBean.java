package pe.com.captiva.bean;

import java.util.List;

public class TablaBean {
	
	private int codigo;
	private int codigoProyecto;
	private String esquema;
	private String nombre;
	private List<CampoSQLBean> camposSQL;
	private List<CampoSQLBean> camposPK;
	private List<CampoSQLBean> camposFK;
	
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
	public String getEsquema() {
		return esquema;
	}
	public void setEsquema(String esquema) {
		this.esquema = esquema;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public List<CampoSQLBean> getCamposSQL() {
		return camposSQL;
	}
	public void setCamposSQL(List<CampoSQLBean> camposSQL) {
		this.camposSQL = camposSQL;
	}
	public List<CampoSQLBean> getCamposPK() {
		return camposPK;
	}
	public void setCamposPK(List<CampoSQLBean> camposPK) {
		this.camposPK = camposPK;
	}
	public List<CampoSQLBean> getCamposFK() {
		return camposFK;
	}
	public void setCamposFK(List<CampoSQLBean> camposFK) {
		this.camposFK = camposFK;
	}
	
}
