package pe.com.cartaFianza.bean;

import java.util.List;

import java.io.Serializable;

public class Solicitud implements Serializable{
 
	private static final long serialVersionUID = 1L;

	private String descripcionCodigoLugarEnvio;
	private boolean comisionEsReducida;
	private java.sql.Date fechaVigencia;
	private boolean flagCambioModalidadCobro;
	private String codigoPostalBeneficiario;
	private java.sql.Timestamp fechaModificacion;
	private boolean flagCambioCuenta;
	private boolean flagEdicionComision;
	private String descripcionCodigoPostalBeneficiario;
	private String piid;
	private java.math.BigDecimal montoIncremento;
	private Cuenta cuenta;
	private String descripcionAgenciaOrigen;
	private String descripcionCodigoAgenciaRecojo;
	private String usuarioModificacion;
	private java.math.BigDecimal montoTramite;
	private String tipoRiesgo;
	private Linea linea;
	private boolean flagCambioBeneficiario;
	private Integer plazoVigencia;
	private String funcionario;
	private String descripcionTipoSolicitud;
	private String flagBloqueo;
	private boolean flagCambioDeudorGarantizado;
	private boolean flagCambioTipoRiesgo;
	private String nombrePersonaAutorizada;
	private String codigoTipoFirma;
	private String direccionBeneficiario;
	private String usuarioFuncionario;
	private boolean flagTipoTextoEspecial;
	private String tipoCobranza;
	private List<Garantia> garantias;
	private Plantilla plantilla;
	private String codigoFuncionario;
	private boolean flagCambioPlantilla;
	private boolean flgConformePersonaRecojo;
	private List<Plantilla> plantillas;
	private java.sql.Date fechaCambioEstado;
	private boolean flagCambioFechaProximoCobro;
	private String productoIBS;
	private String ubigeoBeneficiario;
	private String codigoMoneda;
	private List<Firmante> firmantes;
	private String descripcionEstado;
	private String gerenteGrupo;
	private String descripcionTipoRequerimiento;
	private String codigoLugarEnvio;
	private boolean flagCambioFechaEmision;
	private long numeroCartaFianza;
	private boolean tieneNuevaObservacion;
	private List<Cuenta> cuentas;
	private java.math.BigDecimal comisionBase;
	private boolean flagCambioTipoFirma;
	private List<ListaVerificacion> listaVerificacions;
	private String cuentaContable;
	private boolean flagTextoEspecial;
	private String subProductoIBS;
	private Cliente cliente;
	private java.math.BigDecimal comisionPizarra;
	private String asistente;
	private String numeroDocumentoPersonaAutorizada;
	private String usuarioAutorizador;
	private Integer totalDias;
	private java.sql.Timestamp fechaRegistro;
	private java.sql.Date fechaProximoCobroRequerimiento;
	private boolean flagCambioConcepto;
	private boolean flagContratoMarco;
	private String numeroValorados;
	private Integer codigoPlantilla;
	private String codigoAgenciaRecojo;
	private boolean flagPagare;
	private java.math.BigDecimal comisionPactada;
	private ContratoMarco contratoMarco;
	private String nombreArchivoContratoMarco;
	private String nombreBeneficiario;
	private String codigoVendedor;
	private String textoEspecialSimple;
	private String aleasProceso;
	private long numeroValorado;
	private String estado;
	private String usuarioAsistente;
	private String descripcionTipoCancelacion;
	private String descripcionTipoFianza;
	private List<Riesgo> riesgos;
	private CartaFianza cartaFianza;
	private Long codigo;
	private boolean flagCambioTipoFianza;
	private boolean flagCambioTextoEspecial;
	private String descripcionTipoCobranza;
	private String deudorGarantizado;
	private String tipoSolicitud;
	private String codigoAgenciaOrigen;
	private String descripcionTipoBloqueo;
	private String descripcionCodigoMoneda;
	private java.math.BigDecimal excedeRMC;
	private boolean flagErrorServicioFirmas;
	private boolean flagCambioTipoCobranza;
	private String tipoLugar;
	private java.math.BigDecimal tipoCambioSBS;
	private java.sql.Date fechaVencimiento;
	private List<Funcionario> funcionarios;
	private boolean flgRevisarFirmasPoderes;
	private java.math.BigDecimal monto;
	private String usuarioGerenteGrupo;
	private Liquidacion liquidacion;
	private boolean flagRequiereFirmaFisica;
	private String codigoTipoCancelacion;
	private String descripcionFlagBloqueo;
	private String modalidadCobro;
	private String usuarioRegistro;
	private boolean flagCambioMonto;
	private String conceptoGarantia;
	private String descripcionModalidadCobro;
	private java.sql.Date fechaEmision;
	private String tipoBloqueo;
	private String codigoTarifario;
	private java.math.BigDecimal montoDecremento;
	private String descripcionTipoRiesgo;
	private String descripcionTipoFirma;
	private String codigoTipoFianza;
	private List<Comision> comisions;
	private java.math.BigDecimal tipoCambioBE;
	private String tipoRequerimiento;
	private String tipoDocumentoPersonaAutorizada;
	private String motivo;

