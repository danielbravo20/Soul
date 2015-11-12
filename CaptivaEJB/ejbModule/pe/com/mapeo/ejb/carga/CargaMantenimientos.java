package pe.com.mapeo.ejb.carga;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import pe.com.mapeo.dao.Jpo;
import pe.com.mapeo.util.MapeoUtil;

public class CargaMantenimientos {
	
	public static final String nombreCarpetaController = "controller";
	public static final String nombreCarpetaMantenimiento = "mantenimientos";
	public static final String nombreCarpetaMantenimientoDao = "mantenimientos\\dao";
	
	public static final String prefijoNombre = "Mantenimiento";
	public static final String sufijoController = "Controller";
	public static String preFijoProducto;
	
	public static final String prefijoNombreSP = "SP_MANTENIMIENTO_MAE";
	
	private static Jpo jpo ;
	public static List<Map<String, Object>> mantenimientos ;
	
	/* VARIABLES COMUNES */
	/**
     * Prefijo del paquete donde se genera el codigo <p>
     * EJM: pe.com.financiero.bpm
     */
	private static String prefijoPaquete;
	/**
     * Prefijo del paquete donde se genera el codigo con su proceso y su version <p>
     * EJM: pe.com.financiero.bpm.cf.v1
     */
	private static String nombrePaquete;
		/**
	     * Codigo del proyecto En cada Iteraci�n de mantenimiento : <p>
	     * EJM: BECartaFianza
	     */
		private static String codigoProyecto;
		/**
	     * N�mero de versi�n En cada Iteraci�n de mantenimiento : <p>
	     * EJM: 1
	     */
		private static String numeroVersion;
		/**
	     * Codigo de Mantenimiento En cada Iteraci�n de mantenimiento : <p>
	     * EJM: Catalogo
	     */
		private static String codigoMantenimiento;
		/**
	     * Codigo de Mantenimiento En cada Iteraci�n de mantenimiento con separador al encontrar mayusculas : <p>
	     * EJM: Documento_Tarea
	     */
		private static String codigoMantenimientoSEP;
		/**
	     * Codigo de Mantenimiento En cada Iteraci�n de mantenimiento : <p>
	     * EJM: catalogo
	     */
		private static String nombreObjeto;
		/**
	     * Nombre de Clase En cada Iteraci�n de mantenimiento : <p>
	     * EJM: MantenimientoCatalogo
	     */
		private static String nombreClase;
		/**
	     * Nombre de Interfaz En cada Iteraci�n de mantenimiento : <p>
	     * EJM: mantenimientoCatalogo (Nombre de variable al ser llamada)
	     */
		private static String nombreInterfaz;
		/**
	     * Esquema de BD En cada Iteraci�n de mantenimiento : <p>
	     * EJM: BFP_CARTA_FIANZA
	     */
		private static String esquemaBD;
		/**
	     * Esquema de BD En cada Iteraci�n de mantenimiento : <p>
	     * EJM: jdbc/cartaFianza
	     */
		private static String codigoDataSource;
		
		
		/**
	     * Lista de Atributos En cada Iteraci�n de mantenimiento : <p>
	     * EJM:
	     *  codigoProyecto VARCHAR(50)  	NOT NULL," + <p>
	     *  numeroVersion VARCHAR(10)    	NOT NULL," + <p>
	     *  codigoMantenimiento VARCHAR(50) NOT NULL," + <p>
	     *  codigoAtributo INTEGER          NOT NULL," + <p>
	     *  nombre VARCHAR(50)              NOT NULL," + <p>
	     *  tipoDato VARCHAR(50)            NOT NULL," + <p>
	     *  longitud INTEGER," + <p>
	     *  precision INTEGER," + <p>
	     *  esLlavePrimaria CHARACTER(1)," + <p>
	     *  esListado CHARACTER(1)," + <p>
	     *  esBusqueda CHARACTER(1)," + <p>
	     *  esObligatorio CHARACTER(1)" + <p>
	     */
		static List<Map<String, Object>> atributos;
		
	private static StringBuilder BD_Tablas;
	private static StringBuilder BD_TablasDelete;
	private static StringBuilder BD_Insert;
	private static StringBuilder BD_Delete;
	
	private static StringBuilder BD_SP_Insert;
	private static StringBuilder BD_SP_Update;
	private static StringBuilder BD_SP_Delete;
	private static StringBuilder BD_SP_Select;
	private static StringBuilder BD_SP_List;
	
	private static StringBuilder BD_SP_DropSP;

	private static String Del;
	
	public static boolean cargar(Jpo objJpo,Object objMantenimientos) throws Exception {
		
		BD_Tablas = new StringBuilder();
		BD_TablasDelete = new StringBuilder();
		BD_Insert = new StringBuilder();
		BD_Delete = new StringBuilder();
		
		BD_SP_Insert = new StringBuilder();
		BD_SP_Update = new StringBuilder();
		BD_SP_Delete = new StringBuilder();
		BD_SP_Select = new StringBuilder();
		BD_SP_List = new StringBuilder();

		BD_SP_DropSP = new StringBuilder();
		
		Del = ";";
		
		if(objMantenimientos!=null){
			
			jpo = objJpo;
			
			mantenimientos = (List<Map<String, Object>>) objMantenimientos;
			
			prefijoPaquete  = (String) CargaConfiguracion.config.get("urlPrefijoPaquete");
			nombrePaquete  = CargaConfiguracion.configuracion.getPaqueteURLJava();
			
			//String dirGeneradoWeb = CargaConfiguracion.configuracion.getDirGeneradoWeb();
			String dirGeneradoEJBExt = CargaConfiguracion.configuracion.getDirGeneradoEJBExt();
			String dirGeneradoEJBC = CargaConfiguracion.configuracion.getDirGeneradoEJBClient();
			
			String dirSource = CargaConfiguracion.configuracion.getDirSource();
			String dirSourceWeb = CargaConfiguracion.configuracion.getDirSourceWeb();
			String dirSourceEJBC = CargaConfiguracion.configuracion.getDirSourceEJBClient();
			String dirSourceEJB = CargaConfiguracion.configuracion.getDirSourceEJB();
			
			String dirGeneradoLib = CargaConfiguracion.configuracion.getDirGeneradoLib();
			
			
			
			preFijoProducto = (String) CargaConfiguracion.config.get("producto");

			// Crear Controller de mantenimientos
			MapeoUtil.crearFolder(dirSourceWeb+"\\"+nombreCarpetaController);
			MapeoUtil.crearArchivo(dirSourceWeb+"\\"+nombreCarpetaController,preFijoProducto+prefijoNombre+sufijoController+".java",contenidoController());
			
			
			// Service
			MapeoUtil.crearFolder(dirSourceEJBC+"\\"+nombreCarpetaMantenimiento);
			MapeoUtil.crearFolder(dirSourceEJB+"\\"+nombreCarpetaMantenimiento);
			MapeoUtil.crearFolder(dirGeneradoEJBExt+"\\"+nombreCarpetaMantenimiento);
			
			// Dao
			MapeoUtil.crearFolder(dirSourceEJB+"\\"+nombreCarpetaMantenimientoDao);
			//MapeoUtil.crearFolder(dirSourceEJBC+"\\"+nombreCarpetaMantenimientoDao);
			MapeoUtil.crearFolder(dirGeneradoEJBExt+"\\"+nombreCarpetaMantenimientoDao);
			MapeoUtil.crearFolder(dirSource+"\\"+"Scripts");
			
			MapeoUtil.crearFolder(dirGeneradoEJBC+"\\"+nombreCarpetaMantenimientoDao);
			
			// ENTIDADES
			MapeoUtil.crearFolder(dirGeneradoLib+"\\modelo\\mantenimiento");
			
			// TODO : SE AGREGA EL CAMPO BE
			String dirPresentacion = CargaConfiguracion.configuracion.getDirGeneradoWebContent()+"\\BE\\Mantenimientos";
			// PRESENTACION
			MapeoUtil.crearFolder(dirPresentacion+"\\vista");
			MapeoUtil.crearFolder(dirPresentacion+"\\controlador");
			MapeoUtil.crearFolder(dirPresentacion+"\\preControlador");
			
			for(int i = 0;i<mantenimientos.size();i++){
				
				codigoProyecto = (String) mantenimientos.get(i).get("codigoProyecto");
				numeroVersion = (String) mantenimientos.get(i).get("numeroVersion");
				codigoMantenimiento = (String) mantenimientos.get(i).get("codigoMantenimiento");
				codigoMantenimientoSEP = getSeparador(codigoMantenimiento);
				nombreObjeto = StringUtils.uncapitalize(codigoMantenimiento);
				nombreClase = prefijoNombre+codigoMantenimiento;
				nombreInterfaz = StringUtils.capitalize(nombreClase);
				esquemaBD = (String) mantenimientos.get(i).get("codigoEsquema");
				codigoDataSource = (String) mantenimientos.get(i).get("codigoDataSource");
				
				atributos = (List<Map<String, Object>>) jpo.tabla("MantenimientoAtributo").donde("codigoProyecto = '"+codigoProyecto+"' AND numeroVersion = '"+numeroVersion+"' AND codigoMantenimiento = '"+codigoMantenimiento+"'").seleccionar("*");
				
				if(atributos.size()==0){
					throw new Exception("error debes ingresar atributos para el mantenimiento '"+codigoMantenimiento+"'");
				} else {
					
				
				// JAVA ESTRUCTURA
					
					// SOURCE EJB CLIENT
					MapeoUtil.crearArchivo(dirSourceEJBC+"\\"+nombreCarpetaMantenimiento,nombreClase+".java",cargarSourceEJBLocal(i));
					
					// SOURCE EJB SESSION
					MapeoUtil.crearArchivo(dirSourceEJB+"\\"+nombreCarpetaMantenimiento,nombreClase+"Impl.java",cargarSourceEJBSession(i));
					
					// GENERADO (PRE) EJB SESSION
					MapeoUtil.crearArchivo(dirGeneradoEJBExt+"\\"+nombreCarpetaMantenimiento,"Pre"+nombreClase+"Impl.java",cargarGeneradoEJBSession(i));
					
					// DAO SOURCE EJB CLIENT
					//MapeoUtil.crearArchivo(dirSourceEJBC+"\\"+nombreCarpetaMantenimientoDao,nombreClase+"Dao.java",cargarSourceEJBDaoLocal(i));
					//MapeoUtil.crearArchivo(dirGeneradoEJBExt+"\\"+nombreCarpetaMantenimientoDao,nombreClase+"Dao.java",cargarSourceEJBDaoLocal(i));
					
					// DAO SOURCE EJB SESSION
					MapeoUtil.crearArchivo(dirSourceEJB+"\\"+nombreCarpetaMantenimientoDao,nombreClase+"DaoImpl.java",cargarSourceEJBDaoSession(i));
					
					// DAO GENERADO (PRE) EJB CLIENT
					//MapeoUtil.crearArchivo(dirGeneradoEJBC+"\\"+nombreCarpetaMantenimientoDao,"Pre"+nombreClase+"Dao.java",cargarGeneradoEJBDaoLocal(i));
					MapeoUtil.crearArchivo(dirGeneradoEJBExt+"\\"+nombreCarpetaMantenimientoDao,"Pre"+nombreClase+"Dao.java",cargarGeneradoEJBDaoLocal(i));
					
					// DAO GENERADO (PRE) EJB SESSION
					MapeoUtil.crearArchivo(dirGeneradoEJBExt+"\\"+nombreCarpetaMantenimientoDao,"Pre"+nombreClase+"DaoImpl.java",cargarGeneradoEJBDaoSession(i));
					
				// BASE DE DATOS
					
					//Tablas.append(crearTablaBD(i).get("tablaBD"));	
					crearTablaBD(i);
					
				// ENTIDADES
					
					MapeoUtil.crearArchivo(dirGeneradoLib+"\\modelo\\mantenimiento",codigoMantenimiento+".java",cargarGeneradoModelo(i));
					
				// PRESENTACION
					
					// VISTA
					MapeoUtil.crearArchivo(dirPresentacion+"\\vista",nombreClase+"V"+numeroVersion+".html",cargarPresentacionVista(i),"UTF-8");
					MapeoUtil.crearArchivo(dirPresentacion+"\\controlador",nombreClase+"V"+numeroVersion+".js",cargarPresentacionControlador(i),"UTF-8");
					MapeoUtil.crearArchivo(dirPresentacion+"\\preControlador","pre"+nombreClase+"V"+numeroVersion+".js",cargarPresentacionPreControlador(i),"UTF-8");
					
				}
				
			}
			
			MapeoUtil.crearArchivo(dirSource+"\\"+"Scripts","BDs.sql",BD_Tablas.toString()+BD_Insert.toString());
			MapeoUtil.crearArchivo(dirSource+"\\"+"Scripts","BDs_rollback.sql",BD_Delete.toString()+BD_TablasDelete.toString());
			MapeoUtil.crearArchivo(dirSource+"\\"+"Scripts","SPs.sql",BD_SP_Insert.toString()+BD_SP_Update.toString()+BD_SP_Delete.toString()+BD_SP_Select.toString()+BD_SP_List.toString());
			MapeoUtil.crearArchivo(dirSource+"\\"+"Scripts","SPs_rollback.sql",BD_SP_DropSP.toString());
			
		}
		
		return true;
		
	}
	
