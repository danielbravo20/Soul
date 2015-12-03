package pe.com.captiva.servicio.util.proceso;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import pe.com.captiva.bean.ProcesoBean;
import pe.com.captiva.bean.ProyectoBean;
import pe.com.captiva.servicio.util.Componente;
import pe.com.captiva.servicio.util.MultipleBaseConstructor;
import pe.com.mapeo.dao.Jpo;

public class ProcesoHtml extends MultipleBaseConstructor{

	private Jpo jpo;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Componente> crear(ProyectoBean proyectoBean) throws Exception {
		
		jpo = new Jpo(false);
		
		List<Componente> componentes = new ArrayList<Componente>();
		
		List<ProcesoBean> procesos = proyectoBean.getProcesos();
		for (int x = 0; x < procesos.size(); x++) {
			ProcesoBean procesoBean = procesos.get(x);
			if(procesoBean!=null){
				String directorio = proyectoBean.getEquipoBean().getDirectorioWorkspace() + "\\" + proyectoBean.getProyecto() + ProyectoBean.SUFIJO_PROYECTO_WEB + "\\WebContent\\portal\\procesos\\" + procesoBean.getAleas() + "\\proceso\\" ;

				// INICIAR PROCESO
				
				Componente componente = new Componente();
				componente.setDirectorio(directorio+"vista\\");
				componente.setArchivo("iniciarproceso.html");
				componente.setContenido(iniciarproceso_contenido_html(proyectoBean, procesoBean));
				componentes.add(componente);
				
				componente = new Componente();
				componente.setDirectorio(directorio+"controlador\\");
				componente.setArchivo("iniciarproceso.js");
				componente.setContenido(iniciarproceso_contenido_controlador(proyectoBean, procesoBean));
				componentes.add(componente);
				
				componente = new Componente();
				componente.setDirectorio(directorio+"preControlador\\");
				componente.setArchivo("pre_iniciarproceso.js");
				componente.setContenido(iniciarproceso_contenido_preControlador(proyectoBean, procesoBean));
				componentes.add(componente);
				
				// DETALLE PROCESO
				
				List<Map<String, Object>> seccionesPlantilla = (List<Map<String, Object>>) jpo.tabla("proceso_detalle_seccion").donde("tipo = 'P'").ordenadoPor("2 ASC").seleccionar("*");
				for(Map<String, Object> seccionPlantilla : seccionesPlantilla){
					componente = new Componente();
					componente.setDirectorio(proyectoBean.getEquipoBean().getDirectorioWorkspace() + "\\" + proyectoBean.getProyecto() + ProyectoBean.SUFIJO_PROYECTO_WEB + "\\WebContent\\portal\\procesos\\plantillaprocesodetalle\\");
					componente.setArchivo(seccionPlantilla.get("cod_seccion")+".html");
					componente.setContenido(detalleprocesoSeccion_contenido_html(proyectoBean, procesoBean,"P",seccionPlantilla));
					componentes.add(componente);
				}
				
				File dir = new File(directorio+"vista");
				File[] directoryListing = dir.listFiles();
				if (directoryListing != null) {
					for (File child : directoryListing) {
						if(child.getName().contains("detalleproceso")){
							child.delete();
						}
					}
				}
				
				List<Map<String, Object>> secciones = (List<Map<String, Object>>) jpo.tabla("proceso_detalle_seccion").donde("cod_proceso = '"+procesoBean.getCodigo()+"'").ordenadoPor("2 ASC").seleccionar("*");
				
				for(Map<String, Object> seccion : secciones){
					int codSeccion = Integer.parseInt((String) seccion.get("cod_seccion")) ;
					char tipo = seccion.get("tipo").toString().charAt(0) ;
					if(tipo == 'S'){
						componente = new Componente();
						componente.setDirectorio(directorio+"vista\\");
						componente.setArchivo("detalleproceso_"+codSeccion+".html");
						componente.setContenido(detalleprocesoSeccion_contenido_html(proyectoBean, procesoBean,"S",seccion));
						componentes.add(componente);
					}
				}
				
				componente = new Componente();
				componente.setDirectorio(directorio+"vista\\");
				componente.setArchivo("detalleproceso.html");
				componente.setContenido(detalleproceso_contenido_html(proyectoBean, procesoBean));
				componentes.add(componente);
				
				componente = new Componente();
				componente.setDirectorio(directorio+"controlador\\");
				componente.setArchivo("detalleproceso.js");
				componente.setContenido(detalleproceso_contenido_controlador(proyectoBean, procesoBean));
				componentes.add(componente);
				
				componente = new Componente();
				componente.setDirectorio(directorio+"preControlador\\");
				componente.setArchivo("pre_detalleproceso.js");
				componente.setContenido(detalleproceso_contenido_preControlador(proyectoBean, procesoBean, secciones, seccionesPlantilla));
				componentes.add(componente);
				
			}
		}
		
		return componentes;
	}
		
