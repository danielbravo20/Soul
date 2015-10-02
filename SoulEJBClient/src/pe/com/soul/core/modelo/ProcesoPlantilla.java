package pe.com.soul.core.modelo;

import java.util.Date;

public class ProcesoPlantilla {

	private long codigoProcesoPlantilla;
	private String version;
	private char estado;
	private String nombre;
	private String aleas;
	private Date fechaValidez;
	private boolean flagTodosInician;
	public long getCodigoProcesoPlantilla() {
		return codigoProcesoPlantilla;
	}
	public void setCodigoProcesoPlantilla(long codigoProcesoPlantilla) {
		this.codigoProcesoPlantilla = codigoProcesoPlantilla;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public char getEstado() {
		return estado;
	}
	public void setEstado(char estado) {
		this.estado = estado;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getAleas() {
		return aleas;
	}
	public void setAleas(String aleas) {
		this.aleas = aleas;
	}
	public Date getFechaValidez() {
		return fechaValidez;
	}
	public void setFechaValidez(Date fechaValidez) {
		this.fechaValidez = fechaValidez;
	}
	public boolean isFlagTodosInician() {
		return flagTodosInician;
	}
	public void setFlagTodosInician(boolean flagTodosInician) {
		this.flagTodosInician = flagTodosInician;
	}

	
	
}