	// Controller Service
	private static String contenidoController(){
		
		StringBuilder texto = new StringBuilder();
		
		texto.append("package "+CargaConfiguracion.configuracion.getPaqueteURLJava()+"."+nombreCarpetaController+";\n\n");

		texto.append("import java.util.HashMap;\n");
		texto.append("import java.util.Map;\n\n");

		texto.append("import pe.com.financiero.bpm.core.controller.MantenimientoController;\n\n");

		texto.append("public class "+preFijoProducto+prefijoNombre+sufijoController+" extends MantenimientoController {\n\n");
			
		texto.append("	private static final long serialVersionUID = 1L;\n\n");

		texto.append("	public Map<String, String> cargarLookupMap() {\n");
		texto.append("		Map<String, String> lookMap = new HashMap<String, String>();\n");
		for(int i = 0;i<mantenimientos.size();i++){
		texto.append("		lookMap.put(\""+prefijoNombre+mantenimientos.get(i).get("codigoMantenimiento")+"V"+mantenimientos.get(i).get("numeroVersion")+"\", \"ejblocal:"+CargaConfiguracion.configuracion.getPaqueteURLJava()+".mantenimientos."+prefijoNombre+mantenimientos.get(i).get("codigoMantenimiento")+"\");\n");
		}
		texto.append("		return lookMap;\n");
		texto.append("	}\n\n");
			
		texto.append("	public int getMaxFileSize() {\n");
		texto.append("		return 1024*1024*1;\n");
		texto.append("	}\n\n");
		
		texto.append("	public int getMaxRequestSize() {\n");
		texto.append("		return 1024*1024*1;\n");
		texto.append("	}\n\n");

		texto.append("	public int getThresholdSize() {\n");
		texto.append("		return 1024*1024*1;\n");
		texto.append("	}\n\n");
			
		texto.append("}");
		
		return texto.toString();
		
	}
	
	// SOURCE EJB CLIENT - Interfaz
	private static String cargarSourceEJBLocal(int i){
		
		StringBuilder texto = new StringBuilder();
		
		texto.append("package "+CargaConfiguracion.configuracion.getPaqueteURLJava()+"."+nombreCarpetaMantenimiento+";\n");
		texto.append("import javax.ejb.Local;\n\n");

		texto.append("import pe.com.financiero.bpm.core.ejb."+prefijoNombre+"Base;\n\n");

		texto.append("@Local\n");
		texto.append("public interface "+prefijoNombre+mantenimientos.get(i).get("codigoMantenimiento")+" extends "+prefijoNombre+"Base {\n\n");

		texto.append("}");
		
		return texto.toString();
		
	}
	
	// SOURCE EJB SESSION
	private static String cargarSourceEJBSession(int i){
		
		String nombreClase = prefijoNombre+mantenimientos.get(i).get("codigoMantenimiento");
		
		StringBuilder texto = new StringBuilder();
		
		texto.append("package "+CargaConfiguracion.configuracion.getPaqueteURLJava()+"."+nombreCarpetaMantenimiento+";\n\n");
		texto.append("import javax.ejb.Stateless;\n");
		texto.append("import java.util.Map;\n");
		texto.append("import javax.servlet.http.HttpServletRequest;\n");
		texto.append("import javax.servlet.http.HttpServletResponse;\n\n");
		
		texto.append("@Stateless\n");
		texto.append("public class "+nombreClase+"Impl extends Pre"+nombreClase+"Impl implements "+nombreClase+" {\n\n");

		texto.append("	@Override\n");
		texto.append("	public Object cargarAccion(HttpServletRequest request,Map<String, String> mapRequest, Map mapInputStream)throws Exception {\n");
		texto.append("		return null;\n");
		texto.append("	}\n\n");

		texto.append("	@Override\n");
		texto.append("	public Object cargarAccion(HttpServletRequest request,HttpServletResponse response) throws Exception {\n");
		texto.append("		return null;\n");
		texto.append("	}\n\n");
		
		texto.append("}");
		
		return texto.toString();
		
	}
	
