package pe.com.captiva.servicio.util.proceso;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pe.com.captiva.bean.AtributoProceso;
import pe.com.captiva.bean.CampoSQLBean;
import pe.com.captiva.bean.ProcesoBean;
import pe.com.captiva.bean.ProyectoBean;
import pe.com.captiva.servicio.util.Componente;
import pe.com.captiva.servicio.util.MultipleBaseConstructor;
import pe.com.mapeo.dao.Jpo;

public class TareaHtml extends MultipleBaseConstructor{

	private Jpo jpo;
	private static String carpetaPlantillaResumen = "plantillaresumentarea";
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Componente> crear(ProyectoBean proyectoBean) throws Exception {
		
		jpo = new Jpo(false);
		Componente componente = new Componente();
		List<Componente> componentes = new ArrayList<Componente>();
		
		List<Map<String, Object>> resumenPlantillas = (List<Map<String, Object>>) jpo.tabla("tarea_resumen_plantilla").seleccionar("*");
		for(Map<String, Object> resumenPlantilla : resumenPlantillas){
			componente = new Componente();
			componente.setDirectorio(proyectoBean.getEquipoBean().getDirectorioWorkspace() + "\\" + proyectoBean.getProyecto() + ProyectoBean.SUFIJO_PROYECTO_WEB + "\\WebContent\\portal\\procesos\\"+carpetaPlantillaResumen+"\\");
			componente.setArchivo(resumenPlantilla.get("cod_plantilla")+".html");
			componente.setContenido(tarearesumen_plantilla_html(proyectoBean, resumenPlantilla));
			componentes.add(componente);
		}
		
		List<ProcesoBean> procesos = proyectoBean.getProcesos();
		for (int x = 0; x < procesos.size(); x++) {
			ProcesoBean procesoBean = procesos.get(x);
			if(procesoBean!=null){
				String directorio = proyectoBean.getEquipoBean().getDirectorioWorkspace() + "\\" + proyectoBean.getProyecto() + ProyectoBean.SUFIJO_PROYECTO_WEB + "\\WebContent\\portal\\procesos\\" + procesoBean.getAleas() + "\\tareas\\" ;
				
				List<Map<String, Object>> tareas = (List<Map<String, Object>>) jpo.tabla("tarea").donde("cod_proceso = '"+procesoBean.getCodigo()+"'").ordenadoPor("1 ASC").seleccionar("*");
				for(Map<String, Object> tarea : tareas){
					
					String nombreTarea = tarea.get("clase").toString().toLowerCase();
					
					// BORRA REGISTROS DE TAREA
					File dir = new File(directorio+"vista");
					File[] directoryListing = dir.listFiles();
					if (directoryListing != null) {
						for (File child : directoryListing) {
							if(child.getName().contains(nombreTarea)){
								child.delete();
							}
						}
					}
					
					// RESUMEN DE TAREA
										
					componente = new Componente();
					componente.setDirectorio(directorio+"vista\\");
					componente.setArchivo("resumentarea_"+nombreTarea+".html");
					componente.setContenido(resumentarea_contenido_html(proyectoBean, procesoBean, tarea));
					componentes.add(componente);
					
					componente = new Componente();
					componente.setDirectorio(directorio+"controlador\\");
					componente.setArchivo("resumentarea_"+nombreTarea+".js");
					componente.setContenido(resumentarea_contenido_controlador(proyectoBean, procesoBean, tarea));
					componentes.add(componente);
					
					componente = new Componente();
					componente.setDirectorio(directorio+"preControlador\\");
					componente.setArchivo("resumentarea_"+"pre_"+nombreTarea+".js");
					componente.setContenido(resumentarea_contenido_preControlador(proyectoBean, procesoBean, tarea));
					componentes.add(componente);
					
					// ACCION TAREA
					
					
					List<Map<String, Object>> seccionesPlantilla = (List<Map<String, Object>>) jpo.tabla("tarea_accion_seccion").donde("tipo = 'P'").ordenadoPor("2 ASC").seleccionar("*");
					for(Map<String, Object> seccionPlantilla : seccionesPlantilla){
						componente = new Componente();
						componente.setDirectorio(proyectoBean.getEquipoBean().getDirectorioWorkspace() + "\\" + proyectoBean.getProyecto() + ProyectoBean.SUFIJO_PROYECTO_WEB + "\\WebContent\\portal\\procesos\\plantillaacciontarea\\");
						componente.setArchivo(seccionPlantilla.get("cod_seccion")+".html");
						componente.setContenido(detalleprocesoSeccion_contenido_html(proyectoBean, tarea,"P",seccionPlantilla));
						componentes.add(componente);
					}
					
					List<Map<String, Object>> secciones = (List<Map<String, Object>>) jpo.tabla("tarea_accion_seccion").donde("cod_tarea = '"+tarea.get("cod_tarea")+"'").ordenadoPor("2 ASC").seleccionar("*");
					
					for(Map<String, Object> seccion : secciones){
						int codSeccion = Integer.parseInt((String) seccion.get("cod_seccion")) ;
						char tipo = seccion.get("tipo").toString().charAt(0) ;
						if(tipo == 'S'){
							componente = new Componente();
							componente.setDirectorio(directorio+"vista\\");
							componente.setArchivo(nombreTarea+"_"+codSeccion+".html");
							componente.setContenido(detalleprocesoSeccion_contenido_html(proyectoBean, tarea,"S",seccion));
							componentes.add(componente);
						}
					}
					
					componente = new Componente();
					componente.setDirectorio(directorio+"vista\\");
					componente.setArchivo(nombreTarea+".html");
					componente.setContenido(acciontarea_contenido_html(proyectoBean, procesoBean, tarea));
					componentes.add(componente);
					
					componente = new Componente();
					componente.setDirectorio(directorio+"controlador\\");
					componente.setArchivo(nombreTarea+".js");
					componente.setContenido(acciontarea_contenido_controlador(proyectoBean, procesoBean, tarea));
					componentes.add(componente);
					
					componente = new Componente();
					componente.setDirectorio(directorio+"preControlador\\");
					componente.setArchivo("pre_"+nombreTarea+".js");
					componente.setContenido(acciontarea_contenido_preControlador(proyectoBean, procesoBean, tarea, secciones, seccionesPlantilla));
					componentes.add(componente);
					
				}
				
			}
		}
		
		return componentes;
	}
		