	public String getDescripcionCodigoLugarEnvio(){
		return descripcionCodigoLugarEnvio;
	}

	public void setDescripcionCodigoLugarEnvio(String descripcionCodigoLugarEnvio) {
		this.descripcionCodigoLugarEnvio = descripcionCodigoLugarEnvio;
	}

	public boolean isComisionEsReducida(){
		return comisionEsReducida;
	}

	public void setComisionEsReducida(boolean comisionEsReducida) {
		this.comisionEsReducida = comisionEsReducida;
	}

	public java.sql.Date getFechaVigencia(){
		return fechaVigencia;
	}

	public void setFechaVigencia(java.sql.Date fechaVigencia) {
		this.fechaVigencia = fechaVigencia;
	}

	public boolean isFlagCambioModalidadCobro(){
		return flagCambioModalidadCobro;
	}

	public void setFlagCambioModalidadCobro(boolean flagCambioModalidadCobro) {
		this.flagCambioModalidadCobro = flagCambioModalidadCobro;
	}

	public String getCodigoPostalBeneficiario(){
		return codigoPostalBeneficiario;
	}

	public void setCodigoPostalBeneficiario(String codigoPostalBeneficiario) {
		this.codigoPostalBeneficiario = codigoPostalBeneficiario;
	}

	public java.sql.Timestamp getFechaModificacion(){
		return fechaModificacion;
	}

	public void setFechaModificacion(java.sql.Timestamp fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}

	public boolean isFlagCambioCuenta(){
		return flagCambioCuenta;
	}

	public void setFlagCambioCuenta(boolean flagCambioCuenta) {
		this.flagCambioCuenta = flagCambioCuenta;
	}

	public boolean isFlagEdicionComision(){
		return flagEdicionComision;
	}

	public void setFlagEdicionComision(boolean flagEdicionComision) {
		this.flagEdicionComision = flagEdicionComision;
	}

	public String getDescripcionCodigoPostalBeneficiario(){
		return descripcionCodigoPostalBeneficiario;
	}

	public void setDescripcionCodigoPostalBeneficiario(String descripcionCodigoPostalBeneficiario) {
		this.descripcionCodigoPostalBeneficiario = descripcionCodigoPostalBeneficiario;
	}

	public String getPiid(){
		return piid;
	}

	public void setPiid(String piid) {
		this.piid = piid;
	}

	public java.math.BigDecimal getMontoIncremento(){
		return montoIncremento;
	}

	public void setMontoIncremento(java.math.BigDecimal montoIncremento) {
		this.montoIncremento = montoIncremento;
	}

	public Cuenta getCuenta(){
		return cuenta;
	}

	public void setCuenta(Cuenta cuenta) {
		this.cuenta = cuenta;
	}

	public String getDescripcionAgenciaOrigen(){
		return descripcionAgenciaOrigen;
	}

	public void setDescripcionAgenciaOrigen(String descripcionAgenciaOrigen) {
		this.descripcionAgenciaOrigen = descripcionAgenciaOrigen;
	}