	// GENERADO (PRE) EJB SESSION
	private static String cargarGeneradoEJBSession(int i) throws Exception{
							
		StringBuilder parametroEnvio = new StringBuilder();
		StringBuilder parametroValidar = new StringBuilder();

		for(int e = 0;e<atributos.size();e++){
			String nombre = (String) atributos.get(e).get("nombre");
			String nombreJava = formateaNombreAtributoJava(nombre);
			String tipoDato = (String) atributos.get(e).get("tipoDato");
			
			Integer longitud = (Integer) atributos.get(e).get("longitud") ;
			Integer precision = (Integer) atributos.get(e).get("precision") ;
			//boolean esBusqueda = (atributos.get(e).get("esBusqueda") != null)?(Boolean) atributos.get(e).get("esBusqueda"):false;
			boolean esObligatorio = (atributos.get(e).get("esObligatorio") != null)?(Boolean) atributos.get(e).get("esObligatorio"):false;
			
			parametroEnvio.append("		String "+nombreJava+" = request.getParameter(\""+nombreJava+"\");\n");
						
			if(tipoDato.equals("boolean")){
				parametroEnvio.append("		if("+nombreJava+"!=null && !"+nombreJava+".equals(\"\") && ("+nombreJava+".equals(\"true\") || "+nombreJava+".equals(\"false\"))){\n");
				parametroEnvio.append("			"+nombreObjeto+".set"+StringUtils.capitalize(nombreJava)+"(("+getPrimitivo(tipoDato)+") MantenimientoUtil.obtenerValor("+nombreJava+", \""+tipoDato+"\"));\n");
				parametroEnvio.append("		}\n\n");
				/*parametroEnvio.append("		} else { "+"\n");
				parametroEnvio.append("			throw new Exception(\"Ingrese un valor correcto para el campo '"+nombreJava+"'\"); "+"\n");
				parametroEnvio.append("		}\n\n");*/
				
			} else if(tipoDato.equals("BigDecimal") || tipoDato.equals("double")){
				
				parametroEnvio.append("		try{\n");
				parametroEnvio.append("			java.math.BigDecimal "+nombreJava+"_val = new java.math.BigDecimal("+nombreJava+".trim()); \n");
				parametroEnvio.append("			if("+nombreJava+"_val.precision()>"+longitud+"){\n");
				parametroEnvio.append("				throw new Exception(\"Longitud de '"+nombreJava+"' es mayor a "+longitud+"\"); "+"\n");
				parametroEnvio.append("			} else if ("+nombreJava+"_val.scale()>"+precision+"){\n");
				parametroEnvio.append("				throw new Exception(\"Precision de '"+nombreJava+"' es mayor a "+longitud+"\"); "+"\n");
				parametroEnvio.append("			}\n");
				parametroEnvio.append("		} catch (NumberFormatException nfe){\n");
				parametroEnvio.append("		//		throw new Exception(\"No es un tipo decimal el campo '"+nombreJava+"'\");\n");
				parametroEnvio.append("		}\n\n");
				
				parametroEnvio.append("		"+nombreObjeto+".set"+StringUtils.capitalize(nombreJava)+"(("+getPrimitivo(tipoDato)+") MantenimientoUtil.obtenerValor("+nombreJava+", \""+tipoDato+"\"));\n\n");
			
			} else if (tipoDato.equals("Date")){
			
				parametroEnvio.append("		"+nombreJava+" = "+nombreJava+".substring(0,10);\n\n");
				parametroEnvio.append("		java.text.SimpleDateFormat "+nombreJava+"_sdf = new java.text.SimpleDateFormat(\"dd/MM/yyyy\");\n");
				parametroEnvio.append("		if("+nombreJava+".trim().length() != "+nombreJava+"_sdf.toPattern().length()){\n");
				parametroEnvio.append("			throw new Exception(\"Longitud de fecha incorrecta para el campo '"+nombreJava+"'\");\n");
				parametroEnvio.append("		}\n");
				parametroEnvio.append("		"+nombreJava+"_sdf.setLenient(false);\n");
				parametroEnvio.append("		try{\n");
				parametroEnvio.append("			"+nombreJava+"_sdf.parse("+nombreJava+".trim());\n");
				parametroEnvio.append("		}catch (java.text.ParseException pe) {\n");
				parametroEnvio.append("		//	throw new Exception(\"No es un tipo de fecha del formato 'dd/MM/yyyy' el campo '"+nombreJava+"'\");\n");
				parametroEnvio.append("		}\n\n");
				
				parametroEnvio.append("		"+nombreObjeto+".set"+StringUtils.capitalize(nombreJava)+"(("+getPrimitivo(tipoDato)+") MantenimientoUtil.obtenerValor("+nombreJava+", \""+tipoDato+"\"));\n\n");
			
			} else {
				
				parametroEnvio.append("		if("+nombreJava+"!=null && !"+nombreJava+".equals(\"\")){\n");
				parametroEnvio.append("			"+nombreObjeto+".set"+StringUtils.capitalize(nombreJava)+"(("+getPrimitivo(tipoDato)+") MantenimientoUtil.obtenerValor("+nombreJava+", \""+tipoDato+"\"));\n\n");
				parametroEnvio.append("		}\n\n");
				
			}

			if(esObligatorio){
				
				/*if(tipoDato.equals("boolean")){
					parametroValidar.append("		String "+nombreJava+" = "+nombreObjeto+".get"+StringUtils.capitalize(nombreJava)+"();\n");
				} else {
					
				}*/
				parametroValidar.append("		"+getPrimitivo(tipoDato)+" "+nombreJava+" = "+nombreObjeto+".get"+StringUtils.capitalize(nombreJava)+"();\n");
				
				if(tipoDato.equals("boolean")){
					parametroValidar.append("		if("+nombreJava+" == null){\n");
					parametroValidar.append("			return new MensajeValidacion(false,\"El campo '"+nombreJava+"' es obligatorio\",\""+nombreJava+"\");\n");
					parametroValidar.append("		}\n\n");
				}
				
				if(tipoDato.equals("String")){
					parametroValidar.append("		if("+nombreJava+"==null || "+nombreJava+".trim().length()==0){\n");
					parametroValidar.append("			return new MensajeValidacion(false,\"El campo '"+nombreJava+"' es obligatorio\",\""+nombreJava+"\");\n");
					parametroValidar.append("		}\n\n");
				}
					
				if(tipoDato.equals("Date")){
					parametroValidar.append("		if("+nombreJava+"==null){\n");
					parametroValidar.append("			return new MensajeValidacion(false,\"El campo '"+nombreJava+"' es obligatorio\",\""+nombreJava+"\");\n");
					parametroValidar.append("		}\n\n");
				}
					
				
				if(longitud != null && !(tipoDato.equals("BigDecimal") || tipoDato.equals("double")) && !tipoDato.equals("boolean")){
					parametroValidar.append("		if("+nombreJava+".trim().length()>"+longitud+"){\n");
					parametroValidar.append("			return new MensajeValidacion(false,\"Longitud de '"+nombreJava+"' es mayor a "+longitud+"\",\""+nombreJava+"\");\n");
					parametroValidar.append("		}\n\n");
				}
				/*
				if(tipoDato.equals("boolean")){
					parametroValidar.append("		if(!"+nombreJava+".equals(\"true\") && !"+nombreJava+".equals(\"false\")){\n");
					parametroValidar.append("			return new MensajeValidacion(false,\"Ingrese un valor correcto\",\""+nombreJava+"\");\n");
					parametroValidar.append("		}\n\n");
				}
				*/
				
				/*
				if(tipoDato.equals("BigDecimal") || tipoDato.equals("double")){
					
					parametroValidar.append("		try{\n");
					parametroValidar.append("			java.math.BigDecimal "+nombreJava+"_val = new java.math.BigDecimal("+nombreJava+".toString()); \n");
					parametroValidar.append("			if("+nombreJava+"_val.precision()>"+longitud+"){\n");
					parametroValidar.append("				return new MensajeValidacion(false,\"Longitud de '"+nombreJava+"' es mayor a "+longitud+"\",\""+nombreJava+"\");\n");
					parametroValidar.append("			}else if("+nombreJava+"_val.scale()>"+precision+"){\n");
					parametroValidar.append("				return new MensajeValidacion(false,\"Precision de '"+nombreJava+"' es mayor a "+longitud+"\",\""+nombreJava+"\");\n");
					parametroValidar.append("			}\n");
					parametroValidar.append("		} catch (NumberFormatException nfe){\n");
					parametroValidar.append("				return new MensajeValidacion(false,\"No es un tipo decimal\",\""+nombreJava+"\");\n");
					parametroValidar.append("		}\n\n");
					
				}*/
				/*
				if(tipoDato.equals("Date")){
					
					parametroValidar.append("		"+nombreJava+" = "+nombreJava+".substring(0,10);\n\n");
					
					parametroValidar.append("		java.text.SimpleDateFormat "+nombreJava+"_sdf = new java.text.SimpleDateFormat(\"dd/MM/yyyy\");\n");
					parametroValidar.append("		if("+nombreJava+".trim().length() != "+nombreJava+"_sdf.toPattern().length()){\n");
					parametroValidar.append("			return new MensajeValidacion(false,\"Longitud de fecha incorrecta para el campo '"+nombreJava+"'\",\""+nombreJava+"\");\n");
					parametroValidar.append("		}\n");

					parametroValidar.append("		"+nombreJava+"_sdf.setLenient(false);\n");
					parametroValidar.append("		try{\n");
					parametroValidar.append("			"+nombreJava+"_sdf.parse("+nombreJava+".trim());\n");
					parametroValidar.append("		}catch (java.text.ParseException pe) {\n");
					parametroValidar.append("			return new MensajeValidacion(false,\"No es un tipo de fecha del formato 'dd/MM/yyyy' el campo '"+nombreJava+"'\",\""+nombreJava+"\");\n");
					parametroValidar.append("		}\n\n");
					
				}*/
				
			}
			
		}
		
		String instanciaDao = nombreInterfaz+"Dao";
		
		StringBuilder texto = new StringBuilder();
			
		texto.append("package "+nombrePaquete+"."+nombreCarpetaMantenimiento+";\n\n");

		texto.append("import javax.ejb.EJB;\n");
		texto.append("import javax.servlet.http.HttpServletRequest;\n");
		texto.append("import javax.servlet.http.HttpServletResponse;\n");
		
		texto.append("import "+nombrePaquete+".mantenimientos.dao.Pre"+nombreClase+"Dao;\n\n");
		texto.append("import "+nombrePaquete+".modelo.mantenimiento."+codigoMantenimiento+";\n\n");
		texto.append("import "+prefijoPaquete+".core.ejb.MantenimientoBase;\n\n");
		texto.append("import "+prefijoPaquete+".core.ejb.MantenimientoBaseImpl;\n\n");
		
		texto.append("import "+prefijoPaquete+".core.modelo.MensajeValidacion;\n");
		texto.append("import "+prefijoPaquete+".core.modelo.ParametroBusquedaBean;\n");
		texto.append("import "+prefijoPaquete+".core.util.MantenimientoUtil;\n\n");

		texto.append("public abstract class Pre"+nombreClase+"Impl  extends MantenimientoBaseImpl implements MantenimientoBase {\n\n");
	
		texto.append("	@EJB\n");
		
		texto.append("	private Pre"+nombreClase+"Dao "+instanciaDao+";\n\n");
		
		texto.append("	private static int cantidadDefecto = 10;\n\n");
	
		texto.append("	@Override\n");
		texto.append("	public Object actualizar(HttpServletRequest request,HttpServletResponse response) throws Exception {\n");
		texto.append("		return actualizarAccion(get"+codigoMantenimiento+"(request));\n");
		texto.append("	}\n\n");
		
		texto.append("	@Override\n");
		texto.append("	public Object registrar(HttpServletRequest request,HttpServletResponse response) throws Exception {\n");
		texto.append("		return registrarAccion(get"+codigoMantenimiento+"(request));\n");
		texto.append("	}\n\n");
		
		texto.append("	@Override\n");
		texto.append("	public Object cargarRegistro(HttpServletRequest request,HttpServletResponse response) throws Exception {\n");
		texto.append("		return cargarRegistroAccion(get"+codigoMantenimiento+"(request));\n");
		texto.append("	}\n\n");

		texto.append("	@Override\n");
		texto.append("	public Object eliminar(HttpServletRequest request,HttpServletResponse response) throws Exception {\n");
		texto.append("		return eliminarAccion(get"+codigoMantenimiento+"(request));\n");
		texto.append("	}\n\n");
		
		texto.append("	@Override\n");
		texto.append("	public Object listar(HttpServletRequest request,HttpServletResponse response) throws Exception {\n");
		texto.append("		int cantidad = MantenimientoUtil.getCantidad(cantidadDefecto,request.getParameter(\"cantidad\"));\n");
		texto.append("		int indice = MantenimientoUtil.paginacionIndice(request.getParameter(\"paginacionNro\"),cantidad);\n");
		texto.append("		int indicadorCargaTotal = MantenimientoUtil.indicadorCargaTotal(request.getParameter(\"cargarTotal\"));\n");
		texto.append("		return "+nombreInterfaz+"Dao.listar(get"+codigoMantenimiento+"(request), new ParametroBusquedaBean(indice,cantidad,request.getParameter(\"orden\"),indicadorCargaTotal));\n");
		texto.append("	}\n\n");
		
		texto.append("	public Object actualizarAccion("+codigoMantenimiento+" "+nombreObjeto+") throws Exception {\n");
		texto.append("		MensajeValidacion validar = validar("+nombreObjeto+");\n");
		texto.append("		if(validar.isConforme()){\n");
		texto.append("			if(cargarRegistroAccion("+nombreObjeto+") != null){\n");
		texto.append("				return "+instanciaDao+".actualizar("+nombreObjeto+");\n");
		texto.append("			} else {\n");
		texto.append("				return new MensajeValidacion(false,\"No se puede actualizar un registro no encontrado\");\n");
		texto.append("			}\n");
		texto.append("		} else {\n");
		texto.append("			return validar; \n");
		texto.append("		}\n");
		texto.append("	}\n\n");

		texto.append("	public Object registrarAccion("+codigoMantenimiento+" "+nombreObjeto+") throws Exception {\n");
		texto.append("		MensajeValidacion validar = validar("+nombreObjeto+");\n");
		texto.append("		if(validar.isConforme()){\n");
		texto.append("			if(cargarRegistroAccion("+nombreObjeto+") == null){\n");
		texto.append("				return "+instanciaDao+".registrar("+nombreObjeto+");\n");
		texto.append("			} else {\n");
		texto.append("				return new MensajeValidacion(false,\"Ya existe un registro creado con los c�digos ingresados\");\n");
		texto.append("			}\n");
		texto.append("		} else {\n");
		texto.append("			return validar; \n");
		texto.append("		}\n");
		texto.append("	}\n\n");
		
		texto.append("	public Object cargarRegistroAccion("+codigoMantenimiento+" "+nombreObjeto+") throws Exception {\n");
		texto.append("		return "+instanciaDao+".cargarRegistro("+nombreObjeto+");\n");
		texto.append("	}\n\n");

		texto.append("	public Object eliminarAccion("+codigoMantenimiento+" "+nombreObjeto+") throws Exception {\n");
		texto.append("		return "+instanciaDao+".eliminar("+nombreObjeto+");\n");
		texto.append("	}\n\n");
		
		texto.append("	private MensajeValidacion validar("+codigoMantenimiento+" "+nombreObjeto+") throws Exception{\n\n");
		texto.append(parametroValidar);
		texto.append("		return new MensajeValidacion(true);\n\n");
		texto.append("	}\n\n");
		
		texto.append("	private "+codigoMantenimiento+" get"+codigoMantenimiento+"(HttpServletRequest request) throws Exception{\n\n");
		texto.append("		"+codigoMantenimiento+" "+nombreObjeto+" = new "+codigoMantenimiento+"();\n\n");
		texto.append(parametroEnvio);
		texto.append("		"+nombreObjeto+".setUsuarioCreacion(obtenerUsuario(request).getCodigo());\n\n");
		texto.append("		return "+nombreObjeto+";\n\n");
		texto.append("	}\n\n");
			
		texto.append("}");
		
		return texto.toString();
		
	}
	
	// DAO SOURCE EJB CLIENT
	private static String cargarSourceEJBDaoLocal(int i){
		
		String nombreClase = prefijoNombre+mantenimientos.get(i).get("codigoMantenimiento");
		
		StringBuilder texto = new StringBuilder();
		
		texto.append("package "+CargaConfiguracion.configuracion.getPaqueteURLJava()+"."+nombreCarpetaMantenimiento+".dao;\n");
		texto.append("import javax.ejb.Local;\n\n");

		texto.append("@Local\n");
		texto.append("public interface "+nombreClase+"Dao extends Pre"+nombreClase+"Dao {\n\n");

		texto.append("}");
		
		return texto.toString();
		
	}
	
	// DAO SOURCE EJB SESSION
	private static String cargarSourceEJBDaoSession(int i){
		
		String nombreClase = prefijoNombre+mantenimientos.get(i).get("codigoMantenimiento");
		
		StringBuilder texto = new StringBuilder();
		
		texto.append("package "+CargaConfiguracion.configuracion.getPaqueteURLJava()+"."+nombreCarpetaMantenimiento+".dao;\n\n");
		
		texto.append("import javax.annotation.Resource;\n");
		texto.append("import javax.ejb.Stateless;\n");
		texto.append("import javax.sql.DataSource;\n");
		texto.append("import java.sql.Connection;\n\n");
		
		texto.append("@Stateless\n");
		texto.append("public class "+nombreClase+"DaoImpl extends Pre"+nombreClase+"DaoImpl implements Pre"+nombreClase+"Dao {\n\n");
		
		texto.append("	@Resource(name = \""+codigoDataSource+"\")\n");
		texto.append("	DataSource dataSource;\n\n");
		
		texto.append("	public Connection getConnection() throws Exception{\n");
		texto.append("		return dataSource.getConnection();\n");
		texto.append("	}\n\n");
		
		texto.append("}");
		
		return texto.toString();
		
	}
	
	// DAO GENERADO (PRE) EJB CLIENT
	private static String cargarGeneradoEJBDaoLocal(int i) throws Exception{
		
		StringBuilder parametroEnvio = new StringBuilder();
		
		for(int e = 0;e<atributos.size();e++){
			String nombreJava = formateaNombreAtributoJava((String) atributos.get(e).get("nombre"));
			boolean esBusqueda = (atributos.get(e).get("esBusqueda") != null)?(Boolean) atributos.get(e).get("esBusqueda"):false;
			if(esBusqueda){
				parametroEnvio.append("String "+nombreJava+", ");
			}
		}
		
		StringBuilder texto = new StringBuilder();
		
		texto.append("package "+nombrePaquete+"."+nombreCarpetaMantenimiento+".dao;\n\n");
		
		texto.append("import pe.com.financiero.bpm.core.modelo.ParametroBusquedaBean;\n");
		
		texto.append("import "+CargaConfiguracion.configuracion.getPaqueteURLJava()+".modelo.mantenimiento."+codigoMantenimiento+";\n\n");
		
		texto.append("public interface Pre"+nombreClase+"Dao {\n\n");

		texto.append("	Object registrar("+codigoMantenimiento+" "+nombreObjeto+") throws Exception;\n\n");
		
		texto.append("	Object actualizar("+codigoMantenimiento+" "+nombreObjeto+") throws Exception;\n\n");
		
		texto.append("	Object listar("+codigoMantenimiento+" "+nombreObjeto+", ParametroBusquedaBean parametroBusquedaBean) throws Exception;\n\n");
		
		texto.append("	Object eliminar("+codigoMantenimiento+" "+nombreObjeto+") throws Exception;\n\n");
		
		texto.append("	Object cargarRegistro("+codigoMantenimiento+" "+nombreObjeto+") throws Exception;\n\n");
		
		texto.append("}");
		
		return texto.toString();
		
	}
	
