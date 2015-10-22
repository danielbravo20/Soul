package pe.com.mapeo.ejb.carga;

import java.util.Map;

import pe.com.mapeo.modelo.Configuracion;
import pe.com.mapeo.util.MapeoUtil;

public class CargaConfiguracion {

	public static final String DIRECTORIO_AUTOGENERADO = "generado";
	public static final String DIRECTORIO_SOURCE_SRC = "src";
	public static final String DIRECTORIO_SOURCE_EJB = "ejbModule";
	public static final String DIRECTORIO_WEBCONTENT = "WebContent";
	
	public static Configuracion configuracion;
	public static Map<String, Object> config;
	public static Map<String, Object> usuario;
	public static Map<String, Object> usuarioEquipo;
	
	public static void cargar(Object objUser,Object objConfig,Object objUsuarioEquipo) throws Exception{
		if(objConfig!=null){

			configuracion = new Configuracion();
			if(objConfig != null){
				config = (Map<String, Object>) objConfig;
			}
			if(objUser != null){
				usuario = (Map<String, Object>) objUser;
			}
			if(objUsuarioEquipo != null){
				usuarioEquipo = (Map<String, Object>) objUsuarioEquipo;
			}
			
			String urlProducto 						= (String) config.get("producto"); // CF
			//String urlPaquete 						= (String) config.get("urlPrefijoPaquete")+"."+urlProducto.toLowerCase()+".v1"; // pe.com.financiero.bpm.cf.v1 
			String urlPaquete 						= (String) config.get("urlPrefijoPaquete")+"."+urlProducto.toLowerCase(); // pe.com.financiero.bpm.cf.v1 
			
			configuracion.setPaqueteURL(urlPaquete.replaceAll("\\.", "\\\\")); // -> pe/com/financiero/bpm/cf/v1
			configuracion.setPaqueteURLJava(urlPaquete);
			
			// Destino del workspace (Autogenerado)
			/*
			String directorioWorkspace = "";
			if(objUser != null){
				directorioWorkspace 				= (String) usuario.get("carpetaDestinoWorkspace");
			} else {
				directorioWorkspace					= (String) config.get("carpetaDestinoWorkspace");
			}*/
			String directorioWorkspace				= (String) usuarioEquipo.get("carpetaDestinoWorkspace");
			
			MapeoUtil.crearFolder(directorioWorkspace);
			configuracion.setDirGenerado(directorioWorkspace);
			
			String directorioProyectoWebCont		= directorioWorkspace+"\\"+(String) config.get("directorioWebAuto")+"\\"+DIRECTORIO_WEBCONTENT;
			MapeoUtil.crearFolder(directorioProyectoWebCont);
			configuracion.setDirGeneradoWebContent(directorioProyectoWebCont);
			/*
			String directorioProyectoWebAuto		= directorioWorkspace+"\\"+(String) config.get("directorioWebAuto")+"\\"+DIRECTORIO_AUTOGENERADO+"\\"+configuracion.getPaqueteURL();
			MapeoUtil.crearFolder(directorioProyectoWebAuto);
			configuracion.setDirGeneradoWeb(directorioProyectoWebAuto);
			*/
			String directorioProyectoLib 			= directorioWorkspace+"\\"+(String) config.get("directorioLib")+"\\"+DIRECTORIO_AUTOGENERADO+"\\"+configuracion.getPaqueteURL();
			MapeoUtil.crearFolder(directorioProyectoLib);
			configuracion.setDirGeneradoLib(directorioProyectoLib);
			/*
			String directorioProyectoEJBClient 		= directorioWorkspace+"\\"+(String) config.get("directorioEJBClient")+"\\"+DIRECTORIO_AUTOGENERADO+"\\"+configuracion.getPaqueteURL();
			MapeoUtil.crearFolder(directorioProyectoEJBClient);
			configuracion.setDirGeneradoEJBClient(directorioProyectoEJBClient);
			
			String directorioProyectoEJBAuto		= directorioWorkspace+"\\"+(String) config.get("directorioEJBAuto")+"\\"+DIRECTORIO_AUTOGENERADO+"\\"+configuracion.getPaqueteURL();
			MapeoUtil.crearFolder(directorioProyectoEJBAuto);
			configuracion.setDirGeneradoEJB(directorioProyectoEJBAuto);
			*/
			String directorioProyectoEJBAutoExt 		= directorioWorkspace+"\\"+"CartaFianzaEJBExt"+"\\"+DIRECTORIO_SOURCE_SRC+"\\"+configuracion.getPaqueteURL();
			MapeoUtil.crearFolder(directorioProyectoEJBAutoExt);
			configuracion.setDirGeneradoEJBExt(directorioProyectoEJBAutoExt);
			
			
			// Destino Parcial (Source)
			/*
			String directorioSource = "";
			if(objUser != null){
				directorioSource 					= (String) usuario.get("carpetaDestinoParcial");
			} else {
				directorioSource					= (String) config.get("carpetaDestinoParcial");
			}*/
			String directorioSource				= (String) usuarioEquipo.get("carpetaDestinoParcial");
			
			MapeoUtil.crearFolder(directorioSource);
			configuracion.setDirSource(directorioSource);
			
			String dirSourceWebCont		= directorioSource+"\\"+(String) config.get("directorioWebAuto")+"\\"+DIRECTORIO_WEBCONTENT;
			MapeoUtil.crearFolder(dirSourceWebCont);
			configuracion.setDirSourceWebContent(dirSourceWebCont);
			
			String dirSourceWebAuto		= directorioSource+"\\"+(String) config.get("directorioWebAuto")+"\\"+DIRECTORIO_SOURCE_SRC+"\\"+configuracion.getPaqueteURL();
			MapeoUtil.crearFolder(dirSourceWebAuto);
			configuracion.setDirSourceWeb(dirSourceWebAuto);
			/*
			String dirSourceLib 		= directorioSource+"\\"+(String) config.get("directorioLib")+"\\"+DIRECTORIO_SOURCE_SRC+"\\"+configuracion.getPaqueteURL();
			MapeoUtil.crearFolder(dirSourceLib);
			configuracion.setDirSourceLib(dirSourceLib);
			*/
			String dirSourceEJBClient 	= directorioSource+"\\"+(String) config.get("directorioEJBClient")+"\\"+DIRECTORIO_SOURCE_EJB+"\\"+configuracion.getPaqueteURL();
			MapeoUtil.crearFolder(dirSourceEJBClient);
			configuracion.setDirSourceEJBClient(dirSourceEJBClient);
			
			String dirSourceEJBAuto		= directorioSource+"\\"+(String) config.get("directorioEJBAuto")+"\\"+DIRECTORIO_SOURCE_EJB+"\\"+configuracion.getPaqueteURL();
			MapeoUtil.crearFolder(dirSourceEJBAuto);
			configuracion.setDirSourceEJB(dirSourceEJBAuto);
			/*
			MapeoUtil.crearFolder(directorioSource+"\\META-INF");
			MapeoUtil.crearArchivo(directorioSource+"\\META-INF", "ibm-ejb-jar-bndv2.xml", cargarEJBBinding());
			*/
		}
	}

