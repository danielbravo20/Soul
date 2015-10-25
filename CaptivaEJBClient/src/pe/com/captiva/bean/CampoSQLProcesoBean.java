package pe.com.captiva.bean;

public class CampoSQLProcesoBean extends CampoSQLBean{

	private CampoSQLProcesoBean	fk;
	private boolean flgAutogenerar;
	private String nombreSecuencial;
	private AtributoProceso atributoProceso;
	
	public boolean isFlgAutogenerar() {
		return flgAutogenerar;
	}
	public void setFlgAutogenerar(boolean flgAutogenerar) {
		this.flgAutogenerar = flgAutogenerar;
	}
	public String getNombreSecuencial() {
		return nombreSecuencial;
	}
	public void setNombreSecuencial(String nombreSecuencial) {
		this.nombreSecuencial = nombreSecuencial;
	}
	public AtributoProceso getAtributoProceso() {
		return atributoProceso;
	}
	public void setAtributoProceso(AtributoProceso atributoProceso) {
		this.atributoProceso = atributoProceso;
	}
	public CampoSQLProcesoBean getFk() {
		return fk;
	}
	public void setFk(CampoSQLProcesoBean fk) {
		this.fk = fk;
	}
	
}