	// DAO GENERADO (PRE) EJB SESSION
	private static String cargarGeneradoEJBDaoSession(int i) throws Exception{
		
		String nombreSPActualizar = esquemaBD+"."+prefijoNombreSP+"_"+codigoMantenimientoSEP.toUpperCase()+"_UPD";
		String nombreSPConsultar = esquemaBD+"."+prefijoNombreSP+"_"+codigoMantenimientoSEP.toUpperCase()+"_SEL";
		String nombreSPListar = esquemaBD+"."+prefijoNombreSP+"_"+codigoMantenimientoSEP.toUpperCase()+"_LIS";
		String nombreSPInsertar = esquemaBD+"."+prefijoNombreSP+"_"+codigoMantenimientoSEP.toUpperCase()+"_INS";
		String nombreSPEliminar = esquemaBD+"."+prefijoNombreSP+"_"+codigoMantenimientoSEP.toUpperCase()+"_DEL";
		
		// METODO ELIMINAR
		String llave_parametroEnvio;
		
		// TODOS
		StringBuilder todos_sig = new StringBuilder();
		StringBuilder todos_par = new StringBuilder();
		StringBuilder todos_lis = new StringBuilder();
		
		// ES LLAVE PRIMARIA
		StringBuilder esPrimaria_sig = new StringBuilder();
		StringBuilder esPrimaria_par = new StringBuilder();
		
		// ES BUSQUEDA
		StringBuilder busqueda_sig = new StringBuilder();
		StringBuilder busqueda_par = new StringBuilder();
		
		// ES LISTADO
		StringBuilder esListado_par = new StringBuilder();
		
		int e,contC = 0,contL = 0;
		for(e = 0;e<atributos.size();e++){
			
			String nombre = (String) atributos.get(e).get("nombre");
			String nombreJava = formateaNombreAtributoJava2(nombre);
			String tipoDato = (String) atributos.get(e).get("tipoDato");
			boolean esLlavePrimaria = (atributos.get(e).get("esLlavePrimaria") != null)?(Boolean) atributos.get(e).get("esLlavePrimaria"):false;
			boolean esBusqueda = (atributos.get(e).get("esBusqueda") != null)?(Boolean) atributos.get(e).get("esBusqueda"):false;
			boolean esListado = (atributos.get(e).get("esListado") != null)?(Boolean) atributos.get(e).get("esListado"):false;
			
			todos_sig.append("?,");
			
			String tipoDatoPrim =  StringUtils.capitalize(tipoDato.toLowerCase());
			if(tipoDatoPrim.equals("Int")){
				todos_par.append("			if("+nombreObjeto+".get"+ nombreJava+"() == null){\n");
				todos_par.append("				cstmt.setNull("+(e+1)+", Types.INTEGER);\n");
				todos_par.append("			} else { \n");
				todos_par.append("				cstmt.set"+tipoDatoPrim+"("+(e+1)+", "+nombreObjeto+".get"+ nombreJava+"());\n");
				todos_par.append("			}\n");
			} else if(tipoDatoPrim.equals("Boolean")){
				todos_par.append("			if("+nombreObjeto+".get"+ nombreJava+"() == null){\n");
				todos_par.append("				cstmt.setNull("+(e+1)+", Types.CHAR);\n");
				todos_par.append("			} else { \n");
				todos_par.append("				cstmt.set"+tipoDatoPrim+"("+(e+1)+", "+nombreObjeto+".get"+ nombreJava+"());\n");
				todos_par.append("			}\n");
			} else {
				todos_par.append("			cstmt.set"+tipoDatoPrim+"("+(e+1)+", "+nombreObjeto+".get"+ nombreJava+"());\n");
			}
			
			todos_lis.append("				"+nombreObjeto+"2.set"+nombreJava+"(rs.get"+StringUtils.capitalize(tipoDato.toLowerCase())+"(\""+nombre+"\"));\n");
			
			if (esLlavePrimaria){
				contC++;
				esPrimaria_sig.append("?,");
				esPrimaria_par.append("			cstmt.set"+StringUtils.capitalize(tipoDato.toLowerCase())+"("+(contC)+", "+nombreObjeto+".get"+ nombreJava+"());\n");
			}
			if(esBusqueda){
				contL++;
				busqueda_sig.append("?,");
				if(tipoDatoPrim.equals("Int")){
					busqueda_par.append("			if("+nombreObjeto+".get"+ nombreJava+"() == null){\n");
					busqueda_par.append("				cstmt.setNull("+(contL)+", Types.INTEGER);\n");
					busqueda_par.append("			} else { \n");
					busqueda_par.append("				cstmt.set"+tipoDatoPrim+"("+(contL)+", "+nombreObjeto+".get"+ nombreJava+"());\n");
					busqueda_par.append("			}\n");
				} else if(tipoDatoPrim.equals("Boolean")){
					busqueda_par.append("			if("+nombreObjeto+".get"+ nombreJava+"() == null){\n");
					busqueda_par.append("				cstmt.setNull("+(contL)+", Types.CHAR);\n");
					busqueda_par.append("			} else { \n");
					busqueda_par.append("				cstmt.set"+tipoDatoPrim+"("+(contL)+", "+nombreObjeto+".get"+ nombreJava+"());\n");
					busqueda_par.append("			}\n");
				} else {
					busqueda_par.append("			cstmt.set"+StringUtils.capitalize(tipoDato)+"("+contL+", "+nombreObjeto+".get"+nombreJava+"());\n");
				}
			}
			if(esListado || esLlavePrimaria){
				esListado_par.append("				"+nombreObjeto+"2.set"+nombreJava+"(rs.get"+StringUtils.capitalize(tipoDato.toLowerCase())+"(\""+nombre+"\"));\n");
			}
			
		}
		
		todos_par.append("			cstmt.setString("+(e+1)+", "+nombreObjeto+".getUsuarioCreacion());\n");
		
		// METODO ELIMINAR
		llave_parametroEnvio = esPrimaria_sig.toString().substring(0,esPrimaria_sig.toString().length()-1);

		
		StringBuilder texto = new StringBuilder();
		
		texto.append("package "+CargaConfiguracion.configuracion.getPaqueteURLJava()+"."+nombreCarpetaMantenimiento+".dao;\n\n");

		texto.append("import java.sql.CallableStatement;\n");
		texto.append("import java.sql.Connection;\n");
		texto.append("import java.sql.ResultSet;\n");
		texto.append("import java.sql.SQLException;\n");
		texto.append("import java.sql.Types;\n");
		texto.append("import java.util.ArrayList;\n");
		texto.append("import java.util.List;\n\n");
		
		texto.append("import org.apache.logging.log4j.LogManager;\n");
		texto.append("import org.apache.logging.log4j.Logger;\n\n");
		
		texto.append("import "+nombrePaquete+".modelo.mantenimiento."+codigoMantenimiento+";\n");
		texto.append("import "+prefijoPaquete+".core.modelo.GrillaLista;\n");
		texto.append("import pe.com.financiero.bpm.core.modelo.ParametroBusquedaBean;\n\n");
		
		texto.append("public abstract class Pre"+nombreClase+"DaoImpl implements Pre"+nombreClase+"Dao {\n\n");

		texto.append("	static final Logger logger = LogManager.getLogger(Pre"+nombreClase+"DaoImpl.class.getName());\n");
		texto.append("	public abstract Connection getConnection() throws Exception;\n\n");
		
		texto.append("	@Override\n");
		texto.append("	public Object actualizar("+codigoMantenimiento+" "+nombreObjeto+") throws Exception {\n");
				
		texto.append("		Connection conn = null;\n");
		texto.append("		CallableStatement cstmt = null;\n");
		texto.append("		try {\n");
		texto.append("	        conn = getConnection();\n");
		texto.append("	        cstmt = conn.prepareCall(\"{Call "+nombreSPActualizar+"("+todos_sig.toString()+"?)}\");\n");
		texto.append(todos_par.toString());
		texto.append("	        cstmt.execute();\n");
		texto.append("	        return true;\n");
		texto.append("		} catch (SQLException e) {\n");
		texto.append("			logger.error(e.getMessage(), e);\n");
		texto.append("			throw new Exception(e);\n");
		texto.append("		} finally {\n");
		texto.append("			if (cstmt != null){		cstmt.close();		}\n");
		texto.append("			if (conn != null){		conn.close();		}\n");
		texto.append("		}\n");
		texto.append("	}\n\n");

		texto.append("	@Override\n");
		texto.append("	public Object cargarRegistro("+codigoMantenimiento+" "+nombreObjeto+") throws Exception {\n");
		texto.append("		Connection conn = null;\n");
		texto.append("		CallableStatement cstmt = null;\n");
		texto.append("		ResultSet rs = null;\n");
		texto.append("		"+codigoMantenimiento+" "+nombreObjeto+"2 = null;\n");
		texto.append("		try {\n");
		texto.append("	        conn = getConnection();\n");
		texto.append("	        cstmt = conn.prepareCall(\"{Call "+nombreSPConsultar+"("+llave_parametroEnvio+")}\");\n");
		texto.append(esPrimaria_par.toString());
		texto.append("	        cstmt.execute();\n");
		texto.append("	        rs = cstmt.getResultSet();\n");
		texto.append("	        if (rs.next()){\n");
		texto.append("	        	"+nombreObjeto+"2 = new "+codigoMantenimiento+"(); \n");
		texto.append(todos_lis.toString());
		texto.append("	        }\n");
		texto.append("        } catch (SQLException e) {\n");
		texto.append("			logger.error(e.getMessage(), e);\n");
		texto.append("			throw new Exception(e);\n");
		texto.append("		} finally {\n");
		texto.append("			if (rs!=null){     rs.close();    }\n");
		texto.append("		    if (cstmt!=null){  cstmt.close(); }\n");
		texto.append("		    if (conn!=null){   conn.close();  }\n");
		texto.append("		}\n");
		texto.append("		return "+nombreObjeto+"2;\n");
		texto.append("	}\n");

		texto.append("	@Override\n");
		texto.append("	public Object eliminar("+codigoMantenimiento+" "+nombreObjeto+") throws Exception {\n");
		texto.append("		Connection conn = null;\n");
		texto.append("		CallableStatement cstmt = null;\n");
		texto.append("		try {\n");
		texto.append("			conn = getConnection();\n");
		texto.append("			cstmt = conn.prepareCall(\"{call "+nombreSPEliminar+"("+llave_parametroEnvio+")}\");\n");
		texto.append(esPrimaria_par.toString());
		texto.append("			cstmt.executeUpdate();\n");
		texto.append("			return true;\n");
		texto.append("		} catch (SQLException e) {\n");
		texto.append("			logger.error(e.getMessage(), e);\n");
		texto.append("			throw new Exception(e);\n");
		texto.append("		} finally {\n");
		texto.append("			if (cstmt != null){		cstmt.close();		}\n");
		texto.append("			if (conn != null){		conn.close();		}\n");
		texto.append("		}\n");
		texto.append("	}\n\n");

		contL++;
		texto.append("	@Override\n");
		texto.append("	public Object listar("+codigoMantenimiento+" "+nombreObjeto+", ParametroBusquedaBean parametroBusquedaBean) throws Exception {\n");
		texto.append("		Connection conn = null;\n");
		texto.append("		CallableStatement cstmt = null;\n");
		texto.append("		ResultSet rs = null;\n");
		texto.append("		GrillaLista grilla"+codigoMantenimiento+"s = new GrillaLista();\n");
		texto.append("		try {\n");
		texto.append("			conn = getConnection();\n");
		texto.append("			cstmt = conn.prepareCall(\"{call "+nombreSPListar+"("+busqueda_sig.toString()+"?,?,?,?,?)}\");\n");
		texto.append(busqueda_par.toString());
		texto.append("			cstmt.setInt("+(contL++)+", parametroBusquedaBean.getIndice());\n");
		texto.append("			cstmt.setInt("+(contL++)+", parametroBusquedaBean.getCantidad());\n");
		texto.append("			cstmt.setString("+(contL++)+", parametroBusquedaBean.getOrden());\n");
		texto.append("			cstmt.setInt("+(contL++)+", parametroBusquedaBean.getIndicadorCargaTotal());\n");
		texto.append("			cstmt.registerOutParameter("+(contL++)+", Types.INTEGER);\n");
		
		texto.append("			cstmt.execute();\n\n");
		
		texto.append("			List<"+codigoMantenimiento+"> "+nombreObjeto+"s = new ArrayList<"+codigoMantenimiento+">();\n");
		texto.append("			rs = cstmt.getResultSet();\n");
		texto.append("			grilla"+codigoMantenimiento+"s.setTotalItems(cstmt.getInt(\"PO_TOTAL_SOLICITUDES\"));\n");
		texto.append("			grilla"+codigoMantenimiento+"s.setItemsxPagina(parametroBusquedaBean.getCantidad());\n");
		texto.append("			while (rs.next()) {\n");
		texto.append("				"+codigoMantenimiento+" "+nombreObjeto+"2 = new "+codigoMantenimiento+"();\n");
		texto.append(esListado_par.toString());
		texto.append("				"+nombreObjeto+"s.add("+nombreObjeto+"2);\n");
		texto.append("			}\n\n");
		texto.append("			grilla"+codigoMantenimiento+"s.setItems("+nombreObjeto+"s);\n");
		texto.append("			return grilla"+codigoMantenimiento+"s;\n");
		texto.append("		} catch (SQLException e) {\n");
		texto.append("			logger.error(e.getMessage(), e);\n");
		texto.append("			throw new Exception(e);\n");
		texto.append("		} finally {\n");
		texto.append("			if (rs!=null){     rs.close();    }\n");
		texto.append("		    if (cstmt!=null){  cstmt.close(); }\n");
		texto.append("		    if (conn!=null){   conn.close();  }\n");
		texto.append("		}\n");
			
		texto.append("	}\n\n");

		texto.append("	@Override\n");
		texto.append("	public Object registrar("+codigoMantenimiento+" "+nombreObjeto+") throws Exception {\n");
		texto.append("		Connection conn = null;\n");
		texto.append("		CallableStatement cstmt = null;\n");
		texto.append("		try {\n");
		texto.append("			conn = getConnection();\n");
		texto.append("			cstmt = conn.prepareCall(\"{call "+nombreSPInsertar+"("+todos_sig.toString()+"?)}\");\n");
		texto.append(todos_par.toString());
		texto.append("			cstmt.executeUpdate();\n");
		texto.append("			return true;\n");
		texto.append("		} catch (SQLException e) {\n");
		texto.append("			logger.error(e.getMessage(), e);\n");
		texto.append("			throw new Exception(e);\n");
		texto.append("		} finally {\n");
		texto.append("			if (cstmt != null){		cstmt.close();		}\n");
		texto.append("			if (conn != null){		conn.close();		}\n");
		texto.append("		}\n");
		texto.append("	}\n\n");
		
		texto.append("}");
		
		return texto.toString();
		
	}
		
