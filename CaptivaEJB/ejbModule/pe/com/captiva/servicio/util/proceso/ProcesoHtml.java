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
		
		List<Map<String, Object>> inicio_subSecciones = (List<Map<String, Object>>) jpo.tabla("proceso_inicio_sub_seccion").donde("cod_proceso = '"+procesoBean.getCodigo()+"'").ordenadoPor("2 ASC").seleccionar("*");
		Map<Integer, Integer> mapSP = new HashMap<Integer, Integer>();
		for (int i = 0; i < inicio_subSecciones.size(); i++) {
			mapSP.put((Integer) inicio_subSecciones.get(i).get("cod_sub_seccion"), i);
		}
		
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
		
		for (Map<String, Object> atributo : atributos) {
			int codSubSeccion = (Integer) atributo.get("cod_sub_seccion");
			if(mapSP.get(codSubSeccion) != null){
				int indice = mapSP.get(codSubSeccion);
				List<Map<String, Object>> atributosSubseccion = new ArrayList<Map<String, Object>>();
				if(inicio_subSecciones.get(indice).get("atributos") != null){
					atributosSubseccion = (List<Map<String, Object>>) inicio_subSecciones.get(indice).get("atributos");
				}
				atributosSubseccion.add(atributo);
				inicio_subSecciones.get(indice).put("atributos", atributosSubseccion);
			}
		}
				
		for (Map<String, Object> inicio_subSeccion : inicio_subSecciones) {
			buffer.append("		<div class=\"filaTitulo\">&nbsp;&nbsp;<i class=\"glyphicon glyphicon-list-alt\"></i> "+inicio_subSeccion.get("nombre")+"</div>\r\n");

			int filaActual = 0;
			List<Map<String, Object>> atributosLista = (List<Map<String, Object>>) inicio_subSeccion.get("atributos");
			for (Map<String, Object> atributoProcesoObj : atributosLista) {
				
				AtributoProceso atributoProceso = new AtributoProceso();
				
				atributoProceso.setWebEtiqueta((String) atributoProcesoObj.get("web_etiqueta"));
				atributoProceso.setWebMensajeValidacion((String) atributoProcesoObj.get("web_mensaje_validacion"));
				atributoProceso.setWebTipo((String) atributoProcesoObj.get("web_tipo"));
				if(atributoProcesoObj.get("web_tipo_campo")!=null){
					atributoProceso.setWebTipoCampo(atributoProcesoObj.get("web_tipo_campo").toString().charAt(0));
				}
				atributoProceso.setFlgWebRequerido((atributoProcesoObj.get("web_requerido").toString().equals("1"))?true:false);
				atributoProceso.setNombre((String) atributoProcesoObj.get("atr_nombre"));
					CampoSQLBean campoSQL = new CampoSQLBean();
				if(atributoProcesoObj.get("sql_longitud") != null){
					campoSQL.setPrecision((Integer) atributoProcesoObj.get("sql_longitud"));
				}
				if(atributoProcesoObj.get("sql_precision") != null){
					campoSQL.setLongitud((Integer) atributoProcesoObj.get("sql_precision"));
				}
				atributoProceso.setCampoSQLBean(campoSQL);
					
				if(filaActual == 0 || filaActual%2==0){
					buffer.append("		<div class=\"frm_fila\">\r\n");
				}
				
				// CONDICIONES
				String condRequerido = "";
				String atriRequerido = "";
				String divRequerido = "class=\"frm_campo\"";
				String mensajeError = "";
				if(atributoProceso.isFlgWebRequerido()){
					condRequerido = "<span class=\"frm_requerido\">(*)</span> ";
					atriRequerido = "required";
					divRequerido = "class=\"frm_campo form-group\" show-errors='{showSuccess: true}'";
					mensajeError = "<p class=\"help-block\" ng-if=\"frm_iniciarproceso."+atributoProceso.getNombre()+".$error.required\">"+atributoProceso.getWebMensajeValidacion()+"</p>";
				}
				
				String atributo = "";
				//System.out.println("TIPO DATO "+UtilHtmlGenerador.tipoClase.get(atributoProceso.getWebTipo())+"|"+atributoProceso.getWebTipoCampo());
				if(UtilHtmlGenerador.tipoClase.get(atributoProceso.getWebTipo())=='S'){
					
					if(atributoProceso.getWebTipoCampo() == 'L'){
						atributo = "{{baseIPConfig.data."+atributoProceso.getNombre()+"}}";
					}
					if(atributoProceso.getWebTipoCampo() == 'C'){
						atributo = "<input type=\"text\" name=\""+atributoProceso.getNombre()+"\" ng-model=\"baseIPConfig.data."+atributoProceso.getNombre()+"\" class=\"form-control input-sm\" "+atriRequerido+">";
					}
					if(atributoProceso.getWebTipoCampo() == 'A'){
						atributo = "<textarea name=\""+atributoProceso.getNombre()+"\" ng-model=\"baseIPConfig.data."+atributoProceso.getNombre()+"\" class=\"form-control input-sm\" "+atriRequerido+"></textarea>";
					}
					if(atributoProceso.getWebTipoCampo() == 'E'){
						atributo = "<select name=\""+atributoProceso.getNombre()+"\" ng-model=\"baseIPConfig.data."+atributoProceso.getNombre()+"\" class=\"form-control input-sm\" "+atriRequerido+"></select>";
					}
				}
				if(UtilHtmlGenerador.tipoClase.get(atributoProceso.getWebTipo())=='b'){
					if(atributoProceso.getWebTipoCampo() == 'L'){
						atributo = "<span ng-show=\"baseIPConfig.data."+atributoProceso.getNombre()+"==true\">Sí</span><span ng-show=\"baseIPConfig.data."+atributoProceso.getNombre()+"==false\">No</span>";
					}
					if(atributoProceso.getWebTipoCampo() == 'H'){
						atributo = "<input type=\"checkbox\" ng-model=\"baseIPConfig.data."+atributoProceso.getNombre()+"\" class=\"form-control input-sm\" ng-true-value=\"true\" ng-false-value=\"false\" style=\"width:30px\" "+atriRequerido+">";
					}
				}
				if(UtilHtmlGenerador.tipoClase.get(atributoProceso.getWebTipo())=='B'){
					if(atributoProceso.getWebTipoCampo() == 'L'){
						atributo = "{{baseIPConfig.data."+atributoProceso.getNombre()+" | currency: \"\"}}";
					}
					if(atributoProceso.getWebTipoCampo() == 'C'){
						CampoSQLBean campoSQLBean = atributoProceso.getCampoSQLBean();
						
						String maximo = "";
						if(campoSQLBean.getLongitud()>0 && campoSQLBean.getPrecision()>0){
							maximo = "nx-regla=\"decimal\" nx-max-entero=\""+campoSQLBean.getLongitud()+"\" nx-max-decimal=\""+campoSQLBean.getPrecision()+"\"";
						}
						atributo = "<input type=\"text\" name=\""+atributoProceso.getNombre()+"\" ng-model=\"baseIPConfig.data."+atributoProceso.getNombre()+"\" "+maximo+" class=\"form-control input-sm\" "+atriRequerido+">";
					}
				}
				if(UtilHtmlGenerador.tipoClase.get(atributoProceso.getWebTipo())=='D'){
					if(atributoProceso.getWebTipoCampo() == 'L'){
						atributo = "{{baseIPConfig.data."+atributoProceso.getNombre()+" | date:'dd/MM/yyyy'}}";
					}
					if(atributoProceso.getWebTipoCampo() == 'C'){
						atributo = "<fecha id=\""+atributoProceso.getNombre()+"\" fecha=\"baseIPConfig.data."+atributoProceso.getNombre()+"\" titulo=\""+atributoProceso.getWebMensajeValidacion()+"\" mostrar-error=\""+(atributoProceso.isFlgWebRequerido()?"true":"false")+"\" ></fecha>";
					}
				}
				if(UtilHtmlGenerador.tipoClase.get(atributoProceso.getWebTipo())=='N'){
					if(atributoProceso.getWebTipoCampo() == 'L'){
						atributo = "{{baseIPConfig.data."+atributoProceso.getNombre()+"}}";
					}
					if(atributoProceso.getWebTipoCampo() == 'C'){
						atributo = "<input type=\"number\" name=\""+atributoProceso.getNombre()+"\" ng-model=\"baseIPConfig.data."+atributoProceso.getNombre()+"\" class=\"form-control input-sm\" "+atriRequerido+">";
					}
				}
				
				int estiloNum = 1;
				int estiloNumero = 0;
				if((filaActual+2)%2==0){
					estiloNumero = (filaActual+4)/2;
				}
				if((filaActual+2)%2!=0){
					estiloNumero = (filaActual+3)/2;
				}
				if((estiloNumero)%2==0){
					estiloNum = 1;
				}
				if((estiloNumero)%2!=0){
					estiloNum = 2;
				}
				
				if((filaActual+2)%2==0){
					buffer.append("			<div class=\"frm_celda frm_cel_"+estiloNum+"a\">\r\n");
					buffer.append("				<div class=\"frm_etiqueta\">"+condRequerido+atributoProceso.getWebEtiqueta()+" :</div>\r\n");
					buffer.append("				<div "+divRequerido+">"+atributo+mensajeError+"</div>\r\n");
					buffer.append("			</div>");
				}
				if((filaActual+2)%2!=0){
					buffer.append("<div class=\"frm_celda frm_cel_"+estiloNum+"b\">\r\n");
					buffer.append("				<div class=\"frm_etiqueta\">"+condRequerido+atributoProceso.getWebEtiqueta()+" :</div>\r\n");
					buffer.append("				<div "+divRequerido+">"+atributo+mensajeError+"</div>\r\n");
					buffer.append("			</div>\r\n");
				}
				
				if(filaActual == atributosLista.size()-1 && (filaActual+2)%2==0){
					buffer.append("\r\n		</div>\r\n");
				} else if(filaActual == atributosLista.size()-1 || (filaActual+2)%2!=0){
					buffer.append("		</div>\r\n");
				}
				
				filaActual++;
				
			}
		}
		
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
		
		StringBuffer buffer = new StringBuffer();
		
		List<Map<String, Object>> subSecciones = (List<Map<String, Object>>) jpo.tabla("proceso_detalle_sub_seccion").donde(consulta).ordenadoPor("3 ASC").seleccionar("*");
		Map<Integer, Integer> mapSP = new HashMap<Integer, Integer>();
		for (int i = 0; i < subSecciones.size(); i++) {
			mapSP.put((Integer) subSecciones.get(i).get("cod_sub_seccion"), i);
		}
		
		List<Map<String, Object>> atributos = (List<Map<String, Object>>) jpo.tablas(
				new String[] { "proceso_detalle"	, "atributo"},
				new String[] { "PRO"				, "ATR"		}
		)
		.dondeUnir(
				new String[] { "PRO", "ATR"},
				new String[] { "cod_atributo"}
		).donde(consulta).ordenadoPor("3 ASC, 4 ASC").seleccionar("PRO.*,ATR.tipo AS tipo,ATR.tipo AS web_nombre");
		
		for (Map<String, Object> atributo : atributos) {
			int codSubSeccion = (Integer) atributo.get("cod_sub_seccion");
			if(mapSP.get(codSubSeccion) != null){
				int indice = mapSP.get(codSubSeccion);
				List<Map<String, Object>> atributosSubseccion = new ArrayList<Map<String, Object>>();
				if(subSecciones.get(indice).get("atributos") != null){
					atributosSubseccion = (List<Map<String, Object>>) subSecciones.get(indice).get("atributos");
				}
				atributosSubseccion.add(atributo);
				subSecciones.get(indice).put("atributos", atributosSubseccion);
			}
		}
				
		for (Map<String, Object> subSeccion : subSecciones) {
			buffer.append("<div class=\"filaTitulo\">&nbsp;&nbsp;<i class=\"glyphicon glyphicon-list-alt\"></i> "+subSeccion.get("nombre")+"</div>\r\n");

			int filaActual = 0;
			List<Map<String, Object>> atributosLista = (List<Map<String, Object>>) subSeccion.get("atributos");
			for (Map<String, Object> atributoProcesoObj : atributosLista) {
				
				AtributoProceso atributoProceso = new AtributoProceso();
				
				atributoProceso.setWebEtiqueta((String) atributoProcesoObj.get("web_etiqueta"));
				atributoProceso.setWebTipo((String) atributoProcesoObj.get("tipo"));
				atributoProceso.setWebNombre((String) atributoProcesoObj.get("web_nombre"));

				if(filaActual == 0 || filaActual%2==0){
					buffer.append("<div class=\"frm_fila\">\r\n");
				}
				
				String atributo = "";
				if(UtilHtmlGenerador.tipoClase.get(atributoProceso.getWebTipo())=='S'){
					atributo = "{{baseDPConfig.data."+atributoProceso.getNombre()+"}}";
				}
				if(UtilHtmlGenerador.tipoClase.get(atributoProceso.getWebTipo())=='b'){
					atributo = "<span ng-show=\"baseDPConfig.data."+atributoProceso.getNombre()+"==true\">Sí</span><span ng-show=\"baseDPConfig.data."+atributoProceso.getNombre()+"==false\">No</span>";
				}
				if(UtilHtmlGenerador.tipoClase.get(atributoProceso.getWebTipo())=='B'){
					atributo = "{{baseDPConfig.data."+atributoProceso.getNombre()+" | currency: \"\"}}";
				}
				if(UtilHtmlGenerador.tipoClase.get(atributoProceso.getWebTipo())=='D'){
					atributo = "{{baseDPConfig.data."+atributoProceso.getNombre()+" | date:'dd/MM/yyyy'}}";
				}
				if(UtilHtmlGenerador.tipoClase.get(atributoProceso.getWebTipo())=='N'){
					atributo = "{{baseDPConfig.data."+atributoProceso.getNombre()+"}}";
				}
				
				int estiloNum = 1;
				int estiloNumero = 0;
				if((filaActual+2)%2==0){
					estiloNumero = (filaActual+4)/2;
				}
				if((filaActual+2)%2!=0){
					estiloNumero = (filaActual+3)/2;
				}
				if((estiloNumero)%2==0){
					estiloNum = 1;
				}
				if((estiloNumero)%2!=0){
					estiloNum = 2;
				}
				
				if((filaActual+2)%2==0){
					buffer.append("	<div class=\"frm_celda frm_cel_"+estiloNum+"a\">\r\n");
					buffer.append("		<div class=\"frm_etiqueta\">"+atributoProceso.getWebEtiqueta()+" :</div>\r\n");
					buffer.append("		<div>"+atributo+"</div>\r\n");
					buffer.append("	</div>");
				}
				if((filaActual+2)%2!=0){
					buffer.append("<div class=\"frm_celda frm_cel_"+estiloNum+"b\">\r\n");
					buffer.append("		<div class=\"frm_etiqueta\">"+atributoProceso.getWebEtiqueta()+" :</div>\r\n");
					buffer.append("		<div>"+atributo+"</div>\r\n");
					buffer.append("	</div>\r\n");
				}
				
				if(filaActual == atributosLista.size()-1 && (filaActual+2)%2==0){
					buffer.append("\r\n</div>\r\n");
				} else if(filaActual == atributosLista.size()-1 || (filaActual+2)%2!=0){
					buffer.append("</div>\r\n");
				}
				
				filaActual++;
				
			}
		}
		
		return buffer;
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