	public String getDescripcionCodigoAgenciaRecojo(){
		return descripcionCodigoAgenciaRecojo;
	}

	public void setDescripcionCodigoAgenciaRecojo(String descripcionCodigoAgenciaRecojo) {
		this.descripcionCodigoAgenciaRecojo = descripcionCodigoAgenciaRecojo;
	}

	public String getUsuarioModificacion(){
		return usuarioModificacion;
	}

	public void setUsuarioModificacion(String usuarioModificacion) {
		this.usuarioModificacion = usuarioModificacion;
	}

	public java.math.BigDecimal getMontoTramite(){
		return montoTramite;
	}

	public void setMontoTramite(java.math.BigDecimal montoTramite) {
		this.montoTramite = montoTramite;
	}

	public String getTipoRiesgo(){
		return tipoRiesgo;
	}

	public void setTipoRiesgo(String tipoRiesgo) {
		this.tipoRiesgo = tipoRiesgo;
	}

	public Linea getLinea(){
		return linea;
	}

	public void setLinea(Linea linea) {
		this.linea = linea;
	}

	public boolean isFlagCambioBeneficiario(){
		return flagCambioBeneficiario;
	}

	public void setFlagCambioBeneficiario(boolean flagCambioBeneficiario) {
		this.flagCambioBeneficiario = flagCambioBeneficiario;
	}

	public Integer getPlazoVigencia(){
		return plazoVigencia;
	}

	public void setPlazoVigencia(Integer plazoVigencia) {
		this.plazoVigencia = plazoVigencia;
	}

	public String getFuncionario(){
		return funcionario;
	}

	public void setFuncionario(String funcionario) {
		this.funcionario = funcionario;
	}

	public String getDescripcionTipoSolicitud(){
		return descripcionTipoSolicitud;
	}

	public void setDescripcionTipoSolicitud(String descripcionTipoSolicitud) {
		this.descripcionTipoSolicitud = descripcionTipoSolicitud;
	}

	public String getFlagBloqueo(){
		return flagBloqueo;
	}

	public void setFlagBloqueo(String flagBloqueo) {
		this.flagBloqueo = flagBloqueo;
	}

	public boolean isFlagCambioDeudorGarantizado(){
		return flagCambioDeudorGarantizado;
	}

	public void setFlagCambioDeudorGarantizado(boolean flagCambioDeudorGarantizado) {
		this.flagCambioDeudorGarantizado = flagCambioDeudorGarantizado;
	}

	public boolean isFlagCambioTipoRiesgo(){
		return flagCambioTipoRiesgo;
	}

	public void setFlagCambioTipoRiesgo(boolean flagCambioTipoRiesgo) {
		this.flagCambioTipoRiesgo = flagCambioTipoRiesgo;
	}

	public String getNombrePersonaAutorizada(){
		return nombrePersonaAutorizada;
	}

	public void setNombrePersonaAutorizada(String nombrePersonaAutorizada) {
		this.nombrePersonaAutorizada = nombrePersonaAutorizada;
	}

	public String getCodigoTipoFirma(){
		return codigoTipoFirma;
	}

	public void setCodigoTipoFirma(String codigoTipoFirma) {
		this.codigoTipoFirma = codigoTipoFirma;
	}

	public String getDireccionBeneficiario(){
		return direccionBeneficiario;
	}

	public void setDireccionBeneficiario(String direccionBeneficiario) {
		this.direccionBeneficiario = direccionBeneficiario;
	}

	public String getUsuarioFuncionario(){
		return usuarioFuncionario;
	}

	public void setUsuarioFuncionario(String usuarioFuncionario) {
		this.usuarioFuncionario = usuarioFuncionario;
	}

	public boolean isFlagTipoTextoEspecial(){
		return flagTipoTextoEspecial;
	}

	public void setFlagTipoTextoEspecial(boolean flagTipoTextoEspecial) {
		this.flagTipoTextoEspecial = flagTipoTextoEspecial;
	}

	public String getTipoCobranza(){
		return tipoCobranza;
	}