	@SuppressWarnings("unchecked")
	private StringBuffer tarearesumen_plantilla_html(ProyectoBean proyectoBean, Map<String, Object> resumenPlantilla) throws Exception {
		
		String codPlantilla = resumenPlantilla.get("cod_plantilla").toString();
		
		List<Map<String, Object>> subSecciones = (List<Map<String, Object>>) jpo.tabla("tarea_resumen_plantilla_sub_seccion").donde("cod_plantilla = '"+codPlantilla+"'").ordenadoPor("2 ASC").seleccionar("*");
		
		List<Map<String, Object>> atributos = (List<Map<String, Object>>) jpo.tablas(
				new String[] { "tarea_resumen_plantilla_atributo"	, "atributo"},
				new String[] { "TAR"								, "ATR"		}
		)
		.dondeUnir(
				new String[] { "TAR", "ATR"},
				new String[] { "cod_atributo"}
		).donde("TAR.cod_plantilla = '"+codPlantilla+"'").ordenadoPor("2 ASC, 3 ASC").seleccionar("TAR.*,ATR.tipo AS tipo,ATR.web_nombre AS web_nombre");
		
		return UtilHtmlGenerador.modeloSAS("resumentarea", subSecciones, atributos);
		
	}

	@SuppressWarnings("unchecked")
	private StringBuffer resumentarea_contenido_html(ProyectoBean proyectoBean, ProcesoBean procesoBean,Map<String, Object> tarea) throws Exception{
		
		String codTarea = tarea.get("cod_tarea").toString();
		
		StringBuffer buffer = new StringBuffer();
		
		buffer.append("<script type=\"text/javascript\" src=\"/"+proyectoBean.getProyecto() + ProyectoBean.SUFIJO_PROYECTO_WEB+"/portal/procesos/" + procesoBean.getAleas() + "/tareas/preControlador/pre_resumentarea.js\"></script>\r\n");
		buffer.append("<script type=\"text/javascript\" src=\"/"+proyectoBean.getProyecto() + ProyectoBean.SUFIJO_PROYECTO_WEB+"/portal/procesos/" + procesoBean.getAleas() + "/tareas/controlador/resumentarea.js\"></script>\r\n");
		buffer.append("<div ng-controller=\"pre_resumentarea\" class=\"panel panel-default\">\r\n");
		buffer.append("	<div ng-controller=\"resumentarea\">\r\n");
		
		if(tarea.get("web_plantilla_resumen") != null && tarea.get("web_plantilla_resumen").toString().length()>0){
			buffer.append("		<div ng-include=\"'/"+proyectoBean.getProyecto() + ProyectoBean.SUFIJO_PROYECTO_WEB+"/portal/procesos/"+carpetaPlantillaResumen+"/"+tarea.get("web_plantilla_resumen")+".html'\"></div>\r\n");
		} else {
			
			List<Map<String, Object>> subSecciones = (List<Map<String, Object>>) jpo.tabla("tarea_resumen_sub_seccion").donde("cod_tarea = '"+codTarea+"'").ordenadoPor("2 ASC").seleccionar("*");
			
			List<Map<String, Object>> atributos = (List<Map<String, Object>>) jpo.tablas(
					new String[] { "tarea_resumen"	, "atributo"},
					new String[] { "TAR"			, "ATR"		}
			)
			.dondeUnir(
					new String[] { "TAR", "ATR"},
					new String[] { "cod_atributo"}
			).donde("TAR.cod_tarea = '"+codTarea+"'").ordenadoPor("2 ASC, 3 ASC").seleccionar("TAR.*,ATR.tipo AS tipo,ATR.web_nombre AS web_nombre");
			
			buffer.append(UtilHtmlGenerador.modeloSAS("resumentarea", subSecciones, atributos, 2).toString());
			
		}
		
		buffer.append("	</div>\r\n");
		buffer.append("</div>");
		
		return buffer;
		
	}
	
