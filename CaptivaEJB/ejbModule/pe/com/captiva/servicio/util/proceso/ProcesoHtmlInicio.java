package pe.com.captiva.servicio.util.proceso;

import java.util.ArrayList;
import java.util.List;

import pe.com.captiva.bean.AtributoProceso;
import pe.com.captiva.bean.ProcesoBean;
import pe.com.captiva.bean.ProyectoBean;
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
		
		buffer.append("\t\t<div class=\"panel-heading\"><strong>Datos Principales</strong></div>\r\n");
		
		buffer.append("\t\t\t<table class=\"table table-striped\">\r\n");
		buffer.append("\t\t\t\t<tbody>\r\n");
		
		List<AtributoProceso> atributoProcesos = procesoBean.getAtributosEntrada();
		for (AtributoProceso atributoProceso : atributoProcesos) {
			
			buffer.append("\t\t\t\t\t<tr>\r\n");
			if(atributoProceso.isWebFlgRequerido()){
				buffer.append("\t\t\t\t\t\t<th><span class=\"frm_requerido\">(*)</span> "+atributoProceso.getWebEtiqueta()+"</th>\r\n");
			}else{
				buffer.append("\t\t\t\t\t\t<th> "+atributoProceso.getWebEtiqueta()+"</th>\r\n");
			}
			buffer.append("\t\t\t\t\t\t<td class=\"form-group\" show-errors='{showSuccess: true}'>\r\n");
			if(atributoProceso.isWebFlgRequerido()){
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
		buffer.append("</div>\r\n");
		
		return buffer;
	}


	@Override
	public boolean reemplazarArchivoCuandoExiste() {
		return true;
	}

}