	public void setTipoCobranza(String tipoCobranza) {
		this.tipoCobranza = tipoCobranza;
	}

	public List<Garantia> getGarantias(){
		return garantias;
	}

	public void setGarantias(List<Garantia> garantias) {
		this.garantias = garantias;
	}

	public Plantilla getPlantilla(){
		return plantilla;
	}

	public void setPlantilla(Plantilla plantilla) {
		this.plantilla = plantilla;
	}

	public String getCodigoFuncionario(){
		return codigoFuncionario;
	}

	public void setCodigoFuncionario(String codigoFuncionario) {
		this.codigoFuncionario = codigoFuncionario;
	}

	public boolean isFlagCambioPlantilla(){
		return flagCambioPlantilla;
	}

	public void setFlagCambioPlantilla(boolean flagCambioPlantilla) {
		this.flagCambioPlantilla = flagCambioPlantilla;
	}

	public boolean isFlgConformePersonaRecojo(){
		return flgConformePersonaRecojo;
	}

	public void setFlgConformePersonaRecojo(boolean flgConformePersonaRecojo) {
		this.flgConformePersonaRecojo = flgConformePersonaRecojo;
	}

	public List<Plantilla> getPlantillas(){
		return plantillas;
	}

	public void setPlantillas(List<Plantilla> plantillas) {
		this.plantillas = plantillas;
	}

	public java.sql.Date getFechaCambioEstado(){
		return fechaCambioEstado;
	}

	public void setFechaCambioEstado(java.sql.Date fechaCambioEstado) {
		this.fechaCambioEstado = fechaCambioEstado;
	}

	public boolean isFlagCambioFechaProximoCobro(){
		return flagCambioFechaProximoCobro;
	}

	public void setFlagCambioFechaProximoCobro(boolean flagCambioFechaProximoCobro) {
		this.flagCambioFechaProximoCobro = flagCambioFechaProximoCobro;
	}

	public String getProductoIBS(){
		return productoIBS;
	}

	public void setProductoIBS(String productoIBS) {
		this.productoIBS = productoIBS;
	}

	public String getUbigeoBeneficiario(){
		return ubigeoBeneficiario;
	}

	public void setUbigeoBeneficiario(String ubigeoBeneficiario) {
		this.ubigeoBeneficiario = ubigeoBeneficiario;
	}

	public String getCodigoMoneda(){
		return codigoMoneda;
	}

	public void setCodigoMoneda(String codigoMoneda) {
		this.codigoMoneda = codigoMoneda;
	}

	public List<Firmante> getFirmantes(){
		return firmantes;
	}

	public void setFirmantes(List<Firmante> firmantes) {
		this.firmantes = firmantes;
	}

	public String getDescripcionEstado(){
		return descripcionEstado;
	}

	public void setDescripcionEstado(String descripcionEstado) {
		this.descripcionEstado = descripcionEstado;
	}

	public String getGerenteGrupo(){
		return gerenteGrupo;
	}

	public void setGerenteGrupo(String gerenteGrupo) {
		this.gerenteGrupo = gerenteGrupo;
	}

	public String getDescripcionTipoRequerimiento(){
		return descripcionTipoRequerimiento;
	}

	public void setDescripcionTipoRequerimiento(String descripcionTipoRequerimiento) {
		this.descripcionTipoRequerimiento = descripcionTipoRequerimiento;
	}

	public String getCodigoLugarEnvio(){
		return codigoLugarEnvio;
	}

	public void setCodigoLugarEnvio(String codigoLugarEnvio) {
		this.codigoLugarEnvio = codigoLugarEnvio;
	}

	public boolean isFlagCambioFechaEmision(){
		return flagCambioFechaEmision;
	}

	public void setFlagCambioFechaEmision(boolean flagCambioFechaEmision) {
		this.flagCambioFechaEmision = flagCambioFechaEmision;
	}

	public long getNumeroCartaFianza(){
		return numeroCartaFianza;
	}

	public void setNumeroCartaFianza(long numeroCartaFianza) {
		this.numeroCartaFianza = numeroCartaFianza;
	}

