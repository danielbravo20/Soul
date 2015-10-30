package pe.com.captiva.bean;

public class AtributoBean extends BaseBean{

	private static final long serialVersionUID = 1L;

	private int 	codigo;
	private String 	nombre;
	private String 	tipo;
	private boolean	flgLista;
	private String 	webNombre;
	private CampoSQLBean campoSQLBean;
	
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public boolean isFlgLista() {
		return flgLista;
	}
	public void setFlgLista(boolean flgLista) {
		this.flgLista = flgLista;
	}
	public String getWebNombre() {
		return webNombre;
	}
	public void setWebNombre(String webNombre) {
		this.webNombre = webNombre;
	}
	public CampoSQLBean getCampoSQLBean() {
		return campoSQLBean;
	}
	public void setCampoSQLBean(CampoSQLBean campoSQLBean) {
		this.campoSQLBean = campoSQLBean;
	}
	public boolean isCampoSQLBean(){
		if(getCampoSQLBean()!=null){
			return true;
		}
		return false;
	}
	
}