	public static void cargarEJBBinding() throws Exception {
		
		String directorioSource = configuracion.getDirSource()+"\\"+(String) config.get("directorioEJBAuto")+"\\"+DIRECTORIO_SOURCE_EJB;
		// D:\IBM\WRK\BC\PLD_WEB\BECartaFianzaEJB\ejbModule\META-INF
		
		StringBuilder contenido = new StringBuilder();
		
		contenido.append("<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>\n");
		contenido.append("<ejb-jar-bnd xmlns=\"http://websphere.ibm.com/xml/ns/javaee\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://websphere.ibm.com/xml/ns/javaee http://websphere.ibm.com/xml/ns/javaee/ibm-ejb-jar-bnd_1_0.xsd\" version=\"1.0\">\n");
		contenido.append("\n");
		
			contenido.append("	<!-- PROCESOS -->\n");
		for(int i = 0;i<CargaProcesos.procesos.size();i++){
			String nombreClase = CargaProcesos.procesos.get(i).get("codigoProceso")+"DaoImpl";
			String jdbc = (String) CargaProcesos.procesos.get(i).get("codigoDataSource");
			contenido.append("	<session name=\""+nombreClase+"\">\n");
			contenido.append("		<resource-ref name=\""+jdbc+"\" binding-name=\""+jdbc+"\"></resource-ref>\n");
			contenido.append("	</session>\n");
			contenido.append("\n");
		}
		
			contenido.append("	<!-- TAREAS -->\n");
		for(int i = 0;i<CargaTareas.tareas.size();i++){
			String nombreClase = CargaTareas.tareas.get(i).get("codigoTarea")+"DaoImpl";
			String jdbc = (String) CargaTareas.tareas.get(i).get("codigoDataSource");
			contenido.append("	<session name=\""+nombreClase+"\">\n");
			contenido.append("		<resource-ref name=\""+jdbc+"\" binding-name=\""+jdbc+"\"></resource-ref>\n");
			contenido.append("	</session>\n");
			contenido.append("\n");
		}
			
			contenido.append("	<!-- MANTENIMIENTOS -->\n");
		for(int i = 0;i<CargaMantenimientos.mantenimientos.size();i++){
			String nombreClase = CargaMantenimientos.prefijoNombre+CargaMantenimientos.mantenimientos.get(i).get("codigoMantenimiento")+"DaoImpl";
			String jdbc = (String) CargaMantenimientos.mantenimientos.get(i).get("codigoDataSource");
			contenido.append("	<session name=\""+nombreClase+"\">\n");
			contenido.append("		<resource-ref name=\""+jdbc+"\" binding-name=\""+jdbc+"\"></resource-ref>\n");
			contenido.append("	</session>\n");
			contenido.append("\n");
		}
		
		contenido.append("</ejb-jar-bnd>");
		
		MapeoUtil.crearFolder(directorioSource+"\\META-INF");
		
		MapeoUtil.crearArchivo(directorioSource+"\\META-INF", "ibm-ejb-jar-bnd.xml", contenido.toString());
		
	}
	
}