	public boolean isTieneNuevaObservacion(){
		return tieneNuevaObservacion;
	}

	public void setTieneNuevaObservacion(boolean tieneNuevaObservacion) {
		this.tieneNuevaObservacion = tieneNuevaObservacion;
	}

	public List<Cuenta> getCuentas(){
		return cuentas;
	}

	public void setCuentas(List<Cuenta> cuentas) {
		this.cuentas = cuentas;
	}

	public java.math.BigDecimal getComisionBase(){
		return comisionBase;
	}

	public void setComisionBase(java.math.BigDecimal comisionBase) {
		this.comisionBase = comisionBase;
	}

	public boolean isFlagCambioTipoFirma(){
		return flagCambioTipoFirma;
	}

	public void setFlagCambioTipoFirma(boolean flagCambioTipoFirma) {
		this.flagCambioTipoFirma = flagCambioTipoFirma;
	}

	public List<ListaVerificacion> getListaVerificacions(){
		return listaVerificacions;
	}

	public void setListaVerificacions(List<ListaVerificacion> listaVerificacions) {
		this.listaVerificacions = listaVerificacions;
	}

	public String getCuentaContable(){
		return cuentaContable;
	}

	public void setCuentaContable(String cuentaContable) {
		this.cuentaContable = cuentaContable;
	}

	public boolean isFlagTextoEspecial(){
		return flagTextoEspecial;
	}

	public void setFlagTextoEspecial(boolean flagTextoEspecial) {
		this.flagTextoEspecial = flagTextoEspecial;
	}

	public String getSubProductoIBS(){
		return subProductoIBS;
	}

	public void setSubProductoIBS(String subProductoIBS) {
		this.subProductoIBS = subProductoIBS;
	}

