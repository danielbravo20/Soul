package pe.com.captiva.servicio.util.proceso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pe.com.captiva.bean.AtributoProceso;
import pe.com.captiva.bean.CampoSQLBean;
import pe.com.captiva.bean.ProcesoBean;
import pe.com.captiva.bean.ProyectoBean;
import pe.com.captiva.bean.SubseccionProceso;
import pe.com.captiva.servicio.util.Componente;
import pe.com.captiva.servicio.util.MultipleBaseConstructor;
import pe.com.mapeo.dao.Jpo;

public class ProcesoHtmlInicio extends MultipleBaseConstructor{

	public List<Componente> crear(ProyectoBean proyectoBean) throws Exception{
		List<Componente> componentes = new ArrayList<Componente>();
		
		List<ProcesoBean> procesos = proyectoBean.getProcesos();
		for (int x = 0; x < procesos.size(); x++) {
			ProcesoBean procesoBean = procesos.get(x);
			if(procesoBean!=null){
				String directorio = proyectoBean.getEquipoBean().getDirectorioWorkspace() + "\\" + proyectoBean.getProyecto() + ProyectoBean.SUFIJO_PROYECTO_WEB + "\\WebContent\\portal\\procesos\\" + procesoBean.getAleas() + "\\proceso\\vista\\" ;
				
				Componente componente = new Componente();
				componente.setDirectorio(directorio);
				componente.setArchivo("iniciarproceso.html");
				componente.setContenido(contenido(proyectoBean, procesoBean));
				
				componentes.add(componente);
			}
		}
		
		return componentes;
	}
	
	
	private StringBuffer contenido(ProyectoBean proyectoBean, ProcesoBean procesoBean){
		StringBuffer buffer = new StringBuffer();
		
		buffer.append("<script type=\"text/javascript\" src=\"/"+proyectoBean.getProyecto() + ProyectoBean.SUFIJO_PROYECTO_WEB+"/portal/procesos/" + procesoBean.getAleas() + "/proceso/preControlador/pre_iniciarproceso.js\"></script>\r\n");
		buffer.append("<script type=\"text/javascript\" src=\"/"+proyectoBean.getProyecto() + ProyectoBean.SUFIJO_PROYECTO_WEB+"/portal/procesos/" + procesoBean.getAleas() + "/proceso/controlador/iniciarproceso.js\"></script>\r\n");
		buffer.append("<div ng-controller=\"pre_iniciarproceso\" class=\"panel panel-default\">\r\n");
		buffer.append("	<div ng-controller=\"iniciarproceso\">\r\n");
		
		buffer.append("		<div class=\"panel-heading\"><strong>Datos de Inicio</strong></div>\r\n");
		
		List<SubseccionProceso> subseccionProcesos = procesoBean.getSubseccionEntrada();
		Map<Integer, Integer> mapSP = new HashMap<Integer, Integer>();
		for (int i = 0; i < subseccionProcesos.size(); i++) {
			mapSP.put(subseccionProcesos.get(i).getCodigoSubseccion(), i);
		}
		
		List<AtributoProceso> atributoProcesos = procesoBean.getAtributosEntrada();
		for (AtributoProceso atributoProceso : atributoProcesos) {
			if(mapSP.get(atributoProceso.getCodSubSeccion()) != null){
				int indice = mapSP.get(atributoProceso.getCodSubSeccion());
				List<AtributoProceso> atributoProcesoSP = new ArrayList<AtributoProceso>();
				if(subseccionProcesos.get(indice).getAtributoProceso() != null){
					atributoProcesoSP = subseccionProcesos.get(indice).getAtributoProceso();
				}
				atributoProcesoSP.add(atributoProceso);
				subseccionProcesos.get(indice).setAtributoProceso(atributoProcesoSP);
			}
		}
		
		for (SubseccionProceso subseccionProceso : subseccionProcesos) {
			buffer.append("		<div class=\"filaTitulo\">&nbsp;&nbsp;<i class=\"glyphicon glyphicon-list-alt\"></i> "+subseccionProceso.getNombre()+"</div>\r\n");
			//System.out.println(" --|---> "+subseccionProceso.getCodigoSubseccion()+" | "+subseccionProceso.getNombre());
			
			int filaActual = 0;
			
			for (AtributoProceso atributoProceso : subseccionProceso.getAtributoProceso()) {
				
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
				
				if(filaActual == subseccionProceso.getAtributoProceso().size()-1 && (filaActual+2)%2==0){
					buffer.append("\r\n		</div>\r\n");
				} else if(filaActual == subseccionProceso.getAtributoProceso().size()-1 || (filaActual+2)%2!=0){
					buffer.append("		</div>\r\n");
				}
				
				filaActual++;
				
			}
		}
		
		buffer.append("	</div>\r\n");
		buffer.append("</div>");
		
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