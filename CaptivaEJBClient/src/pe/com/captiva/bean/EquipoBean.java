package pe.com.captiva.bean;

public class EquipoBean extends BaseBean{

	private static final long serialVersionUID = 1L;

	private int codigoProyecto;
	private String usuario;
	private String directorioWorkspace;
	private String directorioParcial;
	public int getCodigoProyecto() {
		return codigoProyecto;
	}
	public void setCodigoProyecto(int codigoProyecto) {
		this.codigoProyecto = codigoProyecto;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getDirectorioWorkspace() {
		return directorioWorkspace;
	}
	public void setDirectorioWorkspace(String directorioWorkspace) {
		this.directorioWorkspace = directorioWorkspace;
	}
	public String getDirectorioParcial() {
		return directorioParcial;
	}
	public void setDirectorioParcial(String directorioParcial) {
		this.directorioParcial = directorioParcial;
	}
	
	
}
