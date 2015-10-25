package pe.com.captiva.bean;

import java.util.List;

public class TablaConsultaBean extends TablaBean{

	private int codigoConsulta;
	public boolean flgFK;
	public boolean flgUnoMuchos;
	public List<CampoSQLConsultaBean> campoSQLConsultas;
	public List<AtributoConsulta> atributosConsulta;
	
	public boolean isFlgFK() {
		return flgFK;
	}
	public void setFlgFK(boolean flgFK) {
		this.flgFK = flgFK;
	}
	public boolean isFlgUnoMuchos() {
		return flgUnoMuchos;
	}
	public void setFlgUnoMuchos(boolean flgUnoMuchos) {
		this.flgUnoMuchos = flgUnoMuchos;
	}
	public List<CampoSQLConsultaBean> getCampoSQLConsultas() {
		return campoSQLConsultas;
	}
	public void setCampoSQLConsultas(List<CampoSQLConsultaBean> campoSQLConsultas) {
		this.campoSQLConsultas = campoSQLConsultas;
	}
	public void setCodigoConsulta(int codigoConsulta) {
		this.codigoConsulta = codigoConsulta;
	}
	public int getCodigoConsulta() {
		return codigoConsulta;
	}
	public List<AtributoConsulta> getAtributosConsulta() {
		return atributosConsulta;
	}
	public void setAtributosConsulta(List<AtributoConsulta> atributosConsulta) {
		this.atributosConsulta = atributosConsulta;
	}
	
}