	@SuppressWarnings("unchecked")
	private StringBuffer iniciarproceso_contenido_html(ProyectoBean proyectoBean, ProcesoBean procesoBean) throws Exception{
		StringBuffer buffer = new StringBuffer();
		
		buffer.append("<script type=\"text/javascript\" src=\"/"+proyectoBean.getProyecto() + ProyectoBean.SUFIJO_PROYECTO_WEB+"/portal/procesos/" + procesoBean.getAleas() + "/proceso/preControlador/pre_iniciarproceso.js\"></script>\r\n");
		buffer.append("<script type=\"text/javascript\" src=\"/"+proyectoBean.getProyecto() + ProyectoBean.SUFIJO_PROYECTO_WEB+"/portal/procesos/" + procesoBean.getAleas() + "/proceso/controlador/iniciarproceso.js\"></script>\r\n");
		buffer.append("<div ng-controller=\"pre_iniciarproceso\" class=\"panel panel-default\">\r\n");
		buffer.append("	<div ng-controller=\"iniciarproceso\">\r\n");
		
		buffer.append("		<div class=\"panel-heading\"><strong>Datos de Inicio</strong></div>\r\n");
		
		List<Map<String, Object>> subSecciones = (List<Map<String, Object>>) jpo.tabla("proceso_inicio_sub_seccion").donde("cod_proceso = '"+procesoBean.getCodigo()+"'").ordenadoPor("2 ASC").seleccionar("*");
		
		List<Map<String, Object>> atributos = (List<Map<String, Object>>) jpo.tablas(
				new String[] { "proceso_inicio"	, "atributo" 	, "atributo_sql"},
				new String[] { "PRO"			, "ATR"			, "ASQ"		 	}
		)
		.dondeUnir(
				new String[] { "PRO", "ATR"},
				new String[] { "cod_atributo"}
		).dondeUnir(
				new String[] { "PRO", "ASQ"},
				new String[] { "cod_atributo"}
		).donde("PRO.cod_proceso = '"+procesoBean.getCodigo()+"' AND PRO.cod_sub_seccion !='0'").ordenadoPor("2 ASC, 3 ASC").seleccionar("PRO.*,ATR.nombre AS atr_nombre,ASQ.longitud AS sql_longitud,ASQ.precision AS sql_precision");
		
		buffer.append(UtilHtmlGenerador.modeloSAD("iniciarproceso", subSecciones, atributos, 2).toString());
		
		buffer.append("	</div>\r\n");
		buffer.append("</div>");
		
		return buffer;
		
	}
	
