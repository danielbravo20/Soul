package pe.com.cartaFianza.dao.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "solicitud", schema = "bfp_carta_fianza")
public class SolicitudEntity implements Serializable{

	private static final long serialVersionUID = 1L;

	private java.sql.Date vigencia;
	private String beneficiario;
	private java.math.BigDecimal monto;
	private Long codigoSolicitud;
	private String estado;

	@Column(name = "vigencia" ,nullable = true )
	public java.sql.Date getVigencia(){
		return vigencia;
	}

	public void setVigencia(java.sql.Date vigencia) {
		this.vigencia = vigencia;
	}

	@Column(name = "beneficiario" ,nullable = true ,length = 50 )
	public String getBeneficiario(){
		return beneficiario;
	}

	public void setBeneficiario(String beneficiario) {
		this.beneficiario = beneficiario;
	}

	@Column(name = "monto" ,nullable = true )
	public java.math.BigDecimal getMonto(){
		return monto;
	}

	public void setMonto(java.math.BigDecimal monto) {
		this.monto = monto;
	}

	@Id
	@Column(name = "cod_solicitud" ,unique = true ,nullable = false )
	public Long getCodigoSolicitud(){
		return codigoSolicitud;
	}

	public void setCodigoSolicitud(Long codigoSolicitud) {
		this.codigoSolicitud = codigoSolicitud;
	}

	@Column(name = "estado" ,nullable = true ,length = 3 )
	public String getEstado(){
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

}