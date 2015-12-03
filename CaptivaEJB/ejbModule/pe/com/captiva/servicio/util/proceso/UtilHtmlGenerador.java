package pe.com.captiva.servicio.util.proceso;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pe.com.captiva.bean.AtributoProceso;
import pe.com.captiva.bean.CampoSQLBean;

public class UtilHtmlGenerador {
	
	public static final Map<String, Character> tipoClase;
	public static final Map<String, String> mod_variablesDATA;
    static {
        Map<String, Character> clase = new HashMap<String, Character>();
        clase.put("Integer", 'N');
        clase.put("Long", 'N');
        clase.put("long", 'N');
        clase.put("String", 'S');
        clase.put("boolean", 'b');
        clase.put("java.math.BigDecimal", 'B');
        clase.put("java.util.Date",'D');
        clase.put("java.sql.Date", 'D');
        clase.put("java.sql.Timestamp", 'T');
        tipoClase = Collections.unmodifiableMap(clase);
        
        Map<String, String> variablesDATA = new HashMap<String, String>();
        variablesDATA.put("detalleproceso", "baseDPConfig");
        variablesDATA.put("iniciarproceso", "baseIPConfig");
        variablesDATA.put("resumentarea", "baseRTConfig");
        mod_variablesDATA = Collections.unmodifiableMap(variablesDATA);
    }
    // SAS : Modelo Seccion Atributo Detallado
    public static StringBuffer modeloSAD(String tipo,List<Map<String, Object>> subSecciones,List<Map<String, Object>> atributos){
    	return modeloSAD(tipo,subSecciones,atributos,0);
    }
    
