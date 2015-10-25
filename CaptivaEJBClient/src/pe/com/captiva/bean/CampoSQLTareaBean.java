package pe.com.captiva.bean;

public class CampoSQLTareaBean extends CampoSQLBean{

	private boolean flgRequiereEliminar;
	private boolean flgListado;
	private CampoSQLTareaBean fk;
	private AtributoTarea atributoTarea;
	
	public boolean isFlgRequiereEliminar() {
		return flgRequiereEliminar;
	}

	public void setFlgRequiereEliminar(boolean flgRequiereEliminar) {
		this.flgRequiereEliminar = flgRequiereEliminar;
	}

	public boolean isFlgListado() {
		return flgListado;
	}

	public void setFlgListado(boolean flgListado) {
		this.flgListado = flgListado;
	}

	public CampoSQLTareaBean getFk() {
		return fk;
	}

	public void setFk(CampoSQLTareaBean fk) {
		this.fk = fk;
	}

	public AtributoTarea getAtributoTarea() {
		return atributoTarea;
	}

	public void setAtributoTarea(AtributoTarea atributoTarea) {
		this.atributoTarea = atributoTarea;
	}
	
}