	private StringBuffer resumentarea_contenido_controlador(ProyectoBean proyectoBean, ProcesoBean procesoBean,Map<String, Object> tarea) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("portal.registerCtrl('resumentarea', function($scope, $modal, ajax) {\r\n");
		buffer.append("\r\n");
		buffer.append("});\r\n");
		return buffer;
	}
	
	private StringBuffer resumentarea_contenido_preControlador(ProyectoBean proyectoBean, ProcesoBean procesoBean,Map<String, Object> tarea) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("portal.registerCtrl('pre_resumentarea', function($scope, $modal, ajax) {\r\n");
		buffer.append("\r\n");
		buffer.append("});\r\n");
		return buffer;
	}
	
	@SuppressWarnings("unchecked")
	private StringBuffer detalleprocesoSeccion_contenido_html(ProyectoBean proyectoBean, Map<String, Object> tarea,String tipo,Map<String, Object> seccion) throws Exception {
		
		String codSeccion = seccion.get("cod_seccion").toString() ;
		String consulta = "";
		if(tipo.equals("S")){
			consulta = "cod_tarea = '"+tarea.get("cod_tarea")+"' AND cod_seccion = '"+codSeccion+"' AND cod_sub_seccion !='0'";
		}
		if(tipo.equals("P")){
			consulta = "cod_seccion = '"+codSeccion+"'";
		}
		
		List<Map<String, Object>> subSecciones = (List<Map<String, Object>>) jpo.tabla("tarea_accion_sub_seccion").donde(consulta).ordenadoPor("3 ASC").seleccionar("*");
		
		List<Map<String, Object>> atributos = (List<Map<String, Object>>) jpo.tablas(
				new String[] { "tarea_accion"	, "atributo" 	, "atributo_sql"},
				new String[] { "TAR"			, "ATR"			, "ASQ"		 	}
		)
		.dondeUnir(
				new String[] { "TAR", "ATR"},
				new String[] { "cod_atributo"}
		).dondeUnir(
				new String[] { "TAR", "ASQ"},
				new String[] { "cod_atributo"}
		).donde(consulta).ordenadoPor("3 ASC, 4 ASC").seleccionar("TAR.*,ATR.web_nombre AS atr_nombre,ASQ.longitud AS sql_longitud,ASQ.precision AS sql_precision");
		
		return UtilHtmlGenerador.modeloSAD("iniciarproceso", subSecciones, atributos);
		
	}
	
	private StringBuffer acciontarea_contenido_html(ProyectoBean proyectoBean, ProcesoBean procesoBean,Map<String, Object> tarea) throws Exception{
		String plantilla = procesoBean.getWebDetalleTipoVista().equals("V")?"inc_listapestana_tab.html":"inc_listapestana_acordeon.html";
			
		StringBuffer buffer = new StringBuffer();
		
		buffer.append("<script type=\"text/javascript\" src=\"/"+proyectoBean.getProyecto() + ProyectoBean.SUFIJO_PROYECTO_WEB+"/portal/procesos/" + procesoBean.getAleas() + "/tareas/preControlador/pre_acciontarea.js\"></script>\r\n");
		buffer.append("<script type=\"text/javascript\" src=\"/"+proyectoBean.getProyecto() + ProyectoBean.SUFIJO_PROYECTO_WEB+"/portal/procesos/" + procesoBean.getAleas() + "/tareas/controlador/acciontarea.js\"></script>\r\n");
		buffer.append("<div ng-controller=\"pre_acciontarea\" class=\"panel panel-default\">\r\n");
		buffer.append("	<div ng-controller=\"acciontarea\">\r\n");
		buffer.append("		<div ng-include=\"'/"+proyectoBean.getProyecto() + ProyectoBean.SUFIJO_PROYECTO_WEB+"/portal/plantillas/"+plantilla+"'\"></div>\r\n");
		buffer.append("	</div>\r\n");
		buffer.append("</div>");
		
		return buffer;
		
	}
	
	private StringBuffer acciontarea_contenido_controlador(ProyectoBean proyectoBean, ProcesoBean procesoBean, Map<String, Object> tarea) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("portal.registerCtrl('acciontarea', function($scope, $modal, ajax) {\r\n");
		buffer.append("\r\n");
		buffer.append("});\r\n");
		return buffer;
	}

	private StringBuffer acciontarea_contenido_preControlador(ProyectoBean proyectoBean, ProcesoBean procesoBean,Map<String, Object> tarea,List<Map<String, Object>> secciones,List<Map<String, Object>> seccionesPlantilla) {
		
		StringBuffer tabs = new StringBuffer();
		for(Map<String, Object> seccion : secciones){
			
			int codSeccion = Integer.parseInt((String) seccion.get("cod_seccion")) ;
			char tipo = seccion.get("tipo").toString().charAt(0);
			String nombre = "";
			String urlRuta = "$scope.urlseccion";
			String url = "acciontarea_"+codSeccion+".html";
			if(tipo == 'S'){
				nombre = seccion.get("nombre").toString();
			} else if(tipo == 'W'){
				urlRuta = "$scope.urlplantilla";
				String tipoWidget = seccion.get("tipo_widget").toString();
				if(tipoWidget.equals("DOC")){
					nombre = "Documentos";
					url = "acciontarea_documentos.html";
				} else if(tipoWidget.equals("HIS")){
					nombre = "Historial de Tareas";
					url = "acciontarea_historialtareas.html";
				} else if(tipoWidget.equals("OBS")){
					nombre = "Observaciones y Subsanaciones";
					url = "acciontarea_observacionsubsanacion.html";
				}
			} else if(tipo == 'A'){
				for(Map<String, Object> seccionPlantilla : seccionesPlantilla){
					if(seccion.get("cod_seccion_padre").toString().equals(seccionPlantilla.get("cod_seccion").toString())){
						nombre = seccionPlantilla.get("nombre").toString();
						urlRuta = "$scope.urlplantillaAccion";
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
		
		buffer.append("portal.registerCtrl('pre_acciontarea', function($scope, $modal, ajax) {\r\n");
		buffer.append("\r\n");
		buffer.append("	$scope.tabs = {\r\n");
		buffer.append("		unoALaVes : true,\r\n");
		buffer.append("		actual : 1,\r\n");
		buffer.append("		lista : [\r\n");
		if(tabsS.length()>0){
			buffer.append(tabsS.substring(0, tabsS.length()-1)+"\r\n");
		}
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