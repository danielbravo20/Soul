package pe.com.cartaFianza.dao.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Table;

@Entity
@Table(name = "solicitud", schema = "bfp_carta_fianza")
public class SolicitudEntity implements Serializable{

	private static final long serialVersionUID = 1L;

	private Long codigoProceso;
	private Long valoNumerico;
	private Long codigoSolicitud;
	private long valorNumericoB;
	private String evento;
	private boolean flagAdicional;
	private java.util.Date fechaFin;
	private Integer valorNumericoC;
	private boolean flagPrincipal;
	private java.math.BigDecimal monto;
	private String descripcion;
	private java.math.BigDecimal montoFinal;
	private java.util.Date vigencia;

	@Column(name = "codigo_proceso" ,unique = true ,nullable = false )
	public Long getCodigoProceso() {
		return codigoProceso;
	}
	public void setCodigoProceso(Long codigoProceso) {
		this.codigoProceso = codigoProceso;
	}

	@Column(name = "VALOR_NUMERO_1" ,nullable = true )
	public Long getValoNumerico(){
		return valoNumerico;
	}

	public void setValoNumerico(Long valoNumerico) {
		this.valoNumerico = valoNumerico;
	}

	@Id
	@GeneratedValue(generator="id_gen_bfp_carta_fianza_seq_cod_solicitud")
	@SequenceGenerator(name="id_gen_bfp_carta_fianza_seq_cod_solicitud",sequenceName="bfp_carta_fianza.seq_cod_solicitud", allocationSize=0)
	@Column(name = "cod_solicitud" ,unique = true ,nullable = false )
	public Long getCodigoSolicitud(){
		return codigoSolicitud;
	}

	public void setCodigoSolicitud(Long codigoSolicitud) {
		this.codigoSolicitud = codigoSolicitud;
	}

	@Column(name = "VALOR_NUMERICO_2" ,nullable = true )
	public long getValorNumericoB(){
		return valorNumericoB;
	}

	public void setValorNumericoB(long valorNumericoB) {
		this.valorNumericoB = valorNumericoB;
	}

	@Column(name = "evento" ,nullable = true ,length = 3 )
	public String getEvento(){
		return evento;
	}

	public void setEvento(String evento) {
		this.evento = evento;
	}

	@Column(name = "FLAG_ADICIONAL" ,nullable = true )
	public boolean isFlagAdicional(){
		return flagAdicional;
	}

	public void setFlagAdicional(boolean flagAdicional) {
		this.flagAdicional = flagAdicional;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "FECHA_FIN" ,nullable = true ,length = 13 )
	public java.util.Date getFechaFin(){
		return fechaFin;
	}

	public void setFechaFin(java.util.Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	@Column(name = "VALOR_NUMERICO_3" ,nullable = true )
	public Integer getValorNumericoC(){
		return valorNumericoC;
	}

	public void setValorNumericoC(Integer valorNumericoC) {
		this.valorNumericoC = valorNumericoC;
	}

	@Column(name = "FLAG_PRINCIPAL" ,nullable = true )
	public boolean isFlagPrincipal(){
		return flagPrincipal;
	}

	public void setFlagPrincipal(boolean flagPrincipal) {
		this.flagPrincipal = flagPrincipal;
	}

	@Column(name = "monto" ,nullable = true ,precision = 12, scale = 3 )
	public java.math.BigDecimal getMonto(){
		return monto;
	}

	public void setMonto(java.math.BigDecimal monto) {
		this.monto = monto;
	}

	@Column(name = "DESCRIPCION" ,nullable = true ,length = 0 )
	public String getDescripcion(){
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Column(name = "MONTO_FINAL" ,nullable = true ,precision = 0, scale = 0 )
	public java.math.BigDecimal getMontoFinal(){
		return montoFinal;
	}

	public void setMontoFinal(java.math.BigDecimal montoFinal) {
		this.montoFinal = montoFinal;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "VIGENCIA" ,nullable = true ,length = 13 )
	public java.util.Date getVigencia(){
		return vigencia;
	}

	public void setVigencia(java.util.Date vigencia) {
		this.vigencia = vigencia;
	}

}