	private static void crearTablaBD(int i) throws Exception{
		
		//Map<String,String> valores = new HashMap<String,String>();
		
		String codigoMantenimientoBD = prefijoNombre+(String) mantenimientos.get(i).get("codigoMantenimiento");
		List<Map<String, Object>> atributos = (List<Map<String, Object>>) jpo.tabla("MantenimientoAtributo").donde("codigoProyecto = '"+codigoProyecto+"' AND numeroVersion = '"+numeroVersion+"' AND codigoMantenimiento = '"+codigoMantenimiento+"'").seleccionar("*");
		
		String nombreMant = (String) mantenimientos.get(i).get("nombre");

		// COMPONENTES
		
		String tabla = "MAE_"+codigoMantenimientoSEP.toUpperCase();
		String nombreTabla = esquemaBD+"."+tabla;
		BD_Tablas.append("CREATE TABLE "+nombreTabla+" (\n");
		BD_TablasDelete.insert(0,"DROP TABLE "+nombreTabla+Del+"\n");
		
		BD_Insert.append("INSERT INTO BFP_PORTAL.BFPBM_SUB_MODULO_WEB(CODIGO_SUB_MODULO_WEB, CODIGO_MODULO_WEB, DESCRIPCION, URL, ORDEN, ESTADO, USUARIO_CREACION, FECHA_CREACION, USUARIO_ACTUALIZACION, FECHA_ACTUALIZACION) VALUES ('"+codigoMantenimientoBD+"', 'mant', '"+nombreMant+"', '"+codigoMantenimientoBD+"V"+numeroVersion+".html', 1, '1', 'OSCHUP', '2014-8-6 12:17:42', 'OSCHUP', '2014-8-6 12:17:42')"+Del+"\n");
		BD_Delete.insert(0,"DELETE FROM BFP_PORTAL.BFPBM_SUB_MODULO_WEB WHERE CODIGO_SUB_MODULO_WEB = '"+codigoMantenimientoBD+"' AND CODIGO_MODULO_WEB = 'mant'"+Del+"\n");

		List<Map<String, Object>> roles = (List<Map<String, Object>>) jpo.tabla("MantenimientoRoles").donde("codigoProyecto = '"+codigoProyecto+"' AND numeroVersion = '"+numeroVersion+"' AND codigoMantenimiento = '"+codigoMantenimiento+"'").seleccionar("*");
		
		//String unidadNegocio = (String) CargaConfiguracion.config.get("unidadNegocio");
		String unidadNegocio = "BE";
		String producto = (String) CargaConfiguracion.config.get("producto");
		for(int e = 0;e<roles.size();e++){
			String codigoRol = (String) roles.get(e).get("codigoRol");
			BD_Insert.append("INSERT INTO BFP_PORTAL.BFPBM_PERFIL_PRODUCTO_SUB_MODULO_WEB(CODIGO_PERFIL,CODIGO_PRODUCTO,CODIGO_UNIDAD_NEGOCIO,CODIGO_SUB_MODULO_WEB,ESTADO,USUARIO_CREACION,FECHA_CREACION,USUARIO_ACTUALIZACION,FECHA_ACTUALIZACION) VALUES ('"+codigoRol+"', '"+producto+"', '"+unidadNegocio+"', '"+codigoMantenimientoBD+"', '1', 'OSCHUP', '2014-8-6 12:17:42', 'OSCHUP', '2014-8-6 12:17:42')"+Del+"\n");
			BD_Delete.insert(0,"DELETE FROM BFP_PORTAL.BFPBM_PERFIL_PRODUCTO_SUB_MODULO_WEB WHERE CODIGO_PERFIL = '"+codigoRol+"' AND CODIGO_PRODUCTO = '"+producto+"' AND CODIGO_UNIDAD_NEGOCIO = '"+unidadNegocio+"' AND CODIGO_SUB_MODULO_WEB = '"+codigoMantenimientoBD+"'"+Del+"\n");
		}
		
		// STORE INSERTAR
		String nombreSPInsertar = esquemaBD+"."+prefijoNombreSP+"_"+codigoMantenimientoSEP.toUpperCase()+"_INS";
		BD_SP_Insert.append("CREATE OR REPLACE PROCEDURE "+nombreSPInsertar+" (\n");
		
		StringBuilder BD_SP_insertA = new StringBuilder();
		StringBuilder BD_SP_insertB = new StringBuilder();
		StringBuilder BD_SP_insertC = new StringBuilder();
		BD_SP_insertB.append("		");
		BD_SP_insertC.append("		");
		
		// STORE ACTUALIZAR	
		String nombreSPActualizar = esquemaBD+"."+prefijoNombreSP+"_"+codigoMantenimientoSEP.toUpperCase()+"_UPD";
		BD_SP_Update.append("CREATE OR REPLACE PROCEDURE "+nombreSPActualizar+" (\n");
		
		StringBuilder BD_SP_updateA = new StringBuilder();
		StringBuilder BD_SP_updateB = new StringBuilder();
		StringBuilder BD_SP_updateC = new StringBuilder();
		
		// STORE ELIMINAR	
		String nombreSPEliminar= esquemaBD+"."+prefijoNombreSP+"_"+codigoMantenimientoSEP.toUpperCase()+"_DEL";
		BD_SP_Delete.append("CREATE OR REPLACE PROCEDURE "+nombreSPEliminar+" (\n");
		
		StringBuilder BD_SP_deleteA = new StringBuilder();
		StringBuilder BD_SP_deleteB = new StringBuilder();
		
		// STORE SELECCIONAR	
		String nombreSPSelect= esquemaBD+"."+prefijoNombreSP+"_"+codigoMantenimientoSEP.toUpperCase()+"_SEL";
		BD_SP_Select.append("CREATE OR REPLACE PROCEDURE "+nombreSPSelect+" (\n");
		
		StringBuilder BD_SP_SelectA = new StringBuilder();
		StringBuilder BD_SP_SelectB = new StringBuilder();
		StringBuilder BD_SP_SelectC = new StringBuilder();
		
		// STORE LISTAR	
		String nombreSPList= esquemaBD+"."+prefijoNombreSP+"_"+codigoMantenimientoSEP.toUpperCase()+"_LIS";
		BD_SP_List.append("CREATE OR REPLACE PROCEDURE "+nombreSPList+" (\n");
		
		StringBuilder BD_SP_ListA = new StringBuilder();
		StringBuilder BD_SP_ListB = new StringBuilder();
		StringBuilder BD_SP_ListC = new StringBuilder();
		StringBuilder BD_SP_ListD = new StringBuilder();
		StringBuilder BD_SP_ListE = new StringBuilder();
		StringBuilder BD_TablasPK = new StringBuilder();
		
		for(int e = 0;e<atributos.size();e++){

			String nombre = (String) atributos.get(e).get("nombre");
			String tipoDato = (String) atributos.get(e).get("tipoDato");
			Integer longitud = (Integer) atributos.get(e).get("longitud") ;
			boolean esLlavePrimaria = (atributos.get(e).get("esLlavePrimaria") != null)?(Boolean) atributos.get(e).get("esLlavePrimaria"):false;
			boolean esListado = (atributos.get(e).get("esListado") != null)?(Boolean) atributos.get(e).get("esListado"):false;
			boolean esBusqueda = (atributos.get(e).get("esBusqueda") != null)?(Boolean) atributos.get(e).get("esBusqueda"):false;
			boolean esObligatorio = (atributos.get(e).get("esObligatorio") != null)?(Boolean) atributos.get(e).get("esObligatorio"):false;
			
			String longitudStr = ((longitud != null)?"("+(longitud)+")":"");
			
			// TABLA
			BD_Tablas.append("	"+MapeoUtil.derecha("BD",nombre)+" "+tipoDatoBD(tipoDato)+longitudStr+((esObligatorio)?" NOT NULL":"")+",\n");
			
			
			
			if(esLlavePrimaria){
				BD_TablasPK.append("\""+nombre+"\",");
			}
			
			// STORE INSERTAR
			BD_SP_insertA.append("	IN PI_"+nombre+" "+tipoDatoBD(tipoDato)+longitudStr+",\n");
			BD_SP_insertB.append(nombre+",");
			BD_SP_insertC.append("PI_"+nombre+",");
			
			// STORE ACTUALIZAR
			BD_SP_updateA.append("	IN PI_"+nombre+" "+tipoDatoBD(tipoDato)+longitudStr+",\n");
			if(esLlavePrimaria){
				BD_SP_updateC.append(nombre+" = PI_"+nombre+" AND ");
			} else {
				BD_SP_updateB.append(nombre+" = PI_"+nombre+", ");
			}
			
			// STORE ELIMINAR
			if(esLlavePrimaria){
				BD_SP_deleteA.append("	IN PI_"+nombre+" "+tipoDatoBD(tipoDato)+longitudStr+",\n");
				BD_SP_deleteB.append(nombre+" = PI_"+nombre+" AND ");
			}
			
			// STORE SELECCIONAR
			if(esLlavePrimaria){
				BD_SP_SelectA.append("	IN PI_"+nombre+" "+tipoDatoBD(tipoDato)+longitudStr+",\n");
				BD_SP_SelectC.append(nombre+" = PI_"+nombre+" AND ");
			}
			BD_SP_SelectB.append(nombre+",");
			
			// STORE LISTAR
			if(esBusqueda){
				String longitudStrLista = longitudStr;
				if(longitud != null && longitud<=6){
					longitudStrLista = "(8)";
				}
				if(tipoDatoBD(tipoDato).equals("DATE")){
					BD_SP_ListE.append("	DECLARE V_PI_"+nombre+"_DESDE VARCHAR(50);\n");
					BD_SP_ListE.append("	DECLARE V_PI_"+nombre+"_HASTA VARCHAR(50);\n");
					
					BD_SP_ListA.append("	IN  PI_"+nombre+"_DESDE "+tipoDatoBD(tipoDato)+longitudStrLista+",\n");
					BD_SP_ListA.append("	IN  PI_"+nombre+"_HASTA "+tipoDatoBD(tipoDato)+longitudStrLista+",\n");
					BD_SP_ListB.append("	IF (PI_"+nombre+"_DESDE IS NULL)  THEN SET V_"+nombre+"_DESDE = 'NULL'; ELSE SET V_"+nombre+"_DESDE = ''''||VARCHAR_FORMAT(PI_"+nombre+"_DESDE,'YYYY-MM-DD')||' 00:00:00'''; END IF;\n");
					BD_SP_ListB.append("	IF (PI_"+nombre+"_HASTA IS NULL)  THEN SET V_"+nombre+"_HASTA = 'NULL'; ELSE SET V_"+nombre+"_HASTA = ''''||VARCHAR_FORMAT(PI_"+nombre+"_HASTA,'YYYY-MM-DD')||' 23:59:59'''; END IF;\n");
					
					BD_SP_ListD.append("				AND ('||V_"+nombre+"_DESDE||' IS NULL OR "+nombre+" >= '||V_"+nombre+"_DESDE||')\n");
					BD_SP_ListD.append("				AND ('||V_"+nombre+"_HASTA||' IS NULL OR "+nombre+" <= '||V_"+nombre+"_HASTA||')\n");
					
				} else {
					
					BD_SP_ListA.append("	IN  PI_"+nombre+" "+tipoDatoBD(tipoDato)+longitudStrLista+",\n");
					
					if(tipoDatoBD(tipoDato).equals("INTEGER") || tipoDatoBD(tipoDato).equals("CHAR")){
						BD_SP_ListE.append("	DECLARE V_PI_"+nombre+" VARCHAR(50);\n");
						BD_SP_ListB.append("	IF (PI_"+nombre+" IS NULL)  THEN SET V_PI_"+nombre+" = 'NULL'; ELSE SET V_PI_"+nombre+" = ''''||PI_"+nombre+"||''''; END IF;\n");
					} else if(tipoDatoBD(tipoDato).equals("VARCHAR")) {
						BD_SP_ListB.append("	IF (PI_"+nombre+" IS NULL)  THEN SET PI_"+nombre+" = 'NULL'; ELSE SET PI_"+nombre+" = '''%'||PI_"+nombre+"||'%'''; END IF;\n");
					} else {
						BD_SP_ListB.append("	IF (PI_"+nombre+" IS NULL)  THEN SET PI_"+nombre+" = 'NULL'; ELSE SET PI_"+nombre+" = ''''||PI_"+nombre+"||''''; END IF;\n");
					}
					
					if(tipoDatoBD(tipoDato).equals("VARCHAR")){
						BD_SP_ListD.append("				AND ('||PI_"+nombre+"||' IS NULL OR "+nombre+" LIKE '||PI_"+nombre+"||')\n");
					} else if(tipoDatoBD(tipoDato).equals("INTEGER") || tipoDatoBD(tipoDato).equals("CHAR")){
						BD_SP_ListD.append("				AND ('||V_PI_"+nombre+"||' IS NULL OR "+nombre+" = '||V_PI_"+nombre+"||')\n");
					} else {
						BD_SP_ListD.append("				AND ('||PI_"+nombre+"||' IS NULL OR "+nombre+" = '||PI_"+nombre+"||')\n");
					}
					
				}
				
			}
			
			if(esListado || esLlavePrimaria){
				BD_SP_ListC.append(nombre+",");
			}
			
			
			
			
		}
		
			// TABLA
			BD_Tablas.append("	USUARIO_CREACION		       VARCHAR(50) NOT NULL,\n");
			BD_Tablas.append("	FECHA_CREACION			       TIMESTAMP NOT NULL,\n");
			BD_Tablas.append("	USUARIO_ACTUALIZACION	       VARCHAR(50) NOT NULL,\n");
			BD_Tablas.append("	FECHA_ACTUALIZACION		       TIMESTAMP NOT NULL\n");
						
			
			// STORE INSERTAR
			BD_SP_insertA.append("	IN PI_USUARIO_CREACION VARCHAR(50)\n");
			
			BD_SP_insertB.append("USUARIO_CREACION,FECHA_CREACION,USUARIO_ACTUALIZACION,FECHA_ACTUALIZACION\n");
			
			BD_SP_insertC.append("PI_USUARIO_CREACION,CURRENT TIMESTAMP,PI_USUARIO_CREACION,CURRENT TIMESTAMP\n");
			
			// STORE ACTUALIZAR
			BD_SP_updateA.append("	IN PI_USUARIO_ACTUALIZACION VARCHAR(50)\n");
			
			BD_SP_updateB.append("USUARIO_ACTUALIZACION = PI_USUARIO_ACTUALIZACION, FECHA_ACTUALIZACION = CURRENT TIMESTAMP\n");
			
			// STORE LISTAR
			BD_SP_ListA.append("	IN  PI_PAGINADO_INDICE INTEGER,\n");
			BD_SP_ListA.append("	IN  PI_PAGINADO_CANTIDAD INTEGER,\n");
			BD_SP_ListA.append("	IN  PI_ORDEN VARCHAR(5),\n");
			BD_SP_ListA.append("	IN  PI_INDICADOR_CARGA_TOTAL INTEGER,\n");
			BD_SP_ListA.append("	OUT PO_TOTAL_SOLICITUDES INTEGER\n");
			
			BD_SP_ListB.append("	IF (PI_ORDEN IS NULL) THEN SET PI_ORDEN = 'ASC'; END IF;");
			
			
		// TABLA
		BD_Tablas.append(")"+Del+"\n\n");
		
		// PKs
		if(BD_TablasPK.length()>0){
			BD_Tablas.append("ALTER TABLE "+nombreTabla+" ADD CONSTRAINT \"PK_"+tabla+"\" PRIMARY KEY ("+BD_TablasPK.substring(0,BD_TablasPK.length()-1)+")"+Del+"\n\n");
		}
		
		// STORE INSERTAR
		BD_SP_Insert.append(BD_SP_insertA.toString());
		BD_SP_Insert.append(")\n");
		BD_SP_Insert.append("	SPECIFIC "+nombreSPInsertar+"\n");
		BD_SP_Insert.append("BEGIN\n");
		BD_SP_Insert.append("	INSERT INTO "+nombreTabla+" (\n");
		BD_SP_Insert.append(BD_SP_insertB.toString());
		BD_SP_Insert.append("	) VALUES (\n");
		BD_SP_Insert.append(BD_SP_insertC.toString());
		BD_SP_Insert.append("	);\n");
		BD_SP_Insert.append("END"+Del+"\n\n");
				
		// STORE ACTUALIZAR
		BD_SP_Update.append(BD_SP_updateA.toString());
		BD_SP_Update.append(")\n");
		BD_SP_Update.append("	SPECIFIC "+nombreSPActualizar+"\n");
		BD_SP_Update.append("BEGIN\n");
		BD_SP_Update.append("	UPDATE "+nombreTabla+" SET \n");
		BD_SP_Update.append("		"+BD_SP_updateB.toString());
		BD_SP_Update.append("	WHERE \n");
		BD_SP_Update.append("		"+MapeoUtil.cortarDerecha(BD_SP_updateC.toString(),5)+";\n");
		BD_SP_Update.append("END"+Del+"\n\n");
		
		// STORE ELIMINAR
		BD_SP_Delete.append(BD_SP_deleteA.toString().substring(0,BD_SP_deleteA.length()-2));
		BD_SP_Delete.append("\n)\n");
		BD_SP_Delete.append("	SPECIFIC "+nombreSPEliminar+"\n");
		BD_SP_Delete.append("BEGIN\n");
		BD_SP_Delete.append("	DELETE FROM "+nombreTabla+" WHERE \n");
		BD_SP_Delete.append("		"+MapeoUtil.cortarDerecha(BD_SP_deleteB.toString(),5)+";\n");
		BD_SP_Delete.append("END"+Del+"\n\n");
		
		// STORE SELECCIONAR
		BD_SP_Select.append(MapeoUtil.cortarDerecha(BD_SP_SelectA.toString(),2));
		BD_SP_Select.append("\n)\n");
		BD_SP_Select.append("	SPECIFIC "+nombreSPSelect+"\n");
		BD_SP_Select.append("	DYNAMIC RESULT SETS 1\n");
		BD_SP_Select.append("	LANGUAGE SQL\n");
		BD_SP_Select.append("BEGIN\n");
		BD_SP_Select.append("	DECLARE r1 CURSOR WITH RETURN TO CLIENT FOR\n");
		BD_SP_Select.append("		SELECT\n");
		BD_SP_Select.append("			"+MapeoUtil.cortarDerecha(BD_SP_SelectB.toString(),1)+"\n");
		BD_SP_Select.append("		FROM "+nombreTabla+" WHERE \n");
		BD_SP_Select.append("		"+MapeoUtil.cortarDerecha(BD_SP_SelectC.toString(),5)+";\n");
		BD_SP_Select.append("	OPEN r1;\n");
		BD_SP_Select.append("END"+Del+"\n\n");
		
		// STORE LISTAR
		BD_SP_List.append(BD_SP_ListA.toString());
		BD_SP_List.append(")\n");
		BD_SP_List.append("RESULT SETS 1\n");
		BD_SP_List.append("LANGUAGE SQL\n");
		BD_SP_List.append("BEGIN\n");
		BD_SP_List.append("	DECLARE SQLCODE INT DEFAULT 0;\n");
		BD_SP_List.append("	DECLARE V_DINAMICO_c1 VARCHAR(4000);\n");
		BD_SP_List.append("	DECLARE V_DINAMICO_c2 VARCHAR(4000);\n");
		BD_SP_List.append("	DECLARE V_SQL_QUERY VARCHAR(4000);\n");
		BD_SP_List.append("	DECLARE V_SQL_PAGINACION VARCHAR(4000);\n");
		BD_SP_List.append("	DECLARE V_SQL_TOTAL VARCHAR(4000);\n");
		BD_SP_List.append("	DECLARE V_SQL_TOTAL_STR VARCHAR(5);\n");
		BD_SP_List.append(BD_SP_ListE.toString());
		BD_SP_List.append("	DECLARE V_CANTIDAD VARCHAR(200);\n");
		BD_SP_List.append("	DECLARE c1 CURSOR WITH RETURN FOR V_DINAMICO_c1;\n");
		BD_SP_List.append("	DECLARE c2 CURSOR WITH RETURN FOR V_DINAMICO_c2;\n");
		
		BD_SP_List.append("	SET PO_TOTAL_SOLICITUDES = 0;\n\n");
		BD_SP_List.append(BD_SP_ListB.toString()+"\n\n");
		BD_SP_List.append("	SET V_SQL_QUERY = '\n");
		BD_SP_List.append("		SELECT \n");
		BD_SP_List.append("			ROWNUMBER() OVER() AS NUMERO_FILA, \n");
		BD_SP_List.append("			RESULTADO.*\n");
		BD_SP_List.append("		FROM (\n");
		BD_SP_List.append("			SELECT \n");
		BD_SP_List.append("				"+MapeoUtil.cortarDerecha(BD_SP_ListC.toString(),1)+"\n");
		BD_SP_List.append("			FROM \n");
		BD_SP_List.append("				"+nombreTabla+"\n");
		BD_SP_List.append("			WHERE 1 = 1\n");
		BD_SP_List.append(BD_SP_ListD.toString());
		BD_SP_List.append("        	ORDER BY\n");
		BD_SP_List.append("				FECHA_CREACION '||PI_ORDEN||'\n");
		BD_SP_List.append("      	) AS RESULTADO\n");
		BD_SP_List.append("	';\n\n");
		
		BD_SP_List.append("	IF (PI_PAGINADO_CANTIDAD = 0) THEN \n");
		BD_SP_List.append("		SET V_CANTIDAD = ''; \n");
		BD_SP_List.append("	ELSE \n");
		BD_SP_List.append("		SET V_CANTIDAD = 'WHERE RESULTADO_PAGINADO.NUMERO_FILA > '||(PI_PAGINADO_INDICE)||' AND RESULTADO_PAGINADO.NUMERO_FILA <= '||(PI_PAGINADO_INDICE+PI_PAGINADO_CANTIDAD);\n");
		BD_SP_List.append("	END IF;\n");
		BD_SP_List.append("	SET V_SQL_PAGINACION = 'SELECT * FROM ( '||V_SQL_QUERY||' ) AS RESULTADO_PAGINADO ' || V_CANTIDAD;\n");
		
		BD_SP_List.append("	PREPARE V_DINAMICO_c1 FROM V_SQL_PAGINACION;\n\n");
		BD_SP_List.append("	IF (PI_INDICADOR_CARGA_TOTAL = 1)  THEN\n");
		BD_SP_List.append("		SET V_SQL_TOTAL = 'SELECT COUNT(*) FROM ( '||V_SQL_QUERY||' )';\n");
		BD_SP_List.append("		PREPARE V_DINAMICO_c2 FROM V_SQL_TOTAL;\n");
		BD_SP_List.append("		OPEN c2;\n");
		BD_SP_List.append("		FETCH c2 into V_SQL_TOTAL_STR;\n");
		BD_SP_List.append("		CLOSE c2;\n");
		BD_SP_List.append("		SET PO_TOTAL_SOLICITUDES = INTEGER(V_SQL_TOTAL_STR);\n");
		BD_SP_List.append("	END IF;\n");
		BD_SP_List.append("	OPEN c1;\n");
		BD_SP_List.append("END"+Del+"\n");
		
		BD_SP_DropSP.append("DROP PROCEDURE "+nombreSPActualizar+""+Del+"\n");
		BD_SP_DropSP.append("DROP PROCEDURE "+nombreSPEliminar+""+Del+"\n");
		BD_SP_DropSP.append("DROP PROCEDURE "+nombreSPSelect+""+Del+"\n");
		BD_SP_DropSP.append("DROP PROCEDURE "+nombreSPList+""+Del+"\n");
		BD_SP_DropSP.append("DROP PROCEDURE "+nombreSPInsertar+""+Del+"\n");
		
	}
	
