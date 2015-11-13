package pe.com.cartaFianza.dao.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.GeneratedValue;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "solicitud", schema = "bfp_carta_fianza")
public class SolicitudEntity implements Serializable{

	private static final long serialVersionUID = 1L;

	private java.math.BigDecimal monto;
	private java.util.Date vigencia;
	private String evento;
	private Long codigoSolicitud;

	@Column(name = "monto" ,nullable = true ,precision = 12, scale = 3 )
	public java.math.BigDecimal getMonto(){
		return monto;
	}

	public void setMonto(java.math.BigDecimal monto) {
		this.monto = monto;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "vigencia" ,nullable = true ,length = 13 )
	public java.util.Date getVigencia(){
		return vigencia;
	}

	public void setVigencia(java.util.Date vigencia) {
		this.vigencia = vigencia;
	}

	@Column(name = "evento" ,nullable = true ,length = 3 )
	public String getEvento(){
		return evento;
	}

	public void setEvento(String evento) {
		this.evento = evento;
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

}