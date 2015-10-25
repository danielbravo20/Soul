package pe.com.captiva.servicio.util;

public class Componente {

	private String directorio;
	private String archivo;
	private StringBuffer contenido;
	
	public String getDirectorio() {
		return directorio;
	}
	public void setDirectorio(String directorio) {
		this.directorio = directorio;
	}
	public String getArchivo() {
		return archivo;
	}
	public void setArchivo(String archivo) {
		this.archivo = archivo;
	}
	public StringBuffer getContenido() {
		return contenido;
	}
	public void setContenido(StringBuffer contenido) {
		this.contenido = contenido;
	}
	
}
