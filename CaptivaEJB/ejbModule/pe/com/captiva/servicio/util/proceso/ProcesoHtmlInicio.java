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
		buffer.append("\t<div ng-controller=\"iniciarproceso\">\r\n");
		
		buffer.append("\t\t<div class=\"panel-heading\"><strong>Datos de Inicio</strong></div>\r\n");
		
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
			buffer.append("			<div class=\"filaTitulo\">&nbsp;&nbsp;<i class=\"glyphicon glyphicon-list-alt\"></i> "+subseccionProceso.getNombre()+"</div>\r\n");
			//System.out.println(" --|---> "+subseccionProceso.getCodigoSubseccion()+" | "+subseccionProceso.getNombre());
			
			int filaActual = 0;
			
			for (AtributoProceso atributoProceso : subseccionProceso.getAtributoProceso()) {
				
				if(filaActual == 0 || filaActual%2==0){
					buffer.append("				<div class=\"frm_fila\">\r\n");
				}
				
				// CONDICIONES
				
				String condRequerido = atributoProceso.isFlgWebRequerido()?"<span class=\"frm_requerido\">(*)</span>":"";
				String atributo = "";
				String tipoSLFormato = "";
				if(atributoProceso.getTipo().equals("java.math.BigDecimal")){
					if(atributoProceso.getWebTipoCampo() == 'L'){
						atributo = "{{"+atributoProceso.getWebNombre()+" | currency: \"\"}}";
					}
					if(atributoProceso.getWebTipoCampo() == 'D'){
						CampoSQLBean campoSQLBean = atributoProceso.getCampoSQLBean();
						
						String maximo = "";
						if(campoSQLBean.getLongitud()>0 && campoSQLBean.getPrecision()>0){
							maximo = "nx-regla=\"decimal\" nx-max-entero=\"13\" nx-max-decimal=\"2\"";
						}
						atributo = "<input type=\"text\" name=\""+atributoProceso.getNombre()+"\" ng-model=\""+atributoProceso.getWebNombre()+"\" "+maximo+" class=\"form-control input-sm\" ng-change=\"validarMontos()\" required>";
					}
				}
				
				if((filaActual+2)%2==0){
					buffer.append("					<div class=\"frm_celda frm_cel_1a\">\r\n");
					buffer.append("						<div class=\"frm_etiqueta\">"+condRequerido+" "+atributoProceso.getWebEtiqueta()+" :</div>\r\n");
					buffer.append("						<div class=\"frm_campo\">"+atributo+"</div>\r\n");
					buffer.append("					</div>");
				}
				if((filaActual+2)%2!=0){
					buffer.append("<div class=\"frm_celda frm_cel_1b\">\r\n");
					buffer.append("						<div class=\"frm_etiqueta\">"+condRequerido+" "+atributoProceso.getWebEtiqueta()+" :</div>\r\n");
					buffer.append("						<div class=\"frm_campo\">"+atributo+"</div>\r\n");
					buffer.append("					</div>\r\n");
				}
				
				if(filaActual == subseccionProceso.getAtributoProceso().size()-1 && (filaActual+2)%2==0){
					buffer.append("\r\n				</div>\r\n");
				}
				if(filaActual == subseccionProceso.getAtributoProceso().size()-1 || (filaActual+2)%2!=0){
					buffer.append("			</div>\r\n");
				}
				
				filaActual++;
				
				//System.out.println(" --||---> "+atributoProceso.getCodProcesoInicio()+" | "+atributoProceso.getWebEtiqueta());
			}
		}
		/*
		for (AtributoProceso atributoProceso : atributoProcesos) {
			
			System.out.println(" --||||---> "+atributoProceso.getCodProcesoInicio()+" | "+atributoProceso.getCodSubSeccion()+" | "+atributoProceso.getWebEtiqueta());
			
			buffer.append("\t\t\t\t\t<tr>\r\n");
			if(atributoProceso.isFlgWebRequerido()){
				buffer.append("\t\t\t\t\t\t<th><span class=\"frm_requerido\">(*)</span> "+atributoProceso.getWebEtiqueta()+"</th>\r\n");
			}else{
				buffer.append("\t\t\t\t\t\t<th> "+atributoProceso.getWebEtiqueta()+"</th>\r\n");
			}
			buffer.append("\t\t\t\t\t\t<td class=\"form-group\" show-errors='{showSuccess: true}'>\r\n");
			if(atributoProceso.isFlgWebRequerido()){
				buffer.append("\t\t\t\t\t\t\t<input type=\"number\" name=\""+atributoProceso.getWebNombre()+"\" ng-model=\"baseIPConfig.data."+atributoProceso.getWebNombre()+"\" class=\"form-control input-sm\" required>\r\n");
				buffer.append("\t\t\t\t\t\t\t<p class=\"help-block\" ng-if=\"frm_iniciarproceso."+atributoProceso.getWebNombre()+".$error.required\">"+atributoProceso.getWebMensajeValidacion()+"</p>\r\n");
			}else{
				buffer.append("\t\t\t\t\t\t\t<input type=\"number\" name=\""+atributoProceso.getWebNombre()+"\" ng-model=\"baseIPConfig.data."+atributoProceso.getWebNombre()+"\" class=\"form-control input-sm\" >\r\n");
			}
			
			buffer.append("\t\t\t\t\t\t</span>\r\n");
			
			buffer.append("\t\t\t\t\t\t</td>\r\n");
			buffer.append("\t\t\t\t\t</tr>\r\n");
			
		}
		
		buffer.append("\t\t\t</tbody>\r\n");
		buffer.append("\t\t</table>\r\n");
		buffer.append("\t</div>\r\n");
		buffer.append("</div>\r\n");*/
		
		return buffer;
		
	}

	@Override
	public boolean reemplazarArchivoCuandoExiste() {
		return true;
	}

}