    @SuppressWarnings("unchecked")
	public static StringBuffer modeloSAD(String tipo,List<Map<String, Object>> subSecciones,List<Map<String, Object>> atributos,int separador){
    	
    	StringBuffer buffer = new StringBuffer();
    	
    	String sep = "";
    	for (int i = 0; i < separador; i++) {
			sep += "	";
		}
		Map<Integer, Integer> mapSP = new HashMap<Integer, Integer>();
		
		for (int i = 0; i < subSecciones.size(); i++) {
			mapSP.put((Integer) subSecciones.get(i).get("cod_sub_seccion"), i);
		}
		
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
			buffer.append(sep+"<div class=\"filaTitulo\">&nbsp;&nbsp;<i class=\"glyphicon glyphicon-list-alt\"></i> "+subSeccion.get("nombre")+"</div>\r\n");

			int filaActual = 0;
			List<Map<String, Object>> atributosLista = (List<Map<String, Object>>) subSeccion.get("atributos");
			if(atributosLista != null){
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
						buffer.append(sep+"<div class=\"frm_fila\">\r\n");
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
					if(tipoClase.get(atributoProceso.getWebTipo())=='S'){
						
						if(atributoProceso.getWebTipoCampo() == 'L'){
							atributo = "{{"+mod_variablesDATA.get(tipo)+".data."+atributoProceso.getNombre()+"}}";
						}
						if(atributoProceso.getWebTipoCampo() == 'C'){
							atributo = "<input type=\"text\" name=\""+atributoProceso.getNombre()+"\" ng-model=\""+mod_variablesDATA.get(tipo)+".data."+atributoProceso.getNombre()+"\" class=\"form-control input-sm\" "+atriRequerido+">";
						}
						if(atributoProceso.getWebTipoCampo() == 'A'){
							atributo = "<textarea name=\""+atributoProceso.getNombre()+"\" ng-model=\""+mod_variablesDATA.get(tipo)+".data."+atributoProceso.getNombre()+"\" class=\"form-control input-sm\" "+atriRequerido+"></textarea>";
						}
						if(atributoProceso.getWebTipoCampo() == 'E'){
							atributo = "<select name=\""+atributoProceso.getNombre()+"\" ng-model=\""+mod_variablesDATA.get(tipo)+".data."+atributoProceso.getNombre()+"\" class=\"form-control input-sm\" "+atriRequerido+"></select>";
						}
					}
					if(tipoClase.get(atributoProceso.getWebTipo())=='b'){
						if(atributoProceso.getWebTipoCampo() == 'L'){
							atributo = "<span ng-show=\""+mod_variablesDATA.get(tipo)+".data."+atributoProceso.getNombre()+"==true\">Sí</span><span ng-show=\""+mod_variablesDATA.get(tipo)+".data."+atributoProceso.getNombre()+"==false\">No</span>";
						}
						if(atributoProceso.getWebTipoCampo() == 'H'){
							atributo = "<input type=\"checkbox\" ng-model=\""+mod_variablesDATA.get(tipo)+".data."+atributoProceso.getNombre()+"\" class=\"form-control input-sm\" ng-true-value=\"true\" ng-false-value=\"false\" style=\"width:30px\" "+atriRequerido+">";
						}
					}
					if(tipoClase.get(atributoProceso.getWebTipo())=='B'){
						if(atributoProceso.getWebTipoCampo() == 'L'){
							atributo = "{{"+mod_variablesDATA.get(tipo)+".data."+atributoProceso.getNombre()+" | currency: \"\"}}";
						}
						if(atributoProceso.getWebTipoCampo() == 'C'){
							CampoSQLBean campoSQLBean = atributoProceso.getCampoSQLBean();
							
							String maximo = "";
							if(campoSQLBean.getLongitud()>0 && campoSQLBean.getPrecision()>0){
								maximo = "nx-regla=\"decimal\" nx-max-entero=\""+campoSQLBean.getLongitud()+"\" nx-max-decimal=\""+campoSQLBean.getPrecision()+"\"";
							}
							atributo = "<input type=\"text\" name=\""+atributoProceso.getNombre()+"\" ng-model=\""+mod_variablesDATA.get(tipo)+".data."+atributoProceso.getNombre()+"\" "+maximo+" class=\"form-control input-sm\" "+atriRequerido+">";
						}
					}
					if(tipoClase.get(atributoProceso.getWebTipo())=='D'){
						if(atributoProceso.getWebTipoCampo() == 'L'){
							atributo = "{{"+mod_variablesDATA.get(tipo)+".data."+atributoProceso.getNombre()+" | date:'dd/MM/yyyy'}}";
						}
						if(atributoProceso.getWebTipoCampo() == 'C'){
							atributo = "<fecha id=\""+atributoProceso.getNombre()+"\" fecha=\""+mod_variablesDATA.get(tipo)+".data."+atributoProceso.getNombre()+"\" titulo=\""+atributoProceso.getWebMensajeValidacion()+"\" mostrar-error=\""+(atributoProceso.isFlgWebRequerido()?"true":"false")+"\" ></fecha>";
						}
					}
					if(tipoClase.get(atributoProceso.getWebTipo())=='N'){
						if(atributoProceso.getWebTipoCampo() == 'L'){
							atributo = "{{"+mod_variablesDATA.get(tipo)+".data."+atributoProceso.getNombre()+"}}";
						}
						if(atributoProceso.getWebTipoCampo() == 'C'){
							atributo = "<input type=\"number\" name=\""+atributoProceso.getNombre()+"\" ng-model=\""+mod_variablesDATA.get(tipo)+".data."+atributoProceso.getNombre()+"\" class=\"form-control input-sm\" "+atriRequerido+">";
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
						buffer.append(sep+"	<div class=\"frm_celda frm_cel_"+estiloNum+"a\">\r\n");
						buffer.append(sep+"		<div class=\"frm_etiqueta\">"+condRequerido+atributoProceso.getWebEtiqueta()+" :</div>\r\n");
						buffer.append(sep+"		<div "+divRequerido+">"+atributo+mensajeError+"</div>\r\n");
						buffer.append(sep+"	</div>");
					}
					if((filaActual+2)%2!=0){
						buffer.append("<div class=\"frm_celda frm_cel_"+estiloNum+"b\">\r\n");
						buffer.append(sep+"		<div class=\"frm_etiqueta\">"+condRequerido+atributoProceso.getWebEtiqueta()+" :</div>\r\n");
						buffer.append(sep+"		<div "+divRequerido+">"+atributo+mensajeError+"</div>\r\n");
						buffer.append(sep+"	</div>\r\n");
					}
					
					if(filaActual == atributosLista.size()-1 && (filaActual+2)%2==0){
						buffer.append("\r\n"+sep+"</div>\r\n");
					} else if(filaActual == atributosLista.size()-1 || (filaActual+2)%2!=0){
						buffer.append(sep+"</div>\r\n");
					}
					
					filaActual++;
					
				}
			}
		}
    	
