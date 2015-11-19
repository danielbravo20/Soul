package pe.com.captiva.bean;

import java.util.List;

public class ConsultaBean {

	private int codigo;
	private String aleas;
	private String paquete;
	private String interfase;
	private TablaConsultaBean tablaPadre;
	private List<TablaConsultaBean> tablasFK;
	public List<AtributoBean> AtributosBean;
	
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getAleas() {
		return aleas;
	}
	public void setAleas(String aleas) {
		this.aleas = aleas;
	}
	public String getPaquete() {
		return paquete;
	}
	public void setPaquete(String paquete) {
		this.paquete = paquete;
	}
	public String getInterfase() {
		return interfase;
	}
	public void setInterfase(String interfase) {
		this.interfase = interfase;
	}
	public TablaConsultaBean getTablaPadre() {
		return tablaPadre;
	}
	public void setTablaPadre(TablaConsultaBean tablaPadre) {
		this.tablaPadre = tablaPadre;
	}
	public List<TablaConsultaBean> getTablasFK() {
		return tablasFK;
	}
	public void setTablasFK(List<TablaConsultaBean> tablasFK) {
		this.tablasFK = tablasFK;
	}
	public List<AtributoBean> getAtributosBean() {
		return AtributosBean;
	}
	public void setAtributosBean(List<AtributoBean> atributosBean) {
		AtributosBean = atributosBean;
	}
	
}
