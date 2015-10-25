package pe.com.captiva.bean;

public class AtributoConsulta extends AtributoBean{

	private boolean flgVisibleWeb;
	private boolean flgCondicion;
	
	public boolean isFlgCondicion() {
		return flgCondicion;
	}

	public void setFlgCondicion(boolean flgCondicion) {
		this.flgCondicion = flgCondicion;
	}

	private CampoSQLConsultaBean campoSQL;

	public boolean isFlgVisibleWeb() {
		return flgVisibleWeb;
	}

	public void setFlgVisibleWeb(boolean flgVisibleWeb) {
		this.flgVisibleWeb = flgVisibleWeb;
	}

	public CampoSQLConsultaBean getCampoSQL() {
		return campoSQL;
	}

	public void setCampoSQL(CampoSQLConsultaBean campoSQL) {
		this.campoSQL = campoSQL;
	}
	
}