	private static String getSeparador(String texto){
        
        Map<java.lang.Character, java.lang.Boolean> datos = new HashMap<java.lang.Character, java.lang.Boolean>();
        
        datos.put('A',true); datos.put('B',true);  datos.put('C',true); datos.put('D',true);
        datos.put('E',true); datos.put('F',true);  datos.put('G',true); datos.put('H',true);
        datos.put('I',true); datos.put('J',true);  datos.put('K',true); datos.put('L',true);
        datos.put('M',true); datos.put('N',true);  datos.put('O',true);
        datos.put('P',true); datos.put('Q',true);  datos.put('R',true); datos.put('S',true);
        datos.put('T',true); datos.put('U',true);  datos.put('V',true); datos.put('W',true);
        datos.put('X',true); datos.put('Y',true);  datos.put('Z',true);
        
        String resultado = "";
        for(int i = 0 ; i< texto.length(); i++){
            if(datos.get(texto.charAt(i)) != null && i != 0){
                resultado += "_"+texto.charAt(i);
            } else {
                resultado += texto.charAt(i);
            }
        }
        return resultado;
     }
	
	private static String cargarGeneradoModelo(int i) throws Exception{
		
		String codigoProyecto = (String) mantenimientos.get(i).get("codigoProyecto");
		String numeroVersion = (String) mantenimientos.get(i).get("numeroVersion");
		String codigoMantenimiento = (String) mantenimientos.get(i).get("codigoMantenimiento");
		
		
		StringBuilder texto1 = new StringBuilder();
		texto1.append("package "+CargaConfiguracion.configuracion.getPaqueteURLJava()+".modelo.mantenimiento;\n\n");
		texto1.append("import pe.com.financiero.bpm.core.modelo.MantenimientoBean;\n\n\n");
		texto1.append("/**\n");
		texto1.append(" * Clase "+codigoMantenimiento+"\n");
		texto1.append(" * @version 1.0\n");
		texto1.append(" * @author GENERADOR\n");
		texto1.append(" *\n */\n");
		texto1.append("public class "+codigoMantenimiento+" extends MantenimientoBean {\n\n");
		
		List<Map<String, Object>> atributos = (List<Map<String, Object>>) jpo.tabla("MantenimientoAtributo").donde("codigoProyecto = '"+codigoProyecto+"' AND numeroVersion = '"+numeroVersion+"' AND codigoMantenimiento = '"+codigoMantenimiento+"'").seleccionar("*");
		
		// Genera atributos de clase
		StringBuilder texto2 = new StringBuilder();
		StringBuilder texto3 = new StringBuilder();
		for(int e = 0;e<atributos.size();e++){
			
			String nombre = (String) atributos.get(e).get("nombre");
			String tipoDato = (String) atributos.get(e).get("tipoDato");
			
			texto2.append("\t/**\n");
			texto2.append("\t * Atributo "+nombre+"\n");
			texto2.append("\t */\n");
			texto2.append("\tprivate "+getPrimitivo(tipoDato)+" "+formateaNombreAtributoJava(nombre)+";\n");
			
			texto3.append("\tpublic "+getPrimitivo(tipoDato)+" get"+formateaNombreMetodoJava(nombre)+"() {\n");
			texto3.append("\t\treturn this."+formateaNombreAtributoJava(nombre)+";\n\t}\n");
			texto3.append("\tpublic void set"+formateaNombreMetodoJava(nombre)+"("+getPrimitivo(tipoDato)+" dato){\n");
			texto3.append("\t\tthis."+formateaNombreAtributoJava(nombre)+" = dato;\n\t}\n");
			
		}
		
		texto1.append(texto2.toString()+texto3.toString()+"\n\n\n");
		
		texto1.append("}");
		
		return texto1.toString();
		
	}
	