	public Cliente getCliente(){
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public java.math.BigDecimal getComisionPizarra(){
		return comisionPizarra;
	}

	public void setComisionPizarra(java.math.BigDecimal comisionPizarra) {
		this.comisionPizarra = comisionPizarra;
	}

	public String getAsistente(){
		return asistente;
	}

	public void setAsistente(String asistente) {
		this.asistente = asistente;
	}

	public String getNumeroDocumentoPersonaAutorizada(){
		return numeroDocumentoPersonaAutorizada;
	}

	public void setNumeroDocumentoPersonaAutorizada(String numeroDocumentoPersonaAutorizada) {
		this.numeroDocumentoPersonaAutorizada = numeroDocumentoPersonaAutorizada;
	}

	public String getUsuarioAutorizador(){
		return usuarioAutorizador;
	}

	public void setUsuarioAutorizador(String usuarioAutorizador) {
		this.usuarioAutorizador = usuarioAutorizador;
	}

	public Integer getTotalDias(){
		return totalDias;
	}

	public void setTotalDias(Integer totalDias) {
		this.totalDias = totalDias;
	}

	public java.sql.Timestamp getFechaRegistro(){
		return fechaRegistro;
	}

	public void setFechaRegistro(java.sql.Timestamp fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public java.sql.Date getFechaProximoCobroRequerimiento(){
		return fechaProximoCobroRequerimiento;
	}

	public void setFechaProximoCobroRequerimiento(java.sql.Date fechaProximoCobroRequerimiento) {
		this.fechaProximoCobroRequerimiento = fechaProximoCobroRequerimiento;
	}

	public boolean isFlagCambioConcepto(){
		return flagCambioConcepto;
	}

	public void setFlagCambioConcepto(boolean flagCambioConcepto) {
		this.flagCambioConcepto = flagCambioConcepto;
	}

	public boolean isFlagContratoMarco(){
		return flagContratoMarco;
	}

	public void setFlagContratoMarco(boolean flagContratoMarco) {
		this.flagContratoMarco = flagContratoMarco;
	}

	public String getNumeroValorados(){
		return numeroValorados;
	}

	public void setNumeroValorados(String numeroValorados) {
		this.numeroValorados = numeroValorados;
	}

	public Integer getCodigoPlantilla(){
		return codigoPlantilla;
	}

	public void setCodigoPlantilla(Integer codigoPlantilla) {
		this.codigoPlantilla = codigoPlantilla;
	}

	public String getCodigoAgenciaRecojo(){
		return codigoAgenciaRecojo;
	}

	public void setCodigoAgenciaRecojo(String codigoAgenciaRecojo) {
		this.codigoAgenciaRecojo = codigoAgenciaRecojo;
	}

	public boolean isFlagPagare(){
		return flagPagare;
	}

	public void setFlagPagare(boolean flagPagare) {
		this.flagPagare = flagPagare;
	}

	public java.math.BigDecimal getComisionPactada(){
		return comisionPactada;
	}

	public void setComisionPactada(java.math.BigDecimal comisionPactada) {
		this.comisionPactada = comisionPactada;
	}

	public ContratoMarco getContratoMarco(){
		return contratoMarco;
	}

	public void setContratoMarco(ContratoMarco contratoMarco) {
		this.contratoMarco = contratoMarco;
	}

	public String getNombreArchivoContratoMarco(){
		return nombreArchivoContratoMarco;
	}

	public void setNombreArchivoContratoMarco(String nombreArchivoContratoMarco) {
		this.nombreArchivoContratoMarco = nombreArchivoContratoMarco;
	}

	public String getNombreBeneficiario(){
		return nombreBeneficiario;
	}

	public void setNombreBeneficiario(String nombreBeneficiario) {
		this.nombreBeneficiario = nombreBeneficiario;
	}

	public String getCodigoVendedor(){
		return codigoVendedor;
	}

	public void setCodigoVendedor(String codigoVendedor) {
		this.codigoVendedor = codigoVendedor;
	}

	public String getTextoEspecialSimple(){
		return textoEspecialSimple;
	}

	public void setTextoEspecialSimple(String textoEspecialSimple) {
		this.textoEspecialSimple = textoEspecialSimple;
	}

	public String getAleasProceso(){
		return aleasProceso;
	}

	public void setAleasProceso(String aleasProceso) {
		this.aleasProceso = aleasProceso;
	}

	public long getNumeroValorado(){
		return numeroValorado;
	}

	public void setNumeroValorado(long numeroValorado) {
		this.numeroValorado = numeroValorado;
	}

	public String getEstado(){
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getUsuarioAsistente(){
		return usuarioAsistente;
	}

	public void setUsuarioAsistente(String usuarioAsistente) {
		this.usuarioAsistente = usuarioAsistente;
	}

	public String getDescripcionTipoCancelacion(){
		return descripcionTipoCancelacion;
	}

	public void setDescripcionTipoCancelacion(String descripcionTipoCancelacion) {
		this.descripcionTipoCancelacion = descripcionTipoCancelacion;
	}

	public String getDescripcionTipoFianza(){
		return descripcionTipoFianza;
	}

	public void setDescripcionTipoFianza(String descripcionTipoFianza) {
		this.descripcionTipoFianza = descripcionTipoFianza;
	}

	public List<Riesgo> getRiesgos(){
		return riesgos;
	}

	public void setRiesgos(List<Riesgo> riesgos) {
		this.riesgos = riesgos;
	}

	public CartaFianza getCartaFianza(){
		return cartaFianza;
	}

	public void setCartaFianza(CartaFianza cartaFianza) {
		this.cartaFianza = cartaFianza;
	}

	public Long getCodigo(){
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public boolean isFlagCambioTipoFianza(){
		return flagCambioTipoFianza;
	}

	public void setFlagCambioTipoFianza(boolean flagCambioTipoFianza) {
		this.flagCambioTipoFianza = flagCambioTipoFianza;
	}

	public boolean isFlagCambioTextoEspecial(){
		return flagCambioTextoEspecial;
	}

	public void setFlagCambioTextoEspecial(boolean flagCambioTextoEspecial) {
		this.flagCambioTextoEspecial = flagCambioTextoEspecial;
	}

	public String getDescripcionTipoCobranza(){
		return descripcionTipoCobranza;
	}

	public void setDescripcionTipoCobranza(String descripcionTipoCobranza) {
		this.descripcionTipoCobranza = descripcionTipoCobranza;
	}

	public String getDeudorGarantizado(){
		return deudorGarantizado;
	}

	public void setDeudorGarantizado(String deudorGarantizado) {
		this.deudorGarantizado = deudorGarantizado;
	}

	public String getTipoSolicitud(){
		return tipoSolicitud;
	}

	public void setTipoSolicitud(String tipoSolicitud) {
		this.tipoSolicitud = tipoSolicitud;
	}

	public String getCodigoAgenciaOrigen(){
		return codigoAgenciaOrigen;
	}

	public void setCodigoAgenciaOrigen(String codigoAgenciaOrigen) {
		this.codigoAgenciaOrigen = codigoAgenciaOrigen;
	}

	public String getDescripcionTipoBloqueo(){
		return descripcionTipoBloqueo;
	}

	public void setDescripcionTipoBloqueo(String descripcionTipoBloqueo) {
		this.descripcionTipoBloqueo = descripcionTipoBloqueo;
	}

	public String getDescripcionCodigoMoneda(){
		return descripcionCodigoMoneda;
	}

	public void setDescripcionCodigoMoneda(String descripcionCodigoMoneda) {
		this.descripcionCodigoMoneda = descripcionCodigoMoneda;
	}

	public java.math.BigDecimal getExcedeRMC(){
		return excedeRMC;
	}

	public void setExcedeRMC(java.math.BigDecimal excedeRMC) {
		this.excedeRMC = excedeRMC;
	}

	public boolean isFlagErrorServicioFirmas(){
		return flagErrorServicioFirmas;
	}

	public void setFlagErrorServicioFirmas(boolean flagErrorServicioFirmas) {
		this.flagErrorServicioFirmas = flagErrorServicioFirmas;
	}

	public boolean isFlagCambioTipoCobranza(){
		return flagCambioTipoCobranza;
	}

	public void setFlagCambioTipoCobranza(boolean flagCambioTipoCobranza) {
		this.flagCambioTipoCobranza = flagCambioTipoCobranza;
	}

	public String getTipoLugar(){
		return tipoLugar;
	}

	public void setTipoLugar(String tipoLugar) {
		this.tipoLugar = tipoLugar;
	}

	public java.math.BigDecimal getTipoCambioSBS(){
		return tipoCambioSBS;
	}

	public void setTipoCambioSBS(java.math.BigDecimal tipoCambioSBS) {
		this.tipoCambioSBS = tipoCambioSBS;
	}

	public java.sql.Date getFechaVencimiento(){
		return fechaVencimiento;
	}

	public void setFechaVencimiento(java.sql.Date fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}

	public List<Funcionario> getFuncionarios(){
		return funcionarios;
	}

	public void setFuncionarios(List<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	}

	public boolean isFlgRevisarFirmasPoderes(){
		return flgRevisarFirmasPoderes;
	}

	public void setFlgRevisarFirmasPoderes(boolean flgRevisarFirmasPoderes) {
		this.flgRevisarFirmasPoderes = flgRevisarFirmasPoderes;
	}

	public java.math.BigDecimal getMonto(){
		return monto;
	}

	public void setMonto(java.math.BigDecimal monto) {
		this.monto = monto;
	}

	public String getUsuarioGerenteGrupo(){
		return usuarioGerenteGrupo;
	}

	public void setUsuarioGerenteGrupo(String usuarioGerenteGrupo) {
		this.usuarioGerenteGrupo = usuarioGerenteGrupo;
	}

	public Liquidacion getLiquidacion(){
		return liquidacion;
	}

	public void setLiquidacion(Liquidacion liquidacion) {
		this.liquidacion = liquidacion;
	}

	public boolean isFlagRequiereFirmaFisica(){
		return flagRequiereFirmaFisica;
	}

	public void setFlagRequiereFirmaFisica(boolean flagRequiereFirmaFisica) {
		this.flagRequiereFirmaFisica = flagRequiereFirmaFisica;
	}

	public String getCodigoTipoCancelacion(){
		return codigoTipoCancelacion;
	}

	public void setCodigoTipoCancelacion(String codigoTipoCancelacion) {
		this.codigoTipoCancelacion = codigoTipoCancelacion;
	}

	public String getDescripcionFlagBloqueo(){
		return descripcionFlagBloqueo;
	}

	public void setDescripcionFlagBloqueo(String descripcionFlagBloqueo) {
		this.descripcionFlagBloqueo = descripcionFlagBloqueo;
	}

	public String getModalidadCobro(){
		return modalidadCobro;
	}

	public void setModalidadCobro(String modalidadCobro) {
		this.modalidadCobro = modalidadCobro;
	}

	public String getUsuarioRegistro(){
		return usuarioRegistro;
	}

	public void setUsuarioRegistro(String usuarioRegistro) {
		this.usuarioRegistro = usuarioRegistro;
	}

	public boolean isFlagCambioMonto(){
		return flagCambioMonto;
	}

	public void setFlagCambioMonto(boolean flagCambioMonto) {
		this.flagCambioMonto = flagCambioMonto;
	}

	public String getConceptoGarantia(){
		return conceptoGarantia;
	}

	public void setConceptoGarantia(String conceptoGarantia) {
		this.conceptoGarantia = conceptoGarantia;
	}

	public String getDescripcionModalidadCobro(){
		return descripcionModalidadCobro;
	}

	public void setDescripcionModalidadCobro(String descripcionModalidadCobro) {
		this.descripcionModalidadCobro = descripcionModalidadCobro;
	}

	public java.sql.Date getFechaEmision(){
		return fechaEmision;
	}

	public void setFechaEmision(java.sql.Date fechaEmision) {
		this.fechaEmision = fechaEmision;
	}

	public String getTipoBloqueo(){
		return tipoBloqueo;
	}

	public void setTipoBloqueo(String tipoBloqueo) {
		this.tipoBloqueo = tipoBloqueo;
	}

	public String getCodigoTarifario(){
		return codigoTarifario;
	}

	public void setCodigoTarifario(String codigoTarifario) {
		this.codigoTarifario = codigoTarifario;
	}

	public java.math.BigDecimal getMontoDecremento(){
		return montoDecremento;
	}

	public void setMontoDecremento(java.math.BigDecimal montoDecremento) {
		this.montoDecremento = montoDecremento;
	}

	public String getDescripcionTipoRiesgo(){
		return descripcionTipoRiesgo;
	}

	public void setDescripcionTipoRiesgo(String descripcionTipoRiesgo) {
		this.descripcionTipoRiesgo = descripcionTipoRiesgo;
	}

	public String getDescripcionTipoFirma(){
		return descripcionTipoFirma;
	}

	public void setDescripcionTipoFirma(String descripcionTipoFirma) {
		this.descripcionTipoFirma = descripcionTipoFirma;
	}

	public String getCodigoTipoFianza(){
		return codigoTipoFianza;
	}

	public void setCodigoTipoFianza(String codigoTipoFianza) {
		this.codigoTipoFianza = codigoTipoFianza;
	}

	public List<Comision> getComisions(){
		return comisions;
	}

	public void setComisions(List<Comision> comisions) {
		this.comisions = comisions;
	}

	public java.math.BigDecimal getTipoCambioBE(){
		return tipoCambioBE;
	}

	public void setTipoCambioBE(java.math.BigDecimal tipoCambioBE) {
		this.tipoCambioBE = tipoCambioBE;
	}

	public String getTipoRequerimiento(){
		return tipoRequerimiento;
	}

	public void setTipoRequerimiento(String tipoRequerimiento) {
		this.tipoRequerimiento = tipoRequerimiento;
	}

	public String getTipoDocumentoPersonaAutorizada(){
		return tipoDocumentoPersonaAutorizada;
	}

	public void setTipoDocumentoPersonaAutorizada(String tipoDocumentoPersonaAutorizada) {
		this.tipoDocumentoPersonaAutorizada = tipoDocumentoPersonaAutorizada;
	}

	public String getMotivo(){
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

}