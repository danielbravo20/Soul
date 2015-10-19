package pe.com.soul.cartaFianza.bean;

import java.math.BigDecimal;

import pe.com.soul.core.modelo.BaseBean;

public class Solicitud extends BaseBean{

	private static final long serialVersionUID = 1L;

	private Long codigo;
	private String tipoFianza;
	private BigDecimal montoFianza;
	private String monedaFianza;
	private Cliente cliente;
	
	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	public String getTipoFianza() {
		return tipoFianza;
	}
	public void setTipoFianza(String tipoFianza) {
		this.tipoFianza = tipoFianza;
	}
	public String getMonedaFianza() {
		return monedaFianza;
	}
	public void setMonedaFianza(String monedaFianza) {
		this.monedaFianza = monedaFianza;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public BigDecimal getMontoFianza() {
		return montoFianza;
	}
	public void setMontoFianza(BigDecimal montoFianza) {
		this.montoFianza = montoFianza;
	}
	
}