	private static String cargarPresentacionVista(int i) throws Exception{
		
		StringBuilder cont_registro = new StringBuilder();
		StringBuilder cont_listado = new StringBuilder();
		StringBuilder cont_listadoDetalle = new StringBuilder();
		StringBuilder cont_busqueda = new StringBuilder();
		
		for(int e = 0;e<atributos.size();e++){
			
			String nombreCampo = (String) atributos.get(e).get("nombre");
			String nombreJava = formateaNombreAtributoJava(nombreCampo);
			String tipoDato = (String) atributos.get(e).get("tipoDato");
			String tipoDatoBD = tipoDatoBD(tipoDato);
			String descripcionCampo = (String) atributos.get(e).get("descripcion");
			boolean esListado = (atributos.get(e).get("esListado") != null)?(Boolean) atributos.get(e).get("esListado"):false;
			boolean esBusqueda = (atributos.get(e).get("esBusqueda") != null)?(Boolean) atributos.get(e).get("esBusqueda"):false;
			boolean esObligatorio = (atributos.get(e).get("esObligatorio") != null)?(Boolean) atributos.get(e).get("esObligatorio"):false;
			boolean esLlavePrimaria = (atributos.get(e).get("esLlavePrimaria") != null)?(Boolean) atributos.get(e).get("esLlavePrimaria"):false;
			
			String obligatorio_A = "";
			String obligatorio_B = "";
			String obligatorio_C = "";
			String obligatorio_D = "";
			
			if(esObligatorio){
				obligatorio_A = "<span class=\"frm_requerido\">(*)</span>";
				obligatorio_B = "show-errors";
				obligatorio_C = "required";
				obligatorio_D = "ng-required=\"true\"";
			}
			
			String llavePrimaria = "";
			if(esLlavePrimaria){
				llavePrimaria = "ng-disabled=\"$parent.inactivoPK\"";
			}
			
			if(tipoDatoBD.equals("CHAR")){
				cont_registro.append("				  <tr>\n");
				cont_registro.append("					<th>"+obligatorio_A+" "+descripcionCampo+" :</th>\n");
				cont_registro.append("					<td>\n");
				cont_registro.append("						<span "+obligatorio_B+">Valor 1 <input type=\"radio\" name=\""+nombreJava+"_1\" ng-model=\"$parent.objeto."+nombreJava+"\" ng-value=\"true\" "+llavePrimaria+" "+obligatorio_D+"/></span>\n");
				cont_registro.append("						<span "+obligatorio_B+">Valor 2 <input type=\"radio\" name=\""+nombreJava+"_2\" ng-model=\"$parent.objeto."+nombreJava+"\" ng-value=\"false\" "+llavePrimaria+" "+obligatorio_D+"/></span>\n");
				cont_registro.append("					</td>\n");
				cont_registro.append("				  </tr>\n");
			} else {
				cont_registro.append("			  <tr>\n");
				cont_registro.append("				<th>"+obligatorio_A+" "+descripcionCampo+" :</th>\n");
				cont_registro.append("				<td "+obligatorio_B+"><input type=\"text\" name=\""+nombreJava+"\" ng-model=\"$parent.objeto."+nombreJava+"\" class=\"form-control input-sm\" "+llavePrimaria+" "+obligatorio_C+"></td>\n");
				cont_registro.append("			  </tr>\n");
			}
			
			if(esListado){

				cont_listado.append("							<th>"+descripcionCampo+"</th>\n");
				cont_listadoDetalle.append("							<td>{{item."+nombreJava+"}}</td>\n");
			}
			
			if(esBusqueda){
				
				if(tipoDato.equals("DATE")){
					
					cont_busqueda.append("					  <tr>\n");
					cont_busqueda.append("					  	<th>"+descripcionCampo+" :</th>\n");
					cont_busqueda.append("					  	<td>\n");
					cont_busqueda.append("							<strong>Desde: </strong>\n");
					cont_busqueda.append("								<p class=\"input-group\" style=\"width: 170px; display: inline-block\">\n");
					cont_busqueda.append("									<input type=\"text\" class=\"form-control input-sm\" ng-model=\"$parent.$parent.filtro."+nombreJava+"Desde\" is-open=\"$parent.fecha.abierto."+nombreJava+"Desde\" datepicker-popup=\"{{$parent.fecha.formato}}\" style=\"width: 120px;\" max-date=\"$parent.$parent.filtro."+nombreJava+"Hasta\" />\n");
					cont_busqueda.append("									<span class=\"input-group-btn\" style=\"display:inline-block\">\n");
					cont_busqueda.append("										<button type=\"button\" class=\"btn btn-default btn-sm\" ng-click=\"$parent.fecha.abrir($event,'"+nombreJava+"Desde')\"><i class=\"glyphicon glyphicon-calendar\"></i>&nbsp;</button>\n");
					cont_busqueda.append("									</span>\n");
					cont_busqueda.append("								</p>\n");
					cont_busqueda.append("							<strong>Hasta: </strong> \n");
					cont_busqueda.append("								<p class=\"input-group\" style=\"width: 170px; display: inline-block\">\n");
					cont_busqueda.append("									<input type=\"text\" class=\"form-control input-sm\" ng-model=\"$parent.$parent.filtro."+nombreJava+"Hasta\" is-open=\"$parent.fecha.abierto."+nombreJava+"Hasta\" datepicker-popup=\"{{$parent.fecha.formato}}\" style=\"width: 120px;\" min-date=\"$parent.$parent.filtro."+nombreJava+"Desde\" />\n");
					cont_busqueda.append("									<span class=\"input-group-btn\" style=\"display:inline-block\">\n");
					cont_busqueda.append("										<button type=\"button\" class=\"btn btn-default btn-sm\" ng-click=\"$parent.fecha.abrir($event,'"+nombreJava+"Hasta')\"><i class=\"glyphicon glyphicon-calendar\"></i>&nbsp;</button>\n");
					cont_busqueda.append("									</span>\n");
					cont_busqueda.append("								</p>\n");
					cont_busqueda.append("					  	</td>\n");
					cont_busqueda.append("					  </tr>\n");
					
				} else {
					
					cont_busqueda.append("					  <tr>\n");
					cont_busqueda.append("					  	<th>"+descripcionCampo+" :</th>\n");
					cont_busqueda.append("					  	<td><input type=\"text\" ng-model=\"$parent.$parent.filtro."+nombreJava+"\" class=\"form-control input-sm\"></td>\n");
					cont_busqueda.append("					  </tr>\n");
					
				}
				
			}
			
		}
		
		StringBuilder content = new StringBuilder();
		
		String nombreClase = prefijoNombre+codigoMantenimiento+"V"+numeroVersion;
		
		content.append("<script type=\"text/javascript\" src=\"/CartaFianza/BE/Mantenimientos/preControlador/pre"+nombreClase+".js\"></script>\n");
		content.append("<script type=\"text/javascript\" src=\"/CartaFianza/BE/Mantenimientos/controlador/"+nombreClase+".js\"></script>\n");
		content.append("<div ng-controller=\"resumenMantenimiento_reglas\"></div>\n");
		content.append("<div ng-controller=\"resumenMantenimiento\">\n");
		content.append("	<div ng-show=\"$parent.seccionM == 'editarNuevo'\">\n");
		content.append("		<form name=\"frm_objeto\">\n");
		content.append("		<div class=\"panel panel-default\">\n");
		content.append("		  <table class=\"table\">\n");
		content.append("			<tbody>\n");
		content.append(cont_registro);
		content.append("			  <tr>\n");
		content.append("				<td colspan=\"2\">\n");
		content.append("					<a class=\"btn btn-primary btn-sm\" ng-click=\"$parent.grabar()\"><i class=\"glyphicon glyphicon-floppy-saved\"></i> GRABAR</a>\n");
		content.append("					<a class=\"btn btn-info btn-sm\" ng-click=\"$parent.listar(false)\"><i class=\"glyphicon glyphicon-list\"></i> LISTAR</a>\n");
		content.append("				</td>\n");
		content.append("			  </tr>\n");
		content.append("			</tbody>\n");
		content.append("		  </table>\n");
		content.append("		</div>\n");
		content.append("		</form>\n");
		content.append("		<alert type=\"danger\" ng-show=\"$parent.mensajeError.mensaje.length > 0\">{{$parent.$parent.mensajeError.mensaje}}</alert>\n");
		content.append("	</div>\n");
		content.append("	<div ng-show=\"$parent.seccionM === 'listar'\">\n");
		content.append("		<a class=\"btn btn-primary\" ng-click=\"$parent.agregarNuevo()\"><i class=\"glyphicon glyphicon-floppy-saved\"></i> AGREGAR NUEVO ITEM</a>\n");
		content.append("		<br><br>\n");
		content.append("		<accordion close-others=\"oneAtATime\">\n");
		content.append("			<accordion-group is-open=\"$parent.$parent.esFiltroAbierto\">\n");
		content.append("				<accordion-heading>\n");
		content.append("					<i class=\"glyphicon glyphicon-filter\"></i>&nbsp; Filtrar\n");
		content.append("				</accordion-heading>\n");
		content.append("				<div class=\"panel panel-default\">\n");
		content.append("				  <table class=\"table\">\n");
		content.append("					<tbody>\n");
		content.append(cont_busqueda);
		content.append("					  <tr>\n");
		content.append("						<td colspan=\"2\"><a ng-click=\"$parent.$parent.filtrar()\" class=\"btn btn-primary btn-sm\"><i class=\"glyphicon glyphicon-filter\"></i> Filtrar</a></td>\n");
		content.append("					  </tr>\n");
		content.append("					</tbody>\n");
		content.append("				  </table>\n");
		content.append("				</div>\n");
		content.append("			</accordion-group>\n");
		content.append("		</accordion>\n");
		content.append("		<div class=\"panel panel-default\">\n");
		content.append("			  <div class=\"panel-heading\" style=\"position: relative;\">\n");
		content.append("				<div style=\"display:inline-block; font-weight:bold\">\n");
		content.append("					LISTADO &nbsp;\n");
		content.append("				</div>\n");
		content.append("				<div style=\"display:inline-block;   right: 8px;  position: absolute;  top: 8px;\">\n");
		content.append("					<a class=\"btn btn-default btn-sm\" ng-click=\"$parent.cambiarOrden()\" tooltip=\"Cambiar Orden\"><i ng-class=\"{'glyphicon glyphicon-circle-arrow-down' : $parent.orden == 'DESC', 'glyphicon glyphicon-circle-arrow-up' : $parent.orden == 'ASC'}\"></i></a>\n");
		content.append("					<a class=\"btn btn-default btn-sm\" ng-click=\"$parent.listar()\" tooltip=\"Actualizar\"><i class=\"glyphicon glyphicon-refresh\"></i></a>\n");
		content.append("				</div>\n");
		content.append("			  </div>\n");
		content.append("			<div style=\"overflow:auto\">\n");
		content.append("				<table class=\"table\">\n");
		content.append("					<thead>\n");
		content.append("						<tr>\n");
		content.append(cont_listado);
		content.append("							<th>&nbsp;</th>\n");
		content.append("						</tr>\n");
		content.append("					</thead>\n");
		content.append("					<tbody>\n");
		content.append("						<tr ng-repeat=\"item in $parent.grilla.items\">\n");
		content.append(cont_listadoDetalle);
		content.append("							<td>\n");
		content.append("								<a ng-click=\"$parent.cargarEditar($index)\" class=\"btn btn-primary btn-sm\" tooltip=\"Editar Registro\"><i class=\"glyphicon glyphicon-edit\"></i></a>&nbsp;\n");
		content.append("								<a ng-click=\"$parent.eliminar($index)\" class=\"btn btn-danger btn-sm\" tooltip=\"Eliminar Registro\"><i class=\"glyphicon glyphicon-trash\"></i></a>\n");
		content.append("							</td>\n");
		content.append("						</tr>\n");
		content.append("					</tbody>\n");
		content.append("				</table>\n");
		content.append("			</div>\n");
		content.append("		</div>\n");
		content.append("		<pagination ng-show=\"$parent.grilla.totalItems>0\" total-items=\"$parent.grilla.totalItems\" ng-model=\"paginacionNro\" ng-change=\"$parent.listar()\" max-size=\"5\" items-per-page=\"$parent.grilla.itemsxPagina\" class=\"pagination-sm\" boundary-links=\"true\" rotate=\"false\"></pagination>\n");
		content.append("	</div>\n");
		content.append("</div>");
		
		return content.toString();
		
	}
		