    	return buffer;
    }
    
    // SAS : Modelo Seccion Atributo Simple
    public static StringBuffer modeloSAS(String tipo,List<Map<String, Object>> subSecciones,List<Map<String, Object>> atributos){
    	return modeloSAS(tipo,subSecciones,atributos,0);
    }
    
    @SuppressWarnings("unchecked")
	public static StringBuffer modeloSAS(String tipo,List<Map<String, Object>> subSecciones,List<Map<String, Object>> atributos,int separador){
    	     	
    	StringBuffer buffer = new StringBuffer();
    	
    	String sep = "";
    	for (int i = 0; i < separador; i++) {
			sep += "	";
		}
    	
		Map<Integer, Integer> mapSP = new HashMap<Integer, Integer>();
		for (int i = 0; i < subSecciones.size(); i++) {
			mapSP.put((Integer) subSecciones.get(i).get("cod_sub_seccion"), i);
		}
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
			buffer.append(sep+"<div class=\"filaTitulo\">&nbsp;&nbsp;<i class=\"glyphicon glyphicon-list-alt\"></i> "+subSeccion.get("nombre")+"</div>\r\n");

			int filaActual = 0;
			List<Map<String, Object>> atributosLista = (List<Map<String, Object>>) subSeccion.get("atributos");
			for (Map<String, Object> atributoObj : atributosLista) {
				
				AtributoProceso atributoProceso = new AtributoProceso();
				
				atributoProceso.setWebEtiqueta((String) atributoObj.get("web_etiqueta"));
				atributoProceso.setWebTipo((String) atributoObj.get("tipo"));
				atributoProceso.setWebNombre((String) atributoObj.get("web_nombre"));

				if(filaActual == 0 || filaActual%2==0){
					buffer.append(sep+"<div class=\"frm_fila\">\r\n");
				}
				
				String atributo = "";
				if(UtilHtmlGenerador.tipoClase.get(atributoProceso.getWebTipo())=='S'){
					atributo = "{{"+mod_variablesDATA.get(tipo)+".data."+atributoProceso.getWebNombre()+"}}";
				}
				if(UtilHtmlGenerador.tipoClase.get(atributoProceso.getWebTipo())=='b'){
					atributo = "<span ng-show=\""+mod_variablesDATA.get(tipo)+".data."+atributoProceso.getWebNombre()+"==true\">Sí</span><span ng-show=\""+mod_variablesDATA.get(tipo)+".data."+atributoProceso.getWebNombre()+"==false\">No</span>";
				}
				if(UtilHtmlGenerador.tipoClase.get(atributoProceso.getWebTipo())=='B'){
					atributo = "{{"+mod_variablesDATA.get(tipo)+".data."+atributoProceso.getWebNombre()+" | currency: \"\"}}";
				}
				if(UtilHtmlGenerador.tipoClase.get(atributoProceso.getWebTipo())=='D'){
					atributo = "{{"+mod_variablesDATA.get(tipo)+".data."+atributoProceso.getWebNombre()+" | date:'dd/MM/yyyy'}}";
				}
				if(UtilHtmlGenerador.tipoClase.get(atributoProceso.getWebTipo())=='N'){
					atributo = "{{"+mod_variablesDATA.get(tipo)+".data."+atributoProceso.getWebNombre()+"}}";
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
					buffer.append(sep+"	<div class=\"frm_celda frm_cel_"+estiloNum+"a\">\r\n");
					buffer.append(sep+"		<div class=\"frm_etiqueta\">"+atributoProceso.getWebEtiqueta()+" :</div>\r\n");
					buffer.append(sep+"		<div>"+atributo+"</div>\r\n");
					buffer.append(sep+"	</div>");
				}
				if((filaActual+2)%2!=0){
					buffer.append("<div class=\"frm_celda frm_cel_"+estiloNum+"b\">\r\n");
					buffer.append(sep+"		<div class=\"frm_etiqueta\">"+atributoProceso.getWebEtiqueta()+" :</div>\r\n");
					buffer.append(sep+"		<div>"+atributo+"</div>\r\n");
					buffer.append(sep+"	</div>\r\n");
				}
				
				if(filaActual == atributosLista.size()-1 && (filaActual+2)%2==0){
					buffer.append("\r\n"+sep+"</div>\r\n");
				} else if(filaActual == atributosLista.size()-1 || (filaActual+2)%2!=0){
					buffer.append(sep+"</div>\r\n");
				}
				
				filaActual++;
				
			}
			
		}
    	
    	return buffer;
    }
    
}