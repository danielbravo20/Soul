package pe.com.captiva.servicio.util.proceso;

import java.util.ArrayList;
import java.util.List;

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
		
		buffer.append("\t\t\t\t\t<tr>\r\n");
		buffer.append("\t\t\t\t\t\t<th><span class=\"frm_requerido\">(*)</span> Tipo de Fianza :</th>\r\n");
		buffer.append("\t\t\t\t\t\t<td class=\"form-group\" show-errors='{showSuccess: true}'>\r\n");
		buffer.append("\t\t\t\t\t\t\t<select name=\"tipoBusqueda\" ng-model=\"baseIPConfig.data.tipoFianza\" class=\"form-control input-sm\" required>\r\n");
		buffer.append("\t\t\t\t\t\t\t\t<option value=\"LIC\">LICITACION</option>\r\n");
		buffer.append("\t\t\t\t\t\t\t\t<option value=\"REQ\">REQUERIMIENTO</option>\r\n");	
		buffer.append("\t\t\t\t\t\t\t</select>\r\n");
		buffer.append("\t\t\t\t\t\t\t<p class=\"help-block\" ng-if=\"frm_iniciarproceso.tipoBusqueda.$error.required\">Seleccione un valor correcto</p>\r\n");
		buffer.append("\t\t\t\t\t\t</td>\r\n");
		buffer.append("\t\t\t\t\t</tr>\r\n");
		
		buffer.append("\t\t\t\t\t<tr>\r\n");
		buffer.append("\t\t\t\t\t\t<th><span class=\"frm_requerido\">(*)</span> Monto :</th>\r\n");
		buffer.append("\t\t\t\t\t\t<td class=\"form-inline\">\r\n");
		buffer.append("\t\t\t\t\t\t<span class=\"form-group\" show-errors='{showSuccess: true}'>\r\n");
		buffer.append("\t\t\t\t\t\t\t<select name=\"monedaFianza\" ng-model=\"baseIPConfig.data.monedaFianza\" class=\"form-control input-sm\" required>\r\n");
		buffer.append("\t\t\t\t\t\t\t\t<option value=\"USD\">DÓLARES AMERICANOS</option>\r\n");
		buffer.append("\t\t\t\t\t\t\t\t<option value=\"PEN\">NUEVOS SOLES</option>\r\n");
		buffer.append("\t\t\t\t\t\t\t</select>\r\n");
		buffer.append("\t\t\t\t\t\t\t<p class=\"help-block\" ng-if=\"frm_iniciarproceso.monedaFianza.$error.required\">Seleccione un valor correcto</p>\r\n");
		buffer.append("\t\t\t\t\t\t</span>\r\n");
		buffer.append("\t\t\t\t\t\t<span class=\"form-group\" show-errors='{showSuccess: true}'>\r\n");
		buffer.append("\t\t\t\t\t\t\t<input type=\"number\" name=\"montoFianza\" ng-model=\"baseIPConfig.data.montoFianza\" class=\"form-control input-sm\" required>\r\n");
		buffer.append("\t\t\t\t\t\t\t<p class=\"help-block\" ng-if=\"frm_iniciarproceso.montoFianza.$error.required\">Ingrese un valor correcto</p>\r\n");
		buffer.append("\t\t\t\t\t\t</span>\r\n");
		buffer.append("\t\t\t\t\t</td>\r\n");
		buffer.append("\t\t\t\t</tr>\r\n");
		
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