	private StringBuffer iniciarproceso_contenido_controlador(ProyectoBean proyectoBean, ProcesoBean procesoBean) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("portal.registerCtrl('iniciarproceso', function($scope, $modal, ajax) {\r\n");
		buffer.append("\r\n");
		buffer.append("});\r\n");
		return buffer;
	}
	
	private StringBuffer iniciarproceso_contenido_preControlador(ProyectoBean proyectoBean, ProcesoBean procesoBean) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("portal.registerCtrl('pre_iniciarproceso', function($scope, $modal, ajax) {\r\n");
		buffer.append("\r\n");
		buffer.append("});\r\n");
		return buffer;
	}
	
	@SuppressWarnings("unchecked")
	private StringBuffer detalleprocesoSeccion_contenido_html(ProyectoBean proyectoBean, ProcesoBean procesoBean,String tipo,Map<String, Object> seccion) throws Exception {
		
		String codSeccion = seccion.get("cod_seccion").toString() ;
		String consulta = "";
		if(tipo.equals("S")){
			consulta = "cod_proceso = '"+procesoBean.getCodigo()+"' AND cod_seccion = '"+codSeccion+"'";
		}
		if(tipo.equals("P")){
			consulta = "cod_seccion = '"+codSeccion+"'";
		}
		
		List<Map<String, Object>> subSecciones = (List<Map<String, Object>>) jpo.tabla("proceso_detalle_sub_seccion").donde(consulta).ordenadoPor("3 ASC").seleccionar("*");
		
		List<Map<String, Object>> atributos = (List<Map<String, Object>>) jpo.tablas(
				new String[] { "proceso_detalle"	, "atributo"},
				new String[] { "PRO"				, "ATR"		}
		)
		.dondeUnir(
				new String[] { "PRO", "ATR"},
				new String[] { "cod_atributo"}
		).donde(consulta).ordenadoPor("3 ASC, 4 ASC").seleccionar("PRO.*,ATR.tipo AS tipo,ATR.web_nombre AS web_nombre");
		
		return UtilHtmlGenerador.modeloSAS("detalleproceso", subSecciones, atributos);
		
	}
	
	private StringBuffer detalleproceso_contenido_html(ProyectoBean proyectoBean, ProcesoBean procesoBean) throws Exception{
		String plantilla = procesoBean.getWebDetalleTipoVista().equals("V")?"inc_listapestana_tab.html":"inc_listapestana_acordeon.html";
			
		StringBuffer buffer = new StringBuffer();
		
		buffer.append("<script type=\"text/javascript\" src=\"/"+proyectoBean.getProyecto() + ProyectoBean.SUFIJO_PROYECTO_WEB+"/portal/procesos/" + procesoBean.getAleas() + "/proceso/preControlador/pre_detalleproceso.js\"></script>\r\n");
		buffer.append("<script type=\"text/javascript\" src=\"/"+proyectoBean.getProyecto() + ProyectoBean.SUFIJO_PROYECTO_WEB+"/portal/procesos/" + procesoBean.getAleas() + "/proceso/controlador/detalleproceso.js\"></script>\r\n");
		buffer.append("<div ng-controller=\"pre_detalleproceso\" class=\"panel panel-default\">\r\n");
		buffer.append("	<div ng-controller=\"detalleproceso\">\r\n");
		buffer.append("		<div ng-include=\"'/"+proyectoBean.getProyecto() + ProyectoBean.SUFIJO_PROYECTO_WEB+"/portal/plantillas/"+plantilla+"'\"></div>\r\n");
		buffer.append("	</div>\r\n");
		buffer.append("</div>");
		
		return buffer;
		
	}
	
	private StringBuffer detalleproceso_contenido_controlador(ProyectoBean proyectoBean, ProcesoBean procesoBean) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("portal.registerCtrl('detalleproceso', function($scope, $modal, ajax) {\r\n");
		buffer.append("\r\n");
		buffer.append("});\r\n");
		return buffer;
	}

	private StringBuffer detalleproceso_contenido_preControlador(ProyectoBean proyectoBean, ProcesoBean procesoBean,List<Map<String, Object>> secciones,List<Map<String, Object>> seccionesPlantilla) {
		
		StringBuffer tabs = new StringBuffer();
		for(Map<String, Object> seccion : secciones){
			
			int codSeccion = Integer.parseInt((String) seccion.get("cod_seccion")) ;
			char tipo = seccion.get("tipo").toString().charAt(0);
			String nombre = "";
			String urlRuta = "$scope.urlseccion";
			String url = "detalleproceso_"+codSeccion+".html";
			if(tipo == 'S'){
				nombre = seccion.get("nombre").toString();
			} else if(tipo == 'W'){
				urlRuta = "$scope.urlplantilla";
				String tipoWidget = seccion.get("tipo_widget").toString();
				if(tipoWidget.equals("DOC")){
					nombre = "Documentos";
					url = "detalleproceso_documentos.html";
				} else if(tipoWidget.equals("HIS")){
					nombre = "Historial de Tareas";
					url = "detalleproceso_historialtareas.html";
				} else if(tipoWidget.equals("OBS")){
					nombre = "Observaciones y Subsanaciones";
					url = "detalleproceso_observacionsubsanacion.html";
				}
			} else if(tipo == 'A'){
				for(Map<String, Object> seccionPlantilla : seccionesPlantilla){
					if(seccion.get("cod_seccion_padre").toString().equals(seccionPlantilla.get("cod_seccion").toString())){
						nombre = seccionPlantilla.get("nombre").toString();
						urlRuta = "$scope.urlplantillaDetalle";
						url = seccion.get("cod_seccion_padre").toString()+".html";
						break;
					}
					
				}
			}
			
			tabs.append("			{\r\n");
			tabs.append("				nombre : \""+nombre+"\",\r\n");
			tabs.append("				activo : true,\r\n");
			tabs.append("				url : "+urlRuta+"+'"+url+"'\r\n");
			tabs.append("			},");
		}
		String tabsS = tabs.toString();
		
		StringBuffer buffer = new StringBuffer();
		
		buffer.append("portal.registerCtrl('pre_detalleproceso', function($scope, $modal, ajax) {\r\n");
		buffer.append("\r\n");
		buffer.append("	$scope.tabs = {\r\n");
		buffer.append("		unoALaVes : true,\r\n");
		buffer.append("		actual : 1,\r\n");
		buffer.append("		lista : [\r\n");
		buffer.append(tabsS.substring(0, tabsS.length()-1)+"\r\n");
		buffer.append("		]\r\n");
		buffer.append("	};\r\n");
		buffer.append("\r\n");
		buffer.append("});\r\n");
		return buffer;
	}
	
	@Override
	public boolean reemplazarArchivoCuandoExiste() {
		return true;
	}

	@Override
	public List<Componente> crear(Jpo jpo, ProyectoBean proyectoBean) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}