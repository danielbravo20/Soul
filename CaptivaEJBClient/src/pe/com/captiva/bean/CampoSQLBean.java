package pe.com.captiva.bean;

public class CampoSQLBean {
	
	private int 		codigo;
	private int 		codigoAtributo;
	private int 		codigoTabla;
	private TablaBean 		tabla;
	private String 		nombre;
	private String 		tipo;
	private int 		longitud;
	private int 		precision;
	private boolean		flgPK;
	private boolean		flgObligatorio;
	private CampoSQLBean	fk;
	private String		funcionBusqueda;
	private String 		funcionBusquedaCatalogo;
	private String 		valorDefecto;
	private boolean     fkUnoMuchos;
	private String		sequence;
	
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public int getCodigoAtributo() {
		return codigoAtributo;
	}
	public void setCodigoAtributo(int codigoAtributo) {
		this.codigoAtributo = codigoAtributo;
	}
	public int getCodigoTabla() {
		return codigoTabla;
	}
	public void setCodigoTabla(int codigoTabla) {
		this.codigoTabla = codigoTabla;
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
	public int getLongitud() {
		return longitud;
	}
	public void setLongitud(int longitud) {
		this.longitud = longitud;
	}
	public int getPrecision() {
		return precision;
	}
	public void setPrecision(int precision) {
		this.precision = precision;
	}
	public boolean isFlgPK() {
		return flgPK;
	}
	public void setFlgPK(boolean flgPK) {
		this.flgPK = flgPK;
	}
	public boolean isFlgObligatorio() {
		return flgObligatorio;
	}
	public void setFlgObligatorio(boolean flgObligatorio) {
		this.flgObligatorio = flgObligatorio;
	}
	public CampoSQLBean getFk() {
		return fk;
	}
	public void setFk(CampoSQLBean fk) {
		this.fk = fk;
	}
	public TablaBean getTabla() {
		return tabla;
	}
	public void setTabla(TablaBean tabla) {
		this.tabla = tabla;
	}
	public String getFuncionBusqueda() {
		return funcionBusqueda;
	}
	public void setFuncionBusqueda(String funcionBusqueda) {
		this.funcionBusqueda = funcionBusqueda;
	}
	public String getFuncionBusquedaCatalogo() {
		return funcionBusquedaCatalogo;
	}
	public void setFuncionBusquedaCatalogo(String funcionBusquedaCatalogo) {
		this.funcionBusquedaCatalogo = funcionBusquedaCatalogo;
	}
	public String getValorDefecto() {
		return valorDefecto;
	}
	public void setValorDefecto(String valorDefecto) {
		this.valorDefecto = valorDefecto;
	}
	public boolean isTieneFuncion(){
		if(this.funcionBusqueda!=null && this.funcionBusqueda.trim().length()>0){
			return true;
		}
		return false;
	}
	public void setFkUnoMuchos(boolean fkUnoMuchos) {
		this.fkUnoMuchos = fkUnoMuchos;
	}
	public boolean isFkUnoMuchos() {
		return fkUnoMuchos;
	}
	
	public boolean isFlgTieneFK(){
		if(getFk()!=null && getFk().getCodigo()>0){
			return true;
		}
		return false;
	}
	public String getSequence() {
		return sequence;
	}
	
	public void setSequence(String sequence) {
		this.sequence = sequence;
	}
	
	public boolean isSequence(){
		if(getSequence()!=null && getSequence().trim().length()>0){
			return true;
		}
		return false;
	}
}