	private static String cargarPresentacionControlador(int i) throws Exception{

		StringBuilder content = new StringBuilder();

		content.append("portal.registerCtrl('resumenMantenimiento', function($scope) {\n\n");
			
		content.append("	// M�todos que se ejecutan antes y despues de cada acci�n, los m�todos antes retornan la confirmaci�n de la acci�n\n");
		content.append("	$scope.callbacks = {\n");
		content.append("		//Parametros para enviar adjuntos, deben estar activos\n");
		content.append("		//esModoAdjunto : true,\n");
		content.append("		//file : {},\n");
		content.append("		//M�todos que se ejecutan al momento de listar los datos de la tabla al inicio y al pulsar boton de filtro\n");
		content.append("		listar_antes : function(){\n");
		content.append("			return true;\n");
		content.append("		},\n");
		content.append("		listar_despues : function(){\n");
		content.append("		},\n");
		content.append("		//M�todos que se ejecutan al grabar los datos de la tabla cuando insertamos un nuevo registro\n");
		content.append("		nuevo_antes : function(){\n");
		content.append("			return true;\n");
		content.append("		},\n");
		content.append("		nuevo_despues : function(){\n");
		content.append("		},\n");
		content.append("		//M�todos que se ejecutan al grabar los datos de la tabla cuando actualizamos un registro\n");
		content.append("		editar_antes : function(){\n");
		content.append("			return true;\n");
		content.append("		},\n");
		content.append("		editar_despues : function(){\n");
		content.append("		},\n");
		content.append("		//M�todos que se ejecutan al pulsar boton eliminar cuando eliminamos un registro\n");
		content.append("		eliminar_antes : function(){\n");
		content.append("			return true;\n");
		content.append("		},\n");
		content.append("		eliminar_despues : function(){\n");
		content.append("		},\n");
		content.append("		//M�todos que se ejecutan al pulsar boton nuevo cuando agregamos un nuevo registro\n");
		content.append("		agregar_antes : function(){\n");
		content.append("			return true;\n");
		content.append("		},\n");
		content.append("		agregar_despues : function(){\n");
		content.append("		},\n");
		content.append("		//M�todos que se ejecutan al pulsar el boton de editar cuando modificamos un registro\n");
		content.append("		cargarEditar_antes : function(){\n");
		content.append("			return true;\n");
		content.append("		},\n");
		content.append("		cargarEditar_despues : function(){\n");
		content.append("		}\n");
		content.append("	};\n\n");
			
		content.append("	$scope.$parent.instanciar();\n");
			
		content.append("});");
		
		
		return content.toString();

	}
	
	private static String cargarPresentacionPreControlador(int i) throws Exception{
		
		String nombreVersion = nombreClase+"V"+numeroVersion;
		
		StringBuilder camposObligatorioRegla = new StringBuilder();
		
		StringBuilder camposPrimarios = new StringBuilder();
		
		for(int e = 0;e<atributos.size();e++){
			String nombreCampo = (String) atributos.get(e).get("nombre");
			String descripcion = (String) atributos.get(e).get("descripcion");
			String nombreJava = formateaNombreAtributoJava(nombreCampo);
			String tipoDato = (String) atributos.get(e).get("tipoDato");
			String tipoDatoBD = tipoDatoBD(tipoDato);
			boolean esLlavePrimaria = (atributos.get(e).get("esLlavePrimaria") != null)?(Boolean) atributos.get(e).get("esLlavePrimaria"):false;
			boolean esObligatorio = (atributos.get(e).get("esObligatorio") != null)?(Boolean) atributos.get(e).get("esObligatorio"):false;
			
			if(esLlavePrimaria){
				camposPrimarios.append("\""+nombreJava+"\",");
			}
			
			if(esObligatorio){
				String atributosWeb = "";
				if(tipoDatoBD.equals("DATE")){
					atributosWeb = ",\"FECHA\"";
				}
				if(tipoDatoBD.equals("TIMESTAMP")){
					atributosWeb = ",\"FECHAHORA\"";
				}
				if(tipoDatoBD.equals("BIGINT") || tipoDatoBD.equals("INTEGER")){
					atributosWeb = ",\"ENTERO\"";
				}
				if(tipoDatoBD.equals("DECIMAL")){
					Integer precision = (Integer) atributos.get(e).get("precision") ;
					atributosWeb = ",[\"DECIMAL\","+precision+"]";
				}
				camposObligatorioRegla.append("		\""+nombreJava+"\" : {\n");
				camposObligatorioRegla.append("			nombre: \""+descripcion+"\",\n");
				camposObligatorioRegla.append("			validacion: [\"REQUERIDO\""+atributosWeb+"]\n");
				camposObligatorioRegla.append("		},\n");
			}
			
		}
		
		String campoRegla = "";
		if(camposObligatorioRegla.length()>0){
			campoRegla = camposObligatorioRegla.substring(0,camposObligatorioRegla.length()-2);
		}
		
		StringBuilder content = new StringBuilder();
		
		content.append("portal.registerCtrl('resumenMantenimiento_reglas', function($scope) {\n\n");
			
		content.append("	$scope.enlace = \""+nombreVersion+"\";\n");
		content.append("	$scope.pk = ["+camposPrimarios.toString().substring(0,camposPrimarios.toString().length()-1)+"];\n\n");
		content.append("	$scope.reglas = {\n");
		content.append(campoRegla+"\n");
		content.append("	};\n\n");
			
		content.append("});");
		
		return content.toString();
		
	}
	
	private static String tipoDatoBD(String TipoDato){
		
		Map<String,String> tipo = new HashMap<String,String>();
		
		tipo.put("long", "BIGINT");
		tipo.put("String", "VARCHAR");
		tipo.put("BigDecimal", "DECIMAL");
		tipo.put("double", "DECIMAL");
		tipo.put("Date", "DATE");
		tipo.put("boolean", "CHAR");
		tipo.put("Timestamp", "TIMESTAMP");
		tipo.put("int", "INTEGER");
		
		if(tipo.get(TipoDato) != null){
			return tipo.get(TipoDato);
		} else {
			return "";
		}
		
	}
	
	private static String getPrimitivo(String TipoDato){
		
		Map<String,String> 	tipoDatoClase = new HashMap<String, String>();
		
		tipoDatoClase.put("long", "java.lang.Long");
		tipoDatoClase.put("String", "java.lang.String");
		tipoDatoClase.put("BigDecimal", "java.math.BigDecimal");
		tipoDatoClase.put("double", "java.lang.Double");
		tipoDatoClase.put("Date", "java.util.Date");
		tipoDatoClase.put("boolean", "java.lang.Boolean");
		tipoDatoClase.put("Timestamp", "java.sql.Timestamp");
		tipoDatoClase.put("int", "java.lang.Integer");
		
		if(tipoDatoClase.get(TipoDato) != null){
			return tipoDatoClase.get(TipoDato);
		} else {
			return "";
		}
		
	}
	
	private static String formateaNombreMetodoJava(String nombre) {
		String[] formatoInicio = StringUtils.lowerCase(nombre).split("_");
		String formatoFinal = "";
		for (int i=0; i<formatoInicio.length;i++){
			formatoFinal += StringUtils.capitalize(formatoInicio[i]);
		}
		
		return formatoFinal;
	}

	private static String formateaNombreAtributoJava(String nombre) {
		String[] formatoInicio = StringUtils.lowerCase(nombre).split("_");
		String formatoFinal = "";
		for (int i=0; i<formatoInicio.length;i++){
			if (i == 0){
				formatoFinal += formatoInicio[i];
			}
			else {
				formatoFinal += StringUtils.capitalize(formatoInicio[i]);
			}
		}
		
		return formatoFinal;
	}
	
	private static String formateaNombreAtributoJava2(String nombre) {
		String[] formatoInicio = StringUtils.lowerCase(nombre).split("_");
		String formatoFinal = "";
		for (int i=0; i<formatoInicio.length;i++){
			if (i == 0){
				formatoFinal += formatoInicio[i];
			}
			else {
				formatoFinal += StringUtils.capitalize(formatoInicio[i]);
			}
		}
		
		return StringUtils.capitalize(formatoFinal);
	}
	
}